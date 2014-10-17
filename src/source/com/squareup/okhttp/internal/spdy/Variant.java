// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.squareup.okhttp.internal.spdy;

import com.squareup.okhttp.Protocol;
import com.squareup.okhttp.internal.okio.BufferedSink;
import com.squareup.okhttp.internal.okio.BufferedSource;

// Referenced classes of package com.squareup.okhttp.internal.spdy:
//            FrameReader, FrameWriter

interface Variant
{

    public abstract Protocol getProtocol();

    public abstract int maxFrameSize();

    public abstract FrameReader newReader(BufferedSource bufferedsource, boolean flag);

    public abstract FrameWriter newWriter(BufferedSink bufferedsink, boolean flag);
}
