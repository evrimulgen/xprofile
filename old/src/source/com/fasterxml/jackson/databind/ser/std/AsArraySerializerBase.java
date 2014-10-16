// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.fasterxml.jackson.databind.ser.std;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonArrayFormatVisitor;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatVisitorWrapper;
import com.fasterxml.jackson.databind.jsonschema.JsonSchema;
import com.fasterxml.jackson.databind.jsonschema.SchemaAware;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.ser.ContainerSerializer;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;
import com.fasterxml.jackson.databind.ser.impl.PropertySerializerMap;
import com.fasterxml.jackson.databind.type.TypeFactory;
import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public abstract class AsArraySerializerBase extends ContainerSerializer
    implements ContextualSerializer
{

    protected AsArraySerializerBase(AsArraySerializerBase asarrayserializerbase, BeanProperty beanproperty, TypeSerializer typeserializer, JsonSerializer jsonserializer)
    {
        super(asarrayserializerbase);
        _elementType = asarrayserializerbase._elementType;
        _staticTyping = asarrayserializerbase._staticTyping;
        _valueTypeSerializer = typeserializer;
        _property = beanproperty;
        _elementSerializer = jsonserializer;
        _dynamicSerializers = asarrayserializerbase._dynamicSerializers;
    }

    protected AsArraySerializerBase(Class class1, JavaType javatype, boolean flag, TypeSerializer typeserializer, BeanProperty beanproperty, JsonSerializer jsonserializer)
    {
        boolean flag1;
label0:
        {
            super(class1, false);
            _elementType = javatype;
            if(!flag)
            {
                flag1 = false;
                if(javatype == null)
                    break label0;
                boolean flag2 = javatype.isFinal();
                flag1 = false;
                if(!flag2)
                    break label0;
            }
            flag1 = true;
        }
        _staticTyping = flag1;
        _valueTypeSerializer = typeserializer;
        _property = beanproperty;
        _elementSerializer = jsonserializer;
        _dynamicSerializers = PropertySerializerMap.emptyMap();
    }

    protected final JsonSerializer _findAndAddDynamic(PropertySerializerMap propertyserializermap, JavaType javatype, SerializerProvider serializerprovider)
        throws JsonMappingException
    {
        com.fasterxml.jackson.databind.ser.impl.PropertySerializerMap.SerializerAndMapResult serializerandmapresult = propertyserializermap.findAndAddSecondarySerializer(javatype, serializerprovider, _property);
        if(propertyserializermap != serializerandmapresult.map)
            _dynamicSerializers = serializerandmapresult.map;
        return serializerandmapresult.serializer;
    }

    protected final JsonSerializer _findAndAddDynamic(PropertySerializerMap propertyserializermap, Class class1, SerializerProvider serializerprovider)
        throws JsonMappingException
    {
        com.fasterxml.jackson.databind.ser.impl.PropertySerializerMap.SerializerAndMapResult serializerandmapresult = propertyserializermap.findAndAddSecondarySerializer(class1, serializerprovider, _property);
        if(propertyserializermap != serializerandmapresult.map)
            _dynamicSerializers = serializerandmapresult.map;
        return serializerandmapresult.serializer;
    }

    public void acceptJsonFormatVisitor(JsonFormatVisitorWrapper jsonformatvisitorwrapper, JavaType javatype)
        throws JsonMappingException
    {
        JsonArrayFormatVisitor jsonarrayformatvisitor;
        if(jsonformatvisitorwrapper == null)
            jsonarrayformatvisitor = null;
        else
            jsonarrayformatvisitor = jsonformatvisitorwrapper.expectArrayFormat(javatype);
        if(jsonarrayformatvisitor != null)
        {
            JavaType javatype1 = jsonformatvisitorwrapper.getProvider().getTypeFactory().moreSpecificType(_elementType, javatype.getContentType());
            if(javatype1 == null)
                throw new JsonMappingException("Could not resolve type");
            JsonSerializer jsonserializer = _elementSerializer;
            if(jsonserializer == null)
                jsonserializer = jsonformatvisitorwrapper.getProvider().findValueSerializer(javatype1, _property);
            jsonarrayformatvisitor.itemsFormat(jsonserializer, javatype1);
        }
    }

    public JsonSerializer createContextual(SerializerProvider serializerprovider, BeanProperty beanproperty)
        throws JsonMappingException
    {
        TypeSerializer typeserializer = _valueTypeSerializer;
        TypeSerializer typeserializer1;
        JsonSerializer jsonserializer;
        JsonSerializer jsonserializer1;
        if(typeserializer != null)
            typeserializer1 = typeserializer.forProperty(beanproperty);
        else
            typeserializer1 = typeserializer;
        jsonserializer = null;
        if(beanproperty != null)
        {
            com.fasterxml.jackson.databind.introspect.AnnotatedMember annotatedmember = beanproperty.getMember();
            jsonserializer = null;
            if(annotatedmember != null)
            {
                Object obj = serializerprovider.getAnnotationIntrospector().findContentSerializer(annotatedmember);
                jsonserializer = null;
                if(obj != null)
                    jsonserializer = serializerprovider.serializerInstance(annotatedmember, obj);
            }
        }
        if(jsonserializer == null)
            jsonserializer = _elementSerializer;
        jsonserializer1 = findConvertingContentSerializer(serializerprovider, beanproperty, jsonserializer);
        if(jsonserializer1 == null)
        {
            if(_elementType != null && (_staticTyping && _elementType.getRawClass() != java/lang/Object || hasContentTypeAnnotation(serializerprovider, beanproperty)))
                jsonserializer1 = serializerprovider.findValueSerializer(_elementType, beanproperty);
        } else
        {
            jsonserializer1 = serializerprovider.handleSecondaryContextualization(jsonserializer1, beanproperty);
        }
        if(jsonserializer1 != _elementSerializer || beanproperty != _property || _valueTypeSerializer != typeserializer1)
            this = withResolved(beanproperty, typeserializer1, jsonserializer1);
        return this;
    }

    public JsonSerializer getContentSerializer()
    {
        return _elementSerializer;
    }

    public JavaType getContentType()
    {
        return _elementType;
    }

    public JsonNode getSchema(SerializerProvider serializerprovider, Type type)
        throws JsonMappingException
    {
        ObjectNode objectnode = createSchemaNode("array", true);
        JavaType javatype;
        JsonNode jsonnode;
        JsonSerializer jsonserializer;
        if(type != null)
        {
            javatype = serializerprovider.constructType(type).getContentType();
            if(javatype == null && (type instanceof ParameterizedType))
            {
                Type atype[] = ((ParameterizedType)type).getActualTypeArguments();
                if(atype.length == 1)
                    javatype = serializerprovider.constructType(atype[0]);
            }
        } else
        {
            javatype = null;
        }
        if(javatype == null && _elementType != null)
            javatype = _elementType;
        if(javatype == null) goto _L2; else goto _L1
_L1:
        if(javatype.getRawClass() == java/lang/Object) goto _L4; else goto _L3
_L3:
        jsonserializer = serializerprovider.findValueSerializer(javatype, _property);
        if(!(jsonserializer instanceof SchemaAware)) goto _L4; else goto _L5
_L5:
        jsonnode = ((SchemaAware)jsonserializer).getSchema(serializerprovider, null);
_L7:
        if(jsonnode == null)
            jsonnode = JsonSchema.getDefaultSchemaNode();
        objectnode.put("items", jsonnode);
_L2:
        return objectnode;
_L4:
        jsonnode = null;
        if(true) goto _L7; else goto _L6
_L6:
    }

    public final void serialize(Object obj, JsonGenerator jsongenerator, SerializerProvider serializerprovider)
        throws IOException, JsonGenerationException
    {
        if(serializerprovider.isEnabled(SerializationFeature.WRITE_SINGLE_ELEM_ARRAYS_UNWRAPPED) && hasSingleElement(obj))
        {
            serializeContents(obj, jsongenerator, serializerprovider);
            return;
        } else
        {
            jsongenerator.writeStartArray();
            serializeContents(obj, jsongenerator, serializerprovider);
            jsongenerator.writeEndArray();
            return;
        }
    }

    protected abstract void serializeContents(Object obj, JsonGenerator jsongenerator, SerializerProvider serializerprovider)
        throws IOException, JsonGenerationException;

    public void serializeWithType(Object obj, JsonGenerator jsongenerator, SerializerProvider serializerprovider, TypeSerializer typeserializer)
        throws IOException, JsonGenerationException
    {
        typeserializer.writeTypePrefixForArray(obj, jsongenerator);
        serializeContents(obj, jsongenerator, serializerprovider);
        typeserializer.writeTypeSuffixForArray(obj, jsongenerator);
    }

    public abstract AsArraySerializerBase withResolved(BeanProperty beanproperty, TypeSerializer typeserializer, JsonSerializer jsonserializer);

    protected PropertySerializerMap _dynamicSerializers;
    protected final JsonSerializer _elementSerializer;
    protected final JavaType _elementType;
    protected final BeanProperty _property;
    protected final boolean _staticTyping;
    protected final TypeSerializer _valueTypeSerializer;
}
