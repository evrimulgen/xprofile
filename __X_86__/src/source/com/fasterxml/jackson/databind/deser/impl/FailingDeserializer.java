// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.fasterxml.jackson.databind.deser.impl;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

public class FailingDeserializer extends StdDeserializer
{

    public FailingDeserializer(String s)
    {
        super(java/lang/Object);
        _message = s;
    }

    public Object deserialize(JsonParser jsonparser, DeserializationContext deserializationcontext)
        throws JsonMappingException
    {
        throw deserializationcontext.mappingException(_message);
    }

    private static final long serialVersionUID = 1L;
    protected final String _message;
}
