// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.roadtrippers.events;


public final class PlacesHighlighted extends Enum
{

    private PlacesHighlighted(String s, int i)
    {
        super(s, i);
    }

    public static PlacesHighlighted valueOf(String s)
    {
        return (PlacesHighlighted)Enum.valueOf(com/roadtrippers/events/PlacesHighlighted, s);
    }

    public static PlacesHighlighted[] values()
    {
        return (PlacesHighlighted[])$VALUES.clone();
    }

    private static final PlacesHighlighted $VALUES[];
    public static final PlacesHighlighted DISABLED;
    public static final PlacesHighlighted ENABLED;

    static 
    {
        ENABLED = new PlacesHighlighted("ENABLED", 0);
        DISABLED = new PlacesHighlighted("DISABLED", 1);
        PlacesHighlighted aplaceshighlighted[] = new PlacesHighlighted[2];
        aplaceshighlighted[0] = ENABLED;
        aplaceshighlighted[1] = DISABLED;
        $VALUES = aplaceshighlighted;
    }
}
