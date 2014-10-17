// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.roadtrippers.events;


public final class CancelButtonClick extends Enum
{

    private CancelButtonClick(String s, int i)
    {
        super(s, i);
    }

    public static CancelButtonClick valueOf(String s)
    {
        return (CancelButtonClick)Enum.valueOf(com/roadtrippers/events/CancelButtonClick, s);
    }

    public static CancelButtonClick[] values()
    {
        return (CancelButtonClick[])$VALUES.clone();
    }

    private static final CancelButtonClick $VALUES[];
    public static final CancelButtonClick INSTANCE;

    static 
    {
        INSTANCE = new CancelButtonClick("INSTANCE", 0);
        CancelButtonClick acancelbuttonclick[] = new CancelButtonClick[1];
        acancelbuttonclick[0] = INSTANCE;
        $VALUES = acancelbuttonclick;
    }
}
