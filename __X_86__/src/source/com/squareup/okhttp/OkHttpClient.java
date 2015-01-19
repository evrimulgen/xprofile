// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.squareup.okhttp;

import com.squareup.okhttp.internal.Util;
import com.squareup.okhttp.internal.http.HttpAuthenticator;
import com.squareup.okhttp.internal.http.HttpURLConnectionImpl;
import com.squareup.okhttp.internal.http.HttpsURLConnectionImpl;
import com.squareup.okhttp.internal.http.ResponseCacheAdapter;
import com.squareup.okhttp.internal.okio.ByteString;
import com.squareup.okhttp.internal.tls.OkHostnameVerifier;
import java.io.IOException;
import java.net.*;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import javax.net.ssl.*;

// Referenced classes of package com.squareup.okhttp:
//            RouteDatabase, OkResponseCache, ConnectionPool, Protocol, 
//            OkAuthenticator

public final class OkHttpClient
    implements URLStreamHandlerFactory, Cloneable
{

    public OkHttpClient()
    {
        followProtocolRedirects = true;
    }

    private SSLSocketFactory getDefaultSSLSocketFactory()
    {
        this;
        JVM INSTR monitorenter ;
        SSLSocketFactory sslsocketfactory = sslSocketFactory;
        if(sslsocketfactory != null)
            break MISSING_BLOCK_LABEL_35;
        SSLContext sslcontext = SSLContext.getInstance("TLS");
        sslcontext.init(null, null, null);
        sslSocketFactory = sslcontext.getSocketFactory();
        SSLSocketFactory sslsocketfactory1 = sslSocketFactory;
        this;
        JVM INSTR monitorexit ;
        return sslsocketfactory1;
        GeneralSecurityException generalsecurityexception;
        generalsecurityexception;
        throw new AssertionError();
        Exception exception;
        exception;
        this;
        JVM INSTR monitorexit ;
        throw exception;
    }

    private OkResponseCache toOkResponseCache(ResponseCache responsecache)
    {
        if(responsecache == null || (responsecache instanceof OkResponseCache))
            return (OkResponseCache)responsecache;
        else
            return new ResponseCacheAdapter(responsecache);
    }

    public OkHttpClient clone()
    {
        OkHttpClient okhttpclient;
        try
        {
            okhttpclient = (OkHttpClient)super.clone();
        }
        catch(CloneNotSupportedException clonenotsupportedexception)
        {
            throw new AssertionError();
        }
        return okhttpclient;
    }

    public volatile Object clone()
        throws CloneNotSupportedException
    {
        return clone();
    }

    OkHttpClient copyWithDefaults()
    {
        OkHttpClient okhttpclient = clone();
        if(okhttpclient.proxySelector == null)
            okhttpclient.proxySelector = ProxySelector.getDefault();
        if(okhttpclient.cookieHandler == null)
            okhttpclient.cookieHandler = CookieHandler.getDefault();
        if(okhttpclient.responseCache == null)
            okhttpclient.responseCache = toOkResponseCache(ResponseCache.getDefault());
        if(okhttpclient.sslSocketFactory == null)
            okhttpclient.sslSocketFactory = getDefaultSSLSocketFactory();
        if(okhttpclient.hostnameVerifier == null)
            okhttpclient.hostnameVerifier = OkHostnameVerifier.INSTANCE;
        if(okhttpclient.authenticator == null)
            okhttpclient.authenticator = HttpAuthenticator.SYSTEM_DEFAULT;
        if(okhttpclient.connectionPool == null)
            okhttpclient.connectionPool = ConnectionPool.getDefault();
        if(okhttpclient.protocols == null)
            okhttpclient.protocols = Util.HTTP2_SPDY3_AND_HTTP;
        return okhttpclient;
    }

    public URLStreamHandler createURLStreamHandler(final String protocol)
    {
        if(!protocol.equals("http") && !protocol.equals("https"))
            return null;
        else
            return new URLStreamHandler() {

                protected int getDefaultPort()
                {
                    if(protocol.equals("http"))
                        return 80;
                    if(protocol.equals("https"))
                        return 443;
                    else
                        throw new AssertionError();
                }

                protected URLConnection openConnection(URL url)
                {
                    return open(url);
                }

                protected URLConnection openConnection(URL url, Proxy proxy1)
                {
                    return open(url, proxy1);
                }

                final OkHttpClient this$0;
                final String val$protocol;

            
            {
                this$0 = OkHttpClient.this;
                protocol = s;
                super();
            }
            }
;
    }

    public OkAuthenticator getAuthenticator()
    {
        return authenticator;
    }

    public int getConnectTimeout()
    {
        return connectTimeout;
    }

    public ConnectionPool getConnectionPool()
    {
        return connectionPool;
    }

    public CookieHandler getCookieHandler()
    {
        return cookieHandler;
    }

    public boolean getFollowProtocolRedirects()
    {
        return followProtocolRedirects;
    }

    public HostnameVerifier getHostnameVerifier()
    {
        return hostnameVerifier;
    }

    public OkResponseCache getOkResponseCache()
    {
        return responseCache;
    }

    public List getProtocols()
    {
        return protocols;
    }

    public Proxy getProxy()
    {
        return proxy;
    }

    public ProxySelector getProxySelector()
    {
        return proxySelector;
    }

    public int getReadTimeout()
    {
        return readTimeout;
    }

    public ResponseCache getResponseCache()
    {
        if(responseCache instanceof ResponseCacheAdapter)
            return ((ResponseCacheAdapter)responseCache).getDelegate();
        else
            return null;
    }

    public RouteDatabase getRoutesDatabase()
    {
        return routeDatabase;
    }

    public SSLSocketFactory getSslSocketFactory()
    {
        return sslSocketFactory;
    }

    public List getTransports()
    {
        ArrayList arraylist = new ArrayList(protocols.size());
        int i = 0;
        for(int j = protocols.size(); i < j; i++)
            arraylist.add(((Protocol)protocols.get(i)).name.utf8());

        return arraylist;
    }

    public HttpURLConnection open(URL url)
    {
        return open(url, proxy);
    }

    HttpURLConnection open(URL url, Proxy proxy1)
    {
        String s = url.getProtocol();
        OkHttpClient okhttpclient = copyWithDefaults();
        okhttpclient.proxy = proxy1;
        if(s.equals("http"))
            return new HttpURLConnectionImpl(url, okhttpclient);
        if(s.equals("https"))
            return new HttpsURLConnectionImpl(url, okhttpclient);
        else
            throw new IllegalArgumentException((new StringBuilder()).append("Unexpected protocol: ").append(s).toString());
    }

    public OkHttpClient setAuthenticator(OkAuthenticator okauthenticator)
    {
        authenticator = okauthenticator;
        return this;
    }

    public void setConnectTimeout(long l, TimeUnit timeunit)
    {
        if(l < 0L)
            throw new IllegalArgumentException("timeout < 0");
        if(timeunit == null)
            throw new IllegalArgumentException("unit == null");
        long l1 = timeunit.toMillis(l);
        if(l1 > 0x7fffffffL)
        {
            throw new IllegalArgumentException("Timeout too large.");
        } else
        {
            connectTimeout = (int)l1;
            return;
        }
    }

    public OkHttpClient setConnectionPool(ConnectionPool connectionpool)
    {
        connectionPool = connectionpool;
        return this;
    }

    public OkHttpClient setCookieHandler(CookieHandler cookiehandler)
    {
        cookieHandler = cookiehandler;
        return this;
    }

    public OkHttpClient setFollowProtocolRedirects(boolean flag)
    {
        followProtocolRedirects = flag;
        return this;
    }

    public OkHttpClient setHostnameVerifier(HostnameVerifier hostnameverifier)
    {
        hostnameVerifier = hostnameverifier;
        return this;
    }

    public OkHttpClient setOkResponseCache(OkResponseCache okresponsecache)
    {
        responseCache = okresponsecache;
        return this;
    }

    public OkHttpClient setProtocols(List list)
    {
        List list1 = Util.immutableList(list);
        if(!list1.contains(Protocol.HTTP_11))
            throw new IllegalArgumentException((new StringBuilder()).append("protocols doesn't contain http/1.1: ").append(list1).toString());
        if(list1.contains(null))
        {
            throw new IllegalArgumentException("protocols must not contain null");
        } else
        {
            protocols = Util.immutableList(list1);
            return this;
        }
    }

    public OkHttpClient setProxy(Proxy proxy1)
    {
        proxy = proxy1;
        return this;
    }

    public OkHttpClient setProxySelector(ProxySelector proxyselector)
    {
        proxySelector = proxyselector;
        return this;
    }

    public void setReadTimeout(long l, TimeUnit timeunit)
    {
        if(l < 0L)
            throw new IllegalArgumentException("timeout < 0");
        if(timeunit == null)
            throw new IllegalArgumentException("unit == null");
        long l1 = timeunit.toMillis(l);
        if(l1 > 0x7fffffffL)
        {
            throw new IllegalArgumentException("Timeout too large.");
        } else
        {
            readTimeout = (int)l1;
            return;
        }
    }

    public OkHttpClient setResponseCache(ResponseCache responsecache)
    {
        return setOkResponseCache(toOkResponseCache(responsecache));
    }

    public OkHttpClient setSslSocketFactory(SSLSocketFactory sslsocketfactory)
    {
        sslSocketFactory = sslsocketfactory;
        return this;
    }

    public OkHttpClient setTransports(List list)
    {
        ArrayList arraylist = new ArrayList(list.size());
        int i = 0;
        int j = list.size();
        while(i < j) 
        {
            try
            {
                arraylist.add(Util.getProtocol(ByteString.encodeUtf8((String)list.get(i))));
            }
            catch(IOException ioexception)
            {
                throw new IllegalArgumentException(ioexception);
            }
            i++;
        }
        return setProtocols(arraylist);
    }

    private OkAuthenticator authenticator;
    private int connectTimeout;
    private ConnectionPool connectionPool;
    private CookieHandler cookieHandler;
    private boolean followProtocolRedirects;
    private HostnameVerifier hostnameVerifier;
    private List protocols;
    private Proxy proxy;
    private ProxySelector proxySelector;
    private int readTimeout;
    private OkResponseCache responseCache;
    private final RouteDatabase routeDatabase = new RouteDatabase();
    private SSLSocketFactory sslSocketFactory;
}
