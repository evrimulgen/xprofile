// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.newrelic.agent.android.harvest;

import com.newrelic.agent.android.harvest.type.HarvestableArray;
import com.newrelic.com.google.gson.*;
import java.util.HashMap;
import java.util.Map;

public class Event extends HarvestableArray
{

    public Event()
    {
        params = new HashMap();
    }

    public JsonArray asJsonArray()
    {
        JsonArray jsonarray = new JsonArray();
        jsonarray.add(new JsonPrimitive(Long.valueOf(timestamp)));
        jsonarray.add(new JsonPrimitive(Long.valueOf(eventName)));
        jsonarray.add((new Gson()).toJsonTree(params, GSON_STRING_MAP_TYPE));
        return jsonarray;
    }

    public long getEventName()
    {
        return eventName;
    }

    public Map getParams()
    {
        return params;
    }

    public long getTimestamp()
    {
        return timestamp;
    }

    public void setEventName(long l)
    {
        eventName = l;
    }

    public void setParams(Map map)
    {
        params = map;
    }

    public void setTimestamp(long l)
    {
        timestamp = l;
    }

    private long eventName;
    private Map params;
    private long timestamp;
}
