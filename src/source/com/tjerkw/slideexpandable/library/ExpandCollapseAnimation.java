// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.tjerkw.slideexpandable.library;

import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Transformation;

public class ExpandCollapseAnimation extends Animation
{

    public ExpandCollapseAnimation(View view, int i)
    {
        mAnimatedView = view;
        mEndHeight = mAnimatedView.getMeasuredHeight();
        mLayoutParams = (android.widget.LinearLayout.LayoutParams)view.getLayoutParams();
        mType = i;
        if(mType == 0)
            mLayoutParams.bottomMargin = -mEndHeight;
        else
            mLayoutParams.bottomMargin = 0;
        view.setVisibility(0);
    }

    protected void applyTransformation(float f, Transformation transformation)
    {
        super.applyTransformation(f, transformation);
        if(f < 1.0F)
        {
            if(mType == 0)
                mLayoutParams.bottomMargin = -mEndHeight + (int)(f * (float)mEndHeight);
            else
                mLayoutParams.bottomMargin = -(int)(f * (float)mEndHeight);
            Log.d("ExpandCollapseAnimation", (new StringBuilder()).append("anim height ").append(mLayoutParams.bottomMargin).toString());
            mAnimatedView.requestLayout();
            return;
        }
        if(mType == 0)
        {
            mLayoutParams.bottomMargin = 0;
            mAnimatedView.requestLayout();
            return;
        } else
        {
            mLayoutParams.bottomMargin = -mEndHeight;
            mAnimatedView.setVisibility(8);
            mAnimatedView.requestLayout();
            return;
        }
    }

    public static final int COLLAPSE = 1;
    public static final int EXPAND;
    private View mAnimatedView;
    private int mEndHeight;
    private android.widget.LinearLayout.LayoutParams mLayoutParams;
    private int mType;
}
