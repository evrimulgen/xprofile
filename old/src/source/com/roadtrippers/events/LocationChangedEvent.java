// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.roadtrippers.events;

import android.location.Location;

public class LocationChangedEvent
{

    public LocationChangedEvent(Location location1)
    {
        location = location1;
    }

    public Location location;
}
