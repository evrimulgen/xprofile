// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.roadtrippers.adapters.base;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.BaseAdapter;

public abstract class InflaterAdapter extends BaseAdapter
{

    public InflaterAdapter(Context context)
    {
        inflater = LayoutInflater.from(context);
    }

    public static void setVisibility(View view, int i)
    {
        if(view != null && view.getVisibility() != i)
            view.setVisibility(i);
    }

    public long getItemId(int i)
    {
        return (long)i;
    }

    protected Resources getResources()
    {
        return inflater.getContext().getResources();
    }

    protected final LayoutInflater inflater;
}
