// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.fasterxml.jackson.core.io;

import java.io.*;

// Referenced classes of package com.fasterxml.jackson.core.io:
//            IOContext

public final class UTF8Writer extends Writer
{

    public UTF8Writer(IOContext iocontext, OutputStream outputstream)
    {
        _surrogate = 0;
        _context = iocontext;
        _out = outputstream;
        _outBuffer = iocontext.allocWriteEncodingBuffer();
        _outBufferEnd = -4 + _outBuffer.length;
        _outPtr = 0;
    }

    protected static void illegalSurrogate(int i)
        throws IOException
    {
        throw new IOException(illegalSurrogateDesc(i));
    }

    protected static String illegalSurrogateDesc(int i)
    {
        if(i > 0x10ffff)
            return (new StringBuilder()).append("Illegal character point (0x").append(Integer.toHexString(i)).append(") to output; max is 0x10FFFF as per RFC 4627").toString();
        if(i >= 55296)
        {
            if(i <= 56319)
                return (new StringBuilder()).append("Unmatched first part of surrogate pair (0x").append(Integer.toHexString(i)).append(")").toString();
            else
                return (new StringBuilder()).append("Unmatched second part of surrogate pair (0x").append(Integer.toHexString(i)).append(")").toString();
        } else
        {
            return (new StringBuilder()).append("Illegal character point (0x").append(Integer.toHexString(i)).append(") to output").toString();
        }
    }

    public Writer append(char c)
        throws IOException
    {
        write(c);
        return this;
    }

    public volatile Appendable append(char c)
        throws IOException
    {
        return append(c);
    }

    public void close()
        throws IOException
    {
        if(_out != null)
        {
            if(_outPtr > 0)
            {
                _out.write(_outBuffer, 0, _outPtr);
                _outPtr = 0;
            }
            OutputStream outputstream = _out;
            _out = null;
            byte abyte0[] = _outBuffer;
            if(abyte0 != null)
            {
                _outBuffer = null;
                _context.releaseWriteEncodingBuffer(abyte0);
            }
            outputstream.close();
            int i = _surrogate;
            _surrogate = 0;
            if(i > 0)
                illegalSurrogate(i);
        }
    }

    protected int convertSurrogate(int i)
        throws IOException
    {
        int j = _surrogate;
        _surrogate = 0;
        if(i < 56320 || i > 57343)
            throw new IOException((new StringBuilder()).append("Broken surrogate pair: first char 0x").append(Integer.toHexString(j)).append(", second 0x").append(Integer.toHexString(i)).append("; illegal combination").toString());
        else
            return 0x10000 + (j - 55296 << 10) + (i - 56320);
    }

    public void flush()
        throws IOException
    {
        if(_out != null)
        {
            if(_outPtr > 0)
            {
                _out.write(_outBuffer, 0, _outPtr);
                _outPtr = 0;
            }
            _out.flush();
        }
    }

    public void write(int i)
        throws IOException
    {
        if(_surrogate > 0)
            i = convertSurrogate(i);
        else
        if(i >= 55296 && i <= 57343)
        {
            if(i > 56319)
                illegalSurrogate(i);
            _surrogate = i;
            return;
        }
        if(_outPtr >= _outBufferEnd)
        {
            _out.write(_outBuffer, 0, _outPtr);
            _outPtr = 0;
        }
        if(i < 128)
        {
            byte abyte9[] = _outBuffer;
            int j2 = _outPtr;
            _outPtr = j2 + 1;
            abyte9[j2] = (byte)i;
            return;
        }
        int j = _outPtr;
        int j1;
        if(i < 2048)
        {
            byte abyte7[] = _outBuffer;
            int i2 = j + 1;
            abyte7[j] = (byte)(0xc0 | i >> 6);
            byte abyte8[] = _outBuffer;
            j1 = i2 + 1;
            abyte8[i2] = (byte)(0x80 | i & 0x3f);
        } else
        if(i <= 65535)
        {
            byte abyte4[] = _outBuffer;
            int k1 = j + 1;
            abyte4[j] = (byte)(0xe0 | i >> 12);
            byte abyte5[] = _outBuffer;
            int l1 = k1 + 1;
            abyte5[k1] = (byte)(0x80 | 0x3f & i >> 6);
            byte abyte6[] = _outBuffer;
            j1 = l1 + 1;
            abyte6[l1] = (byte)(0x80 | i & 0x3f);
        } else
        {
            if(i > 0x10ffff)
                illegalSurrogate(i);
            byte abyte0[] = _outBuffer;
            int k = j + 1;
            abyte0[j] = (byte)(0xf0 | i >> 18);
            byte abyte1[] = _outBuffer;
            int l = k + 1;
            abyte1[k] = (byte)(0x80 | 0x3f & i >> 12);
            byte abyte2[] = _outBuffer;
            int i1 = l + 1;
            abyte2[l] = (byte)(0x80 | 0x3f & i >> 6);
            byte abyte3[] = _outBuffer;
            j1 = i1 + 1;
            abyte3[i1] = (byte)(0x80 | i & 0x3f);
        }
        _outPtr = j1;
    }

    public void write(String s)
        throws IOException
    {
        write(s, 0, s.length());
    }

    public void write(String s, int i, int j)
        throws IOException
    {
        int k;
        byte abyte0[];
        int l;
        int i1;
        int j1;
        if(j < 2)
        {
            if(j == 1)
                write(s.charAt(i));
            return;
        }
        if(_surrogate > 0)
        {
            int j6 = i + 1;
            char c2 = s.charAt(i);
            j--;
            write(convertSurrogate(c2));
            i = j6;
        }
        k = _outPtr;
        abyte0 = _outBuffer;
        l = _outBufferEnd;
        i1 = j + i;
        j1 = i;
_L3:
        if(j1 >= i1) goto _L2; else goto _L1
_L1:
        int k1;
        char c;
        int l1;
        if(k >= l)
        {
            _out.write(abyte0, 0, k);
            k = 0;
        }
        k1 = j1 + 1;
        c = s.charAt(j1);
        if(c >= '\200')
            break MISSING_BLOCK_LABEL_577;
        int i4 = k + 1;
        abyte0[k] = (byte)c;
        int j4 = i1 - k1;
        int k4 = l - i4;
        int i2;
        int j2;
        int k2;
        int l2;
        int i3;
        int j3;
        int k3;
        int l3;
        int l4;
        int i5;
        int j5;
        int k5;
        char c1;
        int l5;
        int i6;
        if(j4 <= k4)
            k4 = j4;
        l4 = k4 + k1;
        i5 = i4;
        j5 = k1;
label0:
        {
            if(j5 < l4)
                break label0;
            i6 = i5;
            j1 = j5;
            k = i6;
        }
          goto _L3
        k5 = j5 + 1;
        c1 = s.charAt(j5);
        if(c1 < '\200') goto _L5; else goto _L4
_L4:
        k = i5;
        j1 = k5;
        l1 = c1;
_L7:
        if(l1 < 2048)
        {
            l3 = k + 1;
            abyte0[k] = (byte)(0xc0 | l1 >> 6);
            k = l3 + 1;
            abyte0[l3] = (byte)(0x80 | l1 & 0x3f);
        } else
        {
label1:
            {
                if(l1 >= 55296 && l1 <= 57343)
                    break label1;
                i2 = k + 1;
                abyte0[k] = (byte)(0xe0 | l1 >> 12);
                j2 = i2 + 1;
                abyte0[i2] = (byte)(0x80 | 0x3f & l1 >> 6);
                k = j2 + 1;
                abyte0[j2] = (byte)(0x80 | l1 & 0x3f);
            }
        }
          goto _L3
_L5:
        l5 = i5 + 1;
        abyte0[i5] = (byte)c1;
        i5 = l5;
        j5 = k5;
        break MISSING_BLOCK_LABEL_182;
        if(l1 > 56319)
        {
            _outPtr = k;
            illegalSurrogate(l1);
        }
        _surrogate = l1;
        if(j1 < i1) goto _L6; else goto _L2
_L2:
        _outPtr = k;
        return;
_L6:
        k2 = j1 + 1;
        l2 = convertSurrogate(s.charAt(j1));
        if(l2 > 0x10ffff)
        {
            _outPtr = k;
            illegalSurrogate(l2);
        }
        i3 = k + 1;
        abyte0[k] = (byte)(0xf0 | l2 >> 18);
        j3 = i3 + 1;
        abyte0[i3] = (byte)(0x80 | 0x3f & l2 >> 12);
        k3 = j3 + 1;
        abyte0[j3] = (byte)(0x80 | 0x3f & l2 >> 6);
        k = k3 + 1;
        abyte0[k3] = (byte)(0x80 | l2 & 0x3f);
        j1 = k2;
          goto _L3
        j1 = k1;
        l1 = c;
          goto _L7
    }

    public void write(char ac[])
        throws IOException
    {
        write(ac, 0, ac.length);
    }

    public void write(char ac[], int i, int j)
        throws IOException
    {
        int k;
        byte abyte0[];
        int l;
        int i1;
        int j1;
        if(j < 2)
        {
            if(j == 1)
                write(ac[i]);
            return;
        }
        if(_surrogate > 0)
        {
            int j6 = i + 1;
            char c2 = ac[i];
            j--;
            write(convertSurrogate(c2));
            i = j6;
        }
        k = _outPtr;
        abyte0 = _outBuffer;
        l = _outBufferEnd;
        i1 = j + i;
        j1 = i;
_L3:
        if(j1 >= i1) goto _L2; else goto _L1
_L1:
        int k1;
        char c;
        int l1;
        if(k >= l)
        {
            _out.write(abyte0, 0, k);
            k = 0;
        }
        k1 = j1 + 1;
        c = ac[j1];
        if(c >= '\200')
            break MISSING_BLOCK_LABEL_567;
        int i4 = k + 1;
        abyte0[k] = (byte)c;
        int j4 = i1 - k1;
        int k4 = l - i4;
        int i2;
        int j2;
        int k2;
        int l2;
        int i3;
        int j3;
        int k3;
        int l3;
        int l4;
        int i5;
        int j5;
        int k5;
        char c1;
        int l5;
        int i6;
        if(j4 <= k4)
            k4 = j4;
        l4 = k4 + k1;
        i5 = i4;
        j5 = k1;
label0:
        {
            if(j5 < l4)
                break label0;
            i6 = i5;
            j1 = j5;
            k = i6;
        }
          goto _L3
        k5 = j5 + 1;
        c1 = ac[j5];
        if(c1 < '\200') goto _L5; else goto _L4
_L4:
        k = i5;
        j1 = k5;
        l1 = c1;
_L7:
        if(l1 < 2048)
        {
            l3 = k + 1;
            abyte0[k] = (byte)(0xc0 | l1 >> 6);
            k = l3 + 1;
            abyte0[l3] = (byte)(0x80 | l1 & 0x3f);
        } else
        {
label1:
            {
                if(l1 >= 55296 && l1 <= 57343)
                    break label1;
                i2 = k + 1;
                abyte0[k] = (byte)(0xe0 | l1 >> 12);
                j2 = i2 + 1;
                abyte0[i2] = (byte)(0x80 | 0x3f & l1 >> 6);
                k = j2 + 1;
                abyte0[j2] = (byte)(0x80 | l1 & 0x3f);
            }
        }
          goto _L3
_L5:
        l5 = i5 + 1;
        abyte0[i5] = (byte)c1;
        i5 = l5;
        j5 = k5;
        break MISSING_BLOCK_LABEL_176;
        if(l1 > 56319)
        {
            _outPtr = k;
            illegalSurrogate(l1);
        }
        _surrogate = l1;
        if(j1 < i1) goto _L6; else goto _L2
_L2:
        _outPtr = k;
        return;
_L6:
        k2 = j1 + 1;
        l2 = convertSurrogate(ac[j1]);
        if(l2 > 0x10ffff)
        {
            _outPtr = k;
            illegalSurrogate(l2);
        }
        i3 = k + 1;
        abyte0[k] = (byte)(0xf0 | l2 >> 18);
        j3 = i3 + 1;
        abyte0[i3] = (byte)(0x80 | 0x3f & l2 >> 12);
        k3 = j3 + 1;
        abyte0[j3] = (byte)(0x80 | 0x3f & l2 >> 6);
        k = k3 + 1;
        abyte0[k3] = (byte)(0x80 | l2 & 0x3f);
        j1 = k2;
          goto _L3
        j1 = k1;
        l1 = c;
          goto _L7
    }

    static final int SURR1_FIRST = 55296;
    static final int SURR1_LAST = 56319;
    static final int SURR2_FIRST = 56320;
    static final int SURR2_LAST = 57343;
    private final IOContext _context;
    private OutputStream _out;
    private byte _outBuffer[];
    private final int _outBufferEnd;
    private int _outPtr;
    private int _surrogate;
}
