// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.handmark.pulltorefresh.library.internal;

import android.content.Context;
import android.content.res.*;
import android.graphics.Typeface;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.widget.*;
import com.handmark.pulltorefresh.library.ILoadingLayout;

// Referenced classes of package com.handmark.pulltorefresh.library.internal:
//            ViewCompat, Utils

public abstract class LoadingLayout extends FrameLayout
    implements ILoadingLayout
{

    public LoadingLayout(Context context, com.handmark.pulltorefresh.library.PullToRefreshBase.Mode mode, com.handmark.pulltorefresh.library.PullToRefreshBase.Orientation orientation, TypedArray typedarray)
    {
        super(context);
        mMode = mode;
        mScrollDirection = orientation;
        static class _cls1
        {

            static final int $SwitchMap$com$handmark$pulltorefresh$library$PullToRefreshBase$Mode[];
            static final int $SwitchMap$com$handmark$pulltorefresh$library$PullToRefreshBase$Orientation[];

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
                catch(NoSuchFieldError nosuchfielderror1) { }
                $SwitchMap$com$handmark$pulltorefresh$library$PullToRefreshBase$Orientation = new int[com.handmark.pulltorefresh.library.PullToRefreshBase.Orientation.values().length];
                try
                {
                    $SwitchMap$com$handmark$pulltorefresh$library$PullToRefreshBase$Orientation[com.handmark.pulltorefresh.library.PullToRefreshBase.Orientation.HORIZONTAL.ordinal()] = 1;
                }
                catch(NoSuchFieldError nosuchfielderror2) { }
                try
                {
                    $SwitchMap$com$handmark$pulltorefresh$library$PullToRefreshBase$Orientation[com.handmark.pulltorefresh.library.PullToRefreshBase.Orientation.VERTICAL.ordinal()] = 2;
                }
                catch(NoSuchFieldError nosuchfielderror3)
                {
                    return;
                }
            }
        }

        _cls1..SwitchMap.com.handmark.pulltorefresh.library.PullToRefreshBase.Orientation[orientation.ordinal()];
        JVM INSTR tableswitch 1 1: default 40
    //                   1 484;
           goto _L1 _L2
_L1:
        LayoutInflater.from(context).inflate(com.handmark.pulltorefresh.library.R.layout.pull_to_refresh_header_vertical, this);
_L5:
        android.widget.FrameLayout.LayoutParams layoutparams;
        mInnerLayout = (FrameLayout)findViewById(com.handmark.pulltorefresh.library.R.id.fl_inner);
        mHeaderText = (TextView)mInnerLayout.findViewById(com.handmark.pulltorefresh.library.R.id.pull_to_refresh_text);
        mHeaderProgress = (ProgressBar)mInnerLayout.findViewById(com.handmark.pulltorefresh.library.R.id.pull_to_refresh_progress);
        mSubHeaderText = (TextView)mInnerLayout.findViewById(com.handmark.pulltorefresh.library.R.id.pull_to_refresh_sub_text);
        mHeaderImage = (ImageView)mInnerLayout.findViewById(com.handmark.pulltorefresh.library.R.id.pull_to_refresh_image);
        layoutparams = (android.widget.FrameLayout.LayoutParams)mInnerLayout.getLayoutParams();
        _cls1..SwitchMap.com.handmark.pulltorefresh.library.PullToRefreshBase.Mode[mode.ordinal()];
        JVM INSTR tableswitch 1 1: default 172
    //                   1 499;
           goto _L3 _L4
_L3:
        Drawable drawable;
        int i;
        TypedValue typedvalue;
        TypedValue typedvalue1;
        boolean flag;
        ColorStateList colorstatelist;
        ColorStateList colorstatelist1;
        Drawable drawable1;
        int j;
        if(orientation == com.handmark.pulltorefresh.library.PullToRefreshBase.Orientation.VERTICAL)
            j = 80;
        else
            j = 5;
        layoutparams.gravity = j;
        mPullLabel = context.getString(com.handmark.pulltorefresh.library.R.string.pull_to_refresh_pull_label);
        mRefreshingLabel = context.getString(com.handmark.pulltorefresh.library.R.string.pull_to_refresh_refreshing_label);
        mReleaseLabel = context.getString(com.handmark.pulltorefresh.library.R.string.pull_to_refresh_release_label);
_L6:
        if(typedarray.hasValue(1))
        {
            drawable1 = typedarray.getDrawable(1);
            if(drawable1 != null)
                ViewCompat.setBackground(this, drawable1);
        }
        if(typedarray.hasValue(10))
        {
            typedvalue = new TypedValue();
            typedarray.getValue(10, typedvalue);
            setTextAppearance(typedvalue.data);
        }
        if(typedarray.hasValue(11))
        {
            typedvalue1 = new TypedValue();
            typedarray.getValue(11, typedvalue1);
            setSubTextAppearance(typedvalue1.data);
        }
        if(typedarray.hasValue(2))
        {
            colorstatelist1 = typedarray.getColorStateList(2);
            if(colorstatelist1 != null)
                setTextColor(colorstatelist1);
        }
        if(typedarray.hasValue(3))
        {
            colorstatelist = typedarray.getColorStateList(3);
            if(colorstatelist != null)
                setSubTextColor(colorstatelist);
        }
        flag = typedarray.hasValue(6);
        drawable = null;
        if(flag)
            drawable = typedarray.getDrawable(6);
        switch(_cls1..SwitchMap.com.handmark.pulltorefresh.library.PullToRefreshBase.Mode[mode.ordinal()])
        {
        default:
            if(typedarray.hasValue(7))
                drawable = typedarray.getDrawable(7);
            else
            if(typedarray.hasValue(17))
            {
                Utils.warnDeprecation("ptrDrawableTop", "ptrDrawableStart");
                drawable = typedarray.getDrawable(17);
            }
            break;

        case 1: // '\001'
            break MISSING_BLOCK_LABEL_594;
        }
_L7:
        if(drawable == null)
            drawable = context.getResources().getDrawable(getDefaultDrawableResId());
        setLoadingDrawable(drawable);
        reset();
        return;
_L2:
        LayoutInflater.from(context).inflate(com.handmark.pulltorefresh.library.R.layout.pull_to_refresh_header_horizontal, this);
          goto _L5
_L4:
        if(orientation == com.handmark.pulltorefresh.library.PullToRefreshBase.Orientation.VERTICAL)
            i = 48;
        else
            i = 3;
        layoutparams.gravity = i;
        mPullLabel = context.getString(com.handmark.pulltorefresh.library.R.string.pull_to_refresh_from_bottom_pull_label);
        mRefreshingLabel = context.getString(com.handmark.pulltorefresh.library.R.string.pull_to_refresh_from_bottom_refreshing_label);
        mReleaseLabel = context.getString(com.handmark.pulltorefresh.library.R.string.pull_to_refresh_from_bottom_release_label);
          goto _L6
        if(typedarray.hasValue(8))
            drawable = typedarray.getDrawable(8);
        else
        if(typedarray.hasValue(18))
        {
            Utils.warnDeprecation("ptrDrawableBottom", "ptrDrawableEnd");
            drawable = typedarray.getDrawable(18);
        }
          goto _L7
    }

    private void setSubHeaderText(CharSequence charsequence)
    {
        if(mSubHeaderText != null)
            if(TextUtils.isEmpty(charsequence))
            {
                mSubHeaderText.setVisibility(8);
            } else
            {
                mSubHeaderText.setText(charsequence);
                if(8 == mSubHeaderText.getVisibility())
                {
                    mSubHeaderText.setVisibility(0);
                    return;
                }
            }
    }

    private void setSubTextAppearance(int i)
    {
        if(mSubHeaderText != null)
            mSubHeaderText.setTextAppearance(getContext(), i);
    }

    private void setSubTextColor(ColorStateList colorstatelist)
    {
        if(mSubHeaderText != null)
            mSubHeaderText.setTextColor(colorstatelist);
    }

    private void setTextAppearance(int i)
    {
        if(mHeaderText != null)
            mHeaderText.setTextAppearance(getContext(), i);
        if(mSubHeaderText != null)
            mSubHeaderText.setTextAppearance(getContext(), i);
    }

    private void setTextColor(ColorStateList colorstatelist)
    {
        if(mHeaderText != null)
            mHeaderText.setTextColor(colorstatelist);
        if(mSubHeaderText != null)
            mSubHeaderText.setTextColor(colorstatelist);
    }

    public final int getContentSize()
    {
        switch(_cls1..SwitchMap.com.handmark.pulltorefresh.library.PullToRefreshBase.Orientation[mScrollDirection.ordinal()])
        {
        default:
            return mInnerLayout.getHeight();

        case 1: // '\001'
            return mInnerLayout.getWidth();
        }
    }

    protected abstract int getDefaultDrawableResId();

    public final void hideAllViews()
    {
        if(mHeaderText.getVisibility() == 0)
            mHeaderText.setVisibility(4);
        if(mHeaderProgress.getVisibility() == 0)
            mHeaderProgress.setVisibility(4);
        if(mHeaderImage.getVisibility() == 0)
            mHeaderImage.setVisibility(4);
        if(mSubHeaderText.getVisibility() == 0)
            mSubHeaderText.setVisibility(4);
    }

    protected abstract void onLoadingDrawableSet(Drawable drawable);

    public final void onPull(float f)
    {
        if(!mUseIntrinsicAnimation)
            onPullImpl(f);
    }

    protected abstract void onPullImpl(float f);

    public final void pullToRefresh()
    {
        if(mHeaderText != null)
            mHeaderText.setText(mPullLabel);
        pullToRefreshImpl();
    }

    protected abstract void pullToRefreshImpl();

    public final void refreshing()
    {
        if(mHeaderText != null)
            mHeaderText.setText(mRefreshingLabel);
        if(mUseIntrinsicAnimation)
            ((AnimationDrawable)mHeaderImage.getDrawable()).start();
        else
            refreshingImpl();
        if(mSubHeaderText != null)
            mSubHeaderText.setVisibility(8);
    }

    protected abstract void refreshingImpl();

    public final void releaseToRefresh()
    {
        if(mHeaderText != null)
            mHeaderText.setText(mReleaseLabel);
        releaseToRefreshImpl();
    }

    protected abstract void releaseToRefreshImpl();

    public final void reset()
    {
label0:
        {
            if(mHeaderText != null)
                mHeaderText.setText(mPullLabel);
            mHeaderImage.setVisibility(0);
            if(mUseIntrinsicAnimation)
                ((AnimationDrawable)mHeaderImage.getDrawable()).stop();
            else
                resetImpl();
            if(mSubHeaderText != null)
            {
                if(!TextUtils.isEmpty(mSubHeaderText.getText()))
                    break label0;
                mSubHeaderText.setVisibility(8);
            }
            return;
        }
        mSubHeaderText.setVisibility(0);
    }

    protected abstract void resetImpl();

    public final void setHeight(int i)
    {
        getLayoutParams().height = i;
        requestLayout();
    }

    public void setLastUpdatedLabel(CharSequence charsequence)
    {
        setSubHeaderText(charsequence);
    }

    public final void setLoadingDrawable(Drawable drawable)
    {
        mHeaderImage.setImageDrawable(drawable);
        mUseIntrinsicAnimation = drawable instanceof AnimationDrawable;
        onLoadingDrawableSet(drawable);
    }

    public void setPullLabel(CharSequence charsequence)
    {
        mPullLabel = charsequence;
    }

    public void setRefreshingLabel(CharSequence charsequence)
    {
        mRefreshingLabel = charsequence;
    }

    public void setReleaseLabel(CharSequence charsequence)
    {
        mReleaseLabel = charsequence;
    }

    public void setTextTypeface(Typeface typeface)
    {
        mHeaderText.setTypeface(typeface);
    }

    public final void setWidth(int i)
    {
        getLayoutParams().width = i;
        requestLayout();
    }

    public final void showInvisibleViews()
    {
        if(4 == mHeaderText.getVisibility())
            mHeaderText.setVisibility(0);
        if(4 == mHeaderProgress.getVisibility())
            mHeaderProgress.setVisibility(0);
        if(4 == mHeaderImage.getVisibility())
            mHeaderImage.setVisibility(0);
        if(4 == mSubHeaderText.getVisibility())
            mSubHeaderText.setVisibility(0);
    }

    static final Interpolator ANIMATION_INTERPOLATOR = new LinearInterpolator();
    static final String LOG_TAG = "PullToRefresh-LoadingLayout";
    protected final ImageView mHeaderImage;
    protected final ProgressBar mHeaderProgress;
    private final TextView mHeaderText;
    private FrameLayout mInnerLayout;
    protected final com.handmark.pulltorefresh.library.PullToRefreshBase.Mode mMode;
    private CharSequence mPullLabel;
    private CharSequence mRefreshingLabel;
    private CharSequence mReleaseLabel;
    protected final com.handmark.pulltorefresh.library.PullToRefreshBase.Orientation mScrollDirection;
    private final TextView mSubHeaderText;
    private boolean mUseIntrinsicAnimation;

}
