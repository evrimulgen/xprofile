// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v7.app;


// Referenced classes of package android.support.v7.app:
//            ActionBarActivityDelegateJB, ActionBarImplJBMR2, ActionBarActivity, ActionBar

class ActionBarActivityDelegateJBMR2 extends ActionBarActivityDelegateJB
{

    ActionBarActivityDelegateJBMR2(ActionBarActivity actionbaractivity)
    {
        super(actionbaractivity);
    }

    public ActionBar createSupportActionBar()
    {
        return new ActionBarImplJBMR2(mActivity, mActivity);
    }
}
