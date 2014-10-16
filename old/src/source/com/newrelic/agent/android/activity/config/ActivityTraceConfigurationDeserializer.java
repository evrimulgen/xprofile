// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.newrelic.agent.android.activity.config;

import com.newrelic.agent.android.logging.AgentLog;
import com.newrelic.agent.android.logging.AgentLogManager;
import com.newrelic.com.google.gson.*;
import java.lang.reflect.Type;

// Referenced classes of package com.newrelic.agent.android.activity.config:
//            ActivityTraceConfiguration

public class ActivityTraceConfigurationDeserializer
    implements JsonDeserializer
{

    public ActivityTraceConfigurationDeserializer()
    {
    }

    private void error(String s)
    {
        log.error((new StringBuilder()).append("ActivityTraceConfigurationDeserializer: ").append(s).toString());
    }

    private Integer getInteger(JsonElement jsonelement)
    {
        if(!jsonelement.isJsonPrimitive())
        {
            error("Expected an integer.");
            return null;
        }
        JsonPrimitive jsonprimitive = jsonelement.getAsJsonPrimitive();
        if(!jsonprimitive.isNumber())
        {
            error("Expected an integer.");
            return null;
        }
        int i = jsonprimitive.getAsInt();
        if(i < 0)
        {
            error("Integer value must not be negative");
            return null;
        } else
        {
            return Integer.valueOf(i);
        }
    }

    public ActivityTraceConfiguration deserialize(JsonElement jsonelement, Type type, JsonDeserializationContext jsondeserializationcontext)
        throws JsonParseException
    {
        ActivityTraceConfiguration activitytraceconfiguration = new ActivityTraceConfiguration();
        if(!jsonelement.isJsonArray())
        {
            error("Expected root element to be an array.");
            return null;
        }
        JsonArray jsonarray = jsonelement.getAsJsonArray();
        if(jsonarray.size() != 2)
        {
            error("Root array must contain 2 elements.");
            return null;
        }
        Integer integer = getInteger(jsonarray.get(0));
        if(integer == null)
            return null;
        if(integer.intValue() < 0)
        {
            error("The first element of the root array must not be negative.");
            return null;
        } else
        {
            activitytraceconfiguration.setMaxTotalTraceCount(integer.intValue());
            return activitytraceconfiguration;
        }
    }

    public volatile Object deserialize(JsonElement jsonelement, Type type, JsonDeserializationContext jsondeserializationcontext)
        throws JsonParseException
    {
        return deserialize(jsonelement, type, jsondeserializationcontext);
    }

    private final AgentLog log = AgentLogManager.getAgentLog();
}
