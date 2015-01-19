// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.mixpanel.android.mpmetrics;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

class SystemInformation
{

    public SystemInformation(Context context)
    {
        PackageManager packagemanager;
        String s;
        mContext = context;
        packagemanager = mContext.getPackageManager();
        s = null;
        Integer integer1;
        PackageInfo packageinfo = packagemanager.getPackageInfo(mContext.getPackageName(), 0);
        s = packageinfo.versionName;
        integer1 = Integer.valueOf(packageinfo.versionCode);
        Integer integer = integer1;
_L1:
        Class class1;
        mAppVersionName = s;
        mAppVersionCode = integer;
        class1 = packagemanager.getClass();
        Method method1 = class1.getMethod("hasSystemFeature", new Class[] {
            java/lang/String
        });
        Method method = method1;
_L2:
        Boolean boolean1 = null;
        Boolean boolean2 = null;
        android.content.pm.PackageManager.NameNotFoundException namenotfoundexception;
        if(method != null)
            try
            {
                boolean1 = (Boolean)method.invoke(packagemanager, new Object[] {
                    "android.hardware.nfc"
                });
                boolean2 = (Boolean)method.invoke(packagemanager, new Object[] {
                    "android.hardware.telephony"
                });
            }
            catch(InvocationTargetException invocationtargetexception)
            {
                Log.w("MixpanelAPI", "System version appeared to support PackageManager.hasSystemFeature, but we were unable to call it.");
                boolean2 = null;
            }
            catch(IllegalAccessException illegalaccessexception)
            {
                Log.w("MixpanelAPI", "System version appeared to support PackageManager.hasSystemFeature, but we were unable to call it.");
                boolean2 = null;
            }
        mHasNFC = boolean1;
        mHasTelephony = boolean2;
        mDisplayMetrics = new DisplayMetrics();
        ((WindowManager)mContext.getSystemService("window")).getDefaultDisplay().getMetrics(mDisplayMetrics);
        return;
        namenotfoundexception;
        Log.w("MixpanelAPI", "System information constructed with a context that apparently doesn't exist.");
        integer = null;
          goto _L1
        NoSuchMethodException nosuchmethodexception;
        nosuchmethodexception;
        method = null;
          goto _L2
    }

    public Integer getAppVersionCode()
    {
        return mAppVersionCode;
    }

    public String getAppVersionName()
    {
        return mAppVersionName;
    }

    public String getCurrentNetworkOperator()
    {
        TelephonyManager telephonymanager = (TelephonyManager)mContext.getSystemService("phone");
        String s = null;
        if(telephonymanager != null)
            s = telephonymanager.getNetworkOperatorName();
        return s;
    }

    public DisplayMetrics getDisplayMetrics()
    {
        return mDisplayMetrics;
    }

    public String getPhoneRadioType()
    {
        TelephonyManager telephonymanager = (TelephonyManager)mContext.getSystemService("phone");
        if(telephonymanager == null) goto _L2; else goto _L1
_L1:
        telephonymanager.getPhoneType();
        JVM INSTR tableswitch 0 3: default 52
    //                   0 54
    //                   1 57
    //                   2 60
    //                   3 63;
           goto _L2 _L3 _L4 _L5 _L6
_L2:
        return null;
_L3:
        return "none";
_L4:
        return "gsm";
_L5:
        return "cdma";
_L6:
        return "sip";
    }

    public boolean hasNFC()
    {
        return mHasNFC.booleanValue();
    }

    public boolean hasTelephony()
    {
        return mHasTelephony.booleanValue();
    }

    public Boolean isWifiConnected()
    {
        int i = mContext.checkCallingOrSelfPermission("android.permission.ACCESS_NETWORK_STATE");
        Boolean boolean1 = null;
        if(i == 0)
            boolean1 = Boolean.valueOf(((ConnectivityManager)mContext.getSystemService("connectivity")).getNetworkInfo(1).isConnected());
        return boolean1;
    }

    public static final String LOGTAG = "MixpanelAPI";
    private final Integer mAppVersionCode;
    private final String mAppVersionName;
    private final Context mContext;
    private final DisplayMetrics mDisplayMetrics;
    private final Boolean mHasNFC;
    private final Boolean mHasTelephony;
}
