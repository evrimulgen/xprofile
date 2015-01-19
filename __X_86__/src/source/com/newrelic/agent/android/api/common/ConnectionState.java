// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.newrelic.agent.android.api.common;

import java.util.concurrent.TimeUnit;

public final class ConnectionState
{

    private ConnectionState()
    {
        dataToken = null;
        crossProcessId = null;
        serverTimestamp = 0L;
        harvestInterval = 60L;
        harvestIntervalTimeUnit = TimeUnit.SECONDS;
        maxTransactionAge = 600L;
        maxTransactionAgeTimeUnit = TimeUnit.SECONDS;
        maxTransactionCount = 1000L;
        stackTraceLimit = 50;
        responseBodyLimit = 1024;
        collectingNetworkErrors = true;
        errorLimit = 10;
    }

    public ConnectionState(Object obj, String s, long l, long l1, TimeUnit timeunit, 
            long l2, TimeUnit timeunit1, long l3, int i, int j, 
            boolean flag, int k)
    {
        dataToken = obj;
        crossProcessId = s;
        serverTimestamp = l;
        harvestInterval = l1;
        harvestIntervalTimeUnit = timeunit;
        maxTransactionAge = l2;
        maxTransactionAgeTimeUnit = timeunit1;
        maxTransactionCount = l3;
        stackTraceLimit = i;
        responseBodyLimit = j;
        collectingNetworkErrors = flag;
        errorLimit = k;
    }

    public String getCrossProcessId()
    {
        return crossProcessId;
    }

    public Object getDataToken()
    {
        return dataToken;
    }

    public int getErrorLimit()
    {
        return errorLimit;
    }

    public long getHarvestIntervalInMilliseconds()
    {
        return TimeUnit.MILLISECONDS.convert(harvestInterval, harvestIntervalTimeUnit);
    }

    public long getHarvestIntervalInSeconds()
    {
        return TimeUnit.SECONDS.convert(harvestInterval, harvestIntervalTimeUnit);
    }

    public long getMaxTransactionAgeInMilliseconds()
    {
        return TimeUnit.MILLISECONDS.convert(maxTransactionAge, maxTransactionAgeTimeUnit);
    }

    public long getMaxTransactionAgeInSeconds()
    {
        return TimeUnit.SECONDS.convert(maxTransactionAge, maxTransactionAgeTimeUnit);
    }

    public long getMaxTransactionCount()
    {
        return maxTransactionCount;
    }

    public int getResponseBodyLimit()
    {
        return responseBodyLimit;
    }

    public long getServerTimestamp()
    {
        return serverTimestamp;
    }

    public int getStackTraceLimit()
    {
        return stackTraceLimit;
    }

    public boolean isCollectingNetworkErrors()
    {
        return collectingNetworkErrors;
    }

    public String toString()
    {
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append(dataToken);
        return stringbuilder.toString();
    }

    public static final ConnectionState NULL = new ConnectionState();
    private final boolean collectingNetworkErrors;
    private final String crossProcessId;
    private final Object dataToken;
    private final int errorLimit;
    private final long harvestInterval;
    private final TimeUnit harvestIntervalTimeUnit;
    private final long maxTransactionAge;
    private final TimeUnit maxTransactionAgeTimeUnit;
    private final long maxTransactionCount;
    private final int responseBodyLimit;
    private final long serverTimestamp;
    private final int stackTraceLimit;

}
