// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.roadtrippers.events;


public final class TripsHighlighted extends Enum
{

    private TripsHighlighted(String s, int i)
    {
        super(s, i);
    }

    public static TripsHighlighted valueOf(String s)
    {
        return (TripsHighlighted)Enum.valueOf(com/roadtrippers/events/TripsHighlighted, s);
    }

    public static TripsHighlighted[] values()
    {
        return (TripsHighlighted[])$VALUES.clone();
    }

    private static final TripsHighlighted $VALUES[];
    public static final TripsHighlighted DISABLED;
    public static final TripsHighlighted ENABLED;

    static 
    {
        ENABLED = new TripsHighlighted("ENABLED", 0);
        DISABLED = new TripsHighlighted("DISABLED", 1);
        TripsHighlighted atripshighlighted[] = new TripsHighlighted[2];
        atripshighlighted[0] = ENABLED;
        atripshighlighted[1] = DISABLED;
        $VALUES = atripshighlighted;
    }
}
