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

public class CirclePageIndicator extends View
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


    public CirclePageIndicator(Context context)
    {
        this(context, null);
    }

    public CirclePageIndicator(Context context, AttributeSet attributeset)
    {
        this(context, attributeset, R.attr.vpiCirclePageIndicatorStyle);
    }

    public CirclePageIndicator(Context context, AttributeSet attributeset, int i)
    {
        super(context, attributeset, i);
        mPaintPageFill = new Paint(1);
        mPaintStroke = new Paint(1);
        mPaintFill = new Paint(1);
        mLastMotionX = -1F;
        mActivePointerId = -1;
        if(isInEditMode())
            return;
        Resources resources = getResources();
        int j = resources.getColor(R.color.default_circle_indicator_page_color);
        int k = resources.getColor(R.color.default_circle_indicator_fill_color);
        int l = resources.getInteger(R.integer.default_circle_indicator_orientation);
        int i1 = resources.getColor(R.color.default_circle_indicator_stroke_color);
        float f = resources.getDimension(R.dimen.default_circle_indicator_stroke_width);
        float f1 = resources.getDimension(R.dimen.default_circle_indicator_radius);
        boolean flag = resources.getBoolean(R.bool.default_circle_indicator_centered);
        boolean flag1 = resources.getBoolean(R.bool.default_circle_indicator_snap);
        TypedArray typedarray = context.obtainStyledAttributes(attributeset, R.styleable.CirclePageIndicator, i, 0);
        mCentered = typedarray.getBoolean(2, flag);
        mOrientation = typedarray.getInt(0, l);
        mPaintPageFill.setStyle(android.graphics.Paint.Style.FILL);
        mPaintPageFill.setColor(typedarray.getColor(5, j));
        mPaintStroke.setStyle(android.graphics.Paint.Style.STROKE);
        mPaintStroke.setColor(typedarray.getColor(8, i1));
        mPaintStroke.setStrokeWidth(typedarray.getDimension(3, f));
        mPaintFill.setStyle(android.graphics.Paint.Style.FILL);
        mPaintFill.setColor(typedarray.getColor(4, k));
        mRadius = typedarray.getDimension(6, f1);
        mSnap = typedarray.getBoolean(7, flag1);
        android.graphics.drawable.Drawable drawable = typedarray.getDrawable(1);
        if(drawable != null)
            setBackgroundDrawable(drawable);
        typedarray.recycle();
        mTouchSlop = ViewConfigurationCompat.getScaledPagingTouchSlop(ViewConfiguration.get(context));
    }

    private int measureLong(int i)
    {
        int j = android.view.View.MeasureSpec.getMode(i);
        int k = android.view.View.MeasureSpec.getSize(i);
        int l;
        if(j == 0x40000000 || mViewPager == null)
        {
            l = k;
        } else
        {
            int i1 = mViewPager.getAdapter().getCount();
            l = (int)(1.0F + ((float)(getPaddingLeft() + getPaddingRight()) + (float)(i1 * 2) * mRadius + (float)(i1 - 1) * mRadius));
            if(j == 0x80000000)
                return Math.min(l, k);
        }
        return l;
    }

    private int measureShort(int i)
    {
        int j = android.view.View.MeasureSpec.getMode(i);
        int k = android.view.View.MeasureSpec.getSize(i);
        int l;
        if(j == 0x40000000)
        {
            l = k;
        } else
        {
            l = (int)(1.0F + (2.0F * mRadius + (float)getPaddingTop() + (float)getPaddingBottom()));
            if(j == 0x80000000)
                return Math.min(l, k);
        }
        return l;
    }

    public int getFillColor()
    {
        return mPaintFill.getColor();
    }

    public int getOrientation()
    {
        return mOrientation;
    }

    public int getPageColor()
    {
        return mPaintPageFill.getColor();
    }

    public float getRadius()
    {
        return mRadius;
    }

    public int getStrokeColor()
    {
        return mPaintStroke.getColor();
    }

    public float getStrokeWidth()
    {
        return mPaintStroke.getStrokeWidth();
    }

    public boolean isCentered()
    {
        return mCentered;
    }

    public boolean isSnap()
    {
        return mSnap;
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
            {
                if(mCurrentPage >= i)
                {
                    setCurrentItem(i - 1);
                    return;
                }
                int j;
                int k;
                int l;
                int i1;
                float f;
                float f1;
                float f2;
                float f3;
                int j1;
                if(mOrientation == 0)
                {
                    j = getWidth();
                    k = getPaddingLeft();
                    l = getPaddingRight();
                    i1 = getPaddingTop();
                } else
                {
                    j = getHeight();
                    k = getPaddingTop();
                    l = getPaddingBottom();
                    i1 = getPaddingLeft();
                }
                f = 3F * mRadius;
                f1 = (float)i1 + mRadius;
                f2 = (float)k + mRadius;
                if(mCentered)
                    f2 += (float)(j - k - l) / 2.0F - (f * (float)i) / 2.0F;
                f3 = mRadius;
                if(mPaintStroke.getStrokeWidth() > 0.0F)
                    f3 -= mPaintStroke.getStrokeWidth() / 2.0F;
                j1 = 0;
                while(j1 < i) 
                {
                    float f7 = f2 + f * (float)j1;
                    float f8;
                    float f9;
                    if(mOrientation == 0)
                    {
                        f8 = f7;
                        f9 = f1;
                    } else
                    {
                        f8 = f1;
                        f9 = f7;
                    }
                    if(mPaintPageFill.getAlpha() > 0)
                        canvas.drawCircle(f8, f9, f3, mPaintPageFill);
                    if(f3 != mRadius)
                        canvas.drawCircle(f8, f9, mRadius, mPaintStroke);
                    j1++;
                }
                int k1;
                float f4;
                float f5;
                float f6;
                if(mSnap)
                    k1 = mSnapPage;
                else
                    k1 = mCurrentPage;
                f4 = f * (float)k1;
                if(!mSnap)
                    f4 += f * mPageOffset;
                if(mOrientation == 0)
                {
                    f5 = f2 + f4;
                    f6 = f1;
                } else
                {
                    f5 = f1;
                    f6 = f2 + f4;
                }
                canvas.drawCircle(f5, f6, mRadius, mPaintFill);
                return;
            }
    }

    protected void onMeasure(int i, int j)
    {
        if(mOrientation == 0)
        {
            setMeasuredDimension(measureLong(i), measureShort(j));
            return;
        } else
        {
            setMeasuredDimension(measureShort(i), measureLong(j));
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
        mPageOffset = f;
        invalidate();
        if(mListener != null)
            mListener.onPageScrolled(i, f, j);
    }

    public void onPageSelected(int i)
    {
        if(mSnap || mScrollState == 0)
        {
            mCurrentPage = i;
            mSnapPage = i;
            invalidate();
        }
        if(mListener != null)
            mListener.onPageSelected(i);
    }

    public void onRestoreInstanceState(Parcelable parcelable)
    {
        SavedState savedstate = (SavedState)parcelable;
        super.onRestoreInstanceState(savedstate.getSuperState());
        mCurrentPage = savedstate.currentPage;
        mSnapPage = savedstate.currentPage;
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

    public void setCentered(boolean flag)
    {
        mCentered = flag;
        invalidate();
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

    public void setFillColor(int i)
    {
        mPaintFill.setColor(i);
        invalidate();
    }

    public void setOnPageChangeListener(android.support.v4.view.ViewPager.OnPageChangeListener onpagechangelistener)
    {
        mListener = onpagechangelistener;
    }

    public void setOrientation(int i)
    {
        switch(i)
        {
        default:
            throw new IllegalArgumentException("Orientation must be either HORIZONTAL or VERTICAL.");

        case 0: // '\0'
        case 1: // '\001'
            mOrientation = i;
            break;
        }
        requestLayout();
    }

    public void setPageColor(int i)
    {
        mPaintPageFill.setColor(i);
        invalidate();
    }

    public void setRadius(float f)
    {
        mRadius = f;
        invalidate();
    }

    public void setSnap(boolean flag)
    {
        mSnap = flag;
        invalidate();
    }

    public void setStrokeColor(int i)
    {
        mPaintStroke.setColor(i);
        invalidate();
    }

    public void setStrokeWidth(float f)
    {
        mPaintStroke.setStrokeWidth(f);
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
            return;
        }
    }

    public void setViewPager(ViewPager viewpager, int i)
    {
        setViewPager(viewpager);
        setCurrentItem(i);
    }

    private static final int INVALID_POINTER = -1;
    private int mActivePointerId;
    private boolean mCentered;
    private int mCurrentPage;
    private boolean mIsDragging;
    private float mLastMotionX;
    private android.support.v4.view.ViewPager.OnPageChangeListener mListener;
    private int mOrientation;
    private float mPageOffset;
    private final Paint mPaintFill;
    private final Paint mPaintPageFill;
    private final Paint mPaintStroke;
    private float mRadius;
    private int mScrollState;
    private boolean mSnap;
    private int mSnapPage;
    private int mTouchSlop;
    private ViewPager mViewPager;
}
