// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.roadtrippers.events;

import com.roadtrippers.api.models.Trip;

public class TripClick
{

    public TripClick(Trip trip1)
    {
        trip = trip1;
    }

    public Trip getTrip()
    {
        return trip;
    }

    public void setTrip(Trip trip1)
    {
        trip = trip1;
    }

    private Trip trip;
}
