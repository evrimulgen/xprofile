// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.viewpagerindicator;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.view.*;
import android.util.AttributeSet;
import android.view.*;

// Referenced classes of package com.viewpagerindicator:
//            PageIndicator

public class UnderlinePageIndicator extends View
    implements PageIndicator
{
    static class SavedState extends android.view.View.BaseSavedState
    {

        public void writeToParcel(Parcel parcel, int i)
        {
            super.writeToParcel(parcel, i);
            parcel.writeInt(currentPage);
        }

        public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

            public SavedState createFromParcel(Parcel parcel)
            {
                return new SavedState(parcel);
            }

            public volatile Object createFromParcel(Parcel parcel)
            {
                return createFromParcel(parcel);
            }

            public SavedState[] newArray(int i)
            {
                return new SavedState[i];
            }

            public volatile Object[] newArray(int i)
            {
                return newArray(i);
            }

        }
;
        int currentPage;


        private SavedState(Parcel parcel)
        {
            super(parcel);
            currentPage = parcel.readInt();
        }


        public SavedState(Parcelable parcelable)
        {
            super(parcelable);
        }
    }


    public UnderlinePageIndicator(Context context)
    {
        this(context, null);
    }

    public UnderlinePageIndicator(Context context, AttributeSet attributeset)
    {
        this(context, attributeset, R.attr.vpiUnderlinePageIndicatorStyle);
    }

    public UnderlinePageIndicator(Context context, AttributeSet attributeset, int i)
    {
        super(context, attributeset, i);
        mPaint = new Paint(1);
        mLastMotionX = -1F;
        mActivePointerId = -1;
        mFadeRunnable = new Runnable() {

            public void run()
            {
                if(mFades)
                {
                    int i1 = Math.max(mPaint.getAlpha() - mFadeBy, 0);
                    mPaint.setAlpha(i1);
                    invalidate();
                    if(i1 > 0)
                    {
                        postDelayed(this, 30L);
                        return;
                    }
                }
            }

            final UnderlinePageIndicator this$0;

            
            {
                this$0 = UnderlinePageIndicator.this;
                super();
            }
        }
;
        if(isInEditMode())
            return;
        Resources resources = getResources();
        boolean flag = resources.getBoolean(R.bool.default_underline_indicator_fades);
        int j = resources.getInteger(R.integer.default_underline_indicator_fade_delay);
        int k = resources.getInteger(R.integer.default_underline_indicator_fade_length);
        int l = resources.getColor(R.color.default_underline_indicator_selected_color);
        TypedArray typedarray = context.obtainStyledAttributes(attributeset, R.styleable.UnderlinePageIndicator, i, 0);
        setFades(typedarray.getBoolean(2, flag));
        setSelectedColor(typedarray.getColor(1, l));
        setFadeDelay(typedarray.getInteger(3, j));
        setFadeLength(typedarray.getInteger(4, k));
        android.graphics.drawable.Drawable drawable = typedarray.getDrawable(0);
        if(drawable != null)
            setBackgroundDrawable(drawable);
        typedarray.recycle();
        mTouchSlop = ViewConfigurationCompat.getScaledPagingTouchSlop(ViewConfiguration.get(context));
    }

    public int getFadeDelay()
    {
        return mFadeDelay;
    }

    public int getFadeLength()
    {
        return mFadeLength;
    }

    public boolean getFades()
    {
        return mFades;
    }

    public int getSelectedColor()
    {
        return mPaint.getColor();
    }

    public void notifyDataSetChanged()
    {
        invalidate();
    }

    protected void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);
        int i;
        if(mViewPager != null)
            if((i = mViewPager.getAdapter().getCount()) != 0)
                if(mCurrentPage >= i)
                {
                    setCurrentItem(i - 1);
                    return;
                } else
                {
                    int j = getPaddingLeft();
                    float f = (float)(getWidth() - j - getPaddingRight()) / (1.0F * (float)i);
                    float f1 = (float)j + f * ((float)mCurrentPage + mPositionOffset);
                    float f2 = f1 + f;
                    canvas.drawRect(f1, getPaddingTop(), f2, getHeight() - getPaddingBottom(), mPaint);
                    return;
                }
    }

    public void onPageScrollStateChanged(int i)
    {
        mScrollState = i;
        if(mListener != null)
            mListener.onPageScrollStateChanged(i);
    }

    public void onPageScrolled(int i, float f, int j)
    {
        mCurrentPage = i;
        mPositionOffset = f;
        if(!mFades) goto _L2; else goto _L1
_L1:
        if(j <= 0) goto _L4; else goto _L3
_L3:
        removeCallbacks(mFadeRunnable);
        mPaint.setAlpha(255);
_L2:
        invalidate();
        if(mListener != null)
            mListener.onPageScrolled(i, f, j);
        return;
_L4:
        if(mScrollState != 1)
            postDelayed(mFadeRunnable, mFadeDelay);
        if(true) goto _L2; else goto _L5
_L5:
    }

    public void onPageSelected(int i)
    {
        if(mScrollState == 0)
        {
            mCurrentPage = i;
            mPositionOffset = 0.0F;
            invalidate();
            mFadeRunnable.run();
        }
        if(mListener != null)
            mListener.onPageSelected(i);
    }

    public void onRestoreInstanceState(Parcelable parcelable)
    {
        SavedState savedstate = (SavedState)parcelable;
        super.onRestoreInstanceState(savedstate.getSuperState());
        mCurrentPage = savedstate.currentPage;
        requestLayout();
    }

    public Parcelable onSaveInstanceState()
    {
        SavedState savedstate = new SavedState(super.onSaveInstanceState());
        savedstate.currentPage = mCurrentPage;
        return savedstate;
    }

    public boolean onTouchEvent(MotionEvent motionevent)
    {
        int i;
        if(super.onTouchEvent(motionevent))
            return true;
        if(mViewPager == null || mViewPager.getAdapter().getCount() == 0)
            return false;
        i = 0xff & motionevent.getAction();
        i;
        JVM INSTR tableswitch 0 6: default 84
    //                   0 86
    //                   1 200
    //                   2 106
    //                   3 200
    //                   4 84
    //                   5 355
    //                   6 384;
           goto _L1 _L2 _L3 _L4 _L3 _L1 _L5 _L6
_L1:
        return true;
_L2:
        mActivePointerId = MotionEventCompat.getPointerId(motionevent, 0);
        mLastMotionX = motionevent.getX();
        continue; /* Loop/switch isn't completed */
_L4:
        float f2 = MotionEventCompat.getX(motionevent, MotionEventCompat.findPointerIndex(motionevent, mActivePointerId));
        float f3 = f2 - mLastMotionX;
        if(!mIsDragging && Math.abs(f3) > (float)mTouchSlop)
            mIsDragging = true;
        if(mIsDragging)
        {
            mLastMotionX = f2;
            if(mViewPager.isFakeDragging() || mViewPager.beginFakeDrag())
                mViewPager.fakeDragBy(f3);
        }
        continue; /* Loop/switch isn't completed */
_L3:
        if(!mIsDragging)
        {
            int i1 = mViewPager.getAdapter().getCount();
            int j1 = getWidth();
            float f = (float)j1 / 2.0F;
            float f1 = (float)j1 / 6F;
            if(mCurrentPage > 0 && motionevent.getX() < f - f1)
            {
                if(i != 3)
                    mViewPager.setCurrentItem(-1 + mCurrentPage);
                return true;
            }
            if(mCurrentPage < i1 - 1 && motionevent.getX() > f + f1)
            {
                if(i != 3)
                    mViewPager.setCurrentItem(1 + mCurrentPage);
                return true;
            }
        }
        mIsDragging = false;
        mActivePointerId = -1;
        if(mViewPager.isFakeDragging())
            mViewPager.endFakeDrag();
        continue; /* Loop/switch isn't completed */
_L5:
        int l = MotionEventCompat.getActionIndex(motionevent);
        mLastMotionX = MotionEventCompat.getX(motionevent, l);
        mActivePointerId = MotionEventCompat.getPointerId(motionevent, l);
        continue; /* Loop/switch isn't completed */
_L6:
        int j = MotionEventCompat.getActionIndex(motionevent);
        if(MotionEventCompat.getPointerId(motionevent, j) == mActivePointerId)
        {
            int k;
            if(j == 0)
                k = 1;
            else
                k = 0;
            mActivePointerId = MotionEventCompat.getPointerId(motionevent, k);
        }
        mLastMotionX = MotionEventCompat.getX(motionevent, MotionEventCompat.findPointerIndex(motionevent, mActivePointerId));
        if(true) goto _L1; else goto _L7
_L7:
    }

    public void setCurrentItem(int i)
    {
        if(mViewPager == null)
        {
            throw new IllegalStateException("ViewPager has not been bound.");
        } else
        {
            mViewPager.setCurrentItem(i);
            mCurrentPage = i;
            invalidate();
            return;
        }
    }

    public void setFadeDelay(int i)
    {
        mFadeDelay = i;
    }

    public void setFadeLength(int i)
    {
        mFadeLength = i;
        mFadeBy = 255 / (mFadeLength / 30);
    }

    public void setFades(boolean flag)
    {
label0:
        {
            if(flag != mFades)
            {
                mFades = flag;
                if(!flag)
                    break label0;
                post(mFadeRunnable);
            }
            return;
        }
        removeCallbacks(mFadeRunnable);
        mPaint.setAlpha(255);
        invalidate();
    }

    public void setOnPageChangeListener(android.support.v4.view.ViewPager.OnPageChangeListener onpagechangelistener)
    {
        mListener = onpagechangelistener;
    }

    public void setSelectedColor(int i)
    {
        mPaint.setColor(i);
        invalidate();
    }

    public void setViewPager(ViewPager viewpager)
    {
        if(mViewPager == viewpager)
            return;
        if(mViewPager != null)
            mViewPager.setOnPageChangeListener(null);
        if(viewpager.getAdapter() == null)
        {
            throw new IllegalStateException("ViewPager does not have adapter instance.");
        } else
        {
            mViewPager = viewpager;
            mViewPager.setOnPageChangeListener(this);
            invalidate();
            post(new Runnable() {

                public void run()
                {
                    if(mFades)
                        post(mFadeRunnable);
                }

                final UnderlinePageIndicator this$0;

            
            {
                this$0 = UnderlinePageIndicator.this;
                super();
            }
            }
);
            return;
        }
    }

    public void setViewPager(ViewPager viewpager, int i)
    {
        setViewPager(viewpager);
        setCurrentItem(i);
    }

    private static final int FADE_FRAME_MS = 30;
    private static final int INVALID_POINTER = -1;
    private int mActivePointerId;
    private int mCurrentPage;
    private int mFadeBy;
    private int mFadeDelay;
    private int mFadeLength;
    private final Runnable mFadeRunnable;
    private boolean mFades;
    private boolean mIsDragging;
    private float mLastMotionX;
    private android.support.v4.view.ViewPager.OnPageChangeListener mListener;
    private final Paint mPaint;
    private float mPositionOffset;
    private int mScrollState;
    private int mTouchSlop;
    private ViewPager mViewPager;




}
