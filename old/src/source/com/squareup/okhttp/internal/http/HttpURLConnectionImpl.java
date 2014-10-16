// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.squareup.okhttp.internal.http;

import com.squareup.okhttp.*;
import com.squareup.okhttp.internal.Platform;
import com.squareup.okhttp.internal.Util;
import com.squareup.okhttp.internal.okio.BufferedSink;
import com.squareup.okhttp.internal.okio.ByteString;
import java.io.*;
import java.net.*;
import java.security.Permission;
import java.util.*;
import java.util.concurrent.TimeUnit;

// Referenced classes of package com.squareup.okhttp.internal.http:
//            HttpEngine, Response, RetryableSink, HttpMethod, 
//            Headers, HttpAuthenticator, Request, OkHeaders, 
//            HttpDate

public class HttpURLConnectionImpl extends HttpURLConnection
{
    static final class Retry extends Enum
    {

        public static Retry valueOf(String s)
        {
            return (Retry)Enum.valueOf(com/squareup/okhttp/internal/http/HttpURLConnectionImpl$Retry, s);
        }

        public static Retry[] values()
        {
            return (Retry[])$VALUES.clone();
        }

        private static final Retry $VALUES[];
        public static final Retry DIFFERENT_CONNECTION;
        public static final Retry NONE;
        public static final Retry SAME_CONNECTION;

        static 
        {
            NONE = new Retry("NONE", 0);
            SAME_CONNECTION = new Retry("SAME_CONNECTION", 1);
            DIFFERENT_CONNECTION = new Retry("DIFFERENT_CONNECTION", 2);
            Retry aretry[] = new Retry[3];
            aretry[0] = NONE;
            aretry[1] = SAME_CONNECTION;
            aretry[2] = DIFFERENT_CONNECTION;
            $VALUES = aretry;
        }

        private Retry(String s, int i)
        {
            super(s, i);
        }
    }


    public HttpURLConnectionImpl(URL url, OkHttpClient okhttpclient)
    {
        super(url);
        requestHeaders = new Headers.Builder();
        fixedContentLength = -1L;
        client = okhttpclient;
    }

    private boolean execute(boolean flag)
        throws IOException
    {
        Handshake handshake1;
        httpEngine.sendRequest();
        route = httpEngine.getRoute();
        if(httpEngine.getConnection() == null)
            break MISSING_BLOCK_LABEL_59;
        handshake1 = httpEngine.getConnection().getHandshake();
_L1:
        handshake = handshake1;
        if(flag)
            try
            {
                httpEngine.readResponse();
            }
            catch(IOException ioexception)
            {
                HttpEngine httpengine = httpEngine.recover(ioexception);
                if(httpengine != null)
                {
                    httpEngine = httpengine;
                    return false;
                } else
                {
                    httpEngineFailure = ioexception;
                    throw ioexception;
                }
            }
        return true;
        handshake1 = null;
          goto _L1
    }

    private HttpEngine getResponse()
        throws IOException
    {
        initHttpEngine();
        if(httpEngine.hasResponse())
            return httpEngine;
        do
        {
            while(!execute(true)) ;
            Retry retry = processResponseHeaders();
            if(retry == Retry.NONE)
            {
                httpEngine.releaseConnection();
                return httpEngine;
            }
            String s = method;
            com.squareup.okhttp.internal.okio.Sink sink = httpEngine.getRequestBody();
            int i = httpEngine.getResponse().code();
            if(i == 300 || i == 301 || i == 302 || i == 303)
            {
                s = "GET";
                requestHeaders.removeAll("Content-Length");
                sink = null;
            }
            if(sink != null && !(sink instanceof RetryableSink))
                throw new HttpRetryException("Cannot retry streamed HTTP body", i);
            if(retry == Retry.DIFFERENT_CONNECTION)
                httpEngine.releaseConnection();
            httpEngine = newHttpEngine(s, httpEngine.close(), (RetryableSink)sink);
        } while(true);
    }

    private void initHttpEngine()
        throws IOException
    {
        if(httpEngineFailure != null)
            throw httpEngineFailure;
        if(httpEngine != null)
            return;
        connected = true;
        if(!doOutput) goto _L2; else goto _L1
_L1:
        if(!method.equals("GET")) goto _L4; else goto _L3
_L3:
        method = "POST";
_L2:
        httpEngine = newHttpEngine(method, null, null);
        return;
        IOException ioexception;
        ioexception;
        httpEngineFailure = ioexception;
        throw ioexception;
_L4:
        if(HttpMethod.hasRequestBody(method)) goto _L2; else goto _L5
_L5:
        throw new ProtocolException((new StringBuilder()).append(method).append(" does not support writing").toString());
    }

    private HttpEngine newHttpEngine(String s, Connection connection, RetryableSink retryablesink)
    {
        Request.Builder builder = (new Request.Builder()).url(getURL()).method(s, null);
        Headers headers = requestHeaders.build();
        for(int i = 0; i < headers.size(); i++)
            builder.addHeader(headers.name(i), headers.value(i));

        boolean flag = HttpMethod.hasRequestBody(s);
        boolean flag1 = false;
        Request request;
        OkHttpClient okhttpclient;
        if(flag)
            if(fixedContentLength != -1L)
                builder.header("Content-Length", Long.toString(fixedContentLength));
            else
            if(chunkLength > 0)
            {
                builder.header("Transfer-Encoding", "chunked");
                flag1 = false;
            } else
            {
                flag1 = true;
            }
        request = builder.build();
        okhttpclient = client;
        if(okhttpclient.getOkResponseCache() != null && !getUseCaches())
            okhttpclient = client.clone().setOkResponseCache(null);
        return new HttpEngine(okhttpclient, request, flag1, connection, null, retryablesink);
    }

    private Retry processResponseHeaders()
        throws IOException
    {
        Connection connection = httpEngine.getConnection();
        Proxy proxy;
        int i;
        if(connection != null)
            proxy = connection.getRoute().getProxy();
        else
            proxy = client.getProxy();
        i = getResponseCode();
        switch(i)
        {
        default:
            return Retry.NONE;

        case 407: 
            if(proxy.type() != java.net.Proxy.Type.HTTP)
                throw new ProtocolException("Received HTTP_PROXY_AUTH (407) code while not using proxy");
            // fall through

        case 401: 
            Request request = HttpAuthenticator.processAuthHeader(client.getAuthenticator(), httpEngine.getResponse(), proxy);
            if(request == null)
            {
                return Retry.NONE;
            } else
            {
                requestHeaders = request.getHeaders().newBuilder();
                return Retry.SAME_CONNECTION;
            }

        case 300: 
        case 301: 
        case 302: 
        case 303: 
        case 307: 
            break;
        }
        if(!getInstanceFollowRedirects())
            return Retry.NONE;
        int j = 1 + redirectionCount;
        redirectionCount = j;
        if(j > 20)
            throw new ProtocolException((new StringBuilder()).append("Too many redirects: ").append(redirectionCount).toString());
        if(i == 307 && !method.equals("GET") && !method.equals("HEAD"))
            return Retry.NONE;
        String s = getHeaderField("Location");
        if(s == null)
            return Retry.NONE;
        URL url = this.url;
        this.url = new URL(url, s);
        if(!this.url.getProtocol().equals("https") && !this.url.getProtocol().equals("http"))
            return Retry.NONE;
        boolean flag = url.getProtocol().equals(this.url.getProtocol());
        if(!flag && !client.getFollowProtocolRedirects())
            return Retry.NONE;
        boolean flag1 = url.getHost().equals(this.url.getHost());
        boolean flag2;
        if(Util.getEffectivePort(url) == Util.getEffectivePort(this.url))
            flag2 = true;
        else
            flag2 = false;
        if(flag1 && flag2 && flag)
            return Retry.SAME_CONNECTION;
        else
            return Retry.DIFFERENT_CONNECTION;
    }

    private void setProtocols(String s, boolean flag)
    {
        ArrayList arraylist = new ArrayList();
        if(flag)
            arraylist.addAll(client.getProtocols());
        String as[] = s.split(",", -1);
        int i = as.length;
        int j = 0;
        while(j < i) 
        {
            String s1 = as[j];
            try
            {
                arraylist.add(Util.getProtocol(ByteString.encodeUtf8(s1)));
            }
            catch(IOException ioexception)
            {
                throw new IllegalStateException(ioexception);
            }
            j++;
        }
        client.setProtocols(arraylist);
    }

    public final void addRequestProperty(String s, String s1)
    {
        if(connected)
            throw new IllegalStateException("Cannot add request property after connection is made");
        if(s == null)
            throw new NullPointerException("field == null");
        if(s1 == null)
        {
            Platform.get().logW((new StringBuilder()).append("Ignoring header ").append(s).append(" because its value was null.").toString());
            return;
        }
        if("X-Android-Transports".equals(s) || "X-Android-Protocols".equals(s))
        {
            setProtocols(s1, true);
            return;
        } else
        {
            requestHeaders.add(s, s1);
            return;
        }
    }

    public final void connect()
        throws IOException
    {
        initHttpEngine();
        while(!execute(false)) ;
    }

    public final void disconnect()
    {
        if(httpEngine == null)
            return;
        try
        {
            httpEngine.disconnect();
            return;
        }
        catch(IOException ioexception)
        {
            return;
        }
    }

    public int getConnectTimeout()
    {
        return client.getConnectTimeout();
    }

    public final InputStream getErrorStream()
    {
        HttpEngine httpengine;
        boolean flag;
        InputStream inputstream;
        int i;
        InputStream inputstream1;
        try
        {
            httpengine = getResponse();
            flag = httpengine.hasResponseBody();
        }
        catch(IOException ioexception)
        {
            return null;
        }
        inputstream = null;
        if(!flag)
            break MISSING_BLOCK_LABEL_47;
        i = httpengine.getResponse().code();
        inputstream = null;
        if(i < 400)
            break MISSING_BLOCK_LABEL_47;
        inputstream1 = httpengine.getResponseBodyBytes();
        inputstream = inputstream1;
        return inputstream;
    }

    public final String getHeaderField(int i)
    {
        String s;
        try
        {
            s = getResponse().getResponse().headers().value(i);
        }
        catch(IOException ioexception)
        {
            return null;
        }
        return s;
    }

    public final String getHeaderField(String s)
    {
        Response response;
        String s1;
        try
        {
            response = getResponse().getResponse();
        }
        catch(IOException ioexception)
        {
            return null;
        }
        if(s != null)
            break MISSING_BLOCK_LABEL_17;
        return response.statusLine();
        s1 = response.headers().get(s);
        return s1;
    }

    public final String getHeaderFieldKey(int i)
    {
        String s;
        try
        {
            s = getResponse().getResponse().headers().name(i);
        }
        catch(IOException ioexception)
        {
            return null;
        }
        return s;
    }

    public final Map getHeaderFields()
    {
        Map map;
        try
        {
            Response response = getResponse().getResponse();
            map = OkHeaders.toMultimap(response.headers(), response.statusLine());
        }
        catch(IOException ioexception)
        {
            return Collections.emptyMap();
        }
        return map;
    }

    public final InputStream getInputStream()
        throws IOException
    {
        if(!doInput)
            throw new ProtocolException("This protocol does not support input");
        HttpEngine httpengine = getResponse();
        if(getResponseCode() >= 400)
            throw new FileNotFoundException(url.toString());
        InputStream inputstream = httpengine.getResponseBodyBytes();
        if(inputstream == null)
            throw new ProtocolException((new StringBuilder()).append("No response body exists; responseCode=").append(getResponseCode()).toString());
        else
            return inputstream;
    }

    public final OutputStream getOutputStream()
        throws IOException
    {
        connect();
        BufferedSink bufferedsink = httpEngine.getBufferedRequestBody();
        if(bufferedsink == null)
            throw new ProtocolException((new StringBuilder()).append("method does not support a request body: ").append(method).toString());
        if(httpEngine.hasResponse())
            throw new ProtocolException("cannot write request body after response has been read");
        else
            return bufferedsink.outputStream();
    }

    public final Permission getPermission()
        throws IOException
    {
        String s = getURL().getHost();
        int i = Util.getEffectivePort(getURL());
        if(usingProxy())
        {
            InetSocketAddress inetsocketaddress = (InetSocketAddress)client.getProxy().address();
            s = inetsocketaddress.getHostName();
            i = inetsocketaddress.getPort();
        }
        return new SocketPermission((new StringBuilder()).append(s).append(":").append(i).toString(), "connect, resolve");
    }

    public int getReadTimeout()
    {
        return client.getReadTimeout();
    }

    public final Map getRequestProperties()
    {
        if(connected)
            throw new IllegalStateException("Cannot access request header fields after connection is set");
        else
            return OkHeaders.toMultimap(requestHeaders.build(), null);
    }

    public final String getRequestProperty(String s)
    {
        if(s == null)
            return null;
        else
            return requestHeaders.get(s);
    }

    public final int getResponseCode()
        throws IOException
    {
        return getResponse().getResponse().code();
    }

    public String getResponseMessage()
        throws IOException
    {
        return getResponse().getResponse().statusMessage();
    }

    public void setConnectTimeout(int i)
    {
        client.setConnectTimeout(i, TimeUnit.MILLISECONDS);
    }

    public void setFixedLengthStreamingMode(int i)
    {
        setFixedLengthStreamingMode(i);
    }

    public void setFixedLengthStreamingMode(long l)
    {
        if(super.connected)
            throw new IllegalStateException("Already connected");
        if(chunkLength > 0)
            throw new IllegalStateException("Already in chunked mode");
        if(l < 0L)
        {
            throw new IllegalArgumentException("contentLength < 0");
        } else
        {
            fixedContentLength = l;
            super.fixedContentLength = (int)Math.min(l, 0x7fffffffL);
            return;
        }
    }

    public void setIfModifiedSince(long l)
    {
        super.setIfModifiedSince(l);
        if(ifModifiedSince != 0L)
        {
            requestHeaders.set("If-Modified-Since", HttpDate.format(new Date(ifModifiedSince)));
            return;
        } else
        {
            requestHeaders.removeAll("If-Modified-Since");
            return;
        }
    }

    public void setReadTimeout(int i)
    {
        client.setReadTimeout(i, TimeUnit.MILLISECONDS);
    }

    public void setRequestMethod(String s)
        throws ProtocolException
    {
        if(!HttpMethod.METHODS.contains(s))
        {
            throw new ProtocolException((new StringBuilder()).append("Expected one of ").append(HttpMethod.METHODS).append(" but was ").append(s).toString());
        } else
        {
            method = s;
            return;
        }
    }

    public final void setRequestProperty(String s, String s1)
    {
        if(connected)
            throw new IllegalStateException("Cannot set request property after connection is made");
        if(s == null)
            throw new NullPointerException("field == null");
        if(s1 == null)
        {
            Platform.get().logW((new StringBuilder()).append("Ignoring header ").append(s).append(" because its value was null.").toString());
            return;
        }
        if("X-Android-Transports".equals(s) || "X-Android-Protocols".equals(s))
        {
            setProtocols(s1, false);
            return;
        } else
        {
            requestHeaders.set(s, s1);
            return;
        }
    }

    public final boolean usingProxy()
    {
        Proxy proxy;
        if(route != null)
            proxy = route.getProxy();
        else
            proxy = client.getProxy();
        return proxy != null && proxy.type() != java.net.Proxy.Type.DIRECT;
    }

    public static final int MAX_REDIRECTS = 20;
    final OkHttpClient client;
    private long fixedContentLength;
    Handshake handshake;
    protected HttpEngine httpEngine;
    protected IOException httpEngineFailure;
    private int redirectionCount;
    private Headers.Builder requestHeaders;
    private Route route;
}
