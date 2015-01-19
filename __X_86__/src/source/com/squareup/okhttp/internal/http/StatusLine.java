// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.squareup.okhttp.internal.http;

import java.io.IOException;
import java.net.ProtocolException;

public final class StatusLine
{

    public StatusLine(String s)
        throws IOException
    {
        int i;
        byte byte0;
label0:
        {
label1:
            {
                super();
                if(s.startsWith("HTTP/1."))
                {
                    if(s.length() < 9 || s.charAt(8) != ' ')
                        throw new ProtocolException((new StringBuilder()).append("Unexpected status line: ").append(s).toString());
                    i = -48 + s.charAt(7);
                    byte0 = 9;
                    if(i < 0 || i > 9)
                        throw new ProtocolException((new StringBuilder()).append("Unexpected status line: ").append(s).toString());
                } else
                {
                    if(!s.startsWith("ICY "))
                        break label1;
                    i = 0;
                    byte0 = 4;
                }
                if(s.length() < byte0 + 3)
                    throw new ProtocolException((new StringBuilder()).append("Unexpected status line: ").append(s).toString());
                break label0;
            }
            throw new ProtocolException((new StringBuilder()).append("Unexpected status line: ").append(s).toString());
        }
        int j = byte0 + 3;
        int k;
        String s1;
        try
        {
            k = Integer.parseInt(s.substring(byte0, j));
        }
        catch(NumberFormatException numberformatexception)
        {
            throw new ProtocolException((new StringBuilder()).append("Unexpected status line: ").append(s).toString());
        }
        s1 = "";
        if(s.length() > byte0 + 3)
        {
            if(s.charAt(byte0 + 3) != ' ')
                throw new ProtocolException((new StringBuilder()).append("Unexpected status line: ").append(s).toString());
            s1 = s.substring(byte0 + 4);
        }
        responseMessage = s1;
        responseCode = k;
        statusLine = s;
        httpMinorVersion = i;
    }

    public int code()
    {
        return responseCode;
    }

    public String getStatusLine()
    {
        return statusLine;
    }

    public int httpMinorVersion()
    {
        if(httpMinorVersion != -1)
            return httpMinorVersion;
        else
            return 1;
    }

    public String message()
    {
        return responseMessage;
    }

    public static final int HTTP_CONTINUE = 100;
    public static final int HTTP_TEMP_REDIRECT = 307;
    private final int httpMinorVersion;
    private final int responseCode;
    private final String responseMessage;
    private final String statusLine;
}
