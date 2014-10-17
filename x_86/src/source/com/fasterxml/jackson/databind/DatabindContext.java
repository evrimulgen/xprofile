// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.fasterxml.jackson.databind;

import com.fasterxml.jackson.annotation.ObjectIdGenerator;
import com.fasterxml.jackson.databind.annotation.NoClass;
import com.fasterxml.jackson.databind.cfg.HandlerInstantiator;
import com.fasterxml.jackson.databind.cfg.MapperConfig;
import com.fasterxml.jackson.databind.introspect.Annotated;
import com.fasterxml.jackson.databind.introspect.ObjectIdInfo;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.databind.util.ClassUtil;
import com.fasterxml.jackson.databind.util.Converter;
import java.lang.reflect.Type;

// Referenced classes of package com.fasterxml.jackson.databind:
//            JavaType, JsonMappingException, AnnotationIntrospector, MapperFeature

public abstract class DatabindContext
{

    public DatabindContext()
    {
    }

    public final boolean canOverrideAccessModifiers()
    {
        return getConfig().canOverrideAccessModifiers();
    }

    public JavaType constructSpecializedType(JavaType javatype, Class class1)
    {
        if(javatype.getRawClass() == class1)
            return javatype;
        else
            return getConfig().constructSpecializedType(javatype, class1);
    }

    public JavaType constructType(Type type)
    {
        return getTypeFactory().constructType(type);
    }

    public Converter converterInstance(Annotated annotated, Object obj)
        throws JsonMappingException
    {
        if(obj == null)
            return null;
        if(obj instanceof Converter)
            return (Converter)obj;
        if(!(obj instanceof Class))
            throw new IllegalStateException((new StringBuilder()).append("AnnotationIntrospector returned Converter definition of type ").append(obj.getClass().getName()).append("; expected type Converter or Class<Converter> instead").toString());
        Class class1 = (Class)obj;
        if(class1 == com/fasterxml/jackson/databind/util/Converter$None || class1 == com/fasterxml/jackson/databind/annotation/NoClass)
            return null;
        if(!com/fasterxml/jackson/databind/util/Converter.isAssignableFrom(class1))
            throw new IllegalStateException((new StringBuilder()).append("AnnotationIntrospector returned Class ").append(class1.getName()).append("; expected Class<Converter>").toString());
        MapperConfig mapperconfig = getConfig();
        HandlerInstantiator handlerinstantiator = mapperconfig.getHandlerInstantiator();
        Converter converter = null;
        if(handlerinstantiator != null)
            converter = handlerinstantiator.converterInstance(mapperconfig, annotated, class1);
        if(converter == null)
            converter = (Converter)ClassUtil.createInstance(class1, mapperconfig.canOverrideAccessModifiers());
        return converter;
    }

    public abstract Class getActiveView();

    public abstract AnnotationIntrospector getAnnotationIntrospector();

    public abstract Object getAttribute(Object obj);

    public abstract MapperConfig getConfig();

    public abstract TypeFactory getTypeFactory();

    public final boolean isEnabled(MapperFeature mapperfeature)
    {
        return getConfig().isEnabled(mapperfeature);
    }

    public ObjectIdGenerator objectIdGeneratorInstance(Annotated annotated, ObjectIdInfo objectidinfo)
        throws JsonMappingException
    {
        Class class1 = objectidinfo.getGeneratorType();
        MapperConfig mapperconfig = getConfig();
        HandlerInstantiator handlerinstantiator = mapperconfig.getHandlerInstantiator();
        ObjectIdGenerator objectidgenerator;
        if(handlerinstantiator == null)
            objectidgenerator = null;
        else
            objectidgenerator = handlerinstantiator.objectIdGeneratorInstance(mapperconfig, annotated, class1);
        if(objectidgenerator == null)
            objectidgenerator = (ObjectIdGenerator)ClassUtil.createInstance(class1, mapperconfig.canOverrideAccessModifiers());
        return objectidgenerator.forScope(objectidinfo.getScope());
    }

    public abstract DatabindContext setAttribute(Object obj, Object obj1);
}
