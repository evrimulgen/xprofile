// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.bugsnag;

import com.bugsnag.http.HttpClient;
import com.bugsnag.http.NetworkException;
import com.bugsnag.utils.JSONUtils;
import com.newrelic.agent.android.instrumentation.JSONObjectInstrumentation;
import java.io.UnsupportedEncodingException;
import org.json.JSONObject;

// Referenced classes of package com.bugsnag:
//            Configuration, Logger, Diagnostics

public class Metrics
{

    public Metrics(Configuration configuration, Diagnostics diagnostics1)
    {
        config = configuration;
        diagnostics = diagnostics1;
    }

    public void deliver()
        throws NetworkException
    {
        String s = config.getMetricsEndpoint();
        try
        {
            HttpClient.post(s, toString(), "application/json");
            return;
        }
        catch(UnsupportedEncodingException unsupportedencodingexception)
        {
            config.logger.warn("Bugsnag unable to send metrics", unsupportedencodingexception);
        }
    }

    public JSONObject toJSON()
    {
        JSONObject jsonobject = diagnostics.getMetrics();
        JSONUtils.safePut(jsonobject, "apiKey", config.apiKey);
        return jsonobject;
    }

    public String toString()
    {
        JSONObject jsonobject = toJSON();
        if(!(jsonobject instanceof JSONObject))
            return jsonobject.toString();
        else
            return JSONObjectInstrumentation.toString((JSONObject)jsonobject);
    }

    private Configuration config;
    private Diagnostics diagnostics;
}
