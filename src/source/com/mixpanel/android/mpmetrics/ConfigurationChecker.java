// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.mixpanel.android.mpmetrics;

import android.content.Context;
import android.content.Intent;
import android.content.pm.*;
import android.util.Log;
import java.util.*;

class ConfigurationChecker
{

    ConfigurationChecker()
    {
    }

    public static boolean checkPushConfiguration(Context context)
    {
        if(android.os.Build.VERSION.SDK_INT < 8)
        {
            Log.i(LOGTAG, (new StringBuilder()).append("Push not supported in SDK ").append(android.os.Build.VERSION.SDK).toString());
        } else
        {
            PackageManager packagemanager = context.getPackageManager();
            String s = context.getPackageName();
            String s1 = (new StringBuilder()).append(s).append(".permission.C2D_MESSAGE").toString();
            try
            {
                packagemanager.getPermissionInfo(s1, 4096);
            }
            catch(android.content.pm.PackageManager.NameNotFoundException namenotfoundexception)
            {
                Log.w(LOGTAG, (new StringBuilder()).append("Application does not define permission ").append(s1).toString());
                Log.i(LOGTAG, (new StringBuilder()).append("You will need to add the following lines to your application manifest:\n<permission android:name=\"").append(s).append(".permission.C2D_MESSAGE\" android:protectionLevel=\"signature\" />\n").append("<uses-permission android:name=\"").append(s).append(".permission.C2D_MESSAGE\" />").toString());
                return false;
            }
            if(packagemanager.checkPermission("com.google.android.c2dm.permission.RECEIVE", s) != 0)
            {
                Log.w(LOGTAG, "Package does not have permission com.google.android.c2dm.permission.RECEIVE");
                Log.i(LOGTAG, "You can fix this by adding the following to your AndroidManifest.xml file:\n<uses-permission android:name=\"com.google.android.c2dm.permission.RECEIVE\" />");
                return false;
            }
            if(packagemanager.checkPermission("android.permission.INTERNET", s) != 0)
            {
                Log.w(LOGTAG, "Package does not have permission android.permission.INTERNET");
                Log.i(LOGTAG, "You can fix this by adding the following to your AndroidManifest.xml file:\n<uses-permission android:name=\"android.permission.INTERNET\" />");
                return false;
            }
            if(packagemanager.checkPermission("android.permission.GET_ACCOUNTS", s) != 0)
            {
                Log.w(LOGTAG, "Package does not have permission android.permission.GET_ACCOUNTS");
                Log.i(LOGTAG, "You can fix this by adding the following to your AndroidManifest.xml file:\n<uses-permission android:name=\"android.permission.GET_ACCOUNTS\" />");
                return false;
            }
            if(packagemanager.checkPermission("android.permission.WAKE_LOCK", s) != 0)
            {
                Log.w(LOGTAG, "Package does not have permission android.permission.WAKE_LOCK");
                Log.i(LOGTAG, "You can fix this by adding the following to your AndroidManifest.xml file:\n<uses-permission android:name=\"android.permission.WAKE_LOCK\" />");
                return false;
            }
            PackageInfo packageinfo;
            ActivityInfo aactivityinfo[];
            try
            {
                packageinfo = packagemanager.getPackageInfo(s, 2);
            }
            catch(android.content.pm.PackageManager.NameNotFoundException namenotfoundexception1)
            {
                Log.w(LOGTAG, (new StringBuilder()).append("Could not get receivers for package ").append(s).toString());
                return false;
            }
            aactivityinfo = packageinfo.receivers;
            if(aactivityinfo == null || aactivityinfo.length == 0)
            {
                Log.w(LOGTAG, (new StringBuilder()).append("No receiver for package ").append(s).toString());
                Log.i(LOGTAG, (new StringBuilder()).append("You can fix this with the following into your <application> tag:\n").append(sampleConfigurationMessage(s)).toString());
                return false;
            }
            HashSet hashset = new HashSet();
            int i = aactivityinfo.length;
            for(int j = 0; j < i; j++)
            {
                ActivityInfo activityinfo = aactivityinfo[j];
                if("com.google.android.c2dm.permission.SEND".equals(activityinfo.permission))
                    hashset.add(activityinfo.name);
            }

            if(hashset.isEmpty())
            {
                Log.w(LOGTAG, "No receiver allowed to receive com.google.android.c2dm.permission.SEND");
                Log.i(LOGTAG, (new StringBuilder()).append("You can fix by pasting the following into the <application> tag in your AndroidManifest.xml:\n").append(sampleConfigurationMessage(s)).toString());
                return false;
            }
            if(checkReceiver(context, hashset, "com.google.android.c2dm.intent.REGISTRATION") && checkReceiver(context, hashset, "com.google.android.c2dm.intent.RECEIVE"))
                return true;
        }
        return false;
    }

    private static boolean checkReceiver(Context context, Set set, String s)
    {
        PackageManager packagemanager = context.getPackageManager();
        String s1 = context.getPackageName();
        Intent intent = new Intent(s);
        intent.setPackage(s1);
        List list = packagemanager.queryBroadcastReceivers(intent, 32);
        if(list.isEmpty())
        {
            Log.w(LOGTAG, (new StringBuilder()).append("No receivers for action ").append(s).toString());
            Log.i(LOGTAG, (new StringBuilder()).append("You can fix by pasting the following into the <application> tag in your AndroidManifest.xml:\n").append(sampleConfigurationMessage(s1)).toString());
            return false;
        }
        for(Iterator iterator = list.iterator(); iterator.hasNext();)
        {
            String s2 = ((ResolveInfo)iterator.next()).activityInfo.name;
            if(!set.contains(s2))
            {
                Log.w(LOGTAG, (new StringBuilder()).append("Receiver ").append(s2).append(" is not set with permission com.google.android.c2dm.permission.SEND").toString());
                Log.i(LOGTAG, "Please add the attribute 'android:permission=\"com.google.android.c2dm.permission.SEND\"' to your <receiver> tag");
                return false;
            }
        }

        return true;
    }

    public static String sampleConfigurationMessage(String s)
    {
        return (new StringBuilder()).append("<receiver android:name=\"com.mixpanel.android.mpmetrics.GCMReceiver\"\n          android:permission=\"com.google.android.c2dm.permission.SEND\" >\n    <intent-filter>\n       <action android:name=\"com.google.android.c2dm.intent.RECEIVE\" />\n       <action android:name=\"com.google.android.c2dm.intent.REGISTRATION\" />\n       <category android:name=\"").append(s).append("\" />\n").append("    </intent-filter>\n").append("</receiver>").toString();
    }

    public static String LOGTAG = "MixpanelAPI.ConfigurationChecker";

}
