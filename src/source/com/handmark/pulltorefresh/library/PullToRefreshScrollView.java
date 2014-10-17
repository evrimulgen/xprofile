// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.handmark.pulltorefresh.library;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ScrollView;

// Referenced classes of package com.handmark.pulltorefresh.library:
//            PullToRefreshBase, OverscrollHelper

public class PullToRefreshScrollView extends PullToRefreshBase
{
    final class InternalScrollViewSDK9 extends ScrollView
    {

        private int getScrollRange()
        {
            int i = getChildCount();
            int j = 0;
            if(i > 0)
                j = Math.max(0, getChildAt(0).getHeight() - (getHeight() - getPaddingBottom() - getPaddingTop()));
            return j;
        }

        protected boolean overScrollBy(int i, int j, int k, int l, int i1, int j1, int k1, 
                int l1, boolean flag)
        {
            boolean flag1 = super.overScrollBy(i, j, k, l, i1, j1, k1, l1, flag);
            OverscrollHelper.overScrollBy(PullToRefreshScrollView.this, i, k, j, l, getScrollRange(), flag);
            return flag1;
        }

        final PullToRefreshScrollView this$0;

        public InternalScrollViewSDK9(Context context, AttributeSet attributeset)
        {
            this$0 = PullToRefreshScrollView.this;
            super(context, attributeset);
        }
    }


    public PullToRefreshScrollView(Context context)
    {
        super(context);
    }

    public PullToRefreshScrollView(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
    }

    public PullToRefreshScrollView(Context context, PullToRefreshBase.Mode mode)
    {
        super(context, mode);
    }

    public PullToRefreshScrollView(Context context, PullToRefreshBase.Mode mode, PullToRefreshBase.AnimationStyle animationstyle)
    {
        super(context, mode, animationstyle);
    }

    protected volatile View createRefreshableView(Context context, AttributeSet attributeset)
    {
        return createRefreshableView(context, attributeset);
    }

    protected ScrollView createRefreshableView(Context context, AttributeSet attributeset)
    {
        Object obj;
        if(android.os.Build.VERSION.SDK_INT >= 9)
            obj = new InternalScrollViewSDK9(context, attributeset);
        else
            obj = new ScrollView(context, attributeset);
        ((ScrollView) (obj)).setId(R.id.scrollview);
        return ((ScrollView) (obj));
    }

    public final PullToRefreshBase.Orientation getPullToRefreshScrollDirection()
    {
        return PullToRefreshBase.Orientation.VERTICAL;
    }

    protected boolean isReadyForPullEnd()
    {
        View view = ((ScrollView)mRefreshableView).getChildAt(0);
        if(view != null)
            return ((ScrollView)mRefreshableView).getScrollY() >= view.getHeight() - getHeight();
        else
            return false;
    }

    protected boolean isReadyForPullStart()
    {
        return ((ScrollView)mRefreshableView).getScrollY() == 0;
    }
}
