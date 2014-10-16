// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.fasterxml.jackson.databind.jsontype.impl;

import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.jsontype.TypeIdResolver;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;

public abstract class TypeSerializerBase extends TypeSerializer
{

    protected TypeSerializerBase(TypeIdResolver typeidresolver, BeanProperty beanproperty)
    {
        _idResolver = typeidresolver;
        _property = beanproperty;
    }

    public String getPropertyName()
    {
        return null;
    }

    public TypeIdResolver getTypeIdResolver()
    {
        return _idResolver;
    }

    public abstract com.fasterxml.jackson.annotation.JsonTypeInfo.As getTypeInclusion();

    protected String idFromValue(Object obj)
    {
        String s = _idResolver.idFromValue(obj);
        if(s == null)
        {
            String s1;
            if(obj == null)
                s1 = "NULL";
            else
                s1 = obj.getClass().getName();
            throw new IllegalArgumentException((new StringBuilder()).append("Can not resolve type id for ").append(s1).append(" (using ").append(_idResolver.getClass().getName()).append(")").toString());
        } else
        {
            return s;
        }
    }

    protected String idFromValueAndType(Object obj, Class class1)
    {
        String s = _idResolver.idFromValueAndType(obj, class1);
        if(s == null)
        {
            String s1;
            if(obj == null)
                s1 = "NULL";
            else
                s1 = obj.getClass().getName();
            throw new IllegalArgumentException((new StringBuilder()).append("Can not resolve type id for ").append(s1).append(" (using ").append(_idResolver.getClass().getName()).append(")").toString());
        } else
        {
            return s;
        }
    }

    protected final TypeIdResolver _idResolver;
    protected final BeanProperty _property;
}
