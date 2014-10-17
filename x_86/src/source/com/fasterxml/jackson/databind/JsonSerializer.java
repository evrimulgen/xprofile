// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.fasterxml.jackson.databind;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatVisitable;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatVisitorWrapper;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.fasterxml.jackson.databind.util.NameTransformer;
import java.io.IOException;

// Referenced classes of package com.fasterxml.jackson.databind:
//            JsonMappingException, JavaType, SerializerProvider

public abstract class JsonSerializer
    implements JsonFormatVisitable
{
    public static abstract class None extends JsonSerializer
    {

        public None()
        {
        }
    }


    public JsonSerializer()
    {
    }

    public void acceptJsonFormatVisitor(JsonFormatVisitorWrapper jsonformatvisitorwrapper, JavaType javatype)
        throws JsonMappingException
    {
        if(jsonformatvisitorwrapper != null)
            jsonformatvisitorwrapper.expectAnyFormat(javatype);
    }

    public JsonSerializer getDelegatee()
    {
        return null;
    }

    public Class handledType()
    {
        return null;
    }

    public boolean isEmpty(Object obj)
    {
        return obj == null;
    }

    public boolean isUnwrappingSerializer()
    {
        return false;
    }

    public JsonSerializer replaceDelegatee(JsonSerializer jsonserializer)
    {
        throw new UnsupportedOperationException();
    }

    public abstract void serialize(Object obj, JsonGenerator jsongenerator, SerializerProvider serializerprovider)
        throws IOException, JsonProcessingException;

    public void serializeWithType(Object obj, JsonGenerator jsongenerator, SerializerProvider serializerprovider, TypeSerializer typeserializer)
        throws IOException, JsonProcessingException
    {
        Class class1 = handledType();
        if(class1 == null)
            class1 = obj.getClass();
        throw new UnsupportedOperationException((new StringBuilder()).append("Type id handling not implemented for type ").append(class1.getName()).toString());
    }

    public JsonSerializer unwrappingSerializer(NameTransformer nametransformer)
    {
        return this;
    }

    public boolean usesObjectId()
    {
        return false;
    }
}
