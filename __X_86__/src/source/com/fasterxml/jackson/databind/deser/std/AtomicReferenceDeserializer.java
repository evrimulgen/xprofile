// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.fasterxml.jackson.databind.deser.std;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.deser.ContextualDeserializer;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicReference;

// Referenced classes of package com.fasterxml.jackson.databind.deser.std:
//            StdDeserializer

public class AtomicReferenceDeserializer extends StdDeserializer
    implements ContextualDeserializer
{

    public AtomicReferenceDeserializer(JavaType javatype)
    {
        this(javatype, null, null);
    }

    public AtomicReferenceDeserializer(JavaType javatype, TypeDeserializer typedeserializer, JsonDeserializer jsondeserializer)
    {
        super(java/util/concurrent/atomic/AtomicReference);
        _referencedType = javatype;
        _valueDeserializer = jsondeserializer;
        _valueTypeDeserializer = typedeserializer;
    }

    public JsonDeserializer createContextual(DeserializationContext deserializationcontext, BeanProperty beanproperty)
        throws JsonMappingException
    {
        JsonDeserializer jsondeserializer = _valueDeserializer;
        TypeDeserializer typedeserializer = _valueTypeDeserializer;
        if(jsondeserializer == null)
            jsondeserializer = deserializationcontext.findContextualValueDeserializer(_referencedType, beanproperty);
        if(typedeserializer != null)
            typedeserializer = typedeserializer.forProperty(beanproperty);
        if(jsondeserializer == _valueDeserializer && typedeserializer == _valueTypeDeserializer)
            return this;
        else
            return withResolved(typedeserializer, jsondeserializer);
    }

    public volatile Object deserialize(JsonParser jsonparser, DeserializationContext deserializationcontext)
        throws IOException, JsonProcessingException
    {
        return deserialize(jsonparser, deserializationcontext);
    }

    public AtomicReference deserialize(JsonParser jsonparser, DeserializationContext deserializationcontext)
        throws IOException, JsonProcessingException
    {
        if(_valueTypeDeserializer != null)
            return new AtomicReference(_valueDeserializer.deserializeWithType(jsonparser, deserializationcontext, _valueTypeDeserializer));
        else
            return new AtomicReference(_valueDeserializer.deserialize(jsonparser, deserializationcontext));
    }

    public volatile Object deserializeWithType(JsonParser jsonparser, DeserializationContext deserializationcontext, TypeDeserializer typedeserializer)
        throws IOException, JsonProcessingException
    {
        return ((Object) (deserializeWithType(jsonparser, deserializationcontext, typedeserializer)));
    }

    public Object[] deserializeWithType(JsonParser jsonparser, DeserializationContext deserializationcontext, TypeDeserializer typedeserializer)
        throws IOException, JsonProcessingException
    {
        return (Object[])(Object[])typedeserializer.deserializeTypedFromAny(jsonparser, deserializationcontext);
    }

    public volatile Object getNullValue()
    {
        return getNullValue();
    }

    public AtomicReference getNullValue()
    {
        return new AtomicReference();
    }

    public AtomicReferenceDeserializer withResolved(TypeDeserializer typedeserializer, JsonDeserializer jsondeserializer)
    {
        return new AtomicReferenceDeserializer(_referencedType, typedeserializer, jsondeserializer);
    }

    private static final long serialVersionUID = 1L;
    protected final JavaType _referencedType;
    protected final JsonDeserializer _valueDeserializer;
    protected final TypeDeserializer _valueTypeDeserializer;
}
