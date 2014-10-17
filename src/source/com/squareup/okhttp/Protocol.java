// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.squareup.okhttp;

import com.squareup.okhttp.internal.okio.ByteString;

public final class Protocol extends Enum
{

    private Protocol(String s, int i, String s1, boolean flag)
    {
        super(s, i);
        name = ByteString.encodeUtf8(s1);
        spdyVariant = flag;
    }

    public static Protocol valueOf(String s)
    {
        return (Protocol)Enum.valueOf(com/squareup/okhttp/Protocol, s);
    }

    public static Protocol[] values()
    {
        return (Protocol[])$VALUES.clone();
    }

    private static final Protocol $VALUES[];
    public static final Protocol HTTP_11;
    public static final Protocol HTTP_2;
    public static final Protocol SPDY_3;
    public final ByteString name;
    public final boolean spdyVariant;

    static 
    {
        HTTP_2 = new Protocol("HTTP_2", 0, "HTTP-draft-09/2.0", true);
        SPDY_3 = new Protocol("SPDY_3", 1, "spdy/3.1", true);
        HTTP_11 = new Protocol("HTTP_11", 2, "http/1.1", false);
        Protocol aprotocol[] = new Protocol[3];
        aprotocol[0] = HTTP_2;
        aprotocol[1] = SPDY_3;
        aprotocol[2] = HTTP_11;
        $VALUES = aprotocol;
    }
}
