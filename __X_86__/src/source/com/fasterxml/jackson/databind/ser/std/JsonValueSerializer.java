// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.fasterxml.jackson.databind.ser.std;

import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatVisitable;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatVisitorWrapper;
import com.fasterxml.jackson.databind.jsonschema.JsonSchema;
import com.fasterxml.jackson.databind.jsonschema.SchemaAware;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;
import java.io.IOException;
import java.lang.reflect.*;

// Referenced classes of package com.fasterxml.jackson.databind.ser.std:
//            StdSerializer

public class JsonValueSerializer extends StdSerializer
    implements ContextualSerializer, JsonFormatVisitable, SchemaAware
{

    public JsonValueSerializer(JsonValueSerializer jsonvalueserializer, BeanProperty beanproperty, JsonSerializer jsonserializer, boolean flag)
    {
        super(_notNullClass(jsonvalueserializer.handledType()));
        _accessorMethod = jsonvalueserializer._accessorMethod;
        _valueSerializer = jsonserializer;
        _property = beanproperty;
        _forceTypeInformation = flag;
    }

    public JsonValueSerializer(Method method, JsonSerializer jsonserializer)
    {
        super(java/lang/Object);
        _accessorMethod = method;
        _valueSerializer = jsonserializer;
        _property = null;
        _forceTypeInformation = true;
    }

    private static final Class _notNullClass(Class class1)
    {
        if(class1 == null)
            class1 = java/lang/Object;
        return class1;
    }

    public void acceptJsonFormatVisitor(JsonFormatVisitorWrapper jsonformatvisitorwrapper, JavaType javatype)
        throws JsonMappingException
    {
        JsonSerializer jsonserializer = _valueSerializer;
        if(jsonserializer == null)
        {
            if(javatype == null)
            {
                if(_property != null)
                    javatype = _property.getType();
                if(javatype == null)
                    javatype = jsonformatvisitorwrapper.getProvider().constructType(_accessorMethod.getReturnType());
            }
            jsonserializer = jsonformatvisitorwrapper.getProvider().findTypedValueSerializer(javatype, false, _property);
            if(jsonserializer == null)
            {
                jsonformatvisitorwrapper.expectAnyFormat(javatype);
                return;
            }
        }
        jsonserializer.acceptJsonFormatVisitor(jsonformatvisitorwrapper, null);
    }

    public JsonSerializer createContextual(SerializerProvider serializerprovider, BeanProperty beanproperty)
        throws JsonMappingException
    {
        JsonSerializer jsonserializer = _valueSerializer;
        if(jsonserializer == null)
        {
            if(serializerprovider.isEnabled(MapperFeature.USE_STATIC_TYPING) || Modifier.isFinal(_accessorMethod.getReturnType().getModifiers()))
            {
                JavaType javatype = serializerprovider.constructType(_accessorMethod.getGenericReturnType());
                JsonSerializer jsonserializer1 = serializerprovider.findPrimaryPropertySerializer(javatype, _property);
                this = withResolved(beanproperty, jsonserializer1, isNaturalTypeWithStdHandling(javatype.getRawClass(), jsonserializer1));
            }
            return this;
        } else
        {
            return withResolved(beanproperty, serializerprovider.handlePrimaryContextualization(jsonserializer, beanproperty), _forceTypeInformation);
        }
    }

    public JsonNode getSchema(SerializerProvider serializerprovider, Type type)
        throws JsonMappingException
    {
        if(_valueSerializer instanceof SchemaAware)
            return ((SchemaAware)_valueSerializer).getSchema(serializerprovider, null);
        else
            return JsonSchema.getDefaultSchemaNode();
    }

    protected boolean isNaturalTypeWithStdHandling(Class class1, JsonSerializer jsonserializer)
    {
        if(class1.isPrimitive() ? class1 != Integer.TYPE && class1 != Boolean.TYPE && class1 != Double.TYPE : class1 != java/lang/String && class1 != java/lang/Integer && class1 != java/lang/Boolean && class1 != java/lang/Double)
            return false;
        else
            return isDefaultSerializer(jsonserializer);
    }

    public void serialize(Object obj, JsonGenerator jsongenerator, SerializerProvider serializerprovider)
        throws IOException, JsonGenerationException
    {
        Object obj2 = _accessorMethod.invoke(obj, new Object[0]);
        if(obj2 == null)
        {
            Object obj1;
            JsonSerializer jsonserializer;
            try
            {
                serializerprovider.defaultSerializeNull(jsongenerator);
                return;
            }
            catch(IOException ioexception)
            {
                throw ioexception;
            }
            // Misplaced declaration of an exception variable
            catch(Object obj1) { }
            break MISSING_BLOCK_LABEL_67;
        }
        jsonserializer = _valueSerializer;
        if(jsonserializer != null)
            break MISSING_BLOCK_LABEL_52;
        jsonserializer = serializerprovider.findTypedValueSerializer(obj2.getClass(), true, _property);
        jsonserializer.serialize(obj2, jsongenerator, serializerprovider);
        return;
        for(; (obj1 instanceof InvocationTargetException) && ((Throwable) (obj1)).getCause() != null; obj1 = ((Throwable) (obj1)).getCause());
        if(obj1 instanceof Error)
            throw (Error)obj1;
        else
            throw JsonMappingException.wrapWithPath(((Throwable) (obj1)), obj, (new StringBuilder()).append(_accessorMethod.getName()).append("()").toString());
    }

    public void serializeWithType(Object obj, JsonGenerator jsongenerator, SerializerProvider serializerprovider, TypeSerializer typeserializer)
        throws IOException, JsonProcessingException
    {
        Object obj2;
        JsonSerializer jsonserializer;
        try
        {
            obj2 = _accessorMethod.invoke(obj, new Object[0]);
        }
        catch(IOException ioexception)
        {
            throw ioexception;
        }
        catch(Object obj1)
        {
            for(; (obj1 instanceof InvocationTargetException) && ((Throwable) (obj1)).getCause() != null; obj1 = ((Throwable) (obj1)).getCause());
            if(obj1 instanceof Error)
                throw (Error)obj1;
            else
                throw JsonMappingException.wrapWithPath(((Throwable) (obj1)), obj, (new StringBuilder()).append(_accessorMethod.getName()).append("()").toString());
        }
        if(obj2 != null)
            break MISSING_BLOCK_LABEL_25;
        serializerprovider.defaultSerializeNull(jsongenerator);
        return;
        jsonserializer = _valueSerializer;
        if(jsonserializer != null) goto _L2; else goto _L1
_L1:
        jsonserializer = serializerprovider.findValueSerializer(obj2.getClass(), _property);
_L4:
        jsonserializer.serializeWithType(obj2, jsongenerator, serializerprovider, typeserializer);
        return;
_L2:
        if(!_forceTypeInformation) goto _L4; else goto _L3
_L3:
        typeserializer.writeTypePrefixForScalar(obj, jsongenerator);
        jsonserializer.serialize(obj2, jsongenerator, serializerprovider);
        typeserializer.writeTypeSuffixForScalar(obj, jsongenerator);
        return;
    }

    public String toString()
    {
        return (new StringBuilder()).append("(@JsonValue serializer for method ").append(_accessorMethod.getDeclaringClass()).append("#").append(_accessorMethod.getName()).append(")").toString();
    }

    public JsonValueSerializer withResolved(BeanProperty beanproperty, JsonSerializer jsonserializer, boolean flag)
    {
        if(_property == beanproperty && _valueSerializer == jsonserializer && flag == _forceTypeInformation)
            return this;
        else
            return new JsonValueSerializer(this, beanproperty, jsonserializer, flag);
    }

    protected final Method _accessorMethod;
    protected final boolean _forceTypeInformation;
    protected final BeanProperty _property;
    protected final JsonSerializer _valueSerializer;
}
