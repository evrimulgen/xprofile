// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.mobeta.android.dslv;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.widget.CursorAdapter;
import android.util.SparseIntArray;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;

public abstract class DragSortCursorAdapter extends CursorAdapter
    implements DragSortListView.DragSortListener
{

    public DragSortCursorAdapter(Context context, Cursor cursor)
    {
        super(context, cursor);
        mListMapping = new SparseIntArray();
        mRemovedCursorPositions = new ArrayList();
    }

    public DragSortCursorAdapter(Context context, Cursor cursor, int i)
    {
        super(context, cursor, i);
        mListMapping = new SparseIntArray();
        mRemovedCursorPositions = new ArrayList();
    }

    public DragSortCursorAdapter(Context context, Cursor cursor, boolean flag)
    {
        super(context, cursor, flag);
        mListMapping = new SparseIntArray();
        mRemovedCursorPositions = new ArrayList();
    }

    private void cleanMapping()
    {
        ArrayList arraylist = new ArrayList();
        int i = mListMapping.size();
        for(int j = 0; j < i; j++)
            if(mListMapping.keyAt(j) == mListMapping.valueAt(j))
                arraylist.add(Integer.valueOf(mListMapping.keyAt(j)));

        int k = arraylist.size();
        for(int l = 0; l < k; l++)
            mListMapping.delete(((Integer)arraylist.get(l)).intValue());

    }

    private void resetMappings()
    {
        mListMapping.clear();
        mRemovedCursorPositions.clear();
    }

    public void changeCursor(Cursor cursor)
    {
        super.changeCursor(cursor);
        resetMappings();
    }

    public void drag(int i, int j)
    {
    }

    public void drop(int i, int j)
    {
        if(i != j)
        {
            int k = mListMapping.get(i, i);
            if(i > j)
            {
                for(int i1 = i; i1 > j; i1--)
                    mListMapping.put(i1, mListMapping.get(i1 - 1, i1 - 1));

            } else
            {
                for(int l = i; l < j; l++)
                    mListMapping.put(l, mListMapping.get(l + 1, l + 1));

            }
            mListMapping.put(j, k);
            cleanMapping();
            notifyDataSetChanged();
        }
    }

    public int getCount()
    {
        return super.getCount() - mRemovedCursorPositions.size();
    }

    public int getCursorPosition(int i)
    {
        return mListMapping.get(i, i);
    }

    public ArrayList getCursorPositions()
    {
        ArrayList arraylist = new ArrayList();
        for(int i = 0; i < getCount(); i++)
            arraylist.add(Integer.valueOf(mListMapping.get(i, i)));

        return arraylist;
    }

    public View getDropDownView(int i, View view, ViewGroup viewgroup)
    {
        return super.getDropDownView(mListMapping.get(i, i), view, viewgroup);
    }

    public Object getItem(int i)
    {
        return super.getItem(mListMapping.get(i, i));
    }

    public long getItemId(int i)
    {
        return super.getItemId(mListMapping.get(i, i));
    }

    public int getListPosition(int i)
    {
        if(mRemovedCursorPositions.contains(Integer.valueOf(i)))
        {
            i = -1;
        } else
        {
            int j = mListMapping.indexOfValue(i);
            if(j >= 0)
                return mListMapping.keyAt(j);
        }
        return i;
    }

    public View getView(int i, View view, ViewGroup viewgroup)
    {
        return super.getView(mListMapping.get(i, i), view, viewgroup);
    }

    public void remove(int i)
    {
        int j = mListMapping.get(i, i);
        if(!mRemovedCursorPositions.contains(Integer.valueOf(j)))
            mRemovedCursorPositions.add(Integer.valueOf(j));
        int k = getCount();
        for(int l = i; l < k; l++)
            mListMapping.put(l, mListMapping.get(l + 1, l + 1));

        mListMapping.delete(k);
        cleanMapping();
        notifyDataSetChanged();
    }

    public void reset()
    {
        resetMappings();
        notifyDataSetChanged();
    }

    public Cursor swapCursor(Cursor cursor)
    {
        Cursor cursor1 = super.swapCursor(cursor);
        resetMappings();
        return cursor1;
    }

    public static final int REMOVED = -1;
    private SparseIntArray mListMapping;
    private ArrayList mRemovedCursorPositions;
}
