// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.squareup.okhttp.internal.okio;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.util.concurrent.TimeUnit;

public class Deadline
{

    public Deadline()
    {
    }

    public boolean reached()
    {
        return System.nanoTime() - deadlineNanos >= 0L;
    }

    public Deadline start(long l, TimeUnit timeunit)
    {
        deadlineNanos = System.nanoTime() + timeunit.toNanos(l);
        return this;
    }

    public final void throwIfReached()
        throws IOException
    {
        if(reached())
            throw new IOException("Deadline reached");
        if(Thread.interrupted())
            throw new InterruptedIOException();
        else
            return;
    }

    public static final Deadline NONE = new Deadline() {

        public boolean reached()
        {
            return false;
        }

        public Deadline start(long l, TimeUnit timeunit)
        {
            throw new UnsupportedOperationException();
        }

    }
;
    private long deadlineNanos;

}
