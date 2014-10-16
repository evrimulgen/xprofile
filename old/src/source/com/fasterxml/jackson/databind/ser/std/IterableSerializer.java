// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.fasterxml.jackson.databind.ser.std;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.fasterxml.jackson.databind.ser.ContainerSerializer;
import java.io.IOException;
import java.util.Iterator;

// Referenced classes of package com.fasterxml.jackson.databind.ser.std:
//            AsArraySerializerBase

public class IterableSerializer extends AsArraySerializerBase
{

    public IterableSerializer(JavaType javatype, boolean flag, TypeSerializer typeserializer, BeanProperty beanproperty)
    {
        super(java/lang/Iterable, javatype, flag, typeserializer, beanproperty, null);
    }

    public IterableSerializer(IterableSerializer iterableserializer, BeanProperty beanproperty, TypeSerializer typeserializer, JsonSerializer jsonserializer)
    {
        super(iterableserializer, beanproperty, typeserializer, jsonserializer);
    }

    public ContainerSerializer _withValueTypeSerializer(TypeSerializer typeserializer)
    {
        return new IterableSerializer(_elementType, _staticTyping, typeserializer, _property);
    }

    public boolean hasSingleElement(Iterable iterable)
    {
        if(iterable != null)
        {
            Iterator iterator = iterable.iterator();
            if(iterator.hasNext())
            {
                iterator.next();
                if(!iterator.hasNext())
                    return true;
            }
        }
        return false;
    }

    public volatile boolean hasSingleElement(Object obj)
    {
        return hasSingleElement((Iterable)obj);
    }

    public boolean isEmpty(Iterable iterable)
    {
        return iterable == null || !iterable.iterator().hasNext();
    }

    public volatile boolean isEmpty(Object obj)
    {
        return isEmpty((Iterable)obj);
    }

    public void serializeContents(Iterable iterable, JsonGenerator jsongenerator, SerializerProvider serializerprovider)
        throws IOException, JsonGenerationException
    {
        Class class1 = null;
        Iterator iterator = iterable.iterator();
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

    public volatile void serializeContents(Object obj, JsonGenerator jsongenerator, SerializerProvider serializerprovider)
        throws IOException, JsonGenerationException
    {
        serializeContents((Iterable)obj, jsongenerator, serializerprovider);
    }

    public volatile AsArraySerializerBase withResolved(BeanProperty beanproperty, TypeSerializer typeserializer, JsonSerializer jsonserializer)
    {
        return withResolved(beanproperty, typeserializer, jsonserializer);
    }

    public IterableSerializer withResolved(BeanProperty beanproperty, TypeSerializer typeserializer, JsonSerializer jsonserializer)
    {
        return new IterableSerializer(this, beanproperty, typeserializer, jsonserializer);
    }
}
