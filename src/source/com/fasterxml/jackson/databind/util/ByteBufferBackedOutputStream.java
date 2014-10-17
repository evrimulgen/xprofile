// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.fasterxml.jackson.databind.util;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;

public class ByteBufferBackedOutputStream extends OutputStream
{

    public ByteBufferBackedOutputStream(ByteBuffer bytebuffer)
    {
        _buffer = bytebuffer;
    }

    public void write(int i)
        throws IOException
    {
        _buffer.put((byte)i);
    }

    public void write(byte abyte0[], int i, int j)
        throws IOException
    {
        _buffer.put(abyte0, i, j);
    }

    protected final ByteBuffer _buffer;
}
