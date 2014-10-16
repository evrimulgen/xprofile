// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.fasterxml.jackson.databind.ser;

import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;

// Referenced classes of package com.fasterxml.jackson.databind.ser:
//            Serializers, BeanSerializerModifier

public abstract class SerializerFactory
{

    public SerializerFactory()
    {
    }

    public JsonSerializer createKeySerializer(SerializationConfig serializationconfig, JavaType javatype)
        throws JsonMappingException
    {
        return createKeySerializer(serializationconfig, javatype, null);
    }

    public abstract JsonSerializer createKeySerializer(SerializationConfig serializationconfig, JavaType javatype, JsonSerializer jsonserializer)
        throws JsonMappingException;

    public abstract JsonSerializer createSerializer(SerializerProvider serializerprovider, JavaType javatype)
        throws JsonMappingException;

    public JsonSerializer createSerializer(SerializerProvider serializerprovider, JavaType javatype, BeanProperty beanproperty)
        throws JsonMappingException
    {
        return createSerializer(serializerprovider, javatype);
    }

    public abstract TypeSerializer createTypeSerializer(SerializationConfig serializationconfig, JavaType javatype)
        throws JsonMappingException;

    public abstract SerializerFactory withAdditionalKeySerializers(Serializers serializers);

    public abstract SerializerFactory withAdditionalSerializers(Serializers serializers);

    public abstract SerializerFactory withSerializerModifier(BeanSerializerModifier beanserializermodifier);
}
