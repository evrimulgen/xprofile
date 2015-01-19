// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.mixpanel.android.mpmetrics;


class MPConfig
{

    MPConfig()
    {
    }

    public static final String BASE_ENDPOINT = "https://api.mixpanel.com";
    public static final int BULK_UPLOAD_LIMIT = 40;
    public static final int DATA_EXPIRATION = 0xa4cb800;
    public static final boolean DEBUG = false;
    public static final String FALLBACK_ENDPOINT = "http://api.mixpanel.com";
    public static final long FLUSH_RATE = 60000L;
    public static final int SUBMIT_THREAD_TTL = 60000;
}
