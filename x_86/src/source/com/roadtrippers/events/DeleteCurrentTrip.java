// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.roadtrippers.events;


public final class DeleteCurrentTrip extends Enum
{

    private DeleteCurrentTrip(String s, int i)
    {
        super(s, i);
    }

    public static DeleteCurrentTrip valueOf(String s)
    {
        return (DeleteCurrentTrip)Enum.valueOf(com/roadtrippers/events/DeleteCurrentTrip, s);
    }

    public static DeleteCurrentTrip[] values()
    {
        return (DeleteCurrentTrip[])$VALUES.clone();
    }

    private static final DeleteCurrentTrip $VALUES[];
    public static final DeleteCurrentTrip EVENT;

    static 
    {
        EVENT = new DeleteCurrentTrip("EVENT", 0);
        DeleteCurrentTrip adeletecurrenttrip[] = new DeleteCurrentTrip[1];
        adeletecurrenttrip[0] = EVENT;
        $VALUES = adeletecurrenttrip;
    }
}
