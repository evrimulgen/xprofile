// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.handmark.pulltorefresh.library;

import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.FloatMath;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

// Referenced classes of package com.handmark.pulltorefresh.library:
//            PullToRefreshBase, OverscrollHelper

public class PullToRefreshWebView extends PullToRefreshBase
{
    final class InternalWebViewSDK9 extends WebView
    {

        private int getScrollRange()
        {
            return (int)Math.max(0.0F, FloatMath.floor((float)((WebView)mRefreshableView).getContentHeight() * ((WebView)mRefreshableView).getScale()) - (float)(getHeight() - getPaddingBottom() - getPaddingTop()));
        }

        protected boolean overScrollBy(int i, int j, int k, int l, int i1, int j1, int k1, 
                int l1, boolean flag)
        {
            boolean flag1 = super.overScrollBy(i, j, k, l, i1, j1, k1, l1, flag);
            OverscrollHelper.overScrollBy(PullToRefreshWebView.this, i, k, j, l, getScrollRange(), 2, 1.5F, flag);
            return flag1;
        }

        static final int OVERSCROLL_FUZZY_THRESHOLD = 2;
        static final float OVERSCROLL_SCALE_FACTOR = 1.5F;
        final PullToRefreshWebView this$0;

        public InternalWebViewSDK9(Context context, AttributeSet attributeset)
        {
            this$0 = PullToRefreshWebView.this;
            super(context, attributeset);
        }
    }


    public PullToRefreshWebView(Context context)
    {
        super(context);
        defaultWebChromeClient = new WebChromeClient() {

            public void onProgressChanged(WebView webview, int i)
            {
                if(i == 100)
                    onRefreshComplete();
            }

            final PullToRefreshWebView this$0;

            
            {
                this$0 = PullToRefreshWebView.this;
                super();
            }
        }
;
        setOnRefreshListener(defaultOnRefreshListener);
        ((WebView)mRefreshableView).setWebChromeClient(defaultWebChromeClient);
    }

    public PullToRefreshWebView(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
        defaultWebChromeClient = new _cls2();
        setOnRefreshListener(defaultOnRefreshListener);
        ((WebView)mRefreshableView).setWebChromeClient(defaultWebChromeClient);
    }

    public PullToRefreshWebView(Context context, PullToRefreshBase.Mode mode)
    {
        super(context, mode);
        defaultWebChromeClient = new _cls2();
        setOnRefreshListener(defaultOnRefreshListener);
        ((WebView)mRefreshableView).setWebChromeClient(defaultWebChromeClient);
    }

    public PullToRefreshWebView(Context context, PullToRefreshBase.Mode mode, PullToRefreshBase.AnimationStyle animationstyle)
    {
        super(context, mode, animationstyle);
        defaultWebChromeClient = new _cls2();
        setOnRefreshListener(defaultOnRefreshListener);
        ((WebView)mRefreshableView).setWebChromeClient(defaultWebChromeClient);
    }

    protected volatile View createRefreshableView(Context context, AttributeSet attributeset)
    {
        return createRefreshableView(context, attributeset);
    }

    protected WebView createRefreshableView(Context context, AttributeSet attributeset)
    {
        Object obj;
        if(android.os.Build.VERSION.SDK_INT >= 9)
            obj = new InternalWebViewSDK9(context, attributeset);
        else
            obj = new WebView(context, attributeset);
        ((WebView) (obj)).setId(R.id.webview);
        return ((WebView) (obj));
    }

    public final PullToRefreshBase.Orientation getPullToRefreshScrollDirection()
    {
        return PullToRefreshBase.Orientation.VERTICAL;
    }

    protected boolean isReadyForPullEnd()
    {
        float f = FloatMath.floor((float)((WebView)mRefreshableView).getContentHeight() * ((WebView)mRefreshableView).getScale());
        return (float)((WebView)mRefreshableView).getScrollY() >= f - (float)((WebView)mRefreshableView).getHeight();
    }

    protected boolean isReadyForPullStart()
    {
        return ((WebView)mRefreshableView).getScrollY() == 0;
    }

    protected void onPtrRestoreInstanceState(Bundle bundle)
    {
        super.onPtrRestoreInstanceState(bundle);
        ((WebView)mRefreshableView).restoreState(bundle);
    }

    protected void onPtrSaveInstanceState(Bundle bundle)
    {
        super.onPtrSaveInstanceState(bundle);
        ((WebView)mRefreshableView).saveState(bundle);
    }

    private static final PullToRefreshBase.OnRefreshListener defaultOnRefreshListener = new PullToRefreshBase.OnRefreshListener() {

        public void onRefresh(PullToRefreshBase pulltorefreshbase)
        {
            ((WebView)pulltorefreshbase.getRefreshableView()).reload();
        }

    }
;
    private final WebChromeClient defaultWebChromeClient;

}
