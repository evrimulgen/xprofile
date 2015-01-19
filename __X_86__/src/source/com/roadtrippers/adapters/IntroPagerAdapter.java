// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.roadtrippers.adapters;

import android.content.Context;
import android.content.res.Resources;
import android.os.Parcelable;
import android.support.v4.app.*;
import com.roadtrippers.fragments.IntroPageFragment;

public class IntroPagerAdapter extends FragmentPagerAdapter
{

    public IntroPagerAdapter(Context context1, FragmentManager fragmentmanager)
    {
        super(fragmentmanager);
        context = context1;
    }

    public int getCount()
    {
        return 5;
    }

    public Fragment getItem(int i)
    {
        return IntroPageFragment.newInstance(context.getResources().getIdentifier((new StringBuilder()).append("intro").append(i + 1).append("_568h").toString(), "drawable", context.getPackageName()));
    }

    public Parcelable saveState()
    {
        return null;
    }

    Context context;
}
