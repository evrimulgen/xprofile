// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.fasterxml.jackson.databind.deser.std;

import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.fasterxml.jackson.databind.util.ArrayBuilders;
import java.io.IOException;

// Referenced classes of package com.fasterxml.jackson.databind.deser.std:
//            StdDeserializer

public abstract class PrimitiveArrayDeserializers extends StdDeserializer
{
    static final class BooleanDeser extends PrimitiveArrayDeserializers
    {

        private final boolean[] handleNonArray(JsonParser jsonparser, DeserializationContext deserializationcontext)
            throws IOException, JsonProcessingException
        {
            if(jsonparser.getCurrentToken() == JsonToken.VALUE_STRING && deserializationcontext.isEnabled(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT) && jsonparser.getText().length() == 0)
                return null;
            if(!deserializationcontext.isEnabled(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY))
            {
                throw deserializationcontext.mappingException(_valueClass);
            } else
            {
                boolean aflag[] = new boolean[1];
                aflag[0] = _parseBooleanPrimitive(jsonparser, deserializationcontext);
                return aflag;
            }
        }

        public volatile Object deserialize(JsonParser jsonparser, DeserializationContext deserializationcontext)
            throws IOException, JsonProcessingException
        {
            return deserialize(jsonparser, deserializationcontext);
        }

        public boolean[] deserialize(JsonParser jsonparser, DeserializationContext deserializationcontext)
            throws IOException, JsonProcessingException
        {
            if(!jsonparser.isExpectedStartArrayToken())
                return handleNonArray(jsonparser, deserializationcontext);
            com.fasterxml.jackson.databind.util.ArrayBuilders.BooleanBuilder booleanbuilder = deserializationcontext.getArrayBuilders().getBooleanBuilder();
            boolean aflag[] = (boolean[])booleanbuilder.resetAndStart();
            int i = 0;
            while(jsonparser.nextToken() != JsonToken.END_ARRAY) 
            {
                boolean flag = _parseBooleanPrimitive(jsonparser, deserializationcontext);
                int j;
                if(i >= aflag.length)
                {
                    boolean aflag1[] = (boolean[])booleanbuilder.appendCompletedChunk(aflag, i);
                    j = 0;
                    aflag = aflag1;
                } else
                {
                    j = i;
                }
                i = j + 1;
                aflag[j] = flag;
            }
            return (boolean[])booleanbuilder.completeAndClearBuffer(aflag, i);
        }

        private static final long serialVersionUID = 1L;

        public BooleanDeser()
        {
            super([Z);
        }
    }

    static final class ByteDeser extends PrimitiveArrayDeserializers
    {

        private final byte[] handleNonArray(JsonParser jsonparser, DeserializationContext deserializationcontext)
            throws IOException, JsonProcessingException
        {
            if(jsonparser.getCurrentToken() == JsonToken.VALUE_STRING && deserializationcontext.isEnabled(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT) && jsonparser.getText().length() == 0)
                return null;
            if(!deserializationcontext.isEnabled(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY))
                throw deserializationcontext.mappingException(_valueClass);
            JsonToken jsontoken = jsonparser.getCurrentToken();
            byte byte0;
            if(jsontoken == JsonToken.VALUE_NUMBER_INT || jsontoken == JsonToken.VALUE_NUMBER_FLOAT)
            {
                byte0 = jsonparser.getByteValue();
            } else
            {
                if(jsontoken != JsonToken.VALUE_NULL)
                    throw deserializationcontext.mappingException(_valueClass.getComponentType());
                byte0 = 0;
            }
            return (new byte[] {
                byte0
            });
        }

        public volatile Object deserialize(JsonParser jsonparser, DeserializationContext deserializationcontext)
            throws IOException, JsonProcessingException
        {
            return deserialize(jsonparser, deserializationcontext);
        }

        public byte[] deserialize(JsonParser jsonparser, DeserializationContext deserializationcontext)
            throws IOException, JsonProcessingException
        {
            JsonToken jsontoken = jsonparser.getCurrentToken();
            if(jsontoken == JsonToken.VALUE_STRING)
                return jsonparser.getBinaryValue(deserializationcontext.getBase64Variant());
            if(jsontoken == JsonToken.VALUE_EMBEDDED_OBJECT)
            {
                Object obj = jsonparser.getEmbeddedObject();
                if(obj == null)
                    return null;
                if(obj instanceof byte[])
                    return (byte[])(byte[])obj;
            }
            if(!jsonparser.isExpectedStartArrayToken())
                return handleNonArray(jsonparser, deserializationcontext);
            com.fasterxml.jackson.databind.util.ArrayBuilders.ByteBuilder bytebuilder = deserializationcontext.getArrayBuilders().getByteBuilder();
            byte abyte0[] = (byte[])bytebuilder.resetAndStart();
            int i = 0;
            do
            {
                JsonToken jsontoken1 = jsonparser.nextToken();
                if(jsontoken1 != JsonToken.END_ARRAY)
                {
                    byte byte0;
                    int j;
                    if(jsontoken1 == JsonToken.VALUE_NUMBER_INT || jsontoken1 == JsonToken.VALUE_NUMBER_FLOAT)
                    {
                        byte0 = jsonparser.getByteValue();
                    } else
                    {
                        if(jsontoken1 != JsonToken.VALUE_NULL)
                            throw deserializationcontext.mappingException(_valueClass.getComponentType());
                        byte0 = 0;
                    }
                    if(i >= abyte0.length)
                    {
                        byte abyte1[] = (byte[])bytebuilder.appendCompletedChunk(abyte0, i);
                        j = 0;
                        abyte0 = abyte1;
                    } else
                    {
                        j = i;
                    }
                    i = j + 1;
                    abyte0[j] = byte0;
                } else
                {
                    return (byte[])bytebuilder.completeAndClearBuffer(abyte0, i);
                }
            } while(true);
        }

        private static final long serialVersionUID = 1L;

        public ByteDeser()
        {
            super([B);
        }
    }

    static final class CharDeser extends PrimitiveArrayDeserializers
    {

        public volatile Object deserialize(JsonParser jsonparser, DeserializationContext deserializationcontext)
            throws IOException, JsonProcessingException
        {
            return deserialize(jsonparser, deserializationcontext);
        }

        public char[] deserialize(JsonParser jsonparser, DeserializationContext deserializationcontext)
            throws IOException, JsonProcessingException
        {
            JsonToken jsontoken = jsonparser.getCurrentToken();
            if(jsontoken == JsonToken.VALUE_STRING)
            {
                char ac[] = jsonparser.getTextCharacters();
                int i = jsonparser.getTextOffset();
                int j = jsonparser.getTextLength();
                char ac1[] = new char[j];
                System.arraycopy(ac, i, ac1, 0, j);
                return ac1;
            }
            if(jsonparser.isExpectedStartArrayToken())
            {
                StringBuilder stringbuilder = new StringBuilder(64);
                do
                {
                    JsonToken jsontoken1 = jsonparser.nextToken();
                    if(jsontoken1 != JsonToken.END_ARRAY)
                    {
                        if(jsontoken1 != JsonToken.VALUE_STRING)
                            throw deserializationcontext.mappingException(Character.TYPE);
                        String s = jsonparser.getText();
                        if(s.length() != 1)
                            throw JsonMappingException.from(jsonparser, (new StringBuilder()).append("Can not convert a JSON String of length ").append(s.length()).append(" into a char element of char array").toString());
                        stringbuilder.append(s.charAt(0));
                    } else
                    {
                        return stringbuilder.toString().toCharArray();
                    }
                } while(true);
            }
            if(jsontoken == JsonToken.VALUE_EMBEDDED_OBJECT)
            {
                Object obj = jsonparser.getEmbeddedObject();
                if(obj == null)
                    return null;
                if(obj instanceof char[])
                    return (char[])(char[])obj;
                if(obj instanceof String)
                    return ((String)obj).toCharArray();
                if(obj instanceof byte[])
                    return Base64Variants.getDefaultVariant().encode((byte[])(byte[])obj, false).toCharArray();
            }
            throw deserializationcontext.mappingException(_valueClass);
        }

        private static final long serialVersionUID = 1L;

        public CharDeser()
        {
            super([C);
        }
    }

    static final class DoubleDeser extends PrimitiveArrayDeserializers
    {

        private final double[] handleNonArray(JsonParser jsonparser, DeserializationContext deserializationcontext)
            throws IOException, JsonProcessingException
        {
            if(jsonparser.getCurrentToken() == JsonToken.VALUE_STRING && deserializationcontext.isEnabled(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT) && jsonparser.getText().length() == 0)
                return null;
            if(!deserializationcontext.isEnabled(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY))
            {
                throw deserializationcontext.mappingException(_valueClass);
            } else
            {
                double ad[] = new double[1];
                ad[0] = _parseDoublePrimitive(jsonparser, deserializationcontext);
                return ad;
            }
        }

        public volatile Object deserialize(JsonParser jsonparser, DeserializationContext deserializationcontext)
            throws IOException, JsonProcessingException
        {
            return deserialize(jsonparser, deserializationcontext);
        }

        public double[] deserialize(JsonParser jsonparser, DeserializationContext deserializationcontext)
            throws IOException, JsonProcessingException
        {
            if(!jsonparser.isExpectedStartArrayToken())
                return handleNonArray(jsonparser, deserializationcontext);
            com.fasterxml.jackson.databind.util.ArrayBuilders.DoubleBuilder doublebuilder = deserializationcontext.getArrayBuilders().getDoubleBuilder();
            double ad[] = (double[])doublebuilder.resetAndStart();
            int i = 0;
            while(jsonparser.nextToken() != JsonToken.END_ARRAY) 
            {
                double d = _parseDoublePrimitive(jsonparser, deserializationcontext);
                int j;
                if(i >= ad.length)
                {
                    double ad1[] = (double[])doublebuilder.appendCompletedChunk(ad, i);
                    j = 0;
                    ad = ad1;
                } else
                {
                    j = i;
                }
                i = j + 1;
                ad[j] = d;
            }
            return (double[])doublebuilder.completeAndClearBuffer(ad, i);
        }

        private static final long serialVersionUID = 1L;

        public DoubleDeser()
        {
            super([D);
        }
    }

    static final class FloatDeser extends PrimitiveArrayDeserializers
    {

        private final float[] handleNonArray(JsonParser jsonparser, DeserializationContext deserializationcontext)
            throws IOException, JsonProcessingException
        {
            if(jsonparser.getCurrentToken() == JsonToken.VALUE_STRING && deserializationcontext.isEnabled(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT) && jsonparser.getText().length() == 0)
                return null;
            if(!deserializationcontext.isEnabled(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY))
            {
                throw deserializationcontext.mappingException(_valueClass);
            } else
            {
                float af[] = new float[1];
                af[0] = _parseFloatPrimitive(jsonparser, deserializationcontext);
                return af;
            }
        }

        public volatile Object deserialize(JsonParser jsonparser, DeserializationContext deserializationcontext)
            throws IOException, JsonProcessingException
        {
            return deserialize(jsonparser, deserializationcontext);
        }

        public float[] deserialize(JsonParser jsonparser, DeserializationContext deserializationcontext)
            throws IOException, JsonProcessingException
        {
            if(!jsonparser.isExpectedStartArrayToken())
                return handleNonArray(jsonparser, deserializationcontext);
            com.fasterxml.jackson.databind.util.ArrayBuilders.FloatBuilder floatbuilder = deserializationcontext.getArrayBuilders().getFloatBuilder();
            float af[] = (float[])floatbuilder.resetAndStart();
            int i = 0;
            while(jsonparser.nextToken() != JsonToken.END_ARRAY) 
            {
                float f = _parseFloatPrimitive(jsonparser, deserializationcontext);
                int j;
                if(i >= af.length)
                {
                    float af1[] = (float[])floatbuilder.appendCompletedChunk(af, i);
                    j = 0;
                    af = af1;
                } else
                {
                    j = i;
                }
                i = j + 1;
                af[j] = f;
            }
            return (float[])floatbuilder.completeAndClearBuffer(af, i);
        }

        private static final long serialVersionUID = 1L;

        public FloatDeser()
        {
            super([F);
        }
    }

    static final class IntDeser extends PrimitiveArrayDeserializers
    {

        private final int[] handleNonArray(JsonParser jsonparser, DeserializationContext deserializationcontext)
            throws IOException, JsonProcessingException
        {
            if(jsonparser.getCurrentToken() == JsonToken.VALUE_STRING && deserializationcontext.isEnabled(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT) && jsonparser.getText().length() == 0)
                return null;
            if(!deserializationcontext.isEnabled(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY))
            {
                throw deserializationcontext.mappingException(_valueClass);
            } else
            {
                int ai[] = new int[1];
                ai[0] = _parseIntPrimitive(jsonparser, deserializationcontext);
                return ai;
            }
        }

        public volatile Object deserialize(JsonParser jsonparser, DeserializationContext deserializationcontext)
            throws IOException, JsonProcessingException
        {
            return deserialize(jsonparser, deserializationcontext);
        }

        public int[] deserialize(JsonParser jsonparser, DeserializationContext deserializationcontext)
            throws IOException, JsonProcessingException
        {
            if(!jsonparser.isExpectedStartArrayToken())
                return handleNonArray(jsonparser, deserializationcontext);
            com.fasterxml.jackson.databind.util.ArrayBuilders.IntBuilder intbuilder = deserializationcontext.getArrayBuilders().getIntBuilder();
            int ai[] = (int[])intbuilder.resetAndStart();
            int i = 0;
            while(jsonparser.nextToken() != JsonToken.END_ARRAY) 
            {
                int j = _parseIntPrimitive(jsonparser, deserializationcontext);
                int k;
                if(i >= ai.length)
                {
                    int ai1[] = (int[])intbuilder.appendCompletedChunk(ai, i);
                    k = 0;
                    ai = ai1;
                } else
                {
                    k = i;
                }
                i = k + 1;
                ai[k] = j;
            }
            return (int[])intbuilder.completeAndClearBuffer(ai, i);
        }

        public static final IntDeser instance = new IntDeser();
        private static final long serialVersionUID = 1L;


        public IntDeser()
        {
            super([I);
        }
    }

    static final class LongDeser extends PrimitiveArrayDeserializers
    {

        private final long[] handleNonArray(JsonParser jsonparser, DeserializationContext deserializationcontext)
            throws IOException, JsonProcessingException
        {
            if(jsonparser.getCurrentToken() == JsonToken.VALUE_STRING && deserializationcontext.isEnabled(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT) && jsonparser.getText().length() == 0)
                return null;
            if(!deserializationcontext.isEnabled(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY))
            {
                throw deserializationcontext.mappingException(_valueClass);
            } else
            {
                long al[] = new long[1];
                al[0] = _parseLongPrimitive(jsonparser, deserializationcontext);
                return al;
            }
        }

        public volatile Object deserialize(JsonParser jsonparser, DeserializationContext deserializationcontext)
            throws IOException, JsonProcessingException
        {
            return deserialize(jsonparser, deserializationcontext);
        }

        public long[] deserialize(JsonParser jsonparser, DeserializationContext deserializationcontext)
            throws IOException, JsonProcessingException
        {
            if(!jsonparser.isExpectedStartArrayToken())
                return handleNonArray(jsonparser, deserializationcontext);
            com.fasterxml.jackson.databind.util.ArrayBuilders.LongBuilder longbuilder = deserializationcontext.getArrayBuilders().getLongBuilder();
            long al[] = (long[])longbuilder.resetAndStart();
            int i = 0;
            while(jsonparser.nextToken() != JsonToken.END_ARRAY) 
            {
                long l = _parseLongPrimitive(jsonparser, deserializationcontext);
                int j;
                if(i >= al.length)
                {
                    long al1[] = (long[])longbuilder.appendCompletedChunk(al, i);
                    j = 0;
                    al = al1;
                } else
                {
                    j = i;
                }
                i = j + 1;
                al[j] = l;
            }
            return (long[])longbuilder.completeAndClearBuffer(al, i);
        }

        public static final LongDeser instance = new LongDeser();
        private static final long serialVersionUID = 1L;


        public LongDeser()
        {
            super([J);
        }
    }

    static final class ShortDeser extends PrimitiveArrayDeserializers
    {

        private final short[] handleNonArray(JsonParser jsonparser, DeserializationContext deserializationcontext)
            throws IOException, JsonProcessingException
        {
            if(jsonparser.getCurrentToken() == JsonToken.VALUE_STRING && deserializationcontext.isEnabled(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT) && jsonparser.getText().length() == 0)
                return null;
            if(!deserializationcontext.isEnabled(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY))
            {
                throw deserializationcontext.mappingException(_valueClass);
            } else
            {
                short aword0[] = new short[1];
                aword0[0] = _parseShortPrimitive(jsonparser, deserializationcontext);
                return aword0;
            }
        }

        public volatile Object deserialize(JsonParser jsonparser, DeserializationContext deserializationcontext)
            throws IOException, JsonProcessingException
        {
            return deserialize(jsonparser, deserializationcontext);
        }

        public short[] deserialize(JsonParser jsonparser, DeserializationContext deserializationcontext)
            throws IOException, JsonProcessingException
        {
            if(!jsonparser.isExpectedStartArrayToken())
                return handleNonArray(jsonparser, deserializationcontext);
            com.fasterxml.jackson.databind.util.ArrayBuilders.ShortBuilder shortbuilder = deserializationcontext.getArrayBuilders().getShortBuilder();
            short aword0[] = (short[])shortbuilder.resetAndStart();
            int i = 0;
            while(jsonparser.nextToken() != JsonToken.END_ARRAY) 
            {
                short word0 = _parseShortPrimitive(jsonparser, deserializationcontext);
                int j;
                if(i >= aword0.length)
                {
                    short aword1[] = (short[])shortbuilder.appendCompletedChunk(aword0, i);
                    j = 0;
                    aword0 = aword1;
                } else
                {
                    j = i;
                }
                i = j + 1;
                aword0[j] = word0;
            }
            return (short[])shortbuilder.completeAndClearBuffer(aword0, i);
        }

        private static final long serialVersionUID = 1L;

        public ShortDeser()
        {
            super([S);
        }
    }


    protected PrimitiveArrayDeserializers(Class class1)
    {
        super(class1);
    }

    public static JsonDeserializer forType(Class class1)
    {
        if(class1 == Integer.TYPE)
            return IntDeser.instance;
        if(class1 == Long.TYPE)
            return LongDeser.instance;
        if(class1 == Byte.TYPE)
            return new ByteDeser();
        if(class1 == Short.TYPE)
            return new ShortDeser();
        if(class1 == Float.TYPE)
            return new FloatDeser();
        if(class1 == Double.TYPE)
            return new DoubleDeser();
        if(class1 == Boolean.TYPE)
            return new BooleanDeser();
        if(class1 == Character.TYPE)
            return new CharDeser();
        else
            throw new IllegalStateException();
    }

    public Object deserializeWithType(JsonParser jsonparser, DeserializationContext deserializationcontext, TypeDeserializer typedeserializer)
        throws IOException, JsonProcessingException
    {
        return typedeserializer.deserializeTypedFromArray(jsonparser, deserializationcontext);
    }
}
