// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.fasterxml.jackson.databind.jsonFormatVisitors;

import com.fasterxml.jackson.databind.*;

// Referenced classes of package com.fasterxml.jackson.databind.jsonFormatVisitors:
//            JsonFormatVisitorWithSerializerProvider, JsonFormatVisitable

public interface JsonObjectFormatVisitor
    extends JsonFormatVisitorWithSerializerProvider
{
    public static class Base
        implements JsonObjectFormatVisitor
    {

        public SerializerProvider getProvider()
        {
            return _provider;
        }

        public void optionalProperty(BeanProperty beanproperty)
            throws JsonMappingException
        {
        }

        public void optionalProperty(String s, JsonFormatVisitable jsonformatvisitable, JavaType javatype)
            throws JsonMappingException
        {
        }

        public void property(BeanProperty beanproperty)
            throws JsonMappingException
        {
        }

        public void property(String s, JsonFormatVisitable jsonformatvisitable, JavaType javatype)
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


    public abstract void optionalProperty(BeanProperty beanproperty)
        throws JsonMappingException;

    public abstract void optionalProperty(String s, JsonFormatVisitable jsonformatvisitable, JavaType javatype)
        throws JsonMappingException;

    public abstract void property(BeanProperty beanproperty)
        throws JsonMappingException;

    public abstract void property(String s, JsonFormatVisitable jsonformatvisitable, JavaType javatype)
        throws JsonMappingException;
}
