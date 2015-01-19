// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.squareup.okhttp.internal.spdy;

import com.squareup.okhttp.internal.okio.OkBuffer;
import java.io.Closeable;
import java.io.IOException;
import java.util.List;

// Referenced classes of package com.squareup.okhttp.internal.spdy:
//            ErrorCode, Settings

public interface FrameWriter
    extends Closeable
{

    public abstract void ackSettings()
        throws IOException;

    public abstract void connectionHeader()
        throws IOException;

    public abstract void data(boolean flag, int i, OkBuffer okbuffer)
        throws IOException;

    public abstract void data(boolean flag, int i, OkBuffer okbuffer, int j)
        throws IOException;

    public abstract void flush()
        throws IOException;

    public abstract void goAway(int i, ErrorCode errorcode, byte abyte0[])
        throws IOException;

    public abstract void headers(int i, List list)
        throws IOException;

    public abstract void ping(boolean flag, int i, int j)
        throws IOException;

    public abstract void pushPromise(int i, int j, List list)
        throws IOException;

    public abstract void rstStream(int i, ErrorCode errorcode)
        throws IOException;

    public abstract void settings(Settings settings1)
        throws IOException;

    public abstract void synReply(boolean flag, int i, List list)
        throws IOException;

    public abstract void synStream(boolean flag, boolean flag1, int i, int j, int k, int l, List list)
        throws IOException;

    public abstract void windowUpdate(int i, long l)
        throws IOException;
}
