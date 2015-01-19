// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.newrelic.agent.android.harvest;

import com.newrelic.agent.android.harvest.type.HarvestableArray;
import com.newrelic.com.google.gson.JsonArray;
import java.util.*;

// Referenced classes of package com.newrelic.agent.android.harvest:
//            HttpTransaction

public class HttpTransactions extends HarvestableArray
{

    public HttpTransactions()
    {
    }

    public void add(HttpTransaction httptransaction)
    {
        this;
        JVM INSTR monitorenter ;
        httpTransactions.add(httptransaction);
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
        for(Iterator iterator = httpTransactions.iterator(); iterator.hasNext(); jsonarray.add(((HttpTransaction)iterator.next()).asJson()));
        return jsonarray;
    }

    public void clear()
    {
        httpTransactions.clear();
    }

    public int count()
    {
        return httpTransactions.size();
    }

    public Collection getHttpTransactions()
    {
        return httpTransactions;
    }

    public void remove(HttpTransaction httptransaction)
    {
        this;
        JVM INSTR monitorenter ;
        httpTransactions.remove(httptransaction);
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public String toString()
    {
        return (new StringBuilder()).append("HttpTransactions{httpTransactions=").append(httpTransactions).append('}').toString();
    }

    private final Collection httpTransactions = new ArrayList();
}
