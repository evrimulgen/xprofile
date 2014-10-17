// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.maps.android.projection;

import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.geometry.Point;

// Referenced classes of package com.google.maps.android.projection:
//            Point

public class SphericalMercatorProjection
{

    public SphericalMercatorProjection(double d)
    {
        mWorldWidth = d;
    }

    public LatLng toLatLng(Point point)
    {
        double d = 360D * (point.x / mWorldWidth - 0.5D);
        return new LatLng(90D - Math.toDegrees(2D * Math.atan(Math.exp(3.1415926535897931D * (2D * -(0.5D - point.y / mWorldWidth))))), d);
    }

    public com.google.maps.android.projection.Point toPoint(LatLng latlng)
    {
        double d = 0.5D + latlng.longitude / 360D;
        double d1 = Math.sin(Math.toRadians(latlng.latitude));
        double d2 = 0.5D + (0.5D * Math.log((1.0D + d1) / (1.0D - d1))) / -6.2831853071795862D;
        return new com.google.maps.android.projection.Point(d * mWorldWidth, d2 * mWorldWidth);
    }

    final double mWorldWidth;
}
