// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.analytics.tracking.android;

import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;

// Referenced classes of package com.google.analytics.tracking.android:
//            DefaultProvider

class ScreenResolutionDefaultProvider
    implements DefaultProvider
{

    protected ScreenResolutionDefaultProvider(Context context)
    {
        mContext = context;
    }

    static void dropInstance()
    {
        synchronized(sInstanceLock)
        {
            sInstance = null;
        }
        return;
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public static ScreenResolutionDefaultProvider getProvider()
    {
        ScreenResolutionDefaultProvider screenresolutiondefaultprovider;
        synchronized(sInstanceLock)
        {
            screenresolutiondefaultprovider = sInstance;
        }
        return screenresolutiondefaultprovider;
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public static void initializeProvider(Context context)
    {
        synchronized(sInstanceLock)
        {
            if(sInstance == null)
                sInstance = new ScreenResolutionDefaultProvider(context);
        }
        return;
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
    }

    protected String getScreenResolutionString()
    {
        DisplayMetrics displaymetrics = mContext.getResources().getDisplayMetrics();
        return (new StringBuilder()).append(displaymetrics.widthPixels).append("x").append(displaymetrics.heightPixels).toString();
    }

    public String getValue(String s)
    {
        while(s == null || !s.equals("&sr")) 
            return null;
        return getScreenResolutionString();
    }

    public boolean providesField(String s)
    {
        return "&sr".equals(s);
    }

    private static ScreenResolutionDefaultProvider sInstance;
    private static Object sInstanceLock = new Object();
    private final Context mContext;

}
