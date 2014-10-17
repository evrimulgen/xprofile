// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.fasterxml.jackson.databind.node;

import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;

// Referenced classes of package com.fasterxml.jackson.databind.node:
//            ValueNode, JsonNodeType

public class BooleanNode extends ValueNode
{

    private BooleanNode(boolean flag)
    {
        _value = flag;
    }

    public static BooleanNode getFalse()
    {
        return FALSE;
    }

    public static BooleanNode getTrue()
    {
        return TRUE;
    }

    public static BooleanNode valueOf(boolean flag)
    {
        if(flag)
            return TRUE;
        else
            return FALSE;
    }

    public boolean asBoolean()
    {
        return _value;
    }

    public boolean asBoolean(boolean flag)
    {
        return _value;
    }

    public double asDouble(double d)
    {
        return !_value ? 0.0D : 1.0D;
    }

    public int asInt(int i)
    {
        return !_value ? 0 : 1;
    }

    public long asLong(long l)
    {
        return !_value ? 0L : 1L;
    }

    public String asText()
    {
        if(_value)
            return "true";
        else
            return "false";
    }

    public JsonToken asToken()
    {
        if(_value)
            return JsonToken.VALUE_TRUE;
        else
            return JsonToken.VALUE_FALSE;
    }

    public boolean booleanValue()
    {
        return _value;
    }

    public boolean equals(Object obj)
    {
        if(obj != this)
        {
            if(obj == null)
                return false;
            if(!(obj instanceof BooleanNode))
                return false;
            if(_value != ((BooleanNode)obj)._value)
                return false;
        }
        return true;
    }

    public JsonNodeType getNodeType()
    {
        return JsonNodeType.BOOLEAN;
    }

    public final void serialize(JsonGenerator jsongenerator, SerializerProvider serializerprovider)
        throws IOException, JsonProcessingException
    {
        jsongenerator.writeBoolean(_value);
    }

    public static final BooleanNode FALSE = new BooleanNode(false);
    public static final BooleanNode TRUE = new BooleanNode(true);
    private final boolean _value;

}
