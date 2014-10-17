// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.fasterxml.jackson.databind.ser.impl;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import java.io.IOException;

public final class TypeWrappedSerializer extends JsonSerializer
{

    public TypeWrappedSerializer(TypeSerializer typeserializer, JsonSerializer jsonserializer)
    {
        _typeSerializer = typeserializer;
        _serializer = jsonserializer;
    }

    public Class handledType()
    {
        return java/lang/Object;
    }

    public void serialize(Object obj, JsonGenerator jsongenerator, SerializerProvider serializerprovider)
        throws IOException, JsonProcessingException
    {
        _serializer.serializeWithType(obj, jsongenerator, serializerprovider, _typeSerializer);
    }

    public void serializeWithType(Object obj, JsonGenerator jsongenerator, SerializerProvider serializerprovider, TypeSerializer typeserializer)
        throws IOException, JsonProcessingException
    {
        _serializer.serializeWithType(obj, jsongenerator, serializerprovider, typeserializer);
    }

    protected final JsonSerializer _serializer;
    protected final TypeSerializer _typeSerializer;
}
