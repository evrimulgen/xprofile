// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.quantcast.measurement.service;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.webkit.WebView;
import java.lang.reflect.Method;

// Referenced classes of package com.quantcast.measurement.service:
//            QCMeasurement, QCLog, QCOptOutUtility, QCDeduplicatedWebView, 
//            AboutQuantcastScreen

public class QuantcastClient
{
    public static interface CollectionEnabledCallback
    {

        public abstract void callback(boolean flag);
    }


    public QuantcastClient()
    {
    }

    public static String activityStart(Context context, String s, String s1, String as[])
    {
        return QCMeasurement.INSTANCE.startUp(context, s, s1, as);
    }

    public static void activityStart(Context context)
    {
        activityStart(context, null, null, null);
    }

    public static void activityStart(Context context, String as[])
    {
        activityStart(context, null, null, as);
    }

    public static void activityStop()
    {
        activityStop(null);
    }

    public static void activityStop(String as[])
    {
        QCMeasurement.INSTANCE.stop(as);
    }

    public static void beginSessionWithApiKey(Activity activity, String s)
    {
        beginSessionWithApiKeyAndWithUserId(activity, s, null);
    }

    public static void beginSessionWithApiKey(Activity activity, String s, String s1)
    {
        beginSessionWithApiKeyAndWithUserId(activity, s, null, s1);
    }

    public static void beginSessionWithApiKey(Activity activity, String s, String as[])
    {
        beginSessionWithApiKeyAndWithUserId(activity, s, null, as);
    }

    public static void beginSessionWithApiKeyAndWithUserId(Activity activity, String s, String s1)
    {
        beginSessionWithApiKeyAndWithUserId(activity, s, s1, new String[0]);
    }

    public static void beginSessionWithApiKeyAndWithUserId(Activity activity, String s, String s1, String s2)
    {
        beginSessionWithApiKeyAndWithUserId(activity, s, s1, new String[] {
            s2
        });
    }

    public static void beginSessionWithApiKeyAndWithUserId(Activity activity, String s, String s1, String as[])
    {
        activityStart(activity, s, s1, as);
    }

    public static void enableLogging(boolean flag)
    {
        if(flag)
        {
            QCLog.setLogLevel(2);
            return;
        } else
        {
            QCLog.setLogLevel(6);
            return;
        }
    }

    public static void endSession(Activity activity)
    {
        endSession(activity, new String[0]);
    }

    public static void endSession(Activity activity, String s)
    {
        endSession(activity, new String[] {
            s
        });
    }

    public static void endSession(Activity activity, String as[])
    {
        activityStop(as);
    }

    public static void isCollectionEnabled(Context context, CollectionEnabledCallback collectionenabledcallback)
    {
        collectionenabledcallback.callback(QCOptOutUtility.isOptedOut(context));
    }

    public static boolean isCollectionEnabled(Context context)
    {
        return QCOptOutUtility.isOptedOut(context);
    }

    public static boolean isUsingSecureConnections()
    {
        return QCMeasurement.INSTANCE.usesSecureConnection();
    }

    public static void logEvent(String s)
    {
        logEvent(s, new String[0]);
    }

    public static void logEvent(String s, String s1)
    {
        logEvent(s, new String[] {
            s1
        });
    }

    public static void logEvent(String s, String as[])
    {
        QCMeasurement.INSTANCE.logEvent(s, as);
    }

    public static void logRefresh()
    {
    }

    public static void logUpdate()
    {
    }

    public static WebView newDeduplicatedWebView(Context context)
    {
        return new QCDeduplicatedWebView(context);
    }

    public static void pauseSession()
    {
        pauseSession(new String[0]);
    }

    public static void pauseSession(String s)
    {
        pauseSession(new String[] {
            s
        });
    }

    public static void pauseSession(String as[])
    {
        activityStop(as);
    }

    public static String recordUserIdentifier(String s)
    {
        return recordUserIdentifier(s, null);
    }

    public static String recordUserIdentifier(String s, String as[])
    {
        return QCMeasurement.INSTANCE.recordUserIdentifier(s, as);
    }

    public static void resumeSession()
    {
        resumeSession(new String[0]);
    }

    public static void resumeSession(String s)
    {
        resumeSession(new String[] {
            s
        });
    }

    public static void resumeSession(String as[])
    {
        activityStart(null, as);
    }

    public static void setEnableLocationGathering(boolean flag)
    {
        QCLog.Tag tag = new QCLog.Tag(com/quantcast/measurement/service/QCMeasurement);
        QCLog.w(tag, "Location is now an optional class.  To enable use QCLocation.setEnableLocationGathering(true) instead of this method.");
        try
        {
            Class class1 = Class.forName("com.quantcast.measurement.service.QCLocation");
            Class aclass[] = new Class[1];
            aclass[0] = Boolean.TYPE;
            Method method = class1.getMethod("setEnableLocationGathering", aclass);
            Object aobj[] = new Object[1];
            aobj[0] = Boolean.valueOf(flag);
            method.invoke(null, aobj);
            return;
        }
        catch(Exception exception)
        {
            QCLog.e(tag, "QCLocation class not found.  It can be found in the optional-src directory.  Please add it to the package.");
        }
    }

    public static void setLogLevel(int i)
    {
        QCLog.setLogLevel(i);
    }

    public static void setUploadEventCount(int i)
    {
        QCMeasurement.INSTANCE.setUploadEventCount(i);
    }

    public static void setUsingSecureConnections(boolean flag)
    {
        QCMeasurement.INSTANCE.setUsesSecureConnection(flag);
    }

    public static void showAboutQuantcastScreen(Activity activity)
    {
        activity.startActivity(new Intent(activity, com/quantcast/measurement/service/AboutQuantcastScreen));
    }
}
