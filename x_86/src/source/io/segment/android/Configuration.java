// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package io.segment.android;

import android.content.Context;
import android.content.res.Resources;
import android.text.TextUtils;

// Referenced classes of package io.segment.android:
//            Options

public class Configuration
{

    public Configuration()
    {
    }

    private static Boolean getBoolean(Context context, String s)
    {
        int i = context.getResources().getIdentifier(s, "string", context.getPackageName());
        if(i > 0)
            return Boolean.valueOf(Boolean.parseBoolean(context.getResources().getString(i)));
        else
            return null;
    }

    private static Integer getInteger(Context context, String s)
    {
        int i = context.getResources().getIdentifier(s, "integer", context.getPackageName());
        if(i > 0)
            return Integer.valueOf(context.getResources().getInteger(i));
        else
            return null;
    }

    public static Options getOptions(Context context)
    {
        Options options = new Options();
        String s = getString(context, "analytics_host");
        if(!TextUtils.isEmpty(s))
            options.setHost(s);
        Boolean boolean1 = getBoolean(context, "analytics_debug");
        if(boolean1 != null)
            options.setDebug(boolean1.booleanValue());
        Integer integer = getInteger(context, "analytics_flush_at");
        if(integer != null)
            options.setFlushAt(integer.intValue());
        Integer integer1 = getInteger(context, "analytics_flush_after");
        if(integer1 != null)
            options.setFlushAfter(integer1.intValue());
        Integer integer2 = getInteger(context, "analytics_max_queue_size");
        if(integer2 != null)
            options.setMaxQueueSize(integer2.intValue());
        Integer integer3 = getInteger(context, "analytics_settings_cache_expiry");
        if(integer3 != null)
            options.setSettingsCacheExpiry(integer3.intValue());
        Boolean boolean2 = getBoolean(context, "analytics_send_location");
        if(boolean2 == null)
            boolean2 = Boolean.valueOf(true);
        options.setSendLocation(boolean2.booleanValue());
        return options;
    }

    private static String getString(Context context, String s)
    {
        int i = context.getResources().getIdentifier(s, "string", context.getPackageName());
        if(i > 0)
            return context.getResources().getString(i);
        else
            return null;
    }

    public static String getWriteKey(Context context)
    {
        String s = getString(context, "analytics_write_key");
        if(s != null)
            return s;
        else
            return getString(context, "analytics_secret");
    }

    private static final String DEBUG_KEY = "analytics_debug";
    private static final String FLUSH_AFTER_KEY = "analytics_flush_after";
    private static final String FLUSH_AT_KEY = "analytics_flush_at";
    private static final String HOST_KEY = "analytics_host";
    private static final String INTEGER_RESOURCE_KEY = "integer";
    private static final String MAX_QUEUE_SIZE_KEY = "analytics_max_queue_size";
    private static final String SECRET_KEY = "analytics_secret";
    private static final String SETTINGS_CACHE_EXPIRY_KEY = "analytics_settings_cache_expiry";
    private static final String SETTINGS_SEND_LOCATION_KEY = "analytics_send_location";
    private static final String STRING_RESOURCE_KEY = "string";
    private static final String WRITE_KEY = "analytics_write_key";
}
