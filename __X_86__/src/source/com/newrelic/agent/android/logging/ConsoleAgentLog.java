// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.newrelic.agent.android.logging;

import java.io.PrintStream;

// Referenced classes of package com.newrelic.agent.android.logging:
//            AgentLog

public class ConsoleAgentLog
    implements AgentLog
{

    public ConsoleAgentLog()
    {
        level = 3;
    }

    private static void print(String s, String s1)
    {
        System.out.println((new StringBuilder()).append("[").append(s).append("] ").append(s1).toString());
    }

    public void debug(String s)
    {
        if(level == 5)
            print("DEBUG", s);
    }

    public void error(String s)
    {
        if(level >= 1)
            print("ERROR", s);
    }

    public void error(String s, Throwable throwable)
    {
        if(level >= 1)
            print("ERROR", (new StringBuilder()).append(s).append(" ").append(throwable.getMessage()).toString());
    }

    public int getLevel()
    {
        return level;
    }

    public void info(String s)
    {
        if(level >= 3)
            print("INFO", s);
    }

    public void setLevel(int i)
    {
        level = i;
    }

    public void verbose(String s)
    {
        if(level >= 4)
            print("VERBOSE", s);
    }

    public void warning(String s)
    {
        if(level >= 2)
            print("WARN", s);
    }

    private int level;
}
