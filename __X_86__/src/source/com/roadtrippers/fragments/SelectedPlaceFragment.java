// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.roadtrippers.fragments;

import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.*;
import android.widget.AdapterView;
import android.widget.ListView;
import com.roadtrippers.activities.DetailActivity;
import com.roadtrippers.adapters.TripsPlacesAdapter;
import com.roadtrippers.api.models.Poi;
import com.roadtrippers.api.models.Waypoint;
import com.roadtrippers.events.LocationChangedEvent;
import com.roadtrippers.events.LocationUnavailableEvent;
import com.roadtrippers.fragments.base.BaseFragment;
import com.roadtrippers.util.*;
import com.squareup.otto.Bus;
import dagger.Lazy;
import io.segment.android.models.EasyJSONObject;

// Referenced classes of package com.roadtrippers.fragments:
//            DetailFragment

public class SelectedPlaceFragment extends BaseFragment
{

    public SelectedPlaceFragment()
    {
    }

    private TripsPlacesAdapter getListAdapter()
    {
        return (TripsPlacesAdapter)listView.getAdapter();
    }

    public static SelectedPlaceFragment newInstance(String s, boolean flag)
    {
        SelectedPlaceFragment selectedplacefragment = new SelectedPlaceFragment();
        Bundle bundle = new Bundle();
        if(!flag)
            bundle.putString("place", s);
        else
            bundle.putString("waypoint", s);
        selectedplacefragment.setArguments(bundle);
        return selectedplacefragment;
    }

    public static void openDirections(Location location, TripsPlacesAdapter tripsplacesadapter, Lazy lazy, Context context, Lazy lazy1)
    {
        Location location1 = tripsplacesadapter.getLocation();
        if(location1 == null)
            location1 = Locations.getLastKnownLocation((LocationManager)lazy1.get());
        if(location1 != null && location != null)
        {
            context.startActivity(DetailFragment.createDirectionsIntent(location1, location));
            return;
        } else
        {
            ((Bus)lazy.get()).post(LocationUnavailableEvent.INSTANCE);
            return;
        }
    }

    Poi getPlace()
    {
        return (Poi)((Serializer)jackson.get()).deserialize(getArguments().getString("place"), com/roadtrippers/api/models/Poi);
    }

    Waypoint getWaypoint()
    {
        return (Waypoint)((Serializer)jackson.get()).deserialize(getArguments().getString("waypoint"), com/roadtrippers/api/models/Waypoint);
    }

    public View onCreateView(LayoutInflater layoutinflater, ViewGroup viewgroup, Bundle bundle)
    {
        listView = new ListView(getActivity());
        return listView;
    }

    public void onLocationChanged(LocationChangedEvent locationchangedevent)
    {
        if(getListAdapter().getLocation() == null)
            getListAdapter().setLocation(locationchangedevent.location);
    }

    public void onViewCreated(View view, Bundle bundle)
    {
        super.onViewCreated(view, bundle);
        final TripsPlacesAdapter adapter = new TripsPlacesAdapter(getActivity(), true);
        if(getArguments().containsKey("place"))
        {
            Poi apoi[] = new Poi[1];
            apoi[0] = getPlace();
            adapter.setPois(apoi);
        } else
        {
            Waypoint awaypoint[] = new Waypoint[1];
            awaypoint[0] = getWaypoint();
            adapter.setWaypoints(awaypoint);
        }
        listView.setAdapter(adapter);
        if(android.os.Build.VERSION.SDK_INT > 10)
            listView.setOnTouchListener(new SwipeTouchListener(listView, 0x7f090115, 0x7f090114, new com.roadtrippers.util.SwipeTouchListener.OnSwipeListener() {

                public void onSwipe(ListView listview, int i)
                {
                    Object obj = adapter.getItem(i);
                    SelectedPlaceFragment.openDirections(adapter.getLocation(i), adapter, busLazy, getActivity(), locationManagerLazy);
                    EasyJSONObject easyjsonobject = new EasyJSONObject();
                    String s;
                    if(obj instanceof Poi)
                        s = "poi";
                    else
                        s = "waypoint";
                    easyjsonobject.put("place_type", s);
                    easyjsonobject.put("launched_from", "listview");
                    RTAnalytics.logEvent(getActivity(), 0x7f0c006c, 0x7f0c0023, easyjsonobject.toString());
                }

                final SelectedPlaceFragment this$0;
                final TripsPlacesAdapter val$adapter;

            
            {
                this$0 = SelectedPlaceFragment.this;
                adapter = tripsplacesadapter;
                super();
            }
            }
));
        listView.setOnItemClickListener(new android.widget.AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView adapterview, View view1, int i, long l)
            {
                Poi poi = adapter.getPlace(i);
                if(poi != null)
                    getActivity().startActivityForResult(DetailActivity.newInstance(getActivity(), ((Serializer)jackson.get()).serialize(poi)), 1006);
            }

            final SelectedPlaceFragment this$0;
            final TripsPlacesAdapter val$adapter;

            
            {
                this$0 = SelectedPlaceFragment.this;
                adapter = tripsplacesadapter;
                super();
            }
        }
);
    }

    static final String PLACE = "place";
    static final String WAYPOINT = "waypoint";
    Lazy busLazy;
    Lazy jackson;
    ListView listView;
    Lazy locationManagerLazy;
}
