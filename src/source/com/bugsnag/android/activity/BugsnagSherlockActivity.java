// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.bugsnag.android.activity;

import android.os.Bundle;
import com.actionbarsherlock.app.SherlockActivity;
import com.bugsnag.android.Bugsnag;

public class BugsnagSherlockActivity extends SherlockActivity
{

    public BugsnagSherlockActivity()
    {
    }

    protected void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        Bugsnag.onActivityCreate(this);
    }

    protected void onDestroy()
    {
        super.onDestroy();
        Bugsnag.onActivityDestroy(this);
    }

    protected void onPause()
    {
        super.onPause();
        Bugsnag.onActivityPause(this);
    }

    protected void onResume()
    {
        super.onResume();
        Bugsnag.onActivityResume(this);
    }
}
