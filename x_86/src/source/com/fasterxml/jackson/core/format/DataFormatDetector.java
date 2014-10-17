// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.fasterxml.jackson.core.format;

import com.fasterxml.jackson.core.JsonFactory;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;

// Referenced classes of package com.fasterxml.jackson.core.format:
//            MatchStrength, DataFormatMatcher

public class DataFormatDetector
{

    public DataFormatDetector(Collection collection)
    {
        this((JsonFactory[])collection.toArray(new JsonFactory[collection.size()]));
    }

    public transient DataFormatDetector(JsonFactory ajsonfactory[])
    {
        this(ajsonfactory, MatchStrength.SOLID_MATCH, MatchStrength.WEAK_MATCH, 64);
    }

    private DataFormatDetector(JsonFactory ajsonfactory[], MatchStrength matchstrength, MatchStrength matchstrength1, int i)
    {
        _detectors = ajsonfactory;
        _optimalMatch = matchstrength;
        _minimalMatch = matchstrength1;
        _maxInputLookahead = i;
    }

    private DataFormatMatcher _findFormat(InputAccessor.Std std)
        throws IOException
    {
        JsonFactory ajsonfactory[];
        int i;
        int j;
        Object obj;
        MatchStrength matchstrength;
        ajsonfactory = _detectors;
        i = ajsonfactory.length;
        j = 0;
        obj = null;
        matchstrength = null;
_L2:
        MatchStrength matchstrength1;
        JsonFactory jsonfactory;
        JsonFactory jsonfactory1;
        if(j >= i)
            break; /* Loop/switch isn't completed */
        jsonfactory = ajsonfactory[j];
        std.reset();
        matchstrength1 = jsonfactory.hasFormat(std);
        if(matchstrength1 != null)
        {
            if(matchstrength1.ordinal() < _minimalMatch.ordinal())
            {
                jsonfactory1 = obj;
            } else
            {
label0:
                {
                    if(obj == null || matchstrength.ordinal() < matchstrength1.ordinal())
                        break label0;
                    jsonfactory1 = obj;
                }
            }
        } else
        {
            jsonfactory1 = obj;
        }
_L5:
        j++;
        obj = jsonfactory1;
        if(true) goto _L2; else goto _L1
        if(matchstrength1.ordinal() < _optimalMatch.ordinal())
            break MISSING_BLOCK_LABEL_142;
_L4:
        return std.createMatcher(jsonfactory, matchstrength1);
_L1:
        matchstrength1 = matchstrength;
        jsonfactory = obj;
        if(true) goto _L4; else goto _L3
_L3:
        matchstrength = matchstrength1;
        jsonfactory1 = jsonfactory;
          goto _L5
    }

    public DataFormatMatcher findFormat(InputStream inputstream)
        throws IOException
    {
        return _findFormat(new InputAccessor.Std(inputstream, new byte[_maxInputLookahead]));
    }

    public DataFormatMatcher findFormat(byte abyte0[])
        throws IOException
    {
        return _findFormat(new InputAccessor.Std(abyte0));
    }

    public DataFormatMatcher findFormat(byte abyte0[], int i, int j)
        throws IOException
    {
        return _findFormat(new InputAccessor.Std(abyte0, i, j));
    }

    public String toString()
    {
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append('[');
        int i = _detectors.length;
        if(i > 0)
        {
            stringbuilder.append(_detectors[0].getFormatName());
            for(int j = 1; j < i; j++)
            {
                stringbuilder.append(", ");
                stringbuilder.append(_detectors[j].getFormatName());
            }

        }
        stringbuilder.append(']');
        return stringbuilder.toString();
    }

    public DataFormatDetector withMaxInputLookahead(int i)
    {
        if(i == _maxInputLookahead)
            return this;
        else
            return new DataFormatDetector(_detectors, _optimalMatch, _minimalMatch, i);
    }

    public DataFormatDetector withMinimalMatch(MatchStrength matchstrength)
    {
        if(matchstrength == _minimalMatch)
            return this;
        else
            return new DataFormatDetector(_detectors, _optimalMatch, matchstrength, _maxInputLookahead);
    }

    public DataFormatDetector withOptimalMatch(MatchStrength matchstrength)
    {
        if(matchstrength == _optimalMatch)
            return this;
        else
            return new DataFormatDetector(_detectors, matchstrength, _minimalMatch, _maxInputLookahead);
    }

    public static final int DEFAULT_MAX_INPUT_LOOKAHEAD = 64;
    protected final JsonFactory _detectors[];
    protected final int _maxInputLookahead;
    protected final MatchStrength _minimalMatch;
    protected final MatchStrength _optimalMatch;
}
