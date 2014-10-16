// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.fasterxml.jackson.databind.node;

import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;

// Referenced classes of package com.fasterxml.jackson.databind.node:
//            ValueNode, JsonNodeType

public class POJONode extends ValueNode
{

    public POJONode(Object obj)
    {
        _value = obj;
    }

    protected boolean _pojoEquals(POJONode pojonode)
    {
        if(_value == null)
            return pojonode._value == null;
        else
            return _value.equals(pojonode._value);
    }

    public boolean asBoolean(boolean flag)
    {
        if(_value != null && (_value instanceof Boolean))
            flag = ((Boolean)_value).booleanValue();
        return flag;
    }

    public double asDouble(double d)
    {
        if(_value instanceof Number)
            d = ((Number)_value).doubleValue();
        return d;
    }

    public int asInt(int i)
    {
        if(_value instanceof Number)
            i = ((Number)_value).intValue();
        return i;
    }

    public long asLong(long l)
    {
        if(_value instanceof Number)
            l = ((Number)_value).longValue();
        return l;
    }

    public String asText()
    {
        if(_value == null)
            return "null";
        else
            return _value.toString();
    }

    public JsonToken asToken()
    {
        return JsonToken.VALUE_EMBEDDED_OBJECT;
    }

    public byte[] binaryValue()
        throws IOException
    {
        if(_value instanceof byte[])
            return (byte[])(byte[])_value;
        else
            return super.binaryValue();
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
                boolean flag1 = obj instanceof POJONode;
                flag = false;
                if(flag1)
                    return _pojoEquals((POJONode)obj);
            }
        }
        return flag;
    }

    public JsonNodeType getNodeType()
    {
        return JsonNodeType.POJO;
    }

    public Object getPojo()
    {
        return _value;
    }

    public int hashCode()
    {
        return _value.hashCode();
    }

    public final void serialize(JsonGenerator jsongenerator, SerializerProvider serializerprovider)
        throws IOException, JsonProcessingException
    {
        if(_value == null)
        {
            serializerprovider.defaultSerializeNull(jsongenerator);
            return;
        } else
        {
            jsongenerator.writeObject(_value);
            return;
        }
    }

    public String toString()
    {
        return String.valueOf(_value);
    }

    protected final Object _value;
}
