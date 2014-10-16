// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.fasterxml.jackson.databind.jsontype.impl;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.jsontype.TypeIdResolver;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import java.io.IOException;

// Referenced classes of package com.fasterxml.jackson.databind.jsontype.impl:
//            TypeSerializerBase

public class AsExternalTypeSerializer extends TypeSerializerBase
{

    public AsExternalTypeSerializer(TypeIdResolver typeidresolver, BeanProperty beanproperty, String s)
    {
        super(typeidresolver, beanproperty);
        _typePropertyName = s;
    }

    protected final void _writeArrayPrefix(Object obj, JsonGenerator jsongenerator)
        throws IOException, JsonProcessingException
    {
        jsongenerator.writeStartArray();
    }

    protected final void _writeArraySuffix(Object obj, JsonGenerator jsongenerator, String s)
        throws IOException, JsonProcessingException
    {
        jsongenerator.writeEndArray();
        jsongenerator.writeStringField(_typePropertyName, s);
    }

    protected final void _writeObjectPrefix(Object obj, JsonGenerator jsongenerator)
        throws IOException, JsonProcessingException
    {
        jsongenerator.writeStartObject();
    }

    protected final void _writeObjectSuffix(Object obj, JsonGenerator jsongenerator, String s)
        throws IOException, JsonProcessingException
    {
        jsongenerator.writeEndObject();
        jsongenerator.writeStringField(_typePropertyName, s);
    }

    protected final void _writeScalarPrefix(Object obj, JsonGenerator jsongenerator)
        throws IOException, JsonProcessingException
    {
    }

    protected final void _writeScalarSuffix(Object obj, JsonGenerator jsongenerator, String s)
        throws IOException, JsonProcessingException
    {
        jsongenerator.writeStringField(_typePropertyName, s);
    }

    public volatile TypeSerializer forProperty(BeanProperty beanproperty)
    {
        return forProperty(beanproperty);
    }

    public AsExternalTypeSerializer forProperty(BeanProperty beanproperty)
    {
        if(_property == beanproperty)
            return this;
        else
            return new AsExternalTypeSerializer(_idResolver, beanproperty, _typePropertyName);
    }

    public String getPropertyName()
    {
        return _typePropertyName;
    }

    public com.fasterxml.jackson.annotation.JsonTypeInfo.As getTypeInclusion()
    {
        return com.fasterxml.jackson.annotation.JsonTypeInfo.As.EXTERNAL_PROPERTY;
    }

    public void writeCustomTypePrefixForArray(Object obj, JsonGenerator jsongenerator, String s)
        throws IOException, JsonProcessingException
    {
        _writeArrayPrefix(obj, jsongenerator);
    }

    public void writeCustomTypePrefixForObject(Object obj, JsonGenerator jsongenerator, String s)
        throws IOException, JsonProcessingException
    {
        _writeObjectPrefix(obj, jsongenerator);
    }

    public void writeCustomTypePrefixForScalar(Object obj, JsonGenerator jsongenerator, String s)
        throws IOException, JsonProcessingException
    {
        _writeScalarPrefix(obj, jsongenerator);
    }

    public void writeCustomTypeSuffixForArray(Object obj, JsonGenerator jsongenerator, String s)
        throws IOException, JsonProcessingException
    {
        _writeArraySuffix(obj, jsongenerator, s);
    }

    public void writeCustomTypeSuffixForObject(Object obj, JsonGenerator jsongenerator, String s)
        throws IOException, JsonProcessingException
    {
        _writeObjectSuffix(obj, jsongenerator, s);
    }

    public void writeCustomTypeSuffixForScalar(Object obj, JsonGenerator jsongenerator, String s)
        throws IOException, JsonProcessingException
    {
        _writeScalarSuffix(obj, jsongenerator, s);
    }

    public void writeTypePrefixForArray(Object obj, JsonGenerator jsongenerator)
        throws IOException, JsonProcessingException
    {
        _writeArrayPrefix(obj, jsongenerator);
    }

    public void writeTypePrefixForArray(Object obj, JsonGenerator jsongenerator, Class class1)
        throws IOException, JsonProcessingException
    {
        _writeArrayPrefix(obj, jsongenerator);
    }

    public void writeTypePrefixForObject(Object obj, JsonGenerator jsongenerator)
        throws IOException, JsonProcessingException
    {
        _writeObjectPrefix(obj, jsongenerator);
    }

    public void writeTypePrefixForObject(Object obj, JsonGenerator jsongenerator, Class class1)
        throws IOException, JsonProcessingException
    {
        _writeObjectPrefix(obj, jsongenerator);
    }

    public void writeTypePrefixForScalar(Object obj, JsonGenerator jsongenerator)
        throws IOException, JsonProcessingException
    {
        _writeScalarPrefix(obj, jsongenerator);
    }

    public void writeTypePrefixForScalar(Object obj, JsonGenerator jsongenerator, Class class1)
        throws IOException, JsonProcessingException
    {
        _writeScalarPrefix(obj, jsongenerator);
    }

    public void writeTypeSuffixForArray(Object obj, JsonGenerator jsongenerator)
        throws IOException, JsonProcessingException
    {
        _writeArraySuffix(obj, jsongenerator, idFromValue(obj));
    }

    public void writeTypeSuffixForObject(Object obj, JsonGenerator jsongenerator)
        throws IOException, JsonProcessingException
    {
        _writeObjectSuffix(obj, jsongenerator, idFromValue(obj));
    }

    public void writeTypeSuffixForScalar(Object obj, JsonGenerator jsongenerator)
        throws IOException, JsonProcessingException
    {
        _writeScalarSuffix(obj, jsongenerator, idFromValue(obj));
    }

    protected final String _typePropertyName;
}
