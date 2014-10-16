// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.fasterxml.jackson.databind.jsonFormatVisitors;

import com.fasterxml.jackson.databind.*;

// Referenced classes of package com.fasterxml.jackson.databind.jsonFormatVisitors:
//            JsonFormatVisitorWithSerializerProvider, JsonFormatTypes, JsonFormatVisitable

public interface JsonArrayFormatVisitor
    extends JsonFormatVisitorWithSerializerProvider
{
    public static class Base
        implements JsonArrayFormatVisitor
    {

        public SerializerProvider getProvider()
        {
            return _provider;
        }

        public void itemsFormat(JsonFormatTypes jsonformattypes)
            throws JsonMappingException
        {
        }

        public void itemsFormat(JsonFormatVisitable jsonformatvisitable, JavaType javatype)
            throws JsonMappingException
        {
        }

        public void setProvider(SerializerProvider serializerprovider)
        {
            _provider = serializerprovider;
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


    public abstract void itemsFormat(JsonFormatTypes jsonformattypes)
        throws JsonMappingException;

    public abstract void itemsFormat(JsonFormatVisitable jsonformatvisitable, JavaType javatype)
        throws JsonMappingException;
}
