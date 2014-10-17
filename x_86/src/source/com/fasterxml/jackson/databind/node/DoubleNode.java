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

public class DoubleNode extends NumericNode
{

    public DoubleNode(double d)
    {
        _value = d;
    }

    public static DoubleNode valueOf(double d)
    {
        return new DoubleNode(d);
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
        return _value >= -2147483648D && _value <= 2147483647D;
    }

    public boolean canConvertToLong()
    {
        return _value >= -9.2233720368547758E+018D && _value <= 9.2233720368547758E+018D;
    }

    public BigDecimal decimalValue()
    {
        return BigDecimal.valueOf(_value);
    }

    public double doubleValue()
    {
        return _value;
    }

    public boolean equals(Object obj)
    {
        if(obj != this)
        {
            if(obj == null)
                return false;
            if(obj instanceof DoubleNode)
            {
                double d = ((DoubleNode)obj)._value;
                if(Double.compare(_value, d) != 0)
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
        return (float)_value;
    }

    public int hashCode()
    {
        long l = Double.doubleToLongBits(_value);
        return (int)l ^ (int)(l >> 32);
    }

    public int intValue()
    {
        return (int)_value;
    }

    public boolean isDouble()
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
        return com.fasterxml.jackson.core.JsonParser.NumberType.DOUBLE;
    }

    public Number numberValue()
    {
        return Double.valueOf(_value);
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

    protected final double _value;
}
