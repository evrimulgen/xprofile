// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.bugsnag.android;

import android.util.Log;

public class Logger extends com.bugsnag.Logger
{

    public Logger()
    {
    }

    public void debug(String s)
    {
        Log.d("Bugsnag", s);
    }

    public void info(String s)
    {
        Log.i("Bugsnag", s);
    }

    public void warn(String s)
    {
        Log.w("Bugsnag", s);
    }

    public void warn(String s, Throwable throwable)
    {
        Log.w("Bugsnag", s, throwable);
    }
}
