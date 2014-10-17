// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.bugsnag.android;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Environment;
import android.os.StatFs;
import android.os.SystemClock;
import android.util.DisplayMetrics;
import com.bugsnag.Configuration;
import com.bugsnag.Logger;
import com.bugsnag.android.utils.Async;
import com.bugsnag.utils.JSONUtils;
import java.io.File;
import java.util.List;
import java.util.Locale;
import java.util.UUID;
import org.json.JSONObject;

// Referenced classes of package com.bugsnag.android:
//            ActivityStack

final class Diagnostics extends com.bugsnag.Diagnostics
{

    public Diagnostics(Configuration configuration, Context context)
    {
label0:
        {
            super(configuration);
            applicationContext = context;
            if(startTime == null)
                startTime = Long.valueOf(SystemClock.elapsedRealtime());
            packageName = applicationContext.getPackageName();
            String as[] = new String[1];
            as[0] = packageName;
            configuration.setProjectPackages(as);
            configuration.getOsVersion().set(android.os.Build.VERSION.RELEASE);
            configuration.getAppVersion().set(getPackageVersion(packageName));
            configuration.getReleaseStage().set(guessReleaseStage(packageName));
            JSONUtils.safePutOpt(deviceData, "manufacturer", Build.MANUFACTURER);
            JSONUtils.safePutOpt(deviceData, "model", Build.MODEL);
            JSONUtils.safePutOpt(deviceData, "screenDensity", Float.valueOf(applicationContext.getResources().getDisplayMetrics().density));
            JSONUtils.safePutOpt(deviceData, "screenResolution", getResolution());
            JSONUtils.safePutOpt(deviceData, "totalMemory", totalMemoryAvailable());
            JSONUtils.safePutOpt(deviceData, "osName", "android");
            JSONUtils.safePutOpt(deviceData, "apiLevel", Integer.valueOf(android.os.Build.VERSION.SDK_INT));
            JSONObject jsonobject = deviceData;
            String s = Build.TAGS;
            boolean flag;
            boolean flag1;
            if(s != null && s.contains("test-keys"))
                flag = true;
            else
                flag = false;
            if(!flag)
            {
                boolean flag2 = checkSuperUserAPK();
                flag1 = false;
                if(!flag2)
                    break label0;
            }
            flag1 = true;
        }
        JSONUtils.safePutOpt(jsonobject, "jailbroken", Boolean.valueOf(flag1));
        JSONUtils.safePutOpt(deviceData, "locale", Locale.getDefault().toString());
        JSONUtils.safePutOpt(appData, "id", packageName);
        JSONUtils.safePutOpt(appData, "packageName", packageName);
        JSONUtils.safePutOpt(appData, "name", getAppName());
    }

    private static boolean checkSuperUserAPK()
    {
        boolean flag;
        try
        {
            flag = (new File("/system/app/Superuser.apk")).exists();
        }
        catch(Exception exception)
        {
            return false;
        }
        return flag;
    }

    private String getAppName()
    {
        String s;
        try
        {
            s = applicationContext.getPackageManager().getApplicationInfo(packageName, 0).name;
        }
        catch(Exception exception)
        {
            config.logger.warn(exception);
            return null;
        }
        return s;
    }

    private Float getChargeLevel()
    {
        Float float1;
        try
        {
            IntentFilter intentfilter = new IntentFilter("android.intent.action.BATTERY_CHANGED");
            Intent intent = applicationContext.registerReceiver(null, intentfilter);
            int i = intent.getIntExtra("level", -1);
            int j = intent.getIntExtra("scale", -1);
            float1 = Float.valueOf((float)i / (float)j);
        }
        catch(Exception exception)
        {
            config.logger.warn(exception);
            return null;
        }
        return float1;
    }

    private Boolean getCharging()
    {
        int i;
        boolean flag;
        Boolean boolean1;
        try
        {
            IntentFilter intentfilter = new IntentFilter("android.intent.action.BATTERY_CHANGED");
            i = applicationContext.registerReceiver(null, intentfilter).getIntExtra("status", -1);
        }
        catch(Exception exception)
        {
            config.logger.warn(exception);
            return null;
        }
        if(i != 2 && i != 5)
            flag = false;
        else
            flag = true;
        boolean1 = Boolean.valueOf(flag);
        return boolean1;
    }

    private Long getFreeDiskSpace()
    {
        Long long1;
        try
        {
            StatFs statfs = new StatFs(Environment.getExternalStorageDirectory().getPath());
            long l = (long)statfs.getBlockSize() * (long)statfs.getBlockCount();
            StatFs statfs1 = new StatFs(Environment.getDataDirectory().getPath());
            long1 = Long.valueOf(Math.min((long)statfs1.getBlockSize() * (long)statfs1.getBlockCount(), l));
        }
        catch(Exception exception)
        {
            config.logger.warn(exception);
            return null;
        }
        return long1;
    }

    private String getGpsAllowed()
    {
        String s;
        try
        {
            s = android.provider.Settings.Secure.getString(applicationContext.getContentResolver(), "location_providers_allowed");
        }
        catch(Exception exception)
        {
            config.logger.warn(exception);
            return null;
        }
        if(s == null)
            break MISSING_BLOCK_LABEL_29;
        if(s.length() > 0)
            return "allowed";
        return "disallowed";
    }

    private String getNetworkStatus()
    {
        NetworkInfo networkinfo;
        try
        {
            networkinfo = ((ConnectivityManager)applicationContext.getSystemService("connectivity")).getActiveNetworkInfo();
        }
        catch(Exception exception)
        {
            config.logger.warn(exception);
            return null;
        }
        if(networkinfo == null)
            break MISSING_BLOCK_LABEL_53;
        if(!networkinfo.isConnectedOrConnecting())
            break MISSING_BLOCK_LABEL_53;
        if(networkinfo.getType() == 1)
            return "wifi";
        if(networkinfo.getType() == 9)
            return "ethernet";
        else
            return "cellular";
        return "none";
    }

    private String getOrientation()
    {
        applicationContext.getResources().getConfiguration().orientation;
        JVM INSTR tableswitch 1 2: default 52
    //                   1 36
    //                   2 54;
           goto _L1 _L2 _L3
_L2:
        return "portrait";
        Exception exception;
        exception;
        config.logger.warn(exception);
_L1:
        return null;
_L3:
        return "landscape";
    }

    private String getPackageVersion(String s)
    {
        String s1;
        try
        {
            s1 = applicationContext.getPackageManager().getPackageInfo(s, 0).versionName;
        }
        catch(Exception exception)
        {
            config.logger.warn("Could not get package version", exception);
            return null;
        }
        return s1;
    }

    private String getResolution()
    {
        String s;
        try
        {
            DisplayMetrics displaymetrics = applicationContext.getResources().getDisplayMetrics();
            Object aobj[] = new Object[2];
            aobj[0] = Integer.valueOf(Math.max(displaymetrics.widthPixels, displaymetrics.heightPixels));
            aobj[1] = Integer.valueOf(Math.min(displaymetrics.widthPixels, displaymetrics.heightPixels));
            s = String.format("%dx%d", aobj);
        }
        catch(Exception exception)
        {
            config.logger.warn(exception);
            return null;
        }
        return s;
    }

    private String getUUID()
    {
        this;
        JVM INSTR monitorenter ;
        if(uuid == null) goto _L2; else goto _L1
_L1:
        String s1 = uuid;
_L3:
        this;
        JVM INSTR monitorexit ;
        return s1;
_L2:
        final SharedPreferences settings;
        String s;
        settings = applicationContext.getSharedPreferences("Bugsnag", 0);
        s = settings.getString("userId", null);
        uuid = s;
        if(s != null)
            break MISSING_BLOCK_LABEL_76;
        final String finalUuid = UUID.randomUUID().toString();
        uuid = finalUuid;
        Async.safeAsync(new Runnable() {

            public final void run()
            {
                android.content.SharedPreferences.Editor editor = settings.edit();
                editor.putString("userId", finalUuid);
                editor.commit();
            }

            private String val$finalUuid;
            private SharedPreferences val$settings;

            
            {
                settings = sharedpreferences;
                finalUuid = s;
                super();
            }
        }
);
        s1 = uuid;
          goto _L3
        Exception exception;
        exception;
        throw exception;
    }

    private String guessReleaseStage(String s)
    {
        String s1 = "production";
        int i;
        boolean flag;
        try
        {
            i = 2 & applicationContext.getPackageManager().getApplicationInfo(s, 0).flags;
        }
        catch(Exception exception)
        {
            config.logger.warn("Could not guess release stage", exception);
            return s1;
        }
        flag = false;
        if(i != 0)
            flag = true;
        if(flag)
            s1 = "development";
        return s1;
    }

    private Boolean lowMemoryState()
    {
        Boolean boolean1;
        try
        {
            ActivityManager activitymanager = (ActivityManager)applicationContext.getSystemService("activity");
            android.app.ActivityManager.MemoryInfo memoryinfo = new android.app.ActivityManager.MemoryInfo();
            activitymanager.getMemoryInfo(memoryinfo);
            boolean1 = Boolean.valueOf(memoryinfo.lowMemory);
        }
        catch(Exception exception)
        {
            config.logger.warn(exception);
            return null;
        }
        return boolean1;
    }

    private Long memoryUsedByApp()
    {
        Long long1;
        try
        {
            long1 = Long.valueOf(Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory());
        }
        catch(Exception exception)
        {
            config.logger.warn(exception);
            return null;
        }
        return long1;
    }

    private Long totalFreeMemory()
    {
        Long long1;
        try
        {
            long1 = Long.valueOf(totalMemoryAvailable().longValue() - memoryUsedByApp().longValue());
        }
        catch(Exception exception)
        {
            config.logger.warn(exception);
            return null;
        }
        return long1;
    }

    private Long totalMemoryAvailable()
    {
        Long long1;
        try
        {
            if(Runtime.getRuntime().maxMemory() != 0x7fffffffffffffffL)
                return Long.valueOf(Runtime.getRuntime().maxMemory());
            long1 = Long.valueOf(Runtime.getRuntime().totalMemory());
        }
        catch(Exception exception)
        {
            config.logger.warn(exception);
            return null;
        }
        return long1;
    }

    public final JSONObject getAppState()
    {
        JSONObject jsonobject = super.getAppState();
        JSONUtils.safePutOpt(jsonobject, "duration", Long.valueOf(SystemClock.elapsedRealtime() - startTime.longValue()));
        JSONUtils.safePutOpt(jsonobject, "durationInForeground", ActivityStack.sessionLength());
        JSONUtils.safePutOpt(jsonobject, "lowMemory", lowMemoryState());
        JSONUtils.safePutOpt(jsonobject, "inForeground", Boolean.valueOf(ActivityStack.inForeground()));
        List list = ActivityStack.getNames();
        if(list.size() > 0)
            JSONUtils.safePutOpt(jsonobject, "screenStack", list);
        JSONUtils.safePutOpt(jsonobject, "activeScreen", ActivityStack.getTopActivityName());
        JSONUtils.safePutOpt(jsonobject, "memoryUsage", memoryUsedByApp());
        return jsonobject;
    }

    public final String getContext()
    {
        return (String)config.getContext().get(ActivityStack.getTopActivityName());
    }

    public final JSONObject getDeviceData()
    {
        super.getDeviceData();
        JSONUtils.safePutOpt(deviceData, "id", getUUID());
        return deviceData;
    }

    public final JSONObject getDeviceState()
    {
        JSONObject jsonobject = super.getDeviceState();
        JSONUtils.safePutOpt(jsonobject, "freeMemory", totalFreeMemory());
        JSONUtils.safePutOpt(jsonobject, "orientation", getOrientation());
        JSONUtils.safePutOpt(jsonobject, "batteryLevel", getChargeLevel());
        JSONUtils.safePutOpt(jsonobject, "freeDisk", getFreeDiskSpace());
        JSONUtils.safePutOpt(jsonobject, "charging", getCharging());
        JSONUtils.safePutOpt(jsonobject, "locationStatus", getGpsAllowed());
        JSONUtils.safePutOpt(jsonobject, "networkAccess", getNetworkStatus());
        return jsonobject;
    }

    public final JSONObject getUser()
    {
        JSONObject jsonobject = super.getUser();
        if(jsonobject.optString("id").equals(""))
            JSONUtils.safePut(jsonobject, "id", getUUID());
        return jsonobject;
    }

    private static Long startTime;
    private static String uuid;
    private Context applicationContext;
    private String packageName;
}
