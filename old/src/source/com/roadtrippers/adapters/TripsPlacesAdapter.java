// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.roadtrippers.adapters;

import android.content.Context;
import android.graphics.Color;
import android.location.Location;
import android.view.*;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.ButterKnife;
import com.roadtrippers.RoadTrippersApp;
import com.roadtrippers.adapters.base.InflaterAdapter;
import com.roadtrippers.api.models.*;
import com.roadtrippers.fragments.CategoriesFragment;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestCreator;
import dagger.Lazy;
import se.emilsjolander.stickylistheaders.StickyListHeadersAdapter;

public class TripsPlacesAdapter extends InflaterAdapter
    implements StickyListHeadersAdapter
{
    class ViewHolder
    {

        void setPlace(Poi poi)
        {
            title.setText(poi.name);
            String s = poi.subtitle;
            if(s != null)
            {
                Object aobj1[];
                String s2;
                if(s.charAt(0) == 'j' || s.charAt(0) == 'p' || s.charAt(0) == 'f')
                    s2 = (new StringBuilder()).append("  ").append(s).toString();
                else
                    s2 = (new StringBuilder()).append(" ").append(s).toString();
                subTitle.setText(s2);
            }
            if(poi.image != null)
                ((Picasso)picassoLazy.get()).load(poi.image.getIphone_thumb_short()).placeholder(0x7f0201aa).error(0x7f0201aa).into(thumb);
            else
                thumb.setImageResource(0x7f0201aa);
            circle.setImageResource(Group.forName(poi.group).getCircleRes(circle.getContext()));
            if(location != null)
            {
                double d = 0.00062137D * (double)location.distanceTo(poi.getLocation());
                String s1;
                if(d >= 10D)
                {
                    aobj1 = new Object[1];
                    aobj1[0] = Double.valueOf(d);
                    s1 = String.format("%.0f mi", aobj1);
                } else
                {
                    Object aobj[] = new Object[1];
                    aobj[0] = Double.valueOf(d);
                    s1 = String.format("%.1f mi", aobj);
                }
                distance.setText(s1);
                InflaterAdapter.setVisibility(distance, 0);
            } else
            {
                InflaterAdapter.setVisibility(distance, 8);
            }
            InflaterAdapter.setVisibility(cornerImage, 4);
            InflaterAdapter.setVisibility(cornerText, 4);
        }

        void setWayPoint(Waypoint waypoint)
        {
            static class _cls1
            {

                static final int $SwitchMap$com$roadtrippers$api$requests$WaypointRequest$Type[];

                static 
                {
                    $SwitchMap$com$roadtrippers$api$requests$WaypointRequest$Type = new int[com.roadtrippers.api.requests.WaypointRequest.Type.values().length];
                    try
                    {
                        $SwitchMap$com$roadtrippers$api$requests$WaypointRequest$Type[com.roadtrippers.api.requests.WaypointRequest.Type.poi.ordinal()] = 1;
                    }
                    catch(NoSuchFieldError nosuchfielderror) { }
                    try
                    {
                        $SwitchMap$com$roadtrippers$api$requests$WaypointRequest$Type[com.roadtrippers.api.requests.WaypointRequest.Type.geo.ordinal()] = 2;
                    }
                    catch(NoSuchFieldError nosuchfielderror1) { }
                    try
                    {
                        $SwitchMap$com$roadtrippers$api$requests$WaypointRequest$Type[com.roadtrippers.api.requests.WaypointRequest.Type.via.ordinal()] = 3;
                    }
                    catch(NoSuchFieldError nosuchfielderror2)
                    {
                        return;
                    }
                }
            }

            _cls1..SwitchMap.com.roadtrippers.api.requests.WaypointRequest.Type[waypoint.type.ordinal()];
            JVM INSTR tableswitch 1 3: default 36
        //                       1 88
        //                       2 99
        //                       3 99;
               goto _L1 _L2 _L3 _L3
_L1:
            break; /* Loop/switch isn't completed */
_L3:
            break MISSING_BLOCK_LABEL_99;
_L4:
            if(!selected)
            {
                int i = CategoriesFragment.indexOf(waypoint, waypoints);
                Location location1;
                double d;
                Object aobj[];
                String s;
                Object aobj1[];
                if(i == 0)
                    cornerText.setText("S");
                else
                if(i == -1 + waypoints.length)
                    cornerText.setText("F");
                else
                if(i > 0)
                    cornerText.setText(String.valueOf(i));
                InflaterAdapter.setVisibility(cornerImage, 0);
                InflaterAdapter.setVisibility(cornerText, 0);
            }
            return;
_L2:
            setPlace(waypoint.poi);
              goto _L4
            title.setText(waypoint.getCellText());
            title.setSelected(true);
            subTitle.setText(waypoint.getSubText());
            subTitle.setSelected(true);
            thumb.setImageResource(waypoint.getImageResource());
            circle.setImageResource(0);
            location1 = waypoint.getAndroidLocation();
            if(location != null && location1 != null)
            {
                d = 0.00062137D * (double)location.distanceTo(location1);
                if(d >= 10D)
                {
                    aobj1 = new Object[1];
                    aobj1[0] = Double.valueOf(d);
                    s = String.format("%.0f mi", aobj1);
                } else
                {
                    aobj = new Object[1];
                    aobj[0] = Double.valueOf(d);
                    s = String.format("%.1f mi", aobj);
                }
                distance.setText(s);
                InflaterAdapter.setVisibility(distance, 0);
            } else
            {
                InflaterAdapter.setVisibility(distance, 8);
            }
              goto _L4
        }

        ImageView circle;
        ImageView cornerImage;
        TextView cornerText;
        TextView distance;
        TextView subTitle;
        final TripsPlacesAdapter this$0;
        ImageView thumb;
        TextView title;

        ViewHolder(View view)
        {
            this$0 = TripsPlacesAdapter.this;
            super();
            ButterKnife.inject(this, view);
        }
    }


    public TripsPlacesAdapter(Context context, boolean flag)
    {
        super(context);
        RoadTrippersApp.from(context).inject(this);
        selected = flag;
    }

    public int getCount()
    {
        return getSavedPlacesCount() + getWaypointsCount() + getPlacesCount();
    }

    public long getHeaderId(int i)
    {
        if(i < getSavedPlacesCount())
            return 0L;
        return i >= getSavedPlacesCount() + getWaypointsCount() ? 2L : 1L;
    }

    public View getHeaderView(int i, View view, ViewGroup viewgroup)
    {
        if(view == null)
            view = inflater.inflate(0x7f030064, viewgroup, false);
        ImageView imageview = (ImageView)view.findViewById(0x7f090141);
        switch((int)getHeaderId(i))
        {
        default:
            return view;

        case 0: // '\0'
            imageview.setImageResource(0x7f020116);
            view.setBackgroundColor(Color.parseColor("#e5d513"));
            return view;

        case 1: // '\001'
            imageview.setImageResource(0x7f020118);
            view.setBackgroundColor(Color.parseColor("#abce2b"));
            return view;

        case 2: // '\002'
            imageview.setImageResource(0x7f020117);
            break;
        }
        view.setBackgroundColor(Color.parseColor("#e5d513"));
        return view;
    }

    public Object getItem(int i)
    {
        if(i < getCount())
        {
            if(i < getSavedPlacesCount())
                return savedPois[i];
            if(i < getSavedPlacesCount() + getWaypointsCount())
                return waypoints[i - getSavedPlacesCount()];
            else
                return pois[i - getSavedPlacesCount() - getWaypointsCount()];
        } else
        {
            return null;
        }
    }

    public Location getLocation()
    {
        return location;
    }

    public Location getLocation(int i)
    {
        Object obj = getItem(i);
        if(obj instanceof Poi)
            return ((Poi)obj).getLocation();
        else
            return ((Waypoint)obj).getAndroidLocation();
    }

    public Poi getPlace(int i)
    {
        Object obj = getItem(i);
        if(obj != null)
        {
            if(obj instanceof Poi)
                return (Poi)obj;
            else
                return ((Waypoint)obj).poi;
        } else
        {
            return null;
        }
    }

    int getPlacesCount()
    {
        if(pois != null)
            return pois.length;
        else
            return 0;
    }

    int getSavedPlacesCount()
    {
        if(savedPois != null)
            return savedPois.length;
        else
            return 0;
    }

    public View getView(int i, View view, ViewGroup viewgroup)
    {
        ViewHolder viewholder;
        Object obj;
label0:
        {
            if(view == null)
            {
                view = inflater.inflate(0x7f030052, viewgroup, false);
                view.setTag(new ViewHolder(view));
            }
            viewholder = (ViewHolder)view.getTag();
            obj = getItem(i);
            if(obj != null)
            {
                if(!(obj instanceof Poi))
                    break label0;
                viewholder.setPlace((Poi)obj);
            }
            return view;
        }
        viewholder.setWayPoint((Waypoint)obj);
        return view;
    }

    int getWaypointsCount()
    {
        if(waypoints != null)
            return waypoints.length;
        else
            return 0;
    }

    public void setLocation(Location location1)
    {
        location = location1;
        notifyDataSetChanged();
    }

    public void setPois(Poi apoi[])
    {
        pois = apoi;
        notifyDataSetChanged();
    }

    public void setSavedPois(Poi apoi[])
    {
        savedPois = apoi;
        notifyDataSetChanged();
    }

    public void setWaypoints(Waypoint awaypoint[])
    {
        waypoints = awaypoint;
        notifyDataSetChanged();
    }

    Poi addedPoi;
    Location location;
    Lazy picassoLazy;
    Poi pois[];
    Poi savedPois[];
    final boolean selected;
    Waypoint waypoints[];
}
