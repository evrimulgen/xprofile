// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v7.internal.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ActionMode;
import android.view.View;
import android.widget.LinearLayout;

public class NativeActionModeAwareLayout extends LinearLayout
{
    public static interface OnActionModeForChildListener
    {

        public abstract android.view.ActionMode.Callback onActionModeForChild(android.view.ActionMode.Callback callback);
    }


    public NativeActionModeAwareLayout(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
    }

    public void setActionModeForChildListener(OnActionModeForChildListener onactionmodeforchildlistener)
    {
        mActionModeForChildListener = onactionmodeforchildlistener;
    }

    public ActionMode startActionModeForChild(View view, android.view.ActionMode.Callback callback)
    {
        if(mActionModeForChildListener != null)
            callback = mActionModeForChildListener.onActionModeForChild(callback);
        return super.startActionModeForChild(view, callback);
    }

    private OnActionModeForChildListener mActionModeForChildListener;
}
