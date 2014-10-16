// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.roadtrippers.db.models;

import com.roadtrippers.api.models.Trip;
import com.roadtrippers.util.Serializer;
import dagger.Lazy;

public class TripData
{

    public TripData()
    {
    }

    public static TripData fromTrip(Trip trip)
    {
        TripData tripdata = new TripData();
        tripdata.id = trip.id;
        tripdata.serializedTrip = ((Serializer)jacksonLazy.get()).serialize(trip);
        return tripdata;
    }

    public Trip toTrip()
    {
        return (Trip)((Serializer)jacksonLazy.get()).deserialize(serializedTrip, com/roadtrippers/api/models/Trip);
    }

    static Lazy jacksonLazy;
    Long _id;
    String id;
    String serializedTrip;
}
