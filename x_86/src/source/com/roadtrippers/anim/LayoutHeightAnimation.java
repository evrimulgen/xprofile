// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.roadtrippers.anim;

import android.view.View;
import android.view.animation.*;

public class LayoutHeightAnimation extends Animation
{

    public LayoutHeightAnimation(View view1, int i)
    {
        view = view1;
        setDuration(300L);
        setInterpolator(new AccelerateDecelerateInterpolator());
        targetHeight = i;
    }

    protected void applyTransformation(float f, Transformation transformation)
    {
        android.widget.LinearLayout.LayoutParams layoutparams = (android.widget.LinearLayout.LayoutParams)view.getLayoutParams();
        if(targetHeight > initialHeight)
            layoutparams.height = (int)(f * (float)(targetHeight - initialHeight) + (float)initialHeight);
        else
            layoutparams.height = (int)((float)initialHeight - f * (float)(initialHeight - targetHeight));
        view.requestLayout();
    }

    public void initialize(int i, int j, int k, int l)
    {
        super.initialize(i, j, k, l);
        initialHeight = j;
    }

    public boolean willChangeBounds()
    {
        return true;
    }

    int initialHeight;
    int targetHeight;
    View view;
}
