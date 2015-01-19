// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.handmark.pulltorefresh.library.internal;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Matrix;
import android.graphics.drawable.Drawable;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.ProgressBar;

// Referenced classes of package com.handmark.pulltorefresh.library.internal:
//            LoadingLayout

public class FlipLoadingLayout extends LoadingLayout
{

    public FlipLoadingLayout(Context context, com.handmark.pulltorefresh.library.PullToRefreshBase.Mode mode, com.handmark.pulltorefresh.library.PullToRefreshBase.Orientation orientation, TypedArray typedarray)
    {
        super(context, mode, orientation, typedarray);
        int i;
        if(mode == com.handmark.pulltorefresh.library.PullToRefreshBase.Mode.PULL_FROM_START)
            i = -180;
        else
            i = 180;
        mRotateAnimation = new RotateAnimation(0.0F, i, 1, 0.5F, 1, 0.5F);
        mRotateAnimation.setInterpolator(ANIMATION_INTERPOLATOR);
        mRotateAnimation.setDuration(150L);
        mRotateAnimation.setFillAfter(true);
        mResetRotateAnimation = new RotateAnimation(i, 0.0F, 1, 0.5F, 1, 0.5F);
        mResetRotateAnimation.setInterpolator(ANIMATION_INTERPOLATOR);
        mResetRotateAnimation.setDuration(150L);
        mResetRotateAnimation.setFillAfter(true);
    }

    private float getDrawableRotationAngle()
    {
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

        _cls1..SwitchMap.com.handmark.pulltorefresh.library.PullToRefreshBase.Mode[mMode.ordinal()];
        JVM INSTR tableswitch 1 2: default 32
    //                   1 34
    //                   2 50;
           goto _L1 _L2 _L3
_L1:
        return 0.0F;
_L2:
        return mScrollDirection != com.handmark.pulltorefresh.library.PullToRefreshBase.Orientation.HORIZONTAL ? 180F : 90F;
_L3:
        if(mScrollDirection == com.handmark.pulltorefresh.library.PullToRefreshBase.Orientation.HORIZONTAL)
            return 270F;
        if(true) goto _L1; else goto _L4
_L4:
    }

    protected int getDefaultDrawableResId()
    {
        return com.handmark.pulltorefresh.library.R.drawable.default_ptr_flip;
    }

    protected void onLoadingDrawableSet(Drawable drawable)
    {
        if(drawable != null)
        {
            int i = drawable.getIntrinsicHeight();
            int j = drawable.getIntrinsicWidth();
            android.view.ViewGroup.LayoutParams layoutparams = mHeaderImage.getLayoutParams();
            int k = Math.max(i, j);
            layoutparams.height = k;
            layoutparams.width = k;
            mHeaderImage.requestLayout();
            mHeaderImage.setScaleType(android.widget.ImageView.ScaleType.MATRIX);
            Matrix matrix = new Matrix();
            matrix.postTranslate((float)(layoutparams.width - j) / 2.0F, (float)(layoutparams.height - i) / 2.0F);
            matrix.postRotate(getDrawableRotationAngle(), (float)layoutparams.width / 2.0F, (float)layoutparams.height / 2.0F);
            mHeaderImage.setImageMatrix(matrix);
        }
    }

    protected void onPullImpl(float f)
    {
    }

    protected void pullToRefreshImpl()
    {
        if(mRotateAnimation == mHeaderImage.getAnimation())
            mHeaderImage.startAnimation(mResetRotateAnimation);
    }

    protected void refreshingImpl()
    {
        mHeaderImage.clearAnimation();
        mHeaderImage.setVisibility(4);
        mHeaderProgress.setVisibility(0);
    }

    protected void releaseToRefreshImpl()
    {
        mHeaderImage.startAnimation(mRotateAnimation);
    }

    protected void resetImpl()
    {
        mHeaderImage.clearAnimation();
        mHeaderProgress.setVisibility(8);
        mHeaderImage.setVisibility(0);
    }

    static final int FLIP_ANIMATION_DURATION = 150;
    private final Animation mResetRotateAnimation;
    private final Animation mRotateAnimation;
}
