// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ly.count.android.api;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Locale;
import org.OpenUDID.OpenUDID_manager;

class DeviceInfo
{

    DeviceInfo()
    {
    }

    public static String appVersion(Context context)
    {
        String s;
        try
        {
            s = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
        }
        catch(android.content.pm.PackageManager.NameNotFoundException namenotfoundexception)
        {
            return "1.0";
        }
        return s;
    }

    public static String getCarrier(Context context)
    {
        return ((TelephonyManager)context.getSystemService("phone")).getNetworkOperatorName();
    }

    public static String getDevice()
    {
        return Build.MODEL;
    }

    public static String getLocale()
    {
        Locale locale = Locale.getDefault();
        return (new StringBuilder(String.valueOf(locale.getLanguage()))).append("_").append(locale.getCountry()).toString();
    }

    public static String getMetrics(Context context)
    {
        String s = (new StringBuilder(String.valueOf((new StringBuilder(String.valueOf((new StringBuilder(String.valueOf((new StringBuilder(String.valueOf((new StringBuilder(String.valueOf((new StringBuilder(String.valueOf((new StringBuilder(String.valueOf((new StringBuilder(String.valueOf("{"))).append("\"_device\":\"").append(getDevice()).append("\"").toString()))).append(",\"_os\":\"").append(getOS()).append("\"").toString()))).append(",\"_os_version\":\"").append(getOSVersion()).append("\"").toString()))).append(",\"_carrier\":\"").append(getCarrier(context)).append("\"").toString()))).append(",\"_resolution\":\"").append(getResolution(context)).append("\"").toString()))).append(",\"_locale\":\"").append(getLocale()).append("\"").toString()))).append(",\"_app_version\":\"").append(appVersion(context)).append("\"").toString()))).append("}").toString();
        String s1;
        try
        {
            s1 = URLEncoder.encode(s, "UTF-8");
        }
        catch(UnsupportedEncodingException unsupportedencodingexception)
        {
            return s;
        }
        return s1;
    }

    public static String getOS()
    {
        return "Android";
    }

    public static String getOSVersion()
    {
        return android.os.Build.VERSION.RELEASE;
    }

    public static String getResolution(Context context)
    {
        Display display = ((WindowManager)context.getSystemService("window")).getDefaultDisplay();
        DisplayMetrics displaymetrics = new DisplayMetrics();
        display.getMetrics(displaymetrics);
        return (new StringBuilder(String.valueOf(displaymetrics.widthPixels))).append("x").append(displaymetrics.heightPixels).toString();
    }

    public static String getUDID()
    {
        if(!OpenUDID_manager.isInitialized())
            return "REPLACE_UDID";
        else
            return OpenUDID_manager.getOpenUDID();
    }
}
