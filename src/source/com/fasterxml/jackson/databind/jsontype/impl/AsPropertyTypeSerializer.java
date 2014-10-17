// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.fasterxml.jackson.databind.jsontype.impl;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.jsontype.TypeIdResolver;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import java.io.IOException;

// Referenced classes of package com.fasterxml.jackson.databind.jsontype.impl:
//            AsArrayTypeSerializer

public class AsPropertyTypeSerializer extends AsArrayTypeSerializer
{

    public AsPropertyTypeSerializer(TypeIdResolver typeidresolver, BeanProperty beanproperty, String s)
    {
        super(typeidresolver, beanproperty);
        _typePropertyName = s;
    }

    public volatile TypeSerializer forProperty(BeanProperty beanproperty)
    {
        return forProperty(beanproperty);
    }

    public volatile AsArrayTypeSerializer forProperty(BeanProperty beanproperty)
    {
        return forProperty(beanproperty);
    }

    public AsPropertyTypeSerializer forProperty(BeanProperty beanproperty)
    {
        if(_property == beanproperty)
            return this;
        else
            return new AsPropertyTypeSerializer(_idResolver, beanproperty, _typePropertyName);
    }

    public String getPropertyName()
    {
        return _typePropertyName;
    }

    public com.fasterxml.jackson.annotation.JsonTypeInfo.As getTypeInclusion()
    {
        return com.fasterxml.jackson.annotation.JsonTypeInfo.As.PROPERTY;
    }

    public void writeCustomTypePrefixForObject(Object obj, JsonGenerator jsongenerator, String s)
        throws IOException, JsonProcessingException
    {
        if(jsongenerator.canWriteTypeId())
        {
            jsongenerator.writeTypeId(s);
            jsongenerator.writeStartObject();
            return;
        } else
        {
            jsongenerator.writeStartObject();
            jsongenerator.writeStringField(_typePropertyName, s);
            return;
        }
    }

    public void writeCustomTypeSuffixForObject(Object obj, JsonGenerator jsongenerator, String s)
        throws IOException, JsonProcessingException
    {
        jsongenerator.writeEndObject();
    }

    public void writeTypePrefixForObject(Object obj, JsonGenerator jsongenerator)
        throws IOException, JsonProcessingException
    {
        String s = idFromValue(obj);
        if(jsongenerator.canWriteTypeId())
        {
            jsongenerator.writeTypeId(s);
            jsongenerator.writeStartObject();
            return;
        } else
        {
            jsongenerator.writeStartObject();
            jsongenerator.writeStringField(_typePropertyName, s);
            return;
        }
    }

    public void writeTypePrefixForObject(Object obj, JsonGenerator jsongenerator, Class class1)
        throws IOException, JsonProcessingException
    {
        String s = idFromValueAndType(obj, class1);
        if(jsongenerator.canWriteTypeId())
        {
            jsongenerator.writeTypeId(s);
            jsongenerator.writeStartObject();
            return;
        } else
        {
            jsongenerator.writeStartObject();
            jsongenerator.writeStringField(_typePropertyName, s);
            return;
        }
    }

    public void writeTypeSuffixForObject(Object obj, JsonGenerator jsongenerator)
        throws IOException, JsonProcessingException
    {
        jsongenerator.writeEndObject();
    }

    protected final String _typePropertyName;
}
