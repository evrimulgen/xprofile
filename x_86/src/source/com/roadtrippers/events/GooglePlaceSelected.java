// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.roadtrippers.events;

import com.roadtrippers.api.models.GooglePlace;
import com.roadtrippers.api.models.Waypoint;

public class GooglePlaceSelected
{

    public GooglePlaceSelected(GooglePlace googleplace, Waypoint waypoint1)
    {
        googlePlace = googleplace;
        waypoint = waypoint1;
    }

    public GooglePlace googlePlace;
    public Waypoint waypoint;
}
