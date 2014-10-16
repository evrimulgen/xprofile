// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.fasterxml.jackson.databind.deser;

import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.introspect.AnnotatedMethod;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.Method;

public class SettableAnyProperty
    implements Serializable
{

    public SettableAnyProperty(BeanProperty beanproperty, AnnotatedMethod annotatedmethod, JavaType javatype, JsonDeserializer jsondeserializer)
    {
        this(beanproperty, annotatedmethod, javatype, jsondeserializer, null);
    }

    public SettableAnyProperty(BeanProperty beanproperty, AnnotatedMethod annotatedmethod, JavaType javatype, JsonDeserializer jsondeserializer, TypeDeserializer typedeserializer)
    {
        this(beanproperty, annotatedmethod.getAnnotated(), javatype, jsondeserializer, typedeserializer);
    }

    public SettableAnyProperty(BeanProperty beanproperty, Method method, JavaType javatype, JsonDeserializer jsondeserializer)
    {
        this(beanproperty, method, javatype, jsondeserializer, null);
    }

    public SettableAnyProperty(BeanProperty beanproperty, Method method, JavaType javatype, JsonDeserializer jsondeserializer, TypeDeserializer typedeserializer)
    {
        _property = beanproperty;
        _type = javatype;
        _setter = method;
        _valueDeserializer = jsondeserializer;
        _valueTypeDeserializer = typedeserializer;
    }

    private String getClassName()
    {
        return _setter.getDeclaringClass().getName();
    }

    protected void _throwAsIOE(Exception exception, String s, Object obj)
        throws IOException
    {
        if(exception instanceof IllegalArgumentException)
        {
            String s1;
            StringBuilder stringbuilder;
            String s2;
            if(obj == null)
                s1 = "[NULL]";
            else
                s1 = obj.getClass().getName();
            stringbuilder = (new StringBuilder("Problem deserializing \"any\" property '")).append(s);
            stringbuilder.append((new StringBuilder()).append("' of class ").append(getClassName()).append(" (expected type: ").toString()).append(_type);
            stringbuilder.append("; actual type: ").append(s1).append(")");
            s2 = exception.getMessage();
            if(s2 != null)
                stringbuilder.append(", problem: ").append(s2);
            else
                stringbuilder.append(" (no error message provided)");
            throw new JsonMappingException(stringbuilder.toString(), null, exception);
        }
        if(exception instanceof IOException)
            throw (IOException)exception;
        if(exception instanceof RuntimeException)
            throw (RuntimeException)exception;
        for(; exception.getCause() != null; exception = exception.getCause());
        throw new JsonMappingException(exception.getMessage(), null, exception);
    }

    public Object deserialize(JsonParser jsonparser, DeserializationContext deserializationcontext)
        throws IOException, JsonProcessingException
    {
        if(jsonparser.getCurrentToken() == JsonToken.VALUE_NULL)
            return null;
        if(_valueTypeDeserializer != null)
            return _valueDeserializer.deserializeWithType(jsonparser, deserializationcontext, _valueTypeDeserializer);
        else
            return _valueDeserializer.deserialize(jsonparser, deserializationcontext);
    }

    public final void deserializeAndSet(JsonParser jsonparser, DeserializationContext deserializationcontext, Object obj, String s)
        throws IOException, JsonProcessingException
    {
        set(obj, s, deserialize(jsonparser, deserializationcontext));
    }

    public BeanProperty getProperty()
    {
        return _property;
    }

    public JavaType getType()
    {
        return _type;
    }

    public boolean hasValueDeserializer()
    {
        return _valueDeserializer != null;
    }

    public void set(Object obj, String s, Object obj1)
        throws IOException
    {
        try
        {
            _setter.invoke(obj, new Object[] {
                s, obj1
            });
            return;
        }
        catch(Exception exception)
        {
            _throwAsIOE(exception, s, obj1);
        }
    }

    public String toString()
    {
        return (new StringBuilder()).append("[any property on class ").append(getClassName()).append("]").toString();
    }

    public SettableAnyProperty withValueDeserializer(JsonDeserializer jsondeserializer)
    {
        return new SettableAnyProperty(_property, _setter, _type, jsondeserializer, _valueTypeDeserializer);
    }

    private static final long serialVersionUID = 1L;
    protected final BeanProperty _property;
    protected final transient Method _setter;
    protected final JavaType _type;
    protected JsonDeserializer _valueDeserializer;
    protected final TypeDeserializer _valueTypeDeserializer;
}
