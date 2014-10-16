// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.squareup.picasso;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;

public interface Target
{

    public abstract void onBitmapFailed(Drawable drawable);

    public abstract void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom loadedfrom);

    public abstract void onPrepareLoad(Drawable drawable);
}
