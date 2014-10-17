// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.squareup.okhttp.internal.okio;

import java.io.Closeable;
import java.io.IOException;

// Referenced classes of package com.squareup.okhttp.internal.okio:
//            Deadline, OkBuffer

public interface Source
    extends Closeable
{

    public abstract void close()
        throws IOException;

    public abstract Source deadline(Deadline deadline1);

    public abstract long read(OkBuffer okbuffer, long l)
        throws IOException;
}
