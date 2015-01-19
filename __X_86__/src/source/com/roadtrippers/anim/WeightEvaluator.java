// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.roadtrippers.anim;

import android.view.View;
import com.nineoldandroids.animation.FloatEvaluator;

public class WeightEvaluator extends FloatEvaluator
{

    public WeightEvaluator(View view1)
    {
        view = view1;
    }

    public static float getWeight(View view1)
    {
        return ((android.widget.LinearLayout.LayoutParams)view1.getLayoutParams()).weight;
    }

    public static void setWeight(View view1, float f)
    {
        android.widget.LinearLayout.LayoutParams layoutparams = (android.widget.LinearLayout.LayoutParams)view1.getLayoutParams();
        layoutparams.weight = f;
        view1.setLayoutParams(layoutparams);
    }

    public Float evaluate(float f, Number number, Number number1)
    {
        Float float1 = super.evaluate(f, number, number1);
        setWeight(view, float1.floatValue());
        return float1;
    }

    public volatile Object evaluate(float f, Object obj, Object obj1)
    {
        return evaluate(f, (Number)obj, (Number)obj1);
    }

    View view;
}
