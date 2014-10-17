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

public class ShortNode extends NumericNode
{

    public ShortNode(short word0)
    {
        _value = word0;
    }

    public static ShortNode valueOf(short word0)
    {
        return new ShortNode(word0);
    }

    public boolean asBoolean(boolean flag)
    {
        return _value != 0;
    }

    public String asText()
    {
        return NumberOutput.toString(_value);
    }

    public JsonToken asToken()
    {
        return JsonToken.VALUE_NUMBER_INT;
    }

    public BigInteger bigIntegerValue()
    {
        return BigInteger.valueOf(_value);
    }

    public boolean canConvertToInt()
    {
        return true;
    }

    public boolean canConvertToLong()
    {
        return true;
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
            if(obj instanceof ShortNode)
            {
                if(((ShortNode)obj)._value != _value)
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
        return _value;
    }

    public int intValue()
    {
        return _value;
    }

    public boolean isIntegralNumber()
    {
        return true;
    }

    public boolean isShort()
    {
        return true;
    }

    public long longValue()
    {
        return (long)_value;
    }

    public com.fasterxml.jackson.core.JsonParser.NumberType numberType()
    {
        return com.fasterxml.jackson.core.JsonParser.NumberType.INT;
    }

    public Number numberValue()
    {
        return Short.valueOf(_value);
    }

    public final void serialize(JsonGenerator jsongenerator, SerializerProvider serializerprovider)
        throws IOException, JsonProcessingException
    {
        jsongenerator.writeNumber(_value);
    }

    public short shortValue()
    {
        return _value;
    }

    protected final short _value;
}
