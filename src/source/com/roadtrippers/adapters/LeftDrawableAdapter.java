// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.roadtrippers.adapters;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.view.*;
import android.widget.TextView;
import com.roadtrippers.adapters.base.InflaterAdapter;

public class LeftDrawableAdapter extends InflaterAdapter
{

    public LeftDrawableAdapter(Context context, String as[], TypedArray typedarray)
    {
        super(context);
        labels = as;
        drawables = typedarray;
    }

    public int getCount()
    {
        return labels.length;
    }

    public Drawable getDrawable(int i)
    {
        return drawables.getDrawable(i);
    }

    public volatile Object getItem(int i)
    {
        return getItem(i);
    }

    public String getItem(int i)
    {
        return labels[i];
    }

    public View getView(int i, View view, ViewGroup viewgroup)
    {
        if(view == null)
            view = inflater.inflate(0x7f030046, viewgroup, false);
        TextView textview = (TextView)view;
        textview.setText(getItem(i));
        textview.setCompoundDrawablesWithIntrinsicBounds(getDrawable(i), null, null, null);
        return view;
    }

    TypedArray drawables;
    String labels[];
}
