// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.fasterxml.jackson.databind.deser.std;

import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import java.io.IOException;

// Referenced classes of package com.fasterxml.jackson.databind.deser.std:
//            StdScalarDeserializer

public class JavaTypeDeserializer extends StdScalarDeserializer
{

    public JavaTypeDeserializer()
    {
        super(com/fasterxml/jackson/databind/JavaType);
    }

    public JavaType deserialize(JsonParser jsonparser, DeserializationContext deserializationcontext)
        throws IOException, JsonProcessingException
    {
        JsonToken jsontoken = jsonparser.getCurrentToken();
        if(jsontoken == JsonToken.VALUE_STRING)
        {
            String s = jsonparser.getText().trim();
            if(s.length() == 0)
                return (JavaType)getEmptyValue();
            else
                return deserializationcontext.getTypeFactory().constructFromCanonical(s);
        }
        if(jsontoken == JsonToken.VALUE_EMBEDDED_OBJECT)
            return (JavaType)jsonparser.getEmbeddedObject();
        else
            throw deserializationcontext.mappingException(_valueClass);
    }

    public volatile Object deserialize(JsonParser jsonparser, DeserializationContext deserializationcontext)
        throws IOException, JsonProcessingException
    {
        return deserialize(jsonparser, deserializationcontext);
    }

    public static final JavaTypeDeserializer instance = new JavaTypeDeserializer();
    private static final long serialVersionUID = 1L;

}
