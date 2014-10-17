// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.shapes.shapes;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class CustomArrayAdapter extends ArrayAdapter
{

    public CustomArrayAdapter(Context context, Object aobj[])
    {
        super(context, 0x1090008, aobj);
    }

    public View getDropDownView(int i, View view, ViewGroup viewgroup)
    {
        View view1 = super.getView(i, view, viewgroup);
        ((TextView)view1.findViewById(0x1020014)).setTextColor(0xff000000);
        return view1;
    }
}
