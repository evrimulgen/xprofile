// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.fasterxml.jackson.databind.deser.std;

import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.deser.ContextualDeserializer;
import com.fasterxml.jackson.databind.deser.ResolvableDeserializer;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.databind.util.ClassUtil;
import com.fasterxml.jackson.databind.util.ObjectBuffer;
import java.io.IOException;
import java.util.*;

// Referenced classes of package com.fasterxml.jackson.databind.deser.std:
//            StdDeserializer

public class UntypedObjectDeserializer extends StdDeserializer
    implements ResolvableDeserializer, ContextualDeserializer
{

    public UntypedObjectDeserializer()
    {
        super(java/lang/Object);
    }

    public UntypedObjectDeserializer(UntypedObjectDeserializer untypedobjectdeserializer, JsonDeserializer jsondeserializer, JsonDeserializer jsondeserializer1, JsonDeserializer jsondeserializer2, JsonDeserializer jsondeserializer3)
    {
        super(java/lang/Object);
        _mapDeserializer = jsondeserializer;
        _listDeserializer = jsondeserializer1;
        _stringDeserializer = jsondeserializer2;
        _numberDeserializer = jsondeserializer3;
    }

    protected JsonDeserializer _findCustomDeser(DeserializationContext deserializationcontext, JavaType javatype)
        throws JsonMappingException
    {
        JsonDeserializer jsondeserializer = deserializationcontext.findRootValueDeserializer(javatype);
        if(ClassUtil.isJacksonStdImpl(jsondeserializer))
            jsondeserializer = null;
        return jsondeserializer;
    }

    protected JsonDeserializer _withResolved(JsonDeserializer jsondeserializer, JsonDeserializer jsondeserializer1, JsonDeserializer jsondeserializer2, JsonDeserializer jsondeserializer3)
    {
        return new UntypedObjectDeserializer(this, jsondeserializer, jsondeserializer1, jsondeserializer2, jsondeserializer3);
    }

    public JsonDeserializer createContextual(DeserializationContext deserializationcontext, BeanProperty beanproperty)
        throws JsonMappingException
    {
        JsonDeserializer jsondeserializer = _mapDeserializer;
        JsonDeserializer jsondeserializer1;
        JsonDeserializer jsondeserializer2;
        JsonDeserializer jsondeserializer3;
        JsonDeserializer jsondeserializer4;
        JsonDeserializer jsondeserializer5;
        JsonDeserializer jsondeserializer6;
        if(jsondeserializer instanceof ContextualDeserializer)
            jsondeserializer1 = ((ContextualDeserializer)jsondeserializer).createContextual(deserializationcontext, beanproperty);
        else
            jsondeserializer1 = jsondeserializer;
        jsondeserializer2 = _listDeserializer;
        if(jsondeserializer2 instanceof ContextualDeserializer)
            jsondeserializer3 = ((ContextualDeserializer)jsondeserializer2).createContextual(deserializationcontext, beanproperty);
        else
            jsondeserializer3 = jsondeserializer2;
        jsondeserializer4 = _stringDeserializer;
        if(jsondeserializer4 instanceof ContextualDeserializer)
            jsondeserializer5 = ((ContextualDeserializer)jsondeserializer4).createContextual(deserializationcontext, beanproperty);
        else
            jsondeserializer5 = jsondeserializer4;
        jsondeserializer6 = _numberDeserializer;
        if(jsondeserializer6 instanceof ContextualDeserializer)
            jsondeserializer6 = ((ContextualDeserializer)jsondeserializer6).createContextual(deserializationcontext, beanproperty);
        if(jsondeserializer1 != _mapDeserializer || jsondeserializer3 != _listDeserializer || jsondeserializer5 != _stringDeserializer || jsondeserializer6 != _numberDeserializer)
            this = _withResolved(jsondeserializer1, jsondeserializer3, jsondeserializer5, jsondeserializer6);
        return this;
    }

    public Object deserialize(JsonParser jsonparser, DeserializationContext deserializationcontext)
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
                    $SwitchMap$com$fasterxml$jackson$core$JsonToken[JsonToken.FIELD_NAME.ordinal()] = 1;
                }
                catch(NoSuchFieldError nosuchfielderror) { }
                try
                {
                    $SwitchMap$com$fasterxml$jackson$core$JsonToken[JsonToken.START_OBJECT.ordinal()] = 2;
                }
                catch(NoSuchFieldError nosuchfielderror1) { }
                try
                {
                    $SwitchMap$com$fasterxml$jackson$core$JsonToken[JsonToken.START_ARRAY.ordinal()] = 3;
                }
                catch(NoSuchFieldError nosuchfielderror2) { }
                try
                {
                    $SwitchMap$com$fasterxml$jackson$core$JsonToken[JsonToken.VALUE_EMBEDDED_OBJECT.ordinal()] = 4;
                }
                catch(NoSuchFieldError nosuchfielderror3) { }
                try
                {
                    $SwitchMap$com$fasterxml$jackson$core$JsonToken[JsonToken.VALUE_STRING.ordinal()] = 5;
                }
                catch(NoSuchFieldError nosuchfielderror4) { }
                try
                {
                    $SwitchMap$com$fasterxml$jackson$core$JsonToken[JsonToken.VALUE_NUMBER_INT.ordinal()] = 6;
                }
                catch(NoSuchFieldError nosuchfielderror5) { }
                try
                {
                    $SwitchMap$com$fasterxml$jackson$core$JsonToken[JsonToken.VALUE_NUMBER_FLOAT.ordinal()] = 7;
                }
                catch(NoSuchFieldError nosuchfielderror6) { }
                try
                {
                    $SwitchMap$com$fasterxml$jackson$core$JsonToken[JsonToken.VALUE_TRUE.ordinal()] = 8;
                }
                catch(NoSuchFieldError nosuchfielderror7) { }
                try
                {
                    $SwitchMap$com$fasterxml$jackson$core$JsonToken[JsonToken.VALUE_FALSE.ordinal()] = 9;
                }
                catch(NoSuchFieldError nosuchfielderror8) { }
                try
                {
                    $SwitchMap$com$fasterxml$jackson$core$JsonToken[JsonToken.VALUE_NULL.ordinal()] = 10;
                }
                catch(NoSuchFieldError nosuchfielderror9) { }
                try
                {
                    $SwitchMap$com$fasterxml$jackson$core$JsonToken[JsonToken.END_ARRAY.ordinal()] = 11;
                }
                catch(NoSuchFieldError nosuchfielderror10) { }
                try
                {
                    $SwitchMap$com$fasterxml$jackson$core$JsonToken[JsonToken.END_OBJECT.ordinal()] = 12;
                }
                catch(NoSuchFieldError nosuchfielderror11)
                {
                    return;
                }
            }
        }

        switch(_cls1..SwitchMap.com.fasterxml.jackson.core.JsonToken[jsonparser.getCurrentToken().ordinal()])
        {
        default:
            throw deserializationcontext.mappingException(java/lang/Object);

        case 1: // '\001'
        case 2: // '\002'
            if(_mapDeserializer != null)
                return _mapDeserializer.deserialize(jsonparser, deserializationcontext);
            else
                return mapObject(jsonparser, deserializationcontext);

        case 3: // '\003'
            if(deserializationcontext.isEnabled(DeserializationFeature.USE_JAVA_ARRAY_FOR_JSON_ARRAY))
                return ((Object) (mapArrayToArray(jsonparser, deserializationcontext)));
            if(_listDeserializer != null)
                return _listDeserializer.deserialize(jsonparser, deserializationcontext);
            else
                return mapArray(jsonparser, deserializationcontext);

        case 4: // '\004'
            return jsonparser.getEmbeddedObject();

        case 5: // '\005'
            if(_stringDeserializer != null)
                return _stringDeserializer.deserialize(jsonparser, deserializationcontext);
            else
                return jsonparser.getText();

        case 6: // '\006'
            if(_numberDeserializer != null)
                return _numberDeserializer.deserialize(jsonparser, deserializationcontext);
            if(deserializationcontext.isEnabled(DeserializationFeature.USE_BIG_INTEGER_FOR_INTS))
                return jsonparser.getBigIntegerValue();
            else
                return jsonparser.getNumberValue();

        case 7: // '\007'
            if(_numberDeserializer != null)
                return _numberDeserializer.deserialize(jsonparser, deserializationcontext);
            if(deserializationcontext.isEnabled(DeserializationFeature.USE_BIG_DECIMAL_FOR_FLOATS))
                return jsonparser.getDecimalValue();
            else
                return Double.valueOf(jsonparser.getDoubleValue());

        case 8: // '\b'
            return Boolean.TRUE;

        case 9: // '\t'
            return Boolean.FALSE;

        case 10: // '\n'
            return null;
        }
    }

    public Object deserializeWithType(JsonParser jsonparser, DeserializationContext deserializationcontext, TypeDeserializer typedeserializer)
        throws IOException, JsonProcessingException
    {
        JsonToken jsontoken = jsonparser.getCurrentToken();
        switch(_cls1..SwitchMap.com.fasterxml.jackson.core.JsonToken[jsontoken.ordinal()])
        {
        default:
            throw deserializationcontext.mappingException(java/lang/Object);

        case 1: // '\001'
        case 2: // '\002'
        case 3: // '\003'
            return typedeserializer.deserializeTypedFromAny(jsonparser, deserializationcontext);

        case 5: // '\005'
            if(_stringDeserializer != null)
                return _stringDeserializer.deserialize(jsonparser, deserializationcontext);
            else
                return jsonparser.getText();

        case 6: // '\006'
            if(_numberDeserializer != null)
                return _numberDeserializer.deserialize(jsonparser, deserializationcontext);
            if(deserializationcontext.isEnabled(DeserializationFeature.USE_BIG_INTEGER_FOR_INTS))
                return jsonparser.getBigIntegerValue();
            else
                return jsonparser.getNumberValue();

        case 7: // '\007'
            if(_numberDeserializer != null)
                return _numberDeserializer.deserialize(jsonparser, deserializationcontext);
            if(deserializationcontext.isEnabled(DeserializationFeature.USE_BIG_DECIMAL_FOR_FLOATS))
                return jsonparser.getDecimalValue();
            else
                return Double.valueOf(jsonparser.getDoubleValue());

        case 8: // '\b'
            return Boolean.TRUE;

        case 9: // '\t'
            return Boolean.FALSE;

        case 4: // '\004'
            return jsonparser.getEmbeddedObject();

        case 10: // '\n'
            return null;
        }
    }

    protected Object mapArray(JsonParser jsonparser, DeserializationContext deserializationcontext)
        throws IOException, JsonProcessingException
    {
        if(jsonparser.nextToken() == JsonToken.END_ARRAY)
            return new ArrayList(4);
        ObjectBuffer objectbuffer = deserializationcontext.leaseObjectBuffer();
        Object aobj[] = objectbuffer.resetAndStart();
        int i = 0;
        Object aobj1[] = aobj;
        int j = 0;
        do
        {
            Object obj = deserialize(jsonparser, deserializationcontext);
            j++;
            int k;
            ArrayList arraylist;
            if(i >= aobj1.length)
            {
                aobj1 = objectbuffer.appendCompletedChunk(aobj1);
                k = 0;
            } else
            {
                k = i;
            }
            i = k + 1;
            aobj1[k] = obj;
        } while(jsonparser.nextToken() != JsonToken.END_ARRAY);
        arraylist = new ArrayList(1 + (j + (j >> 3)));
        objectbuffer.completeAndClearBuffer(aobj1, i, arraylist);
        return arraylist;
    }

    protected Object[] mapArrayToArray(JsonParser jsonparser, DeserializationContext deserializationcontext)
        throws IOException, JsonProcessingException
    {
        if(jsonparser.nextToken() == JsonToken.END_ARRAY)
            return NO_OBJECTS;
        ObjectBuffer objectbuffer = deserializationcontext.leaseObjectBuffer();
        Object aobj[] = objectbuffer.resetAndStart();
        int i = 0;
        do
        {
            Object obj = deserialize(jsonparser, deserializationcontext);
            int j;
            if(i >= aobj.length)
            {
                aobj = objectbuffer.appendCompletedChunk(aobj);
                j = 0;
            } else
            {
                j = i;
            }
            i = j + 1;
            aobj[j] = obj;
        } while(jsonparser.nextToken() != JsonToken.END_ARRAY);
        return objectbuffer.completeAndClearBuffer(aobj, i);
    }

    protected Object mapObject(JsonParser jsonparser, DeserializationContext deserializationcontext)
        throws IOException, JsonProcessingException
    {
        JsonToken jsontoken = jsonparser.getCurrentToken();
        if(jsontoken == JsonToken.START_OBJECT)
            jsontoken = jsonparser.nextToken();
        if(jsontoken != JsonToken.FIELD_NAME)
            return new LinkedHashMap(4);
        String s = jsonparser.getText();
        jsonparser.nextToken();
        Object obj = deserialize(jsonparser, deserializationcontext);
        if(jsonparser.nextToken() != JsonToken.FIELD_NAME)
        {
            LinkedHashMap linkedhashmap = new LinkedHashMap(4);
            linkedhashmap.put(s, obj);
            return linkedhashmap;
        }
        String s1 = jsonparser.getText();
        jsonparser.nextToken();
        Object obj1 = deserialize(jsonparser, deserializationcontext);
        if(jsonparser.nextToken() != JsonToken.FIELD_NAME)
        {
            LinkedHashMap linkedhashmap1 = new LinkedHashMap(4);
            linkedhashmap1.put(s, obj);
            linkedhashmap1.put(s1, obj1);
            return linkedhashmap1;
        }
        LinkedHashMap linkedhashmap2 = new LinkedHashMap();
        linkedhashmap2.put(s, obj);
        linkedhashmap2.put(s1, obj1);
        do
        {
            String s2 = jsonparser.getText();
            jsonparser.nextToken();
            linkedhashmap2.put(s2, deserialize(jsonparser, deserializationcontext));
        } while(jsonparser.nextToken() != JsonToken.END_OBJECT);
        return linkedhashmap2;
    }

    public void resolve(DeserializationContext deserializationcontext)
        throws JsonMappingException
    {
        JavaType javatype = deserializationcontext.constructType(java/lang/Object);
        JavaType javatype1 = deserializationcontext.constructType(java/lang/String);
        TypeFactory typefactory = deserializationcontext.getTypeFactory();
        _mapDeserializer = _findCustomDeser(deserializationcontext, typefactory.constructMapType(java/util/Map, javatype1, javatype));
        _listDeserializer = _findCustomDeser(deserializationcontext, typefactory.constructCollectionType(java/util/List, javatype));
        _stringDeserializer = _findCustomDeser(deserializationcontext, javatype1);
        _numberDeserializer = _findCustomDeser(deserializationcontext, typefactory.constructType(java/lang/Number));
    }

    private static final Object NO_OBJECTS[] = new Object[0];
    public static final UntypedObjectDeserializer instance = new UntypedObjectDeserializer();
    private static final long serialVersionUID = 1L;
    protected JsonDeserializer _listDeserializer;
    protected JsonDeserializer _mapDeserializer;
    protected JsonDeserializer _numberDeserializer;
    protected JsonDeserializer _stringDeserializer;

}
