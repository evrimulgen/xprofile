// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.roadtrippers.anim;

import android.view.View;
import com.nineoldandroids.animation.IntEvaluator;

public class HeightEvaluator extends IntEvaluator
{

    public HeightEvaluator(View view1)
    {
        view = view1;
    }

    public static int getHeight(View view1)
    {
        return ((android.widget.FrameLayout.LayoutParams)view1.getLayoutParams()).height;
    }

    public static void setHeight(View view1, int i)
    {
        android.widget.FrameLayout.LayoutParams layoutparams = (android.widget.FrameLayout.LayoutParams)view1.getLayoutParams();
        layoutparams.height = i;
        view1.setLayoutParams(layoutparams);
    }

    public Integer evaluate(float f, Integer integer, Integer integer1)
    {
        int i = super.evaluate(f, integer, integer1).intValue();
        setHeight(view, i);
        return Integer.valueOf(i);
    }

    public volatile Object evaluate(float f, Object obj, Object obj1)
    {
        return evaluate(f, (Integer)obj, (Integer)obj1);
    }

    View view;
}
