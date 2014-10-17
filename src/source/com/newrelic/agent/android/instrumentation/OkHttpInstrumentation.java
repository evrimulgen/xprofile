// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.newrelic.agent.android.instrumentation;

import java.net.HttpURLConnection;
import javax.net.ssl.HttpsURLConnection;

// Referenced classes of package com.newrelic.agent.android.instrumentation:
//            HttpsURLConnectionExtension, HttpURLConnectionExtension

public class OkHttpInstrumentation
{

    public OkHttpInstrumentation()
    {
    }

    public static HttpURLConnection open(HttpURLConnection httpurlconnection)
    {
        if(httpurlconnection instanceof HttpsURLConnection)
            return new HttpsURLConnectionExtension((HttpsURLConnection)httpurlconnection);
        if(httpurlconnection != null)
            return new HttpURLConnectionExtension(httpurlconnection);
        else
            return null;
    }

    public static HttpURLConnection openWithProxy(HttpURLConnection httpurlconnection)
    {
        if(httpurlconnection instanceof HttpsURLConnection)
            return new HttpsURLConnectionExtension((HttpsURLConnection)httpurlconnection);
        if(httpurlconnection != null)
            return new HttpURLConnectionExtension(httpurlconnection);
        else
            return null;
    }
}
