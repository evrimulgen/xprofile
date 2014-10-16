// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.fasterxml.jackson.core.json;

import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.core.base.GeneratorBase;
import com.fasterxml.jackson.core.io.*;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.core.util.VersionUtil;
import java.io.IOException;

public abstract class JsonGeneratorImpl extends GeneratorBase
{

    public JsonGeneratorImpl(IOContext iocontext, int i, ObjectCodec objectcodec)
    {
        super(i, objectcodec);
        _outputEscapes = sOutputEscapes;
        _rootValueSeparator = DefaultPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR;
        _ioContext = iocontext;
        if(isEnabled(com.fasterxml.jackson.core.JsonGenerator.Feature.ESCAPE_NON_ASCII))
            setHighestNonEscapedChar(127);
    }

    public CharacterEscapes getCharacterEscapes()
    {
        return _characterEscapes;
    }

    public int getHighestEscapedChar()
    {
        return _maximumNonEscapedChar;
    }

    public JsonGenerator setCharacterEscapes(CharacterEscapes characterescapes)
    {
        _characterEscapes = characterescapes;
        if(characterescapes == null)
        {
            _outputEscapes = sOutputEscapes;
            return this;
        } else
        {
            _outputEscapes = characterescapes.getEscapeCodesForAscii();
            return this;
        }
    }

    public JsonGenerator setHighestNonEscapedChar(int i)
    {
        if(i < 0)
            i = 0;
        _maximumNonEscapedChar = i;
        return this;
    }

    public JsonGenerator setRootValueSeparator(SerializableString serializablestring)
    {
        _rootValueSeparator = serializablestring;
        return this;
    }

    public Version version()
    {
        return VersionUtil.versionFor(getClass());
    }

    public final void writeStringField(String s, String s1)
        throws IOException, JsonGenerationException
    {
        writeFieldName(s);
        writeString(s1);
    }

    protected static final int sOutputEscapes[] = CharTypes.get7BitOutputEscapes();
    protected CharacterEscapes _characterEscapes;
    protected final IOContext _ioContext;
    protected int _maximumNonEscapedChar;
    protected int _outputEscapes[];
    protected SerializableString _rootValueSeparator;

}
