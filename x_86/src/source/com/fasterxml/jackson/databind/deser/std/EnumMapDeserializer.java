// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.fasterxml.jackson.databind.deser.std;

import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.deser.ContextualDeserializer;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import java.io.IOException;
import java.util.EnumMap;

// Referenced classes of package com.fasterxml.jackson.databind.deser.std:
//            StdDeserializer

public class EnumMapDeserializer extends StdDeserializer
    implements ContextualDeserializer
{

    public EnumMapDeserializer(JavaType javatype, JsonDeserializer jsondeserializer, JsonDeserializer jsondeserializer1)
    {
        this(javatype, jsondeserializer, jsondeserializer1, null);
    }

    public EnumMapDeserializer(JavaType javatype, JsonDeserializer jsondeserializer, JsonDeserializer jsondeserializer1, TypeDeserializer typedeserializer)
    {
        super(java/util/EnumMap);
        _mapType = javatype;
        _enumClass = javatype.getKeyType().getRawClass();
        _keyDeserializer = jsondeserializer;
        _valueDeserializer = jsondeserializer1;
        _valueTypeDeserializer = typedeserializer;
    }

    private EnumMap constructMap()
    {
        return new EnumMap(_enumClass);
    }

    public JsonDeserializer createContextual(DeserializationContext deserializationcontext, BeanProperty beanproperty)
        throws JsonMappingException
    {
        JsonDeserializer jsondeserializer = _keyDeserializer;
        if(jsondeserializer == null)
            jsondeserializer = deserializationcontext.findContextualValueDeserializer(_mapType.getKeyType(), beanproperty);
        JsonDeserializer jsondeserializer1 = _valueDeserializer;
        JsonDeserializer jsondeserializer2;
        TypeDeserializer typedeserializer;
        if(jsondeserializer1 == null)
            jsondeserializer2 = deserializationcontext.findContextualValueDeserializer(_mapType.getContentType(), beanproperty);
        else
            jsondeserializer2 = deserializationcontext.handleSecondaryContextualization(jsondeserializer1, beanproperty);
        typedeserializer = _valueTypeDeserializer;
        if(typedeserializer != null)
            typedeserializer = typedeserializer.forProperty(beanproperty);
        return withResolved(jsondeserializer, jsondeserializer2, typedeserializer);
    }

    public volatile Object deserialize(JsonParser jsonparser, DeserializationContext deserializationcontext)
        throws IOException, JsonProcessingException
    {
        return deserialize(jsonparser, deserializationcontext);
    }

    public EnumMap deserialize(JsonParser jsonparser, DeserializationContext deserializationcontext)
        throws IOException, JsonProcessingException
    {
        EnumMap enummap;
        JsonDeserializer jsondeserializer;
        TypeDeserializer typedeserializer;
        if(jsonparser.getCurrentToken() != JsonToken.START_OBJECT)
            throw deserializationcontext.mappingException(java/util/EnumMap);
        enummap = constructMap();
        jsondeserializer = _valueDeserializer;
        typedeserializer = _valueTypeDeserializer;
_L8:
        if(jsonparser.nextToken() == JsonToken.END_OBJECT) goto _L2; else goto _L1
_L1:
        Enum enum = (Enum)_keyDeserializer.deserialize(jsonparser, deserializationcontext);
        if(enum != null) goto _L4; else goto _L3
_L3:
        if(deserializationcontext.isEnabled(DeserializationFeature.READ_UNKNOWN_ENUM_VALUES_AS_NULL)) goto _L6; else goto _L5
_L5:
        String s1;
        if(!jsonparser.hasCurrentToken())
            break MISSING_BLOCK_LABEL_182;
        s1 = jsonparser.getText();
        String s = s1;
_L7:
        throw deserializationcontext.weirdStringException(s, _enumClass, "value not one of declared Enum instance names");
        Exception exception;
        exception;
        s = null;
          goto _L7
_L6:
        jsonparser.nextToken();
        jsonparser.skipChildren();
          goto _L8
_L4:
        Object obj;
        if(jsonparser.nextToken() == JsonToken.VALUE_NULL)
            obj = null;
        else
        if(typedeserializer == null)
            obj = jsondeserializer.deserialize(jsonparser, deserializationcontext);
        else
            obj = jsondeserializer.deserializeWithType(jsonparser, deserializationcontext, typedeserializer);
        enummap.put(enum, obj);
          goto _L8
_L2:
        return enummap;
        s = null;
          goto _L7
    }

    public Object deserializeWithType(JsonParser jsonparser, DeserializationContext deserializationcontext, TypeDeserializer typedeserializer)
        throws IOException, JsonProcessingException
    {
        return typedeserializer.deserializeTypedFromObject(jsonparser, deserializationcontext);
    }

    public boolean isCachable()
    {
        return true;
    }

    public EnumMapDeserializer withResolved(JsonDeserializer jsondeserializer, JsonDeserializer jsondeserializer1)
    {
        return withResolved(jsondeserializer, jsondeserializer1, null);
    }

    public EnumMapDeserializer withResolved(JsonDeserializer jsondeserializer, JsonDeserializer jsondeserializer1, TypeDeserializer typedeserializer)
    {
        if(jsondeserializer == _keyDeserializer && jsondeserializer1 == _valueDeserializer && typedeserializer == _valueTypeDeserializer)
            return this;
        else
            return new EnumMapDeserializer(_mapType, jsondeserializer, jsondeserializer1, _valueTypeDeserializer);
    }

    private static final long serialVersionUID = 0x1513c456622f2ab4L;
    protected final Class _enumClass;
    protected JsonDeserializer _keyDeserializer;
    protected final JavaType _mapType;
    protected JsonDeserializer _valueDeserializer;
    protected final TypeDeserializer _valueTypeDeserializer;
}
