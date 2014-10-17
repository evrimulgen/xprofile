// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.android.gms.tagmanager;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import com.newrelic.agent.android.instrumentation.HttpInstrumentation;
import java.io.IOException;
import java.io.InputStream;
import java.net.*;
import java.util.List;
import java.util.Locale;
import org.apache.http.*;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.message.BasicHttpEntityEnclosingRequest;

// Referenced classes of package com.google.android.gms.tagmanager:
//            ab, bh, ap, bn

class db
    implements ab
{
    public static interface a
    {

        public abstract void a(ap ap1);

        public abstract void b(ap ap1);

        public abstract void c(ap ap1);
    }


    db(HttpClient httpclient, Context context, a a1)
    {
        Xf = context.getApplicationContext();
        Xw = a("GoogleTagManager", "3.02b1", android.os.Build.VERSION.RELEASE, b(Locale.getDefault()), Build.MODEL, Build.ID);
        Xx = httpclient;
        Xy = a1;
    }

    private HttpEntityEnclosingRequest a(URL url)
    {
        BasicHttpEntityEnclosingRequest basichttpentityenclosingrequest = new BasicHttpEntityEnclosingRequest("GET", url.toURI().toString());
        basichttpentityenclosingrequest.addHeader("User-Agent", Xw);
        return basichttpentityenclosingrequest;
        URISyntaxException urisyntaxexception1;
        urisyntaxexception1;
        URISyntaxException urisyntaxexception;
        basichttpentityenclosingrequest = null;
        urisyntaxexception = urisyntaxexception1;
_L2:
        bh.w((new StringBuilder()).append("Exception sending hit: ").append(urisyntaxexception.getClass().getSimpleName()).toString());
        bh.w(urisyntaxexception.getMessage());
        return basichttpentityenclosingrequest;
        urisyntaxexception;
        if(true) goto _L2; else goto _L1
_L1:
    }

    private void a(HttpEntityEnclosingRequest httpentityenclosingrequest)
    {
        StringBuffer stringbuffer;
        stringbuffer = new StringBuffer();
        org.apache.http.Header aheader[] = httpentityenclosingrequest.getAllHeaders();
        int i = aheader.length;
        for(int j = 0; j < i; j++)
            stringbuffer.append(aheader[j].toString()).append("\n");

        stringbuffer.append(httpentityenclosingrequest.getRequestLine().toString()).append("\n");
        if(httpentityenclosingrequest.getEntity() == null)
            break MISSING_BLOCK_LABEL_150;
        InputStream inputstream = httpentityenclosingrequest.getEntity().getContent();
        if(inputstream == null)
            break MISSING_BLOCK_LABEL_150;
        int k = inputstream.available();
        if(k > 0)
            try
            {
                byte abyte0[] = new byte[k];
                inputstream.read(abyte0);
                stringbuffer.append("POST:\n");
                stringbuffer.append(new String(abyte0)).append("\n");
            }
            catch(IOException ioexception)
            {
                bh.v("Error Writing hit to log...");
            }
        bh.v(stringbuffer.toString());
        return;
    }

    static String b(Locale locale)
    {
        while(locale == null || locale.getLanguage() == null || locale.getLanguage().length() == 0) 
            return null;
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append(locale.getLanguage().toLowerCase());
        if(locale.getCountry() != null && locale.getCountry().length() != 0)
            stringbuilder.append("-").append(locale.getCountry().toLowerCase());
        return stringbuilder.toString();
    }

    String a(String s, String s1, String s2, String s3, String s4, String s5)
    {
        return String.format("%s/%s (Linux; U; Android %s; %s; %s Build/%s)", new Object[] {
            s, s1, s2, s3, s4, s5
        });
    }

    public boolean bA()
    {
        NetworkInfo networkinfo = ((ConnectivityManager)Xf.getSystemService("connectivity")).getActiveNetworkInfo();
        if(networkinfo == null || !networkinfo.isConnected())
        {
            bh.v("...no network connectivity");
            return false;
        } else
        {
            return true;
        }
    }

    URL d(ap ap1)
    {
        String s = ap1.jf();
        URL url;
        try
        {
            url = new URL(s);
        }
        catch(MalformedURLException malformedurlexception)
        {
            bh.t("Error trying to parse the GTM url.");
            return null;
        }
        return url;
    }

    public void e(List list)
    {
        int i;
        boolean flag;
        int j;
        i = Math.min(list.size(), 40);
        flag = true;
        j = 0;
_L2:
        ap ap1;
        URL url;
        HttpEntityEnclosingRequest httpentityenclosingrequest;
        boolean flag2;
        if(j >= i)
            break; /* Loop/switch isn't completed */
        ap1 = (ap)list.get(j);
        url = d(ap1);
        if(url == null)
        {
            bh.w("No destination: discarding hit.");
            Xy.b(ap1);
            flag2 = flag;
        } else
        {
label0:
            {
                httpentityenclosingrequest = a(url);
                if(httpentityenclosingrequest != null)
                    break label0;
                Xy.b(ap1);
                flag2 = flag;
            }
        }
_L4:
        j++;
        flag = flag2;
        if(true) goto _L2; else goto _L1
        HttpHost httphost;
        httphost = new HttpHost(url.getHost(), url.getPort(), url.getProtocol());
        httpentityenclosingrequest.addHeader("Host", httphost.toHostString());
        a(httpentityenclosingrequest);
        if(!flag)
            break MISSING_BLOCK_LABEL_417;
        bn.p(Xf);
        boolean flag1 = false;
_L7:
        HttpClient httpclient;
        HttpResponse httpresponse;
        httpclient = Xx;
        if(httpclient instanceof HttpClient)
            break MISSING_BLOCK_LABEL_284;
        httpresponse = httpclient.execute(httphost, httpentityenclosingrequest);
_L3:
        int k;
        HttpEntity httpentity;
        k = httpresponse.getStatusLine().getStatusCode();
        httpentity = httpresponse.getEntity();
        if(httpentity == null)
            break MISSING_BLOCK_LABEL_228;
        httpentity.consumeContent();
        if(k == 200)
            break MISSING_BLOCK_LABEL_301;
        bh.w((new StringBuilder()).append("Bad response: ").append(httpresponse.getStatusLine().getStatusCode()).toString());
        Xy.c(ap1);
        break MISSING_BLOCK_LABEL_423;
        httpresponse = HttpInstrumentation.execute((HttpClient)httpclient, httphost, httpentityenclosingrequest);
          goto _L3
        Xy.a(ap1);
        break MISSING_BLOCK_LABEL_423;
        ClientProtocolException clientprotocolexception;
        clientprotocolexception;
        flag = flag1;
_L6:
        bh.w("ClientProtocolException sending hit; discarding hit...");
        Xy.b(ap1);
        flag2 = flag;
          goto _L4
        IOException ioexception1;
        ioexception1;
_L5:
        bh.w((new StringBuilder()).append("Exception sending hit: ").append(ioexception1.getClass().getSimpleName()).toString());
        bh.w(ioexception1.getMessage());
        Xy.c(ap1);
        flag2 = flag;
          goto _L4
_L1:
        return;
        IOException ioexception;
        ioexception;
        flag = flag1;
        ioexception1 = ioexception;
          goto _L5
        ClientProtocolException clientprotocolexception1;
        clientprotocolexception1;
          goto _L6
        flag1 = flag;
          goto _L7
        flag2 = flag1;
          goto _L4
    }

    private final Context Xf;
    private final String Xw;
    private final HttpClient Xx;
    private a Xy;
}
