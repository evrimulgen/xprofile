// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.fasterxml.jackson.databind.deser.std;

import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.fasterxml.jackson.databind.node.*;
import java.io.IOException;

// Referenced classes of package com.fasterxml.jackson.databind.deser.std:
//            BaseNodeDeserializer

public class JsonNodeDeserializer extends BaseNodeDeserializer
{
    static final class ArrayDeserializer extends BaseNodeDeserializer
    {

        public static ArrayDeserializer getInstance()
        {
            return _instance;
        }

        public ArrayNode deserialize(JsonParser jsonparser, DeserializationContext deserializationcontext)
            throws IOException, JsonProcessingException
        {
            if(jsonparser.isExpectedStartArrayToken())
                return deserializeArray(jsonparser, deserializationcontext, deserializationcontext.getNodeFactory());
            else
                throw deserializationcontext.mappingException(com/fasterxml/jackson/databind/node/ArrayNode);
        }

        public volatile Object deserialize(JsonParser jsonparser, DeserializationContext deserializationcontext)
            throws IOException, JsonProcessingException
        {
            return deserialize(jsonparser, deserializationcontext);
        }

        protected static final ArrayDeserializer _instance = new ArrayDeserializer();
        private static final long serialVersionUID = 1L;


        protected ArrayDeserializer()
        {
            super(com/fasterxml/jackson/databind/node/ArrayNode);
        }
    }

    static final class ObjectDeserializer extends BaseNodeDeserializer
    {

        public static ObjectDeserializer getInstance()
        {
            return _instance;
        }

        public ObjectNode deserialize(JsonParser jsonparser, DeserializationContext deserializationcontext)
            throws IOException, JsonProcessingException
        {
            if(jsonparser.getCurrentToken() == JsonToken.START_OBJECT)
            {
                jsonparser.nextToken();
                return deserializeObject(jsonparser, deserializationcontext, deserializationcontext.getNodeFactory());
            }
            if(jsonparser.getCurrentToken() == JsonToken.FIELD_NAME)
                return deserializeObject(jsonparser, deserializationcontext, deserializationcontext.getNodeFactory());
            else
                throw deserializationcontext.mappingException(com/fasterxml/jackson/databind/node/ObjectNode);
        }

        public volatile Object deserialize(JsonParser jsonparser, DeserializationContext deserializationcontext)
            throws IOException, JsonProcessingException
        {
            return deserialize(jsonparser, deserializationcontext);
        }

        protected static final ObjectDeserializer _instance = new ObjectDeserializer();
        private static final long serialVersionUID = 1L;


        protected ObjectDeserializer()
        {
            super(com/fasterxml/jackson/databind/node/ObjectNode);
        }
    }


    protected JsonNodeDeserializer()
    {
        super(com/fasterxml/jackson/databind/JsonNode);
    }

    public static JsonDeserializer getDeserializer(Class class1)
    {
        if(class1 == com/fasterxml/jackson/databind/node/ObjectNode)
            return ObjectDeserializer.getInstance();
        if(class1 == com/fasterxml/jackson/databind/node/ArrayNode)
            return ArrayDeserializer.getInstance();
        else
            return instance;
    }

    public JsonNode deserialize(JsonParser jsonparser, DeserializationContext deserializationcontext)
        throws IOException, JsonProcessingException
    {
        static class _cls1
        {

            static final int $SwitchMap$com$fasterxml$jackson$core$JsonToken[];

            static 
            {
                $SwitchMap$com$fasterxml$jackson$core$JsonToken = new int[JsonToken.values().length];
                try
                {
                    $SwitchMap$com$fasterxml$jackson$core$JsonToken[JsonToken.START_OBJECT.ordinal()] = 1;
                }
                catch(NoSuchFieldError nosuchfielderror) { }
                try
                {
                    $SwitchMap$com$fasterxml$jackson$core$JsonToken[JsonToken.START_ARRAY.ordinal()] = 2;
                }
                catch(NoSuchFieldError nosuchfielderror1)
                {
                    return;
                }
            }
        }

        switch(_cls1..SwitchMap.com.fasterxml.jackson.core.JsonToken[jsonparser.getCurrentToken().ordinal()])
        {
        default:
            return deserializeAny(jsonparser, deserializationcontext, deserializationcontext.getNodeFactory());

        case 1: // '\001'
            return deserializeObject(jsonparser, deserializationcontext, deserializationcontext.getNodeFactory());

        case 2: // '\002'
            return deserializeArray(jsonparser, deserializationcontext, deserializationcontext.getNodeFactory());
        }
    }

    public volatile Object deserialize(JsonParser jsonparser, DeserializationContext deserializationcontext)
        throws IOException, JsonProcessingException
    {
        return deserialize(jsonparser, deserializationcontext);
    }

    public volatile Object deserializeWithType(JsonParser jsonparser, DeserializationContext deserializationcontext, TypeDeserializer typedeserializer)
        throws IOException, JsonProcessingException
    {
        return super.deserializeWithType(jsonparser, deserializationcontext, typedeserializer);
    }

    public JsonNode getNullValue()
    {
        return NullNode.getInstance();
    }

    public volatile Object getNullValue()
    {
        return getNullValue();
    }

    private static final JsonNodeDeserializer instance = new JsonNodeDeserializer();

}
