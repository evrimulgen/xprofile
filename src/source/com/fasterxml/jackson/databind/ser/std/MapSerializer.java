// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.fasterxml.jackson.databind.ser.std;

import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatVisitorWrapper;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonMapFormatVisitor;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.fasterxml.jackson.databind.ser.*;
import com.fasterxml.jackson.databind.ser.impl.PropertySerializerMap;
import com.fasterxml.jackson.databind.type.TypeFactory;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.*;

// Referenced classes of package com.fasterxml.jackson.databind.ser.std:
//            MapProperty

public class MapSerializer extends ContainerSerializer
    implements ContextualSerializer
{

    protected MapSerializer(MapSerializer mapserializer, BeanProperty beanproperty, JsonSerializer jsonserializer, JsonSerializer jsonserializer1, HashSet hashset)
    {
        super(java/util/Map, false);
        _ignoredEntries = hashset;
        _keyType = mapserializer._keyType;
        _valueType = mapserializer._valueType;
        _valueTypeIsStatic = mapserializer._valueTypeIsStatic;
        _valueTypeSerializer = mapserializer._valueTypeSerializer;
        _keySerializer = jsonserializer;
        _valueSerializer = jsonserializer1;
        _dynamicValueSerializers = mapserializer._dynamicValueSerializers;
        _property = beanproperty;
        _filterId = mapserializer._filterId;
    }

    protected MapSerializer(MapSerializer mapserializer, TypeSerializer typeserializer)
    {
        super(java/util/Map, false);
        _ignoredEntries = mapserializer._ignoredEntries;
        _keyType = mapserializer._keyType;
        _valueType = mapserializer._valueType;
        _valueTypeIsStatic = mapserializer._valueTypeIsStatic;
        _valueTypeSerializer = typeserializer;
        _keySerializer = mapserializer._keySerializer;
        _valueSerializer = mapserializer._valueSerializer;
        _dynamicValueSerializers = mapserializer._dynamicValueSerializers;
        _property = mapserializer._property;
        _filterId = mapserializer._filterId;
    }

    protected MapSerializer(MapSerializer mapserializer, Object obj)
    {
        super(java/util/Map, false);
        _ignoredEntries = mapserializer._ignoredEntries;
        _keyType = mapserializer._keyType;
        _valueType = mapserializer._valueType;
        _valueTypeIsStatic = mapserializer._valueTypeIsStatic;
        _valueTypeSerializer = mapserializer._valueTypeSerializer;
        _keySerializer = mapserializer._keySerializer;
        _valueSerializer = mapserializer._valueSerializer;
        _dynamicValueSerializers = mapserializer._dynamicValueSerializers;
        _property = mapserializer._property;
        _filterId = obj;
    }

    protected MapSerializer(HashSet hashset, JavaType javatype, JavaType javatype1, boolean flag, TypeSerializer typeserializer, JsonSerializer jsonserializer, JsonSerializer jsonserializer1)
    {
        super(java/util/Map, false);
        _ignoredEntries = hashset;
        _keyType = javatype;
        _valueType = javatype1;
        _valueTypeIsStatic = flag;
        _valueTypeSerializer = typeserializer;
        _keySerializer = jsonserializer;
        _valueSerializer = jsonserializer1;
        _dynamicValueSerializers = PropertySerializerMap.emptyMap();
        _property = null;
        _filterId = null;
    }

    public static MapSerializer construct(String as[], JavaType javatype, boolean flag, TypeSerializer typeserializer, JsonSerializer jsonserializer, JsonSerializer jsonserializer1)
    {
        return construct(as, javatype, flag, typeserializer, jsonserializer, jsonserializer1, null);
    }

    public static MapSerializer construct(String as[], JavaType javatype, boolean flag, TypeSerializer typeserializer, JsonSerializer jsonserializer, JsonSerializer jsonserializer1, Object obj)
    {
        HashSet hashset = toSet(as);
        JavaType javatype1;
        JavaType javatype2;
        boolean flag2;
        MapSerializer mapserializer;
        if(javatype == null)
        {
            javatype2 = UNSPECIFIED_TYPE;
            javatype1 = javatype2;
        } else
        {
            javatype1 = javatype.getKeyType();
            javatype2 = javatype.getContentType();
        }
        if(!flag)
        {
            boolean flag1 = false;
            if(javatype2 != null)
            {
                boolean flag3 = javatype2.isFinal();
                flag1 = false;
                if(flag3)
                    flag1 = true;
            }
            flag2 = flag1;
        } else
        if(javatype2.getRawClass() == java/lang/Object)
            flag2 = false;
        else
            flag2 = flag;
        mapserializer = new MapSerializer(hashset, javatype1, javatype2, flag2, typeserializer, jsonserializer, jsonserializer1);
        if(obj != null)
            mapserializer = mapserializer.withFilterId(obj);
        return mapserializer;
    }

    private static HashSet toSet(String as[])
    {
        HashSet hashset;
        if(as == null || as.length == 0)
        {
            hashset = null;
        } else
        {
            hashset = new HashSet(as.length);
            int i = as.length;
            int j = 0;
            while(j < i) 
            {
                hashset.add(as[j]);
                j++;
            }
        }
        return hashset;
    }

    protected final JsonSerializer _findAndAddDynamic(PropertySerializerMap propertyserializermap, JavaType javatype, SerializerProvider serializerprovider)
        throws JsonMappingException
    {
        com.fasterxml.jackson.databind.ser.impl.PropertySerializerMap.SerializerAndMapResult serializerandmapresult = propertyserializermap.findAndAddSecondarySerializer(javatype, serializerprovider, _property);
        if(propertyserializermap != serializerandmapresult.map)
            _dynamicValueSerializers = serializerandmapresult.map;
        return serializerandmapresult.serializer;
    }

    protected final JsonSerializer _findAndAddDynamic(PropertySerializerMap propertyserializermap, Class class1, SerializerProvider serializerprovider)
        throws JsonMappingException
    {
        com.fasterxml.jackson.databind.ser.impl.PropertySerializerMap.SerializerAndMapResult serializerandmapresult = propertyserializermap.findAndAddSecondarySerializer(class1, serializerprovider, _property);
        if(propertyserializermap != serializerandmapresult.map)
            _dynamicValueSerializers = serializerandmapresult.map;
        return serializerandmapresult.serializer;
    }

    protected Map _orderEntries(Map map)
    {
        if(map instanceof SortedMap)
            return map;
        else
            return new TreeMap(map);
    }

    public volatile ContainerSerializer _withValueTypeSerializer(TypeSerializer typeserializer)
    {
        return _withValueTypeSerializer(typeserializer);
    }

    public MapSerializer _withValueTypeSerializer(TypeSerializer typeserializer)
    {
        return new MapSerializer(this, typeserializer);
    }

    public void acceptJsonFormatVisitor(JsonFormatVisitorWrapper jsonformatvisitorwrapper, JavaType javatype)
        throws JsonMappingException
    {
        JsonMapFormatVisitor jsonmapformatvisitor;
        if(jsonformatvisitorwrapper == null)
            jsonmapformatvisitor = null;
        else
            jsonmapformatvisitor = jsonformatvisitorwrapper.expectMapFormat(javatype);
        if(jsonmapformatvisitor != null)
        {
            jsonmapformatvisitor.keyFormat(_keySerializer, _keyType);
            JsonSerializer jsonserializer = _valueSerializer;
            if(jsonserializer == null)
                jsonserializer = _findAndAddDynamic(_dynamicValueSerializers, _valueType, jsonformatvisitorwrapper.getProvider());
            jsonmapformatvisitor.valueFormat(jsonserializer, _valueType);
        }
    }

    public JsonSerializer createContextual(SerializerProvider serializerprovider, BeanProperty beanproperty)
        throws JsonMappingException
    {
        if(beanproperty == null) goto _L2; else goto _L1
_L1:
        com.fasterxml.jackson.databind.introspect.AnnotatedMember annotatedmember = beanproperty.getMember();
        if(annotatedmember == null) goto _L2; else goto _L3
_L3:
        JsonSerializer jsonserializer;
        JsonSerializer jsonserializer1;
        AnnotationIntrospector annotationintrospector1 = serializerprovider.getAnnotationIntrospector();
        Object obj1 = annotationintrospector1.findKeySerializer(annotatedmember);
        JsonSerializer jsonserializer2;
        JsonSerializer jsonserializer3;
        JsonSerializer jsonserializer4;
        JsonSerializer jsonserializer5;
        HashSet hashset;
        AnnotationIntrospector annotationintrospector;
        HashSet hashset1;
        MapSerializer mapserializer;
        Object obj;
        String as[];
        int i;
        int j;
        JsonSerializer jsonserializer6;
        Object obj2;
        if(obj1 != null)
            jsonserializer6 = serializerprovider.serializerInstance(annotatedmember, obj1);
        else
            jsonserializer6 = null;
        obj2 = annotationintrospector1.findContentSerializer(annotatedmember);
        if(obj2 != null)
        {
            JsonSerializer jsonserializer7 = serializerprovider.serializerInstance(annotatedmember, obj2);
            JsonSerializer jsonserializer8 = jsonserializer6;
            jsonserializer = jsonserializer7;
            jsonserializer1 = jsonserializer8;
        } else
        {
            jsonserializer1 = jsonserializer6;
            jsonserializer = null;
        }
_L5:
label0:
        {
            if(jsonserializer == null)
                jsonserializer = _valueSerializer;
            jsonserializer2 = findConvertingContentSerializer(serializerprovider, beanproperty, jsonserializer);
            if(jsonserializer2 == null)
            {
                if(_valueTypeIsStatic && _valueType.getRawClass() != java/lang/Object || hasContentTypeAnnotation(serializerprovider, beanproperty))
                    jsonserializer3 = serializerprovider.findValueSerializer(_valueType, beanproperty);
                else
                    jsonserializer3 = jsonserializer2;
            } else
            {
                jsonserializer3 = serializerprovider.handleSecondaryContextualization(jsonserializer2, beanproperty);
            }
            if(jsonserializer1 == null)
                jsonserializer4 = _keySerializer;
            else
                jsonserializer4 = jsonserializer1;
            if(jsonserializer4 == null)
                jsonserializer5 = serializerprovider.findKeySerializer(_keyType, beanproperty);
            else
                jsonserializer5 = serializerprovider.handleSecondaryContextualization(jsonserializer4, beanproperty);
            hashset = _ignoredEntries;
            annotationintrospector = serializerprovider.getAnnotationIntrospector();
            if(annotationintrospector != null && beanproperty != null)
            {
                as = annotationintrospector.findPropertiesToIgnore(beanproperty.getMember());
                if(as != null)
                {
                    if(hashset == null)
                        hashset1 = new HashSet();
                    else
                        hashset1 = new HashSet(hashset);
                    i = as.length;
                    for(j = 0; j < i; j++)
                        hashset1.add(as[j]);

                    break label0;
                }
            }
            hashset1 = hashset;
        }
        mapserializer = withResolved(beanproperty, jsonserializer5, jsonserializer3, hashset1);
        if(beanproperty != null)
        {
            obj = annotationintrospector.findFilterId(beanproperty.getMember());
            if(obj != null)
                mapserializer = mapserializer.withFilterId(obj);
        }
        return mapserializer;
_L2:
        jsonserializer = null;
        jsonserializer1 = null;
        if(true) goto _L5; else goto _L4
_L4:
    }

    public JsonSerializer getContentSerializer()
    {
        return _valueSerializer;
    }

    public JavaType getContentType()
    {
        return _valueType;
    }

    public JsonSerializer getKeySerializer()
    {
        return _keySerializer;
    }

    public JsonNode getSchema(SerializerProvider serializerprovider, Type type)
    {
        return createSchemaNode("object", true);
    }

    public volatile boolean hasSingleElement(Object obj)
    {
        return hasSingleElement((Map)obj);
    }

    public boolean hasSingleElement(Map map)
    {
        return map.size() == 1;
    }

    public volatile boolean isEmpty(Object obj)
    {
        return isEmpty((Map)obj);
    }

    public boolean isEmpty(Map map)
    {
        return map == null || map.isEmpty();
    }

    public volatile void serialize(Object obj, JsonGenerator jsongenerator, SerializerProvider serializerprovider)
        throws IOException, JsonGenerationException
    {
        serialize((Map)obj, jsongenerator, serializerprovider);
    }

    public void serialize(Map map, JsonGenerator jsongenerator, SerializerProvider serializerprovider)
        throws IOException, JsonGenerationException
    {
        jsongenerator.writeStartObject();
        if(!map.isEmpty())
        {
            if(_filterId != null)
            {
                serializeFilteredFields(map, jsongenerator, serializerprovider, findPropertyFilter(serializerprovider, _filterId, map));
                return;
            }
            if(serializerprovider.isEnabled(SerializationFeature.ORDER_MAP_ENTRIES_BY_KEYS))
                map = _orderEntries(map);
            if(_valueSerializer != null)
                serializeFieldsUsing(map, jsongenerator, serializerprovider, _valueSerializer);
            else
                serializeFields(map, jsongenerator, serializerprovider);
        }
        jsongenerator.writeEndObject();
    }

    public void serializeFields(Map map, JsonGenerator jsongenerator, SerializerProvider serializerprovider)
        throws IOException, JsonGenerationException
    {
        if(_valueTypeSerializer == null) goto _L2; else goto _L1
_L1:
        serializeTypedFields(map, jsongenerator, serializerprovider);
_L4:
        return;
_L2:
        JsonSerializer jsonserializer;
        HashSet hashset;
        boolean flag;
        PropertySerializerMap propertyserializermap1;
        Object obj;
        Object obj1;
        jsonserializer = _keySerializer;
        hashset = _ignoredEntries;
        PropertySerializerMap propertyserializermap;
        Iterator iterator;
        java.util.Map.Entry entry;
        if(!serializerprovider.isEnabled(SerializationFeature.WRITE_NULL_MAP_VALUES))
            flag = true;
        else
            flag = false;
        propertyserializermap = _dynamicValueSerializers;
        iterator = map.entrySet().iterator();
        propertyserializermap1 = propertyserializermap;
_L5:
        if(!iterator.hasNext()) goto _L4; else goto _L3
_L3:
        entry = (java.util.Map.Entry)iterator.next();
        obj = entry.getValue();
        obj1 = entry.getKey();
        if(obj1 != null)
            continue; /* Loop/switch isn't completed */
        serializerprovider.findNullKeySerializer(_keyType, _property).serialize(null, jsongenerator, serializerprovider);
_L7:
        PropertySerializerMap propertyserializermap2;
        if(obj == null)
        {
            serializerprovider.defaultSerializeNull(jsongenerator);
            propertyserializermap2 = propertyserializermap1;
        } else
        {
            Class class1 = obj.getClass();
            JsonSerializer jsonserializer1 = propertyserializermap1.serializerFor(class1);
            JsonSerializer jsonserializer2;
            if(jsonserializer1 == null)
            {
                Exception exception;
                JsonSerializer jsonserializer3;
                PropertySerializerMap propertyserializermap3;
                JsonSerializer jsonserializer4;
                if(_valueType.hasGenericTypes())
                    jsonserializer3 = _findAndAddDynamic(propertyserializermap1, serializerprovider.constructSpecializedType(_valueType, class1), serializerprovider);
                else
                    jsonserializer3 = _findAndAddDynamic(propertyserializermap1, class1, serializerprovider);
                propertyserializermap3 = _dynamicValueSerializers;
                jsonserializer4 = jsonserializer3;
                propertyserializermap2 = propertyserializermap3;
                jsonserializer2 = jsonserializer4;
            } else
            {
                propertyserializermap2 = propertyserializermap1;
                jsonserializer2 = jsonserializer1;
            }
            try
            {
                jsonserializer2.serialize(obj, jsongenerator, serializerprovider);
            }
            // Misplaced declaration of an exception variable
            catch(Exception exception)
            {
                wrapAndThrow(serializerprovider, exception, map, (new StringBuilder()).append("").append(obj1).toString());
            }
        }
        propertyserializermap1 = propertyserializermap2;
          goto _L5
          goto _L4
        if(flag && obj == null || hashset != null && hashset.contains(obj1)) goto _L5; else goto _L6
_L6:
        jsonserializer.serialize(obj1, jsongenerator, serializerprovider);
          goto _L7
    }

    protected void serializeFieldsUsing(Map map, JsonGenerator jsongenerator, SerializerProvider serializerprovider, JsonSerializer jsonserializer)
        throws IOException, JsonGenerationException
    {
        JsonSerializer jsonserializer1;
        HashSet hashset;
        TypeSerializer typeserializer;
        boolean flag;
        Object obj;
        Object obj1;
        jsonserializer1 = _keySerializer;
        hashset = _ignoredEntries;
        typeserializer = _valueTypeSerializer;
        Iterator iterator;
        java.util.Map.Entry entry;
        if(!serializerprovider.isEnabled(SerializationFeature.WRITE_NULL_MAP_VALUES))
            flag = true;
        else
            flag = false;
        iterator = map.entrySet().iterator();
_L2:
        if(!iterator.hasNext())
            break; /* Loop/switch isn't completed */
        entry = (java.util.Map.Entry)iterator.next();
        obj = entry.getValue();
        obj1 = entry.getKey();
        if(obj1 == null)
        {
            serializerprovider.findNullKeySerializer(_keyType, _property).serialize(null, jsongenerator, serializerprovider);
        } else
        {
            if(flag && obj == null || hashset != null && hashset.contains(obj1))
                continue; /* Loop/switch isn't completed */
            jsonserializer1.serialize(obj1, jsongenerator, serializerprovider);
        }
        if(obj == null)
        {
            serializerprovider.defaultSerializeNull(jsongenerator);
            continue; /* Loop/switch isn't completed */
        }
        if(typeserializer == null)
        {
            try
            {
                jsonserializer.serialize(obj, jsongenerator, serializerprovider);
            }
            catch(Exception exception)
            {
                wrapAndThrow(serializerprovider, exception, map, (new StringBuilder()).append("").append(obj1).toString());
            }
            continue; /* Loop/switch isn't completed */
        }
        jsonserializer.serializeWithType(obj, jsongenerator, serializerprovider, typeserializer);
        if(true) goto _L2; else goto _L1
_L1:
    }

    public void serializeFilteredFields(Map map, JsonGenerator jsongenerator, SerializerProvider serializerprovider, PropertyFilter propertyfilter)
        throws IOException, JsonGenerationException
    {
        HashSet hashset;
        boolean flag;
        PropertySerializerMap propertyserializermap1;
        Object obj;
        Object obj1;
        JsonSerializer jsonserializer;
        hashset = _ignoredEntries;
        PropertySerializerMap propertyserializermap;
        MapProperty mapproperty;
        Iterator iterator;
        java.util.Map.Entry entry;
        JsonSerializer jsonserializer5;
        if(!serializerprovider.isEnabled(SerializationFeature.WRITE_NULL_MAP_VALUES))
            flag = true;
        else
            flag = false;
        propertyserializermap = _dynamicValueSerializers;
        mapproperty = new MapProperty(_valueTypeSerializer);
        iterator = map.entrySet().iterator();
        propertyserializermap1 = propertyserializermap;
_L2:
        if(!iterator.hasNext())
            break MISSING_BLOCK_LABEL_324;
        entry = (java.util.Map.Entry)iterator.next();
        obj = entry.getKey();
        obj1 = entry.getValue();
        if(obj != null)
            continue; /* Loop/switch isn't completed */
        jsonserializer = serializerprovider.findNullKeySerializer(_keyType, _property);
_L5:
        PropertySerializerMap propertyserializermap2;
        JsonSerializer jsonserializer2;
        if(obj1 == null)
        {
            jsonserializer5 = serializerprovider.getDefaultNullValueSerializer();
            propertyserializermap2 = propertyserializermap1;
            jsonserializer2 = jsonserializer5;
        } else
        {
            Class class1 = obj1.getClass();
            JsonSerializer jsonserializer1 = propertyserializermap1.serializerFor(class1);
            Exception exception;
            if(jsonserializer1 == null)
            {
                JsonSerializer jsonserializer3;
                PropertySerializerMap propertyserializermap3;
                JsonSerializer jsonserializer4;
                if(_valueType.hasGenericTypes())
                    jsonserializer3 = _findAndAddDynamic(propertyserializermap1, serializerprovider.constructSpecializedType(_valueType, class1), serializerprovider);
                else
                    jsonserializer3 = _findAndAddDynamic(propertyserializermap1, class1, serializerprovider);
                propertyserializermap3 = _dynamicValueSerializers;
                jsonserializer4 = jsonserializer3;
                propertyserializermap2 = propertyserializermap3;
                jsonserializer2 = jsonserializer4;
            } else
            {
                propertyserializermap2 = propertyserializermap1;
                jsonserializer2 = jsonserializer1;
            }
        }
        mapproperty.reset(obj, obj1, jsonserializer, jsonserializer2);
        try
        {
            propertyfilter.serializeAsField(map, jsongenerator, serializerprovider, mapproperty);
        }
        // Misplaced declaration of an exception variable
        catch(Exception exception)
        {
            wrapAndThrow(serializerprovider, exception, map, (new StringBuilder()).append("").append(obj).toString());
        }
        propertyserializermap1 = propertyserializermap2;
        if(true) goto _L2; else goto _L1
_L1:
        if(flag && obj1 == null || hashset != null && hashset.contains(obj)) goto _L4; else goto _L3
_L3:
        break MISSING_BLOCK_LABEL_196;
_L4:
        break; /* Loop/switch isn't completed */
        jsonserializer = _keySerializer;
          goto _L5
    }

    protected void serializeTypedFields(Map map, JsonGenerator jsongenerator, SerializerProvider serializerprovider)
        throws IOException, JsonGenerationException
    {
        JsonSerializer jsonserializer = _keySerializer;
        HashSet hashset = _ignoredEntries;
        boolean flag;
        Iterator iterator;
        Class class1;
        JsonSerializer jsonserializer1;
        if(!serializerprovider.isEnabled(SerializationFeature.WRITE_NULL_MAP_VALUES))
            flag = true;
        else
            flag = false;
        iterator = map.entrySet().iterator();
        class1 = null;
        jsonserializer1 = null;
        do
        {
            if(iterator.hasNext())
            {
                java.util.Map.Entry entry = (java.util.Map.Entry)iterator.next();
                Object obj = entry.getValue();
                Object obj1 = entry.getKey();
                JsonSerializer jsonserializer3;
                Class class3;
                if(obj1 == null)
                {
                    serializerprovider.findNullKeySerializer(_keyType, _property).serialize(null, jsongenerator, serializerprovider);
                } else
                {
                    if(flag && obj == null || hashset != null && hashset.contains(obj1))
                        continue;
                    jsonserializer.serialize(obj1, jsongenerator, serializerprovider);
                }
                if(obj == null)
                {
                    serializerprovider.defaultSerializeNull(jsongenerator);
                    class3 = class1;
                    jsonserializer3 = jsonserializer1;
                } else
                {
                    Class class2 = obj.getClass();
                    if(class2 == class1)
                    {
                        class3 = class1;
                        jsonserializer3 = jsonserializer1;
                    } else
                    {
                        JsonSerializer jsonserializer2;
                        if(_valueType.hasGenericTypes())
                            jsonserializer2 = serializerprovider.findValueSerializer(serializerprovider.constructSpecializedType(_valueType, class2), _property);
                        else
                            jsonserializer2 = serializerprovider.findValueSerializer(class2, _property);
                        jsonserializer1 = jsonserializer2;
                        jsonserializer3 = jsonserializer2;
                        class3 = class2;
                    }
                    try
                    {
                        jsonserializer1.serializeWithType(obj, jsongenerator, serializerprovider, _valueTypeSerializer);
                    }
                    catch(Exception exception)
                    {
                        wrapAndThrow(serializerprovider, exception, map, (new StringBuilder()).append("").append(obj1).toString());
                    }
                }
                jsonserializer1 = jsonserializer3;
                class1 = class3;
                continue;
            }
            return;
        } while(true);
    }

    public volatile void serializeWithType(Object obj, JsonGenerator jsongenerator, SerializerProvider serializerprovider, TypeSerializer typeserializer)
        throws IOException, JsonProcessingException
    {
        serializeWithType((Map)obj, jsongenerator, serializerprovider, typeserializer);
    }

    public void serializeWithType(Map map, JsonGenerator jsongenerator, SerializerProvider serializerprovider, TypeSerializer typeserializer)
        throws IOException, JsonGenerationException
    {
        typeserializer.writeTypePrefixForObject(map, jsongenerator);
        if(!map.isEmpty())
        {
            if(serializerprovider.isEnabled(SerializationFeature.ORDER_MAP_ENTRIES_BY_KEYS))
                map = _orderEntries(map);
            if(_valueSerializer != null)
                serializeFieldsUsing(map, jsongenerator, serializerprovider, _valueSerializer);
            else
                serializeFields(map, jsongenerator, serializerprovider);
        }
        typeserializer.writeTypeSuffixForObject(map, jsongenerator);
    }

    public MapSerializer withFilterId(Object obj)
    {
        if(_filterId == obj)
            return this;
        else
            return new MapSerializer(this, obj);
    }

    public MapSerializer withResolved(BeanProperty beanproperty, JsonSerializer jsonserializer, JsonSerializer jsonserializer1, HashSet hashset)
    {
        return new MapSerializer(this, beanproperty, jsonserializer, jsonserializer1, hashset);
    }

    protected static final JavaType UNSPECIFIED_TYPE = TypeFactory.unknownType();
    protected PropertySerializerMap _dynamicValueSerializers;
    protected final Object _filterId;
    protected final HashSet _ignoredEntries;
    protected JsonSerializer _keySerializer;
    protected final JavaType _keyType;
    protected final BeanProperty _property;
    protected JsonSerializer _valueSerializer;
    protected final JavaType _valueType;
    protected final boolean _valueTypeIsStatic;
    protected final TypeSerializer _valueTypeSerializer;

}
