// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.newrelic.agent.android;


public class AgentConfiguration
{

    public AgentConfiguration()
    {
        collectorHost = "mobile-collector.newrelic.com";
    }

    public String getApplicationToken()
    {
        return applicationToken;
    }

    public String getCollectorHost()
    {
        return collectorHost;
    }

    public void setApplicationToken(String s)
    {
        applicationToken = s;
    }

    public void setCollectorHost(String s)
    {
        collectorHost = s;
    }

    public void setUseSsl(boolean flag)
    {
        useSsl = flag;
    }

    public boolean useSsl()
    {
        return useSsl;
    }

    private final String DEFAULT_COLLECTOR_HOST = "mobile-collector.newrelic.com";
    private String applicationToken;
    private String collectorHost;
    private boolean useSsl;
}
