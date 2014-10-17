// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.roadtrippers.adapters;

import android.graphics.Point;
import android.view.*;
import com.mobeta.android.dslv.DragSortListView;
import com.mobeta.android.dslv.SimpleFloatViewManager;

public class DragSortController extends SimpleFloatViewManager
    implements android.view.View.OnTouchListener, android.view.GestureDetector.OnGestureListener
{

    public DragSortController(DragSortListView dragsortlistview)
    {
        this(dragsortlistview, 0, 0, 1);
    }

    public DragSortController(DragSortListView dragsortlistview, int i, int j, int k)
    {
        this(dragsortlistview, i, j, k, 0);
    }

    public DragSortController(DragSortListView dragsortlistview, int i, int j, int k, int l)
    {
        this(dragsortlistview, i, j, k, l, 0);
    }

    public DragSortController(DragSortListView dragsortlistview, int i, int j, int k, int l, int i1)
    {
        super(dragsortlistview);
        mDragInitMode = 0;
        mSortEnabled = true;
        mRemoveEnabled = false;
        mIsRemoving = false;
        mHitPos = -1;
        mFlingHitPos = -1;
        mClickRemoveHitPos = -1;
        mTempLoc = new int[2];
        mDragging = false;
        mFlingSpeed = 500F;
        mDslv = dragsortlistview;
        mDetector = new GestureDetector(dragsortlistview.getContext(), this);
        mFlingRemoveDetector = new GestureDetector(dragsortlistview.getContext(), mFlingRemoveListener);
        mFlingRemoveDetector.setIsLongpressEnabled(false);
        mTouchSlop = ViewConfiguration.get(dragsortlistview.getContext()).getScaledTouchSlop();
        mDragHandleId = i;
        mClickRemoveId = l;
        mFlingHandleId = i1;
        setRemoveMode(k);
        setDragInitMode(j);
    }

    public int dragHandleHitPosition(MotionEvent motionevent)
    {
        return viewIdHitPosition(motionevent, mDragHandleId);
    }

    public int flingHandleHitPosition(MotionEvent motionevent)
    {
        return viewIdHitPosition(motionevent, mFlingHandleId);
    }

    public int getDragInitMode()
    {
        return mDragInitMode;
    }

    public int getRemoveMode()
    {
        return mRemoveMode;
    }

    public boolean isRemoveEnabled()
    {
        return mRemoveEnabled;
    }

    public boolean isSortEnabled()
    {
        return mSortEnabled;
    }

    public boolean onDown(MotionEvent motionevent)
    {
        if(mRemoveEnabled && mRemoveMode == 0)
            mClickRemoveHitPos = viewIdHitPosition(motionevent, mClickRemoveId);
        mHitPos = startDragPosition(motionevent);
        if(mHitPos != -1 && mDragInitMode == 0)
            startDrag(mHitPos, (int)motionevent.getX() - mItemX, (int)motionevent.getY() - mItemY);
        mIsRemoving = false;
        mCanDrag = true;
        mPositionX = 0;
        mFlingHitPos = startFlingPosition(motionevent);
        return true;
    }

    public void onDragFloatView(View view, Point point, Point point1)
    {
        if(mRemoveEnabled && mIsRemoving)
            mPositionX = point.x;
    }

    public final boolean onFling(MotionEvent motionevent, MotionEvent motionevent1, float f, float f1)
    {
        return false;
    }

    public void onLongPress(MotionEvent motionevent)
    {
        if(mHitPos != -1 && mDragInitMode == 2)
        {
            mDslv.performHapticFeedback(0);
            startDrag(mHitPos, mCurrX - mItemX, mCurrY - mItemY);
        }
    }

    public boolean onScroll(MotionEvent motionevent, MotionEvent motionevent1, float f, float f1)
    {
        if(motionevent != null && motionevent1 != null) goto _L2; else goto _L1
_L1:
        return false;
_L2:
        int i;
        int j;
        int k;
        int l;
        int i1;
        int j1;
        i = (int)motionevent.getX();
        j = (int)motionevent.getY();
        k = (int)motionevent1.getX();
        l = (int)motionevent1.getY();
        i1 = k - mItemX;
        j1 = l - mItemY;
        if(mCanDrag && !mDragging && (mHitPos != -1 || mFlingHitPos != -1))
        {
            if(mHitPos == -1)
                continue; /* Loop/switch isn't completed */
            if(mDragInitMode == 1 && Math.abs(l - j) > mTouchSlop && mSortEnabled)
            {
                startDrag(mHitPos, i1, j1);
                return false;
            }
            if(mDragInitMode != 0 && Math.abs(k - i) > mTouchSlop && mRemoveEnabled)
            {
                mIsRemoving = true;
                startDrag(mFlingHitPos, i1, j1);
                return false;
            }
        }
        continue; /* Loop/switch isn't completed */
        if(mFlingHitPos == -1) goto _L1; else goto _L3
_L3:
        if(Math.abs(k - i) > mTouchSlop && mRemoveEnabled)
        {
            mIsRemoving = true;
            startDrag(mFlingHitPos, i1, j1);
            return false;
        }
        if(Math.abs(l - j) > mTouchSlop)
        {
            mCanDrag = false;
            return false;
        }
        if(true) goto _L1; else goto _L4
_L4:
    }

    public void onShowPress(MotionEvent motionevent)
    {
    }

    public boolean onSingleTapUp(MotionEvent motionevent)
    {
        if(mRemoveEnabled && mRemoveMode == 0 && mClickRemoveHitPos != -1)
            mDslv.removeItem(mClickRemoveHitPos - mDslv.getHeaderViewsCount());
        return true;
    }

    public boolean onTouch(View view, MotionEvent motionevent)
    {
        if(!mDslv.isDragEnabled() || mDslv.listViewIntercepted())
            return false;
        mDetector.onTouchEvent(motionevent);
        if(mRemoveEnabled && mDragging && mRemoveMode == 1)
            mFlingRemoveDetector.onTouchEvent(motionevent);
        switch(0xff & motionevent.getAction())
        {
        case 2: // '\002'
        default:
            return false;

        case 3: // '\003'
            break;

        case 0: // '\0'
            mCurrX = (int)motionevent.getX();
            mCurrY = (int)motionevent.getY();
            return false;

        case 1: // '\001'
            if(mRemoveEnabled && mIsRemoving)
            {
                int i;
                if(mPositionX >= 0)
                    i = mPositionX;
                else
                    i = -mPositionX;
                if(i > mDslv.getWidth() / 2)
                    mDslv.stopDragWithVelocity(true, 0.0F);
            }
            break;
        }
        mIsRemoving = false;
        mDragging = false;
        return false;
    }

    public void setClickRemoveId(int i)
    {
        mClickRemoveId = i;
    }

    public void setDragHandleId(int i)
    {
        mDragHandleId = i;
    }

    public void setDragInitMode(int i)
    {
        mDragInitMode = i;
    }

    public void setFlingHandleId(int i)
    {
        mFlingHandleId = i;
    }

    public void setRemoveEnabled(boolean flag)
    {
        mRemoveEnabled = flag;
    }

    public void setRemoveMode(int i)
    {
        mRemoveMode = i;
    }

    public void setSortEnabled(boolean flag)
    {
        mSortEnabled = flag;
    }

    public boolean startDrag(int i, int j, int k)
    {
        boolean flag = mSortEnabled;
        int l = 0;
        if(flag)
        {
            boolean flag1 = mIsRemoving;
            l = 0;
            if(!flag1)
                l = 0 | 0xc;
        }
        if(mRemoveEnabled && mIsRemoving)
            l = 2 | (l | 1);
        mDragging = mDslv.startDrag(i - mDslv.getHeaderViewsCount(), l, j, k);
        return mDragging;
    }

    public int startDragPosition(MotionEvent motionevent)
    {
        return dragHandleHitPosition(motionevent);
    }

    public int startFlingPosition(MotionEvent motionevent)
    {
        if(mRemoveMode == 1)
            return flingHandleHitPosition(motionevent);
        else
            return -1;
    }

    public int viewIdHitPosition(MotionEvent motionevent, int i)
    {
        int j = (int)motionevent.getX();
        int k = (int)motionevent.getY();
        int l = mDslv.pointToPosition(j, k);
        int i1 = mDslv.getHeaderViewsCount();
        int j1 = mDslv.getFooterViewsCount();
        int k1 = mDslv.getCount();
        if(l != -1 && l >= i1 && l < k1 - j1)
        {
            View view = mDslv.getChildAt(l - mDslv.getFirstVisiblePosition());
            int l1 = (int)motionevent.getRawX();
            int i2 = (int)motionevent.getRawY();
            View view1;
            if(i == 0)
                view1 = view;
            else
                view1 = view.findViewById(i);
            if(view1 != null)
            {
                view1.getLocationOnScreen(mTempLoc);
                if(l1 > mTempLoc[0] && i2 > mTempLoc[1] && l1 < mTempLoc[0] + view1.getWidth() && i2 < mTempLoc[1] + view1.getHeight())
                {
                    mItemX = view.getLeft();
                    mItemY = view.getTop();
                    return l;
                }
            }
        }
        return -1;
    }

    public static final int CLICK_REMOVE = 0;
    public static final int FLING_REMOVE = 1;
    public static final int MISS = -1;
    public static final int ON_DOWN = 0;
    public static final int ON_DRAG = 1;
    public static final int ON_LONG_PRESS = 2;
    private boolean mCanDrag;
    private int mClickRemoveHitPos;
    private int mClickRemoveId;
    private int mCurrX;
    private int mCurrY;
    private GestureDetector mDetector;
    private int mDragHandleId;
    private int mDragInitMode;
    private boolean mDragging;
    private DragSortListView mDslv;
    private int mFlingHandleId;
    private int mFlingHitPos;
    private GestureDetector mFlingRemoveDetector;
    private android.view.GestureDetector.OnGestureListener mFlingRemoveListener = new android.view.GestureDetector.SimpleOnGestureListener() {

        public final boolean onFling(MotionEvent motionevent, MotionEvent motionevent1, float f, float f1)
        {
            if(!mRemoveEnabled || !mIsRemoving) goto _L2; else goto _L1
_L1:
            int j1 = mDslv.getWidth() / 5;
            if(f <= mFlingSpeed) goto _L4; else goto _L3
_L3:
            if(mPositionX > -j1)
                mDslv.stopDragWithVelocity(true, f);
_L6:
            mIsRemoving = false;
_L2:
            return false;
_L4:
            if(f < -mFlingSpeed && mPositionX < j1)
                mDslv.stopDragWithVelocity(true, f);
            if(true) goto _L6; else goto _L5
_L5:
        }

        final DragSortController this$0;

            
            {
                this$0 = DragSortController.this;
                super();
            }
    }
;
    private float mFlingSpeed;
    private int mHitPos;
    private boolean mIsRemoving;
    private int mItemX;
    private int mItemY;
    private int mPositionX;
    private boolean mRemoveEnabled;
    private int mRemoveMode;
    private boolean mSortEnabled;
    private int mTempLoc[];
    private int mTouchSlop;




/*
    static boolean access$102(DragSortController dragsortcontroller, boolean flag)
    {
        dragsortcontroller.mIsRemoving = flag;
        return flag;
    }

*/



}
