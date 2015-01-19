// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.newrelic.agent.android.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.telephony.TelephonyManager;
import com.newrelic.agent.android.logging.AgentLog;
import com.newrelic.agent.android.logging.AgentLogManager;
import java.text.MessageFormat;

public final class Carrier
{

    public Carrier()
    {
    }

    public static String nameFromContext(Context context)
    {
        ConnectivityManager connectivitymanager = (ConnectivityManager)context.getSystemService("connectivity");
        NetworkInfo networkinfo;
        try
        {
            networkinfo = connectivitymanager.getActiveNetworkInfo();
        }
        catch(SecurityException securityexception)
        {
            log.warning("Cannot determine network state. Enable android.permission.ACCESS_NETWORK_STATE in your manifest.");
            return "unknown";
        }
        if(networkinfo == null || !networkinfo.isConnected())
            return "none";
        switch(networkinfo.getType())
        {
        case 8: // '\b'
        default:
            AgentLog agentlog = log;
            Object aobj[] = new Object[2];
            aobj[0] = networkinfo.getTypeName();
            aobj[1] = Integer.valueOf(networkinfo.getType());
            agentlog.warning(MessageFormat.format("Unknown network type: {0} [{1}]", aobj));
            return "unknown";

        case 0: // '\0'
        case 2: // '\002'
        case 3: // '\003'
        case 4: // '\004'
        case 5: // '\005'
            return nameFromTelephonyManager(context);

        case 1: // '\001'
        case 6: // '\006'
        case 7: // '\007'
        case 9: // '\t'
            return "wifi";
        }
    }

    private static String nameFromTelephonyManager(Context context)
    {
        String s = ((TelephonyManager)context.getSystemService("phone")).getNetworkOperatorName();
        boolean flag;
        if(Build.PRODUCT.equals("google_sdk") || Build.PRODUCT.equals("sdk") || Build.PRODUCT.equals("sdk_x86") || Build.FINGERPRINT.startsWith("generic"))
            flag = true;
        else
            flag = false;
        if(s.equals("Android") && flag)
            s = "wifi";
        return s;
    }

    private static final String ANDROID = "Android";
    private static AgentLog log = AgentLogManager.getAgentLog();

}
