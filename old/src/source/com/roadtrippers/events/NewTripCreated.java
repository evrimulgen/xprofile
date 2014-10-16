// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.roadtrippers.events;


public final class NewTripCreated extends Enum
{

    private NewTripCreated(String s, int i)
    {
        super(s, i);
    }

    public static NewTripCreated valueOf(String s)
    {
        return (NewTripCreated)Enum.valueOf(com/roadtrippers/events/NewTripCreated, s);
    }

    public static NewTripCreated[] values()
    {
        return (NewTripCreated[])$VALUES.clone();
    }

    private static final NewTripCreated $VALUES[];
    public static final NewTripCreated EVENT;

    static 
    {
        EVENT = new NewTripCreated("EVENT", 0);
        NewTripCreated anewtripcreated[] = new NewTripCreated[1];
        anewtripcreated[0] = EVENT;
        $VALUES = anewtripcreated;
    }
}
