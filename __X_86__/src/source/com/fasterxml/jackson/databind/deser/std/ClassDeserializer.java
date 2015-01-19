// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.fasterxml.jackson.databind.deser.std;

import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.util.ClassUtil;
import java.io.IOException;

// Referenced classes of package com.fasterxml.jackson.databind.deser.std:
//            StdScalarDeserializer

public class ClassDeserializer extends StdScalarDeserializer
{

    public ClassDeserializer()
    {
        super(java/lang/Class);
    }

    public Class deserialize(JsonParser jsonparser, DeserializationContext deserializationcontext)
        throws IOException, JsonProcessingException
    {
        JsonToken jsontoken = jsonparser.getCurrentToken();
        if(jsontoken == JsonToken.VALUE_STRING)
        {
            String s = jsonparser.getText().trim();
            Class class1;
            try
            {
                class1 = deserializationcontext.findClass(s);
            }
            catch(Exception exception)
            {
                throw deserializationcontext.instantiationException(_valueClass, ClassUtil.getRootCause(exception));
            }
            return class1;
        } else
        {
            throw deserializationcontext.mappingException(_valueClass, jsontoken);
        }
    }

    public volatile Object deserialize(JsonParser jsonparser, DeserializationContext deserializationcontext)
        throws IOException, JsonProcessingException
    {
        return deserialize(jsonparser, deserializationcontext);
    }

    public static final ClassDeserializer instance = new ClassDeserializer();
    private static final long serialVersionUID = 1L;

}
