// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.squareup.okhttp.internal.okio;

import java.io.IOException;
import java.io.InputStream;

// Referenced classes of package com.squareup.okhttp.internal.okio:
//            Source, OkBuffer, ByteString

public interface BufferedSource
    extends Source
{

    public abstract OkBuffer buffer();

    public abstract boolean exhausted()
        throws IOException;

    public abstract InputStream inputStream();

    public abstract byte readByte()
        throws IOException;

    public abstract ByteString readByteString(long l)
        throws IOException;

    public abstract int readInt()
        throws IOException;

    public abstract int readIntLe()
        throws IOException;

    public abstract short readShort()
        throws IOException;

    public abstract int readShortLe()
        throws IOException;

    public abstract String readUtf8(long l)
        throws IOException;

    public abstract String readUtf8Line(boolean flag)
        throws IOException;

    public abstract void require(long l)
        throws IOException;

    public abstract long seek(byte byte0)
        throws IOException;

    public abstract void skip(long l)
        throws IOException;
}
