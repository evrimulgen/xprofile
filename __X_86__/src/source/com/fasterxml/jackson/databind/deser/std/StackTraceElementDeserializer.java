// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.fasterxml.jackson.databind.deser.std;

import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonMappingException;
import java.io.IOException;

// Referenced classes of package com.fasterxml.jackson.databind.deser.std:
//            StdScalarDeserializer

public class StackTraceElementDeserializer extends StdScalarDeserializer
{

    public StackTraceElementDeserializer()
    {
        super(java/lang/StackTraceElement);
    }

    public volatile Object deserialize(JsonParser jsonparser, DeserializationContext deserializationcontext)
        throws IOException, JsonProcessingException
    {
        return deserialize(jsonparser, deserializationcontext);
    }

    public StackTraceElement deserialize(JsonParser jsonparser, DeserializationContext deserializationcontext)
        throws IOException, JsonProcessingException
    {
        JsonToken jsontoken = jsonparser.getCurrentToken();
        if(jsontoken == JsonToken.START_OBJECT)
        {
            String s = "";
            String s1 = "";
            String s2 = "";
            int i = -1;
            do
            {
                JsonToken jsontoken1 = jsonparser.nextValue();
                if(jsontoken1 == JsonToken.END_OBJECT)
                    break;
                String s3 = jsonparser.getCurrentName();
                if("className".equals(s3))
                    s = jsonparser.getText();
                else
                if("fileName".equals(s3))
                    s2 = jsonparser.getText();
                else
                if("lineNumber".equals(s3))
                {
                    if(jsontoken1.isNumeric())
                        i = jsonparser.getIntValue();
                    else
                        throw JsonMappingException.from(jsonparser, (new StringBuilder()).append("Non-numeric token (").append(jsontoken1).append(") for property 'lineNumber'").toString());
                } else
                if("methodName".equals(s3))
                    s1 = jsonparser.getText();
                else
                if(!"nativeMethod".equals(s3))
                    handleUnknownProperty(jsonparser, deserializationcontext, _valueClass, s3);
            } while(true);
            return new StackTraceElement(s, s1, s2, i);
        } else
        {
            throw deserializationcontext.mappingException(_valueClass, jsontoken);
        }
    }

    public static final StackTraceElementDeserializer instance = new StackTraceElementDeserializer();
    private static final long serialVersionUID = 1L;

}
