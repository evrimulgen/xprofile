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

public final class StringCollectionDeserializer extends ContainerDeserializerBase
    implements ContextualDeserializer
{

    public StringCollectionDeserializer(JavaType javatype, JsonDeserializer jsondeserializer, ValueInstantiator valueinstantiator)
    {
        this(javatype, valueinstantiator, null, jsondeserializer);
    }

    protected StringCollectionDeserializer(JavaType javatype, ValueInstantiator valueinstantiator, JsonDeserializer jsondeserializer, JsonDeserializer jsondeserializer1)
    {
        super(javatype);
        _collectionType = javatype;
        _valueDeserializer = jsondeserializer1;
        _valueInstantiator = valueinstantiator;
        _delegateDeserializer = jsondeserializer;
    }

    private Collection deserializeUsingCustom(JsonParser jsonparser, DeserializationContext deserializationcontext, Collection collection, JsonDeserializer jsondeserializer)
        throws IOException, JsonProcessingException
    {
        do
        {
            JsonToken jsontoken = jsonparser.nextToken();
            if(jsontoken != JsonToken.END_ARRAY)
            {
                String s;
                if(jsontoken == JsonToken.VALUE_NULL)
                    s = null;
                else
                    s = (String)jsondeserializer.deserialize(jsonparser, deserializationcontext);
                collection.add(s);
            } else
            {
                return collection;
            }
        } while(true);
    }

    private final Collection handleNonArray(JsonParser jsonparser, DeserializationContext deserializationcontext, Collection collection)
        throws IOException, JsonProcessingException
    {
        if(!deserializationcontext.isEnabled(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY))
            throw deserializationcontext.mappingException(_collectionType.getRawClass());
        JsonDeserializer jsondeserializer = _valueDeserializer;
        String s;
        if(jsonparser.getCurrentToken() == JsonToken.VALUE_NULL)
            s = null;
        else
        if(jsondeserializer == null)
            s = _parseString(jsonparser, deserializationcontext);
        else
            s = (String)jsondeserializer.deserialize(jsonparser, deserializationcontext);
        collection.add(s);
        return collection;
    }

    public JsonDeserializer createContextual(DeserializationContext deserializationcontext, BeanProperty beanproperty)
        throws JsonMappingException
    {
        JsonDeserializer jsondeserializer;
        JsonDeserializer jsondeserializer1;
        JsonDeserializer jsondeserializer2;
        boolean flag;
        JsonDeserializer jsondeserializer3;
        if(_valueInstantiator != null && _valueInstantiator.getDelegateCreator() != null)
            jsondeserializer = findDeserializer(deserializationcontext, _valueInstantiator.getDelegateType(deserializationcontext.getConfig()), beanproperty);
        else
            jsondeserializer = null;
        jsondeserializer1 = _valueDeserializer;
        if(jsondeserializer1 == null)
        {
            jsondeserializer2 = findConvertingContentDeserializer(deserializationcontext, beanproperty, jsondeserializer1);
            if(jsondeserializer2 == null)
                jsondeserializer2 = deserializationcontext.findContextualValueDeserializer(_collectionType.getContentType(), beanproperty);
        } else
        {
            jsondeserializer2 = deserializationcontext.handleSecondaryContextualization(jsondeserializer1, beanproperty);
        }
        flag = isDefaultDeserializer(jsondeserializer2);
        jsondeserializer3 = null;
        if(!flag)
            jsondeserializer3 = jsondeserializer2;
        return withResolved(jsondeserializer, jsondeserializer3);
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
        else
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
        if(_valueDeserializer != null)
            return deserializeUsingCustom(jsonparser, deserializationcontext, collection, _valueDeserializer);
        do
        {
            JsonToken jsontoken = jsonparser.nextToken();
            if(jsontoken == JsonToken.END_ARRAY)
                continue;
            String s;
            if(jsontoken == JsonToken.VALUE_NULL)
                s = null;
            else
                s = _parseString(jsonparser, deserializationcontext);
            collection.add(s);
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

    protected StringCollectionDeserializer withResolved(JsonDeserializer jsondeserializer, JsonDeserializer jsondeserializer1)
    {
        if(_valueDeserializer == jsondeserializer1 && _delegateDeserializer == jsondeserializer)
            return this;
        else
            return new StringCollectionDeserializer(_collectionType, _valueInstantiator, jsondeserializer, jsondeserializer1);
    }

    private static final long serialVersionUID = 1L;
    protected final JavaType _collectionType;
    protected final JsonDeserializer _delegateDeserializer;
    protected final JsonDeserializer _valueDeserializer;
    protected final ValueInstantiator _valueInstantiator;
}
