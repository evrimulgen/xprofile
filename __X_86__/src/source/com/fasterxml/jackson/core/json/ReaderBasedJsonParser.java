// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.fasterxml.jackson.core.json;

import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.core.base.ParserBase;
import com.fasterxml.jackson.core.io.CharTypes;
import com.fasterxml.jackson.core.io.IOContext;
import com.fasterxml.jackson.core.sym.CharsToNameCanonicalizer;
import com.fasterxml.jackson.core.util.ByteArrayBuilder;
import com.fasterxml.jackson.core.util.TextBuffer;
import java.io.*;

// Referenced classes of package com.fasterxml.jackson.core.json:
//            JsonReadContext

public final class ReaderBasedJsonParser extends ParserBase
{

    public ReaderBasedJsonParser(IOContext iocontext, int i, Reader reader, ObjectCodec objectcodec, CharsToNameCanonicalizer charstonamecanonicalizer)
    {
        super(iocontext, i);
        _tokenIncomplete = false;
        _reader = reader;
        _inputBuffer = iocontext.allocTokenBuffer();
        _objectCodec = objectcodec;
        _symbols = charstonamecanonicalizer;
        _hashSeed = charstonamecanonicalizer.hashSeed();
    }

    private String _handleOddName2(int i, int j, int ai[])
        throws IOException
    {
        char ac[];
        int k;
        int l;
        _textBuffer.resetWithShared(_inputBuffer, i, _inputPtr - i);
        ac = _textBuffer.getCurrentSegment();
        k = _textBuffer.getCurrentSegmentSize();
        l = ai.length;
_L5:
        if(_inputPtr < _inputEnd || loadMore()) goto _L2; else goto _L1
_L1:
        char c;
        _textBuffer.setCurrentLength(k);
        TextBuffer textbuffer = _textBuffer;
        char ac1[] = textbuffer.getTextBuffer();
        int i1 = textbuffer.getTextOffset();
        int j1 = textbuffer.size();
        return _symbols.findSymbol(ac1, i1, j1, j);
_L2:
        int k1;
        if((c = _inputBuffer[_inputPtr]) > l ? Character.isJavaIdentifierPart(c) : ai[c] == 0) goto _L3; else goto _L1
_L3:
        _inputPtr = 1 + _inputPtr;
        j = c + j * 33;
        k1 = k + 1;
        ac[k] = c;
        if(k1 >= ac.length)
        {
            ac = _textBuffer.finishCurrentSegment();
            k = 0;
        } else
        {
            k = k1;
        }
        if(true) goto _L5; else goto _L4
_L4:
    }

    private JsonToken _nextAfterName()
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

    private String _parseName2(int i, int j, int k)
        throws IOException
    {
        char ac[];
        int l;
        _textBuffer.resetWithShared(_inputBuffer, i, _inputPtr - i);
        ac = _textBuffer.getCurrentSegment();
        l = _textBuffer.getCurrentSegmentSize();
_L3:
        char c;
        if(_inputPtr >= _inputEnd && !loadMore())
            _reportInvalidEOF((new StringBuilder()).append(": was expecting closing '").append((char)k).append("' for name").toString());
        char ac1[] = _inputBuffer;
        int i1 = _inputPtr;
        _inputPtr = i1 + 1;
        c = ac1[i1];
        if(c > '\\')
            break MISSING_BLOCK_LABEL_252;
        if(c != '\\') goto _L2; else goto _L1
_L1:
        char c1 = _decodeEscaped();
_L4:
        j = c + j * 33;
        int j1 = l + 1;
        ac[l] = c1;
        TextBuffer textbuffer;
        char ac2[];
        int k1;
        int l1;
        if(j1 >= ac.length)
        {
            ac = _textBuffer.finishCurrentSegment();
            l = 0;
        } else
        {
            l = j1;
        }
        if(true) goto _L3; else goto _L2
_L2:
        if(c <= k)
        {
            if(c == k)
            {
                _textBuffer.setCurrentLength(l);
                textbuffer = _textBuffer;
                ac2 = textbuffer.getTextBuffer();
                k1 = textbuffer.getTextOffset();
                l1 = textbuffer.size();
                return _symbols.findSymbol(ac2, k1, l1, j);
            }
            if(c < ' ')
                _throwUnquotedSpace(c, "name");
        }
        c1 = c;
          goto _L4
    }

    private JsonToken _parseNumber2(boolean flag)
        throws IOException
    {
        char ac[] = _textBuffer.emptyAndGetCurrentSegment();
        int i;
        char c;
        int j;
        char c1;
        char ac1[];
        char c2;
        int k;
        int l;
        int i1;
        char ac2[];
        boolean flag1;
        int j1;
        char ac3[];
        int k1;
        boolean flag2;
        int l1;
        int i2;
        char c3;
        int j2;
        int k2;
        char c4;
        int l2;
        char c5;
        int i3;
        char c6;
        int j3;
        int k3;
        boolean flag3;
        int l3;
        int i4;
        int j4;
        char ac4[];
        int k4;
        char ac5[];
        int l4;
        char ac6[];
        int i5;
        int j5;
        char ac7[];
        int k5;
        int l5;
        int i6;
        char ac8[];
        int j6;
        boolean flag4;
        boolean flag5;
        int k6;
        int l6;
        char ac9[];
        int i7;
        if(flag)
        {
            ac[0] = '-';
            i = 1;
        } else
        {
            i = 0;
        }
        if(_inputPtr < _inputEnd)
        {
            char ac10[] = _inputBuffer;
            int j7 = _inputPtr;
            _inputPtr = j7 + 1;
            c = ac10[j7];
        } else
        {
            c = getNextChar("No digit following minus sign");
        }
        if(c == '0')
            c = _verifyNoLeadingZeroes();
        j = 0;
        c1 = c;
        ac1 = ac;
        c2 = c1;
        if(c2 < '0' || c2 > '9')
            break MISSING_BLOCK_LABEL_918;
        j++;
        if(i >= ac1.length)
        {
            ac1 = _textBuffer.finishCurrentSegment();
            i = 0;
        }
        l6 = i + 1;
        ac1[i] = c2;
        if(_inputPtr < _inputEnd || loadMore()) goto _L2; else goto _L1
_L1:
        flag1 = true;
        i1 = 0;
        k = j;
        ac2 = ac1;
        l = l6;
_L16:
        if(k == 0)
            reportInvalidNumber((new StringBuilder()).append("Missing integer part (next char ").append(_getCharDesc(i1)).append(")").toString());
        if(i1 != 46) goto _L4; else goto _L3
_L3:
        j5 = l + 1;
        ac2[l] = i1;
        ac7 = ac2;
        k5 = j5;
        l5 = i1;
        i6 = 0;
_L11:
        if(_inputPtr >= _inputEnd && !loadMore())
        {
            j1 = l5;
            flag4 = true;
        } else
        {
            ac8 = _inputBuffer;
            j6 = _inputPtr;
            _inputPtr = j6 + 1;
            l5 = ac8[j6];
            if(l5 >= 48)
            {
label0:
                {
                    if(l5 <= 57)
                        break label0;
                    j1 = l5;
                    flag4 = flag1;
                }
            } else
            {
                j1 = l5;
                flag4 = flag1;
            }
        }
        if(i6 == 0)
            reportUnexpectedNumberChar(j1, "Decimal point not followed by a digit");
        l1 = i6;
        k1 = k5;
        flag5 = flag4;
        ac3 = ac7;
        flag2 = flag5;
_L14:
        if(j1 != 101 && j1 != 69) goto _L6; else goto _L5
_L5:
        if(k1 >= ac3.length)
        {
            ac3 = _textBuffer.finishCurrentSegment();
            k1 = 0;
        }
        i2 = k1 + 1;
        ac3[k1] = j1;
        if(_inputPtr < _inputEnd)
        {
            ac6 = _inputBuffer;
            i5 = _inputPtr;
            _inputPtr = i5 + 1;
            c3 = ac6[i5];
        } else
        {
            c3 = getNextChar("expected a digit for number exponent");
        }
        if(c3 == '-' || c3 == '+')
        {
            if(i2 >= ac3.length)
            {
                ac3 = _textBuffer.finishCurrentSegment();
                j2 = 0;
            } else
            {
                j2 = i2;
            }
            k2 = j2 + 1;
            ac3[j2] = c3;
            if(_inputPtr < _inputEnd)
            {
                ac5 = _inputBuffer;
                l4 = _inputPtr;
                _inputPtr = l4 + 1;
                c4 = ac5[l4];
            } else
            {
                c4 = getNextChar("expected a digit for number exponent");
            }
            l2 = 0;
            c5 = c4;
            i3 = k2;
            c6 = c5;
        } else
        {
            i3 = i2;
            c6 = c3;
            l2 = 0;
        }
        if(c6 > '9' || c6 < '0') goto _L8; else goto _L7
_L7:
        l2++;
        if(i3 >= ac3.length)
        {
            ac3 = _textBuffer.finishCurrentSegment();
            i3 = 0;
        }
        j4 = i3 + 1;
        ac3[i3] = c6;
        if(_inputPtr < _inputEnd || loadMore()) goto _L10; else goto _L9
_L9:
        j3 = l2;
        flag3 = true;
        k3 = j4;
_L12:
        if(j3 == 0)
            reportUnexpectedNumberChar(c6, "Exponent indicator not followed by a digit");
        l3 = k3;
        i4 = c6;
_L13:
        if(!flag3)
        {
            _inputPtr = -1 + _inputPtr;
            if(_parsingContext.inRoot())
                _verifyRootSpace(i4);
        }
        _textBuffer.setCurrentLength(l3);
        return reset(flag, k, l1, j3);
_L2:
        ac9 = _inputBuffer;
        i7 = _inputPtr;
        _inputPtr = i7 + 1;
        c2 = ac9[i7];
        i = l6;
        break MISSING_BLOCK_LABEL_84;
        i6++;
        if(k5 >= ac7.length)
        {
            ac7 = _textBuffer.finishCurrentSegment();
            k6 = 0;
        } else
        {
            k6 = k5;
        }
        k5 = k6 + 1;
        ac7[k6] = l5;
          goto _L11
_L10:
        ac4 = _inputBuffer;
        k4 = _inputPtr;
        _inputPtr = k4 + 1;
        c6 = ac4[k4];
        i3 = j4;
        break MISSING_BLOCK_LABEL_481;
_L8:
        j3 = l2;
        k3 = i3;
        flag3 = flag2;
          goto _L12
_L6:
        i4 = j1;
        l3 = k1;
        flag3 = flag2;
        j3 = 0;
          goto _L13
_L4:
        j1 = i1;
        ac3 = ac2;
        k1 = l;
        flag2 = flag1;
        l1 = 0;
          goto _L14
        k = j;
        l = i;
        i1 = c2;
        ac2 = ac1;
        flag1 = false;
        if(true) goto _L16; else goto _L15
_L15:
    }

    private void _skipCComment()
        throws IOException
    {
        do
        {
            char c;
label0:
            {
label1:
                {
                    if(_inputPtr < _inputEnd || loadMore())
                    {
                        char ac[] = _inputBuffer;
                        int i = _inputPtr;
                        _inputPtr = i + 1;
                        c = ac[i];
                        if(c > '*')
                            continue;
                        if(c != '*')
                            break label0;
                        if(_inputPtr < _inputEnd || loadMore())
                            break label1;
                    }
                    _reportInvalidEOF(" in a comment");
                    return;
                }
                if(_inputBuffer[_inputPtr] == '/')
                {
                    _inputPtr = 1 + _inputPtr;
                    return;
                }
                continue;
            }
            if(c < ' ')
                if(c == '\n')
                {
                    _currInputRow = 1 + _currInputRow;
                    _currInputRowStart = _inputPtr;
                } else
                if(c == '\r')
                    _skipCR();
                else
                if(c != '\t')
                    _throwInvalidSpace(c);
        } while(true);
    }

    private void _skipComment()
        throws IOException
    {
        if(!isEnabled(com.fasterxml.jackson.core.JsonParser.Feature.ALLOW_COMMENTS))
            _reportUnexpectedChar(47, "maybe a (non-standard) comment? (not recognized as one since Feature 'ALLOW_COMMENTS' not enabled for parser)");
        if(_inputPtr >= _inputEnd && !loadMore())
            _reportInvalidEOF(" in a comment");
        char ac[] = _inputBuffer;
        int i = _inputPtr;
        _inputPtr = i + 1;
        char c = ac[i];
        if(c == '/')
        {
            _skipLine();
            return;
        }
        if(c == '*')
        {
            _skipCComment();
            return;
        } else
        {
            _reportUnexpectedChar(c, "was expecting either '*' or '/' for a comment");
            return;
        }
    }

    private void _skipLine()
        throws IOException
    {
        do
        {
            char c;
label0:
            {
                if(_inputPtr < _inputEnd || loadMore())
                {
                    char ac[] = _inputBuffer;
                    int i = _inputPtr;
                    _inputPtr = i + 1;
                    c = ac[i];
                    if(c >= ' ')
                        continue;
                    if(c != '\n')
                        break label0;
                    _currInputRow = 1 + _currInputRow;
                    _currInputRowStart = _inputPtr;
                }
                return;
            }
            if(c == '\r')
            {
                _skipCR();
                return;
            }
            if(c != '\t')
                _throwInvalidSpace(c);
        } while(true);
    }

    private int _skipWS()
        throws IOException
    {
        int ai[] = _icWS;
_L5:
        if(_inputPtr >= _inputEnd && !loadMore()) goto _L2; else goto _L1
_L1:
        char c;
        char ac[] = _inputBuffer;
        int i = _inputPtr;
        _inputPtr = i + 1;
        c = ac[i];
        if(c < '@') goto _L4; else goto _L3
_L3:
        return c;
_L4:
        ai[c];
        JVM INSTR lookupswitch 7: default 124
    //                   -1: 127
    //                   0: 51
    //                   1: 4
    //                   10: 136
    //                   13: 157
    //                   35: 171
    //                   47: 164;
           goto _L5 _L6 _L3 _L5 _L7 _L8 _L9 _L10
_L9:
        continue; /* Loop/switch isn't completed */
_L6:
        _throwInvalidSpace(c);
        return c;
_L7:
        _currInputRow = 1 + _currInputRow;
        _currInputRowStart = _inputPtr;
          goto _L5
_L8:
        _skipCR();
          goto _L5
_L10:
        _skipComment();
          goto _L5
        if(_skipYAMLComment()) goto _L5; else goto _L11
_L11:
        return c;
_L2:
        throw _constructError((new StringBuilder()).append("Unexpected end-of-input within/between ").append(_parsingContext.getTypeDesc()).append(" entries").toString());
    }

    private int _skipWSOrEnd()
        throws IOException
    {
        int ai[] = _icWS;
_L5:
        if(_inputPtr >= _inputEnd && !loadMore()) goto _L2; else goto _L1
_L1:
        char c;
        char ac[] = _inputBuffer;
        int i = _inputPtr;
        _inputPtr = i + 1;
        c = ac[i];
        if(c < '@') goto _L4; else goto _L3
_L3:
        return c;
_L4:
        ai[c];
        JVM INSTR lookupswitch 7: default 124
    //                   -1: 127
    //                   0: 51
    //                   1: 4
    //                   10: 136
    //                   13: 157
    //                   35: 171
    //                   47: 164;
           goto _L5 _L6 _L3 _L5 _L7 _L8 _L9 _L10
_L9:
        continue; /* Loop/switch isn't completed */
_L6:
        _throwInvalidSpace(c);
        return c;
_L7:
        _currInputRow = 1 + _currInputRow;
        _currInputRowStart = _inputPtr;
          goto _L5
_L8:
        _skipCR();
          goto _L5
_L10:
        _skipComment();
          goto _L5
        if(_skipYAMLComment()) goto _L5; else goto _L11
_L11:
        return c;
_L2:
        _handleEOF();
        return -1;
    }

    private boolean _skipYAMLComment()
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

    private char _verifyNoLeadingZeroes()
        throws IOException
    {
        if(_inputPtr < _inputEnd || loadMore()) goto _L2; else goto _L1
_L1:
        char c = '0';
_L4:
        return c;
_L2:
        c = _inputBuffer[_inputPtr];
        if(c < '0' || c > '9')
            return '0';
        if(!isEnabled(com.fasterxml.jackson.core.JsonParser.Feature.ALLOW_NUMERIC_LEADING_ZEROS))
            reportInvalidNumber("Leading zeroes not allowed");
        _inputPtr = 1 + _inputPtr;
        if(c != '0')
            continue; /* Loop/switch isn't completed */
        do
        {
            if(_inputPtr >= _inputEnd && !loadMore())
                continue; /* Loop/switch isn't completed */
            c = _inputBuffer[_inputPtr];
            if(c < '0' || c > '9')
                return '0';
            _inputPtr = 1 + _inputPtr;
        } while(c == '0');
        break; /* Loop/switch isn't completed */
        if(true) goto _L4; else goto _L3
_L3:
        return c;
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

    protected void _closeInput()
        throws IOException
    {
        if(_reader != null)
        {
            if(_ioContext.isResourceManaged() || isEnabled(com.fasterxml.jackson.core.JsonParser.Feature.AUTO_CLOSE_SOURCE))
                _reader.close();
            _reader = null;
        }
    }

    protected byte[] _decodeBase64(Base64Variant base64variant)
        throws IOException
    {
        ByteArrayBuilder bytearraybuilder = _getByteArrayBuilder();
        do
        {
            char c;
            do
            {
                if(_inputPtr >= _inputEnd)
                    loadMoreGuaranteed();
                char ac[] = _inputBuffer;
                int i = _inputPtr;
                _inputPtr = i + 1;
                c = ac[i];
            } while(c <= ' ');
            int j = base64variant.decodeBase64Char(c);
            if(j < 0)
            {
                if(c == '"')
                    return bytearraybuilder.toByteArray();
                j = _decodeBase64Escape(base64variant, c, 0);
                if(j < 0)
                    continue;
            }
            if(_inputPtr >= _inputEnd)
                loadMoreGuaranteed();
            char ac1[] = _inputBuffer;
            int k = _inputPtr;
            _inputPtr = k + 1;
            char c1 = ac1[k];
            int l = base64variant.decodeBase64Char(c1);
            if(l < 0)
                l = _decodeBase64Escape(base64variant, c1, 1);
            int i1 = l | j << 6;
            if(_inputPtr >= _inputEnd)
                loadMoreGuaranteed();
            char ac2[] = _inputBuffer;
            int j1 = _inputPtr;
            _inputPtr = j1 + 1;
            char c2 = ac2[j1];
            int k1 = base64variant.decodeBase64Char(c2);
            if(k1 < 0)
            {
                if(k1 != -2)
                {
                    if(c2 == '"' && !base64variant.usesPadding())
                    {
                        bytearraybuilder.append(i1 >> 4);
                        return bytearraybuilder.toByteArray();
                    }
                    k1 = _decodeBase64Escape(base64variant, c2, 2);
                }
                if(k1 == -2)
                {
                    if(_inputPtr >= _inputEnd)
                        loadMoreGuaranteed();
                    char ac4[] = _inputBuffer;
                    int k2 = _inputPtr;
                    _inputPtr = k2 + 1;
                    char c4 = ac4[k2];
                    if(!base64variant.usesPaddingChar(c4))
                        throw reportInvalidBase64Char(base64variant, c4, 3, (new StringBuilder()).append("expected padding character '").append(base64variant.getPaddingChar()).append("'").toString());
                    bytearraybuilder.append(i1 >> 4);
                    continue;
                }
            }
            int l1 = k1 | i1 << 6;
            if(_inputPtr >= _inputEnd)
                loadMoreGuaranteed();
            char ac3[] = _inputBuffer;
            int i2 = _inputPtr;
            _inputPtr = i2 + 1;
            char c3 = ac3[i2];
            int j2 = base64variant.decodeBase64Char(c3);
            if(j2 < 0)
            {
                if(j2 != -2)
                {
                    if(c3 == '"' && !base64variant.usesPadding())
                    {
                        bytearraybuilder.appendTwoBytes(l1 >> 2);
                        return bytearraybuilder.toByteArray();
                    }
                    j2 = _decodeBase64Escape(base64variant, c3, 3);
                }
                if(j2 == -2)
                {
                    bytearraybuilder.appendTwoBytes(l1 >> 2);
                    continue;
                }
            }
            bytearraybuilder.appendThreeBytes(j2 | l1 << 6);
        } while(true);
    }

    protected char _decodeEscaped()
        throws IOException
    {
        int i = 0;
        if(_inputPtr >= _inputEnd && !loadMore())
            _reportInvalidEOF(" in character escape sequence");
        char ac[] = _inputBuffer;
        int j = _inputPtr;
        _inputPtr = j + 1;
        char c = ac[j];
        int k;
        switch(c)
        {
        default:
            c = _handleUnrecognizedCharacterEscape(c);
            // fall through

        case 34: // '"'
        case 47: // '/'
        case 92: // '\\'
            return c;

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

        case 117: // 'u'
            k = 0;
            break;
        }
        for(; k < 4; k++)
        {
            if(_inputPtr >= _inputEnd && !loadMore())
                _reportInvalidEOF(" in character escape sequence");
            char ac1[] = _inputBuffer;
            int l = _inputPtr;
            _inputPtr = l + 1;
            char c1 = ac1[l];
            int i1 = CharTypes.charToHex(c1);
            if(i1 < 0)
                _reportUnexpectedChar(c1, "expected a hex-digit for character escape sequence");
            i = i1 | i << 4;
        }

        return (char)i;
    }

    protected void _finishString()
        throws IOException
    {
        int i = _inputPtr;
        int j = _inputEnd;
        if(i < j)
        {
            int ai[] = _icLatin1;
            int k = ai.length;
            do
            {
                char c = _inputBuffer[i];
                if(c >= k || ai[c] == 0)
                    continue;
                if(c == '"')
                {
                    _textBuffer.resetWithShared(_inputBuffer, _inputPtr, i - _inputPtr);
                    _inputPtr = i + 1;
                    return;
                }
                break;
            } while(++i < j);
        }
        _textBuffer.resetWithCopy(_inputBuffer, _inputPtr, i - _inputPtr);
        _inputPtr = i;
        _finishString2();
    }

    protected void _finishString2()
        throws IOException
    {
        char ac[];
        int i;
        ac = _textBuffer.getCurrentSegment();
        i = _textBuffer.getCurrentSegmentSize();
_L5:
        char c;
        if(_inputPtr >= _inputEnd && !loadMore())
            _reportInvalidEOF(": was expecting closing quote for a string value");
        char ac1[] = _inputBuffer;
        int j = _inputPtr;
        _inputPtr = j + 1;
        c = ac1[j];
        if(c > '\\') goto _L2; else goto _L1
_L1:
        if(c != '\\') goto _L4; else goto _L3
_L3:
        c = _decodeEscaped();
_L2:
        int k;
        if(i >= ac.length)
        {
            ac = _textBuffer.finishCurrentSegment();
            k = 0;
        } else
        {
            k = i;
        }
        i = k + 1;
        ac[k] = c;
        if(true) goto _L5; else goto _L4
_L4:
        if(c <= '"')
        {
            if(c == '"')
            {
                _textBuffer.setCurrentLength(i);
                return;
            }
            if(c < ' ')
                _throwUnquotedSpace(c, "string value");
        }
          goto _L2
    }

    protected String _getText2(JsonToken jsontoken)
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
        int i;
        ac = _textBuffer.emptyAndGetCurrentSegment();
        i = _textBuffer.getCurrentSegmentSize();
_L5:
        char c;
        if(_inputPtr >= _inputEnd && !loadMore())
            _reportInvalidEOF(": was expecting closing quote for a string value");
        char ac1[] = _inputBuffer;
        int j = _inputPtr;
        _inputPtr = j + 1;
        c = ac1[j];
        if(c > '\\') goto _L2; else goto _L1
_L1:
        if(c != '\\') goto _L4; else goto _L3
_L3:
        c = _decodeEscaped();
_L2:
        int k;
        if(i >= ac.length)
        {
            ac = _textBuffer.finishCurrentSegment();
            k = 0;
        } else
        {
            k = i;
        }
        i = k + 1;
        ac[k] = c;
        if(true) goto _L5; else goto _L4
_L4:
        if(c <= '\'')
        {
            if(c == '\'')
            {
                _textBuffer.setCurrentLength(i);
                return JsonToken.VALUE_STRING;
            }
            if(c < ' ')
                _throwUnquotedSpace(c, "string value");
        }
          goto _L2
    }

    protected JsonToken _handleInvalidNumberStart(int i, boolean flag)
        throws IOException
    {
        double d = (-1.0D / 0.0D);
        if(i != 73) goto _L2; else goto _L1
_L1:
        if(_inputPtr >= _inputEnd && !loadMore())
            _reportInvalidEOFInValue();
        char ac[] = _inputBuffer;
        int j = _inputPtr;
        _inputPtr = j + 1;
        i = ac[j];
        if(i != 78) goto _L4; else goto _L3
_L3:
        String s1;
        if(flag)
            s1 = "-INF";
        else
            s1 = "+INF";
        _matchToken(s1, 3);
        if(isEnabled(com.fasterxml.jackson.core.JsonParser.Feature.ALLOW_NON_NUMERIC_NUMBERS))
        {
            if(!flag)
                d = (1.0D / 0.0D);
            return resetAsNaN(s1, d);
        }
        _reportError((new StringBuilder()).append("Non-standard token '").append(s1).append("': enable JsonParser.Feature.ALLOW_NON_NUMERIC_NUMBERS to allow").toString());
_L2:
        reportUnexpectedNumberChar(i, "expected digit (0-9) to follow minus sign, for valid numeric value");
        return null;
_L4:
        if(i == 110)
        {
            String s;
            if(flag)
                s = "-Infinity";
            else
                s = "+Infinity";
            _matchToken(s, 3);
            if(isEnabled(com.fasterxml.jackson.core.JsonParser.Feature.ALLOW_NON_NUMERIC_NUMBERS))
            {
                if(!flag)
                    d = (1.0D / 0.0D);
                return resetAsNaN(s, d);
            }
            _reportError((new StringBuilder()).append("Non-standard token '").append(s).append("': enable JsonParser.Feature.ALLOW_NON_NUMERIC_NUMBERS to allow").toString());
        }
        if(true) goto _L2; else goto _L5
_L5:
    }

    protected String _handleOddName(int i)
        throws IOException
    {
        if(i == 39 && isEnabled(com.fasterxml.jackson.core.JsonParser.Feature.ALLOW_SINGLE_QUOTES))
            return _parseAposName();
        if(!isEnabled(com.fasterxml.jackson.core.JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES))
            _reportUnexpectedChar(i, "was expecting double-quote to start field name");
        int ai[] = CharTypes.getInputCodeLatin1JsNames();
        int j = ai.length;
        boolean flag;
        int k;
        int l;
        int i1;
        if(i < j)
        {
            if(ai[i] == 0)
                flag = true;
            else
                flag = false;
        } else
        {
            flag = Character.isJavaIdentifierPart((char)i);
        }
        if(!flag)
            _reportUnexpectedChar(i, "was expecting either valid name character (for unquoted name) or double-quote (for quoted) to start field name");
        k = _inputPtr;
        l = _hashSeed;
        i1 = _inputEnd;
        if(k < i1)
            do
            {
                char c = _inputBuffer[k];
                if(c < j)
                {
                    if(ai[c] != 0)
                    {
                        int l1 = -1 + _inputPtr;
                        _inputPtr = k;
                        return _symbols.findSymbol(_inputBuffer, l1, k - l1, l);
                    }
                } else
                if(!Character.isJavaIdentifierPart(c))
                {
                    int k1 = -1 + _inputPtr;
                    _inputPtr = k;
                    return _symbols.findSymbol(_inputBuffer, k1, k - k1, l);
                }
                l = c + l * 33;
            } while(++k < i1);
        int j1 = -1 + _inputPtr;
        _inputPtr = k;
        return _handleOddName2(j1, l, ai);
    }

    protected JsonToken _handleOddValue(int i)
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
        char ac[] = _inputBuffer;
        int j = _inputPtr;
        _inputPtr = j + 1;
        return _handleInvalidNumberStart(ac[j], false);
    }

    protected void _matchToken(String s, int i)
        throws IOException
    {
        int j = s.length();
        do
        {
            if(_inputPtr >= _inputEnd && !loadMore())
                _reportInvalidToken(s.substring(0, i));
            if(_inputBuffer[_inputPtr] != s.charAt(i))
                _reportInvalidToken(s.substring(0, i));
            _inputPtr = 1 + _inputPtr;
        } while(++i < j);
        char c;
        if(_inputPtr < _inputEnd || loadMore())
            if((c = _inputBuffer[_inputPtr]) >= '0' && c != ']' && c != '}' && Character.isJavaIdentifierPart(c))
            {
                _reportInvalidToken(s.substring(0, i));
                return;
            }
    }

    protected String _parseAposName()
        throws IOException
    {
        int i;
        int j;
        int k;
        i = _inputPtr;
        j = _hashSeed;
        k = _inputEnd;
        if(i >= k) goto _L2; else goto _L1
_L1:
        int ai[];
        int i1;
        ai = _icLatin1;
        i1 = ai.length;
_L4:
        char c;
        c = _inputBuffer[i];
        if(c == '\'')
        {
            int j1 = _inputPtr;
            _inputPtr = i + 1;
            return _symbols.findSymbol(_inputBuffer, j1, i - j1, j);
        }
        if(c >= i1 || ai[c] == 0) goto _L3; else goto _L2
_L2:
        int l = _inputPtr;
        _inputPtr = i;
        return _parseName2(l, j, 39);
_L3:
        j = c + j * 33;
        if(++i < k) goto _L4; else goto _L2
    }

    protected String _parseName(int i)
        throws IOException
    {
        if(i != 34)
            return _handleOddName(i);
        int j = _inputPtr;
        int k = _hashSeed;
        int l = _inputEnd;
        if(j < l)
        {
            int ai[] = _icLatin1;
            int j1 = ai.length;
            do
            {
                char c = _inputBuffer[j];
                if(c < j1 && ai[c] != 0)
                {
                    if(c == '"')
                    {
                        int k1 = _inputPtr;
                        _inputPtr = j + 1;
                        return _symbols.findSymbol(_inputBuffer, k1, j - k1, k);
                    }
                    break;
                }
                k = c + k * 33;
            } while(++j < l);
        }
        int i1 = _inputPtr;
        _inputPtr = j;
        return _parseName2(i1, k, 34);
    }

    protected JsonToken _parseNumber(int i)
        throws IOException
    {
        int j;
        int k;
        boolean flag;
        int l;
        int i1;
        int j1;
        j = 1;
        k = 0;
        if(i == 45)
            flag = j;
        else
            flag = false;
        l = _inputPtr;
        i1 = l - 1;
        j1 = _inputEnd;
        if(!flag) goto _L2; else goto _L1
_L1:
        if(l < _inputEnd) goto _L4; else goto _L3
_L3:
        int l1;
        char c;
        char c1;
        int i2;
        int j2;
        int i3;
        char c2;
        int k1;
        char ac[];
        int k2;
        int l2;
        char ac1[];
        char ac2[];
        char ac3[];
        int j3;
        int k3;
        int l3;
        int i4;
        char ac4[];
        int j4;
        char c3;
        int k4;
        char ac5[];
        if(flag)
            k3 = i1 + 1;
        else
            k3 = i1;
        _inputPtr = k3;
        return _parseNumber2(flag);
_L4:
        ac5 = _inputBuffer;
        k1 = l + 1;
        i = ac5[l];
        if(i > 57 || i < 48)
        {
            _inputPtr = k1;
            return _handleInvalidNumberStart(i, j);
        }
        continue; /* Loop/switch isn't completed */
_L2:
        k1 = l;
        if(i == 48) goto _L3; else goto _L5
_L5:
        if(k1 >= _inputEnd) goto _L3; else goto _L6
_L6:
        ac = _inputBuffer;
        l1 = k1 + 1;
        c = ac[k1];
        if(c >= '0' && c <= '9') goto _L8; else goto _L7
_L7:
        if(c != '.')
            break MISSING_BLOCK_LABEL_498;
        l3 = 0;
        i4 = l1;
_L22:
        if(i4 >= j1) goto _L3; else goto _L9
_L9:
        ac4 = _inputBuffer;
        j4 = i4 + 1;
        c3 = ac4[i4];
        if(c3 >= '0' && c3 <= '9') goto _L11; else goto _L10
_L10:
        if(l3 == 0)
            reportUnexpectedNumberChar(c3, "Decimal point not followed by a digit");
        k4 = l3;
        i2 = j4;
        c1 = c3;
        j2 = k4;
_L23:
        if(c1 == 'e')
            continue; /* Loop/switch isn't completed */
        k = 0;
        if(c1 != 'E') goto _L13; else goto _L12
_L12:
        if(i2 >= j1) goto _L3; else goto _L14
_L14:
        ac1 = _inputBuffer;
        i3 = i2 + 1;
        c2 = ac1[i2];
        if(c2 != '-' && c2 != '+') goto _L16; else goto _L15
_L15:
        if(i3 >= j1) goto _L3; else goto _L17
_L17:
        ac2 = _inputBuffer;
        i2 = i3 + 1;
        c1 = ac2[i3];
_L21:
        if(c1 > '9' || c1 < '0') goto _L19; else goto _L18
_L18:
        k++;
        if(i2 >= j1) goto _L3; else goto _L20
_L20:
        ac3 = _inputBuffer;
        j3 = i2 + 1;
        c1 = ac3[i2];
        i2 = j3;
          goto _L21
_L8:
        j++;
        k1 = l1;
          goto _L5
_L11:
        l3++;
        i4 = j4;
          goto _L22
_L19:
        if(k == 0)
            reportUnexpectedNumberChar(c1, "Exponent indicator not followed by a digit");
_L13:
        k2 = i2 - 1;
        _inputPtr = k2;
        if(_parsingContext.inRoot())
            _verifyRootSpace(c1);
        l2 = k2 - i1;
        _textBuffer.resetWithShared(_inputBuffer, i1, l2);
        return reset(flag, j, j2, k);
_L16:
        c1 = c2;
        i2 = i3;
        k = 0;
          goto _L21
        c1 = c;
        i2 = l1;
        j2 = 0;
          goto _L23
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
        char c;
        int i1;
        do
        {
            if(_inputPtr >= _inputEnd)
                loadMoreGuaranteed();
            char ac[] = _inputBuffer;
            int l = _inputPtr;
            _inputPtr = l + 1;
            c = ac[l];
        } while(c <= ' ');
        i1 = base64variant.decodeBase64Char(c);
        if(i1 >= 0) goto _L2; else goto _L1
_L1:
        if(c != '"') goto _L4; else goto _L3
_L3:
        _tokenIncomplete = false;
        if(k > 0)
        {
            j += k;
            outputstream.write(abyte0, 0, k);
        }
        return j;
_L4:
        i1 = _decodeBase64Escape(base64variant, c, 0);
        if(i1 < 0)
            continue; /* Loop/switch isn't completed */
_L2:
        int j1 = i1;
        int k1;
        char ac1[];
        int l1;
        char c1;
        int i2;
        int j2;
        char ac2[];
        int k2;
        char c2;
        int l2;
        int i3;
        char ac3[];
        int j3;
        char c3;
        int k3;
        int l3;
        int i4;
        int j4;
        int k4;
        int l4;
        int i5;
        int j5;
        if(k > i)
        {
            j += k;
            outputstream.write(abyte0, 0, k);
            k1 = 0;
        } else
        {
            k1 = k;
        }
        if(_inputPtr >= _inputEnd)
            loadMoreGuaranteed();
        ac1 = _inputBuffer;
        l1 = _inputPtr;
        _inputPtr = l1 + 1;
        c1 = ac1[l1];
        i2 = base64variant.decodeBase64Char(c1);
        if(i2 < 0)
            i2 = _decodeBase64Escape(base64variant, c1, 1);
        j2 = i2 | j1 << 6;
        if(_inputPtr >= _inputEnd)
            loadMoreGuaranteed();
        ac2 = _inputBuffer;
        k2 = _inputPtr;
        _inputPtr = k2 + 1;
        c2 = ac2[k2];
        l2 = base64variant.decodeBase64Char(c2);
        if(l2 < 0)
        {
            if(l2 != -2)
            {
                if(c2 == '"' && !base64variant.usesPadding())
                {
                    int i6 = j2 >> 4;
                    k = k1 + 1;
                    abyte0[k1] = (byte)i6;
                    continue; /* Loop/switch isn't completed */
                }
                l2 = _decodeBase64Escape(base64variant, c2, 2);
            }
            if(l2 == -2)
            {
                if(_inputPtr >= _inputEnd)
                    loadMoreGuaranteed();
                char ac4[] = _inputBuffer;
                int k5 = _inputPtr;
                _inputPtr = k5 + 1;
                char c4 = ac4[k5];
                if(!base64variant.usesPaddingChar(c4))
                    throw reportInvalidBase64Char(base64variant, c4, 3, (new StringBuilder()).append("expected padding character '").append(base64variant.getPaddingChar()).append("'").toString());
                int l5 = j2 >> 4;
                k = k1 + 1;
                abyte0[k1] = (byte)l5;
                continue; /* Loop/switch isn't completed */
            }
        }
        i3 = l2 | j2 << 6;
        if(_inputPtr >= _inputEnd)
            loadMoreGuaranteed();
        ac3 = _inputBuffer;
        j3 = _inputPtr;
        _inputPtr = j3 + 1;
        c3 = ac3[j3];
        k3 = base64variant.decodeBase64Char(c3);
        if(k3 >= 0)
            break MISSING_BLOCK_LABEL_634;
        if(k3 == -2)
            break MISSING_BLOCK_LABEL_589;
        if(c3 != '"' || base64variant.usesPadding())
            break; /* Loop/switch isn't completed */
        i5 = i3 >> 2;
        j5 = k1 + 1;
        abyte0[k1] = (byte)(i5 >> 8);
        k = j5 + 1;
        abyte0[j5] = (byte)i5;
        if(true) goto _L3; else goto _L5
_L5:
        k3 = _decodeBase64Escape(base64variant, c3, 3);
        if(k3 == -2)
        {
            k4 = i3 >> 2;
            l4 = k1 + 1;
            abyte0[k1] = (byte)(k4 >> 8);
            k = l4 + 1;
            abyte0[l4] = (byte)k4;
            continue; /* Loop/switch isn't completed */
        }
        l3 = k3 | i3 << 6;
        i4 = k1 + 1;
        abyte0[k1] = (byte)(l3 >> 16);
        j4 = i4 + 1;
        abyte0[i4] = (byte)(l3 >> 8);
        k = j4 + 1;
        abyte0[j4] = (byte)l3;
        if(true) goto _L7; else goto _L6
_L6:
    }

    protected void _releaseBuffers()
        throws IOException
    {
        super._releaseBuffers();
        _symbols.release();
        char ac[] = _inputBuffer;
        if(ac != null)
        {
            _inputBuffer = null;
            _ioContext.releaseTokenBuffer(ac);
        }
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
        char c;
        _reportError((new StringBuilder()).append("Unrecognized token '").append(stringbuilder.toString()).append("': was expecting ").append(s1).toString());
        return;
_L2:
        if(!Character.isJavaIdentifierPart(c = _inputBuffer[_inputPtr])) goto _L1; else goto _L3
_L3:
        _inputPtr = 1 + _inputPtr;
        stringbuilder.append(c);
        if(true) goto _L5; else goto _L4
_L4:
    }

    protected void _skipCR()
        throws IOException
    {
        if((_inputPtr < _inputEnd || loadMore()) && _inputBuffer[_inputPtr] == '\n')
            _inputPtr = 1 + _inputPtr;
        _currInputRow = 1 + _currInputRow;
        _currInputRowStart = _inputPtr;
    }

    protected void _skipString()
        throws IOException
    {
        _tokenIncomplete = false;
        int i = _inputPtr;
        int j = _inputEnd;
        char ac[] = _inputBuffer;
        do
        {
            if(i >= j)
            {
                _inputPtr = i;
                if(!loadMore())
                    _reportInvalidEOF(": was expecting closing quote for a string value");
                i = _inputPtr;
                j = _inputEnd;
            }
            int k = i + 1;
            char c = ac[i];
            if(c <= '\\')
            {
                if(c == '\\')
                {
                    _inputPtr = k;
                    _decodeEscaped();
                    i = _inputPtr;
                    j = _inputEnd;
                    continue;
                }
                if(c <= '"')
                {
                    if(c == '"')
                    {
                        _inputPtr = k;
                        return;
                    }
                    if(c < ' ')
                    {
                        _inputPtr = k;
                        _throwUnquotedSpace(c, "string value");
                    }
                }
            }
            i = k;
        } while(true);
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

    public Object getInputSource()
    {
        return _reader;
    }

    protected char getNextChar(String s)
        throws IOException, JsonParseException
    {
        if(_inputPtr >= _inputEnd && !loadMore())
            _reportInvalidEOF(s);
        char ac[] = _inputBuffer;
        int i = _inputPtr;
        _inputPtr = i + 1;
        return ac[i];
    }

    public String getText()
        throws IOException, JsonParseException
    {
        JsonToken jsontoken = _currToken;
        if(jsontoken == JsonToken.VALUE_STRING)
        {
            if(_tokenIncomplete)
            {
                _tokenIncomplete = false;
                _finishString();
            }
            return _textBuffer.contentsAsString();
        } else
        {
            return _getText2(jsontoken);
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

    protected boolean loadMore()
        throws IOException
    {
        _currInputProcessed = _currInputProcessed + (long)_inputEnd;
        _currInputRowStart = _currInputRowStart - _inputEnd;
        Reader reader = _reader;
        boolean flag = false;
        if(reader != null)
        {
            int i = _reader.read(_inputBuffer, 0, _inputBuffer.length);
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
                    throw new IOException((new StringBuilder()).append("Reader returned 0 characters when trying to read ").append(_inputEnd).toString());
            }
        }
        return flag;
    }

    public Boolean nextBooleanValue()
        throws IOException, JsonParseException
    {
        if(_currToken != JsonToken.FIELD_NAME) goto _L2; else goto _L1
_L1:
        JsonToken jsontoken1;
        _nameCopied = false;
        jsontoken1 = _nextToken;
        _nextToken = null;
        _currToken = jsontoken1;
        if(jsontoken1 != JsonToken.VALUE_TRUE) goto _L4; else goto _L3
_L3:
        Boolean boolean1 = Boolean.TRUE;
_L6:
        return boolean1;
_L4:
        if(jsontoken1 == JsonToken.VALUE_FALSE)
            return Boolean.FALSE;
        if(jsontoken1 == JsonToken.START_ARRAY)
        {
            _parsingContext = _parsingContext.createChildArrayContext(_tokenInputRow, _tokenInputCol);
            return null;
        }
        JsonToken jsontoken2 = JsonToken.START_OBJECT;
        boolean1 = null;
        if(jsontoken1 == jsontoken2)
        {
            _parsingContext = _parsingContext.createChildObjectContext(_tokenInputRow, _tokenInputCol);
            return null;
        }
        continue; /* Loop/switch isn't completed */
_L2:
        JsonToken jsontoken = nextToken();
        boolean1 = null;
        if(jsontoken != null)
        {
            int i = jsontoken.id();
            if(i == 9)
                return Boolean.TRUE;
            boolean1 = null;
            if(i == 10)
                return Boolean.FALSE;
        }
        if(true) goto _L6; else goto _L5
_L5:
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
        int i;
        boolean flag;
        _numTypesValid = 0;
        if(_currToken == JsonToken.FIELD_NAME)
            return _nextAfterName();
        if(_tokenIncomplete)
            _skipString();
        i = _skipWSOrEnd();
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
        flag = _parsingContext.inObject();
        if(flag)
        {
            String s = _parseName(i);
            _parsingContext.setCurrentName(s);
            _currToken = JsonToken.FIELD_NAME;
            int j = _skipWS();
            if(j != 58)
                _reportUnexpectedChar(j, "was expecting a colon to separate field name and value");
            i = _skipWS();
        }
        i;
        JVM INSTR lookupswitch 19: default 476
    //                   34: 496
    //                   45: 621
    //                   48: 621
    //                   49: 621
    //                   50: 621
    //                   51: 621
    //                   52: 621
    //                   53: 621
    //                   54: 621
    //                   55: 621
    //                   56: 621
    //                   57: 621
    //                   91: 508
    //                   93: 568
    //                   102: 591
    //                   110: 606
    //                   116: 576
    //                   123: 538
    //                   125: 568;
           goto _L1 _L2 _L3 _L3 _L3 _L3 _L3 _L3 _L3 _L3 _L3 _L3 _L3 _L4 _L5 _L6 _L7 _L8 _L9 _L5
_L3:
        break MISSING_BLOCK_LABEL_621;
_L1:
        JsonToken jsontoken = _handleOddValue(i);
_L10:
        if(flag)
        {
            _nextToken = jsontoken;
            return _currToken;
        } else
        {
            _currToken = jsontoken;
            return jsontoken;
        }
_L2:
        _tokenIncomplete = true;
        jsontoken = JsonToken.VALUE_STRING;
          goto _L10
_L4:
        if(!flag)
            _parsingContext = _parsingContext.createChildArrayContext(_tokenInputRow, _tokenInputCol);
        jsontoken = JsonToken.START_ARRAY;
          goto _L10
_L9:
        if(!flag)
            _parsingContext = _parsingContext.createChildObjectContext(_tokenInputRow, _tokenInputCol);
        jsontoken = JsonToken.START_OBJECT;
          goto _L10
_L5:
        _reportUnexpectedChar(i, "expected a value");
_L8:
        _matchToken("true", 1);
        jsontoken = JsonToken.VALUE_TRUE;
          goto _L10
_L6:
        _matchToken("false", 1);
        jsontoken = JsonToken.VALUE_FALSE;
          goto _L10
_L7:
        _matchToken("null", 1);
        jsontoken = JsonToken.VALUE_NULL;
          goto _L10
        jsontoken = _parseNumber(i);
          goto _L10
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

    public int releaseBuffered(Writer writer)
        throws IOException
    {
        int i = _inputEnd - _inputPtr;
        if(i < 1)
        {
            return 0;
        } else
        {
            int j = _inputPtr;
            writer.write(_inputBuffer, j, i);
            return i;
        }
    }

    public void setCodec(ObjectCodec objectcodec)
    {
        _objectCodec = objectcodec;
    }

    protected static final int _icLatin1[] = CharTypes.getInputCodeLatin1();
    private static final int _icWS[] = CharTypes.getInputCodeWS();
    protected final int _hashSeed;
    protected char _inputBuffer[];
    protected ObjectCodec _objectCodec;
    protected Reader _reader;
    protected final CharsToNameCanonicalizer _symbols;
    protected boolean _tokenIncomplete;

}
