// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.tagmanager;

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
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.message.BasicHttpEntityEnclosingRequest;

// Referenced classes of package com.google.tagmanager:
//            Dispatcher, Log, Hit, NetworkReceiver

class SimpleNetworkDispatcher
    implements Dispatcher
{
    public static interface DispatchListener
    {

        public abstract void onHitDispatched(Hit hit);

        public abstract void onHitPermanentDispatchFailure(Hit hit);

        public abstract void onHitTransientDispatchFailure(Hit hit);
    }


    SimpleNetworkDispatcher(HttpClient httpclient, Context context, DispatchListener dispatchlistener)
    {
        ctx = context.getApplicationContext();
        userAgent = createUserAgentString("GoogleTagManager", "3.01", android.os.Build.VERSION.RELEASE, getUserAgentLanguage(Locale.getDefault()), Build.MODEL, Build.ID);
        httpClient = httpclient;
        dispatchListener = dispatchlistener;
    }

    private HttpEntityEnclosingRequest constructGtmRequest(URL url)
    {
        Object obj = null;
        BasicHttpEntityEnclosingRequest basichttpentityenclosingrequest = new BasicHttpEntityEnclosingRequest("GET", url.toURI().toString());
        basichttpentityenclosingrequest.addHeader("User-Agent", userAgent);
        return basichttpentityenclosingrequest;
        URISyntaxException urisyntaxexception;
        urisyntaxexception;
_L2:
        Log.w((new StringBuilder()).append("Exception sending hit: ").append(urisyntaxexception.getClass().getSimpleName()).toString());
        Log.w(urisyntaxexception.getMessage());
        return ((HttpEntityEnclosingRequest) (obj));
        urisyntaxexception;
        obj = basichttpentityenclosingrequest;
        if(true) goto _L2; else goto _L1
_L1:
    }

    static String getUserAgentLanguage(Locale locale)
    {
        while(locale == null || locale.getLanguage() == null || locale.getLanguage().length() == 0) 
            return null;
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append(locale.getLanguage().toLowerCase());
        if(locale.getCountry() != null && locale.getCountry().length() != 0)
            stringbuilder.append("-").append(locale.getCountry().toLowerCase());
        return stringbuilder.toString();
    }

    private void logDebugInformation(HttpEntityEnclosingRequest httpentityenclosingrequest)
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
                Log.v("Error Writing hit to log...");
            }
        Log.v(stringbuffer.toString());
        return;
    }

    public void close()
    {
        httpClient.getConnectionManager().shutdown();
    }

    String createUserAgentString(String s, String s1, String s2, String s3, String s4, String s5)
    {
        return String.format("%s/%s (Linux; U; Android %s; %s; %s Build/%s)", new Object[] {
            s, s1, s2, s3, s4, s5
        });
    }

    public void dispatchHits(List list)
    {
        int i;
        boolean flag;
        int j;
        i = Math.min(list.size(), 40);
        flag = true;
        j = 0;
_L2:
        Hit hit;
        URL url;
        if(j >= i)
            break MISSING_BLOCK_LABEL_380;
        hit = (Hit)list.get(j);
        url = getUrl(hit);
        if(url != null)
            break; /* Loop/switch isn't completed */
        Log.w("No destination: discarding hit.");
        dispatchListener.onHitPermanentDispatchFailure(hit);
_L3:
        j++;
        if(true) goto _L2; else goto _L1
_L1:
        HttpEntityEnclosingRequest httpentityenclosingrequest;
label0:
        {
            httpentityenclosingrequest = constructGtmRequest(url);
            if(httpentityenclosingrequest != null)
                break label0;
            dispatchListener.onHitPermanentDispatchFailure(hit);
        }
          goto _L3
        HttpHost httphost;
        httphost = new HttpHost(url.getHost(), url.getPort(), url.getProtocol());
        httpentityenclosingrequest.addHeader("Host", httphost.toHostString());
        logDebugInformation(httpentityenclosingrequest);
        if(!flag)
            break MISSING_BLOCK_LABEL_156;
        NetworkReceiver.sendRadioPoweredBroadcast(ctx);
        flag = false;
        HttpClient httpclient;
        HttpResponse httpresponse;
        httpclient = httpClient;
        if(httpclient instanceof HttpClient)
            break MISSING_BLOCK_LABEL_296;
        httpresponse = httpclient.execute(httphost, httpentityenclosingrequest);
_L4:
        int k;
        HttpEntity httpentity;
        k = httpresponse.getStatusLine().getStatusCode();
        httpentity = httpresponse.getEntity();
        if(httpentity == null)
            break MISSING_BLOCK_LABEL_218;
        httpentity.consumeContent();
        if(k == 200)
            break MISSING_BLOCK_LABEL_313;
        try
        {
            Log.w((new StringBuilder()).append("Bad response: ").append(httpresponse.getStatusLine().getStatusCode()).toString());
            dispatchListener.onHitTransientDispatchFailure(hit);
        }
        catch(ClientProtocolException clientprotocolexception)
        {
            Log.w("ClientProtocolException sending hit; discarding hit...");
            dispatchListener.onHitPermanentDispatchFailure(hit);
        }
        catch(IOException ioexception)
        {
            Log.w((new StringBuilder()).append("Exception sending hit: ").append(ioexception.getClass().getSimpleName()).toString());
            Log.w(ioexception.getMessage());
            dispatchListener.onHitTransientDispatchFailure(hit);
        }
          goto _L3
        httpresponse = HttpInstrumentation.execute((HttpClient)httpclient, httphost, httpentityenclosingrequest);
          goto _L4
        dispatchListener.onHitDispatched(hit);
          goto _L3
    }

    URL getUrl(Hit hit)
    {
        String s = hit.getHitUrl();
        URL url;
        try
        {
            url = new URL(s);
        }
        catch(MalformedURLException malformedurlexception)
        {
            Log.e("Error trying to parse the GTM url.");
            return null;
        }
        return url;
    }

    public boolean okToDispatch()
    {
        NetworkInfo networkinfo = ((ConnectivityManager)ctx.getSystemService("connectivity")).getActiveNetworkInfo();
        if(networkinfo == null || !networkinfo.isConnected())
        {
            Log.v("...no network connectivity");
            return false;
        } else
        {
            return true;
        }
    }

    private static final String USER_AGENT_TEMPLATE = "%s/%s (Linux; U; Android %s; %s; %s Build/%s)";
    private final Context ctx;
    private DispatchListener dispatchListener;
    private final HttpClient httpClient;
    private final String userAgent;
}
