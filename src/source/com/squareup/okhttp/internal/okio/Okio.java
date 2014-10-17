// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.squareup.okhttp.internal.okio;

import java.io.*;

// Referenced classes of package com.squareup.okhttp.internal.okio:
//            RealBufferedSink, RealBufferedSource, OkBuffer, Util, 
//            Segment, Sink, BufferedSink, Source, 
//            BufferedSource, Deadline, SegmentPool

public final class Okio
{

    private Okio()
    {
    }

    public static BufferedSink buffer(Sink sink1)
    {
        return new RealBufferedSink(sink1);
    }

    public static BufferedSource buffer(Source source1)
    {
        return new RealBufferedSource(source1);
    }

    public static void copy(OkBuffer okbuffer, long l, long l1, OutputStream outputstream)
        throws IOException
    {
        Util.checkOffsetAndCount(okbuffer.size, l, l1);
        Segment segment;
        for(segment = okbuffer.head; l >= (long)(segment.limit - segment.pos); segment = segment.next)
            l -= segment.limit - segment.pos;

        while(l1 > 0L) 
        {
            int i = (int)(l + (long)segment.pos);
            int j = (int)Math.min(segment.limit - i, l1);
            outputstream.write(segment.data, i, j);
            l1 -= j;
            l = 0L;
        }
    }

    public static Sink sink(OutputStream outputstream)
    {
        return new Sink(outputstream) {

            public void close()
                throws IOException
            {
                out.close();
            }

            public Sink deadline(Deadline deadline1)
            {
                if(deadline1 == null)
                {
                    throw new IllegalArgumentException("deadline == null");
                } else
                {
                    deadline = deadline1;
                    return this;
                }
            }

            public void flush()
                throws IOException
            {
                out.flush();
            }

            public String toString()
            {
                return (new StringBuilder()).append("sink(").append(out).append(")").toString();
            }

            public void write(OkBuffer okbuffer, long l)
                throws IOException
            {
                Util.checkOffsetAndCount(okbuffer.size, 0L, l);
                do
                {
                    if(l <= 0L)
                        break;
                    deadline.throwIfReached();
                    Segment segment = okbuffer.head;
                    int i = (int)Math.min(l, segment.limit - segment.pos);
                    out.write(segment.data, segment.pos, i);
                    segment.pos = i + segment.pos;
                    l -= i;
                    okbuffer.size = okbuffer.size - (long)i;
                    if(segment.pos == segment.limit)
                    {
                        okbuffer.head = segment.pop();
                        SegmentPool.INSTANCE.recycle(segment);
                    }
                } while(true);
            }

            private Deadline deadline;
            final OutputStream val$out;

            
            {
                out = outputstream;
                super();
                deadline = Deadline.NONE;
            }
        }
;
    }

    public static Source source(InputStream inputstream)
    {
        return new Source(inputstream) {

            public void close()
                throws IOException
            {
                in.close();
            }

            public Source deadline(Deadline deadline1)
            {
                if(deadline1 == null)
                {
                    throw new IllegalArgumentException("deadline == null");
                } else
                {
                    deadline = deadline1;
                    return this;
                }
            }

            public long read(OkBuffer okbuffer, long l)
                throws IOException
            {
                if(l < 0L)
                    throw new IllegalArgumentException((new StringBuilder()).append("byteCount < 0: ").append(l).toString());
                deadline.throwIfReached();
                Segment segment = okbuffer.writableSegment(1);
                int i = (int)Math.min(l, 2048 - segment.limit);
                int j = in.read(segment.data, segment.limit, i);
                if(j == -1)
                {
                    return -1L;
                } else
                {
                    segment.limit = j + segment.limit;
                    okbuffer.size = okbuffer.size + (long)j;
                    return (long)j;
                }
            }

            public String toString()
            {
                return (new StringBuilder()).append("source(").append(in).append(")").toString();
            }

            private Deadline deadline;
            final InputStream val$in;

            
            {
                in = inputstream;
                super();
                deadline = Deadline.NONE;
            }
        }
;
    }
}
