// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.roadtrippers.events;


public final class CancelCurrentTrip extends Enum
{

    private CancelCurrentTrip(String s, int i)
    {
        super(s, i);
    }

    public static CancelCurrentTrip valueOf(String s)
    {
        return (CancelCurrentTrip)Enum.valueOf(com/roadtrippers/events/CancelCurrentTrip, s);
    }

    public static CancelCurrentTrip[] values()
    {
        return (CancelCurrentTrip[])$VALUES.clone();
    }

    private static final CancelCurrentTrip $VALUES[];
    public static final CancelCurrentTrip EVENT;

    static 
    {
        EVENT = new CancelCurrentTrip("EVENT", 0);
        CancelCurrentTrip acancelcurrenttrip[] = new CancelCurrentTrip[1];
        acancelcurrenttrip[0] = EVENT;
        $VALUES = acancelcurrenttrip;
    }
}
