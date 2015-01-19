// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package io.segment.android.stats;

import io.segment.android.Logger;

public class Stopwatch
{

    public Stopwatch(String s)
    {
        msg = s;
        start();
    }

    public long duration()
    {
        return end - start;
    }

    public void end()
    {
        end = System.currentTimeMillis();
        Logger.i((new StringBuilder(String.valueOf(msg))).append(" finished in : ").append(duration()).append(" milliseconds.").toString());
    }

    public void start()
    {
        start = System.currentTimeMillis();
    }

    private long end;
    private String msg;
    private long start;
}
