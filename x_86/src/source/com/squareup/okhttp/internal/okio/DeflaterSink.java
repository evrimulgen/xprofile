// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.squareup.okhttp.internal.okio;

import java.io.IOException;
import java.util.zip.Deflater;

// Referenced classes of package com.squareup.okhttp.internal.okio:
//            Sink, Okio, BufferedSink, OkBuffer, 
//            Segment, Util, SegmentPool, Deadline

public final class DeflaterSink
    implements Sink
{

    public DeflaterSink(Sink sink1, Deflater deflater1)
    {
        sink = Okio.buffer(sink1);
        deflater = deflater1;
    }

    private void deflate(boolean flag)
        throws IOException
    {
        OkBuffer okbuffer = sink.buffer();
label0:
        do
            do
            {
                Segment segment = okbuffer.writableSegment(1);
                int i;
                if(flag)
                    i = deflater.deflate(segment.data, segment.limit, 2048 - segment.limit, 2);
                else
                    i = deflater.deflate(segment.data, segment.limit, 2048 - segment.limit);
                if(i <= 0)
                    continue label0;
                segment.limit = i + segment.limit;
                okbuffer.size = okbuffer.size + (long)i;
                sink.emitCompleteSegments();
            } while(true);
        while(!deflater.needsInput());
    }

    public void close()
        throws IOException
    {
        if(!closed) goto _L2; else goto _L1
_L1:
        return;
_L2:
        Throwable throwable = null;
        try
        {
            deflater.finish();
            deflate(false);
        }
        catch(Throwable throwable1)
        {
            throwable = throwable1;
        }
        try
        {
            deflater.end();
        }
        catch(Throwable throwable2)
        {
            if(throwable == null)
                throwable = throwable2;
        }
        try
        {
            sink.close();
        }
        catch(Throwable throwable3)
        {
            if(throwable == null)
                throwable = throwable3;
        }
        closed = true;
        if(throwable != null)
        {
            Util.sneakyRethrow(throwable);
            return;
        }
        if(true) goto _L1; else goto _L3
_L3:
    }

    public Sink deadline(Deadline deadline1)
    {
        sink.deadline(deadline1);
        return this;
    }

    public void flush()
        throws IOException
    {
        deflate(true);
        sink.flush();
    }

    public String toString()
    {
        return (new StringBuilder()).append("DeflaterSink(").append(sink).append(")").toString();
    }

    public void write(OkBuffer okbuffer, long l)
        throws IOException
    {
        Util.checkOffsetAndCount(okbuffer.size, 0L, l);
        int i;
        for(; l > 0L; l -= i)
        {
            Segment segment = okbuffer.head;
            i = (int)Math.min(l, segment.limit - segment.pos);
            deflater.setInput(segment.data, segment.pos, i);
            deflate(false);
            okbuffer.size = okbuffer.size - (long)i;
            segment.pos = i + segment.pos;
            if(segment.pos == segment.limit)
            {
                okbuffer.head = segment.pop();
                SegmentPool.INSTANCE.recycle(segment);
            }
        }

    }

    private boolean closed;
    private final Deflater deflater;
    private final BufferedSink sink;
}
