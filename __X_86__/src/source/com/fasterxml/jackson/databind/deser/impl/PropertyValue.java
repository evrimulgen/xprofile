// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.fasterxml.jackson.databind.deser.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.deser.SettableAnyProperty;
import com.fasterxml.jackson.databind.deser.SettableBeanProperty;
import java.io.IOException;

public abstract class PropertyValue
{
    static final class Any extends PropertyValue
    {

        public void assign(Object obj)
            throws IOException, JsonProcessingException
        {
            _property.set(obj, _propertyName, value);
        }

        final SettableAnyProperty _property;
        final String _propertyName;

        public Any(PropertyValue propertyvalue, Object obj, SettableAnyProperty settableanyproperty, String s)
        {
            super(propertyvalue, obj);
            _property = settableanyproperty;
            _propertyName = s;
        }
    }

    static final class Map extends PropertyValue
    {

        public void assign(Object obj)
            throws IOException, JsonProcessingException
        {
            ((java.util.Map)obj).put(_key, value);
        }

        final Object _key;

        public Map(PropertyValue propertyvalue, Object obj, Object obj1)
        {
            super(propertyvalue, obj);
            _key = obj1;
        }
    }

    static final class Regular extends PropertyValue
    {

        public void assign(Object obj)
            throws IOException, JsonProcessingException
        {
            _property.set(obj, value);
        }

        final SettableBeanProperty _property;

        public Regular(PropertyValue propertyvalue, Object obj, SettableBeanProperty settablebeanproperty)
        {
            super(propertyvalue, obj);
            _property = settablebeanproperty;
        }
    }


    protected PropertyValue(PropertyValue propertyvalue, Object obj)
    {
        next = propertyvalue;
        value = obj;
    }

    public abstract void assign(Object obj)
        throws IOException, JsonProcessingException;

    public final PropertyValue next;
    public final Object value;
}
