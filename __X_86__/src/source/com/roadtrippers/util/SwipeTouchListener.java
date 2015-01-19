// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.roadtrippers.util;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Rect;
import android.view.*;
import android.widget.AbsListView;
import android.widget.ListView;
import com.nineoldandroids.animation.*;
import com.nineoldandroids.view.ViewHelper;

public class SwipeTouchListener
    implements android.view.View.OnTouchListener
{
    public static interface OnSwipeListener
    {

        public abstract void onSwipe(ListView listview, int i);
    }


    public SwipeTouchListener(ListView listview, int i, int j, OnSwipeListener onswipelistener)
    {
        mViewWidth = 1;
        ViewConfiguration viewconfiguration = ViewConfiguration.get(listview.getContext());
        mSlop = viewconfiguration.getScaledTouchSlop();
        mMinFlingVelocity = viewconfiguration.getScaledMinimumFlingVelocity();
        mMaxFlingVelocity = viewconfiguration.getScaledMaximumFlingVelocity();
        mAnimationTime = listview.getContext().getResources().getInteger(0x10e0000);
        mListView = listview;
        frontRes = i;
        backRes = j;
        onSwipeListener = onswipelistener;
    }

    private void close(View view, View view1)
    {
        AnimatorSet animatorset = new AnimatorSet();
        Animator aanimator[] = new Animator[2];
        aanimator[0] = ObjectAnimator.ofFloat(view, "translationX", new float[] {
            0.0F
        });
        aanimator[1] = ObjectAnimator.ofFloat(view1, "alpha", new float[] {
            0.0F
        });
        animatorset.playTogether(aanimator);
        animatorset.setDuration(mAnimationTime);
        animatorset.setStartDelay(100L);
        animatorset.start();
    }

    private void finishFlingThenClose(float f, float f1)
    {
        final View frontView = this.frontView;
        final View backView = this.backView;
        final int position = mDownPosition;
        int i = backView.getWidth();
        AnimatorSet animatorset = new AnimatorSet();
        Animator aanimator[] = new Animator[2];
        float af[] = new float[1];
        af[0] = -i;
        aanimator[0] = ObjectAnimator.ofFloat(frontView, "translationX", af);
        aanimator[1] = ObjectAnimator.ofFloat(backView, "alpha", new float[] {
            1.0F
        });
        animatorset.playTogether(aanimator);
        long l;
        if(f + (float)i >= 0.0F)
            l = (long)((f + (float)i) / f1);
        else
            l = 0L;
        animatorset.setDuration(l);
        animatorset.addListener(new AnimatorListenerAdapter() {

            public void onAnimationEnd(Animator animator)
            {
                showThenClose(frontView, backView, position);
            }

            final SwipeTouchListener this$0;
            final View val$backView;
            final View val$frontView;
            final int val$position;

            
            {
                this$0 = SwipeTouchListener.this;
                frontView = view;
                backView = view1;
                position = i;
                super();
            }
        }
);
        animatorset.start();
    }

    private void showThenClose(View view, View view1, int i)
    {
        onSwipeListener.onSwipe(mListView, i);
        close(view, view1);
    }

    public android.widget.AbsListView.OnScrollListener makeScrollListener()
    {
        return new android.widget.AbsListView.OnScrollListener() {

            public void onScroll(AbsListView abslistview, int i, int j, int k)
            {
            }

            public void onScrollStateChanged(AbsListView abslistview, int i)
            {
                boolean flag = true;
                SwipeTouchListener swipetouchlistener = SwipeTouchListener.this;
                if(i == flag)
                    flag = false;
                swipetouchlistener.setEnabled(flag);
            }

            final SwipeTouchListener this$0;

            
            {
                this$0 = SwipeTouchListener.this;
                super();
            }
        }
;
    }

    public boolean onTouch(View view, MotionEvent motionevent)
    {
        if(mViewWidth < 2)
            mViewWidth = mListView.getWidth();
        motionevent.getActionMasked();
        JVM INSTR tableswitch 0 2: default 48
    //                   0 50
    //                   1 258
    //                   2 502;
           goto _L1 _L2 _L3 _L4
_L1:
        return false;
_L2:
        Rect rect;
        int j;
        int k;
        int l;
        int i1;
        if(mPaused)
            return false;
        rect = new Rect();
        j = mListView.getChildCount();
        int ai[] = new int[2];
        mListView.getLocationOnScreen(ai);
        k = (int)motionevent.getRawX() - ai[0];
        l = (int)motionevent.getRawY() - ai[1];
        i1 = 0;
_L9:
        if(i1 >= j) goto _L6; else goto _L5
_L5:
        View view1;
        view1 = mListView.getChildAt(i1);
        view1.getHitRect(rect);
        if(!rect.contains(k, l)) goto _L8; else goto _L7
_L7:
        mDownView = view1;
_L6:
        if(mDownView != null)
        {
            mDownX = motionevent.getRawX();
            mDownY = motionevent.getRawY();
            mDownPosition = mListView.getPositionForView(mDownView);
            frontView = mDownView.findViewById(frontRes);
            backView = mDownView.findViewById(backRes);
            mVelocityTracker = VelocityTracker.obtain();
            mVelocityTracker.addMovement(motionevent);
        }
        view.onTouchEvent(motionevent);
        return true;
_L8:
        i1++;
          goto _L9
_L3:
        float f1;
        float f2;
        float f3;
        if(mVelocityTracker == null)
            continue; /* Loop/switch isn't completed */
        f1 = motionevent.getRawX() - mDownX;
        mVelocityTracker.addMovement(motionevent);
        mVelocityTracker.computeCurrentVelocity(1000);
        f2 = Math.abs(mVelocityTracker.getXVelocity());
        f3 = Math.abs(mVelocityTracker.getYVelocity());
        if(mSwiping)
        {
            if(f1 > 0.0F || Math.abs(f1 / (float)backView.getWidth()) < 1.0F)
                break; /* Loop/switch isn't completed */
            if(ViewHelper.getTranslationX(frontView) > f1)
                finishFlingThenClose(f1, f2);
            else
                showThenClose(frontView, backView, mDownPosition);
        }
_L11:
        mVelocityTracker = null;
        mDownX = 0.0F;
        mDownY = 0.0F;
        mDownView = null;
        frontView = null;
        backView = null;
        mDownPosition = -1;
        mSwiping = false;
        if(true) goto _L1; else goto _L10
_L10:
        if((float)mMinFlingVelocity <= f2 && f2 <= (float)mMaxFlingVelocity && f3 < f2 && mVelocityTracker.getXVelocity() < 0.0F)
            finishFlingThenClose(f1, f2);
        else
            close(frontView, backView);
          goto _L11
_L4:
        if(mVelocityTracker == null || mPaused) goto _L1; else goto _L12
_L12:
        float f;
        int i;
        mVelocityTracker.addMovement(motionevent);
        f = motionevent.getRawX() - mDownX;
        if(Math.abs(3F * (motionevent.getRawY() - mDownY)) < Math.abs(f) && Math.abs(f) > (float)mSlop)
        {
            mSwiping = true;
            mListView.requestDisallowInterceptTouchEvent(true);
            MotionEvent motionevent1 = MotionEvent.obtain(motionevent);
            motionevent1.setAction(3 | motionevent.getActionIndex() << 8);
            mListView.onTouchEvent(motionevent1);
        }
        i = backView.getWidth();
        if(!mSwiping) goto _L1; else goto _L13
_L13:
        if(f > 0.0F || Math.abs(f / (float)i) > 1.0F) goto _L15; else goto _L14
_L14:
        ViewHelper.setTranslationX(frontView, f);
        ViewHelper.setAlpha(backView, Math.max(0.0F, Math.min(1.0F, Math.abs(f) / (float)i)));
_L17:
        return true;
_L15:
        if(f > 0.0F)
        {
            ViewHelper.setTranslationX(frontView, 0.0F);
            ViewHelper.setAlpha(backView, 0.0F);
        } else
        if(Math.abs(f / (float)i) > 1.0F)
        {
            ViewHelper.setTranslationX(frontView, -i);
            ViewHelper.setAlpha(backView, 1.0F);
        }
        if(true) goto _L17; else goto _L16
_L16:
    }

    public void setEnabled(boolean flag)
    {
        boolean flag1;
        if(!flag)
            flag1 = true;
        else
            flag1 = false;
        mPaused = flag1;
    }

    static final int COEFF_X_TO_Y = 3;
    int backRes;
    View backView;
    int frontRes;
    View frontView;
    private long mAnimationTime;
    private int mDownPosition;
    private View mDownView;
    private float mDownX;
    private float mDownY;
    private ListView mListView;
    private int mMaxFlingVelocity;
    private int mMinFlingVelocity;
    private boolean mPaused;
    private int mSlop;
    private boolean mSwiping;
    private VelocityTracker mVelocityTracker;
    private int mViewWidth;
    OnSwipeListener onSwipeListener;

}
