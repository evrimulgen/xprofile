// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.roadtrippers.events;


public final class CurrentTripFull extends Enum
{

    private CurrentTripFull(String s, int i)
    {
        super(s, i);
    }

    public static CurrentTripFull valueOf(String s)
    {
        return (CurrentTripFull)Enum.valueOf(com/roadtrippers/events/CurrentTripFull, s);
    }

    public static CurrentTripFull[] values()
    {
        return (CurrentTripFull[])$VALUES.clone();
    }

    private static final CurrentTripFull $VALUES[];
    public static final CurrentTripFull EVENT;

    static 
    {
        EVENT = new CurrentTripFull("EVENT", 0);
        CurrentTripFull acurrenttripfull[] = new CurrentTripFull[1];
        acurrenttripfull[0] = EVENT;
        $VALUES = acurrenttripfull;
    }
}
