// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.squareup.picasso;

import android.content.Context;
import android.net.Uri;
import com.newrelic.agent.android.instrumentation.OkHttpInstrumentation;
import com.squareup.okhttp.HttpResponseCache;
import com.squareup.okhttp.OkHttpClient;
import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

// Referenced classes of package com.squareup.picasso:
//            Downloader, Utils

public class OkHttpDownloader
    implements Downloader
{

    public OkHttpDownloader(Context context)
    {
        this(Utils.createDefaultCacheDir(context));
    }

    public OkHttpDownloader(Context context, long l)
    {
        this(Utils.createDefaultCacheDir(context), l);
    }

    public OkHttpDownloader(OkHttpClient okhttpclient)
    {
        client = okhttpclient;
    }

    public OkHttpDownloader(File file)
    {
        this(file, Utils.calculateDiskCacheSize(file));
    }

    public OkHttpDownloader(File file, long l)
    {
        this(new OkHttpClient());
        try
        {
            client.setResponseCache(new HttpResponseCache(file, l));
            return;
        }
        catch(IOException ioexception)
        {
            return;
        }
    }

    protected OkHttpClient getClient()
    {
        return client;
    }

    public Downloader.Response load(Uri uri, boolean flag)
        throws IOException
    {
        HttpURLConnection httpurlconnection = openConnection(uri);
        httpurlconnection.setUseCaches(true);
        if(flag)
            httpurlconnection.setRequestProperty("Cache-Control", "only-if-cached,max-age=2147483647");
        int i = httpurlconnection.getResponseCode();
        if(i >= 300)
        {
            httpurlconnection.disconnect();
            throw new Downloader.ResponseException((new StringBuilder()).append(i).append(" ").append(httpurlconnection.getResponseMessage()).toString());
        }
        String s = httpurlconnection.getHeaderField("OkHttp-Response-Source");
        if(s == null)
            s = httpurlconnection.getHeaderField("X-Android-Response-Source");
        boolean flag1 = Utils.parseResponseSourceHeader(s);
        return new Downloader.Response(httpurlconnection.getInputStream(), flag1);
    }

    protected HttpURLConnection openConnection(Uri uri)
        throws IOException
    {
        HttpURLConnection httpurlconnection = OkHttpInstrumentation.open(client.open(new URL(uri.toString())));
        httpurlconnection.setConnectTimeout(15000);
        httpurlconnection.setReadTimeout(20000);
        return httpurlconnection;
    }

    static final String RESPONSE_SOURCE_ANDROID = "X-Android-Response-Source";
    static final String RESPONSE_SOURCE_OKHTTP = "OkHttp-Response-Source";
    private final OkHttpClient client;
}
