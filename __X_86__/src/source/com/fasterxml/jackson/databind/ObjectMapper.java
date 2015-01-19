// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.fasterxml.jackson.databind;

import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.core.io.*;
import com.fasterxml.jackson.core.type.ResolvedType;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.core.util.ByteArrayBuilder;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.cfg.BaseSettings;
import com.fasterxml.jackson.databind.cfg.ContextAttributes;
import com.fasterxml.jackson.databind.cfg.HandlerInstantiator;
import com.fasterxml.jackson.databind.cfg.PackageVersion;
import com.fasterxml.jackson.databind.deser.BeanDeserializerFactory;
import com.fasterxml.jackson.databind.deser.BeanDeserializerModifier;
import com.fasterxml.jackson.databind.deser.DefaultDeserializationContext;
import com.fasterxml.jackson.databind.deser.DeserializationProblemHandler;
import com.fasterxml.jackson.databind.deser.DeserializerFactory;
import com.fasterxml.jackson.databind.deser.Deserializers;
import com.fasterxml.jackson.databind.deser.KeyDeserializers;
import com.fasterxml.jackson.databind.deser.ValueInstantiators;
import com.fasterxml.jackson.databind.introspect.BasicClassIntrospector;
import com.fasterxml.jackson.databind.introspect.ClassIntrospector;
import com.fasterxml.jackson.databind.introspect.JacksonAnnotationIntrospector;
import com.fasterxml.jackson.databind.introspect.VisibilityChecker;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatVisitorWrapper;
import com.fasterxml.jackson.databind.jsonschema.JsonSchema;
import com.fasterxml.jackson.databind.jsontype.NamedType;
import com.fasterxml.jackson.databind.jsontype.SubtypeResolver;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.fasterxml.jackson.databind.jsontype.TypeResolverBuilder;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.fasterxml.jackson.databind.jsontype.impl.StdSubtypeResolver;
import com.fasterxml.jackson.databind.jsontype.impl.StdTypeResolverBuilder;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.NullNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.TreeTraversingParser;
import com.fasterxml.jackson.databind.ser.BeanSerializerFactory;
import com.fasterxml.jackson.databind.ser.BeanSerializerModifier;
import com.fasterxml.jackson.databind.ser.DefaultSerializerProvider;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.SerializerFactory;
import com.fasterxml.jackson.databind.ser.Serializers;
import com.fasterxml.jackson.databind.type.ClassKey;
import com.fasterxml.jackson.databind.type.SimpleType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.databind.type.TypeModifier;
import com.fasterxml.jackson.databind.util.RootNameLookup;
import com.fasterxml.jackson.databind.util.StdDateFormat;
import com.fasterxml.jackson.databind.util.TokenBuffer;
import java.io.*;
import java.lang.reflect.Type;
import java.net.URL;
import java.text.DateFormat;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicReference;

// Referenced classes of package com.fasterxml.jackson.databind:
//            JsonNode, MappingJsonFactory, SerializationConfig, DeserializationConfig, 
//            MapperFeature, JsonMappingException, SerializationFeature, Module, 
//            JavaType, JsonDeserializer, DeserializationContext, MappingIterator, 
//            ObjectReader, ObjectWriter, AnnotationIntrospector, InjectableValues, 
//            DeserializationFeature, SerializerProvider, PropertyNamingStrategy, AbstractTypeResolver

public class ObjectMapper extends ObjectCodec
    implements Versioned, Serializable
{
    public static class DefaultTypeResolverBuilder extends StdTypeResolverBuilder
        implements Serializable
    {

        public TypeDeserializer buildTypeDeserializer(DeserializationConfig deserializationconfig, JavaType javatype, Collection collection)
        {
            if(useForType(javatype))
                return super.buildTypeDeserializer(deserializationconfig, javatype, collection);
            else
                return null;
        }

        public TypeSerializer buildTypeSerializer(SerializationConfig serializationconfig, JavaType javatype, Collection collection)
        {
            if(useForType(javatype))
                return super.buildTypeSerializer(serializationconfig, javatype, collection);
            else
                return null;
        }

        public boolean useForType(JavaType javatype)
        {
            static class _cls2
            {

                static final int $SwitchMap$com$fasterxml$jackson$databind$ObjectMapper$DefaultTyping[];

                static 
                {
                    $SwitchMap$com$fasterxml$jackson$databind$ObjectMapper$DefaultTyping = new int[DefaultTyping.values().length];
                    try
                    {
                        $SwitchMap$com$fasterxml$jackson$databind$ObjectMapper$DefaultTyping[DefaultTyping.NON_CONCRETE_AND_ARRAYS.ordinal()] = 1;
                    }
                    catch(NoSuchFieldError nosuchfielderror) { }
                    try
                    {
                        $SwitchMap$com$fasterxml$jackson$databind$ObjectMapper$DefaultTyping[DefaultTyping.OBJECT_AND_NON_CONCRETE.ordinal()] = 2;
                    }
                    catch(NoSuchFieldError nosuchfielderror1) { }
                    try
                    {
                        $SwitchMap$com$fasterxml$jackson$databind$ObjectMapper$DefaultTyping[DefaultTyping.NON_FINAL.ordinal()] = 3;
                    }
                    catch(NoSuchFieldError nosuchfielderror2)
                    {
                        return;
                    }
                }
            }

            _cls2..SwitchMap.com.fasterxml.jackson.databind.ObjectMapper.DefaultTyping[_appliesFor.ordinal()];
            JVM INSTR tableswitch 1 3: default 36
        //                       1 47
        //                       2 62
        //                       3 86;
               goto _L1 _L2 _L3 _L4
_L1:
            if(javatype.getRawClass() != java/lang/Object) goto _L6; else goto _L5
_L5:
            return true;
_L2:
            for(; javatype.isArrayType(); javatype = javatype.getContentType());
_L3:
            if(javatype.getRawClass() == java/lang/Object) goto _L8; else goto _L7
_L7:
            boolean flag;
            boolean flag1;
            flag1 = javatype.isConcrete();
            flag = false;
            if(flag1) goto _L9; else goto _L8
_L8:
            flag = true;
_L9:
            return flag;
_L4:
            for(; javatype.isArrayType(); javatype = javatype.getContentType());
            if(javatype.isFinal())
                return false;
            if(true) goto _L5; else goto _L6
_L6:
            return false;
        }

        private static final long serialVersionUID = 1L;
        protected final DefaultTyping _appliesFor;

        public DefaultTypeResolverBuilder(DefaultTyping defaulttyping)
        {
            _appliesFor = defaulttyping;
        }
    }

    public static final class DefaultTyping extends Enum
    {

        public static DefaultTyping valueOf(String s)
        {
            return (DefaultTyping)Enum.valueOf(com/fasterxml/jackson/databind/ObjectMapper$DefaultTyping, s);
        }

        public static DefaultTyping[] values()
        {
            return (DefaultTyping[])$VALUES.clone();
        }

        private static final DefaultTyping $VALUES[];
        public static final DefaultTyping JAVA_LANG_OBJECT;
        public static final DefaultTyping NON_CONCRETE_AND_ARRAYS;
        public static final DefaultTyping NON_FINAL;
        public static final DefaultTyping OBJECT_AND_NON_CONCRETE;

        static 
        {
            JAVA_LANG_OBJECT = new DefaultTyping("JAVA_LANG_OBJECT", 0);
            OBJECT_AND_NON_CONCRETE = new DefaultTyping("OBJECT_AND_NON_CONCRETE", 1);
            NON_CONCRETE_AND_ARRAYS = new DefaultTyping("NON_CONCRETE_AND_ARRAYS", 2);
            NON_FINAL = new DefaultTyping("NON_FINAL", 3);
            DefaultTyping adefaulttyping[] = new DefaultTyping[4];
            adefaulttyping[0] = JAVA_LANG_OBJECT;
            adefaulttyping[1] = OBJECT_AND_NON_CONCRETE;
            adefaulttyping[2] = NON_CONCRETE_AND_ARRAYS;
            adefaulttyping[3] = NON_FINAL;
            $VALUES = adefaulttyping;
        }

        private DefaultTyping(String s, int i)
        {
            super(s, i);
        }
    }


    public ObjectMapper()
    {
        this(null, null, null);
    }

    public ObjectMapper(JsonFactory jsonfactory)
    {
        this(jsonfactory, null, null);
    }

    public ObjectMapper(JsonFactory jsonfactory, DefaultSerializerProvider defaultserializerprovider, DefaultDeserializationContext defaultdeserializationcontext)
    {
        _rootDeserializers = new ConcurrentHashMap(64, 0.6F, 2);
        if(jsonfactory != null) goto _L2; else goto _L1
_L1:
        _jsonFactory = new MappingJsonFactory(this);
_L4:
        _subtypeResolver = new StdSubtypeResolver();
        _rootNames = new RootNameLookup();
        _typeFactory = TypeFactory.defaultInstance();
        HashMap hashmap = new HashMap();
        _mixInAnnotations = hashmap;
        _serializationConfig = new SerializationConfig(DEFAULT_BASE, _subtypeResolver, hashmap);
        _deserializationConfig = new DeserializationConfig(DEFAULT_BASE, _subtypeResolver, hashmap);
        boolean flag = _jsonFactory.requiresPropertyOrdering();
        if(flag ^ _serializationConfig.isEnabled(MapperFeature.SORT_PROPERTIES_ALPHABETICALLY))
            configure(MapperFeature.SORT_PROPERTIES_ALPHABETICALLY, flag);
        if(defaultserializerprovider == null)
            defaultserializerprovider = new com.fasterxml.jackson.databind.ser.DefaultSerializerProvider.Impl();
        _serializerProvider = defaultserializerprovider;
        if(defaultdeserializationcontext == null)
            defaultdeserializationcontext = new com.fasterxml.jackson.databind.deser.DefaultDeserializationContext.Impl(BeanDeserializerFactory.instance);
        _deserializationContext = defaultdeserializationcontext;
        _serializerFactory = BeanSerializerFactory.instance;
        return;
_L2:
        _jsonFactory = jsonfactory;
        if(jsonfactory.getCodec() == null)
            _jsonFactory.setCodec(this);
        if(true) goto _L4; else goto _L3
_L3:
    }

    protected ObjectMapper(ObjectMapper objectmapper)
    {
        _rootDeserializers = new ConcurrentHashMap(64, 0.6F, 2);
        _jsonFactory = objectmapper._jsonFactory.copy();
        _jsonFactory.setCodec(this);
        _subtypeResolver = objectmapper._subtypeResolver;
        _rootNames = new RootNameLookup();
        _typeFactory = objectmapper._typeFactory;
        _serializationConfig = objectmapper._serializationConfig;
        HashMap hashmap = new HashMap(objectmapper._mixInAnnotations);
        _mixInAnnotations = hashmap;
        _serializationConfig = new SerializationConfig(objectmapper._serializationConfig, hashmap);
        _deserializationConfig = new DeserializationConfig(objectmapper._deserializationConfig, hashmap);
        _serializerProvider = objectmapper._serializerProvider;
        _deserializationContext = objectmapper._deserializationContext;
        _serializerFactory = objectmapper._serializerFactory;
    }

    private final void _configAndWriteCloseable(JsonGenerator jsongenerator, Object obj, SerializationConfig serializationconfig)
        throws IOException, JsonGenerationException, JsonMappingException
    {
        Closeable closeable = (Closeable)obj;
        _serializerProvider(serializationconfig).serializeValue(jsongenerator, obj);
        jsongenerator.close();
        closeable.close();
        JsonGenerator jsongenerator1;
        Exception exception1;
        Closeable closeable1;
        Exception exception;
        if(false)
            try
            {
                null.close();
            }
            catch(IOException ioexception3) { }
        if(true)
            break MISSING_BLOCK_LABEL_45;
        null.close();
        return;
        exception;
        jsongenerator1 = jsongenerator;
        exception1 = exception;
        closeable1 = closeable;
_L2:
        IOException ioexception2;
        if(jsongenerator1 != null)
            try
            {
                jsongenerator1.close();
            }
            catch(IOException ioexception1) { }
        if(closeable1 != null)
            try
            {
                closeable1.close();
            }
            catch(IOException ioexception) { }
        throw exception1;
        ioexception2;
        return;
        Exception exception2;
        exception2;
        closeable1 = closeable;
        exception1 = exception2;
        jsongenerator1 = null;
        continue; /* Loop/switch isn't completed */
        exception1;
        closeable1 = null;
        jsongenerator1 = null;
        if(true) goto _L2; else goto _L1
_L1:
    }

    private final void _writeCloseableValue(JsonGenerator jsongenerator, Object obj, SerializationConfig serializationconfig)
        throws IOException, JsonGenerationException, JsonMappingException
    {
        Closeable closeable = (Closeable)obj;
        _serializerProvider(serializationconfig).serializeValue(jsongenerator, obj);
        if(serializationconfig.isEnabled(SerializationFeature.FLUSH_AFTER_WRITE_VALUE))
            jsongenerator.flush();
        closeable.close();
        if(true)
            break MISSING_BLOCK_LABEL_47;
        null.close();
        return;
        Exception exception;
        exception;
        Closeable closeable1;
        Exception exception1;
        closeable1 = closeable;
        exception1 = exception;
_L2:
        IOException ioexception1;
        if(closeable1 != null)
            try
            {
                closeable1.close();
            }
            catch(IOException ioexception) { }
        throw exception1;
        ioexception1;
        return;
        exception1;
        closeable1 = null;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public static List findModules()
    {
        return findModules(null);
    }

    public static List findModules(ClassLoader classloader)
    {
        ArrayList arraylist = new ArrayList();
        ServiceLoader serviceloader;
        Iterator iterator;
        if(classloader == null)
            serviceloader = ServiceLoader.load(com/fasterxml/jackson/databind/Module);
        else
            serviceloader = ServiceLoader.load(com/fasterxml/jackson/databind/Module, classloader);
        for(iterator = serviceloader.iterator(); iterator.hasNext(); arraylist.add((Module)iterator.next()));
        return arraylist;
    }

    protected void _checkInvalidCopy(Class class1)
    {
        if(getClass() != class1)
            throw new IllegalStateException((new StringBuilder()).append("Failed copy(): ").append(getClass().getName()).append(" (version: ").append(version()).append(") does not override copy(); it has to").toString());
        else
            return;
    }

    protected final void _configAndWriteValue(JsonGenerator jsongenerator, Object obj)
        throws IOException, JsonGenerationException, JsonMappingException
    {
        SerializationConfig serializationconfig;
        boolean flag;
        serializationconfig = getSerializationConfig();
        if(serializationconfig.isEnabled(SerializationFeature.INDENT_OUTPUT))
            jsongenerator.useDefaultPrettyPrinter();
        if(serializationconfig.isEnabled(SerializationFeature.WRITE_BIGDECIMAL_AS_PLAIN))
            jsongenerator.enable(com.fasterxml.jackson.core.JsonGenerator.Feature.WRITE_BIGDECIMAL_AS_PLAIN);
        if(serializationconfig.isEnabled(SerializationFeature.CLOSE_CLOSEABLE) && (obj instanceof Closeable))
        {
            _configAndWriteCloseable(jsongenerator, obj, serializationconfig);
            return;
        }
        flag = false;
        _serializerProvider(serializationconfig).serializeValue(jsongenerator, obj);
        flag = true;
        jsongenerator.close();
        return;
        Exception exception;
        exception;
        if(!flag)
            try
            {
                jsongenerator.close();
            }
            catch(IOException ioexception) { }
        throw exception;
    }

    protected final void _configAndWriteValue(JsonGenerator jsongenerator, Object obj, Class class1)
        throws IOException, JsonGenerationException, JsonMappingException
    {
        SerializationConfig serializationconfig;
        boolean flag;
        serializationconfig = getSerializationConfig().withView(class1);
        if(serializationconfig.isEnabled(SerializationFeature.INDENT_OUTPUT))
            jsongenerator.useDefaultPrettyPrinter();
        if(serializationconfig.isEnabled(SerializationFeature.WRITE_BIGDECIMAL_AS_PLAIN))
            jsongenerator.enable(com.fasterxml.jackson.core.JsonGenerator.Feature.WRITE_BIGDECIMAL_AS_PLAIN);
        if(serializationconfig.isEnabled(SerializationFeature.CLOSE_CLOSEABLE) && (obj instanceof Closeable))
        {
            _configAndWriteCloseable(jsongenerator, obj, serializationconfig);
            return;
        }
        flag = false;
        _serializerProvider(serializationconfig).serializeValue(jsongenerator, obj);
        flag = true;
        jsongenerator.close();
        return;
        Exception exception;
        exception;
        if(!flag)
            try
            {
                jsongenerator.close();
            }
            catch(IOException ioexception) { }
        throw exception;
    }

    protected Object _convert(Object obj, JavaType javatype)
        throws IllegalArgumentException
    {
        TokenBuffer tokenbuffer;
        Class class1 = javatype.getRawClass();
        if(class1 != java/lang/Object && !javatype.hasGenericTypes() && class1.isAssignableFrom(obj.getClass()))
            return obj;
        tokenbuffer = new TokenBuffer(this, false);
        JsonParser jsonparser;
        DeserializationConfig deserializationconfig;
        JsonToken jsontoken;
        Object obj1;
        _serializerProvider(getSerializationConfig().without(SerializationFeature.WRAP_ROOT_VALUE)).serializeValue(tokenbuffer, obj);
        jsonparser = tokenbuffer.asParser();
        deserializationconfig = getDeserializationConfig();
        jsontoken = _initForReading(jsonparser);
        if(jsontoken != JsonToken.VALUE_NULL)
            break MISSING_BLOCK_LABEL_135;
        obj1 = _findRootDeserializer(createDeserializationContext(jsonparser, deserializationconfig), javatype).getNullValue();
_L1:
        jsonparser.close();
        IOException ioexception;
        return obj1;
        if(jsontoken != JsonToken.END_ARRAY && jsontoken != JsonToken.END_OBJECT)
        {
            Object obj2;
            try
            {
                DefaultDeserializationContext defaultdeserializationcontext = createDeserializationContext(jsonparser, deserializationconfig);
                obj2 = _findRootDeserializer(defaultdeserializationcontext, javatype).deserialize(jsonparser, defaultdeserializationcontext);
            }
            // Misplaced declaration of an exception variable
            catch(IOException ioexception)
            {
                throw new IllegalArgumentException(ioexception.getMessage(), ioexception);
            }
            obj1 = obj2;
        } else
        {
            obj1 = null;
        }
          goto _L1
    }

    protected PrettyPrinter _defaultPrettyPrinter()
    {
        return _defaultPrettyPrinter;
    }

    protected JsonDeserializer _findRootDeserializer(DeserializationContext deserializationcontext, JavaType javatype)
        throws JsonMappingException
    {
        JsonDeserializer jsondeserializer = (JsonDeserializer)_rootDeserializers.get(javatype);
        if(jsondeserializer != null)
            return jsondeserializer;
        JsonDeserializer jsondeserializer1 = deserializationcontext.findRootValueDeserializer(javatype);
        if(jsondeserializer1 == null)
        {
            throw new JsonMappingException((new StringBuilder()).append("Can not find a deserializer for type ").append(javatype).toString());
        } else
        {
            _rootDeserializers.put(javatype, jsondeserializer1);
            return jsondeserializer1;
        }
    }

    protected JsonToken _initForReading(JsonParser jsonparser)
        throws IOException, JsonParseException, JsonMappingException
    {
        JsonToken jsontoken = jsonparser.getCurrentToken();
        if(jsontoken == null)
        {
            jsontoken = jsonparser.nextToken();
            if(jsontoken == null)
                throw JsonMappingException.from(jsonparser, "No content to map due to end-of-input");
        }
        return jsontoken;
    }

    protected Object _readMapAndClose(JsonParser jsonparser, JavaType javatype)
        throws IOException, JsonParseException, JsonMappingException
    {
        JsonToken jsontoken = _initForReading(jsonparser);
        if(jsontoken != JsonToken.VALUE_NULL) goto _L2; else goto _L1
_L1:
        Object obj = _findRootDeserializer(createDeserializationContext(jsonparser, getDeserializationConfig()), javatype).getNullValue();
_L3:
        jsonparser.clearCurrentToken();
        Exception exception;
        DeserializationConfig deserializationconfig;
        DefaultDeserializationContext defaultdeserializationcontext;
        JsonDeserializer jsondeserializer;
        Object obj1;
        try
        {
            jsonparser.close();
        }
        catch(IOException ioexception1)
        {
            return obj;
        }
        return obj;
_L2:
        if(jsontoken == JsonToken.END_ARRAY || jsontoken == JsonToken.END_OBJECT)
            break MISSING_BLOCK_LABEL_147;
label0:
        {
            deserializationconfig = getDeserializationConfig();
            defaultdeserializationcontext = createDeserializationContext(jsonparser, deserializationconfig);
            jsondeserializer = _findRootDeserializer(defaultdeserializationcontext, javatype);
            if(!deserializationconfig.useRootWrapping())
                break label0;
            obj = _unwrapAndDeserialize(jsonparser, defaultdeserializationcontext, deserializationconfig, javatype, jsondeserializer);
        }
          goto _L3
        obj1 = jsondeserializer.deserialize(jsonparser, defaultdeserializationcontext);
        obj = obj1;
          goto _L3
        exception;
        try
        {
            jsonparser.close();
        }
        catch(IOException ioexception) { }
        throw exception;
        obj = null;
          goto _L3
    }

    protected Object _readValue(DeserializationConfig deserializationconfig, JsonParser jsonparser, JavaType javatype)
        throws IOException, JsonParseException, JsonMappingException
    {
        JsonToken jsontoken = _initForReading(jsonparser);
        Object obj;
        if(jsontoken == JsonToken.VALUE_NULL)
            obj = _findRootDeserializer(createDeserializationContext(jsonparser, deserializationconfig), javatype).getNullValue();
        else
        if(jsontoken == JsonToken.END_ARRAY || jsontoken == JsonToken.END_OBJECT)
        {
            obj = null;
        } else
        {
            DefaultDeserializationContext defaultdeserializationcontext = createDeserializationContext(jsonparser, deserializationconfig);
            JsonDeserializer jsondeserializer = _findRootDeserializer(defaultdeserializationcontext, javatype);
            if(deserializationconfig.useRootWrapping())
                obj = _unwrapAndDeserialize(jsonparser, defaultdeserializationcontext, deserializationconfig, javatype, jsondeserializer);
            else
                obj = jsondeserializer.deserialize(jsonparser, defaultdeserializationcontext);
        }
        jsonparser.clearCurrentToken();
        return obj;
    }

    protected DefaultSerializerProvider _serializerProvider(SerializationConfig serializationconfig)
    {
        return _serializerProvider.createInstance(serializationconfig, _serializerFactory);
    }

    protected Object _unwrapAndDeserialize(JsonParser jsonparser, DeserializationContext deserializationcontext, DeserializationConfig deserializationconfig, JavaType javatype, JsonDeserializer jsondeserializer)
        throws IOException, JsonParseException, JsonMappingException
    {
        String s = deserializationconfig.getRootName();
        if(s == null)
            s = _rootNames.findRootName(javatype, deserializationconfig).getValue();
        if(jsonparser.getCurrentToken() != JsonToken.START_OBJECT)
            throw JsonMappingException.from(jsonparser, (new StringBuilder()).append("Current token not START_OBJECT (needed to unwrap root name '").append(s).append("'), but ").append(jsonparser.getCurrentToken()).toString());
        if(jsonparser.nextToken() != JsonToken.FIELD_NAME)
            throw JsonMappingException.from(jsonparser, (new StringBuilder()).append("Current token not FIELD_NAME (to contain expected root name '").append(s).append("'), but ").append(jsonparser.getCurrentToken()).toString());
        String s1 = jsonparser.getCurrentName();
        if(!s.equals(s1))
            throw JsonMappingException.from(jsonparser, (new StringBuilder()).append("Root name '").append(s1).append("' does not match expected ('").append(s).append("') for type ").append(javatype).toString());
        jsonparser.nextToken();
        Object obj = jsondeserializer.deserialize(jsonparser, deserializationcontext);
        if(jsonparser.nextToken() != JsonToken.END_OBJECT)
            throw JsonMappingException.from(jsonparser, (new StringBuilder()).append("Current token not END_OBJECT (to match wrapper object with root name '").append(s).append("'), but ").append(jsonparser.getCurrentToken()).toString());
        else
            return obj;
    }

    protected void _verifySchemaType(FormatSchema formatschema)
    {
        if(formatschema != null && !_jsonFactory.canUseSchema(formatschema))
            throw new IllegalArgumentException((new StringBuilder()).append("Can not use FormatSchema of type ").append(formatschema.getClass().getName()).append(" for format ").append(_jsonFactory.getFormatName()).toString());
        else
            return;
    }

    public void acceptJsonFormatVisitor(JavaType javatype, JsonFormatVisitorWrapper jsonformatvisitorwrapper)
        throws JsonMappingException
    {
        if(javatype == null)
        {
            throw new IllegalArgumentException("type must be provided");
        } else
        {
            _serializerProvider(getSerializationConfig()).acceptJsonFormatVisitor(javatype, jsonformatvisitorwrapper);
            return;
        }
    }

    public void acceptJsonFormatVisitor(Class class1, JsonFormatVisitorWrapper jsonformatvisitorwrapper)
        throws JsonMappingException
    {
        acceptJsonFormatVisitor(_typeFactory.constructType(class1), jsonformatvisitorwrapper);
    }

    public ObjectMapper addHandler(DeserializationProblemHandler deserializationproblemhandler)
    {
        _deserializationConfig = _deserializationConfig.withHandler(deserializationproblemhandler);
        return this;
    }

    public final void addMixInAnnotations(Class class1, Class class2)
    {
        _mixInAnnotations.put(new ClassKey(class1), class2);
    }

    public boolean canDeserialize(JavaType javatype)
    {
        return createDeserializationContext(null, getDeserializationConfig()).hasValueDeserializerFor(javatype, null);
    }

    public boolean canDeserialize(JavaType javatype, AtomicReference atomicreference)
    {
        return createDeserializationContext(null, getDeserializationConfig()).hasValueDeserializerFor(javatype, atomicreference);
    }

    public boolean canSerialize(Class class1)
    {
        return _serializerProvider(getSerializationConfig()).hasSerializerFor(class1, null);
    }

    public boolean canSerialize(Class class1, AtomicReference atomicreference)
    {
        return _serializerProvider(getSerializationConfig()).hasSerializerFor(class1, atomicreference);
    }

    public ObjectMapper clearProblemHandlers()
    {
        _deserializationConfig = _deserializationConfig.withNoProblemHandlers();
        return this;
    }

    public ObjectMapper configure(com.fasterxml.jackson.core.JsonGenerator.Feature feature, boolean flag)
    {
        _jsonFactory.configure(feature, flag);
        return this;
    }

    public ObjectMapper configure(com.fasterxml.jackson.core.JsonParser.Feature feature, boolean flag)
    {
        _jsonFactory.configure(feature, flag);
        return this;
    }

    public ObjectMapper configure(DeserializationFeature deserializationfeature, boolean flag)
    {
        DeserializationConfig deserializationconfig;
        if(flag)
            deserializationconfig = _deserializationConfig.with(deserializationfeature);
        else
            deserializationconfig = _deserializationConfig.without(deserializationfeature);
        _deserializationConfig = deserializationconfig;
        return this;
    }

    public ObjectMapper configure(MapperFeature mapperfeature, boolean flag)
    {
        SerializationConfig serializationconfig;
        DeserializationConfig deserializationconfig;
        if(flag)
            serializationconfig = _serializationConfig.with(new MapperFeature[] {
                mapperfeature
            });
        else
            serializationconfig = _serializationConfig.without(new MapperFeature[] {
                mapperfeature
            });
        _serializationConfig = serializationconfig;
        if(flag)
            deserializationconfig = _deserializationConfig.with(new MapperFeature[] {
                mapperfeature
            });
        else
            deserializationconfig = _deserializationConfig.without(new MapperFeature[] {
                mapperfeature
            });
        _deserializationConfig = deserializationconfig;
        return this;
    }

    public ObjectMapper configure(SerializationFeature serializationfeature, boolean flag)
    {
        SerializationConfig serializationconfig;
        if(flag)
            serializationconfig = _serializationConfig.with(serializationfeature);
        else
            serializationconfig = _serializationConfig.without(serializationfeature);
        _serializationConfig = serializationconfig;
        return this;
    }

    public JavaType constructType(Type type)
    {
        return _typeFactory.constructType(type);
    }

    public Object convertValue(Object obj, TypeReference typereference)
        throws IllegalArgumentException
    {
        return convertValue(obj, _typeFactory.constructType(typereference));
    }

    public Object convertValue(Object obj, JavaType javatype)
        throws IllegalArgumentException
    {
        if(obj == null)
            return null;
        else
            return _convert(obj, javatype);
    }

    public Object convertValue(Object obj, Class class1)
        throws IllegalArgumentException
    {
        if(obj == null)
            return null;
        else
            return _convert(obj, _typeFactory.constructType(class1));
    }

    public ObjectMapper copy()
    {
        _checkInvalidCopy(com/fasterxml/jackson/databind/ObjectMapper);
        return new ObjectMapper(this);
    }

    public volatile TreeNode createArrayNode()
    {
        return createArrayNode();
    }

    public ArrayNode createArrayNode()
    {
        return _deserializationConfig.getNodeFactory().arrayNode();
    }

    protected DefaultDeserializationContext createDeserializationContext(JsonParser jsonparser, DeserializationConfig deserializationconfig)
    {
        return _deserializationContext.createInstance(deserializationconfig, jsonparser, _injectableValues);
    }

    public volatile TreeNode createObjectNode()
    {
        return createObjectNode();
    }

    public ObjectNode createObjectNode()
    {
        return _deserializationConfig.getNodeFactory().objectNode();
    }

    public ObjectMapper disable(DeserializationFeature deserializationfeature)
    {
        _deserializationConfig = _deserializationConfig.without(deserializationfeature);
        return this;
    }

    public transient ObjectMapper disable(DeserializationFeature deserializationfeature, DeserializationFeature adeserializationfeature[])
    {
        _deserializationConfig = _deserializationConfig.without(deserializationfeature, adeserializationfeature);
        return this;
    }

    public ObjectMapper disable(SerializationFeature serializationfeature)
    {
        _serializationConfig = _serializationConfig.without(serializationfeature);
        return this;
    }

    public transient ObjectMapper disable(SerializationFeature serializationfeature, SerializationFeature aserializationfeature[])
    {
        _serializationConfig = _serializationConfig.without(serializationfeature, aserializationfeature);
        return this;
    }

    public transient ObjectMapper disable(MapperFeature amapperfeature[])
    {
        _deserializationConfig = _deserializationConfig.without(amapperfeature);
        _serializationConfig = _serializationConfig.without(amapperfeature);
        return this;
    }

    public ObjectMapper disableDefaultTyping()
    {
        return setDefaultTyping(null);
    }

    public ObjectMapper enable(DeserializationFeature deserializationfeature)
    {
        _deserializationConfig = _deserializationConfig.with(deserializationfeature);
        return this;
    }

    public transient ObjectMapper enable(DeserializationFeature deserializationfeature, DeserializationFeature adeserializationfeature[])
    {
        _deserializationConfig = _deserializationConfig.with(deserializationfeature, adeserializationfeature);
        return this;
    }

    public ObjectMapper enable(SerializationFeature serializationfeature)
    {
        _serializationConfig = _serializationConfig.with(serializationfeature);
        return this;
    }

    public transient ObjectMapper enable(SerializationFeature serializationfeature, SerializationFeature aserializationfeature[])
    {
        _serializationConfig = _serializationConfig.with(serializationfeature, aserializationfeature);
        return this;
    }

    public transient ObjectMapper enable(MapperFeature amapperfeature[])
    {
        _deserializationConfig = _deserializationConfig.with(amapperfeature);
        _serializationConfig = _serializationConfig.with(amapperfeature);
        return this;
    }

    public ObjectMapper enableDefaultTyping()
    {
        return enableDefaultTyping(DefaultTyping.OBJECT_AND_NON_CONCRETE);
    }

    public ObjectMapper enableDefaultTyping(DefaultTyping defaulttyping)
    {
        return enableDefaultTyping(defaulttyping, com.fasterxml.jackson.annotation.JsonTypeInfo.As.WRAPPER_ARRAY);
    }

    public ObjectMapper enableDefaultTyping(DefaultTyping defaulttyping, com.fasterxml.jackson.annotation.JsonTypeInfo.As as)
    {
        return setDefaultTyping((new DefaultTypeResolverBuilder(defaulttyping)).init(com.fasterxml.jackson.annotation.JsonTypeInfo.Id.CLASS, null).inclusion(as));
    }

    public ObjectMapper enableDefaultTypingAsProperty(DefaultTyping defaulttyping, String s)
    {
        return setDefaultTyping((new DefaultTypeResolverBuilder(defaulttyping)).init(com.fasterxml.jackson.annotation.JsonTypeInfo.Id.CLASS, null).inclusion(com.fasterxml.jackson.annotation.JsonTypeInfo.As.PROPERTY).typeProperty(s));
    }

    public ObjectMapper findAndRegisterModules()
    {
        return registerModules(findModules());
    }

    public final Class findMixInClassFor(Class class1)
    {
        if(_mixInAnnotations == null)
            return null;
        else
            return (Class)_mixInAnnotations.get(new ClassKey(class1));
    }

    public JsonSchema generateJsonSchema(Class class1)
        throws JsonMappingException
    {
        return _serializerProvider(getSerializationConfig()).generateJsonSchema(class1);
    }

    public DeserializationConfig getDeserializationConfig()
    {
        return _deserializationConfig;
    }

    public DeserializationContext getDeserializationContext()
    {
        return _deserializationContext;
    }

    public JsonFactory getFactory()
    {
        return _jsonFactory;
    }

    public JsonFactory getJsonFactory()
    {
        return _jsonFactory;
    }

    public JsonNodeFactory getNodeFactory()
    {
        return _deserializationConfig.getNodeFactory();
    }

    public SerializationConfig getSerializationConfig()
    {
        return _serializationConfig;
    }

    public SerializerFactory getSerializerFactory()
    {
        return _serializerFactory;
    }

    public SerializerProvider getSerializerProvider()
    {
        return _serializerProvider;
    }

    public SubtypeResolver getSubtypeResolver()
    {
        return _subtypeResolver;
    }

    public TypeFactory getTypeFactory()
    {
        return _typeFactory;
    }

    public VisibilityChecker getVisibilityChecker()
    {
        return _serializationConfig.getDefaultVisibilityChecker();
    }

    public boolean isEnabled(com.fasterxml.jackson.core.JsonFactory.Feature feature)
    {
        return _jsonFactory.isEnabled(feature);
    }

    public boolean isEnabled(com.fasterxml.jackson.core.JsonGenerator.Feature feature)
    {
        return _jsonFactory.isEnabled(feature);
    }

    public boolean isEnabled(com.fasterxml.jackson.core.JsonParser.Feature feature)
    {
        return _jsonFactory.isEnabled(feature);
    }

    public boolean isEnabled(DeserializationFeature deserializationfeature)
    {
        return _deserializationConfig.isEnabled(deserializationfeature);
    }

    public boolean isEnabled(MapperFeature mapperfeature)
    {
        return _serializationConfig.isEnabled(mapperfeature);
    }

    public boolean isEnabled(SerializationFeature serializationfeature)
    {
        return _serializationConfig.isEnabled(serializationfeature);
    }

    public final int mixInCount()
    {
        if(_mixInAnnotations == null)
            return 0;
        else
            return _mixInAnnotations.size();
    }

    public TreeNode readTree(JsonParser jsonparser)
        throws IOException, JsonProcessingException
    {
        DeserializationConfig deserializationconfig = getDeserializationConfig();
        Object obj;
        if(jsonparser.getCurrentToken() == null && jsonparser.nextToken() == null)
        {
            obj = null;
        } else
        {
            obj = (JsonNode)_readValue(deserializationconfig, jsonparser, JSON_NODE_TYPE);
            if(obj == null)
                return getNodeFactory().nullNode();
        }
        return ((TreeNode) (obj));
    }

    public JsonNode readTree(File file)
        throws IOException, JsonProcessingException
    {
        Object obj = (JsonNode)_readMapAndClose(_jsonFactory.createParser(file), JSON_NODE_TYPE);
        if(obj == null)
            obj = NullNode.instance;
        return ((JsonNode) (obj));
    }

    public JsonNode readTree(InputStream inputstream)
        throws IOException, JsonProcessingException
    {
        Object obj = (JsonNode)_readMapAndClose(_jsonFactory.createParser(inputstream), JSON_NODE_TYPE);
        if(obj == null)
            obj = NullNode.instance;
        return ((JsonNode) (obj));
    }

    public JsonNode readTree(Reader reader1)
        throws IOException, JsonProcessingException
    {
        Object obj = (JsonNode)_readMapAndClose(_jsonFactory.createParser(reader1), JSON_NODE_TYPE);
        if(obj == null)
            obj = NullNode.instance;
        return ((JsonNode) (obj));
    }

    public JsonNode readTree(String s)
        throws IOException, JsonProcessingException
    {
        Object obj = (JsonNode)_readMapAndClose(_jsonFactory.createParser(s), JSON_NODE_TYPE);
        if(obj == null)
            obj = NullNode.instance;
        return ((JsonNode) (obj));
    }

    public JsonNode readTree(URL url)
        throws IOException, JsonProcessingException
    {
        Object obj = (JsonNode)_readMapAndClose(_jsonFactory.createParser(url), JSON_NODE_TYPE);
        if(obj == null)
            obj = NullNode.instance;
        return ((JsonNode) (obj));
    }

    public JsonNode readTree(byte abyte0[])
        throws IOException, JsonProcessingException
    {
        Object obj = (JsonNode)_readMapAndClose(_jsonFactory.createParser(abyte0), JSON_NODE_TYPE);
        if(obj == null)
            obj = NullNode.instance;
        return ((JsonNode) (obj));
    }

    public final Object readValue(JsonParser jsonparser, ResolvedType resolvedtype)
        throws IOException, JsonParseException, JsonMappingException
    {
        return _readValue(getDeserializationConfig(), jsonparser, (JavaType)resolvedtype);
    }

    public Object readValue(JsonParser jsonparser, TypeReference typereference)
        throws IOException, JsonParseException, JsonMappingException
    {
        return _readValue(getDeserializationConfig(), jsonparser, _typeFactory.constructType(typereference));
    }

    public Object readValue(JsonParser jsonparser, JavaType javatype)
        throws IOException, JsonParseException, JsonMappingException
    {
        return _readValue(getDeserializationConfig(), jsonparser, javatype);
    }

    public Object readValue(JsonParser jsonparser, Class class1)
        throws IOException, JsonParseException, JsonMappingException
    {
        return _readValue(getDeserializationConfig(), jsonparser, _typeFactory.constructType(class1));
    }

    public Object readValue(File file, TypeReference typereference)
        throws IOException, JsonParseException, JsonMappingException
    {
        return _readMapAndClose(_jsonFactory.createParser(file), _typeFactory.constructType(typereference));
    }

    public Object readValue(File file, JavaType javatype)
        throws IOException, JsonParseException, JsonMappingException
    {
        return _readMapAndClose(_jsonFactory.createParser(file), javatype);
    }

    public Object readValue(File file, Class class1)
        throws IOException, JsonParseException, JsonMappingException
    {
        return _readMapAndClose(_jsonFactory.createParser(file), _typeFactory.constructType(class1));
    }

    public Object readValue(InputStream inputstream, TypeReference typereference)
        throws IOException, JsonParseException, JsonMappingException
    {
        return _readMapAndClose(_jsonFactory.createParser(inputstream), _typeFactory.constructType(typereference));
    }

    public Object readValue(InputStream inputstream, JavaType javatype)
        throws IOException, JsonParseException, JsonMappingException
    {
        return _readMapAndClose(_jsonFactory.createParser(inputstream), javatype);
    }

    public Object readValue(InputStream inputstream, Class class1)
        throws IOException, JsonParseException, JsonMappingException
    {
        return _readMapAndClose(_jsonFactory.createParser(inputstream), _typeFactory.constructType(class1));
    }

    public Object readValue(Reader reader1, TypeReference typereference)
        throws IOException, JsonParseException, JsonMappingException
    {
        return _readMapAndClose(_jsonFactory.createParser(reader1), _typeFactory.constructType(typereference));
    }

    public Object readValue(Reader reader1, JavaType javatype)
        throws IOException, JsonParseException, JsonMappingException
    {
        return _readMapAndClose(_jsonFactory.createParser(reader1), javatype);
    }

    public Object readValue(Reader reader1, Class class1)
        throws IOException, JsonParseException, JsonMappingException
    {
        return _readMapAndClose(_jsonFactory.createParser(reader1), _typeFactory.constructType(class1));
    }

    public Object readValue(String s, TypeReference typereference)
        throws IOException, JsonParseException, JsonMappingException
    {
        return _readMapAndClose(_jsonFactory.createParser(s), _typeFactory.constructType(typereference));
    }

    public Object readValue(String s, JavaType javatype)
        throws IOException, JsonParseException, JsonMappingException
    {
        return _readMapAndClose(_jsonFactory.createParser(s), javatype);
    }

    public Object readValue(String s, Class class1)
        throws IOException, JsonParseException, JsonMappingException
    {
        return _readMapAndClose(_jsonFactory.createParser(s), _typeFactory.constructType(class1));
    }

    public Object readValue(URL url, TypeReference typereference)
        throws IOException, JsonParseException, JsonMappingException
    {
        return _readMapAndClose(_jsonFactory.createParser(url), _typeFactory.constructType(typereference));
    }

    public Object readValue(URL url, JavaType javatype)
        throws IOException, JsonParseException, JsonMappingException
    {
        return _readMapAndClose(_jsonFactory.createParser(url), javatype);
    }

    public Object readValue(URL url, Class class1)
        throws IOException, JsonParseException, JsonMappingException
    {
        return _readMapAndClose(_jsonFactory.createParser(url), _typeFactory.constructType(class1));
    }

    public Object readValue(byte abyte0[], int i, int j, TypeReference typereference)
        throws IOException, JsonParseException, JsonMappingException
    {
        return _readMapAndClose(_jsonFactory.createParser(abyte0, i, j), _typeFactory.constructType(typereference));
    }

    public Object readValue(byte abyte0[], int i, int j, JavaType javatype)
        throws IOException, JsonParseException, JsonMappingException
    {
        return _readMapAndClose(_jsonFactory.createParser(abyte0, i, j), javatype);
    }

    public Object readValue(byte abyte0[], int i, int j, Class class1)
        throws IOException, JsonParseException, JsonMappingException
    {
        return _readMapAndClose(_jsonFactory.createParser(abyte0, i, j), _typeFactory.constructType(class1));
    }

    public Object readValue(byte abyte0[], TypeReference typereference)
        throws IOException, JsonParseException, JsonMappingException
    {
        return _readMapAndClose(_jsonFactory.createParser(abyte0), _typeFactory.constructType(typereference));
    }

    public Object readValue(byte abyte0[], JavaType javatype)
        throws IOException, JsonParseException, JsonMappingException
    {
        return _readMapAndClose(_jsonFactory.createParser(abyte0), javatype);
    }

    public Object readValue(byte abyte0[], Class class1)
        throws IOException, JsonParseException, JsonMappingException
    {
        return _readMapAndClose(_jsonFactory.createParser(abyte0), _typeFactory.constructType(class1));
    }

    public MappingIterator readValues(JsonParser jsonparser, ResolvedType resolvedtype)
        throws IOException, JsonProcessingException
    {
        return readValues(jsonparser, (JavaType)resolvedtype);
    }

    public MappingIterator readValues(JsonParser jsonparser, TypeReference typereference)
        throws IOException, JsonProcessingException
    {
        return readValues(jsonparser, _typeFactory.constructType(typereference));
    }

    public MappingIterator readValues(JsonParser jsonparser, JavaType javatype)
        throws IOException, JsonProcessingException
    {
        DefaultDeserializationContext defaultdeserializationcontext = createDeserializationContext(jsonparser, getDeserializationConfig());
        return new MappingIterator(javatype, jsonparser, defaultdeserializationcontext, _findRootDeserializer(defaultdeserializationcontext, javatype), false, null);
    }

    public MappingIterator readValues(JsonParser jsonparser, Class class1)
        throws IOException, JsonProcessingException
    {
        return readValues(jsonparser, _typeFactory.constructType(class1));
    }

    public volatile Iterator readValues(JsonParser jsonparser, ResolvedType resolvedtype)
        throws IOException, JsonProcessingException
    {
        return readValues(jsonparser, resolvedtype);
    }

    public volatile Iterator readValues(JsonParser jsonparser, TypeReference typereference)
        throws IOException, JsonProcessingException
    {
        return readValues(jsonparser, typereference);
    }

    public volatile Iterator readValues(JsonParser jsonparser, Class class1)
        throws IOException, JsonProcessingException
    {
        return readValues(jsonparser, class1);
    }

    public ObjectReader reader()
    {
        return (new ObjectReader(this, getDeserializationConfig())).with(_injectableValues);
    }

    public ObjectReader reader(Base64Variant base64variant)
    {
        return new ObjectReader(this, getDeserializationConfig().with(base64variant));
    }

    public ObjectReader reader(FormatSchema formatschema)
    {
        _verifySchemaType(formatschema);
        return new ObjectReader(this, getDeserializationConfig(), null, null, formatschema, _injectableValues);
    }

    public ObjectReader reader(TypeReference typereference)
    {
        return reader(_typeFactory.constructType(typereference));
    }

    public ObjectReader reader(DeserializationFeature deserializationfeature)
    {
        return new ObjectReader(this, getDeserializationConfig().with(deserializationfeature));
    }

    public transient ObjectReader reader(DeserializationFeature deserializationfeature, DeserializationFeature adeserializationfeature[])
    {
        return new ObjectReader(this, getDeserializationConfig().with(deserializationfeature, adeserializationfeature));
    }

    public ObjectReader reader(InjectableValues injectablevalues)
    {
        return new ObjectReader(this, getDeserializationConfig(), null, null, null, injectablevalues);
    }

    public ObjectReader reader(JavaType javatype)
    {
        return new ObjectReader(this, getDeserializationConfig(), javatype, null, null, _injectableValues);
    }

    public ObjectReader reader(ContextAttributes contextattributes)
    {
        return new ObjectReader(this, getDeserializationConfig().with(contextattributes));
    }

    public ObjectReader reader(JsonNodeFactory jsonnodefactory)
    {
        return (new ObjectReader(this, getDeserializationConfig())).with(jsonnodefactory);
    }

    public ObjectReader reader(Class class1)
    {
        return reader(_typeFactory.constructType(class1));
    }

    public ObjectReader readerForUpdating(Object obj)
    {
        JavaType javatype = _typeFactory.constructType(obj.getClass());
        return new ObjectReader(this, getDeserializationConfig(), javatype, obj, null, _injectableValues);
    }

    public ObjectReader readerWithView(Class class1)
    {
        return new ObjectReader(this, getDeserializationConfig().withView(class1));
    }

    public ObjectMapper registerModule(Module module)
    {
        if(module.getModuleName() == null)
            throw new IllegalArgumentException("Module without defined name");
        if(module.version() == null)
        {
            throw new IllegalArgumentException("Module without defined version");
        } else
        {
            module.setupModule(new Module.SetupContext() {

                public void addAbstractTypeResolver(AbstractTypeResolver abstracttyperesolver)
                {
                    DeserializerFactory deserializerfactory = mapper._deserializationContext._factory.withAbstractTypeResolver(abstracttyperesolver);
                    mapper._deserializationContext = mapper._deserializationContext.with(deserializerfactory);
                }

                public void addBeanDeserializerModifier(BeanDeserializerModifier beandeserializermodifier)
                {
                    DeserializerFactory deserializerfactory = mapper._deserializationContext._factory.withDeserializerModifier(beandeserializermodifier);
                    mapper._deserializationContext = mapper._deserializationContext.with(deserializerfactory);
                }

                public void addBeanSerializerModifier(BeanSerializerModifier beanserializermodifier)
                {
                    mapper._serializerFactory = mapper._serializerFactory.withSerializerModifier(beanserializermodifier);
                }

                public void addDeserializationProblemHandler(DeserializationProblemHandler deserializationproblemhandler)
                {
                    mapper.addHandler(deserializationproblemhandler);
                }

                public void addDeserializers(Deserializers deserializers)
                {
                    DeserializerFactory deserializerfactory = mapper._deserializationContext._factory.withAdditionalDeserializers(deserializers);
                    mapper._deserializationContext = mapper._deserializationContext.with(deserializerfactory);
                }

                public void addKeyDeserializers(KeyDeserializers keydeserializers)
                {
                    DeserializerFactory deserializerfactory = mapper._deserializationContext._factory.withAdditionalKeyDeserializers(keydeserializers);
                    mapper._deserializationContext = mapper._deserializationContext.with(deserializerfactory);
                }

                public void addKeySerializers(Serializers serializers)
                {
                    mapper._serializerFactory = mapper._serializerFactory.withAdditionalKeySerializers(serializers);
                }

                public void addSerializers(Serializers serializers)
                {
                    mapper._serializerFactory = mapper._serializerFactory.withAdditionalSerializers(serializers);
                }

                public void addTypeModifier(TypeModifier typemodifier)
                {
                    TypeFactory typefactory = mapper._typeFactory.withModifier(typemodifier);
                    mapper.setTypeFactory(typefactory);
                }

                public void addValueInstantiators(ValueInstantiators valueinstantiators)
                {
                    DeserializerFactory deserializerfactory = mapper._deserializationContext._factory.withValueInstantiators(valueinstantiators);
                    mapper._deserializationContext = mapper._deserializationContext.with(deserializerfactory);
                }

                public void appendAnnotationIntrospector(AnnotationIntrospector annotationintrospector)
                {
                    mapper._deserializationConfig = mapper._deserializationConfig.withAppendedAnnotationIntrospector(annotationintrospector);
                    mapper._serializationConfig = mapper._serializationConfig.withAppendedAnnotationIntrospector(annotationintrospector);
                }

                public Version getMapperVersion()
                {
                    return version();
                }

                public ObjectCodec getOwner()
                {
                    return mapper;
                }

                public TypeFactory getTypeFactory()
                {
                    return _typeFactory;
                }

                public void insertAnnotationIntrospector(AnnotationIntrospector annotationintrospector)
                {
                    mapper._deserializationConfig = mapper._deserializationConfig.withInsertedAnnotationIntrospector(annotationintrospector);
                    mapper._serializationConfig = mapper._serializationConfig.withInsertedAnnotationIntrospector(annotationintrospector);
                }

                public boolean isEnabled(com.fasterxml.jackson.core.JsonFactory.Feature feature)
                {
                    return mapper.isEnabled(feature);
                }

                public boolean isEnabled(com.fasterxml.jackson.core.JsonGenerator.Feature feature)
                {
                    return mapper.isEnabled(feature);
                }

                public boolean isEnabled(com.fasterxml.jackson.core.JsonParser.Feature feature)
                {
                    return mapper.isEnabled(feature);
                }

                public boolean isEnabled(DeserializationFeature deserializationfeature)
                {
                    return mapper.isEnabled(deserializationfeature);
                }

                public boolean isEnabled(MapperFeature mapperfeature)
                {
                    return mapper.isEnabled(mapperfeature);
                }

                public boolean isEnabled(SerializationFeature serializationfeature)
                {
                    return mapper.isEnabled(serializationfeature);
                }

                public transient void registerSubtypes(NamedType anamedtype[])
                {
                    mapper.registerSubtypes(anamedtype);
                }

                public transient void registerSubtypes(Class aclass[])
                {
                    mapper.registerSubtypes(aclass);
                }

                public void setClassIntrospector(ClassIntrospector classintrospector)
                {
                    mapper._deserializationConfig = mapper._deserializationConfig.with(classintrospector);
                    mapper._serializationConfig = mapper._serializationConfig.with(classintrospector);
                }

                public void setMixInAnnotations(Class class1, Class class2)
                {
                    mapper.addMixInAnnotations(class1, class2);
                }

                public void setNamingStrategy(PropertyNamingStrategy propertynamingstrategy)
                {
                    mapper.setPropertyNamingStrategy(propertynamingstrategy);
                }

                final ObjectMapper this$0;
                final ObjectMapper val$mapper;

            
            {
                this$0 = ObjectMapper.this;
                mapper = objectmapper1;
                super();
            }
            }
);
            return this;
        }
    }

    public ObjectMapper registerModules(Iterable iterable)
    {
        for(Iterator iterator = iterable.iterator(); iterator.hasNext(); registerModule((Module)iterator.next()));
        return this;
    }

    public transient ObjectMapper registerModules(Module amodule[])
    {
        int i = amodule.length;
        for(int j = 0; j < i; j++)
            registerModule(amodule[j]);

        return this;
    }

    public transient void registerSubtypes(NamedType anamedtype[])
    {
        getSubtypeResolver().registerSubtypes(anamedtype);
    }

    public transient void registerSubtypes(Class aclass[])
    {
        getSubtypeResolver().registerSubtypes(aclass);
    }

    public ObjectMapper setAnnotationIntrospector(AnnotationIntrospector annotationintrospector)
    {
        _serializationConfig = _serializationConfig.with(annotationintrospector);
        _deserializationConfig = _deserializationConfig.with(annotationintrospector);
        return this;
    }

    public ObjectMapper setAnnotationIntrospectors(AnnotationIntrospector annotationintrospector, AnnotationIntrospector annotationintrospector1)
    {
        _serializationConfig = _serializationConfig.with(annotationintrospector);
        _deserializationConfig = _deserializationConfig.with(annotationintrospector1);
        return this;
    }

    public ObjectMapper setBase64Variant(Base64Variant base64variant)
    {
        _serializationConfig = _serializationConfig.with(base64variant);
        _deserializationConfig = _deserializationConfig.with(base64variant);
        return this;
    }

    public ObjectMapper setDateFormat(DateFormat dateformat)
    {
        _deserializationConfig = _deserializationConfig.with(dateformat);
        _serializationConfig = _serializationConfig.with(dateformat);
        return this;
    }

    public ObjectMapper setDefaultTyping(TypeResolverBuilder typeresolverbuilder)
    {
        _deserializationConfig = _deserializationConfig.with(typeresolverbuilder);
        _serializationConfig = _serializationConfig.with(typeresolverbuilder);
        return this;
    }

    public void setFilters(FilterProvider filterprovider)
    {
        _serializationConfig = _serializationConfig.withFilters(filterprovider);
    }

    public Object setHandlerInstantiator(HandlerInstantiator handlerinstantiator)
    {
        _deserializationConfig = _deserializationConfig.with(handlerinstantiator);
        _serializationConfig = _serializationConfig.with(handlerinstantiator);
        return this;
    }

    public ObjectMapper setInjectableValues(InjectableValues injectablevalues)
    {
        _injectableValues = injectablevalues;
        return this;
    }

    public ObjectMapper setLocale(Locale locale)
    {
        _deserializationConfig = _deserializationConfig.with(locale);
        _serializationConfig = _serializationConfig.with(locale);
        return this;
    }

    public final void setMixInAnnotations(Map map)
    {
        _mixInAnnotations.clear();
        if(map != null && map.size() > 0)
        {
            java.util.Map.Entry entry;
            for(Iterator iterator = map.entrySet().iterator(); iterator.hasNext(); _mixInAnnotations.put(new ClassKey((Class)entry.getKey()), entry.getValue()))
                entry = (java.util.Map.Entry)iterator.next();

        }
    }

    public ObjectMapper setNodeFactory(JsonNodeFactory jsonnodefactory)
    {
        _deserializationConfig = _deserializationConfig.with(jsonnodefactory);
        return this;
    }

    public ObjectMapper setPropertyNamingStrategy(PropertyNamingStrategy propertynamingstrategy)
    {
        _serializationConfig = _serializationConfig.with(propertynamingstrategy);
        _deserializationConfig = _deserializationConfig.with(propertynamingstrategy);
        return this;
    }

    public ObjectMapper setSerializationInclusion(com.fasterxml.jackson.annotation.JsonInclude.Include include)
    {
        _serializationConfig = _serializationConfig.withSerializationInclusion(include);
        return this;
    }

    public ObjectMapper setSerializerFactory(SerializerFactory serializerfactory)
    {
        _serializerFactory = serializerfactory;
        return this;
    }

    public ObjectMapper setSerializerProvider(DefaultSerializerProvider defaultserializerprovider)
    {
        _serializerProvider = defaultserializerprovider;
        return this;
    }

    public ObjectMapper setSubtypeResolver(SubtypeResolver subtyperesolver)
    {
        _subtypeResolver = subtyperesolver;
        _deserializationConfig = _deserializationConfig.with(subtyperesolver);
        _serializationConfig = _serializationConfig.with(subtyperesolver);
        return this;
    }

    public ObjectMapper setTimeZone(TimeZone timezone)
    {
        _deserializationConfig = _deserializationConfig.with(timezone);
        _serializationConfig = _serializationConfig.with(timezone);
        return this;
    }

    public ObjectMapper setTypeFactory(TypeFactory typefactory)
    {
        _typeFactory = typefactory;
        _deserializationConfig = _deserializationConfig.with(typefactory);
        _serializationConfig = _serializationConfig.with(typefactory);
        return this;
    }

    public ObjectMapper setVisibility(PropertyAccessor propertyaccessor, com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility visibility)
    {
        _deserializationConfig = _deserializationConfig.withVisibility(propertyaccessor, visibility);
        _serializationConfig = _serializationConfig.withVisibility(propertyaccessor, visibility);
        return this;
    }

    public void setVisibilityChecker(VisibilityChecker visibilitychecker)
    {
        _deserializationConfig = _deserializationConfig.with(visibilitychecker);
        _serializationConfig = _serializationConfig.with(visibilitychecker);
    }

    public JsonParser treeAsTokens(TreeNode treenode)
    {
        return new TreeTraversingParser((JsonNode)treenode, this);
    }

    public Object treeToValue(TreeNode treenode, Class class1)
        throws JsonProcessingException
    {
        if(class1 == java/lang/Object)
            break MISSING_BLOCK_LABEL_20;
        if(class1.isAssignableFrom(treenode.getClass()))
            return treenode;
        Object obj;
        try
        {
            obj = readValue(treeAsTokens(treenode), class1);
        }
        catch(JsonProcessingException jsonprocessingexception)
        {
            throw jsonprocessingexception;
        }
        catch(IOException ioexception)
        {
            throw new IllegalArgumentException(ioexception.getMessage(), ioexception);
        }
        return obj;
    }

    public JsonNode valueToTree(Object obj)
        throws IllegalArgumentException
    {
        if(obj == null)
            return null;
        TokenBuffer tokenbuffer = new TokenBuffer(this, false);
        JsonNode jsonnode;
        try
        {
            writeValue(tokenbuffer, obj);
            JsonParser jsonparser = tokenbuffer.asParser();
            jsonnode = (JsonNode)readTree(jsonparser);
            jsonparser.close();
        }
        catch(IOException ioexception)
        {
            throw new IllegalArgumentException(ioexception.getMessage(), ioexception);
        }
        return jsonnode;
    }

    public Version version()
    {
        return PackageVersion.VERSION;
    }

    public void writeTree(JsonGenerator jsongenerator, TreeNode treenode)
        throws IOException, JsonProcessingException
    {
        SerializationConfig serializationconfig = getSerializationConfig();
        _serializerProvider(serializationconfig).serializeValue(jsongenerator, treenode);
        if(serializationconfig.isEnabled(SerializationFeature.FLUSH_AFTER_WRITE_VALUE))
            jsongenerator.flush();
    }

    public void writeTree(JsonGenerator jsongenerator, JsonNode jsonnode)
        throws IOException, JsonProcessingException
    {
        SerializationConfig serializationconfig = getSerializationConfig();
        _serializerProvider(serializationconfig).serializeValue(jsongenerator, jsonnode);
        if(serializationconfig.isEnabled(SerializationFeature.FLUSH_AFTER_WRITE_VALUE))
            jsongenerator.flush();
    }

    public void writeValue(JsonGenerator jsongenerator, Object obj)
        throws IOException, JsonGenerationException, JsonMappingException
    {
        SerializationConfig serializationconfig = getSerializationConfig();
        if(serializationconfig.isEnabled(SerializationFeature.INDENT_OUTPUT))
            jsongenerator.useDefaultPrettyPrinter();
        if(serializationconfig.isEnabled(SerializationFeature.CLOSE_CLOSEABLE) && (obj instanceof Closeable))
        {
            _writeCloseableValue(jsongenerator, obj, serializationconfig);
        } else
        {
            _serializerProvider(serializationconfig).serializeValue(jsongenerator, obj);
            if(serializationconfig.isEnabled(SerializationFeature.FLUSH_AFTER_WRITE_VALUE))
            {
                jsongenerator.flush();
                return;
            }
        }
    }

    public void writeValue(File file, Object obj)
        throws IOException, JsonGenerationException, JsonMappingException
    {
        _configAndWriteValue(_jsonFactory.createGenerator(file, JsonEncoding.UTF8), obj);
    }

    public void writeValue(OutputStream outputstream, Object obj)
        throws IOException, JsonGenerationException, JsonMappingException
    {
        _configAndWriteValue(_jsonFactory.createGenerator(outputstream, JsonEncoding.UTF8), obj);
    }

    public void writeValue(Writer writer1, Object obj)
        throws IOException, JsonGenerationException, JsonMappingException
    {
        _configAndWriteValue(_jsonFactory.createGenerator(writer1), obj);
    }

    public byte[] writeValueAsBytes(Object obj)
        throws JsonProcessingException
    {
        ByteArrayBuilder bytearraybuilder = new ByteArrayBuilder(_jsonFactory._getBufferRecycler());
        byte abyte0[];
        try
        {
            _configAndWriteValue(_jsonFactory.createGenerator(bytearraybuilder, JsonEncoding.UTF8), obj);
        }
        catch(JsonProcessingException jsonprocessingexception)
        {
            throw jsonprocessingexception;
        }
        catch(IOException ioexception)
        {
            throw JsonMappingException.fromUnexpectedIOE(ioexception);
        }
        abyte0 = bytearraybuilder.toByteArray();
        bytearraybuilder.release();
        return abyte0;
    }

    public String writeValueAsString(Object obj)
        throws JsonProcessingException
    {
        SegmentedStringWriter segmentedstringwriter = new SegmentedStringWriter(_jsonFactory._getBufferRecycler());
        try
        {
            _configAndWriteValue(_jsonFactory.createGenerator(segmentedstringwriter), obj);
        }
        catch(JsonProcessingException jsonprocessingexception)
        {
            throw jsonprocessingexception;
        }
        catch(IOException ioexception)
        {
            throw JsonMappingException.fromUnexpectedIOE(ioexception);
        }
        return segmentedstringwriter.getAndClear();
    }

    public ObjectWriter writer()
    {
        return new ObjectWriter(this, getSerializationConfig());
    }

    public ObjectWriter writer(Base64Variant base64variant)
    {
        return new ObjectWriter(this, getSerializationConfig().with(base64variant));
    }

    public ObjectWriter writer(FormatSchema formatschema)
    {
        _verifySchemaType(formatschema);
        return new ObjectWriter(this, getSerializationConfig(), formatschema);
    }

    public ObjectWriter writer(PrettyPrinter prettyprinter)
    {
        if(prettyprinter == null)
            prettyprinter = ObjectWriter.NULL_PRETTY_PRINTER;
        return new ObjectWriter(this, getSerializationConfig(), null, prettyprinter);
    }

    public ObjectWriter writer(CharacterEscapes characterescapes)
    {
        return writer().with(characterescapes);
    }

    public ObjectWriter writer(SerializationFeature serializationfeature)
    {
        return new ObjectWriter(this, getSerializationConfig().with(serializationfeature));
    }

    public transient ObjectWriter writer(SerializationFeature serializationfeature, SerializationFeature aserializationfeature[])
    {
        return new ObjectWriter(this, getSerializationConfig().with(serializationfeature, aserializationfeature));
    }

    public ObjectWriter writer(ContextAttributes contextattributes)
    {
        return new ObjectWriter(this, getSerializationConfig().with(contextattributes));
    }

    public ObjectWriter writer(FilterProvider filterprovider)
    {
        return new ObjectWriter(this, getSerializationConfig().withFilters(filterprovider));
    }

    public ObjectWriter writer(DateFormat dateformat)
    {
        return new ObjectWriter(this, getSerializationConfig().with(dateformat));
    }

    public ObjectWriter writerWithDefaultPrettyPrinter()
    {
        return new ObjectWriter(this, getSerializationConfig(), null, _defaultPrettyPrinter());
    }

    public ObjectWriter writerWithType(TypeReference typereference)
    {
        SerializationConfig serializationconfig = getSerializationConfig();
        JavaType javatype;
        if(typereference == null)
            javatype = null;
        else
            javatype = _typeFactory.constructType(typereference);
        return new ObjectWriter(this, serializationconfig, javatype, null);
    }

    public ObjectWriter writerWithType(JavaType javatype)
    {
        return new ObjectWriter(this, getSerializationConfig(), javatype, null);
    }

    public ObjectWriter writerWithType(Class class1)
    {
        SerializationConfig serializationconfig = getSerializationConfig();
        JavaType javatype;
        if(class1 == null)
            javatype = null;
        else
            javatype = _typeFactory.constructType(class1);
        return new ObjectWriter(this, serializationconfig, javatype, null);
    }

    public ObjectWriter writerWithView(Class class1)
    {
        return new ObjectWriter(this, getSerializationConfig().withView(class1));
    }

    protected static final AnnotationIntrospector DEFAULT_ANNOTATION_INTROSPECTOR;
    protected static final BaseSettings DEFAULT_BASE;
    protected static final ClassIntrospector DEFAULT_INTROSPECTOR;
    private static final JavaType JSON_NODE_TYPE = SimpleType.constructUnsafe(com/fasterxml/jackson/databind/JsonNode);
    protected static final VisibilityChecker STD_VISIBILITY_CHECKER;
    protected static final PrettyPrinter _defaultPrettyPrinter = new DefaultPrettyPrinter();
    private static final long serialVersionUID = 1L;
    protected DeserializationConfig _deserializationConfig;
    protected DefaultDeserializationContext _deserializationContext;
    protected InjectableValues _injectableValues;
    protected final JsonFactory _jsonFactory;
    protected final HashMap _mixInAnnotations;
    protected final ConcurrentHashMap _rootDeserializers;
    protected final RootNameLookup _rootNames;
    protected SerializationConfig _serializationConfig;
    protected SerializerFactory _serializerFactory;
    protected DefaultSerializerProvider _serializerProvider;
    protected SubtypeResolver _subtypeResolver;
    protected TypeFactory _typeFactory;

    static 
    {
        DEFAULT_INTROSPECTOR = BasicClassIntrospector.instance;
        DEFAULT_ANNOTATION_INTROSPECTOR = new JacksonAnnotationIntrospector();
        STD_VISIBILITY_CHECKER = com.fasterxml.jackson.databind.introspect.VisibilityChecker.Std.defaultInstance();
        DEFAULT_BASE = new BaseSettings(DEFAULT_INTROSPECTOR, DEFAULT_ANNOTATION_INTROSPECTOR, STD_VISIBILITY_CHECKER, null, TypeFactory.defaultInstance(), null, StdDateFormat.instance, null, Locale.getDefault(), TimeZone.getTimeZone("GMT"), Base64Variants.getDefaultVariant());
    }
}
