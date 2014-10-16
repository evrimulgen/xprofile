// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.squareup.okhttp.internal.okio;

import java.io.Closeable;
import java.io.IOException;

// Referenced classes of package com.squareup.okhttp.internal.okio:
//            Deadline, OkBuffer

public interface Sink
    extends Closeable
{

    public abstract void close()
        throws IOException;

    public abstract Sink deadline(Deadline deadline1);

    public abstract void flush()
        throws IOException;

    public abstract void write(OkBuffer okbuffer, long l)
        throws IOException;
}
