// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package io.segment.android.request;

import android.util.Log;
import com.newrelic.agent.android.instrumentation.HttpInstrumentation;
import com.newrelic.agent.android.instrumentation.JSONObjectInstrumentation;
import io.segment.android.*;
import io.segment.android.models.Batch;
import io.segment.android.models.EasyJSONObject;
import java.util.Calendar;
import java.util.Map;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

// Referenced classes of package io.segment.android.request:
//            IRequester

public class BasicRequester
    implements IRequester
{

    public BasicRequester()
    {
    }

    public EasyJSONObject fetchSettings()
    {
        DefaultHttpClient defaulthttpclient;
        HttpGet httpget;
        String s = (new StringBuilder(String.valueOf(Analytics.getOptions().getHost()))).append(Defaults.getSettingsEndpoint(Analytics.getWriteKey())).toString();
        defaulthttpclient = new DefaultHttpClient();
        httpget = new HttpGet(s);
        if(defaulthttpclient instanceof HttpClient) goto _L2; else goto _L1
_L1:
        HttpResponse httpresponse1 = defaulthttpclient.execute(httpget);
_L3:
        String s1 = EntityUtils.toString(httpresponse1.getEntity());
        JVM INSTR new #78  <Class JSONObject>;
        return new EasyJSONObject(JSONObjectInstrumentation.init(s1));
_L2:
        HttpResponse httpresponse = HttpInstrumentation.execute((HttpClient)defaulthttpclient, httpget);
        httpresponse1 = httpresponse;
          goto _L3
        Exception exception;
        exception;
        Logger.w((new StringBuilder("Failed to send request. ")).append(Log.getStackTraceString(exception)).toString());
        return null;
    }

    public HttpResponse send(Batch batch)
    {
        batch.setRequestTimestamp(Calendar.getInstance());
        String s = (new StringBuilder(String.valueOf(Analytics.getOptions().getHost()))).append((String)Defaults.ENDPOINTS.get("import")).toString();
        String s1 = batch.toString();
        DefaultHttpClient defaulthttpclient = new DefaultHttpClient();
        HttpPost httppost = new HttpPost(s);
        httppost.setHeader("Content-Type", "application/json");
        HttpResponse httpresponse;
        try
        {
            ByteArrayEntity bytearrayentity = new ByteArrayEntity(s1.getBytes());
            bytearrayentity.setContentEncoding(new BasicHeader("Content-Type", "application/json"));
            httppost.setEntity(bytearrayentity);
            if(!(defaulthttpclient instanceof HttpClient))
                return defaulthttpclient.execute(httppost);
            httpresponse = HttpInstrumentation.execute((HttpClient)defaulthttpclient, httppost);
        }
        catch(Exception exception)
        {
            Logger.w((new StringBuilder("Failed to send request. ")).append(Log.getStackTraceString(exception)).toString());
            return null;
        }
        return httpresponse;
    }
}
