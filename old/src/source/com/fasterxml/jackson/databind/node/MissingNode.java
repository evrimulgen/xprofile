// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.fasterxml.jackson.databind.node;

import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import java.io.IOException;

// Referenced classes of package com.fasterxml.jackson.databind.node:
//            ValueNode, JsonNodeType

public final class MissingNode extends ValueNode
{

    private MissingNode()
    {
    }

    public static MissingNode getInstance()
    {
        return instance;
    }

    public String asText()
    {
        return "";
    }

    public JsonToken asToken()
    {
        return JsonToken.NOT_AVAILABLE;
    }

    public JsonNode deepCopy()
    {
        return this;
    }

    public boolean equals(Object obj)
    {
        return obj == this;
    }

    public JsonNodeType getNodeType()
    {
        return JsonNodeType.MISSING;
    }

    public final void serialize(JsonGenerator jsongenerator, SerializerProvider serializerprovider)
        throws IOException, JsonProcessingException
    {
        jsongenerator.writeNull();
    }

    public void serializeWithType(JsonGenerator jsongenerator, SerializerProvider serializerprovider, TypeSerializer typeserializer)
        throws IOException, JsonProcessingException
    {
        jsongenerator.writeNull();
    }

    public String toString()
    {
        return "";
    }

    private static final MissingNode instance = new MissingNode();

}
