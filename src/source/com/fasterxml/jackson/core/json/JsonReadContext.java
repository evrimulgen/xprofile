// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.fasterxml.jackson.core.json;

import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.core.io.CharTypes;

// Referenced classes of package com.fasterxml.jackson.core.json:
//            DupDetector

public final class JsonReadContext extends JsonStreamContext
{

    public JsonReadContext(JsonReadContext jsonreadcontext, DupDetector dupdetector, int i, int j, int k)
    {
        _child = null;
        _parent = jsonreadcontext;
        _dups = dupdetector;
        _type = i;
        _lineNr = j;
        _columnNr = k;
        _index = -1;
    }

    private void _checkDup(DupDetector dupdetector, String s)
        throws JsonProcessingException
    {
        if(dupdetector.isDup(s))
            throw new JsonParseException((new StringBuilder()).append("Duplicate field '").append(s).append("'").toString(), dupdetector.findLocation());
        else
            return;
    }

    public static JsonReadContext createRootContext()
    {
        return createRootContext(null);
    }

    public static JsonReadContext createRootContext(int i, int j)
    {
        return createRootContext(i, j, null);
    }

    public static JsonReadContext createRootContext(int i, int j, DupDetector dupdetector)
    {
        return new JsonReadContext(null, dupdetector, 0, i, j);
    }

    public static JsonReadContext createRootContext(DupDetector dupdetector)
    {
        return new JsonReadContext(null, dupdetector, 0, 1, 0);
    }

    public JsonReadContext createChildArrayContext(int i, int j)
    {
        JsonReadContext jsonreadcontext = _child;
        if(jsonreadcontext == null)
        {
            DupDetector dupdetector;
            JsonReadContext jsonreadcontext1;
            if(_dups == null)
                dupdetector = null;
            else
                dupdetector = _dups.child();
            jsonreadcontext1 = new JsonReadContext(this, dupdetector, 1, i, j);
            _child = jsonreadcontext1;
            return jsonreadcontext1;
        } else
        {
            jsonreadcontext.reset(1, i, j);
            return jsonreadcontext;
        }
    }

    public JsonReadContext createChildObjectContext(int i, int j)
    {
        JsonReadContext jsonreadcontext = _child;
        if(jsonreadcontext == null)
        {
            DupDetector dupdetector;
            JsonReadContext jsonreadcontext1;
            if(_dups == null)
                dupdetector = null;
            else
                dupdetector = _dups.child();
            jsonreadcontext1 = new JsonReadContext(this, dupdetector, 2, i, j);
            _child = jsonreadcontext1;
            return jsonreadcontext1;
        } else
        {
            jsonreadcontext.reset(2, i, j);
            return jsonreadcontext;
        }
    }

    public boolean expectComma()
    {
        int i = 1 + _index;
        _index = i;
        return _type != 0 && i > 0;
    }

    public String getCurrentName()
    {
        return _currentName;
    }

    public volatile JsonStreamContext getParent()
    {
        return getParent();
    }

    public JsonReadContext getParent()
    {
        return _parent;
    }

    public JsonLocation getStartLocation(Object obj)
    {
        return new JsonLocation(obj, -1L, _lineNr, _columnNr);
    }

    protected void reset(int i, int j, int k)
    {
        _type = i;
        _index = -1;
        _lineNr = j;
        _columnNr = k;
        _currentName = null;
        if(_dups != null)
            _dups.reset();
    }

    public void setCurrentName(String s)
        throws JsonProcessingException
    {
        _currentName = s;
        if(_dups != null)
            _checkDup(_dups, s);
    }

    public String toString()
    {
        StringBuilder stringbuilder = new StringBuilder(64);
        _type;
        JVM INSTR tableswitch 0 2: default 40
    //                   0 45
    //                   1 55
    //                   2 81;
           goto _L1 _L2 _L3 _L4
_L1:
        return stringbuilder.toString();
_L2:
        stringbuilder.append("/");
        continue; /* Loop/switch isn't completed */
_L3:
        stringbuilder.append('[');
        stringbuilder.append(getCurrentIndex());
        stringbuilder.append(']');
        continue; /* Loop/switch isn't completed */
_L4:
        stringbuilder.append('{');
        if(_currentName != null)
        {
            stringbuilder.append('"');
            CharTypes.appendQuoted(stringbuilder, _currentName);
            stringbuilder.append('"');
        } else
        {
            stringbuilder.append('?');
        }
        stringbuilder.append('}');
        if(true) goto _L1; else goto _L5
_L5:
    }

    protected JsonReadContext _child;
    protected int _columnNr;
    protected String _currentName;
    protected final DupDetector _dups;
    protected int _lineNr;
    protected final JsonReadContext _parent;
}
