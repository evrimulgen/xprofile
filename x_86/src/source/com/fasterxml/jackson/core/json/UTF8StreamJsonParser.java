// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.fasterxml.jackson.core.json;

import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.core.base.ParserBase;
import com.fasterxml.jackson.core.io.CharTypes;
import com.fasterxml.jackson.core.io.IOContext;
import com.fasterxml.jackson.core.sym.BytesToNameCanonicalizer;
import com.fasterxml.jackson.core.sym.Name;
import com.fasterxml.jackson.core.util.ByteArrayBuilder;
import com.fasterxml.jackson.core.util.TextBuffer;
import java.io.*;
import java.util.Arrays;

// Referenced classes of package com.fasterxml.jackson.core.json:
//            JsonReadContext

public class UTF8StreamJsonParser extends ParserBase
{

    public UTF8StreamJsonParser(IOContext iocontext, int i, InputStream inputstream, ObjectCodec objectcodec, BytesToNameCanonicalizer bytestonamecanonicalizer, byte abyte0[], int j, 
            int k, boolean flag)
    {
        super(iocontext, i);
        _quadBuffer = new int[16];
        _tokenIncomplete = false;
        _inputStream = inputstream;
        _objectCodec = objectcodec;
        _symbols = bytestonamecanonicalizer;
        _inputBuffer = abyte0;
        _inputPtr = j;
        _inputEnd = k;
        _currInputRowStart = j;
        _currInputProcessed = -j;
        _bufferRecyclable = flag;
    }

    private final int _decodeUtf8_2(int i)
        throws IOException
    {
        if(_inputPtr >= _inputEnd)
            loadMoreGuaranteed();
        byte abyte0[] = _inputBuffer;
        int j = _inputPtr;
        _inputPtr = j + 1;
        byte byte0 = abyte0[j];
        if((byte0 & 0xc0) != 128)
            _reportInvalidOther(byte0 & 0xff, _inputPtr);
        return (i & 0x1f) << 6 | byte0 & 0x3f;
    }

    private final int _decodeUtf8_3(int i)
        throws IOException
    {
        if(_inputPtr >= _inputEnd)
            loadMoreGuaranteed();
        int j = i & 0xf;
        byte abyte0[] = _inputBuffer;
        int k = _inputPtr;
        _inputPtr = k + 1;
        byte byte0 = abyte0[k];
        if((byte0 & 0xc0) != 128)
            _reportInvalidOther(byte0 & 0xff, _inputPtr);
        int l = j << 6 | byte0 & 0x3f;
        if(_inputPtr >= _inputEnd)
            loadMoreGuaranteed();
        byte abyte1[] = _inputBuffer;
        int i1 = _inputPtr;
        _inputPtr = i1 + 1;
        byte byte1 = abyte1[i1];
        if((byte1 & 0xc0) != 128)
            _reportInvalidOther(byte1 & 0xff, _inputPtr);
        return l << 6 | byte1 & 0x3f;
    }

    private final int _decodeUtf8_3fast(int i)
        throws IOException
    {
        int j = i & 0xf;
        byte abyte0[] = _inputBuffer;
        int k = _inputPtr;
        _inputPtr = k + 1;
        byte byte0 = abyte0[k];
        if((byte0 & 0xc0) != 128)
            _reportInvalidOther(byte0 & 0xff, _inputPtr);
        int l = j << 6 | byte0 & 0x3f;
        byte abyte1[] = _inputBuffer;
        int i1 = _inputPtr;
        _inputPtr = i1 + 1;
        byte byte1 = abyte1[i1];
        if((byte1 & 0xc0) != 128)
            _reportInvalidOther(byte1 & 0xff, _inputPtr);
        return l << 6 | byte1 & 0x3f;
    }

    private final int _decodeUtf8_4(int i)
        throws IOException
    {
        if(_inputPtr >= _inputEnd)
            loadMoreGuaranteed();
        byte abyte0[] = _inputBuffer;
        int j = _inputPtr;
        _inputPtr = j + 1;
        byte byte0 = abyte0[j];
        if((byte0 & 0xc0) != 128)
            _reportInvalidOther(byte0 & 0xff, _inputPtr);
        int k = (i & 7) << 6 | byte0 & 0x3f;
        if(_inputPtr >= _inputEnd)
            loadMoreGuaranteed();
        byte abyte1[] = _inputBuffer;
        int l = _inputPtr;
        _inputPtr = l + 1;
        byte byte1 = abyte1[l];
        if((byte1 & 0xc0) != 128)
            _reportInvalidOther(byte1 & 0xff, _inputPtr);
        int i1 = k << 6 | byte1 & 0x3f;
        if(_inputPtr >= _inputEnd)
            loadMoreGuaranteed();
        byte abyte2[] = _inputBuffer;
        int j1 = _inputPtr;
        _inputPtr = j1 + 1;
        byte byte2 = abyte2[j1];
        if((byte2 & 0xc0) != 128)
            _reportInvalidOther(byte2 & 0xff, _inputPtr);
        return (i1 << 6 | byte2 & 0x3f) - 0x10000;
    }

    private final void _finishString2(char ac[], int i)
        throws IOException
    {
        int ai[];
        byte abyte0[];
        ai = _icUTF8;
        abyte0 = _inputBuffer;
_L12:
        int j;
        int k;
        j = _inputPtr;
        if(j >= _inputEnd)
        {
            loadMoreGuaranteed();
            j = _inputPtr;
        }
        if(i >= ac.length)
        {
            ac = _textBuffer.finishCurrentSegment();
            i = 0;
        }
        k = Math.min(_inputEnd, j + (ac.length - i));
_L5:
        int l;
        int i1;
        if(j >= k)
            break; /* Loop/switch isn't completed */
        l = j + 1;
        i1 = 0xff & abyte0[j];
        if(ai[i1] == 0) goto _L2; else goto _L1
_L1:
        _inputPtr = l;
        if(i1 == 34)
        {
            _textBuffer.setCurrentLength(i);
            return;
        }
          goto _L3
_L2:
        int j1 = i + 1;
        ac[i] = (char)i1;
        j = l;
        i = j1;
        if(true) goto _L5; else goto _L4
_L4:
        _inputPtr = j;
          goto _L6
_L3:
        ai[i1];
        JVM INSTR tableswitch 1 4: default 184
    //                   1 231
    //                   2 240
    //                   3 251
    //                   4 286;
           goto _L7 _L8 _L9 _L10 _L11
_L7:
        int j2;
        if(i1 < 32)
            _throwUnquotedSpace(i1, "string value");
        else
            _reportInvalidChar(i1);
        if(i >= ac.length)
        {
            ac = _textBuffer.finishCurrentSegment();
            j2 = 0;
        } else
        {
            j2 = i;
        }
        i = j2 + 1;
        ac[j2] = (char)i1;
_L6:
        if(true) goto _L12; else goto _L8
_L8:
        i1 = _decodeEscaped();
        break MISSING_BLOCK_LABEL_199;
_L9:
        i1 = _decodeUtf8_2(i1);
        break MISSING_BLOCK_LABEL_199;
_L10:
        if(_inputEnd - _inputPtr >= 2)
            i1 = _decodeUtf8_3fast(i1);
        else
            i1 = _decodeUtf8_3(i1);
        break MISSING_BLOCK_LABEL_199;
_L11:
        int k1 = _decodeUtf8_4(i1);
        int l1 = i + 1;
        ac[i] = (char)(0xd800 | k1 >> 10);
        if(l1 >= ac.length)
        {
            ac = _textBuffer.finishCurrentSegment();
            l1 = 0;
        }
        int i2 = 0xdc00 | k1 & 0x3ff;
        i = l1;
        i1 = i2;
        break MISSING_BLOCK_LABEL_199;
    }

    private final boolean _isNextTokenNameMaybe(int i, SerializableString serializablestring)
        throws IOException, JsonParseException
    {
        boolean flag;
        int k;
        String s = _parseName(i).getName();
        _parsingContext.setCurrentName(s);
        flag = s.equals(serializablestring.getValue());
        _currToken = JsonToken.FIELD_NAME;
        int j = _skipWS();
        if(j != 58)
            _reportUnexpectedChar(j, "was expecting a colon to separate field name and value");
        k = _skipWS();
        if(k == 34)
        {
            _tokenIncomplete = true;
            _nextToken = JsonToken.VALUE_STRING;
            return flag;
        }
        k;
        JVM INSTR lookupswitch 18: default 240
    //                   45: 326
    //                   48: 326
    //                   49: 326
    //                   50: 326
    //                   51: 326
    //                   52: 326
    //                   53: 326
    //                   54: 326
    //                   55: 326
    //                   56: 326
    //                   57: 326
    //                   91: 257
    //                   93: 273
    //                   102: 296
    //                   110: 311
    //                   116: 281
    //                   123: 265
    //                   125: 273;
           goto _L1 _L2 _L2 _L2 _L2 _L2 _L2 _L2 _L2 _L2 _L2 _L2 _L3 _L4 _L5 _L6 _L7 _L8 _L4
_L1:
        JsonToken jsontoken = _handleUnexpectedValue(k);
_L10:
        _nextToken = jsontoken;
        return flag;
_L3:
        jsontoken = JsonToken.START_ARRAY;
        continue; /* Loop/switch isn't completed */
_L8:
        jsontoken = JsonToken.START_OBJECT;
        continue; /* Loop/switch isn't completed */
_L4:
        _reportUnexpectedChar(k, "expected a value");
_L7:
        _matchToken("true", 1);
        jsontoken = JsonToken.VALUE_TRUE;
        continue; /* Loop/switch isn't completed */
_L5:
        _matchToken("false", 1);
        jsontoken = JsonToken.VALUE_FALSE;
        continue; /* Loop/switch isn't completed */
_L6:
        _matchToken("null", 1);
        jsontoken = JsonToken.VALUE_NULL;
        continue; /* Loop/switch isn't completed */
_L2:
        jsontoken = _parseNumber(k);
        if(true) goto _L10; else goto _L9
_L9:
    }

    private final void _isNextTokenNameYes()
        throws IOException, JsonParseException
    {
        int i;
        if(_inputPtr < -1 + _inputEnd && _inputBuffer[_inputPtr] == 58)
        {
            byte abyte0[] = _inputBuffer;
            int j = 1 + _inputPtr;
            _inputPtr = j;
            byte byte0 = abyte0[j];
            _inputPtr = 1 + _inputPtr;
            if(byte0 == 34)
            {
                _tokenIncomplete = true;
                _nextToken = JsonToken.VALUE_STRING;
                return;
            }
            if(byte0 == 123)
            {
                _nextToken = JsonToken.START_OBJECT;
                return;
            }
            if(byte0 == 91)
            {
                _nextToken = JsonToken.START_ARRAY;
                return;
            }
            i = byte0 & 0xff;
            if(i <= 32 || i == 47)
            {
                _inputPtr = -1 + _inputPtr;
                i = _skipWS();
            }
        } else
        {
            i = _skipColon();
        }
        switch(i)
        {
        default:
            _nextToken = _handleUnexpectedValue(i);
            return;

        case 34: // '"'
            _tokenIncomplete = true;
            _nextToken = JsonToken.VALUE_STRING;
            return;

        case 91: // '['
            _nextToken = JsonToken.START_ARRAY;
            return;

        case 123: // '{'
            _nextToken = JsonToken.START_OBJECT;
            return;

        case 93: // ']'
        case 125: // '}'
            _reportUnexpectedChar(i, "expected a value");
            // fall through

        case 116: // 't'
            _matchToken("true", 1);
            _nextToken = JsonToken.VALUE_TRUE;
            return;

        case 102: // 'f'
            _matchToken("false", 1);
            _nextToken = JsonToken.VALUE_FALSE;
            return;

        case 110: // 'n'
            _matchToken("null", 1);
            _nextToken = JsonToken.VALUE_NULL;
            return;

        case 45: // '-'
        case 48: // '0'
        case 49: // '1'
        case 50: // '2'
        case 51: // '3'
        case 52: // '4'
        case 53: // '5'
        case 54: // '6'
        case 55: // '7'
        case 56: // '8'
        case 57: // '9'
            _nextToken = _parseNumber(i);
            return;
        }
    }

    private final JsonToken _nextAfterName()
    {
        JsonToken jsontoken;
        _nameCopied = false;
        jsontoken = _nextToken;
        _nextToken = null;
        if(jsontoken != JsonToken.START_ARRAY) goto _L2; else goto _L1
_L1:
        _parsingContext = _parsingContext.createChildArrayContext(_tokenInputRow, _tokenInputCol);
_L4:
        _currToken = jsontoken;
        return jsontoken;
_L2:
        if(jsontoken == JsonToken.START_OBJECT)
            _parsingContext = _parsingContext.createChildObjectContext(_tokenInputRow, _tokenInputCol);
        if(true) goto _L4; else goto _L3
_L3:
    }

    private final JsonToken _nextTokenNotInObject(int i)
        throws IOException, JsonParseException
    {
        if(i == 34)
        {
            _tokenIncomplete = true;
            JsonToken jsontoken7 = JsonToken.VALUE_STRING;
            _currToken = jsontoken7;
            return jsontoken7;
        }
        switch(i)
        {
        default:
            JsonToken jsontoken6 = _handleUnexpectedValue(i);
            _currToken = jsontoken6;
            return jsontoken6;

        case 91: // '['
            _parsingContext = _parsingContext.createChildArrayContext(_tokenInputRow, _tokenInputCol);
            JsonToken jsontoken5 = JsonToken.START_ARRAY;
            _currToken = jsontoken5;
            return jsontoken5;

        case 123: // '{'
            _parsingContext = _parsingContext.createChildObjectContext(_tokenInputRow, _tokenInputCol);
            JsonToken jsontoken4 = JsonToken.START_OBJECT;
            _currToken = jsontoken4;
            return jsontoken4;

        case 93: // ']'
        case 125: // '}'
            _reportUnexpectedChar(i, "expected a value");
            // fall through

        case 116: // 't'
            _matchToken("true", 1);
            JsonToken jsontoken3 = JsonToken.VALUE_TRUE;
            _currToken = jsontoken3;
            return jsontoken3;

        case 102: // 'f'
            _matchToken("false", 1);
            JsonToken jsontoken2 = JsonToken.VALUE_FALSE;
            _currToken = jsontoken2;
            return jsontoken2;

        case 110: // 'n'
            _matchToken("null", 1);
            JsonToken jsontoken1 = JsonToken.VALUE_NULL;
            _currToken = jsontoken1;
            return jsontoken1;

        case 45: // '-'
        case 48: // '0'
        case 49: // '1'
        case 50: // '2'
        case 51: // '3'
        case 52: // '4'
        case 53: // '5'
        case 54: // '6'
        case 55: // '7'
        case 56: // '8'
        case 57: // '9'
            JsonToken jsontoken = _parseNumber(i);
            _currToken = jsontoken;
            return jsontoken;
        }
    }

    private final JsonToken _parseFloat(char ac[], int i, int j, boolean flag, int k)
        throws IOException, JsonParseException
    {
        int l;
        int j5;
        l = 0;
        if(j != 46)
            break MISSING_BLOCK_LABEL_609;
        j5 = i + 1;
        ac[i] = (char)j;
_L5:
        int i1;
        char ac1[];
        int j1;
        boolean flag1;
        int k1;
        int l1;
        int i2;
        byte abyte0[];
        int j2;
        int k2;
        int l2;
        int i3;
        byte abyte1[];
        int j3;
        int k3;
        int l3;
        boolean flag2;
        int j4;
        int k4;
        int l4;
        if(_inputPtr >= _inputEnd && !loadMore())
        {
            flag1 = true;
            i1 = j;
        } else
        {
            byte abyte3[] = _inputBuffer;
            int k5 = _inputPtr;
            _inputPtr = k5 + 1;
            j = 0xff & abyte3[k5];
            int i4;
            byte abyte2[];
            int i5;
            int l5;
            if(j >= 48)
            {
label0:
                {
                    if(j <= 57)
                        break label0;
                    i1 = j;
                    flag1 = false;
                }
            } else
            {
                i1 = j;
                flag1 = false;
            }
        }
        if(l == 0)
            reportUnexpectedNumberChar(i1, "Decimal point not followed by a digit");
        k1 = l;
        j1 = j5;
        ac1 = ac;
_L9:
        l1 = 0;
        if(i1 != 101 && i1 != 69)
            break MISSING_BLOCK_LABEL_575;
        if(j1 >= ac1.length)
        {
            ac1 = _textBuffer.finishCurrentSegment();
            j1 = 0;
        }
        i2 = j1 + 1;
        ac1[j1] = (char)i1;
        if(_inputPtr >= _inputEnd)
            loadMoreGuaranteed();
        abyte0 = _inputBuffer;
        j2 = _inputPtr;
        _inputPtr = j2 + 1;
        k2 = 0xff & abyte0[j2];
        if(k2 == 45 || k2 == 43)
        {
            if(i2 >= ac1.length)
            {
                ac1 = _textBuffer.finishCurrentSegment();
                l2 = 0;
            } else
            {
                l2 = i2;
            }
            i3 = l2 + 1;
            ac1[l2] = (char)k2;
            if(_inputPtr >= _inputEnd)
                loadMoreGuaranteed();
            abyte1 = _inputBuffer;
            j3 = _inputPtr;
            _inputPtr = j3 + 1;
            k3 = 0xff & abyte1[j3];
            l3 = i3;
        } else
        {
            l3 = i2;
            k3 = k2;
            l1 = 0;
        }
_L6:
        if(k3 > 57 || k3 < 48) goto _L2; else goto _L1
_L1:
        l1++;
        if(l3 >= ac1.length)
        {
            ac1 = _textBuffer.finishCurrentSegment();
            l3 = 0;
        }
        l4 = l3 + 1;
        ac1[l3] = (char)k3;
        if(_inputPtr < _inputEnd || loadMore()) goto _L4; else goto _L3
_L3:
        k4 = l1;
        flag2 = true;
        j4 = l4;
_L7:
        if(k4 == 0)
            reportUnexpectedNumberChar(k3, "Exponent indicator not followed by a digit");
_L8:
        if(!flag2)
        {
            _inputPtr = -1 + _inputPtr;
            if(_parsingContext.inRoot())
                _verifyRootSpace(k3);
        }
        _textBuffer.setCurrentLength(j4);
        return resetFloat(flag, k, k1, k4);
        l++;
        if(j5 >= ac.length)
        {
            ac = _textBuffer.finishCurrentSegment();
            l5 = 0;
        } else
        {
            l5 = j5;
        }
        j5 = l5 + 1;
        ac[l5] = (char)j;
          goto _L5
_L4:
        abyte2 = _inputBuffer;
        i5 = _inputPtr;
        _inputPtr = i5 + 1;
        k3 = 0xff & abyte2[i5];
        l3 = l4;
          goto _L6
_L2:
        flag2 = flag1;
        i4 = l1;
        j4 = l3;
        k4 = i4;
          goto _L7
        flag2 = flag1;
        k3 = i1;
        j4 = j1;
        k4 = 0;
          goto _L8
        i1 = j;
        ac1 = ac;
        j1 = i;
        flag1 = false;
        k1 = 0;
          goto _L9
    }

    private final JsonToken _parserNumber2(char ac[], int i, boolean flag, int j)
        throws IOException, JsonParseException
    {
        int k;
        int l;
        char ac1[];
        k = j;
        l = i;
        ac1 = ac;
_L4:
        int j1;
        if(_inputPtr >= _inputEnd && !loadMore())
        {
            _textBuffer.setCurrentLength(l);
            return resetInt(flag, k);
        }
        byte abyte0[] = _inputBuffer;
        int i1 = _inputPtr;
        _inputPtr = i1 + 1;
        j1 = 0xff & abyte0[i1];
        if(j1 <= 57 && j1 >= 48) goto _L2; else goto _L1
_L1:
        if(j1 == 46 || j1 == 101 || j1 == 69)
            return _parseFloat(ac1, l, j1, flag, k);
          goto _L3
_L2:
        byte abyte1[];
        int k1;
        int l1;
        if(l >= ac1.length)
        {
            ac1 = _textBuffer.finishCurrentSegment();
            l1 = 0;
        } else
        {
            l1 = l;
        }
        l = l1 + 1;
        ac1[l1] = (char)j1;
        k++;
        if(true) goto _L4; else goto _L3
_L3:
        _inputPtr = -1 + _inputPtr;
        _textBuffer.setCurrentLength(l);
        if(_parsingContext.inRoot())
        {
            abyte1 = _inputBuffer;
            k1 = _inputPtr;
            _inputPtr = k1 + 1;
            _verifyRootSpace(0xff & abyte1[k1]);
        }
        return resetInt(flag, k);
    }

    private final void _skipCComment()
        throws IOException
    {
        int ai[] = CharTypes.getInputCodeComment();
_L12:
        if(_inputPtr >= _inputEnd && !loadMore()) goto _L2; else goto _L1
_L1:
        int j;
        int k;
        byte abyte0[] = _inputBuffer;
        int i = _inputPtr;
        _inputPtr = i + 1;
        j = 0xff & abyte0[i];
        k = ai[j];
        if(k == 0)
            continue; /* Loop/switch isn't completed */
        k;
        JVM INSTR lookupswitch 6: default 120
    //                   2: 208
    //                   3: 217
    //                   4: 226
    //                   10: 180
    //                   13: 201
    //                   42: 129;
           goto _L3 _L4 _L5 _L6 _L7 _L8 _L9
_L3:
        _reportInvalidChar(j);
        continue; /* Loop/switch isn't completed */
_L9:
        if(_inputPtr < _inputEnd || loadMore()) goto _L10; else goto _L2
_L2:
        _reportInvalidEOF(" in a comment");
        return;
_L10:
        if(_inputBuffer[_inputPtr] == 47)
        {
            _inputPtr = 1 + _inputPtr;
            return;
        }
        continue; /* Loop/switch isn't completed */
_L7:
        _currInputRow = 1 + _currInputRow;
        _currInputRowStart = _inputPtr;
        continue; /* Loop/switch isn't completed */
_L8:
        _skipCR();
        continue; /* Loop/switch isn't completed */
_L4:
        _skipUtf8_2(j);
        continue; /* Loop/switch isn't completed */
_L5:
        _skipUtf8_3(j);
        continue; /* Loop/switch isn't completed */
_L6:
        _skipUtf8_4(j);
        if(true) goto _L12; else goto _L11
_L11:
    }

    private final int _skipColon()
        throws IOException
    {
        byte byte0;
        if(_inputPtr >= _inputEnd)
            loadMoreGuaranteed();
        byte abyte0[] = _inputBuffer;
        int i = _inputPtr;
        _inputPtr = i + 1;
        byte0 = abyte0[i];
        if(byte0 != 58) goto _L2; else goto _L1
_L1:
        if(_inputPtr >= _inputEnd) goto _L4; else goto _L3
_L3:
        int i1 = 0xff & _inputBuffer[_inputPtr];
        if(i1 <= 32 || i1 == 47) goto _L4; else goto _L5
_L5:
        _inputPtr = 1 + _inputPtr;
_L14:
        return i1;
_L2:
        int j = byte0 & 0xff;
_L15:
        j;
        JVM INSTR lookupswitch 5: default 156
    //                   9: 258
    //                   10: 307
    //                   13: 254
    //                   32: 258
    //                   47: 328;
           goto _L6 _L7 _L8 _L9 _L7 _L10
_L6:
        if(j < 32)
            _throwInvalidSpace(j);
        if(j != 58)
            _reportUnexpectedChar(j, "was expecting a colon to separate field name and value");
_L4:
        if(_inputPtr >= _inputEnd && !loadMore())
            break MISSING_BLOCK_LABEL_400;
        byte abyte2[] = _inputBuffer;
        int l = _inputPtr;
        _inputPtr = l + 1;
        i1 = 0xff & abyte2[l];
        if(i1 <= 32) goto _L12; else goto _L11
_L11:
        if(i1 != 47) goto _L14; else goto _L13
_L13:
        _skipComment();
          goto _L4
_L9:
        _skipCR();
_L7:
        if(_inputPtr >= _inputEnd)
            loadMoreGuaranteed();
        byte abyte1[] = _inputBuffer;
        int k = _inputPtr;
        _inputPtr = k + 1;
        j = 0xff & abyte1[k];
          goto _L15
_L8:
        _currInputRow = 1 + _currInputRow;
        _currInputRowStart = _inputPtr;
          goto _L7
_L10:
        _skipComment();
          goto _L7
_L12:
        if(i1 != 32)
            if(i1 == 10)
            {
                _currInputRow = 1 + _currInputRow;
                _currInputRowStart = _inputPtr;
            } else
            if(i1 == 13)
                _skipCR();
            else
            if(i1 != 9)
                _throwInvalidSpace(i1);
          goto _L4
        throw _constructError((new StringBuilder()).append("Unexpected end-of-input within/between ").append(_parsingContext.getTypeDesc()).append(" entries").toString());
          goto _L15
    }

    private final void _skipComment()
        throws IOException
    {
        if(!isEnabled(com.fasterxml.jackson.core.JsonParser.Feature.ALLOW_COMMENTS))
            _reportUnexpectedChar(47, "maybe a (non-standard) comment? (not recognized as one since Feature 'ALLOW_COMMENTS' not enabled for parser)");
        if(_inputPtr >= _inputEnd && !loadMore())
            _reportInvalidEOF(" in a comment");
        byte abyte0[] = _inputBuffer;
        int i = _inputPtr;
        _inputPtr = i + 1;
        int j = 0xff & abyte0[i];
        if(j == 47)
        {
            _skipLine();
            return;
        }
        if(j == 42)
        {
            _skipCComment();
            return;
        } else
        {
            _reportUnexpectedChar(j, "was expecting either '*' or '/' for a comment");
            return;
        }
    }

    private final void _skipLine()
        throws IOException
    {
        int ai[] = CharTypes.getInputCodeComment();
        do
        {
            int j;
label0:
            {
label1:
                {
label2:
                    {
label3:
                        {
                            if(_inputPtr < _inputEnd || loadMore())
                            {
                                byte abyte0[] = _inputBuffer;
                                int i = _inputPtr;
                                _inputPtr = i + 1;
                                j = 0xff & abyte0[i];
                                int k = ai[j];
                                if(k == 0)
                                    continue;
                                switch(k)
                                {
                                default:
                                    if(k < 0)
                                        _reportInvalidChar(j);
                                    continue;

                                case 2: // '\002'
                                    break label2;

                                case 3: // '\003'
                                    break label1;

                                case 4: // '\004'
                                    break label0;

                                case 13: // '\r'
                                    break label3;

                                case 42: // '*'
                                    continue;

                                case 10: // '\n'
                                    _currInputRow = 1 + _currInputRow;
                                    _currInputRowStart = _inputPtr;
                                    break;
                                }
                            }
                            return;
                        }
                        _skipCR();
                        return;
                    }
                    _skipUtf8_2(j);
                    continue;
                }
                _skipUtf8_3(j);
                continue;
            }
            _skipUtf8_4(j);
        } while(true);
    }

    private final void _skipUtf8_2(int i)
        throws IOException
    {
        if(_inputPtr >= _inputEnd)
            loadMoreGuaranteed();
        byte abyte0[] = _inputBuffer;
        int j = _inputPtr;
        _inputPtr = j + 1;
        byte byte0 = abyte0[j];
        if((byte0 & 0xc0) != 128)
            _reportInvalidOther(byte0 & 0xff, _inputPtr);
    }

    private final void _skipUtf8_3(int i)
        throws IOException
    {
        if(_inputPtr >= _inputEnd)
            loadMoreGuaranteed();
        byte abyte0[] = _inputBuffer;
        int j = _inputPtr;
        _inputPtr = j + 1;
        byte byte0 = abyte0[j];
        if((byte0 & 0xc0) != 128)
            _reportInvalidOther(byte0 & 0xff, _inputPtr);
        if(_inputPtr >= _inputEnd)
            loadMoreGuaranteed();
        byte abyte1[] = _inputBuffer;
        int k = _inputPtr;
        _inputPtr = k + 1;
        byte byte1 = abyte1[k];
        if((byte1 & 0xc0) != 128)
            _reportInvalidOther(byte1 & 0xff, _inputPtr);
    }

    private final void _skipUtf8_4(int i)
        throws IOException
    {
        if(_inputPtr >= _inputEnd)
            loadMoreGuaranteed();
        byte abyte0[] = _inputBuffer;
        int j = _inputPtr;
        _inputPtr = j + 1;
        byte byte0 = abyte0[j];
        if((byte0 & 0xc0) != 128)
            _reportInvalidOther(byte0 & 0xff, _inputPtr);
        if(_inputPtr >= _inputEnd)
            loadMoreGuaranteed();
        byte abyte1[] = _inputBuffer;
        int k = _inputPtr;
        _inputPtr = k + 1;
        byte byte1 = abyte1[k];
        if((byte1 & 0xc0) != 128)
            _reportInvalidOther(byte1 & 0xff, _inputPtr);
        if(_inputPtr >= _inputEnd)
            loadMoreGuaranteed();
        byte abyte2[] = _inputBuffer;
        int l = _inputPtr;
        _inputPtr = l + 1;
        byte byte2 = abyte2[l];
        if((byte2 & 0xc0) != 128)
            _reportInvalidOther(byte2 & 0xff, _inputPtr);
    }

    private final int _skipWS()
        throws IOException
    {
        int ai[] = _icWS;
_L13:
        int j;
        if(_inputPtr >= _inputEnd && !loadMore())
            break; /* Loop/switch isn't completed */
        byte abyte0[] = _inputBuffer;
        int i = _inputPtr;
        _inputPtr = i + 1;
        j = 0xff & abyte0[i];
        ai[j];
        JVM INSTR lookupswitch 9: default 136
    //                   0: 227
    //                   1: 4
    //                   2: 158
    //                   3: 167
    //                   4: 176
    //                   10: 185
    //                   13: 206
    //                   35: 220
    //                   47: 213;
           goto _L1 _L2 _L3 _L4 _L5 _L6 _L7 _L8 _L9 _L10
_L3:
        break; /* Loop/switch isn't completed */
_L1:
        if(j < 32)
            _throwInvalidSpace(j);
        _reportInvalidChar(j);
        break; /* Loop/switch isn't completed */
_L4:
        _skipUtf8_2(j);
        break; /* Loop/switch isn't completed */
_L5:
        _skipUtf8_3(j);
        break; /* Loop/switch isn't completed */
_L6:
        _skipUtf8_4(j);
        break; /* Loop/switch isn't completed */
_L7:
        _currInputRow = 1 + _currInputRow;
        _currInputRowStart = _inputPtr;
        break; /* Loop/switch isn't completed */
_L8:
        _skipCR();
        break; /* Loop/switch isn't completed */
_L10:
        _skipComment();
        break; /* Loop/switch isn't completed */
_L9:
        if(_skipYAMLComment()) goto _L11; else goto _L2
_L11:
        if(true) goto _L13; else goto _L12
_L2:
        return j;
_L12:
        throw _constructError((new StringBuilder()).append("Unexpected end-of-input within/between ").append(_parsingContext.getTypeDesc()).append(" entries").toString());
    }

    private final int _skipWSOrEnd()
        throws IOException
    {
        int ai[] = _icWS;
_L13:
        int j;
        if(_inputPtr >= _inputEnd && !loadMore())
            break; /* Loop/switch isn't completed */
        byte abyte0[] = _inputBuffer;
        int i = _inputPtr;
        _inputPtr = i + 1;
        j = 0xff & abyte0[i];
        ai[j];
        JVM INSTR lookupswitch 9: default 136
    //                   0: 214
    //                   1: 4
    //                   2: 145
    //                   3: 154
    //                   4: 163
    //                   10: 172
    //                   13: 193
    //                   35: 207
    //                   47: 200;
           goto _L1 _L2 _L3 _L4 _L5 _L6 _L7 _L8 _L9 _L10
_L3:
        break; /* Loop/switch isn't completed */
_L1:
        _reportInvalidChar(j);
        break; /* Loop/switch isn't completed */
_L4:
        _skipUtf8_2(j);
        break; /* Loop/switch isn't completed */
_L5:
        _skipUtf8_3(j);
        break; /* Loop/switch isn't completed */
_L6:
        _skipUtf8_4(j);
        break; /* Loop/switch isn't completed */
_L7:
        _currInputRow = 1 + _currInputRow;
        _currInputRowStart = _inputPtr;
        break; /* Loop/switch isn't completed */
_L8:
        _skipCR();
        break; /* Loop/switch isn't completed */
_L10:
        _skipComment();
        break; /* Loop/switch isn't completed */
_L9:
        if(_skipYAMLComment()) goto _L11; else goto _L2
_L11:
        if(true) goto _L13; else goto _L12
_L2:
        return j;
_L12:
        _handleEOF();
        return -1;
    }

    private final boolean _skipYAMLComment()
        throws IOException
    {
        if(!isEnabled(com.fasterxml.jackson.core.JsonParser.Feature.ALLOW_YAML_COMMENTS))
        {
            return false;
        } else
        {
            _skipLine();
            return true;
        }
    }

    private final int _verifyNoLeadingZeroes()
        throws IOException, JsonParseException
    {
        if(_inputPtr < _inputEnd || loadMore()) goto _L2; else goto _L1
_L1:
        int i = 48;
_L4:
        return i;
_L2:
        i = 0xff & _inputBuffer[_inputPtr];
        if(i < 48 || i > 57)
            return 48;
        if(!isEnabled(com.fasterxml.jackson.core.JsonParser.Feature.ALLOW_NUMERIC_LEADING_ZEROS))
            reportInvalidNumber("Leading zeroes not allowed");
        _inputPtr = 1 + _inputPtr;
        if(i != 48)
            continue; /* Loop/switch isn't completed */
        do
        {
            if(_inputPtr >= _inputEnd && !loadMore())
                continue; /* Loop/switch isn't completed */
            i = 0xff & _inputBuffer[_inputPtr];
            if(i < 48 || i > 57)
                return 48;
            _inputPtr = 1 + _inputPtr;
        } while(i == 48);
        break; /* Loop/switch isn't completed */
        if(true) goto _L4; else goto _L3
_L3:
        return i;
    }

    private final void _verifyRootSpace(int i)
        throws IOException
    {
        _inputPtr = 1 + _inputPtr;
        switch(i)
        {
        default:
            _reportMissingRootWS(i);
            // fall through

        case 9: // '\t'
        case 32: // ' '
            return;

        case 13: // '\r'
            _skipCR();
            return;

        case 10: // '\n'
            _currInputRow = 1 + _currInputRow;
            _currInputRowStart = _inputPtr;
            return;
        }
    }

    private final Name addName(int ai[], int i, int j)
        throws JsonParseException
    {
        int l;
        char ac[];
        int i1;
        int k1;
        int l1;
        char ac1[];
        int i2;
        int j2;
        int k2;
        int l2;
        int i3;
        int k = j + (-4 + (i << 2));
        int j1;
        int j3;
        int k3;
        int l3;
        int i4;
        int j4;
        int k4;
        if(j < 4)
        {
            l = ai[i - 1];
            ai[i - 1] = l << (4 - j << 3);
        } else
        {
            l = 0;
        }
        ac = _textBuffer.emptyAndGetCurrentSegment();
        i1 = 0;
        j1 = 0;
        if(j1 >= k)
            break; /* Loop/switch isn't completed */
        k1 = 0xff & ai[j1 >> 2] >> (3 - (j1 & 3) << 3);
        l1 = j1 + 1;
        if(k1 <= 127)
            break MISSING_BLOCK_LABEL_528;
        if((k1 & 0xe0) == 192)
        {
            l2 = k1 & 0x1f;
            i3 = 1;
        } else
        if((k1 & 0xf0) == 224)
        {
            l2 = k1 & 0xf;
            i3 = 2;
        } else
        if((k1 & 0xf8) == 240)
        {
            l2 = k1 & 7;
            i3 = 3;
        } else
        {
            _reportInvalidInitial(k1);
            l2 = 1;
            i3 = l2;
        }
        if(l1 + i3 > k)
            _reportInvalidEOF(" in field name");
        j3 = ai[l1 >> 2] >> (3 - (l1 & 3) << 3);
        l1++;
        if((j3 & 0xc0) != 128)
            _reportInvalidOther(j3);
        k1 = l2 << 6 | j3 & 0x3f;
        if(i3 > 1)
        {
            j4 = ai[l1 >> 2] >> (3 - (l1 & 3) << 3);
            l1++;
            if((j4 & 0xc0) != 128)
                _reportInvalidOther(j4);
            k1 = k1 << 6 | j4 & 0x3f;
            if(i3 > 2)
            {
                k4 = ai[l1 >> 2] >> (3 - (l1 & 3) << 3);
                l1++;
                if((k4 & 0xc0) != 128)
                    _reportInvalidOther(k4 & 0xff);
                k1 = k1 << 6 | k4 & 0x3f;
            }
        }
        if(i3 <= 2)
            break MISSING_BLOCK_LABEL_528;
        k3 = k1 - 0x10000;
        if(i1 >= ac.length)
            ac = _textBuffer.expandCurrentSegment();
        l3 = i1 + 1;
        ac[i1] = (char)(55296 + (k3 >> 10));
        i4 = 0xdc00 | k3 & 0x3ff;
        j2 = l1;
        k2 = l3;
        ac1 = ac;
        i2 = i4;
_L4:
        if(k2 >= ac1.length)
            ac1 = _textBuffer.expandCurrentSegment();
        i1 = k2 + 1;
        ac1[k2] = (char)i2;
        j1 = j2;
        ac = ac1;
        if(true) goto _L2; else goto _L1
_L2:
        break MISSING_BLOCK_LABEL_50;
_L1:
        String s = new String(ac, 0, i1);
        if(j < 4)
            ai[i - 1] = l;
        return _symbols.addName(s, ai, i);
        ac1 = ac;
        i2 = k1;
        j2 = l1;
        k2 = i1;
        if(true) goto _L4; else goto _L3
_L3:
    }

    private final Name findName(int i, int j)
        throws JsonParseException
    {
        Name name = _symbols.findName(i);
        if(name != null)
        {
            return name;
        } else
        {
            _quadBuffer[0] = i;
            return addName(_quadBuffer, 1, j);
        }
    }

    private final Name findName(int i, int j, int k)
        throws JsonParseException
    {
        Name name = _symbols.findName(i, j);
        if(name != null)
        {
            return name;
        } else
        {
            _quadBuffer[0] = i;
            _quadBuffer[1] = j;
            return addName(_quadBuffer, 2, k);
        }
    }

    private final Name findName(int ai[], int i, int j, int k)
        throws JsonParseException
    {
        if(i >= ai.length)
        {
            ai = growArrayBy(ai, ai.length);
            _quadBuffer = ai;
        }
        int l = i + 1;
        ai[i] = j;
        Name name = _symbols.findName(ai, l);
        if(name == null)
            name = addName(ai, l, k);
        return name;
    }

    public static int[] growArrayBy(int ai[], int i)
    {
        if(ai == null)
            return new int[i];
        else
            return Arrays.copyOf(ai, i + ai.length);
    }

    private int nextByte()
        throws IOException
    {
        if(_inputPtr >= _inputEnd)
            loadMoreGuaranteed();
        byte abyte0[] = _inputBuffer;
        int i = _inputPtr;
        _inputPtr = i + 1;
        return 0xff & abyte0[i];
    }

    private final Name parseName(int i, int j, int k)
        throws IOException
    {
        return parseEscapedName(_quadBuffer, 0, i, j, k);
    }

    private final Name parseName(int i, int j, int k, int l)
        throws IOException
    {
        _quadBuffer[0] = i;
        return parseEscapedName(_quadBuffer, 1, j, k, l);
    }

    protected void _closeInput()
        throws IOException
    {
        if(_inputStream != null)
        {
            if(_ioContext.isResourceManaged() || isEnabled(com.fasterxml.jackson.core.JsonParser.Feature.AUTO_CLOSE_SOURCE))
                _inputStream.close();
            _inputStream = null;
        }
    }

    protected final byte[] _decodeBase64(Base64Variant base64variant)
        throws IOException
    {
        ByteArrayBuilder bytearraybuilder = _getByteArrayBuilder();
        do
        {
            int j;
            do
            {
                if(_inputPtr >= _inputEnd)
                    loadMoreGuaranteed();
                byte abyte0[] = _inputBuffer;
                int i = _inputPtr;
                _inputPtr = i + 1;
                j = 0xff & abyte0[i];
            } while(j <= 32);
            int k = base64variant.decodeBase64Char(j);
            if(k < 0)
            {
                if(j == 34)
                    return bytearraybuilder.toByteArray();
                k = _decodeBase64Escape(base64variant, j, 0);
                if(k < 0)
                    continue;
            }
            if(_inputPtr >= _inputEnd)
                loadMoreGuaranteed();
            byte abyte1[] = _inputBuffer;
            int l = _inputPtr;
            _inputPtr = l + 1;
            int i1 = 0xff & abyte1[l];
            int j1 = base64variant.decodeBase64Char(i1);
            if(j1 < 0)
                j1 = _decodeBase64Escape(base64variant, i1, 1);
            int k1 = j1 | k << 6;
            if(_inputPtr >= _inputEnd)
                loadMoreGuaranteed();
            byte abyte2[] = _inputBuffer;
            int l1 = _inputPtr;
            _inputPtr = l1 + 1;
            int i2 = 0xff & abyte2[l1];
            int j2 = base64variant.decodeBase64Char(i2);
            if(j2 < 0)
            {
                if(j2 != -2)
                {
                    if(i2 == 34 && !base64variant.usesPadding())
                    {
                        bytearraybuilder.append(k1 >> 4);
                        return bytearraybuilder.toByteArray();
                    }
                    j2 = _decodeBase64Escape(base64variant, i2, 2);
                }
                if(j2 == -2)
                {
                    if(_inputPtr >= _inputEnd)
                        loadMoreGuaranteed();
                    byte abyte4[] = _inputBuffer;
                    int k3 = _inputPtr;
                    _inputPtr = k3 + 1;
                    int l3 = 0xff & abyte4[k3];
                    if(!base64variant.usesPaddingChar(l3))
                        throw reportInvalidBase64Char(base64variant, l3, 3, (new StringBuilder()).append("expected padding character '").append(base64variant.getPaddingChar()).append("'").toString());
                    bytearraybuilder.append(k1 >> 4);
                    continue;
                }
            }
            int k2 = j2 | k1 << 6;
            if(_inputPtr >= _inputEnd)
                loadMoreGuaranteed();
            byte abyte3[] = _inputBuffer;
            int l2 = _inputPtr;
            _inputPtr = l2 + 1;
            int i3 = 0xff & abyte3[l2];
            int j3 = base64variant.decodeBase64Char(i3);
            if(j3 < 0)
            {
                if(j3 != -2)
                {
                    if(i3 == 34 && !base64variant.usesPadding())
                    {
                        bytearraybuilder.appendTwoBytes(k2 >> 2);
                        return bytearraybuilder.toByteArray();
                    }
                    j3 = _decodeBase64Escape(base64variant, i3, 3);
                }
                if(j3 == -2)
                {
                    bytearraybuilder.appendTwoBytes(k2 >> 2);
                    continue;
                }
            }
            bytearraybuilder.appendThreeBytes(j3 | k2 << 6);
        } while(true);
    }

    protected int _decodeCharForError(int i)
        throws IOException
    {
        if(i < 0)
        {
            int j;
            int k;
            if((i & 0xe0) == 192)
            {
                i &= 0x1f;
                j = 1;
            } else
            if((i & 0xf0) == 224)
            {
                i &= 0xf;
                j = 2;
            } else
            if((i & 0xf8) == 240)
            {
                i &= 7;
                j = 3;
            } else
            {
                _reportInvalidInitial(i & 0xff);
                j = 1;
            }
            k = nextByte();
            if((k & 0xc0) != 128)
                _reportInvalidOther(k & 0xff);
            i = i << 6 | k & 0x3f;
            if(j > 1)
            {
                int l = nextByte();
                if((l & 0xc0) != 128)
                    _reportInvalidOther(l & 0xff);
                i = i << 6 | l & 0x3f;
                if(j > 2)
                {
                    int i1 = nextByte();
                    if((i1 & 0xc0) != 128)
                        _reportInvalidOther(i1 & 0xff);
                    i = i << 6 | i1 & 0x3f;
                }
            }
        }
        return i;
    }

    protected char _decodeEscaped()
        throws IOException
    {
        int i = 0;
        if(_inputPtr >= _inputEnd && !loadMore())
            _reportInvalidEOF(" in character escape sequence");
        byte abyte0[] = _inputBuffer;
        int j = _inputPtr;
        _inputPtr = j + 1;
        byte byte0 = abyte0[j];
        int k;
        switch(byte0)
        {
        default:
            return _handleUnrecognizedCharacterEscape((char)_decodeCharForError(byte0));

        case 98: // 'b'
            return '\b';

        case 116: // 't'
            return '\t';

        case 110: // 'n'
            return '\n';

        case 102: // 'f'
            return '\f';

        case 114: // 'r'
            return '\r';

        case 34: // '"'
        case 47: // '/'
        case 92: // '\\'
            return (char)byte0;

        case 117: // 'u'
            k = 0;
            break;
        }
        for(; i < 4; i++)
        {
            if(_inputPtr >= _inputEnd && !loadMore())
                _reportInvalidEOF(" in character escape sequence");
            byte abyte1[] = _inputBuffer;
            int l = _inputPtr;
            _inputPtr = l + 1;
            byte byte1 = abyte1[l];
            int i1 = CharTypes.charToHex(byte1);
            if(i1 < 0)
                _reportUnexpectedChar(byte1, "expected a hex-digit for character escape sequence");
            k = i1 | k << 4;
        }

        return (char)k;
    }

    protected void _finishString()
        throws IOException
    {
        int i = _inputPtr;
        if(i >= _inputEnd)
        {
            loadMoreGuaranteed();
            i = _inputPtr;
        }
        char ac[] = _textBuffer.emptyAndGetCurrentSegment();
        int ai[] = _icUTF8;
        int j = Math.min(_inputEnd, i + ac.length);
        byte abyte0[] = _inputBuffer;
        int k = i;
        int l = 0;
        do
        {
            if(k >= j)
                break;
            int i1 = 0xff & abyte0[k];
            if(ai[i1] != 0)
            {
                if(i1 == 34)
                {
                    _inputPtr = k + 1;
                    _textBuffer.setCurrentLength(l);
                    return;
                }
                break;
            }
            int j1 = k + 1;
            int k1 = l + 1;
            ac[l] = (char)i1;
            l = k1;
            k = j1;
        } while(true);
        _inputPtr = k;
        _finishString2(ac, l);
    }

    protected final String _getText2(JsonToken jsontoken)
    {
        if(jsontoken == null)
            return null;
        switch(jsontoken.id())
        {
        default:
            return jsontoken.asString();

        case 5: // '\005'
            return _parsingContext.getCurrentName();

        case 6: // '\006'
        case 7: // '\007'
        case 8: // '\b'
            return _textBuffer.contentsAsString();
        }
    }

    protected JsonToken _handleApos()
        throws IOException
    {
        char ac[];
        int ai[];
        byte abyte0[];
        int i;
        ac = _textBuffer.emptyAndGetCurrentSegment();
        ai = _icUTF8;
        abyte0 = _inputBuffer;
        i = 0;
_L6:
        do
        {
            if(_inputPtr >= _inputEnd)
                loadMoreGuaranteed();
            if(i >= ac.length)
            {
                ac = _textBuffer.finishCurrentSegment();
                i = 0;
            }
            int j = _inputEnd;
            int k = _inputPtr + (ac.length - i);
            int i1;
            int j1;
            int k1;
            int l1;
            int i2;
            if(k >= j)
                k = j;
            while(_inputPtr < k) 
            {
label0:
                {
                    int l = _inputPtr;
                    _inputPtr = l + 1;
                    i1 = 0xff & abyte0[l];
                    if(i1 == 39 || ai[i1] != 0)
                    {
                        if(i1 == 39)
                        {
                            _textBuffer.setCurrentLength(i);
                            return JsonToken.VALUE_STRING;
                        }
                        break label0;
                    }
                    int j2 = i + 1;
                    ac[i] = (char)i1;
                    i = j2;
                }
            }
        } while(true);
        ai[i1];
        JVM INSTR tableswitch 1 4: default 200
    //                   1 259
    //                   2 275
    //                   3 286
    //                   4 321;
           goto _L1 _L2 _L3 _L4 _L5
_L5:
        break MISSING_BLOCK_LABEL_321;
_L1:
        if(i1 < 32)
            _throwUnquotedSpace(i1, "string value");
        _reportInvalidChar(i1);
_L8:
        l1 = i1;
_L9:
        if(i >= ac.length)
        {
            ac = _textBuffer.finishCurrentSegment();
            i2 = 0;
        } else
        {
            i2 = i;
        }
        i = i2 + 1;
        ac[i2] = (char)l1;
          goto _L6
_L2:
        if(i1 == 39) goto _L8; else goto _L7
_L7:
        l1 = _decodeEscaped();
          goto _L9
_L3:
        l1 = _decodeUtf8_2(i1);
          goto _L9
_L4:
        if(_inputEnd - _inputPtr >= 2)
            l1 = _decodeUtf8_3fast(i1);
        else
            l1 = _decodeUtf8_3(i1);
          goto _L9
        j1 = _decodeUtf8_4(i1);
        k1 = i + 1;
        ac[i] = (char)(0xd800 | j1 >> 10);
        if(k1 >= ac.length)
        {
            ac = _textBuffer.finishCurrentSegment();
            i = 0;
        } else
        {
            i = k1;
        }
        l1 = 0xdc00 | j1 & 0x3ff;
          goto _L9
    }

    protected JsonToken _handleInvalidNumberStart(int i, boolean flag)
        throws IOException
    {
        int j;
label0:
        {
            j = i;
            byte byte0;
            do
            {
                if(j != 73)
                    break label0;
                if(_inputPtr >= _inputEnd && !loadMore())
                    _reportInvalidEOFInValue();
                byte abyte0[] = _inputBuffer;
                int k = _inputPtr;
                _inputPtr = k + 1;
                byte0 = abyte0[k];
                String s;
                if(byte0 == 78)
                {
                    if(flag)
                        s = "-INF";
                    else
                        s = "+INF";
                } else
                {
                    if(byte0 != 110)
                        break;
                    if(flag)
                        s = "-Infinity";
                    else
                        s = "+Infinity";
                }
                _matchToken(s, 3);
                if(isEnabled(com.fasterxml.jackson.core.JsonParser.Feature.ALLOW_NON_NUMERIC_NUMBERS))
                {
                    double d;
                    if(flag)
                        d = (-1.0D / 0.0D);
                    else
                        d = (1.0D / 0.0D);
                    return resetAsNaN(s, d);
                }
                _reportError((new StringBuilder()).append("Non-standard token '").append(s).append("': enable JsonParser.Feature.ALLOW_NON_NUMERIC_NUMBERS to allow").toString());
                j = byte0;
            } while(true);
            j = byte0;
        }
        reportUnexpectedNumberChar(j, "expected digit (0-9) to follow minus sign, for valid numeric value");
        return null;
    }

    protected Name _handleOddName(int i)
        throws IOException
    {
        if(i == 39 && isEnabled(com.fasterxml.jackson.core.JsonParser.Feature.ALLOW_SINGLE_QUOTES))
            return _parseAposName();
        if(!isEnabled(com.fasterxml.jackson.core.JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES))
            _reportUnexpectedChar(i, "was expecting double-quote to start field name");
        int ai[] = CharTypes.getInputCodeUtf8JsNames();
        if(ai[i] != 0)
            _reportUnexpectedChar(i, "was expecting either valid name character (for unquoted name) or double-quote (for quoted) to start field name");
        int ai1[] = _quadBuffer;
        int j = 0;
        int k = 0;
        int l = i;
        int i1 = 0;
        int ai2[] = ai1;
        do
        {
            int ai3[];
            int k1;
            int l1;
            int i2;
            int j2;
            if(j < 4)
            {
                int l2 = j + 1;
                l1 = l | k << 8;
                i2 = i1;
                ai3 = ai2;
                k1 = l2;
            } else
            {
                if(i1 >= ai2.length)
                {
                    ai2 = growArrayBy(ai2, ai2.length);
                    _quadBuffer = ai2;
                }
                int j1 = i1 + 1;
                ai2[i1] = k;
                ai3 = ai2;
                k1 = 1;
                l1 = l;
                i2 = j1;
            }
            if(_inputPtr >= _inputEnd && !loadMore())
                _reportInvalidEOF(" in field name");
            j2 = 0xff & _inputBuffer[_inputPtr];
            if(ai[j2] != 0)
            {
                if(k1 > 0)
                {
                    if(i2 >= ai3.length)
                    {
                        ai3 = growArrayBy(ai3, ai3.length);
                        _quadBuffer = ai3;
                    }
                    int k2 = i2 + 1;
                    ai3[i2] = l1;
                    i2 = k2;
                }
                Name name = _symbols.findName(ai3, i2);
                if(name == null)
                    return addName(ai3, i2, k1);
                else
                    return name;
            }
            _inputPtr = 1 + _inputPtr;
            k = l1;
            j = k1;
            ai2 = ai3;
            i1 = i2;
            l = j2;
        } while(true);
    }

    protected JsonToken _handleUnexpectedValue(int i)
        throws IOException
    {
        i;
        JVM INSTR lookupswitch 4: default 44
    //                   39: 89
    //                   43: 182
    //                   73: 143
    //                   78: 104;
           goto _L1 _L2 _L3 _L4 _L5
_L1:
        if(Character.isJavaIdentifierStart(i))
            _reportInvalidToken((new StringBuilder()).append("").append((char)i).toString(), "('true', 'false' or 'null')");
        _reportUnexpectedChar(i, "expected a valid value (number, String, array, object, 'true', 'false' or 'null')");
        return null;
_L2:
        if(isEnabled(com.fasterxml.jackson.core.JsonParser.Feature.ALLOW_SINGLE_QUOTES))
            return _handleApos();
        continue; /* Loop/switch isn't completed */
_L5:
        _matchToken("NaN", 1);
        if(isEnabled(com.fasterxml.jackson.core.JsonParser.Feature.ALLOW_NON_NUMERIC_NUMBERS))
            return resetAsNaN("NaN", (0.0D / 0.0D));
        _reportError("Non-standard token 'NaN': enable JsonParser.Feature.ALLOW_NON_NUMERIC_NUMBERS to allow");
        continue; /* Loop/switch isn't completed */
_L4:
        _matchToken("Infinity", 1);
        if(isEnabled(com.fasterxml.jackson.core.JsonParser.Feature.ALLOW_NON_NUMERIC_NUMBERS))
            return resetAsNaN("Infinity", (1.0D / 0.0D));
        _reportError("Non-standard token 'Infinity': enable JsonParser.Feature.ALLOW_NON_NUMERIC_NUMBERS to allow");
        if(true) goto _L1; else goto _L3
_L3:
        if(_inputPtr >= _inputEnd && !loadMore())
            _reportInvalidEOFInValue();
        byte abyte0[] = _inputBuffer;
        int j = _inputPtr;
        _inputPtr = j + 1;
        return _handleInvalidNumberStart(0xff & abyte0[j], false);
    }

    protected final boolean _loadToHaveAtLeast(int i)
        throws IOException
    {
        if(_inputStream != null) goto _L2; else goto _L1
_L1:
        return false;
_L2:
        int j = _inputEnd - _inputPtr;
        if(j > 0 && _inputPtr > 0)
        {
            _currInputProcessed = _currInputProcessed + (long)_inputPtr;
            _currInputRowStart = _currInputRowStart - _inputPtr;
            System.arraycopy(_inputBuffer, _inputPtr, _inputBuffer, 0, j);
            _inputEnd = j;
        } else
        {
            _inputEnd = 0;
        }
        _inputPtr = 0;
        do
        {
            if(_inputEnd >= i)
                break;
            int k = _inputStream.read(_inputBuffer, _inputEnd, _inputBuffer.length - _inputEnd);
            if(k < 1)
            {
                _closeInput();
                if(k == 0)
                    throw new IOException((new StringBuilder()).append("InputStream.read() returned 0 characters when trying to read ").append(j).append(" bytes").toString());
                continue; /* Loop/switch isn't completed */
            }
            _inputEnd = k + _inputEnd;
        } while(true);
        return true;
        if(true) goto _L1; else goto _L3
_L3:
    }

    protected void _matchToken(String s, int i)
        throws IOException
    {
        int j = s.length();
        do
        {
            if(_inputPtr >= _inputEnd && !loadMore() || _inputBuffer[_inputPtr] != s.charAt(i))
                _reportInvalidToken(s.substring(0, i));
            _inputPtr = 1 + _inputPtr;
        } while(++i < j);
        int k;
        if(_inputPtr < _inputEnd || loadMore())
            if((k = 0xff & _inputBuffer[_inputPtr]) >= 48 && k != 93 && k != 125 && Character.isJavaIdentifierPart((char)_decodeCharForError(k)))
            {
                _reportInvalidToken(s.substring(0, i));
                return;
            }
    }

    protected Name _parseAposName()
        throws IOException
    {
        int j;
        int ai[];
        int ai1[];
        int k;
        int l;
        int i1;
        if(_inputPtr >= _inputEnd && !loadMore())
            _reportInvalidEOF(": was expecting closing ''' for name");
        byte abyte0[] = _inputBuffer;
        int i = _inputPtr;
        _inputPtr = i + 1;
        j = 0xff & abyte0[i];
        if(j == 39)
            return BytesToNameCanonicalizer.getEmptyName();
        ai = _quadBuffer;
        ai1 = _icLatin1;
        k = 0;
        l = 0;
        i1 = 0;
_L1:
        int j1;
        int k1;
        int l1;
        int i2;
        int j4;
        int k4;
        if(j == 39)
        {
            int j2;
            int ai2[];
            int k2;
            int l2;
            int i3;
            byte abyte1[];
            int j3;
            int k3;
            int l3;
            int i6;
            int j6;
            int k6;
            int ai4[];
            int l6;
            int i7;
            int j7;
            int k7;
            int l7;
            int ai5[];
            int i8;
            Name name;
            if(k > 0)
            {
                if(i1 >= ai.length)
                {
                    ai = growArrayBy(ai, ai.length);
                    _quadBuffer = ai;
                }
                int j8 = i1 + 1;
                ai[i1] = l;
                ai5 = ai;
                i8 = j8;
            } else
            {
                ai5 = ai;
                i8 = i1;
            }
            name = _symbols.findName(ai5, i8);
            if(name == null)
                return addName(ai5, i8, k);
            else
                return name;
        }
        if(j == 34 || ai1[j] == 0)
            break MISSING_BLOCK_LABEL_664;
        if(j != 92)
            _throwUnquotedSpace(j, "name");
        else
            j = _decodeEscaped();
        if(j <= 127)
            break MISSING_BLOCK_LABEL_664;
        if(k >= 4)
        {
            if(i1 >= ai.length)
            {
                ai = growArrayBy(ai, ai.length);
                _quadBuffer = ai;
            }
            l7 = i1 + 1;
            ai[i1] = l;
            k4 = 0;
            k1 = l7;
            j4 = 0;
        } else
        {
            int i4 = k;
            j4 = l;
            k1 = i1;
            k4 = i4;
        }
        if(j < 2048)
        {
            j7 = j4 << 8 | (0xc0 | j >> 6);
            k7 = k4 + 1;
            i6 = j7;
            ai4 = ai;
            j6 = k7;
        } else
        {
            int l4 = j4 << 8 | (0xe0 | j >> 12);
            int i5 = k4 + 1;
            int j5;
            int k5;
            int ai3[];
            int l5;
            if(i5 >= 4)
            {
                if(k1 >= ai.length)
                {
                    ai = growArrayBy(ai, ai.length);
                    _quadBuffer = ai;
                }
                i7 = k1 + 1;
                ai[k1] = l4;
                k5 = i7;
                ai3 = ai;
                l5 = 0;
                j5 = 0;
            } else
            {
                j5 = l4;
                k5 = k1;
                ai3 = ai;
                l5 = i5;
            }
            i6 = j5 << 8 | (0x80 | 0x3f & j >> 6);
            j6 = l5 + 1;
            k6 = k5;
            ai4 = ai3;
            k1 = k6;
        }
        l6 = 0x80 | j & 0x3f;
        j1 = i6;
        l1 = j6;
        ai = ai4;
        i2 = l6;
_L2:
        if(l1 < 4)
        {
            l3 = l1 + 1;
            l2 = i2 | j1 << 8;
            i3 = k1;
            ai2 = ai;
            k2 = l3;
        } else
        {
            if(k1 >= ai.length)
            {
                ai = growArrayBy(ai, ai.length);
                _quadBuffer = ai;
            }
            j2 = k1 + 1;
            ai[k1] = j1;
            ai2 = ai;
            k2 = 1;
            l2 = i2;
            i3 = j2;
        }
        if(_inputPtr >= _inputEnd && !loadMore())
            _reportInvalidEOF(" in field name");
        abyte1 = _inputBuffer;
        j3 = _inputPtr;
        _inputPtr = j3 + 1;
        j = 0xff & abyte1[j3];
        k3 = k2;
        ai = ai2;
        l = l2;
        i1 = i3;
        k = k3;
          goto _L1
        j1 = l;
        k1 = i1;
        l1 = k;
        i2 = j;
          goto _L2
    }

    protected Name _parseName(int i)
        throws IOException
    {
        if(i != 34)
            return _handleOddName(i);
        if(9 + _inputPtr > _inputEnd)
            return slowParseName();
        byte abyte0[] = _inputBuffer;
        int ai[] = _icLatin1;
        int j = _inputPtr;
        _inputPtr = j + 1;
        int k = 0xff & abyte0[j];
        if(ai[k] == 0)
        {
            int l = _inputPtr;
            _inputPtr = l + 1;
            int i1 = 0xff & abyte0[l];
            if(ai[i1] == 0)
            {
                int j1 = i1 | k << 8;
                int k1 = _inputPtr;
                _inputPtr = k1 + 1;
                int l1 = 0xff & abyte0[k1];
                if(ai[l1] == 0)
                {
                    int i2 = l1 | j1 << 8;
                    int j2 = _inputPtr;
                    _inputPtr = j2 + 1;
                    int k2 = 0xff & abyte0[j2];
                    if(ai[k2] == 0)
                    {
                        int l2 = k2 | i2 << 8;
                        int i3 = _inputPtr;
                        _inputPtr = i3 + 1;
                        int j3 = 0xff & abyte0[i3];
                        if(ai[j3] == 0)
                        {
                            _quad1 = l2;
                            return parseMediumName(j3, ai);
                        }
                        if(j3 == 34)
                            return findName(l2, 4);
                        else
                            return parseName(l2, j3, 4);
                    }
                    if(k2 == 34)
                        return findName(i2, 3);
                    else
                        return parseName(i2, k2, 3);
                }
                if(l1 == 34)
                    return findName(j1, 2);
                else
                    return parseName(j1, l1, 2);
            }
            if(i1 == 34)
                return findName(k, 1);
            else
                return parseName(k, i1, 1);
        }
        if(k == 34)
            return BytesToNameCanonicalizer.getEmptyName();
        else
            return parseName(0, k, 0);
    }

    protected JsonToken _parseNumber(int i)
        throws IOException, JsonParseException
    {
        boolean flag = true;
        char ac[] = _textBuffer.emptyAndGetCurrentSegment();
        boolean flag1;
        int j;
        if(i == 45)
            flag1 = flag;
        else
            flag1 = false;
        int k;
        int l;
        int i1;
        byte abyte0[];
        int j1;
        int k1;
        int l1;
        if(flag1)
        {
            ac[0] = '-';
            if(_inputPtr >= _inputEnd)
                loadMoreGuaranteed();
            byte abyte1[] = _inputBuffer;
            int i2 = _inputPtr;
            _inputPtr = i2 + 1;
            j = 0xff & abyte1[i2];
            if(j < 48 || j > 57)
                return _handleInvalidNumberStart(j, flag);
            k = ((flag) ? 1 : 0);
        } else
        {
            j = i;
            k = 0;
        }
        if(j == 48)
            j = _verifyNoLeadingZeroes();
        l = k + 1;
        ac[k] = (char)j;
        i1 = _inputPtr + ac.length;
        if(i1 > _inputEnd)
            i1 = _inputEnd;
        if(_inputPtr >= i1)
            return _parserNumber2(ac, l, flag1, flag);
        abyte0 = _inputBuffer;
        j1 = _inputPtr;
        _inputPtr = j1 + 1;
        k1 = 0xff & abyte0[j1];
        if(k1 < 48 || k1 > 57)
        {
            if(k1 == 46 || k1 == 101 || k1 == 69)
                return _parseFloat(ac, l, k1, flag1, flag);
        } else
        {
            flag++;
            if(l >= ac.length)
            {
                ac = _textBuffer.finishCurrentSegment();
                l1 = 0;
            } else
            {
                l1 = l;
            }
            l = l1 + 1;
            ac[l1] = (char)k1;
            if(false)
                break MISSING_BLOCK_LABEL_292;
            else
                break MISSING_BLOCK_LABEL_156;
        }
        _inputPtr = -1 + _inputPtr;
        _textBuffer.setCurrentLength(l);
        if(_parsingContext.inRoot())
            _verifyRootSpace(k1);
        return resetInt(flag1, flag);
    }

    protected int _readBinary(Base64Variant base64variant, OutputStream outputstream, byte abyte0[])
        throws IOException, JsonParseException
    {
        int i;
        int j;
        int k;
        i = -3 + abyte0.length;
        j = 0;
        k = 0;
_L7:
        int i1;
        int j1;
        do
        {
            if(_inputPtr >= _inputEnd)
                loadMoreGuaranteed();
            byte abyte1[] = _inputBuffer;
            int l = _inputPtr;
            _inputPtr = l + 1;
            i1 = 0xff & abyte1[l];
        } while(i1 <= 32);
        j1 = base64variant.decodeBase64Char(i1);
        if(j1 >= 0) goto _L2; else goto _L1
_L1:
        if(i1 != 34) goto _L4; else goto _L3
_L3:
        _tokenIncomplete = false;
        if(k > 0)
        {
            j += k;
            outputstream.write(abyte0, 0, k);
        }
        return j;
_L4:
        j1 = _decodeBase64Escape(base64variant, i1, 0);
        if(j1 < 0)
            continue; /* Loop/switch isn't completed */
_L2:
        int k1 = j1;
        int l1;
        byte abyte2[];
        int i2;
        int j2;
        int k2;
        int l2;
        byte abyte3[];
        int i3;
        int j3;
        int k3;
        int l3;
        byte abyte4[];
        int i4;
        int j4;
        int k4;
        int l4;
        int i5;
        int j5;
        int k5;
        int l5;
        int i6;
        int j6;
        if(k > i)
        {
            j += k;
            outputstream.write(abyte0, 0, k);
            l1 = 0;
        } else
        {
            l1 = k;
        }
        if(_inputPtr >= _inputEnd)
            loadMoreGuaranteed();
        abyte2 = _inputBuffer;
        i2 = _inputPtr;
        _inputPtr = i2 + 1;
        j2 = 0xff & abyte2[i2];
        k2 = base64variant.decodeBase64Char(j2);
        if(k2 < 0)
            k2 = _decodeBase64Escape(base64variant, j2, 1);
        l2 = k2 | k1 << 6;
        if(_inputPtr >= _inputEnd)
            loadMoreGuaranteed();
        abyte3 = _inputBuffer;
        i3 = _inputPtr;
        _inputPtr = i3 + 1;
        j3 = 0xff & abyte3[i3];
        k3 = base64variant.decodeBase64Char(j3);
        if(k3 < 0)
        {
            if(k3 != -2)
            {
                if(j3 == 34 && !base64variant.usesPadding())
                {
                    int j7 = l2 >> 4;
                    k = l1 + 1;
                    abyte0[l1] = (byte)j7;
                    continue; /* Loop/switch isn't completed */
                }
                k3 = _decodeBase64Escape(base64variant, j3, 2);
            }
            if(k3 == -2)
            {
                if(_inputPtr >= _inputEnd)
                    loadMoreGuaranteed();
                byte abyte5[] = _inputBuffer;
                int k6 = _inputPtr;
                _inputPtr = k6 + 1;
                int l6 = 0xff & abyte5[k6];
                if(!base64variant.usesPaddingChar(l6))
                    throw reportInvalidBase64Char(base64variant, l6, 3, (new StringBuilder()).append("expected padding character '").append(base64variant.getPaddingChar()).append("'").toString());
                int i7 = l2 >> 4;
                k = l1 + 1;
                abyte0[l1] = (byte)i7;
                continue; /* Loop/switch isn't completed */
            }
        }
        l3 = k3 | l2 << 6;
        if(_inputPtr >= _inputEnd)
            loadMoreGuaranteed();
        abyte4 = _inputBuffer;
        i4 = _inputPtr;
        _inputPtr = i4 + 1;
        j4 = 0xff & abyte4[i4];
        k4 = base64variant.decodeBase64Char(j4);
        if(k4 >= 0)
            break MISSING_BLOCK_LABEL_654;
        if(k4 == -2)
            break MISSING_BLOCK_LABEL_609;
        if(j4 != 34 || base64variant.usesPadding())
            break; /* Loop/switch isn't completed */
        i6 = l3 >> 2;
        j6 = l1 + 1;
        abyte0[l1] = (byte)(i6 >> 8);
        k = j6 + 1;
        abyte0[j6] = (byte)i6;
        if(true) goto _L3; else goto _L5
_L5:
        k4 = _decodeBase64Escape(base64variant, j4, 3);
        if(k4 == -2)
        {
            k5 = l3 >> 2;
            l5 = l1 + 1;
            abyte0[l1] = (byte)(k5 >> 8);
            k = l5 + 1;
            abyte0[l5] = (byte)k5;
            continue; /* Loop/switch isn't completed */
        }
        l4 = k4 | l3 << 6;
        i5 = l1 + 1;
        abyte0[l1] = (byte)(l4 >> 16);
        j5 = i5 + 1;
        abyte0[i5] = (byte)(l4 >> 8);
        k = j5 + 1;
        abyte0[j5] = (byte)l4;
        if(true) goto _L7; else goto _L6
_L6:
    }

    protected void _releaseBuffers()
        throws IOException
    {
        super._releaseBuffers();
        _symbols.release();
        if(_bufferRecyclable)
        {
            byte abyte0[] = _inputBuffer;
            if(abyte0 != null)
            {
                _inputBuffer = null;
                _ioContext.releaseReadIOBuffer(abyte0);
            }
        }
    }

    protected void _reportInvalidChar(int i)
        throws JsonParseException
    {
        if(i < 32)
            _throwInvalidSpace(i);
        _reportInvalidInitial(i);
    }

    protected void _reportInvalidInitial(int i)
        throws JsonParseException
    {
        _reportError((new StringBuilder()).append("Invalid UTF-8 start byte 0x").append(Integer.toHexString(i)).toString());
    }

    protected void _reportInvalidOther(int i)
        throws JsonParseException
    {
        _reportError((new StringBuilder()).append("Invalid UTF-8 middle byte 0x").append(Integer.toHexString(i)).toString());
    }

    protected void _reportInvalidOther(int i, int j)
        throws JsonParseException
    {
        _inputPtr = j;
        _reportInvalidOther(i);
    }

    protected void _reportInvalidToken(String s)
        throws IOException
    {
        _reportInvalidToken(s, "'null', 'true', 'false' or NaN");
    }

    protected void _reportInvalidToken(String s, String s1)
        throws IOException
    {
        StringBuilder stringbuilder = new StringBuilder(s);
_L5:
        if(_inputPtr < _inputEnd || loadMore()) goto _L2; else goto _L1
_L1:
        _reportError((new StringBuilder()).append("Unrecognized token '").append(stringbuilder.toString()).append("': was expecting ").append(s1).toString());
        return;
_L2:
        char c;
        byte abyte0[] = _inputBuffer;
        int i = _inputPtr;
        _inputPtr = i + 1;
        c = (char)_decodeCharForError(abyte0[i]);
        if(!Character.isJavaIdentifierPart(c)) goto _L1; else goto _L3
_L3:
        stringbuilder.append(c);
        if(true) goto _L5; else goto _L4
_L4:
    }

    protected final void _skipCR()
        throws IOException
    {
        if((_inputPtr < _inputEnd || loadMore()) && _inputBuffer[_inputPtr] == 10)
            _inputPtr = 1 + _inputPtr;
        _currInputRow = 1 + _currInputRow;
        _currInputRowStart = _inputPtr;
    }

    protected void _skipString()
        throws IOException
    {
        int ai[];
        byte abyte0[];
        _tokenIncomplete = false;
        ai = _icUTF8;
        abyte0 = _inputBuffer;
_L2:
        do
        {
label0:
            {
                int i = _inputPtr;
                int j = _inputEnd;
                if(i >= j)
                {
                    loadMoreGuaranteed();
                    i = _inputPtr;
                    j = _inputEnd;
                }
                int k;
                int l;
                for(; i < j; i = k)
                {
                    k = i + 1;
                    l = 0xff & abyte0[i];
                    if(ai[l] == 0)
                        break MISSING_BLOCK_LABEL_190;
                    _inputPtr = k;
                    if(l == 34)
                        return;
                    break label0;
                }

                _inputPtr = i;
            }
        } while(true);
        switch(ai[l])
        {
        default:
            if(l < 32)
                _throwUnquotedSpace(l, "string value");
            else
                _reportInvalidChar(l);
            break;

        case 1: // '\001'
            _decodeEscaped();
            break;

        case 2: // '\002'
            _skipUtf8_2(l);
            break;

        case 3: // '\003'
            _skipUtf8_3(l);
            break;

        case 4: // '\004'
            _skipUtf8_4(l);
            break;
        }
        continue; /* Loop/switch isn't completed */
        if(true) goto _L2; else goto _L1
_L1:
    }

    public byte[] getBinaryValue(Base64Variant base64variant)
        throws IOException, JsonParseException
    {
        if(_currToken != JsonToken.VALUE_STRING && (_currToken != JsonToken.VALUE_EMBEDDED_OBJECT || _binaryValue == null))
            _reportError((new StringBuilder()).append("Current token (").append(_currToken).append(") not VALUE_STRING or VALUE_EMBEDDED_OBJECT, can not access as binary").toString());
        if(!_tokenIncomplete) goto _L2; else goto _L1
_L1:
        try
        {
            _binaryValue = _decodeBase64(base64variant);
        }
        catch(IllegalArgumentException illegalargumentexception)
        {
            throw _constructError((new StringBuilder()).append("Failed to decode VALUE_STRING as base64 (").append(base64variant).append("): ").append(illegalargumentexception.getMessage()).toString());
        }
        _tokenIncomplete = false;
_L4:
        return _binaryValue;
_L2:
        if(_binaryValue == null)
        {
            ByteArrayBuilder bytearraybuilder = _getByteArrayBuilder();
            _decodeBase64(getText(), bytearraybuilder, base64variant);
            _binaryValue = bytearraybuilder.toByteArray();
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    public ObjectCodec getCodec()
    {
        return _objectCodec;
    }

    public JsonLocation getCurrentLocation()
    {
        int i = 1 + (_inputPtr - _currInputRowStart);
        return new JsonLocation(_ioContext.getSourceReference(), _currInputProcessed + (long)_inputPtr, -1L, _currInputRow, i);
    }

    public Object getInputSource()
    {
        return _inputStream;
    }

    public String getText()
        throws IOException, JsonParseException
    {
        if(_currToken == JsonToken.VALUE_STRING)
        {
            if(_tokenIncomplete)
            {
                _tokenIncomplete = false;
                _finishString();
            }
            return _textBuffer.contentsAsString();
        } else
        {
            return _getText2(_currToken);
        }
    }

    public char[] getTextCharacters()
        throws IOException, JsonParseException
    {
        if(_currToken == null)
            break MISSING_BLOCK_LABEL_155;
        _currToken.id();
        JVM INSTR tableswitch 5 8: default 44
    //                   5 52
    //                   6 131
    //                   7 147
    //                   8 147;
           goto _L1 _L2 _L3 _L4 _L4
_L4:
        break; /* Loop/switch isn't completed */
_L1:
        return _currToken.asCharArray();
_L2:
        if(_nameCopied) goto _L6; else goto _L5
_L5:
        String s;
        int i;
        s = _parsingContext.getCurrentName();
        i = s.length();
        if(_nameCopyBuffer != null) goto _L8; else goto _L7
_L7:
        _nameCopyBuffer = _ioContext.allocNameCopyBuffer(i);
_L10:
        s.getChars(0, i, _nameCopyBuffer, 0);
        _nameCopied = true;
_L6:
        return _nameCopyBuffer;
_L8:
        if(_nameCopyBuffer.length < i)
            _nameCopyBuffer = new char[i];
        if(true) goto _L10; else goto _L9
_L9:
        break; /* Loop/switch isn't completed */
_L3:
        if(_tokenIncomplete)
        {
            _tokenIncomplete = false;
            _finishString();
        }
        return _textBuffer.getTextBuffer();
        return null;
    }

    public int getTextLength()
        throws IOException, JsonParseException
    {
label0:
        {
label1:
            {
label2:
                {
                    JsonToken jsontoken = _currToken;
                    int i = 0;
                    if(jsontoken != null)
                        switch(_currToken.id())
                        {
                        default:
                            i = _currToken.asCharArray().length;
                            break;

                        case 5: // '\005'
                            break label2;

                        case 6: // '\006'
                            break label1;

                        case 7: // '\007'
                        case 8: // '\b'
                            break label0;
                        }
                    return i;
                }
                return _parsingContext.getCurrentName().length();
            }
            if(_tokenIncomplete)
            {
                _tokenIncomplete = false;
                _finishString();
            }
        }
        return _textBuffer.size();
    }

    public int getTextOffset()
        throws IOException, JsonParseException
    {
        if(_currToken == null) goto _L2; else goto _L1
_L1:
        _currToken.id();
        JVM INSTR tableswitch 5 8: default 44
    //                   5 44
    //                   6 46
    //                   7 62
    //                   8 62;
           goto _L2 _L2 _L3 _L4 _L4
_L2:
        return 0;
_L3:
        if(_tokenIncomplete)
        {
            _tokenIncomplete = false;
            _finishString();
        }
_L4:
        return _textBuffer.getTextOffset();
    }

    public JsonLocation getTokenLocation()
    {
        return new JsonLocation(_ioContext.getSourceReference(), getTokenCharacterOffset(), -1L, getTokenLineNr(), getTokenColumnNr());
    }

    public String getValueAsString()
        throws IOException, JsonParseException
    {
        if(_currToken == JsonToken.VALUE_STRING)
        {
            if(_tokenIncomplete)
            {
                _tokenIncomplete = false;
                _finishString();
            }
            return _textBuffer.contentsAsString();
        } else
        {
            return super.getValueAsString(null);
        }
    }

    public String getValueAsString(String s)
        throws IOException, JsonParseException
    {
        if(_currToken == JsonToken.VALUE_STRING)
        {
            if(_tokenIncomplete)
            {
                _tokenIncomplete = false;
                _finishString();
            }
            return _textBuffer.contentsAsString();
        } else
        {
            return super.getValueAsString(s);
        }
    }

    protected final boolean loadMore()
        throws IOException
    {
        _currInputProcessed = _currInputProcessed + (long)_inputEnd;
        _currInputRowStart = _currInputRowStart - _inputEnd;
        InputStream inputstream = _inputStream;
        boolean flag = false;
        if(inputstream != null)
        {
            int i = _inputStream.read(_inputBuffer, 0, _inputBuffer.length);
            if(i > 0)
            {
                _inputPtr = 0;
                _inputEnd = i;
                flag = true;
            } else
            {
                _closeInput();
                flag = false;
                if(i == 0)
                    throw new IOException((new StringBuilder()).append("InputStream.read() returned 0 characters when trying to read ").append(_inputBuffer.length).append(" bytes").toString());
            }
        }
        return flag;
    }

    public Boolean nextBooleanValue()
        throws IOException, JsonParseException
    {
        JsonToken jsontoken;
        if(_currToken != JsonToken.FIELD_NAME)
            break MISSING_BLOCK_LABEL_114;
        _nameCopied = false;
        jsontoken = _nextToken;
        _nextToken = null;
        _currToken = jsontoken;
        if(jsontoken != JsonToken.VALUE_TRUE) goto _L2; else goto _L1
_L1:
        Boolean boolean1 = Boolean.TRUE;
_L4:
        return boolean1;
_L2:
        JsonToken jsontoken1;
        if(jsontoken == JsonToken.VALUE_FALSE)
            return Boolean.FALSE;
        if(jsontoken == JsonToken.START_ARRAY)
        {
            _parsingContext = _parsingContext.createChildArrayContext(_tokenInputRow, _tokenInputCol);
            return null;
        }
        jsontoken1 = JsonToken.START_OBJECT;
        boolean1 = null;
        if(jsontoken != jsontoken1) goto _L4; else goto _L3
_L3:
        _parsingContext = _parsingContext.createChildObjectContext(_tokenInputRow, _tokenInputCol);
        return null;
        switch(nextToken().id())
        {
        default:
            return null;

        case 9: // '\t'
            return Boolean.TRUE;

        case 10: // '\n'
            return Boolean.FALSE;
        }
    }

    public boolean nextFieldName(SerializableString serializablestring)
        throws IOException, JsonParseException
    {
        int i;
        int j;
        i = 0;
        _numTypesValid = 0;
        if(_currToken == JsonToken.FIELD_NAME)
        {
            _nextAfterName();
            return false;
        }
        if(_tokenIncomplete)
            _skipString();
        j = _skipWSOrEnd();
        if(j < 0)
        {
            close();
            _currToken = null;
            return false;
        }
        _tokenInputTotal = (_currInputProcessed + (long)_inputPtr) - 1L;
        _tokenInputRow = _currInputRow;
        _tokenInputCol = -1 + (_inputPtr - _currInputRowStart);
        _binaryValue = null;
        if(j == 93)
        {
            if(!_parsingContext.inArray())
                _reportMismatchedEndMarker(j, '}');
            _parsingContext = _parsingContext.getParent();
            _currToken = JsonToken.END_ARRAY;
            return false;
        }
        if(j == 125)
        {
            if(!_parsingContext.inObject())
                _reportMismatchedEndMarker(j, ']');
            _parsingContext = _parsingContext.getParent();
            _currToken = JsonToken.END_OBJECT;
            return false;
        }
        if(_parsingContext.expectComma())
        {
            if(j != 44)
                _reportUnexpectedChar(j, (new StringBuilder()).append("was expecting comma to separate ").append(_parsingContext.getTypeDesc()).append(" entries").toString());
            j = _skipWS();
        }
        if(!_parsingContext.inObject())
        {
            _nextTokenNotInObject(j);
            return false;
        }
        if(j != 34) goto _L2; else goto _L1
_L1:
        byte abyte0[];
        int k;
        abyte0 = serializablestring.asQuotedUTF8();
        k = abyte0.length;
        if(k + _inputPtr >= _inputEnd) goto _L2; else goto _L3
_L3:
        int l = k + _inputPtr;
        if(_inputBuffer[l] != 34) goto _L2; else goto _L4
_L4:
        int i1 = _inputPtr;
_L7:
        if(i == k)
        {
            _inputPtr = l + 1;
            _parsingContext.setCurrentName(serializablestring.getValue());
            _currToken = JsonToken.FIELD_NAME;
            _isNextTokenNameYes();
            return true;
        }
        if(abyte0[i] == _inputBuffer[i1 + i]) goto _L5; else goto _L2
_L2:
        return _isNextTokenNameMaybe(j, serializablestring);
_L5:
        i++;
        if(true) goto _L7; else goto _L6
_L6:
    }

    public int nextIntValue(int i)
        throws IOException, JsonParseException
    {
        if(_currToken != JsonToken.FIELD_NAME) goto _L2; else goto _L1
_L1:
        JsonToken jsontoken;
        _nameCopied = false;
        jsontoken = _nextToken;
        _nextToken = null;
        _currToken = jsontoken;
        if(jsontoken != JsonToken.VALUE_NUMBER_INT) goto _L4; else goto _L3
_L3:
        i = getIntValue();
_L6:
        return i;
_L4:
        if(jsontoken == JsonToken.START_ARRAY)
        {
            _parsingContext = _parsingContext.createChildArrayContext(_tokenInputRow, _tokenInputCol);
            return i;
        }
        if(jsontoken == JsonToken.START_OBJECT)
        {
            _parsingContext = _parsingContext.createChildObjectContext(_tokenInputRow, _tokenInputCol);
            return i;
        }
        continue; /* Loop/switch isn't completed */
_L2:
        if(nextToken() == JsonToken.VALUE_NUMBER_INT)
            return getIntValue();
        if(true) goto _L6; else goto _L5
_L5:
    }

    public long nextLongValue(long l)
        throws IOException, JsonParseException
    {
        if(_currToken != JsonToken.FIELD_NAME) goto _L2; else goto _L1
_L1:
        JsonToken jsontoken;
        _nameCopied = false;
        jsontoken = _nextToken;
        _nextToken = null;
        _currToken = jsontoken;
        if(jsontoken != JsonToken.VALUE_NUMBER_INT) goto _L4; else goto _L3
_L3:
        l = getLongValue();
_L6:
        return l;
_L4:
        if(jsontoken == JsonToken.START_ARRAY)
        {
            _parsingContext = _parsingContext.createChildArrayContext(_tokenInputRow, _tokenInputCol);
            return l;
        }
        if(jsontoken == JsonToken.START_OBJECT)
        {
            _parsingContext = _parsingContext.createChildObjectContext(_tokenInputRow, _tokenInputCol);
            return l;
        }
        continue; /* Loop/switch isn't completed */
_L2:
        if(nextToken() == JsonToken.VALUE_NUMBER_INT)
            return getLongValue();
        if(true) goto _L6; else goto _L5
_L5:
    }

    public String nextTextValue()
        throws IOException, JsonParseException
    {
        if(_currToken != JsonToken.FIELD_NAME) goto _L2; else goto _L1
_L1:
        JsonToken jsontoken2;
        _nameCopied = false;
        jsontoken2 = _nextToken;
        _nextToken = null;
        _currToken = jsontoken2;
        if(jsontoken2 != JsonToken.VALUE_STRING) goto _L4; else goto _L3
_L3:
        String s;
        if(_tokenIncomplete)
        {
            _tokenIncomplete = false;
            _finishString();
        }
        s = _textBuffer.contentsAsString();
_L6:
        return s;
_L4:
        if(jsontoken2 == JsonToken.START_ARRAY)
        {
            _parsingContext = _parsingContext.createChildArrayContext(_tokenInputRow, _tokenInputCol);
            return null;
        }
        JsonToken jsontoken3 = JsonToken.START_OBJECT;
        s = null;
        if(jsontoken2 == jsontoken3)
        {
            _parsingContext = _parsingContext.createChildObjectContext(_tokenInputRow, _tokenInputCol);
            return null;
        }
        continue; /* Loop/switch isn't completed */
_L2:
        JsonToken jsontoken = nextToken();
        JsonToken jsontoken1 = JsonToken.VALUE_STRING;
        s = null;
        if(jsontoken == jsontoken1)
            return getText();
        if(true) goto _L6; else goto _L5
_L5:
    }

    public JsonToken nextToken()
        throws IOException, JsonParseException
    {
        _numTypesValid = 0;
        if(_currToken == JsonToken.FIELD_NAME)
            return _nextAfterName();
        if(_tokenIncomplete)
            _skipString();
        int i = _skipWSOrEnd();
        if(i < 0)
        {
            close();
            _currToken = null;
            return null;
        }
        _tokenInputTotal = (_currInputProcessed + (long)_inputPtr) - 1L;
        _tokenInputRow = _currInputRow;
        _tokenInputCol = -1 + (_inputPtr - _currInputRowStart);
        _binaryValue = null;
        if(i == 93)
        {
            if(!_parsingContext.inArray())
                _reportMismatchedEndMarker(i, '}');
            _parsingContext = _parsingContext.getParent();
            JsonToken jsontoken2 = JsonToken.END_ARRAY;
            _currToken = jsontoken2;
            return jsontoken2;
        }
        if(i == 125)
        {
            if(!_parsingContext.inObject())
                _reportMismatchedEndMarker(i, ']');
            _parsingContext = _parsingContext.getParent();
            JsonToken jsontoken1 = JsonToken.END_OBJECT;
            _currToken = jsontoken1;
            return jsontoken1;
        }
        if(_parsingContext.expectComma())
        {
            if(i != 44)
                _reportUnexpectedChar(i, (new StringBuilder()).append("was expecting comma to separate ").append(_parsingContext.getTypeDesc()).append(" entries").toString());
            i = _skipWS();
        }
        if(!_parsingContext.inObject())
            return _nextTokenNotInObject(i);
        Name name = _parseName(i);
        _parsingContext.setCurrentName(name.getName());
        _currToken = JsonToken.FIELD_NAME;
        if(_inputPtr >= _inputEnd || _inputBuffer[_inputPtr] != 58) goto _L2; else goto _L1
_L1:
        _inputPtr = 1 + _inputPtr;
_L4:
        int k;
        k = _skipWS();
        if(k == 34)
        {
            _tokenIncomplete = true;
            _nextToken = JsonToken.VALUE_STRING;
            return _currToken;
        }
        break; /* Loop/switch isn't completed */
_L2:
        int j = _skipWS();
        if(j != 58)
            _reportUnexpectedChar(j, "was expecting a colon to separate field name and value");
        if(true) goto _L4; else goto _L3
_L3:
        k;
        JVM INSTR lookupswitch 18: default 532
    //                   45: 620
    //                   48: 620
    //                   49: 620
    //                   50: 620
    //                   51: 620
    //                   52: 620
    //                   53: 620
    //                   54: 620
    //                   55: 620
    //                   56: 620
    //                   57: 620
    //                   91: 551
    //                   93: 567
    //                   102: 590
    //                   110: 605
    //                   116: 575
    //                   123: 559
    //                   125: 567;
           goto _L5 _L6 _L6 _L6 _L6 _L6 _L6 _L6 _L6 _L6 _L6 _L6 _L7 _L8 _L9 _L10 _L11 _L12 _L8
_L5:
        JsonToken jsontoken = _handleUnexpectedValue(k);
_L14:
        _nextToken = jsontoken;
        return _currToken;
_L7:
        jsontoken = JsonToken.START_ARRAY;
        continue; /* Loop/switch isn't completed */
_L12:
        jsontoken = JsonToken.START_OBJECT;
        continue; /* Loop/switch isn't completed */
_L8:
        _reportUnexpectedChar(k, "expected a value");
_L11:
        _matchToken("true", 1);
        jsontoken = JsonToken.VALUE_TRUE;
        continue; /* Loop/switch isn't completed */
_L9:
        _matchToken("false", 1);
        jsontoken = JsonToken.VALUE_FALSE;
        continue; /* Loop/switch isn't completed */
_L10:
        _matchToken("null", 1);
        jsontoken = JsonToken.VALUE_NULL;
        continue; /* Loop/switch isn't completed */
_L6:
        jsontoken = _parseNumber(k);
        if(true) goto _L14; else goto _L13
_L13:
    }

    protected Name parseEscapedName(int ai[], int i, int j, int k, int l)
        throws IOException
    {
        int ai1[] = _icLatin1;
_L2:
        int i1;
        int ai2[];
        if(ai1[k] == 0)
            break; /* Loop/switch isn't completed */
        if(k == 34)
        {
            if(l > 0)
            {
                if(i >= ai.length)
                {
                    ai = growArrayBy(ai, ai.length);
                    _quadBuffer = ai;
                }
                int l4 = i + 1;
                ai[i] = j;
                i = l4;
            }
            Name name = _symbols.findName(ai, i);
            if(name == null)
                name = addName(ai, i, l);
            return name;
        }
        byte abyte0[];
        int k1;
        int l1;
        int ai3[];
        int j3;
        int k3;
        int ai5[];
        if(k != 92)
            _throwUnquotedSpace(k, "name");
        else
            k = _decodeEscaped();
        if(k <= 127)
            break; /* Loop/switch isn't completed */
        if(l >= 4)
        {
            if(i >= ai.length)
            {
                ai = growArrayBy(ai, ai.length);
                _quadBuffer = ai;
            }
            l1 = i + 1;
            ai[i] = j;
            l = 0;
            j = 0;
            ai3 = ai;
        } else
        {
            l1 = i;
            ai3 = ai;
        }
        if(k < 2048)
        {
            int j4 = j << 8 | (0xc0 | k >> 6);
            int k4 = l + 1;
            j3 = j4;
            ai5 = ai3;
            k3 = k4;
        } else
        {
            int i2 = j << 8 | (0xe0 | k >> 12);
            int j2 = l + 1;
            int j1;
            int k2;
            int l2;
            int ai4[];
            int i3;
            int l3;
            if(j2 >= 4)
            {
                if(l1 >= ai3.length)
                {
                    ai3 = growArrayBy(ai3, ai3.length);
                    _quadBuffer = ai3;
                }
                int i4 = l1 + 1;
                ai3[l1] = i2;
                l2 = i4;
                ai4 = ai3;
                i3 = 0;
                k2 = 0;
            } else
            {
                k2 = i2;
                l2 = l1;
                ai4 = ai3;
                i3 = j2;
            }
            j3 = k2 << 8 | (0x80 | 0x3f & k >> 6);
            k3 = i3 + 1;
            l3 = l2;
            ai5 = ai4;
            l1 = l3;
        }
        j = 0x80 | k & 0x3f;
        l = k3;
        i = l1;
        ai2 = ai5;
        i1 = j3;
_L3:
        if(l < 4)
        {
            l++;
            j |= i1 << 8;
            ai = ai2;
        } else
        {
            if(i >= ai2.length)
            {
                ai2 = growArrayBy(ai2, ai2.length);
                _quadBuffer = ai2;
            }
            j1 = i + 1;
            ai2[i] = i1;
            l = 1;
            i = j1;
            ai = ai2;
        }
        if(_inputPtr >= _inputEnd && !loadMore())
            _reportInvalidEOF(" in field name");
        abyte0 = _inputBuffer;
        k1 = _inputPtr;
        _inputPtr = k1 + 1;
        k = 0xff & abyte0[k1];
        if(true) goto _L2; else goto _L1
_L1:
        i1 = j;
        ai2 = ai;
        j = k;
          goto _L3
        if(true) goto _L2; else goto _L4
_L4:
    }

    protected Name parseLongName(int i)
        throws IOException
    {
        int ai[] = _icLatin1;
        int j = 2;
        int k = i;
        do
        {
            if(_inputEnd - _inputPtr < 4)
                return parseEscapedName(_quadBuffer, j, 0, k, 0);
            byte abyte0[] = _inputBuffer;
            int l = _inputPtr;
            _inputPtr = l + 1;
            int i1 = 0xff & abyte0[l];
            if(ai[i1] != 0)
                if(i1 == 34)
                    return findName(_quadBuffer, j, k, 1);
                else
                    return parseEscapedName(_quadBuffer, j, k, i1, 1);
            int j1 = i1 | k << 8;
            byte abyte1[] = _inputBuffer;
            int k1 = _inputPtr;
            _inputPtr = k1 + 1;
            int l1 = 0xff & abyte1[k1];
            if(ai[l1] != 0)
                if(l1 == 34)
                    return findName(_quadBuffer, j, j1, 2);
                else
                    return parseEscapedName(_quadBuffer, j, j1, l1, 2);
            int i2 = l1 | j1 << 8;
            byte abyte2[] = _inputBuffer;
            int j2 = _inputPtr;
            _inputPtr = j2 + 1;
            int k2 = 0xff & abyte2[j2];
            if(ai[k2] != 0)
                if(k2 == 34)
                    return findName(_quadBuffer, j, i2, 3);
                else
                    return parseEscapedName(_quadBuffer, j, i2, k2, 3);
            int l2 = k2 | i2 << 8;
            byte abyte3[] = _inputBuffer;
            int i3 = _inputPtr;
            _inputPtr = i3 + 1;
            k = 0xff & abyte3[i3];
            if(ai[k] != 0)
                if(k == 34)
                    return findName(_quadBuffer, j, l2, 4);
                else
                    return parseEscapedName(_quadBuffer, j, l2, k, 4);
            if(j >= _quadBuffer.length)
                _quadBuffer = growArrayBy(_quadBuffer, j);
            int ai1[] = _quadBuffer;
            int j3 = j + 1;
            ai1[j] = l2;
            j = j3;
        } while(true);
    }

    protected Name parseMediumName(int i, int ai[])
        throws IOException
    {
        byte abyte0[] = _inputBuffer;
        int j = _inputPtr;
        _inputPtr = j + 1;
        int k = 0xff & abyte0[j];
        if(ai[k] != 0)
            if(k == 34)
                return findName(_quad1, i, 1);
            else
                return parseName(_quad1, i, k, 1);
        int l = k | i << 8;
        byte abyte1[] = _inputBuffer;
        int i1 = _inputPtr;
        _inputPtr = i1 + 1;
        int j1 = 0xff & abyte1[i1];
        if(ai[j1] != 0)
            if(j1 == 34)
                return findName(_quad1, l, 2);
            else
                return parseName(_quad1, l, j1, 2);
        int k1 = j1 | l << 8;
        byte abyte2[] = _inputBuffer;
        int l1 = _inputPtr;
        _inputPtr = l1 + 1;
        int i2 = 0xff & abyte2[l1];
        if(ai[i2] != 0)
            if(i2 == 34)
                return findName(_quad1, k1, 3);
            else
                return parseName(_quad1, k1, i2, 3);
        int j2 = i2 | k1 << 8;
        byte abyte3[] = _inputBuffer;
        int k2 = _inputPtr;
        _inputPtr = k2 + 1;
        int l2 = 0xff & abyte3[k2];
        if(ai[l2] != 0)
        {
            if(l2 == 34)
                return findName(_quad1, j2, 4);
            else
                return parseName(_quad1, j2, l2, 4);
        } else
        {
            _quadBuffer[0] = _quad1;
            _quadBuffer[1] = j2;
            return parseLongName(l2);
        }
    }

    public int readBinaryValue(Base64Variant base64variant, OutputStream outputstream)
        throws IOException, JsonParseException
    {
        byte abyte1[];
        if(!_tokenIncomplete || _currToken != JsonToken.VALUE_STRING)
        {
            byte abyte0[] = getBinaryValue(base64variant);
            outputstream.write(abyte0);
            return abyte0.length;
        }
        abyte1 = _ioContext.allocBase64Buffer();
        int i = _readBinary(base64variant, outputstream, abyte1);
        _ioContext.releaseBase64Buffer(abyte1);
        return i;
        Exception exception;
        exception;
        _ioContext.releaseBase64Buffer(abyte1);
        throw exception;
    }

    public int releaseBuffered(OutputStream outputstream)
        throws IOException
    {
        int i = _inputEnd - _inputPtr;
        if(i < 1)
        {
            return 0;
        } else
        {
            int j = _inputPtr;
            outputstream.write(_inputBuffer, j, i);
            return i;
        }
    }

    public void setCodec(ObjectCodec objectcodec)
    {
        _objectCodec = objectcodec;
    }

    protected Name slowParseName()
        throws IOException
    {
        if(_inputPtr >= _inputEnd && !loadMore())
            _reportInvalidEOF(": was expecting closing '\"' for name");
        byte abyte0[] = _inputBuffer;
        int i = _inputPtr;
        _inputPtr = i + 1;
        int j = 0xff & abyte0[i];
        if(j == 34)
            return BytesToNameCanonicalizer.getEmptyName();
        else
            return parseEscapedName(_quadBuffer, 0, 0, j, 0);
    }

    static final byte BYTE_LF = 10;
    protected static final int _icLatin1[] = CharTypes.getInputCodeLatin1();
    private static final int _icUTF8[] = CharTypes.getInputCodeUtf8();
    private static final int _icWS[] = CharTypes.getInputCodeWS();
    protected boolean _bufferRecyclable;
    protected byte _inputBuffer[];
    protected InputStream _inputStream;
    protected ObjectCodec _objectCodec;
    private int _quad1;
    protected int _quadBuffer[];
    protected final BytesToNameCanonicalizer _symbols;
    protected boolean _tokenIncomplete;

}
