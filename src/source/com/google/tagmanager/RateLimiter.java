// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.tagmanager;


interface RateLimiter
{

    public abstract boolean tokenAvailable();

    public static final int DEFAULT_MAX_TOKEN_COUNT = 60;
    public static final long DEFAULT_MILLISECONDS_PER_TOKEN = 2000L;
}
