// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.quantcast.measurement.service;

import android.util.Log;

public class QCLog
{
    public static class Tag
    {

        public final String safeTag;

        public Tag(Class class1)
        {
            String s = class1.getSimpleName();
            if(s.length() > 21)
                s = s.substring(-21 + s.length());
            safeTag = (new StringBuilder()).append("q.").append(s).toString();
        }
    }


    public QCLog()
    {
    }

    public static void e(Tag tag, String s)
    {
        log(6, tag, s);
    }

    public static void e(Tag tag, String s, Throwable throwable)
    {
        log(6, tag, s, throwable);
    }

    public static void i(Tag tag, String s)
    {
        log(4, tag, s);
    }

    private static void log(int j, Tag tag, String s)
    {
        if(logLevel <= j && Log.isLoggable(tag.safeTag, j))
            Log.println(j, tag.safeTag, s);
    }

    private static void log(int j, Tag tag, String s, Throwable throwable)
    {
        if(logLevel <= j && Log.isLoggable(tag.safeTag, j))
            Log.println(j, tag.safeTag, (new StringBuilder()).append(s).append('\n').append(Log.getStackTraceString(throwable)).toString());
    }

    static void setLogLevel(int j)
    {
        logLevel = j;
    }

    public static void w(Tag tag, String s)
    {
        log(5, tag, s);
    }

    public static void w(Tag tag, String s, Throwable throwable)
    {
        log(5, tag, s, throwable);
    }

    private static int logLevel = 6;

}
