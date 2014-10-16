// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.squareup.okhttp.internal.okio;

import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

// Referenced classes of package com.squareup.okhttp.internal.okio:
//            BufferedSource, BufferedSink, Util, Segment, 
//            SegmentPool, ByteString, Deadline, Sink, 
//            Source

public final class OkBuffer
    implements BufferedSource, BufferedSink, Cloneable
{

    public OkBuffer()
    {
    }

    private byte[] readBytes(long l)
    {
        Util.checkOffsetAndCount(size, 0L, l);
        if(l > 0x7fffffffL)
            throw new IllegalArgumentException((new StringBuilder()).append("byteCount > Integer.MAX_VALUE: ").append(l).toString());
        int i = 0;
        byte abyte0[] = new byte[(int)l];
        do
        {
            if((long)i >= l)
                break;
            int j = (int)Math.min(l - (long)i, head.limit - head.pos);
            System.arraycopy(head.data, head.pos, abyte0, i, j);
            i += j;
            Segment segment = head;
            segment.pos = j + segment.pos;
            if(head.pos == head.limit)
            {
                Segment segment1 = head;
                head = segment1.pop();
                SegmentPool.INSTANCE.recycle(segment1);
            }
        } while(true);
        size = size - l;
        return abyte0;
    }

    public OkBuffer buffer()
    {
        return this;
    }

    public void clear()
    {
        skip(size);
    }

    public OkBuffer clone()
    {
        OkBuffer okbuffer = new OkBuffer();
        if(size() != 0L)
        {
            okbuffer.write(head.data, head.pos, head.limit - head.pos);
            Segment segment = head.next;
            while(segment != head) 
            {
                okbuffer.write(segment.data, segment.pos, segment.limit - segment.pos);
                segment = segment.next;
            }
        }
        return okbuffer;
    }

    public volatile Object clone()
        throws CloneNotSupportedException
    {
        return clone();
    }

    public void close()
    {
    }

    public long completeSegmentByteCount()
    {
        long l = size;
        if(l == 0L)
            return 0L;
        Segment segment = head.prev;
        if(segment.limit < 2048)
            l -= segment.limit - segment.pos;
        return l;
    }

    public OkBuffer deadline(Deadline deadline1)
    {
        return this;
    }

    public volatile Sink deadline(Deadline deadline1)
    {
        return deadline(deadline1);
    }

    public volatile Source deadline(Deadline deadline1)
    {
        return deadline(deadline1);
    }

    public volatile BufferedSink emitCompleteSegments()
        throws IOException
    {
        return emitCompleteSegments();
    }

    public OkBuffer emitCompleteSegments()
    {
        return this;
    }

    public boolean equals(Object obj)
    {
        if(!(obj instanceof OkBuffer))
            return false;
        OkBuffer okbuffer = (OkBuffer)obj;
        if(size != okbuffer.size)
            return false;
        if(size == 0L)
            return true;
        Segment segment = head;
        Segment segment1 = okbuffer.head;
        int i = segment.pos;
        int j = segment1.pos;
        long l = 0L;
        while(l < size) 
        {
            long l1 = Math.min(segment.limit - i, segment1.limit - j);
            int k = 0;
            int i1 = j;
            int j1;
            int k1;
            for(j1 = i; (long)k < l1; j1 = k1)
            {
                byte abyte0[] = segment.data;
                k1 = j1 + 1;
                byte byte0 = abyte0[j1];
                byte abyte1[] = segment1.data;
                int i2 = i1 + 1;
                if(byte0 != abyte1[i1])
                    return false;
                k++;
                i1 = i2;
            }

            if(j1 == segment.limit)
            {
                segment = segment.next;
                i = segment.pos;
            } else
            {
                i = j1;
            }
            if(i1 == segment1.limit)
            {
                segment1 = segment1.next;
                j = segment1.pos;
            } else
            {
                j = i1;
            }
            l += l1;
        }
        return true;
    }

    public boolean exhausted()
    {
        return size == 0L;
    }

    public void flush()
    {
    }

    public byte getByte(long l)
    {
        Util.checkOffsetAndCount(size, l, 1L);
        Segment segment = head;
        do
        {
            int i = segment.limit - segment.pos;
            if(l < (long)i)
                return segment.data[segment.pos + (int)l];
            l -= i;
            segment = segment.next;
        } while(true);
    }

    public int hashCode()
    {
        Segment segment = head;
        if(segment == null)
            return 0;
        int i = 1;
        do
        {
            int j = segment.pos;
            for(int k = segment.limit; j < k; j++)
                i = i * 31 + segment.data[j];

            segment = segment.next;
        } while(segment != head);
        return i;
    }

    public long indexOf(byte byte0)
    {
        return indexOf(byte0, 0L);
    }

    public long indexOf(byte byte0, long l)
    {
        Segment segment = head;
        if(segment == null)
            return -1L;
        long l1 = 0L;
        do
        {
            int i = segment.limit - segment.pos;
            if(l > (long)i)
            {
                l -= i;
            } else
            {
                byte abyte0[] = segment.data;
                long l2 = l + (long)segment.pos;
                for(long l3 = segment.limit; l2 < l3; l2++)
                    if(abyte0[(int)l2] == byte0)
                        return (l1 + l2) - (long)segment.pos;

                l = 0L;
            }
            l1 += i;
            segment = segment.next;
        } while(segment != head);
        return -1L;
    }

    public InputStream inputStream()
    {
        return new InputStream() {

            public int available()
            {
                return (int)Math.min(size, 0x7fffffffL);
            }

            public void close()
            {
            }

            public int read()
            {
                return 0xff & readByte();
            }

            public int read(byte abyte0[], int i, int j)
            {
                return OkBuffer.this.read(abyte0, i, j);
            }

            public String toString()
            {
                return (new StringBuilder()).append(OkBuffer.this).append(".inputStream()").toString();
            }

            final OkBuffer this$0;

            
            {
                this$0 = OkBuffer.this;
                super();
            }
        }
;
    }

    public OutputStream outputStream()
    {
        return new OutputStream() {

            public void close()
            {
            }

            public void flush()
            {
            }

            public String toString()
            {
                return (new StringBuilder()).append(this).append(".outputStream()").toString();
            }

            public void write(int i)
            {
                writeByte((byte)i);
            }

            public void write(byte abyte0[], int i, int j)
            {
                OkBuffer.this.write(abyte0, i, j);
            }

            final OkBuffer this$0;

            
            {
                this$0 = OkBuffer.this;
                super();
            }
        }
;
    }

    int read(byte abyte0[], int i, int j)
    {
        Segment segment = head;
        int k;
        if(segment == null)
        {
            k = -1;
        } else
        {
            k = Math.min(j, segment.limit - segment.pos);
            System.arraycopy(segment.data, segment.pos, abyte0, i, k);
            segment.pos = k + segment.pos;
            size = size - (long)k;
            if(segment.pos == segment.limit)
            {
                head = segment.pop();
                SegmentPool.INSTANCE.recycle(segment);
                return k;
            }
        }
        return k;
    }

    public long read(OkBuffer okbuffer, long l)
    {
        if(size == 0L)
            return -1L;
        if(l > size)
            l = size;
        okbuffer.write(this, l);
        return l;
    }

    public byte readByte()
    {
        if(size == 0L)
            throw new IllegalStateException("size == 0");
        Segment segment = head;
        int i = segment.pos;
        int j = segment.limit;
        byte abyte0[] = segment.data;
        int k = i + 1;
        byte byte0 = abyte0[i];
        size = size - 1L;
        if(k == j)
        {
            head = segment.pop();
            SegmentPool.INSTANCE.recycle(segment);
            return byte0;
        } else
        {
            segment.pos = k;
            return byte0;
        }
    }

    public ByteString readByteString(long l)
    {
        return new ByteString(readBytes(l));
    }

    public int readInt()
    {
        if(size < 4L)
            throw new IllegalArgumentException((new StringBuilder()).append("size < 4: ").append(size).toString());
        Segment segment = head;
        int i = segment.pos;
        int j = segment.limit;
        if(j - i < 4)
            return (0xff & readByte()) << 24 | (0xff & readByte()) << 16 | (0xff & readByte()) << 8 | 0xff & readByte();
        byte abyte0[] = segment.data;
        int k = i + 1;
        int l = (0xff & abyte0[i]) << 24;
        int i1 = k + 1;
        int j1 = l | (0xff & abyte0[k]) << 16;
        int k1 = i1 + 1;
        int l1 = j1 | (0xff & abyte0[i1]) << 8;
        int i2 = k1 + 1;
        int j2 = l1 | 0xff & abyte0[k1];
        size = size - 4L;
        if(i2 == j)
        {
            head = segment.pop();
            SegmentPool.INSTANCE.recycle(segment);
            return j2;
        } else
        {
            segment.pos = i2;
            return j2;
        }
    }

    public int readIntLe()
    {
        return Util.reverseBytesInt(readInt());
    }

    public short readShort()
    {
        if(size < 2L)
            throw new IllegalArgumentException((new StringBuilder()).append("size < 2: ").append(size).toString());
        Segment segment = head;
        int i = segment.pos;
        int j = segment.limit;
        if(j - i < 2)
            return (short)((0xff & readByte()) << 8 | 0xff & readByte());
        byte abyte0[] = segment.data;
        int k = i + 1;
        int l = (0xff & abyte0[i]) << 8;
        int i1 = k + 1;
        int j1 = l | 0xff & abyte0[k];
        size = size - 2L;
        if(i1 == j)
        {
            head = segment.pop();
            SegmentPool.INSTANCE.recycle(segment);
        } else
        {
            segment.pos = i1;
        }
        return (short)j1;
    }

    public int readShortLe()
    {
        return Util.reverseBytesShort(readShort());
    }

    public String readUtf8(long l)
    {
        Util.checkOffsetAndCount(size, 0L, l);
        if(l > 0x7fffffffL)
            throw new IllegalArgumentException((new StringBuilder()).append("byteCount > Integer.MAX_VALUE: ").append(l).toString());
        if(l != 0L) goto _L2; else goto _L1
_L1:
        String s = "";
_L4:
        return s;
_L2:
        Segment segment;
        segment = head;
        if(l + (long)segment.pos > (long)segment.limit)
        {
            String s1;
            try
            {
                s1 = new String(readBytes(l), "UTF-8");
            }
            catch(UnsupportedEncodingException unsupportedencodingexception1)
            {
                throw new AssertionError(unsupportedencodingexception1);
            }
            return s1;
        }
        s = new String(segment.data, segment.pos, (int)l, "UTF-8");
        segment.pos = (int)(l + (long)segment.pos);
        size = size - l;
        if(segment.pos != segment.limit) goto _L4; else goto _L3
_L3:
        head = segment.pop();
        SegmentPool.INSTANCE.recycle(segment);
        return s;
        UnsupportedEncodingException unsupportedencodingexception;
        unsupportedencodingexception;
        throw new AssertionError(unsupportedencodingexception);
    }

    public String readUtf8Line(boolean flag)
        throws EOFException
    {
        long l = indexOf((byte)10);
        if(l == -1L)
        {
            if(flag)
                throw new EOFException();
            String s2;
            if(size != 0L)
                s2 = readUtf8(size);
            else
                s2 = null;
            return s2;
        }
        if(l > 0L && getByte(l - 1L) == 13)
        {
            String s1 = readUtf8(l - 1L);
            skip(2L);
            return s1;
        } else
        {
            String s = readUtf8(l);
            skip(1L);
            return s;
        }
    }

    public void require(long l)
        throws EOFException
    {
        if(size < l)
            throw new EOFException();
        else
            return;
    }

    public long seek(byte byte0)
        throws EOFException
    {
        long l = indexOf(byte0);
        if(l == -1L)
            throw new EOFException();
        else
            return l;
    }

    List segmentSizes()
    {
        Object obj;
        if(head == null)
        {
            obj = Collections.emptyList();
        } else
        {
            obj = new ArrayList();
            ((List) (obj)).add(Integer.valueOf(head.limit - head.pos));
            Segment segment = head.next;
            while(segment != head) 
            {
                ((List) (obj)).add(Integer.valueOf(segment.limit - segment.pos));
                segment = segment.next;
            }
        }
        return ((List) (obj));
    }

    public long size()
    {
        return size;
    }

    public void skip(long l)
    {
        Util.checkOffsetAndCount(size, 0L, l);
        size = size - l;
        do
        {
            if(l <= 0L)
                break;
            int i = (int)Math.min(l, head.limit - head.pos);
            l -= i;
            Segment segment = head;
            segment.pos = i + segment.pos;
            if(head.pos == head.limit)
            {
                Segment segment1 = head;
                head = segment1.pop();
                SegmentPool.INSTANCE.recycle(segment1);
            }
        } while(true);
    }

    public String toString()
    {
        if(size == 0L)
            return "OkBuffer[size=0]";
        if(size <= 16L)
        {
            ByteString bytestring = clone().readByteString(size);
            Object aobj1[] = new Object[2];
            aobj1[0] = Long.valueOf(size);
            aobj1[1] = bytestring.hex();
            return String.format("OkBuffer[size=%s data=%s]", aobj1);
        }
        MessageDigest messagedigest;
        Object aobj[];
        String s;
        try
        {
            messagedigest = MessageDigest.getInstance("MD5");
            messagedigest.update(head.data, head.pos, head.limit - head.pos);
            for(Segment segment = head.next; segment != head; segment = segment.next)
                messagedigest.update(segment.data, segment.pos, segment.limit - segment.pos);

        }
        catch(NoSuchAlgorithmException nosuchalgorithmexception)
        {
            throw new AssertionError();
        }
        aobj = new Object[2];
        aobj[0] = Long.valueOf(size);
        aobj[1] = ByteString.of(messagedigest.digest()).hex();
        s = String.format("OkBuffer[size=%s md5=%s]", aobj);
        return s;
    }

    Segment writableSegment(int i)
    {
        if(i < 1 || i > 2048)
            throw new IllegalArgumentException();
        Segment segment;
        if(head == null)
        {
            head = SegmentPool.INSTANCE.take();
            Segment segment1 = head;
            Segment segment2 = head;
            segment = head;
            segment2.prev = segment;
            segment1.next = segment;
        } else
        {
            segment = head.prev;
            if(i + segment.limit > 2048)
                return segment.push(SegmentPool.INSTANCE.take());
        }
        return segment;
    }

    public volatile BufferedSink write(ByteString bytestring)
        throws IOException
    {
        return write(bytestring);
    }

    public volatile BufferedSink write(byte abyte0[])
        throws IOException
    {
        return write(abyte0);
    }

    public volatile BufferedSink write(byte abyte0[], int i, int j)
        throws IOException
    {
        return write(abyte0, i, j);
    }

    public OkBuffer write(ByteString bytestring)
    {
        return write(bytestring.data, 0, bytestring.data.length);
    }

    public OkBuffer write(byte abyte0[])
    {
        return write(abyte0, 0, abyte0.length);
    }

    public OkBuffer write(byte abyte0[], int i, int j)
    {
        for(int k = i + j; i < k;)
        {
            Segment segment = writableSegment(1);
            int l = Math.min(k - i, 2048 - segment.limit);
            System.arraycopy(abyte0, i, segment.data, segment.limit, l);
            i += l;
            segment.limit = l + segment.limit;
        }

        size = size + (long)j;
        return this;
    }

    public void write(OkBuffer okbuffer, long l)
    {
        if(okbuffer == this)
            throw new IllegalArgumentException("source == this");
        Util.checkOffsetAndCount(okbuffer.size, 0L, l);
_L5:
        if(l <= 0L)
            break MISSING_BLOCK_LABEL_248;
        if(l >= (long)(okbuffer.head.limit - okbuffer.head.pos)) goto _L2; else goto _L1
_L1:
        Segment segment4;
        if(head != null)
            segment4 = head.prev;
        else
            segment4 = null;
        if(segment4 != null && l + (long)(segment4.limit - segment4.pos) <= 2048L) goto _L4; else goto _L3
_L3:
        okbuffer.head = okbuffer.head.split((int)l);
_L2:
        Segment segment = okbuffer.head;
        long l1 = segment.limit - segment.pos;
        okbuffer.head = segment.pop();
        if(head == null)
        {
            head = segment;
            Segment segment1 = head;
            Segment segment2 = head;
            Segment segment3 = head;
            segment2.prev = segment3;
            segment1.next = segment3;
        } else
        {
            head.prev.push(segment).compact();
        }
        okbuffer.size = okbuffer.size - l1;
        size = l1 + size;
        l -= l1;
        if(true) goto _L5; else goto _L4
_L4:
        okbuffer.head.writeTo(segment4, (int)l);
        okbuffer.size = okbuffer.size - l;
        size = l + size;
    }

    public volatile BufferedSink writeByte(int i)
        throws IOException
    {
        return writeByte(i);
    }

    public OkBuffer writeByte(int i)
    {
        Segment segment = writableSegment(1);
        byte abyte0[] = segment.data;
        int j = segment.limit;
        segment.limit = j + 1;
        abyte0[j] = (byte)i;
        size = 1L + size;
        return this;
    }

    public volatile BufferedSink writeInt(int i)
        throws IOException
    {
        return writeInt(i);
    }

    public OkBuffer writeInt(int i)
    {
        Segment segment = writableSegment(4);
        byte abyte0[] = segment.data;
        int j = segment.limit;
        int k = j + 1;
        abyte0[j] = (byte)(0xff & i >> 24);
        int l = k + 1;
        abyte0[k] = (byte)(0xff & i >> 16);
        int i1 = l + 1;
        abyte0[l] = (byte)(0xff & i >> 8);
        int j1 = i1 + 1;
        abyte0[i1] = (byte)(i & 0xff);
        segment.limit = j1;
        size = 4L + size;
        return this;
    }

    public volatile BufferedSink writeShort(int i)
        throws IOException
    {
        return writeShort(i);
    }

    public OkBuffer writeShort(int i)
    {
        Segment segment = writableSegment(2);
        byte abyte0[] = segment.data;
        int j = segment.limit;
        int k = j + 1;
        abyte0[j] = (byte)(0xff & i >> 8);
        int l = k + 1;
        abyte0[k] = (byte)(i & 0xff);
        segment.limit = l;
        size = 2L + size;
        return this;
    }

    public volatile BufferedSink writeUtf8(String s)
        throws IOException
    {
        return writeUtf8(s);
    }

    public OkBuffer writeUtf8(String s)
    {
        OkBuffer okbuffer;
        try
        {
            byte abyte0[] = s.getBytes("UTF-8");
            okbuffer = write(abyte0, 0, abyte0.length);
        }
        catch(UnsupportedEncodingException unsupportedencodingexception)
        {
            throw new AssertionError(unsupportedencodingexception);
        }
        return okbuffer;
    }

    Segment head;
    long size;
}
