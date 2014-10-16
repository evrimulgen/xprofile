// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.fasterxml.jackson.databind.module;

import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.deser.KeyDeserializers;
import com.fasterxml.jackson.databind.type.ClassKey;
import java.io.Serializable;
import java.util.HashMap;

public class SimpleKeyDeserializers
    implements KeyDeserializers, Serializable
{

    public SimpleKeyDeserializers()
    {
        _classMappings = null;
    }

    public SimpleKeyDeserializers addDeserializer(Class class1, KeyDeserializer keydeserializer)
    {
        if(_classMappings == null)
            _classMappings = new HashMap();
        _classMappings.put(new ClassKey(class1), keydeserializer);
        return this;
    }

    public KeyDeserializer findKeyDeserializer(JavaType javatype, DeserializationConfig deserializationconfig, BeanDescription beandescription)
    {
        if(_classMappings == null)
            return null;
        else
            return (KeyDeserializer)_classMappings.get(new ClassKey(javatype.getRawClass()));
    }

    private static final long serialVersionUID = 0xa1d1de4dc728cf95L;
    protected HashMap _classMappings;
}
