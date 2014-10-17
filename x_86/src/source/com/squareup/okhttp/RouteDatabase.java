// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.squareup.okhttp;

import java.util.LinkedHashSet;
import java.util.Set;

// Referenced classes of package com.squareup.okhttp:
//            Route

public final class RouteDatabase
{

    public RouteDatabase()
    {
    }

    public void connected(Route route)
    {
        this;
        JVM INSTR monitorenter ;
        failedRoutes.remove(route);
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public void failed(Route route)
    {
        this;
        JVM INSTR monitorenter ;
        failedRoutes.add(route);
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public int failedRoutesCount()
    {
        this;
        JVM INSTR monitorenter ;
        int i = failedRoutes.size();
        this;
        JVM INSTR monitorexit ;
        return i;
        Exception exception;
        exception;
        throw exception;
    }

    public boolean shouldPostpone(Route route)
    {
        this;
        JVM INSTR monitorenter ;
        boolean flag = failedRoutes.contains(route);
        this;
        JVM INSTR monitorexit ;
        return flag;
        Exception exception;
        exception;
        throw exception;
    }

    private final Set failedRoutes = new LinkedHashSet();
}
