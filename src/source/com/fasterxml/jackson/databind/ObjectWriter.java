// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.fasterxml.jackson.databind;

import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.core.io.CharacterEscapes;
import com.fasterxml.jackson.core.io.SegmentedStringWriter;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.core.util.*;
import com.fasterxml.jackson.databind.cfg.ContextAttributes;
import com.fasterxml.jackson.databind.cfg.PackageVersion;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatVisitorWrapper;
import com.fasterxml.jackson.databind.ser.DefaultSerializerProvider;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.SerializerFactory;
import com.fasterxml.jackson.databind.type.TypeFactory;
import java.io.*;
import java.text.DateFormat;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

// Referenced classes of package com.fasterxml.jackson.databind:
//            SerializationFeature, SerializationConfig, ObjectMapper, JavaType, 
//            MapperFeature, JsonMappingException, JsonSerializer

public class ObjectWriter
    implements Versioned, Serializable
{

    protected ObjectWriter(ObjectMapper objectmapper, SerializationConfig serializationconfig)
    {
        _config = serializationconfig;
        _cfgBigDecimalAsPlain = _config.isEnabled(SerializationFeature.WRITE_BIGDECIMAL_AS_PLAIN);
        _serializerProvider = objectmapper._serializerProvider;
        _serializerFactory = objectmapper._serializerFactory;
        _generatorFactory = objectmapper._jsonFactory;
        _rootType = null;
        _rootSerializer = null;
        _prettyPrinter = null;
        _schema = null;
        _characterEscapes = null;
    }

    protected ObjectWriter(ObjectMapper objectmapper, SerializationConfig serializationconfig, FormatSchema formatschema)
    {
        _config = serializationconfig;
        _cfgBigDecimalAsPlain = _config.isEnabled(SerializationFeature.WRITE_BIGDECIMAL_AS_PLAIN);
        _serializerProvider = objectmapper._serializerProvider;
        _serializerFactory = objectmapper._serializerFactory;
        _generatorFactory = objectmapper._jsonFactory;
        _rootType = null;
        _rootSerializer = null;
        _prettyPrinter = null;
        _schema = formatschema;
        _characterEscapes = null;
    }

    protected ObjectWriter(ObjectMapper objectmapper, SerializationConfig serializationconfig, JavaType javatype, PrettyPrinter prettyprinter)
    {
        _config = serializationconfig;
        _cfgBigDecimalAsPlain = _config.isEnabled(SerializationFeature.WRITE_BIGDECIMAL_AS_PLAIN);
        _serializerProvider = objectmapper._serializerProvider;
        _serializerFactory = objectmapper._serializerFactory;
        _generatorFactory = objectmapper._jsonFactory;
        if(javatype != null)
            javatype = javatype.withStaticTyping();
        _rootType = javatype;
        _prettyPrinter = prettyprinter;
        _schema = null;
        _characterEscapes = null;
        _rootSerializer = _prefetchRootSerializer(serializationconfig, javatype);
    }

    protected ObjectWriter(ObjectWriter objectwriter, JsonFactory jsonfactory)
    {
        _config = objectwriter._config.with(MapperFeature.SORT_PROPERTIES_ALPHABETICALLY, jsonfactory.requiresPropertyOrdering());
        _cfgBigDecimalAsPlain = objectwriter._cfgBigDecimalAsPlain;
        _serializerProvider = objectwriter._serializerProvider;
        _serializerFactory = objectwriter._serializerFactory;
        _generatorFactory = objectwriter._generatorFactory;
        _schema = objectwriter._schema;
        _characterEscapes = objectwriter._characterEscapes;
        _rootType = objectwriter._rootType;
        _rootSerializer = objectwriter._rootSerializer;
        _prettyPrinter = objectwriter._prettyPrinter;
    }

    protected ObjectWriter(ObjectWriter objectwriter, SerializationConfig serializationconfig)
    {
        _config = serializationconfig;
        _cfgBigDecimalAsPlain = _config.isEnabled(SerializationFeature.WRITE_BIGDECIMAL_AS_PLAIN);
        _serializerProvider = objectwriter._serializerProvider;
        _serializerFactory = objectwriter._serializerFactory;
        _generatorFactory = objectwriter._generatorFactory;
        _schema = objectwriter._schema;
        _characterEscapes = objectwriter._characterEscapes;
        _rootType = objectwriter._rootType;
        _rootSerializer = objectwriter._rootSerializer;
        _prettyPrinter = objectwriter._prettyPrinter;
    }

    protected ObjectWriter(ObjectWriter objectwriter, SerializationConfig serializationconfig, JavaType javatype, JsonSerializer jsonserializer, PrettyPrinter prettyprinter, FormatSchema formatschema, CharacterEscapes characterescapes)
    {
        _config = serializationconfig;
        _cfgBigDecimalAsPlain = _config.isEnabled(SerializationFeature.WRITE_BIGDECIMAL_AS_PLAIN);
        _serializerProvider = objectwriter._serializerProvider;
        _serializerFactory = objectwriter._serializerFactory;
        _generatorFactory = objectwriter._generatorFactory;
        _rootType = javatype;
        _rootSerializer = jsonserializer;
        _prettyPrinter = prettyprinter;
        _schema = formatschema;
        _characterEscapes = characterescapes;
    }

    private void _configureJsonGenerator(JsonGenerator jsongenerator)
    {
        if(_prettyPrinter == null) goto _L2; else goto _L1
_L1:
        PrettyPrinter prettyprinter = _prettyPrinter;
        if(prettyprinter == NULL_PRETTY_PRINTER)
        {
            jsongenerator.setPrettyPrinter(null);
        } else
        {
            if(prettyprinter instanceof Instantiatable)
                prettyprinter = (PrettyPrinter)((Instantiatable)prettyprinter).createInstance();
            jsongenerator.setPrettyPrinter(prettyprinter);
        }
_L4:
        if(_characterEscapes != null)
            jsongenerator.setCharacterEscapes(_characterEscapes);
        if(_schema != null)
            jsongenerator.setSchema(_schema);
        if(_cfgBigDecimalAsPlain)
            jsongenerator.enable(com.fasterxml.jackson.core.JsonGenerator.Feature.WRITE_BIGDECIMAL_AS_PLAIN);
        return;
_L2:
        if(_config.isEnabled(SerializationFeature.INDENT_OUTPUT))
            jsongenerator.useDefaultPrettyPrinter();
        if(true) goto _L4; else goto _L3
_L3:
    }

    private final void _writeCloseable(JsonGenerator jsongenerator, Object obj, SerializationConfig serializationconfig)
        throws IOException, JsonGenerationException, JsonMappingException
    {
        Closeable closeable = (Closeable)obj;
        if(_rootType != null)
            break MISSING_BLOCK_LABEL_53;
        _serializerProvider(serializationconfig).serializeValue(jsongenerator, obj);
_L1:
        jsongenerator.close();
        closeable.close();
        Closeable closeable1;
        Exception exception1;
        Exception exception;
        if(false)
            try
            {
                null.close();
            }
            catch(IOException ioexception3) { }
        if(true)
            break MISSING_BLOCK_LABEL_52;
        null.close();
        return;
        _serializerProvider(serializationconfig).serializeValue(jsongenerator, obj, _rootType, _rootSerializer);
          goto _L1
        exception;
        closeable1 = closeable;
        exception1 = exception;
_L3:
        IOException ioexception2;
        if(jsongenerator != null)
            try
            {
                jsongenerator.close();
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
        exception1 = exception2;
        closeable1 = closeable;
        jsongenerator = null;
        continue; /* Loop/switch isn't completed */
        exception1;
        closeable1 = null;
        jsongenerator = null;
        if(true) goto _L3; else goto _L2
_L2:
    }

    private final void _writeCloseableValue(JsonGenerator jsongenerator, Object obj, SerializationConfig serializationconfig)
        throws IOException, JsonGenerationException, JsonMappingException
    {
        Closeable closeable = (Closeable)obj;
        if(_rootType != null)
            break MISSING_BLOCK_LABEL_58;
        _serializerProvider(serializationconfig).serializeValue(jsongenerator, obj);
_L1:
        if(_config.isEnabled(SerializationFeature.FLUSH_AFTER_WRITE_VALUE))
            jsongenerator.flush();
        closeable.close();
        if(true)
            break MISSING_BLOCK_LABEL_57;
        null.close();
        return;
        _serializerProvider(serializationconfig).serializeValue(jsongenerator, obj, _rootType, _rootSerializer);
          goto _L1
        Exception exception;
        exception;
        Closeable closeable1;
        Exception exception1;
        closeable1 = closeable;
        exception1 = exception;
_L3:
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
        if(true) goto _L3; else goto _L2
_L2:
    }

    protected final void _configAndWriteValue(JsonGenerator jsongenerator, Object obj)
        throws IOException, JsonGenerationException, JsonMappingException
    {
        boolean flag;
        _configureJsonGenerator(jsongenerator);
        if(_config.isEnabled(SerializationFeature.CLOSE_CLOSEABLE) && (obj instanceof Closeable))
        {
            _writeCloseable(jsongenerator, obj, _config);
            return;
        }
        flag = false;
        JavaType javatype = _rootType;
        flag = false;
        if(javatype != null)
            break MISSING_BLOCK_LABEL_84;
        _serializerProvider(_config).serializeValue(jsongenerator, obj);
_L1:
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
        _serializerProvider(_config).serializeValue(jsongenerator, obj, _rootType, _rootSerializer);
          goto _L1
    }

    protected JsonSerializer _prefetchRootSerializer(SerializationConfig serializationconfig, JavaType javatype)
    {
        if(javatype == null || !_config.isEnabled(SerializationFeature.EAGER_SERIALIZER_FETCH))
            return null;
        JsonSerializer jsonserializer;
        try
        {
            jsonserializer = _serializerProvider(serializationconfig).findTypedValueSerializer(javatype, true, null);
        }
        catch(JsonProcessingException jsonprocessingexception)
        {
            return null;
        }
        return jsonserializer;
    }

    protected DefaultSerializerProvider _serializerProvider(SerializationConfig serializationconfig)
    {
        return _serializerProvider.createInstance(serializationconfig, _serializerFactory);
    }

    protected void _verifySchemaType(FormatSchema formatschema)
    {
        if(formatschema != null && !_generatorFactory.canUseSchema(formatschema))
            throw new IllegalArgumentException((new StringBuilder()).append("Can not use FormatSchema of type ").append(formatschema.getClass().getName()).append(" for format ").append(_generatorFactory.getFormatName()).toString());
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
            _serializerProvider(_config).acceptJsonFormatVisitor(javatype, jsonformatvisitorwrapper);
            return;
        }
    }

    public boolean canSerialize(Class class1)
    {
        return _serializerProvider(_config).hasSerializerFor(class1, null);
    }

    public boolean canSerialize(Class class1, AtomicReference atomicreference)
    {
        return _serializerProvider(_config).hasSerializerFor(class1, atomicreference);
    }

    public ContextAttributes getAttributes()
    {
        return _config.getAttributes();
    }

    public SerializationConfig getConfig()
    {
        return _config;
    }

    public JsonFactory getFactory()
    {
        return _generatorFactory;
    }

    public JsonFactory getJsonFactory()
    {
        return _generatorFactory;
    }

    public TypeFactory getTypeFactory()
    {
        return _config.getTypeFactory();
    }

    public boolean hasPrefetchedSerializer()
    {
        return _rootSerializer != null;
    }

    public boolean isEnabled(com.fasterxml.jackson.core.JsonParser.Feature feature)
    {
        return _generatorFactory.isEnabled(feature);
    }

    public boolean isEnabled(MapperFeature mapperfeature)
    {
        return _config.isEnabled(mapperfeature);
    }

    public boolean isEnabled(SerializationFeature serializationfeature)
    {
        return _config.isEnabled(serializationfeature);
    }

    public Version version()
    {
        return PackageVersion.VERSION;
    }

    public ObjectWriter with(Base64Variant base64variant)
    {
        SerializationConfig serializationconfig = _config.with(base64variant);
        if(serializationconfig == _config)
            return this;
        else
            return new ObjectWriter(this, serializationconfig);
    }

    public ObjectWriter with(JsonFactory jsonfactory)
    {
        if(jsonfactory == _generatorFactory)
            return this;
        else
            return new ObjectWriter(this, jsonfactory);
    }

    public ObjectWriter with(PrettyPrinter prettyprinter)
    {
        if(prettyprinter == _prettyPrinter)
            return this;
        PrettyPrinter prettyprinter1;
        if(prettyprinter == null)
            prettyprinter1 = NULL_PRETTY_PRINTER;
        else
            prettyprinter1 = prettyprinter;
        return new ObjectWriter(this, _config, _rootType, _rootSerializer, prettyprinter1, _schema, _characterEscapes);
    }

    public ObjectWriter with(CharacterEscapes characterescapes)
    {
        if(_characterEscapes == characterescapes)
            return this;
        else
            return new ObjectWriter(this, _config, _rootType, _rootSerializer, _prettyPrinter, _schema, characterescapes);
    }

    public ObjectWriter with(SerializationFeature serializationfeature)
    {
        SerializationConfig serializationconfig = _config.with(serializationfeature);
        if(serializationconfig == _config)
            return this;
        else
            return new ObjectWriter(this, serializationconfig);
    }

    public transient ObjectWriter with(SerializationFeature serializationfeature, SerializationFeature aserializationfeature[])
    {
        SerializationConfig serializationconfig = _config.with(serializationfeature, aserializationfeature);
        if(serializationconfig == _config)
            return this;
        else
            return new ObjectWriter(this, serializationconfig);
    }

    public ObjectWriter with(ContextAttributes contextattributes)
    {
        SerializationConfig serializationconfig = _config.with(contextattributes);
        if(serializationconfig == _config)
            return this;
        else
            return new ObjectWriter(this, serializationconfig);
    }

    public ObjectWriter with(FilterProvider filterprovider)
    {
        if(filterprovider == _config.getFilterProvider())
            return this;
        else
            return new ObjectWriter(this, _config.withFilters(filterprovider));
    }

    public ObjectWriter with(DateFormat dateformat)
    {
        SerializationConfig serializationconfig = _config.with(dateformat);
        if(serializationconfig == _config)
            return this;
        else
            return new ObjectWriter(this, serializationconfig);
    }

    public ObjectWriter with(Locale locale)
    {
        SerializationConfig serializationconfig = _config.with(locale);
        if(serializationconfig == _config)
            return this;
        else
            return new ObjectWriter(this, serializationconfig);
    }

    public ObjectWriter with(TimeZone timezone)
    {
        SerializationConfig serializationconfig = _config.with(timezone);
        if(serializationconfig == _config)
            return this;
        else
            return new ObjectWriter(this, serializationconfig);
    }

    public ObjectWriter withAttribute(Object obj, Object obj1)
    {
        SerializationConfig serializationconfig = (SerializationConfig)_config.withAttribute(obj, obj1);
        if(serializationconfig == _config)
            return this;
        else
            return new ObjectWriter(this, serializationconfig);
    }

    public ObjectWriter withAttributes(Map map)
    {
        SerializationConfig serializationconfig = (SerializationConfig)_config.withAttributes(map);
        if(serializationconfig == _config)
            return this;
        else
            return new ObjectWriter(this, serializationconfig);
    }

    public ObjectWriter withDefaultPrettyPrinter()
    {
        return with(new DefaultPrettyPrinter());
    }

    public transient ObjectWriter withFeatures(SerializationFeature aserializationfeature[])
    {
        SerializationConfig serializationconfig = _config.withFeatures(aserializationfeature);
        if(serializationconfig == _config)
            return this;
        else
            return new ObjectWriter(this, serializationconfig);
    }

    public ObjectWriter withRootName(String s)
    {
        SerializationConfig serializationconfig = _config.withRootName(s);
        if(serializationconfig == _config)
            return this;
        else
            return new ObjectWriter(this, serializationconfig);
    }

    public ObjectWriter withSchema(FormatSchema formatschema)
    {
        if(_schema == formatschema)
        {
            return this;
        } else
        {
            _verifySchemaType(formatschema);
            return new ObjectWriter(this, _config, _rootType, _rootSerializer, _prettyPrinter, formatschema, _characterEscapes);
        }
    }

    public ObjectWriter withType(TypeReference typereference)
    {
        return withType(_config.getTypeFactory().constructType(typereference.getType()));
    }

    public ObjectWriter withType(JavaType javatype)
    {
        JavaType javatype1 = javatype.withStaticTyping();
        JsonSerializer jsonserializer = _prefetchRootSerializer(_config, javatype1);
        return new ObjectWriter(this, _config, javatype1, jsonserializer, _prettyPrinter, _schema, _characterEscapes);
    }

    public ObjectWriter withType(Class class1)
    {
        return withType(_config.constructType(class1));
    }

    public ObjectWriter withView(Class class1)
    {
        SerializationConfig serializationconfig = _config.withView(class1);
        if(serializationconfig == _config)
            return this;
        else
            return new ObjectWriter(this, serializationconfig);
    }

    public ObjectWriter without(SerializationFeature serializationfeature)
    {
        SerializationConfig serializationconfig = _config.without(serializationfeature);
        if(serializationconfig == _config)
            return this;
        else
            return new ObjectWriter(this, serializationconfig);
    }

    public transient ObjectWriter without(SerializationFeature serializationfeature, SerializationFeature aserializationfeature[])
    {
        SerializationConfig serializationconfig = _config.without(serializationfeature, aserializationfeature);
        if(serializationconfig == _config)
            return this;
        else
            return new ObjectWriter(this, serializationconfig);
    }

    public ObjectWriter withoutAttribute(Object obj)
    {
        SerializationConfig serializationconfig = (SerializationConfig)_config.withoutAttribute(obj);
        if(serializationconfig == _config)
            return this;
        else
            return new ObjectWriter(this, serializationconfig);
    }

    public transient ObjectWriter withoutFeatures(SerializationFeature aserializationfeature[])
    {
        SerializationConfig serializationconfig = _config.withoutFeatures(aserializationfeature);
        if(serializationconfig == _config)
            return this;
        else
            return new ObjectWriter(this, serializationconfig);
    }

    public void writeValue(JsonGenerator jsongenerator, Object obj)
        throws IOException, JsonGenerationException, JsonMappingException
    {
        _configureJsonGenerator(jsongenerator);
        if(_config.isEnabled(SerializationFeature.CLOSE_CLOSEABLE) && (obj instanceof Closeable))
        {
            _writeCloseableValue(jsongenerator, obj, _config);
        } else
        {
            if(_rootType == null)
                _serializerProvider(_config).serializeValue(jsongenerator, obj);
            else
                _serializerProvider(_config).serializeValue(jsongenerator, obj, _rootType, _rootSerializer);
            if(_config.isEnabled(SerializationFeature.FLUSH_AFTER_WRITE_VALUE))
            {
                jsongenerator.flush();
                return;
            }
        }
    }

    public void writeValue(File file, Object obj)
        throws IOException, JsonGenerationException, JsonMappingException
    {
        _configAndWriteValue(_generatorFactory.createGenerator(file, JsonEncoding.UTF8), obj);
    }

    public void writeValue(OutputStream outputstream, Object obj)
        throws IOException, JsonGenerationException, JsonMappingException
    {
        _configAndWriteValue(_generatorFactory.createGenerator(outputstream, JsonEncoding.UTF8), obj);
    }

    public void writeValue(Writer writer, Object obj)
        throws IOException, JsonGenerationException, JsonMappingException
    {
        _configAndWriteValue(_generatorFactory.createGenerator(writer), obj);
    }

    public byte[] writeValueAsBytes(Object obj)
        throws JsonProcessingException
    {
        ByteArrayBuilder bytearraybuilder = new ByteArrayBuilder(_generatorFactory._getBufferRecycler());
        byte abyte0[];
        try
        {
            _configAndWriteValue(_generatorFactory.createGenerator(bytearraybuilder, JsonEncoding.UTF8), obj);
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
        SegmentedStringWriter segmentedstringwriter = new SegmentedStringWriter(_generatorFactory._getBufferRecycler());
        try
        {
            _configAndWriteValue(_generatorFactory.createGenerator(segmentedstringwriter), obj);
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

    protected static final PrettyPrinter NULL_PRETTY_PRINTER = new MinimalPrettyPrinter();
    private static final long serialVersionUID = 0x9e4a868e1a7967a4L;
    protected final boolean _cfgBigDecimalAsPlain;
    protected final CharacterEscapes _characterEscapes;
    protected final SerializationConfig _config;
    protected final JsonFactory _generatorFactory;
    protected final PrettyPrinter _prettyPrinter;
    protected final JsonSerializer _rootSerializer;
    protected final JavaType _rootType;
    protected final FormatSchema _schema;
    protected final SerializerFactory _serializerFactory;
    protected final DefaultSerializerProvider _serializerProvider;

}
