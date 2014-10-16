// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.fasterxml.jackson.databind.deser;

import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.annotation.NoClass;
import com.fasterxml.jackson.databind.deser.std.StdDelegatingDeserializer;
import com.fasterxml.jackson.databind.introspect.Annotated;
import com.fasterxml.jackson.databind.type.*;
import com.fasterxml.jackson.databind.util.ClassUtil;
import com.fasterxml.jackson.databind.util.Converter;
import java.io.Serializable;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

// Referenced classes of package com.fasterxml.jackson.databind.deser:
//            ResolvableDeserializer, DeserializerFactory

public final class DeserializerCache
    implements Serializable
{

    public DeserializerCache()
    {
    }

    private Class _verifyAsClass(Object obj, String s, Class class1)
    {
        Class class2;
        if(obj == null)
        {
            class2 = null;
        } else
        {
            if(!(obj instanceof Class))
                throw new IllegalStateException((new StringBuilder()).append("AnnotationIntrospector.").append(s).append("() returned value of type ").append(obj.getClass().getName()).append(": expected type JsonSerializer or Class<JsonSerializer> instead").toString());
            class2 = (Class)obj;
            if(class2 == class1 || class2 == com/fasterxml/jackson/databind/annotation/NoClass)
                return null;
        }
        return class2;
    }

    private JavaType modifyTypeByAnnotation(DeserializationContext deserializationcontext, Annotated annotated, JavaType javatype)
        throws JsonMappingException
    {
        AnnotationIntrospector annotationintrospector = deserializationcontext.getAnnotationIntrospector();
        Class class1 = annotationintrospector.findDeserializationType(annotated, javatype);
        Object obj;
        if(class1 != null)
        {
            JavaType javatype4;
            try
            {
                javatype4 = javatype.narrowBy(class1);
            }
            catch(IllegalArgumentException illegalargumentexception2)
            {
                throw new JsonMappingException((new StringBuilder()).append("Failed to narrow type ").append(javatype).append(" with concrete-type annotation (value ").append(class1.getName()).append("), method '").append(annotated.getName()).append("': ").append(illegalargumentexception2.getMessage()).toString(), null, illegalargumentexception2);
            }
            obj = javatype4;
        } else
        {
            obj = javatype;
        }
        if(((JavaType) (obj)).isContainerType())
        {
            Class class2 = annotationintrospector.findDeserializationKeyType(annotated, ((JavaType) (obj)).getKeyType());
            Object obj1;
            Class class3;
            if(class2 != null)
            {
                if(!(obj instanceof MapLikeType))
                    throw new JsonMappingException((new StringBuilder()).append("Illegal key-type annotation: type ").append(obj).append(" is not a Map(-like) type").toString());
                JavaType javatype1;
                JavaType javatype2;
                Object obj3;
                KeyDeserializer keydeserializer;
                JavaType javatype3;
                try
                {
                    javatype3 = ((MapLikeType)obj).narrowKey(class2);
                }
                catch(IllegalArgumentException illegalargumentexception1)
                {
                    throw new JsonMappingException((new StringBuilder()).append("Failed to narrow key type ").append(obj).append(" with key-type annotation (").append(class2.getName()).append("): ").append(illegalargumentexception1.getMessage()).toString(), null, illegalargumentexception1);
                }
                obj1 = javatype3;
            } else
            {
                obj1 = obj;
            }
            javatype1 = ((JavaType) (obj1)).getKeyType();
            if(javatype1 != null && javatype1.getValueHandler() == null)
            {
                obj3 = annotationintrospector.findKeyDeserializer(annotated);
                if(obj3 != null)
                {
                    keydeserializer = deserializationcontext.keyDeserializerInstance(annotated, obj3);
                    if(keydeserializer != null)
                    {
                        obj1 = ((MapLikeType)obj1).withKeyValueHandler(keydeserializer);
                        ((JavaType) (obj1)).getKeyType();
                    }
                }
            }
            class3 = annotationintrospector.findDeserializationContentType(annotated, ((JavaType) (obj1)).getContentType());
            if(class3 != null)
            {
                try
                {
                    javatype2 = ((JavaType) (obj1)).narrowContentsBy(class3);
                }
                catch(IllegalArgumentException illegalargumentexception)
                {
                    throw new JsonMappingException((new StringBuilder()).append("Failed to narrow content type ").append(obj1).append(" with content-type annotation (").append(class3.getName()).append("): ").append(illegalargumentexception.getMessage()).toString(), null, illegalargumentexception);
                }
                obj = javatype2;
            } else
            {
                obj = obj1;
            }
            if(((JavaType) (obj)).getContentType().getValueHandler() == null)
            {
                Object obj2 = annotationintrospector.findContentDeserializer(annotated);
                if(obj2 != null)
                {
                    JsonDeserializer jsondeserializer;
                    if(obj2 instanceof JsonDeserializer)
                    {
                        JsonDeserializer _tmp = (JsonDeserializer)obj2;
                        jsondeserializer = null;
                    } else
                    {
                        Class class4 = _verifyAsClass(obj2, "findContentDeserializer", com/fasterxml/jackson/databind/JsonDeserializer$None);
                        if(class4 != null)
                            jsondeserializer = deserializationcontext.deserializerInstance(annotated, class4);
                        else
                            jsondeserializer = null;
                    }
                    if(jsondeserializer != null)
                        obj = ((JavaType) (obj)).withContentValueHandler(jsondeserializer);
                }
            }
        }
        return ((JavaType) (obj));
    }

    protected JsonDeserializer _createAndCache2(DeserializationContext deserializationcontext, DeserializerFactory deserializerfactory, JavaType javatype)
        throws JsonMappingException
    {
        JsonDeserializer jsondeserializer;
        JsonDeserializer jsondeserializer1;
        try
        {
            jsondeserializer = _createDeserializer(deserializationcontext, deserializerfactory, javatype);
        }
        catch(IllegalArgumentException illegalargumentexception)
        {
            throw new JsonMappingException(illegalargumentexception.getMessage(), null, illegalargumentexception);
        }
        jsondeserializer1 = jsondeserializer;
        if(jsondeserializer1 == null)
        {
            jsondeserializer1 = null;
        } else
        {
            boolean flag = jsondeserializer1 instanceof ResolvableDeserializer;
            boolean flag1 = jsondeserializer1.isCachable();
            if(flag)
            {
                _incompleteDeserializers.put(javatype, jsondeserializer1);
                ((ResolvableDeserializer)jsondeserializer1).resolve(deserializationcontext);
                _incompleteDeserializers.remove(javatype);
            }
            if(flag1)
            {
                _cachedDeserializers.put(javatype, jsondeserializer1);
                return jsondeserializer1;
            }
        }
        return jsondeserializer1;
    }

    protected JsonDeserializer _createAndCacheValueDeserializer(DeserializationContext deserializationcontext, DeserializerFactory deserializerfactory, JavaType javatype)
        throws JsonMappingException
    {
        HashMap hashmap = _incompleteDeserializers;
        hashmap;
        JVM INSTR monitorenter ;
        JsonDeserializer jsondeserializer = _findCachedDeserializer(javatype);
        if(jsondeserializer == null)
            break MISSING_BLOCK_LABEL_27;
        hashmap;
        JVM INSTR monitorexit ;
        return jsondeserializer;
        int i = _incompleteDeserializers.size();
        if(i <= 0)
            break MISSING_BLOCK_LABEL_73;
        JsonDeserializer jsondeserializer1 = (JsonDeserializer)_incompleteDeserializers.get(javatype);
        if(jsondeserializer1 == null)
            break MISSING_BLOCK_LABEL_73;
        hashmap;
        JVM INSTR monitorexit ;
        return jsondeserializer1;
        Exception exception;
        exception;
        hashmap;
        JVM INSTR monitorexit ;
        throw exception;
        JsonDeserializer jsondeserializer2 = _createAndCache2(deserializationcontext, deserializerfactory, javatype);
        if(i != 0)
            break MISSING_BLOCK_LABEL_104;
        if(_incompleteDeserializers.size() > 0)
            _incompleteDeserializers.clear();
        hashmap;
        JVM INSTR monitorexit ;
        return jsondeserializer2;
        Exception exception1;
        exception1;
        if(i != 0)
            break MISSING_BLOCK_LABEL_134;
        if(_incompleteDeserializers.size() > 0)
            _incompleteDeserializers.clear();
        throw exception1;
    }

    protected JsonDeserializer _createDeserializer(DeserializationContext deserializationcontext, DeserializerFactory deserializerfactory, JavaType javatype)
        throws JsonMappingException
    {
        DeserializationConfig deserializationconfig = deserializationcontext.getConfig();
        if(javatype.isAbstract() || javatype.isMapLikeType() || javatype.isCollectionLikeType())
            javatype = deserializerfactory.mapAbstractType(deserializationconfig, javatype);
        BeanDescription beandescription = deserializationconfig.introspect(javatype);
        JsonDeserializer jsondeserializer = findDeserializerFromAnnotation(deserializationcontext, beandescription.getClassInfo());
        if(jsondeserializer != null)
            return jsondeserializer;
        JavaType javatype1 = modifyTypeByAnnotation(deserializationcontext, beandescription.getClassInfo(), javatype);
        if(javatype1 != javatype)
        {
            beandescription = deserializationconfig.introspect(javatype1);
            javatype = javatype1;
        }
        Class class1 = beandescription.findPOJOBuilder();
        if(class1 != null)
            return deserializerfactory.createBuilderBasedDeserializer(deserializationcontext, javatype, beandescription, class1);
        Converter converter = beandescription.findDeserializationConverter();
        if(converter == null)
            return _createDeserializer2(deserializationcontext, deserializerfactory, javatype, beandescription);
        JavaType javatype2 = converter.getInputType(deserializationcontext.getTypeFactory());
        if(!javatype2.hasRawClass(javatype.getRawClass()))
            beandescription = deserializationconfig.introspect(javatype2);
        return new StdDelegatingDeserializer(converter, javatype2, _createDeserializer2(deserializationcontext, deserializerfactory, javatype2, beandescription));
    }

    protected JsonDeserializer _createDeserializer2(DeserializationContext deserializationcontext, DeserializerFactory deserializerfactory, JavaType javatype, BeanDescription beandescription)
        throws JsonMappingException
    {
        DeserializationConfig deserializationconfig = deserializationcontext.getConfig();
        if(javatype.isEnumType())
            return deserializerfactory.createEnumDeserializer(deserializationcontext, javatype, beandescription);
        if(javatype.isContainerType())
        {
            if(javatype.isArrayType())
                return deserializerfactory.createArrayDeserializer(deserializationcontext, (ArrayType)javatype, beandescription);
            if(javatype.isMapLikeType())
            {
                MapLikeType mapliketype = (MapLikeType)javatype;
                if(mapliketype.isTrueMapType())
                    return deserializerfactory.createMapDeserializer(deserializationcontext, (MapType)mapliketype, beandescription);
                else
                    return deserializerfactory.createMapLikeDeserializer(deserializationcontext, mapliketype, beandescription);
            }
            if(javatype.isCollectionLikeType())
            {
                com.fasterxml.jackson.annotation.JsonFormat.Value value = beandescription.findExpectedFormat(null);
                if(value == null || value.getShape() != com.fasterxml.jackson.annotation.JsonFormat.Shape.OBJECT)
                {
                    CollectionLikeType collectionliketype = (CollectionLikeType)javatype;
                    if(collectionliketype.isTrueCollectionType())
                        return deserializerfactory.createCollectionDeserializer(deserializationcontext, (CollectionType)collectionliketype, beandescription);
                    else
                        return deserializerfactory.createCollectionLikeDeserializer(deserializationcontext, collectionliketype, beandescription);
                }
            }
        }
        if(com/fasterxml/jackson/databind/JsonNode.isAssignableFrom(javatype.getRawClass()))
            return deserializerfactory.createTreeDeserializer(deserializationconfig, javatype, beandescription);
        else
            return deserializerfactory.createBeanDeserializer(deserializationcontext, javatype, beandescription);
    }

    protected JsonDeserializer _findCachedDeserializer(JavaType javatype)
    {
        if(javatype == null)
            throw new IllegalArgumentException("Null JavaType passed");
        else
            return (JsonDeserializer)_cachedDeserializers.get(javatype);
    }

    protected KeyDeserializer _handleUnknownKeyDeserializer(JavaType javatype)
        throws JsonMappingException
    {
        throw new JsonMappingException((new StringBuilder()).append("Can not find a (Map) Key deserializer for type ").append(javatype).toString());
    }

    protected JsonDeserializer _handleUnknownValueDeserializer(JavaType javatype)
        throws JsonMappingException
    {
        if(!ClassUtil.isConcrete(javatype.getRawClass()))
            throw new JsonMappingException((new StringBuilder()).append("Can not find a Value deserializer for abstract type ").append(javatype).toString());
        else
            throw new JsonMappingException((new StringBuilder()).append("Can not find a Value deserializer for type ").append(javatype).toString());
    }

    public int cachedDeserializersCount()
    {
        return _cachedDeserializers.size();
    }

    protected Converter findConverter(DeserializationContext deserializationcontext, Annotated annotated)
        throws JsonMappingException
    {
        Object obj = deserializationcontext.getAnnotationIntrospector().findDeserializationConverter(annotated);
        if(obj == null)
            return null;
        else
            return deserializationcontext.converterInstance(annotated, obj);
    }

    protected JsonDeserializer findConvertingDeserializer(DeserializationContext deserializationcontext, Annotated annotated, JsonDeserializer jsondeserializer)
        throws JsonMappingException
    {
        Converter converter = findConverter(deserializationcontext, annotated);
        if(converter == null)
            return jsondeserializer;
        else
            return new StdDelegatingDeserializer(converter, converter.getInputType(deserializationcontext.getTypeFactory()), jsondeserializer);
    }

    protected JsonDeserializer findDeserializerFromAnnotation(DeserializationContext deserializationcontext, Annotated annotated)
        throws JsonMappingException
    {
        Object obj = deserializationcontext.getAnnotationIntrospector().findDeserializer(annotated);
        if(obj == null)
            return null;
        else
            return findConvertingDeserializer(deserializationcontext, annotated, deserializationcontext.deserializerInstance(annotated, obj));
    }

    public KeyDeserializer findKeyDeserializer(DeserializationContext deserializationcontext, DeserializerFactory deserializerfactory, JavaType javatype)
        throws JsonMappingException
    {
        KeyDeserializer keydeserializer = deserializerfactory.createKeyDeserializer(deserializationcontext, javatype);
        if(keydeserializer == null)
            keydeserializer = _handleUnknownKeyDeserializer(javatype);
        else
        if(keydeserializer instanceof ResolvableDeserializer)
        {
            ((ResolvableDeserializer)keydeserializer).resolve(deserializationcontext);
            return keydeserializer;
        }
        return keydeserializer;
    }

    public JsonDeserializer findValueDeserializer(DeserializationContext deserializationcontext, DeserializerFactory deserializerfactory, JavaType javatype)
        throws JsonMappingException
    {
        JsonDeserializer jsondeserializer = _findCachedDeserializer(javatype);
        if(jsondeserializer == null)
        {
            jsondeserializer = _createAndCacheValueDeserializer(deserializationcontext, deserializerfactory, javatype);
            if(jsondeserializer == null)
                jsondeserializer = _handleUnknownValueDeserializer(javatype);
        }
        return jsondeserializer;
    }

    public void flushCachedDeserializers()
    {
        _cachedDeserializers.clear();
    }

    public boolean hasValueDeserializerFor(DeserializationContext deserializationcontext, DeserializerFactory deserializerfactory, JavaType javatype)
        throws JsonMappingException
    {
        JsonDeserializer jsondeserializer = _findCachedDeserializer(javatype);
        if(jsondeserializer == null)
            jsondeserializer = _createAndCacheValueDeserializer(deserializationcontext, deserializerfactory, javatype);
        return jsondeserializer != null;
    }

    Object writeReplace()
    {
        _incompleteDeserializers.clear();
        return this;
    }

    private static final long serialVersionUID = 1L;
    protected final ConcurrentHashMap _cachedDeserializers = new ConcurrentHashMap(64, 0.75F, 2);
    protected final HashMap _incompleteDeserializers = new HashMap(8);
}
