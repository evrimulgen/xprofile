// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.maps.android;


class MathUtil
{

    MathUtil()
    {
    }

    static double arcHav(double d)
    {
        return 2D * Math.asin(Math.sqrt(d));
    }

    static double clamp(double d, double d1, double d2)
    {
        if(d < d1)
            return d1;
        if(d > d2)
            return d2;
        else
            return d;
    }

    static double hav(double d)
    {
        double d1 = Math.sin(0.5D * d);
        return d1 * d1;
    }

    static double havDistance(double d, double d1, double d2)
    {
        return hav(d - d1) + hav(d2) * Math.cos(d) * Math.cos(d1);
    }

    static double havFromSin(double d)
    {
        double d1 = d * d;
        return 0.5D * (d1 / (1.0D + Math.sqrt(1.0D - d1)));
    }

    static double inverseMercator(double d)
    {
        return 2D * Math.atan(Math.exp(d)) - 1.5707963267948966D;
    }

    static double mercator(double d)
    {
        return Math.log(Math.tan(0.78539816339744828D + 0.5D * d));
    }

    static double mod(double d, double d1)
    {
        return (d1 + d % d1) % d1;
    }

    static double sinFromHav(double d)
    {
        return 2D * Math.sqrt(d * (1.0D - d));
    }

    static double sinSumFromHav(double d, double d1)
    {
        double d2 = Math.sqrt(d * (1.0D - d));
        double d3 = Math.sqrt(d1 * (1.0D - d1));
        return 2D * ((d2 + d3) - 2D * (d2 * d1 + d3 * d));
    }

    static double wrap(double d, double d1, double d2)
    {
        if(d >= d1 && d < d2)
            return d;
        else
            return d1 + mod(d - d1, d2 - d1);
    }

    static final double EARTH_RADIUS = 6371009D;
}
