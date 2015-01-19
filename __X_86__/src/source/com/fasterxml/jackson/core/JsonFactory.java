// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.fasterxml.jackson.core;

import com.fasterxml.jackson.core.format.InputAccessor;
import com.fasterxml.jackson.core.format.MatchStrength;
import com.fasterxml.jackson.core.io.CharacterEscapes;
import com.fasterxml.jackson.core.io.IOContext;
import com.fasterxml.jackson.core.io.InputDecorator;
import com.fasterxml.jackson.core.io.OutputDecorator;
import com.fasterxml.jackson.core.io.SerializedString;
import com.fasterxml.jackson.core.io.UTF8Writer;
import com.fasterxml.jackson.core.json.ByteSourceJsonBootstrapper;
import com.fasterxml.jackson.core.json.PackageVersion;
import com.fasterxml.jackson.core.json.ReaderBasedJsonParser;
import com.fasterxml.jackson.core.json.UTF8JsonGenerator;
import com.fasterxml.jackson.core.json.WriterBasedJsonGenerator;
import com.fasterxml.jackson.core.sym.BytesToNameCanonicalizer;
import com.fasterxml.jackson.core.sym.CharsToNameCanonicalizer;
import com.fasterxml.jackson.core.util.BufferRecycler;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import java.io.*;
import java.lang.ref.SoftReference;
import java.net.URL;

// Referenced classes of package com.fasterxml.jackson.core:
//            Versioned, ObjectCodec, JsonParseException, JsonEncoding, 
//            FormatSchema, SerializableString, JsonGenerator, JsonParser, 
//            Version

public class JsonFactory
    implements Versioned, Serializable
{
    public static final class Feature extends Enum
    {

        public static int collectDefaults()
        {
            int i = 0;
            Feature afeature[] = values();
            int j = afeature.length;
            for(int k = 0; k < j; k++)
            {
                Feature feature = afeature[k];
                if(feature.enabledByDefault())
                    i |= feature.getMask();
            }

            return i;
        }

        public static Feature valueOf(String s)
        {
            return (Feature)Enum.valueOf(com/fasterxml/jackson/core/JsonFactory$Feature, s);
        }

        public static Feature[] values()
        {
            return (Feature[])$VALUES.clone();
        }

        public boolean enabledByDefault()
        {
            return _defaultState;
        }

        public boolean enabledIn(int i)
        {
            return (i & getMask()) != 0;
        }

        public int getMask()
        {
            return 1 << ordinal();
        }

        private static final Feature $VALUES[];
        public static final Feature CANONICALIZE_FIELD_NAMES;
        public static final Feature INTERN_FIELD_NAMES;
        private final boolean _defaultState;

        static 
        {
            INTERN_FIELD_NAMES = new Feature("INTERN_FIELD_NAMES", 0, true);
            CANONICALIZE_FIELD_NAMES = new Feature("CANONICALIZE_FIELD_NAMES", 1, true);
            Feature afeature[] = new Feature[2];
            afeature[0] = INTERN_FIELD_NAMES;
            afeature[1] = CANONICALIZE_FIELD_NAMES;
            $VALUES = afeature;
        }

        private Feature(String s, int i, boolean flag)
        {
            super(s, i);
            _defaultState = flag;
        }
    }


    public JsonFactory()
    {
        this((ObjectCodec)null);
    }

    protected JsonFactory(JsonFactory jsonfactory, ObjectCodec objectcodec)
    {
        _rootCharSymbols = CharsToNameCanonicalizer.createRoot();
        _rootByteSymbols = BytesToNameCanonicalizer.createRoot();
        _factoryFeatures = DEFAULT_FACTORY_FEATURE_FLAGS;
        _parserFeatures = DEFAULT_PARSER_FEATURE_FLAGS;
        _generatorFeatures = DEFAULT_GENERATOR_FEATURE_FLAGS;
        _rootValueSeparator = DEFAULT_ROOT_VALUE_SEPARATOR;
        _objectCodec = null;
        _factoryFeatures = jsonfactory._factoryFeatures;
        _parserFeatures = jsonfactory._parserFeatures;
        _generatorFeatures = jsonfactory._generatorFeatures;
        _characterEscapes = jsonfactory._characterEscapes;
        _inputDecorator = jsonfactory._inputDecorator;
        _outputDecorator = jsonfactory._outputDecorator;
        _rootValueSeparator = jsonfactory._rootValueSeparator;
    }

    public JsonFactory(ObjectCodec objectcodec)
    {
        _rootCharSymbols = CharsToNameCanonicalizer.createRoot();
        _rootByteSymbols = BytesToNameCanonicalizer.createRoot();
        _factoryFeatures = DEFAULT_FACTORY_FEATURE_FLAGS;
        _parserFeatures = DEFAULT_PARSER_FEATURE_FLAGS;
        _generatorFeatures = DEFAULT_GENERATOR_FEATURE_FLAGS;
        _rootValueSeparator = DEFAULT_ROOT_VALUE_SEPARATOR;
        _objectCodec = objectcodec;
    }

    protected void _checkInvalidCopy(Class class1)
    {
        if(getClass() != class1)
            throw new IllegalStateException((new StringBuilder()).append("Failed copy(): ").append(getClass().getName()).append(" (version: ").append(version()).append(") does not override copy(); it has to").toString());
        else
            return;
    }

    protected IOContext _createContext(Object obj, boolean flag)
    {
        return new IOContext(_getBufferRecycler(), obj, flag);
    }

    protected JsonGenerator _createGenerator(Writer writer, IOContext iocontext)
        throws IOException
    {
        WriterBasedJsonGenerator writerbasedjsongenerator = new WriterBasedJsonGenerator(iocontext, _generatorFeatures, _objectCodec, writer);
        if(_characterEscapes != null)
            writerbasedjsongenerator.setCharacterEscapes(_characterEscapes);
        SerializableString serializablestring = _rootValueSeparator;
        if(serializablestring != DEFAULT_ROOT_VALUE_SEPARATOR)
            writerbasedjsongenerator.setRootValueSeparator(serializablestring);
        return writerbasedjsongenerator;
    }

    protected JsonGenerator _createJsonGenerator(Writer writer, IOContext iocontext)
        throws IOException
    {
        return _createGenerator(writer, iocontext);
    }

    protected JsonParser _createJsonParser(InputStream inputstream, IOContext iocontext)
        throws IOException, JsonParseException
    {
        return _createParser(inputstream, iocontext);
    }

    protected JsonParser _createJsonParser(Reader reader, IOContext iocontext)
        throws IOException, JsonParseException
    {
        return _createParser(reader, iocontext);
    }

    protected JsonParser _createJsonParser(byte abyte0[], int i, int j, IOContext iocontext)
        throws IOException, JsonParseException
    {
        return _createParser(abyte0, i, j, iocontext);
    }

    protected JsonParser _createParser(InputStream inputstream, IOContext iocontext)
        throws IOException, JsonParseException
    {
        return (new ByteSourceJsonBootstrapper(iocontext, inputstream)).constructParser(_parserFeatures, _objectCodec, _rootByteSymbols, _rootCharSymbols, isEnabled(Feature.CANONICALIZE_FIELD_NAMES), isEnabled(Feature.INTERN_FIELD_NAMES));
    }

    protected JsonParser _createParser(Reader reader, IOContext iocontext)
        throws IOException, JsonParseException
    {
        return new ReaderBasedJsonParser(iocontext, _parserFeatures, reader, _objectCodec, _rootCharSymbols.makeChild(isEnabled(Feature.CANONICALIZE_FIELD_NAMES), isEnabled(Feature.INTERN_FIELD_NAMES)));
    }

    protected JsonParser _createParser(byte abyte0[], int i, int j, IOContext iocontext)
        throws IOException, JsonParseException
    {
        return (new ByteSourceJsonBootstrapper(iocontext, abyte0, i, j)).constructParser(_parserFeatures, _objectCodec, _rootByteSymbols, _rootCharSymbols, isEnabled(Feature.CANONICALIZE_FIELD_NAMES), isEnabled(Feature.INTERN_FIELD_NAMES));
    }

    protected JsonGenerator _createUTF8Generator(OutputStream outputstream, IOContext iocontext)
        throws IOException
    {
        UTF8JsonGenerator utf8jsongenerator = new UTF8JsonGenerator(iocontext, _generatorFeatures, _objectCodec, outputstream);
        if(_characterEscapes != null)
            utf8jsongenerator.setCharacterEscapes(_characterEscapes);
        SerializableString serializablestring = _rootValueSeparator;
        if(serializablestring != DEFAULT_ROOT_VALUE_SEPARATOR)
            utf8jsongenerator.setRootValueSeparator(serializablestring);
        return utf8jsongenerator;
    }

    protected JsonGenerator _createUTF8JsonGenerator(OutputStream outputstream, IOContext iocontext)
        throws IOException
    {
        return _createUTF8Generator(outputstream, iocontext);
    }

    protected Writer _createWriter(OutputStream outputstream, JsonEncoding jsonencoding, IOContext iocontext)
        throws IOException
    {
        if(jsonencoding == JsonEncoding.UTF8)
            return new UTF8Writer(iocontext, outputstream);
        else
            return new OutputStreamWriter(outputstream, jsonencoding.getJavaName());
    }

    public BufferRecycler _getBufferRecycler()
    {
        SoftReference softreference = (SoftReference)_recyclerRef.get();
        BufferRecycler bufferrecycler;
        if(softreference == null)
            bufferrecycler = null;
        else
            bufferrecycler = (BufferRecycler)softreference.get();
        if(bufferrecycler == null)
        {
            bufferrecycler = new BufferRecycler();
            _recyclerRef.set(new SoftReference(bufferrecycler));
        }
        return bufferrecycler;
    }

    protected InputStream _optimizedStreamFromURL(URL url)
        throws IOException
    {
        if("file".equals(url.getProtocol()))
        {
            String s = url.getHost();
            if((s == null || s.length() == 0) && url.getPath().indexOf('%') < 0)
                return new FileInputStream(url.getPath());
        }
        return url.openStream();
    }

    public boolean canHandleBinaryNatively()
    {
        return false;
    }

    public boolean canUseSchema(FormatSchema formatschema)
    {
        String s = getFormatName();
        return s != null && s.equals(formatschema.getSchemaType());
    }

    public final JsonFactory configure(Feature feature, boolean flag)
    {
        if(flag)
            return enable(feature);
        else
            return disable(feature);
    }

    public final JsonFactory configure(JsonGenerator.Feature feature, boolean flag)
    {
        if(flag)
            return enable(feature);
        else
            return disable(feature);
    }

    public final JsonFactory configure(JsonParser.Feature feature, boolean flag)
    {
        if(flag)
            return enable(feature);
        else
            return disable(feature);
    }

    public JsonFactory copy()
    {
        _checkInvalidCopy(com/fasterxml/jackson/core/JsonFactory);
        return new JsonFactory(this, null);
    }

    public JsonGenerator createGenerator(File file, JsonEncoding jsonencoding)
        throws IOException
    {
        Object obj = new FileOutputStream(file);
        IOContext iocontext = _createContext(obj, true);
        iocontext.setEncoding(jsonencoding);
        if(jsonencoding == JsonEncoding.UTF8)
        {
            if(_outputDecorator != null)
                obj = _outputDecorator.decorate(iocontext, ((OutputStream) (obj)));
            return _createUTF8Generator(((OutputStream) (obj)), iocontext);
        }
        Writer writer = _createWriter(((OutputStream) (obj)), jsonencoding, iocontext);
        if(_outputDecorator != null)
            writer = _outputDecorator.decorate(iocontext, writer);
        return _createGenerator(writer, iocontext);
    }

    public JsonGenerator createGenerator(OutputStream outputstream)
        throws IOException
    {
        return createGenerator(outputstream, JsonEncoding.UTF8);
    }

    public JsonGenerator createGenerator(OutputStream outputstream, JsonEncoding jsonencoding)
        throws IOException
    {
        IOContext iocontext = _createContext(outputstream, false);
        iocontext.setEncoding(jsonencoding);
        if(jsonencoding == JsonEncoding.UTF8)
        {
            if(_outputDecorator != null)
                outputstream = _outputDecorator.decorate(iocontext, outputstream);
            return _createUTF8Generator(outputstream, iocontext);
        }
        Writer writer = _createWriter(outputstream, jsonencoding, iocontext);
        if(_outputDecorator != null)
            writer = _outputDecorator.decorate(iocontext, writer);
        return _createGenerator(writer, iocontext);
    }

    public JsonGenerator createGenerator(Writer writer)
        throws IOException
    {
        IOContext iocontext = _createContext(writer, false);
        if(_outputDecorator != null)
            writer = _outputDecorator.decorate(iocontext, writer);
        return _createGenerator(writer, iocontext);
    }

    public JsonGenerator createJsonGenerator(File file, JsonEncoding jsonencoding)
        throws IOException
    {
        return createGenerator(file, jsonencoding);
    }

    public JsonGenerator createJsonGenerator(OutputStream outputstream)
        throws IOException
    {
        return createGenerator(outputstream, JsonEncoding.UTF8);
    }

    public JsonGenerator createJsonGenerator(OutputStream outputstream, JsonEncoding jsonencoding)
        throws IOException
    {
        return createGenerator(outputstream, jsonencoding);
    }

    public JsonGenerator createJsonGenerator(Writer writer)
        throws IOException
    {
        return createGenerator(writer);
    }

    public JsonParser createJsonParser(File file)
        throws IOException, JsonParseException
    {
        return createParser(file);
    }

    public JsonParser createJsonParser(InputStream inputstream)
        throws IOException, JsonParseException
    {
        return createParser(inputstream);
    }

    public JsonParser createJsonParser(Reader reader)
        throws IOException, JsonParseException
    {
        return createParser(reader);
    }

    public JsonParser createJsonParser(String s)
        throws IOException, JsonParseException
    {
        return createParser(s);
    }

    public JsonParser createJsonParser(URL url)
        throws IOException, JsonParseException
    {
        return createParser(url);
    }

    public JsonParser createJsonParser(byte abyte0[])
        throws IOException, JsonParseException
    {
        return createParser(abyte0);
    }

    public JsonParser createJsonParser(byte abyte0[], int i, int j)
        throws IOException, JsonParseException
    {
        return createParser(abyte0, i, j);
    }

    public JsonParser createParser(File file)
        throws IOException, JsonParseException
    {
        IOContext iocontext = _createContext(file, true);
        Object obj = new FileInputStream(file);
        if(_inputDecorator != null)
            obj = _inputDecorator.decorate(iocontext, ((InputStream) (obj)));
        return _createParser(((InputStream) (obj)), iocontext);
    }

    public JsonParser createParser(InputStream inputstream)
        throws IOException, JsonParseException
    {
        IOContext iocontext = _createContext(inputstream, false);
        if(_inputDecorator != null)
            inputstream = _inputDecorator.decorate(iocontext, inputstream);
        return _createParser(inputstream, iocontext);
    }

    public JsonParser createParser(Reader reader)
        throws IOException, JsonParseException
    {
        IOContext iocontext = _createContext(reader, false);
        if(_inputDecorator != null)
            reader = _inputDecorator.decorate(iocontext, reader);
        return _createParser(reader, iocontext);
    }

    public JsonParser createParser(String s)
        throws IOException, JsonParseException
    {
        Object obj = new StringReader(s);
        IOContext iocontext = _createContext(obj, true);
        if(_inputDecorator != null)
            obj = _inputDecorator.decorate(iocontext, ((Reader) (obj)));
        return _createParser(((Reader) (obj)), iocontext);
    }

    public JsonParser createParser(URL url)
        throws IOException, JsonParseException
    {
        IOContext iocontext = _createContext(url, true);
        InputStream inputstream = _optimizedStreamFromURL(url);
        if(_inputDecorator != null)
            inputstream = _inputDecorator.decorate(iocontext, inputstream);
        return _createParser(inputstream, iocontext);
    }

    public JsonParser createParser(byte abyte0[])
        throws IOException, JsonParseException
    {
        IOContext iocontext = _createContext(abyte0, true);
        if(_inputDecorator != null)
        {
            InputStream inputstream = _inputDecorator.decorate(iocontext, abyte0, 0, abyte0.length);
            if(inputstream != null)
                return _createParser(inputstream, iocontext);
        }
        return _createParser(abyte0, 0, abyte0.length, iocontext);
    }

    public JsonParser createParser(byte abyte0[], int i, int j)
        throws IOException, JsonParseException
    {
        IOContext iocontext = _createContext(abyte0, true);
        if(_inputDecorator != null)
        {
            InputStream inputstream = _inputDecorator.decorate(iocontext, abyte0, i, j);
            if(inputstream != null)
                return _createParser(inputstream, iocontext);
        }
        return _createParser(abyte0, i, j, iocontext);
    }

    public JsonFactory disable(Feature feature)
    {
        _factoryFeatures = _factoryFeatures & (-1 ^ feature.getMask());
        return this;
    }

    public JsonFactory disable(JsonGenerator.Feature feature)
    {
        _generatorFeatures = _generatorFeatures & (-1 ^ feature.getMask());
        return this;
    }

    public JsonFactory disable(JsonParser.Feature feature)
    {
        _parserFeatures = _parserFeatures & (-1 ^ feature.getMask());
        return this;
    }

    public JsonFactory enable(Feature feature)
    {
        _factoryFeatures = _factoryFeatures | feature.getMask();
        return this;
    }

    public JsonFactory enable(JsonGenerator.Feature feature)
    {
        _generatorFeatures = _generatorFeatures | feature.getMask();
        return this;
    }

    public JsonFactory enable(JsonParser.Feature feature)
    {
        _parserFeatures = _parserFeatures | feature.getMask();
        return this;
    }

    public CharacterEscapes getCharacterEscapes()
    {
        return _characterEscapes;
    }

    public ObjectCodec getCodec()
    {
        return _objectCodec;
    }

    public String getFormatName()
    {
        if(getClass() == com/fasterxml/jackson/core/JsonFactory)
            return "JSON";
        else
            return null;
    }

    public InputDecorator getInputDecorator()
    {
        return _inputDecorator;
    }

    public OutputDecorator getOutputDecorator()
    {
        return _outputDecorator;
    }

    public String getRootValueSeparator()
    {
        if(_rootValueSeparator == null)
            return null;
        else
            return _rootValueSeparator.getValue();
    }

    public MatchStrength hasFormat(InputAccessor inputaccessor)
        throws IOException
    {
        if(getClass() == com/fasterxml/jackson/core/JsonFactory)
            return hasJSONFormat(inputaccessor);
        else
            return null;
    }

    protected MatchStrength hasJSONFormat(InputAccessor inputaccessor)
        throws IOException
    {
        return ByteSourceJsonBootstrapper.hasJSONFormat(inputaccessor);
    }

    public final boolean isEnabled(Feature feature)
    {
        return (_factoryFeatures & feature.getMask()) != 0;
    }

    public final boolean isEnabled(JsonGenerator.Feature feature)
    {
        return (_generatorFeatures & feature.getMask()) != 0;
    }

    public final boolean isEnabled(JsonParser.Feature feature)
    {
        return (_parserFeatures & feature.getMask()) != 0;
    }

    protected Object readResolve()
    {
        return new JsonFactory(this, _objectCodec);
    }

    public boolean requiresCustomCodec()
    {
        return false;
    }

    public boolean requiresPropertyOrdering()
    {
        return false;
    }

    public JsonFactory setCharacterEscapes(CharacterEscapes characterescapes)
    {
        _characterEscapes = characterescapes;
        return this;
    }

    public JsonFactory setCodec(ObjectCodec objectcodec)
    {
        _objectCodec = objectcodec;
        return this;
    }

    public JsonFactory setInputDecorator(InputDecorator inputdecorator)
    {
        _inputDecorator = inputdecorator;
        return this;
    }

    public JsonFactory setOutputDecorator(OutputDecorator outputdecorator)
    {
        _outputDecorator = outputdecorator;
        return this;
    }

    public JsonFactory setRootValueSeparator(String s)
    {
        Object obj;
        if(s == null)
            obj = null;
        else
            obj = new SerializedString(s);
        _rootValueSeparator = ((SerializableString) (obj));
        return this;
    }

    public Version version()
    {
        return PackageVersion.VERSION;
    }

    protected static final int DEFAULT_FACTORY_FEATURE_FLAGS = Feature.collectDefaults();
    protected static final int DEFAULT_GENERATOR_FEATURE_FLAGS = JsonGenerator.Feature.collectDefaults();
    protected static final int DEFAULT_PARSER_FEATURE_FLAGS = JsonParser.Feature.collectDefaults();
    private static final SerializableString DEFAULT_ROOT_VALUE_SEPARATOR;
    public static final String FORMAT_NAME_JSON = "JSON";
    protected static final ThreadLocal _recyclerRef = new ThreadLocal();
    private static final long serialVersionUID = 0x2c54da77e773c512L;
    protected CharacterEscapes _characterEscapes;
    protected int _factoryFeatures;
    protected int _generatorFeatures;
    protected InputDecorator _inputDecorator;
    protected ObjectCodec _objectCodec;
    protected OutputDecorator _outputDecorator;
    protected int _parserFeatures;
    protected final transient BytesToNameCanonicalizer _rootByteSymbols;
    protected final transient CharsToNameCanonicalizer _rootCharSymbols;
    protected SerializableString _rootValueSeparator;

    static 
    {
        DEFAULT_ROOT_VALUE_SEPARATOR = DefaultPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR;
    }
}
