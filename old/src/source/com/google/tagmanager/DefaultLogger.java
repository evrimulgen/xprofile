// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.tagmanager;

import android.util.Log;

// Referenced classes of package com.google.tagmanager:
//            Logger

class DefaultLogger
    implements Logger
{

    DefaultLogger()
    {
        mLogLevel = Logger.LogLevel.WARNING;
    }

    public void d(String s)
    {
        if(mLogLevel.ordinal() <= Logger.LogLevel.DEBUG.ordinal())
            Log.d("GoogleTagManager", s);
    }

    public void d(String s, Throwable throwable)
    {
        if(mLogLevel.ordinal() <= Logger.LogLevel.DEBUG.ordinal())
            Log.d("GoogleTagManager", s, throwable);
    }

    public void e(String s)
    {
        if(mLogLevel.ordinal() <= Logger.LogLevel.ERROR.ordinal())
            Log.e("GoogleTagManager", s);
    }

    public void e(String s, Throwable throwable)
    {
        if(mLogLevel.ordinal() <= Logger.LogLevel.ERROR.ordinal())
            Log.e("GoogleTagManager", s, throwable);
    }

    public Logger.LogLevel getLogLevel()
    {
        return mLogLevel;
    }

    public void i(String s)
    {
        if(mLogLevel.ordinal() <= Logger.LogLevel.INFO.ordinal())
            Log.i("GoogleTagManager", s);
    }

    public void i(String s, Throwable throwable)
    {
        if(mLogLevel.ordinal() <= Logger.LogLevel.INFO.ordinal())
            Log.i("GoogleTagManager", s, throwable);
    }

    public void setLogLevel(Logger.LogLevel loglevel)
    {
        mLogLevel = loglevel;
    }

    public void v(String s)
    {
        if(mLogLevel.ordinal() <= Logger.LogLevel.VERBOSE.ordinal())
            Log.v("GoogleTagManager", s);
    }

    public void v(String s, Throwable throwable)
    {
        if(mLogLevel.ordinal() <= Logger.LogLevel.VERBOSE.ordinal())
            Log.v("GoogleTagManager", s, throwable);
    }

    public void w(String s)
    {
        if(mLogLevel.ordinal() <= Logger.LogLevel.WARNING.ordinal())
            Log.w("GoogleTagManager", s);
    }

    public void w(String s, Throwable throwable)
    {
        if(mLogLevel.ordinal() <= Logger.LogLevel.WARNING.ordinal())
            Log.w("GoogleTagManager", s, throwable);
    }

    private static final String LOG_TAG = "GoogleTagManager";
    private Logger.LogLevel mLogLevel;
}
