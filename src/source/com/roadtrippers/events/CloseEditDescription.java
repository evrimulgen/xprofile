// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.roadtrippers.events;


public final class CloseEditDescription extends Enum
{

    private CloseEditDescription(String s, int i)
    {
        super(s, i);
    }

    public static CloseEditDescription valueOf(String s)
    {
        return (CloseEditDescription)Enum.valueOf(com/roadtrippers/events/CloseEditDescription, s);
    }

    public static CloseEditDescription[] values()
    {
        return (CloseEditDescription[])$VALUES.clone();
    }

    private static final CloseEditDescription $VALUES[];
    public static final CloseEditDescription EVENT;

    static 
    {
        EVENT = new CloseEditDescription("EVENT", 0);
        CloseEditDescription acloseeditdescription[] = new CloseEditDescription[1];
        acloseeditdescription[0] = EVENT;
        $VALUES = acloseeditdescription;
    }
}
