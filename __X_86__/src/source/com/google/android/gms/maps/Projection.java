// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.android.gms.maps;

import android.graphics.Point;
import android.os.RemoteException;
import com.google.android.gms.dynamic.c;
import com.google.android.gms.maps.internal.IProjectionDelegate;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.RuntimeRemoteException;
import com.google.android.gms.maps.model.VisibleRegion;

public final class Projection
{

    Projection(IProjectionDelegate iprojectiondelegate)
    {
        Px = iprojectiondelegate;
    }

    public LatLng fromScreenLocation(Point point)
    {
        LatLng latlng;
        try
        {
            latlng = Px.fromScreenLocation(c.h(point));
        }
        catch(RemoteException remoteexception)
        {
            throw new RuntimeRemoteException(remoteexception);
        }
        return latlng;
    }

    public VisibleRegion getVisibleRegion()
    {
        VisibleRegion visibleregion;
        try
        {
            visibleregion = Px.getVisibleRegion();
        }
        catch(RemoteException remoteexception)
        {
            throw new RuntimeRemoteException(remoteexception);
        }
        return visibleregion;
    }

    public Point toScreenLocation(LatLng latlng)
    {
        Point point;
        try
        {
            point = (Point)c.b(Px.toScreenLocation(latlng));
        }
        catch(RemoteException remoteexception)
        {
            throw new RuntimeRemoteException(remoteexception);
        }
        return point;
    }

    private final IProjectionDelegate Px;
}
