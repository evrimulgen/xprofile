// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.fasterxml.jackson.databind.ser.impl;

import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonArrayFormatVisitor;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatTypes;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;
import com.fasterxml.jackson.databind.ser.std.StaticListSerializerBase;
import java.io.IOException;
import java.util.List;

public final class IndexedStringListSerializer extends StaticListSerializerBase
    implements ContextualSerializer
{

    protected IndexedStringListSerializer()
    {
        this(null);
    }

    public IndexedStringListSerializer(JsonSerializer jsonserializer)
    {
        super(java/util/List);
        _serializer = jsonserializer;
    }

    private final void _serializeUnwrapped(List list, JsonGenerator jsongenerator, SerializerProvider serializerprovider)
        throws IOException, JsonGenerationException
    {
        if(_serializer == null)
        {
            serializeContents(list, jsongenerator, serializerprovider, 1);
            return;
        } else
        {
            serializeUsingCustom(list, jsongenerator, serializerprovider, 1);
            return;
        }
    }

    private final void serializeContents(List list, JsonGenerator jsongenerator, SerializerProvider serializerprovider, int i)
        throws IOException, JsonGenerationException
    {
        int j = 0;
_L2:
        if(j >= i)
            break MISSING_BLOCK_LABEL_57;
        String s = (String)list.get(j);
        if(s == null)
        {
            try
            {
                serializerprovider.defaultSerializeNull(jsongenerator);
                break MISSING_BLOCK_LABEL_58;
            }
            catch(Exception exception)
            {
                wrapAndThrow(serializerprovider, exception, list, j);
            }
            break MISSING_BLOCK_LABEL_57;
        }
        jsongenerator.writeString(s);
        break MISSING_BLOCK_LABEL_58;
        return;
        j++;
        if(true) goto _L2; else goto _L1
_L1:
    }

    private final void serializeUsingCustom(List list, JsonGenerator jsongenerator, SerializerProvider serializerprovider, int i)
        throws IOException, JsonGenerationException
    {
        int j = 0;
        JsonSerializer jsonserializer = _serializer;
_L2:
        if(j >= i)
            break MISSING_BLOCK_LABEL_66;
        String s = (String)list.get(j);
        if(s == null)
        {
            try
            {
                serializerprovider.defaultSerializeNull(jsongenerator);
                break MISSING_BLOCK_LABEL_67;
            }
            catch(Exception exception)
            {
                wrapAndThrow(serializerprovider, exception, list, j);
            }
            break MISSING_BLOCK_LABEL_66;
        }
        jsonserializer.serialize(s, jsongenerator, serializerprovider);
        break MISSING_BLOCK_LABEL_67;
        return;
        j++;
        if(true) goto _L2; else goto _L1
_L1:
    }

    protected void acceptContentVisitor(JsonArrayFormatVisitor jsonarrayformatvisitor)
        throws JsonMappingException
    {
        jsonarrayformatvisitor.itemsFormat(JsonFormatTypes.STRING);
    }

    protected JsonNode contentSchema()
    {
        return createSchemaNode("string", true);
    }

    public JsonSerializer createContextual(SerializerProvider serializerprovider, BeanProperty beanproperty)
        throws JsonMappingException
    {
        if(beanproperty == null) goto _L2; else goto _L1
_L1:
        com.fasterxml.jackson.databind.introspect.AnnotatedMember annotatedmember = beanproperty.getMember();
        if(annotatedmember == null) goto _L2; else goto _L3
_L3:
        Object obj = serializerprovider.getAnnotationIntrospector().findContentSerializer(annotatedmember);
        if(obj == null) goto _L2; else goto _L4
_L4:
        JsonSerializer jsonserializer = serializerprovider.serializerInstance(annotatedmember, obj);
_L6:
        if(jsonserializer == null)
            jsonserializer = _serializer;
        JsonSerializer jsonserializer1 = findConvertingContentSerializer(serializerprovider, beanproperty, jsonserializer);
        JsonSerializer jsonserializer2;
        if(jsonserializer1 == null)
            jsonserializer2 = serializerprovider.findValueSerializer(java/lang/String, beanproperty);
        else
            jsonserializer2 = serializerprovider.handleSecondaryContextualization(jsonserializer1, beanproperty);
        if(isDefaultSerializer(jsonserializer2))
            jsonserializer2 = null;
        if(jsonserializer2 == _serializer)
            return this;
        else
            return new IndexedStringListSerializer(jsonserializer2);
_L2:
        jsonserializer = null;
        if(true) goto _L6; else goto _L5
_L5:
    }

    public volatile void serialize(Object obj, JsonGenerator jsongenerator, SerializerProvider serializerprovider)
        throws IOException, JsonGenerationException
    {
        serialize((List)obj, jsongenerator, serializerprovider);
    }

    public void serialize(List list, JsonGenerator jsongenerator, SerializerProvider serializerprovider)
        throws IOException, JsonGenerationException
    {
        int i = list.size();
        if(i == 1 && serializerprovider.isEnabled(SerializationFeature.WRITE_SINGLE_ELEM_ARRAYS_UNWRAPPED))
        {
            _serializeUnwrapped(list, jsongenerator, serializerprovider);
            return;
        }
        jsongenerator.writeStartArray();
        if(_serializer == null)
            serializeContents(list, jsongenerator, serializerprovider, i);
        else
            serializeUsingCustom(list, jsongenerator, serializerprovider, i);
        jsongenerator.writeEndArray();
    }

    public volatile void serializeWithType(Object obj, JsonGenerator jsongenerator, SerializerProvider serializerprovider, TypeSerializer typeserializer)
        throws IOException, JsonProcessingException
    {
        serializeWithType((List)obj, jsongenerator, serializerprovider, typeserializer);
    }

    public void serializeWithType(List list, JsonGenerator jsongenerator, SerializerProvider serializerprovider, TypeSerializer typeserializer)
        throws IOException, JsonGenerationException
    {
        int i = list.size();
        typeserializer.writeTypePrefixForArray(list, jsongenerator);
        if(_serializer == null)
            serializeContents(list, jsongenerator, serializerprovider, i);
        else
            serializeUsingCustom(list, jsongenerator, serializerprovider, i);
        typeserializer.writeTypeSuffixForArray(list, jsongenerator);
    }

    public static final IndexedStringListSerializer instance = new IndexedStringListSerializer();
    protected final JsonSerializer _serializer;

}
