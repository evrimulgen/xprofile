// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.fasterxml.jackson.databind;

import com.fasterxml.jackson.annotation.ObjectIdGenerator;
import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.databind.cfg.ContextAttributes;
import com.fasterxml.jackson.databind.cfg.MapperConfig;
import com.fasterxml.jackson.databind.deser.ContextualDeserializer;
import com.fasterxml.jackson.databind.deser.ContextualKeyDeserializer;
import com.fasterxml.jackson.databind.deser.DeserializationProblemHandler;
import com.fasterxml.jackson.databind.deser.DeserializerCache;
import com.fasterxml.jackson.databind.deser.DeserializerFactory;
import com.fasterxml.jackson.databind.deser.impl.ReadableObjectId;
import com.fasterxml.jackson.databind.deser.impl.TypeWrappedDeserializer;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;
import com.fasterxml.jackson.databind.introspect.Annotated;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.databind.util.ArrayBuilders;
import com.fasterxml.jackson.databind.util.ClassUtil;
import com.fasterxml.jackson.databind.util.LinkedNode;
import com.fasterxml.jackson.databind.util.ObjectBuffer;
import java.io.IOException;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

// Referenced classes of package com.fasterxml.jackson.databind:
//            DatabindContext, DeserializationConfig, JsonMappingException, InjectableValues, 
//            DeserializationFeature, JsonDeserializer, JavaType, BeanProperty, 
//            KeyDeserializer, AnnotationIntrospector

public abstract class DeserializationContext extends DatabindContext
    implements Serializable
{

    protected DeserializationContext(DeserializationContext deserializationcontext, DeserializationConfig deserializationconfig, JsonParser jsonparser, InjectableValues injectablevalues)
    {
        _cache = deserializationcontext._cache;
        _factory = deserializationcontext._factory;
        _config = deserializationconfig;
        _featureFlags = deserializationconfig.getDeserializationFeatures();
        _view = deserializationconfig.getActiveView();
        _parser = jsonparser;
        _injectableValues = injectablevalues;
        _attributes = deserializationconfig.getAttributes();
    }

    protected DeserializationContext(DeserializationContext deserializationcontext, DeserializerFactory deserializerfactory)
    {
        _cache = deserializationcontext._cache;
        _factory = deserializerfactory;
        _config = deserializationcontext._config;
        _featureFlags = deserializationcontext._featureFlags;
        _view = deserializationcontext._view;
        _parser = deserializationcontext._parser;
        _injectableValues = deserializationcontext._injectableValues;
        _attributes = deserializationcontext._attributes;
    }

    protected DeserializationContext(DeserializerFactory deserializerfactory)
    {
        this(deserializerfactory, ((DeserializerCache) (null)));
    }

    protected DeserializationContext(DeserializerFactory deserializerfactory, DeserializerCache deserializercache)
    {
        if(deserializerfactory == null)
            throw new IllegalArgumentException("Can not pass null DeserializerFactory");
        _factory = deserializerfactory;
        if(deserializercache == null)
            deserializercache = new DeserializerCache();
        _cache = deserializercache;
        _featureFlags = 0;
        _config = null;
        _injectableValues = null;
        _view = null;
        _attributes = null;
    }

    protected String _calcName(Class class1)
    {
        if(class1.isArray())
            return (new StringBuilder()).append(_calcName(class1.getComponentType())).append("[]").toString();
        else
            return class1.getName();
    }

    protected String _desc(String s)
    {
        if(s.length() > 500)
            s = (new StringBuilder()).append(s.substring(0, 500)).append("]...[").append(s.substring(-500 + s.length())).toString();
        return s;
    }

    protected String _valueDesc()
    {
        String s;
        try
        {
            s = _desc(_parser.getText());
        }
        catch(Exception exception)
        {
            return "[N/A]";
        }
        return s;
    }

    public Calendar constructCalendar(Date date)
    {
        Calendar calendar = Calendar.getInstance(getTimeZone());
        calendar.setTime(date);
        return calendar;
    }

    public final JavaType constructType(Class class1)
    {
        return _config.constructType(class1);
    }

    public abstract JsonDeserializer deserializerInstance(Annotated annotated, Object obj)
        throws JsonMappingException;

    protected String determineClassName(Object obj)
    {
        return ClassUtil.getClassDescription(obj);
    }

    public JsonMappingException endOfInputException(Class class1)
    {
        return JsonMappingException.from(_parser, (new StringBuilder()).append("Unexpected end-of-input when trying to deserialize a ").append(class1.getName()).toString());
    }

    public Class findClass(String s)
        throws ClassNotFoundException
    {
        return ClassUtil.findClass(s);
    }

    public final JsonDeserializer findContextualValueDeserializer(JavaType javatype, BeanProperty beanproperty)
        throws JsonMappingException
    {
        JsonDeserializer jsondeserializer = _cache.findValueDeserializer(this, _factory, javatype);
        if(jsondeserializer != null)
            jsondeserializer = handleSecondaryContextualization(jsondeserializer, beanproperty);
        return jsondeserializer;
    }

    public final Object findInjectableValue(Object obj, BeanProperty beanproperty, Object obj1)
    {
        if(_injectableValues == null)
            throw new IllegalStateException((new StringBuilder()).append("No 'injectableValues' configured, can not inject value with id [").append(obj).append("]").toString());
        else
            return _injectableValues.findInjectableValue(obj, this, beanproperty, obj1);
    }

    public final KeyDeserializer findKeyDeserializer(JavaType javatype, BeanProperty beanproperty)
        throws JsonMappingException
    {
        KeyDeserializer keydeserializer = _cache.findKeyDeserializer(this, _factory, javatype);
        if(keydeserializer instanceof ContextualKeyDeserializer)
            keydeserializer = ((ContextualKeyDeserializer)keydeserializer).createContextual(this, beanproperty);
        return keydeserializer;
    }

    public abstract ReadableObjectId findObjectId(Object obj, ObjectIdGenerator objectidgenerator);

    public final JsonDeserializer findRootValueDeserializer(JavaType javatype)
        throws JsonMappingException
    {
        JsonDeserializer jsondeserializer = _cache.findValueDeserializer(this, _factory, javatype);
        if(jsondeserializer == null)
            return null;
        JsonDeserializer jsondeserializer1 = handleSecondaryContextualization(jsondeserializer, null);
        TypeDeserializer typedeserializer = _factory.findTypeDeserializer(_config, javatype);
        if(typedeserializer != null)
            return new TypeWrappedDeserializer(typedeserializer.forProperty(null), jsondeserializer1);
        else
            return jsondeserializer1;
    }

    public final Class getActiveView()
    {
        return _view;
    }

    public final AnnotationIntrospector getAnnotationIntrospector()
    {
        return _config.getAnnotationIntrospector();
    }

    public final ArrayBuilders getArrayBuilders()
    {
        if(_arrayBuilders == null)
            _arrayBuilders = new ArrayBuilders();
        return _arrayBuilders;
    }

    public Object getAttribute(Object obj)
    {
        return _attributes.getAttribute(obj);
    }

    public final Base64Variant getBase64Variant()
    {
        return _config.getBase64Variant();
    }

    public DeserializationConfig getConfig()
    {
        return _config;
    }

    public volatile MapperConfig getConfig()
    {
        return getConfig();
    }

    protected DateFormat getDateFormat()
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

    public DeserializerFactory getFactory()
    {
        return _factory;
    }

    public Locale getLocale()
    {
        return _config.getLocale();
    }

    public final JsonNodeFactory getNodeFactory()
    {
        return _config.getNodeFactory();
    }

    public final JsonParser getParser()
    {
        return _parser;
    }

    public TimeZone getTimeZone()
    {
        return _config.getTimeZone();
    }

    public final TypeFactory getTypeFactory()
    {
        return _config.getTypeFactory();
    }

    public JsonDeserializer handlePrimaryContextualization(JsonDeserializer jsondeserializer, BeanProperty beanproperty)
        throws JsonMappingException
    {
        if(jsondeserializer != null && (jsondeserializer instanceof ContextualDeserializer))
            jsondeserializer = ((ContextualDeserializer)jsondeserializer).createContextual(this, beanproperty);
        return jsondeserializer;
    }

    public JsonDeserializer handleSecondaryContextualization(JsonDeserializer jsondeserializer, BeanProperty beanproperty)
        throws JsonMappingException
    {
        if(jsondeserializer != null && (jsondeserializer instanceof ContextualDeserializer))
            jsondeserializer = ((ContextualDeserializer)jsondeserializer).createContextual(this, beanproperty);
        return jsondeserializer;
    }

    public boolean handleUnknownProperty(JsonParser jsonparser, JsonDeserializer jsondeserializer, Object obj, String s)
        throws IOException, JsonProcessingException
    {
        LinkedNode linkednode = _config.getProblemHandlers();
        if(linkednode != null)
        {
            for(LinkedNode linkednode1 = linkednode; linkednode1 != null; linkednode1 = linkednode1.next())
                if(((DeserializationProblemHandler)linkednode1.value()).handleUnknownProperty(this, jsonparser, jsondeserializer, obj, s))
                    return true;

        }
        return false;
    }

    public final boolean hasDeserializationFeatures(int i)
    {
        return _config.hasDeserializationFeatures(i);
    }

    public boolean hasValueDeserializerFor(JavaType javatype)
    {
        return hasValueDeserializerFor(javatype, null);
    }

    public boolean hasValueDeserializerFor(JavaType javatype, AtomicReference atomicreference)
    {
        boolean flag = _cache.hasValueDeserializerFor(this, _factory, javatype);
        return flag;
        JsonMappingException jsonmappingexception;
        jsonmappingexception;
        if(atomicreference != null)
            atomicreference.set(jsonmappingexception);
_L2:
        return false;
        RuntimeException runtimeexception;
        runtimeexception;
        if(atomicreference == null)
            throw runtimeexception;
        atomicreference.set(runtimeexception);
        if(true) goto _L2; else goto _L1
_L1:
    }

    public JsonMappingException instantiationException(Class class1, String s)
    {
        return JsonMappingException.from(_parser, (new StringBuilder()).append("Can not construct instance of ").append(class1.getName()).append(", problem: ").append(s).toString());
    }

    public JsonMappingException instantiationException(Class class1, Throwable throwable)
    {
        return JsonMappingException.from(_parser, (new StringBuilder()).append("Can not construct instance of ").append(class1.getName()).append(", problem: ").append(throwable.getMessage()).toString(), throwable);
    }

    public final boolean isEnabled(DeserializationFeature deserializationfeature)
    {
        return (_featureFlags & deserializationfeature.getMask()) != 0;
    }

    public abstract KeyDeserializer keyDeserializerInstance(Annotated annotated, Object obj)
        throws JsonMappingException;

    public final ObjectBuffer leaseObjectBuffer()
    {
        ObjectBuffer objectbuffer = _objectBuffer;
        if(objectbuffer == null)
        {
            return new ObjectBuffer();
        } else
        {
            _objectBuffer = null;
            return objectbuffer;
        }
    }

    public JsonMappingException mappingException(Class class1)
    {
        return mappingException(class1, _parser.getCurrentToken());
    }

    public JsonMappingException mappingException(Class class1, JsonToken jsontoken)
    {
        String s = _calcName(class1);
        return JsonMappingException.from(_parser, (new StringBuilder()).append("Can not deserialize instance of ").append(s).append(" out of ").append(jsontoken).append(" token").toString());
    }

    public JsonMappingException mappingException(String s)
    {
        return JsonMappingException.from(getParser(), s);
    }

    public Date parseDate(String s)
        throws IllegalArgumentException
    {
        Date date;
        try
        {
            date = getDateFormat().parse(s);
        }
        catch(ParseException parseexception)
        {
            throw new IllegalArgumentException((new StringBuilder()).append("Failed to parse Date value '").append(s).append("': ").append(parseexception.getMessage()).toString());
        }
        return date;
    }

    public void reportUnknownProperty(Object obj, String s, JsonDeserializer jsondeserializer)
        throws JsonMappingException
    {
        if(!isEnabled(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES))
            return;
        java.util.Collection collection;
        if(jsondeserializer == null)
            collection = null;
        else
            collection = jsondeserializer.getKnownPropertyNames();
        throw UnrecognizedPropertyException.from(_parser, obj, s, collection);
    }

    public final void returnObjectBuffer(ObjectBuffer objectbuffer)
    {
        if(_objectBuffer == null || objectbuffer.initialCapacity() >= _objectBuffer.initialCapacity())
            _objectBuffer = objectbuffer;
    }

    public volatile DatabindContext setAttribute(Object obj, Object obj1)
    {
        return setAttribute(obj, obj1);
    }

    public DeserializationContext setAttribute(Object obj, Object obj1)
    {
        _attributes = _attributes.withPerCallAttribute(obj, obj1);
        return this;
    }

    public JsonMappingException unknownTypeException(JavaType javatype, String s)
    {
        return JsonMappingException.from(_parser, (new StringBuilder()).append("Could not resolve type id '").append(s).append("' into a subtype of ").append(javatype).toString());
    }

    public JsonMappingException weirdKeyException(Class class1, String s, String s1)
    {
        return InvalidFormatException.from(_parser, (new StringBuilder()).append("Can not construct Map key of type ").append(class1.getName()).append(" from String \"").append(_desc(s)).append("\": ").append(s1).toString(), s, class1);
    }

    public JsonMappingException weirdNumberException(Class class1, String s)
    {
        return weirdStringException(null, class1, s);
    }

    public JsonMappingException weirdNumberException(Number number, Class class1, String s)
    {
        return InvalidFormatException.from(_parser, (new StringBuilder()).append("Can not construct instance of ").append(class1.getName()).append(" from number value (").append(_valueDesc()).append("): ").append(s).toString(), null, class1);
    }

    public JsonMappingException weirdStringException(Class class1, String s)
    {
        return weirdStringException(null, class1, s);
    }

    public JsonMappingException weirdStringException(String s, Class class1, String s1)
    {
        return InvalidFormatException.from(_parser, (new StringBuilder()).append("Can not construct instance of ").append(class1.getName()).append(" from String value '").append(_valueDesc()).append("': ").append(s1).toString(), s, class1);
    }

    public JsonMappingException wrongTokenException(JsonParser jsonparser, JsonToken jsontoken, String s)
    {
        return JsonMappingException.from(jsonparser, (new StringBuilder()).append("Unexpected token (").append(jsonparser.getCurrentToken()).append("), expected ").append(jsontoken).append(": ").append(s).toString());
    }

    private static final int MAX_ERROR_STR_LEN = 500;
    private static final long serialVersionUID = 0x94c2daea9ee8c98dL;
    protected transient ArrayBuilders _arrayBuilders;
    protected transient ContextAttributes _attributes;
    protected final DeserializerCache _cache;
    protected final DeserializationConfig _config;
    protected transient DateFormat _dateFormat;
    protected final DeserializerFactory _factory;
    protected final int _featureFlags;
    protected final InjectableValues _injectableValues;
    protected transient ObjectBuffer _objectBuffer;
    protected transient JsonParser _parser;
    protected final Class _view;
}
