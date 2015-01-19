// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.newrelic.agent.android.logging;


public interface AgentLog
{

    public abstract void debug(String s);

    public abstract void error(String s);

    public abstract void error(String s, Throwable throwable);

    public abstract int getLevel();

    public abstract void info(String s);

    public abstract void setLevel(int i);

    public abstract void verbose(String s);

    public abstract void warning(String s);

    public static final int DEBUG = 5;
    public static final int ERROR = 1;
    public static final int INFO = 3;
    public static final int VERBOSE = 4;
    public static final int WARNING = 2;
}
