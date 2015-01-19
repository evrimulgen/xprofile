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
import java.lang.reflect.Method;

public final class MethodProperty extends SettableBeanProperty
{

    protected MethodProperty(MethodProperty methodproperty, JsonDeserializer jsondeserializer)
    {
        super(methodproperty, jsondeserializer);
        _annotated = methodproperty._annotated;
        _setter = methodproperty._setter;
    }

    protected MethodProperty(MethodProperty methodproperty, PropertyName propertyname)
    {
        super(methodproperty, propertyname);
        _annotated = methodproperty._annotated;
        _setter = methodproperty._setter;
    }

    protected MethodProperty(MethodProperty methodproperty, Method method)
    {
        super(methodproperty);
        _annotated = methodproperty._annotated;
        _setter = method;
    }

    public MethodProperty(BeanPropertyDefinition beanpropertydefinition, JavaType javatype, TypeDeserializer typedeserializer, Annotations annotations, AnnotatedMethod annotatedmethod)
    {
        super(beanpropertydefinition, javatype, typedeserializer, annotations);
        _annotated = annotatedmethod;
        _setter = annotatedmethod.getAnnotated();
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
        return new MethodProperty(this, _annotated.getAnnotated());
    }

    public final void set(Object obj, Object obj1)
        throws IOException
    {
        try
        {
            _setter.invoke(obj, new Object[] {
                obj1
            });
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
        Object obj2;
        try
        {
            obj2 = _setter.invoke(obj, new Object[] {
                obj1
            });
        }
        catch(Exception exception)
        {
            _throwAsIOE(exception, obj1);
            return null;
        }
        if(obj2 == null)
            return obj;
        else
            return obj2;
    }

    public volatile SettableBeanProperty withName(PropertyName propertyname)
    {
        return withName(propertyname);
    }

    public MethodProperty withName(PropertyName propertyname)
    {
        return new MethodProperty(this, propertyname);
    }

    public volatile SettableBeanProperty withValueDeserializer(JsonDeserializer jsondeserializer)
    {
        return withValueDeserializer(jsondeserializer);
    }

    public MethodProperty withValueDeserializer(JsonDeserializer jsondeserializer)
    {
        return new MethodProperty(this, jsondeserializer);
    }

    private static final long serialVersionUID = 1L;
    protected final AnnotatedMethod _annotated;
    protected final transient Method _setter;
}
