// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.newrelic.agent.android.harvest;

import com.newrelic.agent.android.harvest.type.HarvestableArray;
import com.newrelic.agent.android.tracing.ActivityTrace;
import com.newrelic.com.google.gson.JsonArray;
import java.util.*;

public class ActivityTraces extends HarvestableArray
{

    public ActivityTraces()
    {
    }

    public void add(ActivityTrace activitytrace)
    {
        this;
        JVM INSTR monitorenter ;
        activityTraces.add(activitytrace);
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public JsonArray asJsonArray()
    {
        JsonArray jsonarray = new JsonArray();
        for(Iterator iterator = activityTraces.iterator(); iterator.hasNext(); jsonarray.add(((ActivityTrace)iterator.next()).asJson()));
        return jsonarray;
    }

    public void clear()
    {
        activityTraces.clear();
    }

    public int count()
    {
        return activityTraces.size();
    }

    public Collection getActivityTraces()
    {
        return activityTraces;
    }

    public void remove(ActivityTrace activitytrace)
    {
        this;
        JVM INSTR monitorenter ;
        activityTraces.remove(activitytrace);
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    private final Collection activityTraces = new ArrayList();
}
