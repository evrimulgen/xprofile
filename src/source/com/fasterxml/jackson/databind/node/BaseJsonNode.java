// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.fasterxml.jackson.databind.node;

import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import java.io.IOException;

// Referenced classes of package com.fasterxml.jackson.databind.node:
//            MissingNode, TreeTraversingParser

public abstract class BaseJsonNode extends JsonNode
    implements JsonSerializable
{

    protected BaseJsonNode()
    {
    }

    public abstract JsonToken asToken();

    public final JsonNode findPath(String s)
    {
        Object obj = findValue(s);
        if(obj == null)
            obj = MissingNode.getInstance();
        return ((JsonNode) (obj));
    }

    public com.fasterxml.jackson.core.JsonParser.NumberType numberType()
    {
        return null;
    }

    public abstract void serialize(JsonGenerator jsongenerator, SerializerProvider serializerprovider)
        throws IOException, JsonProcessingException;

    public abstract void serializeWithType(JsonGenerator jsongenerator, SerializerProvider serializerprovider, TypeSerializer typeserializer)
        throws IOException, JsonProcessingException;

    public JsonParser traverse()
    {
        return new TreeTraversingParser(this);
    }

    public JsonParser traverse(ObjectCodec objectcodec)
    {
        return new TreeTraversingParser(this, objectcodec);
    }
}
