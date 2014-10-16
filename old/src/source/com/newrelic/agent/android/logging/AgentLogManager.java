// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.newrelic.agent.android.logging;


// Referenced classes of package com.newrelic.agent.android.logging:
//            DefaultAgentLog, AgentLog

public class AgentLogManager
{

    public AgentLogManager()
    {
    }

    public static AgentLog getAgentLog()
    {
        return instance;
    }

    public static void setAgentLog(AgentLog agentlog)
    {
        instance.setImpl(agentlog);
    }

    private static DefaultAgentLog instance = new DefaultAgentLog();

}
