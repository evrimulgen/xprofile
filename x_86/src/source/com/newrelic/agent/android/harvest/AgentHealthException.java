// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.newrelic.agent.android.harvest;

import com.newrelic.agent.android.harvest.type.HarvestableArray;
import com.newrelic.com.google.gson.*;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

public class AgentHealthException extends HarvestableArray
{

    public AgentHealthException(Exception exception)
    {
        this(exception, Thread.currentThread().getName());
    }

    public AgentHealthException(Exception exception, String s)
    {
        this(exception.getClass().getName(), exception.getMessage(), s, exception.getStackTrace());
    }

    public AgentHealthException(String s, String s1, String s2, StackTraceElement astacktraceelement[])
    {
        this(s, s1, s2, astacktraceelement, null);
    }

    public AgentHealthException(String s, String s1, String s2, StackTraceElement astacktraceelement[], Map map)
    {
        count = new AtomicLong(1L);
        exceptionClass = s;
        message = s1;
        threadName = s2;
        stackTrace = astacktraceelement;
        extras = map;
    }

    private JsonObject extrasToJson()
    {
        JsonObject jsonobject = new JsonObject();
        if(extras != null)
        {
            java.util.Map.Entry entry;
            for(Iterator iterator = extras.entrySet().iterator(); iterator.hasNext(); jsonobject.add((String)entry.getKey(), new JsonPrimitive((String)entry.getValue())))
                entry = (java.util.Map.Entry)iterator.next();

        }
        return jsonobject;
    }

    private JsonArray stackTraceToJson()
    {
        JsonArray jsonarray = new JsonArray();
        StackTraceElement astacktraceelement[] = stackTrace;
        int i = astacktraceelement.length;
        for(int j = 0; j < i; j++)
            jsonarray.add(new JsonPrimitive(astacktraceelement[j].toString()));

        return jsonarray;
    }

    public JsonArray asJsonArray()
    {
        JsonArray jsonarray = new JsonArray();
        jsonarray.add(new JsonPrimitive(exceptionClass));
        jsonarray.add(new JsonPrimitive(message));
        jsonarray.add(new JsonPrimitive(threadName));
        jsonarray.add(stackTraceToJson());
        jsonarray.add(new JsonPrimitive(Long.valueOf(count.get())));
        jsonarray.add(extrasToJson());
        return jsonarray;
    }

    public long getCount()
    {
        return count.get();
    }

    public String getExceptionClass()
    {
        return exceptionClass;
    }

    public Map getExtras()
    {
        return extras;
    }

    public String getMessage()
    {
        return message;
    }

    public String getSourceClass()
    {
        return stackTrace[0].getClassName();
    }

    public String getSourceMethod()
    {
        return stackTrace[0].getMethodName();
    }

    public StackTraceElement[] getStackTrace()
    {
        return stackTrace;
    }

    public String getThreadName()
    {
        return threadName;
    }

    public void increment()
    {
        count.getAndIncrement();
    }

    public void increment(long l)
    {
        count.getAndAdd(l);
    }

    private final AtomicLong count;
    private String exceptionClass;
    private Map extras;
    private String message;
    private StackTraceElement stackTrace[];
    private String threadName;
}
