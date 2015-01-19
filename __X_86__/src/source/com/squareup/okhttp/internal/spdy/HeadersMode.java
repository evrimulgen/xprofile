// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.squareup.okhttp.internal.spdy;


final class HeadersMode extends Enum
{

    private HeadersMode(String s, int i)
    {
        super(s, i);
    }

    public static HeadersMode valueOf(String s)
    {
        return (HeadersMode)Enum.valueOf(com/squareup/okhttp/internal/spdy/HeadersMode, s);
    }

    public static HeadersMode[] values()
    {
        return (HeadersMode[])$VALUES.clone();
    }

    public boolean failIfHeadersAbsent()
    {
        return this == SPDY_HEADERS;
    }

    public boolean failIfHeadersPresent()
    {
        return this == SPDY_REPLY;
    }

    public boolean failIfStreamAbsent()
    {
        return this == SPDY_REPLY || this == SPDY_HEADERS;
    }

    public boolean failIfStreamPresent()
    {
        return this == SPDY_SYN_STREAM;
    }

    private static final HeadersMode $VALUES[];
    public static final HeadersMode HTTP_20_HEADERS;
    public static final HeadersMode SPDY_HEADERS;
    public static final HeadersMode SPDY_REPLY;
    public static final HeadersMode SPDY_SYN_STREAM;

    static 
    {
        SPDY_SYN_STREAM = new HeadersMode("SPDY_SYN_STREAM", 0);
        SPDY_REPLY = new HeadersMode("SPDY_REPLY", 1);
        SPDY_HEADERS = new HeadersMode("SPDY_HEADERS", 2);
        HTTP_20_HEADERS = new HeadersMode("HTTP_20_HEADERS", 3);
        HeadersMode aheadersmode[] = new HeadersMode[4];
        aheadersmode[0] = SPDY_SYN_STREAM;
        aheadersmode[1] = SPDY_REPLY;
        aheadersmode[2] = SPDY_HEADERS;
        aheadersmode[3] = HTTP_20_HEADERS;
        $VALUES = aheadersmode;
    }
}
