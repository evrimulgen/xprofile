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
//            StdDeserializer

abstract class BaseNodeDeserializer extends StdDeserializer
{

    public BaseNodeDeserializer(Class class1)
    {
        super(class1);
    }

    protected void _handleDuplicateField(JsonParser jsonparser, DeserializationContext deserializationcontext, JsonNodeFactory jsonnodefactory, String s, ObjectNode objectnode, JsonNode jsonnode, JsonNode jsonnode1)
        throws JsonProcessingException
    {
        if(deserializationcontext.isEnabled(DeserializationFeature.FAIL_ON_READING_DUP_TREE_KEY))
            _reportProblem(jsonparser, (new StringBuilder()).append("Duplicate field '").append(s).append("' for ObjectNode: not allowed when FAIL_ON_READING_DUP_TREE_KEY enabled").toString());
        _handleDuplicateField(s, objectnode, jsonnode, jsonnode1);
    }

    protected void _handleDuplicateField(String s, ObjectNode objectnode, JsonNode jsonnode, JsonNode jsonnode1)
        throws JsonProcessingException
    {
    }

    protected void _reportProblem(JsonParser jsonparser, String s)
        throws JsonMappingException
    {
        throw new JsonMappingException(s, jsonparser.getTokenLocation());
    }

    protected final JsonNode deserializeAny(JsonParser jsonparser, DeserializationContext deserializationcontext, JsonNodeFactory jsonnodefactory)
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
                catch(NoSuchFieldError nosuchfielderror1) { }
                try
                {
                    $SwitchMap$com$fasterxml$jackson$core$JsonToken[JsonToken.VALUE_STRING.ordinal()] = 3;
                }
                catch(NoSuchFieldError nosuchfielderror2) { }
                try
                {
                    $SwitchMap$com$fasterxml$jackson$core$JsonToken[JsonToken.END_ARRAY.ordinal()] = 4;
                }
                catch(NoSuchFieldError nosuchfielderror3) { }
                try
                {
                    $SwitchMap$com$fasterxml$jackson$core$JsonToken[JsonToken.END_OBJECT.ordinal()] = 5;
                }
                catch(NoSuchFieldError nosuchfielderror4) { }
                try
                {
                    $SwitchMap$com$fasterxml$jackson$core$JsonToken[JsonToken.FIELD_NAME.ordinal()] = 6;
                }
                catch(NoSuchFieldError nosuchfielderror5) { }
                try
                {
                    $SwitchMap$com$fasterxml$jackson$core$JsonToken[JsonToken.VALUE_EMBEDDED_OBJECT.ordinal()] = 7;
                }
                catch(NoSuchFieldError nosuchfielderror6) { }
                try
                {
                    $SwitchMap$com$fasterxml$jackson$core$JsonToken[JsonToken.VALUE_NUMBER_INT.ordinal()] = 8;
                }
                catch(NoSuchFieldError nosuchfielderror7) { }
                try
                {
                    $SwitchMap$com$fasterxml$jackson$core$JsonToken[JsonToken.VALUE_NUMBER_FLOAT.ordinal()] = 9;
                }
                catch(NoSuchFieldError nosuchfielderror8) { }
                try
                {
                    $SwitchMap$com$fasterxml$jackson$core$JsonToken[JsonToken.VALUE_TRUE.ordinal()] = 10;
                }
                catch(NoSuchFieldError nosuchfielderror9) { }
                try
                {
                    $SwitchMap$com$fasterxml$jackson$core$JsonToken[JsonToken.VALUE_FALSE.ordinal()] = 11;
                }
                catch(NoSuchFieldError nosuchfielderror10) { }
                try
                {
                    $SwitchMap$com$fasterxml$jackson$core$JsonToken[JsonToken.VALUE_NULL.ordinal()] = 12;
                }
                catch(NoSuchFieldError nosuchfielderror11)
                {
                    return;
                }
            }
        }

        switch(_cls1..SwitchMap.com.fasterxml.jackson.core.JsonToken[jsonparser.getCurrentToken().ordinal()])
        {
        case 4: // '\004'
        default:
            throw deserializationcontext.mappingException(handledType());

        case 1: // '\001'
        case 5: // '\005'
            return deserializeObject(jsonparser, deserializationcontext, jsonnodefactory);

        case 2: // '\002'
            return deserializeArray(jsonparser, deserializationcontext, jsonnodefactory);

        case 6: // '\006'
            return deserializeObject(jsonparser, deserializationcontext, jsonnodefactory);

        case 7: // '\007'
            Object obj = jsonparser.getEmbeddedObject();
            if(obj == null)
                return jsonnodefactory.nullNode();
            if(obj.getClass() == [B)
                return jsonnodefactory.binaryNode((byte[])(byte[])obj);
            else
                return jsonnodefactory.pojoNode(obj);

        case 3: // '\003'
            return jsonnodefactory.textNode(jsonparser.getText());

        case 8: // '\b'
            com.fasterxml.jackson.core.JsonParser.NumberType numbertype = jsonparser.getNumberType();
            if(numbertype == com.fasterxml.jackson.core.JsonParser.NumberType.BIG_INTEGER || deserializationcontext.isEnabled(DeserializationFeature.USE_BIG_INTEGER_FOR_INTS))
                return jsonnodefactory.numberNode(jsonparser.getBigIntegerValue());
            if(numbertype == com.fasterxml.jackson.core.JsonParser.NumberType.INT)
                return jsonnodefactory.numberNode(jsonparser.getIntValue());
            else
                return jsonnodefactory.numberNode(jsonparser.getLongValue());

        case 9: // '\t'
            if(jsonparser.getNumberType() == com.fasterxml.jackson.core.JsonParser.NumberType.BIG_DECIMAL || deserializationcontext.isEnabled(DeserializationFeature.USE_BIG_DECIMAL_FOR_FLOATS))
                return jsonnodefactory.numberNode(jsonparser.getDecimalValue());
            else
                return jsonnodefactory.numberNode(jsonparser.getDoubleValue());

        case 10: // '\n'
            return jsonnodefactory.booleanNode(true);

        case 11: // '\013'
            return jsonnodefactory.booleanNode(false);

        case 12: // '\f'
            return jsonnodefactory.nullNode();
        }
    }

    protected final ArrayNode deserializeArray(JsonParser jsonparser, DeserializationContext deserializationcontext, JsonNodeFactory jsonnodefactory)
        throws IOException, JsonProcessingException
    {
        ArrayNode arraynode = jsonnodefactory.arrayNode();
        do
        {
            JsonToken jsontoken = jsonparser.nextToken();
            if(jsontoken == null)
                throw deserializationcontext.mappingException("Unexpected end-of-input when binding data into ArrayNode");
            switch(_cls1..SwitchMap.com.fasterxml.jackson.core.JsonToken[jsontoken.ordinal()])
            {
            default:
                arraynode.add(deserializeAny(jsonparser, deserializationcontext, jsonnodefactory));
                break;

            case 1: // '\001'
                arraynode.add(deserializeObject(jsonparser, deserializationcontext, jsonnodefactory));
                break;

            case 2: // '\002'
                arraynode.add(deserializeArray(jsonparser, deserializationcontext, jsonnodefactory));
                break;

            case 3: // '\003'
                arraynode.add(jsonnodefactory.textNode(jsonparser.getText()));
                break;

            case 4: // '\004'
                return arraynode;
            }
        } while(true);
    }

    protected final ObjectNode deserializeObject(JsonParser jsonparser, DeserializationContext deserializationcontext, JsonNodeFactory jsonnodefactory)
        throws IOException, JsonProcessingException
    {
        ObjectNode objectnode;
        JsonToken jsontoken;
        objectnode = jsonnodefactory.objectNode();
        jsontoken = jsonparser.getCurrentToken();
        if(jsontoken == JsonToken.START_OBJECT)
            jsontoken = jsonparser.nextToken();
_L6:
        String s;
        if(jsontoken != JsonToken.FIELD_NAME)
            break MISSING_BLOCK_LABEL_162;
        s = jsonparser.getCurrentName();
        _cls1..SwitchMap.com.fasterxml.jackson.core.JsonToken[jsonparser.nextToken().ordinal()];
        JVM INSTR tableswitch 1 3: default 76
    //                   1 125
    //                   2 137
    //                   3 149;
           goto _L1 _L2 _L3 _L4
_L4:
        break MISSING_BLOCK_LABEL_149;
_L2:
        break; /* Loop/switch isn't completed */
_L1:
        Object obj = deserializeAny(jsonparser, deserializationcontext, jsonnodefactory);
_L7:
        JsonNode jsonnode = objectnode.replace(s, ((JsonNode) (obj)));
        if(jsonnode != null)
            _handleDuplicateField(jsonparser, deserializationcontext, jsonnodefactory, s, objectnode, jsonnode, ((JsonNode) (obj)));
        jsontoken = jsonparser.nextToken();
        if(true) goto _L6; else goto _L5
_L5:
        obj = deserializeObject(jsonparser, deserializationcontext, jsonnodefactory);
          goto _L7
_L3:
        obj = deserializeArray(jsonparser, deserializationcontext, jsonnodefactory);
          goto _L7
        obj = jsonnodefactory.textNode(jsonparser.getText());
          goto _L7
        return objectnode;
    }

    public Object deserializeWithType(JsonParser jsonparser, DeserializationContext deserializationcontext, TypeDeserializer typedeserializer)
        throws IOException, JsonProcessingException
    {
        return typedeserializer.deserializeTypedFromAny(jsonparser, deserializationcontext);
    }
}
