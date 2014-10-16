// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.newrelic.agent.android.stats;

import com.newrelic.agent.android.TaskQueue;
import com.newrelic.agent.android.harvest.HarvestAdapter;
import com.newrelic.agent.android.metric.Metric;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class StatsEngine extends HarvestAdapter
{

    private StatsEngine()
    {
        enabled = true;
        statsMap = new ConcurrentHashMap();
    }

    public static void disable()
    {
        com/newrelic/agent/android/stats/StatsEngine;
        JVM INSTR monitorenter ;
        INSTANCE.enabled = false;
        com/newrelic/agent/android/stats/StatsEngine;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public static void enable()
    {
        com/newrelic/agent/android/stats/StatsEngine;
        JVM INSTR monitorenter ;
        INSTANCE.enabled = true;
        com/newrelic/agent/android/stats/StatsEngine;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public static StatsEngine get()
    {
        return INSTANCE;
    }

    private ConcurrentHashMap getStatsMap()
    {
        return statsMap;
    }

    private Metric lazyGet(String s)
    {
        Metric metric = (Metric)statsMap.get(s);
        if(metric == null)
        {
            metric = new Metric(s);
            if(enabled)
                statsMap.put(s, metric);
        }
        return metric;
    }

    public static void populateMetrics()
    {
        for(Iterator iterator = INSTANCE.getStatsMap().entrySet().iterator(); iterator.hasNext(); TaskQueue.queue((Metric)((java.util.Map.Entry)iterator.next()).getValue()));
    }

    public static void reset()
    {
        INSTANCE.getStatsMap().clear();
    }

    public void inc(String s)
    {
        synchronized(lazyGet(s))
        {
            metric.increment();
        }
        return;
        exception;
        metric;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public void inc(String s, long l)
    {
        synchronized(lazyGet(s))
        {
            metric.increment(l);
        }
        return;
        exception;
        metric;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public void onHarvest()
    {
        populateMetrics();
        reset();
    }

    public void sample(String s, float f)
    {
        Metric metric = lazyGet(s);
        metric;
        JVM INSTR monitorenter ;
        double d = f;
        metric.sample(d);
        metric;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        metric;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public void sampleTimeMs(String s, long l)
    {
        sample(s, (float)l / 1000F);
    }

    public static final StatsEngine INSTANCE = new StatsEngine();
    public boolean enabled;
    private ConcurrentHashMap statsMap;

}
