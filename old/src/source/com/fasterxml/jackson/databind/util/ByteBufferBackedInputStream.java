// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.fasterxml.jackson.databind.util;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

public class ByteBufferBackedInputStream extends InputStream
{

    public ByteBufferBackedInputStream(ByteBuffer bytebuffer)
    {
        _buffer = bytebuffer;
    }

    public int available()
    {
        return _buffer.remaining();
    }

    public int read()
        throws IOException
    {
        if(_buffer.hasRemaining())
            return 0xff & _buffer.get();
        else
            return -1;
    }

    public int read(byte abyte0[], int i, int j)
        throws IOException
    {
        if(!_buffer.hasRemaining())
        {
            return -1;
        } else
        {
            int k = Math.min(j, _buffer.remaining());
            _buffer.get(abyte0, i, k);
            return k;
        }
    }

    protected final ByteBuffer _buffer;
}
