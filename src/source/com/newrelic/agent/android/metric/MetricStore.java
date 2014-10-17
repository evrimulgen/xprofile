// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.newrelic.agent.android.metric;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

// Referenced classes of package com.newrelic.agent.android.metric:
//            Metric

public class MetricStore
{

    public MetricStore()
    {
    }

    public void add(Metric metric)
    {
        String s = metric.getStringScope();
        String s1 = metric.getName();
        if(!metricStore.containsKey(s))
            metricStore.put(s, new HashMap());
        if(((Map)metricStore.get(s)).containsKey(s1))
        {
            ((Metric)((Map)metricStore.get(s)).get(s1)).aggregate(metric);
            return;
        } else
        {
            ((Map)metricStore.get(s)).put(s1, metric);
            return;
        }
    }

    public void clear()
    {
        metricStore.clear();
    }

    public Metric get(String s)
    {
        return get(s, "");
    }

    public Metric get(String s, String s1)
    {
        Map map;
        Metric metric;
        try
        {
            map = metricStore;
        }
        catch(NullPointerException nullpointerexception)
        {
            return null;
        }
        if(s1 == null)
            s1 = "";
        metric = (Metric)((Map)map.get(s1)).get(s);
        return metric;
    }

    public List getAll()
    {
        ArrayList arraylist = new ArrayList();
        for(Iterator iterator = metricStore.entrySet().iterator(); iterator.hasNext();)
        {
            Iterator iterator1 = ((Map)((java.util.Map.Entry)iterator.next()).getValue()).entrySet().iterator();
            while(iterator1.hasNext()) 
                arraylist.add(((java.util.Map.Entry)iterator1.next()).getValue());
        }

        return arraylist;
    }

    public List getAllByScope(String s)
    {
        ArrayList arraylist = new ArrayList();
        try
        {
            for(Iterator iterator = ((Map)metricStore.get(s)).entrySet().iterator(); iterator.hasNext(); arraylist.add(((java.util.Map.Entry)iterator.next()).getValue()));
        }
        catch(NullPointerException nullpointerexception) { }
        return arraylist;
    }

    public List getAllUnscoped()
    {
        return getAllByScope("");
    }

    public boolean isEmpty()
    {
        return metricStore.isEmpty();
    }

    public void remove(Metric metric)
    {
        String s = metric.getStringScope();
        String s1;
        for(s1 = metric.getName(); !metricStore.containsKey(s) || !((Map)metricStore.get(s)).containsKey(s1);)
            return;

        ((Map)metricStore.get(s)).remove(s1);
    }

    public void removeAll(List list)
    {
        Map map = metricStore;
        map;
        JVM INSTR monitorenter ;
        for(Iterator iterator = list.iterator(); iterator.hasNext(); remove((Metric)iterator.next()));
        break MISSING_BLOCK_LABEL_47;
        Exception exception;
        exception;
        map;
        JVM INSTR monitorexit ;
        throw exception;
        map;
        JVM INSTR monitorexit ;
    }

    public List removeAllWithScope(String s)
    {
        List list = getAllByScope(s);
        if(!list.isEmpty())
            removeAll(list);
        return list;
    }

    private final Map metricStore = new ConcurrentHashMap();
}
