// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.newrelic.agent.android.logging;


// Referenced classes of package com.newrelic.agent.android.logging:
//            AgentLog

public class NullAgentLog
    implements AgentLog
{

    public NullAgentLog()
    {
    }

    public void debug(String s)
    {
    }

    public void error(String s)
    {
    }

    public void error(String s, Throwable throwable)
    {
    }

    public int getLevel()
    {
        return 5;
    }

    public void info(String s)
    {
    }

    public void setLevel(int i)
    {
    }

    public void verbose(String s)
    {
    }

    public void warning(String s)
    {
    }
}
