// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.fasterxml.jackson.databind.module;

import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.deser.Deserializers;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.fasterxml.jackson.databind.type.*;
import java.io.Serializable;
import java.util.*;

public class SimpleDeserializers
    implements Deserializers, Serializable
{

    public SimpleDeserializers()
    {
        _classMappings = null;
        _hasEnumDeserializer = false;
    }

    public SimpleDeserializers(Map map)
    {
        _classMappings = null;
        _hasEnumDeserializer = false;
        addDeserializers(map);
    }

    public void addDeserializer(Class class1, JsonDeserializer jsondeserializer)
    {
        ClassKey classkey = new ClassKey(class1);
        if(_classMappings == null)
            _classMappings = new HashMap();
        _classMappings.put(classkey, jsondeserializer);
        if(class1 == java/lang/Enum)
            _hasEnumDeserializer = true;
    }

    public void addDeserializers(Map map)
    {
        java.util.Map.Entry entry;
        for(Iterator iterator = map.entrySet().iterator(); iterator.hasNext(); addDeserializer((Class)entry.getKey(), (JsonDeserializer)entry.getValue()))
            entry = (java.util.Map.Entry)iterator.next();

    }

    public JsonDeserializer findArrayDeserializer(ArrayType arraytype, DeserializationConfig deserializationconfig, BeanDescription beandescription, TypeDeserializer typedeserializer, JsonDeserializer jsondeserializer)
        throws JsonMappingException
    {
        if(_classMappings == null)
            return null;
        else
            return (JsonDeserializer)_classMappings.get(new ClassKey(arraytype.getRawClass()));
    }

    public JsonDeserializer findBeanDeserializer(JavaType javatype, DeserializationConfig deserializationconfig, BeanDescription beandescription)
        throws JsonMappingException
    {
        if(_classMappings == null)
            return null;
        else
            return (JsonDeserializer)_classMappings.get(new ClassKey(javatype.getRawClass()));
    }

    public JsonDeserializer findCollectionDeserializer(CollectionType collectiontype, DeserializationConfig deserializationconfig, BeanDescription beandescription, TypeDeserializer typedeserializer, JsonDeserializer jsondeserializer)
        throws JsonMappingException
    {
        if(_classMappings == null)
            return null;
        else
            return (JsonDeserializer)_classMappings.get(new ClassKey(collectiontype.getRawClass()));
    }

    public JsonDeserializer findCollectionLikeDeserializer(CollectionLikeType collectionliketype, DeserializationConfig deserializationconfig, BeanDescription beandescription, TypeDeserializer typedeserializer, JsonDeserializer jsondeserializer)
        throws JsonMappingException
    {
        if(_classMappings == null)
            return null;
        else
            return (JsonDeserializer)_classMappings.get(new ClassKey(collectionliketype.getRawClass()));
    }

    public JsonDeserializer findEnumDeserializer(Class class1, DeserializationConfig deserializationconfig, BeanDescription beandescription)
        throws JsonMappingException
    {
        JsonDeserializer jsondeserializer;
        if(_classMappings == null)
        {
            jsondeserializer = null;
        } else
        {
            jsondeserializer = (JsonDeserializer)_classMappings.get(new ClassKey(class1));
            if(jsondeserializer == null && _hasEnumDeserializer && class1.isEnum())
                return (JsonDeserializer)_classMappings.get(new ClassKey(java/lang/Enum));
        }
        return jsondeserializer;
    }

    public JsonDeserializer findMapDeserializer(MapType maptype, DeserializationConfig deserializationconfig, BeanDescription beandescription, KeyDeserializer keydeserializer, TypeDeserializer typedeserializer, JsonDeserializer jsondeserializer)
        throws JsonMappingException
    {
        if(_classMappings == null)
            return null;
        else
            return (JsonDeserializer)_classMappings.get(new ClassKey(maptype.getRawClass()));
    }

    public JsonDeserializer findMapLikeDeserializer(MapLikeType mapliketype, DeserializationConfig deserializationconfig, BeanDescription beandescription, KeyDeserializer keydeserializer, TypeDeserializer typedeserializer, JsonDeserializer jsondeserializer)
        throws JsonMappingException
    {
        if(_classMappings == null)
            return null;
        else
            return (JsonDeserializer)_classMappings.get(new ClassKey(mapliketype.getRawClass()));
    }

    public JsonDeserializer findTreeNodeDeserializer(Class class1, DeserializationConfig deserializationconfig, BeanDescription beandescription)
        throws JsonMappingException
    {
        if(_classMappings == null)
            return null;
        else
            return (JsonDeserializer)_classMappings.get(new ClassKey(class1));
    }

    private static final long serialVersionUID = 0xd6462683a6216850L;
    protected HashMap _classMappings;
    protected boolean _hasEnumDeserializer;
}
