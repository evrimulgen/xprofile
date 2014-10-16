// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.bugsnag.http;

import com.newrelic.agent.android.instrumentation.HttpInstrumentation;
import com.newrelic.agent.android.instrumentation.JSONObjectInstrumentation;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject;

// Referenced classes of package com.bugsnag.http:
//            NetworkException, BadResponseException

public class HttpClient
{

    public HttpClient()
    {
    }

    public static void post(String s, InputStream inputstream)
        throws NetworkException
    {
        post(s, inputstream, "application/json");
    }

    public static void post(String s, InputStream inputstream, String s1)
        throws NetworkException
    {
        Object obj = null;
        HttpURLConnection httpurlconnection = (HttpURLConnection)HttpInstrumentation.openConnection((new URL(s)).openConnection());
        httpurlconnection.setDoOutput(true);
        httpurlconnection.setChunkedStreamingMode(0);
        if(s1 == null)
            break MISSING_BLOCK_LABEL_45;
        httpurlconnection.addRequestProperty("Content-Type", s1);
        byte abyte0[];
        obj = httpurlconnection.getOutputStream();
        abyte0 = new byte[1024];
_L3:
        int i = inputstream.read(abyte0);
        if(i == -1) goto _L2; else goto _L1
_L1:
        ((OutputStream) (obj)).write(abyte0, 0, i);
          goto _L3
        Exception exception2;
        exception2;
        if(obj == null)
            break MISSING_BLOCK_LABEL_94;
        ((OutputStream) (obj)).close();
        throw exception2;
        IOException ioexception1;
        ioexception1;
        IOException ioexception;
        obj = httpurlconnection;
        ioexception = ioexception1;
_L7:
        throw new NetworkException(String.format("Network error when posting to %s", new Object[] {
            s
        }), ioexception);
        Exception exception;
        exception;
_L5:
        if(obj != null)
            ((HttpURLConnection) (obj)).disconnect();
        throw exception;
_L2:
        if(obj == null)
            break MISSING_BLOCK_LABEL_150;
        ((OutputStream) (obj)).close();
        int j = httpurlconnection.getResponseCode();
        if(j / 100 != 2)
            throw new BadResponseException(s, j);
        break; /* Loop/switch isn't completed */
        Exception exception1;
        exception1;
        obj = httpurlconnection;
        exception = exception1;
        if(true) goto _L5; else goto _L4
_L4:
        if(httpurlconnection != null)
            httpurlconnection.disconnect();
        return;
        ioexception;
        obj = null;
        if(true) goto _L7; else goto _L6
_L6:
    }

    public static void post(String s, String s1, String s2)
        throws NetworkException, UnsupportedEncodingException
    {
        post(s, ((InputStream) (new ByteArrayInputStream(s1.getBytes("UTF-8")))), s2);
    }

    public static void post(String s, JSONObject jsonobject)
        throws NetworkException, UnsupportedEncodingException
    {
        String s1;
        if(!(jsonobject instanceof JSONObject))
            s1 = jsonobject.toString();
        else
            s1 = JSONObjectInstrumentation.toString((JSONObject)jsonobject);
        post(s, s1, "application/json");
    }
}
