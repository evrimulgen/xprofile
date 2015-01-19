// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.fasterxml.jackson.databind.jsontype.impl;

import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.fasterxml.jackson.databind.jsontype.TypeIdResolver;

// Referenced classes of package com.fasterxml.jackson.databind.jsontype.impl:
//            AsArrayTypeDeserializer

public class AsExternalTypeDeserializer extends AsArrayTypeDeserializer
{

    public AsExternalTypeDeserializer(JavaType javatype, TypeIdResolver typeidresolver, String s, boolean flag, Class class1)
    {
        super(javatype, typeidresolver, s, flag, class1);
    }

    public AsExternalTypeDeserializer(AsExternalTypeDeserializer asexternaltypedeserializer, BeanProperty beanproperty)
    {
        super(asexternaltypedeserializer, beanproperty);
    }

    public TypeDeserializer forProperty(BeanProperty beanproperty)
    {
        if(beanproperty == _property)
            return this;
        else
            return new AsExternalTypeDeserializer(this, beanproperty);
    }

    public com.fasterxml.jackson.annotation.JsonTypeInfo.As getTypeInclusion()
    {
        return com.fasterxml.jackson.annotation.JsonTypeInfo.As.EXTERNAL_PROPERTY;
    }

    private static final long serialVersionUID = 1L;
}
