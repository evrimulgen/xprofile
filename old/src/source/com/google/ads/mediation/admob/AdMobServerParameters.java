// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.ads.mediation.admob;

import com.google.ads.mediation.MediationServerParameters;

public final class AdMobServerParameters extends MediationServerParameters
{

    public AdMobServerParameters()
    {
        allowHouseAds = null;
        tagForChildDirectedTreatment = -1;
    }

    public String adJson;
    public String adUnitId;
    public String allowHouseAds;
    public int tagForChildDirectedTreatment;
}
