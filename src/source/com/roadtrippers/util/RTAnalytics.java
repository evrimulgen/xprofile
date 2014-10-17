// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.roadtrippers.util;

import android.app.ActivityManager;
import android.content.Context;
import dagger.Lazy;
import io.segment.android.Analytics;
import io.segment.android.models.Props;

public class RTAnalytics
{

    public RTAnalytics()
    {
    }

    public static void logEvent(Context context, int i, int j)
    {
        if(ActivityManager.isUserAMonkey())
        {
            return;
        } else
        {
            Props props = new Props();
            props.put("category", context.getString(i));
            props.put("action", context.getString(j));
            Analytics.track(context.getString(j), props);
            return;
        }
    }

    public static void logEvent(Context context, int i, int j, Props props)
    {
        if(ActivityManager.isUserAMonkey())
        {
            return;
        } else
        {
            props.put("category", context.getString(i));
            props.put("action", context.getString(j));
            Analytics.track(context.getString(j), props);
            return;
        }
    }

    public static void logEvent(Context context, int i, int j, String s)
    {
        if(ActivityManager.isUserAMonkey())
        {
            return;
        } else
        {
            Props props = new Props();
            props.put("category", context.getString(i));
            props.put("action", context.getString(j));
            props.put("label", s);
            Analytics.track(context.getString(j), props);
            return;
        }
    }

    public static void logScreenView(Context context, String s)
    {
        if(ActivityManager.isUserAMonkey())
        {
            return;
        } else
        {
            Analytics.screen(s);
            return;
        }
    }

    static Lazy jackson;
}
