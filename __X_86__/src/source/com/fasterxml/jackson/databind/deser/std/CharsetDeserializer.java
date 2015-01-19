// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.fasterxml.jackson.databind.deser.std;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import java.nio.charset.Charset;

// Referenced classes of package com.fasterxml.jackson.databind.deser.std:
//            FromStringDeserializer

public class CharsetDeserializer extends FromStringDeserializer
{

    public CharsetDeserializer()
    {
        FromStringDeserializer(java/nio/charset/Charset);
    }

    protected volatile Object _deserialize(String s, DeserializationContext deserializationcontext)
        throws IOException, JsonProcessingException
    {
        return _deserialize(s, deserializationcontext);
    }

    protected Charset _deserialize(String s, DeserializationContext deserializationcontext)
        throws IOException
    {
        return Charset.forName(s);
    }

    private static final long serialVersionUID = 1L;
}
