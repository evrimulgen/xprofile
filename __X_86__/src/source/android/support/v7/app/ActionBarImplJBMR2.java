// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v7.app;

import android.app.ActionBar;
import android.app.Activity;
import android.graphics.drawable.Drawable;

// Referenced classes of package android.support.v7.app:
//            ActionBarImplJB

public class ActionBarImplJBMR2 extends ActionBarImplJB
{

    public ActionBarImplJBMR2(Activity activity, ActionBar.Callback callback)
    {
        super(activity, callback);
    }

    public void setHomeActionContentDescription(int i)
    {
        mActionBar.setHomeActionContentDescription(i);
    }

    public void setHomeActionContentDescription(CharSequence charsequence)
    {
        mActionBar.setHomeActionContentDescription(charsequence);
    }

    public void setHomeAsUpIndicator(int i)
    {
        mActionBar.setHomeAsUpIndicator(i);
    }

    public void setHomeAsUpIndicator(Drawable drawable)
    {
        mActionBar.setHomeAsUpIndicator(drawable);
    }
}
