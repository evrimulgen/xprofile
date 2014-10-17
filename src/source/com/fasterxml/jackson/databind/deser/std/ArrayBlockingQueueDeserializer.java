// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.fasterxml.jackson.databind.deser.std;

import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.deser.ValueInstantiator;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.ArrayBlockingQueue;

// Referenced classes of package com.fasterxml.jackson.databind.deser.std:
//            CollectionDeserializer

public class ArrayBlockingQueueDeserializer extends CollectionDeserializer
{

    public ArrayBlockingQueueDeserializer(JavaType javatype, JsonDeserializer jsondeserializer, TypeDeserializer typedeserializer, ValueInstantiator valueinstantiator, JsonDeserializer jsondeserializer1)
    {
        super(javatype, jsondeserializer, typedeserializer, valueinstantiator, jsondeserializer1);
    }

    protected ArrayBlockingQueueDeserializer(ArrayBlockingQueueDeserializer arrayblockingqueuedeserializer)
    {
        super(arrayblockingqueuedeserializer);
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
        return deserialize(jsonparser, deserializationcontext, ((Collection) (null)));
    }

    public Collection deserialize(JsonParser jsonparser, DeserializationContext deserializationcontext, Collection collection)
        throws IOException, JsonProcessingException
    {
        if(!jsonparser.isExpectedStartArrayToken())
            return handleNonArray(jsonparser, deserializationcontext, new ArrayBlockingQueue(1));
        ArrayList arraylist = new ArrayList();
        JsonDeserializer jsondeserializer = _valueDeserializer;
        TypeDeserializer typedeserializer = _valueTypeDeserializer;
        do
        {
            JsonToken jsontoken = jsonparser.nextToken();
            if(jsontoken == JsonToken.END_ARRAY)
                break;
            Object obj;
            if(jsontoken == JsonToken.VALUE_NULL)
                obj = null;
            else
            if(typedeserializer == null)
                obj = jsondeserializer.deserialize(jsonparser, deserializationcontext);
            else
                obj = jsondeserializer.deserializeWithType(jsonparser, deserializationcontext, typedeserializer);
            arraylist.add(obj);
        } while(true);
        if(collection != null)
        {
            collection.addAll(arraylist);
            return collection;
        } else
        {
            return new ArrayBlockingQueue(arraylist.size(), false, arraylist);
        }
    }

    public Object deserializeWithType(JsonParser jsonparser, DeserializationContext deserializationcontext, TypeDeserializer typedeserializer)
        throws IOException, JsonProcessingException
    {
        return typedeserializer.deserializeTypedFromArray(jsonparser, deserializationcontext);
    }

    protected ArrayBlockingQueueDeserializer withResolved(JsonDeserializer jsondeserializer, JsonDeserializer jsondeserializer1, TypeDeserializer typedeserializer)
    {
        if(jsondeserializer == _delegateDeserializer && jsondeserializer1 == _valueDeserializer && typedeserializer == _valueTypeDeserializer)
            return this;
        else
            return new ArrayBlockingQueueDeserializer(_collectionType, jsondeserializer1, typedeserializer, _valueInstantiator, jsondeserializer);
    }

    protected volatile CollectionDeserializer withResolved(JsonDeserializer jsondeserializer, JsonDeserializer jsondeserializer1, TypeDeserializer typedeserializer)
    {
        return withResolved(jsondeserializer, jsondeserializer1, typedeserializer);
    }

    private static final long serialVersionUID = 0x4bf04fdfa60ff8f4L;
}
