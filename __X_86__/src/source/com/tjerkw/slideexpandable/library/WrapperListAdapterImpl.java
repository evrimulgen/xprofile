// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.tjerkw.slideexpandable.library;

import android.database.DataSetObserver;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

public abstract class WrapperListAdapterImpl extends BaseAdapter
    implements WrapperListAdapter
{

    public WrapperListAdapterImpl(ListAdapter listadapter)
    {
        wrapped = listadapter;
    }

    public boolean areAllItemsEnabled()
    {
        return wrapped.areAllItemsEnabled();
    }

    public int getCount()
    {
        return wrapped.getCount();
    }

    public Object getItem(int i)
    {
        return wrapped.getItem(i);
    }

    public long getItemId(int i)
    {
        return wrapped.getItemId(i);
    }

    public int getItemViewType(int i)
    {
        return wrapped.getItemViewType(i);
    }

    public View getView(int i, View view, ViewGroup viewgroup)
    {
        return wrapped.getView(i, view, viewgroup);
    }

    public int getViewTypeCount()
    {
        return wrapped.getViewTypeCount();
    }

    public ListAdapter getWrappedAdapter()
    {
        return wrapped;
    }

    public boolean hasStableIds()
    {
        return wrapped.hasStableIds();
    }

    public boolean isEmpty()
    {
        return wrapped.isEmpty();
    }

    public boolean isEnabled(int i)
    {
        return wrapped.isEnabled(i);
    }

    public void registerDataSetObserver(DataSetObserver datasetobserver)
    {
        wrapped.registerDataSetObserver(datasetobserver);
    }

    public void unregisterDataSetObserver(DataSetObserver datasetobserver)
    {
        wrapped.unregisterDataSetObserver(datasetobserver);
    }

    protected ListAdapter wrapped;
}
