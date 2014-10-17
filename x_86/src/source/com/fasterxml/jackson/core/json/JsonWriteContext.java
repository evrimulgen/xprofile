// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.fasterxml.jackson.core.json;

import com.fasterxml.jackson.core.*;

// Referenced classes of package com.fasterxml.jackson.core.json:
//            DupDetector

public class JsonWriteContext extends JsonStreamContext
{

    protected JsonWriteContext(int i, JsonWriteContext jsonwritecontext, DupDetector dupdetector)
    {
        _child = null;
        _type = i;
        _parent = jsonwritecontext;
        _dups = dupdetector;
        _index = -1;
    }

    private void _checkDup(DupDetector dupdetector, String s)
        throws JsonProcessingException
    {
        if(dupdetector.isDup(s))
            throw new JsonGenerationException((new StringBuilder()).append("Duplicate field '").append(s).append("'").toString());
        else
            return;
    }

    public static JsonWriteContext createRootContext()
    {
        return createRootContext(null);
    }

    public static JsonWriteContext createRootContext(DupDetector dupdetector)
    {
        return new JsonWriteContext(0, null, dupdetector);
    }

    protected final void appendDesc(StringBuilder stringbuilder)
    {
        if(_type == 2)
        {
            stringbuilder.append('{');
            if(_currentName != null)
            {
                stringbuilder.append('"');
                stringbuilder.append(_currentName);
                stringbuilder.append('"');
            } else
            {
                stringbuilder.append('?');
            }
            stringbuilder.append('}');
            return;
        }
        if(_type == 1)
        {
            stringbuilder.append('[');
            stringbuilder.append(getCurrentIndex());
            stringbuilder.append(']');
            return;
        } else
        {
            stringbuilder.append("/");
            return;
        }
    }

    public JsonWriteContext createChildArrayContext()
    {
        JsonWriteContext jsonwritecontext = _child;
        if(jsonwritecontext == null)
        {
            DupDetector dupdetector;
            JsonWriteContext jsonwritecontext1;
            if(_dups == null)
                dupdetector = null;
            else
                dupdetector = _dups.child();
            jsonwritecontext1 = new JsonWriteContext(1, this, dupdetector);
            _child = jsonwritecontext1;
            return jsonwritecontext1;
        } else
        {
            return jsonwritecontext.reset(1);
        }
    }

    public JsonWriteContext createChildObjectContext()
    {
        JsonWriteContext jsonwritecontext = _child;
        if(jsonwritecontext == null)
        {
            DupDetector dupdetector;
            JsonWriteContext jsonwritecontext1;
            if(_dups == null)
                dupdetector = null;
            else
                dupdetector = _dups.child();
            jsonwritecontext1 = new JsonWriteContext(2, this, dupdetector);
            _child = jsonwritecontext1;
            return jsonwritecontext1;
        } else
        {
            return jsonwritecontext.reset(2);
        }
    }

    public final String getCurrentName()
    {
        return _currentName;
    }

    public volatile JsonStreamContext getParent()
    {
        return getParent();
    }

    public final JsonWriteContext getParent()
    {
        return _parent;
    }

    protected JsonWriteContext reset(int i)
    {
        _type = i;
        _index = -1;
        _currentName = null;
        _gotName = false;
        if(_dups != null)
            _dups.reset();
        return this;
    }

    public final String toString()
    {
        StringBuilder stringbuilder = new StringBuilder(64);
        appendDesc(stringbuilder);
        return stringbuilder.toString();
    }

    public final int writeFieldName(String s)
        throws JsonProcessingException
    {
        boolean flag = true;
        _gotName = flag;
        _currentName = s;
        if(_dups != null)
            _checkDup(_dups, s);
        if(_index < 0)
            flag = false;
        return ((flag) ? 1 : 0);
    }

    public final int writeValue()
    {
        if(_type != 2) goto _L2; else goto _L1
_L1:
        byte byte0;
        _gotName = false;
        _index = 1 + _index;
        byte0 = 2;
_L4:
        return byte0;
_L2:
        if(_type != 1)
            break; /* Loop/switch isn't completed */
        int j = _index;
        _index = 1 + _index;
        byte0 = 0;
        if(j >= 0)
            return 1;
        if(true) goto _L4; else goto _L3
_L3:
        _index = 1 + _index;
        int i = _index;
        byte0 = 0;
        if(i != 0)
            return 3;
        if(true) goto _L4; else goto _L5
_L5:
    }

    public static final int STATUS_EXPECT_NAME = 5;
    public static final int STATUS_EXPECT_VALUE = 4;
    public static final int STATUS_OK_AFTER_COLON = 2;
    public static final int STATUS_OK_AFTER_COMMA = 1;
    public static final int STATUS_OK_AFTER_SPACE = 3;
    public static final int STATUS_OK_AS_IS;
    protected JsonWriteContext _child;
    protected String _currentName;
    protected final DupDetector _dups;
    protected boolean _gotName;
    protected final JsonWriteContext _parent;
}
