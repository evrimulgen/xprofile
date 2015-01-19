// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.roadtrippers.fragments;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.roadtrippers.adapters.GalleryPagerAdapter;
import com.roadtrippers.api.models.GalleryItem;
import com.roadtrippers.fragments.base.BaseFragment;
import com.roadtrippers.util.GalleryZoomOutPageTransformer;
import com.roadtrippers.util.Serializer;
import dagger.Lazy;

public class GalleryFragment extends BaseFragment
{

    public GalleryFragment()
    {
    }

    public static GalleryFragment newInstance(GalleryItem agalleryitem[])
    {
        GalleryFragment galleryfragment = new GalleryFragment();
        Bundle bundle = new Bundle();
        try
        {
            bundle.putString("POI", (new ObjectMapper()).writeValueAsString(agalleryitem));
        }
        catch(JsonProcessingException jsonprocessingexception)
        {
            jsonprocessingexception.printStackTrace();
        }
        galleryfragment.setArguments(bundle);
        return galleryfragment;
    }

    public com.roadtrippers.api.models.GalleryItem.GalleryImage getCurrentImage()
    {
        return adapter.getGalleryItem(pager.getCurrentItem()).getImage();
    }

    public View onCreateView(LayoutInflater layoutinflater, ViewGroup viewgroup, Bundle bundle)
    {
        items = (GalleryItem[])((Serializer)jackson.get()).deserialize(getArguments().getString("POI"), [Lcom/roadtrippers/api/models/GalleryItem;);
        return layoutinflater.inflate(0x7f030038, viewgroup, false);
    }

    public void onViewCreated(View view, Bundle bundle)
    {
        super.onViewCreated(view, bundle);
        adapter = new GalleryPagerAdapter(getChildFragmentManager(), items);
        pager.setAdapter(adapter);
        if(android.os.Build.VERSION.SDK_INT >= 11)
            pager.setPageTransformer(false, new GalleryZoomOutPageTransformer());
    }

    private static final String POI = "POI";
    private GalleryPagerAdapter adapter;
    GalleryItem items[];
    Lazy jackson;
    ViewPager pager;
}
