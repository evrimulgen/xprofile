// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.quantcast.measurement.service;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

class QCReachability
{

    QCReachability()
    {
    }

    static boolean isConnected(Context context)
    {
label0:
        {
            ConnectivityManager connectivitymanager = (ConnectivityManager)context.getSystemService("connectivity");
            boolean flag = false;
            if(connectivitymanager != null)
            {
                NetworkInfo networkinfo = connectivitymanager.getActiveNetworkInfo();
                if(networkinfo == null || !networkinfo.isConnected())
                    break label0;
                flag = true;
            }
            return flag;
        }
        return false;
    }

    static String networkType(Context context)
    {
        String s;
        ConnectivityManager connectivitymanager;
        s = "unknown";
        connectivitymanager = (ConnectivityManager)context.getSystemService("connectivity");
        if(connectivitymanager == null) goto _L2; else goto _L1
_L1:
        NetworkInfo networkinfo;
        networkinfo = connectivitymanager.getActiveNetworkInfo();
        if(networkinfo == null || !networkinfo.isConnected())
            break MISSING_BLOCK_LABEL_65;
        if(networkinfo.getType() != 0) goto _L4; else goto _L3
_L3:
        s = networkinfo.getSubtypeName();
        if(s == null)
            s = "wwan";
_L2:
        return s;
_L4:
        if(networkinfo.getType() != 1) goto _L2; else goto _L5
_L5:
        return "wifi";
        return "disconnected";
    }
}
