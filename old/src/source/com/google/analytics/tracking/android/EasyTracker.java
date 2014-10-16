// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.analytics.tracking.android;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import java.util.*;

// Referenced classes of package com.google.analytics.tracking.android:
//            Tracker, ParameterLoaderImpl, GoogleAnalytics, GAServiceManager, 
//            ParameterLoader, Log, Logger, ServiceManager, 
//            ExceptionReporter, GAUsage, Clock, TrackerHandler

public class EasyTracker extends Tracker
{
    private class NotInForegroundTimerTask extends TimerTask
    {

        public void run()
        {
            mIsInForeground = false;
        }

        final EasyTracker this$0;

        private NotInForegroundTimerTask()
        {
            this$0 = EasyTracker.this;
            super();
        }

    }


    private EasyTracker(Context context)
    {
        this(context, ((ParameterLoader) (new ParameterLoaderImpl(context))), GoogleAnalytics.getInstance(context), ((ServiceManager) (GAServiceManager.getInstance())), null);
    }

    private EasyTracker(Context context, ParameterLoader parameterloader, GoogleAnalytics googleanalytics, ServiceManager servicemanager, TrackerHandler trackerhandler)
    {
        if(trackerhandler == null)
            trackerhandler = googleanalytics;
        super("easy_tracker", null, trackerhandler);
        mIsAutoActivityTracking = false;
        mActivitiesActive = 0;
        mActivityNameMap = new HashMap();
        mIsInForeground = false;
        mStartSessionOnNextSend = false;
        if(sResourcePackageName != null)
            parameterloader.setResourcePackageName(sResourcePackageName);
        mGoogleAnalytics = googleanalytics;
        setContext(context, parameterloader, servicemanager);
    }

    private void clearExistingTimer()
    {
        this;
        JVM INSTR monitorenter ;
        if(mTimer != null)
        {
            mTimer.cancel();
            mTimer = null;
        }
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    private String getActivityName(Activity activity)
    {
        String s = activity.getClass().getCanonicalName();
        if(mActivityNameMap.containsKey(s))
            return (String)mActivityNameMap.get(s);
        String s1 = mParameterFetcher.getString(s);
        if(s1 == null)
            s1 = s;
        mActivityNameMap.put(s, s1);
        return s1;
    }

    public static EasyTracker getInstance(Context context)
    {
        if(sInstance == null)
            sInstance = new EasyTracker(context);
        return sInstance;
    }

    private Logger.LogLevel getLogLevelFromString(String s)
    {
        Logger.LogLevel loglevel;
        try
        {
            loglevel = Logger.LogLevel.valueOf(s.toUpperCase());
        }
        catch(IllegalArgumentException illegalargumentexception)
        {
            return null;
        }
        return loglevel;
    }

    static EasyTracker getNewInstance(Context context, ParameterLoader parameterloader, GoogleAnalytics googleanalytics, ServiceManager servicemanager, TrackerHandler trackerhandler)
    {
        sInstance = new EasyTracker(context, parameterloader, googleanalytics, servicemanager, trackerhandler);
        return sInstance;
    }

    private void loadParameters()
    {
        Log.v("Starting EasyTracker.");
        String s = mParameterFetcher.getString("ga_trackingId");
        if(TextUtils.isEmpty(s))
            s = mParameterFetcher.getString("ga_api_key");
        set("&tid", s);
        Log.v((new StringBuilder()).append("[EasyTracker] trackingId loaded: ").append(s).toString());
        String s1 = mParameterFetcher.getString("ga_appName");
        if(!TextUtils.isEmpty(s1))
        {
            Log.v((new StringBuilder()).append("[EasyTracker] app name loaded: ").append(s1).toString());
            set("&an", s1);
        }
        String s2 = mParameterFetcher.getString("ga_appVersion");
        if(s2 != null)
        {
            Log.v((new StringBuilder()).append("[EasyTracker] app version loaded: ").append(s2).toString());
            set("&av", s2);
        }
        String s3 = mParameterFetcher.getString("ga_logLevel");
        if(s3 != null)
        {
            Logger.LogLevel loglevel = getLogLevelFromString(s3);
            if(loglevel != null)
            {
                Log.v((new StringBuilder()).append("[EasyTracker] log level loaded: ").append(loglevel).toString());
                mGoogleAnalytics.getLogger().setLogLevel(loglevel);
            }
        }
        Double double1 = mParameterFetcher.getDoubleFromString("ga_sampleFrequency");
        if(double1 == null)
            double1 = new Double(mParameterFetcher.getInt("ga_sampleRate", 100));
        if(double1.doubleValue() != 100D)
            set("&sf", Double.toString(double1.doubleValue()));
        Log.v((new StringBuilder()).append("[EasyTracker] sample rate loaded: ").append(double1).toString());
        int i = mParameterFetcher.getInt("ga_dispatchPeriod", 1800);
        Log.v((new StringBuilder()).append("[EasyTracker] dispatch period loaded: ").append(i).toString());
        mServiceManager.setLocalDispatchPeriod(i);
        mSessionTimeout = 1000 * mParameterFetcher.getInt("ga_sessionTimeout", 30);
        Log.v((new StringBuilder()).append("[EasyTracker] session timeout loaded: ").append(mSessionTimeout).toString());
        boolean flag;
        boolean flag1;
        boolean flag2;
        if(mParameterFetcher.getBoolean("ga_autoActivityTracking") || mParameterFetcher.getBoolean("ga_auto_activity_tracking"))
            flag = true;
        else
            flag = false;
        mIsAutoActivityTracking = flag;
        Log.v((new StringBuilder()).append("[EasyTracker] auto activity tracking loaded: ").append(mIsAutoActivityTracking).toString());
        flag1 = mParameterFetcher.getBoolean("ga_anonymizeIp");
        if(flag1)
        {
            set("&aip", "1");
            Log.v((new StringBuilder()).append("[EasyTracker] anonymize ip loaded: ").append(flag1).toString());
        }
        mIsReportUncaughtExceptionsEnabled = mParameterFetcher.getBoolean("ga_reportUncaughtExceptions");
        if(mIsReportUncaughtExceptionsEnabled)
        {
            Thread.setDefaultUncaughtExceptionHandler(new ExceptionReporter(this, mServiceManager, Thread.getDefaultUncaughtExceptionHandler(), mContext));
            Log.v((new StringBuilder()).append("[EasyTracker] report uncaught exceptions loaded: ").append(mIsReportUncaughtExceptionsEnabled).toString());
        }
        flag2 = mParameterFetcher.getBoolean("ga_dryRun");
        mGoogleAnalytics.setDryRun(flag2);
    }

    private void setContext(Context context, ParameterLoader parameterloader, ServiceManager servicemanager)
    {
        if(context == null)
            Log.e("Context cannot be null");
        mContext = context.getApplicationContext();
        mServiceManager = servicemanager;
        mParameterFetcher = parameterloader;
        loadParameters();
    }

    public static void setResourcePackageName(String s)
    {
        sResourcePackageName = s;
    }

    public void activityStart(Activity activity)
    {
        GAUsage.getInstance().setUsage(GAUsage.Field.EASY_TRACKER_ACTIVITY_START);
        clearExistingTimer();
        if(!mIsInForeground && mActivitiesActive == 0 && checkForNewSession())
            mStartSessionOnNextSend = true;
        mIsInForeground = true;
        mActivitiesActive = 1 + mActivitiesActive;
        if(mIsAutoActivityTracking)
        {
            HashMap hashmap = new HashMap();
            hashmap.put("&t", "appview");
            GAUsage.getInstance().setDisableUsage(true);
            set("&cd", getActivityName(activity));
            send(hashmap);
            GAUsage.getInstance().setDisableUsage(false);
        }
    }

    public void activityStop(Activity activity)
    {
        GAUsage.getInstance().setUsage(GAUsage.Field.EASY_TRACKER_ACTIVITY_STOP);
        mActivitiesActive = -1 + mActivitiesActive;
        mActivitiesActive = Math.max(0, mActivitiesActive);
        mLastOnStopTime = mClock.currentTimeMillis();
        if(mActivitiesActive == 0)
        {
            clearExistingTimer();
            mTimerTask = new NotInForegroundTimerTask();
            mTimer = new Timer("waitForActivityStart");
            mTimer.schedule(mTimerTask, 1000L);
        }
    }

    boolean checkForNewSession()
    {
        return mSessionTimeout == 0L || mSessionTimeout > 0L && mClock.currentTimeMillis() > mLastOnStopTime + mSessionTimeout;
    }

    public void dispatchLocalHits()
    {
        mServiceManager.dispatchLocalHits();
    }

    int getActivitiesActive()
    {
        return mActivitiesActive;
    }

    void overrideUncaughtExceptionHandler(Thread.UncaughtExceptionHandler uncaughtexceptionhandler)
    {
        if(mIsReportUncaughtExceptionsEnabled)
            Thread.setDefaultUncaughtExceptionHandler(uncaughtexceptionhandler);
    }

    public void send(Map map)
    {
        if(mStartSessionOnNextSend)
        {
            map.put("&sc", "start");
            mStartSessionOnNextSend = false;
        }
        super.send(map);
    }

    void setClock(Clock clock)
    {
        mClock = clock;
    }

    private static final int DEFAULT_SAMPLE_RATE = 100;
    private static final String EASY_TRACKER_NAME = "easy_tracker";
    static final int NUM_MILLISECONDS_TO_WAIT_FOR_OPEN_ACTIVITY = 1000;
    private static EasyTracker sInstance;
    private static String sResourcePackageName;
    private int mActivitiesActive;
    private final Map mActivityNameMap;
    private Clock mClock = new Clock() {

        public long currentTimeMillis()
        {
            return System.currentTimeMillis();
        }

        final EasyTracker this$0;

            
            {
                this$0 = EasyTracker.this;
                super();
            }
    }
;
    private Context mContext;
    private final GoogleAnalytics mGoogleAnalytics;
    private boolean mIsAutoActivityTracking;
    private boolean mIsInForeground;
    private boolean mIsReportUncaughtExceptionsEnabled;
    private long mLastOnStopTime;
    private ParameterLoader mParameterFetcher;
    private ServiceManager mServiceManager;
    private long mSessionTimeout;
    private boolean mStartSessionOnNextSend;
    private Timer mTimer;
    private TimerTask mTimerTask;


/*
    static boolean access$102(EasyTracker easytracker, boolean flag)
    {
        easytracker.mIsInForeground = flag;
        return flag;
    }

*/
}
