// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.maps.android.heatmaps;

import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.geometry.Point;
import com.google.maps.android.projection.SphericalMercatorProjection;

public class WeightedLatLng
    implements com.google.maps.android.quadtree.PointQuadTree.Item
{

    public WeightedLatLng(LatLng latlng)
    {
        this(latlng, 1.0D);
    }

    public WeightedLatLng(LatLng latlng, double d)
    {
        mPoint = sProjection.toPoint(latlng);
        if(d >= 0.0D)
        {
            mIntensity = d;
            return;
        } else
        {
            mIntensity = 1.0D;
            return;
        }
    }

    public double getIntensity()
    {
        return mIntensity;
    }

    public Point getPoint()
    {
        return mPoint;
    }

    public static final double DEFAULT_INTENSITY = 1D;
    private static final SphericalMercatorProjection sProjection = new SphericalMercatorProjection(1.0D);
    private double mIntensity;
    private Point mPoint;

}
