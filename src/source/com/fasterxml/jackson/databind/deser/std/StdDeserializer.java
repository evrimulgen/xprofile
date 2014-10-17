// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.fasterxml.jackson.databind.deser.std;

import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.core.io.NumberInput;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.fasterxml.jackson.databind.util.ClassUtil;
import com.fasterxml.jackson.databind.util.Converter;
import java.io.IOException;
import java.io.Serializable;
import java.util.Date;

// Referenced classes of package com.fasterxml.jackson.databind.deser.std:
//            StdDelegatingDeserializer

public abstract class StdDeserializer extends JsonDeserializer
    implements Serializable
{

    protected StdDeserializer(JavaType javatype)
    {
        Class class1;
        if(javatype == null)
            class1 = null;
        else
            class1 = javatype.getRawClass();
        _valueClass = class1;
    }

    protected StdDeserializer(Class class1)
    {
        _valueClass = class1;
    }

    protected static final double parseDouble(String s)
        throws NumberFormatException
    {
        if("2.2250738585072012e-308".equals(s))
            return 4.9406564584124654E-324D;
        else
            return Double.parseDouble(s);
    }

    protected boolean _hasTextualNull(String s)
    {
        return "null".equals(s);
    }

    protected final Boolean _parseBoolean(JsonParser jsonparser, DeserializationContext deserializationcontext)
        throws IOException, JsonProcessingException
    {
        JsonToken jsontoken = jsonparser.getCurrentToken();
        if(jsontoken == JsonToken.VALUE_TRUE)
            return Boolean.TRUE;
        if(jsontoken == JsonToken.VALUE_FALSE)
            return Boolean.FALSE;
        if(jsontoken == JsonToken.VALUE_NUMBER_INT)
            if(jsonparser.getNumberType() == com.fasterxml.jackson.core.JsonParser.NumberType.INT)
            {
                if(jsonparser.getIntValue() == 0)
                    return Boolean.FALSE;
                else
                    return Boolean.TRUE;
            } else
            {
                return Boolean.valueOf(_parseBooleanFromNumber(jsonparser, deserializationcontext));
            }
        if(jsontoken == JsonToken.VALUE_NULL)
            return (Boolean)getNullValue();
        if(jsontoken == JsonToken.VALUE_STRING)
        {
            String s = jsonparser.getText().trim();
            if("true".equals(s))
                return Boolean.TRUE;
            if("false".equals(s))
                return Boolean.FALSE;
            if(s.length() == 0)
                return (Boolean)getEmptyValue();
            if(_hasTextualNull(s))
                return (Boolean)getNullValue();
            else
                throw deserializationcontext.weirdStringException(s, _valueClass, "only \"true\" or \"false\" recognized");
        } else
        {
            throw deserializationcontext.mappingException(_valueClass, jsontoken);
        }
    }

    protected final boolean _parseBooleanFromNumber(JsonParser jsonparser, DeserializationContext deserializationcontext)
        throws IOException, JsonProcessingException
    {
        if(jsonparser.getNumberType() == com.fasterxml.jackson.core.JsonParser.NumberType.LONG)
        {
            Boolean boolean1;
            if(jsonparser.getLongValue() == 0L)
                boolean1 = Boolean.FALSE;
            else
                boolean1 = Boolean.TRUE;
            return boolean1.booleanValue();
        }
        String s = jsonparser.getText();
        if("0.0".equals(s) || "0".equals(s))
            return Boolean.FALSE.booleanValue();
        else
            return Boolean.TRUE.booleanValue();
    }

    protected final boolean _parseBooleanPrimitive(JsonParser jsonparser, DeserializationContext deserializationcontext)
        throws IOException, JsonProcessingException
    {
        boolean flag = true;
        JsonToken jsontoken = jsonparser.getCurrentToken();
        boolean flag1;
        if(jsontoken == JsonToken.VALUE_TRUE)
        {
            flag1 = flag;
        } else
        {
            JsonToken jsontoken1 = JsonToken.VALUE_FALSE;
            flag1 = false;
            if(jsontoken != jsontoken1)
            {
                JsonToken jsontoken2 = JsonToken.VALUE_NULL;
                flag1 = false;
                if(jsontoken != jsontoken2)
                {
                    if(jsontoken == JsonToken.VALUE_NUMBER_INT)
                        if(jsonparser.getNumberType() == com.fasterxml.jackson.core.JsonParser.NumberType.INT)
                        {
                            if(jsonparser.getIntValue() == 0)
                                flag = false;
                            return flag;
                        } else
                        {
                            return _parseBooleanFromNumber(jsonparser, deserializationcontext);
                        }
                    if(jsontoken == JsonToken.VALUE_STRING)
                    {
                        String s = jsonparser.getText().trim();
                        if("true".equals(s))
                            return flag;
                        boolean flag2 = "false".equals(s);
                        flag1 = false;
                        if(!flag2)
                        {
                            int i = s.length();
                            flag1 = false;
                            if(i != 0)
                            {
                                boolean flag3 = _hasTextualNull(s);
                                flag1 = false;
                                if(!flag3)
                                    throw deserializationcontext.weirdStringException(s, _valueClass, "only \"true\" or \"false\" recognized");
                            }
                        }
                    } else
                    {
                        throw deserializationcontext.mappingException(_valueClass, jsontoken);
                    }
                }
            }
        }
        return flag1;
    }

    protected Byte _parseByte(JsonParser jsonparser, DeserializationContext deserializationcontext)
        throws IOException, JsonProcessingException
    {
        JsonToken jsontoken = jsonparser.getCurrentToken();
        if(jsontoken == JsonToken.VALUE_NUMBER_INT || jsontoken == JsonToken.VALUE_NUMBER_FLOAT)
            return Byte.valueOf(jsonparser.getByteValue());
        if(jsontoken == JsonToken.VALUE_STRING)
        {
            String s = jsonparser.getText().trim();
            if(_hasTextualNull(s))
                return (Byte)getNullValue();
            int i;
            try
            {
                if(s.length() == 0)
                    return (Byte)getEmptyValue();
                i = NumberInput.parseInt(s);
            }
            catch(IllegalArgumentException illegalargumentexception)
            {
                throw deserializationcontext.weirdStringException(s, _valueClass, "not a valid Byte value");
            }
            if(i < -128 || i > 255)
                throw deserializationcontext.weirdStringException(s, _valueClass, "overflow, value can not be represented as 8-bit value");
            else
                return Byte.valueOf((byte)i);
        }
        if(jsontoken == JsonToken.VALUE_NULL)
            return (Byte)getNullValue();
        else
            throw deserializationcontext.mappingException(_valueClass, jsontoken);
    }

    protected Date _parseDate(JsonParser jsonparser, DeserializationContext deserializationcontext)
        throws IOException, JsonProcessingException
    {
        JsonToken jsontoken = jsonparser.getCurrentToken();
        if(jsontoken == JsonToken.VALUE_NUMBER_INT)
            return new Date(jsonparser.getLongValue());
        if(jsontoken == JsonToken.VALUE_NULL)
            return (Date)getNullValue();
        if(jsontoken == JsonToken.VALUE_STRING)
        {
            String s = null;
            Date date;
            try
            {
                s = jsonparser.getText().trim();
                if(s.length() == 0)
                    return (Date)getEmptyValue();
                if(_hasTextualNull(s))
                    return (Date)getNullValue();
                date = deserializationcontext.parseDate(s);
            }
            catch(IllegalArgumentException illegalargumentexception)
            {
                throw deserializationcontext.weirdStringException(s, _valueClass, (new StringBuilder()).append("not a valid representation (error: ").append(illegalargumentexception.getMessage()).append(")").toString());
            }
            return date;
        } else
        {
            throw deserializationcontext.mappingException(_valueClass, jsontoken);
        }
    }

    protected final Double _parseDouble(JsonParser jsonparser, DeserializationContext deserializationcontext)
        throws IOException, JsonProcessingException
    {
        JsonToken jsontoken;
        jsontoken = jsonparser.getCurrentToken();
        if(jsontoken == JsonToken.VALUE_NUMBER_INT || jsontoken == JsonToken.VALUE_NUMBER_FLOAT)
            return Double.valueOf(jsonparser.getDoubleValue());
        if(jsontoken != JsonToken.VALUE_STRING) goto _L2; else goto _L1
_L1:
        String s;
        s = jsonparser.getText().trim();
        if(s.length() == 0)
            return (Double)getEmptyValue();
        if(_hasTextualNull(s))
            return (Double)getNullValue();
        s.charAt(0);
        JVM INSTR lookupswitch 3: default 116
    //                   45: 173
    //                   73: 129
    //                   78: 156;
           goto _L3 _L4 _L5 _L6
_L3:
        Double double1;
        try
        {
            double1 = Double.valueOf(parseDouble(s));
        }
        catch(IllegalArgumentException illegalargumentexception)
        {
            throw deserializationcontext.weirdStringException(s, _valueClass, "not a valid Double value");
        }
        return double1;
_L5:
        if(!"Infinity".equals(s) && !"INF".equals(s)) goto _L3; else goto _L7
_L7:
        return Double.valueOf((1.0D / 0.0D));
_L6:
        if(!"NaN".equals(s)) goto _L3; else goto _L8
_L8:
        return Double.valueOf((0.0D / 0.0D));
_L4:
        if(!"-Infinity".equals(s) && !"-INF".equals(s)) goto _L3; else goto _L9
_L9:
        return Double.valueOf((-1.0D / 0.0D));
_L2:
        if(jsontoken == JsonToken.VALUE_NULL)
            return (Double)getNullValue();
        else
            throw deserializationcontext.mappingException(_valueClass, jsontoken);
    }

    protected final double _parseDoublePrimitive(JsonParser jsonparser, DeserializationContext deserializationcontext)
        throws IOException, JsonProcessingException
    {
        double d;
        JsonToken jsontoken;
        d = 0.0D;
        jsontoken = jsonparser.getCurrentToken();
        if(jsontoken != JsonToken.VALUE_NUMBER_INT && jsontoken != JsonToken.VALUE_NUMBER_FLOAT) goto _L2; else goto _L1
_L1:
        d = jsonparser.getDoubleValue();
_L4:
        return d;
_L2:
        String s;
        if(jsontoken != JsonToken.VALUE_STRING)
            continue; /* Loop/switch isn't completed */
        s = jsonparser.getText().trim();
        if(s.length() == 0 || _hasTextualNull(s)) goto _L4; else goto _L3
_L3:
        s.charAt(0);
        JVM INSTR lookupswitch 3: default 104
    //                   45: 152
    //                   73: 114
    //                   78: 138;
           goto _L5 _L6 _L7 _L8
_L5:
        double d1;
        try
        {
            d1 = parseDouble(s);
        }
        catch(IllegalArgumentException illegalargumentexception)
        {
            throw deserializationcontext.weirdStringException(s, _valueClass, "not a valid double value");
        }
        return d1;
_L7:
        if(!"Infinity".equals(s) && !"INF".equals(s)) goto _L5; else goto _L9
_L9:
        return (1.0D / 0.0D);
_L8:
        if(!"NaN".equals(s)) goto _L5; else goto _L10
_L10:
        return (0.0D / 0.0D);
_L6:
        if(!"-Infinity".equals(s) && !"-INF".equals(s)) goto _L5; else goto _L11
_L11:
        return (-1.0D / 0.0D);
        if(jsontoken == JsonToken.VALUE_NULL) goto _L4; else goto _L12
_L12:
        throw deserializationcontext.mappingException(_valueClass, jsontoken);
    }

    protected final Float _parseFloat(JsonParser jsonparser, DeserializationContext deserializationcontext)
        throws IOException, JsonProcessingException
    {
        JsonToken jsontoken;
        jsontoken = jsonparser.getCurrentToken();
        if(jsontoken == JsonToken.VALUE_NUMBER_INT || jsontoken == JsonToken.VALUE_NUMBER_FLOAT)
            return Float.valueOf(jsonparser.getFloatValue());
        if(jsontoken != JsonToken.VALUE_STRING) goto _L2; else goto _L1
_L1:
        String s;
        s = jsonparser.getText().trim();
        if(s.length() == 0)
            return (Float)getEmptyValue();
        if(_hasTextualNull(s))
            return (Float)getNullValue();
        s.charAt(0);
        JVM INSTR lookupswitch 3: default 116
    //                   45: 173
    //                   73: 129
    //                   78: 156;
           goto _L3 _L4 _L5 _L6
_L3:
        Float float1;
        try
        {
            float1 = Float.valueOf(Float.parseFloat(s));
        }
        catch(IllegalArgumentException illegalargumentexception)
        {
            throw deserializationcontext.weirdStringException(s, _valueClass, "not a valid Float value");
        }
        return float1;
_L5:
        if(!"Infinity".equals(s) && !"INF".equals(s)) goto _L3; else goto _L7
_L7:
        return Float.valueOf((1.0F / 0.0F));
_L6:
        if(!"NaN".equals(s)) goto _L3; else goto _L8
_L8:
        return Float.valueOf((0.0F / 0.0F));
_L4:
        if(!"-Infinity".equals(s) && !"-INF".equals(s)) goto _L3; else goto _L9
_L9:
        return Float.valueOf((-1.0F / 0.0F));
_L2:
        if(jsontoken == JsonToken.VALUE_NULL)
            return (Float)getNullValue();
        else
            throw deserializationcontext.mappingException(_valueClass, jsontoken);
    }

    protected final float _parseFloatPrimitive(JsonParser jsonparser, DeserializationContext deserializationcontext)
        throws IOException, JsonProcessingException
    {
        JsonToken jsontoken = jsonparser.getCurrentToken();
        if(jsontoken != JsonToken.VALUE_NUMBER_INT && jsontoken != JsonToken.VALUE_NUMBER_FLOAT) goto _L2; else goto _L1
_L1:
        float f = jsonparser.getFloatValue();
_L13:
        return f;
_L2:
        if(jsontoken != JsonToken.VALUE_STRING) goto _L4; else goto _L3
_L3:
        String s;
        s = jsonparser.getText().trim();
        int i = s.length();
        f = 0.0F;
        if(i == 0)
            continue; /* Loop/switch isn't completed */
        boolean flag = _hasTextualNull(s);
        f = 0.0F;
        if(flag)
            continue; /* Loop/switch isn't completed */
        s.charAt(0);
        JVM INSTR lookupswitch 3: default 116
    //                   45: 164
    //                   73: 126
    //                   78: 150;
           goto _L5 _L6 _L7 _L8
_L5:
        float f1;
        try
        {
            f1 = Float.parseFloat(s);
        }
        catch(IllegalArgumentException illegalargumentexception)
        {
            throw deserializationcontext.weirdStringException(s, _valueClass, "not a valid float value");
        }
        return f1;
_L7:
        if(!"Infinity".equals(s) && !"INF".equals(s)) goto _L5; else goto _L9
_L9:
        return (1.0F / 0.0F);
_L8:
        if(!"NaN".equals(s)) goto _L5; else goto _L10
_L10:
        return (0.0F / 0.0F);
_L6:
        if(!"-Infinity".equals(s) && !"-INF".equals(s)) goto _L5; else goto _L11
_L11:
        return (-1.0F / 0.0F);
_L4:
        JsonToken jsontoken1 = JsonToken.VALUE_NULL;
        f = 0.0F;
        if(jsontoken != jsontoken1)
            throw deserializationcontext.mappingException(_valueClass, jsontoken);
        if(true) goto _L13; else goto _L12
_L12:
    }

    protected final int _parseIntPrimitive(JsonParser jsonparser, DeserializationContext deserializationcontext)
        throws IOException, JsonProcessingException
    {
        JsonToken jsontoken = jsonparser.getCurrentToken();
        if(jsontoken != JsonToken.VALUE_NUMBER_INT && jsontoken != JsonToken.VALUE_NUMBER_FLOAT) goto _L2; else goto _L1
_L1:
        int i = jsonparser.getIntValue();
_L4:
        return i;
_L2:
        String s;
        int j;
        long l;
        if(jsontoken != JsonToken.VALUE_STRING)
            break MISSING_BLOCK_LABEL_198;
        s = jsonparser.getText().trim();
        boolean flag = _hasTextualNull(s);
        i = 0;
        if(flag)
            continue; /* Loop/switch isn't completed */
        try
        {
            j = s.length();
        }
        catch(IllegalArgumentException illegalargumentexception)
        {
            throw deserializationcontext.weirdStringException(s, _valueClass, "not a valid int value");
        }
        if(j <= 9)
            break MISSING_BLOCK_LABEL_180;
        l = Long.parseLong(s);
        if(l >= 0xffffffff80000000L && l <= 0x7fffffffL)
            break MISSING_BLOCK_LABEL_176;
        throw deserializationcontext.weirdStringException(s, _valueClass, (new StringBuilder()).append("Overflow: numeric value (").append(s).append(") out of range of int (").append(0x80000000).append(" - ").append(0x7fffffff).append(")").toString());
        return (int)l;
        i = 0;
        if(j == 0)
            continue; /* Loop/switch isn't completed */
        int k = NumberInput.parseInt(s);
        return k;
        JsonToken jsontoken1 = JsonToken.VALUE_NULL;
        i = 0;
        if(jsontoken != jsontoken1)
            throw deserializationcontext.mappingException(_valueClass, jsontoken);
        if(true) goto _L4; else goto _L3
_L3:
    }

    protected final Integer _parseInteger(JsonParser jsonparser, DeserializationContext deserializationcontext)
        throws IOException, JsonProcessingException
    {
        JsonToken jsontoken;
        String s;
        int i;
        long l;
        jsontoken = jsonparser.getCurrentToken();
        if(jsontoken == JsonToken.VALUE_NUMBER_INT || jsontoken == JsonToken.VALUE_NUMBER_FLOAT)
            return Integer.valueOf(jsonparser.getIntValue());
        if(jsontoken != JsonToken.VALUE_STRING)
            break MISSING_BLOCK_LABEL_213;
        s = jsonparser.getText().trim();
        try
        {
            i = s.length();
            if(_hasTextualNull(s))
                return (Integer)getNullValue();
        }
        catch(IllegalArgumentException illegalargumentexception)
        {
            throw deserializationcontext.weirdStringException(s, _valueClass, "not a valid Integer value");
        }
        if(i <= 9)
            break MISSING_BLOCK_LABEL_187;
        l = Long.parseLong(s);
        if(l >= 0xffffffff80000000L && l <= 0x7fffffffL)
            break MISSING_BLOCK_LABEL_176;
        throw deserializationcontext.weirdStringException(s, _valueClass, (new StringBuilder()).append("Overflow: numeric value (").append(s).append(") out of range of Integer (").append(0x80000000).append(" - ").append(0x7fffffff).append(")").toString());
        int j = (int)l;
        return Integer.valueOf(j);
        if(i != 0)
            break MISSING_BLOCK_LABEL_200;
        return (Integer)getEmptyValue();
        Integer integer = Integer.valueOf(NumberInput.parseInt(s));
        return integer;
        if(jsontoken == JsonToken.VALUE_NULL)
            return (Integer)getNullValue();
        else
            throw deserializationcontext.mappingException(_valueClass, jsontoken);
    }

    protected final Long _parseLong(JsonParser jsonparser, DeserializationContext deserializationcontext)
        throws IOException, JsonProcessingException
    {
        JsonToken jsontoken = jsonparser.getCurrentToken();
        if(jsontoken == JsonToken.VALUE_NUMBER_INT || jsontoken == JsonToken.VALUE_NUMBER_FLOAT)
            return Long.valueOf(jsonparser.getLongValue());
        if(jsontoken == JsonToken.VALUE_STRING)
        {
            String s = jsonparser.getText().trim();
            if(s.length() == 0)
                return (Long)getEmptyValue();
            if(_hasTextualNull(s))
                return (Long)getNullValue();
            Long long1;
            try
            {
                long1 = Long.valueOf(NumberInput.parseLong(s));
            }
            catch(IllegalArgumentException illegalargumentexception)
            {
                throw deserializationcontext.weirdStringException(s, _valueClass, "not a valid Long value");
            }
            return long1;
        }
        if(jsontoken == JsonToken.VALUE_NULL)
            return (Long)getNullValue();
        else
            throw deserializationcontext.mappingException(_valueClass, jsontoken);
    }

    protected final long _parseLongPrimitive(JsonParser jsonparser, DeserializationContext deserializationcontext)
        throws IOException, JsonProcessingException
    {
        long l;
        JsonToken jsontoken;
        l = 0L;
        jsontoken = jsonparser.getCurrentToken();
        if(jsontoken != JsonToken.VALUE_NUMBER_INT && jsontoken != JsonToken.VALUE_NUMBER_FLOAT) goto _L2; else goto _L1
_L1:
        l = jsonparser.getLongValue();
_L4:
        return l;
_L2:
        String s;
        if(jsontoken != JsonToken.VALUE_STRING)
            continue; /* Loop/switch isn't completed */
        s = jsonparser.getText().trim();
        if(s.length() == 0 || _hasTextualNull(s)) goto _L4; else goto _L3
_L3:
        long l1;
        try
        {
            l1 = NumberInput.parseLong(s);
        }
        catch(IllegalArgumentException illegalargumentexception)
        {
            throw deserializationcontext.weirdStringException(s, _valueClass, "not a valid long value");
        }
        return l1;
        if(jsontoken == JsonToken.VALUE_NULL) goto _L4; else goto _L5
_L5:
        throw deserializationcontext.mappingException(_valueClass, jsontoken);
    }

    protected Short _parseShort(JsonParser jsonparser, DeserializationContext deserializationcontext)
        throws IOException, JsonProcessingException
    {
        JsonToken jsontoken = jsonparser.getCurrentToken();
        if(jsontoken == JsonToken.VALUE_NUMBER_INT || jsontoken == JsonToken.VALUE_NUMBER_FLOAT)
            return Short.valueOf(jsonparser.getShortValue());
        if(jsontoken == JsonToken.VALUE_STRING)
        {
            String s = jsonparser.getText().trim();
            int i;
            try
            {
                if(s.length() == 0)
                    return (Short)getEmptyValue();
                if(_hasTextualNull(s))
                    return (Short)getNullValue();
                i = NumberInput.parseInt(s);
            }
            catch(IllegalArgumentException illegalargumentexception)
            {
                throw deserializationcontext.weirdStringException(s, _valueClass, "not a valid Short value");
            }
            if(i < -32768 || i > 32767)
                throw deserializationcontext.weirdStringException(s, _valueClass, "overflow, value can not be represented as 16-bit value");
            else
                return Short.valueOf((short)i);
        }
        if(jsontoken == JsonToken.VALUE_NULL)
            return (Short)getNullValue();
        else
            throw deserializationcontext.mappingException(_valueClass, jsontoken);
    }

    protected final short _parseShortPrimitive(JsonParser jsonparser, DeserializationContext deserializationcontext)
        throws IOException, JsonProcessingException
    {
        int i = _parseIntPrimitive(jsonparser, deserializationcontext);
        if(i < -32768 || i > 32767)
            throw deserializationcontext.weirdStringException(String.valueOf(i), _valueClass, "overflow, value can not be represented as 16-bit value");
        else
            return (short)i;
    }

    protected final String _parseString(JsonParser jsonparser, DeserializationContext deserializationcontext)
        throws IOException, JsonProcessingException
    {
        String s = jsonparser.getValueAsString();
        if(s != null)
            return s;
        else
            throw deserializationcontext.mappingException(java/lang/String, jsonparser.getCurrentToken());
    }

    public Object deserializeWithType(JsonParser jsonparser, DeserializationContext deserializationcontext, TypeDeserializer typedeserializer)
        throws IOException, JsonProcessingException
    {
        return typedeserializer.deserializeTypedFromAny(jsonparser, deserializationcontext);
    }

    protected JsonDeserializer findConvertingContentDeserializer(DeserializationContext deserializationcontext, BeanProperty beanproperty, JsonDeserializer jsondeserializer)
        throws JsonMappingException
    {
        AnnotationIntrospector annotationintrospector = deserializationcontext.getAnnotationIntrospector();
        if(annotationintrospector != null && beanproperty != null)
        {
            Object obj = annotationintrospector.findDeserializationContentConverter(beanproperty.getMember());
            if(obj != null)
            {
                Converter converter = deserializationcontext.converterInstance(beanproperty.getMember(), obj);
                JavaType javatype = converter.getInputType(deserializationcontext.getTypeFactory());
                if(jsondeserializer == null)
                    jsondeserializer = deserializationcontext.findContextualValueDeserializer(javatype, beanproperty);
                jsondeserializer = new StdDelegatingDeserializer(converter, javatype, jsondeserializer);
            }
        }
        return jsondeserializer;
    }

    protected JsonDeserializer findDeserializer(DeserializationContext deserializationcontext, JavaType javatype, BeanProperty beanproperty)
        throws JsonMappingException
    {
        return deserializationcontext.findContextualValueDeserializer(javatype, beanproperty);
    }

    public final Class getValueClass()
    {
        return _valueClass;
    }

    public JavaType getValueType()
    {
        return null;
    }

    protected void handleUnknownProperty(JsonParser jsonparser, DeserializationContext deserializationcontext, Object obj, String s)
        throws IOException, JsonProcessingException
    {
        if(obj == null)
            obj = handledType();
        if(deserializationcontext.handleUnknownProperty(jsonparser, this, obj, s))
        {
            return;
        } else
        {
            deserializationcontext.reportUnknownProperty(obj, s, this);
            jsonparser.skipChildren();
            return;
        }
    }

    public Class handledType()
    {
        return _valueClass;
    }

    protected boolean isDefaultDeserializer(JsonDeserializer jsondeserializer)
    {
        return ClassUtil.isJacksonStdImpl(jsondeserializer);
    }

    protected boolean isDefaultKeyDeserializer(KeyDeserializer keydeserializer)
    {
        return ClassUtil.isJacksonStdImpl(keydeserializer);
    }

    private static final long serialVersionUID = 1L;
    protected final Class _valueClass;
}
