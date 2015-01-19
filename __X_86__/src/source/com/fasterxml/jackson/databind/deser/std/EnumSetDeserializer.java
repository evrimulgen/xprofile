// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.fasterxml.jackson.databind.deser.std;

import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.deser.ContextualDeserializer;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import java.io.IOException;
import java.util.EnumSet;

// Referenced classes of package com.fasterxml.jackson.databind.deser.std:
//            StdDeserializer

public class EnumSetDeserializer extends StdDeserializer
    implements ContextualDeserializer
{

    public EnumSetDeserializer(JavaType javatype, JsonDeserializer jsondeserializer)
    {
        super(java/util/EnumSet);
        _enumType = javatype;
        _enumClass = javatype.getRawClass();
        _enumDeserializer = jsondeserializer;
    }

    private EnumSet constructSet()
    {
        return EnumSet.noneOf(_enumClass);
    }

    public JsonDeserializer createContextual(DeserializationContext deserializationcontext, BeanProperty beanproperty)
        throws JsonMappingException
    {
        JsonDeserializer jsondeserializer = _enumDeserializer;
        JsonDeserializer jsondeserializer1;
        if(jsondeserializer == null)
            jsondeserializer1 = deserializationcontext.findContextualValueDeserializer(_enumType, beanproperty);
        else
            jsondeserializer1 = deserializationcontext.handleSecondaryContextualization(jsondeserializer, beanproperty);
        return withDeserializer(jsondeserializer1);
    }

    public volatile Object deserialize(JsonParser jsonparser, DeserializationContext deserializationcontext)
        throws IOException, JsonProcessingException
    {
        return deserialize(jsonparser, deserializationcontext);
    }

    public EnumSet deserialize(JsonParser jsonparser, DeserializationContext deserializationcontext)
        throws IOException, JsonProcessingException
    {
        if(!jsonparser.isExpectedStartArrayToken())
            throw deserializationcontext.mappingException(java/util/EnumSet);
        EnumSet enumset = constructSet();
        do
        {
            JsonToken jsontoken = jsonparser.nextToken();
            if(jsontoken == JsonToken.END_ARRAY)
                break;
            if(jsontoken == JsonToken.VALUE_NULL)
                throw deserializationcontext.mappingException(_enumClass);
            Enum enum = (Enum)_enumDeserializer.deserialize(jsonparser, deserializationcontext);
            if(enum != null)
                enumset.add(enum);
        } while(true);
        return enumset;
    }

    public Object deserializeWithType(JsonParser jsonparser, DeserializationContext deserializationcontext, TypeDeserializer typedeserializer)
        throws IOException, JsonProcessingException
    {
        return typedeserializer.deserializeTypedFromArray(jsonparser, deserializationcontext);
    }

    public boolean isCachable()
    {
        return true;
    }

    public EnumSetDeserializer withDeserializer(JsonDeserializer jsondeserializer)
    {
        if(_enumDeserializer == jsondeserializer)
            return this;
        else
            return new EnumSetDeserializer(_enumType, jsondeserializer);
    }

    private static final long serialVersionUID = 0x304981f4d0f126c9L;
    protected final Class _enumClass;
    protected JsonDeserializer _enumDeserializer;
    protected final JavaType _enumType;
}
