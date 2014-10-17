// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.roadtrippers.events;


public final class PoiAddedToTrip extends Enum
{

    private PoiAddedToTrip(String s, int i)
    {
        super(s, i);
    }

    public static PoiAddedToTrip valueOf(String s)
    {
        return (PoiAddedToTrip)Enum.valueOf(com/roadtrippers/events/PoiAddedToTrip, s);
    }

    public static PoiAddedToTrip[] values()
    {
        return (PoiAddedToTrip[])$VALUES.clone();
    }

    private static final PoiAddedToTrip $VALUES[];
    public static final PoiAddedToTrip EVENT;

    static 
    {
        EVENT = new PoiAddedToTrip("EVENT", 0);
        PoiAddedToTrip apoiaddedtotrip[] = new PoiAddedToTrip[1];
        apoiaddedtotrip[0] = EVENT;
        $VALUES = apoiaddedtotrip;
    }
}
