// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.mobeta.android.dslv;

import android.content.Context;
import android.database.Cursor;
import android.view.*;

// Referenced classes of package com.mobeta.android.dslv:
//            DragSortCursorAdapter

public abstract class ResourceDragSortCursorAdapter extends DragSortCursorAdapter
{

    public ResourceDragSortCursorAdapter(Context context, int i, Cursor cursor)
    {
        super(context, cursor);
        mDropDownLayout = i;
        mLayout = i;
        mInflater = (LayoutInflater)context.getSystemService("layout_inflater");
    }

    public ResourceDragSortCursorAdapter(Context context, int i, Cursor cursor, int j)
    {
        super(context, cursor, j);
        mDropDownLayout = i;
        mLayout = i;
        mInflater = (LayoutInflater)context.getSystemService("layout_inflater");
    }

    public ResourceDragSortCursorAdapter(Context context, int i, Cursor cursor, boolean flag)
    {
        super(context, cursor, flag);
        mDropDownLayout = i;
        mLayout = i;
        mInflater = (LayoutInflater)context.getSystemService("layout_inflater");
    }

    public View newDropDownView(Context context, Cursor cursor, ViewGroup viewgroup)
    {
        return mInflater.inflate(mDropDownLayout, viewgroup, false);
    }

    public View newView(Context context, Cursor cursor, ViewGroup viewgroup)
    {
        return mInflater.inflate(mLayout, viewgroup, false);
    }

    public void setDropDownViewResource(int i)
    {
        mDropDownLayout = i;
    }

    public void setViewResource(int i)
    {
        mLayout = i;
    }

    private int mDropDownLayout;
    private LayoutInflater mInflater;
    private int mLayout;
}
