// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.roadtrippers.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import dagger.Lazy;

public class Serializer
{

    public Serializer(Lazy lazy)
    {
        objectMapper = lazy;
    }

    public Object deserialize(String s, TypeReference typereference)
    {
        Object obj;
        try
        {
            obj = ((ObjectMapper)objectMapper.get()).readValue(s, typereference);
        }
        catch(Exception exception)
        {
            return null;
        }
        return obj;
    }

    public Object deserialize(String s, Class class1)
    {
        Object obj;
        try
        {
            obj = ((ObjectMapper)objectMapper.get()).readValue(s, class1);
        }
        catch(Exception exception)
        {
            return null;
        }
        return obj;
    }

    public String serialize(Object obj)
    {
        String s;
        try
        {
            s = ((ObjectMapper)objectMapper.get()).writeValueAsString(obj);
        }
        catch(Exception exception)
        {
            return null;
        }
        return s;
    }

    final Lazy objectMapper;
}
