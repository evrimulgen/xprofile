// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.roadtrippers.util;

import android.view.View;

public class GalleryZoomOutPageTransformer
    implements android.support.v4.view.ViewPager.PageTransformer
{

    public GalleryZoomOutPageTransformer()
    {
    }

    public void transformPage(View view, float f)
    {
        int i = view.getWidth();
        int j = view.getHeight();
        if(f < -1F)
        {
            view.setAlpha(0.0F);
            return;
        }
        if(f <= 1.0F)
        {
            float f1 = Math.max(0.85F, 1.0F - Math.abs(f));
            float f2 = ((float)j * (1.0F - f1)) / 2.0F;
            float f3 = ((float)i * (1.0F - f1)) / 2.0F;
            float f4;
            if(f < 0.0F)
                view.setTranslationX(f3 - f2 / 2.0F);
            else
                view.setTranslationX(-f3 + f2 / 2.0F);
            view.setScaleX(f1);
            view.setScaleY(f1);
            view.setAlpha(0.5F + 0.5F * ((f1 - 0.85F) / 0.15F));
            f4 = Math.max(0.0F, 1.0F - 2.0F * Math.abs(f));
            view.findViewById(0x7f0900d6).setAlpha(f4);
            return;
        } else
        {
            view.setAlpha(0.0F);
            return;
        }
    }

    private static final float MIN_ALPHA = 0.5F;
    private static final float MIN_SCALE = 0.85F;
}
