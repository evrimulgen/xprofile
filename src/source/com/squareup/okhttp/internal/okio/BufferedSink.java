// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.squareup.okhttp.internal.okio;

import java.io.IOException;
import java.io.OutputStream;

// Referenced classes of package com.squareup.okhttp.internal.okio:
//            Sink, OkBuffer, ByteString

public interface BufferedSink
    extends Sink
{

    public abstract OkBuffer buffer();

    public abstract BufferedSink emitCompleteSegments()
        throws IOException;

    public abstract OutputStream outputStream();

    public abstract BufferedSink write(ByteString bytestring)
        throws IOException;

    public abstract BufferedSink write(byte abyte0[])
        throws IOException;

    public abstract BufferedSink write(byte abyte0[], int i, int j)
        throws IOException;

    public abstract BufferedSink writeByte(int i)
        throws IOException;

    public abstract BufferedSink writeInt(int i)
        throws IOException;

    public abstract BufferedSink writeShort(int i)
        throws IOException;

    public abstract BufferedSink writeUtf8(String s)
        throws IOException;
}
