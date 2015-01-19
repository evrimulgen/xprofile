// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.fasterxml.jackson.core.type;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public abstract class TypeReference
    implements Comparable
{

    protected TypeReference()
    {
        Type type = getClass().getGenericSuperclass();
        if(type instanceof Class)
        {
            throw new IllegalArgumentException("Internal error: TypeReference constructed without actual type information");
        } else
        {
            _type = ((ParameterizedType)type).getActualTypeArguments()[0];
            return;
        }
    }

    public int compareTo(TypeReference typereference)
    {
        return 0;
    }

    public volatile int compareTo(Object obj)
    {
        return compareTo((TypeReference)obj);
    }

    public Type getType()
    {
        return _type;
    }

    protected final Type _type;
}
