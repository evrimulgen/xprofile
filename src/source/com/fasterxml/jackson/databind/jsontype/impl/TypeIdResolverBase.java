// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.fasterxml.jackson.databind.jsontype.impl;

import com.fasterxml.jackson.databind.DatabindContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.jsontype.TypeIdResolver;
import com.fasterxml.jackson.databind.type.TypeFactory;

public abstract class TypeIdResolverBase
    implements TypeIdResolver
{

    protected TypeIdResolverBase()
    {
        this(null, null);
    }

    protected TypeIdResolverBase(JavaType javatype, TypeFactory typefactory)
    {
        _baseType = javatype;
        _typeFactory = typefactory;
    }

    public String idFromBaseType()
    {
        return idFromValueAndType(null, _baseType.getRawClass());
    }

    public void init(JavaType javatype)
    {
    }

    public JavaType typeFromId(DatabindContext databindcontext, String s)
    {
        return typeFromId(s);
    }

    public abstract JavaType typeFromId(String s);

    protected final JavaType _baseType;
    protected final TypeFactory _typeFactory;
}
