// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.fasterxml.jackson.core.util;

import java.util.concurrent.ConcurrentHashMap;

public final class InternCache extends ConcurrentHashMap
{

    private InternCache()
    {
        super(180, 0.8F, 4);
    }

    public String intern(String s)
    {
        String s1 = (String)get(s);
        if(s1 != null)
            return s1;
        if(size() >= 180)
            synchronized(_flushLock)
            {
                if(size() >= 180)
                    clear();
            }
        String s2 = s.intern();
        put(s2, s2);
        return s2;
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
    }

    private static final int MAX_ENTRIES = 180;
    private static final Object _flushLock = new Object();
    public static final InternCache instance = new InternCache();

}
