// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.roadtrippers.fragments;

import android.os.Bundle;
import android.view.*;
import android.widget.ImageView;
import com.roadtrippers.fragments.base.BaseFragment;

public class IntroPageFragment extends BaseFragment
{

    public IntroPageFragment()
    {
    }

    public static IntroPageFragment newInstance(int i)
    {
        IntroPageFragment intropagefragment = new IntroPageFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("resId", i);
        intropagefragment.setArguments(bundle);
        return intropagefragment;
    }

    public View onCreateView(LayoutInflater layoutinflater, ViewGroup viewgroup, Bundle bundle)
    {
        imageView = new ImageView(getActivity());
        imageView.setLayoutParams(new android.widget.FrameLayout.LayoutParams(-1, -1));
        imageView.setScaleType(android.widget.ImageView.ScaleType.CENTER_INSIDE);
        int i = getArguments().getInt("resId");
        if(i != 0)
        {
            imageView.setImageResource(i);
            imageView.setBackgroundResource(0x7f080034);
        }
        return imageView;
    }

    ImageView imageView;
}
