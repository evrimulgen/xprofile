// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.fasterxml.jackson.core.base;

import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.core.json.DupDetector;
import com.fasterxml.jackson.core.json.JsonWriteContext;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.core.util.VersionUtil;
import java.io.IOException;
import java.io.InputStream;

public abstract class GeneratorBase extends JsonGenerator
{

    protected GeneratorBase(int i, ObjectCodec objectcodec)
    {
        _features = i;
        DupDetector dupdetector;
        if(com.fasterxml.jackson.core.JsonGenerator.Feature.STRICT_DUPLICATE_DETECTION.enabledIn(i))
            dupdetector = DupDetector.rootDetector(this);
        else
            dupdetector = null;
        _writeContext = JsonWriteContext.createRootContext(dupdetector);
        _objectCodec = objectcodec;
        _cfgNumbersAsStrings = com.fasterxml.jackson.core.JsonGenerator.Feature.WRITE_NUMBERS_AS_STRINGS.enabledIn(i);
    }

    protected abstract void _releaseBuffers();

    protected abstract void _verifyValueWrite(String s)
        throws IOException, JsonGenerationException;

    protected void _writeSimpleObject(Object obj)
        throws IOException, JsonGenerationException
    {
        super._writeSimpleObject(obj);
    }

    public void close()
        throws IOException
    {
        _closed = true;
    }

    public JsonGenerator disable(com.fasterxml.jackson.core.JsonGenerator.Feature feature)
    {
        _features = _features & (-1 ^ feature.getMask());
        if(feature == com.fasterxml.jackson.core.JsonGenerator.Feature.WRITE_NUMBERS_AS_STRINGS)
            _cfgNumbersAsStrings = false;
        else
        if(feature == com.fasterxml.jackson.core.JsonGenerator.Feature.ESCAPE_NON_ASCII)
        {
            setHighestNonEscapedChar(0);
            return this;
        }
        return this;
    }

    public JsonGenerator enable(com.fasterxml.jackson.core.JsonGenerator.Feature feature)
    {
        _features = _features | feature.getMask();
        if(feature == com.fasterxml.jackson.core.JsonGenerator.Feature.WRITE_NUMBERS_AS_STRINGS)
            _cfgNumbersAsStrings = true;
        else
        if(feature == com.fasterxml.jackson.core.JsonGenerator.Feature.ESCAPE_NON_ASCII)
        {
            setHighestNonEscapedChar(127);
            return this;
        }
        return this;
    }

    public abstract void flush()
        throws IOException;

    public final ObjectCodec getCodec()
    {
        return _objectCodec;
    }

    public int getFeatureMask()
    {
        return _features;
    }

    public volatile JsonStreamContext getOutputContext()
    {
        return getOutputContext();
    }

    public final JsonWriteContext getOutputContext()
    {
        return _writeContext;
    }

    public boolean isClosed()
    {
        return _closed;
    }

    public final boolean isEnabled(com.fasterxml.jackson.core.JsonGenerator.Feature feature)
    {
        return (_features & feature.getMask()) != 0;
    }

    public JsonGenerator setCodec(ObjectCodec objectcodec)
    {
        _objectCodec = objectcodec;
        return this;
    }

    public JsonGenerator setFeatureMask(int i)
    {
        _features = i;
        return this;
    }

    public JsonGenerator useDefaultPrettyPrinter()
    {
        if(getPrettyPrinter() != null)
            return this;
        else
            return setPrettyPrinter(new DefaultPrettyPrinter());
    }

    public Version version()
    {
        return VersionUtil.versionFor(getClass());
    }

    public int writeBinary(Base64Variant base64variant, InputStream inputstream, int i)
        throws IOException, JsonGenerationException
    {
        _reportUnsupportedOperation();
        return 0;
    }

    public void writeFieldName(SerializableString serializablestring)
        throws IOException, JsonGenerationException
    {
        writeFieldName(serializablestring.getValue());
    }

    public void writeObject(Object obj)
        throws IOException, JsonProcessingException
    {
        if(obj == null)
        {
            writeNull();
            return;
        }
        if(_objectCodec != null)
        {
            _objectCodec.writeValue(this, obj);
            return;
        } else
        {
            _writeSimpleObject(obj);
            return;
        }
    }

    public void writeRawValue(String s)
        throws IOException, JsonGenerationException
    {
        _verifyValueWrite("write raw value");
        writeRaw(s);
    }

    public void writeRawValue(String s, int i, int j)
        throws IOException, JsonGenerationException
    {
        _verifyValueWrite("write raw value");
        writeRaw(s, i, j);
    }

    public void writeRawValue(char ac[], int i, int j)
        throws IOException, JsonGenerationException
    {
        _verifyValueWrite("write raw value");
        writeRaw(ac, i, j);
    }

    public void writeString(SerializableString serializablestring)
        throws IOException, JsonGenerationException
    {
        writeString(serializablestring.getValue());
    }

    public void writeTree(TreeNode treenode)
        throws IOException, JsonProcessingException
    {
        if(treenode == null)
        {
            writeNull();
            return;
        }
        if(_objectCodec == null)
        {
            throw new IllegalStateException("No ObjectCodec defined");
        } else
        {
            _objectCodec.writeValue(this, treenode);
            return;
        }
    }

    protected boolean _cfgNumbersAsStrings;
    protected boolean _closed;
    protected int _features;
    protected ObjectCodec _objectCodec;
    protected JsonWriteContext _writeContext;
}
