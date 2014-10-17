// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.roadtrippers.widget;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.*;

public class BeastViewPager extends ViewPager
{

    public BeastViewPager(Context context)
    {
        super(context);
        mCurrX = 0.0F;
        mCurrY = 0.0F;
        init();
    }

    public BeastViewPager(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
        mCurrX = 0.0F;
        mCurrY = 0.0F;
        init();
    }

    private void init()
    {
        mTouchSlop = ViewConfiguration.get(getContext()).getScaledTouchSlop();
    }

    public boolean onInterceptTouchEvent(MotionEvent motionevent)
    {
        boolean flag;
        boolean flag1;
        flag = enabled;
        flag1 = false;
        if(!flag) goto _L2; else goto _L1
_L1:
        int i;
        super.onInterceptTouchEvent(motionevent);
        i = motionevent.getAction();
        if(i != 0) goto _L4; else goto _L3
_L3:
        mCurrX = motionevent.getX();
        mCurrY = motionevent.getY();
        mStartX = motionevent.getX();
        mStartY = motionevent.getY();
        getParent().requestDisallowInterceptTouchEvent(true);
_L6:
        flag1 = super.onInterceptTouchEvent(motionevent);
_L2:
        return flag1;
_L4:
        if(Math.abs(motionevent.getY() - mStartY) > Math.abs(motionevent.getX() - mStartX))
            getParent().requestDisallowInterceptTouchEvent(false);
        else
        if(i != 2)
            if(i == 1 || i == 3)
                getParent().requestDisallowInterceptTouchEvent(false);
            else
                getParent().requestDisallowInterceptTouchEvent(true);
        if(true) goto _L6; else goto _L5
_L5:
    }

    public boolean onTouchEvent(MotionEvent motionevent)
    {
        boolean flag;
        boolean flag1;
        flag = enabled;
        flag1 = false;
        if(!flag) goto _L2; else goto _L1
_L1:
        int i = motionevent.getAction();
          goto _L3
_L5:
        flag1 = super.onTouchEvent(motionevent);
_L2:
        return flag1;
_L3:
        if(i == 0 || i != 2) goto _L5; else goto _L4
_L4:
        if(getCurrentItem() == 0 && motionevent.getX() - mCurrX > (float)mTouchSlop)
            getParent().requestDisallowInterceptTouchEvent(false);
        else
        if(getCurrentItem() == -1 + getAdapter().getCount() && mCurrX - motionevent.getX() > (float)mTouchSlop)
            getParent().requestDisallowInterceptTouchEvent(false);
        else
        if(Math.abs(motionevent.getY() - mCurrY) > Math.abs(motionevent.getX() - mCurrX))
            getParent().requestDisallowInterceptTouchEvent(false);
        if(true) goto _L5; else goto _L6
_L6:
    }

    public void setEnabled(boolean flag)
    {
        enabled = flag;
    }

    private boolean enabled;
    private float mCurrX;
    private float mCurrY;
    private float mStartX;
    private float mStartY;
    private int mTouchSlop;
}
