// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.squareup.okhttp;


public final class ResponseSource extends Enum
{

    private ResponseSource(String s, int i)
    {
        super(s, i);
    }

    public static ResponseSource valueOf(String s)
    {
        return (ResponseSource)Enum.valueOf(com/squareup/okhttp/ResponseSource, s);
    }

    public static ResponseSource[] values()
    {
        return (ResponseSource[])$VALUES.clone();
    }

    public boolean requiresConnection()
    {
        return this == CONDITIONAL_CACHE || this == NETWORK;
    }

    public boolean usesCache()
    {
        return this == CACHE || this == CONDITIONAL_CACHE;
    }

    private static final ResponseSource $VALUES[];
    public static final ResponseSource CACHE;
    public static final ResponseSource CONDITIONAL_CACHE;
    public static final ResponseSource NETWORK;
    public static final ResponseSource NONE;

    static 
    {
        CACHE = new ResponseSource("CACHE", 0);
        CONDITIONAL_CACHE = new ResponseSource("CONDITIONAL_CACHE", 1);
        NETWORK = new ResponseSource("NETWORK", 2);
        NONE = new ResponseSource("NONE", 3);
        ResponseSource aresponsesource[] = new ResponseSource[4];
        aresponsesource[0] = CACHE;
        aresponsesource[1] = CONDITIONAL_CACHE;
        aresponsesource[2] = NETWORK;
        aresponsesource[3] = NONE;
        $VALUES = aresponsesource;
    }
}
