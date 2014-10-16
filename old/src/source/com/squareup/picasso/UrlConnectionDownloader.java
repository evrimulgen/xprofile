// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.squareup.picasso;

import android.content.Context;
import android.net.Uri;
import android.net.http.HttpResponseCache;
import com.newrelic.agent.android.instrumentation.HttpInstrumentation;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

// Referenced classes of package com.squareup.picasso:
//            Downloader, Utils

public class UrlConnectionDownloader
    implements Downloader
{
    private static class ResponseCacheIcs
    {

        static Object install(Context context1)
            throws IOException
        {
            java.io.File file = Utils.createDefaultCacheDir(context1);
            HttpResponseCache httpresponsecache = HttpResponseCache.getInstalled();
            if(httpresponsecache == null)
                httpresponsecache = HttpResponseCache.install(file, Utils.calculateDiskCacheSize(file));
            return httpresponsecache;
        }

        private ResponseCacheIcs()
        {
        }
    }


    public UrlConnectionDownloader(Context context1)
    {
        context = context1.getApplicationContext();
    }

    private static void installCacheIfNeeded(Context context1)
    {
        if(cache != null)
            break MISSING_BLOCK_LABEL_34;
        synchronized(lock)
        {
            if(cache == null)
                cache = ResponseCacheIcs.install(context1);
        }
        return;
        exception;
        obj;
        JVM INSTR monitorexit ;
        try
        {
            throw exception;
        }
        catch(IOException ioexception) { }
    }

    public Downloader.Response load(Uri uri, boolean flag)
        throws IOException
    {
        if(android.os.Build.VERSION.SDK_INT >= 14)
            installCacheIfNeeded(context);
        HttpURLConnection httpurlconnection = openConnection(uri);
        httpurlconnection.setUseCaches(true);
        if(flag)
            httpurlconnection.setRequestProperty("Cache-Control", "only-if-cached,max-age=2147483647");
        int i = httpurlconnection.getResponseCode();
        if(i >= 300)
        {
            httpurlconnection.disconnect();
            throw new Downloader.ResponseException((new StringBuilder()).append(i).append(" ").append(httpurlconnection.getResponseMessage()).toString());
        } else
        {
            boolean flag1 = Utils.parseResponseSourceHeader(httpurlconnection.getHeaderField("X-Android-Response-Source"));
            return new Downloader.Response(httpurlconnection.getInputStream(), flag1);
        }
    }

    protected HttpURLConnection openConnection(Uri uri)
        throws IOException
    {
        HttpURLConnection httpurlconnection = (HttpURLConnection)HttpInstrumentation.openConnection((new URL(uri.toString())).openConnection());
        httpurlconnection.setConnectTimeout(15000);
        httpurlconnection.setReadTimeout(20000);
        return httpurlconnection;
    }

    static final String RESPONSE_SOURCE = "X-Android-Response-Source";
    static volatile Object cache;
    private static final Object lock = new Object();
    private final Context context;

}
