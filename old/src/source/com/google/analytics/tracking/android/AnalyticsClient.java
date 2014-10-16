// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.analytics.tracking.android;

import java.util.List;
import java.util.Map;

interface AnalyticsClient
{

    public abstract void clearHits();

    public abstract void connect();

    public abstract void disconnect();

    public abstract void sendHit(Map map, long l, String s, List list);
}
