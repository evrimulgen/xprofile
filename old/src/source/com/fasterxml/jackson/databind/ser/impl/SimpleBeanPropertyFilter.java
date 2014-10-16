// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.fasterxml.jackson.databind.ser.impl;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonObjectFormatVisitor;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.ser.*;
import java.io.Serializable;
import java.util.*;

public abstract class SimpleBeanPropertyFilter
    implements BeanPropertyFilter, PropertyFilter
{
    public static class FilterExceptFilter extends SimpleBeanPropertyFilter
        implements Serializable
    {

        protected boolean include(BeanPropertyWriter beanpropertywriter)
        {
            return _propertiesToInclude.contains(beanpropertywriter.getName());
        }

        protected boolean include(PropertyWriter propertywriter)
        {
            return _propertiesToInclude.contains(propertywriter.getName());
        }

        private static final long serialVersionUID = 1L;
        protected final Set _propertiesToInclude;

        public FilterExceptFilter(Set set)
        {
            _propertiesToInclude = set;
        }
    }

    public static class SerializeExceptFilter extends SimpleBeanPropertyFilter
        implements Serializable
    {

        protected boolean include(BeanPropertyWriter beanpropertywriter)
        {
            return !_propertiesToExclude.contains(beanpropertywriter.getName());
        }

        protected boolean include(PropertyWriter propertywriter)
        {
            return !_propertiesToExclude.contains(propertywriter.getName());
        }

        private static final long serialVersionUID = 1L;
        protected final Set _propertiesToExclude;

        public SerializeExceptFilter(Set set)
        {
            _propertiesToExclude = set;
        }
    }


    protected SimpleBeanPropertyFilter()
    {
    }

    public static SimpleBeanPropertyFilter filterOutAllExcept(Set set)
    {
        return new FilterExceptFilter(set);
    }

    public static transient SimpleBeanPropertyFilter filterOutAllExcept(String as[])
    {
        HashSet hashset = new HashSet(as.length);
        Collections.addAll(hashset, as);
        return new FilterExceptFilter(hashset);
    }

    public static PropertyFilter from(BeanPropertyFilter beanpropertyfilter)
    {
        return new PropertyFilter(beanpropertyfilter) {

            public void depositSchemaProperty(PropertyWriter propertywriter, JsonObjectFormatVisitor jsonobjectformatvisitor, SerializerProvider serializerprovider)
                throws JsonMappingException
            {
                src.depositSchemaProperty((BeanPropertyWriter)propertywriter, jsonobjectformatvisitor, serializerprovider);
            }

            public void depositSchemaProperty(PropertyWriter propertywriter, ObjectNode objectnode, SerializerProvider serializerprovider)
                throws JsonMappingException
            {
                src.depositSchemaProperty((BeanPropertyWriter)propertywriter, objectnode, serializerprovider);
            }

            public void serializeAsElement(Object obj, JsonGenerator jsongenerator, SerializerProvider serializerprovider, PropertyWriter propertywriter)
                throws Exception
            {
                throw new UnsupportedOperationException();
            }

            public void serializeAsField(Object obj, JsonGenerator jsongenerator, SerializerProvider serializerprovider, PropertyWriter propertywriter)
                throws Exception
            {
                src.serializeAsField(obj, jsongenerator, serializerprovider, (BeanPropertyWriter)propertywriter);
            }

            final BeanPropertyFilter val$src;

            
            {
                src = beanpropertyfilter;
                super();
            }
        }
;
    }

    public static SimpleBeanPropertyFilter serializeAllExcept(Set set)
    {
        return new SerializeExceptFilter(set);
    }

    public static transient SimpleBeanPropertyFilter serializeAllExcept(String as[])
    {
        HashSet hashset = new HashSet(as.length);
        Collections.addAll(hashset, as);
        return new SerializeExceptFilter(hashset);
    }

    public void depositSchemaProperty(BeanPropertyWriter beanpropertywriter, JsonObjectFormatVisitor jsonobjectformatvisitor, SerializerProvider serializerprovider)
        throws JsonMappingException
    {
        if(include(beanpropertywriter))
            beanpropertywriter.depositSchemaProperty(jsonobjectformatvisitor);
    }

    public void depositSchemaProperty(BeanPropertyWriter beanpropertywriter, ObjectNode objectnode, SerializerProvider serializerprovider)
        throws JsonMappingException
    {
        if(include(beanpropertywriter))
            beanpropertywriter.depositSchemaProperty(objectnode, serializerprovider);
    }

    public void depositSchemaProperty(PropertyWriter propertywriter, JsonObjectFormatVisitor jsonobjectformatvisitor, SerializerProvider serializerprovider)
        throws JsonMappingException
    {
        if(include(propertywriter))
            propertywriter.depositSchemaProperty(jsonobjectformatvisitor);
    }

    public void depositSchemaProperty(PropertyWriter propertywriter, ObjectNode objectnode, SerializerProvider serializerprovider)
        throws JsonMappingException
    {
        if(include(propertywriter))
            propertywriter.depositSchemaProperty(objectnode, serializerprovider);
    }

    protected abstract boolean include(BeanPropertyWriter beanpropertywriter);

    protected abstract boolean include(PropertyWriter propertywriter);

    protected boolean includeElement(Object obj)
    {
        return true;
    }

    public void serializeAsElement(Object obj, JsonGenerator jsongenerator, SerializerProvider serializerprovider, PropertyWriter propertywriter)
        throws Exception
    {
        if(includeElement(obj))
            propertywriter.serializeAsElement(obj, jsongenerator, serializerprovider);
    }

    public void serializeAsField(Object obj, JsonGenerator jsongenerator, SerializerProvider serializerprovider, BeanPropertyWriter beanpropertywriter)
        throws Exception
    {
        if(include(beanpropertywriter))
            beanpropertywriter.serializeAsField(obj, jsongenerator, serializerprovider);
        else
        if(!jsongenerator.canOmitFields())
        {
            beanpropertywriter.serializeAsOmittedField(obj, jsongenerator, serializerprovider);
            return;
        }
    }

    public void serializeAsField(Object obj, JsonGenerator jsongenerator, SerializerProvider serializerprovider, PropertyWriter propertywriter)
        throws Exception
    {
        if(include(propertywriter))
            propertywriter.serializeAsField(obj, jsongenerator, serializerprovider);
        else
        if(!jsongenerator.canOmitFields())
        {
            propertywriter.serializeAsOmittedField(obj, jsongenerator, serializerprovider);
            return;
        }
    }
}
