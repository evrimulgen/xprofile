// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.analytics.tracking.android;

import android.util.Log;

// Referenced classes of package com.google.analytics.tracking.android:
//            Logger

class DefaultLoggerImpl
    implements Logger
{

    DefaultLoggerImpl()
    {
        mLogLevel = Logger.LogLevel.INFO;
    }

    private String formatMessage(String s)
    {
        return (new StringBuilder()).append(Thread.currentThread().toString()).append(": ").append(s).toString();
    }

    public void error(Exception exception)
    {
        if(mLogLevel.ordinal() <= Logger.LogLevel.ERROR.ordinal())
            Log.e("GAV3", null, exception);
    }

    public void error(String s)
    {
        if(mLogLevel.ordinal() <= Logger.LogLevel.ERROR.ordinal())
            Log.e("GAV3", formatMessage(s));
    }

    public Logger.LogLevel getLogLevel()
    {
        return mLogLevel;
    }

    public void info(String s)
    {
        if(mLogLevel.ordinal() <= Logger.LogLevel.INFO.ordinal())
            Log.i("GAV3", formatMessage(s));
    }

    public void setLogLevel(Logger.LogLevel loglevel)
    {
        mLogLevel = loglevel;
    }

    public void verbose(String s)
    {
        if(mLogLevel.ordinal() <= Logger.LogLevel.VERBOSE.ordinal())
            Log.v("GAV3", formatMessage(s));
    }

    public void warn(String s)
    {
        if(mLogLevel.ordinal() <= Logger.LogLevel.WARNING.ordinal())
            Log.w("GAV3", formatMessage(s));
    }

    static final String LOG_TAG = "GAV3";
    private Logger.LogLevel mLogLevel;
}
