// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.fasterxml.jackson.databind.util;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import java.io.IOException;

public class JSONWrappedObject
    implements JsonSerializable
{

    public JSONWrappedObject(String s, String s1, Object obj)
    {
        this(s, s1, obj, (JavaType)null);
    }

    public JSONWrappedObject(String s, String s1, Object obj, JavaType javatype)
    {
        _prefix = s;
        _suffix = s1;
        _value = obj;
        _serializationType = javatype;
    }

    public String getPrefix()
    {
        return _prefix;
    }

    public JavaType getSerializationType()
    {
        return _serializationType;
    }

    public String getSuffix()
    {
        return _suffix;
    }

    public Object getValue()
    {
        return _value;
    }

    public void serialize(JsonGenerator jsongenerator, SerializerProvider serializerprovider)
        throws IOException, JsonProcessingException
    {
        if(_prefix != null)
            jsongenerator.writeRaw(_prefix);
        if(_value == null)
            serializerprovider.defaultSerializeNull(jsongenerator);
        else
        if(_serializationType != null)
            serializerprovider.findTypedValueSerializer(_serializationType, true, null).serialize(_value, jsongenerator, serializerprovider);
        else
            serializerprovider.findTypedValueSerializer(_value.getClass(), true, null).serialize(_value, jsongenerator, serializerprovider);
        if(_suffix != null)
            jsongenerator.writeRaw(_suffix);
    }

    public void serializeWithType(JsonGenerator jsongenerator, SerializerProvider serializerprovider, TypeSerializer typeserializer)
        throws IOException, JsonProcessingException
    {
        serialize(jsongenerator, serializerprovider);
    }

    protected final String _prefix;
    protected final JavaType _serializationType;
    protected final String _suffix;
    protected final Object _value;
}
