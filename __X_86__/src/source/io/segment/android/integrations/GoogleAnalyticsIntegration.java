// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package io.segment.android.integrations;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import com.google.analytics.tracking.android.EasyTracker;
import com.google.analytics.tracking.android.ExceptionReporter;
import com.google.analytics.tracking.android.GAServiceManager;
import com.google.analytics.tracking.android.GoogleAnalytics;
import com.google.analytics.tracking.android.MapBuilder;
import com.google.analytics.tracking.android.Tracker;
import io.segment.android.Logger;
import io.segment.android.errors.InvalidSettingsException;
import io.segment.android.integration.SimpleIntegration;
import io.segment.android.models.EasyJSONObject;
import io.segment.android.models.Props;
import io.segment.android.models.Screen;
import io.segment.android.models.Track;

public class GoogleAnalyticsIntegration extends SimpleIntegration
{
    private static class SettingKey
    {

        private static final String ANONYMIZE_IP_TRACKING = "anonymizeIp";
        private static final String REPORT_UNCAUGHT_EXCEPTIONS = "reportUncaughtExceptions";
        private static final String SAMPLING_FREQUENCY = "sampleFrequency";
        private static final String TRACKING_ID = "mobileTrackingId";
        private static final String USE_HTTPS = "useHttps";

        private SettingKey()
        {
        }
    }


    public GoogleAnalyticsIntegration()
    {
    }

    private void applyOptOut(Activity activity)
    {
        GoogleAnalytics.getInstance(activity).setAppOptOut(optedOut);
    }

    private void enableAutomaticExceptionTracking(Tracker tracker1, Context context)
    {
        Thread.setDefaultUncaughtExceptionHandler(new ExceptionReporter(tracker1, GAServiceManager.getInstance(), Thread.getDefaultUncaughtExceptionHandler(), context));
    }

    private void initialize(Context context)
    {
        EasyJSONObject easyjsonobject = getSettings();
        String s = easyjsonobject.getString("mobileTrackingId");
        Double double1 = easyjsonobject.getDouble("sampleFrequency", Double.valueOf(100D));
        boolean flag = easyjsonobject.getBoolean("anonymizeIp", Boolean.valueOf(false)).booleanValue();
        boolean flag1 = easyjsonobject.getBoolean("reportUncaughtExceptions", Boolean.valueOf(false)).booleanValue();
        boolean flag2 = easyjsonobject.getBoolean("useHttps", Boolean.valueOf(false)).booleanValue();
        GoogleAnalytics googleanalytics = GoogleAnalytics.getInstance(context);
        if(Logger.isLogging())
            googleanalytics.getLogger().setLogLevel(com.google.analytics.tracking.android.Logger.LogLevel.VERBOSE);
        tracker = googleanalytics.getTracker(s);
        tracker.set("&sf", (new StringBuilder()).append(double1).toString());
        tracker.set("&aip", (new StringBuilder()).append(flag).toString());
        tracker.set("useSecure", (new StringBuilder()).append(flag2).toString());
        if(flag1)
            enableAutomaticExceptionTracking(tracker, context);
        googleanalytics.setDefaultTracker(tracker);
        ready();
    }

    public String getKey()
    {
        return "Google Analytics";
    }

    public void onActivityStart(Activity activity)
    {
        applyOptOut(activity);
        EasyTracker.getInstance(activity).activityStart(activity);
    }

    public void onActivityStop(Activity activity)
    {
        applyOptOut(activity);
        EasyTracker.getInstance(activity).activityStop(activity);
    }

    public void onCreate(Context context)
    {
        initialize(context);
    }

    public void screen(Screen screen1)
    {
        String s = screen1.getName();
        tracker.send(MapBuilder.createAppView().set("&cd", s).build());
    }

    public void toggleOptOut(boolean flag)
    {
        optedOut = flag;
    }

    public void track(Track track1)
    {
        Props props = track1.getProperties();
        String s = props.getString("category", "All");
        String s1 = track1.getEvent();
        String s2 = props.getString("label", null);
        Integer integer = props.getInt("value", Integer.valueOf(0));
        tracker.send(MapBuilder.createEvent(s, s1, s2, Long.valueOf(integer.longValue())).build());
    }

    public void validate(EasyJSONObject easyjsonobject)
        throws InvalidSettingsException
    {
        if(TextUtils.isEmpty(easyjsonobject.getString("mobileTrackingId")))
            throw new InvalidSettingsException("mobileTrackingId", "Google Analytics requires the trackingId (UA-XXXXXXXX-XX) setting.");
        else
            return;
    }

    private boolean optedOut;
    private Tracker tracker;
}
