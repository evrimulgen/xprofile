// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.roadtrippers.events;


public final class CreateNewTrip extends Enum
{

    private CreateNewTrip(String s, int i)
    {
        super(s, i);
    }

    public static CreateNewTrip valueOf(String s)
    {
        return (CreateNewTrip)Enum.valueOf(com/roadtrippers/events/CreateNewTrip, s);
    }

    public static CreateNewTrip[] values()
    {
        return (CreateNewTrip[])$VALUES.clone();
    }

    private static final CreateNewTrip $VALUES[];
    public static final CreateNewTrip EVENT;

    static 
    {
        EVENT = new CreateNewTrip("EVENT", 0);
        CreateNewTrip acreatenewtrip[] = new CreateNewTrip[1];
        acreatenewtrip[0] = EVENT;
        $VALUES = acreatenewtrip;
    }
}
