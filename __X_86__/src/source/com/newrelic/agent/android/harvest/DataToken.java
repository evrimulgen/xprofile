// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.newrelic.agent.android.harvest;

import com.newrelic.agent.android.harvest.type.HarvestableArray;
import com.newrelic.com.google.gson.JsonArray;
import com.newrelic.com.google.gson.JsonPrimitive;

public class DataToken extends HarvestableArray
{

    public DataToken()
    {
    }

    public DataToken(long l, long l1)
    {
        accountId = l;
        agentId = l1;
    }

    public JsonArray asJsonArray()
    {
        JsonArray jsonarray = new JsonArray();
        jsonarray.add(new JsonPrimitive(Long.valueOf(accountId)));
        jsonarray.add(new JsonPrimitive(Long.valueOf(agentId)));
        return jsonarray;
    }

    public void clear()
    {
        accountId = 0L;
        agentId = 0L;
    }

    public long getAccountId()
    {
        return accountId;
    }

    public long getAgentId()
    {
        return agentId;
    }

    public boolean isValid()
    {
        return accountId > 0L && agentId > 0L;
    }

    public void setAccountId(long l)
    {
        accountId = l;
    }

    public void setAgentId(long l)
    {
        agentId = l;
    }

    public String toString()
    {
        return (new StringBuilder()).append("DataToken{accountId=").append(accountId).append(", agentId=").append(agentId).append('}').toString();
    }

    private long accountId;
    private long agentId;
}
