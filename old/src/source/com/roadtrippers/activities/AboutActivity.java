// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.roadtrippers.activities;

import android.os.Bundle;
import com.roadtrippers.activities.base.BaseActivity;
import com.roadtrippers.fragments.AboutFragment;

public class AboutActivity extends BaseActivity
{

    public AboutActivity()
    {
    }

    protected void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        setContentFragment(new AboutFragment());
    }
}
