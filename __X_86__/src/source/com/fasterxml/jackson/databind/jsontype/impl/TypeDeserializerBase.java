// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.fasterxml.jackson.databind.jsontype.impl;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.annotation.NoClass;
import com.fasterxml.jackson.databind.deser.std.NullifyingDeserializer;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.fasterxml.jackson.databind.jsontype.TypeIdResolver;
import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;

// Referenced classes of package com.fasterxml.jackson.databind.jsontype.impl:
//            TypeIdResolverBase

public abstract class TypeDeserializerBase extends TypeDeserializer
    implements Serializable
{

    protected TypeDeserializerBase(JavaType javatype, TypeIdResolver typeidresolver, String s, boolean flag, Class class1)
    {
        _baseType = javatype;
        _idResolver = typeidresolver;
        _typePropertyName = s;
        _typeIdVisible = flag;
        _deserializers = new HashMap();
        if(class1 == null)
            _defaultImpl = null;
        else
            _defaultImpl = javatype.forcedNarrowBy(class1);
        _property = null;
    }

    protected TypeDeserializerBase(TypeDeserializerBase typedeserializerbase, BeanProperty beanproperty)
    {
        _baseType = typedeserializerbase._baseType;
        _idResolver = typedeserializerbase._idResolver;
        _typePropertyName = typedeserializerbase._typePropertyName;
        _typeIdVisible = typedeserializerbase._typeIdVisible;
        _deserializers = typedeserializerbase._deserializers;
        _defaultImpl = typedeserializerbase._defaultImpl;
        _defaultImplDeserializer = typedeserializerbase._defaultImplDeserializer;
        _property = beanproperty;
    }

    protected Object _deserializeWithNativeTypeId(JsonParser jsonparser, DeserializationContext deserializationcontext)
        throws IOException, JsonProcessingException
    {
        Object obj = jsonparser.getTypeId();
        if(obj != null) goto _L2; else goto _L1
_L1:
        if(_defaultImpl == null) goto _L4; else goto _L3
_L3:
        JsonDeserializer jsondeserializer = _findDefaultImplDeserializer(deserializationcontext);
_L6:
        return jsondeserializer.deserialize(jsonparser, deserializationcontext);
_L4:
        throw deserializationcontext.mappingException("No (native) type id found when one was expected for polymorphic type handling");
_L2:
        String s;
        if(obj instanceof String)
            s = (String)obj;
        else
            s = String.valueOf(obj);
        jsondeserializer = _findDeserializer(deserializationcontext, s);
        if(true) goto _L6; else goto _L5
_L5:
    }

    protected final JsonDeserializer _findDefaultImplDeserializer(DeserializationContext deserializationcontext)
        throws IOException, JsonProcessingException
    {
        if(_defaultImpl == null)
            if(!deserializationcontext.isEnabled(DeserializationFeature.FAIL_ON_INVALID_SUBTYPE))
                return NullifyingDeserializer.instance;
            else
                return null;
        if(_defaultImpl.getRawClass() == com/fasterxml/jackson/databind/annotation/NoClass)
            return NullifyingDeserializer.instance;
        JsonDeserializer jsondeserializer;
        synchronized(_defaultImpl)
        {
            if(_defaultImplDeserializer == null)
                _defaultImplDeserializer = deserializationcontext.findContextualValueDeserializer(_defaultImpl, _property);
            jsondeserializer = _defaultImplDeserializer;
        }
        return jsondeserializer;
        exception;
        javatype;
        JVM INSTR monitorexit ;
        throw exception;
    }

    protected final JsonDeserializer _findDeserializer(DeserializationContext deserializationcontext, String s)
        throws IOException, JsonProcessingException
    {
        HashMap hashmap = _deserializers;
        hashmap;
        JVM INSTR monitorenter ;
        JsonDeserializer jsondeserializer = (JsonDeserializer)_deserializers.get(s);
        if(jsondeserializer != null) goto _L2; else goto _L1
_L1:
        if(!(_idResolver instanceof TypeIdResolverBase)) goto _L4; else goto _L3
_L3:
        JavaType javatype = ((TypeIdResolverBase)_idResolver).typeFromId(deserializationcontext, s);
_L8:
        if(javatype != null) goto _L6; else goto _L5
_L5:
        if(_defaultImpl == null)
            throw deserializationcontext.unknownTypeException(_baseType, s);
          goto _L7
        Exception exception;
        exception;
        hashmap;
        JVM INSTR monitorexit ;
        throw exception;
_L4:
        javatype = _idResolver.typeFromId(s);
          goto _L8
_L7:
        jsondeserializer = _findDefaultImplDeserializer(deserializationcontext);
_L9:
        _deserializers.put(s, jsondeserializer);
_L2:
        hashmap;
        JVM INSTR monitorexit ;
        return jsondeserializer;
_L6:
        JsonDeserializer jsondeserializer1;
        if(_baseType != null && _baseType.getClass() == javatype.getClass())
            javatype = _baseType.narrowBy(javatype.getRawClass());
        jsondeserializer1 = deserializationcontext.findContextualValueDeserializer(javatype, _property);
        jsondeserializer = jsondeserializer1;
          goto _L9
    }

    public String baseTypeName()
    {
        return _baseType.getRawClass().getName();
    }

    public abstract TypeDeserializer forProperty(BeanProperty beanproperty);

    public Class getDefaultImpl()
    {
        if(_defaultImpl == null)
            return null;
        else
            return _defaultImpl.getRawClass();
    }

    public final String getPropertyName()
    {
        return _typePropertyName;
    }

    public TypeIdResolver getTypeIdResolver()
    {
        return _idResolver;
    }

    public abstract com.fasterxml.jackson.annotation.JsonTypeInfo.As getTypeInclusion();

    public String toString()
    {
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append('[').append(getClass().getName());
        stringbuilder.append("; base-type:").append(_baseType);
        stringbuilder.append("; id-resolver: ").append(_idResolver);
        stringbuilder.append(']');
        return stringbuilder.toString();
    }

    private static final long serialVersionUID = 0x3dd3c47a2bb4a93L;
    protected final JavaType _baseType;
    protected final JavaType _defaultImpl;
    protected JsonDeserializer _defaultImplDeserializer;
    protected final HashMap _deserializers;
    protected final TypeIdResolver _idResolver;
    protected final BeanProperty _property;
    protected final boolean _typeIdVisible;
    protected final String _typePropertyName;
}
