// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.handmark.pulltorefresh.library.internal;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Matrix;
import android.graphics.drawable.Drawable;
import android.view.animation.*;
import android.widget.FrameLayout;
import android.widget.ImageView;

public class IndicatorLayout extends FrameLayout
    implements android.view.animation.Animation.AnimationListener
{

    public IndicatorLayout(Context context, com.handmark.pulltorefresh.library.PullToRefreshBase.Mode mode)
    {
        Drawable drawable;
        super(context);
        mArrowImageView = new ImageView(context);
        drawable = getResources().getDrawable(com.handmark.pulltorefresh.library.R.drawable.indicator_arrow);
        mArrowImageView.setImageDrawable(drawable);
        int i = getResources().getDimensionPixelSize(com.handmark.pulltorefresh.library.R.dimen.indicator_internal_padding);
        mArrowImageView.setPadding(i, i, i, i);
        addView(mArrowImageView);
        static class _cls1
        {

            static final int $SwitchMap$com$handmark$pulltorefresh$library$PullToRefreshBase$Mode[];

            static 
            {
                $SwitchMap$com$handmark$pulltorefresh$library$PullToRefreshBase$Mode = new int[com.handmark.pulltorefresh.library.PullToRefreshBase.Mode.values().length];
                try
                {
                    $SwitchMap$com$handmark$pulltorefresh$library$PullToRefreshBase$Mode[com.handmark.pulltorefresh.library.PullToRefreshBase.Mode.PULL_FROM_END.ordinal()] = 1;
                }
                catch(NoSuchFieldError nosuchfielderror) { }
                try
                {
                    $SwitchMap$com$handmark$pulltorefresh$library$PullToRefreshBase$Mode[com.handmark.pulltorefresh.library.PullToRefreshBase.Mode.PULL_FROM_START.ordinal()] = 2;
                }
                catch(NoSuchFieldError nosuchfielderror1)
                {
                    return;
                }
            }
        }

        _cls1..SwitchMap.com.handmark.pulltorefresh.library.PullToRefreshBase.Mode[mode.ordinal()];
        JVM INSTR tableswitch 1 1: default 96
    //                   1 253;
           goto _L1 _L2
_L1:
        int j;
        int k;
        j = com.handmark.pulltorefresh.library.R.anim.slide_in_from_top;
        k = com.handmark.pulltorefresh.library.R.anim.slide_out_to_top;
        setBackgroundResource(com.handmark.pulltorefresh.library.R.drawable.indicator_bg_top);
_L4:
        mInAnim = AnimationUtils.loadAnimation(context, j);
        mInAnim.setAnimationListener(this);
        mOutAnim = AnimationUtils.loadAnimation(context, k);
        mOutAnim.setAnimationListener(this);
        LinearInterpolator linearinterpolator = new LinearInterpolator();
        mRotateAnimation = new RotateAnimation(0.0F, -180F, 1, 0.5F, 1, 0.5F);
        mRotateAnimation.setInterpolator(linearinterpolator);
        mRotateAnimation.setDuration(150L);
        mRotateAnimation.setFillAfter(true);
        mResetRotateAnimation = new RotateAnimation(-180F, 0.0F, 1, 0.5F, 1, 0.5F);
        mResetRotateAnimation.setInterpolator(linearinterpolator);
        mResetRotateAnimation.setDuration(150L);
        mResetRotateAnimation.setFillAfter(true);
        return;
_L2:
        j = com.handmark.pulltorefresh.library.R.anim.slide_in_from_bottom;
        k = com.handmark.pulltorefresh.library.R.anim.slide_out_to_bottom;
        setBackgroundResource(com.handmark.pulltorefresh.library.R.drawable.indicator_bg_bottom);
        mArrowImageView.setScaleType(android.widget.ImageView.ScaleType.MATRIX);
        Matrix matrix = new Matrix();
        matrix.setRotate(180F, (float)drawable.getIntrinsicWidth() / 2.0F, (float)drawable.getIntrinsicHeight() / 2.0F);
        mArrowImageView.setImageMatrix(matrix);
        if(true) goto _L4; else goto _L3
_L3:
    }

    public void hide()
    {
        startAnimation(mOutAnim);
    }

    public final boolean isVisible()
    {
        Animation animation = getAnimation();
        if(animation == null) goto _L2; else goto _L1
_L1:
        if(mInAnim != animation) goto _L4; else goto _L3
_L3:
        return true;
_L4:
        return false;
_L2:
        if(getVisibility() != 0)
            return false;
        if(true) goto _L3; else goto _L5
_L5:
    }

    public void onAnimationEnd(Animation animation)
    {
        if(animation != mOutAnim) goto _L2; else goto _L1
_L1:
        mArrowImageView.clearAnimation();
        setVisibility(8);
_L4:
        clearAnimation();
        return;
_L2:
        if(animation == mInAnim)
            setVisibility(0);
        if(true) goto _L4; else goto _L3
_L3:
    }

    public void onAnimationRepeat(Animation animation)
    {
    }

    public void onAnimationStart(Animation animation)
    {
        setVisibility(0);
    }

    public void pullToRefresh()
    {
        mArrowImageView.startAnimation(mResetRotateAnimation);
    }

    public void releaseToRefresh()
    {
        mArrowImageView.startAnimation(mRotateAnimation);
    }

    public void show()
    {
        mArrowImageView.clearAnimation();
        startAnimation(mInAnim);
    }

    static final int DEFAULT_ROTATION_ANIMATION_DURATION = 150;
    private ImageView mArrowImageView;
    private Animation mInAnim;
    private Animation mOutAnim;
    private final Animation mResetRotateAnimation;
    private final Animation mRotateAnimation;
}
