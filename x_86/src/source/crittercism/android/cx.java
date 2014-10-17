// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package crittercism.android;

import com.newrelic.agent.android.instrumentation.HttpInstrumentation;
import com.newrelic.agent.android.instrumentation.JSONObjectInstrumentation;
import java.io.*;
import java.net.*;
import java.util.*;
import org.json.JSONException;
import org.json.JSONObject;

// Referenced classes of package crittercism.android:
//            cv, cp, cj, cl

public final class cx extends cv
{

    public cx(cj cj1, cp cp1)
    {
        this(cj1, cp1, false, null);
    }

    public cx(cj cj1, cp cp1, boolean flag, cl cl1)
    {
        a = cj1;
        b = cp1;
        c = flag;
        d = cl1;
    }

    private static JSONObject a(URLConnection urlconnection)
    {
        StringBuilder stringbuilder = new StringBuilder();
        BufferedReader bufferedreader = new BufferedReader(new InputStreamReader(urlconnection.getInputStream()));
        do
        {
            int i = bufferedreader.read();
            if(i != -1)
            {
                stringbuilder.append((char)i);
            } else
            {
                bufferedreader.close();
                String s = stringbuilder.toString();
                JSONObject _tmp = JVM INSTR new #65  <Class JSONObject>;
                return JSONObjectInstrumentation.init(s);
            }
        } while(true);
    }

    public final void a()
    {
        cp cp1;
        HttpURLConnection httpurlconnection;
        cp1 = b;
        httpurlconnection = (HttpURLConnection)HttpInstrumentation.openConnection(cp1.a.openConnection());
        for(Iterator iterator = cp1.b.entrySet().iterator(); iterator.hasNext();)
        {
            java.util.Map.Entry entry = (java.util.Map.Entry)iterator.next();
            Iterator iterator1 = ((List)entry.getValue()).iterator();
            while(iterator1.hasNext()) 
            {
                String s3 = (String)iterator1.next();
                httpurlconnection.addRequestProperty((String)entry.getKey(), s3);
            }
        }

        httpurlconnection.setConnectTimeout(cp1.h);
        httpurlconnection.setReadTimeout(cp1.h);
        httpurlconnection.setDoInput(cp1.d);
        httpurlconnection.setDoOutput(cp1.e);
        if(cp1.g)
            httpurlconnection.setChunkedStreamingMode(cp1.c);
        httpurlconnection.setRequestMethod(cp1.f);
        if(httpurlconnection == null) goto _L2; else goto _L1
_L1:
        OutputStream outputstream;
        JSONObject jsonobject1;
        cj cj1 = a;
        outputstream = httpurlconnection.getOutputStream();
        jsonobject1 = new JSONObject(cj1.a);
        if(jsonobject1 instanceof JSONObject) goto _L4; else goto _L3
_L3:
        String s2 = jsonobject1.toString();
_L14:
        int j1;
        outputstream.write(s2.getBytes("UTF8"));
        j1 = httpurlconnection.getResponseCode();
        int l = j1;
        if(!c) goto _L6; else goto _L5
_L5:
        JSONObject jsonobject2 = a(((URLConnection) (httpurlconnection)));
        JSONObject jsonobject = jsonobject2;
_L12:
        int j;
        boolean flag;
        j = l;
        flag = false;
_L7:
        JSONException jsonexception1;
        int i;
        IOException ioexception2;
        int k;
        SocketTimeoutException sockettimeoutexception;
        UnsupportedEncodingException unsupportedencodingexception1;
        int i1;
        httpurlconnection.disconnect();
        if(j >= 200 && j < 300)
            if(jsonobject != null)
            {
                StringBuilder stringbuilder = new StringBuilder("resp = ");
                JSONException jsonexception;
                String s;
                IOException ioexception1;
                UnsupportedEncodingException unsupportedencodingexception;
                String s1;
                if(!(jsonobject instanceof JSONObject))
                    s = jsonobject.toString();
                else
                    s = JSONObjectInstrumentation.toString((JSONObject)jsonobject);
                stringbuilder.append(s);
            } else
            {
                (new StringBuilder("respCode = ")).append(j);
            }
        if(d != null)
        {
            d.a(flag, j, jsonobject);
            return;
        }
        break; /* Loop/switch isn't completed */
_L4:
        s1 = JSONObjectInstrumentation.toString((JSONObject)jsonobject1);
        s2 = s1;
        continue; /* Loop/switch isn't completed */
        unsupportedencodingexception;
        unsupportedencodingexception1 = unsupportedencodingexception;
        i1 = -1;
_L11:
        (new StringBuilder("UnsupportedEncodingException in proceed(): ")).append(unsupportedencodingexception1.getMessage());
        j = i1;
        jsonobject = null;
        flag = false;
          goto _L7
        sockettimeoutexception;
        l = -1;
_L10:
        (new StringBuilder("SocketTimeoutException in proceed(): ")).append(sockettimeoutexception.getMessage());
        j = l;
        flag = true;
        jsonobject = null;
          goto _L7
        ioexception1;
        ioexception2 = ioexception1;
        k = -1;
_L9:
        (new StringBuilder("IOException in proceed(): ")).append(ioexception2.getMessage());
        j = k;
        jsonobject = null;
        flag = false;
          goto _L7
        jsonexception;
        jsonexception1 = jsonexception;
        i = -1;
_L8:
        (new StringBuilder("JSONException in proceed(): ")).append(jsonexception1.getMessage());
        j = i;
        jsonobject = null;
        flag = false;
          goto _L7
        JSONException jsonexception2;
        jsonexception2;
        i = l;
        jsonexception1 = jsonexception2;
          goto _L8
        IOException ioexception3;
        ioexception3;
        k = l;
        ioexception2 = ioexception3;
          goto _L9
        sockettimeoutexception;
          goto _L10
        UnsupportedEncodingException unsupportedencodingexception2;
        unsupportedencodingexception2;
        i1 = l;
        unsupportedencodingexception1 = unsupportedencodingexception2;
          goto _L11
_L6:
        jsonobject = null;
        if(true) goto _L12; else goto _L2
        IOException ioexception;
        ioexception;
_L2:
        return;
        if(true) goto _L14; else goto _L13
_L13:
    }

    private cj a;
    private cp b;
    private boolean c;
    private cl d;
}
