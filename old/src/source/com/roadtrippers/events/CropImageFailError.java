// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.roadtrippers.events;


public final class CropImageFailError extends Enum
{

    private CropImageFailError(String s, int i)
    {
        super(s, i);
    }

    public static CropImageFailError valueOf(String s)
    {
        return (CropImageFailError)Enum.valueOf(com/roadtrippers/events/CropImageFailError, s);
    }

    public static CropImageFailError[] values()
    {
        return (CropImageFailError[])$VALUES.clone();
    }

    private static final CropImageFailError $VALUES[];
    public static final CropImageFailError INSTANCE;

    static 
    {
        INSTANCE = new CropImageFailError("INSTANCE", 0);
        CropImageFailError acropimagefailerror[] = new CropImageFailError[1];
        acropimagefailerror[0] = INSTANCE;
        $VALUES = acropimagefailerror;
    }
}
