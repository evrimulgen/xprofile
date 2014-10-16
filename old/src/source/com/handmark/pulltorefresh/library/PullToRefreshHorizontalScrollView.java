// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.handmark.pulltorefresh.library;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.HorizontalScrollView;

// Referenced classes of package com.handmark.pulltorefresh.library:
//            PullToRefreshBase, OverscrollHelper

public class PullToRefreshHorizontalScrollView extends PullToRefreshBase
{
    final class InternalHorizontalScrollViewSDK9 extends HorizontalScrollView
    {

        private int getScrollRange()
        {
            int i = getChildCount();
            int j = 0;
            if(i > 0)
                j = Math.max(0, getChildAt(0).getWidth() - (getWidth() - getPaddingLeft() - getPaddingRight()));
            return j;
        }

        protected boolean overScrollBy(int i, int j, int k, int l, int i1, int j1, int k1, 
                int l1, boolean flag)
        {
            boolean flag1 = super.overScrollBy(i, j, k, l, i1, j1, k1, l1, flag);
            OverscrollHelper.overScrollBy(PullToRefreshHorizontalScrollView.this, i, k, j, l, getScrollRange(), flag);
            return flag1;
        }

        final PullToRefreshHorizontalScrollView this$0;

        public InternalHorizontalScrollViewSDK9(Context context, AttributeSet attributeset)
        {
            this$0 = PullToRefreshHorizontalScrollView.this;
            super(context, attributeset);
        }
    }


    public PullToRefreshHorizontalScrollView(Context context)
    {
        super(context);
    }

    public PullToRefreshHorizontalScrollView(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
    }

    public PullToRefreshHorizontalScrollView(Context context, PullToRefreshBase.Mode mode)
    {
        super(context, mode);
    }

    public PullToRefreshHorizontalScrollView(Context context, PullToRefreshBase.Mode mode, PullToRefreshBase.AnimationStyle animationstyle)
    {
        super(context, mode, animationstyle);
    }

    protected volatile View createRefreshableView(Context context, AttributeSet attributeset)
    {
        return createRefreshableView(context, attributeset);
    }

    protected HorizontalScrollView createRefreshableView(Context context, AttributeSet attributeset)
    {
        Object obj;
        if(android.os.Build.VERSION.SDK_INT >= 9)
            obj = new InternalHorizontalScrollViewSDK9(context, attributeset);
        else
            obj = new HorizontalScrollView(context, attributeset);
        ((HorizontalScrollView) (obj)).setId(R.id.scrollview);
        return ((HorizontalScrollView) (obj));
    }

    public final PullToRefreshBase.Orientation getPullToRefreshScrollDirection()
    {
        return PullToRefreshBase.Orientation.HORIZONTAL;
    }

    protected boolean isReadyForPullEnd()
    {
        View view = ((HorizontalScrollView)mRefreshableView).getChildAt(0);
        if(view != null)
            return ((HorizontalScrollView)mRefreshableView).getScrollX() >= view.getWidth() - getWidth();
        else
            return false;
    }

    protected boolean isReadyForPullStart()
    {
        return ((HorizontalScrollView)mRefreshableView).getScrollX() == 0;
    }
}
