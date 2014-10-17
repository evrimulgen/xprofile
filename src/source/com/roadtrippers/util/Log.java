// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.roadtrippers.util;


public class Log
{

    public Log()
    {
    }

    public static void d(String s)
    {
    }

    public static transient void d(String s, Object aobj[])
    {
    }

    public static void e(String s)
    {
        if(s == null);
    }

    public static transient void e(String s, Object aobj[])
    {
        if(s != null && aobj != null)
            String.format(s, aobj);
    }

    public static void e(Throwable throwable)
    {
        if(throwable == null);
    }

    private static final String TAG = "roadtrippers";
}
