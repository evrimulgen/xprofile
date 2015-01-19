// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.tagmanager;

import java.io.IOException;
import java.io.InputStream;

interface NetworkClient
{

    public abstract void close();

    public abstract InputStream getInputStream(String s)
        throws IOException;

    public abstract void sendPostRequest(String s, byte abyte0[])
        throws IOException;

    public static final int DEFAULT_CONNECTION_TIMEOUT_MILLIS = 20000;
    public static final int DEFAULT_SOCKET_TIMEOUT_MILLIS = 20000;
}
