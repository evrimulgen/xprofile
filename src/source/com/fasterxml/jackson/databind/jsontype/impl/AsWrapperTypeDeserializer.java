// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.fasterxml.jackson.databind.jsontype.impl;

import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.core.util.JsonParserSequence;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.fasterxml.jackson.databind.jsontype.TypeIdResolver;
import com.fasterxml.jackson.databind.util.TokenBuffer;
import java.io.IOException;
import java.io.Serializable;

// Referenced classes of package com.fasterxml.jackson.databind.jsontype.impl:
//            TypeDeserializerBase

public class AsWrapperTypeDeserializer extends TypeDeserializerBase
    implements Serializable
{

    public AsWrapperTypeDeserializer(JavaType javatype, TypeIdResolver typeidresolver, String s, boolean flag, Class class1)
    {
        super(javatype, typeidresolver, s, flag, null);
    }

    protected AsWrapperTypeDeserializer(AsWrapperTypeDeserializer aswrappertypedeserializer, BeanProperty beanproperty)
    {
        super(aswrappertypedeserializer, beanproperty);
    }

    private final Object _deserialize(JsonParser jsonparser, DeserializationContext deserializationcontext)
        throws IOException, JsonProcessingException
    {
        Object obj;
        if(jsonparser.canReadTypeId())
        {
            obj = _deserializeWithNativeTypeId(jsonparser, deserializationcontext);
        } else
        {
            if(jsonparser.getCurrentToken() != JsonToken.START_OBJECT)
                throw deserializationcontext.wrongTokenException(jsonparser, JsonToken.START_OBJECT, (new StringBuilder()).append("need JSON Object to contain As.WRAPPER_OBJECT type information for class ").append(baseTypeName()).toString());
            if(jsonparser.nextToken() != JsonToken.FIELD_NAME)
                throw deserializationcontext.wrongTokenException(jsonparser, JsonToken.FIELD_NAME, (new StringBuilder()).append("need JSON String that contains type id (for subtype of ").append(baseTypeName()).append(")").toString());
            String s = jsonparser.getText();
            JsonDeserializer jsondeserializer = _findDeserializer(deserializationcontext, s);
            jsonparser.nextToken();
            if(_typeIdVisible && jsonparser.getCurrentToken() == JsonToken.START_OBJECT)
            {
                TokenBuffer tokenbuffer = new TokenBuffer(null, false);
                tokenbuffer.writeStartObject();
                tokenbuffer.writeFieldName(_typePropertyName);
                tokenbuffer.writeString(s);
                jsonparser = JsonParserSequence.createFlattened(tokenbuffer.asParser(jsonparser), jsonparser);
                jsonparser.nextToken();
            }
            obj = jsondeserializer.deserialize(jsonparser, deserializationcontext);
            if(jsonparser.nextToken() != JsonToken.END_OBJECT)
                throw deserializationcontext.wrongTokenException(jsonparser, JsonToken.END_OBJECT, "expected closing END_OBJECT after type information and deserialized value");
        }
        return obj;
    }

    public Object deserializeTypedFromAny(JsonParser jsonparser, DeserializationContext deserializationcontext)
        throws IOException, JsonProcessingException
    {
        return _deserialize(jsonparser, deserializationcontext);
    }

    public Object deserializeTypedFromArray(JsonParser jsonparser, DeserializationContext deserializationcontext)
        throws IOException, JsonProcessingException
    {
        return _deserialize(jsonparser, deserializationcontext);
    }

    public Object deserializeTypedFromObject(JsonParser jsonparser, DeserializationContext deserializationcontext)
        throws IOException, JsonProcessingException
    {
        return _deserialize(jsonparser, deserializationcontext);
    }

    public Object deserializeTypedFromScalar(JsonParser jsonparser, DeserializationContext deserializationcontext)
        throws IOException, JsonProcessingException
    {
        return _deserialize(jsonparser, deserializationcontext);
    }

    public TypeDeserializer forProperty(BeanProperty beanproperty)
    {
        if(beanproperty == _property)
            return this;
        else
            return new AsWrapperTypeDeserializer(this, beanproperty);
    }

    public com.fasterxml.jackson.annotation.JsonTypeInfo.As getTypeInclusion()
    {
        return com.fasterxml.jackson.annotation.JsonTypeInfo.As.WRAPPER_OBJECT;
    }

    private static final long serialVersionUID = 0x4a2f47f9ad71b962L;
}
