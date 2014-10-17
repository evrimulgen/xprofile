// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.tagmanager;

import com.newrelic.agent.android.instrumentation.HttpInstrumentation;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

// Referenced classes of package com.google.tagmanager:
//            NetworkClient

class HttpUrlConnectionNetworkClient
    implements NetworkClient
{

    HttpUrlConnectionNetworkClient()
    {
    }

    private void closeURLConnection(HttpURLConnection httpurlconnection)
    {
        if(httpurlconnection != null)
            httpurlconnection.disconnect();
    }

    private InputStream handleServerResponse(HttpURLConnection httpurlconnection)
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

    public void close()
    {
        closeURLConnection(mConnection);
    }

    public InputStream getInputStream(String s)
        throws IOException
    {
        mConnection = getUrlConnection(s);
        return handleServerResponse(mConnection);
    }

    HttpURLConnection getUrlConnection(String s)
        throws IOException
    {
        HttpURLConnection httpurlconnection = (HttpURLConnection)HttpInstrumentation.openConnection((new URL(s)).openConnection());
        httpurlconnection.setReadTimeout(20000);
        httpurlconnection.setConnectTimeout(20000);
        return httpurlconnection;
    }

    public void sendPostRequest(String s, byte abyte0[])
        throws IOException
    {
        HttpURLConnection httpurlconnection = getUrlConnection(s);
        OutputStream outputstream;
        httpurlconnection.setRequestProperty("Content-Length", Integer.toString(abyte0.length));
        httpurlconnection.setRequestMethod("POST");
        httpurlconnection.setDoOutput(true);
        httpurlconnection.connect();
        outputstream = httpurlconnection.getOutputStream();
        outputstream.write(abyte0);
        outputstream.flush();
        outputstream.close();
        handleServerResponse(httpurlconnection);
        closeURLConnection(httpurlconnection);
        return;
        Exception exception1;
        exception1;
        outputstream.close();
        throw exception1;
        Exception exception;
        exception;
        closeURLConnection(httpurlconnection);
        throw exception;
    }

    private HttpURLConnection mConnection;
}
