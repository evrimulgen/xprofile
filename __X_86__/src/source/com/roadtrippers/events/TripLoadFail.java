// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.roadtrippers.events;


public final class TripLoadFail extends Enum
{

    private TripLoadFail(String s, int i)
    {
        super(s, i);
    }

    public static TripLoadFail valueOf(String s)
    {
        return (TripLoadFail)Enum.valueOf(com/roadtrippers/events/TripLoadFail, s);
    }

    public static TripLoadFail[] values()
    {
        return (TripLoadFail[])$VALUES.clone();
    }

    private static final TripLoadFail $VALUES[];
    public static final TripLoadFail EVENT;

    static 
    {
        EVENT = new TripLoadFail("EVENT", 0);
        TripLoadFail atriploadfail[] = new TripLoadFail[1];
        atriploadfail[0] = EVENT;
        $VALUES = atriploadfail;
    }
}
