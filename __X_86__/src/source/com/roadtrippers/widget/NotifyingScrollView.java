// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.roadtrippers.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ScrollView;

public class NotifyingScrollView extends ScrollView
{
    public static interface OnScrollChangedListener
    {

        public abstract void onScrollChanged(ScrollView scrollview, int i, int j, int k, int l);
    }

    public static interface OnTouchInterceptor
    {

        public abstract void onInterceptTouchEvent(MotionEvent motionevent);
    }


    public NotifyingScrollView(Context context)
    {
        super(context);
        mDisableEdgeEffects = true;
    }

    public NotifyingScrollView(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
        mDisableEdgeEffects = true;
    }

    public NotifyingScrollView(Context context, AttributeSet attributeset, int i)
    {
        super(context, attributeset, i);
        mDisableEdgeEffects = true;
    }

    protected float getBottomFadingEdgeStrength()
    {
        if(mDisableEdgeEffects && android.os.Build.VERSION.SDK_INT < 11)
            return 0.0F;
        else
            return super.getBottomFadingEdgeStrength();
    }

    protected float getTopFadingEdgeStrength()
    {
        if(mDisableEdgeEffects && android.os.Build.VERSION.SDK_INT < 11)
            return 0.0F;
        else
            return super.getTopFadingEdgeStrength();
    }

    public boolean onInterceptTouchEvent(MotionEvent motionevent)
    {
        if(onTouchInterceptor != null)
        {
            onTouchInterceptor.onInterceptTouchEvent(motionevent);
            return false;
        } else
        {
            return super.onInterceptTouchEvent(motionevent);
        }
    }

    protected void onScrollChanged(int i, int j, int k, int l)
    {
        super.onScrollChanged(i, j, k, l);
        if(mOnScrollChangedListener != null)
            mOnScrollChangedListener.onScrollChanged(this, i, j, k, l);
    }

    public void setOnScrollChangedListener(OnScrollChangedListener onscrollchangedlistener)
    {
        mOnScrollChangedListener = onscrollchangedlistener;
    }

    public void setOnTouchInterceptor(OnTouchInterceptor ontouchinterceptor)
    {
        onTouchInterceptor = ontouchinterceptor;
    }

    private boolean mDisableEdgeEffects;
    private OnScrollChangedListener mOnScrollChangedListener;
    OnTouchInterceptor onTouchInterceptor;
}
