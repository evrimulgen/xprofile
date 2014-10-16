// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.android.gms.internal;

import android.graphics.Canvas;
import android.net.Uri;
import android.widget.ImageView;

public final class ec extends ImageView
{

    public void N(int i)
    {
        Bm = i;
    }

    public void d(Uri uri)
    {
        Bl = uri;
    }

    public int dQ()
    {
        return Bm;
    }

    protected void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);
        if(Bn != 0)
            canvas.drawColor(Bn);
    }

    private Uri Bl;
    private int Bm;
    private int Bn;
}
