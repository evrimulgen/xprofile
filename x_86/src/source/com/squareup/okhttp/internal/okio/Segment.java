// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.squareup.okhttp.internal.okio;


// Referenced classes of package com.squareup.okhttp.internal.okio:
//            SegmentPool

final class Segment
{

    Segment()
    {
    }

    public void compact()
    {
        if(prev == this)
            throw new IllegalStateException();
        if((prev.limit - prev.pos) + (limit - pos) > 2048)
        {
            return;
        } else
        {
            writeTo(prev, limit - pos);
            pop();
            SegmentPool.INSTANCE.recycle(this);
            return;
        }
    }

    public Segment pop()
    {
        Segment segment;
        if(next != this)
            segment = next;
        else
            segment = null;
        prev.next = next;
        next.prev = prev;
        next = null;
        prev = null;
        return segment;
    }

    public Segment push(Segment segment)
    {
        segment.prev = this;
        segment.next = next;
        next.prev = segment;
        next = segment;
        return segment;
    }

    public Segment split(int i)
    {
        int j = limit - pos - i;
        if(i <= 0 || j <= 0)
            throw new IllegalArgumentException();
        if(i < j)
        {
            Segment segment1 = SegmentPool.INSTANCE.take();
            System.arraycopy(data, pos, segment1.data, segment1.pos, i);
            pos = i + pos;
            segment1.limit = i + segment1.limit;
            prev.push(segment1);
            return segment1;
        } else
        {
            Segment segment = SegmentPool.INSTANCE.take();
            System.arraycopy(data, i + pos, segment.data, segment.pos, j);
            limit = limit - j;
            segment.limit = j + segment.limit;
            push(segment);
            return this;
        }
    }

    public void writeTo(Segment segment, int i)
    {
        if(i + (segment.limit - segment.pos) > 2048)
            throw new IllegalArgumentException();
        if(i + segment.limit > 2048)
        {
            System.arraycopy(segment.data, segment.pos, segment.data, 0, segment.limit - segment.pos);
            segment.limit = segment.limit - segment.pos;
            segment.pos = 0;
        }
        System.arraycopy(data, pos, segment.data, segment.limit, i);
        segment.limit = i + segment.limit;
        pos = i + pos;
    }

    static final int SIZE = 2048;
    final byte data[] = new byte[2048];
    int limit;
    Segment next;
    int pos;
    Segment prev;
}
