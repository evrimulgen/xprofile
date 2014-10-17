// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.fasterxml.jackson.databind.deser.impl;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.deser.SettableBeanProperty;
import com.fasterxml.jackson.databind.introspect.AnnotatedMember;
import com.fasterxml.jackson.databind.util.Annotations;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.*;

public final class ManagedReferenceProperty extends SettableBeanProperty
{

    public ManagedReferenceProperty(SettableBeanProperty settablebeanproperty, String s, SettableBeanProperty settablebeanproperty1, Annotations annotations, boolean flag)
    {
        super(settablebeanproperty.getFullName(), settablebeanproperty.getType(), settablebeanproperty.getWrapperName(), settablebeanproperty.getValueTypeDeserializer(), annotations, settablebeanproperty.getMetadata());
        _referenceName = s;
        _managedProperty = settablebeanproperty;
        _backProperty = settablebeanproperty1;
        _isContainer = flag;
    }

    protected ManagedReferenceProperty(ManagedReferenceProperty managedreferenceproperty, JsonDeserializer jsondeserializer)
    {
        super(managedreferenceproperty, jsondeserializer);
        _referenceName = managedreferenceproperty._referenceName;
        _isContainer = managedreferenceproperty._isContainer;
        _managedProperty = managedreferenceproperty._managedProperty;
        _backProperty = managedreferenceproperty._backProperty;
    }

    protected ManagedReferenceProperty(ManagedReferenceProperty managedreferenceproperty, PropertyName propertyname)
    {
        super(managedreferenceproperty, propertyname);
        _referenceName = managedreferenceproperty._referenceName;
        _isContainer = managedreferenceproperty._isContainer;
        _managedProperty = managedreferenceproperty._managedProperty;
        _backProperty = managedreferenceproperty._backProperty;
    }

    public void deserializeAndSet(JsonParser jsonparser, DeserializationContext deserializationcontext, Object obj)
        throws IOException, JsonProcessingException
    {
        set(obj, _managedProperty.deserialize(jsonparser, deserializationcontext));
    }

    public Object deserializeSetAndReturn(JsonParser jsonparser, DeserializationContext deserializationcontext, Object obj)
        throws IOException, JsonProcessingException
    {
        return setAndReturn(obj, deserialize(jsonparser, deserializationcontext));
    }

    public Annotation getAnnotation(Class class1)
    {
        return _managedProperty.getAnnotation(class1);
    }

    public AnnotatedMember getMember()
    {
        return _managedProperty.getMember();
    }

    public final void set(Object obj, Object obj1)
        throws IOException
    {
        setAndReturn(obj, obj1);
    }

    public Object setAndReturn(Object obj, Object obj1)
        throws IOException
    {
        Object obj2 = _managedProperty.setAndReturn(obj, obj1);
        if(obj1 != null)
            if(_isContainer)
            {
                if(obj1 instanceof Object[])
                {
                    Object aobj[] = (Object[])(Object[])obj1;
                    int i = aobj.length;
                    for(int j = 0; j < i; j++)
                    {
                        Object obj5 = aobj[j];
                        if(obj5 != null)
                            _backProperty.set(obj5, obj);
                    }

                } else
                if(obj1 instanceof Collection)
                {
                    Iterator iterator1 = ((Collection)obj1).iterator();
                    do
                    {
                        if(!iterator1.hasNext())
                            break;
                        Object obj4 = iterator1.next();
                        if(obj4 != null)
                            _backProperty.set(obj4, obj);
                    } while(true);
                } else
                if(obj1 instanceof Map)
                {
                    Iterator iterator = ((Map)obj1).values().iterator();
                    do
                    {
                        if(!iterator.hasNext())
                            break;
                        Object obj3 = iterator.next();
                        if(obj3 != null)
                            _backProperty.set(obj3, obj);
                    } while(true);
                } else
                {
                    throw new IllegalStateException((new StringBuilder()).append("Unsupported container type (").append(obj1.getClass().getName()).append(") when resolving reference '").append(_referenceName).append("'").toString());
                }
            } else
            {
                _backProperty.set(obj1, obj);
            }
        return obj2;
    }

    public volatile SettableBeanProperty withName(PropertyName propertyname)
    {
        return withName(propertyname);
    }

    public ManagedReferenceProperty withName(PropertyName propertyname)
    {
        return new ManagedReferenceProperty(this, propertyname);
    }

    public volatile SettableBeanProperty withValueDeserializer(JsonDeserializer jsondeserializer)
    {
        return withValueDeserializer(jsondeserializer);
    }

    public ManagedReferenceProperty withValueDeserializer(JsonDeserializer jsondeserializer)
    {
        return new ManagedReferenceProperty(this, jsondeserializer);
    }

    private static final long serialVersionUID = 1L;
    protected final SettableBeanProperty _backProperty;
    protected final boolean _isContainer;
    protected final SettableBeanProperty _managedProperty;
    protected final String _referenceName;
}
