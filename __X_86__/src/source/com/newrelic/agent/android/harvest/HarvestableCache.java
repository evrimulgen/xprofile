// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.newrelic.agent.android.harvest;

import com.newrelic.agent.android.harvest.type.Harvestable;
import java.util.*;

public class HarvestableCache
{

    public HarvestableCache()
    {
        limit = 1024;
    }

    public void add(Harvestable harvestable)
    {
        if(harvestable == null || cache.size() >= limit)
        {
            return;
        } else
        {
            cache.add(harvestable);
            return;
        }
    }

    public Collection flush()
    {
        if(cache.size() == 0)
            return Collections.emptyList();
        this;
        JVM INSTR monitorenter ;
        ArrayList arraylist;
        arraylist = new ArrayList(cache);
        cache.clear();
        this;
        JVM INSTR monitorexit ;
        return arraylist;
        Exception exception;
        exception;
        this;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public int getSize()
    {
        return cache.size();
    }

    public void setLimit(int i)
    {
        limit = i;
    }

    private static final int DEFAULT_CACHE_LIMIT = 1024;
    private final Collection cache = new ArrayList();
    private int limit;
}
