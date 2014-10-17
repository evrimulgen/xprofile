// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.android.gms.internal;

import android.app.Activity;
import android.os.RemoteException;
import com.google.ads.mediation.*;
import com.google.ads.mediation.admob.AdMobServerParameters;
import com.google.android.gms.dynamic.b;
import com.google.android.gms.dynamic.c;
import com.newrelic.agent.android.instrumentation.JSONObjectInstrumentation;
import java.util.*;
import org.json.JSONObject;

// Referenced classes of package com.google.android.gms.internal:
//            da, bj, z, bk, 
//            ab, bh

public final class bi extends bg.a
{

    public bi(MediationAdapter mediationadapter, NetworkExtras networkextras)
    {
        mR = mediationadapter;
        mS = networkextras;
    }

    private MediationServerParameters a(String s, int i, String s1)
        throws RemoteException
    {
        if(s == null) goto _L2; else goto _L1
_L1:
        HashMap hashmap1;
        HashMap hashmap2;
        try
        {
            JVM INSTR new #28  <Class JSONObject>;
            JSONObject jsonobject = JSONObjectInstrumentation.init(s);
            hashmap2 = new HashMap(jsonobject.length());
            String s2;
            for(Iterator iterator = jsonobject.keys(); iterator.hasNext(); hashmap2.put(s2, jsonobject.getString(s2)))
                s2 = (String)iterator.next();

        }
        catch(Throwable throwable)
        {
            com.google.android.gms.internal.da.b("Could not get MediationServerParameters.", throwable);
            throw new RemoteException();
        }
        hashmap1 = hashmap2;
_L4:
        Class class1 = mR.getServerParametersType();
        MediationServerParameters mediationserverparameters;
        mediationserverparameters = null;
        if(class1 == null)
            break MISSING_BLOCK_LABEL_138;
        MediationServerParameters mediationserverparameters1;
        mediationserverparameters1 = (MediationServerParameters)class1.newInstance();
        mediationserverparameters1.load(hashmap1);
        mediationserverparameters = mediationserverparameters1;
        if(!(mediationserverparameters instanceof AdMobServerParameters))
            break; /* Loop/switch isn't completed */
        AdMobServerParameters admobserverparameters = (AdMobServerParameters)mediationserverparameters;
        admobserverparameters.adJson = s1;
        admobserverparameters.tagForChildDirectedTreatment = i;
        return mediationserverparameters;
_L2:
        HashMap hashmap = new HashMap(0);
        hashmap1 = hashmap;
        if(true) goto _L4; else goto _L3
_L3:
        return mediationserverparameters;
    }

    public void a(b b, ab ab, z z1, String s, bh bh)
        throws RemoteException
    {
        a(b, ab, z1, s, null, bh);
    }

    public void a(b b, ab ab, z z1, String s, String s1, bh bh)
        throws RemoteException
    {
        if(!(mR instanceof MediationBannerAdapter))
        {
            da.w((new StringBuilder()).append("MediationAdapter is not a MediationBannerAdapter: ").append(mR.getClass().getCanonicalName()).toString());
            throw new RemoteException();
        }
        da.s("Requesting banner ad from adapter.");
        try
        {
            ((MediationBannerAdapter)mR).requestBannerAd(new bj(bh), (Activity)c.b(b), a(s, z1.tagForChildDirectedTreatment, s1), com.google.android.gms.internal.bk.b(ab), bk.e(z1), mS);
            return;
        }
        catch(Throwable throwable)
        {
            com.google.android.gms.internal.da.b("Could not request banner ad from adapter.", throwable);
        }
        throw new RemoteException();
    }

    public void a(b b, z z1, String s, bh bh)
        throws RemoteException
    {
        a(b, z1, s, null, bh);
    }

    public void a(b b, z z1, String s, String s1, bh bh)
        throws RemoteException
    {
        if(!(mR instanceof MediationInterstitialAdapter))
        {
            da.w((new StringBuilder()).append("MediationAdapter is not a MediationInterstitialAdapter: ").append(mR.getClass().getCanonicalName()).toString());
            throw new RemoteException();
        }
        da.s("Requesting interstitial ad from adapter.");
        try
        {
            ((MediationInterstitialAdapter)mR).requestInterstitialAd(new bj(bh), (Activity)c.b(b), a(s, z1.tagForChildDirectedTreatment, s1), bk.e(z1), mS);
            return;
        }
        catch(Throwable throwable)
        {
            com.google.android.gms.internal.da.b("Could not request interstitial ad from adapter.", throwable);
        }
        throw new RemoteException();
    }

    public void destroy()
        throws RemoteException
    {
        try
        {
            mR.destroy();
            return;
        }
        catch(Throwable throwable)
        {
            com.google.android.gms.internal.da.b("Could not destroy adapter.", throwable);
        }
        throw new RemoteException();
    }

    public b getView()
        throws RemoteException
    {
        if(!(mR instanceof MediationBannerAdapter))
        {
            da.w((new StringBuilder()).append("MediationAdapter is not a MediationBannerAdapter: ").append(mR.getClass().getCanonicalName()).toString());
            throw new RemoteException();
        }
        b b;
        try
        {
            b = c.h(((MediationBannerAdapter)mR).getBannerView());
        }
        catch(Throwable throwable)
        {
            com.google.android.gms.internal.da.b("Could not get banner view from adapter.", throwable);
            throw new RemoteException();
        }
        return b;
    }

    public void showInterstitial()
        throws RemoteException
    {
        if(!(mR instanceof MediationInterstitialAdapter))
        {
            da.w((new StringBuilder()).append("MediationAdapter is not a MediationInterstitialAdapter: ").append(mR.getClass().getCanonicalName()).toString());
            throw new RemoteException();
        }
        da.s("Showing interstitial from adapter.");
        try
        {
            ((MediationInterstitialAdapter)mR).showInterstitial();
            return;
        }
        catch(Throwable throwable)
        {
            com.google.android.gms.internal.da.b("Could not show interstitial from adapter.", throwable);
        }
        throw new RemoteException();
    }

    private final MediationAdapter mR;
    private final NetworkExtras mS;
}
