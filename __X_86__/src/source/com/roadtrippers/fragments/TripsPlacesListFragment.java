// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.roadtrippers.fragments;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.*;
import android.widget.AdapterView;
import android.widget.ListView;
import com.google.gson.JsonObject;
import com.roadtrippers.activities.DetailActivity;
import com.roadtrippers.adapters.TripsPlacesAdapter;
import com.roadtrippers.api.models.*;
import com.roadtrippers.events.*;
import com.roadtrippers.fragments.base.RetainedProgressFragment;
import com.roadtrippers.util.*;
import dagger.Lazy;
import io.segment.android.models.EasyJSONObject;
import se.emilsjolander.stickylistheaders.StickyListHeadersListView;

// Referenced classes of package com.roadtrippers.fragments:
//            SelectedPlaceFragment

public class TripsPlacesListFragment extends RetainedProgressFragment
{

    public TripsPlacesListFragment()
    {
    }

    static boolean isEmpty(Object aobj[])
    {
        return aobj == null || aobj.length <= 0;
    }

    public void addPoi(Poi poi)
    {
        addedPoi = poi;
        onPlacesLoaded(pois);
    }

    public void clearPlaces()
    {
        addedPoi = null;
        onPlacesLoaded(null);
        setSavedPois(null);
    }

    protected TripsPlacesAdapter getListAdapter()
    {
        return (TripsPlacesAdapter)listView.getAdapter();
    }

    protected void onContentCreated(Bundle bundle)
    {
        listView.setEmptyView(emptyView);
        listView.setVerticalScrollBarEnabled(true);
        final TripsPlacesAdapter adapter = new TripsPlacesAdapter(getActivity(), false);
        if(pois != null)
            adapter.setPois(pois);
        if(waypoints != null)
            adapter.setWaypoints(waypoints);
        if(savedPois != null)
            adapter.setSavedPois(savedPois);
        listView.setAdapter(adapter);
        if(android.os.Build.VERSION.SDK_INT > 10)
        {
            SwipeTouchListener swipetouchlistener = new SwipeTouchListener(listView.getWrappedList(), 0x7f090115, 0x7f090114, new com.roadtrippers.util.SwipeTouchListener.OnSwipeListener() {

                public void onSwipe(ListView listview, int i)
                {
                    android.location.Location location = adapter.getLocation(i);
                    Object obj = adapter.getItem(i);
                    SelectedPlaceFragment.openDirections(location, adapter, busLazy, getActivity(), locationManagerLazy);
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

                final TripsPlacesListFragment this$0;
                final TripsPlacesAdapter val$adapter;

            
            {
                this$0 = TripsPlacesListFragment.this;
                adapter = tripsplacesadapter;
                super();
            }
            }
);
            listView.getWrappedList().setOnTouchListener(swipetouchlistener);
            listView.setOnScrollListener(swipetouchlistener.makeScrollListener());
        }
        listView.setOnItemClickListener(new android.widget.AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView adapterview, View view, int i, long l)
            {
                new JsonObject();
                Poi poi = adapter.getPlace(i);
                if(poi != null)
                {
                    EasyJSONObject easyjsonobject = new EasyJSONObject();
                    easyjsonobject.put("poi", poi.id);
                    RTAnalytics.logEvent(view.getContext(), 0x7f0c0068, 0x7f0c0019, easyjsonobject.toString());
                    getActivity().startActivityForResult(DetailActivity.newInstance(getActivity(), ((Serializer)jackson.get()).serialize(poi)), 1006);
                }
            }

            final TripsPlacesListFragment this$0;
            final TripsPlacesAdapter val$adapter;

            
            {
                this$0 = TripsPlacesListFragment.this;
                adapter = tripsplacesadapter;
                super();
            }
        }
);
        setContentShownNoAnimation(true);
    }

    public void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        setRetainInstance(true);
    }

    protected void onCreateContent(Bundle bundle)
    {
        setContentView(0x7f030065);
    }

    public View onCreateView(LayoutInflater layoutinflater, ViewGroup viewgroup, Bundle bundle)
    {
        View view = super.onCreateView(layoutinflater, viewgroup, bundle);
        view.setBackgroundResource(0x7f080034);
        return view;
    }

    public void onLoadFailed(TripLoadFail triploadfail)
    {
        setContentShown(true);
    }

    public void onLocationChanged(LocationChangedEvent locationchangedevent)
    {
        if(getListAdapter().getLocation() == null)
            getListAdapter().setLocation(locationchangedevent.location);
    }

    public void onPlacesLoaded(Poi apoi[])
    {
        pois = apoi;
        if(addedPoi != null && apoi != null)
        {
            pois = new Poi[1 + apoi.length];
            pois[0] = addedPoi;
            for(int i = 0; i < apoi.length; i++)
                pois[i + 1] = apoi[i];

        } else
        if(addedPoi != null && apoi == null)
        {
            Poi apoi1[] = new Poi[1];
            apoi1[0] = addedPoi;
            pois = apoi1;
        }
        getListAdapter().setPois(pois);
        listView.setSelection(0);
        setContentShown(true);
    }

    public void onResultTrip(TripLoadedEvent triploadedevent)
    {
        if(triploadedevent.trip != null)
            waypoints = triploadedevent.trip.waypoints;
        else
            waypoints = null;
        getListAdapter().setWaypoints(waypoints);
        listView.setSelection(0);
        setContentShown(true);
    }

    protected void onRetryButtonClick()
    {
    }

    public void setContentShown(boolean flag)
    {
        if(!flag && (!isEmpty(pois) || !isEmpty(waypoints) || !isEmpty(savedPois)))
        {
            return;
        } else
        {
            super.setContentShown(flag);
            return;
        }
    }

    public void setSavedPois(Poi apoi[])
    {
        savedPois = apoi;
        getListAdapter().setSavedPois(apoi);
        listView.setSelection(0);
        setContentShown(true);
    }

    Poi addedPoi;
    Lazy busLazy;
    View emptyView;
    Lazy jackson;
    StickyListHeadersListView listView;
    Lazy locationManagerLazy;
    Poi pois[];
    Poi savedPois[];
    Waypoint waypoints[];
}
