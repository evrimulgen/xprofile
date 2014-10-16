// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.fasterxml.jackson.databind.node;

import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;

// Referenced classes of package com.fasterxml.jackson.databind.node:
//            ValueNode, JsonNodeType

public final class NullNode extends ValueNode
{

    private NullNode()
    {
    }

    public static NullNode getInstance()
    {
        return instance;
    }

    public String asText()
    {
        return "null";
    }

    public JsonToken asToken()
    {
        return JsonToken.VALUE_NULL;
    }

    public boolean equals(Object obj)
    {
        return obj == this;
    }

    public JsonNodeType getNodeType()
    {
        return JsonNodeType.NULL;
    }

    public final void serialize(JsonGenerator jsongenerator, SerializerProvider serializerprovider)
        throws IOException, JsonProcessingException
    {
        serializerprovider.defaultSerializeNull(jsongenerator);
    }

    public static final NullNode instance = new NullNode();

}
