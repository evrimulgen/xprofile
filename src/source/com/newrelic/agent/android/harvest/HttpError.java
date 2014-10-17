// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.newrelic.agent.android.harvest;

import com.newrelic.agent.android.Agent;
import com.newrelic.agent.android.harvest.type.HarvestableArray;
import com.newrelic.agent.android.harvest.type.HarvestableObject;
import com.newrelic.agent.android.logging.AgentLog;
import com.newrelic.agent.android.logging.AgentLogManager;
import com.newrelic.agent.android.measurement.http.HttpErrorMeasurement;
import com.newrelic.agent.android.util.Encoder;
import com.newrelic.com.google.gson.*;
import java.nio.ByteBuffer;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Collections;
import java.util.Map;

// Referenced classes of package com.newrelic.agent.android.harvest:
//            Harvest, HarvestConfiguration

public class HttpError extends HarvestableArray
{

    public HttpError()
    {
    }

    public HttpError(HttpErrorMeasurement httperrormeasurement)
    {
        this(httperrormeasurement.getUrl(), httperrormeasurement.getHttpStatusCode(), httperrormeasurement.getResponseBody(), httperrormeasurement.getStackTrace(), httperrormeasurement.getParams());
        setTimestamp(Long.valueOf(httperrormeasurement.getStartTime()));
    }

    public HttpError(String s, int i, String s1, String s2, Map map)
    {
        url = s;
        httpStatusCode = i;
        responseBody = s1;
        stackTrace = s2;
        params = map;
        count = 1L;
        digest = computeHash();
    }

    private String computeHash()
    {
        MessageDigest messagedigest;
        try
        {
            messagedigest = MessageDigest.getInstance("SHA-1");
        }
        catch(NoSuchAlgorithmException nosuchalgorithmexception)
        {
            log.error("Unable to initialize SHA-1 hash algorithm");
            return null;
        }
        messagedigest.update(url.getBytes());
        messagedigest.update(ByteBuffer.allocate(8).putInt(httpStatusCode).array());
        if(stackTrace != null && stackTrace.length() > 0)
            messagedigest.update(stackTrace.getBytes());
        return new String(messagedigest.digest());
    }

    public JsonArray asJsonArray()
    {
        int i = Harvest.getHarvestConfiguration().getResponse_body_limit();
        JsonArray jsonarray = new JsonArray();
        jsonarray.add(new JsonPrimitive(url));
        jsonarray.add(new JsonPrimitive(Integer.valueOf(httpStatusCode)));
        jsonarray.add(new JsonPrimitive(Long.valueOf(count)));
        String s = optional(responseBody);
        if(s.length() > i)
        {
            log.warning((new StringBuilder()).append("HTTP Error response body is too large. Truncating to ").append(i).append(" bytes.").toString());
            s = s.substring(0, i);
        }
        jsonarray.add(new JsonPrimitive(Agent.getEncoder().encode(s.getBytes())));
        jsonarray.add(new JsonPrimitive(optional(stackTrace)));
        JsonObject jsonobject = new JsonObject();
        if(params == null)
            params = Collections.emptyMap();
        jsonobject.add("custom_params", HarvestableObject.fromMap(params).asJson());
        jsonarray.add(jsonobject);
        jsonarray.add(new JsonPrimitive(optional(appData)));
        return jsonarray;
    }

    public void digest()
    {
        digest = computeHash();
    }

    public String getHash()
    {
        return digest;
    }

    public Long getTimestamp()
    {
        return timestamp;
    }

    public void incrementCount()
    {
        count = 1L + count;
    }

    public void setAppData(String s)
    {
        appData = s;
    }

    public void setCount(long l)
    {
        count = l;
    }

    public void setHttpStatusCode(int i)
    {
        httpStatusCode = i;
    }

    public void setParams(Map map)
    {
        params = map;
    }

    public void setResponseBody(String s)
    {
        responseBody = s;
    }

    public void setStackTrace(String s)
    {
        stackTrace = s;
    }

    public void setTimestamp(Long long1)
    {
        timestamp = long1;
    }

    public void setUrl(String s)
    {
        url = s;
    }

    private static final AgentLog log = AgentLogManager.getAgentLog();
    private String appData;
    private long count;
    private String digest;
    private int httpStatusCode;
    private Map params;
    private String responseBody;
    private String stackTrace;
    private Long timestamp;
    private String url;

}
