// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.fasterxml.jackson.core;

import com.fasterxml.jackson.core.util.ByteArrayBuilder;
import java.io.Serializable;
import java.util.Arrays;

// Referenced classes of package com.fasterxml.jackson.core:
//            Base64Variants

public final class Base64Variant
    implements Serializable
{

    public Base64Variant(Base64Variant base64variant, String s, int i)
    {
        this(base64variant, s, base64variant._usesPadding, base64variant._paddingChar, i);
    }

    public Base64Variant(Base64Variant base64variant, String s, boolean flag, char c, int i)
    {
        _asciiToBase64 = new int[128];
        _base64ToAsciiC = new char[64];
        _base64ToAsciiB = new byte[64];
        _name = s;
        byte abyte0[] = base64variant._base64ToAsciiB;
        System.arraycopy(abyte0, 0, _base64ToAsciiB, 0, abyte0.length);
        char ac[] = base64variant._base64ToAsciiC;
        System.arraycopy(ac, 0, _base64ToAsciiC, 0, ac.length);
        int ai[] = base64variant._asciiToBase64;
        System.arraycopy(ai, 0, _asciiToBase64, 0, ai.length);
        _usesPadding = flag;
        _paddingChar = c;
        _maxLineLength = i;
    }

    public Base64Variant(String s, String s1, boolean flag, char c, int i)
    {
        int j = 0;
        super();
        _asciiToBase64 = new int[128];
        _base64ToAsciiC = new char[64];
        _base64ToAsciiB = new byte[64];
        _name = s;
        _usesPadding = flag;
        _paddingChar = c;
        _maxLineLength = i;
        int k = s1.length();
        if(k != 64)
            throw new IllegalArgumentException((new StringBuilder()).append("Base64Alphabet length must be exactly 64 (was ").append(k).append(")").toString());
        s1.getChars(0, k, _base64ToAsciiC, 0);
        Arrays.fill(_asciiToBase64, -1);
        for(; j < k; j++)
        {
            char c1 = _base64ToAsciiC[j];
            _base64ToAsciiB[j] = (byte)c1;
            _asciiToBase64[c1] = j;
        }

        if(flag)
            _asciiToBase64[c] = -2;
    }

    protected void _reportBase64EOF()
        throws IllegalArgumentException
    {
        throw new IllegalArgumentException("Unexpected end-of-String in base64 content");
    }

    protected void _reportInvalidBase64(char c, int i, String s)
        throws IllegalArgumentException
    {
        String s1;
        if(c <= ' ')
            s1 = (new StringBuilder()).append("Illegal white space character (code 0x").append(Integer.toHexString(c)).append(") as character #").append(i + 1).append(" of 4-char base64 unit: can only used between units").toString();
        else
        if(usesPaddingChar(c))
            s1 = (new StringBuilder()).append("Unexpected padding character ('").append(getPaddingChar()).append("') as character #").append(i + 1).append(" of 4-char base64 unit: padding only legal as 3rd or 4th character").toString();
        else
        if(!Character.isDefined(c) || Character.isISOControl(c))
            s1 = (new StringBuilder()).append("Illegal character (code 0x").append(Integer.toHexString(c)).append(") in base64 content").toString();
        else
            s1 = (new StringBuilder()).append("Illegal character '").append(c).append("' (code 0x").append(Integer.toHexString(c)).append(") in base64 content").toString();
        if(s != null)
            s1 = (new StringBuilder()).append(s1).append(": ").append(s).toString();
        throw new IllegalArgumentException(s1);
    }

    public void decode(String s, ByteArrayBuilder bytearraybuilder)
        throws IllegalArgumentException
    {
        int i;
        int j;
        i = s.length();
        j = 0;
_L4:
        if(j >= i) goto _L2; else goto _L1
_L1:
        int k;
        char c;
        k = j + 1;
        c = s.charAt(j);
        if(k < i) goto _L3; else goto _L2
_L2:
        return;
_L3:
label0:
        {
            if(c <= ' ')
                break label0;
            int l = decodeBase64Char(c);
            if(l < 0)
                _reportInvalidBase64(c, 0, null);
            if(k >= i)
                _reportBase64EOF();
            int i1 = k + 1;
            char c1 = s.charAt(k);
            int j1 = decodeBase64Char(c1);
            if(j1 < 0)
                _reportInvalidBase64(c1, 1, null);
            int k1 = j1 | l << 6;
            if(i1 >= i)
            {
                if(!usesPadding())
                {
                    bytearraybuilder.append(k1 >> 4);
                    return;
                }
                _reportBase64EOF();
            }
            int l1 = i1 + 1;
            char c2 = s.charAt(i1);
            int i2 = decodeBase64Char(c2);
            if(i2 < 0)
            {
                if(i2 != -2)
                    _reportInvalidBase64(c2, 2, null);
                if(l1 >= i)
                    _reportBase64EOF();
                j = l1 + 1;
                char c4 = s.charAt(l1);
                if(!usesPaddingChar(c4))
                    _reportInvalidBase64(c4, 3, (new StringBuilder()).append("expected padding character '").append(getPaddingChar()).append("'").toString());
                bytearraybuilder.append(k1 >> 4);
            } else
            {
                int j2 = i2 | k1 << 6;
                if(l1 >= i)
                {
                    if(!usesPadding())
                    {
                        bytearraybuilder.appendTwoBytes(j2 >> 2);
                        return;
                    }
                    _reportBase64EOF();
                }
                j = l1 + 1;
                char c3 = s.charAt(l1);
                int k2 = decodeBase64Char(c3);
                if(k2 < 0)
                {
                    if(k2 != -2)
                        _reportInvalidBase64(c3, 3, null);
                    bytearraybuilder.appendTwoBytes(j2 >> 2);
                } else
                {
                    bytearraybuilder.appendThreeBytes(k2 | j2 << 6);
                }
            }
        }
          goto _L4
        j = k;
          goto _L1
    }

    public byte[] decode(String s)
        throws IllegalArgumentException
    {
        ByteArrayBuilder bytearraybuilder = new ByteArrayBuilder();
        decode(s, bytearraybuilder);
        return bytearraybuilder.toByteArray();
    }

    public int decodeBase64Byte(byte byte0)
    {
        if(byte0 <= 127)
            return _asciiToBase64[byte0];
        else
            return -1;
    }

    public int decodeBase64Char(char c)
    {
        if(c <= '\177')
            return _asciiToBase64[c];
        else
            return -1;
    }

    public int decodeBase64Char(int i)
    {
        if(i <= 127)
            return _asciiToBase64[i];
        else
            return -1;
    }

    public String encode(byte abyte0[])
    {
        return encode(abyte0, false);
    }

    public String encode(byte abyte0[], boolean flag)
    {
        int i = abyte0.length;
        StringBuilder stringbuilder = new StringBuilder(i + (i >> 2) + (i >> 3));
        if(flag)
            stringbuilder.append('"');
        int j = getMaxLineLength() >> 2;
        int k = 0;
        int l = i - 3;
        int i1 = j;
        int i3;
        for(; k <= l; k = i3)
        {
            int i2 = k + 1;
            int j2 = abyte0[k] << 8;
            int k2 = i2 + 1;
            int l2 = (j2 | 0xff & abyte0[i2]) << 8;
            i3 = k2 + 1;
            encodeBase64Chunk(stringbuilder, l2 | 0xff & abyte0[k2]);
            int j3 = i1 - 1;
            if(j3 <= 0)
            {
                stringbuilder.append('\\');
                stringbuilder.append('n');
                j3 = getMaxLineLength() >> 2;
            }
            i1 = j3;
        }

        int j1 = i - k;
        if(j1 > 0)
        {
            int k1 = k + 1;
            int l1 = abyte0[k] << 16;
            if(j1 == 2)
            {
                int _tmp = k1 + 1;
                l1 |= (0xff & abyte0[k1]) << 8;
            }
            encodeBase64Partial(stringbuilder, l1, j1);
        }
        if(flag)
            stringbuilder.append('"');
        return stringbuilder.toString();
    }

    public byte encodeBase64BitsAsByte(int i)
    {
        return _base64ToAsciiB[i];
    }

    public char encodeBase64BitsAsChar(int i)
    {
        return _base64ToAsciiC[i];
    }

    public int encodeBase64Chunk(int i, byte abyte0[], int j)
    {
        int k = j + 1;
        abyte0[j] = _base64ToAsciiB[0x3f & i >> 18];
        int l = k + 1;
        abyte0[k] = _base64ToAsciiB[0x3f & i >> 12];
        int i1 = l + 1;
        abyte0[l] = _base64ToAsciiB[0x3f & i >> 6];
        int j1 = i1 + 1;
        abyte0[i1] = _base64ToAsciiB[i & 0x3f];
        return j1;
    }

    public int encodeBase64Chunk(int i, char ac[], int j)
    {
        int k = j + 1;
        ac[j] = _base64ToAsciiC[0x3f & i >> 18];
        int l = k + 1;
        ac[k] = _base64ToAsciiC[0x3f & i >> 12];
        int i1 = l + 1;
        ac[l] = _base64ToAsciiC[0x3f & i >> 6];
        int j1 = i1 + 1;
        ac[i1] = _base64ToAsciiC[i & 0x3f];
        return j1;
    }

    public void encodeBase64Chunk(StringBuilder stringbuilder, int i)
    {
        stringbuilder.append(_base64ToAsciiC[0x3f & i >> 18]);
        stringbuilder.append(_base64ToAsciiC[0x3f & i >> 12]);
        stringbuilder.append(_base64ToAsciiC[0x3f & i >> 6]);
        stringbuilder.append(_base64ToAsciiC[i & 0x3f]);
    }

    public int encodeBase64Partial(int i, int j, byte abyte0[], int k)
    {
        int l = k + 1;
        abyte0[k] = _base64ToAsciiB[0x3f & i >> 18];
        int i1 = l + 1;
        abyte0[l] = _base64ToAsciiB[0x3f & i >> 12];
        if(_usesPadding)
        {
            byte byte0 = (byte)_paddingChar;
            int k1 = i1 + 1;
            byte byte1;
            int l1;
            if(j == 2)
                byte1 = _base64ToAsciiB[0x3f & i >> 6];
            else
                byte1 = byte0;
            abyte0[i1] = byte1;
            l1 = k1 + 1;
            abyte0[k1] = byte0;
            return l1;
        }
        if(j == 2)
        {
            int j1 = i1 + 1;
            abyte0[i1] = _base64ToAsciiB[0x3f & i >> 6];
            return j1;
        } else
        {
            return i1;
        }
    }

    public int encodeBase64Partial(int i, int j, char ac[], int k)
    {
        int l = k + 1;
        ac[k] = _base64ToAsciiC[0x3f & i >> 18];
        int i1 = l + 1;
        ac[l] = _base64ToAsciiC[0x3f & i >> 12];
        if(_usesPadding)
        {
            int k1 = i1 + 1;
            char c;
            int l1;
            if(j == 2)
                c = _base64ToAsciiC[0x3f & i >> 6];
            else
                c = _paddingChar;
            ac[i1] = c;
            l1 = k1 + 1;
            ac[k1] = _paddingChar;
            return l1;
        }
        if(j == 2)
        {
            int j1 = i1 + 1;
            ac[i1] = _base64ToAsciiC[0x3f & i >> 6];
            return j1;
        } else
        {
            return i1;
        }
    }

    public void encodeBase64Partial(StringBuilder stringbuilder, int i, int j)
    {
        stringbuilder.append(_base64ToAsciiC[0x3f & i >> 18]);
        stringbuilder.append(_base64ToAsciiC[0x3f & i >> 12]);
        if(_usesPadding)
        {
            char c;
            if(j == 2)
                c = _base64ToAsciiC[0x3f & i >> 6];
            else
                c = _paddingChar;
            stringbuilder.append(c);
            stringbuilder.append(_paddingChar);
        } else
        if(j == 2)
        {
            stringbuilder.append(_base64ToAsciiC[0x3f & i >> 6]);
            return;
        }
    }

    public boolean equals(Object obj)
    {
        return obj == this;
    }

    public int getMaxLineLength()
    {
        return _maxLineLength;
    }

    public String getName()
    {
        return _name;
    }

    public byte getPaddingByte()
    {
        return (byte)_paddingChar;
    }

    public char getPaddingChar()
    {
        return _paddingChar;
    }

    public int hashCode()
    {
        return _name.hashCode();
    }

    protected Object readResolve()
    {
        return Base64Variants.valueOf(_name);
    }

    public String toString()
    {
        return _name;
    }

    public boolean usesPadding()
    {
        return _usesPadding;
    }

    public boolean usesPaddingChar(char c)
    {
        return c == _paddingChar;
    }

    public boolean usesPaddingChar(int i)
    {
        return i == _paddingChar;
    }

    public static final int BASE64_VALUE_INVALID = -1;
    public static final int BASE64_VALUE_PADDING = -2;
    private static final int INT_SPACE = 32;
    static final char PADDING_CHAR_NONE = 0;
    private static final long serialVersionUID = 1L;
    private final transient int _asciiToBase64[];
    private final transient byte _base64ToAsciiB[];
    private final transient char _base64ToAsciiC[];
    protected final transient int _maxLineLength;
    protected final String _name;
    protected final transient char _paddingChar;
    protected final transient boolean _usesPadding;
}
