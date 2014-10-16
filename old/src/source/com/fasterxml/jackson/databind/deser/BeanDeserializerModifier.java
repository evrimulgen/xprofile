// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.fasterxml.jackson.databind.deser;

import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.type.*;
import java.util.List;

// Referenced classes of package com.fasterxml.jackson.databind.deser:
//            BeanDeserializerBuilder

public abstract class BeanDeserializerModifier
{

    public BeanDeserializerModifier()
    {
    }

    public JsonDeserializer modifyArrayDeserializer(DeserializationConfig deserializationconfig, ArrayType arraytype, BeanDescription beandescription, JsonDeserializer jsondeserializer)
    {
        return jsondeserializer;
    }

    public JsonDeserializer modifyCollectionDeserializer(DeserializationConfig deserializationconfig, CollectionType collectiontype, BeanDescription beandescription, JsonDeserializer jsondeserializer)
    {
        return jsondeserializer;
    }

    public JsonDeserializer modifyCollectionLikeDeserializer(DeserializationConfig deserializationconfig, CollectionLikeType collectionliketype, BeanDescription beandescription, JsonDeserializer jsondeserializer)
    {
        return jsondeserializer;
    }

    public JsonDeserializer modifyDeserializer(DeserializationConfig deserializationconfig, BeanDescription beandescription, JsonDeserializer jsondeserializer)
    {
        return jsondeserializer;
    }

    public JsonDeserializer modifyEnumDeserializer(DeserializationConfig deserializationconfig, JavaType javatype, BeanDescription beandescription, JsonDeserializer jsondeserializer)
    {
        return jsondeserializer;
    }

    public KeyDeserializer modifyKeyDeserializer(DeserializationConfig deserializationconfig, JavaType javatype, KeyDeserializer keydeserializer)
    {
        return keydeserializer;
    }

    public JsonDeserializer modifyMapDeserializer(DeserializationConfig deserializationconfig, MapType maptype, BeanDescription beandescription, JsonDeserializer jsondeserializer)
    {
        return jsondeserializer;
    }

    public JsonDeserializer modifyMapLikeDeserializer(DeserializationConfig deserializationconfig, MapLikeType mapliketype, BeanDescription beandescription, JsonDeserializer jsondeserializer)
    {
        return jsondeserializer;
    }

    public BeanDeserializerBuilder updateBuilder(DeserializationConfig deserializationconfig, BeanDescription beandescription, BeanDeserializerBuilder beandeserializerbuilder)
    {
        return beandeserializerbuilder;
    }

    public List updateProperties(DeserializationConfig deserializationconfig, BeanDescription beandescription, List list)
    {
        return list;
    }
}
