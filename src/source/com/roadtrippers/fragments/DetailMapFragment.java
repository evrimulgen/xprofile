// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.roadtrippers.fragments;

import android.os.Bundle;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMapOptions;
import com.google.android.gms.maps.model.*;
import com.roadtrippers.api.models.Group;
import com.roadtrippers.api.models.Poi;
import com.roadtrippers.fragments.base.BaseMapFragment;
import com.roadtrippers.util.Serializer;
import dagger.Lazy;

public class DetailMapFragment extends BaseMapFragment
{

    public DetailMapFragment()
    {
    }

    private Poi getPlace()
    {
        return (Poi)((Serializer)jackson.get()).deserialize(getArguments().getString("place"), com/roadtrippers/api/models/Poi);
    }

    public static DetailMapFragment newInstance(String s, GoogleMapOptions googlemapoptions)
    {
        DetailMapFragment detailmapfragment = new DetailMapFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable("MapOptions", googlemapoptions);
        bundle.putString("place", s);
        detailmapfragment.setArguments(bundle);
        return detailmapfragment;
    }

    public void onActivityCreated(Bundle bundle)
    {
        super.onActivityCreated(bundle);
        Poi poi = getPlace();
        MarkerOptions markeroptions = (new MarkerOptions()).position(poi.getLatLng());
        int i = Group.forName(poi.group).getSmallMarkerRes(getActivity());
        if(i > 0)
            markeroptions.icon(BitmapDescriptorFactory.fromResource(i));
        if(getMap() != null)
            getMap().addMarker(markeroptions);
    }

    public void onCameraChange(CameraPosition cameraposition)
    {
    }

    public void onMapClick(LatLng latlng)
    {
    }

    public boolean onMarkerClick(Marker marker)
    {
        return false;
    }

    public static final String PLACE = "place";
    Lazy jackson;
}
