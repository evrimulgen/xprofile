// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.fasterxml.jackson.databind.node;

import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;
import java.util.Arrays;

// Referenced classes of package com.fasterxml.jackson.databind.node:
//            ValueNode, JsonNodeType

public class BinaryNode extends ValueNode
{

    public BinaryNode(byte abyte0[])
    {
        _data = abyte0;
    }

    public BinaryNode(byte abyte0[], int i, int j)
    {
        if(i == 0 && j == abyte0.length)
        {
            _data = abyte0;
            return;
        } else
        {
            _data = new byte[j];
            System.arraycopy(abyte0, i, _data, 0, j);
            return;
        }
    }

    public static BinaryNode valueOf(byte abyte0[])
    {
        if(abyte0 == null)
            return null;
        if(abyte0.length == 0)
            return EMPTY_BINARY_NODE;
        else
            return new BinaryNode(abyte0);
    }

    public static BinaryNode valueOf(byte abyte0[], int i, int j)
    {
        if(abyte0 == null)
            return null;
        if(j == 0)
            return EMPTY_BINARY_NODE;
        else
            return new BinaryNode(abyte0, i, j);
    }

    public String asText()
    {
        return Base64Variants.getDefaultVariant().encode(_data, false);
    }

    public JsonToken asToken()
    {
        return JsonToken.VALUE_EMBEDDED_OBJECT;
    }

    public byte[] binaryValue()
    {
        return _data;
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
                boolean flag1 = obj instanceof BinaryNode;
                flag = false;
                if(flag1)
                    return Arrays.equals(((BinaryNode)obj)._data, _data);
            }
        }
        return flag;
    }

    public JsonNodeType getNodeType()
    {
        return JsonNodeType.BINARY;
    }

    public int hashCode()
    {
        if(_data == null)
            return -1;
        else
            return _data.length;
    }

    public final void serialize(JsonGenerator jsongenerator, SerializerProvider serializerprovider)
        throws IOException, JsonProcessingException
    {
        jsongenerator.writeBinary(serializerprovider.getConfig().getBase64Variant(), _data, 0, _data.length);
    }

    public String toString()
    {
        return Base64Variants.getDefaultVariant().encode(_data, true);
    }

    static final BinaryNode EMPTY_BINARY_NODE = new BinaryNode(new byte[0]);
    protected final byte _data[];

}
