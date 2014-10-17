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
import java.util.Collection;
import java.util.Iterator;

public class StringCollectionSerializer extends StaticListSerializerBase
    implements ContextualSerializer
{

    protected StringCollectionSerializer()
    {
        this(null);
    }

    protected StringCollectionSerializer(JsonSerializer jsonserializer)
    {
        super(java/util/Collection);
        _serializer = jsonserializer;
    }

    private final void _serializeUnwrapped(Collection collection, JsonGenerator jsongenerator, SerializerProvider serializerprovider)
        throws IOException, JsonGenerationException
    {
        if(_serializer == null)
        {
            serializeContents(collection, jsongenerator, serializerprovider);
            return;
        } else
        {
            serializeUsingCustom(collection, jsongenerator, serializerprovider);
            return;
        }
    }

    private final void serializeContents(Collection collection, JsonGenerator jsongenerator, SerializerProvider serializerprovider)
        throws IOException, JsonGenerationException
    {
        if(_serializer == null) goto _L2; else goto _L1
_L1:
        serializeUsingCustom(collection, jsongenerator, serializerprovider);
_L6:
        return;
_L2:
        Iterator iterator;
        int i;
        iterator = collection.iterator();
        i = 0;
_L4:
        int j;
        if(!iterator.hasNext())
            continue; /* Loop/switch isn't completed */
        String s = (String)iterator.next();
        if(s == null)
        {
            try
            {
                serializerprovider.defaultSerializeNull(jsongenerator);
            }
            catch(Exception exception)
            {
                wrapAndThrow(serializerprovider, exception, collection, i);
                j = i;
                break MISSING_BLOCK_LABEL_95;
            }
            break MISSING_BLOCK_LABEL_89;
        }
        jsongenerator.writeString(s);
        j = i + 1;
        i = j;
        if(true) goto _L4; else goto _L3
_L3:
        if(true) goto _L6; else goto _L5
_L5:
    }

    private void serializeUsingCustom(Collection collection, JsonGenerator jsongenerator, SerializerProvider serializerprovider)
        throws IOException, JsonGenerationException
    {
        JsonSerializer jsonserializer;
        Iterator iterator;
        jsonserializer = _serializer;
        iterator = collection.iterator();
_L2:
        String s;
        if(!iterator.hasNext())
            break; /* Loop/switch isn't completed */
        s = (String)iterator.next();
        if(s == null)
        {
            try
            {
                serializerprovider.defaultSerializeNull(jsongenerator);
            }
            catch(Exception exception)
            {
                wrapAndThrow(serializerprovider, exception, collection, 0);
            }
            continue; /* Loop/switch isn't completed */
        }
        jsonserializer.serialize(s, jsongenerator, serializerprovider);
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
            return new StringCollectionSerializer(jsonserializer2);
_L2:
        jsonserializer = null;
        if(true) goto _L6; else goto _L5
_L5:
    }

    public volatile void serialize(Object obj, JsonGenerator jsongenerator, SerializerProvider serializerprovider)
        throws IOException, JsonGenerationException
    {
        serialize((Collection)obj, jsongenerator, serializerprovider);
    }

    public void serialize(Collection collection, JsonGenerator jsongenerator, SerializerProvider serializerprovider)
        throws IOException, JsonGenerationException
    {
        if(collection.size() == 1 && serializerprovider.isEnabled(SerializationFeature.WRITE_SINGLE_ELEM_ARRAYS_UNWRAPPED))
        {
            _serializeUnwrapped(collection, jsongenerator, serializerprovider);
            return;
        }
        jsongenerator.writeStartArray();
        if(_serializer == null)
            serializeContents(collection, jsongenerator, serializerprovider);
        else
            serializeUsingCustom(collection, jsongenerator, serializerprovider);
        jsongenerator.writeEndArray();
    }

    public volatile void serializeWithType(Object obj, JsonGenerator jsongenerator, SerializerProvider serializerprovider, TypeSerializer typeserializer)
        throws IOException, JsonProcessingException
    {
        serializeWithType((Collection)obj, jsongenerator, serializerprovider, typeserializer);
    }

    public void serializeWithType(Collection collection, JsonGenerator jsongenerator, SerializerProvider serializerprovider, TypeSerializer typeserializer)
        throws IOException, JsonGenerationException
    {
        typeserializer.writeTypePrefixForArray(collection, jsongenerator);
        if(_serializer == null)
            serializeContents(collection, jsongenerator, serializerprovider);
        else
            serializeUsingCustom(collection, jsongenerator, serializerprovider);
        typeserializer.writeTypeSuffixForArray(collection, jsongenerator);
    }

    public static final StringCollectionSerializer instance = new StringCollectionSerializer();
    protected final JsonSerializer _serializer;

}
