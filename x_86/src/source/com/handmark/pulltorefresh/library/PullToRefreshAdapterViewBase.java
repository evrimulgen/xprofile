// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.handmark.pulltorefresh.library;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import com.handmark.pulltorefresh.library.internal.EmptyViewMethodAccessor;
import com.handmark.pulltorefresh.library.internal.IndicatorLayout;

// Referenced classes of package com.handmark.pulltorefresh.library:
//            PullToRefreshBase

public abstract class PullToRefreshAdapterViewBase extends PullToRefreshBase
    implements android.widget.AbsListView.OnScrollListener
{

    public PullToRefreshAdapterViewBase(Context context)
    {
        super(context);
        mScrollEmptyView = true;
        ((AbsListView)mRefreshableView).setOnScrollListener(this);
    }

    public PullToRefreshAdapterViewBase(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
        mScrollEmptyView = true;
        ((AbsListView)mRefreshableView).setOnScrollListener(this);
    }

    public PullToRefreshAdapterViewBase(Context context, PullToRefreshBase.Mode mode)
    {
        super(context, mode);
        mScrollEmptyView = true;
        ((AbsListView)mRefreshableView).setOnScrollListener(this);
    }

    public PullToRefreshAdapterViewBase(Context context, PullToRefreshBase.Mode mode, PullToRefreshBase.AnimationStyle animationstyle)
    {
        super(context, mode, animationstyle);
        mScrollEmptyView = true;
        ((AbsListView)mRefreshableView).setOnScrollListener(this);
    }

    private void addIndicatorViews()
    {
        PullToRefreshBase.Mode mode;
        FrameLayout framelayout;
        mode = getMode();
        framelayout = getRefreshableViewWrapper();
        if(!mode.showHeaderLoadingLayout() || mIndicatorIvTop != null) goto _L2; else goto _L1
_L1:
        mIndicatorIvTop = new IndicatorLayout(getContext(), PullToRefreshBase.Mode.PULL_FROM_START);
        android.widget.FrameLayout.LayoutParams layoutparams1 = new android.widget.FrameLayout.LayoutParams(-2, -2);
        layoutparams1.rightMargin = getResources().getDimensionPixelSize(R.dimen.indicator_right_padding);
        layoutparams1.gravity = 53;
        framelayout.addView(mIndicatorIvTop, layoutparams1);
_L8:
        if(!mode.showFooterLoadingLayout() || mIndicatorIvBottom != null) goto _L4; else goto _L3
_L3:
        mIndicatorIvBottom = new IndicatorLayout(getContext(), PullToRefreshBase.Mode.PULL_FROM_END);
        android.widget.FrameLayout.LayoutParams layoutparams = new android.widget.FrameLayout.LayoutParams(-2, -2);
        layoutparams.rightMargin = getResources().getDimensionPixelSize(R.dimen.indicator_right_padding);
        layoutparams.gravity = 85;
        framelayout.addView(mIndicatorIvBottom, layoutparams);
_L6:
        return;
_L2:
        if(!mode.showHeaderLoadingLayout() && mIndicatorIvTop != null)
        {
            framelayout.removeView(mIndicatorIvTop);
            mIndicatorIvTop = null;
        }
        continue; /* Loop/switch isn't completed */
_L4:
        if(mode.showFooterLoadingLayout() || mIndicatorIvBottom == null) goto _L6; else goto _L5
_L5:
        framelayout.removeView(mIndicatorIvBottom);
        mIndicatorIvBottom = null;
        return;
        if(true) goto _L8; else goto _L7
_L7:
    }

    private static android.widget.FrameLayout.LayoutParams convertEmptyViewLayoutParams(android.view.ViewGroup.LayoutParams layoutparams)
    {
        android.widget.FrameLayout.LayoutParams layoutparams1;
label0:
        {
            layoutparams1 = null;
            if(layoutparams != null)
            {
                layoutparams1 = new android.widget.FrameLayout.LayoutParams(layoutparams);
                if(!(layoutparams instanceof android.widget.LinearLayout.LayoutParams))
                    break label0;
                layoutparams1.gravity = ((android.widget.LinearLayout.LayoutParams)layoutparams).gravity;
            }
            return layoutparams1;
        }
        layoutparams1.gravity = 17;
        return layoutparams1;
    }

    private boolean getShowIndicatorInternal()
    {
        return mShowIndicator && isPullToRefreshEnabled();
    }

    private boolean isFirstItemVisible()
    {
        Adapter adapter = ((AbsListView)mRefreshableView).getAdapter();
        if(adapter == null || adapter.isEmpty())
        {
            Log.d("PullToRefresh", "isFirstItemVisible. Empty View.");
            return true;
        }
        if(((AbsListView)mRefreshableView).getFirstVisiblePosition() <= 1)
        {
            View view = ((AbsListView)mRefreshableView).getChildAt(0);
            if(view != null)
                return view.getTop() >= ((AbsListView)mRefreshableView).getTop();
        }
        return false;
    }

    private boolean isLastItemVisible()
    {
        Adapter adapter = ((AbsListView)mRefreshableView).getAdapter();
        if(adapter == null || adapter.isEmpty())
        {
            Log.d("PullToRefresh", "isLastItemVisible. Empty View.");
            return true;
        }
        int i = -1 + ((AbsListView)mRefreshableView).getCount();
        int j = ((AbsListView)mRefreshableView).getLastVisiblePosition();
        Log.d("PullToRefresh", (new StringBuilder()).append("isLastItemVisible. Last Item Position: ").append(i).append(" Last Visible Pos: ").append(j).toString());
        if(j >= i - 1)
        {
            int k = j - ((AbsListView)mRefreshableView).getFirstVisiblePosition();
            View view = ((AbsListView)mRefreshableView).getChildAt(k);
            if(view != null)
                return view.getBottom() <= ((AbsListView)mRefreshableView).getBottom();
        }
        return false;
    }

    private void removeIndicatorViews()
    {
        if(mIndicatorIvTop != null)
        {
            getRefreshableViewWrapper().removeView(mIndicatorIvTop);
            mIndicatorIvTop = null;
        }
        if(mIndicatorIvBottom != null)
        {
            getRefreshableViewWrapper().removeView(mIndicatorIvBottom);
            mIndicatorIvBottom = null;
        }
    }

    private void updateIndicatorViewsVisibility()
    {
        if(mIndicatorIvTop == null) goto _L2; else goto _L1
_L1:
        if(isRefreshing() || !isReadyForPullStart()) goto _L4; else goto _L3
_L3:
        if(!mIndicatorIvTop.isVisible())
            mIndicatorIvTop.show();
_L2:
        if(mIndicatorIvBottom == null) goto _L6; else goto _L5
_L5:
        if(isRefreshing() || !isReadyForPullEnd()) goto _L8; else goto _L7
_L7:
        if(!mIndicatorIvBottom.isVisible())
            mIndicatorIvBottom.show();
_L6:
        return;
_L4:
        if(mIndicatorIvTop.isVisible())
            mIndicatorIvTop.hide();
        continue; /* Loop/switch isn't completed */
_L8:
        if(!mIndicatorIvBottom.isVisible()) goto _L6; else goto _L9
_L9:
        mIndicatorIvBottom.hide();
        return;
        if(true) goto _L2; else goto _L10
_L10:
    }

    public boolean getShowIndicator()
    {
        return mShowIndicator;
    }

    protected void handleStyledAttributes(TypedArray typedarray)
    {
        boolean flag;
        if(!isPullToRefreshOverScrollEnabled())
            flag = true;
        else
            flag = false;
        mShowIndicator = typedarray.getBoolean(5, flag);
    }

    protected boolean isReadyForPullEnd()
    {
        return isLastItemVisible();
    }

    protected boolean isReadyForPullStart()
    {
        return isFirstItemVisible();
    }

    protected void onPullToRefresh()
    {
        super.onPullToRefresh();
        if(!getShowIndicatorInternal()) goto _L2; else goto _L1
_L1:
        static class _cls1
        {

            static final int $SwitchMap$com$handmark$pulltorefresh$library$PullToRefreshBase$Mode[];

            static 
            {
                $SwitchMap$com$handmark$pulltorefresh$library$PullToRefreshBase$Mode = new int[PullToRefreshBase.Mode.values().length];
                try
                {
                    $SwitchMap$com$handmark$pulltorefresh$library$PullToRefreshBase$Mode[PullToRefreshBase.Mode.PULL_FROM_END.ordinal()] = 1;
                }
                catch(NoSuchFieldError nosuchfielderror) { }
                try
                {
                    $SwitchMap$com$handmark$pulltorefresh$library$PullToRefreshBase$Mode[PullToRefreshBase.Mode.PULL_FROM_START.ordinal()] = 2;
                }
                catch(NoSuchFieldError nosuchfielderror1)
                {
                    return;
                }
            }
        }

        _cls1..SwitchMap.com.handmark.pulltorefresh.library.PullToRefreshBase.Mode[getCurrentMode().ordinal()];
        JVM INSTR tableswitch 1 2: default 44
    //                   1 45
    //                   2 53;
           goto _L2 _L3 _L4
_L2:
        return;
_L3:
        mIndicatorIvBottom.pullToRefresh();
        return;
_L4:
        mIndicatorIvTop.pullToRefresh();
        return;
    }

    protected void onRefreshing(boolean flag)
    {
        super.onRefreshing(flag);
        if(getShowIndicatorInternal())
            updateIndicatorViewsVisibility();
    }

    protected void onReleaseToRefresh()
    {
        super.onReleaseToRefresh();
        if(!getShowIndicatorInternal()) goto _L2; else goto _L1
_L1:
        _cls1..SwitchMap.com.handmark.pulltorefresh.library.PullToRefreshBase.Mode[getCurrentMode().ordinal()];
        JVM INSTR tableswitch 1 2: default 44
    //                   1 45
    //                   2 53;
           goto _L2 _L3 _L4
_L2:
        return;
_L3:
        mIndicatorIvBottom.releaseToRefresh();
        return;
_L4:
        mIndicatorIvTop.releaseToRefresh();
        return;
    }

    protected void onReset()
    {
        super.onReset();
        if(getShowIndicatorInternal())
            updateIndicatorViewsVisibility();
    }

    public final void onScroll(AbsListView abslistview, int i, int j, int k)
    {
        Log.d("PullToRefresh", (new StringBuilder()).append("First Visible: ").append(i).append(". Visible Count: ").append(j).append(". Total Items:").append(k).toString());
        if(mOnLastItemVisibleListener != null)
        {
            boolean flag;
            if(k > 0 && i + j >= k - 1)
                flag = true;
            else
                flag = false;
            mLastItemVisible = flag;
        }
        if(getShowIndicatorInternal())
            updateIndicatorViewsVisibility();
        if(mOnScrollListener != null)
            mOnScrollListener.onScroll(abslistview, i, j, k);
    }

    protected void onScrollChanged(int i, int j, int k, int l)
    {
        super.onScrollChanged(i, j, k, l);
        if(mEmptyView != null && !mScrollEmptyView)
            mEmptyView.scrollTo(-i, -j);
    }

    public final void onScrollStateChanged(AbsListView abslistview, int i)
    {
        if(i == 0 && mOnLastItemVisibleListener != null && mLastItemVisible)
            mOnLastItemVisibleListener.onLastItemVisible();
        if(mOnScrollListener != null)
            mOnScrollListener.onScrollStateChanged(abslistview, i);
    }

    public void setAdapter(ListAdapter listadapter)
    {
        ((AdapterView)mRefreshableView).setAdapter(listadapter);
    }

    public final void setEmptyView(View view)
    {
        FrameLayout framelayout = getRefreshableViewWrapper();
        if(view != null)
        {
            view.setClickable(true);
            android.view.ViewParent viewparent = view.getParent();
            if(viewparent != null && (viewparent instanceof ViewGroup))
                ((ViewGroup)viewparent).removeView(view);
            android.widget.FrameLayout.LayoutParams layoutparams = convertEmptyViewLayoutParams(view.getLayoutParams());
            if(layoutparams != null)
                framelayout.addView(view, layoutparams);
            else
                framelayout.addView(view);
        }
        if(mRefreshableView instanceof EmptyViewMethodAccessor)
            ((EmptyViewMethodAccessor)mRefreshableView).setEmptyViewInternal(view);
        else
            ((AbsListView)mRefreshableView).setEmptyView(view);
        mEmptyView = view;
    }

    public void setOnItemClickListener(android.widget.AdapterView.OnItemClickListener onitemclicklistener)
    {
        ((AbsListView)mRefreshableView).setOnItemClickListener(onitemclicklistener);
    }

    public final void setOnLastItemVisibleListener(PullToRefreshBase.OnLastItemVisibleListener onlastitemvisiblelistener)
    {
        mOnLastItemVisibleListener = onlastitemvisiblelistener;
    }

    public final void setOnScrollListener(android.widget.AbsListView.OnScrollListener onscrolllistener)
    {
        mOnScrollListener = onscrolllistener;
    }

    public final void setScrollEmptyView(boolean flag)
    {
        mScrollEmptyView = flag;
    }

    public void setShowIndicator(boolean flag)
    {
        mShowIndicator = flag;
        if(getShowIndicatorInternal())
        {
            addIndicatorViews();
            return;
        } else
        {
            removeIndicatorViews();
            return;
        }
    }

    protected void updateUIForMode()
    {
        super.updateUIForMode();
        if(getShowIndicatorInternal())
        {
            addIndicatorViews();
            return;
        } else
        {
            removeIndicatorViews();
            return;
        }
    }

    private View mEmptyView;
    private IndicatorLayout mIndicatorIvBottom;
    private IndicatorLayout mIndicatorIvTop;
    private boolean mLastItemVisible;
    private PullToRefreshBase.OnLastItemVisibleListener mOnLastItemVisibleListener;
    private android.widget.AbsListView.OnScrollListener mOnScrollListener;
    private boolean mScrollEmptyView;
    private boolean mShowIndicator;
}
