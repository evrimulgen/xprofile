// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.fasterxml.jackson.databind.node;

import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import java.io.IOException;
import java.util.List;

// Referenced classes of package com.fasterxml.jackson.databind.node:
//            BaseJsonNode, MissingNode, ObjectNode

public abstract class ValueNode extends BaseJsonNode
{

    protected ValueNode()
    {
    }

    protected JsonNode _at(JsonPointer jsonpointer)
    {
        return MissingNode.getInstance();
    }

    public abstract JsonToken asToken();

    public JsonNode deepCopy()
    {
        return this;
    }

    public volatile JsonNode findParent(String s)
    {
        return findParent(s);
    }

    public final ObjectNode findParent(String s)
    {
        return null;
    }

    public final List findParents(String s, List list)
    {
        return list;
    }

    public final JsonNode findValue(String s)
    {
        return null;
    }

    public final List findValues(String s, List list)
    {
        return list;
    }

    public final List findValuesAsText(String s, List list)
    {
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

    public final JsonNode get(int i)
    {
        return null;
    }

    public final JsonNode get(String s)
    {
        return null;
    }

    public final boolean has(int i)
    {
        return false;
    }

    public final boolean has(String s)
    {
        return false;
    }

    public final boolean hasNonNull(int i)
    {
        return false;
    }

    public final boolean hasNonNull(String s)
    {
        return false;
    }

    public volatile TreeNode path(int i)
    {
        return path(i);
    }

    public volatile TreeNode path(String s)
    {
        return path(s);
    }

    public final JsonNode path(int i)
    {
        return MissingNode.getInstance();
    }

    public final JsonNode path(String s)
    {
        return MissingNode.getInstance();
    }

    public void serializeWithType(JsonGenerator jsongenerator, SerializerProvider serializerprovider, TypeSerializer typeserializer)
        throws IOException, JsonProcessingException
    {
        typeserializer.writeTypePrefixForScalar(this, jsongenerator);
        serialize(jsongenerator, serializerprovider);
        typeserializer.writeTypeSuffixForScalar(this, jsongenerator);
    }

    public String toString()
    {
        return asText();
    }
}
