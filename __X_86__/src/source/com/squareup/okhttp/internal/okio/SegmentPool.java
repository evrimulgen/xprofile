// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.squareup.okhttp.internal.okio;


// Referenced classes of package com.squareup.okhttp.internal.okio:
//            Segment

final class SegmentPool
{

    private SegmentPool()
    {
    }

    void recycle(Segment segment)
    {
        if(segment.next != null || segment.prev != null)
            throw new IllegalArgumentException();
        this;
        JVM INSTR monitorenter ;
        if(2048L + byteCount <= 0x10000L)
            break MISSING_BLOCK_LABEL_42;
        this;
        JVM INSTR monitorexit ;
        return;
        byteCount = 2048L + byteCount;
        segment.next = next;
        segment.limit = 0;
        segment.pos = 0;
        next = segment;
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        this;
        JVM INSTR monitorexit ;
        throw exception;
    }

    Segment take()
    {
        this;
        JVM INSTR monitorenter ;
        Segment segment;
        if(next == null)
            break MISSING_BLOCK_LABEL_43;
        segment = next;
        next = segment.next;
        segment.next = null;
        byteCount = byteCount - 2048L;
        return segment;
        this;
        JVM INSTR monitorexit ;
        return new Segment();
        Exception exception;
        exception;
        this;
        JVM INSTR monitorexit ;
        throw exception;
    }

    static final SegmentPool INSTANCE = new SegmentPool();
    static final long MAX_SIZE = 0x10000L;
    long byteCount;
    private Segment next;

}
