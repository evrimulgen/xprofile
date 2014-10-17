// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.tagmanager;

import android.content.Context;
import com.google.analytics.tracking.android.*;

// Referenced classes of package com.google.tagmanager:
//            Log

class TrackerProvider
{
    static class LoggerImpl
        implements Logger
    {

        private static com.google.analytics.tracking.android.Logger.LogLevel toAnalyticsLogLevel(Logger.LogLevel loglevel)
        {
            static class _cls1
            {

                static final int $SwitchMap$com$google$tagmanager$Logger$LogLevel[];

                static 
                {
                    $SwitchMap$com$google$tagmanager$Logger$LogLevel = new int[Logger.LogLevel.values().length];
                    try
                    {
                        $SwitchMap$com$google$tagmanager$Logger$LogLevel[Logger.LogLevel.NONE.ordinal()] = 1;
                    }
                    catch(NoSuchFieldError nosuchfielderror) { }
                    try
                    {
                        $SwitchMap$com$google$tagmanager$Logger$LogLevel[Logger.LogLevel.ERROR.ordinal()] = 2;
                    }
                    catch(NoSuchFieldError nosuchfielderror1) { }
                    try
                    {
                        $SwitchMap$com$google$tagmanager$Logger$LogLevel[Logger.LogLevel.WARNING.ordinal()] = 3;
                    }
                    catch(NoSuchFieldError nosuchfielderror2) { }
                    try
                    {
                        $SwitchMap$com$google$tagmanager$Logger$LogLevel[Logger.LogLevel.INFO.ordinal()] = 4;
                    }
                    catch(NoSuchFieldError nosuchfielderror3) { }
                    try
                    {
                        $SwitchMap$com$google$tagmanager$Logger$LogLevel[Logger.LogLevel.DEBUG.ordinal()] = 5;
                    }
                    catch(NoSuchFieldError nosuchfielderror4) { }
                    try
                    {
                        $SwitchMap$com$google$tagmanager$Logger$LogLevel[Logger.LogLevel.VERBOSE.ordinal()] = 6;
                    }
                    catch(NoSuchFieldError nosuchfielderror5)
                    {
                        return;
                    }
                }
            }

            switch(_cls1..SwitchMap.com.google.tagmanager.Logger.LogLevel[loglevel.ordinal()])
            {
            default:
                return com.google.analytics.tracking.android.Logger.LogLevel.ERROR;

            case 1: // '\001'
            case 2: // '\002'
                return com.google.analytics.tracking.android.Logger.LogLevel.ERROR;

            case 3: // '\003'
                return com.google.analytics.tracking.android.Logger.LogLevel.WARNING;

            case 4: // '\004'
            case 5: // '\005'
                return com.google.analytics.tracking.android.Logger.LogLevel.INFO;

            case 6: // '\006'
                return com.google.analytics.tracking.android.Logger.LogLevel.VERBOSE;
            }
        }

        public void error(Exception exception)
        {
            Log.e("", exception);
        }

        public void error(String s)
        {
            Log.e(s);
        }

        public com.google.analytics.tracking.android.Logger.LogLevel getLogLevel()
        {
            Logger.LogLevel loglevel = Log.getLogLevel();
            if(loglevel == null)
                return com.google.analytics.tracking.android.Logger.LogLevel.ERROR;
            else
                return toAnalyticsLogLevel(loglevel);
        }

        public void info(String s)
        {
            Log.i(s);
        }

        public void setLogLevel(com.google.analytics.tracking.android.Logger.LogLevel loglevel)
        {
            Log.w("GA uses GTM logger. Please use TagManager.getLogger().setLogLevel(LogLevel) instead.");
        }

        public void verbose(String s)
        {
            Log.v(s);
        }

        public void warn(String s)
        {
            Log.w(s);
        }

        LoggerImpl()
        {
        }
    }


    TrackerProvider(Context context)
    {
        mContext = context;
    }

    TrackerProvider(GoogleAnalytics googleanalytics)
    {
        mGoogleAnalytics = googleanalytics;
        mGoogleAnalytics.setLogger(new LoggerImpl());
    }

    private void initTrackProviderIfNecessary()
    {
        this;
        JVM INSTR monitorenter ;
        if(mGoogleAnalytics == null)
        {
            mGoogleAnalytics = GoogleAnalytics.getInstance(mContext);
            mGoogleAnalytics.setLogger(new LoggerImpl());
        }
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public void close(Tracker tracker)
    {
        mGoogleAnalytics.closeTracker(tracker.getName());
    }

    public Tracker getTracker(String s)
    {
        initTrackProviderIfNecessary();
        return mGoogleAnalytics.getTracker(s);
    }

    private Context mContext;
    private GoogleAnalytics mGoogleAnalytics;
}
