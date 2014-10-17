// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.fasterxml.jackson.databind.deser.impl;

import com.fasterxml.jackson.annotation.ObjectIdGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.deser.SettableBeanProperty;
import java.io.IOException;
import java.io.Serializable;

public class ObjectIdReader
    implements Serializable
{

    protected ObjectIdReader(JavaType javatype, PropertyName propertyname, ObjectIdGenerator objectidgenerator, JsonDeserializer jsondeserializer, SettableBeanProperty settablebeanproperty)
    {
        _idType = javatype;
        propertyName = propertyname;
        generator = objectidgenerator;
        _deserializer = jsondeserializer;
        idProperty = settablebeanproperty;
    }

    protected ObjectIdReader(JavaType javatype, String s, ObjectIdGenerator objectidgenerator, JsonDeserializer jsondeserializer, SettableBeanProperty settablebeanproperty)
    {
        this(javatype, new PropertyName(s), objectidgenerator, jsondeserializer, settablebeanproperty);
    }

    public static ObjectIdReader construct(JavaType javatype, PropertyName propertyname, ObjectIdGenerator objectidgenerator, JsonDeserializer jsondeserializer, SettableBeanProperty settablebeanproperty)
    {
        return new ObjectIdReader(javatype, propertyname, objectidgenerator, jsondeserializer, settablebeanproperty);
    }

    public static ObjectIdReader construct(JavaType javatype, String s, ObjectIdGenerator objectidgenerator, JsonDeserializer jsondeserializer, SettableBeanProperty settablebeanproperty)
    {
        return construct(javatype, new PropertyName(s), objectidgenerator, jsondeserializer, settablebeanproperty);
    }

    public JsonDeserializer getDeserializer()
    {
        return _deserializer;
    }

    public JavaType getIdType()
    {
        return _idType;
    }

    public Object readObjectReference(JsonParser jsonparser, DeserializationContext deserializationcontext)
        throws IOException, JsonProcessingException
    {
        return _deserializer.deserialize(jsonparser, deserializationcontext);
    }

    private static final long serialVersionUID = 1L;
    protected final JsonDeserializer _deserializer;
    protected final JavaType _idType;
    public final ObjectIdGenerator generator;
    public final SettableBeanProperty idProperty;
    public final PropertyName propertyName;
}
