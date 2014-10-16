// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.fasterxml.jackson.databind.ser.impl;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.jsonFormatVisitors.*;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.ser.ContainerSerializer;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;
import com.fasterxml.jackson.databind.ser.std.ArraySerializerBase;
import com.fasterxml.jackson.databind.type.TypeFactory;
import java.io.IOException;
import java.lang.reflect.Type;

public class StringArraySerializer extends ArraySerializerBase
    implements ContextualSerializer
{

    protected StringArraySerializer()
    {
        super([Ljava/lang/String;, null);
        _elementSerializer = null;
    }

    public StringArraySerializer(StringArraySerializer stringarrayserializer, BeanProperty beanproperty, JsonSerializer jsonserializer)
    {
        super(stringarrayserializer, beanproperty);
        _elementSerializer = jsonserializer;
    }

    private void serializeContentsSlow(String as[], JsonGenerator jsongenerator, SerializerProvider serializerprovider, JsonSerializer jsonserializer)
        throws IOException, JsonGenerationException
    {
        int i = 0;
        int j = as.length;
        while(i < j) 
        {
            if(as[i] == null)
                serializerprovider.defaultSerializeNull(jsongenerator);
            else
                jsonserializer.serialize(as[i], jsongenerator, serializerprovider);
            i++;
        }
    }

    public ContainerSerializer _withValueTypeSerializer(TypeSerializer typeserializer)
    {
        return this;
    }

    public void acceptJsonFormatVisitor(JsonFormatVisitorWrapper jsonformatvisitorwrapper, JavaType javatype)
        throws JsonMappingException
    {
        if(jsonformatvisitorwrapper != null)
        {
            JsonArrayFormatVisitor jsonarrayformatvisitor = jsonformatvisitorwrapper.expectArrayFormat(javatype);
            if(jsonarrayformatvisitor != null)
                jsonarrayformatvisitor.itemsFormat(JsonFormatTypes.STRING);
        }
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
            jsonserializer = _elementSerializer;
        JsonSerializer jsonserializer1 = findConvertingContentSerializer(serializerprovider, beanproperty, jsonserializer);
        JsonSerializer jsonserializer2;
        if(jsonserializer1 == null)
            jsonserializer2 = serializerprovider.findValueSerializer(java/lang/String, beanproperty);
        else
            jsonserializer2 = serializerprovider.handleSecondaryContextualization(jsonserializer1, beanproperty);
        if(isDefaultSerializer(jsonserializer2))
            jsonserializer2 = null;
        if(jsonserializer2 == _elementSerializer)
            return this;
        else
            return new StringArraySerializer(this, beanproperty, jsonserializer2);
_L2:
        jsonserializer = null;
        if(true) goto _L6; else goto _L5
_L5:
    }

    public JsonSerializer getContentSerializer()
    {
        return _elementSerializer;
    }

    public JavaType getContentType()
    {
        return VALUE_TYPE;
    }

    public JsonNode getSchema(SerializerProvider serializerprovider, Type type)
    {
        ObjectNode objectnode = createSchemaNode("array", true);
        objectnode.put("items", createSchemaNode("string"));
        return objectnode;
    }

    public volatile boolean hasSingleElement(Object obj)
    {
        return hasSingleElement((String[])obj);
    }

    public boolean hasSingleElement(String as[])
    {
        return as.length == 1;
    }

    public volatile boolean isEmpty(Object obj)
    {
        return isEmpty((String[])obj);
    }

    public boolean isEmpty(String as[])
    {
        return as == null || as.length == 0;
    }

    public volatile void serializeContents(Object obj, JsonGenerator jsongenerator, SerializerProvider serializerprovider)
        throws IOException, JsonGenerationException
    {
        serializeContents((String[])obj, jsongenerator, serializerprovider);
    }

    public void serializeContents(String as[], JsonGenerator jsongenerator, SerializerProvider serializerprovider)
        throws IOException, JsonGenerationException
    {
        int i = as.length;
        if(i != 0)
        {
            if(_elementSerializer != null)
            {
                serializeContentsSlow(as, jsongenerator, serializerprovider, _elementSerializer);
                return;
            }
            int j = 0;
            while(j < i) 
            {
                if(as[j] == null)
                    jsongenerator.writeNull();
                else
                    jsongenerator.writeString(as[j]);
                j++;
            }
        }
    }

    private static final JavaType VALUE_TYPE = TypeFactory.defaultInstance().uncheckedSimpleType(java/lang/String);
    public static final StringArraySerializer instance = new StringArraySerializer();
    protected final JsonSerializer _elementSerializer;

}
