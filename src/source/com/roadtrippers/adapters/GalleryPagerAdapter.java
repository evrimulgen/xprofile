// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.roadtrippers.adapters;

import android.support.v4.app.*;
import com.roadtrippers.api.models.GalleryItem;
import com.roadtrippers.fragments.GalleryItemFragment;

public class GalleryPagerAdapter extends FragmentStatePagerAdapter
{

    public GalleryPagerAdapter(FragmentManager fragmentmanager, GalleryItem agalleryitem[])
    {
        super(fragmentmanager);
        items = agalleryitem;
    }

    public int getCount()
    {
        return items.length;
    }

    public GalleryItem getGalleryItem(int i)
    {
        return items[i];
    }

    public Fragment getItem(int i)
    {
        return GalleryItemFragment.newInstance(items[i]);
    }

    GalleryItem items[];
}
