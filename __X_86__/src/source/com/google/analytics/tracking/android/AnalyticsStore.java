// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.analytics.tracking.android;

import java.util.Collection;
import java.util.Map;

// Referenced classes of package com.google.analytics.tracking.android:
//            Dispatcher

interface AnalyticsStore
{

    public abstract void clearHits(long l);

    public abstract void close();

    public abstract void dispatch();

    public abstract Dispatcher getDispatcher();

    public abstract void putHit(Map map, long l, String s, Collection collection);

    public abstract void setDispatch(boolean flag);
}
