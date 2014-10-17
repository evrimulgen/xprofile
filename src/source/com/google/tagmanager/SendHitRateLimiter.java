// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.tagmanager;


// Referenced classes of package com.google.tagmanager:
//            RateLimiter, Log

class SendHitRateLimiter
    implements RateLimiter
{

    public SendHitRateLimiter()
    {
        this(60, 2000L);
    }

    public SendHitRateLimiter(int i, long l)
    {
        mTokenLock = new Object();
        mMaxTokens = i;
        mTokens = mMaxTokens;
        mMillisecondsPerToken = l;
    }

    void setLastTrackTime(long l)
    {
        mLastTrackTime = l;
    }

    void setTokensAvailable(long l)
    {
        mTokens = l;
    }

    public boolean tokenAvailable()
    {
        Object obj = mTokenLock;
        obj;
        JVM INSTR monitorenter ;
        long l;
        double d;
        l = System.currentTimeMillis();
        if(mTokens >= (double)mMaxTokens)
            break MISSING_BLOCK_LABEL_65;
        d = (double)(l - mLastTrackTime) / (double)mMillisecondsPerToken;
        if(d <= 0.0D)
            break MISSING_BLOCK_LABEL_65;
        mTokens = Math.min(mMaxTokens, d + mTokens);
        mLastTrackTime = l;
        if(mTokens < 1.0D)
            break MISSING_BLOCK_LABEL_93;
        mTokens = mTokens - 1.0D;
        obj;
        JVM INSTR monitorexit ;
        return true;
        Log.w("No more tokens available.");
        obj;
        JVM INSTR monitorexit ;
        return false;
        Exception exception;
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
    }

    private long mLastTrackTime;
    private final int mMaxTokens;
    private final long mMillisecondsPerToken;
    private final Object mTokenLock;
    private double mTokens;
}
