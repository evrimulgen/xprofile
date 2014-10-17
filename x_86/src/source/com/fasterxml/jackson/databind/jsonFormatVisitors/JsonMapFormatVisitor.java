// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.fasterxml.jackson.databind.jsonFormatVisitors;

import com.fasterxml.jackson.databind.*;

// Referenced classes of package com.fasterxml.jackson.databind.jsonFormatVisitors:
//            JsonFormatVisitorWithSerializerProvider, JsonFormatVisitable

public interface JsonMapFormatVisitor
    extends JsonFormatVisitorWithSerializerProvider
{
    public static class Base
        implements JsonMapFormatVisitor
    {

        public SerializerProvider getProvider()
        {
            return _provider;
        }

        public void keyFormat(JsonFormatVisitable jsonformatvisitable, JavaType javatype)
            throws JsonMappingException
        {
        }

        public void setProvider(SerializerProvider serializerprovider)
        {
            _provider = serializerprovider;
        }

        public void valueFormat(JsonFormatVisitable jsonformatvisitable, JavaType javatype)
            throws JsonMappingException
        {
        }

        protected SerializerProvider _provider;

        public Base()
        {
        }

        public Base(SerializerProvider serializerprovider)
        {
            _provider = serializerprovider;
        }
    }


    public abstract void keyFormat(JsonFormatVisitable jsonformatvisitable, JavaType javatype)
        throws JsonMappingException;

    public abstract void valueFormat(JsonFormatVisitable jsonformatvisitable, JavaType javatype)
        throws JsonMappingException;
}
