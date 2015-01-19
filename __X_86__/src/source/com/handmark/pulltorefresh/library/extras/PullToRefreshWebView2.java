// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.handmark.pulltorefresh.library.extras;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.webkit.WebView;
import com.handmark.pulltorefresh.library.PullToRefreshWebView;
import java.util.concurrent.atomic.AtomicBoolean;

public class PullToRefreshWebView2 extends PullToRefreshWebView
{
    final class JsValueCallback
    {

        public void isReadyForPullDownResponse(boolean flag)
        {
            mIsReadyForPullDown.set(flag);
        }

        public void isReadyForPullUpResponse(boolean flag)
        {
            mIsReadyForPullUp.set(flag);
        }

        final PullToRefreshWebView2 this$0;

        JsValueCallback()
        {
            this$0 = PullToRefreshWebView2.this;
            super();
        }
    }


    public PullToRefreshWebView2(Context context)
    {
        super(context);
        mIsReadyForPullDown = new AtomicBoolean(false);
        mIsReadyForPullUp = new AtomicBoolean(false);
    }

    public PullToRefreshWebView2(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
        mIsReadyForPullDown = new AtomicBoolean(false);
        mIsReadyForPullUp = new AtomicBoolean(false);
    }

    public PullToRefreshWebView2(Context context, com.handmark.pulltorefresh.library.PullToRefreshBase.Mode mode)
    {
        super(context, mode);
        mIsReadyForPullDown = new AtomicBoolean(false);
        mIsReadyForPullUp = new AtomicBoolean(false);
    }

    protected volatile View createRefreshableView(Context context, AttributeSet attributeset)
    {
        return createRefreshableView(context, attributeset);
    }

    protected WebView createRefreshableView(Context context, AttributeSet attributeset)
    {
        WebView webview = super.createRefreshableView(context, attributeset);
        mJsCallback = new JsValueCallback();
        webview.addJavascriptInterface(mJsCallback, "ptr");
        return webview;
    }

    protected boolean isReadyForPullEnd()
    {
        ((WebView)getRefreshableView()).loadUrl("javascript:isReadyForPullUp();");
        return mIsReadyForPullUp.get();
    }

    protected boolean isReadyForPullStart()
    {
        ((WebView)getRefreshableView()).loadUrl("javascript:isReadyForPullDown();");
        return mIsReadyForPullDown.get();
    }

    static final String DEF_JS_READY_PULL_DOWN_CALL = "javascript:isReadyForPullDown();";
    static final String DEF_JS_READY_PULL_UP_CALL = "javascript:isReadyForPullUp();";
    static final String JS_INTERFACE_PKG = "ptr";
    private final AtomicBoolean mIsReadyForPullDown;
    private final AtomicBoolean mIsReadyForPullUp;
    private JsValueCallback mJsCallback;


}
