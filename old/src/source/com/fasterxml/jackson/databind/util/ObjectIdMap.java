// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.fasterxml.jackson.databind.util;

import java.util.IdentityHashMap;

public class ObjectIdMap extends IdentityHashMap
{

    public ObjectIdMap()
    {
        super(16);
    }

    public Object findId(Object obj)
    {
        return get(obj);
    }

    public void insertId(Object obj, Object obj1)
    {
        put(obj, obj1);
    }
}
