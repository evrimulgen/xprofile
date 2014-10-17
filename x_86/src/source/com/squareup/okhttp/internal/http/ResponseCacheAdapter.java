// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.squareup.okhttp.internal.http;

import com.squareup.okhttp.*;
import java.io.*;
import java.net.*;
import java.util.*;
import javax.net.ssl.*;

// Referenced classes of package com.squareup.okhttp.internal.http:
//            Response, Request, OkHeaders, Headers, 
//            DelegatingHttpsURLConnection

public class ResponseCacheAdapter
    implements OkResponseCache
{
    private static final class CacheHttpURLConnection extends HttpURLConnection
    {

        public void addRequestProperty(String s, String s1)
        {
            throw ResponseCacheAdapter.throwRequestModificationException();
        }

        public void connect()
            throws IOException
        {
            throw ResponseCacheAdapter.throwRequestModificationException();
        }

        public void disconnect()
        {
            throw ResponseCacheAdapter.throwRequestModificationException();
        }

        public boolean getAllowUserInteraction()
        {
            return false;
        }

        public int getConnectTimeout()
        {
            return 0;
        }

        public Object getContent()
            throws IOException
        {
            throw ResponseCacheAdapter.throwResponseBodyAccessException();
        }

        public Object getContent(Class aclass[])
            throws IOException
        {
            throw ResponseCacheAdapter.throwResponseBodyAccessException();
        }

        public boolean getDefaultUseCaches()
        {
            return super.getDefaultUseCaches();
        }

        public boolean getDoInput()
        {
            return true;
        }

        public boolean getDoOutput()
        {
            return request.body() != null;
        }

        public InputStream getErrorStream()
        {
            return null;
        }

        public String getHeaderField(int i)
        {
            if(i < 0)
                throw new IllegalArgumentException((new StringBuilder()).append("Invalid header index: ").append(i).toString());
            if(i == 0)
                return response.statusLine();
            else
                return response.headers().value(i - 1);
        }

        public String getHeaderField(String s)
        {
            if(s == null)
                return response.statusLine();
            else
                return response.headers().get(s);
        }

        public String getHeaderFieldKey(int i)
        {
            if(i < 0)
                throw new IllegalArgumentException((new StringBuilder()).append("Invalid header index: ").append(i).toString());
            if(i == 0)
                return null;
            else
                return response.headers().name(i - 1);
        }

        public Map getHeaderFields()
        {
            return OkHeaders.toMultimap(response.headers(), response.statusLine());
        }

        public long getIfModifiedSince()
        {
            return 0L;
        }

        public InputStream getInputStream()
            throws IOException
        {
            throw ResponseCacheAdapter.throwResponseBodyAccessException();
        }

        public boolean getInstanceFollowRedirects()
        {
            return super.getInstanceFollowRedirects();
        }

        public OutputStream getOutputStream()
            throws IOException
        {
            throw ResponseCacheAdapter.throwRequestModificationException();
        }

        public int getReadTimeout()
        {
            return 0;
        }

        public String getRequestMethod()
        {
            return request.method();
        }

        public Map getRequestProperties()
        {
            throw ResponseCacheAdapter.throwRequestHeaderAccessException();
        }

        public String getRequestProperty(String s)
        {
            return request.header(s);
        }

        public int getResponseCode()
            throws IOException
        {
            return response.code();
        }

        public String getResponseMessage()
            throws IOException
        {
            return response.statusMessage();
        }

        public boolean getUseCaches()
        {
            return super.getUseCaches();
        }

        public void setAllowUserInteraction(boolean flag)
        {
            throw ResponseCacheAdapter.throwRequestModificationException();
        }

        public void setChunkedStreamingMode(int i)
        {
            throw ResponseCacheAdapter.throwRequestModificationException();
        }

        public void setConnectTimeout(int i)
        {
            throw ResponseCacheAdapter.throwRequestModificationException();
        }

        public void setDefaultUseCaches(boolean flag)
        {
            super.setDefaultUseCaches(flag);
        }

        public void setDoInput(boolean flag)
        {
            throw ResponseCacheAdapter.throwRequestModificationException();
        }

        public void setDoOutput(boolean flag)
        {
            throw ResponseCacheAdapter.throwRequestModificationException();
        }

        public void setFixedLengthStreamingMode(int i)
        {
            throw ResponseCacheAdapter.throwRequestModificationException();
        }

        public void setFixedLengthStreamingMode(long l)
        {
            throw ResponseCacheAdapter.throwRequestModificationException();
        }

        public void setIfModifiedSince(long l)
        {
            throw ResponseCacheAdapter.throwRequestModificationException();
        }

        public void setInstanceFollowRedirects(boolean flag)
        {
            throw ResponseCacheAdapter.throwRequestModificationException();
        }

        public void setReadTimeout(int i)
        {
            throw ResponseCacheAdapter.throwRequestModificationException();
        }

        public void setRequestMethod(String s)
            throws ProtocolException
        {
            throw ResponseCacheAdapter.throwRequestModificationException();
        }

        public void setRequestProperty(String s, String s1)
        {
            throw ResponseCacheAdapter.throwRequestModificationException();
        }

        public void setUseCaches(boolean flag)
        {
            throw ResponseCacheAdapter.throwRequestModificationException();
        }

        public boolean usingProxy()
        {
            return false;
        }

        private final Request request;
        private final Response response;


        public CacheHttpURLConnection(Response response1)
        {
            boolean flag = true;
            super(response1.request().url());
            request = response1.request();
            response = response1;
            connected = flag;
            if(response1.body() != null)
                flag = false;
            doOutput = flag;
            method = request.method();
        }
    }

    private static final class CacheHttpsURLConnection extends DelegatingHttpsURLConnection
    {

        public long getContentLengthLong()
        {
            return _flddelegate.getContentLengthLong();
        }

        public long getHeaderFieldLong(String s, long l)
        {
            return _flddelegate.getHeaderFieldLong(s, l);
        }

        public HostnameVerifier getHostnameVerifier()
        {
            throw ResponseCacheAdapter.throwRequestSslAccessException();
        }

        public SSLSocketFactory getSSLSocketFactory()
        {
            throw ResponseCacheAdapter.throwRequestSslAccessException();
        }

        protected Handshake handshake()
        {
            return _flddelegate.response.handshake();
        }

        public void setFixedLengthStreamingMode(long l)
        {
            _flddelegate.setFixedLengthStreamingMode(l);
        }

        public void setHostnameVerifier(HostnameVerifier hostnameverifier)
        {
            throw ResponseCacheAdapter.throwRequestModificationException();
        }

        public void setSSLSocketFactory(SSLSocketFactory sslsocketfactory)
        {
            throw ResponseCacheAdapter.throwRequestModificationException();
        }

        private final CacheHttpURLConnection _flddelegate;

        public CacheHttpsURLConnection(CacheHttpURLConnection cachehttpurlconnection)
        {
            super(cachehttpurlconnection);
            _flddelegate = cachehttpurlconnection;
        }
    }


    public ResponseCacheAdapter(ResponseCache responsecache)
    {
        _flddelegate = responsecache;
    }

    private static HttpURLConnection createJavaUrlConnection(Response response)
    {
        if(response.request().isHttps())
            return new CacheHttpsURLConnection(new CacheHttpURLConnection(response));
        else
            return new CacheHttpURLConnection(response);
    }

    private static Response.Body createOkBody(Headers headers, InputStream inputstream)
    {
        return new Response.Body(headers, inputstream) {

            public InputStream byteStream()
            {
                return body;
            }

            public long contentLength()
            {
                return OkHeaders.contentLength(okHeaders);
            }

            public MediaType contentType()
            {
                String s = okHeaders.get("Content-Type");
                if(s == null)
                    return null;
                else
                    return MediaType.parse(s);
            }

            public boolean ready()
                throws IOException
            {
                return true;
            }

            final InputStream val$body;
            final Headers val$okHeaders;

            
            {
                okHeaders = headers;
                body = inputstream;
                super();
            }
        }
;
    }

    private static Map extractJavaHeaders(Request request)
    {
        return OkHeaders.toMultimap(request.headers(), null);
    }

    private static Headers extractOkHeaders(CacheResponse cacheresponse)
        throws IOException
    {
        Map map = cacheresponse.getHeaders();
        Headers.Builder builder = new Headers.Builder();
        Iterator iterator = map.entrySet().iterator();
        do
        {
            if(!iterator.hasNext())
                break;
            java.util.Map.Entry entry = (java.util.Map.Entry)iterator.next();
            String s = (String)entry.getKey();
            if(s != null)
            {
                Iterator iterator1 = ((List)entry.getValue()).iterator();
                while(iterator1.hasNext()) 
                    builder.add(s, (String)iterator1.next());
            }
        } while(true);
        return builder.build();
    }

    private static String extractStatusLine(CacheResponse cacheresponse)
        throws IOException
    {
        List list = (List)cacheresponse.getHeaders().get(null);
        if(list == null || list.size() == 0)
            return null;
        else
            return (String)list.get(0);
    }

    private CacheResponse getJavaCachedResponse(Request request)
        throws IOException
    {
        Map map = extractJavaHeaders(request);
        return _flddelegate.get(request.uri(), request.method(), map);
    }

    private static RuntimeException throwRequestHeaderAccessException()
    {
        throw new UnsupportedOperationException("ResponseCache cannot access request headers");
    }

    private static RuntimeException throwRequestModificationException()
    {
        throw new UnsupportedOperationException("ResponseCache cannot modify the request.");
    }

    private static RuntimeException throwRequestSslAccessException()
    {
        throw new UnsupportedOperationException("ResponseCache cannot access SSL internals");
    }

    private static RuntimeException throwResponseBodyAccessException()
    {
        throw new UnsupportedOperationException("ResponseCache cannot access the response body.");
    }

    public Response get(Request request)
        throws IOException
    {
        CacheResponse cacheresponse;
        Response.Builder builder;
        cacheresponse = getJavaCachedResponse(request);
        if(cacheresponse == null)
            return null;
        builder = new Response.Builder();
        builder.request(request);
        builder.statusLine(extractStatusLine(cacheresponse));
        Headers headers = extractOkHeaders(cacheresponse);
        builder.headers(headers);
        builder.setResponseSource(ResponseSource.CACHE);
        builder.body(createOkBody(headers, cacheresponse.getBody()));
        if(!(cacheresponse instanceof SecureCacheResponse)) goto _L2; else goto _L1
_L1:
        SecureCacheResponse securecacheresponse = (SecureCacheResponse)cacheresponse;
        List list2 = securecacheresponse.getServerCertificateChain();
        List list = list2;
_L4:
        List list1 = securecacheresponse.getLocalCertificateChain();
        if(list1 == null)
            list1 = Collections.emptyList();
        builder.handshake(Handshake.get(securecacheresponse.getCipherSuite(), list, list1));
_L2:
        return builder.build();
        SSLPeerUnverifiedException sslpeerunverifiedexception;
        sslpeerunverifiedexception;
        list = Collections.emptyList();
        if(true) goto _L4; else goto _L3
_L3:
    }

    public ResponseCache getDelegate()
    {
        return _flddelegate;
    }

    public boolean maybeRemove(Request request)
        throws IOException
    {
        return false;
    }

    public CacheRequest put(Response response)
        throws IOException
    {
        java.net.URI uri = response.request().uri();
        HttpURLConnection httpurlconnection = createJavaUrlConnection(response);
        return _flddelegate.put(uri, httpurlconnection);
    }

    public void trackConditionalCacheHit()
    {
    }

    public void trackResponse(ResponseSource responsesource)
    {
    }

    public void update(Response response, Response response1)
        throws IOException
    {
    }

    private final ResponseCache _flddelegate;




}
