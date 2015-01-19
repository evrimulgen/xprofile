// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.roadtrippers.adapters;

import android.app.Activity;
import android.content.res.Resources;
import android.view.*;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.ButterKnife;
import com.roadtrippers.RoadTrippersApp;
import com.roadtrippers.adapters.base.InflaterAdapter;
import com.roadtrippers.api.models.Image;
import com.roadtrippers.api.models.Trip;
import com.roadtrippers.util.TripManager;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestCreator;
import dagger.Lazy;

public class TripsAdapter extends InflaterAdapter
{
    class ViewHolder
    {

        void setTrip(Trip trip)
        {
            tripText.setText(trip.display_name.toUpperCase());
            ((Picasso)picassoLazy.get()).load(trip.image.getIphone_thumb_short()).placeholder(0x7f0201aa).error(0x7f0201aa).into(tripIcon);
            boolean flag = trip.equals(((TripManager)tripManagerLazy.get()).getCurrentTrip());
            TextView textview = tripText;
            Resources resources = getResources();
            int i;
            View view;
            int j;
            if(flag)
                i = 0x7f080034;
            else
                i = 0x7f0201be;
            textview.setTextColor(resources.getColor(i));
            view = front;
            if(flag)
                j = 0x7f080043;
            else
                j = 0x7f0201bd;
            view.setBackgroundResource(j);
        }

        View front;
        final TripsAdapter this$0;
        ImageView tripIcon;
        TextView tripText;

        ViewHolder(View view)
        {
            this$0 = TripsAdapter.this;
            super();
            ButterKnife.inject(this, view);
        }
    }


    public TripsAdapter(Activity activity, Trip atrip[])
    {
        super(activity);
        RoadTrippersApp.from(activity).inject(this);
        trips = atrip;
    }

    public int getCount()
    {
        if(trips != null)
            return trips.length;
        else
            return 0;
    }

    public Trip getItem(int i)
    {
        if(i > -1 && i < trips.length)
            return trips[i];
        else
            return null;
    }

    public volatile Object getItem(int i)
    {
        return getItem(i);
    }

    public View getView(int i, View view, ViewGroup viewgroup)
    {
        if(view == null)
        {
            view = inflater.inflate(0x7f030062, viewgroup, false);
            view.setTag(new ViewHolder(view));
        }
        ((ViewHolder)view.getTag()).setTrip(getItem(i));
        return view;
    }

    public void setTrips(Trip atrip[])
    {
        trips = atrip;
        notifyDataSetChanged();
    }

    Lazy picassoLazy;
    Lazy tripManagerLazy;
    Trip trips[];

}
