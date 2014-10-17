// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.android.gms.internal;

import android.os.Handler;
import android.os.RemoteException;
import com.google.ads.mediation.*;

// Referenced classes of package com.google.android.gms.internal:
//            da, cz, bh, bk

public final class bj
    implements MediationBannerListener, MediationInterstitialListener
{

    public bj(bh bh1)
    {
        mT = bh1;
    }

    static bh a(bj bj1)
    {
        return bj1.mT;
    }

    public void onClick(MediationBannerAdapter mediationbanneradapter)
    {
        da.s("Adapter called onClick.");
        if(!cz.aX())
        {
            da.w("onClick must be called on the main UI thread.");
            cz.pT.post(new Runnable() {

                public void run()
                {
                    try
                    {
                        bj.a(mU).O();
                        return;
                    }
                    catch(RemoteException remoteexception1)
                    {
                        da.b("Could not call onAdClicked.", remoteexception1);
                    }
                }

                final bj mU;

            
            {
                mU = bj.this;
                super();
            }
            }
);
            return;
        }
        try
        {
            mT.O();
            return;
        }
        catch(RemoteException remoteexception)
        {
            da.b("Could not call onAdClicked.", remoteexception);
        }
    }

    public void onDismissScreen(MediationBannerAdapter mediationbanneradapter)
    {
        da.s("Adapter called onDismissScreen.");
        if(!cz.aX())
        {
            da.w("onDismissScreen must be called on the main UI thread.");
            cz.pT.post(new Runnable() {

                public void run()
                {
                    try
                    {
                        bj.a(mU).onAdClosed();
                        return;
                    }
                    catch(RemoteException remoteexception1)
                    {
                        da.b("Could not call onAdClosed.", remoteexception1);
                    }
                }

                final bj mU;

            
            {
                mU = bj.this;
                super();
            }
            }
);
            return;
        }
        try
        {
            mT.onAdClosed();
            return;
        }
        catch(RemoteException remoteexception)
        {
            da.b("Could not call onAdClosed.", remoteexception);
        }
    }

    public void onDismissScreen(MediationInterstitialAdapter mediationinterstitialadapter)
    {
        da.s("Adapter called onDismissScreen.");
        if(!cz.aX())
        {
            da.w("onDismissScreen must be called on the main UI thread.");
            cz.pT.post(new Runnable() {

                public void run()
                {
                    try
                    {
                        bj.a(mU).onAdClosed();
                        return;
                    }
                    catch(RemoteException remoteexception1)
                    {
                        da.b("Could not call onAdClosed.", remoteexception1);
                    }
                }

                final bj mU;

            
            {
                mU = bj.this;
                super();
            }
            }
);
            return;
        }
        try
        {
            mT.onAdClosed();
            return;
        }
        catch(RemoteException remoteexception)
        {
            da.b("Could not call onAdClosed.", remoteexception);
        }
    }

    public void onFailedToReceiveAd(MediationBannerAdapter mediationbanneradapter, com.google.ads.AdRequest.ErrorCode errorcode)
    {
        da.s((new StringBuilder()).append("Adapter called onFailedToReceiveAd with error. ").append(errorcode).toString());
        if(!cz.aX())
        {
            da.w("onFailedToReceiveAd must be called on the main UI thread.");
            cz.pT.post(new Runnable(errorcode) {

                public void run()
                {
                    try
                    {
                        bj.a(mU).onAdFailedToLoad(bk.a(mV));
                        return;
                    }
                    catch(RemoteException remoteexception1)
                    {
                        da.b("Could not call onAdFailedToLoad.", remoteexception1);
                    }
                }

                final bj mU;
                final com.google.ads.AdRequest.ErrorCode mV;

            
            {
                mU = bj.this;
                mV = errorcode;
                super();
            }
            }
);
            return;
        }
        try
        {
            mT.onAdFailedToLoad(bk.a(errorcode));
            return;
        }
        catch(RemoteException remoteexception)
        {
            da.b("Could not call onAdFailedToLoad.", remoteexception);
        }
    }

    public void onFailedToReceiveAd(MediationInterstitialAdapter mediationinterstitialadapter, com.google.ads.AdRequest.ErrorCode errorcode)
    {
        da.s((new StringBuilder()).append("Adapter called onFailedToReceiveAd with error ").append(errorcode).append(".").toString());
        if(!cz.aX())
        {
            da.w("onFailedToReceiveAd must be called on the main UI thread.");
            cz.pT.post(new Runnable(errorcode) {

                public void run()
                {
                    try
                    {
                        bj.a(mU).onAdFailedToLoad(bk.a(mV));
                        return;
                    }
                    catch(RemoteException remoteexception1)
                    {
                        da.b("Could not call onAdFailedToLoad.", remoteexception1);
                    }
                }

                final bj mU;
                final com.google.ads.AdRequest.ErrorCode mV;

            
            {
                mU = bj.this;
                mV = errorcode;
                super();
            }
            }
);
            return;
        }
        try
        {
            mT.onAdFailedToLoad(bk.a(errorcode));
            return;
        }
        catch(RemoteException remoteexception)
        {
            da.b("Could not call onAdFailedToLoad.", remoteexception);
        }
    }

    public void onLeaveApplication(MediationBannerAdapter mediationbanneradapter)
    {
        da.s("Adapter called onLeaveApplication.");
        if(!cz.aX())
        {
            da.w("onLeaveApplication must be called on the main UI thread.");
            cz.pT.post(new Runnable() {

                public void run()
                {
                    try
                    {
                        bj.a(mU).onAdLeftApplication();
                        return;
                    }
                    catch(RemoteException remoteexception1)
                    {
                        da.b("Could not call onAdLeftApplication.", remoteexception1);
                    }
                }

                final bj mU;

            
            {
                mU = bj.this;
                super();
            }
            }
);
            return;
        }
        try
        {
            mT.onAdLeftApplication();
            return;
        }
        catch(RemoteException remoteexception)
        {
            da.b("Could not call onAdLeftApplication.", remoteexception);
        }
    }

    public void onLeaveApplication(MediationInterstitialAdapter mediationinterstitialadapter)
    {
        da.s("Adapter called onLeaveApplication.");
        if(!cz.aX())
        {
            da.w("onLeaveApplication must be called on the main UI thread.");
            cz.pT.post(new Runnable() {

                public void run()
                {
                    try
                    {
                        bj.a(mU).onAdLeftApplication();
                        return;
                    }
                    catch(RemoteException remoteexception1)
                    {
                        da.b("Could not call onAdLeftApplication.", remoteexception1);
                    }
                }

                final bj mU;

            
            {
                mU = bj.this;
                super();
            }
            }
);
            return;
        }
        try
        {
            mT.onAdLeftApplication();
            return;
        }
        catch(RemoteException remoteexception)
        {
            da.b("Could not call onAdLeftApplication.", remoteexception);
        }
    }

    public void onPresentScreen(MediationBannerAdapter mediationbanneradapter)
    {
        da.s("Adapter called onPresentScreen.");
        if(!cz.aX())
        {
            da.w("onPresentScreen must be called on the main UI thread.");
            cz.pT.post(new Runnable() {

                public void run()
                {
                    try
                    {
                        bj.a(mU).onAdOpened();
                        return;
                    }
                    catch(RemoteException remoteexception1)
                    {
                        da.b("Could not call onAdOpened.", remoteexception1);
                    }
                }

                final bj mU;

            
            {
                mU = bj.this;
                super();
            }
            }
);
            return;
        }
        try
        {
            mT.onAdOpened();
            return;
        }
        catch(RemoteException remoteexception)
        {
            da.b("Could not call onAdOpened.", remoteexception);
        }
    }

    public void onPresentScreen(MediationInterstitialAdapter mediationinterstitialadapter)
    {
        da.s("Adapter called onPresentScreen.");
        if(!cz.aX())
        {
            da.w("onPresentScreen must be called on the main UI thread.");
            cz.pT.post(new Runnable() {

                public void run()
                {
                    try
                    {
                        bj.a(mU).onAdOpened();
                        return;
                    }
                    catch(RemoteException remoteexception1)
                    {
                        da.b("Could not call onAdOpened.", remoteexception1);
                    }
                }

                final bj mU;

            
            {
                mU = bj.this;
                super();
            }
            }
);
            return;
        }
        try
        {
            mT.onAdOpened();
            return;
        }
        catch(RemoteException remoteexception)
        {
            da.b("Could not call onAdOpened.", remoteexception);
        }
    }

    public void onReceivedAd(MediationBannerAdapter mediationbanneradapter)
    {
        da.s("Adapter called onReceivedAd.");
        if(!cz.aX())
        {
            da.w("onReceivedAd must be called on the main UI thread.");
            cz.pT.post(new Runnable() {

                public void run()
                {
                    try
                    {
                        bj.a(mU).onAdLoaded();
                        return;
                    }
                    catch(RemoteException remoteexception1)
                    {
                        da.b("Could not call onAdLoaded.", remoteexception1);
                    }
                }

                final bj mU;

            
            {
                mU = bj.this;
                super();
            }
            }
);
            return;
        }
        try
        {
            mT.onAdLoaded();
            return;
        }
        catch(RemoteException remoteexception)
        {
            da.b("Could not call onAdLoaded.", remoteexception);
        }
    }

    public void onReceivedAd(MediationInterstitialAdapter mediationinterstitialadapter)
    {
        da.s("Adapter called onReceivedAd.");
        if(!cz.aX())
        {
            da.w("onReceivedAd must be called on the main UI thread.");
            cz.pT.post(new Runnable() {

                public void run()
                {
                    try
                    {
                        bj.a(mU).onAdLoaded();
                        return;
                    }
                    catch(RemoteException remoteexception1)
                    {
                        da.b("Could not call onAdLoaded.", remoteexception1);
                    }
                }

                final bj mU;

            
            {
                mU = bj.this;
                super();
            }
            }
);
            return;
        }
        try
        {
            mT.onAdLoaded();
            return;
        }
        catch(RemoteException remoteexception)
        {
            da.b("Could not call onAdLoaded.", remoteexception);
        }
    }

    private final bh mT;
}
