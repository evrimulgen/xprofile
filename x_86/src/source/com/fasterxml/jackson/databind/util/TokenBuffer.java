// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.fasterxml.jackson.databind.util;

import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.core.base.ParserMinimalBase;
import com.fasterxml.jackson.core.json.JsonReadContext;
import com.fasterxml.jackson.core.json.JsonWriteContext;
import com.fasterxml.jackson.core.util.ByteArrayBuilder;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.cfg.PackageVersion;
import java.io.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.TreeMap;

public class TokenBuffer extends JsonGenerator
{
    protected static final class Parser extends ParserMinimalBase
    {

        protected final void _checkIsNumber()
            throws JsonParseException
        {
            if(_currToken == null || !_currToken.isNumeric())
                throw _constructError((new StringBuilder()).append("Current token (").append(_currToken).append(") not numeric, can not use numeric value accessors").toString());
            else
                return;
        }

        protected final Object _currentObject()
        {
            return _segment.get(_segmentPtr);
        }

        protected void _handleEOF()
            throws JsonParseException
        {
            _throwInternal();
        }

        public boolean canReadObjectId()
        {
            return _hasNativeObjectIds;
        }

        public boolean canReadTypeId()
        {
            return _hasNativeTypeIds;
        }

        public void close()
            throws IOException
        {
            if(!_closed)
                _closed = true;
        }

        public BigInteger getBigIntegerValue()
            throws IOException, JsonParseException
        {
            Number number = getNumberValue();
            if(number instanceof BigInteger)
                return (BigInteger)number;
            if(getNumberType() == com.fasterxml.jackson.core.JsonParser.NumberType.BIG_DECIMAL)
                return ((BigDecimal)number).toBigInteger();
            else
                return BigInteger.valueOf(number.longValue());
        }

        public byte[] getBinaryValue(Base64Variant base64variant)
            throws IOException, JsonParseException
        {
            if(_currToken == JsonToken.VALUE_EMBEDDED_OBJECT)
            {
                Object obj = _currentObject();
                if(obj instanceof byte[])
                    return (byte[])(byte[])obj;
            }
            if(_currToken != JsonToken.VALUE_STRING)
                throw _constructError((new StringBuilder()).append("Current token (").append(_currToken).append(") not VALUE_STRING (or VALUE_EMBEDDED_OBJECT with byte[]), can not access as binary").toString());
            String s = getText();
            if(s == null)
                return null;
            ByteArrayBuilder bytearraybuilder = _byteBuilder;
            if(bytearraybuilder == null)
            {
                bytearraybuilder = new ByteArrayBuilder(100);
                _byteBuilder = bytearraybuilder;
            } else
            {
                _byteBuilder.reset();
            }
            _decodeBase64(s, bytearraybuilder, base64variant);
            return bytearraybuilder.toByteArray();
        }

        public ObjectCodec getCodec()
        {
            return _codec;
        }

        public JsonLocation getCurrentLocation()
        {
            if(_location == null)
                return JsonLocation.NA;
            else
                return _location;
        }

        public String getCurrentName()
        {
            return _parsingContext.getCurrentName();
        }

        public BigDecimal getDecimalValue()
            throws IOException, JsonParseException
        {
            Number number = getNumberValue();
            if(number instanceof BigDecimal)
                return (BigDecimal)number;
            static class _cls1
            {

                static final int $SwitchMap$com$fasterxml$jackson$core$JsonParser$NumberType[];
                static final int $SwitchMap$com$fasterxml$jackson$core$JsonToken[];

                static 
                {
                    $SwitchMap$com$fasterxml$jackson$core$JsonParser$NumberType = new int[com.fasterxml.jackson.core.JsonParser.NumberType.values().length];
                    try
                    {
                        $SwitchMap$com$fasterxml$jackson$core$JsonParser$NumberType[com.fasterxml.jackson.core.JsonParser.NumberType.INT.ordinal()] = 1;
                    }
                    catch(NoSuchFieldError nosuchfielderror) { }
                    try
                    {
                        $SwitchMap$com$fasterxml$jackson$core$JsonParser$NumberType[com.fasterxml.jackson.core.JsonParser.NumberType.BIG_INTEGER.ordinal()] = 2;
                    }
                    catch(NoSuchFieldError nosuchfielderror1) { }
                    try
                    {
                        $SwitchMap$com$fasterxml$jackson$core$JsonParser$NumberType[com.fasterxml.jackson.core.JsonParser.NumberType.BIG_DECIMAL.ordinal()] = 3;
                    }
                    catch(NoSuchFieldError nosuchfielderror2) { }
                    try
                    {
                        $SwitchMap$com$fasterxml$jackson$core$JsonParser$NumberType[com.fasterxml.jackson.core.JsonParser.NumberType.FLOAT.ordinal()] = 4;
                    }
                    catch(NoSuchFieldError nosuchfielderror3) { }
                    try
                    {
                        $SwitchMap$com$fasterxml$jackson$core$JsonParser$NumberType[com.fasterxml.jackson.core.JsonParser.NumberType.LONG.ordinal()] = 5;
                    }
                    catch(NoSuchFieldError nosuchfielderror4) { }
                    $SwitchMap$com$fasterxml$jackson$core$JsonToken = new int[JsonToken.values().length];
                    try
                    {
                        $SwitchMap$com$fasterxml$jackson$core$JsonToken[JsonToken.START_OBJECT.ordinal()] = 1;
                    }
                    catch(NoSuchFieldError nosuchfielderror5) { }
                    try
                    {
                        $SwitchMap$com$fasterxml$jackson$core$JsonToken[JsonToken.END_OBJECT.ordinal()] = 2;
                    }
                    catch(NoSuchFieldError nosuchfielderror6) { }
                    try
                    {
                        $SwitchMap$com$fasterxml$jackson$core$JsonToken[JsonToken.START_ARRAY.ordinal()] = 3;
                    }
                    catch(NoSuchFieldError nosuchfielderror7) { }
                    try
                    {
                        $SwitchMap$com$fasterxml$jackson$core$JsonToken[JsonToken.END_ARRAY.ordinal()] = 4;
                    }
                    catch(NoSuchFieldError nosuchfielderror8) { }
                    try
                    {
                        $SwitchMap$com$fasterxml$jackson$core$JsonToken[JsonToken.FIELD_NAME.ordinal()] = 5;
                    }
                    catch(NoSuchFieldError nosuchfielderror9) { }
                    try
                    {
                        $SwitchMap$com$fasterxml$jackson$core$JsonToken[JsonToken.VALUE_STRING.ordinal()] = 6;
                    }
                    catch(NoSuchFieldError nosuchfielderror10) { }
                    try
                    {
                        $SwitchMap$com$fasterxml$jackson$core$JsonToken[JsonToken.VALUE_NUMBER_INT.ordinal()] = 7;
                    }
                    catch(NoSuchFieldError nosuchfielderror11) { }
                    try
                    {
                        $SwitchMap$com$fasterxml$jackson$core$JsonToken[JsonToken.VALUE_NUMBER_FLOAT.ordinal()] = 8;
                    }
                    catch(NoSuchFieldError nosuchfielderror12) { }
                    try
                    {
                        $SwitchMap$com$fasterxml$jackson$core$JsonToken[JsonToken.VALUE_TRUE.ordinal()] = 9;
                    }
                    catch(NoSuchFieldError nosuchfielderror13) { }
                    try
                    {
                        $SwitchMap$com$fasterxml$jackson$core$JsonToken[JsonToken.VALUE_FALSE.ordinal()] = 10;
                    }
                    catch(NoSuchFieldError nosuchfielderror14) { }
                    try
                    {
                        $SwitchMap$com$fasterxml$jackson$core$JsonToken[JsonToken.VALUE_NULL.ordinal()] = 11;
                    }
                    catch(NoSuchFieldError nosuchfielderror15) { }
                    try
                    {
                        $SwitchMap$com$fasterxml$jackson$core$JsonToken[JsonToken.VALUE_EMBEDDED_OBJECT.ordinal()] = 12;
                    }
                    catch(NoSuchFieldError nosuchfielderror16)
                    {
                        return;
                    }
                }
            }

            switch(_cls1..SwitchMap.com.fasterxml.jackson.core.JsonParser.NumberType[getNumberType().ordinal()])
            {
            case 3: // '\003'
            case 4: // '\004'
            default:
                return BigDecimal.valueOf(number.doubleValue());

            case 1: // '\001'
            case 5: // '\005'
                return BigDecimal.valueOf(number.longValue());

            case 2: // '\002'
                return new BigDecimal((BigInteger)number);
            }
        }

        public double getDoubleValue()
            throws IOException, JsonParseException
        {
            return getNumberValue().doubleValue();
        }

        public Object getEmbeddedObject()
        {
            if(_currToken == JsonToken.VALUE_EMBEDDED_OBJECT)
                return _currentObject();
            else
                return null;
        }

        public float getFloatValue()
            throws IOException, JsonParseException
        {
            return getNumberValue().floatValue();
        }

        public int getIntValue()
            throws IOException, JsonParseException
        {
            if(_currToken == JsonToken.VALUE_NUMBER_INT)
                return ((Number)_currentObject()).intValue();
            else
                return getNumberValue().intValue();
        }

        public long getLongValue()
            throws IOException, JsonParseException
        {
            return getNumberValue().longValue();
        }

        public com.fasterxml.jackson.core.JsonParser.NumberType getNumberType()
            throws IOException, JsonParseException
        {
            Number number = getNumberValue();
            if(number instanceof Integer)
                return com.fasterxml.jackson.core.JsonParser.NumberType.INT;
            if(number instanceof Long)
                return com.fasterxml.jackson.core.JsonParser.NumberType.LONG;
            if(number instanceof Double)
                return com.fasterxml.jackson.core.JsonParser.NumberType.DOUBLE;
            if(number instanceof BigDecimal)
                return com.fasterxml.jackson.core.JsonParser.NumberType.BIG_DECIMAL;
            if(number instanceof BigInteger)
                return com.fasterxml.jackson.core.JsonParser.NumberType.BIG_INTEGER;
            if(number instanceof Float)
                return com.fasterxml.jackson.core.JsonParser.NumberType.FLOAT;
            if(number instanceof Short)
                return com.fasterxml.jackson.core.JsonParser.NumberType.INT;
            else
                return null;
        }

        public final Number getNumberValue()
            throws IOException, JsonParseException
        {
            _checkIsNumber();
            Object obj = _currentObject();
            if(obj instanceof Number)
                return (Number)obj;
            if(obj instanceof String)
            {
                String s = (String)obj;
                if(s.indexOf('.') >= 0)
                    return Double.valueOf(Double.parseDouble(s));
                else
                    return Long.valueOf(Long.parseLong(s));
            }
            if(obj == null)
                return null;
            else
                throw new IllegalStateException((new StringBuilder()).append("Internal error: entry should be a Number, but is of type ").append(obj.getClass().getName()).toString());
        }

        public Object getObjectId()
        {
            return _segment.findObjectId(_segmentPtr);
        }

        public JsonStreamContext getParsingContext()
        {
            return _parsingContext;
        }

        public String getText()
        {
            if(_currToken != JsonToken.VALUE_STRING && _currToken != JsonToken.FIELD_NAME) goto _L2; else goto _L1
_L1:
            Object obj = _currentObject();
            if(!(obj instanceof String)) goto _L4; else goto _L3
_L3:
            String s1 = (String)obj;
_L6:
            return s1;
_L4:
            String s;
            if(obj == null)
                s = null;
            else
                s = obj.toString();
            return s;
_L2:
            JsonToken jsontoken = _currToken;
            s1 = null;
            if(jsontoken != null)
            {
                Object obj1;
                switch(_cls1..SwitchMap.com.fasterxml.jackson.core.JsonToken[_currToken.ordinal()])
                {
                default:
                    return _currToken.asString();

                case 7: // '\007'
                case 8: // '\b'
                    obj1 = _currentObject();
                    break;
                }
                s1 = null;
                if(obj1 != null)
                    return obj1.toString();
            }
            if(true) goto _L6; else goto _L5
_L5:
        }

        public char[] getTextCharacters()
        {
            String s = getText();
            if(s == null)
                return null;
            else
                return s.toCharArray();
        }

        public int getTextLength()
        {
            String s = getText();
            if(s == null)
                return 0;
            else
                return s.length();
        }

        public int getTextOffset()
        {
            return 0;
        }

        public JsonLocation getTokenLocation()
        {
            return getCurrentLocation();
        }

        public Object getTypeId()
        {
            return _segment.findTypeId(_segmentPtr);
        }

        public boolean hasTextCharacters()
        {
            return false;
        }

        public boolean isClosed()
        {
            return _closed;
        }

        public JsonToken nextToken()
            throws IOException, JsonParseException
        {
            if(!_closed && _segment != null) goto _L2; else goto _L1
_L1:
            return null;
_L2:
            int i = 1 + _segmentPtr;
            _segmentPtr = i;
            if(i < 16)
                break; /* Loop/switch isn't completed */
            _segmentPtr = 0;
            _segment = _segment.next();
            if(_segment == null) goto _L1; else goto _L3
_L3:
            _currToken = _segment.type(_segmentPtr);
            if(_currToken != JsonToken.FIELD_NAME) goto _L5; else goto _L4
_L4:
            Object obj = _currentObject();
            String s;
            if(obj instanceof String)
                s = (String)obj;
            else
                s = obj.toString();
            _parsingContext.setCurrentName(s);
_L7:
            return _currToken;
_L5:
            if(_currToken == JsonToken.START_OBJECT)
                _parsingContext = _parsingContext.createChildObjectContext(-1, -1);
            else
            if(_currToken == JsonToken.START_ARRAY)
                _parsingContext = _parsingContext.createChildArrayContext(-1, -1);
            else
            if(_currToken == JsonToken.END_OBJECT || _currToken == JsonToken.END_ARRAY)
            {
                _parsingContext = _parsingContext.getParent();
                if(_parsingContext == null)
                    _parsingContext = JsonReadContext.createRootContext(null);
            }
            if(true) goto _L7; else goto _L6
_L6:
        }

        public void overrideCurrentName(String s)
        {
            JsonReadContext jsonreadcontext = _parsingContext;
            if(_currToken == JsonToken.START_OBJECT || _currToken == JsonToken.START_ARRAY)
                jsonreadcontext = jsonreadcontext.getParent();
            try
            {
                jsonreadcontext.setCurrentName(s);
                return;
            }
            catch(IOException ioexception)
            {
                throw new RuntimeException(ioexception);
            }
        }

        public JsonToken peekNextToken()
            throws IOException, JsonParseException
        {
            if(!_closed)
            {
                Segment segment = _segment;
                int i = 1 + _segmentPtr;
                Segment segment1;
                if(i >= 16)
                {
                    Segment segment2;
                    if(segment == null)
                        segment2 = null;
                    else
                        segment2 = segment.next();
                    segment1 = segment2;
                    i = 0;
                } else
                {
                    segment1 = segment;
                }
                if(segment1 != null)
                    return segment1.type(i);
            }
            return null;
        }

        public int readBinaryValue(Base64Variant base64variant, OutputStream outputstream)
            throws IOException, JsonParseException
        {
            byte abyte0[] = getBinaryValue(base64variant);
            int i = 0;
            if(abyte0 != null)
            {
                outputstream.write(abyte0, 0, abyte0.length);
                i = abyte0.length;
            }
            return i;
        }

        public void setCodec(ObjectCodec objectcodec)
        {
            _codec = objectcodec;
        }

        public void setLocation(JsonLocation jsonlocation)
        {
            _location = jsonlocation;
        }

        public Version version()
        {
            return PackageVersion.VERSION;
        }

        protected transient ByteArrayBuilder _byteBuilder;
        protected boolean _closed;
        protected ObjectCodec _codec;
        protected final boolean _hasNativeIds;
        protected final boolean _hasNativeObjectIds;
        protected final boolean _hasNativeTypeIds;
        protected JsonLocation _location;
        protected JsonReadContext _parsingContext;
        protected Segment _segment;
        protected int _segmentPtr;

        protected Parser(Segment segment, ObjectCodec objectcodec)
        {
            this(segment, objectcodec, false, false);
        }

        public Parser(Segment segment, ObjectCodec objectcodec, boolean flag, boolean flag1)
        {
            super(0);
            _location = null;
            _segment = segment;
            _segmentPtr = -1;
            _codec = objectcodec;
            _parsingContext = JsonReadContext.createRootContext(null);
            _hasNativeTypeIds = flag;
            _hasNativeObjectIds = flag1;
            _hasNativeIds = flag | flag1;
        }
    }

    protected static final class Segment
    {

        private final int _objectIdIndex(int i)
        {
            return 1 + (i + i);
        }

        private final int _typeIdIndex(int i)
        {
            return i + i;
        }

        private final void assignNativeIds(int i, Object obj, Object obj1)
        {
            if(_nativeIds == null)
                _nativeIds = new TreeMap();
            if(obj != null)
                _nativeIds.put(Integer.valueOf(_objectIdIndex(i)), obj);
            if(obj1 != null)
                _nativeIds.put(Integer.valueOf(_typeIdIndex(i)), obj1);
        }

        private void set(int i, int j, Object obj)
        {
            _tokens[i] = obj;
            long l = j;
            if(i > 0)
                l <<= i << 2;
            _tokenTypes = l | _tokenTypes;
        }

        private void set(int i, int j, Object obj, Object obj1, Object obj2)
        {
            _tokens[i] = obj;
            long l = j;
            if(i > 0)
                l <<= i << 2;
            _tokenTypes = l | _tokenTypes;
            assignNativeIds(i, obj1, obj2);
        }

        private void set(int i, JsonToken jsontoken)
        {
            long l = jsontoken.ordinal();
            if(i > 0)
                l <<= i << 2;
            _tokenTypes = l | _tokenTypes;
        }

        private void set(int i, JsonToken jsontoken, Object obj)
        {
            _tokens[i] = obj;
            long l = jsontoken.ordinal();
            if(i > 0)
                l <<= i << 2;
            _tokenTypes = l | _tokenTypes;
        }

        private void set(int i, JsonToken jsontoken, Object obj, Object obj1)
        {
            long l = jsontoken.ordinal();
            if(i > 0)
                l <<= i << 2;
            _tokenTypes = l | _tokenTypes;
            assignNativeIds(i, obj, obj1);
        }

        private void set(int i, JsonToken jsontoken, Object obj, Object obj1, Object obj2)
        {
            _tokens[i] = obj;
            long l = jsontoken.ordinal();
            if(i > 0)
                l <<= i << 2;
            _tokenTypes = l | _tokenTypes;
            assignNativeIds(i, obj1, obj2);
        }

        public Segment append(int i, JsonToken jsontoken)
        {
            if(i < 16)
            {
                set(i, jsontoken);
                return null;
            } else
            {
                _next = new Segment();
                _next.set(0, jsontoken);
                return _next;
            }
        }

        public Segment append(int i, JsonToken jsontoken, Object obj)
        {
            if(i < 16)
            {
                set(i, jsontoken, obj);
                return null;
            } else
            {
                _next = new Segment();
                _next.set(0, jsontoken, obj);
                return _next;
            }
        }

        public Segment append(int i, JsonToken jsontoken, Object obj, Object obj1)
        {
            if(i < 16)
            {
                set(i, jsontoken, obj, obj1);
                return null;
            } else
            {
                _next = new Segment();
                _next.set(0, jsontoken, obj, obj1);
                return _next;
            }
        }

        public Segment append(int i, JsonToken jsontoken, Object obj, Object obj1, Object obj2)
        {
            if(i < 16)
            {
                set(i, jsontoken, obj, obj1, obj2);
                return null;
            } else
            {
                _next = new Segment();
                _next.set(0, jsontoken, obj, obj1, obj2);
                return _next;
            }
        }

        public Segment appendRaw(int i, int j, Object obj)
        {
            if(i < 16)
            {
                set(i, j, obj);
                return null;
            } else
            {
                _next = new Segment();
                _next.set(0, j, obj);
                return _next;
            }
        }

        public Segment appendRaw(int i, int j, Object obj, Object obj1, Object obj2)
        {
            if(i < 16)
            {
                set(i, j, obj, obj1, obj2);
                return null;
            } else
            {
                _next = new Segment();
                _next.set(0, j, obj, obj1, obj2);
                return _next;
            }
        }

        public Object findObjectId(int i)
        {
            if(_nativeIds == null)
                return null;
            else
                return _nativeIds.get(Integer.valueOf(_objectIdIndex(i)));
        }

        public Object findTypeId(int i)
        {
            if(_nativeIds == null)
                return null;
            else
                return _nativeIds.get(Integer.valueOf(_typeIdIndex(i)));
        }

        public Object get(int i)
        {
            return _tokens[i];
        }

        public boolean hasIds()
        {
            return _nativeIds != null;
        }

        public Segment next()
        {
            return _next;
        }

        public int rawType(int i)
        {
            long l = _tokenTypes;
            if(i > 0)
                l >>= i << 2;
            return 0xf & (int)l;
        }

        public JsonToken type(int i)
        {
            long l = _tokenTypes;
            if(i > 0)
                l >>= i << 2;
            int j = 0xf & (int)l;
            return TOKEN_TYPES_BY_INDEX[j];
        }

        public static final int TOKENS_PER_SEGMENT = 16;
        private static final JsonToken TOKEN_TYPES_BY_INDEX[];
        protected TreeMap _nativeIds;
        protected Segment _next;
        protected long _tokenTypes;
        protected final Object _tokens[] = new Object[16];

        static 
        {
            TOKEN_TYPES_BY_INDEX = new JsonToken[16];
            JsonToken ajsontoken[] = JsonToken.values();
            System.arraycopy(ajsontoken, 1, TOKEN_TYPES_BY_INDEX, 1, Math.min(15, -1 + ajsontoken.length));
        }

        public Segment()
        {
        }
    }


    public TokenBuffer(JsonParser jsonparser)
    {
        _hasNativeId = false;
        _objectCodec = jsonparser.getCodec();
        _generatorFeatures = DEFAULT_GENERATOR_FEATURES;
        _writeContext = JsonWriteContext.createRootContext(null);
        Segment segment = new Segment();
        _last = segment;
        _first = segment;
        _appendOffset = 0;
        _hasNativeTypeIds = jsonparser.canReadTypeId();
        _hasNativeObjectIds = jsonparser.canReadObjectId();
        _mayHaveNativeIds = _hasNativeTypeIds | _hasNativeObjectIds;
    }

    public TokenBuffer(ObjectCodec objectcodec)
    {
        this(objectcodec, false);
    }

    public TokenBuffer(ObjectCodec objectcodec, boolean flag)
    {
        _hasNativeId = false;
        _objectCodec = objectcodec;
        _generatorFeatures = DEFAULT_GENERATOR_FEATURES;
        _writeContext = JsonWriteContext.createRootContext(null);
        Segment segment = new Segment();
        _last = segment;
        _first = segment;
        _appendOffset = 0;
        _hasNativeTypeIds = flag;
        _hasNativeObjectIds = flag;
        _mayHaveNativeIds = _hasNativeTypeIds | _hasNativeObjectIds;
    }

    private final void _appendNativeIds(StringBuilder stringbuilder)
    {
        Object obj = _last.findObjectId(-1 + _appendOffset);
        if(obj != null)
            stringbuilder.append("[objectId=").append(String.valueOf(obj)).append(']');
        Object obj1 = _last.findTypeId(-1 + _appendOffset);
        if(obj1 != null)
            stringbuilder.append("[typeId=").append(String.valueOf(obj1)).append(']');
    }

    private final void _checkNativeIds(JsonParser jsonparser)
        throws IOException, JsonProcessingException
    {
        Object obj = jsonparser.getTypeId();
        _typeId = obj;
        if(obj != null)
            _hasNativeId = true;
        Object obj1 = jsonparser.getObjectId();
        _objectId = obj1;
        if(obj1 != null)
            _hasNativeId = true;
    }

    protected final void _append(JsonToken jsontoken)
    {
        Segment segment;
        if(_hasNativeId)
            segment = _last.append(_appendOffset, jsontoken, _objectId, _typeId);
        else
            segment = _last.append(_appendOffset, jsontoken);
        if(segment == null)
        {
            _appendOffset = 1 + _appendOffset;
            return;
        } else
        {
            _last = segment;
            _appendOffset = 1;
            return;
        }
    }

    protected final void _append(JsonToken jsontoken, Object obj)
    {
        Segment segment;
        if(_hasNativeId)
            segment = _last.append(_appendOffset, jsontoken, obj, _objectId, _typeId);
        else
            segment = _last.append(_appendOffset, jsontoken, obj);
        if(segment == null)
        {
            _appendOffset = 1 + _appendOffset;
            return;
        } else
        {
            _last = segment;
            _appendOffset = 1;
            return;
        }
    }

    protected final void _appendRaw(int i, Object obj)
    {
        Segment segment;
        if(_hasNativeId)
            segment = _last.appendRaw(_appendOffset, i, obj, _objectId, _typeId);
        else
            segment = _last.appendRaw(_appendOffset, i, obj);
        if(segment == null)
        {
            _appendOffset = 1 + _appendOffset;
            return;
        } else
        {
            _last = segment;
            _appendOffset = 1;
            return;
        }
    }

    protected void _reportUnsupportedOperation()
    {
        throw new UnsupportedOperationException("Called operation not supported for TokenBuffer");
    }

    public TokenBuffer append(TokenBuffer tokenbuffer)
        throws IOException, JsonGenerationException
    {
        if(!_hasNativeTypeIds)
            _hasNativeTypeIds = tokenbuffer.canWriteTypeId();
        if(!_hasNativeObjectIds)
            _hasNativeObjectIds = tokenbuffer.canWriteObjectId();
        _mayHaveNativeIds = _hasNativeTypeIds | _hasNativeObjectIds;
        for(JsonParser jsonparser = tokenbuffer.asParser(); jsonparser.nextToken() != null; copyCurrentStructure(jsonparser));
        return this;
    }

    public JsonParser asParser()
    {
        return asParser(_objectCodec);
    }

    public JsonParser asParser(JsonParser jsonparser)
    {
        Parser parser = new Parser(_first, jsonparser.getCodec(), _hasNativeTypeIds, _hasNativeObjectIds);
        parser.setLocation(jsonparser.getTokenLocation());
        return parser;
    }

    public JsonParser asParser(ObjectCodec objectcodec)
    {
        return new Parser(_first, objectcodec, _hasNativeTypeIds, _hasNativeObjectIds);
    }

    public boolean canWriteBinaryNatively()
    {
        return true;
    }

    public boolean canWriteObjectId()
    {
        return _hasNativeObjectIds;
    }

    public boolean canWriteTypeId()
    {
        return _hasNativeTypeIds;
    }

    public void close()
        throws IOException
    {
        _closed = true;
    }

    public void copyCurrentEvent(JsonParser jsonparser)
        throws IOException, JsonProcessingException
    {
        if(_mayHaveNativeIds)
            _checkNativeIds(jsonparser);
        switch(_cls1..SwitchMap.com.fasterxml.jackson.core.JsonToken[jsonparser.getCurrentToken().ordinal()])
        {
        default:
            throw new RuntimeException("Internal error: should never end up through this code path");

        case 1: // '\001'
            writeStartObject();
            return;

        case 2: // '\002'
            writeEndObject();
            return;

        case 3: // '\003'
            writeStartArray();
            return;

        case 4: // '\004'
            writeEndArray();
            return;

        case 5: // '\005'
            writeFieldName(jsonparser.getCurrentName());
            return;

        case 6: // '\006'
            if(jsonparser.hasTextCharacters())
            {
                writeString(jsonparser.getTextCharacters(), jsonparser.getTextOffset(), jsonparser.getTextLength());
                return;
            } else
            {
                writeString(jsonparser.getText());
                return;
            }

        case 7: // '\007'
            switch(_cls1..SwitchMap.com.fasterxml.jackson.core.JsonParser.NumberType[jsonparser.getNumberType().ordinal()])
            {
            default:
                writeNumber(jsonparser.getLongValue());
                return;

            case 1: // '\001'
                writeNumber(jsonparser.getIntValue());
                return;

            case 2: // '\002'
                writeNumber(jsonparser.getBigIntegerValue());
                break;
            }
            return;

        case 8: // '\b'
            switch(_cls1..SwitchMap.com.fasterxml.jackson.core.JsonParser.NumberType[jsonparser.getNumberType().ordinal()])
            {
            default:
                writeNumber(jsonparser.getDoubleValue());
                return;

            case 3: // '\003'
                writeNumber(jsonparser.getDecimalValue());
                return;

            case 4: // '\004'
                writeNumber(jsonparser.getFloatValue());
                break;
            }
            return;

        case 9: // '\t'
            writeBoolean(true);
            return;

        case 10: // '\n'
            writeBoolean(false);
            return;

        case 11: // '\013'
            writeNull();
            return;

        case 12: // '\f'
            writeObject(jsonparser.getEmbeddedObject());
            return;
        }
    }

    public void copyCurrentStructure(JsonParser jsonparser)
        throws IOException, JsonProcessingException
    {
        JsonToken jsontoken = jsonparser.getCurrentToken();
        if(jsontoken == JsonToken.FIELD_NAME)
        {
            if(_mayHaveNativeIds)
                _checkNativeIds(jsonparser);
            writeFieldName(jsonparser.getCurrentName());
            jsontoken = jsonparser.nextToken();
        }
        if(_mayHaveNativeIds)
            _checkNativeIds(jsonparser);
        switch(_cls1..SwitchMap.com.fasterxml.jackson.core.JsonToken[jsontoken.ordinal()])
        {
        case 2: // '\002'
        default:
            copyCurrentEvent(jsonparser);
            return;

        case 3: // '\003'
            writeStartArray();
            for(; jsonparser.nextToken() != JsonToken.END_ARRAY; copyCurrentStructure(jsonparser));
            writeEndArray();
            return;

        case 1: // '\001'
            writeStartObject();
            break;
        }
        for(; jsonparser.nextToken() != JsonToken.END_OBJECT; copyCurrentStructure(jsonparser));
        writeEndObject();
    }

    public TokenBuffer deserialize(JsonParser jsonparser, DeserializationContext deserializationcontext)
        throws IOException, JsonProcessingException
    {
        copyCurrentStructure(jsonparser);
        return this;
    }

    public JsonGenerator disable(com.fasterxml.jackson.core.JsonGenerator.Feature feature)
    {
        _generatorFeatures = _generatorFeatures & (-1 ^ feature.getMask());
        return this;
    }

    public JsonGenerator enable(com.fasterxml.jackson.core.JsonGenerator.Feature feature)
    {
        _generatorFeatures = _generatorFeatures | feature.getMask();
        return this;
    }

    public JsonToken firstToken()
    {
        if(_first != null)
            return _first.type(0);
        else
            return null;
    }

    public void flush()
        throws IOException
    {
    }

    public ObjectCodec getCodec()
    {
        return _objectCodec;
    }

    public int getFeatureMask()
    {
        return _generatorFeatures;
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

    public boolean isEnabled(com.fasterxml.jackson.core.JsonGenerator.Feature feature)
    {
        return (_generatorFeatures & feature.getMask()) != 0;
    }

    public void serialize(JsonGenerator jsongenerator)
        throws IOException, JsonGenerationException
    {
        boolean flag;
        boolean flag1;
        int i;
        Segment segment1;
        int j;
        Segment segment3;
        Segment segment = _first;
        flag = _mayHaveNativeIds;
        if(flag && segment.hasIds())
            flag1 = true;
        else
            flag1 = false;
        i = -1;
        segment1 = segment;
_L17:
        j = i + 1;
        if(j < 16)
            break MISSING_BLOCK_LABEL_656;
        segment3 = segment1.next();
        if(segment3 != null) goto _L2; else goto _L1
_L1:
        return;
_L2:
        Segment segment2;
        int k;
        boolean flag2;
        JsonToken jsontoken;
        Object obj4;
        Object obj5;
        boolean flag3;
        if(flag && segment3.hasIds())
            flag3 = true;
        else
            flag3 = false;
        k = 0;
        segment2 = segment3;
        flag2 = flag3;
_L19:
        jsontoken = segment2.type(k);
        if(jsontoken == null) goto _L1; else goto _L3
_L3:
        if(flag2)
        {
            obj4 = segment2.findObjectId(k);
            if(obj4 != null)
                jsongenerator.writeObjectId(obj4);
            obj5 = segment2.findTypeId(k);
            if(obj5 != null)
                jsongenerator.writeTypeId(obj5);
        }
        _cls1..SwitchMap.com.fasterxml.jackson.core.JsonToken[jsontoken.ordinal()];
        JVM INSTR tableswitch 1 12: default 220
    //                   1 236
    //                   2 255
    //                   3 262
    //                   4 269
    //                   5 276
    //                   6 317
    //                   7 358
    //                   8 471
    //                   9 619
    //                   10 627
    //                   11 635
    //                   12 642;
           goto _L4 _L5 _L6 _L7 _L8 _L9 _L10 _L11 _L12 _L13 _L14 _L15 _L16
_L16:
        break MISSING_BLOCK_LABEL_642;
_L4:
        throw new RuntimeException("Internal error: should never end up through this code path");
_L5:
        jsongenerator.writeStartObject();
_L18:
        flag1 = flag2;
        i = k;
        segment1 = segment2;
          goto _L17
_L6:
        jsongenerator.writeEndObject();
          goto _L18
_L7:
        jsongenerator.writeStartArray();
          goto _L18
_L8:
        jsongenerator.writeEndArray();
          goto _L18
_L9:
        Object obj3 = segment2.get(k);
        if(obj3 instanceof SerializableString)
            jsongenerator.writeFieldName((SerializableString)obj3);
        else
            jsongenerator.writeFieldName((String)obj3);
          goto _L18
_L10:
        Object obj2 = segment2.get(k);
        if(obj2 instanceof SerializableString)
            jsongenerator.writeString((SerializableString)obj2);
        else
            jsongenerator.writeString((String)obj2);
          goto _L18
_L11:
        Object obj1 = segment2.get(k);
        if(obj1 instanceof Integer)
            jsongenerator.writeNumber(((Integer)obj1).intValue());
        else
        if(obj1 instanceof BigInteger)
            jsongenerator.writeNumber((BigInteger)obj1);
        else
        if(obj1 instanceof Long)
            jsongenerator.writeNumber(((Long)obj1).longValue());
        else
        if(obj1 instanceof Short)
            jsongenerator.writeNumber(((Short)obj1).shortValue());
        else
            jsongenerator.writeNumber(((Number)obj1).intValue());
          goto _L18
_L12:
        Object obj = segment2.get(k);
        if(obj instanceof Double)
            jsongenerator.writeNumber(((Double)obj).doubleValue());
        else
        if(obj instanceof BigDecimal)
            jsongenerator.writeNumber((BigDecimal)obj);
        else
        if(obj instanceof Float)
            jsongenerator.writeNumber(((Float)obj).floatValue());
        else
        if(obj == null)
            jsongenerator.writeNull();
        else
        if(obj instanceof String)
            jsongenerator.writeNumber((String)obj);
        else
            throw new JsonGenerationException((new StringBuilder()).append("Unrecognized value type for VALUE_NUMBER_FLOAT: ").append(obj.getClass().getName()).append(", can not serialize").toString());
          goto _L18
_L13:
        jsongenerator.writeBoolean(true);
          goto _L18
_L14:
        jsongenerator.writeBoolean(false);
          goto _L18
_L15:
        jsongenerator.writeNull();
          goto _L18
        jsongenerator.writeObject(segment2.get(k));
          goto _L18
        segment2 = segment1;
        k = j;
        flag2 = flag1;
          goto _L19
    }

    public JsonGenerator setCodec(ObjectCodec objectcodec)
    {
        _objectCodec = objectcodec;
        return this;
    }

    public JsonGenerator setFeatureMask(int i)
    {
        _generatorFeatures = i;
        return this;
    }

    public String toString()
    {
        int i;
        StringBuilder stringbuilder;
        JsonParser jsonparser;
        boolean flag;
        JsonToken jsontoken;
        i = 0;
        stringbuilder = new StringBuilder();
        stringbuilder.append("[TokenBuffer: ");
        jsonparser = asParser();
        if(_hasNativeTypeIds || _hasNativeObjectIds)
        {
            flag = true;
        } else
        {
            flag = false;
            i = 0;
        }
_L1:
        try
        {
            jsontoken = jsonparser.nextToken();
        }
        catch(IOException ioexception)
        {
            throw new IllegalStateException(ioexception);
        }
        if(jsontoken == null)
        {
            if(i >= 100)
                stringbuilder.append(" ... (truncated ").append(i - 100).append(" entries)");
            stringbuilder.append(']');
            return stringbuilder.toString();
        }
        if(!flag)
            break MISSING_BLOCK_LABEL_110;
        _appendNativeIds(stringbuilder);
        if(i >= 100)
            break MISSING_BLOCK_LABEL_170;
        if(i <= 0)
            break MISSING_BLOCK_LABEL_128;
        stringbuilder.append(", ");
        stringbuilder.append(jsontoken.toString());
        if(jsontoken == JsonToken.FIELD_NAME)
        {
            stringbuilder.append('(');
            stringbuilder.append(jsonparser.getCurrentName());
            stringbuilder.append(')');
        }
        i++;
          goto _L1
    }

    public JsonGenerator useDefaultPrettyPrinter()
    {
        return this;
    }

    public Version version()
    {
        return PackageVersion.VERSION;
    }

    public int writeBinary(Base64Variant base64variant, InputStream inputstream, int i)
    {
        throw new UnsupportedOperationException();
    }

    public void writeBinary(Base64Variant base64variant, byte abyte0[], int i, int j)
        throws IOException, JsonGenerationException
    {
        byte abyte1[] = new byte[j];
        System.arraycopy(abyte0, i, abyte1, 0, j);
        writeObject(abyte1);
    }

    public void writeBoolean(boolean flag)
        throws IOException, JsonGenerationException
    {
        JsonToken jsontoken;
        if(flag)
            jsontoken = JsonToken.VALUE_TRUE;
        else
            jsontoken = JsonToken.VALUE_FALSE;
        _append(jsontoken);
    }

    public final void writeEndArray()
        throws IOException, JsonGenerationException
    {
        _append(JsonToken.END_ARRAY);
        JsonWriteContext jsonwritecontext = _writeContext.getParent();
        if(jsonwritecontext != null)
            _writeContext = jsonwritecontext;
    }

    public final void writeEndObject()
        throws IOException, JsonGenerationException
    {
        _append(JsonToken.END_OBJECT);
        JsonWriteContext jsonwritecontext = _writeContext.getParent();
        if(jsonwritecontext != null)
            _writeContext = jsonwritecontext;
    }

    public void writeFieldName(SerializableString serializablestring)
        throws IOException, JsonGenerationException
    {
        _append(JsonToken.FIELD_NAME, serializablestring);
        _writeContext.writeFieldName(serializablestring.getValue());
    }

    public final void writeFieldName(String s)
        throws IOException, JsonGenerationException
    {
        _append(JsonToken.FIELD_NAME, s);
        _writeContext.writeFieldName(s);
    }

    public void writeNull()
        throws IOException, JsonGenerationException
    {
        _append(JsonToken.VALUE_NULL);
    }

    public void writeNumber(double d)
        throws IOException, JsonGenerationException
    {
        _append(JsonToken.VALUE_NUMBER_FLOAT, Double.valueOf(d));
    }

    public void writeNumber(float f)
        throws IOException, JsonGenerationException
    {
        _append(JsonToken.VALUE_NUMBER_FLOAT, Float.valueOf(f));
    }

    public void writeNumber(int i)
        throws IOException, JsonGenerationException
    {
        _append(JsonToken.VALUE_NUMBER_INT, Integer.valueOf(i));
    }

    public void writeNumber(long l)
        throws IOException, JsonGenerationException
    {
        _append(JsonToken.VALUE_NUMBER_INT, Long.valueOf(l));
    }

    public void writeNumber(String s)
        throws IOException, JsonGenerationException
    {
        _append(JsonToken.VALUE_NUMBER_FLOAT, s);
    }

    public void writeNumber(BigDecimal bigdecimal)
        throws IOException, JsonGenerationException
    {
        if(bigdecimal == null)
        {
            writeNull();
            return;
        } else
        {
            _append(JsonToken.VALUE_NUMBER_FLOAT, bigdecimal);
            return;
        }
    }

    public void writeNumber(BigInteger biginteger)
        throws IOException, JsonGenerationException
    {
        if(biginteger == null)
        {
            writeNull();
            return;
        } else
        {
            _append(JsonToken.VALUE_NUMBER_INT, biginteger);
            return;
        }
    }

    public void writeNumber(short word0)
        throws IOException, JsonGenerationException
    {
        _append(JsonToken.VALUE_NUMBER_INT, Short.valueOf(word0));
    }

    public void writeObject(Object obj)
        throws IOException, JsonProcessingException
    {
        _append(JsonToken.VALUE_EMBEDDED_OBJECT, obj);
    }

    public void writeObjectId(Object obj)
    {
        _objectId = obj;
        _hasNativeId = true;
    }

    public void writeRaw(char c)
        throws IOException, JsonGenerationException
    {
        _reportUnsupportedOperation();
    }

    public void writeRaw(SerializableString serializablestring)
        throws IOException, JsonGenerationException
    {
        _reportUnsupportedOperation();
    }

    public void writeRaw(String s)
        throws IOException, JsonGenerationException
    {
        _reportUnsupportedOperation();
    }

    public void writeRaw(String s, int i, int j)
        throws IOException, JsonGenerationException
    {
        _reportUnsupportedOperation();
    }

    public void writeRaw(char ac[], int i, int j)
        throws IOException, JsonGenerationException
    {
        _reportUnsupportedOperation();
    }

    public void writeRawUTF8String(byte abyte0[], int i, int j)
        throws IOException, JsonGenerationException
    {
        _reportUnsupportedOperation();
    }

    public void writeRawValue(String s)
        throws IOException, JsonGenerationException
    {
        _reportUnsupportedOperation();
    }

    public void writeRawValue(String s, int i, int j)
        throws IOException, JsonGenerationException
    {
        _reportUnsupportedOperation();
    }

    public void writeRawValue(char ac[], int i, int j)
        throws IOException, JsonGenerationException
    {
        _reportUnsupportedOperation();
    }

    public final void writeStartArray()
        throws IOException, JsonGenerationException
    {
        _append(JsonToken.START_ARRAY);
        _writeContext = _writeContext.createChildArrayContext();
    }

    public final void writeStartObject()
        throws IOException, JsonGenerationException
    {
        _append(JsonToken.START_OBJECT);
        _writeContext = _writeContext.createChildObjectContext();
    }

    public void writeString(SerializableString serializablestring)
        throws IOException, JsonGenerationException
    {
        if(serializablestring == null)
        {
            writeNull();
            return;
        } else
        {
            _append(JsonToken.VALUE_STRING, serializablestring);
            return;
        }
    }

    public void writeString(String s)
        throws IOException, JsonGenerationException
    {
        if(s == null)
        {
            writeNull();
            return;
        } else
        {
            _append(JsonToken.VALUE_STRING, s);
            return;
        }
    }

    public void writeString(char ac[], int i, int j)
        throws IOException, JsonGenerationException
    {
        writeString(new String(ac, i, j));
    }

    public void writeTree(TreeNode treenode)
        throws IOException, JsonProcessingException
    {
        _append(JsonToken.VALUE_EMBEDDED_OBJECT, treenode);
    }

    public void writeTypeId(Object obj)
    {
        _typeId = obj;
        _hasNativeId = true;
    }

    public void writeUTF8String(byte abyte0[], int i, int j)
        throws IOException, JsonGenerationException
    {
        _reportUnsupportedOperation();
    }

    protected static final int DEFAULT_GENERATOR_FEATURES = com.fasterxml.jackson.core.JsonGenerator.Feature.collectDefaults();
    protected int _appendOffset;
    protected boolean _closed;
    protected Segment _first;
    protected int _generatorFeatures;
    protected boolean _hasNativeId;
    protected boolean _hasNativeObjectIds;
    protected boolean _hasNativeTypeIds;
    protected Segment _last;
    protected boolean _mayHaveNativeIds;
    protected ObjectCodec _objectCodec;
    protected Object _objectId;
    protected Object _typeId;
    protected JsonWriteContext _writeContext;

}
