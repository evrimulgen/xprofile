// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.fasterxml.jackson.databind.ser.std;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonObjectFormatVisitor;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.ser.PropertyWriter;
import java.io.IOException;

public class MapProperty extends PropertyWriter
{

    public MapProperty(TypeSerializer typeserializer)
    {
        _typeSerializer = typeserializer;
    }

    public void depositSchemaProperty(JsonObjectFormatVisitor jsonobjectformatvisitor)
        throws JsonMappingException
    {
    }

    public void depositSchemaProperty(ObjectNode objectnode, SerializerProvider serializerprovider)
        throws JsonMappingException
    {
    }

    public PropertyName getFullName()
    {
        return new PropertyName(getName());
    }

    public String getName()
    {
        if(_key instanceof String)
            return (String)_key;
        else
            return String.valueOf(_key);
    }

    public void reset(Object obj, Object obj1, JsonSerializer jsonserializer, JsonSerializer jsonserializer1)
    {
        _key = obj;
        _value = obj1;
        _keySerializer = jsonserializer;
        _valueSerializer = jsonserializer1;
    }

    public void serializeAsElement(Object obj, JsonGenerator jsongenerator, SerializerProvider serializerprovider)
        throws Exception
    {
        if(_typeSerializer == null)
        {
            _valueSerializer.serialize(_value, jsongenerator, serializerprovider);
            return;
        } else
        {
            _valueSerializer.serializeWithType(_value, jsongenerator, serializerprovider, _typeSerializer);
            return;
        }
    }

    public void serializeAsField(Object obj, JsonGenerator jsongenerator, SerializerProvider serializerprovider)
        throws IOException
    {
        _keySerializer.serialize(_key, jsongenerator, serializerprovider);
        if(_typeSerializer == null)
        {
            _valueSerializer.serialize(_value, jsongenerator, serializerprovider);
            return;
        } else
        {
            _valueSerializer.serializeWithType(_value, jsongenerator, serializerprovider, _typeSerializer);
            return;
        }
    }

    public void serializeAsOmittedField(Object obj, JsonGenerator jsongenerator, SerializerProvider serializerprovider)
        throws Exception
    {
        if(!jsongenerator.canOmitFields())
            jsongenerator.writeOmittedField(getName());
    }

    public void serializeAsPlaceholder(Object obj, JsonGenerator jsongenerator, SerializerProvider serializerprovider)
        throws Exception
    {
        jsongenerator.writeNull();
    }

    protected Object _key;
    protected JsonSerializer _keySerializer;
    protected TypeSerializer _typeSerializer;
    protected Object _value;
    protected JsonSerializer _valueSerializer;
}
