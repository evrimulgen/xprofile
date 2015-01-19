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
import android.util.FloatMath;
import android.view.*;

// Referenced classes of package com.viewpagerindicator:
//            PageIndicator

public class LinePageIndicator extends View
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


    public LinePageIndicator(Context context)
    {
        this(context, null);
    }

    public LinePageIndicator(Context context, AttributeSet attributeset)
    {
        this(context, attributeset, R.attr.vpiLinePageIndicatorStyle);
    }

    public LinePageIndicator(Context context, AttributeSet attributeset, int i)
    {
        super(context, attributeset, i);
        mPaintUnselected = new Paint(1);
        mPaintSelected = new Paint(1);
        mLastMotionX = -1F;
        mActivePointerId = -1;
        if(isInEditMode())
            return;
        Resources resources = getResources();
        int j = resources.getColor(R.color.default_line_indicator_selected_color);
        int k = resources.getColor(R.color.default_line_indicator_unselected_color);
        float f = resources.getDimension(R.dimen.default_line_indicator_line_width);
        float f1 = resources.getDimension(R.dimen.default_line_indicator_gap_width);
        float f2 = resources.getDimension(R.dimen.default_line_indicator_stroke_width);
        boolean flag = resources.getBoolean(R.bool.default_line_indicator_centered);
        TypedArray typedarray = context.obtainStyledAttributes(attributeset, R.styleable.LinePageIndicator, i, 0);
        mCentered = typedarray.getBoolean(1, flag);
        mLineWidth = typedarray.getDimension(5, f);
        mGapWidth = typedarray.getDimension(6, f1);
        setStrokeWidth(typedarray.getDimension(3, f2));
        mPaintUnselected.setColor(typedarray.getColor(4, k));
        mPaintSelected.setColor(typedarray.getColor(2, j));
        android.graphics.drawable.Drawable drawable = typedarray.getDrawable(0);
        if(drawable != null)
            setBackgroundDrawable(drawable);
        typedarray.recycle();
        mTouchSlop = ViewConfigurationCompat.getScaledPagingTouchSlop(ViewConfiguration.get(context));
    }

    private int measureHeight(int i)
    {
        int j;
        int k;
        j = android.view.View.MeasureSpec.getMode(i);
        k = android.view.View.MeasureSpec.getSize(i);
        if(j != 0x40000000) goto _L2; else goto _L1
_L1:
        float f = k;
_L4:
        return (int)FloatMath.ceil(f);
_L2:
        f = mPaintSelected.getStrokeWidth() + (float)getPaddingTop() + (float)getPaddingBottom();
        if(j == 0x80000000)
            f = Math.min(f, k);
        if(true) goto _L4; else goto _L3
_L3:
    }

    private int measureWidth(int i)
    {
        int j;
        int k;
        j = android.view.View.MeasureSpec.getMode(i);
        k = android.view.View.MeasureSpec.getSize(i);
        if(j != 0x40000000 && mViewPager != null) goto _L2; else goto _L1
_L1:
        float f = k;
_L4:
        return (int)FloatMath.ceil(f);
_L2:
        int l = mViewPager.getAdapter().getCount();
        f = (float)(getPaddingLeft() + getPaddingRight()) + (float)l * mLineWidth + (float)(l - 1) * mGapWidth;
        if(j == 0x80000000)
            f = Math.min(f, k);
        if(true) goto _L4; else goto _L3
_L3:
    }

    public float getGapWidth()
    {
        return mGapWidth;
    }

    public float getLineWidth()
    {
        return mLineWidth;
    }

    public int getSelectedColor()
    {
        return mPaintSelected.getColor();
    }

    public float getStrokeWidth()
    {
        return mPaintSelected.getStrokeWidth();
    }

    public int getUnselectedColor()
    {
        return mPaintUnselected.getColor();
    }

    public boolean isCentered()
    {
        return mCentered;
    }

    public void notifyDataSetChanged()
    {
        invalidate();
    }

    protected void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);
        if(mViewPager != null) goto _L2; else goto _L1
_L1:
        int i;
        return;
_L2:
        if((i = mViewPager.getAdapter().getCount()) != 0)
        {
            if(mCurrentPage >= i)
            {
                setCurrentItem(i - 1);
                return;
            }
            float f = mLineWidth + mGapWidth;
            float f1 = f * (float)i - mGapWidth;
            float f2 = getPaddingTop();
            float f3 = getPaddingLeft();
            float f4 = getPaddingRight();
            float f5 = f2 + ((float)getHeight() - f2 - (float)getPaddingBottom()) / 2.0F;
            float f6 = f3;
            if(mCentered)
                f6 += ((float)getWidth() - f3 - f4) / 2.0F - f1 / 2.0F;
            int j = 0;
            while(j < i) 
            {
                float f7 = f6 + f * (float)j;
                float f8 = f7 + mLineWidth;
                Paint paint;
                if(j == mCurrentPage)
                    paint = mPaintSelected;
                else
                    paint = mPaintUnselected;
                canvas.drawLine(f7, f5, f8, f5, paint);
                j++;
            }
        }
        if(true) goto _L1; else goto _L3
_L3:
    }

    protected void onMeasure(int i, int j)
    {
        setMeasuredDimension(measureWidth(i), measureHeight(j));
    }

    public void onPageScrollStateChanged(int i)
    {
        if(mListener != null)
            mListener.onPageScrollStateChanged(i);
    }

    public void onPageScrolled(int i, float f, int j)
    {
        if(mListener != null)
            mListener.onPageScrolled(i, f, j);
    }

    public void onPageSelected(int i)
    {
        mCurrentPage = i;
        invalidate();
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

    public void setGapWidth(float f)
    {
        mGapWidth = f;
        invalidate();
    }

    public void setLineWidth(float f)
    {
        mLineWidth = f;
        invalidate();
    }

    public void setOnPageChangeListener(android.support.v4.view.ViewPager.OnPageChangeListener onpagechangelistener)
    {
        mListener = onpagechangelistener;
    }

    public void setSelectedColor(int i)
    {
        mPaintSelected.setColor(i);
        invalidate();
    }

    public void setStrokeWidth(float f)
    {
        mPaintSelected.setStrokeWidth(f);
        mPaintUnselected.setStrokeWidth(f);
        invalidate();
    }

    public void setUnselectedColor(int i)
    {
        mPaintUnselected.setColor(i);
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
    private float mGapWidth;
    private boolean mIsDragging;
    private float mLastMotionX;
    private float mLineWidth;
    private android.support.v4.view.ViewPager.OnPageChangeListener mListener;
    private final Paint mPaintSelected;
    private final Paint mPaintUnselected;
    private int mTouchSlop;
    private ViewPager mViewPager;
}
