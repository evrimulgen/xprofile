// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.android.gms.tagmanager;

import com.newrelic.agent.android.instrumentation.HttpInstrumentation;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

// Referenced classes of package com.google.android.gms.tagmanager:
//            bl

class aw
    implements bl
{

    aw()
    {
    }

    private InputStream a(HttpURLConnection httpurlconnection)
        throws IOException
    {
        int i = httpurlconnection.getResponseCode();
        if(i == 200)
            return httpurlconnection.getInputStream();
        String s = (new StringBuilder()).append("Bad response: ").append(i).toString();
        if(i == 404)
            throw new FileNotFoundException(s);
        else
            throw new IOException(s);
    }

    private void b(HttpURLConnection httpurlconnection)
    {
        if(httpurlconnection != null)
            httpurlconnection.disconnect();
    }

    public InputStream bo(String s)
        throws IOException
    {
        Vj = bp(s);
        return a(Vj);
    }

    HttpURLConnection bp(String s)
        throws IOException
    {
        HttpURLConnection httpurlconnection = (HttpURLConnection)HttpInstrumentation.openConnection((new URL(s)).openConnection());
        httpurlconnection.setReadTimeout(20000);
        httpurlconnection.setConnectTimeout(20000);
        return httpurlconnection;
    }

    public void close()
    {
        b(Vj);
    }

    private HttpURLConnection Vj;
}
