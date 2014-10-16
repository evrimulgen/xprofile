// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.quantcast.measurement.service;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.UUID;

// Referenced classes of package com.quantcast.measurement.service:
//            QuantcastClient, QCLog

class QCUtility
{

    QCUtility()
    {
    }

    protected static String addScheme(String s)
    {
        StringBuilder stringbuilder = new StringBuilder();
        String s1;
        if(QuantcastClient.isUsingSecureConnections())
            s1 = "https://";
        else
            s1 = "http://";
        return stringbuilder.append(s1).append(s).toString();
    }

    static String applyHash(String s)
    {
        double ad[] = new double[HASH_CONSTANTS.length];
        for(int i = 0; i < ad.length; i++)
            ad[i] = applyUserHash(HASH_CONSTANTS[i], s);

        double d = 1.0D;
        int j = ad.length;
        for(int k = 0; k < j; k++)
            d *= ad[k];

        return Long.toHexString(Math.round(Math.abs(d) / 65536D));
    }

    private static long applyUserHash(long l, String s)
    {
        for(int i = 0; i < s.length(); i++)
        {
            int j = (int)l ^ s.charAt(i);
            l = (long)j + ((long)(j << 1) + (long)(j << 4) + (long)(j << 7) + (long)(j << 8) + (long)(j << 24));
        }

        return l;
    }

    protected static void dumpAppInstallID(Context context)
    {
        android.content.SharedPreferences.Editor editor = context.getSharedPreferences("com.quantcast.measurement.service", 0).edit();
        editor.remove("applicationId");
        editor.commit();
    }

    protected static String encodeStringArray(String as[])
    {
        if(as != null && as.length != 0) goto _L2; else goto _L1
_L1:
        String s = null;
_L6:
        return s;
_L2:
        int i;
        int j;
        s = null;
        i = as.length;
        j = 0;
_L4:
        String s1;
        if(j >= i)
            continue; /* Loop/switch isn't completed */
        s1 = as[j];
        if(s1 == null)
            break MISSING_BLOCK_LABEL_95;
        String s2 = URLEncoder.encode(s1, "UTF-8").replaceAll("\\+", "%20");
        if(s == null)
        {
            s = s2;
            break MISSING_BLOCK_LABEL_95;
        }
        String s3 = (new StringBuilder()).append(s).append(",").append(s2).toString();
        s = s3;
        break MISSING_BLOCK_LABEL_95;
        UnsupportedEncodingException unsupportedencodingexception;
        unsupportedencodingexception;
        j++;
        if(true) goto _L4; else goto _L3
_L3:
        if(true) goto _L6; else goto _L5
_L5:
    }

    private static String generateAndSaveAppInstallId(SharedPreferences sharedpreferences)
    {
        Object obj = APPLICATION_ID_LOCK;
        obj;
        JVM INSTR monitorenter ;
        String s = sharedpreferences.getString("applicationId", null);
        if(s != null)
            break MISSING_BLOCK_LABEL_81;
        s = generateUniqueId();
        QCLog.i(TAG, (new StringBuilder()).append("Saving install id:").append(s).append(".").toString());
        android.content.SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString("applicationId", s);
        editor.commit();
        obj;
        JVM INSTR monitorexit ;
        return s;
        Exception exception;
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
    }

    protected static String generateUniqueId()
    {
        return UUID.randomUUID().toString();
    }

    protected static String getAPIKey(Context context)
    {
        PackageManager packagemanager = context.getPackageManager();
        String s = null;
        if(packagemanager == null)
            break MISSING_BLOCK_LABEL_60;
        ApplicationInfo applicationinfo;
        Bundle bundle;
        String s1;
        try
        {
            applicationinfo = packagemanager.getApplicationInfo(context.getPackageName(), 128);
        }
        catch(android.content.pm.PackageManager.NameNotFoundException namenotfoundexception)
        {
            return null;
        }
        s = null;
        if(applicationinfo == null)
            break MISSING_BLOCK_LABEL_60;
        bundle = applicationinfo.metaData;
        s = null;
        if(bundle == null)
            break MISSING_BLOCK_LABEL_60;
        s1 = applicationinfo.metaData.getString("com.quantcast.apiKey");
        s = s1;
        return s;
    }

    protected static String getAppInstallId(Context context)
    {
        SharedPreferences sharedpreferences = context.getSharedPreferences("com.quantcast.measurement.service", 0);
        String s = sharedpreferences.getString("applicationId", null);
        if(s == null)
            s = generateAndSaveAppInstallId(sharedpreferences);
        return s;
    }

    protected static String getAppName(Context context)
    {
        String s = "app";
        ApplicationInfo applicationinfo = context.getApplicationInfo();
        if(applicationinfo != null)
        {
            int i = applicationinfo.labelRes;
            String s1;
            try
            {
                s1 = context.getString(i);
            }
            catch(android.content.res.Resources.NotFoundException notfoundexception)
            {
                QCLog.i(TAG, (new StringBuilder()).append("AppName: Resource not found for ").append(i).toString());
                return s;
            }
            s = s1;
        }
        return s;
    }

    public static final String API_VERSION = "1_1_0";
    private static final Object APPLICATION_ID_LOCK = new Object();
    private static final long HASH_CONSTANTS[] = {
        0xffffffff811c9dc5L, 0xffffffffc9dc5118L
    };
    private static final String HTTPS_SCHEME = "https://";
    private static final String HTTP_SCHEME = "http://";
    private static final String INSTALL_ID_PREF_NAME = "applicationId";
    private static final String SHARED_PREFERENCES_NAME = "com.quantcast.measurement.service";
    private static final QCLog.Tag TAG = new QCLog.Tag(com/quantcast/measurement/service/QCUtility);

}
