// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.roadtrippers.events;


public final class ClearTripsClick extends Enum
{

    private ClearTripsClick(String s, int i)
    {
        super(s, i);
    }

    public static ClearTripsClick valueOf(String s)
    {
        return (ClearTripsClick)Enum.valueOf(com/roadtrippers/events/ClearTripsClick, s);
    }

    public static ClearTripsClick[] values()
    {
        return (ClearTripsClick[])$VALUES.clone();
    }

    private static final ClearTripsClick $VALUES[];
    public static final ClearTripsClick INSTANCE;

    static 
    {
        INSTANCE = new ClearTripsClick("INSTANCE", 0);
        ClearTripsClick acleartripsclick[] = new ClearTripsClick[1];
        acleartripsclick[0] = INSTANCE;
        $VALUES = acleartripsclick;
    }
}
