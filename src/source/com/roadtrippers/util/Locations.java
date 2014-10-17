// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.roadtrippers.util;

import android.location.Location;
import android.location.LocationManager;

public class Locations
{

    public Locations()
    {
    }

    public static Location getLastKnownLocation(LocationManager locationmanager)
    {
        Location location = locationmanager.getLastKnownLocation("gps");
        if(location == null)
            location = locationmanager.getLastKnownLocation("network");
        return location;
    }
}
