// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.fasterxml.jackson.databind;

import com.fasterxml.jackson.core.JsonPointer;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.node.JsonNodeType;
import com.fasterxml.jackson.databind.node.MissingNode;
import com.fasterxml.jackson.databind.util.EmptyIterator;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;

public abstract class JsonNode
    implements TreeNode, Iterable
{

    protected JsonNode()
    {
    }

    protected abstract JsonNode _at(JsonPointer jsonpointer);

    public boolean asBoolean()
    {
        return asBoolean(false);
    }

    public boolean asBoolean(boolean flag)
    {
        return flag;
    }

    public double asDouble()
    {
        return asDouble(0.0D);
    }

    public double asDouble(double d)
    {
        return d;
    }

    public int asInt()
    {
        return asInt(0);
    }

    public int asInt(int i)
    {
        return i;
    }

    public long asLong()
    {
        return asLong(0L);
    }

    public long asLong(long l)
    {
        return l;
    }

    public abstract String asText();

    public volatile TreeNode at(JsonPointer jsonpointer)
    {
        return at(jsonpointer);
    }

    public volatile TreeNode at(String s)
        throws IllegalArgumentException
    {
        return at(s);
    }

    public final JsonNode at(JsonPointer jsonpointer)
    {
        if(jsonpointer.matches())
            return this;
        JsonNode jsonnode = _at(jsonpointer);
        if(jsonnode == null)
            return MissingNode.getInstance();
        else
            return jsonnode.at(jsonpointer.tail());
    }

    public final JsonNode at(String s)
    {
        return at(JsonPointer.compile(s));
    }

    public BigInteger bigIntegerValue()
    {
        return BigInteger.ZERO;
    }

    public byte[] binaryValue()
        throws IOException
    {
        return null;
    }

    public boolean booleanValue()
    {
        return false;
    }

    public boolean canConvertToInt()
    {
        return false;
    }

    public boolean canConvertToLong()
    {
        return false;
    }

    public BigDecimal decimalValue()
    {
        return BigDecimal.ZERO;
    }

    public abstract JsonNode deepCopy();

    public double doubleValue()
    {
        return 0.0D;
    }

    public Iterator elements()
    {
        return EmptyIterator.instance();
    }

    public abstract boolean equals(Object obj);

    public Iterator fieldNames()
    {
        return EmptyIterator.instance();
    }

    public Iterator fields()
    {
        return EmptyIterator.instance();
    }

    public abstract JsonNode findParent(String s);

    public final List findParents(String s)
    {
        List list = findParents(s, null);
        if(list == null)
            list = Collections.emptyList();
        return list;
    }

    public abstract List findParents(String s, List list);

    public abstract JsonNode findPath(String s);

    public abstract JsonNode findValue(String s);

    public final List findValues(String s)
    {
        List list = findValues(s, null);
        if(list == null)
            list = Collections.emptyList();
        return list;
    }

    public abstract List findValues(String s, List list);

    public final List findValuesAsText(String s)
    {
        List list = findValuesAsText(s, null);
        if(list == null)
            list = Collections.emptyList();
        return list;
    }

    public abstract List findValuesAsText(String s, List list);

    public float floatValue()
    {
        return 0.0F;
    }

    public volatile TreeNode get(int i)
    {
        return get(i);
    }

    public volatile TreeNode get(String s)
    {
        return get(s);
    }

    public abstract JsonNode get(int i);

    public JsonNode get(String s)
    {
        return null;
    }

    public abstract JsonNodeType getNodeType();

    public boolean has(int i)
    {
        return get(i) != null;
    }

    public boolean has(String s)
    {
        return get(s) != null;
    }

    public boolean hasNonNull(int i)
    {
        JsonNode jsonnode = get(i);
        return jsonnode != null && !jsonnode.isNull();
    }

    public boolean hasNonNull(String s)
    {
        JsonNode jsonnode = get(s);
        return jsonnode != null && !jsonnode.isNull();
    }

    public int intValue()
    {
        return 0;
    }

    public final boolean isArray()
    {
        return getNodeType() == JsonNodeType.ARRAY;
    }

    public boolean isBigDecimal()
    {
        return false;
    }

    public boolean isBigInteger()
    {
        return false;
    }

    public final boolean isBinary()
    {
        return getNodeType() == JsonNodeType.BINARY;
    }

    public final boolean isBoolean()
    {
        return getNodeType() == JsonNodeType.BOOLEAN;
    }

    public final boolean isContainerNode()
    {
        JsonNodeType jsonnodetype = getNodeType();
        return jsonnodetype == JsonNodeType.OBJECT || jsonnodetype == JsonNodeType.ARRAY;
    }

    public boolean isDouble()
    {
        return false;
    }

    public boolean isFloat()
    {
        return false;
    }

    public boolean isFloatingPointNumber()
    {
        return false;
    }

    public boolean isInt()
    {
        return false;
    }

    public boolean isIntegralNumber()
    {
        return false;
    }

    public boolean isLong()
    {
        return false;
    }

    public final boolean isMissingNode()
    {
        return getNodeType() == JsonNodeType.MISSING;
    }

    public final boolean isNull()
    {
        return getNodeType() == JsonNodeType.NULL;
    }

    public final boolean isNumber()
    {
        return getNodeType() == JsonNodeType.NUMBER;
    }

    public final boolean isObject()
    {
        return getNodeType() == JsonNodeType.OBJECT;
    }

    public final boolean isPojo()
    {
        return getNodeType() == JsonNodeType.POJO;
    }

    public boolean isShort()
    {
        return false;
    }

    public final boolean isTextual()
    {
        return getNodeType() == JsonNodeType.STRING;
    }

    public final boolean isValueNode()
    {
        static class _cls1
        {

            static final int $SwitchMap$com$fasterxml$jackson$databind$node$JsonNodeType[];

            static 
            {
                $SwitchMap$com$fasterxml$jackson$databind$node$JsonNodeType = new int[JsonNodeType.values().length];
                try
                {
                    $SwitchMap$com$fasterxml$jackson$databind$node$JsonNodeType[JsonNodeType.ARRAY.ordinal()] = 1;
                }
                catch(NoSuchFieldError nosuchfielderror) { }
                try
                {
                    $SwitchMap$com$fasterxml$jackson$databind$node$JsonNodeType[JsonNodeType.OBJECT.ordinal()] = 2;
                }
                catch(NoSuchFieldError nosuchfielderror1) { }
                try
                {
                    $SwitchMap$com$fasterxml$jackson$databind$node$JsonNodeType[JsonNodeType.MISSING.ordinal()] = 3;
                }
                catch(NoSuchFieldError nosuchfielderror2)
                {
                    return;
                }
            }
        }

        switch(_cls1..SwitchMap.com.fasterxml.jackson.databind.node.JsonNodeType[getNodeType().ordinal()])
        {
        default:
            return true;

        case 1: // '\001'
        case 2: // '\002'
        case 3: // '\003'
            return false;
        }
    }

    public final Iterator iterator()
    {
        return elements();
    }

    public long longValue()
    {
        return 0L;
    }

    public Number numberValue()
    {
        return null;
    }

    public volatile TreeNode path(int i)
    {
        return path(i);
    }

    public volatile TreeNode path(String s)
    {
        return path(s);
    }

    public abstract JsonNode path(int i);

    public abstract JsonNode path(String s);

    public short shortValue()
    {
        return 0;
    }

    public int size()
    {
        return 0;
    }

    public String textValue()
    {
        return null;
    }

    public abstract String toString();

    public JsonNode with(String s)
    {
        throw new UnsupportedOperationException((new StringBuilder()).append("JsonNode not of type ObjectNode (but ").append(getClass().getName()).append("), can not call with() on it").toString());
    }

    public JsonNode withArray(String s)
    {
        throw new UnsupportedOperationException((new StringBuilder()).append("JsonNode not of type ObjectNode (but ").append(getClass().getName()).append("), can not call withArray() on it").toString());
    }
}
