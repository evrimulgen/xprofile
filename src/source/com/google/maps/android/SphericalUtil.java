// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.maps.android;

import com.google.android.gms.maps.model.LatLng;
import java.util.Iterator;
import java.util.List;

// Referenced classes of package com.google.maps.android:
//            MathUtil

public class SphericalUtil
{

    private SphericalUtil()
    {
    }

    static double computeAngleBetween(LatLng latlng, LatLng latlng1)
    {
        return distanceRadians(Math.toRadians(latlng.latitude), Math.toRadians(latlng.longitude), Math.toRadians(latlng1.latitude), Math.toRadians(latlng1.longitude));
    }

    public static double computeArea(List list)
    {
        return Math.abs(computeSignedArea(list));
    }

    public static double computeDistanceBetween(LatLng latlng, LatLng latlng1)
    {
        return 6371009D * computeAngleBetween(latlng, latlng1);
    }

    public static double computeHeading(LatLng latlng, LatLng latlng1)
    {
        double d = Math.toRadians(latlng.latitude);
        double d1 = Math.toRadians(latlng.longitude);
        double d2 = Math.toRadians(latlng1.latitude);
        double d3 = Math.toRadians(latlng1.longitude) - d1;
        return wrap(Math.toDegrees(Math.atan2(Math.sin(d3) * Math.cos(d2), Math.cos(d) * Math.sin(d2) - Math.sin(d) * Math.cos(d2) * Math.cos(d3))), -180D, 180D);
    }

    public static double computeLength(List list)
    {
        if(list.size() < 2)
            return 0.0D;
        double d = 0.0D;
        LatLng latlng = (LatLng)list.get(0);
        double d1 = Math.toRadians(latlng.latitude);
        double d2 = Math.toRadians(latlng.longitude);
        for(Iterator iterator = list.iterator(); iterator.hasNext();)
        {
            LatLng latlng1 = (LatLng)iterator.next();
            double d3 = Math.toRadians(latlng1.latitude);
            double d4 = Math.toRadians(latlng1.longitude);
            d += distanceRadians(d1, d2, d3, d4);
            d1 = d3;
            d2 = d4;
        }

        return 6371009D * d;
    }

    public static LatLng computeOffset(LatLng latlng, double d, double d1)
    {
        double d2 = d / 6371009D;
        double d3 = Math.toRadians(d1);
        double d4 = Math.toRadians(latlng.latitude);
        double d5 = Math.toRadians(latlng.longitude);
        double d6 = Math.cos(d2);
        double d7 = Math.sin(d2);
        double d8 = Math.sin(d4);
        double d9 = Math.cos(d4);
        double d10 = d6 * d8 + d7 * d9 * Math.cos(d3);
        double d11 = Math.atan2(d7 * d9 * Math.sin(d3), d6 - d8 * d10);
        return new LatLng(Math.toDegrees(Math.asin(d10)), Math.toDegrees(d5 + d11));
    }

    public static LatLng computeOffsetOrigin(LatLng latlng, double d, double d1)
    {
        double d2 = Math.toRadians(d1);
        double d3 = d / 6371009D;
        double d4 = Math.cos(d3);
        double d5 = Math.sin(d3) * Math.cos(d2);
        double d6 = Math.sin(d3) * Math.sin(d2);
        double d7 = Math.sin(Math.toRadians(latlng.latitude));
        double d8 = d4 * d4;
        double d9 = (d8 * (d5 * d5) + d8 * d8) - d7 * (d8 * d7);
        if(d9 < 0.0D)
            return null;
        double d10 = (d5 * d7 + Math.sqrt(d9)) / (d4 * d4 + d5 * d5);
        double d11 = (d7 - d5 * d10) / d4;
        double d12 = Math.atan2(d11, d10);
        if(d12 < -1.5707963267948966D || d12 > 1.5707963267948966D)
            d12 = Math.atan2(d11, (d5 * d7 - Math.sqrt(d9)) / (d4 * d4 + d5 * d5));
        if(d12 < -1.5707963267948966D || d12 > 1.5707963267948966D)
        {
            return null;
        } else
        {
            double d13 = Math.toRadians(latlng.longitude) - Math.atan2(d6, d4 * Math.cos(d12) - d5 * Math.sin(d12));
            return new LatLng(Math.toDegrees(d12), Math.toDegrees(d13));
        }
    }

    public static double computeSignedArea(List list)
    {
        return computeSignedArea(list, 6371009D);
    }

    static double computeSignedArea(List list, double d)
    {
        int i = list.size();
        if(i < 3)
            return 0.0D;
        double d1 = 0.0D;
        LatLng latlng = (LatLng)list.get(i - 1);
        double d2 = Math.tan((1.5707963267948966D - Math.toRadians(latlng.latitude)) / 2D);
        double d3 = Math.toRadians(latlng.longitude);
        for(Iterator iterator = list.iterator(); iterator.hasNext();)
        {
            LatLng latlng1 = (LatLng)iterator.next();
            double d4 = Math.tan((1.5707963267948966D - Math.toRadians(latlng1.latitude)) / 2D);
            double d5 = Math.toRadians(latlng1.longitude);
            d1 += polarTriangleArea(d4, d5, d2, d3);
            d2 = d4;
            d3 = d5;
        }

        return d1 * (d * d);
    }

    private static double distanceRadians(double d, double d1, double d2, double d3)
    {
        return MathUtil.arcHav(MathUtil.havDistance(d, d2, d1 - d3));
    }

    public static LatLng interpolate(LatLng latlng, LatLng latlng1, double d)
    {
        double d1 = Math.toRadians(latlng.latitude);
        double d2 = Math.toRadians(latlng.longitude);
        double d3 = Math.toRadians(latlng1.latitude);
        double d4 = Math.toRadians(latlng1.longitude);
        double d5 = Math.cos(d1);
        double d6 = Math.cos(d3);
        double d7 = computeAngleBetween(latlng, latlng1);
        double d8 = Math.sin(d7);
        if(d8 < 9.9999999999999995E-007D)
        {
            return latlng;
        } else
        {
            double d9 = Math.sin(d7 * (1.0D - d)) / d8;
            double d10 = Math.sin(d * d7) / d8;
            double d11 = d9 * d5 * Math.cos(d2) + d10 * d6 * Math.cos(d4);
            double d12 = d9 * d5 * Math.sin(d2) + d10 * d6 * Math.sin(d4);
            double d13 = Math.atan2(d9 * Math.sin(d1) + d10 * Math.sin(d3), Math.sqrt(d11 * d11 + d12 * d12));
            double d14 = Math.atan2(d12, d11);
            LatLng latlng2 = new LatLng(Math.toDegrees(d13), Math.toDegrees(d14));
            return latlng2;
        }
    }

    static double mod(double d, double d1)
    {
        return (d1 + d % d1) % d1;
    }

    private static double polarTriangleArea(double d, double d1, double d2, double d3)
    {
        double d4 = d1 - d3;
        double d5 = d * d2;
        return 2D * Math.atan2(d5 * Math.sin(d4), 1.0D + d5 * Math.cos(d4));
    }

    static double wrap(double d, double d1, double d2)
    {
        if(d >= d1 && d < d2)
            return d;
        else
            return d1 + mod(d - d1, d2 - d1);
    }
}
