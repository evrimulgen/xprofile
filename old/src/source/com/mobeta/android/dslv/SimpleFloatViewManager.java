// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.mobeta.android.dslv;

import android.graphics.Bitmap;
import android.graphics.Point;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

public class SimpleFloatViewManager
    implements DragSortListView.FloatViewManager
{

    public SimpleFloatViewManager(ListView listview)
    {
        mFloatBGColor = 0xff000000;
        mListView = listview;
    }

    public View onCreateFloatView(int i)
    {
        View view = mListView.getChildAt((i + mListView.getHeaderViewsCount()) - mListView.getFirstVisiblePosition());
        if(view == null)
            return null;
        view.setPressed(false);
        view.setDrawingCacheEnabled(true);
        mFloatBitmap = Bitmap.createBitmap(view.getDrawingCache());
        view.setDrawingCacheEnabled(false);
        if(mImageView == null)
            mImageView = new ImageView(mListView.getContext());
        mImageView.setBackgroundColor(mFloatBGColor);
        mImageView.setPadding(0, 0, 0, 0);
        mImageView.setImageBitmap(mFloatBitmap);
        mImageView.setLayoutParams(new android.view.ViewGroup.LayoutParams(view.getWidth(), view.getHeight()));
        return mImageView;
    }

    public void onDestroyFloatView(View view)
    {
        ((ImageView)view).setImageDrawable(null);
        mFloatBitmap.recycle();
        mFloatBitmap = null;
    }

    public void onDragFloatView(View view, Point point, Point point1)
    {
    }

    public void setBackgroundColor(int i)
    {
        mFloatBGColor = i;
    }

    private int mFloatBGColor;
    private Bitmap mFloatBitmap;
    private ImageView mImageView;
    private ListView mListView;
}
