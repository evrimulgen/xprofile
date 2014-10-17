// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.roadtrippers.events;


public final class TripPolylineEvent extends Enum
{

    private TripPolylineEvent(String s, int i)
    {
        super(s, i);
    }

    public static TripPolylineEvent valueOf(String s)
    {
        return (TripPolylineEvent)Enum.valueOf(com/roadtrippers/events/TripPolylineEvent, s);
    }

    public static TripPolylineEvent[] values()
    {
        return (TripPolylineEvent[])$VALUES.clone();
    }

    private static final TripPolylineEvent $VALUES[];
    public static final TripPolylineEvent EVENT;

    static 
    {
        EVENT = new TripPolylineEvent("EVENT", 0);
        TripPolylineEvent atrippolylineevent[] = new TripPolylineEvent[1];
        atrippolylineevent[0] = EVENT;
        $VALUES = atrippolylineevent;
    }
}
