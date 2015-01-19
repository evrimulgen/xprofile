// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.fasterxml.jackson.databind.deser.std;

import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.deser.ContextualDeserializer;
import com.fasterxml.jackson.databind.deser.ValueInstantiator;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import java.io.IOException;
import java.util.Collection;

// Referenced classes of package com.fasterxml.jackson.databind.deser.std:
//            ContainerDeserializerBase

public class CollectionDeserializer extends ContainerDeserializerBase
    implements ContextualDeserializer
{

    public CollectionDeserializer(JavaType javatype, JsonDeserializer jsondeserializer, TypeDeserializer typedeserializer, ValueInstantiator valueinstantiator)
    {
        this(javatype, jsondeserializer, typedeserializer, valueinstantiator, null);
    }

    protected CollectionDeserializer(JavaType javatype, JsonDeserializer jsondeserializer, TypeDeserializer typedeserializer, ValueInstantiator valueinstantiator, JsonDeserializer jsondeserializer1)
    {
        super(javatype);
        _collectionType = javatype;
        _valueDeserializer = jsondeserializer;
        _valueTypeDeserializer = typedeserializer;
        _valueInstantiator = valueinstantiator;
        _delegateDeserializer = jsondeserializer1;
    }

    protected CollectionDeserializer(CollectionDeserializer collectiondeserializer)
    {
        super(collectiondeserializer._collectionType);
        _collectionType = collectiondeserializer._collectionType;
        _valueDeserializer = collectiondeserializer._valueDeserializer;
        _valueTypeDeserializer = collectiondeserializer._valueTypeDeserializer;
        _valueInstantiator = collectiondeserializer._valueInstantiator;
        _delegateDeserializer = collectiondeserializer._delegateDeserializer;
    }

    public volatile JsonDeserializer createContextual(DeserializationContext deserializationcontext, BeanProperty beanproperty)
        throws JsonMappingException
    {
        return createContextual(deserializationcontext, beanproperty);
    }

    public CollectionDeserializer createContextual(DeserializationContext deserializationcontext, BeanProperty beanproperty)
        throws JsonMappingException
    {
        ValueInstantiator valueinstantiator = _valueInstantiator;
        JsonDeserializer jsondeserializer = null;
        if(valueinstantiator != null)
        {
            boolean flag = _valueInstantiator.canCreateUsingDelegate();
            jsondeserializer = null;
            if(flag)
            {
                JavaType javatype = _valueInstantiator.getDelegateType(deserializationcontext.getConfig());
                if(javatype == null)
                    throw new IllegalArgumentException((new StringBuilder()).append("Invalid delegate-creator definition for ").append(_collectionType).append(": value instantiator (").append(_valueInstantiator.getClass().getName()).append(") returned true for 'canCreateUsingDelegate()', but null for 'getDelegateType()'").toString());
                jsondeserializer = findDeserializer(deserializationcontext, javatype, beanproperty);
            }
        }
        JsonDeserializer jsondeserializer1 = findConvertingContentDeserializer(deserializationcontext, beanproperty, _valueDeserializer);
        JsonDeserializer jsondeserializer2;
        TypeDeserializer typedeserializer;
        if(jsondeserializer1 == null)
            jsondeserializer2 = deserializationcontext.findContextualValueDeserializer(_collectionType.getContentType(), beanproperty);
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

    public volatile Object deserialize(JsonParser jsonparser, DeserializationContext deserializationcontext, Object obj)
        throws IOException, JsonProcessingException
    {
        return deserialize(jsonparser, deserializationcontext, (Collection)obj);
    }

    public Collection deserialize(JsonParser jsonparser, DeserializationContext deserializationcontext)
        throws IOException, JsonProcessingException
    {
        if(_delegateDeserializer != null)
            return (Collection)_valueInstantiator.createUsingDelegate(deserializationcontext, _delegateDeserializer.deserialize(jsonparser, deserializationcontext));
        if(jsonparser.getCurrentToken() == JsonToken.VALUE_STRING)
        {
            String s = jsonparser.getText();
            if(s.length() == 0)
                return (Collection)_valueInstantiator.createFromString(deserializationcontext, s);
        }
        return deserialize(jsonparser, deserializationcontext, (Collection)_valueInstantiator.createUsingDefault(deserializationcontext));
    }

    public Collection deserialize(JsonParser jsonparser, DeserializationContext deserializationcontext, Collection collection)
        throws IOException, JsonProcessingException
    {
        if(jsonparser.isExpectedStartArrayToken()) goto _L2; else goto _L1
_L1:
        collection = handleNonArray(jsonparser, deserializationcontext, collection);
_L4:
        return collection;
_L2:
        JsonDeserializer jsondeserializer = _valueDeserializer;
        TypeDeserializer typedeserializer = _valueTypeDeserializer;
        do
        {
            JsonToken jsontoken = jsonparser.nextToken();
            if(jsontoken == JsonToken.END_ARRAY)
                continue;
            Object obj;
            if(jsontoken == JsonToken.VALUE_NULL)
                obj = null;
            else
            if(typedeserializer == null)
                obj = jsondeserializer.deserialize(jsonparser, deserializationcontext);
            else
                obj = jsondeserializer.deserializeWithType(jsonparser, deserializationcontext, typedeserializer);
            collection.add(obj);
        } while(true);
        if(true) goto _L4; else goto _L3
_L3:
    }

    public Object deserializeWithType(JsonParser jsonparser, DeserializationContext deserializationcontext, TypeDeserializer typedeserializer)
        throws IOException, JsonProcessingException
    {
        return typedeserializer.deserializeTypedFromArray(jsonparser, deserializationcontext);
    }

    public JsonDeserializer getContentDeserializer()
    {
        return _valueDeserializer;
    }

    public JavaType getContentType()
    {
        return _collectionType.getContentType();
    }

    protected final Collection handleNonArray(JsonParser jsonparser, DeserializationContext deserializationcontext, Collection collection)
        throws IOException, JsonProcessingException
    {
        if(!deserializationcontext.isEnabled(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY))
            throw deserializationcontext.mappingException(_collectionType.getRawClass());
        JsonDeserializer jsondeserializer = _valueDeserializer;
        TypeDeserializer typedeserializer = _valueTypeDeserializer;
        Object obj;
        if(jsonparser.getCurrentToken() == JsonToken.VALUE_NULL)
            obj = null;
        else
        if(typedeserializer == null)
            obj = jsondeserializer.deserialize(jsonparser, deserializationcontext);
        else
            obj = jsondeserializer.deserializeWithType(jsonparser, deserializationcontext, typedeserializer);
        collection.add(obj);
        return collection;
    }

    protected CollectionDeserializer withResolved(JsonDeserializer jsondeserializer, JsonDeserializer jsondeserializer1, TypeDeserializer typedeserializer)
    {
        if(jsondeserializer == _delegateDeserializer && jsondeserializer1 == _valueDeserializer && typedeserializer == _valueTypeDeserializer)
            return this;
        else
            return new CollectionDeserializer(_collectionType, jsondeserializer1, typedeserializer, _valueInstantiator, jsondeserializer);
    }

    private static final long serialVersionUID = 0xe430f8b0319acf62L;
    protected final JavaType _collectionType;
    protected final JsonDeserializer _delegateDeserializer;
    protected final JsonDeserializer _valueDeserializer;
    protected final ValueInstantiator _valueInstantiator;
    protected final TypeDeserializer _valueTypeDeserializer;
}
