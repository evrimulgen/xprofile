// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.maps.android.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.widget.TextView;

public class SquareTextView extends TextView
{

    public SquareTextView(Context context)
    {
        super(context);
        mOffsetTop = 0;
        mOffsetLeft = 0;
    }

    public SquareTextView(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
        mOffsetTop = 0;
        mOffsetLeft = 0;
    }

    public SquareTextView(Context context, AttributeSet attributeset, int i)
    {
        super(context, attributeset, i);
        mOffsetTop = 0;
        mOffsetLeft = 0;
    }

    public void draw(Canvas canvas)
    {
        canvas.translate(mOffsetLeft / 2, mOffsetTop / 2);
        super.draw(canvas);
    }

    protected void onMeasure(int i, int j)
    {
        super.onMeasure(i, j);
        int k = getMeasuredWidth();
        int l = getMeasuredHeight();
        int i1 = Math.max(k, l);
        if(k > l)
        {
            mOffsetTop = k - l;
            mOffsetLeft = 0;
        } else
        {
            mOffsetTop = 0;
            mOffsetLeft = l - k;
        }
        setMeasuredDimension(i1, i1);
    }

    private int mOffsetLeft;
    private int mOffsetTop;
}
