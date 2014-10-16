// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.bugsnag.http;


public class BadResponseException extends RuntimeException
{

    public BadResponseException(String s, int i)
    {
        url = s;
        responseCode = i;
    }

    public String getMessage()
    {
        Object aobj[] = new Object[2];
        aobj[0] = Integer.valueOf(responseCode);
        aobj[1] = url;
        return String.format("Got non-200 response code (%d) from %s", aobj);
    }

    public int getResponseCode()
    {
        return responseCode;
    }

    private int responseCode;
    private String url;
}
