// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.ads.mediation.customevent;

import android.app.Activity;
import com.google.ads.AdSize;
import com.google.ads.mediation.MediationAdRequest;

// Referenced classes of package com.google.ads.mediation.customevent:
//            CustomEvent, CustomEventBannerListener

public interface CustomEventBanner
    extends CustomEvent
{

    public abstract void requestBannerAd(CustomEventBannerListener customeventbannerlistener, Activity activity, String s, String s1, AdSize adsize, MediationAdRequest mediationadrequest, Object obj);
}