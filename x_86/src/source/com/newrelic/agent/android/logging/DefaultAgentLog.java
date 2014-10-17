// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.newrelic.agent.android.logging;


// Referenced classes of package com.newrelic.agent.android.logging:
//            AgentLog, NullAgentLog

public class DefaultAgentLog
    implements AgentLog
{

    public DefaultAgentLog()
    {
        impl = new NullAgentLog();
    }

    public void debug(String s)
    {
        this;
        JVM INSTR monitorenter ;
        impl.debug(s);
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        this;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public void error(String s)
    {
        this;
        JVM INSTR monitorenter ;
        impl.error(s);
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        this;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public void error(String s, Throwable throwable)
    {
        this;
        JVM INSTR monitorenter ;
        impl.error(s, throwable);
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        this;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public int getLevel()
    {
        this;
        JVM INSTR monitorenter ;
        int i = impl.getLevel();
        this;
        JVM INSTR monitorexit ;
        return i;
        Exception exception;
        exception;
        this;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public void info(String s)
    {
        this;
        JVM INSTR monitorenter ;
        impl.info(s);
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        this;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public void setImpl(AgentLog agentlog)
    {
        this;
        JVM INSTR monitorenter ;
        impl = agentlog;
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        this;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public void setLevel(int i)
    {
        this;
        JVM INSTR monitorenter ;
        impl.setLevel(i);
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        this;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public void verbose(String s)
    {
        this;
        JVM INSTR monitorenter ;
        impl.verbose(s);
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        this;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public void warning(String s)
    {
        this;
        JVM INSTR monitorenter ;
        impl.warning(s);
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        this;
        JVM INSTR monitorexit ;
        throw exception;
    }

    private AgentLog impl;
}
