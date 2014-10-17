// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.fasterxml.jackson.databind.node;

import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;

// Referenced classes of package com.fasterxml.jackson.databind.node:
//            NumericNode

public class BigIntegerNode extends NumericNode
{

    public BigIntegerNode(BigInteger biginteger)
    {
        _value = biginteger;
    }

    public static BigIntegerNode valueOf(BigInteger biginteger)
    {
        return new BigIntegerNode(biginteger);
    }

    public boolean asBoolean(boolean flag)
    {
        return !BigInteger.ZERO.equals(_value);
    }

    public String asText()
    {
        return _value.toString();
    }

    public JsonToken asToken()
    {
        return JsonToken.VALUE_NUMBER_INT;
    }

    public BigInteger bigIntegerValue()
    {
        return _value;
    }

    public boolean canConvertToInt()
    {
        return _value.compareTo(MIN_INTEGER) >= 0 && _value.compareTo(MAX_INTEGER) <= 0;
    }

    public boolean canConvertToLong()
    {
        return _value.compareTo(MIN_LONG) >= 0 && _value.compareTo(MAX_LONG) <= 0;
    }

    public BigDecimal decimalValue()
    {
        return new BigDecimal(_value);
    }

    public double doubleValue()
    {
        return _value.doubleValue();
    }

    public boolean equals(Object obj)
    {
        boolean flag;
        if(obj == this)
        {
            flag = true;
        } else
        {
            flag = false;
            if(obj != null)
            {
                boolean flag1 = obj instanceof BigIntegerNode;
                flag = false;
                if(flag1)
                    return ((BigIntegerNode)obj)._value.equals(_value);
            }
        }
        return flag;
    }

    public float floatValue()
    {
        return _value.floatValue();
    }

    public int hashCode()
    {
        return _value.hashCode();
    }

    public int intValue()
    {
        return _value.intValue();
    }

    public boolean isBigInteger()
    {
        return true;
    }

    public boolean isIntegralNumber()
    {
        return true;
    }

    public long longValue()
    {
        return _value.longValue();
    }

    public com.fasterxml.jackson.core.JsonParser.NumberType numberType()
    {
        return com.fasterxml.jackson.core.JsonParser.NumberType.BIG_INTEGER;
    }

    public Number numberValue()
    {
        return _value;
    }

    public final void serialize(JsonGenerator jsongenerator, SerializerProvider serializerprovider)
        throws IOException, JsonProcessingException
    {
        jsongenerator.writeNumber(_value);
    }

    public short shortValue()
    {
        return _value.shortValue();
    }

    private static final BigInteger MAX_INTEGER = BigInteger.valueOf(0x7fffffffL);
    private static final BigInteger MAX_LONG = BigInteger.valueOf(0x7fffffffffffffffL);
    private static final BigInteger MIN_INTEGER = BigInteger.valueOf(0xffffffff80000000L);
    private static final BigInteger MIN_LONG = BigInteger.valueOf(0x8000000000000000L);
    protected final BigInteger _value;

}
