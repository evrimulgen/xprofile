// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.fasterxml.jackson.core.format;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.io.MergedStream;
import java.io.*;

// Referenced classes of package com.fasterxml.jackson.core.format:
//            MatchStrength

public class DataFormatMatcher
{

    protected DataFormatMatcher(InputStream inputstream, byte abyte0[], int i, int j, JsonFactory jsonfactory, MatchStrength matchstrength)
    {
        _originalStream = inputstream;
        _bufferedData = abyte0;
        _bufferedStart = i;
        _bufferedLength = j;
        _match = jsonfactory;
        _matchStrength = matchstrength;
    }

    public JsonParser createParserWithMatch()
        throws IOException
    {
        if(_match == null)
            return null;
        if(_originalStream == null)
            return _match.createParser(_bufferedData, _bufferedStart, _bufferedLength);
        else
            return _match.createParser(getDataStream());
    }

    public InputStream getDataStream()
    {
        if(_originalStream == null)
            return new ByteArrayInputStream(_bufferedData, _bufferedStart, _bufferedLength);
        else
            return new MergedStream(null, _originalStream, _bufferedData, _bufferedStart, _bufferedLength);
    }

    public JsonFactory getMatch()
    {
        return _match;
    }

    public MatchStrength getMatchStrength()
    {
        if(_matchStrength == null)
            return MatchStrength.INCONCLUSIVE;
        else
            return _matchStrength;
    }

    public String getMatchedFormatName()
    {
        return _match.getFormatName();
    }

    public boolean hasMatch()
    {
        return _match != null;
    }

    protected final byte _bufferedData[];
    protected final int _bufferedLength;
    protected final int _bufferedStart;
    protected final JsonFactory _match;
    protected final MatchStrength _matchStrength;
    protected final InputStream _originalStream;
}
