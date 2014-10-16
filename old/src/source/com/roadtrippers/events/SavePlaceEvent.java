// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.roadtrippers.events;

import com.roadtrippers.api.models.Poi;

public class SavePlaceEvent
{

    public SavePlaceEvent(Poi poi1)
    {
        poi = poi1;
    }

    public Poi getPoi()
    {
        return poi;
    }

    Poi poi;
}
