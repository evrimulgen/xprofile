// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.newrelic.com.google.gson;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.Collection;

public final class FieldAttributes
{

    public FieldAttributes(Field field1)
    {
        com.newrelic.com.google.gson.internal..Gson.Preconditions.checkNotNull(field1);
        field = field1;
    }

    Object get(Object obj)
        throws IllegalAccessException
    {
        return field.get(obj);
    }

    public Annotation getAnnotation(Class class1)
    {
        return field.getAnnotation(class1);
    }

    public Collection getAnnotations()
    {
        return Arrays.asList(field.getAnnotations());
    }

    public Class getDeclaredClass()
    {
        return field.getType();
    }

    public Type getDeclaredType()
    {
        return field.getGenericType();
    }

    public Class getDeclaringClass()
    {
        return field.getDeclaringClass();
    }

    public String getName()
    {
        return field.getName();
    }

    public boolean hasModifier(int i)
    {
        return (i & field.getModifiers()) != 0;
    }

    boolean isSynthetic()
    {
        return field.isSynthetic();
    }

    private final Field field;
}
