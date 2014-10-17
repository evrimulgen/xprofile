// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.fasterxml.jackson.core.io;

import java.io.*;

// Referenced classes of package com.fasterxml.jackson.core.io:
//            BaseReader, IOContext

public class UTF32Reader extends BaseReader
{

    public UTF32Reader(IOContext iocontext, InputStream inputstream, byte abyte0[], int i, int j, boolean flag)
    {
        super(iocontext, inputstream, abyte0, i, j);
        _surrogate = '\0';
        _charCount = 0;
        _byteCount = 0;
        _bigEndian = flag;
        boolean flag1 = false;
        if(inputstream != null)
            flag1 = true;
        _managedBuffers = flag1;
    }

    private boolean loadMore(int i)
        throws IOException
    {
        _byteCount = _byteCount + (_length - i);
        if(i > 0)
        {
            if(_ptr > 0)
            {
                for(int l = 0; l < i; l++)
                    _buffer[l] = _buffer[l + _ptr];

                _ptr = 0;
            }
            _length = i;
        } else
        {
            _ptr = 0;
            int j;
            if(_in == null)
                j = -1;
            else
                j = _in.read(_buffer);
            if(j < 1)
            {
                _length = 0;
                if(j < 0)
                {
                    if(_managedBuffers)
                        freeBuffers();
                    return false;
                }
                reportStrangeStream();
            }
            _length = j;
        }
        while(_length < 4) 
        {
            int k;
            if(_in == null)
                k = -1;
            else
                k = _in.read(_buffer, _length, _buffer.length - _length);
            if(k < 1)
            {
                if(k < 0)
                {
                    if(_managedBuffers)
                        freeBuffers();
                    reportUnexpectedEOF(_length, 4);
                }
                reportStrangeStream();
            }
            _length = k + _length;
        }
        return true;
    }

    private void reportInvalid(int i, int j, String s)
        throws IOException
    {
        int k = -1 + (_byteCount + _ptr);
        int l = j + _charCount;
        throw new CharConversionException((new StringBuilder()).append("Invalid UTF-32 character 0x").append(Integer.toHexString(i)).append(s).append(" at char #").append(l).append(", byte #").append(k).append(")").toString());
    }

    private void reportUnexpectedEOF(int i, int j)
        throws IOException
    {
        int k = i + _byteCount;
        int l = _charCount;
        throw new CharConversionException((new StringBuilder()).append("Unexpected EOF in the middle of a 4-byte UTF-32 char: got ").append(i).append(", needed ").append(j).append(", at char #").append(l).append(", byte #").append(k).append(")").toString());
    }

    public volatile void close()
        throws IOException
    {
        super.close();
    }

    public volatile int read()
        throws IOException
    {
        return super.read();
    }

    public int read(char ac[], int i, int j)
        throws IOException
    {
        if(_buffer != null) goto _L2; else goto _L1
_L1:
        j = -1;
_L4:
        return j;
_L2:
        if(j < 1) goto _L4; else goto _L3
_L3:
        int k;
        if(i < 0 || i + j > ac.length)
            reportBounds(ac, i, j);
        k = j + i;
        if(_surrogate == 0) goto _L6; else goto _L5
_L5:
        int i1;
        i1 = i + 1;
        ac[i] = _surrogate;
        _surrogate = '\0';
_L16:
        if(i1 >= k) goto _L8; else goto _L7
_L7:
        int j1;
        int i2;
        int l1 = _ptr;
        int l;
        int k1;
        int j2;
        if(_bigEndian)
            i2 = _buffer[l1] << 24 | (0xff & _buffer[l1 + 1]) << 16 | (0xff & _buffer[l1 + 2]) << 8 | 0xff & _buffer[l1 + 3];
        else
            i2 = 0xff & _buffer[l1] | (0xff & _buffer[l1 + 1]) << 8 | (0xff & _buffer[l1 + 2]) << 16 | _buffer[l1 + 3] << 24;
        _ptr = 4 + _ptr;
        if(i2 <= 65535) goto _L10; else goto _L9
_L9:
        if(i2 > 0x10ffff)
            reportInvalid(i2, i1 - i, (new StringBuilder()).append("(above ").append(Integer.toHexString(0x10ffff)).append(") ").toString());
        j2 = i2 - 0x10000;
        j1 = i1 + 1;
        ac[i1] = (char)(55296 + (j2 >> 10));
        i2 = 0xdc00 | j2 & 0x3ff;
        if(j1 < k) goto _L12; else goto _L11
_L11:
        _surrogate = (char)i2;
_L14:
        k1 = j1 - i;
        _charCount = k1 + _charCount;
        return k1;
_L6:
        l = _length - _ptr;
        if(l < 4 && !loadMore(l))
            return -1;
        break; /* Loop/switch isn't completed */
_L10:
        j1 = i1;
_L12:
        i1 = j1 + 1;
        ac[j1] = (char)i2;
        if(_ptr < _length)
            continue; /* Loop/switch isn't completed */
        j1 = i1;
        continue; /* Loop/switch isn't completed */
_L8:
        j1 = i1;
        if(true) goto _L14; else goto _L13
_L13:
        i1 = i;
        if(true) goto _L16; else goto _L15
_L15:
    }

    protected final boolean _bigEndian;
    protected int _byteCount;
    protected int _charCount;
    protected final boolean _managedBuffers;
    protected char _surrogate;
}
