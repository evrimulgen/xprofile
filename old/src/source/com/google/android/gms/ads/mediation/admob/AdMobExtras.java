// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.android.gms.ads.mediation.admob;

import android.os.Bundle;
import com.google.ads.mediation.NetworkExtras;

public final class AdMobExtras
    implements NetworkExtras
{

    public AdMobExtras(Bundle bundle)
    {
        Bundle bundle1;
        if(bundle != null)
            bundle1 = new Bundle(bundle);
        else
            bundle1 = null;
        qs = bundle1;
    }

    public Bundle getExtras()
    {
        return qs;
    }

    private final Bundle qs;
}
