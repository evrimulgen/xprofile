// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.crittercism.app;


public final class CritterRateMyAppButtons extends Enum
{

    private CritterRateMyAppButtons(String s, int i)
    {
        super(s, i);
    }

    public static CritterRateMyAppButtons valueOf(String s)
    {
        return (CritterRateMyAppButtons)Enum.valueOf(com/crittercism/app/CritterRateMyAppButtons, s);
    }

    public static CritterRateMyAppButtons[] values()
    {
        return (CritterRateMyAppButtons[])$VALUES.clone();
    }

    private static final CritterRateMyAppButtons $VALUES[];
    public static final CritterRateMyAppButtons LATER;
    public static final CritterRateMyAppButtons NO;
    public static final CritterRateMyAppButtons YES;

    static 
    {
        YES = new CritterRateMyAppButtons("YES", 0);
        NO = new CritterRateMyAppButtons("NO", 1);
        LATER = new CritterRateMyAppButtons("LATER", 2);
        CritterRateMyAppButtons acritterratemyappbuttons[] = new CritterRateMyAppButtons[3];
        acritterratemyappbuttons[0] = YES;
        acritterratemyappbuttons[1] = NO;
        acritterratemyappbuttons[2] = LATER;
        $VALUES = acritterratemyappbuttons;
    }
}
