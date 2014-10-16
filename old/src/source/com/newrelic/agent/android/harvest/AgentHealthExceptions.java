// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.newrelic.agent.android.harvest;

import com.newrelic.agent.android.harvest.type.HarvestableObject;
import com.newrelic.com.google.gson.*;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

// Referenced classes of package com.newrelic.agent.android.harvest:
//            AgentHealthException

public class AgentHealthExceptions extends HarvestableObject
{

    public AgentHealthExceptions()
    {
        keyArray.add(new JsonPrimitive("ExceptionClass"));
        keyArray.add(new JsonPrimitive("Message"));
        keyArray.add(new JsonPrimitive("ThreadName"));
        keyArray.add(new JsonPrimitive("CallStack"));
        keyArray.add(new JsonPrimitive("Count"));
        keyArray.add(new JsonPrimitive("Extras"));
    }

    public void add(AgentHealthException agenthealthexception)
    {
        String s = (new StringBuilder()).append(agenthealthexception.getExceptionClass()).append(agenthealthexception.getStackTrace()[0].toString()).toString();
        Map map = agentHealthExceptions;
        map;
        JVM INSTR monitorenter ;
        AgentHealthException agenthealthexception1 = (AgentHealthException)agentHealthExceptions.get(s);
        if(agenthealthexception1 != null)
            break MISSING_BLOCK_LABEL_72;
        agentHealthExceptions.put(s, agenthealthexception);
_L2:
        map;
        JVM INSTR monitorexit ;
        return;
        agenthealthexception1.increment();
        if(true) goto _L2; else goto _L1
_L1:
        Exception exception;
        exception;
        map;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public JsonObject asJsonObject()
    {
        JsonObject jsonobject = new JsonObject();
        JsonArray jsonarray = new JsonArray();
        for(Iterator iterator = agentHealthExceptions.values().iterator(); iterator.hasNext(); jsonarray.add(((AgentHealthException)iterator.next()).asJsonArray()));
        jsonobject.add("Type", new JsonPrimitive("AgentErrors"));
        jsonobject.add("Keys", keyArray);
        jsonobject.add("Data", jsonarray);
        return jsonobject;
    }

    public void clear()
    {
        synchronized(agentHealthExceptions)
        {
            agentHealthExceptions.clear();
        }
        return;
        exception;
        map;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public Map getAgentHealthExceptions()
    {
        return agentHealthExceptions;
    }

    public boolean isEmpty()
    {
        return agentHealthExceptions.isEmpty();
    }

    private static final JsonArray keyArray = new JsonArray();
    private final Map agentHealthExceptions = new ConcurrentHashMap();

}
