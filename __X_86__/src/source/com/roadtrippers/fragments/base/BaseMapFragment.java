// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.roadtrippers.fragments.base;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import butterknife.ButterKnife;
import com.google.android.gms.maps.*;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.Marker;
import com.google.maps.android.PolyUtil;
import com.roadtrippers.RoadTrippersApp;
import com.roadtrippers.api.models.Group;
import com.roadtrippers.util.Log;
import com.squareup.otto.Bus;
import dagger.Lazy;
import java.util.List;

public abstract class BaseMapFragment extends SupportMapFragment
    implements com.google.android.gms.maps.GoogleMap.OnCameraChangeListener, com.google.android.gms.maps.GoogleMap.OnMarkerClickListener, com.google.android.gms.maps.GoogleMap.OnMapClickListener
{

    public BaseMapFragment()
    {
    }

    protected static List decodePoints(String s)
    {
        return PolyUtil.decode(s);
    }

    protected static void setMarkerIcon(Context context, Marker marker, boolean flag)
    {
        int i;
        Group group = Group.forName(marker.getTitle());
        if(flag)
            i = group.getLargeMarkerRes(context);
        else
            i = group.getSmallMarkerRes(context);
        if(i <= 0)
            break MISSING_BLOCK_LABEL_33;
        marker.setIcon(BitmapDescriptorFactory.fromResource(i));
        return;
        Exception exception;
        exception;
        Object aobj[] = new Object[3];
        aobj[0] = Boolean.valueOf(flag);
        aobj[1] = Integer.valueOf(i);
        aobj[2] = marker.getTitle();
        Log.e("crash setting %b resource %d for group %s", aobj);
        Log.e(exception);
        return;
    }

    protected void init(GoogleMap googlemap)
    {
        googlemap.setMyLocationEnabled(true);
        UiSettings uisettings = googlemap.getUiSettings();
        uisettings.setCompassEnabled(false);
        uisettings.setZoomControlsEnabled(false);
        uisettings.setMyLocationButtonEnabled(false);
        googlemap.setOnCameraChangeListener(this);
        googlemap.setOnMarkerClickListener(this);
        googlemap.setOnMapClickListener(this);
        inited = true;
    }

    public void onActivityCreated(Bundle bundle)
    {
        super.onActivityCreated(null);
        GoogleMap googlemap = getMap();
        if(!inited && googlemap != null)
            init(googlemap);
    }

    public void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        RoadTrippersApp.from(getActivity()).inject(this);
        MapsInitializer.initialize(getActivity().getApplicationContext());
    }

    public void onDestroyView()
    {
        ButterKnife.reset(this);
        super.onDestroyView();
    }

    public void onPause()
    {
        ((Bus)bus.get()).unregister(this);
        super.onPause();
    }

    public void onResume()
    {
        super.onResume();
        GoogleMap googlemap = getMap();
        if(!inited && googlemap != null)
            init(googlemap);
        ((Bus)bus.get()).register(this);
    }

    public void onViewCreated(View view, Bundle bundle)
    {
        super.onViewCreated(view, bundle);
        ButterKnife.inject(this, view);
    }

    Lazy bus;
    boolean inited;
}
