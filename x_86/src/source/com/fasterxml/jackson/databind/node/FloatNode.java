// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.fasterxml.jackson.databind.node;

import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.core.io.NumberOutput;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;

// Referenced classes of package com.fasterxml.jackson.databind.node:
//            NumericNode

public class FloatNode extends NumericNode
{

    public FloatNode(float f)
    {
        _value = f;
    }

    public static FloatNode valueOf(float f)
    {
        return new FloatNode(f);
    }

    public String asText()
    {
        return NumberOutput.toString(_value);
    }

    public JsonToken asToken()
    {
        return JsonToken.VALUE_NUMBER_FLOAT;
    }

    public BigInteger bigIntegerValue()
    {
        return decimalValue().toBigInteger();
    }

    public boolean canConvertToInt()
    {
        return _value >= -2.147484E+009F && _value <= 2.147484E+009F;
    }

    public boolean canConvertToLong()
    {
        return _value >= -9.223372E+018F && _value <= 9.223372E+018F;
    }

    public BigDecimal decimalValue()
    {
        return BigDecimal.valueOf(_value);
    }

    public double doubleValue()
    {
        return (double)_value;
    }

    public boolean equals(Object obj)
    {
        if(obj != this)
        {
            if(obj == null)
                return false;
            if(obj instanceof FloatNode)
            {
                float f = ((FloatNode)obj)._value;
                if(Float.compare(_value, f) != 0)
                    return false;
            } else
            {
                return false;
            }
        }
        return true;
    }

    public float floatValue()
    {
        return _value;
    }

    public int hashCode()
    {
        return Float.floatToIntBits(_value);
    }

    public int intValue()
    {
        return (int)_value;
    }

    public boolean isFloat()
    {
        return true;
    }

    public boolean isFloatingPointNumber()
    {
        return true;
    }

    public long longValue()
    {
        return (long)_value;
    }

    public com.fasterxml.jackson.core.JsonParser.NumberType numberType()
    {
        return com.fasterxml.jackson.core.JsonParser.NumberType.FLOAT;
    }

    public Number numberValue()
    {
        return Float.valueOf(_value);
    }

    public final void serialize(JsonGenerator jsongenerator, SerializerProvider serializerprovider)
        throws IOException, JsonProcessingException
    {
        jsongenerator.writeNumber(_value);
    }

    public short shortValue()
    {
        return (short)(int)_value;
    }

    protected final float _value;
}
