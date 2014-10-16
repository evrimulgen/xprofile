// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.fasterxml.jackson.core.json;

import com.fasterxml.jackson.core.*;
import java.util.HashSet;

public class DupDetector
{

    private DupDetector(Object obj)
    {
        _source = obj;
    }

    public static DupDetector rootDetector(JsonGenerator jsongenerator)
    {
        return new DupDetector(jsongenerator);
    }

    public static DupDetector rootDetector(JsonParser jsonparser)
    {
        return new DupDetector(jsonparser);
    }

    public DupDetector child()
    {
        return new DupDetector(_source);
    }

    public JsonLocation findLocation()
    {
        if(_source instanceof JsonParser)
            return ((JsonParser)_source).getCurrentLocation();
        else
            return null;
    }

    public boolean isDup(String s)
        throws JsonParseException
    {
        boolean flag = true;
        if(_firstName == null)
        {
            _firstName = s;
            flag = false;
        } else
        if(!s.equals(_firstName))
        {
            if(_secondName == null)
            {
                _secondName = s;
                return false;
            }
            if(!s.equals(_secondName))
            {
                if(_seen == null)
                {
                    _seen = new HashSet(16);
                    _seen.add(_firstName);
                    _seen.add(_secondName);
                }
                if(_seen.add(s))
                    return false;
            }
        }
        return flag;
    }

    public void reset()
    {
        _firstName = null;
        _secondName = null;
        _seen = null;
    }

    protected String _firstName;
    protected String _secondName;
    protected HashSet _seen;
    protected final Object _source;
}
