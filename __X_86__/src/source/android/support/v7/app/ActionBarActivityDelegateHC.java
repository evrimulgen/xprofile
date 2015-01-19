// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v7.app;


// Referenced classes of package android.support.v7.app:
//            ActionBarActivityDelegateBase, ActionBarImplHC, ActionBarActivity, ActionBar

class ActionBarActivityDelegateHC extends ActionBarActivityDelegateBase
{

    ActionBarActivityDelegateHC(ActionBarActivity actionbaractivity)
    {
        super(actionbaractivity);
    }

    public ActionBar createSupportActionBar()
    {
        ensureSubDecor();
        return new ActionBarImplHC(mActivity, mActivity);
    }
}
