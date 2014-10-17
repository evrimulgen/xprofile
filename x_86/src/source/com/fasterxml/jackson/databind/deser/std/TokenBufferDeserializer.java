// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.fasterxml.jackson.databind.deser.std;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.util.TokenBuffer;
import java.io.IOException;

// Referenced classes of package com.fasterxml.jackson.databind.deser.std:
//            StdScalarDeserializer

public class TokenBufferDeserializer extends StdScalarDeserializer
{

    public TokenBufferDeserializer()
    {
        super(com/fasterxml/jackson/databind/util/TokenBuffer);
    }

    protected TokenBuffer createBufferInstance(JsonParser jsonparser)
    {
        return new TokenBuffer(jsonparser);
    }

    public TokenBuffer deserialize(JsonParser jsonparser, DeserializationContext deserializationcontext)
        throws IOException, JsonProcessingException
    {
        return createBufferInstance(jsonparser).deserialize(jsonparser, deserializationcontext);
    }

    public volatile Object deserialize(JsonParser jsonparser, DeserializationContext deserializationcontext)
        throws IOException, JsonProcessingException
    {
        return deserialize(jsonparser, deserializationcontext);
    }

    public static final TokenBufferDeserializer instance = new TokenBufferDeserializer();
    private static final long serialVersionUID = 1L;

}
