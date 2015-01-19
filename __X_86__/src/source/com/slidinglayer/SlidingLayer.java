// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.slidinglayer;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.support.v4.view.*;
import android.util.*;
import android.view.*;
import android.view.animation.Interpolator;
import android.widget.FrameLayout;
import android.widget.Scroller;
import java.lang.reflect.Method;
import java.util.Random;

public class SlidingLayer extends FrameLayout
{
    public static interface OnInteractListener
    {

        public abstract void onClose();

        public abstract void onClosed();

        public abstract void onOpen();

        public abstract void onOpened();
    }


    public SlidingLayer(Context context)
    {
        this(context, null);
    }

    public SlidingLayer(Context context, AttributeSet attributeset)
    {
        this(context, attributeset, 0);
    }

    public SlidingLayer(Context context, AttributeSet attributeset, int i)
    {
        super(context, attributeset, i);
        mActivePointerId = -1;
        mScreenSide = 0;
        closeOnTapEnabled = true;
        openOnTapEnabled = true;
        mEnabled = true;
        mSlidingFromShadowEnabled = true;
        mLastX = -1F;
        mLastY = -1F;
        mInitialX = -1F;
        mInitialY = -1F;
        mLastTouchAllowed = false;
        TypedArray typedarray = context.obtainStyledAttributes(attributeset, R.styleable.SlidingLayer);
        setStickTo(typedarray.getInt(5, 0));
        int j = typedarray.getResourceId(0, -1);
        if(j != -1)
            setShadowDrawable(j);
        setShadowWidth((int)typedarray.getDimension(1, 0.0F));
        closeOnTapEnabled = typedarray.getBoolean(2, true);
        openOnTapEnabled = typedarray.getBoolean(3, true);
        setOffsetWidth(typedarray.getDimensionPixelOffset(4, 0));
        typedarray.recycle();
        init();
    }

    private boolean allowDragingX(float f, float f1)
    {
        boolean flag = true;
        if((!mIsOpen || (float)getLeft() > f1) && (float)getRight() < f1) goto _L2; else goto _L1
_L1:
        mScreenSide;
        JVM INSTR tableswitch -3 -1: default 60
    //                   -3 132
    //                   -2 124
    //                   -1 116;
           goto _L2 _L3 _L4 _L5
_L2:
        if(mIsOpen || mOffsetWidth <= 0 || f <= 0.0F) goto _L7; else goto _L6
_L6:
        mScreenSide;
        JVM INSTR tableswitch -3 -1: default 112
    //                   -3 181
    //                   -2 140
    //                   -1 158;
           goto _L7 _L8 _L9 _L10
_L7:
        flag = false;
_L12:
        return flag;
_L5:
        if(f <= 0.0F)
            return false;
        continue; /* Loop/switch isn't completed */
_L4:
        if(f >= 0.0F)
            return false;
        continue; /* Loop/switch isn't completed */
_L3:
        if(f == 0.0F)
            return false;
        continue; /* Loop/switch isn't completed */
_L9:
        if(f1 > (float)mOffsetWidth || f <= 0.0F)
            return false;
        continue; /* Loop/switch isn't completed */
_L10:
        if(f1 < (float)(getWidth() - mOffsetWidth) || f >= 0.0F)
            return false;
        continue; /* Loop/switch isn't completed */
_L8:
        if(f == 0.0F)
            return false;
        if(true) goto _L12; else goto _L11
_L11:
    }

    private boolean allowDragingY(float f, float f1)
    {
        boolean flag = true;
        if((!mIsOpen || (float)getTop() > f1) && (float)getBottom() < f1) goto _L2; else goto _L1
_L1:
        mScreenSide;
        JVM INSTR tableswitch -5 -3: default 60
    //                   -5 116
    //                   -4 131
    //                   -3 146;
           goto _L2 _L3 _L4 _L5
_L2:
        if(mIsOpen || mOffsetWidth <= 0 || f <= 0.0F) goto _L7; else goto _L6
_L6:
        mScreenSide;
        JVM INSTR tableswitch -5 -3: default 112
    //                   -5 179
    //                   -4 161
    //                   -3 202;
           goto _L7 _L8 _L9 _L10
_L7:
        flag = false;
_L12:
        return flag;
_L3:
        if(!mIsOpen || f <= 0.0F)
            return false;
        continue; /* Loop/switch isn't completed */
_L4:
        if(!mIsOpen || f >= 0.0F)
            return false;
        continue; /* Loop/switch isn't completed */
_L5:
        if(!mIsOpen || f == 0.0F)
            return false;
        continue; /* Loop/switch isn't completed */
_L9:
        if(f1 > (float)mOffsetWidth || f <= 0.0F)
            return false;
        continue; /* Loop/switch isn't completed */
_L8:
        if(f1 < (float)(getHeight() - mOffsetWidth) || f >= 0.0F)
            return false;
        continue; /* Loop/switch isn't completed */
_L10:
        if(f == 0.0F)
            return false;
        if(true) goto _L12; else goto _L11
_L11:
    }

    private boolean allowSlidingFromHereX(MotionEvent motionevent, float f)
    {
        boolean flag = true;
        mScreenSide;
        JVM INSTR tableswitch -3 -1: default 32
    //                   -3 36
    //                   -2 36
    //                   -1 36;
           goto _L1 _L2 _L2 _L2
_L1:
        flag = false;
_L4:
        return flag;
_L2:
        if(mIsOpen) goto _L4; else goto _L3
_L3:
        if(mIsOpen || mOffsetWidth <= 0) goto _L1; else goto _L5
_L5:
        mScreenSide;
        JVM INSTR tableswitch -2 -1: default 84
    //                   -2 87
    //                   -1 99;
           goto _L1 _L6 _L7
_L7:
        continue; /* Loop/switch isn't completed */
_L6:
        if(f <= (float)mOffsetWidth) goto _L4; else goto _L8
_L8:
        return false;
        if(f >= (float)(getWidth() - mOffsetWidth)) goto _L4; else goto _L9
_L9:
        return false;
    }

    private boolean allowSlidingFromHereY(MotionEvent motionevent, float f)
    {
        boolean flag = true;
        mScreenSide;
        JVM INSTR tableswitch -5 -3: default 32
    //                   -5 36
    //                   -4 36
    //                   -3 36;
           goto _L1 _L2 _L2 _L2
_L1:
        flag = false;
_L4:
        return flag;
_L2:
        if(mIsOpen) goto _L4; else goto _L3
_L3:
        if(mIsOpen || mOffsetWidth <= 0) goto _L1; else goto _L5
_L5:
        mScreenSide;
        JVM INSTR tableswitch -5 -4: default 84
    //                   -5 87
    //                   -4 104;
           goto _L1 _L6 _L7
_L7:
        continue; /* Loop/switch isn't completed */
_L6:
        if(f >= (float)(getHeight() - mOffsetWidth)) goto _L4; else goto _L8
_L8:
        return false;
        if(f <= (float)mOffsetWidth) goto _L4; else goto _L9
_L9:
        return false;
    }

    private void closeLayer(boolean flag, boolean flag1)
    {
        switchLayer(false, flag, flag1, 0, 0);
    }

    private void completeScroll()
    {
        if(!mScrolling) goto _L2; else goto _L1
_L1:
        setDrawingCacheEnabled(false);
        mScroller.abortAnimation();
        int i = getScrollX();
        int j = getScrollY();
        int k = mScroller.getCurrX();
        int l = mScroller.getCurrY();
        if(i != k || j != l)
            scrollTo(k, l);
        if(!mIsOpen) goto _L4; else goto _L3
_L3:
        if(mOnInteractListener != null)
            mOnInteractListener.onOpened();
_L2:
        mScrolling = false;
        return;
_L4:
        if(mOnInteractListener != null)
            mOnInteractListener.onClosed();
        if(true) goto _L2; else goto _L5
_L5:
    }

    private boolean determineNextStateOpened(boolean flag, float f, float f1, int i, int j, int k, int l)
    {
        mScreenSide;
        JVM INSTR tableswitch -5 -1: default 40
    //                   -5 118
    //                   -4 118
    //                   -3 136
    //                   -2 127
    //                   -1 127;
           goto _L1 _L2 _L2 _L3 _L4 _L4
_L1:
        boolean flag1;
        boolean flag2;
        flag1 = false;
        flag2 = false;
_L6:
        boolean flag9;
label0:
        {
            if(!flag2 || Math.abs(k) <= mFlingDistance || Math.abs(i) <= mMinimumVelocity)
                break; /* Loop/switch isn't completed */
            if(mScreenSide != -1 || i > 0)
            {
                int i3 = mScreenSide;
                flag9 = false;
                if(i3 != -2)
                    break label0;
                flag9 = false;
                if(i <= 0)
                    break label0;
            }
            flag9 = true;
        }
        return flag9;
_L2:
        flag1 = true;
        flag2 = false;
        continue; /* Loop/switch isn't completed */
_L4:
        flag2 = true;
        flag1 = false;
        continue; /* Loop/switch isn't completed */
_L3:
        flag1 = true;
        flag2 = flag1;
        if(true) goto _L6; else goto _L5
_L5:
        if(flag1 && Math.abs(l) > mFlingDistance && Math.abs(j) > mMinimumVelocity)
        {
            boolean flag8;
label1:
            {
                if(mScreenSide != -5 || j > 0)
                {
                    int l2 = mScreenSide;
                    flag8 = false;
                    if(l2 != -4)
                        break label1;
                    flag8 = false;
                    if(j <= 0)
                        break label1;
                }
                flag8 = true;
            }
            return flag8;
        } else
        {
            int i1 = getWidth();
            int j1 = getHeight();
            int k1;
            boolean flag3;
            switch(mScreenSide)
            {
            default:
                return true;

            case -1: 
                int k2 = f != (float)(-i1 / 2);
                boolean flag7 = false;
                if(k2 > 0)
                    flag7 = true;
                return flag7;

            case -5: 
                int j2 = f1 != (float)(-j1 / 2);
                boolean flag6 = false;
                if(j2 > 0)
                    flag6 = true;
                return flag6;

            case -2: 
                int i2 = f != (float)(i1 / 2);
                boolean flag5 = false;
                if(i2 < 0)
                    flag5 = true;
                return flag5;

            case -4: 
                int l1 = f1 != (float)(j1 / 2);
                boolean flag4 = false;
                if(l1 < 0)
                    flag4 = true;
                return flag4;

            case -3: 
                k1 = Math.abs(f) != (float)(i1 / 2);
                flag3 = false;
                break;
            }
            if(k1 < 0)
                flag3 = true;
            return flag3;
        }
    }

    private void endDrag()
    {
        mIsDragging = false;
        mIsUnableToDrag = false;
        mLastTouchAllowed = false;
        if(mVelocityTracker != null)
        {
            mVelocityTracker.recycle();
            mVelocityTracker = null;
        }
    }

    private int getDestScrollX()
    {
        return getDestScrollX(0);
    }

    private int getDestScrollX(int i)
    {
        if(!mIsOpen)
        {
            if(mScreenSide == -1)
                return -getWidth() + mOffsetWidth;
            if(mScreenSide == -2)
                return getWidth() - mOffsetWidth;
            if(mScreenSide != -4 && mScreenSide != -5)
            {
                if(i == 0)
                    if((new Random()).nextBoolean())
                        return -getWidth();
                    else
                        return getWidth();
                if(i > 0)
                    return -getWidth();
                else
                    return getWidth();
            }
        }
        return 0;
    }

    private int getDestScrollY()
    {
        return getDestScrollY(0);
    }

    private int getDestScrollY(int i)
    {
        if(!mIsOpen)
        {
            if(mScreenSide == -5)
                return -getHeight() + mOffsetWidth;
            if(mScreenSide == -4)
                return getHeight() - mOffsetWidth;
            if(mScreenSide != -2 && mScreenSide != -1)
            {
                if(i == 0)
                    if((new Random()).nextBoolean())
                        return -getHeight();
                    else
                        return getHeight();
                if(i > 0)
                    return -getHeight();
                else
                    return getHeight();
            }
        }
        return 0;
    }

    private int getScreenSideAuto(int i, int j)
    {
        boolean flag = true;
        if(mScreenSide == 0)
        {
            Display display = ((WindowManager)getContext().getSystemService("window")).getDefaultDisplay();
            int k;
            boolean flag1;
            try
            {
                Class aclass[] = {
                    android/graphics/Point
                };
                Point point = new Point();
                android/view/Display.getMethod("getSize", aclass).invoke(display, new Object[] {
                    point
                });
                k = point.x;
            }
            catch(Exception exception)
            {
                k = display.getWidth();
            }
            if(i == 0)
                flag1 = flag;
            else
                flag1 = false;
            if(j != k)
                flag = false;
            if(flag1 == flag && getLayoutParams().width == -1)
                return -3;
            return !flag1 ? -1 : -2;
        } else
        {
            return mScreenSide;
        }
    }

    private void init()
    {
        setWillNotDraw(false);
        setDescendantFocusability(0x40000);
        setFocusable(true);
        Context context = getContext();
        mScroller = new Scroller(context, sMenuInterpolator);
        ViewConfiguration viewconfiguration = ViewConfiguration.get(context);
        mTouchSlop = ViewConfigurationCompat.getScaledPagingTouchSlop(viewconfiguration);
        mMinimumVelocity = viewconfiguration.getScaledMinimumFlingVelocity();
        mMaximumVelocity = viewconfiguration.getScaledMaximumFlingVelocity();
        mFlingDistance = (int)(25F * context.getResources().getDisplayMetrics().density);
    }

    private void onSecondaryPointerUp(MotionEvent motionevent)
    {
        int i = MotionEventCompat.getActionIndex(motionevent);
        if(MotionEventCompat.getPointerId(motionevent, i) == mActivePointerId)
        {
            int j;
            if(i == 0)
                j = 1;
            else
                j = 0;
            mLastX = MotionEventCompat.getX(motionevent, j);
            mActivePointerId = MotionEventCompat.getPointerId(motionevent, j);
            if(mVelocityTracker != null)
                mVelocityTracker.clear();
        }
    }

    private void openLayer(boolean flag, boolean flag1)
    {
        switchLayer(true, flag, flag1, 0, 0);
    }

    private void switchLayer(boolean flag, boolean flag1, boolean flag2)
    {
        switchLayer(flag, flag1, flag2, 0, 0);
    }

    private void switchLayer(boolean flag, boolean flag1, boolean flag2, int i, int j)
    {
        if(!flag2 && flag == mIsOpen)
        {
            setDrawingCacheEnabled(false);
            return;
        }
        int k;
        int l;
        if(flag)
        {
            if(mOnInteractListener != null)
                mOnInteractListener.onOpen();
        } else
        if(mOnInteractListener != null)
            mOnInteractListener.onClose();
        mIsOpen = flag;
        k = getDestScrollX(i);
        l = getDestScrollY(j);
        if(flag1)
        {
            smoothScrollTo(k, l, Math.max(i, j));
            return;
        } else
        {
            completeScroll();
            scrollTo(k, l);
            return;
        }
    }

    public void closeLayer(boolean flag)
    {
        closeLayer(flag, false);
    }

    public void computeScroll()
    {
        if(!mScroller.isFinished() && mScroller.computeScrollOffset())
        {
            int i = getScrollX();
            int j = getScrollY();
            int k = mScroller.getCurrX();
            int l = mScroller.getCurrY();
            if(i != k || j != l)
                scrollTo(k, l);
            invalidate(i + getLeft(), j + getTop(), getRight() - i, getBottom() - j);
            return;
        } else
        {
            completeScroll();
            return;
        }
    }

    protected void dispatchDraw(Canvas canvas)
    {
        super.dispatchDraw(canvas);
        if(mShadowWidth > 0 && mShadowDrawable != null)
        {
            if(mScreenSide == -1)
                mShadowDrawable.setBounds(0, 0, mShadowWidth, getHeight());
            if(mScreenSide == -4)
                mShadowDrawable.setBounds(0, getHeight() - mShadowWidth, getWidth(), getHeight());
            if(mScreenSide == -2)
                mShadowDrawable.setBounds(getWidth() - mShadowWidth, 0, getWidth(), getHeight());
            if(mScreenSide == -5)
                mShadowDrawable.setBounds(0, 0, getWidth(), mShadowWidth);
            mShadowDrawable.draw(canvas);
        }
    }

    float distanceInfluenceForSnapDuration(float f)
    {
        return FloatMath.sin((float)(0.4712389167638204D * (double)(f - 0.5F)));
    }

    protected void drawableStateChanged()
    {
        super.drawableStateChanged();
        Drawable drawable = mShadowDrawable;
        if(drawable != null && drawable.isStateful())
            drawable.setState(getDrawableState());
    }

    public int getContentLeft()
    {
        return getLeft() + getPaddingLeft();
    }

    public int getOffsetWidth()
    {
        return mOffsetWidth;
    }

    public int getShadowWidth()
    {
        return mShadowWidth;
    }

    public boolean isOpened()
    {
        return mIsOpen;
    }

    public boolean isSlidingEnabled()
    {
        return mEnabled;
    }

    public boolean isSlidingFromShadowEnabled()
    {
        return mSlidingFromShadowEnabled;
    }

    protected void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);
    }

    public boolean onInterceptTouchEvent(MotionEvent motionevent)
    {
        int i;
        if(!mEnabled)
            return false;
        i = 0xff & motionevent.getAction();
        if(i == 3 || i == 1)
        {
            mIsDragging = false;
            mIsUnableToDrag = false;
            mActivePointerId = -1;
            if(mVelocityTracker != null)
            {
                mVelocityTracker.recycle();
                mVelocityTracker = null;
            }
            return false;
        }
        if(i != 0)
        {
            if(mIsDragging)
                return true;
            if(mIsUnableToDrag)
                return false;
        }
        i;
        JVM INSTR lookupswitch 3: default 120
    //                   0: 343
    //                   2: 154
    //                   6: 485;
           goto _L1 _L2 _L3 _L4
_L1:
        if(!mIsDragging)
        {
            if(mVelocityTracker == null)
                mVelocityTracker = VelocityTracker.obtain();
            mVelocityTracker.addMovement(motionevent);
        }
        return mIsDragging;
_L3:
        int l = mActivePointerId;
        if(l != -1)
        {
            int i1 = MotionEventCompat.findPointerIndex(motionevent, l);
            if(i1 == -1)
            {
                mActivePointerId = -1;
            } else
            {
                float f2 = MotionEventCompat.getX(motionevent, i1);
                float f3 = f2 - mLastX;
                float f4 = Math.abs(f3);
                float f5 = MotionEventCompat.getY(motionevent, i1);
                float f6 = f5 - mLastY;
                float f7 = Math.abs(f5 - mLastY);
                if(f4 > (float)mTouchSlop && f4 > f7 && allowDragingX(f3, mInitialX))
                {
                    mIsDragging = true;
                    mLastX = f2;
                    setDrawingCacheEnabled(true);
                } else
                if(f7 > (float)mTouchSlop && f7 > f4 && allowDragingY(f6, mInitialY))
                {
                    mIsDragging = true;
                    mLastY = f5;
                    setDrawingCacheEnabled(true);
                }
            }
        }
        continue; /* Loop/switch isn't completed */
_L2:
        int j = motionevent.getAction();
        int k;
        float f;
        float f1;
        if(android.os.Build.VERSION.SDK_INT >= 8)
            k = 65280;
        else
            k = 65280;
        mActivePointerId = k & j;
        f = MotionEventCompat.getX(motionevent, mActivePointerId);
        mInitialX = f;
        mLastX = f;
        f1 = MotionEventCompat.getY(motionevent, mActivePointerId);
        mInitialY = f1;
        mLastY = f1;
        if(allowSlidingFromHereX(motionevent, mInitialX))
        {
            mIsDragging = false;
            mIsUnableToDrag = false;
            return super.onInterceptTouchEvent(motionevent);
        }
        if(allowSlidingFromHereY(motionevent, mInitialY))
        {
            mIsDragging = false;
            mIsUnableToDrag = false;
            return super.onInterceptTouchEvent(motionevent);
        }
        mIsUnableToDrag = true;
        continue; /* Loop/switch isn't completed */
_L4:
        onSecondaryPointerUp(motionevent);
        if(true) goto _L1; else goto _L5
_L5:
    }

    protected void onLayout(boolean flag, int i, int j, int k, int l)
    {
        int i1;
        i1 = mScreenSide;
        if(mScreenSide == 0)
            i1 = getScreenSideAuto(i, k);
        if(i1 == mScreenSide && !mForceLayout) goto _L2; else goto _L1
_L1:
        mForceLayout = false;
        mScreenSide = i1;
        closeLayer(false, true);
        if(mScreenSide != -1) goto _L4; else goto _L3
_L3:
        setPadding(getPaddingLeft() + mShadowWidth, getPaddingTop(), getPaddingRight(), getPaddingBottom());
_L2:
        super.onLayout(flag, i, j, k, l);
        return;
_L4:
        if(mScreenSide == -5)
            setPadding(getPaddingLeft(), getPaddingTop() + mShadowWidth, getPaddingRight(), getPaddingBottom());
        else
        if(mScreenSide == -2)
            setPadding(getPaddingLeft(), getPaddingTop(), getPaddingRight() + mShadowWidth, getPaddingBottom());
        else
        if(mScreenSide == -4)
            setPadding(getPaddingLeft(), getPaddingTop(), getPaddingRight(), getPaddingBottom() + mShadowWidth);
        else
        if(mScreenSide == -3)
            setPadding(getPaddingLeft() + mShadowWidth, getPaddingTop(), getPaddingRight() + mShadowWidth, getPaddingBottom());
        if(true) goto _L2; else goto _L5
_L5:
    }

    protected void onMeasure(int i, int j)
    {
        int k = getDefaultSize(0, i);
        int l = getDefaultSize(0, j);
        setMeasuredDimension(k, l);
        super.onMeasure(getChildMeasureSpec(i, 0, k), getChildMeasureSpec(j, 0, l));
    }

    protected void onSizeChanged(int i, int j, int k, int l)
    {
        super.onSizeChanged(i, j, k, l);
        if(i != k)
        {
            completeScroll();
            scrollTo(getDestScrollX(), getDestScrollY());
        }
    }

    public boolean onTouchEvent(MotionEvent motionevent)
    {
        if(!mEnabled || !mIsDragging && !mLastTouchAllowed && !allowSlidingFromHereX(motionevent, mInitialX) && !allowSlidingFromHereY(motionevent, mInitialY))
            return false;
        int i = motionevent.getAction();
        if(i == 1 || i == 3 || i == 4)
            mLastTouchAllowed = false;
        else
            mLastTouchAllowed = true;
        if(mVelocityTracker == null)
            mVelocityTracker = VelocityTracker.obtain();
        mVelocityTracker.addMovement(motionevent);
        i & 0xff;
        JVM INSTR tableswitch 0 6: default 140
    //                   0 163
    //                   1 726
    //                   2 215
    //                   3 922
    //                   4 140
    //                   5 951
    //                   6 986;
           goto _L1 _L2 _L3 _L4 _L5 _L1 _L6 _L7
_L1:
        if(mActivePointerId == -1)
            mLastTouchAllowed = false;
        return true;
_L2:
        completeScroll();
        float f18 = motionevent.getX();
        mInitialX = f18;
        mLastX = f18;
        float f19 = motionevent.getY();
        mInitialY = f19;
        mLastY = f19;
        mActivePointerId = MotionEventCompat.getPointerId(motionevent, 0);
        continue; /* Loop/switch isn't completed */
_L4:
        if(mIsDragging) goto _L9; else goto _L8
_L8:
        float f14;
        float f15;
        float f16;
        float f17;
        int k2 = MotionEventCompat.findPointerIndex(motionevent, mActivePointerId);
        if(k2 == -1)
        {
            mActivePointerId = -1;
            continue; /* Loop/switch isn't completed */
        }
        f14 = MotionEventCompat.getX(motionevent, k2);
        f15 = Math.abs(f14 - mLastX);
        f16 = MotionEventCompat.getY(motionevent, k2);
        f17 = Math.abs(f16 - mLastY);
        if(f15 <= (float)mTouchSlop || f15 <= f17) goto _L11; else goto _L10
_L10:
        mIsDragging = true;
        mLastX = f14;
        setDrawingCacheEnabled(true);
_L9:
        int j2;
        if(!mIsDragging)
            continue; /* Loop/switch isn't completed */
        j2 = MotionEventCompat.findPointerIndex(motionevent, mActivePointerId);
        if(j2 == -1)
        {
            mActivePointerId = -1;
            continue; /* Loop/switch isn't completed */
        }
        break; /* Loop/switch isn't completed */
_L11:
        if(f17 > (float)mTouchSlop && f17 > f15)
        {
            mIsDragging = true;
            mLastY = f16;
            setDrawingCacheEnabled(true);
        }
        if(true) goto _L9; else goto _L12
_L12:
        float f8;
        float f9;
        float f2 = MotionEventCompat.getX(motionevent, j2);
        float f3 = MotionEventCompat.getY(motionevent, j2);
        float f4 = mLastX - f2;
        float f5 = mLastY - f3;
        mLastX = f2;
        mLastY = f3;
        float f6 = getScrollX();
        float f7 = getScrollY();
        f8 = f6 + f4;
        f9 = f7 + f5;
        mScreenSide;
        JVM INSTR tableswitch -5 -1: default 504
    //                   -5 676
    //                   -4 657
    //                   -3 604
    //                   -2 585
    //                   -1 637;
           goto _L13 _L14 _L15 _L16 _L17 _L18
_L14:
        break MISSING_BLOCK_LABEL_676;
_L13:
        float f10;
        float f11;
        float f12;
        float f13;
        f11 = 0.0F;
        f12 = 0.0F;
        f10 = 0.0F;
        f13 = 0.0F;
_L19:
        if(f8 > f11)
            f8 = f11;
        else
        if(f8 < f12)
            f8 = f12;
        if(f9 > f13)
            f9 = f13;
        else
        if(f9 < f10)
            f9 = f10;
        mLastX = mLastX + (f8 - (float)(int)f8);
        mLastY = mLastY + (f9 - (float)(int)f9);
        scrollTo((int)f8, (int)f9);
        continue; /* Loop/switch isn't completed */
_L17:
        f11 = getWidth();
        f10 = 0.0F;
        f12 = 0.0F;
        f13 = 0.0F;
          goto _L19
_L16:
        f13 = getHeight();
        f10 = -getHeight();
        f11 = getWidth();
        f12 = -getWidth();
          goto _L19
_L18:
        f12 = -getWidth();
        f10 = 0.0F;
        f11 = 0.0F;
        f13 = 0.0F;
          goto _L19
_L15:
        f13 = getHeight();
        f10 = 0.0F;
        f11 = 0.0F;
        f12 = 0.0F;
          goto _L19
        f10 = -getHeight();
        f11 = 0.0F;
        f12 = 0.0F;
        f13 = 0.0F;
          goto _L19
_L3:
        if(mIsDragging)
        {
            VelocityTracker velocitytracker = mVelocityTracker;
            velocitytracker.computeCurrentVelocity(1000, mMaximumVelocity);
            int k = (int)VelocityTrackerCompat.getXVelocity(velocitytracker, mActivePointerId);
            int l = (int)VelocityTrackerCompat.getYVelocity(velocitytracker, mActivePointerId);
            int i1 = getScrollX();
            int j1 = getScrollY();
            int k1 = MotionEventCompat.findPointerIndex(motionevent, mActivePointerId);
            float f = MotionEventCompat.getX(motionevent, k1);
            float f1 = MotionEventCompat.getY(motionevent, k1);
            int l1 = (int)(f - mInitialX);
            int i2 = (int)(f1 - mInitialY);
            switchLayer(determineNextStateOpened(mIsOpen, i1, j1, k, l, l1, i2), true, true, k, l);
            mActivePointerId = -1;
            endDrag();
        } else
        if(mIsOpen && closeOnTapEnabled)
            closeLayer(true);
        else
        if(!mIsOpen && openOnTapEnabled)
            openLayer(true);
        continue; /* Loop/switch isn't completed */
_L5:
        if(mIsDragging)
        {
            switchLayer(mIsOpen, true, true);
            mActivePointerId = -1;
            endDrag();
        }
        continue; /* Loop/switch isn't completed */
_L6:
        int j = MotionEventCompat.getActionIndex(motionevent);
        mLastX = MotionEventCompat.getX(motionevent, j);
        mLastY = MotionEventCompat.getY(motionevent, j);
        mActivePointerId = MotionEventCompat.getPointerId(motionevent, j);
        continue; /* Loop/switch isn't completed */
_L7:
        onSecondaryPointerUp(motionevent);
        mLastX = MotionEventCompat.getX(motionevent, MotionEventCompat.findPointerIndex(motionevent, mActivePointerId));
        mLastY = MotionEventCompat.getY(motionevent, MotionEventCompat.findPointerIndex(motionevent, mActivePointerId));
        if(true) goto _L1; else goto _L20
_L20:
    }

    public void openLayer(boolean flag)
    {
        openLayer(flag, false);
    }

    public void setCloseOnTapEnabled(boolean flag)
    {
        closeOnTapEnabled = flag;
    }

    public void setDrawingCacheEnabled(boolean flag)
    {
        super.setDrawingCacheEnabled(false);
    }

    public void setOffsetWidth(int i)
    {
        mOffsetWidth = i;
        invalidate(getLeft(), getTop(), getRight(), getBottom());
    }

    public void setOnInteractListener(OnInteractListener oninteractlistener)
    {
        mOnInteractListener = oninteractlistener;
    }

    public void setOpenOnTapEnabled(boolean flag)
    {
        openOnTapEnabled = flag;
    }

    public void setShadowDrawable(int i)
    {
        setShadowDrawable(getContext().getResources().getDrawable(i));
    }

    public void setShadowDrawable(Drawable drawable)
    {
        mShadowDrawable = drawable;
        refreshDrawableState();
        setWillNotDraw(false);
        invalidate(getLeft(), getTop(), getRight(), getBottom());
    }

    public void setShadowWidth(int i)
    {
        mShadowWidth = i;
        invalidate(getLeft(), getTop(), getRight(), getBottom());
    }

    public void setShadowWidthRes(int i)
    {
        setShadowWidth((int)getResources().getDimension(i));
    }

    public void setSlidingEnabled(boolean flag)
    {
        mEnabled = flag;
    }

    public void setSlidingFromShadowEnabled(boolean flag)
    {
        mSlidingFromShadowEnabled = flag;
    }

    public void setStickTo(int i)
    {
        if(i != 0)
            mForceLayout = true;
        mScreenSide = i;
        closeLayer(false, true);
    }

    void smoothScrollTo(int i, int j)
    {
        smoothScrollTo(i, j, 0);
    }

    void smoothScrollTo(int i, int j, int k)
    {
        if(getChildCount() != 0) goto _L2; else goto _L1
_L1:
        setDrawingCacheEnabled(false);
_L4:
        return;
_L2:
        int l;
        int i1;
        int j1;
        int k1;
        l = getScrollX();
        i1 = getScrollY();
        j1 = i - l;
        k1 = j - i1;
        if(j1 != 0 || k1 != 0)
            break MISSING_BLOCK_LABEL_92;
        completeScroll();
        if(!mIsOpen)
            continue; /* Loop/switch isn't completed */
        if(mOnInteractListener == null) goto _L4; else goto _L3
_L3:
        mOnInteractListener.onOpened();
        return;
        if(mOnInteractListener == null) goto _L4; else goto _L5
_L5:
        mOnInteractListener.onClosed();
        return;
        setDrawingCacheEnabled(true);
        mScrolling = true;
        int l1 = getWidth();
        int i2 = l1 / 2;
        float f = Math.min(1.0F, (1.0F * (float)Math.abs(j1)) / (float)l1);
        float f1 = (float)i2 + (float)i2 * distanceInfluenceForSnapDuration(f);
        int j2 = Math.abs(k);
        int k2;
        int l2;
        if(j2 > 0)
            k2 = 4 * Math.round(1000F * Math.abs(f1 / (float)j2));
        else
            k2 = 600;
        l2 = Math.min(k2, 600);
        mScroller.startScroll(l, i1, j1, k1, l2);
        invalidate();
        return;
    }

    protected boolean verifyDrawable(Drawable drawable)
    {
        return super.verifyDrawable(drawable) || drawable == mShadowDrawable;
    }

    private static final int INVALID_POINTER = -1;
    private static final int MAX_SCROLLING_DURATION = 600;
    private static final int MIN_DISTANCE_FOR_FLING = 25;
    public static final int STICK_TO_AUTO = 0;
    public static final int STICK_TO_BOTTOM = -5;
    public static final int STICK_TO_LEFT = -2;
    public static final int STICK_TO_MIDDLE = -3;
    public static final int STICK_TO_RIGHT = -1;
    public static final int STICK_TO_TOP = -4;
    private static final Interpolator sMenuInterpolator = new Interpolator() {

        public float getInterpolation(float f)
        {
            return 1.0F + (float)Math.pow(f - 1.0F, 5D);
        }

    }
;
    private boolean closeOnTapEnabled;
    protected int mActivePointerId;
    private boolean mDrawingCacheEnabled;
    private boolean mEnabled;
    private int mFlingDistance;
    private boolean mForceLayout;
    private float mInitialX;
    private float mInitialY;
    private boolean mIsDragging;
    private boolean mIsOpen;
    private boolean mIsUnableToDrag;
    private boolean mLastTouchAllowed;
    private float mLastX;
    private float mLastY;
    protected int mMaximumVelocity;
    private int mMinimumVelocity;
    private int mOffsetWidth;
    private OnInteractListener mOnInteractListener;
    private int mScreenSide;
    private Scroller mScroller;
    private boolean mScrolling;
    private Drawable mShadowDrawable;
    private int mShadowWidth;
    private boolean mSlidingFromShadowEnabled;
    private int mTouchSlop;
    protected VelocityTracker mVelocityTracker;
    private boolean openOnTapEnabled;

}
