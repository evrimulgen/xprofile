// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.fasterxml.jackson.databind.deser.std;

import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.HashSet;

// Referenced classes of package com.fasterxml.jackson.databind.deser.std:
//            StdDeserializer, StdScalarDeserializer

public class NumberDeserializers
{
    public static class BigDecimalDeserializer extends StdScalarDeserializer
    {

        public volatile Object deserialize(JsonParser jsonparser, DeserializationContext deserializationcontext)
            throws IOException, JsonProcessingException
        {
            return deserialize(jsonparser, deserializationcontext);
        }

        public BigDecimal deserialize(JsonParser jsonparser, DeserializationContext deserializationcontext)
            throws IOException, JsonProcessingException
        {
            JsonToken jsontoken = jsonparser.getCurrentToken();
            if(jsontoken == JsonToken.VALUE_NUMBER_INT || jsontoken == JsonToken.VALUE_NUMBER_FLOAT)
                return jsonparser.getDecimalValue();
            if(jsontoken == JsonToken.VALUE_STRING)
            {
                String s = jsonparser.getText().trim();
                if(s.length() == 0)
                    return null;
                BigDecimal bigdecimal;
                try
                {
                    bigdecimal = new BigDecimal(s);
                }
                catch(IllegalArgumentException illegalargumentexception)
                {
                    throw deserializationcontext.weirdStringException(s, _valueClass, "not a valid representation");
                }
                return bigdecimal;
            } else
            {
                throw deserializationcontext.mappingException(_valueClass, jsontoken);
            }
        }

        public static final BigDecimalDeserializer instance = new BigDecimalDeserializer();


        public BigDecimalDeserializer()
        {
            super(java/math/BigDecimal);
        }
    }

    public static class BigIntegerDeserializer extends StdScalarDeserializer
    {

        public volatile Object deserialize(JsonParser jsonparser, DeserializationContext deserializationcontext)
            throws IOException, JsonProcessingException
        {
            return deserialize(jsonparser, deserializationcontext);
        }

        public BigInteger deserialize(JsonParser jsonparser, DeserializationContext deserializationcontext)
            throws IOException, JsonProcessingException
        {
            JsonToken jsontoken = jsonparser.getCurrentToken();
            if(jsontoken != JsonToken.VALUE_NUMBER_INT) goto _L2; else goto _L1
_L1:
            static class _cls1
            {

                static final int $SwitchMap$com$fasterxml$jackson$core$JsonParser$NumberType[];
                static final int $SwitchMap$com$fasterxml$jackson$core$JsonToken[];

                static 
                {
                    $SwitchMap$com$fasterxml$jackson$core$JsonParser$NumberType = new int[com.fasterxml.jackson.core.JsonParser.NumberType.values().length];
                    try
                    {
                        $SwitchMap$com$fasterxml$jackson$core$JsonParser$NumberType[com.fasterxml.jackson.core.JsonParser.NumberType.INT.ordinal()] = 1;
                    }
                    catch(NoSuchFieldError nosuchfielderror) { }
                    try
                    {
                        $SwitchMap$com$fasterxml$jackson$core$JsonParser$NumberType[com.fasterxml.jackson.core.JsonParser.NumberType.LONG.ordinal()] = 2;
                    }
                    catch(NoSuchFieldError nosuchfielderror1) { }
                    $SwitchMap$com$fasterxml$jackson$core$JsonToken = new int[JsonToken.values().length];
                    try
                    {
                        $SwitchMap$com$fasterxml$jackson$core$JsonToken[JsonToken.VALUE_NUMBER_INT.ordinal()] = 1;
                    }
                    catch(NoSuchFieldError nosuchfielderror2) { }
                    try
                    {
                        $SwitchMap$com$fasterxml$jackson$core$JsonToken[JsonToken.VALUE_NUMBER_FLOAT.ordinal()] = 2;
                    }
                    catch(NoSuchFieldError nosuchfielderror3) { }
                    try
                    {
                        $SwitchMap$com$fasterxml$jackson$core$JsonToken[JsonToken.VALUE_STRING.ordinal()] = 3;
                    }
                    catch(NoSuchFieldError nosuchfielderror4)
                    {
                        return;
                    }
                }
            }

            _cls1..SwitchMap.com.fasterxml.jackson.core.JsonParser.NumberType[jsonparser.getNumberType().ordinal()];
            JVM INSTR tableswitch 1 2: default 44
        //                       1 63
        //                       2 63;
               goto _L3 _L4 _L4
_L3:
            String s;
            s = jsonparser.getText().trim();
            if(s.length() == 0)
                return null;
            break; /* Loop/switch isn't completed */
_L4:
            return BigInteger.valueOf(jsonparser.getLongValue());
_L2:
            if(jsontoken == JsonToken.VALUE_NUMBER_FLOAT)
                return jsonparser.getDecimalValue().toBigInteger();
            if(jsontoken != JsonToken.VALUE_STRING)
                throw deserializationcontext.mappingException(_valueClass, jsontoken);
            if(true) goto _L3; else goto _L5
_L5:
            BigInteger biginteger;
            try
            {
                biginteger = new BigInteger(s);
            }
            catch(IllegalArgumentException illegalargumentexception)
            {
                throw deserializationcontext.weirdStringException(s, _valueClass, "not a valid representation");
            }
            return biginteger;
        }

        public static final BigIntegerDeserializer instance = new BigIntegerDeserializer();


        public BigIntegerDeserializer()
        {
            super(java/math/BigInteger);
        }
    }

    public static final class BooleanDeserializer extends PrimitiveOrWrapperDeserializer
    {

        public Boolean deserialize(JsonParser jsonparser, DeserializationContext deserializationcontext)
            throws IOException, JsonProcessingException
        {
            return _parseBoolean(jsonparser, deserializationcontext);
        }

        public volatile Object deserialize(JsonParser jsonparser, DeserializationContext deserializationcontext)
            throws IOException, JsonProcessingException
        {
            return deserialize(jsonparser, deserializationcontext);
        }

        public Boolean deserializeWithType(JsonParser jsonparser, DeserializationContext deserializationcontext, TypeDeserializer typedeserializer)
            throws IOException, JsonProcessingException
        {
            return _parseBoolean(jsonparser, deserializationcontext);
        }

        public volatile Object deserializeWithType(JsonParser jsonparser, DeserializationContext deserializationcontext, TypeDeserializer typedeserializer)
            throws IOException, JsonProcessingException
        {
            return deserializeWithType(jsonparser, deserializationcontext, typedeserializer);
        }

        private static final BooleanDeserializer primitiveInstance;
        private static final long serialVersionUID = 1L;
        private static final BooleanDeserializer wrapperInstance;

        static 
        {
            primitiveInstance = new BooleanDeserializer(java/lang/Boolean, Boolean.FALSE);
            wrapperInstance = new BooleanDeserializer(Boolean.TYPE, null);
        }



        public BooleanDeserializer(Class class1, Boolean boolean1)
        {
            super(class1, boolean1);
        }
    }

    public static final class ByteDeserializer extends PrimitiveOrWrapperDeserializer
    {

        public Byte deserialize(JsonParser jsonparser, DeserializationContext deserializationcontext)
            throws IOException, JsonProcessingException
        {
            return _parseByte(jsonparser, deserializationcontext);
        }

        public volatile Object deserialize(JsonParser jsonparser, DeserializationContext deserializationcontext)
            throws IOException, JsonProcessingException
        {
            return deserialize(jsonparser, deserializationcontext);
        }

        private static final ByteDeserializer primitiveInstance;
        private static final long serialVersionUID = 1L;
        private static final ByteDeserializer wrapperInstance = new ByteDeserializer(java/lang/Byte, null);

        static 
        {
            primitiveInstance = new ByteDeserializer(Byte.TYPE, Byte.valueOf((byte)0));
        }



        public ByteDeserializer(Class class1, Byte byte1)
        {
            super(class1, byte1);
        }
    }

    public static final class CharacterDeserializer extends PrimitiveOrWrapperDeserializer
    {

        public Character deserialize(JsonParser jsonparser, DeserializationContext deserializationcontext)
            throws IOException, JsonProcessingException
        {
            JsonToken jsontoken = jsonparser.getCurrentToken();
            if(jsontoken == JsonToken.VALUE_NUMBER_INT)
            {
                int i = jsonparser.getIntValue();
                if(i >= 0 && i <= 65535)
                    return Character.valueOf((char)i);
            } else
            if(jsontoken == JsonToken.VALUE_STRING)
            {
                String s = jsonparser.getText();
                if(s.length() == 1)
                    return Character.valueOf(s.charAt(0));
                if(s.length() == 0)
                    return (Character)getEmptyValue();
            }
            throw deserializationcontext.mappingException(_valueClass, jsontoken);
        }

        public volatile Object deserialize(JsonParser jsonparser, DeserializationContext deserializationcontext)
            throws IOException, JsonProcessingException
        {
            return deserialize(jsonparser, deserializationcontext);
        }

        private static final CharacterDeserializer primitiveInstance = new CharacterDeserializer(java/lang/Character, Character.valueOf('\0'));
        private static final long serialVersionUID = 1L;
        private static final CharacterDeserializer wrapperInstance;

        static 
        {
            wrapperInstance = new CharacterDeserializer(Character.TYPE, null);
        }



        public CharacterDeserializer(Class class1, Character character)
        {
            super(class1, character);
        }
    }

    public static final class DoubleDeserializer extends PrimitiveOrWrapperDeserializer
    {

        public Double deserialize(JsonParser jsonparser, DeserializationContext deserializationcontext)
            throws IOException, JsonProcessingException
        {
            return _parseDouble(jsonparser, deserializationcontext);
        }

        public volatile Object deserialize(JsonParser jsonparser, DeserializationContext deserializationcontext)
            throws IOException, JsonProcessingException
        {
            return deserialize(jsonparser, deserializationcontext);
        }

        public Double deserializeWithType(JsonParser jsonparser, DeserializationContext deserializationcontext, TypeDeserializer typedeserializer)
            throws IOException, JsonProcessingException
        {
            return _parseDouble(jsonparser, deserializationcontext);
        }

        public volatile Object deserializeWithType(JsonParser jsonparser, DeserializationContext deserializationcontext, TypeDeserializer typedeserializer)
            throws IOException, JsonProcessingException
        {
            return deserializeWithType(jsonparser, deserializationcontext, typedeserializer);
        }

        private static final DoubleDeserializer primitiveInstance = new DoubleDeserializer(java/lang/Double, Double.valueOf(0.0D));
        private static final long serialVersionUID = 1L;
        private static final DoubleDeserializer wrapperInstance;

        static 
        {
            wrapperInstance = new DoubleDeserializer(Double.TYPE, null);
        }



        public DoubleDeserializer(Class class1, Double double1)
        {
            super(class1, double1);
        }
    }

    public static final class FloatDeserializer extends PrimitiveOrWrapperDeserializer
    {

        public Float deserialize(JsonParser jsonparser, DeserializationContext deserializationcontext)
            throws IOException, JsonProcessingException
        {
            return _parseFloat(jsonparser, deserializationcontext);
        }

        public volatile Object deserialize(JsonParser jsonparser, DeserializationContext deserializationcontext)
            throws IOException, JsonProcessingException
        {
            return deserialize(jsonparser, deserializationcontext);
        }

        private static final FloatDeserializer primitiveInstance = new FloatDeserializer(java/lang/Float, Float.valueOf(0.0F));
        private static final long serialVersionUID = 1L;
        private static final FloatDeserializer wrapperInstance;

        static 
        {
            wrapperInstance = new FloatDeserializer(Float.TYPE, null);
        }



        public FloatDeserializer(Class class1, Float float1)
        {
            super(class1, float1);
        }
    }

    public static final class IntegerDeserializer extends PrimitiveOrWrapperDeserializer
    {

        public Integer deserialize(JsonParser jsonparser, DeserializationContext deserializationcontext)
            throws IOException, JsonProcessingException
        {
            return _parseInteger(jsonparser, deserializationcontext);
        }

        public volatile Object deserialize(JsonParser jsonparser, DeserializationContext deserializationcontext)
            throws IOException, JsonProcessingException
        {
            return deserialize(jsonparser, deserializationcontext);
        }

        public Integer deserializeWithType(JsonParser jsonparser, DeserializationContext deserializationcontext, TypeDeserializer typedeserializer)
            throws IOException, JsonProcessingException
        {
            return _parseInteger(jsonparser, deserializationcontext);
        }

        public volatile Object deserializeWithType(JsonParser jsonparser, DeserializationContext deserializationcontext, TypeDeserializer typedeserializer)
            throws IOException, JsonProcessingException
        {
            return deserializeWithType(jsonparser, deserializationcontext, typedeserializer);
        }

        private static final IntegerDeserializer primitiveInstance = new IntegerDeserializer(java/lang/Integer, Integer.valueOf(0));
        private static final long serialVersionUID = 1L;
        private static final IntegerDeserializer wrapperInstance;

        static 
        {
            wrapperInstance = new IntegerDeserializer(Integer.TYPE, null);
        }



        public IntegerDeserializer(Class class1, Integer integer)
        {
            super(class1, integer);
        }
    }

    public static final class LongDeserializer extends PrimitiveOrWrapperDeserializer
    {

        public Long deserialize(JsonParser jsonparser, DeserializationContext deserializationcontext)
            throws IOException, JsonProcessingException
        {
            return _parseLong(jsonparser, deserializationcontext);
        }

        public volatile Object deserialize(JsonParser jsonparser, DeserializationContext deserializationcontext)
            throws IOException, JsonProcessingException
        {
            return deserialize(jsonparser, deserializationcontext);
        }

        private static final LongDeserializer primitiveInstance = new LongDeserializer(java/lang/Long, Long.valueOf(0L));
        private static final long serialVersionUID = 1L;
        private static final LongDeserializer wrapperInstance;

        static 
        {
            wrapperInstance = new LongDeserializer(Long.TYPE, null);
        }



        public LongDeserializer(Class class1, Long long1)
        {
            super(class1, long1);
        }
    }

    public static final class NumberDeserializer extends StdScalarDeserializer
    {

        public Number deserialize(JsonParser jsonparser, DeserializationContext deserializationcontext)
            throws IOException, JsonProcessingException
        {
            JsonToken jsontoken;
            String s;
label0:
            {
                jsontoken = jsonparser.getCurrentToken();
                if(jsontoken == JsonToken.VALUE_NUMBER_INT)
                    if(deserializationcontext.isEnabled(DeserializationFeature.USE_BIG_INTEGER_FOR_INTS))
                        return jsonparser.getBigIntegerValue();
                    else
                        return jsonparser.getNumberValue();
                if(jsontoken == JsonToken.VALUE_NUMBER_FLOAT)
                    if(deserializationcontext.isEnabled(DeserializationFeature.USE_BIG_DECIMAL_FOR_FLOATS))
                        return jsonparser.getDecimalValue();
                    else
                        return Double.valueOf(jsonparser.getDoubleValue());
                if(jsontoken != JsonToken.VALUE_STRING)
                    break MISSING_BLOCK_LABEL_199;
                s = jsonparser.getText().trim();
                BigDecimal bigdecimal;
                try
                {
                    if(s.indexOf('.') < 0)
                        break MISSING_BLOCK_LABEL_137;
                    if(!deserializationcontext.isEnabled(DeserializationFeature.USE_BIG_DECIMAL_FOR_FLOATS))
                        break label0;
                    bigdecimal = new BigDecimal(s);
                }
                catch(IllegalArgumentException illegalargumentexception)
                {
                    throw deserializationcontext.weirdStringException(s, _valueClass, "not a valid number");
                }
                return bigdecimal;
            }
            return new Double(s);
            long l;
            if(deserializationcontext.isEnabled(DeserializationFeature.USE_BIG_INTEGER_FOR_INTS))
                return new BigInteger(s);
            l = Long.parseLong(s);
            if(l > 0x7fffffffL || l < 0xffffffff80000000L)
                break MISSING_BLOCK_LABEL_189;
            return Integer.valueOf((int)l);
            Long long1 = Long.valueOf(l);
            return long1;
            throw deserializationcontext.mappingException(_valueClass, jsontoken);
        }

        public volatile Object deserialize(JsonParser jsonparser, DeserializationContext deserializationcontext)
            throws IOException, JsonProcessingException
        {
            return deserialize(jsonparser, deserializationcontext);
        }

        public Object deserializeWithType(JsonParser jsonparser, DeserializationContext deserializationcontext, TypeDeserializer typedeserializer)
            throws IOException, JsonProcessingException
        {
            switch(_cls1..SwitchMap.com.fasterxml.jackson.core.JsonToken[jsonparser.getCurrentToken().ordinal()])
            {
            default:
                return typedeserializer.deserializeTypedFromScalar(jsonparser, deserializationcontext);

            case 1: // '\001'
            case 2: // '\002'
            case 3: // '\003'
                return deserialize(jsonparser, deserializationcontext);
            }
        }

        public static final NumberDeserializer instance = new NumberDeserializer();


        public NumberDeserializer()
        {
            super(java/lang/Number);
        }
    }

    protected static abstract class PrimitiveOrWrapperDeserializer extends StdScalarDeserializer
    {

        public final Object getNullValue()
        {
            return _nullValue;
        }

        private static final long serialVersionUID = 1L;
        protected final Object _nullValue;

        protected PrimitiveOrWrapperDeserializer(Class class1, Object obj)
        {
            super(class1);
            _nullValue = obj;
        }
    }

    public static final class ShortDeserializer extends PrimitiveOrWrapperDeserializer
    {

        public volatile Object deserialize(JsonParser jsonparser, DeserializationContext deserializationcontext)
            throws IOException, JsonProcessingException
        {
            return deserialize(jsonparser, deserializationcontext);
        }

        public Short deserialize(JsonParser jsonparser, DeserializationContext deserializationcontext)
            throws IOException, JsonProcessingException
        {
            return _parseShort(jsonparser, deserializationcontext);
        }

        private static final ShortDeserializer primitiveInstance = new ShortDeserializer(java/lang/Short, Short.valueOf((short)0));
        private static final long serialVersionUID = 1L;
        private static final ShortDeserializer wrapperInstance;

        static 
        {
            wrapperInstance = new ShortDeserializer(Short.TYPE, null);
        }



        public ShortDeserializer(Class class1, Short short1)
        {
            super(class1, short1);
        }
    }


    public NumberDeserializers()
    {
    }

    public static StdDeserializer[] all()
    {
        StdDeserializer astddeserializer[] = new StdDeserializer[19];
        astddeserializer[0] = new BooleanDeserializer(java/lang/Boolean, null);
        astddeserializer[1] = new ByteDeserializer(java/lang/Byte, null);
        astddeserializer[2] = new ShortDeserializer(java/lang/Short, null);
        astddeserializer[3] = new CharacterDeserializer(java/lang/Character, null);
        astddeserializer[4] = new IntegerDeserializer(java/lang/Integer, null);
        astddeserializer[5] = new LongDeserializer(java/lang/Long, null);
        astddeserializer[6] = new FloatDeserializer(java/lang/Float, null);
        astddeserializer[7] = new DoubleDeserializer(java/lang/Double, null);
        astddeserializer[8] = new BooleanDeserializer(Boolean.TYPE, Boolean.FALSE);
        astddeserializer[9] = new ByteDeserializer(Byte.TYPE, Byte.valueOf((byte)0));
        astddeserializer[10] = new ShortDeserializer(Short.TYPE, Short.valueOf((short)0));
        astddeserializer[11] = new CharacterDeserializer(Character.TYPE, Character.valueOf('\0'));
        astddeserializer[12] = new IntegerDeserializer(Integer.TYPE, Integer.valueOf(0));
        astddeserializer[13] = new LongDeserializer(Long.TYPE, Long.valueOf(0L));
        astddeserializer[14] = new FloatDeserializer(Float.TYPE, Float.valueOf(0.0F));
        astddeserializer[15] = new DoubleDeserializer(Double.TYPE, Double.valueOf(0.0D));
        astddeserializer[16] = new NumberDeserializer();
        astddeserializer[17] = new BigDecimalDeserializer();
        astddeserializer[18] = new BigIntegerDeserializer();
        return astddeserializer;
    }

    public static JsonDeserializer find(Class class1, String s)
    {
        if(class1.isPrimitive())
        {
            if(class1 == Integer.TYPE)
                return IntegerDeserializer.primitiveInstance;
            if(class1 == Boolean.TYPE)
                return BooleanDeserializer.primitiveInstance;
            if(class1 == Long.TYPE)
                return LongDeserializer.primitiveInstance;
            if(class1 == Double.TYPE)
                return DoubleDeserializer.primitiveInstance;
            if(class1 == Character.TYPE)
                return CharacterDeserializer.primitiveInstance;
            if(class1 == Byte.TYPE)
                return ByteDeserializer.primitiveInstance;
            if(class1 == Short.TYPE)
                return ShortDeserializer.primitiveInstance;
            if(class1 == Float.TYPE)
                return FloatDeserializer.primitiveInstance;
        } else
        if(_classNames.contains(s))
        {
            if(class1 == java/lang/Integer)
                return IntegerDeserializer.wrapperInstance;
            if(class1 == java/lang/Boolean)
                return BooleanDeserializer.wrapperInstance;
            if(class1 == java/lang/Long)
                return LongDeserializer.wrapperInstance;
            if(class1 == java/lang/Double)
                return DoubleDeserializer.wrapperInstance;
            if(class1 == java/lang/Character)
                return CharacterDeserializer.wrapperInstance;
            if(class1 == java/lang/Byte)
                return ByteDeserializer.wrapperInstance;
            if(class1 == java/lang/Short)
                return ShortDeserializer.wrapperInstance;
            if(class1 == java/lang/Float)
                return FloatDeserializer.wrapperInstance;
            if(class1 == java/lang/Number)
                return NumberDeserializer.instance;
            if(class1 == java/math/BigDecimal)
                return BigDecimalDeserializer.instance;
            if(class1 == java/math/BigInteger)
                return BigIntegerDeserializer.instance;
        } else
        {
            return null;
        }
        throw new IllegalArgumentException((new StringBuilder()).append("Internal error: can't find deserializer for ").append(class1.getName()).toString());
    }

    private static final HashSet _classNames;

    static 
    {
        int i = 0;
        _classNames = new HashSet();
        Class aclass[] = {
            java/lang/Boolean, java/lang/Byte, java/lang/Short, java/lang/Character, java/lang/Integer, java/lang/Long, java/lang/Float, java/lang/Double, java/lang/Number, java/math/BigDecimal, 
            java/math/BigInteger
        };
        for(int j = aclass.length; i < j; i++)
        {
            Class class1 = aclass[i];
            _classNames.add(class1.getName());
        }

    }
}
