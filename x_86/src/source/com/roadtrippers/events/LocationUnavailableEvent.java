// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.roadtrippers.events;


public final class LocationUnavailableEvent extends Enum
{

    private LocationUnavailableEvent(String s, int i)
    {
        super(s, i);
    }

    public static LocationUnavailableEvent valueOf(String s)
    {
        return (LocationUnavailableEvent)Enum.valueOf(com/roadtrippers/events/LocationUnavailableEvent, s);
    }

    public static LocationUnavailableEvent[] values()
    {
        return (LocationUnavailableEvent[])$VALUES.clone();
    }

    private static final LocationUnavailableEvent $VALUES[];
    public static final LocationUnavailableEvent INSTANCE;

    static 
    {
        INSTANCE = new LocationUnavailableEvent("INSTANCE", 0);
        LocationUnavailableEvent alocationunavailableevent[] = new LocationUnavailableEvent[1];
        alocationunavailableevent[0] = INSTANCE;
        $VALUES = alocationunavailableevent;
    }
}
