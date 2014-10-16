// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.flurry.android;

import android.content.*;
import android.os.Bundle;
import com.flurry.sdk.*;

public final class InstallReceiver extends BroadcastReceiver
{

    public InstallReceiver()
    {
    }

    public void onReceive(Context context, Intent intent)
    {
        ex.a(4, a, (new StringBuilder()).append("Received an Install nofication of ").append(intent.getAction()).toString());
        String s = intent.getExtras().getString("referrer");
        ex.a(4, a, (new StringBuilder()).append("Received an Install referrer of ").append(s).toString());
        if(s == null || !"com.android.vending.INSTALL_REFERRER".equals(intent.getAction()))
        {
            ex.a(5, a, "referrer is null");
            return;
        }
        if(!s.contains("="))
        {
            ex.a(4, a, (new StringBuilder()).append("referrer is before decoding: ").append(s).toString());
            s = fh.c(s);
            ex.a(4, a, (new StringBuilder()).append("referrer is: ").append(s).toString());
        }
        (new by(context)).a(s);
    }

    static final String a = com/flurry/android/InstallReceiver.getSimpleName();

}
