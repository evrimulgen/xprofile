// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.fasterxml.jackson.databind.cfg;

import com.fasterxml.jackson.annotation.ObjectIdGenerator;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.deser.ValueInstantiator;
import com.fasterxml.jackson.databind.introspect.Annotated;
import com.fasterxml.jackson.databind.jsontype.TypeIdResolver;
import com.fasterxml.jackson.databind.jsontype.TypeResolverBuilder;
import com.fasterxml.jackson.databind.util.Converter;

// Referenced classes of package com.fasterxml.jackson.databind.cfg:
//            MapperConfig

public abstract class HandlerInstantiator
{

    public HandlerInstantiator()
    {
    }

    public Converter converterInstance(MapperConfig mapperconfig, Annotated annotated, Class class1)
    {
        return null;
    }

    public abstract JsonDeserializer deserializerInstance(DeserializationConfig deserializationconfig, Annotated annotated, Class class1);

    public abstract KeyDeserializer keyDeserializerInstance(DeserializationConfig deserializationconfig, Annotated annotated, Class class1);

    public PropertyNamingStrategy namingStrategyInstance(MapperConfig mapperconfig, Annotated annotated, Class class1)
    {
        return null;
    }

    public ObjectIdGenerator objectIdGeneratorInstance(MapperConfig mapperconfig, Annotated annotated, Class class1)
    {
        return null;
    }

    public abstract JsonSerializer serializerInstance(SerializationConfig serializationconfig, Annotated annotated, Class class1);

    public abstract TypeIdResolver typeIdResolverInstance(MapperConfig mapperconfig, Annotated annotated, Class class1);

    public abstract TypeResolverBuilder typeResolverBuilderInstance(MapperConfig mapperconfig, Annotated annotated, Class class1);

    public ValueInstantiator valueInstantiatorInstance(MapperConfig mapperconfig, Annotated annotated, Class class1)
    {
        return null;
    }
}
