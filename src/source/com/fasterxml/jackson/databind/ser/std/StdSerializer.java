// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.fasterxml.jackson.databind.ser.std;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatVisitable;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatVisitorWrapper;
import com.fasterxml.jackson.databind.jsonschema.SchemaAware;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.PropertyFilter;
import com.fasterxml.jackson.databind.util.ClassUtil;
import com.fasterxml.jackson.databind.util.Converter;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Type;

// Referenced classes of package com.fasterxml.jackson.databind.ser.std:
//            StdDelegatingSerializer

public abstract class StdSerializer extends JsonSerializer
    implements JsonFormatVisitable, SchemaAware
{

    protected StdSerializer(JavaType javatype)
    {
        _handledType = javatype.getRawClass();
    }

    protected StdSerializer(Class class1)
    {
        _handledType = class1;
    }

    protected StdSerializer(Class class1, boolean flag)
    {
        _handledType = class1;
    }

    public void acceptJsonFormatVisitor(JsonFormatVisitorWrapper jsonformatvisitorwrapper, JavaType javatype)
        throws JsonMappingException
    {
        jsonformatvisitorwrapper.expectAnyFormat(javatype);
    }

    protected ObjectNode createObjectNode()
    {
        return JsonNodeFactory.instance.objectNode();
    }

    protected ObjectNode createSchemaNode(String s)
    {
        ObjectNode objectnode = createObjectNode();
        objectnode.put("type", s);
        return objectnode;
    }

    protected ObjectNode createSchemaNode(String s, boolean flag)
    {
        ObjectNode objectnode = createSchemaNode(s);
        if(!flag)
        {
            boolean flag1;
            if(!flag)
                flag1 = true;
            else
                flag1 = false;
            objectnode.put("required", flag1);
        }
        return objectnode;
    }

    protected JsonSerializer findConvertingContentSerializer(SerializerProvider serializerprovider, BeanProperty beanproperty, JsonSerializer jsonserializer)
        throws JsonMappingException
    {
        AnnotationIntrospector annotationintrospector = serializerprovider.getAnnotationIntrospector();
        if(annotationintrospector != null && beanproperty != null)
        {
            Object obj = annotationintrospector.findSerializationContentConverter(beanproperty.getMember());
            if(obj != null)
            {
                Converter converter = serializerprovider.converterInstance(beanproperty.getMember(), obj);
                JavaType javatype = converter.getOutputType(serializerprovider.getTypeFactory());
                if(jsonserializer == null)
                    jsonserializer = serializerprovider.findValueSerializer(javatype, beanproperty);
                jsonserializer = new StdDelegatingSerializer(converter, javatype, jsonserializer);
            }
        }
        return jsonserializer;
    }

    protected PropertyFilter findPropertyFilter(SerializerProvider serializerprovider, Object obj, Object obj1)
        throws JsonMappingException
    {
        FilterProvider filterprovider = serializerprovider.getFilterProvider();
        if(filterprovider == null)
            throw new JsonMappingException((new StringBuilder()).append("Can not resolve PropertyFilter with id '").append(obj).append("'; no FilterProvider configured").toString());
        else
            return filterprovider.findPropertyFilter(obj, obj1);
    }

    public JsonNode getSchema(SerializerProvider serializerprovider, Type type)
        throws JsonMappingException
    {
        return createSchemaNode("string");
    }

    public JsonNode getSchema(SerializerProvider serializerprovider, Type type, boolean flag)
        throws JsonMappingException
    {
        ObjectNode objectnode = (ObjectNode)getSchema(serializerprovider, type);
        if(!flag)
        {
            boolean flag1;
            if(!flag)
                flag1 = true;
            else
                flag1 = false;
            objectnode.put("required", flag1);
        }
        return objectnode;
    }

    public Class handledType()
    {
        return _handledType;
    }

    protected boolean isDefaultSerializer(JsonSerializer jsonserializer)
    {
        return ClassUtil.isJacksonStdImpl(jsonserializer);
    }

    public abstract void serialize(Object obj, JsonGenerator jsongenerator, SerializerProvider serializerprovider)
        throws IOException, JsonGenerationException;

    public void wrapAndThrow(SerializerProvider serializerprovider, Throwable throwable, Object obj, int i)
        throws IOException
    {
        Throwable throwable1;
        for(throwable1 = throwable; (throwable1 instanceof InvocationTargetException) && throwable1.getCause() != null; throwable1 = throwable1.getCause());
        if(throwable1 instanceof Error)
            throw (Error)throwable1;
        boolean flag;
        if(serializerprovider == null || serializerprovider.isEnabled(SerializationFeature.WRAP_EXCEPTIONS))
            flag = true;
        else
            flag = false;
        if(throwable1 instanceof IOException)
        {
            if(!flag || !(throwable1 instanceof JsonMappingException))
                throw (IOException)throwable1;
        } else
        if(!flag && (throwable1 instanceof RuntimeException))
            throw (RuntimeException)throwable1;
        throw JsonMappingException.wrapWithPath(throwable1, obj, i);
    }

    public void wrapAndThrow(SerializerProvider serializerprovider, Throwable throwable, Object obj, String s)
        throws IOException
    {
        Throwable throwable1;
        for(throwable1 = throwable; (throwable1 instanceof InvocationTargetException) && throwable1.getCause() != null; throwable1 = throwable1.getCause());
        if(throwable1 instanceof Error)
            throw (Error)throwable1;
        boolean flag;
        if(serializerprovider == null || serializerprovider.isEnabled(SerializationFeature.WRAP_EXCEPTIONS))
            flag = true;
        else
            flag = false;
        if(throwable1 instanceof IOException)
        {
            if(!flag || !(throwable1 instanceof JsonMappingException))
                throw (IOException)throwable1;
        } else
        if(!flag && (throwable1 instanceof RuntimeException))
            throw (RuntimeException)throwable1;
        throw JsonMappingException.wrapWithPath(throwable1, obj, s);
    }

    protected final Class _handledType;
}
