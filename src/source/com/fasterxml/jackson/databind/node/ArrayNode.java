// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.fasterxml.jackson.databind.node;

import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;

// Referenced classes of package com.fasterxml.jackson.databind.node:
//            ContainerNode, ObjectNode, JsonNodeType, MissingNode, 
//            BaseJsonNode, JsonNodeFactory

public class ArrayNode extends ContainerNode
{

    public ArrayNode(JsonNodeFactory jsonnodefactory)
    {
        super(jsonnodefactory);
    }

    protected ArrayNode _add(JsonNode jsonnode)
    {
        _children.add(jsonnode);
        return this;
    }

    protected JsonNode _at(JsonPointer jsonpointer)
    {
        return get(jsonpointer.getMatchingIndex());
    }

    protected boolean _childrenEqual(ArrayNode arraynode)
    {
        return _children.equals(arraynode._children);
    }

    protected ArrayNode _insert(int i, JsonNode jsonnode)
    {
        if(i < 0)
        {
            _children.add(0, jsonnode);
            return this;
        }
        if(i >= _children.size())
        {
            _children.add(jsonnode);
            return this;
        } else
        {
            _children.add(i, jsonnode);
            return this;
        }
    }

    public ArrayNode add(double d)
    {
        return _add(numberNode(d));
    }

    public ArrayNode add(float f)
    {
        return _add(numberNode(f));
    }

    public ArrayNode add(int i)
    {
        _add(numberNode(i));
        return this;
    }

    public ArrayNode add(long l)
    {
        return _add(numberNode(l));
    }

    public ArrayNode add(JsonNode jsonnode)
    {
        if(jsonnode == null)
            jsonnode = nullNode();
        _add(jsonnode);
        return this;
    }

    public ArrayNode add(Boolean boolean1)
    {
        if(boolean1 == null)
            return addNull();
        else
            return _add(booleanNode(boolean1.booleanValue()));
    }

    public ArrayNode add(Double double1)
    {
        if(double1 == null)
            return addNull();
        else
            return _add(numberNode(double1.doubleValue()));
    }

    public ArrayNode add(Float float1)
    {
        if(float1 == null)
            return addNull();
        else
            return _add(numberNode(float1.floatValue()));
    }

    public ArrayNode add(Integer integer)
    {
        if(integer == null)
            return addNull();
        else
            return _add(numberNode(integer.intValue()));
    }

    public ArrayNode add(Long long1)
    {
        if(long1 == null)
            return addNull();
        else
            return _add(numberNode(long1.longValue()));
    }

    public ArrayNode add(String s)
    {
        if(s == null)
            return addNull();
        else
            return _add(textNode(s));
    }

    public ArrayNode add(BigDecimal bigdecimal)
    {
        if(bigdecimal == null)
            return addNull();
        else
            return _add(numberNode(bigdecimal));
    }

    public ArrayNode add(boolean flag)
    {
        return _add(booleanNode(flag));
    }

    public ArrayNode add(byte abyte0[])
    {
        if(abyte0 == null)
            return addNull();
        else
            return _add(binaryNode(abyte0));
    }

    public ArrayNode addAll(ArrayNode arraynode)
    {
        _children.addAll(arraynode._children);
        return this;
    }

    public ArrayNode addAll(Collection collection)
    {
        _children.addAll(collection);
        return this;
    }

    public ArrayNode addArray()
    {
        ArrayNode arraynode = arrayNode();
        _add(arraynode);
        return arraynode;
    }

    public ArrayNode addNull()
    {
        _add(nullNode());
        return this;
    }

    public ObjectNode addObject()
    {
        ObjectNode objectnode = objectNode();
        _add(objectnode);
        return objectnode;
    }

    public ArrayNode addPOJO(Object obj)
    {
        if(obj == null)
        {
            addNull();
            return this;
        } else
        {
            _add(pojoNode(obj));
            return this;
        }
    }

    public JsonToken asToken()
    {
        return JsonToken.START_ARRAY;
    }

    public volatile JsonNode deepCopy()
    {
        return deepCopy();
    }

    public ArrayNode deepCopy()
    {
        ArrayNode arraynode = new ArrayNode(_nodeFactory);
        JsonNode jsonnode;
        for(Iterator iterator = _children.iterator(); iterator.hasNext(); arraynode._children.add(jsonnode.deepCopy()))
            jsonnode = (JsonNode)iterator.next();

        return arraynode;
    }

    public Iterator elements()
    {
        return _children.iterator();
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
                boolean flag1 = obj instanceof ArrayNode;
                flag = false;
                if(flag1)
                    return _children.equals(((ArrayNode)obj)._children);
            }
        }
        return flag;
    }

    public volatile JsonNode findParent(String s)
    {
        return findParent(s);
    }

    public ObjectNode findParent(String s)
    {
        for(Iterator iterator = _children.iterator(); iterator.hasNext();)
        {
            JsonNode jsonnode = ((JsonNode)iterator.next()).findParent(s);
            if(jsonnode != null)
                return (ObjectNode)jsonnode;
        }

        return null;
    }

    public List findParents(String s, List list)
    {
        for(Iterator iterator = _children.iterator(); iterator.hasNext();)
            list = ((JsonNode)iterator.next()).findParents(s, list);

        return list;
    }

    public JsonNode findValue(String s)
    {
        for(Iterator iterator = _children.iterator(); iterator.hasNext();)
        {
            JsonNode jsonnode = ((JsonNode)iterator.next()).findValue(s);
            if(jsonnode != null)
                return jsonnode;
        }

        return null;
    }

    public List findValues(String s, List list)
    {
        for(Iterator iterator = _children.iterator(); iterator.hasNext();)
            list = ((JsonNode)iterator.next()).findValues(s, list);

        return list;
    }

    public List findValuesAsText(String s, List list)
    {
        for(Iterator iterator = _children.iterator(); iterator.hasNext();)
            list = ((JsonNode)iterator.next()).findValuesAsText(s, list);

        return list;
    }

    public volatile TreeNode get(int i)
    {
        return get(i);
    }

    public volatile TreeNode get(String s)
    {
        return get(s);
    }

    public JsonNode get(int i)
    {
        if(i >= 0 && i < _children.size())
            return (JsonNode)_children.get(i);
        else
            return null;
    }

    public JsonNode get(String s)
    {
        return null;
    }

    public JsonNodeType getNodeType()
    {
        return JsonNodeType.ARRAY;
    }

    public int hashCode()
    {
        return _children.hashCode();
    }

    public ArrayNode insert(int i, double d)
    {
        return _insert(i, numberNode(d));
    }

    public ArrayNode insert(int i, float f)
    {
        return _insert(i, numberNode(f));
    }

    public ArrayNode insert(int i, int j)
    {
        _insert(i, numberNode(j));
        return this;
    }

    public ArrayNode insert(int i, long l)
    {
        return _insert(i, numberNode(l));
    }

    public ArrayNode insert(int i, JsonNode jsonnode)
    {
        if(jsonnode == null)
            jsonnode = nullNode();
        _insert(i, jsonnode);
        return this;
    }

    public ArrayNode insert(int i, Boolean boolean1)
    {
        if(boolean1 == null)
            return insertNull(i);
        else
            return _insert(i, booleanNode(boolean1.booleanValue()));
    }

    public ArrayNode insert(int i, Double double1)
    {
        if(double1 == null)
            return insertNull(i);
        else
            return _insert(i, numberNode(double1.doubleValue()));
    }

    public ArrayNode insert(int i, Float float1)
    {
        if(float1 == null)
            return insertNull(i);
        else
            return _insert(i, numberNode(float1.floatValue()));
    }

    public ArrayNode insert(int i, Integer integer)
    {
        if(integer == null)
        {
            insertNull(i);
            return this;
        } else
        {
            _insert(i, numberNode(integer.intValue()));
            return this;
        }
    }

    public ArrayNode insert(int i, Long long1)
    {
        if(long1 == null)
            return insertNull(i);
        else
            return _insert(i, numberNode(long1.longValue()));
    }

    public ArrayNode insert(int i, String s)
    {
        if(s == null)
            return insertNull(i);
        else
            return _insert(i, textNode(s));
    }

    public ArrayNode insert(int i, BigDecimal bigdecimal)
    {
        if(bigdecimal == null)
            return insertNull(i);
        else
            return _insert(i, numberNode(bigdecimal));
    }

    public ArrayNode insert(int i, boolean flag)
    {
        return _insert(i, booleanNode(flag));
    }

    public ArrayNode insert(int i, byte abyte0[])
    {
        if(abyte0 == null)
            return insertNull(i);
        else
            return _insert(i, binaryNode(abyte0));
    }

    public ArrayNode insertArray(int i)
    {
        ArrayNode arraynode = arrayNode();
        _insert(i, arraynode);
        return arraynode;
    }

    public ArrayNode insertNull(int i)
    {
        _insert(i, nullNode());
        return this;
    }

    public ObjectNode insertObject(int i)
    {
        ObjectNode objectnode = objectNode();
        _insert(i, objectnode);
        return objectnode;
    }

    public ArrayNode insertPOJO(int i, Object obj)
    {
        if(obj == null)
            return insertNull(i);
        else
            return _insert(i, pojoNode(obj));
    }

    public volatile TreeNode path(int i)
    {
        return path(i);
    }

    public volatile TreeNode path(String s)
    {
        return path(s);
    }

    public JsonNode path(int i)
    {
        if(i >= 0 && i < _children.size())
            return (JsonNode)_children.get(i);
        else
            return MissingNode.getInstance();
    }

    public JsonNode path(String s)
    {
        return MissingNode.getInstance();
    }

    public JsonNode remove(int i)
    {
        if(i >= 0 && i < _children.size())
            return (JsonNode)_children.remove(i);
        else
            return null;
    }

    public ArrayNode removeAll()
    {
        _children.clear();
        return this;
    }

    public volatile ContainerNode removeAll()
    {
        return removeAll();
    }

    public void serialize(JsonGenerator jsongenerator, SerializerProvider serializerprovider)
        throws IOException, JsonProcessingException
    {
        jsongenerator.writeStartArray();
        for(Iterator iterator = _children.iterator(); iterator.hasNext(); ((BaseJsonNode)(JsonNode)iterator.next()).serialize(jsongenerator, serializerprovider));
        jsongenerator.writeEndArray();
    }

    public void serializeWithType(JsonGenerator jsongenerator, SerializerProvider serializerprovider, TypeSerializer typeserializer)
        throws IOException, JsonProcessingException
    {
        typeserializer.writeTypePrefixForArray(this, jsongenerator);
        for(Iterator iterator = _children.iterator(); iterator.hasNext(); ((BaseJsonNode)(JsonNode)iterator.next()).serialize(jsongenerator, serializerprovider));
        typeserializer.writeTypeSuffixForArray(this, jsongenerator);
    }

    public JsonNode set(int i, JsonNode jsonnode)
    {
        if(jsonnode == null)
            jsonnode = nullNode();
        if(i < 0 || i >= _children.size())
            throw new IndexOutOfBoundsException((new StringBuilder()).append("Illegal index ").append(i).append(", array size ").append(size()).toString());
        else
            return (JsonNode)_children.set(i, jsonnode);
    }

    public int size()
    {
        return _children.size();
    }

    public String toString()
    {
        StringBuilder stringbuilder = new StringBuilder(16 + (size() << 4));
        stringbuilder.append('[');
        int i = _children.size();
        for(int j = 0; j < i; j++)
        {
            if(j > 0)
                stringbuilder.append(',');
            stringbuilder.append(((JsonNode)_children.get(j)).toString());
        }

        stringbuilder.append(']');
        return stringbuilder.toString();
    }

    private final List _children = new ArrayList();
}
