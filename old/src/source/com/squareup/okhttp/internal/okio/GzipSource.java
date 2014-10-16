// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.squareup.okhttp.internal.okio;

import java.io.IOException;
import java.util.zip.CRC32;
import java.util.zip.Inflater;

// Referenced classes of package com.squareup.okhttp.internal.okio:
//            Source, Okio, InflaterSource, BufferedSource, 
//            OkBuffer, Segment, Deadline

public final class GzipSource
    implements Source
{

    public GzipSource(Source source1)
        throws IOException
    {
        section = 0;
        source = Okio.buffer(source1);
        inflaterSource = new InflaterSource(source, inflater);
    }

    private void checkEqual(String s, int i, int j)
        throws IOException
    {
        if(j != i)
        {
            Object aobj[] = new Object[3];
            aobj[0] = s;
            aobj[1] = Integer.valueOf(j);
            aobj[2] = Integer.valueOf(i);
            throw new IOException(String.format("%s: actual 0x%08x != expected 0x%08x", aobj));
        } else
        {
            return;
        }
    }

    private void consumeHeader()
        throws IOException
    {
        source.require(10L);
        byte byte0 = source.buffer().getByte(3L);
        boolean flag;
        if((1 & byte0 >> 1) == 1)
            flag = true;
        else
            flag = false;
        if(flag)
            updateCrc(source.buffer(), 0L, 10L);
        checkEqual("ID1ID2", 8075, source.readShort());
        source.skip(8L);
        if((1 & byte0 >> 2) == 1)
        {
            source.require(2L);
            if(flag)
                updateCrc(source.buffer(), 0L, 2L);
            int i = 0xffff & source.buffer().readShortLe();
            source.require(i);
            if(flag)
                updateCrc(source.buffer(), 0L, i);
            source.skip(i);
        }
        if((1 & byte0 >> 3) == 1)
        {
            long l1 = source.seek((byte)0);
            if(flag)
                updateCrc(source.buffer(), 0L, 1L + l1);
            source.skip(1L + l1);
        }
        if((1 & byte0 >> 4) == 1)
        {
            long l = source.seek((byte)0);
            if(flag)
                updateCrc(source.buffer(), 0L, 1L + l);
            source.skip(1L + l);
        }
        if(flag)
        {
            checkEqual("FHCRC", source.readShortLe(), (short)(int)crc.getValue());
            crc.reset();
        }
    }

    private void consumeTrailer()
        throws IOException
    {
        checkEqual("CRC", source.readIntLe(), (int)crc.getValue());
        checkEqual("ISIZE", source.readIntLe(), inflater.getTotalOut());
    }

    private void updateCrc(OkBuffer okbuffer, long l, long l1)
    {
        for(Segment segment = okbuffer.head; l1 > 0L; segment = segment.next)
        {
            int i = segment.limit - segment.pos;
            if(l < (long)i)
            {
                int j = (int)Math.min(l1, (long)i - l);
                crc.update(segment.data, (int)(l + (long)segment.pos), j);
                l1 -= j;
            }
            l -= i;
        }

    }

    public void close()
        throws IOException
    {
        inflaterSource.close();
    }

    public Source deadline(Deadline deadline1)
    {
        source.deadline(deadline1);
        return this;
    }

    public long read(OkBuffer okbuffer, long l)
        throws IOException
    {
        if(l < 0L)
            throw new IllegalArgumentException((new StringBuilder()).append("byteCount < 0: ").append(l).toString());
        if(l == 0L)
            return 0L;
        if(section == 0)
        {
            consumeHeader();
            section = 1;
        }
        if(section == 1)
        {
            long l1 = okbuffer.size;
            long l2 = inflaterSource.read(okbuffer, l);
            if(l2 != -1L)
            {
                updateCrc(okbuffer, l1, l2);
                return l2;
            }
            section = 2;
        }
        if(section == 2)
        {
            consumeTrailer();
            section = 3;
            if(!source.exhausted())
                throw new IOException("gzip finished without exhausting source");
        }
        return -1L;
    }

    private static final byte FCOMMENT = 4;
    private static final byte FEXTRA = 2;
    private static final byte FHCRC = 1;
    private static final byte FNAME = 3;
    private static final byte SECTION_BODY = 1;
    private static final byte SECTION_DONE = 3;
    private static final byte SECTION_HEADER = 0;
    private static final byte SECTION_TRAILER = 2;
    private final CRC32 crc = new CRC32();
    private final Inflater inflater = new Inflater(true);
    private final InflaterSource inflaterSource;
    private int section;
    private final BufferedSource source;
}
