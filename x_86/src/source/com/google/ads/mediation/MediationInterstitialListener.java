// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.ads.mediation;


// Referenced classes of package com.google.ads.mediation:
//            MediationInterstitialAdapter

public interface MediationInterstitialListener
{

    public abstract void onDismissScreen(MediationInterstitialAdapter mediationinterstitialadapter);

    public abstract void onFailedToReceiveAd(MediationInterstitialAdapter mediationinterstitialadapter, com.google.ads.AdRequest.ErrorCode errorcode);

    public abstract void onLeaveApplication(MediationInterstitialAdapter mediationinterstitialadapter);

    public abstract void onPresentScreen(MediationInterstitialAdapter mediationinterstitialadapter);

    public abstract void onReceivedAd(MediationInterstitialAdapter mediationinterstitialadapter);
}