// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.fasterxml.jackson.core.format;


public final class MatchStrength extends Enum
{

    private MatchStrength(String s, int i)
    {
        super(s, i);
    }

    public static MatchStrength valueOf(String s)
    {
        return (MatchStrength)Enum.valueOf(com/fasterxml/jackson/core/format/MatchStrength, s);
    }

    public static MatchStrength[] values()
    {
        return (MatchStrength[])$VALUES.clone();
    }

    private static final MatchStrength $VALUES[];
    public static final MatchStrength FULL_MATCH;
    public static final MatchStrength INCONCLUSIVE;
    public static final MatchStrength NO_MATCH;
    public static final MatchStrength SOLID_MATCH;
    public static final MatchStrength WEAK_MATCH;

    static 
    {
        NO_MATCH = new MatchStrength("NO_MATCH", 0);
        INCONCLUSIVE = new MatchStrength("INCONCLUSIVE", 1);
        WEAK_MATCH = new MatchStrength("WEAK_MATCH", 2);
        SOLID_MATCH = new MatchStrength("SOLID_MATCH", 3);
        FULL_MATCH = new MatchStrength("FULL_MATCH", 4);
        MatchStrength amatchstrength[] = new MatchStrength[5];
        amatchstrength[0] = NO_MATCH;
        amatchstrength[1] = INCONCLUSIVE;
        amatchstrength[2] = WEAK_MATCH;
        amatchstrength[3] = SOLID_MATCH;
        amatchstrength[4] = FULL_MATCH;
        $VALUES = amatchstrength;
    }
}
