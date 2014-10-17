// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package de.keyboardsurfer.android.widget.crouton;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;

final class DefaultAnimationsBuilder
{

    private DefaultAnimationsBuilder()
    {
    }

    private static boolean areLastMeasuredAnimationHeightAndCurrentEqual(int i, View view)
    {
        return i == view.getMeasuredHeight();
    }

    private static boolean areLastMeasuredInAnimationHeightAndCurrentEqual(View view)
    {
        return areLastMeasuredAnimationHeightAndCurrentEqual(lastInAnimationHeight, view);
    }

    private static boolean areLastMeasuredOutAnimationHeightAndCurrentEqual(View view)
    {
        return areLastMeasuredAnimationHeightAndCurrentEqual(lastOutAnimationHeight, view);
    }

    static Animation buildDefaultSlideInDownAnimation(View view)
    {
        if(!areLastMeasuredInAnimationHeightAndCurrentEqual(view) || slideInDownAnimation == null)
        {
            slideInDownAnimation = new TranslateAnimation(0.0F, 0.0F, -view.getMeasuredHeight(), 0.0F);
            slideInDownAnimation.setDuration(400L);
            setLastInAnimationHeight(view.getMeasuredHeight());
        }
        return slideInDownAnimation;
    }

    static Animation buildDefaultSlideOutUpAnimation(View view)
    {
        if(!areLastMeasuredOutAnimationHeightAndCurrentEqual(view) || slideOutUpAnimation == null)
        {
            slideOutUpAnimation = new TranslateAnimation(0.0F, 0.0F, 0.0F, -view.getMeasuredHeight());
            slideOutUpAnimation.setDuration(400L);
            setLastOutAnimationHeight(view.getMeasuredHeight());
        }
        return slideOutUpAnimation;
    }

    private static void setLastInAnimationHeight(int i)
    {
        lastInAnimationHeight = i;
    }

    private static void setLastOutAnimationHeight(int i)
    {
        lastOutAnimationHeight = i;
    }

    private static final long DURATION = 400L;
    private static int lastInAnimationHeight;
    private static int lastOutAnimationHeight;
    private static Animation slideInDownAnimation;
    private static Animation slideOutUpAnimation;
}
