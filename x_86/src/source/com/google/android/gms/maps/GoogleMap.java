// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.android.gms.maps;

import android.graphics.Bitmap;
import android.location.Location;
import android.os.RemoteException;
import android.view.View;
import com.google.android.gms.dynamic.b;
import com.google.android.gms.dynamic.c;
import com.google.android.gms.internal.er;
import com.google.android.gms.maps.internal.IGoogleMapDelegate;
import com.google.android.gms.maps.internal.g;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.GroundOverlay;
import com.google.android.gms.maps.model.GroundOverlayOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.gms.maps.model.RuntimeRemoteException;
import com.google.android.gms.maps.model.TileOverlay;
import com.google.android.gms.maps.model.TileOverlayOptions;
import com.google.android.gms.maps.model.internal.d;

// Referenced classes of package com.google.android.gms.maps:
//            CameraUpdate, Projection, UiSettings, LocationSource

public final class GoogleMap
{
    public static interface CancelableCallback
    {

        public abstract void onCancel();

        public abstract void onFinish();
    }

    public static interface InfoWindowAdapter
    {

        public abstract View getInfoContents(Marker marker);

        public abstract View getInfoWindow(Marker marker);
    }

    public static interface OnCameraChangeListener
    {

        public abstract void onCameraChange(CameraPosition cameraposition);
    }

    public static interface OnInfoWindowClickListener
    {

        public abstract void onInfoWindowClick(Marker marker);
    }

    public static interface OnMapClickListener
    {

        public abstract void onMapClick(LatLng latlng);
    }

    public static interface OnMapLoadedCallback
    {

        public abstract void onMapLoaded();
    }

    public static interface OnMapLongClickListener
    {

        public abstract void onMapLongClick(LatLng latlng);
    }

    public static interface OnMarkerClickListener
    {

        public abstract boolean onMarkerClick(Marker marker);
    }

    public static interface OnMarkerDragListener
    {

        public abstract void onMarkerDrag(Marker marker);

        public abstract void onMarkerDragEnd(Marker marker);

        public abstract void onMarkerDragStart(Marker marker);
    }

    public static interface OnMyLocationButtonClickListener
    {

        public abstract boolean onMyLocationButtonClick();
    }

    public static interface OnMyLocationChangeListener
    {

        public abstract void onMyLocationChange(Location location);
    }

    public static interface SnapshotReadyCallback
    {

        public abstract void onSnapshotReady(Bitmap bitmap);
    }

    private static final class a extends com.google.android.gms.maps.internal.b.a
    {

        public void onCancel()
        {
            Pb.onCancel();
        }

        public void onFinish()
        {
            Pb.onFinish();
        }

        private final CancelableCallback Pb;

        a(CancelableCallback cancelablecallback)
        {
            Pb = cancelablecallback;
        }
    }


    protected GoogleMap(IGoogleMapDelegate igooglemapdelegate)
    {
        OK = (IGoogleMapDelegate)er.f(igooglemapdelegate);
    }

    public final Circle addCircle(CircleOptions circleoptions)
    {
        Circle circle;
        try
        {
            circle = new Circle(OK.addCircle(circleoptions));
        }
        catch(RemoteException remoteexception)
        {
            throw new RuntimeRemoteException(remoteexception);
        }
        return circle;
    }

    public final GroundOverlay addGroundOverlay(GroundOverlayOptions groundoverlayoptions)
    {
        com.google.android.gms.maps.model.internal.c c1;
        GroundOverlay groundoverlay;
        try
        {
            c1 = OK.addGroundOverlay(groundoverlayoptions);
        }
        catch(RemoteException remoteexception)
        {
            throw new RuntimeRemoteException(remoteexception);
        }
        if(c1 == null)
            break MISSING_BLOCK_LABEL_28;
        groundoverlay = new GroundOverlay(c1);
        return groundoverlay;
        return null;
    }

    public final Marker addMarker(MarkerOptions markeroptions)
    {
        d d;
        Marker marker;
        try
        {
            d = OK.addMarker(markeroptions);
        }
        catch(RemoteException remoteexception)
        {
            throw new RuntimeRemoteException(remoteexception);
        }
        if(d == null)
            break MISSING_BLOCK_LABEL_28;
        marker = new Marker(d);
        return marker;
        return null;
    }

    public final Polygon addPolygon(PolygonOptions polygonoptions)
    {
        Polygon polygon;
        try
        {
            polygon = new Polygon(OK.addPolygon(polygonoptions));
        }
        catch(RemoteException remoteexception)
        {
            throw new RuntimeRemoteException(remoteexception);
        }
        return polygon;
    }

    public final Polyline addPolyline(PolylineOptions polylineoptions)
    {
        Polyline polyline;
        try
        {
            polyline = new Polyline(OK.addPolyline(polylineoptions));
        }
        catch(RemoteException remoteexception)
        {
            throw new RuntimeRemoteException(remoteexception);
        }
        return polyline;
    }

    public final TileOverlay addTileOverlay(TileOverlayOptions tileoverlayoptions)
    {
        com.google.android.gms.maps.model.internal.f f;
        TileOverlay tileoverlay;
        try
        {
            f = OK.addTileOverlay(tileoverlayoptions);
        }
        catch(RemoteException remoteexception)
        {
            throw new RuntimeRemoteException(remoteexception);
        }
        if(f == null)
            break MISSING_BLOCK_LABEL_28;
        tileoverlay = new TileOverlay(f);
        return tileoverlay;
        return null;
    }

    public final void animateCamera(CameraUpdate cameraupdate)
    {
        try
        {
            OK.animateCamera(cameraupdate.gK());
            return;
        }
        catch(RemoteException remoteexception)
        {
            throw new RuntimeRemoteException(remoteexception);
        }
    }

    public final void animateCamera(CameraUpdate cameraupdate, int i, CancelableCallback cancelablecallback)
    {
        IGoogleMapDelegate igooglemapdelegate;
        b b;
        Object obj;
        try
        {
            igooglemapdelegate = OK;
            b = cameraupdate.gK();
        }
        catch(RemoteException remoteexception)
        {
            throw new RuntimeRemoteException(remoteexception);
        }
        if(cancelablecallback != null)
            break MISSING_BLOCK_LABEL_32;
        obj = null;
        igooglemapdelegate.animateCameraWithDurationAndCallback(b, i, ((com.google.android.gms.maps.internal.b) (obj)));
        return;
        obj = new a(cancelablecallback);
        break MISSING_BLOCK_LABEL_19;
    }

    public final void animateCamera(CameraUpdate cameraupdate, CancelableCallback cancelablecallback)
    {
        IGoogleMapDelegate igooglemapdelegate;
        b b;
        Object obj;
        try
        {
            igooglemapdelegate = OK;
            b = cameraupdate.gK();
        }
        catch(RemoteException remoteexception)
        {
            throw new RuntimeRemoteException(remoteexception);
        }
        if(cancelablecallback != null)
            break MISSING_BLOCK_LABEL_31;
        obj = null;
        igooglemapdelegate.animateCameraWithCallback(b, ((com.google.android.gms.maps.internal.b) (obj)));
        return;
        obj = new a(cancelablecallback);
        break MISSING_BLOCK_LABEL_19;
    }

    public final void clear()
    {
        try
        {
            OK.clear();
            return;
        }
        catch(RemoteException remoteexception)
        {
            throw new RuntimeRemoteException(remoteexception);
        }
    }

    IGoogleMapDelegate gM()
    {
        return OK;
    }

    public final CameraPosition getCameraPosition()
    {
        CameraPosition cameraposition;
        try
        {
            cameraposition = OK.getCameraPosition();
        }
        catch(RemoteException remoteexception)
        {
            throw new RuntimeRemoteException(remoteexception);
        }
        return cameraposition;
    }

    public final int getMapType()
    {
        int i;
        try
        {
            i = OK.getMapType();
        }
        catch(RemoteException remoteexception)
        {
            throw new RuntimeRemoteException(remoteexception);
        }
        return i;
    }

    public final float getMaxZoomLevel()
    {
        float f;
        try
        {
            f = OK.getMaxZoomLevel();
        }
        catch(RemoteException remoteexception)
        {
            throw new RuntimeRemoteException(remoteexception);
        }
        return f;
    }

    public final float getMinZoomLevel()
    {
        float f;
        try
        {
            f = OK.getMinZoomLevel();
        }
        catch(RemoteException remoteexception)
        {
            throw new RuntimeRemoteException(remoteexception);
        }
        return f;
    }

    public final Location getMyLocation()
    {
        Location location;
        try
        {
            location = OK.getMyLocation();
        }
        catch(RemoteException remoteexception)
        {
            throw new RuntimeRemoteException(remoteexception);
        }
        return location;
    }

    public final Projection getProjection()
    {
        Projection projection;
        try
        {
            projection = new Projection(OK.getProjection());
        }
        catch(RemoteException remoteexception)
        {
            throw new RuntimeRemoteException(remoteexception);
        }
        return projection;
    }

    public final UiSettings getUiSettings()
    {
        UiSettings uisettings;
        try
        {
            if(OL == null)
                OL = new UiSettings(OK.getUiSettings());
            uisettings = OL;
        }
        catch(RemoteException remoteexception)
        {
            throw new RuntimeRemoteException(remoteexception);
        }
        return uisettings;
    }

    public final boolean isBuildingsEnabled()
    {
        boolean flag;
        try
        {
            flag = OK.isBuildingsEnabled();
        }
        catch(RemoteException remoteexception)
        {
            throw new RuntimeRemoteException(remoteexception);
        }
        return flag;
    }

    public final boolean isIndoorEnabled()
    {
        boolean flag;
        try
        {
            flag = OK.isIndoorEnabled();
        }
        catch(RemoteException remoteexception)
        {
            throw new RuntimeRemoteException(remoteexception);
        }
        return flag;
    }

    public final boolean isMyLocationEnabled()
    {
        boolean flag;
        try
        {
            flag = OK.isMyLocationEnabled();
        }
        catch(RemoteException remoteexception)
        {
            throw new RuntimeRemoteException(remoteexception);
        }
        return flag;
    }

    public final boolean isTrafficEnabled()
    {
        boolean flag;
        try
        {
            flag = OK.isTrafficEnabled();
        }
        catch(RemoteException remoteexception)
        {
            throw new RuntimeRemoteException(remoteexception);
        }
        return flag;
    }

    public final void moveCamera(CameraUpdate cameraupdate)
    {
        try
        {
            OK.moveCamera(cameraupdate.gK());
            return;
        }
        catch(RemoteException remoteexception)
        {
            throw new RuntimeRemoteException(remoteexception);
        }
    }

    public final void setBuildingsEnabled(boolean flag)
    {
        try
        {
            OK.setBuildingsEnabled(flag);
            return;
        }
        catch(RemoteException remoteexception)
        {
            throw new RuntimeRemoteException(remoteexception);
        }
    }

    public final boolean setIndoorEnabled(boolean flag)
    {
        boolean flag1;
        try
        {
            flag1 = OK.setIndoorEnabled(flag);
        }
        catch(RemoteException remoteexception)
        {
            throw new RuntimeRemoteException(remoteexception);
        }
        return flag1;
    }

    public final void setInfoWindowAdapter(InfoWindowAdapter infowindowadapter)
    {
        if(infowindowadapter == null)
            try
            {
                OK.setInfoWindowAdapter(null);
                return;
            }
            catch(RemoteException remoteexception)
            {
                throw new RuntimeRemoteException(remoteexception);
            }
        OK.setInfoWindowAdapter(new com.google.android.gms.maps.internal.d.a(infowindowadapter) {

            public b f(d d)
            {
                return c.h(OZ.getInfoWindow(new Marker(d)));
            }

            public b g(d d)
            {
                return c.h(OZ.getInfoContents(new Marker(d)));
            }

            final GoogleMap ON;
            final InfoWindowAdapter OZ;

            
            {
                ON = GoogleMap.this;
                OZ = infowindowadapter;
                super();
            }
        }
);
        return;
    }

    public final void setLocationSource(LocationSource locationsource)
    {
        if(locationsource == null)
            try
            {
                OK.setLocationSource(null);
                return;
            }
            catch(RemoteException remoteexception)
            {
                throw new RuntimeRemoteException(remoteexception);
            }
        OK.setLocationSource(new com.google.android.gms.maps.internal.ILocationSourceDelegate.a(locationsource) {

            public void activate(g g)
            {
                OM.activate(new LocationSource.OnLocationChangedListener(this, g) {

                    public void onLocationChanged(Location location)
                    {
                        try
                        {
                            OO.g(c.h(location));
                            return;
                        }
                        catch(RemoteException remoteexception)
                        {
                            throw new RuntimeRemoteException(remoteexception);
                        }
                    }

                    final g OO;
                    final _cls1 OP;

            
            {
                OP = _pcls1;
                OO = g1;
                super();
            }
                }
);
            }

            public void deactivate()
            {
                OM.deactivate();
            }

            final LocationSource OM;
            final GoogleMap ON;

            
            {
                ON = GoogleMap.this;
                OM = locationsource;
                super();
            }
        }
);
        return;
    }

    public final void setMapType(int i)
    {
        try
        {
            OK.setMapType(i);
            return;
        }
        catch(RemoteException remoteexception)
        {
            throw new RuntimeRemoteException(remoteexception);
        }
    }

    public final void setMyLocationEnabled(boolean flag)
    {
        try
        {
            OK.setMyLocationEnabled(flag);
            return;
        }
        catch(RemoteException remoteexception)
        {
            throw new RuntimeRemoteException(remoteexception);
        }
    }

    public final void setOnCameraChangeListener(OnCameraChangeListener oncamerachangelistener)
    {
        if(oncamerachangelistener == null)
            try
            {
                OK.setOnCameraChangeListener(null);
                return;
            }
            catch(RemoteException remoteexception)
            {
                throw new RuntimeRemoteException(remoteexception);
            }
        OK.setOnCameraChangeListener(new com.google.android.gms.maps.internal.e.a(oncamerachangelistener) {

            public void onCameraChange(CameraPosition cameraposition)
            {
                OT.onCameraChange(cameraposition);
            }

            final GoogleMap ON;
            final OnCameraChangeListener OT;

            
            {
                ON = GoogleMap.this;
                OT = oncamerachangelistener;
                super();
            }
        }
);
        return;
    }

    public final void setOnInfoWindowClickListener(OnInfoWindowClickListener oninfowindowclicklistener)
    {
        if(oninfowindowclicklistener == null)
            try
            {
                OK.setOnInfoWindowClickListener(null);
                return;
            }
            catch(RemoteException remoteexception)
            {
                throw new RuntimeRemoteException(remoteexception);
            }
        OK.setOnInfoWindowClickListener(new com.google.android.gms.maps.internal.f.a(oninfowindowclicklistener) {

            public void e(d d)
            {
                OY.onInfoWindowClick(new Marker(d));
            }

            final GoogleMap ON;
            final OnInfoWindowClickListener OY;

            
            {
                ON = GoogleMap.this;
                OY = oninfowindowclicklistener;
                super();
            }
        }
);
        return;
    }

    public final void setOnMapClickListener(OnMapClickListener onmapclicklistener)
    {
        if(onmapclicklistener == null)
            try
            {
                OK.setOnMapClickListener(null);
                return;
            }
            catch(RemoteException remoteexception)
            {
                throw new RuntimeRemoteException(remoteexception);
            }
        OK.setOnMapClickListener(new com.google.android.gms.maps.internal.h.a(onmapclicklistener) {

            public void onMapClick(LatLng latlng)
            {
                OU.onMapClick(latlng);
            }

            final GoogleMap ON;
            final OnMapClickListener OU;

            
            {
                ON = GoogleMap.this;
                OU = onmapclicklistener;
                super();
            }
        }
);
        return;
    }

    public void setOnMapLoadedCallback(OnMapLoadedCallback onmaploadedcallback)
    {
        if(onmaploadedcallback == null)
            try
            {
                OK.setOnMapLoadedCallback(null);
                return;
            }
            catch(RemoteException remoteexception)
            {
                throw new RuntimeRemoteException(remoteexception);
            }
        OK.setOnMapLoadedCallback(new com.google.android.gms.maps.internal.i.a(onmaploadedcallback) {

            public void onMapLoaded()
                throws RemoteException
            {
                OR.onMapLoaded();
            }

            final GoogleMap ON;
            final OnMapLoadedCallback OR;

            
            {
                ON = GoogleMap.this;
                OR = onmaploadedcallback;
                super();
            }
        }
);
        return;
    }

    public final void setOnMapLongClickListener(OnMapLongClickListener onmaplongclicklistener)
    {
        if(onmaplongclicklistener == null)
            try
            {
                OK.setOnMapLongClickListener(null);
                return;
            }
            catch(RemoteException remoteexception)
            {
                throw new RuntimeRemoteException(remoteexception);
            }
        OK.setOnMapLongClickListener(new com.google.android.gms.maps.internal.j.a(onmaplongclicklistener) {

            public void onMapLongClick(LatLng latlng)
            {
                OV.onMapLongClick(latlng);
            }

            final GoogleMap ON;
            final OnMapLongClickListener OV;

            
            {
                ON = GoogleMap.this;
                OV = onmaplongclicklistener;
                super();
            }
        }
);
        return;
    }

    public final void setOnMarkerClickListener(OnMarkerClickListener onmarkerclicklistener)
    {
        if(onmarkerclicklistener == null)
            try
            {
                OK.setOnMarkerClickListener(null);
                return;
            }
            catch(RemoteException remoteexception)
            {
                throw new RuntimeRemoteException(remoteexception);
            }
        OK.setOnMarkerClickListener(new com.google.android.gms.maps.internal.k.a(onmarkerclicklistener) {

            public boolean a(d d)
            {
                return OW.onMarkerClick(new Marker(d));
            }

            final GoogleMap ON;
            final OnMarkerClickListener OW;

            
            {
                ON = GoogleMap.this;
                OW = onmarkerclicklistener;
                super();
            }
        }
);
        return;
    }

    public final void setOnMarkerDragListener(OnMarkerDragListener onmarkerdraglistener)
    {
        if(onmarkerdraglistener == null)
            try
            {
                OK.setOnMarkerDragListener(null);
                return;
            }
            catch(RemoteException remoteexception)
            {
                throw new RuntimeRemoteException(remoteexception);
            }
        OK.setOnMarkerDragListener(new com.google.android.gms.maps.internal.l.a(onmarkerdraglistener) {

            public void b(d d1)
            {
                OX.onMarkerDragStart(new Marker(d1));
            }

            public void c(d d1)
            {
                OX.onMarkerDragEnd(new Marker(d1));
            }

            public void d(d d1)
            {
                OX.onMarkerDrag(new Marker(d1));
            }

            final GoogleMap ON;
            final OnMarkerDragListener OX;

            
            {
                ON = GoogleMap.this;
                OX = onmarkerdraglistener;
                super();
            }
        }
);
        return;
    }

    public final void setOnMyLocationButtonClickListener(OnMyLocationButtonClickListener onmylocationbuttonclicklistener)
    {
        if(onmylocationbuttonclicklistener == null)
            try
            {
                OK.setOnMyLocationButtonClickListener(null);
                return;
            }
            catch(RemoteException remoteexception)
            {
                throw new RuntimeRemoteException(remoteexception);
            }
        OK.setOnMyLocationButtonClickListener(new com.google.android.gms.maps.internal.m.a(onmylocationbuttonclicklistener) {

            public boolean onMyLocationButtonClick()
                throws RemoteException
            {
                return OQ.onMyLocationButtonClick();
            }

            final GoogleMap ON;
            final OnMyLocationButtonClickListener OQ;

            
            {
                ON = GoogleMap.this;
                OQ = onmylocationbuttonclicklistener;
                super();
            }
        }
);
        return;
    }

    public final void setOnMyLocationChangeListener(OnMyLocationChangeListener onmylocationchangelistener)
    {
        if(onmylocationchangelistener == null)
            try
            {
                OK.setOnMyLocationChangeListener(null);
                return;
            }
            catch(RemoteException remoteexception)
            {
                throw new RuntimeRemoteException(remoteexception);
            }
        OK.setOnMyLocationChangeListener(new com.google.android.gms.maps.internal.n.a(onmylocationchangelistener) {

            public void d(b b)
            {
                Pa.onMyLocationChange((Location)c.b(b));
            }

            final GoogleMap ON;
            final OnMyLocationChangeListener Pa;

            
            {
                ON = GoogleMap.this;
                Pa = onmylocationchangelistener;
                super();
            }
        }
);
        return;
    }

    public final void setPadding(int i, int j, int k, int l)
    {
        try
        {
            OK.setPadding(i, j, k, l);
            return;
        }
        catch(RemoteException remoteexception)
        {
            throw new RuntimeRemoteException(remoteexception);
        }
    }

    public final void setTrafficEnabled(boolean flag)
    {
        try
        {
            OK.setTrafficEnabled(flag);
            return;
        }
        catch(RemoteException remoteexception)
        {
            throw new RuntimeRemoteException(remoteexception);
        }
    }

    public final void snapshot(SnapshotReadyCallback snapshotreadycallback)
    {
        snapshot(snapshotreadycallback, null);
    }

    public final void snapshot(SnapshotReadyCallback snapshotreadycallback, Bitmap bitmap)
    {
        b b;
        c c1;
        if(bitmap != null)
            b = c.h(bitmap);
        else
            b = null;
        c1 = (c)(c)b;
        try
        {
            OK.snapshot(new com.google.android.gms.maps.internal.o.a(snapshotreadycallback) {

                public void c(b b1)
                    throws RemoteException
                {
                    OS.onSnapshotReady((Bitmap)com.google.android.gms.dynamic.c.b(b1));
                }

                public void onSnapshotReady(Bitmap bitmap1)
                    throws RemoteException
                {
                    OS.onSnapshotReady(bitmap1);
                }

                final GoogleMap ON;
                final SnapshotReadyCallback OS;

            
            {
                ON = GoogleMap.this;
                OS = snapshotreadycallback;
                super();
            }
            }
, c1);
            return;
        }
        catch(RemoteException remoteexception)
        {
            throw new RuntimeRemoteException(remoteexception);
        }
    }

    public final void stopAnimation()
    {
        try
        {
            OK.stopAnimation();
            return;
        }
        catch(RemoteException remoteexception)
        {
            throw new RuntimeRemoteException(remoteexception);
        }
    }

    public static final int MAP_TYPE_HYBRID = 4;
    public static final int MAP_TYPE_NONE = 0;
    public static final int MAP_TYPE_NORMAL = 1;
    public static final int MAP_TYPE_SATELLITE = 2;
    public static final int MAP_TYPE_TERRAIN = 3;
    private final IGoogleMapDelegate OK;
    private UiSettings OL;
}
