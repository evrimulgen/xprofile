// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.roadtrippers.util;

import android.graphics.Paint;
import android.graphics.Typeface;
import android.text.TextPaint;
import android.text.style.TypefaceSpan;

public class CustomTypefaceSpan extends TypefaceSpan
{

    public CustomTypefaceSpan(Typeface typeface)
    {
        super("sans");
        newType = typeface;
    }

    private static void applyCustomTypeFace(Paint paint, Typeface typeface)
    {
        Typeface typeface1 = paint.getTypeface();
        int i;
        int j;
        if(typeface1 == null)
            i = 0;
        else
            i = typeface1.getStyle();
        j = i & (-1 ^ typeface.getStyle());
        if((j & 1) != 0)
            paint.setFakeBoldText(true);
        if((j & 2) != 0)
            paint.setTextSkewX(-0.25F);
        paint.setTypeface(typeface);
    }

    public void updateDrawState(TextPaint textpaint)
    {
        applyCustomTypeFace(textpaint, newType);
    }

    public void updateMeasureState(TextPaint textpaint)
    {
        applyCustomTypeFace(textpaint, newType);
    }

    private final Typeface newType;
}
