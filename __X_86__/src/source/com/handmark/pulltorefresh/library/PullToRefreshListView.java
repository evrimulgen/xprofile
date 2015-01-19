// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.handmark.pulltorefresh.library;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.*;
import com.handmark.pulltorefresh.library.internal.EmptyViewMethodAccessor;
import com.handmark.pulltorefresh.library.internal.LoadingLayout;

// Referenced classes of package com.handmark.pulltorefresh.library:
//            PullToRefreshAdapterViewBase, LoadingLayoutProxy, OverscrollHelper

public class PullToRefreshListView extends PullToRefreshAdapterViewBase
{
    protected class InternalListView extends ListView
        implements EmptyViewMethodAccessor
    {

        protected void dispatchDraw(Canvas canvas)
        {
            try
            {
                super.dispatchDraw(canvas);
                return;
            }
            catch(IndexOutOfBoundsException indexoutofboundsexception)
            {
                indexoutofboundsexception.printStackTrace();
            }
        }

        public boolean dispatchTouchEvent(MotionEvent motionevent)
        {
            boolean flag;
            try
            {
                flag = super.dispatchTouchEvent(motionevent);
            }
            catch(IndexOutOfBoundsException indexoutofboundsexception)
            {
                indexoutofboundsexception.printStackTrace();
                return false;
            }
            return flag;
        }

        public volatile void setAdapter(Adapter adapter)
        {
            setAdapter((ListAdapter)adapter);
        }

        public void setAdapter(ListAdapter listadapter)
        {
            if(mLvFooterLoadingFrame != null && !mAddedLvFooter)
            {
                addFooterView(mLvFooterLoadingFrame, null, false);
                mAddedLvFooter = true;
            }
            super.setAdapter(listadapter);
        }

        public void setEmptyView(View view)
        {
            PullToRefreshListView.this.setEmptyView(view);
        }

        public void setEmptyViewInternal(View view)
        {
            super.setEmptyView(view);
        }

        private boolean mAddedLvFooter;
        final PullToRefreshListView this$0;

        public InternalListView(Context context, AttributeSet attributeset)
        {
            this$0 = PullToRefreshListView.this;
            super(context, attributeset);
            mAddedLvFooter = false;
        }
    }

    final class InternalListViewSDK9 extends InternalListView
    {

        protected boolean overScrollBy(int i, int j, int k, int l, int i1, int j1, int k1, 
                int l1, boolean flag)
        {
            boolean flag1 = super.overScrollBy(i, j, k, l, i1, j1, k1, l1, flag);
            OverscrollHelper.overScrollBy(PullToRefreshListView.this, i, k, j, l, flag);
            return flag1;
        }

        final PullToRefreshListView this$0;

        public InternalListViewSDK9(Context context, AttributeSet attributeset)
        {
            this$0 = PullToRefreshListView.this;
            super(context, attributeset);
        }
    }


    public PullToRefreshListView(Context context)
    {
        super(context);
    }

    public PullToRefreshListView(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
    }

    public PullToRefreshListView(Context context, PullToRefreshBase.Mode mode)
    {
        super(context, mode);
    }

    public PullToRefreshListView(Context context, PullToRefreshBase.Mode mode, PullToRefreshBase.AnimationStyle animationstyle)
    {
        super(context, mode, animationstyle);
    }

    protected ListView createListView(Context context, AttributeSet attributeset)
    {
        if(android.os.Build.VERSION.SDK_INT >= 9)
            return new InternalListViewSDK9(context, attributeset);
        else
            return new InternalListView(context, attributeset);
    }

    protected LoadingLayoutProxy createLoadingLayoutProxy(boolean flag, boolean flag1)
    {
        LoadingLayoutProxy loadinglayoutproxy = super.createLoadingLayoutProxy(flag, flag1);
        if(mListViewExtrasEnabled)
        {
            PullToRefreshBase.Mode mode = getMode();
            if(flag && mode.showHeaderLoadingLayout())
                loadinglayoutproxy.addLayout(mHeaderLoadingView);
            if(flag1 && mode.showFooterLoadingLayout())
                loadinglayoutproxy.addLayout(mFooterLoadingView);
        }
        return loadinglayoutproxy;
    }

    protected volatile View createRefreshableView(Context context, AttributeSet attributeset)
    {
        return createRefreshableView(context, attributeset);
    }

    protected ListView createRefreshableView(Context context, AttributeSet attributeset)
    {
        ListView listview = createListView(context, attributeset);
        listview.setId(0x102000a);
        return listview;
    }

    public final PullToRefreshBase.Orientation getPullToRefreshScrollDirection()
    {
        return PullToRefreshBase.Orientation.VERTICAL;
    }

    protected void handleStyledAttributes(TypedArray typedarray)
    {
        super.handleStyledAttributes(typedarray);
        mListViewExtrasEnabled = typedarray.getBoolean(14, true);
        if(mListViewExtrasEnabled)
        {
            android.widget.FrameLayout.LayoutParams layoutparams = new android.widget.FrameLayout.LayoutParams(-1, -2, 1);
            FrameLayout framelayout = new FrameLayout(getContext());
            mHeaderLoadingView = createLoadingLayout(getContext(), PullToRefreshBase.Mode.PULL_FROM_START, typedarray);
            mHeaderLoadingView.setVisibility(8);
            framelayout.addView(mHeaderLoadingView, layoutparams);
            ((ListView)mRefreshableView).addHeaderView(framelayout, null, false);
            mLvFooterLoadingFrame = new FrameLayout(getContext());
            mFooterLoadingView = createLoadingLayout(getContext(), PullToRefreshBase.Mode.PULL_FROM_END, typedarray);
            mFooterLoadingView.setVisibility(8);
            mLvFooterLoadingFrame.addView(mFooterLoadingView, layoutparams);
            if(!typedarray.hasValue(13))
                setScrollingWhileRefreshingEnabled(true);
        }
    }

    protected void onRefreshing(boolean flag)
    {
        ListAdapter listadapter = ((ListView)mRefreshableView).getAdapter();
        if(mListViewExtrasEnabled && getShowViewWhileRefreshing() && listadapter != null && !listadapter.isEmpty()) goto _L2; else goto _L1
_L1:
        super.onRefreshing(flag);
_L4:
        return;
_L2:
        LoadingLayout loadinglayout;
        LoadingLayout loadinglayout1;
        LoadingLayout loadinglayout2;
        int i;
        int j;
        super.onRefreshing(false);
        static class _cls1
        {

            static final int $SwitchMap$com$handmark$pulltorefresh$library$PullToRefreshBase$Mode[];

            static 
            {
                $SwitchMap$com$handmark$pulltorefresh$library$PullToRefreshBase$Mode = new int[PullToRefreshBase.Mode.values().length];
                try
                {
                    $SwitchMap$com$handmark$pulltorefresh$library$PullToRefreshBase$Mode[PullToRefreshBase.Mode.MANUAL_REFRESH_ONLY.ordinal()] = 1;
                }
                catch(NoSuchFieldError nosuchfielderror) { }
                try
                {
                    $SwitchMap$com$handmark$pulltorefresh$library$PullToRefreshBase$Mode[PullToRefreshBase.Mode.PULL_FROM_END.ordinal()] = 2;
                }
                catch(NoSuchFieldError nosuchfielderror1) { }
                try
                {
                    $SwitchMap$com$handmark$pulltorefresh$library$PullToRefreshBase$Mode[PullToRefreshBase.Mode.PULL_FROM_START.ordinal()] = 3;
                }
                catch(NoSuchFieldError nosuchfielderror2)
                {
                    return;
                }
            }
        }

        switch(_cls1..SwitchMap.com.handmark.pulltorefresh.library.PullToRefreshBase.Mode[getCurrentMode().ordinal()])
        {
        default:
            loadinglayout = getHeaderLayout();
            loadinglayout1 = mHeaderLoadingView;
            loadinglayout2 = mFooterLoadingView;
            i = 0;
            j = getScrollY() + getHeaderSize();
            break;

        case 1: // '\001'
        case 2: // '\002'
            break; /* Loop/switch isn't completed */
        }
_L5:
        loadinglayout.reset();
        loadinglayout.hideAllViews();
        loadinglayout2.setVisibility(8);
        loadinglayout1.setVisibility(0);
        loadinglayout1.refreshing();
        if(flag)
        {
            disableLoadingLayoutVisibilityChanges();
            setHeaderScroll(j);
            ((ListView)mRefreshableView).setSelection(i);
            smoothScrollTo(0);
            return;
        }
        if(true) goto _L4; else goto _L3
_L3:
        loadinglayout = getFooterLayout();
        loadinglayout1 = mFooterLoadingView;
        loadinglayout2 = mHeaderLoadingView;
        i = -1 + ((ListView)mRefreshableView).getCount();
        j = getScrollY() - getFooterSize();
          goto _L5
        if(true) goto _L4; else goto _L6
_L6:
    }

    protected void onReset()
    {
        int i;
        i = 1;
        if(!mListViewExtrasEnabled)
        {
            super.onReset();
            return;
        }
        _cls1..SwitchMap.com.handmark.pulltorefresh.library.PullToRefreshBase.Mode[getCurrentMode().ordinal()];
        JVM INSTR tableswitch 1 2: default 48
    //                   1 145
    //                   2 145;
           goto _L1 _L2 _L2
_L1:
        LoadingLayout loadinglayout = getHeaderLayout();
        LoadingLayout loadinglayout1 = mHeaderLoadingView;
        int k = -getHeaderSize();
        int l = Math.abs(((ListView)mRefreshableView).getFirstVisiblePosition() - 0);
        int j = 0;
        if(l > i)
        {
            i = 0;
            j = 0;
        }
_L4:
        if(loadinglayout1.getVisibility() == 0)
        {
            loadinglayout.showInvisibleViews();
            loadinglayout1.setVisibility(8);
            if(i != 0 && getState() != PullToRefreshBase.State.MANUAL_REFRESHING)
            {
                ((ListView)mRefreshableView).setSelection(j);
                setHeaderScroll(k);
            }
        }
        super.onReset();
        return;
_L2:
        loadinglayout = getFooterLayout();
        loadinglayout1 = mFooterLoadingView;
        j = -1 + ((ListView)mRefreshableView).getCount();
        k = getFooterSize();
        if(Math.abs(((ListView)mRefreshableView).getLastVisiblePosition() - j) > i)
            i = 0;
        if(true) goto _L4; else goto _L3
_L3:
    }

    private LoadingLayout mFooterLoadingView;
    private LoadingLayout mHeaderLoadingView;
    private boolean mListViewExtrasEnabled;
    private FrameLayout mLvFooterLoadingFrame;

}
