// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.roadtrippers.events;


public final class UpdatingTrip extends Enum
{

    private UpdatingTrip(String s, int i)
    {
        super(s, i);
    }

    public static UpdatingTrip valueOf(String s)
    {
        return (UpdatingTrip)Enum.valueOf(com/roadtrippers/events/UpdatingTrip, s);
    }

    public static UpdatingTrip[] values()
    {
        return (UpdatingTrip[])$VALUES.clone();
    }

    private static final UpdatingTrip $VALUES[];
    public static final UpdatingTrip EVENT;

    static 
    {
        EVENT = new UpdatingTrip("EVENT", 0);
        UpdatingTrip aupdatingtrip[] = new UpdatingTrip[1];
        aupdatingtrip[0] = EVENT;
        $VALUES = aupdatingtrip;
    }
}
