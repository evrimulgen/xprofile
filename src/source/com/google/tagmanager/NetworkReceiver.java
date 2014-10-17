// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.tagmanager;

import android.content.*;
import android.os.Bundle;

// Referenced classes of package com.google.tagmanager:
//            ServiceManager

class NetworkReceiver extends BroadcastReceiver
{

    NetworkReceiver(ServiceManager servicemanager)
    {
        mManager = servicemanager;
    }

    public static void sendRadioPoweredBroadcast(Context context)
    {
        Intent intent = new Intent("com.google.analytics.RADIO_POWERED");
        intent.addCategory(context.getPackageName());
        intent.putExtra(SELF_IDENTIFYING_EXTRA, true);
        context.sendBroadcast(intent);
    }

    public void onReceive(Context context, Intent intent)
    {
        String s = intent.getAction();
        if("android.net.conn.CONNECTIVITY_CHANGE".equals(s))
        {
            Bundle bundle = intent.getExtras();
            Boolean boolean1 = Boolean.FALSE;
            if(bundle != null)
                boolean1 = Boolean.valueOf(intent.getExtras().getBoolean("noConnectivity"));
            ServiceManager servicemanager = mManager;
            boolean flag;
            if(!boolean1.booleanValue())
                flag = true;
            else
                flag = false;
            servicemanager.updateConnectivityStatus(flag);
        } else
        if("com.google.analytics.RADIO_POWERED".equals(s) && !intent.hasExtra(SELF_IDENTIFYING_EXTRA))
        {
            mManager.onRadioPowered();
            return;
        }
    }

    public void register(Context context)
    {
        IntentFilter intentfilter = new IntentFilter();
        intentfilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        context.registerReceiver(this, intentfilter);
        IntentFilter intentfilter1 = new IntentFilter();
        intentfilter1.addAction("com.google.analytics.RADIO_POWERED");
        intentfilter1.addCategory(context.getPackageName());
        context.registerReceiver(this, intentfilter1);
    }

    static final String SELF_IDENTIFYING_EXTRA = com/google/tagmanager/NetworkReceiver.getName();
    private final ServiceManager mManager;

}
