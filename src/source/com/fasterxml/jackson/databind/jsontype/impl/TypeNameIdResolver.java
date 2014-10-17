// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.fasterxml.jackson.databind.jsontype.impl;

import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.cfg.MapperConfig;
import com.fasterxml.jackson.databind.jsontype.NamedType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import java.util.*;

// Referenced classes of package com.fasterxml.jackson.databind.jsontype.impl:
//            TypeIdResolverBase

public class TypeNameIdResolver extends TypeIdResolverBase
{

    protected TypeNameIdResolver(MapperConfig mapperconfig, JavaType javatype, HashMap hashmap, HashMap hashmap1)
    {
        super(javatype, mapperconfig.getTypeFactory());
        _config = mapperconfig;
        _typeToId = hashmap;
        _idToType = hashmap1;
    }

    protected static String _defaultTypeId(Class class1)
    {
        String s = class1.getName();
        int i = s.lastIndexOf('.');
        if(i < 0)
            return s;
        else
            return s.substring(i + 1);
    }

    public static TypeNameIdResolver construct(MapperConfig mapperconfig, JavaType javatype, Collection collection, boolean flag, boolean flag1)
    {
        if(flag == flag1)
            throw new IllegalArgumentException();
        HashMap hashmap;
        HashMap hashmap1;
        if(flag)
            hashmap = new HashMap();
        else
            hashmap = null;
        if(flag1)
            hashmap1 = new HashMap();
        else
            hashmap1 = null;
        if(collection != null)
        {
            Iterator iterator = collection.iterator();
            do
            {
                if(!iterator.hasNext())
                    break;
                NamedType namedtype = (NamedType)iterator.next();
                Class class1 = namedtype.getType();
                String s;
                if(namedtype.hasName())
                    s = namedtype.getName();
                else
                    s = _defaultTypeId(class1);
                if(flag)
                    hashmap.put(class1.getName(), s);
                if(flag1)
                {
                    JavaType javatype1 = (JavaType)hashmap1.get(s);
                    if(javatype1 == null || !class1.isAssignableFrom(javatype1.getRawClass()))
                        hashmap1.put(s, mapperconfig.constructType(class1));
                }
            } while(true);
        }
        return new TypeNameIdResolver(mapperconfig, javatype, hashmap, hashmap1);
    }

    protected JavaType _typeFromId(String s)
    {
        return (JavaType)_idToType.get(s);
    }

    public com.fasterxml.jackson.annotation.JsonTypeInfo.Id getMechanism()
    {
        return com.fasterxml.jackson.annotation.JsonTypeInfo.Id.NAME;
    }

    public String idFromValue(Object obj)
    {
        Class class1;
        String s;
        class1 = _typeFactory.constructType(obj.getClass()).getRawClass();
        s = class1.getName();
        HashMap hashmap = _typeToId;
        hashmap;
        JVM INSTR monitorenter ;
        String s1 = (String)_typeToId.get(s);
        if(s1 != null)
            break MISSING_BLOCK_LABEL_106;
        if(_config.isAnnotationProcessingEnabled())
        {
            BeanDescription beandescription = _config.introspectClassAnnotations(class1);
            s1 = _config.getAnnotationIntrospector().findTypeName(beandescription.getClassInfo());
        }
        if(s1 != null)
            break MISSING_BLOCK_LABEL_95;
        s1 = _defaultTypeId(class1);
        _typeToId.put(s, s1);
        hashmap;
        JVM INSTR monitorexit ;
        return s1;
        Exception exception;
        exception;
        hashmap;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public String idFromValueAndType(Object obj, Class class1)
    {
        if(obj == null)
            return null;
        else
            return idFromValue(obj);
    }

    public String toString()
    {
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append('[').append(getClass().getName());
        stringbuilder.append("; id-to-type=").append(_idToType);
        stringbuilder.append(']');
        return stringbuilder.toString();
    }

    public JavaType typeFromId(DatabindContext databindcontext, String s)
    {
        return _typeFromId(s);
    }

    public JavaType typeFromId(String s)
    {
        return _typeFromId(s);
    }

    protected final MapperConfig _config;
    protected final HashMap _idToType;
    protected final HashMap _typeToId;
}
