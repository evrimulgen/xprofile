// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.bugsnag.http;

import java.io.IOException;

public class NetworkException extends IOException
{

    public NetworkException(String s, Throwable throwable)
    {
        super(s);
        initCause(throwable);
    }
}
