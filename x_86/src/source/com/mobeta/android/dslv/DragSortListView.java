// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.mobeta.android.dslv;

import android.content.Context;
import android.content.res.TypedArray;
import android.database.DataSetObserver;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.os.Environment;
import android.os.SystemClock;
import android.util.*;
import android.view.*;
import android.widget.*;
import java.io.*;
import java.util.ArrayList;

// Referenced classes of package com.mobeta.android.dslv:
//            DragSortController, DragSortItemView, DragSortItemViewCheckable

public class DragSortListView extends ListView
{
    private class AdapterWrapper extends BaseAdapter
    {

        public boolean areAllItemsEnabled()
        {
            return mAdapter.areAllItemsEnabled();
        }

        public ListAdapter getAdapter()
        {
            return mAdapter;
        }

        public int getCount()
        {
            return mAdapter.getCount();
        }

        public Object getItem(int i)
        {
            return mAdapter.getItem(i);
        }

        public long getItemId(int i)
        {
            return mAdapter.getItemId(i);
        }

        public int getItemViewType(int i)
        {
            return mAdapter.getItemViewType(i);
        }

        public View getView(int i, View view, ViewGroup viewgroup)
        {
            Object obj;
            if(view != null)
            {
                obj = (DragSortItemView)view;
                View view2 = ((DragSortItemView) (obj)).getChildAt(0);
                View view3 = mAdapter.getView(i, view2, DragSortListView.this);
                if(view3 != view2)
                {
                    if(view2 != null)
                        ((DragSortItemView) (obj)).removeViewAt(0);
                    ((DragSortItemView) (obj)).addView(view3);
                }
            } else
            {
                View view1 = mAdapter.getView(i, null, DragSortListView.this);
                if(view1 instanceof Checkable)
                    obj = new DragSortItemViewCheckable(getContext());
                else
                    obj = new DragSortItemView(getContext());
                ((DragSortItemView) (obj)).setLayoutParams(new android.widget.AbsListView.LayoutParams(-1, -2));
                ((DragSortItemView) (obj)).addView(view1);
            }
            adjustItem(i + getHeaderViewsCount(), ((View) (obj)), true);
            return ((View) (obj));
        }

        public int getViewTypeCount()
        {
            return mAdapter.getViewTypeCount();
        }

        public boolean hasStableIds()
        {
            return mAdapter.hasStableIds();
        }

        public boolean isEmpty()
        {
            return mAdapter.isEmpty();
        }

        public boolean isEnabled(int i)
        {
            return mAdapter.isEnabled(i);
        }

        private ListAdapter mAdapter;
        final DragSortListView this$0;

        public AdapterWrapper(ListAdapter listadapter)
        {
            this$0 = DragSortListView.this;
            super();
            mAdapter = listadapter;
            mAdapter.registerDataSetObserver(new _cls1());
        }
    }

    public static interface DragListener
    {

        public abstract void drag(int i, int j);
    }

    public static interface DragScrollProfile
    {

        public abstract float getSpeed(float f, long l);
    }

    private class DragScroller
        implements Runnable
    {

        public int getScrollDir()
        {
            if(mScrolling)
                return scrollDir;
            else
                return -1;
        }

        public boolean isScrolling()
        {
            return mScrolling;
        }

        public void run()
        {
            if(mAbort)
            {
                mScrolling = false;
                return;
            }
            int i = getFirstVisiblePosition();
            int j = getLastVisiblePosition();
            int k = getCount();
            int l = getPaddingTop();
            int i1 = getHeight() - l - getPaddingBottom();
            int j1 = Math.min(mY, mFloatViewMid + mFloatViewHeightHalf);
            int k1 = Math.max(mY, mFloatViewMid - mFloatViewHeightHalf);
            int l1;
            View view1;
            int i2;
            if(scrollDir == 0)
            {
                View view2 = getChildAt(0);
                if(view2 == null)
                {
                    mScrolling = false;
                    return;
                }
                if(i == 0 && view2.getTop() == l)
                {
                    mScrolling = false;
                    return;
                }
                mScrollSpeed = mScrollProfile.getSpeed((mUpScrollStartYF - (float)k1) / mDragUpScrollHeight, mPrevTime);
            } else
            {
                View view = getChildAt(j - i);
                if(view == null)
                {
                    mScrolling = false;
                    return;
                }
                if(j == k - 1 && view.getBottom() <= i1 + l)
                {
                    mScrolling = false;
                    return;
                }
                mScrollSpeed = -mScrollProfile.getSpeed(((float)j1 - mDownScrollStartYF) / mDragDownScrollHeight, mPrevTime);
            }
            mCurrTime = SystemClock.uptimeMillis();
            dt = mCurrTime - mPrevTime;
            dy = Math.round(mScrollSpeed * dt);
            if(dy >= 0)
            {
                dy = Math.min(i1, dy);
                l1 = i;
            } else
            {
                dy = Math.max(-i1, dy);
                l1 = j;
            }
            view1 = getChildAt(l1 - i);
            i2 = view1.getTop() + dy;
            if(l1 == 0 && i2 > l)
                i2 = l;
            mBlockLayoutRequests = true;
            setSelectionFromTop(l1, i2 - l);
            layoutChildren();
            invalidate();
            mBlockLayoutRequests = false;
            doDragFloatView(l1, view1, false);
            mPrevTime = mCurrTime;
            post(this);
        }

        public void startScrolling(int i)
        {
            if(!mScrolling)
            {
                mAbort = false;
                mScrolling = true;
                tStart = SystemClock.uptimeMillis();
                mPrevTime = tStart;
                scrollDir = i;
                post(this);
            }
        }

        public void stopScrolling(boolean flag)
        {
            if(flag)
            {
                removeCallbacks(this);
                mScrolling = false;
                return;
            } else
            {
                mAbort = true;
                return;
            }
        }

        public static final int DOWN = 1;
        public static final int STOP = -1;
        public static final int UP;
        private float dt;
        private int dy;
        private boolean mAbort;
        private long mCurrTime;
        private int mFirstFooter;
        private int mLastHeader;
        private long mPrevTime;
        private float mScrollSpeed;
        private boolean mScrolling;
        private int scrollDir;
        private long tStart;
        final DragSortListView this$0;

        public DragScroller()
        {
            this$0 = DragSortListView.this;
            super();
            mScrolling = false;
        }
    }

    public static interface DragSortListener
        extends DropListener, DragListener, RemoveListener
    {
    }

    private class DragSortTracker
    {

        public void appendState()
        {
            if(mTracking)
            {
                mBuilder.append("<DSLVState>\n");
                int i = getChildCount();
                int j = getFirstVisiblePosition();
                mBuilder.append("    <Positions>");
                for(int k = 0; k < i; k++)
                    mBuilder.append(j + k).append(",");

                mBuilder.append("</Positions>\n");
                mBuilder.append("    <Tops>");
                for(int l = 0; l < i; l++)
                    mBuilder.append(getChildAt(l).getTop()).append(",");

                mBuilder.append("</Tops>\n");
                mBuilder.append("    <Bottoms>");
                for(int i1 = 0; i1 < i; i1++)
                    mBuilder.append(getChildAt(i1).getBottom()).append(",");

                mBuilder.append("</Bottoms>\n");
                mBuilder.append("    <FirstExpPos>").append(mFirstExpPos).append("</FirstExpPos>\n");
                mBuilder.append("    <FirstExpBlankHeight>").append(getItemHeight(mFirstExpPos) - getChildHeight(mFirstExpPos)).append("</FirstExpBlankHeight>\n");
                mBuilder.append("    <SecondExpPos>").append(mSecondExpPos).append("</SecondExpPos>\n");
                mBuilder.append("    <SecondExpBlankHeight>").append(getItemHeight(mSecondExpPos) - getChildHeight(mSecondExpPos)).append("</SecondExpBlankHeight>\n");
                mBuilder.append("    <SrcPos>").append(mSrcPos).append("</SrcPos>\n");
                mBuilder.append("    <SrcHeight>").append(mFloatViewHeight + getDividerHeight()).append("</SrcHeight>\n");
                mBuilder.append("    <ViewHeight>").append(getHeight()).append("</ViewHeight>\n");
                mBuilder.append("    <LastY>").append(mLastY).append("</LastY>\n");
                mBuilder.append("    <FloatY>").append(mFloatViewMid).append("</FloatY>\n");
                mBuilder.append("    <ShuffleEdges>");
                for(int j1 = 0; j1 < i; j1++)
                    mBuilder.append(getShuffleEdge(j + j1, getChildAt(j1).getTop())).append(",");

                mBuilder.append("</ShuffleEdges>\n");
                mBuilder.append("</DSLVState>\n");
                mNumInBuffer = 1 + mNumInBuffer;
                if(mNumInBuffer > 1000)
                {
                    flush();
                    mNumInBuffer = 0;
                    return;
                }
            }
        }

        public void flush()
        {
            boolean flag;
            if(!mTracking)
                return;
            flag = true;
            if(mNumFlushes == 0)
                flag = false;
            try
            {
                FileWriter filewriter = new FileWriter(mFile, flag);
                filewriter.write(mBuilder.toString());
                mBuilder.delete(0, mBuilder.length());
                filewriter.flush();
                filewriter.close();
                mNumFlushes = 1 + mNumFlushes;
                return;
            }
            catch(IOException ioexception)
            {
                return;
            }
        }

        public void startTracking()
        {
            mBuilder.append("<DSLVStates>\n");
            mNumFlushes = 0;
            mTracking = true;
        }

        public void stopTracking()
        {
            if(mTracking)
            {
                mBuilder.append("</DSLVStates>\n");
                flush();
                mTracking = false;
            }
        }

        StringBuilder mBuilder;
        File mFile;
        private int mNumFlushes;
        private int mNumInBuffer;
        private boolean mTracking;
        final DragSortListView this$0;

        public DragSortTracker()
        {
            this$0 = DragSortListView.this;
            super();
            mBuilder = new StringBuilder();
            mNumInBuffer = 0;
            mNumFlushes = 0;
            mTracking = false;
            mFile = new File(Environment.getExternalStorageDirectory(), "dslv_state.txt");
            if(mFile.exists())
                break MISSING_BLOCK_LABEL_77;
            mFile.createNewFile();
            Log.d("mobeta", "file created");
            return;
            IOException ioexception;
            ioexception;
            Log.w("mobeta", "Could not create dslv_state.txt");
            Log.d("mobeta", ioexception.getMessage());
            return;
        }
    }

    private class DropAnimator extends SmoothAnimator
    {

        private int getTargetY()
        {
            int i = getFirstVisiblePosition();
            int j = (mItemHeightCollapsed + getDividerHeight()) / 2;
            View view = getChildAt(mDropPos - i);
            if(view != null)
            {
                if(mDropPos == srcPos)
                    return view.getTop();
                if(mDropPos < srcPos)
                    return view.getTop() - j;
                else
                    return (j + view.getBottom()) - mFloatViewHeight;
            } else
            {
                cancel();
                return -1;
            }
        }

        public void onStart()
        {
            mDropPos = mFloatPos;
            srcPos = mSrcPos;
            mDragState = 2;
            mInitDeltaY = mFloatLoc.y - getTargetY();
            mInitDeltaX = mFloatLoc.x - getPaddingLeft();
        }

        public void onStop()
        {
            dropFloatView();
        }

        public void onUpdate(float f, float f1)
        {
            int i = getTargetY();
            int j = getPaddingLeft();
            float f2 = mFloatLoc.y - i;
            float f3 = mFloatLoc.x - j;
            float f4 = 1.0F - f1;
            if(f4 < Math.abs(f2 / mInitDeltaY) || f4 < Math.abs(f3 / mInitDeltaX))
            {
                mFloatLoc.y = i + (int)(f4 * mInitDeltaY);
                mFloatLoc.x = getPaddingLeft() + (int)(f4 * mInitDeltaX);
                doDragFloatView(true);
            }
        }

        private int mDropPos;
        private float mInitDeltaX;
        private float mInitDeltaY;
        private int srcPos;
        final DragSortListView this$0;

        public DropAnimator(float f, int i)
        {
            this$0 = DragSortListView.this;
            super(f, i);
        }
    }

    public static interface DropListener
    {

        public abstract void drop(int i, int j);
    }

    public static interface FloatViewManager
    {

        public abstract View onCreateFloatView(int i);

        public abstract void onDestroyFloatView(View view);

        public abstract void onDragFloatView(View view, Point point, Point point1);
    }

    private class HeightCache
    {

        public void add(int i, int j)
        {
            int k = mMap.get(i, -1);
            if(k != j)
            {
                if(k == -1)
                {
                    if(mMap.size() == mMaxSize)
                        mMap.delete(((Integer)mOrder.remove(0)).intValue());
                } else
                {
                    mOrder.remove(Integer.valueOf(i));
                }
                mMap.put(i, j);
                mOrder.add(Integer.valueOf(i));
            }
        }

        public void clear()
        {
            mMap.clear();
            mOrder.clear();
        }

        public int get(int i)
        {
            return mMap.get(i, -1);
        }

        private SparseIntArray mMap;
        private int mMaxSize;
        private ArrayList mOrder;
        final DragSortListView this$0;

        public HeightCache(int i)
        {
            this$0 = DragSortListView.this;
            super();
            mMap = new SparseIntArray(i);
            mOrder = new ArrayList(i);
            mMaxSize = i;
        }
    }

    private class LiftAnimator extends SmoothAnimator
    {

        public void onStart()
        {
            mInitDragDeltaY = mDragDeltaY;
            mFinalDragDeltaY = mFloatViewHeightHalf;
        }

        public void onUpdate(float f, float f1)
        {
            if(mDragState != 4)
            {
                cancel();
                return;
            } else
            {
                mDragDeltaY = (int)(f1 * mFinalDragDeltaY + (1.0F - f1) * mInitDragDeltaY);
                mFloatLoc.y = mY - mDragDeltaY;
                doDragFloatView(true);
                return;
            }
        }

        private float mFinalDragDeltaY;
        private float mInitDragDeltaY;
        final DragSortListView this$0;

        public LiftAnimator(float f, int i)
        {
            this$0 = DragSortListView.this;
            super(f, i);
        }
    }

    public static interface RemoveListener
    {

        public abstract void remove(int i);
    }

    private class SmoothAnimator
        implements Runnable
    {

        public void cancel()
        {
            mCanceled = true;
        }

        public void onStart()
        {
        }

        public void onStop()
        {
        }

        public void onUpdate(float f, float f1)
        {
        }

        public void run()
        {
            if(mCanceled)
                return;
            float f = (float)(SystemClock.uptimeMillis() - mStartTime) / mDurationF;
            if(f >= 1.0F)
            {
                onUpdate(1.0F, 1.0F);
                onStop();
                return;
            } else
            {
                onUpdate(f, transform(f));
                post(this);
                return;
            }
        }

        public void start()
        {
            mStartTime = SystemClock.uptimeMillis();
            mCanceled = false;
            onStart();
            post(this);
        }

        public float transform(float f)
        {
            if(f < mAlpha)
                return f * (f * mA);
            if(f < 1.0F - mAlpha)
                return mB + f * mC;
            else
                return 1.0F - mD * (f - 1.0F) * (f - 1.0F);
        }

        private float mA;
        private float mAlpha;
        private float mB;
        private float mC;
        private boolean mCanceled;
        private float mD;
        private float mDurationF;
        protected long mStartTime;
        final DragSortListView this$0;

        public SmoothAnimator(float f, int i)
        {
            this$0 = DragSortListView.this;
            super();
            mAlpha = f;
            mDurationF = i;
            float f1 = 1.0F / (2.0F * mAlpha * (1.0F - mAlpha));
            mD = f1;
            mA = f1;
            mB = mAlpha / (2.0F * (mAlpha - 1.0F));
            mC = 1.0F / (1.0F - mAlpha);
        }
    }


    public DragSortListView(Context context, AttributeSet attributeset)
    {
        ListView(context, attributeset);
        mFloatLoc = new Point();
        mTouchLoc = new Point();
        mFloatViewOnMeasured = false;
        mFloatAlpha = 1.0F;
        mCurrFloatAlpha = 1.0F;
        mAnimate = false;
        mDragEnabled = true;
        mDragState = 0;
        mItemHeightCollapsed = 1;
        mWidthMeasureSpec = 0;
        mSampleViewTypes = new View[1];
        mDragUpScrollStartFrac = 0.3333333F;
        mDragDownScrollStartFrac = 0.3333333F;
        mMaxScrollSpeed = 0.5F;
        mScrollProfile = new DragScrollProfile() {

            public float getSpeed(float f, long l2)
            {
                return f * mMaxScrollSpeed;
            }

            final DragSortListView this$0;

            
            {
                this$0 = DragSortListView.this;
                super();
            }
        }
;
        mDragFlags = 0;
        mLastCallWasIntercept = false;
        mInTouchEvent = false;
        mFloatViewManager = null;
        mCancelMethod = 0;
        mSlideRegionFrac = 0.25F;
        mSlideFrac = 0.0F;
        mTrackDragSort = false;
        mBlockLayoutRequests = false;
        mIgnoreTouchEvent = false;
        mChildHeightCache = new HeightCache(3);
        mRemoveVelocityX = 0.0F;
        mListViewIntercepted = false;
        mFloatViewInvalidated = false;
        int i = 150;
        int j = 150;
        if(attributeset != null)
        {
            TypedArray typedarray = getContext().obtainStyledAttributes(attributeset, R.styleable.DragSortListView, 0, 0);
            mItemHeightCollapsed = Math.max(1, typedarray.getDimensionPixelSize(0, 1));
            mTrackDragSort = typedarray.getBoolean(5, false);
            if(mTrackDragSort)
                mDragSortTracker = new DragSortTracker();
            mFloatAlpha = typedarray.getFloat(6, mFloatAlpha);
            mCurrFloatAlpha = mFloatAlpha;
            mDragEnabled = typedarray.getBoolean(10, mDragEnabled);
            mSlideRegionFrac = Math.max(0.0F, Math.min(1.0F, 1.0F - typedarray.getFloat(7, 0.75F)));
            boolean flag;
            if(mSlideRegionFrac > 0.0F)
                flag = true;
            else
                flag = false;
            mAnimate = flag;
            setDragScrollStart(typedarray.getFloat(1, mDragUpScrollStartFrac));
            mMaxScrollSpeed = typedarray.getFloat(2, mMaxScrollSpeed);
            i = typedarray.getInt(8, i);
            j = typedarray.getInt(9, j);
            if(typedarray.getBoolean(17, true))
            {
                boolean flag1 = typedarray.getBoolean(12, false);
                int k = typedarray.getInt(4, 1);
                boolean flag2 = typedarray.getBoolean(11, true);
                int l = typedarray.getInt(13, 0);
                int i1 = typedarray.getResourceId(14, 0);
                int j1 = typedarray.getResourceId(15, 0);
                int k1 = typedarray.getResourceId(16, 0);
                int l1 = typedarray.getColor(3, 0xff000000);
                DragSortController dragsortcontroller = new DragSortController(this, i1, l, k, k1, j1);
                dragsortcontroller.setRemoveEnabled(flag1);
                dragsortcontroller.setSortEnabled(flag2);
                dragsortcontroller.setBackgroundColor(l1);
                mFloatViewManager = dragsortcontroller;
                setOnTouchListener(dragsortcontroller);
            }
            typedarray.recycle();
        }
        mDragScroller = new DragScroller();
        if(i > 0)
            mRemoveAnimator = new RemoveAnimator(0.5F, i);
        if(j > 0)
            mDropAnimator = new DropAnimator(0.5F, j);
        mCancelEvent = MotionEvent.obtain(0L, 0L, 3, 0.0F, 0.0F, 0.0F, 0.0F, 0, 0.0F, 0.0F, 0, 0);
        mObserver = new DataSetObserver() {

            private void cancel()
            {
                if(mDragState == 4)
                    cancelDrag();
            }

            public void onChanged()
            {
                cancel();
            }

            public void onInvalidated()
            {
                cancel();
            }

            final DragSortListView this$0;

            
            {
                this$0 = DragSortListView.this;
                super();
            }
        }
;
    }

    private void adjustAllItems()
    {
        int i = getFirstVisiblePosition();
        int j = getLastVisiblePosition();
        int k = Math.max(0, getHeaderViewsCount() - i);
        int l = Math.min(j - i, (-1 + getCount()) - getFooterViewsCount() - i);
        for(int i1 = k; i1 <= l; i1++)
        {
            View view = getChildAt(i1);
            if(view != null)
                adjustItem(i + i1, view, false);
        }

    }

    private void adjustItem(int i)
    {
        View view = getChildAt(i - getFirstVisiblePosition());
        if(view != null)
            adjustItem(i, view, false);
    }

    private void adjustItem(int i, View view, boolean flag)
    {
        android.view.ViewGroup.LayoutParams layoutparams = view.getLayoutParams();
        int j;
        int k;
        int l;
        byte byte0;
        View view1;
        if(i != mSrcPos && i != mFirstExpPos && i != mSecondExpPos)
            j = -2;
        else
            j = calcItemHeight(i, view, flag);
        if(j != layoutparams.height)
        {
            layoutparams.height = j;
            view.setLayoutParams(layoutparams);
        }
        if(i != mFirstExpPos && i != mSecondExpPos) goto _L2; else goto _L1
_L1:
        if(i >= mSrcPos) goto _L4; else goto _L3
_L3:
        ((DragSortItemView)view).setGravity(80);
_L2:
        k = view.getVisibility();
        l = mSrcPos;
        byte0 = 0;
        if(i == l)
        {
            view1 = mFloatView;
            byte0 = 0;
            if(view1 != null)
                byte0 = 4;
        }
        if(byte0 != k)
            view.setVisibility(byte0);
        return;
_L4:
        if(i > mSrcPos)
            ((DragSortItemView)view).setGravity(48);
        if(true) goto _L2; else goto _L5
_L5:
    }

    private void adjustOnReorder()
    {
        int i = getFirstVisiblePosition();
        if(mSrcPos < i)
        {
            View view = getChildAt(0);
            int j = 0;
            if(view != null)
                j = view.getTop();
            setSelectionFromTop(i - 1, j - getPaddingTop());
        }
    }

    private int adjustScroll(int i, View view, int j, int k)
    {
        int l = getChildHeight(i);
        int i1 = view.getHeight();
        int j1 = calcItemHeight(i, l);
        int k1 = i1;
        int l1 = j1;
        if(i != mSrcPos)
        {
            k1 -= l;
            l1 -= l;
        }
        int i2 = mFloatViewHeight;
        if(mSrcPos != mFirstExpPos && mSrcPos != mSecondExpPos)
            i2 -= mItemHeightCollapsed;
        int k2;
        if(i <= j)
        {
            int l2 = mFirstExpPos;
            k2 = 0;
            if(i > l2)
                k2 = 0 + (i2 - l1);
        } else
        {
            if(i == k)
            {
                if(i <= mFirstExpPos)
                    return 0 + (k1 - i2);
                if(i == mSecondExpPos)
                    return 0 + (i1 - j1);
                else
                    return 0 + k1;
            }
            if(i <= mFirstExpPos)
                return 0 - i2;
            int j2 = mSecondExpPos;
            k2 = 0;
            if(i == j2)
                return 0 - l1;
        }
        return k2;
    }

    private static int buildRunList(SparseBooleanArray sparsebooleanarray, int i, int j, int ai[], int ai1[])
    {
        int k = 0;
        int l = findFirstSetIndex(sparsebooleanarray, i, j);
        if(l == -1)
            return 0;
        int i1 = sparsebooleanarray.keyAt(l);
        int j1 = i1 + 1;
        int k1 = l + 1;
        do
        {
            if(k1 >= sparsebooleanarray.size())
                break;
            int i2 = sparsebooleanarray.keyAt(k1);
            if(i2 >= j)
                break;
            if(sparsebooleanarray.valueAt(k1))
                if(i2 == j1)
                {
                    j1++;
                } else
                {
                    ai[k] = i1;
                    ai1[k] = j1;
                    k++;
                    i1 = i2;
                    j1 = i2 + 1;
                }
            k1++;
        } while(true);
        if(j1 == j)
            j1 = i;
        ai[k] = i1;
        ai1[k] = j1;
        int l1 = k + 1;
        if(l1 > 1 && ai[0] == i && ai1[l1 - 1] == i)
        {
            ai[0] = ai[l1 - 1];
            l1--;
        }
        return l1;
    }

    private int calcItemHeight(int i, int j)
    {
        getDividerHeight();
        boolean flag;
        int k;
        int l;
        if(mAnimate && mFirstExpPos != mSecondExpPos)
            flag = true;
        else
            flag = false;
        k = mFloatViewHeight - mItemHeightCollapsed;
        l = (int)(mSlideFrac * (float)k);
        if(i == mSrcPos)
        {
            if(mSrcPos == mFirstExpPos)
                if(flag)
                    return l + mItemHeightCollapsed;
                else
                    return mFloatViewHeight;
            if(mSrcPos == mSecondExpPos)
                return mFloatViewHeight - l;
            else
                return mItemHeightCollapsed;
        }
        if(i == mFirstExpPos)
            if(flag)
                return j + l;
            else
                return j + k;
        if(i == mSecondExpPos)
            return (j + k) - l;
        else
            return j;
    }

    private int calcItemHeight(int i, View view, boolean flag)
    {
        return calcItemHeight(i, getChildHeight(i, view, flag));
    }

    private void clearPositions()
    {
        mSrcPos = -1;
        mFirstExpPos = -1;
        mSecondExpPos = -1;
        mFloatPos = -1;
    }

    private void continueDrag(int i, int j)
    {
        mFloatLoc.x = i - mDragDeltaX;
        mFloatLoc.y = j - mDragDeltaY;
        doDragFloatView(true);
        int k = Math.min(j, mFloatViewMid + mFloatViewHeightHalf);
        int l = Math.max(j, mFloatViewMid - mFloatViewHeightHalf);
        int i1 = mDragScroller.getScrollDir();
        if(k > mLastY && k > mDownScrollStartY && i1 != 1)
        {
            if(i1 != -1)
                mDragScroller.stopScrolling(true);
            mDragScroller.startScrolling(1);
        } else
        {
            if(l < mLastY && l < mUpScrollStartY && i1 != 0)
            {
                if(i1 != -1)
                    mDragScroller.stopScrolling(true);
                mDragScroller.startScrolling(0);
                return;
            }
            if(l >= mUpScrollStartY && k <= mDownScrollStartY && mDragScroller.isScrolling())
            {
                mDragScroller.stopScrolling(true);
                return;
            }
        }
    }

    private void destroyFloatView()
    {
        if(mFloatView != null)
        {
            mFloatView.setVisibility(8);
            if(mFloatViewManager != null)
                mFloatViewManager.onDestroyFloatView(mFloatView);
            mFloatView = null;
            invalidate();
        }
    }

    private void doActionUpOrCancel()
    {
        mCancelMethod = 0;
        mInTouchEvent = false;
        if(mDragState == 3)
            mDragState = 0;
        mCurrFloatAlpha = mFloatAlpha;
        mListViewIntercepted = false;
        mChildHeightCache.clear();
    }

    private void doDragFloatView(int i, View view, boolean flag)
    {
        mBlockLayoutRequests = true;
        updateFloatView();
        int j = mFirstExpPos;
        int k = mSecondExpPos;
        boolean flag1 = updatePositions();
        if(flag1)
        {
            adjustAllItems();
            setSelectionFromTop(i, (adjustScroll(i, view, j, k) + view.getTop()) - getPaddingTop());
            layoutChildren();
        }
        if(flag1 || flag)
            invalidate();
        mBlockLayoutRequests = false;
    }

    private void doDragFloatView(boolean flag)
    {
        int i = getFirstVisiblePosition() + getChildCount() / 2;
        View view = getChildAt(getChildCount() / 2);
        if(view == null)
        {
            return;
        } else
        {
            doDragFloatView(i, view, flag);
            return;
        }
    }

    private void doRemoveItem()
    {
        doRemoveItem(mSrcPos - getHeaderViewsCount());
    }

    private void doRemoveItem(int i)
    {
        mDragState = 1;
        if(mRemoveListener != null)
            mRemoveListener.remove(i);
        destroyFloatView();
        adjustOnReorder();
        clearPositions();
        if(mInTouchEvent)
        {
            mDragState = 3;
            return;
        } else
        {
            mDragState = 0;
            return;
        }
    }

    private void drawDivider(int i, Canvas canvas)
    {
        Drawable drawable = getDivider();
        int j = getDividerHeight();
        if(drawable != null && j != 0)
        {
            ViewGroup viewgroup = (ViewGroup)getChildAt(i - getFirstVisiblePosition());
            if(viewgroup != null)
            {
                int k = getPaddingLeft();
                int l = getWidth() - getPaddingRight();
                int i1 = viewgroup.getChildAt(0).getHeight();
                int j1;
                int k1;
                if(i > mSrcPos)
                {
                    k1 = i1 + viewgroup.getTop();
                    j1 = k1 + j;
                } else
                {
                    j1 = viewgroup.getBottom() - i1;
                    k1 = j1 - j;
                }
                canvas.save();
                canvas.clipRect(k, k1, l, j1);
                drawable.setBounds(k, k1, l, j1);
                drawable.draw(canvas);
                canvas.restore();
            }
        }
    }

    private void dropFloatView()
    {
        mDragState = 2;
        if(mDropListener != null && mFloatPos >= 0 && mFloatPos < getCount())
        {
            int i = getHeaderViewsCount();
            mDropListener.drop(mSrcPos - i, mFloatPos - i);
        }
        destroyFloatView();
        adjustOnReorder();
        clearPositions();
        adjustAllItems();
        if(mInTouchEvent)
        {
            mDragState = 3;
            return;
        } else
        {
            mDragState = 0;
            return;
        }
    }

    private static int findFirstSetIndex(SparseBooleanArray sparsebooleanarray, int i, int j)
    {
        int k = sparsebooleanarray.size();
        int l;
        for(l = insertionIndexForKey(sparsebooleanarray, i); l < k && sparsebooleanarray.keyAt(l) < j && !sparsebooleanarray.valueAt(l); l++);
        if(l == k || sparsebooleanarray.keyAt(l) >= j)
            l = -1;
        return l;
    }

    private int getChildHeight(int i)
    {
        int j = mSrcPos;
        int k = 0;
        if(i != j)
        {
            View view = getChildAt(i - getFirstVisiblePosition());
            if(view != null)
                return getChildHeight(i, view, false);
            k = mChildHeightCache.get(i);
            if(k == -1)
            {
                ListAdapter listadapter = getAdapter();
                int l = listadapter.getItemViewType(i);
                int i1 = listadapter.getViewTypeCount();
                if(i1 != mSampleViewTypes.length)
                    mSampleViewTypes = new View[i1];
                View view1;
                int j1;
                if(l >= 0)
                {
                    if(mSampleViewTypes[l] == null)
                    {
                        view1 = listadapter.getView(i, null, this);
                        mSampleViewTypes[l] = view1;
                    } else
                    {
                        view1 = listadapter.getView(i, mSampleViewTypes[l], this);
                    }
                } else
                {
                    view1 = listadapter.getView(i, null, this);
                }
                j1 = getChildHeight(i, view1, true);
                mChildHeightCache.add(i, j1);
                return j1;
            }
        }
        return k;
    }

    private int getChildHeight(int i, View view, boolean flag)
    {
        int j = mSrcPos;
        int k = 0;
        if(i != j)
        {
            View view1;
            android.view.ViewGroup.LayoutParams layoutparams;
            if(i < getHeaderViewsCount() || i >= getCount() - getFooterViewsCount())
                view1 = view;
            else
                view1 = ((ViewGroup)view).getChildAt(0);
            layoutparams = view1.getLayoutParams();
            if(layoutparams != null && layoutparams.height > 0)
                return layoutparams.height;
            k = view1.getHeight();
            if(k == 0 || flag)
            {
                measureItem(view1);
                return view1.getMeasuredHeight();
            }
        }
        return k;
    }

    private int getItemHeight(int i)
    {
        View view = getChildAt(i - getFirstVisiblePosition());
        if(view != null)
            return view.getHeight();
        else
            return calcItemHeight(i, getChildHeight(i));
    }

    private int getShuffleEdge(int i, int j)
    {
        int k = getHeaderViewsCount();
        int l = getFooterViewsCount();
        if(i <= k || i >= getCount() - l)
            return j;
        int i1 = getDividerHeight();
        int j1 = mFloatViewHeight - mItemHeightCollapsed;
        int k1 = getChildHeight(i);
        int l1 = getItemHeight(i);
        int i2 = j;
        if(mSecondExpPos <= mSrcPos)
        {
            if(i == mSecondExpPos && mFirstExpPos != mSecondExpPos)
            {
                if(i == mSrcPos)
                    i2 = (j + l1) - mFloatViewHeight;
                else
                    i2 = (j + (l1 - k1)) - j1;
            } else
            if(i > mSecondExpPos && i <= mSrcPos)
                i2 = j - j1;
        } else
        if(i > mSrcPos && i <= mFirstExpPos)
            i2 = j + j1;
        else
        if(i == mSecondExpPos && mFirstExpPos != mSecondExpPos)
            i2 = j + (l1 - k1);
        if(i <= mSrcPos)
            return i2 + (mFloatViewHeight - i1 - getChildHeight(i - 1)) / 2;
        else
            return i2 + (k1 - i1 - mFloatViewHeight) / 2;
    }

    private static int insertionIndexForKey(SparseBooleanArray sparsebooleanarray, int i)
    {
        int j = 0;
        for(int k = sparsebooleanarray.size(); k - j > 0;)
        {
            int l = j + k >> 1;
            if(sparsebooleanarray.keyAt(l) < i)
                j = l + 1;
            else
                k = l;
        }

        return j;
    }

    private void invalidateFloatView()
    {
        mFloatViewInvalidated = true;
    }

    private void measureFloatView()
    {
        if(mFloatView != null)
        {
            measureItem(mFloatView);
            mFloatViewHeight = mFloatView.getMeasuredHeight();
            mFloatViewHeightHalf = mFloatViewHeight / 2;
        }
    }

    private void measureItem(View view)
    {
        Object obj = view.getLayoutParams();
        if(obj == null)
        {
            obj = new LayoutParams(-1, -2);
            view.setLayoutParams(((android.view.ViewGroup.LayoutParams) (obj)));
        }
        int i = ViewGroup.getChildMeasureSpec(mWidthMeasureSpec, getListPaddingLeft() + getListPaddingRight(), ((android.view.ViewGroup.LayoutParams) (obj)).width);
        int j;
        if(((android.view.ViewGroup.LayoutParams) (obj)).height > 0)
            j = android.view.View.MeasureSpec.makeMeasureSpec(((android.view.ViewGroup.LayoutParams) (obj)).height, 0x40000000);
        else
            j = android.view.View.MeasureSpec.makeMeasureSpec(0, 0);
        view.measure(i, j);
    }

    private void printPosData()
    {
        Log.d("mobeta", (new StringBuilder()).append("mSrcPos=").append(mSrcPos).append(" mFirstExpPos=").append(mFirstExpPos).append(" mSecondExpPos=").append(mSecondExpPos).toString());
    }

    private static int rotate(int i, int j, int k, int l)
    {
        int i1 = l - k;
        int j1 = i + j;
        if(j1 < k)
            j1 += i1;
        else
        if(j1 >= l)
            return j1 - i1;
        return j1;
    }

    private void saveTouchCoords(MotionEvent motionevent)
    {
        int i = 0xff & motionevent.getAction();
        if(i != 0)
        {
            mLastX = mX;
            mLastY = mY;
        }
        mX = (int)motionevent.getX();
        mY = (int)motionevent.getY();
        if(i == 0)
        {
            mLastX = mX;
            mLastY = mY;
        }
        mOffsetX = (int)motionevent.getRawX() - mX;
        mOffsetY = (int)motionevent.getRawY() - mY;
    }

    private void updateFloatView()
    {
        if(mFloatViewManager != null)
        {
            mTouchLoc.set(mX, mY);
            mFloatViewManager.onDragFloatView(mFloatView, mFloatLoc, mTouchLoc);
        }
        int i = mFloatLoc.x;
        int j = mFloatLoc.y;
        int k = getPaddingLeft();
        int l;
        int i1;
        int j1;
        int k1;
        int l1;
        int i2;
        if((1 & mDragFlags) == 0 && i > k)
            mFloatLoc.x = k;
        else
        if((2 & mDragFlags) == 0 && i < k)
            mFloatLoc.x = k;
        l = getHeaderViewsCount();
        i1 = getFooterViewsCount();
        j1 = getFirstVisiblePosition();
        k1 = getLastVisiblePosition();
        l1 = getPaddingTop();
        if(j1 < l)
            l1 = getChildAt(-1 + (l - j1)).getBottom();
        if((8 & mDragFlags) == 0 && j1 <= mSrcPos)
            l1 = Math.max(getChildAt(mSrcPos - j1).getTop(), l1);
        i2 = getHeight() - getPaddingBottom();
        if(k1 >= -1 + (getCount() - i1))
            i2 = getChildAt((-1 + (getCount() - i1)) - j1).getBottom();
        if((4 & mDragFlags) == 0 && k1 >= mSrcPos)
            i2 = Math.min(getChildAt(mSrcPos - j1).getBottom(), i2);
        if(j < l1)
            mFloatLoc.y = l1;
        else
        if(j + mFloatViewHeight > i2)
            mFloatLoc.y = i2 - mFloatViewHeight;
        mFloatViewMid = mFloatLoc.y + mFloatViewHeightHalf;
    }

    private boolean updatePositions()
    {
        int l;
        int i1;
        int j1;
        int k1;
        int l1;
        int i2;
        int i = getFirstVisiblePosition();
        int j = mFirstExpPos;
        View view = getChildAt(j - i);
        if(view == null)
        {
            j = i + getChildCount() / 2;
            view = getChildAt(j - i);
        }
        int k = view.getTop();
        l = view.getHeight();
        i1 = getShuffleEdge(j, k);
        j1 = i1;
        k1 = getDividerHeight();
        l1 = j;
        i2 = k;
        if(mFloatViewMid >= i1) goto _L2; else goto _L1
_L1:
        if(l1 < 0) goto _L4; else goto _L3
_L3:
        int j5;
        l1--;
        j5 = getItemHeight(l1);
        if(l1 != 0) goto _L6; else goto _L5
_L5:
        i1 = i2 - k1 - j5;
_L4:
label0:
        {
            int k2 = getHeaderViewsCount();
            int l2 = getFooterViewsCount();
            int i3 = mFirstExpPos;
            int j3 = mSecondExpPos;
            float f = mSlideFrac;
            if(mAnimate)
            {
                int l3 = Math.abs(i1 - j1);
                int j2;
                boolean flag;
                int k3;
                int i4;
                int j4;
                int k4;
                float f1;
                int l4;
                int i5;
                if(mFloatViewMid < i1)
                {
                    j4 = i1;
                    i4 = j1;
                } else
                {
                    i4 = i1;
                    j4 = j1;
                }
                k4 = (int)(0.5F * mSlideRegionFrac * (float)l3);
                f1 = k4;
                l4 = i4 + k4;
                i5 = j4 - k4;
                if(mFloatViewMid < l4)
                {
                    mFirstExpPos = l1 - 1;
                    mSecondExpPos = l1;
                    mSlideFrac = (0.5F * (float)(l4 - mFloatViewMid)) / f1;
                } else
                if(mFloatViewMid < i5)
                {
                    mFirstExpPos = l1;
                    mSecondExpPos = l1;
                } else
                {
                    mFirstExpPos = l1;
                    mSecondExpPos = l1 + 1;
                    mSlideFrac = 0.5F * (1.0F + (float)(j4 - mFloatViewMid) / f1);
                }
            } else
            {
                mFirstExpPos = l1;
                mSecondExpPos = l1;
            }
            if(mFirstExpPos < k2)
            {
                l1 = k2;
                mFirstExpPos = l1;
                mSecondExpPos = l1;
            } else
            if(mSecondExpPos >= getCount() - l2)
            {
                l1 = -1 + (getCount() - l2);
                mFirstExpPos = l1;
                mSecondExpPos = l1;
            }
            if(mFirstExpPos == i3 && mSecondExpPos == j3)
            {
                k3 = mSlideFrac != f;
                flag = false;
                if(k3 == 0)
                    break label0;
            }
            flag = true;
        }
        if(l1 != mFloatPos)
        {
            if(mDragListener != null)
                mDragListener.drag(mFloatPos - k2, l1 - k2);
            mFloatPos = l1;
            flag = true;
        }
        return flag;
_L6:
        i2 -= j5 + k1;
        i1 = getShuffleEdge(l1, i2);
        if(mFloatViewMid >= i1) goto _L4; else goto _L7
_L7:
        j1 = i1;
          goto _L1
_L2:
        j2 = getCount();
_L9:
        if(l1 < j2)
        {
label1:
            {
                if(l1 != j2 - 1)
                    break label1;
                i1 = l + (i2 + k1);
            }
        }
          goto _L4
        i2 += k1 + l;
        l = getItemHeight(l1 + 1);
        i1 = getShuffleEdge(l1 + 1, i2);
        if(mFloatViewMid < i1) goto _L4; else goto _L8
_L8:
        j1 = i1;
        l1++;
          goto _L9
    }

    private void updateScrollStarts()
    {
        int i = getPaddingTop();
        int j = getHeight() - i - getPaddingBottom();
        float f = j;
        mUpScrollStartYF = (float)i + f * mDragUpScrollStartFrac;
        mDownScrollStartYF = (float)i + f * (1.0F - mDragDownScrollStartFrac);
        mUpScrollStartY = (int)mUpScrollStartYF;
        mDownScrollStartY = (int)mDownScrollStartYF;
        mDragUpScrollHeight = mUpScrollStartYF - (float)i;
        mDragDownScrollHeight = (float)(i + j) - mDownScrollStartYF;
    }

    public void cancelDrag()
    {
label0:
        {
            if(mDragState == 4)
            {
                mDragScroller.stopScrolling(true);
                destroyFloatView();
                clearPositions();
                adjustAllItems();
                if(!mInTouchEvent)
                    break label0;
                mDragState = 3;
            }
            return;
        }
        mDragState = 0;
    }

    protected void dispatchDraw(Canvas canvas)
    {
        dispatchDraw(canvas);
        if(mDragState != 0)
        {
            if(mFirstExpPos != mSrcPos)
                drawDivider(mFirstExpPos, canvas);
            if(mSecondExpPos != mFirstExpPos && mSecondExpPos != mSrcPos)
                drawDivider(mSecondExpPos, canvas);
        }
        if(mFloatView != null)
        {
            int i = mFloatView.getWidth();
            int j = mFloatView.getHeight();
            int k = mFloatLoc.x;
            int l = getWidth();
            if(k < 0)
                k = -k;
            float f;
            int i1;
            if(k < l)
            {
                float f1 = (float)(l - k) / (float)l;
                f = f1 * f1;
            } else
            {
                f = 0.0F;
            }
            i1 = (int)(f * (255F * mCurrFloatAlpha));
            canvas.save();
            canvas.translate(mFloatLoc.x, mFloatLoc.y);
            canvas.clipRect(0, 0, i, j);
            canvas.saveLayerAlpha(0.0F, 0.0F, i, j, i1, 31);
            mFloatView.draw(canvas);
            canvas.restore();
            canvas.restore();
        }
    }

    public float getFloatAlpha()
    {
        return mCurrFloatAlpha;
    }

    public ListAdapter getInputAdapter()
    {
        if(mAdapterWrapper == null)
            return null;
        else
            return mAdapterWrapper.getAdapter();
    }

    public boolean isDragEnabled()
    {
        return mDragEnabled;
    }

    protected void layoutChildren()
    {
        layoutChildren();
        if(mFloatView != null)
        {
            if(mFloatView.isLayoutRequested() && !mFloatViewOnMeasured)
                measureFloatView();
            mFloatView.layout(0, 0, mFloatView.getMeasuredWidth(), mFloatView.getMeasuredHeight());
            mFloatViewOnMeasured = false;
        }
    }

    public boolean listViewIntercepted()
    {
        return mListViewIntercepted;
    }

    public void moveCheckState(int i, int j)
    {
        int k;
        int i1;
        int ai[];
        int ai1[];
        int j1;
        SparseBooleanArray sparsebooleanarray = getCheckedItemPositions();
        k = i;
        int l = j;
        if(j < i)
        {
            k = j;
            l = i;
        }
        i1 = l + 1;
        ai = new int[sparsebooleanarray.size()];
        ai1 = new int[sparsebooleanarray.size()];
        j1 = buildRunList(sparsebooleanarray, k, i1, ai, ai1);
        if(j1 != 1 || ai[0] != ai1[0]) goto _L2; else goto _L1
_L1:
        return;
_L2:
        if(i >= j)
            break; /* Loop/switch isn't completed */
        int l1 = 0;
        while(l1 != j1) 
        {
            setItemChecked(rotate(ai[l1], -1, k, i1), true);
            setItemChecked(rotate(ai1[l1], -1, k, i1), false);
            l1++;
        }
        if(true) goto _L1; else goto _L3
_L3:
        int k1 = 0;
        while(k1 != j1) 
        {
            setItemChecked(ai[k1], false);
            setItemChecked(ai1[k1], true);
            k1++;
        }
        if(true) goto _L1; else goto _L4
_L4:
    }

    public void moveItem(int i, int j)
    {
        if(mDropListener != null)
        {
            int k = getInputAdapter().getCount();
            if(i >= 0 && i < k && j >= 0 && j < k)
                mDropListener.drop(i, j);
        }
    }

    protected boolean onDragTouchEvent(MotionEvent motionevent)
    {
        0xff & motionevent.getAction();
        0xff & motionevent.getAction();
        JVM INSTR tableswitch 1 3: default 44
    //                   1 65
    //                   2 86
    //                   3 46;
           goto _L1 _L2 _L3 _L4
_L1:
        return true;
_L4:
        if(mDragState == 4)
            cancelDrag();
        doActionUpOrCancel();
        continue; /* Loop/switch isn't completed */
_L2:
        if(mDragState == 4)
            stopDrag(false);
        doActionUpOrCancel();
        continue; /* Loop/switch isn't completed */
_L3:
        continueDrag((int)motionevent.getX(), (int)motionevent.getY());
        if(true) goto _L1; else goto _L5
_L5:
    }

    protected void onDraw(Canvas canvas)
    {
        onDraw(canvas);
        if(mTrackDragSort)
            mDragSortTracker.appendState();
    }

    public boolean onInterceptTouchEvent(MotionEvent motionevent)
    {
        if(mDragEnabled) goto _L2; else goto _L1
_L1:
        boolean flag1 = onInterceptTouchEvent(motionevent);
_L4:
        return flag1;
_L2:
        int i;
        saveTouchCoords(motionevent);
        mLastCallWasIntercept = true;
        i = 0xff & motionevent.getAction();
        if(i == 0)
        {
            if(mDragState != 0)
            {
                mIgnoreTouchEvent = true;
                return true;
            }
            mInTouchEvent = true;
        }
        if(mFloatView == null)
            break; /* Loop/switch isn't completed */
        flag1 = true;
_L6:
        if(i == 1 || i == 3)
        {
            mInTouchEvent = false;
            return flag1;
        }
        if(true) goto _L4; else goto _L3
_L3:
        boolean flag = onInterceptTouchEvent(motionevent);
        flag1 = false;
        if(flag)
        {
            mListViewIntercepted = true;
            flag1 = true;
        }
        switch(i)
        {
        case 2: // '\002'
        default:
            if(flag1)
                mCancelMethod = 1;
            else
                mCancelMethod = 2;
            break;

        case 1: // '\001'
        case 3: // '\003'
            doActionUpOrCancel();
            break;
        }
        if(true) goto _L6; else goto _L5
_L5:
    }

    protected void onMeasure(int i, int j)
    {
        onMeasure(i, j);
        if(mFloatView != null)
        {
            if(mFloatView.isLayoutRequested())
                measureFloatView();
            mFloatViewOnMeasured = true;
        }
        mWidthMeasureSpec = i;
    }

    protected void onSizeChanged(int i, int j, int k, int l)
    {
        onSizeChanged(i, j, k, l);
        updateScrollStarts();
    }

    public boolean onTouchEvent(MotionEvent motionevent)
    {
        if(!mIgnoreTouchEvent) goto _L2; else goto _L1
_L1:
        boolean flag1;
        mIgnoreTouchEvent = false;
        flag1 = false;
_L4:
        return flag1;
_L2:
        if(!mDragEnabled)
            return onTouchEvent(motionevent);
        boolean flag = mLastCallWasIntercept;
        mLastCallWasIntercept = false;
        if(!flag)
            saveTouchCoords(motionevent);
        if(mDragState == 4)
        {
            onDragTouchEvent(motionevent);
            return true;
        }
        int i = mDragState;
        flag1 = false;
        if(i == 0)
        {
            boolean flag2 = onTouchEvent(motionevent);
            flag1 = false;
            if(flag2)
                flag1 = true;
        }
        switch(0xff & motionevent.getAction())
        {
        case 2: // '\002'
        default:
            if(flag1)
            {
                mCancelMethod = 1;
                return flag1;
            }
            break;

        case 1: // '\001'
        case 3: // '\003'
            doActionUpOrCancel();
            return flag1;
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    public void removeCheckState(int i)
    {
        SparseBooleanArray sparsebooleanarray = getCheckedItemPositions();
        if(sparsebooleanarray.size() != 0)
        {
            int ai[] = new int[sparsebooleanarray.size()];
            int ai1[] = new int[sparsebooleanarray.size()];
            int j = 1 + sparsebooleanarray.keyAt(-1 + sparsebooleanarray.size());
            int k = buildRunList(sparsebooleanarray, i, j, ai, ai1);
            int l = 0;
            while(l != k) 
            {
                if(ai[l] != i && (ai1[l] >= ai[l] || ai1[l] <= i))
                    setItemChecked(rotate(ai[l], -1, i, j), true);
                setItemChecked(rotate(ai1[l], -1, i, j), false);
                l++;
            }
        }
    }

    public void removeItem(int i)
    {
        mUseRemoveVelocity = false;
        removeItem(i, 0.0F);
    }

    public void removeItem(int i, float f)
    {
        if(mDragState != 0 && mDragState != 4) goto _L2; else goto _L1
_L1:
        if(mDragState == 0)
        {
            mSrcPos = i + getHeaderViewsCount();
            mFirstExpPos = mSrcPos;
            mSecondExpPos = mSrcPos;
            mFloatPos = mSrcPos;
            View view = getChildAt(mSrcPos - getFirstVisiblePosition());
            if(view != null)
                view.setVisibility(4);
        }
        mDragState = 1;
        mRemoveVelocityX = f;
        if(!mInTouchEvent) goto _L4; else goto _L3
_L3:
        mCancelMethod;
        JVM INSTR tableswitch 1 2: default 124
    //                   1 139
    //                   2 151;
           goto _L4 _L5 _L6
_L4:
        if(mRemoveAnimator == null)
            break; /* Loop/switch isn't completed */
        mRemoveAnimator.start();
_L2:
        return;
_L5:
        onTouchEvent(mCancelEvent);
        continue; /* Loop/switch isn't completed */
_L6:
        onInterceptTouchEvent(mCancelEvent);
        if(true) goto _L4; else goto _L7
_L7:
        doRemoveItem(i);
        return;
    }

    public void requestLayout()
    {
        if(!mBlockLayoutRequests)
            requestLayout();
    }

    public volatile void setAdapter(Adapter adapter)
    {
        setAdapter((ListAdapter)adapter);
    }

    public void setAdapter(ListAdapter listadapter)
    {
        if(listadapter != null)
        {
            mAdapterWrapper = new AdapterWrapper(listadapter);
            listadapter.registerDataSetObserver(mObserver);
            if(listadapter instanceof DropListener)
                setDropListener((DropListener)listadapter);
            if(listadapter instanceof DragListener)
                setDragListener((DragListener)listadapter);
            if(listadapter instanceof RemoveListener)
                setRemoveListener((RemoveListener)listadapter);
        } else
        {
            mAdapterWrapper = null;
        }
        setAdapter(mAdapterWrapper);
    }

    public void setDragEnabled(boolean flag)
    {
        mDragEnabled = flag;
    }

    public void setDragListener(DragListener draglistener)
    {
        mDragListener = draglistener;
    }

    public void setDragScrollProfile(DragScrollProfile dragscrollprofile)
    {
        if(dragscrollprofile != null)
            mScrollProfile = dragscrollprofile;
    }

    public void setDragScrollStart(float f)
    {
        setDragScrollStarts(f, f);
    }

    public void setDragScrollStarts(float f, float f1)
    {
        if(f1 > 0.5F)
            mDragDownScrollStartFrac = 0.5F;
        else
            mDragDownScrollStartFrac = f1;
        if(f > 0.5F)
            mDragUpScrollStartFrac = 0.5F;
        else
            mDragUpScrollStartFrac = f;
        if(getHeight() != 0)
            updateScrollStarts();
    }

    public void setDragSortListener(DragSortListener dragsortlistener)
    {
        setDropListener(dragsortlistener);
        setDragListener(dragsortlistener);
        setRemoveListener(dragsortlistener);
    }

    public void setDropListener(DropListener droplistener)
    {
        mDropListener = droplistener;
    }

    public void setFloatAlpha(float f)
    {
        mCurrFloatAlpha = f;
    }

    public void setFloatViewManager(FloatViewManager floatviewmanager)
    {
        mFloatViewManager = floatviewmanager;
    }

    public void setMaxScrollSpeed(float f)
    {
        mMaxScrollSpeed = f;
    }

    public void setRemoveListener(RemoveListener removelistener)
    {
        mRemoveListener = removelistener;
    }

    public boolean startDrag(int i, int j, int k, int l)
    {
        View view;
        if(mInTouchEvent && mFloatViewManager != null)
            if((view = mFloatViewManager.onCreateFloatView(i)) != null)
                return startDrag(i, view, j, k, l);
        return false;
    }

    public boolean startDrag(int i, View view, int j, int k, int l)
    {
        boolean flag = true;
        if(mDragState == 0 && mInTouchEvent && mFloatView == null && view != null && mDragEnabled) goto _L2; else goto _L1
_L1:
        flag = false;
_L4:
        return flag;
_L2:
        if(getParent() != null)
            getParent().requestDisallowInterceptTouchEvent(flag);
        int i1 = i + getHeaderViewsCount();
        mFirstExpPos = i1;
        mSecondExpPos = i1;
        mSrcPos = i1;
        mFloatPos = i1;
        mDragState = 4;
        mDragFlags = 0;
        mDragFlags = j | mDragFlags;
        mFloatView = view;
        measureFloatView();
        mDragDeltaX = k;
        mDragDeltaY = l;
        mDragStartY = mY;
        mFloatLoc.x = mX - mDragDeltaX;
        mFloatLoc.y = mY - mDragDeltaY;
        View view1 = getChildAt(mSrcPos - getFirstVisiblePosition());
        if(view1 != null)
            view1.setVisibility(4);
        if(mTrackDragSort)
            mDragSortTracker.startTracking();
        switch(mCancelMethod)
        {
        default:
            break;

        case 1: // '\001'
            break; /* Loop/switch isn't completed */

        case 2: // '\002'
            break;
        }
        break MISSING_BLOCK_LABEL_273;
_L5:
        requestLayout();
        if(mLiftAnimator != null)
        {
            mLiftAnimator.start();
            return flag;
        }
        if(true) goto _L4; else goto _L3
_L3:
        onTouchEvent(mCancelEvent);
          goto _L5
        onInterceptTouchEvent(mCancelEvent);
          goto _L5
    }

    public boolean stopDrag(boolean flag)
    {
        mUseRemoveVelocity = false;
        return stopDrag(flag, 0.0F);
    }

    public boolean stopDrag(boolean flag, float f)
    {
        if(mFloatView != null)
        {
            mDragScroller.stopScrolling(true);
            if(flag)
                removeItem(mSrcPos - getHeaderViewsCount(), f);
            else
            if(mDropAnimator != null)
                mDropAnimator.start();
            else
                dropFloatView();
            if(mTrackDragSort)
                mDragSortTracker.stopTracking();
            return true;
        } else
        {
            return false;
        }
    }

    public boolean stopDragWithVelocity(boolean flag, float f)
    {
        mUseRemoveVelocity = true;
        return stopDrag(flag, f);
    }

    private static final int DRAGGING = 4;
    public static final int DRAG_NEG_X = 2;
    public static final int DRAG_NEG_Y = 8;
    public static final int DRAG_POS_X = 1;
    public static final int DRAG_POS_Y = 4;
    private static final int DROPPING = 2;
    private static final int IDLE = 0;
    private static final int NO_CANCEL = 0;
    private static final int ON_INTERCEPT_TOUCH_EVENT = 2;
    private static final int ON_TOUCH_EVENT = 1;
    private static final int REMOVING = 1;
    private static final int STOPPED = 3;
    private static final int sCacheSize = 3;
    private AdapterWrapper mAdapterWrapper;
    private boolean mAnimate;
    private boolean mBlockLayoutRequests;
    private MotionEvent mCancelEvent;
    private int mCancelMethod;
    private HeightCache mChildHeightCache;
    private float mCurrFloatAlpha;
    private int mDownScrollStartY;
    private float mDownScrollStartYF;
    private int mDragDeltaX;
    private int mDragDeltaY;
    private float mDragDownScrollHeight;
    private float mDragDownScrollStartFrac;
    private boolean mDragEnabled;
    private int mDragFlags;
    private DragListener mDragListener;
    private DragScroller mDragScroller;
    private DragSortTracker mDragSortTracker;
    private int mDragStartY;
    private int mDragState;
    private float mDragUpScrollHeight;
    private float mDragUpScrollStartFrac;
    private DropAnimator mDropAnimator;
    private DropListener mDropListener;
    private int mFirstExpPos;
    private float mFloatAlpha;
    private Point mFloatLoc;
    private int mFloatPos;
    private View mFloatView;
    private int mFloatViewHeight;
    private int mFloatViewHeightHalf;
    private boolean mFloatViewInvalidated;
    private FloatViewManager mFloatViewManager;
    private int mFloatViewMid;
    private boolean mFloatViewOnMeasured;
    private boolean mIgnoreTouchEvent;
    private boolean mInTouchEvent;
    private int mItemHeightCollapsed;
    private boolean mLastCallWasIntercept;
    private int mLastX;
    private int mLastY;
    private LiftAnimator mLiftAnimator;
    private boolean mListViewIntercepted;
    private float mMaxScrollSpeed;
    private DataSetObserver mObserver;
    private int mOffsetX;
    private int mOffsetY;
    private RemoveAnimator mRemoveAnimator;
    private RemoveListener mRemoveListener;
    private float mRemoveVelocityX;
    private View mSampleViewTypes[];
    private DragScrollProfile mScrollProfile;
    private int mSecondExpPos;
    private float mSlideFrac;
    private float mSlideRegionFrac;
    private int mSrcPos;
    private Point mTouchLoc;
    private boolean mTrackDragSort;
    private int mUpScrollStartY;
    private float mUpScrollStartYF;
    private boolean mUseRemoveVelocity;
    private int mWidthMeasureSpec;
    private int mX;
    private int mY;





/*
    static int access$102(DragSortListView dragsortlistview, int i)
    {
        dragsortlistview.mDragState = i;
        return i;
    }

*/








/*
    static float access$1602(DragSortListView dragsortlistview, float f)
    {
        dragsortlistview.mRemoveVelocityX = f;
        return f;
    }

*/


/*
    static float access$1616(DragSortListView dragsortlistview, float f)
    {
        float f1 = f + dragsortlistview.mRemoveVelocityX;
        dragsortlistview.mRemoveVelocityX = f1;
        return f1;
    }

*/












/*
    static boolean access$2602(DragSortListView dragsortlistview, boolean flag)
    {
        dragsortlistview.mBlockLayoutRequests = flag;
        return flag;
    }

*/







/*
    static int access$302(DragSortListView dragsortlistview, int i)
    {
        dragsortlistview.mDragDeltaY = i;
        return i;
    }

*/








    // Unreferenced inner class com/mobeta/android/dslv/DragSortListView$AdapterWrapper$1

/* anonymous class */
    class AdapterWrapper._cls1 extends DataSetObserver
    {

        public void onChanged()
        {
            notifyDataSetChanged();
        }

        public void onInvalidated()
        {
            notifyDataSetInvalidated();
        }

        final AdapterWrapper this$1;
        final DragSortListView val$this$0;

            
            {
                this$1 = final_adapterwrapper;
                this$0 = DragSortListView.this;
                super();
            }
    }

}
