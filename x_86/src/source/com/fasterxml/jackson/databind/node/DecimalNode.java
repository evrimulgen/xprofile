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

public class DecimalNode extends NumericNode
{

    public DecimalNode(BigDecimal bigdecimal)
    {
        _value = bigdecimal;
    }

    public static DecimalNode valueOf(BigDecimal bigdecimal)
    {
        return new DecimalNode(bigdecimal);
    }

    public String asText()
    {
        return _value.toString();
    }

    public JsonToken asToken()
    {
        return JsonToken.VALUE_NUMBER_FLOAT;
    }

    public BigInteger bigIntegerValue()
    {
        return _value.toBigInteger();
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
        return _value;
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
                boolean flag1 = obj instanceof DecimalNode;
                flag = false;
                if(flag1)
                    return ((DecimalNode)obj)._value.equals(_value);
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

    public boolean isBigDecimal()
    {
        return true;
    }

    public boolean isFloatingPointNumber()
    {
        return true;
    }

    public long longValue()
    {
        return _value.longValue();
    }

    public com.fasterxml.jackson.core.JsonParser.NumberType numberType()
    {
        return com.fasterxml.jackson.core.JsonParser.NumberType.BIG_DECIMAL;
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

    private static final BigDecimal MAX_INTEGER = BigDecimal.valueOf(0x7fffffffL);
    private static final BigDecimal MAX_LONG = BigDecimal.valueOf(0x7fffffffffffffffL);
    private static final BigDecimal MIN_INTEGER = BigDecimal.valueOf(0xffffffff80000000L);
    private static final BigDecimal MIN_LONG = BigDecimal.valueOf(0x8000000000000000L);
    public static final DecimalNode ZERO;
    protected final BigDecimal _value;

    static 
    {
        ZERO = new DecimalNode(BigDecimal.ZERO);
    }
}
