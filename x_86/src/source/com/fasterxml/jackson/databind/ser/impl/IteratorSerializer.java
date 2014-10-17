// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.fasterxml.jackson.databind.ser.impl;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.fasterxml.jackson.databind.ser.ContainerSerializer;
import com.fasterxml.jackson.databind.ser.std.AsArraySerializerBase;
import java.io.IOException;
import java.util.Iterator;

public class IteratorSerializer extends AsArraySerializerBase
{

    public IteratorSerializer(JavaType javatype, boolean flag, TypeSerializer typeserializer, BeanProperty beanproperty)
    {
        super(java/util/Iterator, javatype, flag, typeserializer, beanproperty, null);
    }

    public IteratorSerializer(IteratorSerializer iteratorserializer, BeanProperty beanproperty, TypeSerializer typeserializer, JsonSerializer jsonserializer)
    {
        super(iteratorserializer, beanproperty, typeserializer, jsonserializer);
    }

    public ContainerSerializer _withValueTypeSerializer(TypeSerializer typeserializer)
    {
        return new IteratorSerializer(_elementType, _staticTyping, typeserializer, _property);
    }

    public volatile boolean hasSingleElement(Object obj)
    {
        return hasSingleElement((Iterator)obj);
    }

    public boolean hasSingleElement(Iterator iterator)
    {
        return false;
    }

    public volatile boolean isEmpty(Object obj)
    {
        return isEmpty((Iterator)obj);
    }

    public boolean isEmpty(Iterator iterator)
    {
        return iterator == null || !iterator.hasNext();
    }

    public volatile void serializeContents(Object obj, JsonGenerator jsongenerator, SerializerProvider serializerprovider)
        throws IOException, JsonGenerationException
    {
        serializeContents((Iterator)obj, jsongenerator, serializerprovider);
    }

    public void serializeContents(Iterator iterator, JsonGenerator jsongenerator, SerializerProvider serializerprovider)
        throws IOException, JsonGenerationException
    {
        Class class1 = null;
        if(iterator.hasNext())
        {
            TypeSerializer typeserializer = _valueTypeSerializer;
            JsonSerializer jsonserializer = null;
            do
            {
                Object obj = iterator.next();
                if(obj == null)
                {
                    serializerprovider.defaultSerializeNull(jsongenerator);
                } else
                {
                    JsonSerializer jsonserializer1 = _elementSerializer;
                    if(jsonserializer1 == null)
                    {
                        Class class2 = obj.getClass();
                        if(class2 == class1)
                        {
                            jsonserializer1 = jsonserializer;
                        } else
                        {
                            jsonserializer = serializerprovider.findValueSerializer(class2, _property);
                            class1 = class2;
                            jsonserializer1 = jsonserializer;
                        }
                    }
                    if(typeserializer == null)
                        jsonserializer1.serialize(obj, jsongenerator, serializerprovider);
                    else
                        jsonserializer1.serializeWithType(obj, jsongenerator, serializerprovider, typeserializer);
                }
            } while(iterator.hasNext());
        }
    }

    public IteratorSerializer withResolved(BeanProperty beanproperty, TypeSerializer typeserializer, JsonSerializer jsonserializer)
    {
        return new IteratorSerializer(this, beanproperty, typeserializer, jsonserializer);
    }

    public volatile AsArraySerializerBase withResolved(BeanProperty beanproperty, TypeSerializer typeserializer, JsonSerializer jsonserializer)
    {
        return withResolved(beanproperty, typeserializer, jsonserializer);
    }
}
