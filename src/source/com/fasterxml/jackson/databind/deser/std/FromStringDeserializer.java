// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.fasterxml.jackson.databind.deser.std;

import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;

// Referenced classes of package com.fasterxml.jackson.databind.deser.std:
//            StdScalarDeserializer

public abstract class FromStringDeserializer extends StdScalarDeserializer
{

    protected FromStringDeserializer(Class class1)
    {
        super(class1);
    }

    protected abstract Object _deserialize(String s, DeserializationContext deserializationcontext)
        throws IOException, JsonProcessingException;

    protected Object _deserializeEmbedded(Object obj, DeserializationContext deserializationcontext)
        throws IOException, JsonProcessingException
    {
        throw deserializationcontext.mappingException((new StringBuilder()).append("Don't know how to convert embedded Object of type ").append(obj.getClass().getName()).append(" into ").append(_valueClass.getName()).toString());
    }

    public final Object deserialize(JsonParser jsonparser, DeserializationContext deserializationcontext)
        throws IOException, JsonProcessingException
    {
        String s = jsonparser.getValueAsString();
        if(s == null) goto _L2; else goto _L1
_L1:
        Object obj1;
        int i;
        i = s.length();
        obj1 = null;
        if(i == 0) goto _L4; else goto _L3
_L3:
        String s1;
        int j;
        s1 = s.trim();
        j = s1.length();
        obj1 = null;
        if(j != 0) goto _L5; else goto _L4
_L4:
        Object obj2;
        return obj1;
_L5:
        if((obj1 = obj2 = _deserialize(s1, deserializationcontext)) != null) goto _L4; else goto _L6
_L6:
        throw deserializationcontext.weirdStringException(s1, _valueClass, "not a valid textual representation");
_L2:
        if(jsonparser.getCurrentToken() == JsonToken.VALUE_EMBEDDED_OBJECT)
        {
            Object obj = jsonparser.getEmbeddedObject();
            obj1 = null;
            if(obj != null)
                if(_valueClass.isAssignableFrom(obj.getClass()))
                    return obj;
                else
                    return _deserializeEmbedded(obj, deserializationcontext);
        } else
        {
            throw deserializationcontext.mappingException(_valueClass);
        }
          goto _L4
        IllegalArgumentException illegalargumentexception;
        illegalargumentexception;
          goto _L6
    }

    private static final long serialVersionUID = 1L;
}
