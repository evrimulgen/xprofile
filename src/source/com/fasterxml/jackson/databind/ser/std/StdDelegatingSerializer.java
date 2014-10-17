// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.fasterxml.jackson.databind.ser.std;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatVisitable;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatVisitorWrapper;
import com.fasterxml.jackson.databind.jsonschema.SchemaAware;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;
import com.fasterxml.jackson.databind.ser.ResolvableSerializer;
import com.fasterxml.jackson.databind.util.Converter;
import java.io.IOException;
import java.lang.reflect.Type;

// Referenced classes of package com.fasterxml.jackson.databind.ser.std:
//            StdSerializer

public class StdDelegatingSerializer extends StdSerializer
    implements ContextualSerializer, ResolvableSerializer, JsonFormatVisitable, SchemaAware
{

    public StdDelegatingSerializer(Converter converter)
    {
        super(java/lang/Object);
        _converter = converter;
        _delegateType = null;
        _delegateSerializer = null;
    }

    public StdDelegatingSerializer(Converter converter, JavaType javatype, JsonSerializer jsonserializer)
    {
        super(javatype);
        _converter = converter;
        _delegateType = javatype;
        _delegateSerializer = jsonserializer;
    }

    public StdDelegatingSerializer(Class class1, Converter converter)
    {
        super(class1, false);
        _converter = converter;
        _delegateType = null;
        _delegateSerializer = null;
    }

    public void acceptJsonFormatVisitor(JsonFormatVisitorWrapper jsonformatvisitorwrapper, JavaType javatype)
        throws JsonMappingException
    {
        _delegateSerializer.acceptJsonFormatVisitor(jsonformatvisitorwrapper, javatype);
    }

    protected Object convertValue(Object obj)
    {
        return _converter.convert(obj);
    }

    public JsonSerializer createContextual(SerializerProvider serializerprovider, BeanProperty beanproperty)
        throws JsonMappingException
    {
label0:
        {
            JsonSerializer jsonserializer;
label1:
            {
                if(_delegateSerializer == null)
                    break label0;
                if(_delegateSerializer instanceof ContextualSerializer)
                {
                    jsonserializer = serializerprovider.handleSecondaryContextualization(_delegateSerializer, beanproperty);
                    if(jsonserializer != _delegateSerializer)
                        break label1;
                }
                return this;
            }
            return withDelegate(_converter, _delegateType, jsonserializer);
        }
        JavaType javatype = _delegateType;
        if(javatype == null)
            javatype = _converter.getOutputType(serializerprovider.getTypeFactory());
        return withDelegate(_converter, javatype, serializerprovider.findValueSerializer(javatype, beanproperty));
    }

    protected Converter getConverter()
    {
        return _converter;
    }

    public JsonSerializer getDelegatee()
    {
        return _delegateSerializer;
    }

    public JsonNode getSchema(SerializerProvider serializerprovider, Type type)
        throws JsonMappingException
    {
        if(_delegateSerializer instanceof SchemaAware)
            return ((SchemaAware)_delegateSerializer).getSchema(serializerprovider, type);
        else
            return super.getSchema(serializerprovider, type);
    }

    public JsonNode getSchema(SerializerProvider serializerprovider, Type type, boolean flag)
        throws JsonMappingException
    {
        if(_delegateSerializer instanceof SchemaAware)
            return ((SchemaAware)_delegateSerializer).getSchema(serializerprovider, type, flag);
        else
            return super.getSchema(serializerprovider, type);
    }

    public boolean isEmpty(Object obj)
    {
        Object obj1 = convertValue(obj);
        return _delegateSerializer.isEmpty(obj1);
    }

    public void resolve(SerializerProvider serializerprovider)
        throws JsonMappingException
    {
        if(_delegateSerializer != null && (_delegateSerializer instanceof ResolvableSerializer))
            ((ResolvableSerializer)_delegateSerializer).resolve(serializerprovider);
    }

    public void serialize(Object obj, JsonGenerator jsongenerator, SerializerProvider serializerprovider)
        throws IOException, JsonProcessingException
    {
        Object obj1 = convertValue(obj);
        if(obj1 == null)
        {
            serializerprovider.defaultSerializeNull(jsongenerator);
            return;
        } else
        {
            _delegateSerializer.serialize(obj1, jsongenerator, serializerprovider);
            return;
        }
    }

    public void serializeWithType(Object obj, JsonGenerator jsongenerator, SerializerProvider serializerprovider, TypeSerializer typeserializer)
        throws IOException, JsonProcessingException
    {
        Object obj1 = convertValue(obj);
        _delegateSerializer.serializeWithType(obj1, jsongenerator, serializerprovider, typeserializer);
    }

    protected StdDelegatingSerializer withDelegate(Converter converter, JavaType javatype, JsonSerializer jsonserializer)
    {
        if(getClass() != com/fasterxml/jackson/databind/ser/std/StdDelegatingSerializer)
            throw new IllegalStateException((new StringBuilder()).append("Sub-class ").append(getClass().getName()).append(" must override 'withDelegate'").toString());
        else
            return new StdDelegatingSerializer(converter, javatype, jsonserializer);
    }

    protected final Converter _converter;
    protected final JsonSerializer _delegateSerializer;
    protected final JavaType _delegateType;
}
