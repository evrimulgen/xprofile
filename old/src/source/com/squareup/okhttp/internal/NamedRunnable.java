// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.squareup.okhttp.internal;


public abstract class NamedRunnable
    implements Runnable
{

    public transient NamedRunnable(String s, Object aobj[])
    {
        name = String.format(s, aobj);
    }

    protected abstract void execute();

    public final void run()
    {
        String s;
        s = Thread.currentThread().getName();
        Thread.currentThread().setName(name);
        execute();
        Thread.currentThread().setName(s);
        return;
        Exception exception;
        exception;
        Thread.currentThread().setName(s);
        throw exception;
    }

    private final String name;
}
