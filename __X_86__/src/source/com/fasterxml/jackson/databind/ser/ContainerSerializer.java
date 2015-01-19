// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.fasterxml.jackson.databind.ser;

import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

public abstract class ContainerSerializer extends StdSerializer
{

    protected ContainerSerializer(ContainerSerializer containerserializer)
    {
        super(containerserializer._handledType, false);
    }

    protected ContainerSerializer(Class class1)
    {
        super(class1);
    }

    protected ContainerSerializer(Class class1, boolean flag)
    {
        super(class1, flag);
    }

    protected abstract ContainerSerializer _withValueTypeSerializer(TypeSerializer typeserializer);

    public abstract JsonSerializer getContentSerializer();

    public abstract JavaType getContentType();

    protected boolean hasContentTypeAnnotation(SerializerProvider serializerprovider, BeanProperty beanproperty)
    {
        if(beanproperty != null)
        {
            AnnotationIntrospector annotationintrospector = serializerprovider.getAnnotationIntrospector();
            if(annotationintrospector != null && annotationintrospector.findSerializationContentType(beanproperty.getMember(), beanproperty.getType()) != null)
                return true;
        }
        return false;
    }

    public abstract boolean hasSingleElement(Object obj);

    public abstract boolean isEmpty(Object obj);

    public ContainerSerializer withValueTypeSerializer(TypeSerializer typeserializer)
    {
        if(typeserializer == null)
            return this;
        else
            return _withValueTypeSerializer(typeserializer);
    }
}
