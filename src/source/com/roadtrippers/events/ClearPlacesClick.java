// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.roadtrippers.events;


public final class ClearPlacesClick extends Enum
{

    private ClearPlacesClick(String s, int i)
    {
        super(s, i);
    }

    public static ClearPlacesClick valueOf(String s)
    {
        return (ClearPlacesClick)Enum.valueOf(com/roadtrippers/events/ClearPlacesClick, s);
    }

    public static ClearPlacesClick[] values()
    {
        return (ClearPlacesClick[])$VALUES.clone();
    }

    private static final ClearPlacesClick $VALUES[];
    public static final ClearPlacesClick INSTANCE;

    static 
    {
        INSTANCE = new ClearPlacesClick("INSTANCE", 0);
        ClearPlacesClick aclearplacesclick[] = new ClearPlacesClick[1];
        aclearplacesclick[0] = INSTANCE;
        $VALUES = aclearplacesclick;
    }
}
