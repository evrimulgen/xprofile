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
//            TypeSerializerBase

public class AsWrapperTypeSerializer extends TypeSerializerBase
{

    public AsWrapperTypeSerializer(TypeIdResolver typeidresolver, BeanProperty beanproperty)
    {
        super(typeidresolver, beanproperty);
    }

    public volatile TypeSerializer forProperty(BeanProperty beanproperty)
    {
        return forProperty(beanproperty);
    }

    public AsWrapperTypeSerializer forProperty(BeanProperty beanproperty)
    {
        if(_property == beanproperty)
            return this;
        else
            return new AsWrapperTypeSerializer(_idResolver, beanproperty);
    }

    public com.fasterxml.jackson.annotation.JsonTypeInfo.As getTypeInclusion()
    {
        return com.fasterxml.jackson.annotation.JsonTypeInfo.As.WRAPPER_OBJECT;
    }

    public void writeCustomTypePrefixForArray(Object obj, JsonGenerator jsongenerator, String s)
        throws IOException, JsonProcessingException
    {
        if(jsongenerator.canWriteTypeId())
        {
            jsongenerator.writeTypeId(s);
            jsongenerator.writeStartArray();
            return;
        } else
        {
            jsongenerator.writeStartObject();
            jsongenerator.writeArrayFieldStart(s);
            return;
        }
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
            jsongenerator.writeObjectFieldStart(s);
            return;
        }
    }

    public void writeCustomTypePrefixForScalar(Object obj, JsonGenerator jsongenerator, String s)
        throws IOException, JsonProcessingException
    {
        if(jsongenerator.canWriteTypeId())
        {
            jsongenerator.writeTypeId(s);
            return;
        } else
        {
            jsongenerator.writeStartObject();
            jsongenerator.writeFieldName(s);
            return;
        }
    }

    public void writeCustomTypeSuffixForArray(Object obj, JsonGenerator jsongenerator, String s)
        throws IOException, JsonProcessingException
    {
        if(!jsongenerator.canWriteTypeId())
            writeTypeSuffixForArray(obj, jsongenerator);
    }

    public void writeCustomTypeSuffixForObject(Object obj, JsonGenerator jsongenerator, String s)
        throws IOException, JsonProcessingException
    {
        if(!jsongenerator.canWriteTypeId())
            writeTypeSuffixForObject(obj, jsongenerator);
    }

    public void writeCustomTypeSuffixForScalar(Object obj, JsonGenerator jsongenerator, String s)
        throws IOException, JsonProcessingException
    {
        if(!jsongenerator.canWriteTypeId())
            writeTypeSuffixForScalar(obj, jsongenerator);
    }

    public void writeTypePrefixForArray(Object obj, JsonGenerator jsongenerator)
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
            jsongenerator.writeArrayFieldStart(s);
            return;
        }
    }

    public void writeTypePrefixForArray(Object obj, JsonGenerator jsongenerator, Class class1)
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
            jsongenerator.writeArrayFieldStart(s);
            return;
        }
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
            jsongenerator.writeObjectFieldStart(s);
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
            jsongenerator.writeObjectFieldStart(s);
            return;
        }
    }

    public void writeTypePrefixForScalar(Object obj, JsonGenerator jsongenerator)
        throws IOException, JsonProcessingException
    {
        String s = idFromValue(obj);
        if(jsongenerator.canWriteTypeId())
        {
            jsongenerator.writeTypeId(s);
            return;
        } else
        {
            jsongenerator.writeStartObject();
            jsongenerator.writeFieldName(s);
            return;
        }
    }

    public void writeTypePrefixForScalar(Object obj, JsonGenerator jsongenerator, Class class1)
        throws IOException, JsonProcessingException
    {
        String s = idFromValueAndType(obj, class1);
        if(jsongenerator.canWriteTypeId())
        {
            jsongenerator.writeTypeId(s);
            return;
        } else
        {
            jsongenerator.writeStartObject();
            jsongenerator.writeFieldName(s);
            return;
        }
    }

    public void writeTypeSuffixForArray(Object obj, JsonGenerator jsongenerator)
        throws IOException, JsonProcessingException
    {
        jsongenerator.writeEndArray();
        if(!jsongenerator.canWriteTypeId())
            jsongenerator.writeEndObject();
    }

    public void writeTypeSuffixForObject(Object obj, JsonGenerator jsongenerator)
        throws IOException, JsonProcessingException
    {
        jsongenerator.writeEndObject();
        if(!jsongenerator.canWriteTypeId())
            jsongenerator.writeEndObject();
    }

    public void writeTypeSuffixForScalar(Object obj, JsonGenerator jsongenerator)
        throws IOException, JsonProcessingException
    {
        if(!jsongenerator.canWriteTypeId())
            jsongenerator.writeEndObject();
    }
}
