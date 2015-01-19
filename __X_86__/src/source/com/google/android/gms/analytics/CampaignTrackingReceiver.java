// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.android.gms.analytics;

import android.content.*;

// Referenced classes of package com.google.android.gms.analytics:
//            CampaignTrackingService

public final class CampaignTrackingReceiver extends BroadcastReceiver
{

    public CampaignTrackingReceiver()
    {
    }

    public void onReceive(Context context, Intent intent)
    {
        String s = intent.getStringExtra("referrer");
        if(!"com.android.vending.INSTALL_REFERRER".equals(intent.getAction()) || s == null)
        {
            return;
        } else
        {
            Intent intent1 = new Intent(context, com/google/android/gms/analytics/CampaignTrackingService);
            intent1.putExtra("referrer", s);
            context.startService(intent1);
            return;
        }
    }
}
