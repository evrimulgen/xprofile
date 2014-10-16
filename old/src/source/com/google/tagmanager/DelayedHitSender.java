// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.tagmanager;

import android.content.Context;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

// Referenced classes of package com.google.tagmanager:
//            HitSender, HitSendingThreadImpl, SendHitRateLimiter, RateLimiter, 
//            Log, HitSendingThread

class DelayedHitSender
    implements HitSender
{

    private DelayedHitSender(Context context)
    {
        this(((HitSendingThread) (HitSendingThreadImpl.getInstance(context))), ((RateLimiter) (new SendHitRateLimiter())));
    }

    DelayedHitSender(HitSendingThread hitsendingthread, RateLimiter ratelimiter)
    {
        mSendingThread = hitsendingthread;
        mRateLimiter = ratelimiter;
    }

    public static HitSender getInstance(Context context)
    {
        DelayedHitSender delayedhitsender;
        synchronized(sInstanceLock)
        {
            if(sInstance == null)
                sInstance = new DelayedHitSender(context);
            delayedhitsender = sInstance;
        }
        return delayedhitsender;
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public boolean sendHit(String s)
    {
        if(!mRateLimiter.tokenAvailable())
        {
            Log.w("Too many urls sent too quickly with the TagManagerSender, rate limiting invoked.");
            return false;
        }
        if(mWrapperUrl != null && mWrapperQueryParameter != null)
            try
            {
                s = (new StringBuilder()).append(mWrapperUrl).append("?").append(mWrapperQueryParameter).append("=").append(URLEncoder.encode(s, "UTF-8")).toString();
                Log.v((new StringBuilder()).append("Sending wrapped url hit: ").append(s).toString());
            }
            catch(UnsupportedEncodingException unsupportedencodingexception)
            {
                Log.w("Error wrapping URL for testing.", unsupportedencodingexception);
                return false;
            }
        mSendingThread.sendHit(s);
        return true;
    }

    public void setUrlWrapModeForTesting(String s, String s1)
    {
        mWrapperUrl = s;
        mWrapperQueryParameter = s1;
    }

    private static DelayedHitSender sInstance;
    private static final Object sInstanceLock = new Object();
    private RateLimiter mRateLimiter;
    private HitSendingThread mSendingThread;
    private String mWrapperQueryParameter;
    private String mWrapperUrl;

}
