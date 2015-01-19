// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.fasterxml.jackson.databind;

import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.core.io.SerializedString;
import com.fasterxml.jackson.core.type.ResolvedType;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.cfg.ContextAttributes;
import com.fasterxml.jackson.databind.cfg.PackageVersion;
import com.fasterxml.jackson.databind.deser.DataFormatReaders;
import com.fasterxml.jackson.databind.deser.DefaultDeserializationContext;
import com.fasterxml.jackson.databind.deser.DeserializationProblemHandler;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.NullNode;
import com.fasterxml.jackson.databind.node.TreeTraversingParser;
import com.fasterxml.jackson.databind.type.SimpleType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.databind.util.RootNameLookup;
import java.io.*;
import java.lang.reflect.Type;
import java.net.URL;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

// Referenced classes of package com.fasterxml.jackson.databind:
//            JsonNode, ObjectMapper, JavaType, DeserializationConfig, 
//            MapperFeature, JsonMappingException, JsonDeserializer, MappingIterator, 
//            DeserializationContext, DeserializationFeature, InjectableValues

public class ObjectReader extends ObjectCodec
    implements Versioned, Serializable
{

    protected ObjectReader(ObjectMapper objectmapper, DeserializationConfig deserializationconfig)
    {
        this(objectmapper, deserializationconfig, null, null, null, null);
    }

    protected ObjectReader(ObjectMapper objectmapper, DeserializationConfig deserializationconfig, JavaType javatype, Object obj, FormatSchema formatschema, InjectableValues injectablevalues)
    {
        _config = deserializationconfig;
        _context = objectmapper._deserializationContext;
        _rootDeserializers = objectmapper._rootDeserializers;
        _parserFactory = objectmapper._jsonFactory;
        _rootNames = objectmapper._rootNames;
        _valueType = javatype;
        _valueToUpdate = obj;
        if(obj != null && javatype.isArrayType())
        {
            throw new IllegalArgumentException("Can not update an array value");
        } else
        {
            _schema = formatschema;
            _injectableValues = injectablevalues;
            _unwrapRoot = deserializationconfig.useRootWrapping();
            _rootDeserializer = _prefetchRootDeserializer(deserializationconfig, javatype);
            _dataFormatReaders = null;
            return;
        }
    }

    protected ObjectReader(ObjectReader objectreader, JsonFactory jsonfactory)
    {
        _config = objectreader._config.with(MapperFeature.SORT_PROPERTIES_ALPHABETICALLY, jsonfactory.requiresPropertyOrdering());
        _context = objectreader._context;
        _rootDeserializers = objectreader._rootDeserializers;
        _parserFactory = jsonfactory;
        _rootNames = objectreader._rootNames;
        _valueType = objectreader._valueType;
        _rootDeserializer = objectreader._rootDeserializer;
        _valueToUpdate = objectreader._valueToUpdate;
        _schema = objectreader._schema;
        _injectableValues = objectreader._injectableValues;
        _unwrapRoot = objectreader._unwrapRoot;
        _dataFormatReaders = objectreader._dataFormatReaders;
    }

    protected ObjectReader(ObjectReader objectreader, DeserializationConfig deserializationconfig)
    {
        _config = deserializationconfig;
        _context = objectreader._context;
        _rootDeserializers = objectreader._rootDeserializers;
        _parserFactory = objectreader._parserFactory;
        _rootNames = objectreader._rootNames;
        _valueType = objectreader._valueType;
        _rootDeserializer = objectreader._rootDeserializer;
        _valueToUpdate = objectreader._valueToUpdate;
        _schema = objectreader._schema;
        _injectableValues = objectreader._injectableValues;
        _unwrapRoot = deserializationconfig.useRootWrapping();
        _dataFormatReaders = objectreader._dataFormatReaders;
    }

    protected ObjectReader(ObjectReader objectreader, DeserializationConfig deserializationconfig, JavaType javatype, JsonDeserializer jsondeserializer, Object obj, FormatSchema formatschema, InjectableValues injectablevalues, 
            DataFormatReaders dataformatreaders)
    {
        _config = deserializationconfig;
        _context = objectreader._context;
        _rootDeserializers = objectreader._rootDeserializers;
        _parserFactory = objectreader._parserFactory;
        _rootNames = objectreader._rootNames;
        _valueType = javatype;
        _rootDeserializer = jsondeserializer;
        _valueToUpdate = obj;
        if(obj != null && javatype.isArrayType())
        {
            throw new IllegalArgumentException("Can not update an array value");
        } else
        {
            _schema = formatschema;
            _injectableValues = injectablevalues;
            _unwrapRoot = deserializationconfig.useRootWrapping();
            _dataFormatReaders = dataformatreaders;
            return;
        }
    }

    protected static JsonToken _initForReading(JsonParser jsonparser)
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

    protected Object _bind(JsonParser jsonparser, Object obj)
        throws IOException, JsonParseException, JsonMappingException
    {
        JsonToken jsontoken = _initForReading(jsonparser);
        if(jsontoken != JsonToken.VALUE_NULL) goto _L2; else goto _L1
_L1:
        if(obj == null)
            obj = _findRootDeserializer(createDeserializationContext(jsonparser, _config), _valueType).getNullValue();
_L4:
        jsonparser.clearCurrentToken();
        return obj;
_L2:
        if(jsontoken != JsonToken.END_ARRAY && jsontoken != JsonToken.END_OBJECT)
        {
            DefaultDeserializationContext defaultdeserializationcontext = createDeserializationContext(jsonparser, _config);
            JsonDeserializer jsondeserializer = _findRootDeserializer(defaultdeserializationcontext, _valueType);
            if(_unwrapRoot)
                obj = _unwrapAndDeserialize(jsonparser, defaultdeserializationcontext, _valueType, jsondeserializer);
            else
            if(obj == null)
                obj = jsondeserializer.deserialize(jsonparser, defaultdeserializationcontext);
            else
                jsondeserializer.deserialize(jsonparser, defaultdeserializationcontext, obj);
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    protected Object _bindAndClose(JsonParser jsonparser, Object obj)
        throws IOException, JsonParseException, JsonMappingException
    {
        if(_schema != null)
            jsonparser.setSchema(_schema);
        JsonToken jsontoken = _initForReading(jsonparser);
        if(jsontoken != JsonToken.VALUE_NULL) goto _L2; else goto _L1
_L1:
        if(obj != null)
            break MISSING_BLOCK_LABEL_58;
        Object obj1 = _findRootDeserializer(createDeserializationContext(jsonparser, _config), _valueType).getNullValue();
        obj = obj1;
_L4:
        Exception exception;
        DefaultDeserializationContext defaultdeserializationcontext;
        JsonDeserializer jsondeserializer;
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
        if(jsontoken == JsonToken.END_ARRAY || jsontoken == JsonToken.END_OBJECT) goto _L4; else goto _L3
_L3:
        defaultdeserializationcontext = createDeserializationContext(jsonparser, _config);
        jsondeserializer = _findRootDeserializer(defaultdeserializationcontext, _valueType);
        if(!_unwrapRoot)
            break MISSING_BLOCK_LABEL_127;
        obj = _unwrapAndDeserialize(jsonparser, defaultdeserializationcontext, _valueType, jsondeserializer);
          goto _L4
        if(obj != null)
            break MISSING_BLOCK_LABEL_143;
        obj = jsondeserializer.deserialize(jsonparser, defaultdeserializationcontext);
          goto _L4
        jsondeserializer.deserialize(jsonparser, defaultdeserializationcontext, obj);
          goto _L4
        exception;
        try
        {
            jsonparser.close();
        }
        catch(IOException ioexception) { }
        throw exception;
    }

    protected JsonNode _bindAndCloseAsTree(JsonParser jsonparser)
        throws IOException, JsonParseException, JsonMappingException
    {
        if(_schema != null)
            jsonparser.setSchema(_schema);
        JsonNode jsonnode = _bindAsTree(jsonparser);
        Exception exception;
        try
        {
            jsonparser.close();
        }
        catch(IOException ioexception1)
        {
            return jsonnode;
        }
        return jsonnode;
        exception;
        try
        {
            jsonparser.close();
        }
        catch(IOException ioexception) { }
        throw exception;
    }

    protected MappingIterator _bindAndReadValues(JsonParser jsonparser, Object obj)
        throws IOException, JsonProcessingException
    {
        if(_schema != null)
            jsonparser.setSchema(_schema);
        jsonparser.nextToken();
        DefaultDeserializationContext defaultdeserializationcontext = createDeserializationContext(jsonparser, _config);
        return new MappingIterator(_valueType, jsonparser, defaultdeserializationcontext, _findRootDeserializer(defaultdeserializationcontext, _valueType), true, _valueToUpdate);
    }

    protected JsonNode _bindAsTree(JsonParser jsonparser)
        throws IOException, JsonParseException, JsonMappingException
    {
        JsonToken jsontoken = _initForReading(jsonparser);
        Object obj;
        if(jsontoken == JsonToken.VALUE_NULL || jsontoken == JsonToken.END_ARRAY || jsontoken == JsonToken.END_OBJECT)
        {
            obj = NullNode.instance;
        } else
        {
            DefaultDeserializationContext defaultdeserializationcontext = createDeserializationContext(jsonparser, _config);
            JsonDeserializer jsondeserializer = _findRootDeserializer(defaultdeserializationcontext, JSON_NODE_TYPE);
            if(_unwrapRoot)
                obj = (JsonNode)_unwrapAndDeserialize(jsonparser, defaultdeserializationcontext, JSON_NODE_TYPE, jsondeserializer);
            else
                obj = (JsonNode)jsondeserializer.deserialize(jsonparser, defaultdeserializationcontext);
        }
        jsonparser.clearCurrentToken();
        return ((JsonNode) (obj));
    }

    protected Object _detectBindAndClose(com.fasterxml.jackson.databind.deser.DataFormatReaders.Match match, boolean flag)
        throws IOException
    {
        if(!match.hasMatch())
            _reportUnkownFormat(_dataFormatReaders, match);
        JsonParser jsonparser = match.createParserWithMatch();
        if(flag)
            jsonparser.enable(com.fasterxml.jackson.core.JsonParser.Feature.AUTO_CLOSE_SOURCE);
        return match.getReader()._bindAndClose(jsonparser, _valueToUpdate);
    }

    protected Object _detectBindAndClose(byte abyte0[], int i, int j)
        throws IOException
    {
        com.fasterxml.jackson.databind.deser.DataFormatReaders.Match match = _dataFormatReaders.findFormat(abyte0, i, j);
        if(!match.hasMatch())
            _reportUnkownFormat(_dataFormatReaders, match);
        JsonParser jsonparser = match.createParserWithMatch();
        return match.getReader()._bindAndClose(jsonparser, _valueToUpdate);
    }

    protected JsonNode _detectBindAndCloseAsTree(InputStream inputstream)
        throws IOException
    {
        com.fasterxml.jackson.databind.deser.DataFormatReaders.Match match = _dataFormatReaders.findFormat(inputstream);
        if(!match.hasMatch())
            _reportUnkownFormat(_dataFormatReaders, match);
        JsonParser jsonparser = match.createParserWithMatch();
        jsonparser.enable(com.fasterxml.jackson.core.JsonParser.Feature.AUTO_CLOSE_SOURCE);
        return match.getReader()._bindAndCloseAsTree(jsonparser);
    }

    protected MappingIterator _detectBindAndReadValues(com.fasterxml.jackson.databind.deser.DataFormatReaders.Match match, boolean flag)
        throws IOException, JsonProcessingException
    {
        if(!match.hasMatch())
            _reportUnkownFormat(_dataFormatReaders, match);
        JsonParser jsonparser = match.createParserWithMatch();
        if(flag)
            jsonparser.enable(com.fasterxml.jackson.core.JsonParser.Feature.AUTO_CLOSE_SOURCE);
        return match.getReader()._bindAndReadValues(jsonparser, _valueToUpdate);
    }

    protected JsonDeserializer _findRootDeserializer(DeserializationContext deserializationcontext, JavaType javatype)
        throws JsonMappingException
    {
        JsonDeserializer jsondeserializer;
        if(_rootDeserializer != null)
        {
            jsondeserializer = _rootDeserializer;
        } else
        {
            if(javatype == null)
                throw new JsonMappingException("No value type configured for ObjectReader");
            jsondeserializer = (JsonDeserializer)_rootDeserializers.get(javatype);
            if(jsondeserializer == null)
            {
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
        }
        return jsondeserializer;
    }

    protected InputStream _inputStream(File file)
        throws IOException
    {
        return new FileInputStream(file);
    }

    protected InputStream _inputStream(URL url)
        throws IOException
    {
        return url.openStream();
    }

    protected JsonDeserializer _prefetchRootDeserializer(DeserializationConfig deserializationconfig, JavaType javatype)
    {
        JsonDeserializer jsondeserializer = null;
        if(javatype == null) goto _L2; else goto _L1
_L1:
        boolean flag;
        flag = _config.isEnabled(DeserializationFeature.EAGER_DESERIALIZER_FETCH);
        jsondeserializer = null;
        if(flag) goto _L3; else goto _L2
_L2:
        return jsondeserializer;
_L3:
        if((jsondeserializer = (JsonDeserializer)_rootDeserializers.get(javatype)) != null) goto _L2; else goto _L4
_L4:
        try
        {
            jsondeserializer = createDeserializationContext(null, _config).findRootValueDeserializer(javatype);
        }
        catch(JsonProcessingException jsonprocessingexception)
        {
            return jsondeserializer;
        }
        if(jsondeserializer == null) goto _L2; else goto _L5
_L5:
        _rootDeserializers.put(javatype, jsondeserializer);
        return jsondeserializer;
    }

    protected void _reportUndetectableSource(Object obj)
        throws JsonProcessingException
    {
        throw new JsonParseException((new StringBuilder()).append("Can not use source of type ").append(obj.getClass().getName()).append(" with format auto-detection: must be byte- not char-based").toString(), JsonLocation.NA);
    }

    protected void _reportUnkownFormat(DataFormatReaders dataformatreaders, com.fasterxml.jackson.databind.deser.DataFormatReaders.Match match)
        throws JsonProcessingException
    {
        throw new JsonParseException((new StringBuilder()).append("Can not detect format from input, does not look like any of detectable formats ").append(dataformatreaders.toString()).toString(), JsonLocation.NA);
    }

    protected Object _unwrapAndDeserialize(JsonParser jsonparser, DeserializationContext deserializationcontext, JavaType javatype, JsonDeserializer jsondeserializer)
        throws IOException, JsonParseException, JsonMappingException
    {
        String s = _config.getRootName();
        if(s == null)
            s = _rootNames.findRootName(javatype, _config).getValue();
        if(jsonparser.getCurrentToken() != JsonToken.START_OBJECT)
            throw JsonMappingException.from(jsonparser, (new StringBuilder()).append("Current token not START_OBJECT (needed to unwrap root name '").append(s).append("'), but ").append(jsonparser.getCurrentToken()).toString());
        if(jsonparser.nextToken() != JsonToken.FIELD_NAME)
            throw JsonMappingException.from(jsonparser, (new StringBuilder()).append("Current token not FIELD_NAME (to contain expected root name '").append(s).append("'), but ").append(jsonparser.getCurrentToken()).toString());
        String s1 = jsonparser.getCurrentName();
        if(!s.equals(s1))
            throw JsonMappingException.from(jsonparser, (new StringBuilder()).append("Root name '").append(s1).append("' does not match expected ('").append(s).append("') for type ").append(javatype).toString());
        jsonparser.nextToken();
        Object obj;
        if(_valueToUpdate == null)
        {
            obj = jsondeserializer.deserialize(jsonparser, deserializationcontext);
        } else
        {
            jsondeserializer.deserialize(jsonparser, deserializationcontext, _valueToUpdate);
            obj = _valueToUpdate;
        }
        if(jsonparser.nextToken() != JsonToken.END_OBJECT)
            throw JsonMappingException.from(jsonparser, (new StringBuilder()).append("Current token not END_OBJECT (to match wrapper object with root name '").append(s).append("'), but ").append(jsonparser.getCurrentToken()).toString());
        else
            return obj;
    }

    protected void _verifySchemaType(FormatSchema formatschema)
    {
        if(formatschema != null && !_parserFactory.canUseSchema(formatschema))
            throw new IllegalArgumentException((new StringBuilder()).append("Can not use FormatSchema of type ").append(formatschema.getClass().getName()).append(" for format ").append(_parserFactory.getFormatName()).toString());
        else
            return;
    }

    protected ObjectReader _with(DeserializationConfig deserializationconfig)
    {
        if(deserializationconfig == _config)
            return this;
        if(_dataFormatReaders != null)
            return (new ObjectReader(this, deserializationconfig)).withFormatDetection(_dataFormatReaders.with(deserializationconfig));
        else
            return new ObjectReader(this, deserializationconfig);
    }

    public volatile TreeNode createArrayNode()
    {
        return createArrayNode();
    }

    public JsonNode createArrayNode()
    {
        return _config.getNodeFactory().arrayNode();
    }

    protected DefaultDeserializationContext createDeserializationContext(JsonParser jsonparser, DeserializationConfig deserializationconfig)
    {
        return _context.createInstance(deserializationconfig, jsonparser, _injectableValues);
    }

    public volatile TreeNode createObjectNode()
    {
        return createObjectNode();
    }

    public JsonNode createObjectNode()
    {
        return _config.getNodeFactory().objectNode();
    }

    public ContextAttributes getAttributes()
    {
        return _config.getAttributes();
    }

    public DeserializationConfig getConfig()
    {
        return _config;
    }

    public JsonFactory getFactory()
    {
        return _parserFactory;
    }

    public JsonFactory getJsonFactory()
    {
        return _parserFactory;
    }

    public TypeFactory getTypeFactory()
    {
        return _config.getTypeFactory();
    }

    public boolean isEnabled(com.fasterxml.jackson.core.JsonParser.Feature feature)
    {
        return _parserFactory.isEnabled(feature);
    }

    public boolean isEnabled(DeserializationFeature deserializationfeature)
    {
        return _config.isEnabled(deserializationfeature);
    }

    public boolean isEnabled(MapperFeature mapperfeature)
    {
        return _config.isEnabled(mapperfeature);
    }

    public TreeNode readTree(JsonParser jsonparser)
        throws IOException, JsonProcessingException
    {
        return _bindAsTree(jsonparser);
    }

    public JsonNode readTree(InputStream inputstream)
        throws IOException, JsonProcessingException
    {
        if(_dataFormatReaders != null)
            return _detectBindAndCloseAsTree(inputstream);
        else
            return _bindAndCloseAsTree(_parserFactory.createParser(inputstream));
    }

    public JsonNode readTree(Reader reader)
        throws IOException, JsonProcessingException
    {
        if(_dataFormatReaders != null)
            _reportUndetectableSource(reader);
        return _bindAndCloseAsTree(_parserFactory.createParser(reader));
    }

    public JsonNode readTree(String s)
        throws IOException, JsonProcessingException
    {
        if(_dataFormatReaders != null)
            _reportUndetectableSource(s);
        return _bindAndCloseAsTree(_parserFactory.createParser(s));
    }

    public Object readValue(JsonParser jsonparser)
        throws IOException, JsonProcessingException
    {
        return _bind(jsonparser, _valueToUpdate);
    }

    public Object readValue(JsonParser jsonparser, ResolvedType resolvedtype)
        throws IOException, JsonProcessingException
    {
        return withType((JavaType)resolvedtype).readValue(jsonparser);
    }

    public Object readValue(JsonParser jsonparser, TypeReference typereference)
        throws IOException, JsonProcessingException
    {
        return withType(typereference).readValue(jsonparser);
    }

    public Object readValue(JsonParser jsonparser, JavaType javatype)
        throws IOException, JsonProcessingException
    {
        return withType(javatype).readValue(jsonparser);
    }

    public Object readValue(JsonParser jsonparser, Class class1)
        throws IOException, JsonProcessingException
    {
        return withType(class1).readValue(jsonparser);
    }

    public Object readValue(JsonNode jsonnode)
        throws IOException, JsonProcessingException
    {
        if(_dataFormatReaders != null)
            _reportUndetectableSource(jsonnode);
        return _bindAndClose(treeAsTokens(jsonnode), _valueToUpdate);
    }

    public Object readValue(File file)
        throws IOException, JsonProcessingException
    {
        if(_dataFormatReaders != null)
            return _detectBindAndClose(_dataFormatReaders.findFormat(_inputStream(file)), true);
        else
            return _bindAndClose(_parserFactory.createParser(file), _valueToUpdate);
    }

    public Object readValue(InputStream inputstream)
        throws IOException, JsonProcessingException
    {
        if(_dataFormatReaders != null)
            return _detectBindAndClose(_dataFormatReaders.findFormat(inputstream), false);
        else
            return _bindAndClose(_parserFactory.createParser(inputstream), _valueToUpdate);
    }

    public Object readValue(Reader reader)
        throws IOException, JsonProcessingException
    {
        if(_dataFormatReaders != null)
            _reportUndetectableSource(reader);
        return _bindAndClose(_parserFactory.createParser(reader), _valueToUpdate);
    }

    public Object readValue(String s)
        throws IOException, JsonProcessingException
    {
        if(_dataFormatReaders != null)
            _reportUndetectableSource(s);
        return _bindAndClose(_parserFactory.createParser(s), _valueToUpdate);
    }

    public Object readValue(URL url)
        throws IOException, JsonProcessingException
    {
        if(_dataFormatReaders != null)
            return _detectBindAndClose(_dataFormatReaders.findFormat(_inputStream(url)), true);
        else
            return _bindAndClose(_parserFactory.createParser(url), _valueToUpdate);
    }

    public Object readValue(byte abyte0[])
        throws IOException, JsonProcessingException
    {
        if(_dataFormatReaders != null)
            return _detectBindAndClose(abyte0, 0, abyte0.length);
        else
            return _bindAndClose(_parserFactory.createParser(abyte0), _valueToUpdate);
    }

    public Object readValue(byte abyte0[], int i, int j)
        throws IOException, JsonProcessingException
    {
        if(_dataFormatReaders != null)
            return _detectBindAndClose(abyte0, i, j);
        else
            return _bindAndClose(_parserFactory.createParser(abyte0, i, j), _valueToUpdate);
    }

    public MappingIterator readValues(JsonParser jsonparser)
        throws IOException, JsonProcessingException
    {
        DefaultDeserializationContext defaultdeserializationcontext = createDeserializationContext(jsonparser, _config);
        return new MappingIterator(_valueType, jsonparser, defaultdeserializationcontext, _findRootDeserializer(defaultdeserializationcontext, _valueType), false, _valueToUpdate);
    }

    public MappingIterator readValues(File file)
        throws IOException, JsonProcessingException
    {
        if(_dataFormatReaders != null)
            return _detectBindAndReadValues(_dataFormatReaders.findFormat(_inputStream(file)), false);
        else
            return _bindAndReadValues(_parserFactory.createParser(file), _valueToUpdate);
    }

    public MappingIterator readValues(InputStream inputstream)
        throws IOException, JsonProcessingException
    {
        if(_dataFormatReaders != null)
            return _detectBindAndReadValues(_dataFormatReaders.findFormat(inputstream), false);
        else
            return _bindAndReadValues(_parserFactory.createParser(inputstream), _valueToUpdate);
    }

    public MappingIterator readValues(Reader reader)
        throws IOException, JsonProcessingException
    {
        if(_dataFormatReaders != null)
            _reportUndetectableSource(reader);
        JsonParser jsonparser = _parserFactory.createParser(reader);
        if(_schema != null)
            jsonparser.setSchema(_schema);
        jsonparser.nextToken();
        DefaultDeserializationContext defaultdeserializationcontext = createDeserializationContext(jsonparser, _config);
        return new MappingIterator(_valueType, jsonparser, defaultdeserializationcontext, _findRootDeserializer(defaultdeserializationcontext, _valueType), true, _valueToUpdate);
    }

    public MappingIterator readValues(String s)
        throws IOException, JsonProcessingException
    {
        if(_dataFormatReaders != null)
            _reportUndetectableSource(s);
        JsonParser jsonparser = _parserFactory.createParser(s);
        if(_schema != null)
            jsonparser.setSchema(_schema);
        jsonparser.nextToken();
        DefaultDeserializationContext defaultdeserializationcontext = createDeserializationContext(jsonparser, _config);
        return new MappingIterator(_valueType, jsonparser, defaultdeserializationcontext, _findRootDeserializer(defaultdeserializationcontext, _valueType), true, _valueToUpdate);
    }

    public MappingIterator readValues(URL url)
        throws IOException, JsonProcessingException
    {
        if(_dataFormatReaders != null)
            return _detectBindAndReadValues(_dataFormatReaders.findFormat(_inputStream(url)), true);
        else
            return _bindAndReadValues(_parserFactory.createParser(url), _valueToUpdate);
    }

    public final MappingIterator readValues(byte abyte0[])
        throws IOException, JsonProcessingException
    {
        return readValues(abyte0, 0, abyte0.length);
    }

    public MappingIterator readValues(byte abyte0[], int i, int j)
        throws IOException, JsonProcessingException
    {
        if(_dataFormatReaders != null)
            return _detectBindAndReadValues(_dataFormatReaders.findFormat(abyte0, i, j), false);
        else
            return _bindAndReadValues(_parserFactory.createParser(abyte0), _valueToUpdate);
    }

    public Iterator readValues(JsonParser jsonparser, ResolvedType resolvedtype)
        throws IOException, JsonProcessingException
    {
        return readValues(jsonparser, (JavaType)resolvedtype);
    }

    public Iterator readValues(JsonParser jsonparser, TypeReference typereference)
        throws IOException, JsonProcessingException
    {
        return withType(typereference).readValues(jsonparser);
    }

    public Iterator readValues(JsonParser jsonparser, JavaType javatype)
        throws IOException, JsonProcessingException
    {
        return withType(javatype).readValues(jsonparser);
    }

    public Iterator readValues(JsonParser jsonparser, Class class1)
        throws IOException, JsonProcessingException
    {
        return withType(class1).readValues(jsonparser);
    }

    public JsonParser treeAsTokens(TreeNode treenode)
    {
        return new TreeTraversingParser((JsonNode)treenode, this);
    }

    public Object treeToValue(TreeNode treenode, Class class1)
        throws JsonProcessingException
    {
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

    public Version version()
    {
        return PackageVersion.VERSION;
    }

    public ObjectReader with(Base64Variant base64variant)
    {
        return _with(_config.with(base64variant));
    }

    public ObjectReader with(FormatSchema formatschema)
    {
        if(_schema == formatschema)
        {
            return this;
        } else
        {
            _verifySchemaType(formatschema);
            return new ObjectReader(this, _config, _valueType, _rootDeserializer, _valueToUpdate, formatschema, _injectableValues, _dataFormatReaders);
        }
    }

    public ObjectReader with(JsonFactory jsonfactory)
    {
        if(jsonfactory == _parserFactory)
            return this;
        ObjectReader objectreader = new ObjectReader(this, jsonfactory);
        if(jsonfactory.getCodec() == null)
            jsonfactory.setCodec(objectreader);
        return objectreader;
    }

    public ObjectReader with(DeserializationConfig deserializationconfig)
    {
        return _with(deserializationconfig);
    }

    public ObjectReader with(DeserializationFeature deserializationfeature)
    {
        return _with(_config.with(deserializationfeature));
    }

    public transient ObjectReader with(DeserializationFeature deserializationfeature, DeserializationFeature adeserializationfeature[])
    {
        return _with(_config.with(deserializationfeature, adeserializationfeature));
    }

    public ObjectReader with(InjectableValues injectablevalues)
    {
        if(_injectableValues == injectablevalues)
            return this;
        else
            return new ObjectReader(this, _config, _valueType, _rootDeserializer, _valueToUpdate, _schema, injectablevalues, _dataFormatReaders);
    }

    public ObjectReader with(ContextAttributes contextattributes)
    {
        DeserializationConfig deserializationconfig = _config.with(contextattributes);
        if(deserializationconfig == _config)
            return this;
        else
            return new ObjectReader(this, deserializationconfig);
    }

    public ObjectReader with(JsonNodeFactory jsonnodefactory)
    {
        return _with(_config.with(jsonnodefactory));
    }

    public ObjectReader with(Locale locale)
    {
        return _with(_config.with(locale));
    }

    public ObjectReader with(TimeZone timezone)
    {
        return _with(_config.with(timezone));
    }

    public ObjectReader withAttribute(Object obj, Object obj1)
    {
        DeserializationConfig deserializationconfig = (DeserializationConfig)_config.withAttribute(obj, obj1);
        if(deserializationconfig == _config)
            return this;
        else
            return new ObjectReader(this, deserializationconfig);
    }

    public ObjectReader withAttributes(Map map)
    {
        DeserializationConfig deserializationconfig = (DeserializationConfig)_config.withAttributes(map);
        if(deserializationconfig == _config)
            return this;
        else
            return new ObjectReader(this, deserializationconfig);
    }

    public transient ObjectReader withFeatures(DeserializationFeature adeserializationfeature[])
    {
        return _with(_config.withFeatures(adeserializationfeature));
    }

    public ObjectReader withFormatDetection(DataFormatReaders dataformatreaders)
    {
        return new ObjectReader(this, _config, _valueType, _rootDeserializer, _valueToUpdate, _schema, _injectableValues, dataformatreaders);
    }

    public transient ObjectReader withFormatDetection(ObjectReader aobjectreader[])
    {
        return withFormatDetection(new DataFormatReaders(aobjectreader));
    }

    public ObjectReader withHandler(DeserializationProblemHandler deserializationproblemhandler)
    {
        return _with(_config.withHandler(deserializationproblemhandler));
    }

    public ObjectReader withRootName(String s)
    {
        return _with(_config.withRootName(s));
    }

    public ObjectReader withType(TypeReference typereference)
    {
        return withType(_config.getTypeFactory().constructType(typereference.getType()));
    }

    public ObjectReader withType(JavaType javatype)
    {
        if(javatype != null && javatype.equals(_valueType))
            return this;
        JsonDeserializer jsondeserializer = _prefetchRootDeserializer(_config, javatype);
        DataFormatReaders dataformatreaders = _dataFormatReaders;
        if(dataformatreaders != null)
            dataformatreaders = dataformatreaders.withType(javatype);
        return new ObjectReader(this, _config, javatype, jsondeserializer, _valueToUpdate, _schema, _injectableValues, dataformatreaders);
    }

    public ObjectReader withType(Class class1)
    {
        return withType(_config.constructType(class1));
    }

    public ObjectReader withType(Type type)
    {
        return withType(_config.getTypeFactory().constructType(type));
    }

    public ObjectReader withValueToUpdate(Object obj)
    {
        if(obj == _valueToUpdate)
            return this;
        if(obj == null)
            throw new IllegalArgumentException("cat not update null value");
        JavaType javatype;
        if(_valueType == null)
            javatype = _config.constructType(obj.getClass());
        else
            javatype = _valueType;
        return new ObjectReader(this, _config, javatype, _rootDeserializer, obj, _schema, _injectableValues, _dataFormatReaders);
    }

    public ObjectReader withView(Class class1)
    {
        return _with(_config.withView(class1));
    }

    public ObjectReader without(DeserializationFeature deserializationfeature)
    {
        return _with(_config.without(deserializationfeature));
    }

    public transient ObjectReader without(DeserializationFeature deserializationfeature, DeserializationFeature adeserializationfeature[])
    {
        return _with(_config.without(deserializationfeature, adeserializationfeature));
    }

    public ObjectReader withoutAttribute(Object obj)
    {
        DeserializationConfig deserializationconfig = (DeserializationConfig)_config.withoutAttribute(obj);
        if(deserializationconfig == _config)
            return this;
        else
            return new ObjectReader(this, deserializationconfig);
    }

    public transient ObjectReader withoutFeatures(DeserializationFeature adeserializationfeature[])
    {
        return _with(_config.withoutFeatures(adeserializationfeature));
    }

    public void writeTree(JsonGenerator jsongenerator, TreeNode treenode)
    {
        throw new UnsupportedOperationException();
    }

    public void writeValue(JsonGenerator jsongenerator, Object obj)
        throws IOException, JsonProcessingException
    {
        throw new UnsupportedOperationException("Not implemented for ObjectReader");
    }

    private static final JavaType JSON_NODE_TYPE = SimpleType.constructUnsafe(com/fasterxml/jackson/databind/JsonNode);
    private static final long serialVersionUID = 0xc4ffd6d3565588ffL;
    protected final DeserializationConfig _config;
    protected final DefaultDeserializationContext _context;
    protected final DataFormatReaders _dataFormatReaders;
    protected final InjectableValues _injectableValues;
    protected final JsonFactory _parserFactory;
    protected final JsonDeserializer _rootDeserializer;
    protected final ConcurrentHashMap _rootDeserializers;
    protected final RootNameLookup _rootNames;
    protected final FormatSchema _schema;
    protected final boolean _unwrapRoot;
    protected final Object _valueToUpdate;
    protected final JavaType _valueType;

}
