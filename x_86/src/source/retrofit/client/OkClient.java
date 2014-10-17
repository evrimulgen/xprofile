// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package retrofit.client;

import com.newrelic.agent.android.instrumentation.OkHttpInstrumentation;
import com.squareup.okhttp.OkHttpClient;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.TimeUnit;

// Referenced classes of package retrofit.client:
//            UrlConnectionClient, Request

public class OkClient extends UrlConnectionClient
{

    public OkClient()
    {
        this(generateDefaultOkHttp());
    }

    public OkClient(OkHttpClient okhttpclient)
    {
        client = okhttpclient;
    }

    private static OkHttpClient generateDefaultOkHttp()
    {
        OkHttpClient okhttpclient = new OkHttpClient();
        okhttpclient.setConnectTimeout(15000L, TimeUnit.MILLISECONDS);
        okhttpclient.setReadTimeout(20000L, TimeUnit.MILLISECONDS);
        return okhttpclient;
    }

    protected HttpURLConnection openConnection(Request request)
        throws IOException
    {
        return OkHttpInstrumentation.open(client.open(new URL(request.getUrl())));
    }

    private final OkHttpClient client;
}
