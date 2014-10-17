// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.fasterxml.jackson.databind.ser.impl;

import com.fasterxml.jackson.annotation.ObjectIdGenerator;
import com.fasterxml.jackson.core.io.SerializedString;
import com.fasterxml.jackson.databind.*;

public final class ObjectIdWriter
{

    protected ObjectIdWriter(JavaType javatype, SerializedString serializedstring, ObjectIdGenerator objectidgenerator, JsonSerializer jsonserializer, boolean flag)
    {
        idType = javatype;
        propertyName = serializedstring;
        generator = objectidgenerator;
        serializer = jsonserializer;
        alwaysAsId = flag;
    }

    public static ObjectIdWriter construct(JavaType javatype, PropertyName propertyname, ObjectIdGenerator objectidgenerator, boolean flag)
    {
        String s;
        if(propertyname == null)
            s = null;
        else
            s = propertyname.getSimpleName();
        return construct(javatype, s, objectidgenerator, flag);
    }

    public static ObjectIdWriter construct(JavaType javatype, String s, ObjectIdGenerator objectidgenerator, boolean flag)
    {
        SerializedString serializedstring;
        if(s == null)
            serializedstring = null;
        else
            serializedstring = new SerializedString(s);
        return new ObjectIdWriter(javatype, serializedstring, objectidgenerator, null, flag);
    }

    public ObjectIdWriter withAlwaysAsId(boolean flag)
    {
        if(flag == alwaysAsId)
            return this;
        else
            return new ObjectIdWriter(idType, propertyName, generator, serializer, flag);
    }

    public ObjectIdWriter withSerializer(JsonSerializer jsonserializer)
    {
        return new ObjectIdWriter(idType, propertyName, generator, jsonserializer, alwaysAsId);
    }

    public final boolean alwaysAsId;
    public final ObjectIdGenerator generator;
    public final JavaType idType;
    public final SerializedString propertyName;
    public final JsonSerializer serializer;
}
