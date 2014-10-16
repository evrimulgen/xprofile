// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.flurry.sdk;

import com.newrelic.agent.android.instrumentation.HttpInstrumentation;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.params.*;

// Referenced classes of package com.flurry.sdk:
//            fi, ew, ch, ex

public class ce extends fi
{

    public ce(String s, String s1, String s2, byte abyte0[], ch ch1)
    {
        b = s;
        c = s1;
        d = s2;
        e = abyte0;
        f = ch1;
    }

    public void a()
    {
        HttpClient httpclient;
        httpclient = null;
        Long long1 = Long.valueOf(Thread.currentThread().getId());
        Thread.currentThread().setName((new StringBuilder()).append("DataSender Sending Executor Thread, id = ").append(long1).toString());
        HttpPost httppost;
        HttpClient httpclient2;
        ByteArrayEntity bytearrayentity = new ByteArrayEntity(e);
        bytearrayentity.setContentType("application/octet-stream");
        httppost = new HttpPost(b);
        httppost.setEntity(bytearrayentity);
        BasicHttpParams basichttpparams = new BasicHttpParams();
        HttpConnectionParams.setConnectionTimeout(basichttpparams, 10000);
        HttpConnectionParams.setSoTimeout(basichttpparams, a);
        httppost.getParams().setBooleanParameter("http.protocol.expect-continue", false);
        httpclient2 = ew.b(basichttpparams);
        HttpClient httpclient1 = httpclient2;
        if(httpclient1 instanceof HttpClient) goto _L2; else goto _L1
_L1:
        HttpResponse httpresponse2 = httpclient1.execute(httppost);
        HttpResponse httpresponse = httpresponse2;
_L3:
        if(httpclient1 != null)
            httpclient1.getConnectionManager().shutdown();
_L4:
        Exception exception;
        Exception exception1;
        HttpResponse httpresponse1;
        if(httpresponse == null || httpresponse.getStatusLine() == null)
        {
            f.a(c, d);
            return;
        } else
        {
            StatusLine statusline = httpresponse.getStatusLine();
            int i = statusline.getStatusCode();
            String s = statusline.getReasonPhrase();
            f.a(i, s, c, d);
            return;
        }
_L2:
        httpresponse1 = HttpInstrumentation.execute((HttpClient)httpclient1, httppost);
        httpresponse = httpresponse1;
          goto _L3
        exception1;
        httpclient1 = null;
_L7:
        ex.a(6, g, "Exception: ", exception1);
        if(httpclient1 != null)
        {
            httpclient1.getConnectionManager().shutdown();
            httpresponse = null;
        } else
        {
            httpresponse = null;
        }
          goto _L4
        exception;
_L6:
        if(httpclient != null)
            httpclient.getConnectionManager().shutdown();
        throw exception;
        exception;
        httpclient = httpclient1;
        if(true) goto _L6; else goto _L5
_L5:
        exception1;
          goto _L7
    }

    static int a = 15000;
    private static final String g = com/flurry/sdk/ce.getSimpleName();
    String b;
    String c;
    String d;
    byte e[];
    ch f;

}
