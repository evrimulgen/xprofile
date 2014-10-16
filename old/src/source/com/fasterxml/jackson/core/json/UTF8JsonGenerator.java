// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.fasterxml.jackson.core.json;

import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.core.io.*;
import java.io.*;
import java.math.BigDecimal;
import java.math.BigInteger;

// Referenced classes of package com.fasterxml.jackson.core.json:
//            JsonGeneratorImpl, JsonWriteContext

public class UTF8JsonGenerator extends JsonGeneratorImpl
{

    public UTF8JsonGenerator(IOContext iocontext, int i, ObjectCodec objectcodec, OutputStream outputstream)
    {
        super(iocontext, i, objectcodec);
        _outputTail = 0;
        _outputStream = outputstream;
        _bufferRecyclable = true;
        _outputBuffer = iocontext.allocWriteEncodingBuffer();
        _outputEnd = _outputBuffer.length;
        _outputMaxContiguous = _outputEnd >> 3;
        _charBuffer = iocontext.allocConcatBuffer();
        _charBufferLength = _charBuffer.length;
        if(isEnabled(com.fasterxml.jackson.core.JsonGenerator.Feature.ESCAPE_NON_ASCII))
            setHighestNonEscapedChar(127);
        _cfgQuoteNames = com.fasterxml.jackson.core.JsonGenerator.Feature.QUOTE_FIELD_NAMES.enabledIn(i);
    }

    public UTF8JsonGenerator(IOContext iocontext, int i, ObjectCodec objectcodec, OutputStream outputstream, byte abyte0[], int j, boolean flag)
    {
        super(iocontext, i, objectcodec);
        _outputTail = 0;
        _outputStream = outputstream;
        _bufferRecyclable = flag;
        _outputTail = j;
        _outputBuffer = abyte0;
        _outputEnd = _outputBuffer.length;
        _outputMaxContiguous = _outputEnd >> 3;
        _charBuffer = iocontext.allocConcatBuffer();
        _charBufferLength = _charBuffer.length;
        _cfgQuoteNames = com.fasterxml.jackson.core.JsonGenerator.Feature.QUOTE_FIELD_NAMES.enabledIn(i);
    }

    private final int _handleLongCustomEscape(byte abyte0[], int i, int j, byte abyte1[], int k)
        throws IOException, JsonGenerationException
    {
        int l = abyte1.length;
        if(i + l <= j) goto _L2; else goto _L1
_L1:
        int i1;
        _outputTail = i;
        _flushBuffer();
        i1 = _outputTail;
        if(l <= abyte0.length) goto _L4; else goto _L3
_L3:
        _outputStream.write(abyte1, 0, l);
_L9:
        return i1;
_L4:
        System.arraycopy(abyte1, 0, abyte0, i1, l);
        i1 += l;
_L7:
        if(i1 + k * 6 > j)
        {
            _flushBuffer();
            return _outputTail;
        }
          goto _L5
_L2:
        i1 = i;
        if(true) goto _L7; else goto _L6
_L6:
_L5:
        if(true) goto _L9; else goto _L8
_L8:
    }

    private final int _outputMultiByteChar(int i, int j)
        throws IOException
    {
        byte abyte0[] = _outputBuffer;
        if(i >= 55296 && i <= 57343)
        {
            int j1 = j + 1;
            abyte0[j] = 92;
            int k1 = j1 + 1;
            abyte0[j1] = 117;
            int l1 = k1 + 1;
            abyte0[k1] = HEX_CHARS[0xf & i >> 12];
            int i2 = l1 + 1;
            abyte0[l1] = HEX_CHARS[0xf & i >> 8];
            int j2 = i2 + 1;
            abyte0[i2] = HEX_CHARS[0xf & i >> 4];
            int k2 = j2 + 1;
            abyte0[j2] = HEX_CHARS[i & 0xf];
            return k2;
        } else
        {
            int k = j + 1;
            abyte0[j] = (byte)(0xe0 | i >> 12);
            int l = k + 1;
            abyte0[k] = (byte)(0x80 | 0x3f & i >> 6);
            int i1 = l + 1;
            abyte0[l] = (byte)(0x80 | i & 0x3f);
            return i1;
        }
    }

    private final int _outputRawMultiByteChar(int i, char ac[], int j, int k)
        throws IOException
    {
        if(i >= 55296 && i <= 57343)
        {
            if(j >= k || ac == null)
                _reportError("Split surrogate on writeRaw() input (last character)");
            _outputSurrogates(i, ac[j]);
            return j + 1;
        } else
        {
            byte abyte0[] = _outputBuffer;
            int l = _outputTail;
            _outputTail = l + 1;
            abyte0[l] = (byte)(0xe0 | i >> 12);
            int i1 = _outputTail;
            _outputTail = i1 + 1;
            abyte0[i1] = (byte)(0x80 | 0x3f & i >> 6);
            int j1 = _outputTail;
            _outputTail = j1 + 1;
            abyte0[j1] = (byte)(0x80 | i & 0x3f);
            return j;
        }
    }

    private final int _readMore(InputStream inputstream, byte abyte0[], int i, int j, int k)
        throws IOException
    {
        int l;
        int i1;
        l = 0;
        int i2;
        for(; i < j; i = i2)
        {
            int l1 = l + 1;
            i2 = i + 1;
            abyte0[l] = abyte0[i];
            l = l1;
        }

        i1 = Math.min(k, abyte0.length);
_L5:
        int j1 = i1 - l;
        if(j1 != 0) goto _L2; else goto _L1
_L1:
        int k1;
        return l;
_L2:
        if((k1 = inputstream.read(abyte0, l, j1)) < 0) goto _L1; else goto _L3
_L3:
        l += k1;
        if(l >= 3)
            return l;
        if(true) goto _L5; else goto _L4
_L4:
    }

    private final void _writeBytes(byte abyte0[])
        throws IOException
    {
        int i = abyte0.length;
        if(i + _outputTail > _outputEnd)
        {
            _flushBuffer();
            if(i > 512)
            {
                _outputStream.write(abyte0, 0, i);
                return;
            }
        }
        System.arraycopy(abyte0, 0, _outputBuffer, _outputTail, i);
        _outputTail = i + _outputTail;
    }

    private final void _writeBytes(byte abyte0[], int i, int j)
        throws IOException
    {
        if(j + _outputTail > _outputEnd)
        {
            _flushBuffer();
            if(j > 512)
            {
                _outputStream.write(abyte0, i, j);
                return;
            }
        }
        System.arraycopy(abyte0, i, _outputBuffer, _outputTail, j);
        _outputTail = j + _outputTail;
    }

    private final int _writeCustomEscape(byte abyte0[], int i, SerializableString serializablestring, int j)
        throws IOException, JsonGenerationException
    {
        byte abyte1[] = serializablestring.asUnquotedUTF8();
        int k = abyte1.length;
        if(k > 6)
        {
            return _handleLongCustomEscape(abyte0, i, _outputEnd, abyte1, j);
        } else
        {
            System.arraycopy(abyte1, 0, abyte0, i, k);
            return k + i;
        }
    }

    private final void _writeCustomStringSegment2(char ac[], int i, int j)
        throws IOException, JsonGenerationException
    {
        if(_outputTail + 6 * (j - i) > _outputEnd)
            _flushBuffer();
        int k = _outputTail;
        byte abyte0[] = _outputBuffer;
        int ai[] = _outputEscapes;
        int l;
        CharacterEscapes characterescapes;
        if(_maximumNonEscapedChar <= 0)
            l = 65535;
        else
            l = _maximumNonEscapedChar;
        characterescapes = _characterEscapes;
        while(i < j) 
        {
            int i1 = i + 1;
            char c = ac[i];
            if(c <= '\177')
            {
                if(ai[c] == 0)
                {
                    int i2 = k + 1;
                    abyte0[k] = (byte)c;
                    k = i2;
                    i = i1;
                } else
                {
                    int k1 = ai[c];
                    if(k1 > 0)
                    {
                        int l1 = k + 1;
                        abyte0[k] = 92;
                        k = l1 + 1;
                        abyte0[l1] = (byte)k1;
                        i = i1;
                    } else
                    if(k1 == -2)
                    {
                        SerializableString serializablestring1 = characterescapes.getEscapeSequence(c);
                        if(serializablestring1 == null)
                            _reportError((new StringBuilder()).append("Invalid custom escape definitions; custom escape not found for character code 0x").append(Integer.toHexString(c)).append(", although was supposed to have one").toString());
                        k = _writeCustomEscape(abyte0, k, serializablestring1, j - i1);
                        i = i1;
                    } else
                    {
                        k = _writeGenericEscape(c, k);
                        i = i1;
                    }
                }
            } else
            if(c > l)
            {
                k = _writeGenericEscape(c, k);
                i = i1;
            } else
            {
                SerializableString serializablestring = characterescapes.getEscapeSequence(c);
                if(serializablestring != null)
                {
                    k = _writeCustomEscape(abyte0, k, serializablestring, j - i1);
                    i = i1;
                } else
                {
                    if(c <= '\u07FF')
                    {
                        int j1 = k + 1;
                        abyte0[k] = (byte)(0xc0 | c >> 6);
                        k = j1 + 1;
                        abyte0[j1] = (byte)(0x80 | c & 0x3f);
                    } else
                    {
                        k = _outputMultiByteChar(c, k);
                    }
                    i = i1;
                }
            }
        }
        _outputTail = k;
    }

    private int _writeGenericEscape(int i, int j)
        throws IOException
    {
        byte abyte0[] = _outputBuffer;
        int k = j + 1;
        abyte0[j] = 92;
        int l = k + 1;
        abyte0[k] = 117;
        int j1;
        int k1;
        int l1;
        if(i > 255)
        {
            int i2 = 0xff & i >> 8;
            int j2 = l + 1;
            abyte0[l] = HEX_CHARS[i2 >> 4];
            j1 = j2 + 1;
            abyte0[j2] = HEX_CHARS[i2 & 0xf];
            i &= 0xff;
        } else
        {
            int i1 = l + 1;
            abyte0[l] = 48;
            j1 = i1 + 1;
            abyte0[i1] = 48;
        }
        k1 = j1 + 1;
        abyte0[j1] = HEX_CHARS[i >> 4];
        l1 = k1 + 1;
        abyte0[k1] = HEX_CHARS[i & 0xf];
        return l1;
    }

    private void _writeLongString(String s)
        throws IOException, JsonGenerationException
    {
        if(_outputTail >= _outputEnd)
            _flushBuffer();
        byte abyte0[] = _outputBuffer;
        int i = _outputTail;
        _outputTail = i + 1;
        abyte0[i] = 34;
        _writeStringSegments(s);
        if(_outputTail >= _outputEnd)
            _flushBuffer();
        byte abyte1[] = _outputBuffer;
        int j = _outputTail;
        _outputTail = j + 1;
        abyte1[j] = 34;
    }

    private void _writeLongString(char ac[], int i, int j)
        throws IOException, JsonGenerationException
    {
        if(_outputTail >= _outputEnd)
            _flushBuffer();
        byte abyte0[] = _outputBuffer;
        int k = _outputTail;
        _outputTail = k + 1;
        abyte0[k] = 34;
        _writeStringSegments(_charBuffer, 0, j);
        if(_outputTail >= _outputEnd)
            _flushBuffer();
        byte abyte1[] = _outputBuffer;
        int l = _outputTail;
        _outputTail = l + 1;
        abyte1[l] = 34;
    }

    private final void _writeNull()
        throws IOException
    {
        if(4 + _outputTail >= _outputEnd)
            _flushBuffer();
        System.arraycopy(NULL_BYTES, 0, _outputBuffer, _outputTail, 4);
        _outputTail = 4 + _outputTail;
    }

    private final void _writeQuotedInt(int i)
        throws IOException
    {
        if(13 + _outputTail >= _outputEnd)
            _flushBuffer();
        byte abyte0[] = _outputBuffer;
        int j = _outputTail;
        _outputTail = j + 1;
        abyte0[j] = 34;
        _outputTail = NumberOutput.outputInt(i, _outputBuffer, _outputTail);
        byte abyte1[] = _outputBuffer;
        int k = _outputTail;
        _outputTail = k + 1;
        abyte1[k] = 34;
    }

    private final void _writeQuotedLong(long l)
        throws IOException
    {
        if(23 + _outputTail >= _outputEnd)
            _flushBuffer();
        byte abyte0[] = _outputBuffer;
        int i = _outputTail;
        _outputTail = i + 1;
        abyte0[i] = 34;
        _outputTail = NumberOutput.outputLong(l, _outputBuffer, _outputTail);
        byte abyte1[] = _outputBuffer;
        int j = _outputTail;
        _outputTail = j + 1;
        abyte1[j] = 34;
    }

    private final void _writeQuotedRaw(Object obj)
        throws IOException
    {
        if(_outputTail >= _outputEnd)
            _flushBuffer();
        byte abyte0[] = _outputBuffer;
        int i = _outputTail;
        _outputTail = i + 1;
        abyte0[i] = 34;
        writeRaw(obj.toString());
        if(_outputTail >= _outputEnd)
            _flushBuffer();
        byte abyte1[] = _outputBuffer;
        int j = _outputTail;
        _outputTail = j + 1;
        abyte1[j] = 34;
    }

    private final void _writeQuotedShort(short word0)
        throws IOException
    {
        if(8 + _outputTail >= _outputEnd)
            _flushBuffer();
        byte abyte0[] = _outputBuffer;
        int i = _outputTail;
        _outputTail = i + 1;
        abyte0[i] = 34;
        _outputTail = NumberOutput.outputInt(word0, _outputBuffer, _outputTail);
        byte abyte1[] = _outputBuffer;
        int j = _outputTail;
        _outputTail = j + 1;
        abyte1[j] = 34;
    }

    private final void _writeSegmentedRaw(char ac[], int i, int j)
        throws IOException, JsonGenerationException
    {
        int k = _outputEnd;
        byte abyte0[] = _outputBuffer;
        int l = i;
label0:
        do
        {
            if(l < j)
                do
                {
                    char c = ac[l];
                    if(c >= '\200')
                    {
                        if(3 + _outputTail >= _outputEnd)
                            _flushBuffer();
                        int j1 = l + 1;
                        char c1 = ac[l];
                        int i1;
                        if(c1 < '\u0800')
                        {
                            int k1 = _outputTail;
                            _outputTail = k1 + 1;
                            abyte0[k1] = (byte)(0xc0 | c1 >> 6);
                            int l1 = _outputTail;
                            _outputTail = l1 + 1;
                            abyte0[l1] = (byte)(0x80 | c1 & 0x3f);
                            l = j1;
                        } else
                        {
                            l = _outputRawMultiByteChar(c1, ac, j1, j);
                        }
                        continue label0;
                    }
                    if(_outputTail >= k)
                        _flushBuffer();
                    i1 = _outputTail;
                    _outputTail = i1 + 1;
                    abyte0[i1] = (byte)c;
                } while(++l < j);
            return;
        } while(true);
    }

    private final void _writeStringSegment(char ac[], int i, int j)
        throws IOException, JsonGenerationException
    {
        int k = j + i;
        int l = _outputTail;
        byte abyte0[] = _outputBuffer;
        int ai[] = _outputEscapes;
label0:
        do
        {
            char c;
label1:
            {
                if(i < k)
                {
                    c = ac[i];
                    if(c <= '\177' && ai[c] == 0)
                        break label1;
                }
                _outputTail = l;
                if(i < k)
                {
                    if(_characterEscapes == null)
                        break label0;
                    _writeCustomStringSegment2(ac, i, k);
                }
                return;
            }
            int i1 = l + 1;
            abyte0[l] = (byte)c;
            i++;
            l = i1;
        } while(true);
        if(_maximumNonEscapedChar == 0)
        {
            _writeStringSegment2(ac, i, k);
            return;
        } else
        {
            _writeStringSegmentASCII2(ac, i, k);
            return;
        }
    }

    private final void _writeStringSegment2(char ac[], int i, int j)
        throws IOException, JsonGenerationException
    {
        if(_outputTail + 6 * (j - i) > _outputEnd)
            _flushBuffer();
        int k = _outputTail;
        byte abyte0[] = _outputBuffer;
        int ai[] = _outputEscapes;
        while(i < j) 
        {
            int l = i + 1;
            char c = ac[i];
            if(c <= '\177')
            {
                if(ai[c] == 0)
                {
                    int l1 = k + 1;
                    abyte0[k] = (byte)c;
                    k = l1;
                    i = l;
                } else
                {
                    int j1 = ai[c];
                    if(j1 > 0)
                    {
                        int k1 = k + 1;
                        abyte0[k] = 92;
                        k = k1 + 1;
                        abyte0[k1] = (byte)j1;
                        i = l;
                    } else
                    {
                        k = _writeGenericEscape(c, k);
                        i = l;
                    }
                }
            } else
            {
                if(c <= '\u07FF')
                {
                    int i1 = k + 1;
                    abyte0[k] = (byte)(0xc0 | c >> 6);
                    k = i1 + 1;
                    abyte0[i1] = (byte)(0x80 | c & 0x3f);
                } else
                {
                    k = _outputMultiByteChar(c, k);
                }
                i = l;
            }
        }
        _outputTail = k;
    }

    private final void _writeStringSegmentASCII2(char ac[], int i, int j)
        throws IOException, JsonGenerationException
    {
        if(_outputTail + 6 * (j - i) > _outputEnd)
            _flushBuffer();
        int k = _outputTail;
        byte abyte0[] = _outputBuffer;
        int ai[] = _outputEscapes;
        int l = _maximumNonEscapedChar;
        while(i < j) 
        {
            int i1 = i + 1;
            char c = ac[i];
            if(c <= '\177')
            {
                if(ai[c] == 0)
                {
                    int i2 = k + 1;
                    abyte0[k] = (byte)c;
                    k = i2;
                    i = i1;
                } else
                {
                    int k1 = ai[c];
                    if(k1 > 0)
                    {
                        int l1 = k + 1;
                        abyte0[k] = 92;
                        k = l1 + 1;
                        abyte0[l1] = (byte)k1;
                        i = i1;
                    } else
                    {
                        k = _writeGenericEscape(c, k);
                        i = i1;
                    }
                }
            } else
            if(c > l)
            {
                k = _writeGenericEscape(c, k);
                i = i1;
            } else
            {
                if(c <= '\u07FF')
                {
                    int j1 = k + 1;
                    abyte0[k] = (byte)(0xc0 | c >> 6);
                    k = j1 + 1;
                    abyte0[j1] = (byte)(0x80 | c & 0x3f);
                } else
                {
                    k = _outputMultiByteChar(c, k);
                }
                i = i1;
            }
        }
        _outputTail = k;
    }

    private final void _writeStringSegments(String s)
        throws IOException, JsonGenerationException
    {
        int i = s.length();
        char ac[] = _charBuffer;
        int j = i;
        int k = 0;
        int l;
        for(; j > 0; j -= l)
        {
            l = Math.min(_outputMaxContiguous, j);
            s.getChars(k, k + l, ac, 0);
            if(l + _outputTail > _outputEnd)
                _flushBuffer();
            _writeStringSegment(ac, 0, l);
            k += l;
        }

    }

    private final void _writeStringSegments(char ac[], int i, int j)
        throws IOException, JsonGenerationException
    {
        do
        {
            int k = Math.min(_outputMaxContiguous, j);
            if(k + _outputTail > _outputEnd)
                _flushBuffer();
            _writeStringSegment(ac, i, k);
            i += k;
            j -= k;
        } while(j > 0);
    }

    private final void _writeUTF8Segment(byte abyte0[], int i, int j)
        throws IOException, JsonGenerationException
    {
        int ai[] = _outputEscapes;
        int k = i + j;
        int i1;
        for(int l = i; l < k; l = i1)
        {
            i1 = l + 1;
            byte byte0 = abyte0[l];
            if(byte0 >= 0 && ai[byte0] != 0)
            {
                _writeUTF8Segment2(abyte0, i, j);
                return;
            }
        }

        if(j + _outputTail > _outputEnd)
            _flushBuffer();
        System.arraycopy(abyte0, i, _outputBuffer, _outputTail, j);
        _outputTail = j + _outputTail;
    }

    private final void _writeUTF8Segment2(byte abyte0[], int i, int j)
        throws IOException, JsonGenerationException
    {
        int k = _outputTail;
        if(k + j * 6 > _outputEnd)
        {
            _flushBuffer();
            k = _outputTail;
        }
        byte abyte1[] = _outputBuffer;
        int ai[] = _outputEscapes;
        for(int l = j + i; i < l;)
        {
            int i1 = i + 1;
            byte byte0 = abyte0[i];
            if(byte0 < 0 || ai[byte0] == 0)
            {
                int j1 = k + 1;
                abyte1[k] = byte0;
                k = j1;
                i = i1;
            } else
            {
                int k1 = ai[byte0];
                if(k1 > 0)
                {
                    int l1 = k + 1;
                    abyte1[k] = 92;
                    k = l1 + 1;
                    abyte1[l1] = (byte)k1;
                } else
                {
                    k = _writeGenericEscape(byte0, k);
                }
                i = i1;
            }
        }

        _outputTail = k;
    }

    private final void _writeUTF8Segments(byte abyte0[], int i, int j)
        throws IOException, JsonGenerationException
    {
        do
        {
            int k = Math.min(_outputMaxContiguous, j);
            _writeUTF8Segment(abyte0, i, k);
            i += k;
            j -= k;
        } while(j > 0);
    }

    protected final int _decodeSurrogate(int i, int j)
        throws IOException
    {
        if(j < 56320 || j > 57343)
            _reportError((new StringBuilder()).append("Incomplete surrogate pair: first char 0x").append(Integer.toHexString(i)).append(", second 0x").append(Integer.toHexString(j)).toString());
        return 0x10000 + (i - 55296 << 10) + (j - 56320);
    }

    protected final void _flushBuffer()
        throws IOException
    {
        int i = _outputTail;
        if(i > 0)
        {
            _outputTail = 0;
            _outputStream.write(_outputBuffer, 0, i);
        }
    }

    protected final void _outputSurrogates(int i, int j)
        throws IOException
    {
        int k = _decodeSurrogate(i, j);
        if(4 + _outputTail > _outputEnd)
            _flushBuffer();
        byte abyte0[] = _outputBuffer;
        int l = _outputTail;
        _outputTail = l + 1;
        abyte0[l] = (byte)(0xf0 | k >> 18);
        int i1 = _outputTail;
        _outputTail = i1 + 1;
        abyte0[i1] = (byte)(0x80 | 0x3f & k >> 12);
        int j1 = _outputTail;
        _outputTail = j1 + 1;
        abyte0[j1] = (byte)(0x80 | 0x3f & k >> 6);
        int k1 = _outputTail;
        _outputTail = k1 + 1;
        abyte0[k1] = (byte)(0x80 | k & 0x3f);
    }

    protected void _releaseBuffers()
    {
        byte abyte0[] = _outputBuffer;
        if(abyte0 != null && _bufferRecyclable)
        {
            _outputBuffer = null;
            _ioContext.releaseWriteEncodingBuffer(abyte0);
        }
        char ac[] = _charBuffer;
        if(ac != null)
        {
            _charBuffer = null;
            _ioContext.releaseConcatBuffer(ac);
        }
    }

    protected final void _verifyPrettyValueWrite(String s, int i)
        throws IOException, JsonGenerationException
    {
        i;
        JVM INSTR tableswitch 0 3: default 32
    //                   0 70
    //                   1 37
    //                   2 48
    //                   3 59;
           goto _L1 _L2 _L3 _L4 _L5
_L1:
        _throwInternal();
_L7:
        return;
_L3:
        _cfgPrettyPrinter.writeArrayValueSeparator(this);
        return;
_L4:
        _cfgPrettyPrinter.writeObjectFieldValueSeparator(this);
        return;
_L5:
        _cfgPrettyPrinter.writeRootValueSeparator(this);
        return;
_L2:
        if(_writeContext.inArray())
        {
            _cfgPrettyPrinter.beforeArrayValues(this);
            return;
        }
        if(_writeContext.inObject())
        {
            _cfgPrettyPrinter.beforeObjectEntries(this);
            return;
        }
        if(true) goto _L7; else goto _L6
_L6:
    }

    protected final void _verifyValueWrite(String s)
        throws IOException, JsonGenerationException
    {
        int i;
        i = _writeContext.writeValue();
        if(i == 5)
            _reportError((new StringBuilder()).append("Can not ").append(s).append(", expecting field name").toString());
        if(_cfgPrettyPrinter != null) goto _L2; else goto _L1
_L1:
        i;
        JVM INSTR tableswitch 1 3: default 76
    //                   1 77
    //                   2 118
    //                   3 125;
           goto _L3 _L4 _L5 _L6
_L3:
        return;
_L4:
        byte byte0 = 44;
_L7:
        if(_outputTail >= _outputEnd)
            _flushBuffer();
        _outputBuffer[_outputTail] = byte0;
        _outputTail = 1 + _outputTail;
        return;
_L5:
        byte0 = 58;
          goto _L7
_L6:
        if(_rootValueSeparator == null) goto _L3; else goto _L8
_L8:
        byte abyte0[] = _rootValueSeparator.asUnquotedUTF8();
        if(abyte0.length <= 0) goto _L3; else goto _L9
_L9:
        _writeBytes(abyte0);
        return;
_L2:
        _verifyPrettyValueWrite(s, i);
        return;
    }

    protected final int _writeBinary(Base64Variant base64variant, InputStream inputstream, byte abyte0[])
        throws IOException, JsonGenerationException
    {
        int i = -3;
        int j = -6 + _outputEnd;
        int k = base64variant.getMaxLineLength() >> 2;
        int l = 0;
        int i1 = 0;
        int j1 = 0;
        do
        {
            if(j1 > i)
            {
                i1 = _readMore(inputstream, abyte0, j1, i1, abyte0.length);
                if(i1 < 3)
                    if(i1 < 0)
                    {
                        if(_outputTail > j)
                            _flushBuffer();
                        int k3 = abyte0[0] << 16;
                        int k1;
                        int l1;
                        int i2;
                        int j2;
                        int k2;
                        int l2;
                        byte abyte1[];
                        int i3;
                        byte abyte2[];
                        int j3;
                        int l3;
                        byte byte0;
                        int i4;
                        if(1 < i1)
                        {
                            l3 = k3 | (0xff & abyte0[1]) << 8;
                            byte0 = 2;
                        } else
                        {
                            l3 = k3;
                            byte0 = 1;
                        }
                        i4 = l + byte0;
                        _outputTail = base64variant.encodeBase64Partial(l3, byte0, _outputBuffer, _outputTail);
                        return i4;
                    } else
                    {
                        return l;
                    }
                i = i1 - 3;
                j1 = 0;
            }
            if(_outputTail > j)
                _flushBuffer();
            k1 = j1 + 1;
            l1 = abyte0[j1] << 8;
            i2 = k1 + 1;
            j2 = (l1 | 0xff & abyte0[k1]) << 8;
            j1 = i2 + 1;
            k2 = j2 | 0xff & abyte0[i2];
            l += 3;
            _outputTail = base64variant.encodeBase64Chunk(k2, _outputBuffer, _outputTail);
            l2 = k - 1;
            if(l2 <= 0)
            {
                abyte1 = _outputBuffer;
                i3 = _outputTail;
                _outputTail = i3 + 1;
                abyte1[i3] = 92;
                abyte2 = _outputBuffer;
                j3 = _outputTail;
                _outputTail = j3 + 1;
                abyte2[j3] = 110;
                l2 = base64variant.getMaxLineLength() >> 2;
            }
            k = l2;
        } while(true);
    }

    protected final int _writeBinary(Base64Variant base64variant, InputStream inputstream, byte abyte0[], int i)
        throws IOException, JsonGenerationException
    {
        int j = 0;
        int k = 0;
        int l = -3;
        int i1 = -6 + _outputEnd;
        int j1 = base64variant.getMaxLineLength() >> 2;
        int k1 = i;
        do
        {
label0:
            {
label1:
                {
                    if(k1 > 2)
                    {
                        if(j <= l)
                            break label0;
                        k = _readMore(inputstream, abyte0, j, k, k1);
                        j = 0;
                        if(k >= 3)
                            break label1;
                    }
                    if(k1 > 0)
                    {
                        int l1 = _readMore(inputstream, abyte0, j, k, k1);
                        if(l1 > 0)
                        {
                            if(_outputTail > i1)
                                _flushBuffer();
                            int i2 = abyte0[0] << 16;
                            byte byte0;
                            int j2;
                            int k2;
                            int l2;
                            int i3;
                            int j3;
                            int k3;
                            byte abyte1[];
                            int l3;
                            byte abyte2[];
                            int i4;
                            if(1 < l1)
                            {
                                i2 |= (0xff & abyte0[1]) << 8;
                                byte0 = 2;
                            } else
                            {
                                byte0 = 1;
                            }
                            _outputTail = base64variant.encodeBase64Partial(i2, byte0, _outputBuffer, _outputTail);
                            k1 -= byte0;
                        }
                    }
                    return k1;
                }
                l = k - 3;
            }
            if(_outputTail > i1)
                _flushBuffer();
            j2 = j + 1;
            k2 = abyte0[j] << 8;
            l2 = j2 + 1;
            i3 = (k2 | 0xff & abyte0[j2]) << 8;
            j = l2 + 1;
            j3 = i3 | 0xff & abyte0[l2];
            k1 -= 3;
            _outputTail = base64variant.encodeBase64Chunk(j3, _outputBuffer, _outputTail);
            k3 = j1 - 1;
            if(k3 <= 0)
            {
                abyte1 = _outputBuffer;
                l3 = _outputTail;
                _outputTail = l3 + 1;
                abyte1[l3] = 92;
                abyte2 = _outputBuffer;
                i4 = _outputTail;
                _outputTail = i4 + 1;
                abyte2[i4] = 110;
                k3 = base64variant.getMaxLineLength() >> 2;
            }
            j1 = k3;
        } while(true);
    }

    protected final void _writeBinary(Base64Variant base64variant, byte abyte0[], int i, int j)
        throws IOException, JsonGenerationException
    {
        int k = j - 3;
        int l = -6 + _outputEnd;
        int i1 = base64variant.getMaxLineLength() >> 2;
        do
        {
            if(i > k)
                break;
            if(_outputTail > l)
                _flushBuffer();
            int i2 = i + 1;
            int j2 = abyte0[i] << 8;
            int k2 = i2 + 1;
            int l2 = (j2 | 0xff & abyte0[i2]) << 8;
            i = k2 + 1;
            _outputTail = base64variant.encodeBase64Chunk(l2 | 0xff & abyte0[k2], _outputBuffer, _outputTail);
            if(--i1 <= 0)
            {
                byte abyte1[] = _outputBuffer;
                int i3 = _outputTail;
                _outputTail = i3 + 1;
                abyte1[i3] = 92;
                byte abyte2[] = _outputBuffer;
                int j3 = _outputTail;
                _outputTail = j3 + 1;
                abyte2[j3] = 110;
                i1 = base64variant.getMaxLineLength() >> 2;
            }
        } while(true);
        int j1 = j - i;
        if(j1 > 0)
        {
            if(_outputTail > l)
                _flushBuffer();
            int k1 = i + 1;
            int l1 = abyte0[i] << 16;
            if(j1 == 2)
            {
                int _tmp = k1 + 1;
                l1 |= (0xff & abyte0[k1]) << 8;
            }
            _outputTail = base64variant.encodeBase64Partial(l1, j1, _outputBuffer, _outputTail);
        }
    }

    protected final void _writeFieldName(SerializableString serializablestring)
        throws IOException, JsonGenerationException
    {
        if(!_cfgQuoteNames)
        {
            int l = serializablestring.appendQuotedUTF8(_outputBuffer, _outputTail);
            if(l < 0)
            {
                _writeBytes(serializablestring.asQuotedUTF8());
                return;
            } else
            {
                _outputTail = l + _outputTail;
                return;
            }
        }
        if(_outputTail >= _outputEnd)
            _flushBuffer();
        byte abyte0[] = _outputBuffer;
        int i = _outputTail;
        _outputTail = i + 1;
        abyte0[i] = 34;
        int j = serializablestring.appendQuotedUTF8(_outputBuffer, _outputTail);
        byte abyte1[];
        int k;
        if(j < 0)
            _writeBytes(serializablestring.asQuotedUTF8());
        else
            _outputTail = j + _outputTail;
        if(_outputTail >= _outputEnd)
            _flushBuffer();
        abyte1 = _outputBuffer;
        k = _outputTail;
        _outputTail = k + 1;
        abyte1[k] = 34;
    }

    protected final void _writeFieldName(String s)
        throws IOException, JsonGenerationException
    {
        if(!_cfgQuoteNames)
        {
            _writeStringSegments(s);
            return;
        }
        if(_outputTail >= _outputEnd)
            _flushBuffer();
        byte abyte0[] = _outputBuffer;
        int i = _outputTail;
        _outputTail = i + 1;
        abyte0[i] = 34;
        int j = s.length();
        if(j <= _charBufferLength)
        {
            s.getChars(0, j, _charBuffer, 0);
            byte abyte1[];
            int k;
            if(j <= _outputMaxContiguous)
            {
                if(j + _outputTail > _outputEnd)
                    _flushBuffer();
                _writeStringSegment(_charBuffer, 0, j);
            } else
            {
                _writeStringSegments(_charBuffer, 0, j);
            }
        } else
        {
            _writeStringSegments(s);
        }
        if(_outputTail >= _outputEnd)
            _flushBuffer();
        abyte1 = _outputBuffer;
        k = _outputTail;
        _outputTail = k + 1;
        abyte1[k] = 34;
    }

    protected final void _writePPFieldName(SerializableString serializablestring, boolean flag)
        throws IOException, JsonGenerationException
    {
        boolean flag1;
        if(flag)
            _cfgPrettyPrinter.writeObjectEntrySeparator(this);
        else
            _cfgPrettyPrinter.beforeObjectEntries(this);
        flag1 = _cfgQuoteNames;
        if(flag1)
        {
            if(_outputTail >= _outputEnd)
                _flushBuffer();
            byte abyte1[] = _outputBuffer;
            int j = _outputTail;
            _outputTail = j + 1;
            abyte1[j] = 34;
        }
        _writeBytes(serializablestring.asQuotedUTF8());
        if(flag1)
        {
            if(_outputTail >= _outputEnd)
                _flushBuffer();
            byte abyte0[] = _outputBuffer;
            int i = _outputTail;
            _outputTail = i + 1;
            abyte0[i] = 34;
        }
    }

    protected final void _writePPFieldName(String s, boolean flag)
        throws IOException, JsonGenerationException
    {
        if(flag)
            _cfgPrettyPrinter.writeObjectEntrySeparator(this);
        else
            _cfgPrettyPrinter.beforeObjectEntries(this);
        if(_cfgQuoteNames)
        {
            if(_outputTail >= _outputEnd)
                _flushBuffer();
            byte abyte0[] = _outputBuffer;
            int i = _outputTail;
            _outputTail = i + 1;
            abyte0[i] = 34;
            int j = s.length();
            if(j <= _charBufferLength)
            {
                s.getChars(0, j, _charBuffer, 0);
                byte abyte1[];
                int k;
                if(j <= _outputMaxContiguous)
                {
                    if(j + _outputTail > _outputEnd)
                        _flushBuffer();
                    _writeStringSegment(_charBuffer, 0, j);
                } else
                {
                    _writeStringSegments(_charBuffer, 0, j);
                }
            } else
            {
                _writeStringSegments(s);
            }
            if(_outputTail >= _outputEnd)
                _flushBuffer();
            abyte1 = _outputBuffer;
            k = _outputTail;
            _outputTail = k + 1;
            abyte1[k] = 34;
            return;
        } else
        {
            _writeStringSegments(s);
            return;
        }
    }

    public void close()
        throws IOException
    {
        super.close();
        if(_outputBuffer != null && isEnabled(com.fasterxml.jackson.core.JsonGenerator.Feature.AUTO_CLOSE_JSON_CONTENT))
            do
            {
                JsonWriteContext jsonwritecontext = getOutputContext();
                if(jsonwritecontext.inArray())
                {
                    writeEndArray();
                    continue;
                }
                if(!jsonwritecontext.inObject())
                    break;
                writeEndObject();
            } while(true);
        _flushBuffer();
        if(_outputStream == null) goto _L2; else goto _L1
_L1:
        if(!_ioContext.isResourceManaged() && !isEnabled(com.fasterxml.jackson.core.JsonGenerator.Feature.AUTO_CLOSE_TARGET)) goto _L4; else goto _L3
_L3:
        _outputStream.close();
_L2:
        _releaseBuffers();
        return;
_L4:
        if(isEnabled(com.fasterxml.jackson.core.JsonGenerator.Feature.FLUSH_PASSED_TO_STREAM))
            _outputStream.flush();
        if(true) goto _L2; else goto _L5
_L5:
    }

    public void flush()
        throws IOException
    {
        _flushBuffer();
        if(_outputStream != null && isEnabled(com.fasterxml.jackson.core.JsonGenerator.Feature.FLUSH_PASSED_TO_STREAM))
            _outputStream.flush();
    }

    public Object getOutputTarget()
    {
        return _outputStream;
    }

    public int writeBinary(Base64Variant base64variant, InputStream inputstream, int i)
        throws IOException, JsonGenerationException
    {
        byte abyte1[];
        _verifyValueWrite("write binary value");
        if(_outputTail >= _outputEnd)
            _flushBuffer();
        byte abyte0[] = _outputBuffer;
        int j = _outputTail;
        _outputTail = j + 1;
        abyte0[j] = 34;
        abyte1 = _ioContext.allocBase64Buffer();
        if(i >= 0) goto _L2; else goto _L1
_L1:
        int i1 = _writeBinary(base64variant, inputstream, abyte1);
        i = i1;
_L4:
        _ioContext.releaseBase64Buffer(abyte1);
        if(_outputTail >= _outputEnd)
            _flushBuffer();
        byte abyte2[] = _outputBuffer;
        int l = _outputTail;
        _outputTail = l + 1;
        abyte2[l] = 34;
        return i;
_L2:
        int k = _writeBinary(base64variant, inputstream, abyte1, i);
        if(k <= 0) goto _L4; else goto _L3
_L3:
        _reportError((new StringBuilder()).append("Too few bytes available: missing ").append(k).append(" bytes (out of ").append(i).append(")").toString());
          goto _L4
        Exception exception;
        exception;
        _ioContext.releaseBase64Buffer(abyte1);
        throw exception;
    }

    public void writeBinary(Base64Variant base64variant, byte abyte0[], int i, int j)
        throws IOException, JsonGenerationException
    {
        _verifyValueWrite("write binary value");
        if(_outputTail >= _outputEnd)
            _flushBuffer();
        byte abyte1[] = _outputBuffer;
        int k = _outputTail;
        _outputTail = k + 1;
        abyte1[k] = 34;
        _writeBinary(base64variant, abyte0, i, i + j);
        if(_outputTail >= _outputEnd)
            _flushBuffer();
        byte abyte2[] = _outputBuffer;
        int l = _outputTail;
        _outputTail = l + 1;
        abyte2[l] = 34;
    }

    public void writeBoolean(boolean flag)
        throws IOException, JsonGenerationException
    {
        _verifyValueWrite("write boolean value");
        if(5 + _outputTail >= _outputEnd)
            _flushBuffer();
        byte abyte0[];
        int i;
        if(flag)
            abyte0 = TRUE_BYTES;
        else
            abyte0 = FALSE_BYTES;
        i = abyte0.length;
        System.arraycopy(abyte0, 0, _outputBuffer, _outputTail, i);
        _outputTail = i + _outputTail;
    }

    public final void writeEndArray()
        throws IOException, JsonGenerationException
    {
        if(!_writeContext.inArray())
            _reportError((new StringBuilder()).append("Current context not an ARRAY but ").append(_writeContext.getTypeDesc()).toString());
        if(_cfgPrettyPrinter != null)
        {
            _cfgPrettyPrinter.writeEndArray(this, _writeContext.getEntryCount());
        } else
        {
            if(_outputTail >= _outputEnd)
                _flushBuffer();
            byte abyte0[] = _outputBuffer;
            int i = _outputTail;
            _outputTail = i + 1;
            abyte0[i] = 93;
        }
        _writeContext = _writeContext.getParent();
    }

    public final void writeEndObject()
        throws IOException, JsonGenerationException
    {
        if(!_writeContext.inObject())
            _reportError((new StringBuilder()).append("Current context not an object but ").append(_writeContext.getTypeDesc()).toString());
        if(_cfgPrettyPrinter != null)
        {
            _cfgPrettyPrinter.writeEndObject(this, _writeContext.getEntryCount());
        } else
        {
            if(_outputTail >= _outputEnd)
                _flushBuffer();
            byte abyte0[] = _outputBuffer;
            int i = _outputTail;
            _outputTail = i + 1;
            abyte0[i] = 125;
        }
        _writeContext = _writeContext.getParent();
    }

    public void writeFieldName(SerializableString serializablestring)
        throws IOException, JsonGenerationException
    {
        boolean flag = true;
        int i = _writeContext.writeFieldName(serializablestring.getValue());
        if(i == 4)
            _reportError("Can not write a field name, expecting a value");
        if(_cfgPrettyPrinter != null)
        {
            if(i != flag)
                flag = false;
            _writePPFieldName(serializablestring, flag);
            return;
        }
        if(i == flag)
        {
            if(_outputTail >= _outputEnd)
                _flushBuffer();
            byte abyte0[] = _outputBuffer;
            int j = _outputTail;
            _outputTail = j + 1;
            abyte0[j] = 44;
        }
        _writeFieldName(serializablestring);
    }

    public void writeFieldName(String s)
        throws IOException, JsonGenerationException
    {
        boolean flag = true;
        int i = _writeContext.writeFieldName(s);
        if(i == 4)
            _reportError("Can not write a field name, expecting a value");
        if(_cfgPrettyPrinter != null)
        {
            if(i != flag)
                flag = false;
            _writePPFieldName(s, flag);
            return;
        }
        if(i == flag)
        {
            if(_outputTail >= _outputEnd)
                _flushBuffer();
            byte abyte0[] = _outputBuffer;
            int j = _outputTail;
            _outputTail = j + 1;
            abyte0[j] = 44;
        }
        _writeFieldName(s);
    }

    public void writeNull()
        throws IOException, JsonGenerationException
    {
        _verifyValueWrite("write null value");
        _writeNull();
    }

    public void writeNumber(double d)
        throws IOException, JsonGenerationException
    {
        if(_cfgNumbersAsStrings || (Double.isNaN(d) || Double.isInfinite(d)) && isEnabled(com.fasterxml.jackson.core.JsonGenerator.Feature.QUOTE_NON_NUMERIC_NUMBERS))
        {
            writeString(String.valueOf(d));
            return;
        } else
        {
            _verifyValueWrite("write number");
            writeRaw(String.valueOf(d));
            return;
        }
    }

    public void writeNumber(float f)
        throws IOException, JsonGenerationException
    {
        if(_cfgNumbersAsStrings || (Float.isNaN(f) || Float.isInfinite(f)) && isEnabled(com.fasterxml.jackson.core.JsonGenerator.Feature.QUOTE_NON_NUMERIC_NUMBERS))
        {
            writeString(String.valueOf(f));
            return;
        } else
        {
            _verifyValueWrite("write number");
            writeRaw(String.valueOf(f));
            return;
        }
    }

    public void writeNumber(int i)
        throws IOException, JsonGenerationException
    {
        _verifyValueWrite("write number");
        if(11 + _outputTail >= _outputEnd)
            _flushBuffer();
        if(_cfgNumbersAsStrings)
        {
            _writeQuotedInt(i);
            return;
        } else
        {
            _outputTail = NumberOutput.outputInt(i, _outputBuffer, _outputTail);
            return;
        }
    }

    public void writeNumber(long l)
        throws IOException, JsonGenerationException
    {
        _verifyValueWrite("write number");
        if(_cfgNumbersAsStrings)
        {
            _writeQuotedLong(l);
            return;
        }
        if(21 + _outputTail >= _outputEnd)
            _flushBuffer();
        _outputTail = NumberOutput.outputLong(l, _outputBuffer, _outputTail);
    }

    public void writeNumber(String s)
        throws IOException, JsonGenerationException
    {
        _verifyValueWrite("write number");
        if(_cfgNumbersAsStrings)
        {
            _writeQuotedRaw(s);
            return;
        } else
        {
            writeRaw(s);
            return;
        }
    }

    public void writeNumber(BigDecimal bigdecimal)
        throws IOException, JsonGenerationException
    {
        _verifyValueWrite("write number");
        if(bigdecimal == null)
        {
            _writeNull();
            return;
        }
        if(_cfgNumbersAsStrings)
        {
            _writeQuotedRaw(bigdecimal);
            return;
        }
        if(isEnabled(com.fasterxml.jackson.core.JsonGenerator.Feature.WRITE_BIGDECIMAL_AS_PLAIN))
        {
            writeRaw(bigdecimal.toPlainString());
            return;
        } else
        {
            writeRaw(bigdecimal.toString());
            return;
        }
    }

    public void writeNumber(BigInteger biginteger)
        throws IOException, JsonGenerationException
    {
        _verifyValueWrite("write number");
        if(biginteger == null)
        {
            _writeNull();
            return;
        }
        if(_cfgNumbersAsStrings)
        {
            _writeQuotedRaw(biginteger);
            return;
        } else
        {
            writeRaw(biginteger.toString());
            return;
        }
    }

    public void writeNumber(short word0)
        throws IOException, JsonGenerationException
    {
        _verifyValueWrite("write number");
        if(6 + _outputTail >= _outputEnd)
            _flushBuffer();
        if(_cfgNumbersAsStrings)
        {
            _writeQuotedShort(word0);
            return;
        } else
        {
            _outputTail = NumberOutput.outputInt(word0, _outputBuffer, _outputTail);
            return;
        }
    }

    public void writeRaw(char c)
        throws IOException, JsonGenerationException
    {
        if(3 + _outputTail >= _outputEnd)
            _flushBuffer();
        byte abyte0[] = _outputBuffer;
        if(c <= '\177')
        {
            int k = _outputTail;
            _outputTail = k + 1;
            abyte0[k] = (byte)c;
            return;
        }
        if(c < '\u0800')
        {
            int i = _outputTail;
            _outputTail = i + 1;
            abyte0[i] = (byte)(0xc0 | c >> 6);
            int j = _outputTail;
            _outputTail = j + 1;
            abyte0[j] = (byte)(0x80 | c & 0x3f);
            return;
        } else
        {
            _outputRawMultiByteChar(c, null, 0, 0);
            return;
        }
    }

    public void writeRaw(SerializableString serializablestring)
        throws IOException, JsonGenerationException
    {
        byte abyte0[] = serializablestring.asUnquotedUTF8();
        if(abyte0.length > 0)
            _writeBytes(abyte0);
    }

    public void writeRaw(String s)
        throws IOException, JsonGenerationException
    {
        int i = s.length();
        int j = 0;
        int k;
        for(; i > 0; i -= k)
        {
            char ac[] = _charBuffer;
            k = ac.length;
            if(i < k)
                k = i;
            s.getChars(j, j + k, ac, 0);
            writeRaw(ac, 0, k);
            j += k;
        }

    }

    public void writeRaw(String s, int i, int j)
        throws IOException, JsonGenerationException
    {
        int l;
        for(int k = j; k > 0; k -= l)
        {
            char ac[] = _charBuffer;
            l = ac.length;
            if(k < l)
                l = k;
            s.getChars(i, i + l, ac, 0);
            writeRaw(ac, 0, l);
            i += l;
        }

    }

    public final void writeRaw(char ac[], int i, int j)
        throws IOException, JsonGenerationException
    {
        int k = j + (j + j);
        if(k + _outputTail <= _outputEnd) goto _L2; else goto _L1
_L1:
        if(_outputEnd >= k) goto _L4; else goto _L3
_L3:
        _writeSegmentedRaw(ac, i, j);
_L10:
        return;
_L4:
        _flushBuffer();
_L2:
        int l;
        int i1;
        l = j + i;
        i1 = i;
_L8:
        if(i1 >= l)
            continue; /* Loop/switch isn't completed */
_L6:
label0:
        {
            char c = ac[i1];
            if(c <= '\177')
                break label0;
            int k1 = i1 + 1;
            char c1 = ac[i1];
            byte abyte0[];
            int j1;
            if(c1 < '\u0800')
            {
                byte abyte1[] = _outputBuffer;
                int l1 = _outputTail;
                _outputTail = l1 + 1;
                abyte1[l1] = (byte)(0xc0 | c1 >> 6);
                byte abyte2[] = _outputBuffer;
                int i2 = _outputTail;
                _outputTail = i2 + 1;
                abyte2[i2] = (byte)(0x80 | c1 & 0x3f);
                i1 = k1;
            } else
            {
                i1 = _outputRawMultiByteChar(c1, ac, k1, l);
            }
        }
        continue; /* Loop/switch isn't completed */
        abyte0 = _outputBuffer;
        j1 = _outputTail;
        _outputTail = j1 + 1;
        abyte0[j1] = (byte)c;
        if(++i1 < l) goto _L6; else goto _L5
_L5:
        return;
        if(true) goto _L8; else goto _L7
_L7:
        if(true) goto _L10; else goto _L9
_L9:
    }

    public void writeRawUTF8String(byte abyte0[], int i, int j)
        throws IOException, JsonGenerationException
    {
        _verifyValueWrite("write text value");
        if(_outputTail >= _outputEnd)
            _flushBuffer();
        byte abyte1[] = _outputBuffer;
        int k = _outputTail;
        _outputTail = k + 1;
        abyte1[k] = 34;
        _writeBytes(abyte0, i, j);
        if(_outputTail >= _outputEnd)
            _flushBuffer();
        byte abyte2[] = _outputBuffer;
        int l = _outputTail;
        _outputTail = l + 1;
        abyte2[l] = 34;
    }

    public final void writeStartArray()
        throws IOException, JsonGenerationException
    {
        _verifyValueWrite("start an array");
        _writeContext = _writeContext.createChildArrayContext();
        if(_cfgPrettyPrinter != null)
        {
            _cfgPrettyPrinter.writeStartArray(this);
            return;
        }
        if(_outputTail >= _outputEnd)
            _flushBuffer();
        byte abyte0[] = _outputBuffer;
        int i = _outputTail;
        _outputTail = i + 1;
        abyte0[i] = 91;
    }

    public final void writeStartObject()
        throws IOException, JsonGenerationException
    {
        _verifyValueWrite("start an object");
        _writeContext = _writeContext.createChildObjectContext();
        if(_cfgPrettyPrinter != null)
        {
            _cfgPrettyPrinter.writeStartObject(this);
            return;
        }
        if(_outputTail >= _outputEnd)
            _flushBuffer();
        byte abyte0[] = _outputBuffer;
        int i = _outputTail;
        _outputTail = i + 1;
        abyte0[i] = 123;
    }

    public final void writeString(SerializableString serializablestring)
        throws IOException, JsonGenerationException
    {
        _verifyValueWrite("write text value");
        if(_outputTail >= _outputEnd)
            _flushBuffer();
        byte abyte0[] = _outputBuffer;
        int i = _outputTail;
        _outputTail = i + 1;
        abyte0[i] = 34;
        int j = serializablestring.appendQuotedUTF8(_outputBuffer, _outputTail);
        byte abyte1[];
        int k;
        if(j < 0)
            _writeBytes(serializablestring.asQuotedUTF8());
        else
            _outputTail = j + _outputTail;
        if(_outputTail >= _outputEnd)
            _flushBuffer();
        abyte1 = _outputBuffer;
        k = _outputTail;
        _outputTail = k + 1;
        abyte1[k] = 34;
    }

    public void writeString(String s)
        throws IOException, JsonGenerationException
    {
        _verifyValueWrite("write text value");
        if(s == null)
        {
            _writeNull();
            return;
        }
        int i = s.length();
        if(i > _charBufferLength)
        {
            _writeLongString(s);
            return;
        }
        s.getChars(0, i, _charBuffer, 0);
        if(i > _outputMaxContiguous)
        {
            _writeLongString(_charBuffer, 0, i);
            return;
        }
        if(i + _outputTail >= _outputEnd)
            _flushBuffer();
        byte abyte0[] = _outputBuffer;
        int j = _outputTail;
        _outputTail = j + 1;
        abyte0[j] = 34;
        _writeStringSegment(_charBuffer, 0, i);
        if(_outputTail >= _outputEnd)
            _flushBuffer();
        byte abyte1[] = _outputBuffer;
        int k = _outputTail;
        _outputTail = k + 1;
        abyte1[k] = 34;
    }

    public void writeString(char ac[], int i, int j)
        throws IOException, JsonGenerationException
    {
        _verifyValueWrite("write text value");
        if(_outputTail >= _outputEnd)
            _flushBuffer();
        byte abyte0[] = _outputBuffer;
        int k = _outputTail;
        _outputTail = k + 1;
        abyte0[k] = 34;
        byte abyte1[];
        int l;
        if(j <= _outputMaxContiguous)
        {
            if(j + _outputTail > _outputEnd)
                _flushBuffer();
            _writeStringSegment(ac, i, j);
        } else
        {
            _writeStringSegments(ac, i, j);
        }
        if(_outputTail >= _outputEnd)
            _flushBuffer();
        abyte1 = _outputBuffer;
        l = _outputTail;
        _outputTail = l + 1;
        abyte1[l] = 34;
    }

    public void writeUTF8String(byte abyte0[], int i, int j)
        throws IOException, JsonGenerationException
    {
        _verifyValueWrite("write text value");
        if(_outputTail >= _outputEnd)
            _flushBuffer();
        byte abyte1[] = _outputBuffer;
        int k = _outputTail;
        _outputTail = k + 1;
        abyte1[k] = 34;
        byte abyte2[];
        int l;
        if(j <= _outputMaxContiguous)
            _writeUTF8Segment(abyte0, i, j);
        else
            _writeUTF8Segments(abyte0, i, j);
        if(_outputTail >= _outputEnd)
            _flushBuffer();
        abyte2 = _outputBuffer;
        l = _outputTail;
        _outputTail = l + 1;
        abyte2[l] = 34;
    }

    private static final byte BYTE_0 = 48;
    private static final byte BYTE_BACKSLASH = 92;
    private static final byte BYTE_COLON = 58;
    private static final byte BYTE_COMMA = 44;
    private static final byte BYTE_LBRACKET = 91;
    private static final byte BYTE_LCURLY = 123;
    private static final byte BYTE_QUOTE = 34;
    private static final byte BYTE_RBRACKET = 93;
    private static final byte BYTE_RCURLY = 125;
    private static final byte BYTE_u = 117;
    private static final byte FALSE_BYTES[] = {
        102, 97, 108, 115, 101
    };
    static final byte HEX_CHARS[] = CharTypes.copyHexBytes();
    private static final int MAX_BYTES_TO_BUFFER = 512;
    private static final byte NULL_BYTES[] = {
        110, 117, 108, 108
    };
    protected static final int SURR1_FIRST = 55296;
    protected static final int SURR1_LAST = 56319;
    protected static final int SURR2_FIRST = 56320;
    protected static final int SURR2_LAST = 57343;
    private static final byte TRUE_BYTES[] = {
        116, 114, 117, 101
    };
    protected boolean _bufferRecyclable;
    protected final boolean _cfgQuoteNames;
    protected char _charBuffer[];
    protected final int _charBufferLength;
    protected byte _entityBuffer[];
    protected byte _outputBuffer[];
    protected final int _outputEnd;
    protected final int _outputMaxContiguous;
    protected final OutputStream _outputStream;
    protected int _outputTail;

}
