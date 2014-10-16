// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.squareup.okhttp.internal.http;

import com.squareup.okhttp.*;
import com.squareup.okhttp.internal.Dns;
import com.squareup.okhttp.internal.Util;
import com.squareup.okhttp.internal.okio.*;
import java.io.IOException;
import java.io.InputStream;
import java.net.*;
import java.security.cert.CertificateException;
import javax.net.ssl.SSLHandshakeException;

// Referenced classes of package com.squareup.okhttp.internal.http:
//            Response, Headers, Request, RouteSelector, 
//            CacheStrategy, OkHeaders, Transport, HttpMethod, 
//            RetryableSink

public class HttpEngine
{

    public HttpEngine(OkHttpClient okhttpclient, Request request1, boolean flag, Connection connection1, RouteSelector routeselector, RetryableSink retryablesink)
    {
        sentRequestMillis = -1L;
        client = okhttpclient;
        originalRequest = request1;
        request = request1;
        bufferRequestBody = flag;
        connection = connection1;
        routeSelector = routeselector;
        requestBodyOut = retryablesink;
        if(connection1 != null)
        {
            connection1.setOwner(this);
            route = connection1.getRoute();
            return;
        } else
        {
            route = null;
            return;
        }
    }

    private Response cacheableResponse()
    {
        return response.newBuilder().body(null).build();
    }

    private static Response combine(Response response1, Response response2)
        throws IOException
    {
        Headers.Builder builder = new Headers.Builder();
        Headers headers = response1.headers();
        int i = 0;
        do
        {
            if(i >= headers.size())
                break;
            String s1 = headers.name(i);
            String s2 = headers.value(i);
            if((!"Warning".equals(s1) || !s2.startsWith("1")) && (!isEndToEnd(s1) || response2.header(s1) == null))
                builder.add(s1, s2);
            i++;
        } while(true);
        Headers headers1 = response2.headers();
        for(int j = 0; j < headers1.size(); j++)
        {
            String s = headers1.name(j);
            if(isEndToEnd(s))
                builder.add(s, headers1.value(j));
        }

        return response1.newBuilder().headers(builder.build()).build();
    }

    private void connect()
        throws IOException
    {
        if(connection != null)
            throw new IllegalStateException();
        if(routeSelector == null)
        {
            String s = request.url().getHost();
            if(s == null || s.length() == 0)
                throw new UnknownHostException(request.url().toString());
            boolean flag = request.isHttps();
            javax.net.ssl.SSLSocketFactory sslsocketfactory = null;
            javax.net.ssl.HostnameVerifier hostnameverifier = null;
            if(flag)
            {
                sslsocketfactory = client.getSslSocketFactory();
                hostnameverifier = client.getHostnameVerifier();
            }
            routeSelector = new RouteSelector(new Address(s, Util.getEffectivePort(request.url()), sslsocketfactory, hostnameverifier, client.getAuthenticator(), client.getProxy(), client.getProtocols()), request.uri(), client.getProxySelector(), client.getConnectionPool(), Dns.DEFAULT, client.getRoutesDatabase());
        }
        connection = routeSelector.next(request.method());
        connection.setOwner(this);
        if(connection.isConnected()) goto _L2; else goto _L1
_L1:
        connection.connect(client.getConnectTimeout(), client.getReadTimeout(), getTunnelConfig());
        if(connection.isSpdy())
            client.getConnectionPool().share(connection);
        client.getRoutesDatabase().connected(connection.getRoute());
_L4:
        route = connection.getRoute();
        return;
_L2:
        if(!connection.isSpdy())
            connection.updateReadTimeout(client.getReadTimeout());
        if(true) goto _L4; else goto _L3
_L3:
    }

    public static String getDefaultUserAgent()
    {
        String s = System.getProperty("http.agent");
        if(s != null)
            return s;
        else
            return (new StringBuilder()).append("Java").append(System.getProperty("java.version")).toString();
    }

    private TunnelRequest getTunnelConfig()
    {
        if(!request.isHttps())
            return null;
        String s = request.getUserAgent();
        if(s == null)
            s = getDefaultUserAgent();
        URL url = request.url();
        return new TunnelRequest(url.getHost(), Util.getEffectivePort(url), s, request.getProxyAuthorization());
    }

    public static String hostHeader(URL url)
    {
        if(Util.getEffectivePort(url) != Util.getDefaultPort(url.getProtocol()))
            return (new StringBuilder()).append(url.getHost()).append(":").append(url.getPort()).toString();
        else
            return url.getHost();
    }

    private void initContentStream(Source source)
        throws IOException
    {
        responseTransferSource = source;
        if(transparentGzip && "gzip".equalsIgnoreCase(response.header("Content-Encoding")))
        {
            response = response.newBuilder().removeHeader("Content-Encoding").removeHeader("Content-Length").build();
            responseBody = new GzipSource(source);
            return;
        } else
        {
            responseBody = source;
            return;
        }
    }

    private static boolean isEndToEnd(String s)
    {
        return !"Connection".equalsIgnoreCase(s) && !"Keep-Alive".equalsIgnoreCase(s) && !"Proxy-Authenticate".equalsIgnoreCase(s) && !"Proxy-Authorization".equalsIgnoreCase(s) && !"TE".equalsIgnoreCase(s) && !"Trailers".equalsIgnoreCase(s) && !"Transfer-Encoding".equalsIgnoreCase(s) && !"Upgrade".equalsIgnoreCase(s);
    }

    private boolean isRecoverable(IOException ioexception)
    {
        boolean flag;
        boolean flag1;
        if((ioexception instanceof SSLHandshakeException) && (ioexception.getCause() instanceof CertificateException))
            flag = true;
        else
            flag = false;
        flag1 = ioexception instanceof ProtocolException;
        return !flag && !flag1;
    }

    private void maybeCache()
        throws IOException
    {
        OkResponseCache okresponsecache = client.getOkResponseCache();
        if(okresponsecache == null)
            return;
        if(!CacheStrategy.isCacheable(response, request))
        {
            okresponsecache.maybeRemove(request);
            return;
        } else
        {
            cacheRequest = okresponsecache.put(cacheableResponse());
            return;
        }
    }

    private void prepareRawRequestHeaders()
        throws IOException
    {
        Request.Builder builder = request.newBuilder();
        if(request.getUserAgent() == null)
            builder.setUserAgent(getDefaultUserAgent());
        if(request.header("Host") == null)
            builder.header("Host", hostHeader(request.url()));
        if((connection == null || connection.getHttpMinorVersion() != 0) && request.header("Connection") == null)
            builder.header("Connection", "Keep-Alive");
        if(request.header("Accept-Encoding") == null)
        {
            transparentGzip = true;
            builder.header("Accept-Encoding", "gzip");
        }
        if(hasRequestBody() && request.header("Content-Type") == null)
            builder.header("Content-Type", "application/x-www-form-urlencoded");
        CookieHandler cookiehandler = client.getCookieHandler();
        if(cookiehandler != null)
        {
            java.util.Map map = OkHeaders.toMultimap(builder.build().headers(), null);
            OkHeaders.addCookies(builder, cookiehandler.get(request.uri(), map));
        }
        request = builder.build();
    }

    public final Connection close()
    {
        if(bufferedRequestBody == null) goto _L2; else goto _L1
_L1:
        Util.closeQuietly(bufferedRequestBody);
_L4:
        if(responseBody == null)
        {
            Util.closeQuietly(connection);
            connection = null;
            return null;
        }
        break; /* Loop/switch isn't completed */
_L2:
        if(requestBodyOut != null)
            Util.closeQuietly(requestBodyOut);
        if(true) goto _L4; else goto _L3
_L3:
        Util.closeQuietly(responseBody);
        Util.closeQuietly(responseBodyBytes);
        if(transport != null && !transport.canReuseConnection())
        {
            Util.closeQuietly(connection);
            connection = null;
            return null;
        }
        if(connection != null && !connection.clearOwner())
            connection = null;
        Connection connection1 = connection;
        connection = null;
        return connection1;
    }

    public final void disconnect()
        throws IOException
    {
        if(transport != null)
            transport.disconnect(this);
    }

    public final BufferedSink getBufferedRequestBody()
    {
        BufferedSink bufferedsink = bufferedRequestBody;
        if(bufferedsink != null)
            return bufferedsink;
        Sink sink = getRequestBody();
        BufferedSink bufferedsink1;
        if(sink != null)
        {
            bufferedsink1 = Okio.buffer(sink);
            bufferedRequestBody = bufferedsink1;
        } else
        {
            bufferedsink1 = null;
        }
        return bufferedsink1;
    }

    public final Connection getConnection()
    {
        return connection;
    }

    public final Request getRequest()
    {
        return request;
    }

    public final Sink getRequestBody()
    {
        if(responseSource == null)
            throw new IllegalStateException();
        else
            return requestBodyOut;
    }

    public final Response getResponse()
    {
        if(response == null)
            throw new IllegalStateException();
        else
            return response;
    }

    public final Source getResponseBody()
    {
        if(response == null)
            throw new IllegalStateException();
        else
            return responseBody;
    }

    public final InputStream getResponseBodyBytes()
    {
        InputStream inputstream = responseBodyBytes;
        if(inputstream != null)
        {
            return inputstream;
        } else
        {
            InputStream inputstream1 = Okio.buffer(getResponseBody()).inputStream();
            responseBodyBytes = inputstream1;
            return inputstream1;
        }
    }

    public Route getRoute()
    {
        return route;
    }

    boolean hasRequestBody()
    {
        return HttpMethod.hasRequestBody(request.method());
    }

    public final boolean hasResponse()
    {
        return response != null;
    }

    public final boolean hasResponseBody()
    {
        if(!request.method().equals("HEAD"))
        {
            int i = response.code();
            if((i < 100 || i >= 200) && i != 204 && i != 304)
                return true;
            if(OkHeaders.contentLength(response) != -1L || "chunked".equalsIgnoreCase(response.header("Transfer-Encoding")))
                return true;
        }
        return false;
    }

    public final void readResponse()
        throws IOException
    {
        if(response == null) goto _L2; else goto _L1
_L1:
        return;
_L2:
        if(responseSource == null)
            throw new IllegalStateException("call sendRequest() first!");
        if(!responseSource.requiresConnection())
            continue; /* Loop/switch isn't completed */
        if(bufferedRequestBody != null && bufferedRequestBody.buffer().size() > 0L)
            bufferedRequestBody.flush();
        if(sentRequestMillis == -1L)
        {
            if(OkHeaders.contentLength(request) == -1L && (requestBodyOut instanceof RetryableSink))
            {
                long l = ((RetryableSink)requestBodyOut).contentLength();
                request = request.newBuilder().header("Content-Length", Long.toString(l)).build();
            }
            transport.writeRequestHeaders(request);
        }
        if(requestBodyOut != null)
        {
            OkResponseCache okresponsecache;
            if(bufferedRequestBody != null)
                bufferedRequestBody.close();
            else
                requestBodyOut.close();
            if(requestBodyOut instanceof RetryableSink)
                transport.writeRequestBody((RetryableSink)requestBodyOut);
        }
        transport.flushRequest();
        response = transport.readResponseHeaders().request(request).handshake(connection.getHandshake()).header(OkHeaders.SENT_MILLIS, Long.toString(sentRequestMillis)).header(OkHeaders.RECEIVED_MILLIS, Long.toString(System.currentTimeMillis())).setResponseSource(responseSource).build();
        connection.setHttpMinorVersion(response.httpMinorVersion());
        receiveHeaders(response.headers());
        if(responseSource != ResponseSource.CONDITIONAL_CACHE)
            break MISSING_BLOCK_LABEL_427;
        if(!validatingResponse.validate(response))
            break; /* Loop/switch isn't completed */
        transport.emptyTransferStream();
        releaseConnection();
        response = combine(validatingResponse, response);
        okresponsecache = client.getOkResponseCache();
        okresponsecache.trackConditionalCacheHit();
        okresponsecache.update(validatingResponse, cacheableResponse());
        if(validatingResponse.body() != null)
        {
            initContentStream(validatingResponse.body().source());
            return;
        }
        if(true) goto _L1; else goto _L3
_L3:
        Util.closeQuietly(validatingResponse.body());
        if(!hasResponseBody())
        {
            responseTransferSource = transport.getTransferStream(cacheRequest);
            responseBody = responseTransferSource;
            return;
        } else
        {
            maybeCache();
            initContentStream(transport.getTransferStream(cacheRequest));
            return;
        }
    }

    public void receiveHeaders(Headers headers)
        throws IOException
    {
        CookieHandler cookiehandler = client.getCookieHandler();
        if(cookiehandler != null)
            cookiehandler.put(request.uri(), OkHeaders.toMultimap(headers, null));
    }

    public HttpEngine recover(IOException ioexception)
    {
        if(routeSelector != null && connection != null)
            routeSelector.connectFailed(connection, ioexception);
        boolean flag;
        if(requestBodyOut == null || (requestBodyOut instanceof RetryableSink))
            flag = true;
        else
            flag = false;
        if(routeSelector == null && connection == null || routeSelector != null && !routeSelector.hasNext() || !isRecoverable(ioexception) || !flag)
        {
            return null;
        } else
        {
            Connection connection1 = close();
            return new HttpEngine(client, originalRequest, bufferRequestBody, connection1, routeSelector, (RetryableSink)requestBodyOut);
        }
    }

    public final void releaseConnection()
        throws IOException
    {
        if(transport != null && connection != null)
            transport.releaseConnectionOnIdle();
        connection = null;
    }

    public final ResponseSource responseSource()
    {
        return responseSource;
    }

    public final void sendRequest()
        throws IOException
    {
        if(responseSource == null) goto _L2; else goto _L1
_L1:
        return;
_L2:
        if(transport != null)
            throw new IllegalStateException();
        prepareRawRequestHeaders();
        OkResponseCache okresponsecache = client.getOkResponseCache();
        Response response1;
        CacheStrategy cachestrategy;
        if(okresponsecache != null)
            response1 = okresponsecache.get(request);
        else
            response1 = null;
        cachestrategy = (new CacheStrategy.Factory(System.currentTimeMillis(), request, response1)).get();
        responseSource = cachestrategy.source;
        request = cachestrategy.request;
        if(okresponsecache != null)
            okresponsecache.trackResponse(responseSource);
        if(responseSource != ResponseSource.NETWORK)
            validatingResponse = cachestrategy.response;
        if(response1 != null && !responseSource.usesCache())
            Util.closeQuietly(response1.body());
        if(!responseSource.requiresConnection())
            break; /* Loop/switch isn't completed */
        if(connection == null)
            connect();
        if(connection.getOwner() != this && !connection.isSpdy())
            throw new AssertionError();
        transport = (Transport)connection.newTransport(this);
        if(hasRequestBody() && requestBodyOut == null)
        {
            requestBodyOut = transport.createRequestBody(request);
            return;
        }
        if(true) goto _L1; else goto _L3
_L3:
        if(connection != null)
        {
            client.getConnectionPool().recycle(connection);
            connection = null;
        }
        response = validatingResponse;
        if(validatingResponse.body() != null)
        {
            initContentStream(validatingResponse.body().source());
            return;
        }
        if(true) goto _L1; else goto _L4
_L4:
    }

    public void writingRequestHeaders()
    {
        if(sentRequestMillis != -1L)
        {
            throw new IllegalStateException();
        } else
        {
            sentRequestMillis = System.currentTimeMillis();
            return;
        }
    }

    public final boolean bufferRequestBody;
    private BufferedSink bufferedRequestBody;
    private CacheRequest cacheRequest;
    final OkHttpClient client;
    private Connection connection;
    private Request originalRequest;
    private Request request;
    private Sink requestBodyOut;
    private Response response;
    private Source responseBody;
    private InputStream responseBodyBytes;
    private ResponseSource responseSource;
    private Source responseTransferSource;
    private Route route;
    private RouteSelector routeSelector;
    long sentRequestMillis;
    private boolean transparentGzip;
    private Transport transport;
    private Response validatingResponse;
}
