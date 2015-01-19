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

// Referenced classes of package com.fasterxml.jackson.databind.jsontype.impl:
//            AsArrayTypeDeserializer

public class AsPropertyTypeDeserializer extends AsArrayTypeDeserializer
{

    public AsPropertyTypeDeserializer(JavaType javatype, TypeIdResolver typeidresolver, String s, boolean flag, Class class1)
    {
        super(javatype, typeidresolver, s, flag, class1);
    }

    public AsPropertyTypeDeserializer(AsPropertyTypeDeserializer aspropertytypedeserializer, BeanProperty beanproperty)
    {
        super(aspropertytypedeserializer, beanproperty);
    }

    protected final Object _deserializeTypedForId(JsonParser jsonparser, DeserializationContext deserializationcontext, TokenBuffer tokenbuffer)
        throws IOException, JsonProcessingException
    {
        String s = jsonparser.getText();
        JsonDeserializer jsondeserializer = _findDeserializer(deserializationcontext, s);
        if(_typeIdVisible)
        {
            if(tokenbuffer == null)
                tokenbuffer = new TokenBuffer(null, false);
            tokenbuffer.writeFieldName(jsonparser.getCurrentName());
            tokenbuffer.writeString(s);
        }
        if(tokenbuffer != null)
            jsonparser = JsonParserSequence.createFlattened(tokenbuffer.asParser(jsonparser), jsonparser);
        jsonparser.nextToken();
        return jsondeserializer.deserialize(jsonparser, deserializationcontext);
    }

    protected Object _deserializeTypedUsingDefaultImpl(JsonParser jsonparser, DeserializationContext deserializationcontext, TokenBuffer tokenbuffer)
        throws IOException, JsonProcessingException
    {
        JsonDeserializer jsondeserializer = _findDefaultImplDeserializer(deserializationcontext);
        Object obj;
        if(jsondeserializer != null)
        {
            if(tokenbuffer != null)
            {
                tokenbuffer.writeEndObject();
                jsonparser = tokenbuffer.asParser(jsonparser);
                jsonparser.nextToken();
            }
            obj = jsondeserializer.deserialize(jsonparser, deserializationcontext);
        } else
        {
            obj = TypeDeserializer.deserializeIfNatural(jsonparser, deserializationcontext, _baseType);
            if(obj == null)
                if(jsonparser.getCurrentToken() == JsonToken.START_ARRAY)
                    return super.deserializeTypedFromAny(jsonparser, deserializationcontext);
                else
                    throw deserializationcontext.wrongTokenException(jsonparser, JsonToken.FIELD_NAME, (new StringBuilder()).append("missing property '").append(_typePropertyName).append("' that is to contain type id  (for class ").append(baseTypeName()).append(")").toString());
        }
        return obj;
    }

    public Object deserializeTypedFromAny(JsonParser jsonparser, DeserializationContext deserializationcontext)
        throws IOException, JsonProcessingException
    {
        if(jsonparser.getCurrentToken() == JsonToken.START_ARRAY)
            return super.deserializeTypedFromArray(jsonparser, deserializationcontext);
        else
            return deserializeTypedFromObject(jsonparser, deserializationcontext);
    }

    public Object deserializeTypedFromObject(JsonParser jsonparser, DeserializationContext deserializationcontext)
        throws IOException, JsonProcessingException
    {
        if(jsonparser.canReadTypeId())
            return _deserializeWithNativeTypeId(jsonparser, deserializationcontext);
        JsonToken jsontoken = jsonparser.getCurrentToken();
        JsonToken jsontoken1;
        TokenBuffer tokenbuffer;
        if(jsontoken == JsonToken.START_OBJECT)
        {
            jsontoken = jsonparser.nextToken();
        } else
        {
            if(jsontoken == JsonToken.START_ARRAY)
                return _deserializeTypedUsingDefaultImpl(jsonparser, deserializationcontext, null);
            if(jsontoken != JsonToken.FIELD_NAME)
                return _deserializeTypedUsingDefaultImpl(jsonparser, deserializationcontext, null);
        }
        jsontoken1 = jsontoken;
        tokenbuffer = null;
        for(; jsontoken1 == JsonToken.FIELD_NAME; jsontoken1 = jsonparser.nextToken())
        {
            String s = jsonparser.getCurrentName();
            jsonparser.nextToken();
            if(_typePropertyName.equals(s))
                return _deserializeTypedForId(jsonparser, deserializationcontext, tokenbuffer);
            if(tokenbuffer == null)
                tokenbuffer = new TokenBuffer(null, false);
            tokenbuffer.writeFieldName(s);
            tokenbuffer.copyCurrentStructure(jsonparser);
        }

        return _deserializeTypedUsingDefaultImpl(jsonparser, deserializationcontext, tokenbuffer);
    }

    public TypeDeserializer forProperty(BeanProperty beanproperty)
    {
        if(beanproperty == _property)
            return this;
        else
            return new AsPropertyTypeDeserializer(this, beanproperty);
    }

    public com.fasterxml.jackson.annotation.JsonTypeInfo.As getTypeInclusion()
    {
        return com.fasterxml.jackson.annotation.JsonTypeInfo.As.PROPERTY;
    }

    private static final long serialVersionUID = 1L;
}
