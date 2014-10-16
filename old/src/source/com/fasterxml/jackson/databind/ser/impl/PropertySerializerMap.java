// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.fasterxml.jackson.databind.ser.impl;

import com.fasterxml.jackson.databind.*;

public abstract class PropertySerializerMap
{
    private static final class Double extends PropertySerializerMap
    {

        public PropertySerializerMap newWith(Class class1, JsonSerializer jsonserializer)
        {
            TypeAndSerializer atypeandserializer[] = new TypeAndSerializer[2];
            atypeandserializer[0] = new TypeAndSerializer(_type1, _serializer1);
            atypeandserializer[1] = new TypeAndSerializer(_type2, _serializer2);
            return new Multi(atypeandserializer);
        }

        public JsonSerializer serializerFor(Class class1)
        {
            if(class1 == _type1)
                return _serializer1;
            if(class1 == _type2)
                return _serializer2;
            else
                return null;
        }

        private final JsonSerializer _serializer1;
        private final JsonSerializer _serializer2;
        private final Class _type1;
        private final Class _type2;

        public Double(Class class1, JsonSerializer jsonserializer, Class class2, JsonSerializer jsonserializer1)
        {
            _type1 = class1;
            _serializer1 = jsonserializer;
            _type2 = class2;
            _serializer2 = jsonserializer1;
        }
    }

    private static final class Empty extends PropertySerializerMap
    {

        public PropertySerializerMap newWith(Class class1, JsonSerializer jsonserializer)
        {
            return new Single(class1, jsonserializer);
        }

        public JsonSerializer serializerFor(Class class1)
        {
            return null;
        }

        protected static final Empty instance = new Empty();


        private Empty()
        {
        }
    }

    private static final class Multi extends PropertySerializerMap
    {

        public PropertySerializerMap newWith(Class class1, JsonSerializer jsonserializer)
        {
            int i = _entries.length;
            if(i == 8)
            {
                return this;
            } else
            {
                TypeAndSerializer atypeandserializer[] = new TypeAndSerializer[i + 1];
                System.arraycopy(_entries, 0, atypeandserializer, 0, i);
                atypeandserializer[i] = new TypeAndSerializer(class1, jsonserializer);
                return new Multi(atypeandserializer);
            }
        }

        public JsonSerializer serializerFor(Class class1)
        {
            int i = 0;
            for(int j = _entries.length; i < j; i++)
            {
                TypeAndSerializer typeandserializer = _entries[i];
                if(typeandserializer.type == class1)
                    return typeandserializer.serializer;
            }

            return null;
        }

        private static final int MAX_ENTRIES = 8;
        private final TypeAndSerializer _entries[];

        public Multi(TypeAndSerializer atypeandserializer[])
        {
            _entries = atypeandserializer;
        }
    }

    public static final class SerializerAndMapResult
    {

        public final PropertySerializerMap map;
        public final JsonSerializer serializer;

        public SerializerAndMapResult(JsonSerializer jsonserializer, PropertySerializerMap propertyserializermap)
        {
            serializer = jsonserializer;
            map = propertyserializermap;
        }
    }

    private static final class Single extends PropertySerializerMap
    {

        public PropertySerializerMap newWith(Class class1, JsonSerializer jsonserializer)
        {
            return new Double(_type, _serializer, class1, jsonserializer);
        }

        public JsonSerializer serializerFor(Class class1)
        {
            if(class1 == _type)
                return _serializer;
            else
                return null;
        }

        private final JsonSerializer _serializer;
        private final Class _type;

        public Single(Class class1, JsonSerializer jsonserializer)
        {
            _type = class1;
            _serializer = jsonserializer;
        }
    }

    private static final class TypeAndSerializer
    {

        public final JsonSerializer serializer;
        public final Class type;

        public TypeAndSerializer(Class class1, JsonSerializer jsonserializer)
        {
            type = class1;
            serializer = jsonserializer;
        }
    }


    public PropertySerializerMap()
    {
    }

    public static PropertySerializerMap emptyMap()
    {
        return Empty.instance;
    }

    public final SerializerAndMapResult findAndAddPrimarySerializer(JavaType javatype, SerializerProvider serializerprovider, BeanProperty beanproperty)
        throws JsonMappingException
    {
        JsonSerializer jsonserializer = serializerprovider.findPrimaryPropertySerializer(javatype, beanproperty);
        return new SerializerAndMapResult(jsonserializer, newWith(javatype.getRawClass(), jsonserializer));
    }

    public final SerializerAndMapResult findAndAddPrimarySerializer(Class class1, SerializerProvider serializerprovider, BeanProperty beanproperty)
        throws JsonMappingException
    {
        JsonSerializer jsonserializer = serializerprovider.findPrimaryPropertySerializer(class1, beanproperty);
        return new SerializerAndMapResult(jsonserializer, newWith(class1, jsonserializer));
    }

    public final SerializerAndMapResult findAndAddSecondarySerializer(JavaType javatype, SerializerProvider serializerprovider, BeanProperty beanproperty)
        throws JsonMappingException
    {
        JsonSerializer jsonserializer = serializerprovider.findValueSerializer(javatype, beanproperty);
        return new SerializerAndMapResult(jsonserializer, newWith(javatype.getRawClass(), jsonserializer));
    }

    public final SerializerAndMapResult findAndAddSecondarySerializer(Class class1, SerializerProvider serializerprovider, BeanProperty beanproperty)
        throws JsonMappingException
    {
        JsonSerializer jsonserializer = serializerprovider.findValueSerializer(class1, beanproperty);
        return new SerializerAndMapResult(jsonserializer, newWith(class1, jsonserializer));
    }

    public final SerializerAndMapResult findAndAddSerializer(JavaType javatype, SerializerProvider serializerprovider, BeanProperty beanproperty)
        throws JsonMappingException
    {
        return findAndAddSecondarySerializer(javatype, serializerprovider, beanproperty);
    }

    public final SerializerAndMapResult findAndAddSerializer(Class class1, SerializerProvider serializerprovider, BeanProperty beanproperty)
        throws JsonMappingException
    {
        return findAndAddSecondarySerializer(class1, serializerprovider, beanproperty);
    }

    public abstract PropertySerializerMap newWith(Class class1, JsonSerializer jsonserializer);

    public abstract JsonSerializer serializerFor(Class class1);
}
