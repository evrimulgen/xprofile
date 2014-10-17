// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.newrelic.agent.android.harvest;

import com.newrelic.agent.android.harvest.type.HarvestableArray;
import com.newrelic.agent.android.metric.Metric;
import com.newrelic.agent.android.metric.MetricStore;
import com.newrelic.com.google.gson.Gson;
import com.newrelic.com.google.gson.JsonArray;
import java.util.*;

public class MachineMeasurements extends HarvestableArray
{

    public MachineMeasurements()
    {
    }

    public void addMetric(Metric metric)
    {
        metrics.add(metric);
    }

    public void addMetric(String s, double d)
    {
        Metric metric = new Metric(s);
        metric.sample(d);
        addMetric(metric);
    }

    public JsonArray asJsonArray()
    {
        JsonArray jsonarray = new JsonArray();
        JsonArray jsonarray1;
        for(Iterator iterator = metrics.getAll().iterator(); iterator.hasNext(); jsonarray.add(jsonarray1))
        {
            Metric metric = (Metric)iterator.next();
            jsonarray1 = new JsonArray();
            HashMap hashmap = new HashMap();
            hashmap.put("name", metric.getName());
            hashmap.put("scope", metric.getStringScope());
            jsonarray1.add((new Gson()).toJsonTree(hashmap, GSON_STRING_MAP_TYPE));
            jsonarray1.add(metric.asJsonObject());
        }

        return jsonarray;
    }

    public void clear()
    {
        metrics.clear();
    }

    public MetricStore getMetrics()
    {
        return metrics;
    }

    public boolean isEmpty()
    {
        return metrics.isEmpty();
    }

    private final MetricStore metrics = new MetricStore();
}
