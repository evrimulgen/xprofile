// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.fasterxml.jackson.core.base;

import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.core.io.NumberInput;
import com.fasterxml.jackson.core.util.ByteArrayBuilder;
import com.fasterxml.jackson.core.util.VersionUtil;
import java.io.IOException;

public abstract class ParserMinimalBase extends JsonParser
{

    protected ParserMinimalBase()
    {
    }

    protected ParserMinimalBase(int i)
    {
        super(i);
    }

    protected static final String _getCharDesc(int i)
    {
        char c = (char)i;
        if(Character.isISOControl(c))
            return (new StringBuilder()).append("(CTRL-CHAR, code ").append(i).append(")").toString();
        if(i > 255)
            return (new StringBuilder()).append("'").append(c).append("' (code ").append(i).append(" / 0x").append(Integer.toHexString(i)).append(")").toString();
        else
            return (new StringBuilder()).append("'").append(c).append("' (code ").append(i).append(")").toString();
    }

    protected final JsonParseException _constructError(String s, Throwable throwable)
    {
        return new JsonParseException(s, getCurrentLocation(), throwable);
    }

    protected void _decodeBase64(String s, ByteArrayBuilder bytearraybuilder, Base64Variant base64variant)
        throws IOException, JsonParseException
    {
        try
        {
            base64variant.decode(s, bytearraybuilder);
            return;
        }
        catch(IllegalArgumentException illegalargumentexception)
        {
            _reportError(illegalargumentexception.getMessage());
        }
    }

    protected abstract void _handleEOF()
        throws JsonParseException;

    protected char _handleUnrecognizedCharacterEscape(char c)
        throws JsonProcessingException
    {
        while(isEnabled(com.fasterxml.jackson.core.JsonParser.Feature.ALLOW_BACKSLASH_ESCAPING_ANY_CHARACTER) || c == '\'' && isEnabled(com.fasterxml.jackson.core.JsonParser.Feature.ALLOW_SINGLE_QUOTES)) 
            return c;
        _reportError((new StringBuilder()).append("Unrecognized character escape ").append(_getCharDesc(c)).toString());
        return c;
    }

    protected boolean _hasTextualNull(String s)
    {
        return "null".equals(s);
    }

    protected void _reportBase64EOF()
        throws JsonParseException
    {
        throw _constructError("Unexpected end-of-String in base64 content");
    }

    protected final void _reportError(String s)
        throws JsonParseException
    {
        throw _constructError(s);
    }

    protected void _reportInvalidBase64(Base64Variant base64variant, char c, int i, String s)
        throws JsonParseException
    {
        String s1;
        if(c <= ' ')
            s1 = (new StringBuilder()).append("Illegal white space character (code 0x").append(Integer.toHexString(c)).append(") as character #").append(i + 1).append(" of 4-char base64 unit: can only used between units").toString();
        else
        if(base64variant.usesPaddingChar(c))
            s1 = (new StringBuilder()).append("Unexpected padding character ('").append(base64variant.getPaddingChar()).append("') as character #").append(i + 1).append(" of 4-char base64 unit: padding only legal as 3rd or 4th character").toString();
        else
        if(!Character.isDefined(c) || Character.isISOControl(c))
            s1 = (new StringBuilder()).append("Illegal character (code 0x").append(Integer.toHexString(c)).append(") in base64 content").toString();
        else
            s1 = (new StringBuilder()).append("Illegal character '").append(c).append("' (code 0x").append(Integer.toHexString(c)).append(") in base64 content").toString();
        if(s != null)
            s1 = (new StringBuilder()).append(s1).append(": ").append(s).toString();
        throw _constructError(s1);
    }

    protected void _reportInvalidEOF()
        throws JsonParseException
    {
        _reportInvalidEOF((new StringBuilder()).append(" in ").append(_currToken).toString());
    }

    protected void _reportInvalidEOF(String s)
        throws JsonParseException
    {
        _reportError((new StringBuilder()).append("Unexpected end-of-input").append(s).toString());
    }

    protected void _reportInvalidEOFInValue()
        throws JsonParseException
    {
        _reportInvalidEOF(" in a value");
    }

    protected void _reportMissingRootWS(int i)
        throws JsonParseException
    {
        _reportUnexpectedChar(i, "Expected space separating root-level values");
    }

    protected void _reportUnexpectedChar(int i, String s)
        throws JsonParseException
    {
        if(i < 0)
            _reportInvalidEOF();
        String s1 = (new StringBuilder()).append("Unexpected character (").append(_getCharDesc(i)).append(")").toString();
        if(s != null)
            s1 = (new StringBuilder()).append(s1).append(": ").append(s).toString();
        _reportError(s1);
    }

    protected final void _throwInternal()
    {
        VersionUtil.throwInternal();
    }

    protected void _throwInvalidSpace(int i)
        throws JsonParseException
    {
        char c = (char)i;
        _reportError((new StringBuilder()).append("Illegal character (").append(_getCharDesc(c)).append("): only regular white space (\\r, \\n, \\t) is allowed between tokens").toString());
    }

    protected void _throwUnquotedSpace(int i, String s)
        throws JsonParseException
    {
        if(!isEnabled(com.fasterxml.jackson.core.JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS) || i >= 32)
        {
            char c = (char)i;
            _reportError((new StringBuilder()).append("Illegal unquoted character (").append(_getCharDesc(c)).append("): has to be escaped using backslash to be included in ").append(s).toString());
        }
    }

    protected final void _wrapError(String s, Throwable throwable)
        throws JsonParseException
    {
        throw _constructError(s, throwable);
    }

    public void clearCurrentToken()
    {
        if(_currToken != null)
        {
            _lastClearedToken = _currToken;
            _currToken = null;
        }
    }

    public abstract void close()
        throws IOException;

    public abstract byte[] getBinaryValue(Base64Variant base64variant)
        throws IOException, JsonParseException;

    public abstract String getCurrentName()
        throws IOException, JsonParseException;

    public JsonToken getCurrentToken()
    {
        return _currToken;
    }

    public final int getCurrentTokenId()
    {
        JsonToken jsontoken = _currToken;
        if(jsontoken == null)
            return 0;
        else
            return jsontoken.id();
    }

    public JsonToken getLastClearedToken()
    {
        return _lastClearedToken;
    }

    public abstract JsonStreamContext getParsingContext();

    public abstract String getText()
        throws IOException, JsonParseException;

    public abstract char[] getTextCharacters()
        throws IOException, JsonParseException;

    public abstract int getTextLength()
        throws IOException, JsonParseException;

    public abstract int getTextOffset()
        throws IOException, JsonParseException;

    public boolean getValueAsBoolean(boolean flag)
        throws IOException, JsonParseException
    {
        boolean flag1;
        JsonToken jsontoken;
        flag1 = true;
        jsontoken = _currToken;
        if(jsontoken == null) goto _L2; else goto _L1
_L1:
        jsontoken.id();
        JVM INSTR tableswitch 6 12: default 56
    //                   6 60
    //                   7 104
    //                   8 56
    //                   9 58
    //                   10 113
    //                   11 113
    //                   12 115;
           goto _L2 _L3 _L4 _L2 _L5 _L6 _L6 _L7
_L2:
        flag1 = flag;
_L5:
        return flag1;
_L3:
        String s = getText().trim();
        if("true".equals(s)) goto _L5; else goto _L8
_L8:
        if("false".equals(s))
            return false;
        if(_hasTextualNull(s))
            return false;
        continue; /* Loop/switch isn't completed */
_L4:
        if(getIntValue() != 0) goto _L5; else goto _L9
_L9:
        return false;
_L6:
        return false;
_L7:
        Object obj = getEmbeddedObject();
        if(obj instanceof Boolean)
            return ((Boolean)obj).booleanValue();
        if(true) goto _L2; else goto _L10
_L10:
    }

    public double getValueAsDouble(double d)
        throws IOException, JsonParseException
    {
        JsonToken jsontoken = _currToken;
        if(jsontoken == null) goto _L2; else goto _L1
_L1:
        jsontoken.id();
        JVM INSTR tableswitch 6 12: default 56
    //                   6 58
    //                   7 82
    //                   8 82
    //                   9 87
    //                   10 89
    //                   11 89
    //                   12 91;
           goto _L2 _L3 _L4 _L4 _L5 _L6 _L6 _L7
_L2:
        return d;
_L3:
        String s = getText();
        if(_hasTextualNull(s))
            return 0.0D;
        else
            return NumberInput.parseAsDouble(s, d);
_L4:
        return getDoubleValue();
_L5:
        return 1.0D;
_L6:
        return 0.0D;
_L7:
        Object obj = getEmbeddedObject();
        if(obj instanceof Number)
            return ((Number)obj).doubleValue();
        if(true) goto _L2; else goto _L8
_L8:
    }

    public int getValueAsInt(int i)
        throws IOException, JsonParseException
    {
        JsonToken jsontoken = _currToken;
        if(jsontoken == null) goto _L2; else goto _L1
_L1:
        jsontoken.id();
        JVM INSTR tableswitch 6 12: default 56
    //                   6 58
    //                   7 82
    //                   8 82
    //                   9 87
    //                   10 89
    //                   11 91
    //                   12 93;
           goto _L2 _L3 _L4 _L4 _L5 _L6 _L7 _L8
_L2:
        return i;
_L3:
        String s = getText();
        if(_hasTextualNull(s))
            return 0;
        else
            return NumberInput.parseAsInt(s, i);
_L4:
        return getIntValue();
_L5:
        return 1;
_L6:
        return 0;
_L7:
        return 0;
_L8:
        Object obj = getEmbeddedObject();
        if(obj instanceof Number)
            return ((Number)obj).intValue();
        if(true) goto _L2; else goto _L9
_L9:
    }

    public long getValueAsLong(long l)
        throws IOException, JsonParseException
    {
        JsonToken jsontoken = _currToken;
        if(jsontoken == null) goto _L2; else goto _L1
_L1:
        jsontoken.id();
        JVM INSTR tableswitch 6 12: default 56
    //                   6 58
    //                   7 82
    //                   8 82
    //                   9 87
    //                   10 89
    //                   11 89
    //                   12 91;
           goto _L2 _L3 _L4 _L4 _L5 _L6 _L6 _L7
_L2:
        return l;
_L3:
        String s = getText();
        if(_hasTextualNull(s))
            return 0L;
        else
            return NumberInput.parseAsLong(s, l);
_L4:
        return getLongValue();
_L5:
        return 1L;
_L6:
        return 0L;
_L7:
        Object obj = getEmbeddedObject();
        if(obj instanceof Number)
            return ((Number)obj).longValue();
        if(true) goto _L2; else goto _L8
_L8:
    }

    public String getValueAsString(String s)
        throws IOException, JsonParseException
    {
        if(_currToken != JsonToken.VALUE_STRING && (_currToken == null || _currToken == JsonToken.VALUE_NULL || !_currToken.isScalarValue()))
            return s;
        else
            return getText();
    }

    public boolean hasCurrentToken()
    {
        return _currToken != null;
    }

    public abstract boolean hasTextCharacters();

    public abstract boolean isClosed();

    public abstract JsonToken nextToken()
        throws IOException, JsonParseException;

    public JsonToken nextValue()
        throws IOException, JsonParseException
    {
        JsonToken jsontoken = nextToken();
        if(jsontoken == JsonToken.FIELD_NAME)
            jsontoken = nextToken();
        return jsontoken;
    }

    public abstract void overrideCurrentName(String s);

    public JsonParser skipChildren()
        throws IOException, JsonParseException
    {
        if(_currToken != JsonToken.START_OBJECT && _currToken != JsonToken.START_ARRAY)
            return this;
        int i = 1;
        JsonToken jsontoken;
        do
            do
            {
                jsontoken = nextToken();
                if(jsontoken == null)
                {
                    _handleEOF();
                    return this;
                }
                if(!jsontoken.isStructStart())
                    break;
                i++;
            } while(true);
        while(!jsontoken.isStructEnd() || --i != 0);
        return this;
    }

    public Version version()
    {
        return VersionUtil.versionFor(getClass());
    }

    protected static final int INT_BACKSLASH = 92;
    protected static final int INT_COLON = 58;
    protected static final int INT_COMMA = 44;
    protected static final int INT_CR = 13;
    protected static final int INT_LBRACKET = 91;
    protected static final int INT_LCURLY = 123;
    protected static final int INT_LF = 10;
    protected static final int INT_QUOTE = 34;
    protected static final int INT_RBRACKET = 93;
    protected static final int INT_RCURLY = 125;
    protected static final int INT_SLASH = 47;
    protected static final int INT_SPACE = 32;
    protected static final int INT_TAB = 9;
    protected JsonToken _currToken;
    protected JsonToken _lastClearedToken;
}
