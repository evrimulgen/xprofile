// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.fasterxml.jackson.databind.deser.std;

import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.deser.ContextualDeserializer;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.fasterxml.jackson.databind.type.ArrayType;
import com.fasterxml.jackson.databind.util.ObjectBuffer;
import java.io.IOException;
import java.lang.reflect.Array;

// Referenced classes of package com.fasterxml.jackson.databind.deser.std:
//            ContainerDeserializerBase

public class ObjectArrayDeserializer extends ContainerDeserializerBase
    implements ContextualDeserializer
{

    public ObjectArrayDeserializer(ArrayType arraytype, JsonDeserializer jsondeserializer, TypeDeserializer typedeserializer)
    {
        super(arraytype);
        _arrayType = arraytype;
        _elementClass = arraytype.getContentType().getRawClass();
        boolean flag;
        if(_elementClass == java/lang/Object)
            flag = true;
        else
            flag = false;
        _untyped = flag;
        _elementDeserializer = jsondeserializer;
        _elementTypeDeserializer = typedeserializer;
    }

    private final Object[] handleNonArray(JsonParser jsonparser, DeserializationContext deserializationcontext)
        throws IOException, JsonProcessingException
    {
        if(jsonparser.getCurrentToken() == JsonToken.VALUE_STRING && deserializationcontext.isEnabled(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT) && jsonparser.getText().length() == 0)
            return null;
        if(!deserializationcontext.isEnabled(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY))
            if(jsonparser.getCurrentToken() == JsonToken.VALUE_STRING && _elementClass == java/lang/Byte)
                return deserializeFromBase64(jsonparser, deserializationcontext);
            else
                throw deserializationcontext.mappingException(_arrayType.getRawClass());
        Object obj;
        Object aobj[];
        if(jsonparser.getCurrentToken() == JsonToken.VALUE_NULL)
            obj = null;
        else
        if(_elementTypeDeserializer == null)
            obj = _elementDeserializer.deserialize(jsonparser, deserializationcontext);
        else
            obj = _elementDeserializer.deserializeWithType(jsonparser, deserializationcontext, _elementTypeDeserializer);
        if(_untyped)
            aobj = new Object[1];
        else
            aobj = (Object[])(Object[])Array.newInstance(_elementClass, 1);
        aobj[0] = obj;
        return aobj;
    }

    public JsonDeserializer createContextual(DeserializationContext deserializationcontext, BeanProperty beanproperty)
        throws JsonMappingException
    {
        JsonDeserializer jsondeserializer = findConvertingContentDeserializer(deserializationcontext, beanproperty, _elementDeserializer);
        JsonDeserializer jsondeserializer1;
        TypeDeserializer typedeserializer;
        if(jsondeserializer == null)
            jsondeserializer1 = deserializationcontext.findContextualValueDeserializer(_arrayType.getContentType(), beanproperty);
        else
            jsondeserializer1 = deserializationcontext.handleSecondaryContextualization(jsondeserializer, beanproperty);
        typedeserializer = _elementTypeDeserializer;
        if(typedeserializer != null)
            typedeserializer = typedeserializer.forProperty(beanproperty);
        return withDeserializer(typedeserializer, jsondeserializer1);
    }

    public volatile Object deserialize(JsonParser jsonparser, DeserializationContext deserializationcontext)
        throws IOException, JsonProcessingException
    {
        return ((Object) (deserialize(jsonparser, deserializationcontext)));
    }

    public Object[] deserialize(JsonParser jsonparser, DeserializationContext deserializationcontext)
        throws IOException, JsonProcessingException
    {
        ObjectBuffer objectbuffer;
        TypeDeserializer typedeserializer;
        Object aobj1[];
        int i;
        if(!jsonparser.isExpectedStartArrayToken())
            return handleNonArray(jsonparser, deserializationcontext);
        objectbuffer = deserializationcontext.leaseObjectBuffer();
        Object aobj[] = objectbuffer.resetAndStart();
        typedeserializer = _elementTypeDeserializer;
        aobj1 = aobj;
        i = 0;
_L3:
        JsonToken jsontoken = jsonparser.nextToken();
        if(jsontoken == JsonToken.END_ARRAY) goto _L2; else goto _L1
_L1:
        Object obj;
        int j;
        if(jsontoken == JsonToken.VALUE_NULL)
            obj = null;
        else
        if(typedeserializer == null)
            obj = _elementDeserializer.deserialize(jsonparser, deserializationcontext);
        else
            obj = _elementDeserializer.deserializeWithType(jsonparser, deserializationcontext, typedeserializer);
        Object aobj2[];
        if(i >= aobj1.length)
        {
            aobj1 = objectbuffer.appendCompletedChunk(aobj1);
            j = 0;
        } else
        {
            j = i;
        }
        i = j + 1;
        aobj1[j] = obj;
        if(true) goto _L3; else goto _L2
_L2:
        if(_untyped)
            aobj2 = objectbuffer.completeAndClearBuffer(aobj1, i);
        else
            aobj2 = objectbuffer.completeAndClearBuffer(aobj1, i, _elementClass);
        deserializationcontext.returnObjectBuffer(objectbuffer);
        return aobj2;
    }

    protected Byte[] deserializeFromBase64(JsonParser jsonparser, DeserializationContext deserializationcontext)
        throws IOException, JsonProcessingException
    {
        byte abyte0[] = jsonparser.getBinaryValue(deserializationcontext.getBase64Variant());
        Byte abyte[] = new Byte[abyte0.length];
        int i = 0;
        for(int j = abyte0.length; i < j; i++)
            abyte[i] = Byte.valueOf(abyte0[i]);

        return abyte;
    }

    public volatile Object deserializeWithType(JsonParser jsonparser, DeserializationContext deserializationcontext, TypeDeserializer typedeserializer)
        throws IOException, JsonProcessingException
    {
        return ((Object) (deserializeWithType(jsonparser, deserializationcontext, typedeserializer)));
    }

    public Object[] deserializeWithType(JsonParser jsonparser, DeserializationContext deserializationcontext, TypeDeserializer typedeserializer)
        throws IOException, JsonProcessingException
    {
        return (Object[])(Object[])typedeserializer.deserializeTypedFromArray(jsonparser, deserializationcontext);
    }

    public JsonDeserializer getContentDeserializer()
    {
        return _elementDeserializer;
    }

    public JavaType getContentType()
    {
        return _arrayType.getContentType();
    }

    public ObjectArrayDeserializer withDeserializer(TypeDeserializer typedeserializer, JsonDeserializer jsondeserializer)
    {
        if(jsondeserializer == _elementDeserializer && typedeserializer == _elementTypeDeserializer)
            return this;
        else
            return new ObjectArrayDeserializer(_arrayType, jsondeserializer, typedeserializer);
    }

    private static final long serialVersionUID = 1L;
    protected final ArrayType _arrayType;
    protected final Class _elementClass;
    protected JsonDeserializer _elementDeserializer;
    protected final TypeDeserializer _elementTypeDeserializer;
    protected final boolean _untyped;
}
