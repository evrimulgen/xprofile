// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.fasterxml.jackson.databind.deser.std;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.deser.SettableBeanProperty;

// Referenced classes of package com.fasterxml.jackson.databind.deser.std:
//            StdDeserializer

public abstract class ContainerDeserializerBase extends StdDeserializer
{

    protected ContainerDeserializerBase(JavaType javatype)
    {
        super(javatype);
    }

    protected ContainerDeserializerBase(Class class1)
    {
        super(class1);
    }

    public SettableBeanProperty findBackReference(String s)
    {
        JsonDeserializer jsondeserializer = getContentDeserializer();
        if(jsondeserializer == null)
            throw new IllegalArgumentException((new StringBuilder()).append("Can not handle managed/back reference '").append(s).append("': type: container deserializer of type ").append(getClass().getName()).append(" returned null for 'getContentDeserializer()'").toString());
        else
            return jsondeserializer.findBackReference(s);
    }

    public abstract JsonDeserializer getContentDeserializer();

    public abstract JavaType getContentType();
}
