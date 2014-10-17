// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.handmark.pulltorefresh.library.internal;

import android.graphics.drawable.Drawable;
import android.view.View;

public class ViewCompat
{
    static class SDK11
    {

        public static void setLayerType(View view, int i)
        {
            view.setLayerType(i, null);
        }

        SDK11()
        {
        }
    }

    static class SDK16
    {

        public static void postOnAnimation(View view, Runnable runnable)
        {
            view.postOnAnimation(runnable);
        }

        public static void setBackground(View view, Drawable drawable)
        {
            view.setBackground(drawable);
        }

        SDK16()
        {
        }
    }


    public ViewCompat()
    {
    }

    public static void postOnAnimation(View view, Runnable runnable)
    {
        if(android.os.Build.VERSION.SDK_INT >= 16)
        {
            SDK16.postOnAnimation(view, runnable);
            return;
        } else
        {
            view.postDelayed(runnable, 16L);
            return;
        }
    }

    public static void setBackground(View view, Drawable drawable)
    {
        if(android.os.Build.VERSION.SDK_INT >= 16)
        {
            SDK16.setBackground(view, drawable);
            return;
        } else
        {
            view.setBackgroundDrawable(drawable);
            return;
        }
    }

    public static void setLayerType(View view, int i)
    {
        if(android.os.Build.VERSION.SDK_INT >= 11)
            SDK11.setLayerType(view, i);
    }
}
