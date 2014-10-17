// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.tagmanager;

import com.newrelic.agent.android.instrumentation.HttpInstrumentation;
import java.io.*;
import org.apache.http.*;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;

// Referenced classes of package com.google.tagmanager:
//            NetworkClient, Log

class HttpNetworkClient
    implements NetworkClient
{

    HttpNetworkClient()
    {
    }

    private void closeWithClient(HttpClient httpclient)
    {
        if(httpclient != null && httpclient.getConnectionManager() != null)
            httpclient.getConnectionManager().shutdown();
    }

    private InputStream handleServerResponse(HttpClient httpclient, HttpResponse httpresponse)
        throws IOException
    {
        int i = httpresponse.getStatusLine().getStatusCode();
        if(i == 200)
        {
            Log.v("Success response");
            return httpresponse.getEntity().getContent();
        }
        String s = (new StringBuilder()).append("Bad response: ").append(i).toString();
        if(i == 404)
            throw new FileNotFoundException(s);
        else
            throw new IOException(s);
    }

    public void close()
    {
        closeWithClient(mClient);
    }

    HttpClient createNewHttpClient()
    {
        BasicHttpParams basichttpparams = new BasicHttpParams();
        HttpConnectionParams.setConnectionTimeout(basichttpparams, 20000);
        HttpConnectionParams.setSoTimeout(basichttpparams, 20000);
        return new DefaultHttpClient(basichttpparams);
    }

    HttpPost createPostRequest(String s, byte abyte0[])
    {
        HttpPost httppost = new HttpPost(s);
        httppost.setEntity(new ByteArrayEntity(abyte0));
        return httppost;
    }

    public InputStream getInputStream(String s)
        throws IOException
    {
        mClient = createNewHttpClient();
        HttpClient httpclient = mClient;
        HttpGet httpget = new HttpGet(s);
        HttpResponse httpresponse;
        if(!(httpclient instanceof HttpClient))
            httpresponse = httpclient.execute(httpget);
        else
            httpresponse = HttpInstrumentation.execute((HttpClient)httpclient, httpget);
        return handleServerResponse(mClient, httpresponse);
    }

    public void sendPostRequest(String s, byte abyte0[])
        throws IOException
    {
        HttpClient httpclient = createNewHttpClient();
        HttpPost httppost = createPostRequest(s, abyte0);
        HttpResponse httpresponse;
        if(!(httpclient instanceof HttpClient))
            httpresponse = httpclient.execute(httppost);
        else
            httpresponse = HttpInstrumentation.execute((HttpClient)httpclient, httppost);
        handleServerResponse(httpclient, httpresponse);
        closeWithClient(httpclient);
    }

    private HttpClient mClient;
}
