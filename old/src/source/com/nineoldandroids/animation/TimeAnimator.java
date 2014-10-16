// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.nineoldandroids.animation;


// Referenced classes of package com.nineoldandroids.animation:
//            ValueAnimator

public class TimeAnimator extends ValueAnimator
{
    public static interface TimeListener
    {

        public abstract void onTimeUpdate(TimeAnimator timeanimator, long l, long l1);
    }


    public TimeAnimator()
    {
        mPreviousTime = -1L;
    }

    void animateValue(float f)
    {
    }

    boolean animationFrame(long l)
    {
        long l1 = 0L;
        if(mPlayingState == 0)
        {
            mPlayingState = 1;
            long l2;
            if(mSeekTime < l1)
            {
                mStartTime = l;
            } else
            {
                mStartTime = l - mSeekTime;
                mSeekTime = -1L;
            }
        }
        if(mListener != null)
        {
            l2 = l - mStartTime;
            if(mPreviousTime >= l1)
                l1 = l - mPreviousTime;
            mPreviousTime = l;
            mListener.onTimeUpdate(this, l2, l1);
        }
        return false;
    }

    void initAnimation()
    {
    }

    public void setTimeListener(TimeListener timelistener)
    {
        mListener = timelistener;
    }

    private TimeListener mListener;
    private long mPreviousTime;
}
