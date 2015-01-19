// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v7.internal.view;

import android.content.Context;
import android.view.ActionMode;

// Referenced classes of package android.support.v7.internal.view:
//            ActionModeWrapper

public class ActionModeWrapperJB extends ActionModeWrapper
{
    public static class CallbackWrapper extends ActionModeWrapper.CallbackWrapper
    {

        protected ActionModeWrapper createActionModeWrapper(Context context, ActionMode actionmode)
        {
            return new ActionModeWrapperJB(context, actionmode);
        }

        public CallbackWrapper(Context context, android.support.v7.view.ActionMode.Callback callback)
        {
            super(context, callback);
        }
    }


    public ActionModeWrapperJB(Context context, ActionMode actionmode)
    {
        super(context, actionmode);
    }

    public boolean getTitleOptionalHint()
    {
        return mWrappedObject.getTitleOptionalHint();
    }

    public boolean isTitleOptional()
    {
        return mWrappedObject.isTitleOptional();
    }

    public void setTitleOptionalHint(boolean flag)
    {
        mWrappedObject.setTitleOptionalHint(flag);
    }
}
