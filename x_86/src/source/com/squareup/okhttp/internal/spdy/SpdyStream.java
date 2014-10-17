// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.squareup.okhttp.internal.spdy;

import com.squareup.okhttp.internal.okio.*;
import java.io.*;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.List;

// Referenced classes of package com.squareup.okhttp.internal.spdy:
//            SpdyConnection, Settings, ErrorCode, HeadersMode

public final class SpdyStream
{
    final class SpdyDataSink
        implements Sink
    {

        public void close()
            throws IOException
        {
label0:
            {
                if(!$assertionsDisabled && Thread.holdsLock(SpdyStream.this))
                    throw new AssertionError();
                synchronized(SpdyStream.this)
                {
                    if(!closed)
                        break label0;
                }
                return;
            }
            spdystream;
            JVM INSTR monitorexit ;
            if(!sink.finished)
                connection.writeData(id, true, null, 0L);
            synchronized(SpdyStream.this)
            {
                closed = true;
            }
            connection.flush();
            cancelStreamIfNecessary();
            return;
            exception;
            spdystream;
            JVM INSTR monitorexit ;
            throw exception;
            exception1;
            spdystream1;
            JVM INSTR monitorexit ;
            throw exception1;
        }

        public Sink deadline(Deadline deadline1)
        {
            return this;
        }

        public void flush()
            throws IOException
        {
            if(!$assertionsDisabled && Thread.holdsLock(SpdyStream.this))
                throw new AssertionError();
            synchronized(SpdyStream.this)
            {
                checkOutNotClosed();
            }
            connection.flush();
            return;
            exception;
            spdystream;
            JVM INSTR monitorexit ;
            throw exception;
        }

        public void write(OkBuffer okbuffer, long l)
            throws IOException
        {
            if(!$assertionsDisabled && Thread.holdsLock(SpdyStream.this))
                throw new AssertionError();
              goto _L1
_L3:
            long l1;
            checkOutNotClosed();
            l1 = Math.min(bytesLeftInWriteWindow, l);
            SpdyStream spdystream1 = SpdyStream.this;
            spdystream1.bytesLeftInWriteWindow = spdystream1.bytesLeftInWriteWindow - l1;
            SpdyStream spdystream;
            spdystream;
            JVM INSTR monitorexit ;
            l -= l1;
            connection.writeData(id, false, okbuffer, l1);
_L1:
            if(l <= 0L)
                break; /* Loop/switch isn't completed */
            spdystream = SpdyStream.this;
            spdystream;
            JVM INSTR monitorenter ;
            while(bytesLeftInWriteWindow <= 0L) 
                wait();
            if(true) goto _L3; else goto _L2
            InterruptedException interruptedexception;
            interruptedexception;
            throw new InterruptedIOException();
            Exception exception;
            exception;
            spdystream;
            JVM INSTR monitorexit ;
            throw exception;
_L2:
        }

        static final boolean $assertionsDisabled;
        private boolean closed;
        private boolean finished;
        final SpdyStream this$0;

        static 
        {
            boolean flag;
            if(!com/squareup/okhttp/internal/spdy/SpdyStream.desiredAssertionStatus())
                flag = true;
            else
                flag = false;
            $assertionsDisabled = flag;
        }



/*
        static boolean access$202(SpdyDataSink spdydatasink, boolean flag)
        {
            spdydatasink.finished = flag;
            return flag;
        }

*/


        SpdyDataSink()
        {
            this$0 = SpdyStream.this;
            super();
        }
    }

    private final class SpdyDataSource
        implements Source
    {

        private void checkNotClosed()
            throws IOException
        {
            if(closed)
                throw new IOException("stream closed");
            if(errorCode != null)
                throw new IOException((new StringBuilder()).append("stream was reset: ").append(errorCode).toString());
            else
                return;
        }

        private void waitUntilReadable()
            throws IOException
        {
            long l;
            long l1;
            l = 0L;
            l1 = 0L;
            if(readTimeoutMillis != 0L)
            {
                l = System.nanoTime() / 0xf4240L;
                l1 = readTimeoutMillis;
            }
            do
            {
                if(readBuffer.size() != 0L || finished || closed || errorCode != null)
                    break MISSING_BLOCK_LABEL_145;
                InterruptedException interruptedexception;
                if(readTimeoutMillis == 0L)
                {
                    wait();
                    continue;
                }
                if(l1 <= 0L)
                    break;
                try
                {
                    wait(l1);
                    l1 = (l + readTimeoutMillis) - System.nanoTime() / 0xf4240L;
                }
                // Misplaced declaration of an exception variable
                catch(InterruptedException interruptedexception)
                {
                    throw new InterruptedIOException();
                }
            } while(true);
            throw new SocketTimeoutException("Read timed out");
        }

        public void close()
            throws IOException
        {
            synchronized(SpdyStream.this)
            {
                closed = true;
                readBuffer.clear();
                notifyAll();
            }
            cancelStreamIfNecessary();
            return;
            exception;
            spdystream;
            JVM INSTR monitorexit ;
            throw exception;
        }

        public Source deadline(Deadline deadline1)
        {
            return this;
        }

        public long read(OkBuffer okbuffer, long l)
            throws IOException
        {
label0:
            {
                if(l < 0L)
                    throw new IllegalArgumentException((new StringBuilder()).append("byteCount < 0: ").append(l).toString());
                synchronized(SpdyStream.this)
                {
                    waitUntilReadable();
                    checkNotClosed();
                    if(readBuffer.size() != 0L)
                        break label0;
                }
                return -1L;
            }
            long l1;
            l1 = readBuffer.read(okbuffer, Math.min(l, readBuffer.size()));
            SpdyStream spdystream1 = SpdyStream.this;
            spdystream1.unacknowledgedBytesRead = l1 + spdystream1.unacknowledgedBytesRead;
            if(unacknowledgedBytesRead >= (long)(connection.peerSettings.getInitialWindowSize(0x10000) / 2))
            {
                connection.writeWindowUpdateLater(id, unacknowledgedBytesRead);
                unacknowledgedBytesRead = 0L;
            }
            spdystream;
            JVM INSTR monitorexit ;
            synchronized(connection)
            {
                SpdyConnection spdyconnection1 = connection;
                spdyconnection1.unacknowledgedBytesRead = l1 + spdyconnection1.unacknowledgedBytesRead;
                if(connection.unacknowledgedBytesRead >= (long)(connection.peerSettings.getInitialWindowSize(0x10000) / 2))
                {
                    connection.writeWindowUpdateLater(0, connection.unacknowledgedBytesRead);
                    connection.unacknowledgedBytesRead = 0L;
                }
            }
            return l1;
            exception1;
            spdyconnection;
            JVM INSTR monitorexit ;
            throw exception1;
            exception;
            spdystream;
            JVM INSTR monitorexit ;
            throw exception;
        }

        void receive(BufferedSource bufferedsource, long l)
            throws IOException
        {
            if(!$assertionsDisabled && Thread.holdsLock(SpdyStream.this))
                throw new AssertionError();
              goto _L1
_L3:
            long l1;
            l -= l1;
            SpdyStream spdystream1 = SpdyStream.this;
            spdystream1;
            JVM INSTR monitorenter ;
            SpdyStream spdystream;
            Exception exception;
            boolean flag;
            boolean flag1;
            boolean flag2;
            if(readBuffer.size() == 0L)
                flag2 = true;
            else
                flag2 = false;
            readBuffer.write(receiveBuffer, receiveBuffer.size());
            if(!flag2)
                break MISSING_BLOCK_LABEL_83;
            notifyAll();
            spdystream1;
            JVM INSTR monitorexit ;
_L1:
            if(l <= 0L)
                break MISSING_BLOCK_LABEL_152;
            spdystream = SpdyStream.this;
            spdystream;
            JVM INSTR monitorenter ;
            flag = finished;
            if(l + readBuffer.size() > maxByteCount)
                flag1 = true;
            else
                flag1 = false;
            spdystream;
            JVM INSTR monitorexit ;
            if(!flag1)
                break MISSING_BLOCK_LABEL_167;
            bufferedsource.skip(l);
            closeLater(ErrorCode.FLOW_CONTROL_ERROR);
            return;
            exception;
            spdystream;
            JVM INSTR monitorexit ;
            throw exception;
            if(flag)
            {
                bufferedsource.skip(l);
                return;
            }
            l1 = bufferedsource.read(receiveBuffer, l);
            if(l1 != -1L) goto _L3; else goto _L2
_L2:
            throw new EOFException();
            Exception exception1;
            exception1;
            spdystream1;
            JVM INSTR monitorexit ;
            throw exception1;
        }

        static final boolean $assertionsDisabled;
        private boolean closed;
        private boolean finished;
        private final long maxByteCount;
        private final OkBuffer readBuffer;
        private final OkBuffer receiveBuffer;
        final SpdyStream this$0;

        static 
        {
            boolean flag;
            if(!com/squareup/okhttp/internal/spdy/SpdyStream.desiredAssertionStatus())
                flag = true;
            else
                flag = false;
            $assertionsDisabled = flag;
        }



/*
        static boolean access$102(SpdyDataSource spdydatasource, boolean flag)
        {
            spdydatasource.finished = flag;
            return flag;
        }

*/


        private SpdyDataSource(long l)
        {
            this$0 = SpdyStream.this;
            super();
            receiveBuffer = new OkBuffer();
            readBuffer = new OkBuffer();
            maxByteCount = l;
        }

        SpdyDataSource(long l, _cls1 _pcls1)
        {
            this(l);
        }
    }


    SpdyStream(int i, SpdyConnection spdyconnection, boolean flag, boolean flag1, int j, List list)
    {
        unacknowledgedBytesRead = 0L;
        readTimeoutMillis = 0L;
        errorCode = null;
        if(spdyconnection == null)
            throw new NullPointerException("connection == null");
        if(list == null)
        {
            throw new NullPointerException("requestHeaders == null");
        } else
        {
            id = i;
            connection = spdyconnection;
            bytesLeftInWriteWindow = spdyconnection.peerSettings.getInitialWindowSize(0x10000);
            source = new SpdyDataSource(spdyconnection.okHttpSettings.getInitialWindowSize(0x10000));
            sink = new SpdyDataSink();
            source.finished = flag1;
            sink.finished = flag;
            priority = j;
            requestHeaders = list;
            return;
        }
    }

    private void cancelStreamIfNecessary()
        throws IOException
    {
        if(!$assertionsDisabled && Thread.holdsLock(this))
            throw new AssertionError();
        this;
        JVM INSTR monitorenter ;
        boolean flag;
        boolean flag1;
        if(source.finished || !source.closed || !sink.finished && !sink.closed)
            flag = false;
        else
            flag = true;
        flag1 = isOpen();
        this;
        JVM INSTR monitorexit ;
        Exception exception;
        if(flag)
            close(ErrorCode.CANCEL);
        else
        if(!flag1)
        {
            connection.removeStream(id);
            return;
        }
        return;
        exception;
        this;
        JVM INSTR monitorexit ;
        throw exception;
    }

    private void checkOutNotClosed()
        throws IOException
    {
        if(sink.closed)
            throw new IOException("stream closed");
        if(sink.finished)
            throw new IOException("stream finished");
        if(errorCode != null)
            throw new IOException((new StringBuilder()).append("stream was reset: ").append(errorCode).toString());
        else
            return;
    }

    private boolean closeInternal(ErrorCode errorcode)
    {
        if(!$assertionsDisabled && Thread.holdsLock(this))
            throw new AssertionError();
        this;
        JVM INSTR monitorenter ;
        if(errorCode == null)
            break MISSING_BLOCK_LABEL_34;
        this;
        JVM INSTR monitorexit ;
        return false;
        if(!source.finished || !sink.finished)
            break MISSING_BLOCK_LABEL_63;
        this;
        JVM INSTR monitorexit ;
        return false;
        Exception exception;
        exception;
        this;
        JVM INSTR monitorexit ;
        throw exception;
        errorCode = errorcode;
        notifyAll();
        this;
        JVM INSTR monitorexit ;
        connection.removeStream(id);
        return true;
    }

    void addBytesToWriteWindow(long l)
    {
        bytesLeftInWriteWindow = l + bytesLeftInWriteWindow;
        if(l > 0L)
            notifyAll();
    }

    public void close(ErrorCode errorcode)
        throws IOException
    {
        if(!closeInternal(errorcode))
        {
            return;
        } else
        {
            connection.writeSynReset(id, errorcode);
            return;
        }
    }

    public void closeLater(ErrorCode errorcode)
    {
        if(!closeInternal(errorcode))
        {
            return;
        } else
        {
            connection.writeSynResetLater(id, errorcode);
            return;
        }
    }

    public SpdyConnection getConnection()
    {
        return connection;
    }

    public ErrorCode getErrorCode()
    {
        this;
        JVM INSTR monitorenter ;
        ErrorCode errorcode = errorCode;
        this;
        JVM INSTR monitorexit ;
        return errorcode;
        Exception exception;
        exception;
        throw exception;
    }

    public int getId()
    {
        return id;
    }

    int getPriority()
    {
        return priority;
    }

    public long getReadTimeoutMillis()
    {
        return readTimeoutMillis;
    }

    public List getRequestHeaders()
    {
        return requestHeaders;
    }

    public List getResponseHeaders()
        throws IOException
    {
        this;
        JVM INSTR monitorenter ;
        long l;
        long l1;
        l = 0L;
        l1 = 0L;
        if(readTimeoutMillis != 0L)
        {
            l1 = System.nanoTime() / 0xf4240L;
            l = readTimeoutMillis;
        }
_L3:
        if(responseHeaders != null || errorCode != null)
            break MISSING_BLOCK_LABEL_146;
        if(readTimeoutMillis != 0L) goto _L2; else goto _L1
_L1:
        wait();
          goto _L3
        InterruptedException interruptedexception;
        interruptedexception;
        InterruptedIOException interruptedioexception = new InterruptedIOException();
        interruptedioexception.initCause(interruptedexception);
        throw interruptedioexception;
        Exception exception;
        exception;
        this;
        JVM INSTR monitorexit ;
        throw exception;
_L2:
        if(l <= 0L)
            break MISSING_BLOCK_LABEL_116;
        wait(l);
        l = (l1 + readTimeoutMillis) - System.nanoTime() / 0xf4240L;
          goto _L3
        throw new SocketTimeoutException((new StringBuilder()).append("Read response header timeout. readTimeoutMillis: ").append(readTimeoutMillis).toString());
        List list;
        if(responseHeaders == null)
            break MISSING_BLOCK_LABEL_164;
        list = responseHeaders;
        this;
        JVM INSTR monitorexit ;
        return list;
        throw new IOException((new StringBuilder()).append("stream was reset: ").append(errorCode).toString());
    }

    public Sink getSink()
    {
        this;
        JVM INSTR monitorenter ;
        if(responseHeaders == null && !isLocallyInitiated())
            throw new IllegalStateException("reply before requesting the sink");
        break MISSING_BLOCK_LABEL_31;
        Exception exception;
        exception;
        this;
        JVM INSTR monitorexit ;
        throw exception;
        this;
        JVM INSTR monitorexit ;
        return sink;
    }

    public Source getSource()
    {
        return source;
    }

    public boolean isLocallyInitiated()
    {
        boolean flag;
        if((1 & id) == 1)
            flag = true;
        else
            flag = false;
        return connection.client == flag;
    }

    public boolean isOpen()
    {
        this;
        JVM INSTR monitorenter ;
        ErrorCode errorcode = errorCode;
        boolean flag = false;
        if(errorcode == null) goto _L2; else goto _L1
_L1:
        this;
        JVM INSTR monitorexit ;
        return flag;
_L2:
        List list;
        if(!source.finished && !source.closed || !sink.finished && !sink.closed)
            break MISSING_BLOCK_LABEL_70;
        list = responseHeaders;
        flag = false;
        if(list != null)
            continue; /* Loop/switch isn't completed */
        flag = true;
        if(true) goto _L1; else goto _L3
_L3:
        Exception exception;
        exception;
        throw exception;
    }

    void receiveData(BufferedSource bufferedsource, int i)
        throws IOException
    {
        if(!$assertionsDisabled && Thread.holdsLock(this))
        {
            throw new AssertionError();
        } else
        {
            source.receive(bufferedsource, i);
            return;
        }
    }

    void receiveFin()
    {
        if(!$assertionsDisabled && Thread.holdsLock(this))
            throw new AssertionError();
        this;
        JVM INSTR monitorenter ;
        boolean flag;
        source.finished = true;
        flag = isOpen();
        notifyAll();
        this;
        JVM INSTR monitorexit ;
        if(!flag)
            connection.removeStream(id);
        return;
        Exception exception;
        exception;
        this;
        JVM INSTR monitorexit ;
        throw exception;
    }

    void receiveHeaders(List list, HeadersMode headersmode)
    {
        if(!$assertionsDisabled && Thread.holdsLock(this))
            throw new AssertionError();
        boolean flag = true;
        this;
        JVM INSTR monitorenter ;
        if(responseHeaders != null) goto _L2; else goto _L1
_L1:
        if(!headersmode.failIfHeadersAbsent()) goto _L4; else goto _L3
_L3:
        ErrorCode errorcode = ErrorCode.PROTOCOL_ERROR;
_L7:
        this;
        JVM INSTR monitorexit ;
        if(errorcode == null) goto _L6; else goto _L5
_L5:
        closeLater(errorcode);
_L9:
        return;
_L4:
        responseHeaders = list;
        flag = isOpen();
        notifyAll();
        errorcode = null;
          goto _L7
        Exception exception;
        exception;
        this;
        JVM INSTR monitorexit ;
        throw exception;
_L2:
label0:
        {
            if(!headersmode.failIfHeadersPresent())
                break label0;
            errorcode = ErrorCode.STREAM_IN_USE;
        }
          goto _L7
        ArrayList arraylist = new ArrayList();
        arraylist.addAll(responseHeaders);
        arraylist.addAll(list);
        responseHeaders = arraylist;
        errorcode = null;
          goto _L7
_L6:
        if(flag) goto _L9; else goto _L8
_L8:
        connection.removeStream(id);
        return;
          goto _L7
    }

    void receiveRstStream(ErrorCode errorcode)
    {
        this;
        JVM INSTR monitorenter ;
        if(errorCode == null)
        {
            errorCode = errorcode;
            notifyAll();
        }
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public void reply(List list, boolean flag)
        throws IOException
    {
        if(!$assertionsDisabled && Thread.holdsLock(this))
            throw new AssertionError();
        this;
        JVM INSTR monitorenter ;
        if(list != null)
            break MISSING_BLOCK_LABEL_45;
        throw new NullPointerException("responseHeaders == null");
        Exception exception;
        exception;
        this;
        JVM INSTR monitorexit ;
        throw exception;
        if(responseHeaders != null)
            throw new IllegalStateException("reply already sent");
        responseHeaders = list;
        boolean flag1;
        flag1 = false;
        if(flag)
            break MISSING_BLOCK_LABEL_85;
        sink.finished = true;
        flag1 = true;
        this;
        JVM INSTR monitorexit ;
        connection.writeSynReply(id, flag1, list);
        if(flag1)
            connection.flush();
        return;
    }

    public void setReadTimeout(long l)
    {
        readTimeoutMillis = l;
    }

    static final boolean $assertionsDisabled;
    long bytesLeftInWriteWindow;
    private final SpdyConnection connection;
    private ErrorCode errorCode;
    private final int id;
    private final int priority;
    private long readTimeoutMillis;
    private final List requestHeaders;
    private List responseHeaders;
    final SpdyDataSink sink;
    private final SpdyDataSource source;
    long unacknowledgedBytesRead;

    static 
    {
        boolean flag;
        if(!com/squareup/okhttp/internal/spdy/SpdyStream.desiredAssertionStatus())
            flag = true;
        else
            flag = false;
        $assertionsDisabled = flag;
    }






}
