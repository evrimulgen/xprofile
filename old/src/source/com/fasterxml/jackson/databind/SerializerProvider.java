// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.fasterxml.jackson.databind;

import com.fasterxml.jackson.annotation.ObjectIdGenerator;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.cfg.ContextAttributes;
import com.fasterxml.jackson.databind.cfg.MapperConfig;
import com.fasterxml.jackson.databind.introspect.Annotated;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.ResolvableSerializer;
import com.fasterxml.jackson.databind.ser.SerializerCache;
import com.fasterxml.jackson.databind.ser.SerializerFactory;
import com.fasterxml.jackson.databind.ser.impl.FailingSerializer;
import com.fasterxml.jackson.databind.ser.impl.ReadOnlyClassToSerializerMap;
import com.fasterxml.jackson.databind.ser.impl.TypeWrappedSerializer;
import com.fasterxml.jackson.databind.ser.impl.UnknownSerializer;
import com.fasterxml.jackson.databind.ser.impl.WritableObjectId;
import com.fasterxml.jackson.databind.ser.std.NullSerializer;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.databind.util.ClassUtil;
import com.fasterxml.jackson.databind.util.RootNameLookup;
import java.io.IOException;
import java.text.DateFormat;
import java.util.*;

// Referenced classes of package com.fasterxml.jackson.databind:
//            DatabindContext, SerializationConfig, JsonMappingException, JavaType, 
//            SerializationFeature, JsonSerializer, BeanProperty, AnnotationIntrospector

public abstract class SerializerProvider extends DatabindContext
{

    public SerializerProvider()
    {
        _unknownTypeSerializer = DEFAULT_UNKNOWN_SERIALIZER;
        _nullValueSerializer = NullSerializer.instance;
        _nullKeySerializer = DEFAULT_NULL_KEY_SERIALIZER;
        _config = null;
        _serializerFactory = null;
        _serializerCache = new SerializerCache();
        _knownSerializers = null;
        _rootNames = new RootNameLookup();
        _serializationView = null;
        _attributes = null;
        _stdNullValueSerializer = true;
    }

    protected SerializerProvider(SerializerProvider serializerprovider, SerializationConfig serializationconfig, SerializerFactory serializerfactory)
    {
        _unknownTypeSerializer = DEFAULT_UNKNOWN_SERIALIZER;
        _nullValueSerializer = NullSerializer.instance;
        _nullKeySerializer = DEFAULT_NULL_KEY_SERIALIZER;
        if(serializationconfig == null)
            throw new NullPointerException();
        _serializerFactory = serializerfactory;
        _config = serializationconfig;
        _serializerCache = serializerprovider._serializerCache;
        _unknownTypeSerializer = serializerprovider._unknownTypeSerializer;
        _keySerializer = serializerprovider._keySerializer;
        _nullValueSerializer = serializerprovider._nullValueSerializer;
        boolean flag;
        if(_nullValueSerializer == DEFAULT_NULL_KEY_SERIALIZER)
            flag = true;
        else
            flag = false;
        _stdNullValueSerializer = flag;
        _nullKeySerializer = serializerprovider._nullKeySerializer;
        _rootNames = serializerprovider._rootNames;
        _knownSerializers = _serializerCache.getReadOnlyLookupMap();
        _serializationView = serializationconfig.getActiveView();
        _attributes = serializationconfig.getAttributes();
    }

    protected JsonSerializer _createAndCacheUntypedSerializer(JavaType javatype)
        throws JsonMappingException
    {
        JsonSerializer jsonserializer;
        try
        {
            jsonserializer = _createUntypedSerializer(javatype);
        }
        catch(IllegalArgumentException illegalargumentexception)
        {
            throw new JsonMappingException(illegalargumentexception.getMessage(), null, illegalargumentexception);
        }
        if(jsonserializer != null)
            _serializerCache.addAndResolveNonTypedSerializer(javatype, jsonserializer, this);
        return jsonserializer;
    }

    protected JsonSerializer _createAndCacheUntypedSerializer(Class class1)
        throws JsonMappingException
    {
        JsonSerializer jsonserializer;
        try
        {
            jsonserializer = _createUntypedSerializer(_config.constructType(class1));
        }
        catch(IllegalArgumentException illegalargumentexception)
        {
            throw new JsonMappingException(illegalargumentexception.getMessage(), null, illegalargumentexception);
        }
        if(jsonserializer != null)
            _serializerCache.addAndResolveNonTypedSerializer(class1, jsonserializer, this);
        return jsonserializer;
    }

    protected JsonSerializer _createUntypedSerializer(JavaType javatype)
        throws JsonMappingException
    {
        return _serializerFactory.createSerializer(this, javatype);
    }

    protected final DateFormat _dateFormat()
    {
        if(_dateFormat != null)
        {
            return _dateFormat;
        } else
        {
            DateFormat dateformat = (DateFormat)_config.getDateFormat().clone();
            _dateFormat = dateformat;
            return dateformat;
        }
    }

    protected JsonSerializer _findExplicitUntypedSerializer(Class class1)
        throws JsonMappingException
    {
        JsonSerializer jsonserializer = _knownSerializers.untypedValueSerializer(class1);
        if(jsonserializer == null)
            if((jsonserializer = _serializerCache.untypedValueSerializer(class1)) == null)
                return _createAndCacheUntypedSerializer(class1);
        return jsonserializer;
    }

    protected JsonSerializer _handleContextualResolvable(JsonSerializer jsonserializer, BeanProperty beanproperty)
        throws JsonMappingException
    {
        if(jsonserializer instanceof ResolvableSerializer)
            ((ResolvableSerializer)jsonserializer).resolve(this);
        return handleSecondaryContextualization(jsonserializer, beanproperty);
    }

    protected JsonSerializer _handleResolvable(JsonSerializer jsonserializer)
        throws JsonMappingException
    {
        if(jsonserializer instanceof ResolvableSerializer)
            ((ResolvableSerializer)jsonserializer).resolve(this);
        return jsonserializer;
    }

    protected void _reportIncompatibleRootType(Object obj, JavaType javatype)
        throws IOException, JsonProcessingException
    {
        if(javatype.isPrimitive() && ClassUtil.wrapperType(javatype.getRawClass()).isAssignableFrom(obj.getClass()))
            return;
        else
            throw new JsonMappingException((new StringBuilder()).append("Incompatible types: declared root type (").append(javatype).append(") vs ").append(obj.getClass().getName()).toString());
    }

    public void defaultSerializeDateKey(long l, JsonGenerator jsongenerator)
        throws IOException, JsonProcessingException
    {
        if(isEnabled(SerializationFeature.WRITE_DATE_KEYS_AS_TIMESTAMPS))
        {
            jsongenerator.writeFieldName(String.valueOf(l));
            return;
        } else
        {
            jsongenerator.writeFieldName(_dateFormat().format(new Date(l)));
            return;
        }
    }

    public void defaultSerializeDateKey(Date date, JsonGenerator jsongenerator)
        throws IOException, JsonProcessingException
    {
        if(isEnabled(SerializationFeature.WRITE_DATE_KEYS_AS_TIMESTAMPS))
        {
            jsongenerator.writeFieldName(String.valueOf(date.getTime()));
            return;
        } else
        {
            jsongenerator.writeFieldName(_dateFormat().format(date));
            return;
        }
    }

    public final void defaultSerializeDateValue(long l, JsonGenerator jsongenerator)
        throws IOException, JsonProcessingException
    {
        if(isEnabled(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS))
        {
            jsongenerator.writeNumber(l);
            return;
        } else
        {
            jsongenerator.writeString(_dateFormat().format(new Date(l)));
            return;
        }
    }

    public final void defaultSerializeDateValue(Date date, JsonGenerator jsongenerator)
        throws IOException, JsonProcessingException
    {
        if(isEnabled(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS))
        {
            jsongenerator.writeNumber(date.getTime());
            return;
        } else
        {
            jsongenerator.writeString(_dateFormat().format(date));
            return;
        }
    }

    public final void defaultSerializeField(String s, Object obj, JsonGenerator jsongenerator)
        throws IOException, JsonProcessingException
    {
        jsongenerator.writeFieldName(s);
        if(obj == null)
        {
            if(_stdNullValueSerializer)
            {
                jsongenerator.writeNull();
                return;
            } else
            {
                _nullValueSerializer.serialize(null, jsongenerator, this);
                return;
            }
        } else
        {
            findTypedValueSerializer(obj.getClass(), true, null).serialize(obj, jsongenerator, this);
            return;
        }
    }

    public final void defaultSerializeNull(JsonGenerator jsongenerator)
        throws IOException, JsonProcessingException
    {
        if(_stdNullValueSerializer)
        {
            jsongenerator.writeNull();
            return;
        } else
        {
            _nullValueSerializer.serialize(null, jsongenerator, this);
            return;
        }
    }

    public final void defaultSerializeValue(Object obj, JsonGenerator jsongenerator)
        throws IOException, JsonProcessingException
    {
        if(obj == null)
        {
            if(_stdNullValueSerializer)
            {
                jsongenerator.writeNull();
                return;
            } else
            {
                _nullValueSerializer.serialize(null, jsongenerator, this);
                return;
            }
        } else
        {
            findTypedValueSerializer(obj.getClass(), true, null).serialize(obj, jsongenerator, this);
            return;
        }
    }

    public JsonSerializer findKeySerializer(JavaType javatype, BeanProperty beanproperty)
        throws JsonMappingException
    {
        return _handleContextualResolvable(_serializerFactory.createKeySerializer(_config, javatype, _keySerializer), beanproperty);
    }

    public JsonSerializer findNullKeySerializer(JavaType javatype, BeanProperty beanproperty)
        throws JsonMappingException
    {
        return _nullKeySerializer;
    }

    public JsonSerializer findNullValueSerializer(BeanProperty beanproperty)
        throws JsonMappingException
    {
        return _nullValueSerializer;
    }

    public abstract WritableObjectId findObjectId(Object obj, ObjectIdGenerator objectidgenerator);

    public JsonSerializer findPrimaryPropertySerializer(JavaType javatype, BeanProperty beanproperty)
        throws JsonMappingException
    {
        JsonSerializer jsonserializer = _knownSerializers.untypedValueSerializer(javatype);
        if(jsonserializer == null)
        {
            jsonserializer = _serializerCache.untypedValueSerializer(javatype);
            if(jsonserializer == null)
            {
                jsonserializer = _createAndCacheUntypedSerializer(javatype);
                if(jsonserializer == null)
                    return getUnknownTypeSerializer(javatype.getRawClass());
            }
        }
        return handlePrimaryContextualization(jsonserializer, beanproperty);
    }

    public JsonSerializer findPrimaryPropertySerializer(Class class1, BeanProperty beanproperty)
        throws JsonMappingException
    {
        JsonSerializer jsonserializer = _knownSerializers.untypedValueSerializer(class1);
        if(jsonserializer == null)
        {
            jsonserializer = _serializerCache.untypedValueSerializer(class1);
            if(jsonserializer == null)
            {
                jsonserializer = _serializerCache.untypedValueSerializer(_config.constructType(class1));
                if(jsonserializer == null)
                {
                    jsonserializer = _createAndCacheUntypedSerializer(class1);
                    if(jsonserializer == null)
                        return getUnknownTypeSerializer(class1);
                }
            }
        }
        return handlePrimaryContextualization(jsonserializer, beanproperty);
    }

    public JsonSerializer findTypedValueSerializer(JavaType javatype, boolean flag, BeanProperty beanproperty)
        throws JsonMappingException
    {
        Object obj = _knownSerializers.typedValueSerializer(javatype);
        if(obj == null)
            if((obj = _serializerCache.typedValueSerializer(javatype)) == null)
            {
                JsonSerializer jsonserializer = findValueSerializer(javatype, beanproperty);
                TypeSerializer typeserializer = _serializerFactory.createTypeSerializer(_config, javatype);
                if(typeserializer != null)
                    obj = new TypeWrappedSerializer(typeserializer.forProperty(beanproperty), jsonserializer);
                else
                    obj = jsonserializer;
                if(flag)
                {
                    _serializerCache.addTypedSerializer(javatype, ((JsonSerializer) (obj)));
                    return ((JsonSerializer) (obj));
                }
            }
        return ((JsonSerializer) (obj));
    }

    public JsonSerializer findTypedValueSerializer(Class class1, boolean flag, BeanProperty beanproperty)
        throws JsonMappingException
    {
        Object obj = _knownSerializers.typedValueSerializer(class1);
        if(obj == null)
            if((obj = _serializerCache.typedValueSerializer(class1)) == null)
            {
                JsonSerializer jsonserializer = findValueSerializer(class1, beanproperty);
                TypeSerializer typeserializer = _serializerFactory.createTypeSerializer(_config, _config.constructType(class1));
                if(typeserializer != null)
                    obj = new TypeWrappedSerializer(typeserializer.forProperty(beanproperty), jsonserializer);
                else
                    obj = jsonserializer;
                if(flag)
                {
                    _serializerCache.addTypedSerializer(class1, ((JsonSerializer) (obj)));
                    return ((JsonSerializer) (obj));
                }
            }
        return ((JsonSerializer) (obj));
    }

    public JsonSerializer findValueSerializer(JavaType javatype, BeanProperty beanproperty)
        throws JsonMappingException
    {
        JsonSerializer jsonserializer = _knownSerializers.untypedValueSerializer(javatype);
        if(jsonserializer == null)
        {
            jsonserializer = _serializerCache.untypedValueSerializer(javatype);
            if(jsonserializer == null)
            {
                jsonserializer = _createAndCacheUntypedSerializer(javatype);
                if(jsonserializer == null)
                    return getUnknownTypeSerializer(javatype.getRawClass());
            }
        }
        return handleSecondaryContextualization(jsonserializer, beanproperty);
    }

    public JsonSerializer findValueSerializer(Class class1, BeanProperty beanproperty)
        throws JsonMappingException
    {
        JsonSerializer jsonserializer = _knownSerializers.untypedValueSerializer(class1);
        if(jsonserializer == null)
        {
            jsonserializer = _serializerCache.untypedValueSerializer(class1);
            if(jsonserializer == null)
            {
                jsonserializer = _serializerCache.untypedValueSerializer(_config.constructType(class1));
                if(jsonserializer == null)
                {
                    jsonserializer = _createAndCacheUntypedSerializer(class1);
                    if(jsonserializer == null)
                        return getUnknownTypeSerializer(class1);
                }
            }
        }
        return handleSecondaryContextualization(jsonserializer, beanproperty);
    }

    public final Class getActiveView()
    {
        return _serializationView;
    }

    public final AnnotationIntrospector getAnnotationIntrospector()
    {
        return _config.getAnnotationIntrospector();
    }

    public Object getAttribute(Object obj)
    {
        return _attributes.getAttribute(obj);
    }

    public final SerializationConfig getConfig()
    {
        return _config;
    }

    public volatile MapperConfig getConfig()
    {
        return getConfig();
    }

    public JsonSerializer getDefaultNullKeySerializer()
    {
        return _nullKeySerializer;
    }

    public JsonSerializer getDefaultNullValueSerializer()
    {
        return _nullValueSerializer;
    }

    public final FilterProvider getFilterProvider()
    {
        return _config.getFilterProvider();
    }

    public Locale getLocale()
    {
        return _config.getLocale();
    }

    public final Class getSerializationView()
    {
        return _serializationView;
    }

    public TimeZone getTimeZone()
    {
        return _config.getTimeZone();
    }

    public final TypeFactory getTypeFactory()
    {
        return _config.getTypeFactory();
    }

    public JsonSerializer getUnknownTypeSerializer(Class class1)
    {
        return _unknownTypeSerializer;
    }

    public JsonSerializer handleContextualization(JsonSerializer jsonserializer, BeanProperty beanproperty)
        throws JsonMappingException
    {
        return handleSecondaryContextualization(jsonserializer, beanproperty);
    }

    public JsonSerializer handlePrimaryContextualization(JsonSerializer jsonserializer, BeanProperty beanproperty)
        throws JsonMappingException
    {
        if(jsonserializer != null && (jsonserializer instanceof ContextualSerializer))
            jsonserializer = ((ContextualSerializer)jsonserializer).createContextual(this, beanproperty);
        return jsonserializer;
    }

    public JsonSerializer handleSecondaryContextualization(JsonSerializer jsonserializer, BeanProperty beanproperty)
        throws JsonMappingException
    {
        if(jsonserializer != null && (jsonserializer instanceof ContextualSerializer))
            jsonserializer = ((ContextualSerializer)jsonserializer).createContextual(this, beanproperty);
        return jsonserializer;
    }

    public final boolean hasSerializationFeatures(int i)
    {
        return _config.hasSerializationFeatures(i);
    }

    public final boolean isEnabled(SerializationFeature serializationfeature)
    {
        return _config.isEnabled(serializationfeature);
    }

    public abstract JsonSerializer serializerInstance(Annotated annotated, Object obj)
        throws JsonMappingException;

    public volatile DatabindContext setAttribute(Object obj, Object obj1)
    {
        return setAttribute(obj, obj1);
    }

    public SerializerProvider setAttribute(Object obj, Object obj1)
    {
        _attributes = _attributes.withPerCallAttribute(obj, obj1);
        return this;
    }

    public void setDefaultKeySerializer(JsonSerializer jsonserializer)
    {
        if(jsonserializer == null)
        {
            throw new IllegalArgumentException("Can not pass null JsonSerializer");
        } else
        {
            _keySerializer = jsonserializer;
            return;
        }
    }

    public void setNullKeySerializer(JsonSerializer jsonserializer)
    {
        if(jsonserializer == null)
        {
            throw new IllegalArgumentException("Can not pass null JsonSerializer");
        } else
        {
            _nullKeySerializer = jsonserializer;
            return;
        }
    }

    public void setNullValueSerializer(JsonSerializer jsonserializer)
    {
        if(jsonserializer == null)
        {
            throw new IllegalArgumentException("Can not pass null JsonSerializer");
        } else
        {
            _nullValueSerializer = jsonserializer;
            return;
        }
    }

    protected static final boolean CACHE_UNKNOWN_MAPPINGS;
    public static final JsonSerializer DEFAULT_NULL_KEY_SERIALIZER = new FailingSerializer("Null key for a Map not allowed in JSON (use a converting NullKeySerializer?)");
    protected static final JsonSerializer DEFAULT_UNKNOWN_SERIALIZER = new UnknownSerializer();
    protected static final JavaType TYPE_OBJECT = TypeFactory.defaultInstance().uncheckedSimpleType(java/lang/Object);
    protected transient ContextAttributes _attributes;
    protected final SerializationConfig _config;
    protected DateFormat _dateFormat;
    protected JsonSerializer _keySerializer;
    protected final ReadOnlyClassToSerializerMap _knownSerializers;
    protected JsonSerializer _nullKeySerializer;
    protected JsonSerializer _nullValueSerializer;
    protected final RootNameLookup _rootNames;
    protected final Class _serializationView;
    protected final SerializerCache _serializerCache;
    protected final SerializerFactory _serializerFactory;
    protected final boolean _stdNullValueSerializer;
    protected JsonSerializer _unknownTypeSerializer;

}
