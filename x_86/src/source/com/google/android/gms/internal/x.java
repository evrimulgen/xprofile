// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.android.gms.internal;

import com.google.android.gms.ads.AdListener;

public final class x extends af.a
{

    public x(AdListener adlistener)
    {
        lc = adlistener;
    }

    public void onAdClosed()
    {
        lc.onAdClosed();
    }

    public void onAdFailedToLoad(int i)
    {
        lc.onAdFailedToLoad(i);
    }

    public void onAdLeftApplication()
    {
        lc.onAdLeftApplication();
    }

    public void onAdLoaded()
    {
        lc.onAdLoaded();
    }

    public void onAdOpened()
    {
        lc.onAdOpened();
    }

    private final AdListener lc;
}
