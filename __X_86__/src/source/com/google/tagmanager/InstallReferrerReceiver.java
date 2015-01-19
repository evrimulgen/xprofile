// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.tagmanager;

import android.content.*;

// Referenced classes of package com.google.tagmanager:
//            InstallReferrerUtil, InstallReferrerService

public final class InstallReferrerReceiver extends BroadcastReceiver
{

    public InstallReferrerReceiver()
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
            InstallReferrerUtil.cacheInstallReferrer(s);
            Intent intent1 = new Intent(context, com/google/tagmanager/InstallReferrerService);
            intent1.putExtra("referrer", s);
            context.startService(intent1);
            return;
        }
    }

    static final String INSTALL_ACTION = "com.android.vending.INSTALL_REFERRER";
}
