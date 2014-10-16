// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.fasterxml.jackson.databind.deser.std;

import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.deser.ContextualDeserializer;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.fasterxml.jackson.databind.util.ObjectBuffer;
import java.io.IOException;

// Referenced classes of package com.fasterxml.jackson.databind.deser.std:
//            StdDeserializer

public final class StringArrayDeserializer extends StdDeserializer
    implements ContextualDeserializer
{

    public StringArrayDeserializer()
    {
        super([Ljava/lang/String;);
        _elementDeserializer = null;
    }

    protected StringArrayDeserializer(JsonDeserializer jsondeserializer)
    {
        super([Ljava/lang/String;);
        _elementDeserializer = jsondeserializer;
    }

    private final String[] handleNonArray(JsonParser jsonparser, DeserializationContext deserializationcontext)
        throws IOException, JsonProcessingException
    {
        if(!deserializationcontext.isEnabled(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY))
            if(jsonparser.getCurrentToken() == JsonToken.VALUE_STRING && deserializationcontext.isEnabled(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT) && jsonparser.getText().length() == 0)
                return null;
            else
                throw deserializationcontext.mappingException(_valueClass);
        String as[] = new String[1];
        JsonToken jsontoken = jsonparser.getCurrentToken();
        JsonToken jsontoken1 = JsonToken.VALUE_NULL;
        String s = null;
        if(jsontoken != jsontoken1)
            s = _parseString(jsonparser, deserializationcontext);
        as[0] = s;
        return as;
    }

    protected final String[] _deserializeCustom(JsonParser jsonparser, DeserializationContext deserializationcontext)
        throws IOException, JsonProcessingException
    {
        ObjectBuffer objectbuffer = deserializationcontext.leaseObjectBuffer();
        Object aobj[] = objectbuffer.resetAndStart();
        JsonDeserializer jsondeserializer = _elementDeserializer;
        int i = 0;
        Object aobj1[] = aobj;
        do
        {
            JsonToken jsontoken = jsonparser.nextToken();
            if(jsontoken != JsonToken.END_ARRAY)
            {
                Object obj;
                Object aobj2[];
                int k;
                int l;
                if(jsontoken == JsonToken.VALUE_NULL)
                    obj = null;
                else
                    obj = (String)jsondeserializer.deserialize(jsonparser, deserializationcontext);
                String as[];
                if(i >= aobj1.length)
                {
                    aobj2 = objectbuffer.appendCompletedChunk(aobj1);
                    k = 0;
                } else
                {
                    int j = i;
                    aobj2 = aobj1;
                    k = j;
                }
                l = k + 1;
                aobj2[k] = obj;
                aobj1 = aobj2;
                i = l;
            } else
            {
                as = (String[])objectbuffer.completeAndClearBuffer(aobj1, i, java/lang/String);
                deserializationcontext.returnObjectBuffer(objectbuffer);
                return as;
            }
        } while(true);
    }

    public JsonDeserializer createContextual(DeserializationContext deserializationcontext, BeanProperty beanproperty)
        throws JsonMappingException
    {
        JsonDeserializer jsondeserializer = findConvertingContentDeserializer(deserializationcontext, beanproperty, _elementDeserializer);
        JsonDeserializer jsondeserializer1;
        if(jsondeserializer == null)
            jsondeserializer1 = deserializationcontext.findContextualValueDeserializer(deserializationcontext.constructType(java/lang/String), beanproperty);
        else
            jsondeserializer1 = deserializationcontext.handleSecondaryContextualization(jsondeserializer, beanproperty);
        if(jsondeserializer1 != null && isDefaultDeserializer(jsondeserializer1))
            jsondeserializer1 = null;
        if(_elementDeserializer != jsondeserializer1)
            this = new StringArrayDeserializer(jsondeserializer1);
        return this;
    }

    public volatile Object deserialize(JsonParser jsonparser, DeserializationContext deserializationcontext)
        throws IOException, JsonProcessingException
    {
        return deserialize(jsonparser, deserializationcontext);
    }

    public String[] deserialize(JsonParser jsonparser, DeserializationContext deserializationcontext)
        throws IOException, JsonProcessingException
    {
        if(!jsonparser.isExpectedStartArrayToken())
            return handleNonArray(jsonparser, deserializationcontext);
        if(_elementDeserializer != null)
            return _deserializeCustom(jsonparser, deserializationcontext);
        ObjectBuffer objectbuffer = deserializationcontext.leaseObjectBuffer();
        Object aobj[] = objectbuffer.resetAndStart();
        int i = 0;
        do
        {
            JsonToken jsontoken = jsonparser.nextToken();
            if(jsontoken != JsonToken.END_ARRAY)
            {
                String s;
                int j;
                if(jsontoken == JsonToken.VALUE_STRING)
                    s = jsonparser.getText();
                else
                if(jsontoken == JsonToken.VALUE_NULL)
                    s = null;
                else
                    s = _parseString(jsonparser, deserializationcontext);
                String as[];
                if(i >= aobj.length)
                {
                    aobj = objectbuffer.appendCompletedChunk(aobj);
                    j = 0;
                } else
                {
                    j = i;
                }
                i = j + 1;
                aobj[j] = s;
            } else
            {
                as = (String[])objectbuffer.completeAndClearBuffer(aobj, i, java/lang/String);
                deserializationcontext.returnObjectBuffer(objectbuffer);
                return as;
            }
        } while(true);
    }

    public Object deserializeWithType(JsonParser jsonparser, DeserializationContext deserializationcontext, TypeDeserializer typedeserializer)
        throws IOException, JsonProcessingException
    {
        return typedeserializer.deserializeTypedFromArray(jsonparser, deserializationcontext);
    }

    public static final StringArrayDeserializer instance = new StringArrayDeserializer();
    private static final long serialVersionUID = 0x96aca308ea9cc20bL;
    protected JsonDeserializer _elementDeserializer;

}
