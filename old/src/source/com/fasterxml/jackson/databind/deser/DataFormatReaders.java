// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.fasterxml.jackson.databind.deser;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.format.MatchStrength;
import com.fasterxml.jackson.core.io.MergedStream;
import com.fasterxml.jackson.databind.*;
import java.io.*;
import java.util.Collection;

public class DataFormatReaders
{
    protected class AccessorForReader extends com.fasterxml.jackson.core.format.InputAccessor.Std
    {

        public Match createMatcher(ObjectReader objectreader, MatchStrength matchstrength)
        {
            return new Match(_in, _buffer, _bufferedStart, _bufferedEnd - _bufferedStart, objectreader, matchstrength);
        }

        final DataFormatReaders this$0;

        public AccessorForReader(InputStream inputstream, byte abyte0[])
        {
            this$0 = DataFormatReaders.this;
            super(inputstream, abyte0);
        }

        public AccessorForReader(byte abyte0[])
        {
            this$0 = DataFormatReaders.this;
            super(abyte0);
        }

        public AccessorForReader(byte abyte0[], int i, int j)
        {
            this$0 = DataFormatReaders.this;
            super(abyte0, i, j);
        }
    }

    public static class Match
    {

        public JsonParser createParserWithMatch()
            throws IOException
        {
            if(_match == null)
                return null;
            JsonFactory jsonfactory = _match.getFactory();
            if(_originalStream == null)
                return jsonfactory.createParser(_bufferedData, _bufferedStart, _bufferedLength);
            else
                return jsonfactory.createParser(getDataStream());
        }

        public InputStream getDataStream()
        {
            if(_originalStream == null)
                return new ByteArrayInputStream(_bufferedData, _bufferedStart, _bufferedLength);
            else
                return new MergedStream(null, _originalStream, _bufferedData, _bufferedStart, _bufferedLength);
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
            return _match.getFactory().getFormatName();
        }

        public ObjectReader getReader()
        {
            return _match;
        }

        public boolean hasMatch()
        {
            return _match != null;
        }

        protected final byte _bufferedData[];
        protected final int _bufferedLength;
        protected final int _bufferedStart;
        protected final ObjectReader _match;
        protected final MatchStrength _matchStrength;
        protected final InputStream _originalStream;

        protected Match(InputStream inputstream, byte abyte0[], int i, int j, ObjectReader objectreader, MatchStrength matchstrength)
        {
            _originalStream = inputstream;
            _bufferedData = abyte0;
            _bufferedStart = i;
            _bufferedLength = j;
            _match = objectreader;
            _matchStrength = matchstrength;
        }
    }


    public DataFormatReaders(Collection collection)
    {
        this((ObjectReader[])collection.toArray(new ObjectReader[collection.size()]));
    }

    public transient DataFormatReaders(ObjectReader aobjectreader[])
    {
        this(aobjectreader, MatchStrength.SOLID_MATCH, MatchStrength.WEAK_MATCH, 64);
    }

    private DataFormatReaders(ObjectReader aobjectreader[], MatchStrength matchstrength, MatchStrength matchstrength1, int i)
    {
        _readers = aobjectreader;
        _optimalMatch = matchstrength;
        _minimalMatch = matchstrength1;
        _maxInputLookahead = i;
    }

    private Match _findFormat(AccessorForReader accessorforreader)
        throws IOException
    {
        ObjectReader aobjectreader[];
        int i;
        int j;
        Object obj;
        MatchStrength matchstrength;
        aobjectreader = _readers;
        i = aobjectreader.length;
        j = 0;
        obj = null;
        matchstrength = null;
_L2:
        MatchStrength matchstrength1;
        ObjectReader objectreader;
        ObjectReader objectreader1;
        if(j >= i)
            break; /* Loop/switch isn't completed */
        objectreader = aobjectreader[j];
        accessorforreader.reset();
        matchstrength1 = objectreader.getFactory().hasFormat(accessorforreader);
        if(matchstrength1 != null)
        {
            if(matchstrength1.ordinal() < _minimalMatch.ordinal())
            {
                objectreader1 = obj;
            } else
            {
label0:
                {
                    if(obj == null || matchstrength.ordinal() < matchstrength1.ordinal())
                        break label0;
                    objectreader1 = obj;
                }
            }
        } else
        {
            objectreader1 = obj;
        }
_L5:
        j++;
        obj = objectreader1;
        if(true) goto _L2; else goto _L1
        if(matchstrength1.ordinal() < _optimalMatch.ordinal())
            break MISSING_BLOCK_LABEL_145;
_L4:
        return accessorforreader.createMatcher(objectreader, matchstrength1);
_L1:
        matchstrength1 = matchstrength;
        objectreader = obj;
        if(true) goto _L4; else goto _L3
_L3:
        matchstrength = matchstrength1;
        objectreader1 = objectreader;
          goto _L5
    }

    public Match findFormat(InputStream inputstream)
        throws IOException
    {
        return _findFormat(new AccessorForReader(inputstream, new byte[_maxInputLookahead]));
    }

    public Match findFormat(byte abyte0[])
        throws IOException
    {
        return _findFormat(new AccessorForReader(abyte0));
    }

    public Match findFormat(byte abyte0[], int i, int j)
        throws IOException
    {
        return _findFormat(new AccessorForReader(abyte0, i, j));
    }

    public String toString()
    {
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append('[');
        int i = _readers.length;
        if(i > 0)
        {
            stringbuilder.append(_readers[0].getFactory().getFormatName());
            for(int j = 1; j < i; j++)
            {
                stringbuilder.append(", ");
                stringbuilder.append(_readers[j].getFactory().getFormatName());
            }

        }
        stringbuilder.append(']');
        return stringbuilder.toString();
    }

    public DataFormatReaders with(DeserializationConfig deserializationconfig)
    {
        int i = _readers.length;
        ObjectReader aobjectreader[] = new ObjectReader[i];
        for(int j = 0; j < i; j++)
            aobjectreader[j] = _readers[j].with(deserializationconfig);

        return new DataFormatReaders(aobjectreader, _optimalMatch, _minimalMatch, _maxInputLookahead);
    }

    public DataFormatReaders with(ObjectReader aobjectreader[])
    {
        return new DataFormatReaders(aobjectreader, _optimalMatch, _minimalMatch, _maxInputLookahead);
    }

    public DataFormatReaders withMaxInputLookahead(int i)
    {
        if(i == _maxInputLookahead)
            return this;
        else
            return new DataFormatReaders(_readers, _optimalMatch, _minimalMatch, i);
    }

    public DataFormatReaders withMinimalMatch(MatchStrength matchstrength)
    {
        if(matchstrength == _minimalMatch)
            return this;
        else
            return new DataFormatReaders(_readers, _optimalMatch, matchstrength, _maxInputLookahead);
    }

    public DataFormatReaders withOptimalMatch(MatchStrength matchstrength)
    {
        if(matchstrength == _optimalMatch)
            return this;
        else
            return new DataFormatReaders(_readers, matchstrength, _minimalMatch, _maxInputLookahead);
    }

    public DataFormatReaders withType(JavaType javatype)
    {
        int i = _readers.length;
        ObjectReader aobjectreader[] = new ObjectReader[i];
        for(int j = 0; j < i; j++)
            aobjectreader[j] = _readers[j].withType(javatype);

        return new DataFormatReaders(aobjectreader, _optimalMatch, _minimalMatch, _maxInputLookahead);
    }

    public static final int DEFAULT_MAX_INPUT_LOOKAHEAD = 64;
    protected final int _maxInputLookahead;
    protected final MatchStrength _minimalMatch;
    protected final MatchStrength _optimalMatch;
    protected final ObjectReader _readers[];
}
