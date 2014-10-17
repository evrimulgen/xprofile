// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.fasterxml.jackson.core.io;

import com.fasterxml.jackson.core.util.ByteArrayBuilder;
import com.fasterxml.jackson.core.util.TextBuffer;
import java.lang.ref.SoftReference;

// Referenced classes of package com.fasterxml.jackson.core.io:
//            CharTypes, UTF8Writer

public final class JsonStringEncoder
{

    public JsonStringEncoder()
    {
        _quoteBuffer[0] = '\\';
        _quoteBuffer[2] = '0';
        _quoteBuffer[3] = '0';
    }

    private int _appendByteEscape(int i, int j, ByteArrayBuilder bytearraybuilder, int k)
    {
        bytearraybuilder.setCurrentSegmentLength(k);
        bytearraybuilder.append(92);
        if(j < 0)
        {
            bytearraybuilder.append(117);
            if(i > 255)
            {
                int l = i >> 8;
                bytearraybuilder.append(HEX_BYTES[l >> 4]);
                bytearraybuilder.append(HEX_BYTES[l & 0xf]);
                i &= 0xff;
            } else
            {
                bytearraybuilder.append(48);
                bytearraybuilder.append(48);
            }
            bytearraybuilder.append(HEX_BYTES[i >> 4]);
            bytearraybuilder.append(HEX_BYTES[i & 0xf]);
        } else
        {
            bytearraybuilder.append((byte)j);
        }
        return bytearraybuilder.getCurrentSegmentLength();
    }

    private int _appendNamedEscape(int i, char ac[])
    {
        ac[1] = (char)i;
        return 2;
    }

    private int _appendNumericEscape(int i, char ac[])
    {
        ac[1] = 'u';
        ac[4] = HEX_CHARS[i >> 4];
        ac[5] = HEX_CHARS[i & 0xf];
        return 6;
    }

    protected static int _convertSurrogate(int i, int j)
    {
        if(j < 56320 || j > 57343)
            throw new IllegalArgumentException((new StringBuilder()).append("Broken surrogate pair: first char 0x").append(Integer.toHexString(i)).append(", second 0x").append(Integer.toHexString(j)).append("; illegal combination").toString());
        else
            return 0x10000 + (i - 55296 << 10) + (j - 56320);
    }

    protected static void _illegalSurrogate(int i)
    {
        throw new IllegalArgumentException(UTF8Writer.illegalSurrogateDesc(i));
    }

    public static JsonStringEncoder getInstance()
    {
        SoftReference softreference = (SoftReference)_threadEncoder.get();
        JsonStringEncoder jsonstringencoder;
        if(softreference == null)
            jsonstringencoder = null;
        else
            jsonstringencoder = (JsonStringEncoder)softreference.get();
        if(jsonstringencoder == null)
        {
            jsonstringencoder = new JsonStringEncoder();
            _threadEncoder.set(new SoftReference(jsonstringencoder));
        }
        return jsonstringencoder;
    }

    public byte[] encodeAsUTF8(String s)
    {
        ByteArrayBuilder bytearraybuilder;
        int i;
        byte abyte0[];
        int j;
        int k;
        int l;
        bytearraybuilder = _byteBuilder;
        if(bytearraybuilder == null)
        {
            bytearraybuilder = new ByteArrayBuilder(null);
            _byteBuilder = bytearraybuilder;
        }
        i = s.length();
        abyte0 = bytearraybuilder.resetAndGetFirstSegment();
        j = abyte0.length;
        k = 0;
        l = 0;
_L6:
        int j1;
        char c;
        byte abyte1[];
        int l1;
        int i2;
        if(l >= i)
            break MISSING_BLOCK_LABEL_572;
        j1 = l + 1;
        c = s.charAt(l);
        int k1 = j;
        abyte1 = abyte0;
        l1 = k;
        i2 = k1;
_L5:
        if(c > '\177') goto _L2; else goto _L1
_L1:
        int j5;
        if(l1 >= i2)
        {
            abyte1 = bytearraybuilder.finishCurrentSegment();
            i2 = abyte1.length;
            l1 = 0;
        }
        j5 = l1 + 1;
        abyte1[l1] = (byte)c;
        if(j1 < i) goto _L4; else goto _L3
_L3:
        int i1 = j5;
_L7:
        return _byteBuilder.completeAndCoalesce(i1);
_L4:
        int k5 = j1 + 1;
        c = s.charAt(j1);
        j1 = k5;
        l1 = j5;
          goto _L5
_L2:
        int j2;
        int i3;
        int j3;
        int k3;
        if(l1 >= i2)
        {
            abyte1 = bytearraybuilder.finishCurrentSegment();
            i2 = abyte1.length;
            j2 = 0;
        } else
        {
            j2 = l1;
        }
        if(c < '\u0800')
        {
            i3 = j2 + 1;
            abyte1[j2] = (byte)(0xc0 | c >> 6);
            j3 = c;
            l = j1;
        } else
        if(c < '\uD800' || c > '\uDFFF')
        {
            int k2 = j2 + 1;
            abyte1[j2] = (byte)(0xe0 | c >> 12);
            if(k2 >= i2)
            {
                abyte1 = bytearraybuilder.finishCurrentSegment();
                i2 = abyte1.length;
                k2 = 0;
            }
            int l2 = k2 + 1;
            abyte1[k2] = (byte)(0x80 | 0x3f & c >> 6);
            i3 = l2;
            j3 = c;
            l = j1;
        } else
        {
            if(c > '\uDBFF')
                _illegalSurrogate(c);
            if(j1 >= i)
                _illegalSurrogate(c);
            int l3 = j1 + 1;
            int i4 = _convertSurrogate(c, s.charAt(j1));
            if(i4 > 0x10ffff)
                _illegalSurrogate(i4);
            int j4 = j2 + 1;
            abyte1[j2] = (byte)(0xf0 | i4 >> 18);
            if(j4 >= i2)
            {
                abyte1 = bytearraybuilder.finishCurrentSegment();
                i2 = abyte1.length;
                j4 = 0;
            }
            int k4 = j4 + 1;
            abyte1[j4] = (byte)(0x80 | 0x3f & i4 >> 12);
            int l4;
            int i5;
            if(k4 >= i2)
            {
                abyte1 = bytearraybuilder.finishCurrentSegment();
                i2 = abyte1.length;
                l4 = 0;
            } else
            {
                l4 = k4;
            }
            i5 = l4 + 1;
            abyte1[l4] = (byte)(0x80 | 0x3f & i4 >> 6);
            i3 = i5;
            j3 = i4;
            l = l3;
        }
        if(i3 >= i2)
        {
            abyte1 = bytearraybuilder.finishCurrentSegment();
            i2 = abyte1.length;
            i3 = 0;
        }
        k3 = i3 + 1;
        abyte1[i3] = (byte)(0x80 | j3 & 0x3f);
        abyte0 = abyte1;
        j = i2;
        k = k3;
          goto _L6
        i1 = k;
          goto _L7
    }

    public char[] quoteAsString(String s)
    {
        TextBuffer textbuffer = _textBuffer;
        if(textbuffer == null)
        {
            textbuffer = new TextBuffer(null);
            _textBuffer = textbuffer;
        }
        char ac[] = textbuffer.emptyAndGetCurrentSegment();
        int ai[] = CharTypes.get7BitOutputEscapes();
        int i = ai.length;
        int j = s.length();
        int k = 0;
        int l = 0;
label0:
        do
        {
            if(l < j)
                do
                {
                    char c = s.charAt(l);
                    int i1;
                    if(c < i && ai[c] != 0)
                    {
                        int j1 = l + 1;
                        char c1 = s.charAt(l);
                        int k1 = ai[c1];
                        int l1;
                        if(k1 < 0)
                            l1 = _appendNumericEscape(c1, _quoteBuffer);
                        else
                            l1 = _appendNamedEscape(k1, _quoteBuffer);
                        if(k + l1 > ac.length)
                        {
                            int i2 = ac.length - k;
                            if(i2 > 0)
                                System.arraycopy(_quoteBuffer, 0, ac, k, i2);
                            ac = textbuffer.finishCurrentSegment();
                            k = l1 - i2;
                            System.arraycopy(_quoteBuffer, i2, ac, 0, k);
                        } else
                        {
                            System.arraycopy(_quoteBuffer, 0, ac, k, l1);
                            k += l1;
                        }
                        l = j1;
                        continue label0;
                    }
                    if(k >= ac.length)
                    {
                        ac = textbuffer.finishCurrentSegment();
                        i1 = 0;
                    } else
                    {
                        i1 = k;
                    }
                    k = i1 + 1;
                    ac[i1] = c;
                } while(++l < j);
            textbuffer.setCurrentLength(k);
            return textbuffer.contentsAsArray();
        } while(true);
    }

    public byte[] quoteAsUTF8(String s)
    {
        ByteArrayBuilder bytearraybuilder;
        int i;
        byte abyte0[];
        int j;
        int k;
        bytearraybuilder = _byteBuilder;
        if(bytearraybuilder == null)
        {
            bytearraybuilder = new ByteArrayBuilder(null);
            _byteBuilder = bytearraybuilder;
        }
        i = s.length();
        abyte0 = bytearraybuilder.resetAndGetFirstSegment();
        j = 0;
        k = 0;
_L2:
label0:
        {
            if(k < i)
            {
                int ai[] = CharTypes.get7BitOutputEscapes();
                do
                {
                    char c = s.charAt(k);
                    int l;
                    char c1;
                    if(c > '\177' || ai[c] != 0)
                    {
                        if(j >= abyte0.length)
                        {
                            abyte0 = bytearraybuilder.finishCurrentSegment();
                            j = 0;
                        }
                        l = k + 1;
                        c1 = s.charAt(k);
                        if(c1 <= '\177')
                        {
                            j = _appendByteEscape(c1, ai[c1], bytearraybuilder, j);
                            abyte0 = bytearraybuilder.getCurrentSegment();
                            k = l;
                            continue; /* Loop/switch isn't completed */
                        }
                        break label0;
                    }
                    int i1;
                    int j1;
                    int k1;
                    int l1;
                    byte abyte1[];
                    int i2;
                    int j2;
                    int k2;
                    int l2;
                    int i3;
                    int j3;
                    int k3;
                    int l3;
                    int i4;
                    byte abyte2[];
                    int j4;
                    int k4;
                    if(j >= abyte0.length)
                    {
                        abyte0 = bytearraybuilder.finishCurrentSegment();
                        k4 = 0;
                    } else
                    {
                        k4 = j;
                    }
                    j = k4 + 1;
                    abyte0[k4] = (byte)c;
                } while(++k < i);
            }
            return _byteBuilder.completeAndCoalesce(j);
        }
        if(c1 > '\u07FF')
            break; /* Loop/switch isn't completed */
        k1 = j + 1;
        abyte0[j] = (byte)(0xc0 | c1 >> 6);
        j4 = 0x80 | c1 & 0x3f;
        abyte1 = abyte0;
        i2 = j4;
_L3:
        if(k1 >= abyte1.length)
        {
            abyte1 = bytearraybuilder.finishCurrentSegment();
            k1 = 0;
        }
        j2 = k1 + 1;
        abyte1[k1] = (byte)i2;
        abyte0 = abyte1;
        k = l;
        j = j2;
        if(true) goto _L2; else goto _L1
_L1:
        if(c1 < '\uD800' || c1 > '\uDFFF')
        {
            i1 = j + 1;
            abyte0[j] = (byte)(0xe0 | c1 >> 12);
            if(i1 >= abyte0.length)
            {
                abyte0 = bytearraybuilder.finishCurrentSegment();
                j1 = 0;
            } else
            {
                j1 = i1;
            }
            k1 = j1 + 1;
            abyte0[j1] = (byte)(0x80 | 0x3f & c1 >> 6);
            l1 = 0x80 | c1 & 0x3f;
            abyte1 = abyte0;
            i2 = l1;
        } else
        {
            if(c1 > '\uDBFF')
                _illegalSurrogate(c1);
            if(l >= i)
                _illegalSurrogate(c1);
            k2 = l + 1;
            l2 = _convertSurrogate(c1, s.charAt(l));
            if(l2 > 0x10ffff)
                _illegalSurrogate(l2);
            i3 = j + 1;
            abyte0[j] = (byte)(0xf0 | l2 >> 18);
            if(i3 >= abyte0.length)
            {
                abyte0 = bytearraybuilder.finishCurrentSegment();
                j3 = 0;
            } else
            {
                j3 = i3;
            }
            k3 = j3 + 1;
            abyte0[j3] = (byte)(0x80 | 0x3f & l2 >> 12);
            if(k3 >= abyte0.length)
            {
                abyte0 = bytearraybuilder.finishCurrentSegment();
                l3 = 0;
            } else
            {
                l3 = k3;
            }
            k1 = l3 + 1;
            abyte0[l3] = (byte)(0x80 | 0x3f & l2 >> 6);
            i4 = 0x80 | l2 & 0x3f;
            l = k2;
            abyte2 = abyte0;
            i2 = i4;
            abyte1 = abyte2;
        }
          goto _L3
        if(true) goto _L2; else goto _L4
_L4:
    }

    private static final byte HEX_BYTES[] = CharTypes.copyHexBytes();
    private static final char HEX_CHARS[] = CharTypes.copyHexChars();
    private static final int INT_0 = 48;
    private static final int INT_BACKSLASH = 92;
    private static final int INT_U = 117;
    private static final int SURR1_FIRST = 55296;
    private static final int SURR1_LAST = 56319;
    private static final int SURR2_FIRST = 56320;
    private static final int SURR2_LAST = 57343;
    protected static final ThreadLocal _threadEncoder = new ThreadLocal();
    protected ByteArrayBuilder _byteBuilder;
    protected final char _quoteBuffer[] = new char[6];
    protected TextBuffer _textBuffer;

}
