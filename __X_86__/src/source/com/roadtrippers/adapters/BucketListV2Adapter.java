// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.roadtrippers.adapters;

import android.content.Context;
import android.view.*;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.ButterKnife;
import com.roadtrippers.adapters.base.InflaterAdapter;
import com.roadtrippers.api.models.Bucket;

public class BucketListV2Adapter extends InflaterAdapter
{
    static class ViewHolder
    {

        void setBucket(Bucket bucket)
        {
            name.setText(bucket.getName().toUpperCase());
            ImageView imageview = checkmark;
            int i;
            if(bucket.checked)
                i = 0;
            else
                i = 4;
            InflaterAdapter.setVisibility(imageview, i);
        }

        ImageView checkmark;
        TextView name;

        ViewHolder(View view)
        {
            ButterKnife.inject(this, view);
        }
    }


    public BucketListV2Adapter(Context context, Bucket abucket[])
    {
        super(context);
        buckets = abucket;
    }

    public int getCount()
    {
        if(buckets != null)
            return buckets.length;
        else
            return 0;
    }

    public Bucket getItem(int i)
    {
        return buckets[i];
    }

    public volatile Object getItem(int i)
    {
        return getItem(i);
    }

    public View getView(int i, View view, ViewGroup viewgroup)
    {
        if(view == null)
        {
            view = inflater.inflate(0x7f03001e, viewgroup, false);
            view.setTag(new ViewHolder(view));
        }
        ((ViewHolder)view.getTag()).setBucket(getItem(i));
        return view;
    }

    Bucket buckets[];
}
