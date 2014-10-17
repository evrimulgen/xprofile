// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.localytics.android;

import android.content.ContentResolver;
import android.content.Context;
import android.content.pm.*;
import android.database.Cursor;
import android.net.*;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;
import java.io.*;
import java.lang.reflect.Field;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

// Referenced classes of package com.localytics.android:
//            Constants, ReflectionUtils

final class DatapointHelper
{

    private DatapointHelper()
    {
        throw new UnsupportedOperationException("This class is non-instantiable");
    }

    public static String getAndroidIdHashOrNull(Context context)
    {
        String s = getAndroidIdOrNull(context);
        if(s == null)
            return null;
        else
            return getSha256_buggy(s);
    }

    public static String getAndroidIdOrNull(Context context)
    {
        File file = new File((new StringBuilder()).append(context.getFilesDir()).append("/localytics/device_id").toString());
        if(!file.exists() || file.length() <= 0L) goto _L2; else goto _L1
_L1:
        BufferedReader bufferedreader = null;
        char ac[];
        BufferedReader bufferedreader1;
        ac = new char[100];
        bufferedreader1 = new BufferedReader(new FileReader(file), 128);
        String s1;
        s1 = String.copyValueOf(ac, 0, bufferedreader1.read(ac));
        bufferedreader1.close();
        if(bufferedreader1 == null)
            break MISSING_BLOCK_LABEL_104;
        bufferedreader1.close();
        return s1;
        FileNotFoundException filenotfoundexception;
        filenotfoundexception;
_L5:
        String s;
        Exception exception;
        if(bufferedreader != null)
            try
            {
                bufferedreader.close();
            }
            catch(IOException ioexception) { }
_L2:
        s = android.provider.Settings.Secure.getString(context.getContentResolver(), "android_id");
        if(s == null || s.toLowerCase().equals("9774d56d682e549c"))
            return null;
        else
            return s;
        exception;
_L3:
        if(bufferedreader == null)
            break MISSING_BLOCK_LABEL_155;
        bufferedreader.close();
        throw exception;
        IOException ioexception1;
        ioexception1;
          goto _L2
        exception;
        bufferedreader = bufferedreader1;
          goto _L3
        FileNotFoundException filenotfoundexception1;
        filenotfoundexception1;
        bufferedreader = bufferedreader1;
        if(true) goto _L5; else goto _L4
_L4:
    }

    static int getApiLevel()
    {
        int j;
        try
        {
            j = Integer.parseInt((String)android/os/Build$VERSION.getField("SDK").get(null));
        }
        catch(Exception exception)
        {
            Log.w("Localytics", "Caught exception", exception);
            int i;
            try
            {
                i = android/os/Build$VERSION.getField("SDK_INT").getInt(null);
            }
            catch(Exception exception1)
            {
                return 3;
            }
            return i;
        }
        return j;
    }

    public static String getAppVersion(Context context)
    {
        PackageManager packagemanager = context.getPackageManager();
        String s;
        try
        {
            s = packagemanager.getPackageInfo(context.getPackageName(), 0).versionName;
        }
        catch(android.content.pm.PackageManager.NameNotFoundException namenotfoundexception)
        {
            throw new RuntimeException(namenotfoundexception);
        }
        if(s == null)
            s = "unknown";
        return s;
    }

    public static String getFBAttribution(Context context)
    {
        ContentResolver contentresolver;
        Uri uri;
        String as[];
        Cursor cursor;
        contentresolver = context.getContentResolver();
        uri = Uri.parse("content://com.facebook.katana.provider.AttributionIdProvider");
        as = (new String[] {
            "aid"
        });
        cursor = null;
        cursor = contentresolver.query(uri, as, null, null, null);
        String s;
        s = null;
        if(cursor == null)
            break MISSING_BLOCK_LABEL_82;
        boolean flag = cursor.moveToFirst();
        s = null;
        if(!flag)
            break MISSING_BLOCK_LABEL_82;
        String s1 = cursor.getString(cursor.getColumnIndex("aid"));
        s = s1;
        if(cursor != null)
            cursor.close();
_L2:
        return s;
        Exception exception1;
        exception1;
        s = null;
        if(cursor == null) goto _L2; else goto _L1
_L1:
        cursor.close();
        return null;
        Exception exception;
        exception;
        if(cursor != null)
            cursor.close();
        throw exception;
    }

    public static String getLocalyticsAppKeyOrNull(Context context)
    {
        Object obj;
        boolean flag;
        String s;
        try
        {
            obj = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128).metaData.get("LOCALYTICS_APP_KEY");
            flag = obj instanceof String;
        }
        catch(android.content.pm.PackageManager.NameNotFoundException namenotfoundexception)
        {
            throw new RuntimeException(namenotfoundexception);
        }
        s = null;
        if(!flag)
            break MISSING_BLOCK_LABEL_41;
        s = (String)obj;
        return s;
    }

    public static String getLocalyticsRollupKeyOrNull(Context context)
    {
        ApplicationInfo applicationinfo;
        Bundle bundle;
        String s;
        String s1;
        boolean flag;
        try
        {
            applicationinfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128);
            bundle = applicationinfo.metaData;
        }
        catch(android.content.pm.PackageManager.NameNotFoundException namenotfoundexception)
        {
            throw new RuntimeException(namenotfoundexception);
        }
        s = null;
        if(bundle == null)
            break MISSING_BLOCK_LABEL_63;
        s1 = (String)applicationinfo.metaData.get("LOCALYTICS_ROLLUP_KEY");
        flag = s1 instanceof String;
        s = null;
        if(!flag)
            break MISSING_BLOCK_LABEL_63;
        s = (String)s1;
        return s;
    }

    public static String getManufacturer()
    {
        String s = "unknown";
        if(Constants.CURRENT_API_LEVEL > 3)
            try
            {
                s = (String)android/os/Build.getField("MANUFACTURER").get(null);
            }
            catch(Exception exception)
            {
                return s;
            }
        return s;
    }

    public static String getNetworkType(Context context, TelephonyManager telephonymanager)
    {
        NetworkInfo networkinfo;
        if(context.getPackageManager().checkPermission("android.permission.ACCESS_WIFI_STATE", context.getPackageName()) != 0)
            break MISSING_BLOCK_LABEL_52;
        networkinfo = ((ConnectivityManager)context.getSystemService("connectivity")).getNetworkInfo(1);
        if(networkinfo == null)
            break MISSING_BLOCK_LABEL_52;
        boolean flag = networkinfo.isConnectedOrConnecting();
        if(flag)
            return "wifi";
        break MISSING_BLOCK_LABEL_52;
        SecurityException securityexception;
        securityexception;
        return (new StringBuilder("android_network_type_")).append(telephonymanager.getNetworkType()).toString();
    }

    public static String getSerialNumberHashOrNull()
    {
        int i = Constants.CURRENT_API_LEVEL;
        String s = null;
        if(i >= 9)
            try
            {
                s = (String)android/os/Build.getField("SERIAL").get(null);
            }
            catch(Exception exception)
            {
                throw new RuntimeException(exception);
            }
        if(s == null)
            return null;
        else
            return getSha256_buggy(s);
    }

    static String getSha256_buggy(String s)
    {
        if(s == null)
            throw new IllegalArgumentException("string cannot be null");
        String s1;
        try
        {
            s1 = (new BigInteger(1, MessageDigest.getInstance("SHA-256").digest(s.getBytes("UTF-8")))).toString(16);
        }
        catch(NoSuchAlgorithmException nosuchalgorithmexception)
        {
            throw new RuntimeException(nosuchalgorithmexception);
        }
        catch(UnsupportedEncodingException unsupportedencodingexception)
        {
            throw new RuntimeException(unsupportedencodingexception);
        }
        return s1;
    }

    public static String getTelephonyDeviceIdOrNull(Context context)
    {
        while(Constants.CURRENT_API_LEVEL >= 7 && !((Boolean)ReflectionUtils.tryInvokeInstance(context.getPackageManager(), "hasSystemFeature", STRING_CLASS_ARRAY, HARDWARE_TELEPHONY)).booleanValue() || context.getPackageManager().checkPermission("android.permission.READ_PHONE_STATE", context.getPackageName()) != 0) 
            return null;
        return ((TelephonyManager)context.getSystemService("phone")).getDeviceId();
    }

    public static String getWifiMacHashOrNull(Context context)
    {
        if(Constants.CURRENT_API_LEVEL < 8 || ((Boolean)ReflectionUtils.tryInvokeInstance(context.getPackageManager(), "hasSystemFeature", STRING_CLASS_ARRAY, HARDWARE_WIFI)).booleanValue())
        {
            int i = context.getPackageManager().checkPermission("android.permission.ACCESS_WIFI_STATE", context.getPackageName());
            String s = null;
            if(i == 0)
            {
                WifiInfo wifiinfo = ((WifiManager)context.getSystemService("wifi")).getConnectionInfo();
                s = null;
                if(wifiinfo != null)
                    s = wifiinfo.getMacAddress();
            }
            if(s != null)
                return getSha256_buggy(s);
        }
        return null;
    }

    private static final Object HARDWARE_TELEPHONY[] = {
        "android.hardware.telephony"
    };
    private static final Object HARDWARE_WIFI[] = {
        "android.hardware.wifi"
    };
    private static final String INVALID_ANDROID_ID = "9774d56d682e549c";
    private static final String LEGACY_DEVICE_ID_FILE = "/localytics/device_id";
    private static final Class STRING_CLASS_ARRAY[] = {
        java/lang/String
    };

}
