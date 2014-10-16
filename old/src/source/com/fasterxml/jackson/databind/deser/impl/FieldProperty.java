// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.fasterxml.jackson.databind.deser.impl;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.deser.SettableBeanProperty;
import com.fasterxml.jackson.databind.introspect.*;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.fasterxml.jackson.databind.util.Annotations;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

public final class FieldProperty extends SettableBeanProperty
{

    protected FieldProperty(FieldProperty fieldproperty, JsonDeserializer jsondeserializer)
    {
        super(fieldproperty, jsondeserializer);
        _annotated = fieldproperty._annotated;
        _field = fieldproperty._field;
    }

    protected FieldProperty(FieldProperty fieldproperty, PropertyName propertyname)
    {
        super(fieldproperty, propertyname);
        _annotated = fieldproperty._annotated;
        _field = fieldproperty._field;
    }

    protected FieldProperty(FieldProperty fieldproperty, Field field)
    {
        super(fieldproperty);
        _annotated = fieldproperty._annotated;
        if(field == null)
        {
            throw new IllegalArgumentException((new StringBuilder()).append("No Field passed for property '").append(fieldproperty.getName()).append("' (class ").append(fieldproperty.getDeclaringClass().getName()).append(")").toString());
        } else
        {
            _field = field;
            return;
        }
    }

    public FieldProperty(BeanPropertyDefinition beanpropertydefinition, JavaType javatype, TypeDeserializer typedeserializer, Annotations annotations, AnnotatedField annotatedfield)
    {
        super(beanpropertydefinition, javatype, typedeserializer, annotations);
        _annotated = annotatedfield;
        _field = annotatedfield.getAnnotated();
    }

    public void deserializeAndSet(JsonParser jsonparser, DeserializationContext deserializationcontext, Object obj)
        throws IOException, JsonProcessingException
    {
        set(obj, deserialize(jsonparser, deserializationcontext));
    }

    public Object deserializeSetAndReturn(JsonParser jsonparser, DeserializationContext deserializationcontext, Object obj)
        throws IOException, JsonProcessingException
    {
        return setAndReturn(obj, deserialize(jsonparser, deserializationcontext));
    }

    public Annotation getAnnotation(Class class1)
    {
        return _annotated.getAnnotation(class1);
    }

    public AnnotatedMember getMember()
    {
        return _annotated;
    }

    Object readResolve()
    {
        return new FieldProperty(this, _annotated.getAnnotated());
    }

    public final void set(Object obj, Object obj1)
        throws IOException
    {
        try
        {
            _field.set(obj, obj1);
            return;
        }
        catch(Exception exception)
        {
            _throwAsIOE(exception, obj1);
        }
    }

    public Object setAndReturn(Object obj, Object obj1)
        throws IOException
    {
        try
        {
            _field.set(obj, obj1);
        }
        catch(Exception exception)
        {
            _throwAsIOE(exception, obj1);
            return obj;
        }
        return obj;
    }

    public volatile SettableBeanProperty withName(PropertyName propertyname)
    {
        return withName(propertyname);
    }

    public FieldProperty withName(PropertyName propertyname)
    {
        return new FieldProperty(this, propertyname);
    }

    public volatile SettableBeanProperty withValueDeserializer(JsonDeserializer jsondeserializer)
    {
        return withValueDeserializer(jsondeserializer);
    }

    public FieldProperty withValueDeserializer(JsonDeserializer jsondeserializer)
    {
        return new FieldProperty(this, jsondeserializer);
    }

    private static final long serialVersionUID = 1L;
    protected final AnnotatedField _annotated;
    protected final transient Field _field;
}
