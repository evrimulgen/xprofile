// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.maps.android.heatmaps;

import android.graphics.Color;
import java.util.HashMap;

public class Gradient
{
    private class ColorInterval
    {

        private final int color1;
        private final int color2;
        private final float duration;
        final Gradient this$0;




        private ColorInterval(int i, int j, float f)
        {
            this$0 = Gradient.this;
            super();
            color1 = i;
            color2 = j;
            duration = f;
        }

    }


    public Gradient(int ai[], float af[])
    {
        this(ai, af, 1000);
    }

    public Gradient(int ai[], float af[], int i)
    {
        if(ai.length != af.length)
            throw new IllegalArgumentException("colors and startPoints should be same length");
        if(ai.length == 0)
            throw new IllegalArgumentException("No colors have been defined");
        for(int j = 1; j < af.length; j++)
            if(af[j] <= af[j - 1])
                throw new IllegalArgumentException("startPoints should be in increasing order");

        mColorMapSize = i;
        mColors = new int[ai.length];
        mStartPoints = new float[af.length];
        System.arraycopy(ai, 0, mColors, 0, ai.length);
        System.arraycopy(af, 0, mStartPoints, 0, af.length);
    }

    private HashMap generateColorIntervals()
    {
        HashMap hashmap = new HashMap();
        if(mStartPoints[0] != 0.0F)
        {
            int k = Color.argb(0, Color.red(mColors[0]), Color.green(mColors[0]), Color.blue(mColors[0]));
            hashmap.put(Integer.valueOf(0), new ColorInterval(k, mColors[0], (float)mColorMapSize * mStartPoints[0]));
        }
        for(int i = 1; i < mColors.length; i++)
            hashmap.put(Integer.valueOf((int)((float)mColorMapSize * mStartPoints[i - 1])), new ColorInterval(mColors[i - 1], mColors[i], (float)mColorMapSize * (mStartPoints[i] - mStartPoints[i - 1])));

        if(mStartPoints[-1 + mStartPoints.length] != 1.0F)
        {
            int j = -1 + mStartPoints.length;
            hashmap.put(Integer.valueOf((int)((float)mColorMapSize * mStartPoints[j])), new ColorInterval(mColors[j], mColors[j], (float)mColorMapSize * (1.0F - mStartPoints[j])));
        }
        return hashmap;
    }

    static int interpolateColor(int i, int j, float f)
    {
        int k;
        float af[];
        float af1[];
        k = (int)(f * (float)(Color.alpha(j) - Color.alpha(i)) + (float)Color.alpha(i));
        af = new float[3];
        Color.RGBToHSV(Color.red(i), Color.green(i), Color.blue(i), af);
        af1 = new float[3];
        Color.RGBToHSV(Color.red(j), Color.green(j), Color.blue(j), af1);
        if(af[0] - af1[0] <= 180F) goto _L2; else goto _L1
_L1:
        af1[0] = 360F + af1[0];
_L4:
        float af2[];
        af2 = new float[3];
        for(int l = 0; l < 3; l++)
            af2[l] = f * (af1[l] - af[l]) + af[l];

        break; /* Loop/switch isn't completed */
_L2:
        if(af1[0] - af[0] > 180F)
            af[0] = 360F + af[0];
        if(true) goto _L4; else goto _L3
_L3:
        return Color.HSVToColor(k, af2);
    }

    int[] generateColorMap(double d)
    {
        HashMap hashmap = generateColorIntervals();
        int ai[] = new int[mColorMapSize];
        ColorInterval colorinterval = (ColorInterval)hashmap.get(Integer.valueOf(0));
        int i = 0;
        for(int j = 0; j < mColorMapSize; j++)
        {
            if(hashmap.containsKey(Integer.valueOf(j)))
            {
                colorinterval = (ColorInterval)hashmap.get(Integer.valueOf(j));
                i = j;
            }
            float f = (float)(j - i) / colorinterval.duration;
            ai[j] = interpolateColor(colorinterval.color1, colorinterval.color2, f);
        }

        if(d != 1.0D)
        {
            for(int k = 0; k < mColorMapSize; k++)
            {
                int l = ai[k];
                ai[k] = Color.argb((int)(d * (double)Color.alpha(l)), Color.red(l), Color.green(l), Color.blue(l));
            }

        }
        return ai;
    }

    private static final int DEFAULT_COLOR_MAP_SIZE = 1000;
    public final int mColorMapSize;
    public int mColors[];
    public float mStartPoints[];
}
