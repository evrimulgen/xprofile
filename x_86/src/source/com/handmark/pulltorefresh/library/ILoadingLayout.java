// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.handmark.pulltorefresh.library;

import android.graphics.Typeface;
import android.graphics.drawable.Drawable;

public interface ILoadingLayout
{

    public abstract void setLastUpdatedLabel(CharSequence charsequence);

    public abstract void setLoadingDrawable(Drawable drawable);

    public abstract void setPullLabel(CharSequence charsequence);

    public abstract void setRefreshingLabel(CharSequence charsequence);

    public abstract void setReleaseLabel(CharSequence charsequence);

    public abstract void setTextTypeface(Typeface typeface);
}
