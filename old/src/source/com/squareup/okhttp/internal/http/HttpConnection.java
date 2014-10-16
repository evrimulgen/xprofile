// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.squareup.okhttp.internal.http;

import com.squareup.okhttp.*;
import com.squareup.okhttp.internal.Util;
import com.squareup.okhttp.internal.okio.*;
import java.io.IOException;
import java.io.OutputStream;
import java.net.*;

// Referenced classes of package com.squareup.okhttp.internal.http:
//            StatusLine, OkHeaders, Headers, RetryableSink, 
//            HttpEngine

public final class HttpConnection
{
    private class AbstractSource
    {

        protected final void cacheWrite(OkBuffer okbuffer, long l)
            throws IOException
        {
            if(cacheBody != null)
                Okio.copy(okbuffer, okbuffer.size() - l, l, cacheBody);
        }

        protected final void endOfInput(boolean flag)
            throws IOException
        {
            if(state != 5)
                throw new IllegalStateException((new StringBuilder()).append("state: ").append(state).toString());
            if(cacheRequest != null)
                cacheBody.close();
            state = 0;
            if(flag && onIdle == 1)
            {
                onIdle = 0;
                pool.recycle(connection);
            } else
            if(onIdle == 2)
            {
                state = 6;
                connection.close();
                return;
            }
        }

        protected final void unexpectedEndOfInput()
        {
            if(cacheRequest != null)
                cacheRequest.abort();
            Util.closeQuietly(connection);
            state = 6;
        }

        protected final OutputStream cacheBody;
        private final CacheRequest cacheRequest;
        protected boolean closed;
        final HttpConnection this$0;

        AbstractSource(CacheRequest cacherequest)
            throws IOException
        {
            this$0 = HttpConnection.this;
            super();
            OutputStream outputstream;
            if(cacherequest != null)
                outputstream = cacherequest.getBody();
            else
                outputstream = null;
            if(outputstream == null)
                cacherequest = null;
            cacheBody = outputstream;
            cacheRequest = cacherequest;
        }
    }

    private final class ChunkedSink
        implements Sink
    {

        private void writeHex(long l)
            throws IOException
        {
            int i = 16;
            do
            {
                byte abyte0[] = hex;
                i--;
                abyte0[i] = HttpConnection.HEX_DIGITS[(int)(15L & l)];
                l >>>= 4;
            } while(l != 0L);
            sink.write(hex, i, hex.length - i);
        }

        public void close()
            throws IOException
        {
            this;
            JVM INSTR monitorenter ;
            boolean flag = closed;
            if(!flag) goto _L2; else goto _L1
_L1:
            this;
            JVM INSTR monitorexit ;
            return;
_L2:
            closed = true;
            sink.write(HttpConnection.FINAL_CHUNK);
            state = 3;
            if(true) goto _L1; else goto _L3
_L3:
            Exception exception;
            exception;
            throw exception;
        }

        public Sink deadline(Deadline deadline1)
        {
            return this;
        }

        public void flush()
            throws IOException
        {
            this;
            JVM INSTR monitorenter ;
            boolean flag = closed;
            if(!flag) goto _L2; else goto _L1
_L1:
            this;
            JVM INSTR monitorexit ;
            return;
_L2:
            sink.flush();
            if(true) goto _L1; else goto _L3
_L3:
            Exception exception;
            exception;
            throw exception;
        }

        public void write(OkBuffer okbuffer, long l)
            throws IOException
        {
            if(closed)
                throw new IllegalStateException("closed");
            if(l == 0L)
            {
                return;
            } else
            {
                writeHex(l);
                sink.write(okbuffer, l);
                sink.writeUtf8("\r\n");
                return;
            }
        }

        private boolean closed;
        private final byte hex[] = {
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
            0, 0, 0, 0, 0, 0, 13, 10
        };
        final HttpConnection this$0;

        private ChunkedSink()
        {
            this$0 = HttpConnection.this;
            super();
        }

    }

    private class ChunkedSource extends AbstractSource
        implements Source
    {

        private void readChunkSize()
            throws IOException
        {
            if(bytesRemainingInChunk != -1)
                source.readUtf8Line(true);
            String s = source.readUtf8Line(true);
            int i = s.indexOf(";");
            if(i != -1)
                s = s.substring(0, i);
            try
            {
                bytesRemainingInChunk = Integer.parseInt(s.trim(), 16);
            }
            catch(NumberFormatException numberformatexception)
            {
                throw new ProtocolException((new StringBuilder()).append("Expected a hex chunk size but was ").append(s).toString());
            }
            if(bytesRemainingInChunk == 0)
            {
                hasMoreChunks = false;
                Headers.Builder builder = new Headers.Builder();
                readHeaders(builder);
                httpEngine.receiveHeaders(builder.build());
                endOfInput(true);
            }
        }

        public void close()
            throws IOException
        {
            if(closed)
                return;
            if(hasMoreChunks && !discard(this, 100))
                unexpectedEndOfInput();
            closed = true;
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
            if(closed)
                throw new IllegalStateException("closed");
            if(!hasMoreChunks)
                return -1L;
            if(bytesRemainingInChunk == 0 || bytesRemainingInChunk == -1)
            {
                readChunkSize();
                if(!hasMoreChunks)
                    return -1L;
            }
            long l1 = source.read(okbuffer, Math.min(l, bytesRemainingInChunk));
            if(l1 == -1L)
            {
                unexpectedEndOfInput();
                throw new IOException("unexpected end of stream");
            } else
            {
                bytesRemainingInChunk = (int)((long)bytesRemainingInChunk - l1);
                cacheWrite(okbuffer, l1);
                return l1;
            }
        }

        private static final int NO_CHUNK_YET = -1;
        private int bytesRemainingInChunk;
        private boolean hasMoreChunks;
        private final HttpEngine httpEngine;
        final HttpConnection this$0;

        ChunkedSource(CacheRequest cacherequest, HttpEngine httpengine)
            throws IOException
        {
            this$0 = HttpConnection.this;
            super(cacherequest);
            bytesRemainingInChunk = -1;
            hasMoreChunks = true;
            httpEngine = httpengine;
        }
    }

    private final class FixedLengthSink
        implements Sink
    {

        public void close()
            throws IOException
        {
            if(closed)
                return;
            closed = true;
            if(bytesRemaining > 0L)
            {
                throw new ProtocolException("unexpected end of stream");
            } else
            {
                state = 3;
                return;
            }
        }

        public Sink deadline(Deadline deadline1)
        {
            return this;
        }

        public void flush()
            throws IOException
        {
            if(closed)
            {
                return;
            } else
            {
                sink.flush();
                return;
            }
        }

        public void write(OkBuffer okbuffer, long l)
            throws IOException
        {
            if(closed)
                throw new IllegalStateException("closed");
            Util.checkOffsetAndCount(okbuffer.size(), 0L, l);
            if(l > bytesRemaining)
            {
                throw new ProtocolException((new StringBuilder()).append("expected ").append(bytesRemaining).append(" bytes but received ").append(l).toString());
            } else
            {
                sink.write(okbuffer, l);
                bytesRemaining = bytesRemaining - l;
                return;
            }
        }

        private long bytesRemaining;
        private boolean closed;
        final HttpConnection this$0;

        private FixedLengthSink(long l)
        {
            this$0 = HttpConnection.this;
            super();
            bytesRemaining = l;
        }

        FixedLengthSink(long l, _cls1 _pcls1)
        {
            this(l);
        }
    }

    private class FixedLengthSource extends AbstractSource
        implements Source
    {

        public void close()
            throws IOException
        {
            if(closed)
                return;
            if(bytesRemaining != 0L && !discard(this, 100))
                unexpectedEndOfInput();
            closed = true;
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
            if(closed)
                throw new IllegalStateException("closed");
            long l1;
            if(bytesRemaining == 0L)
            {
                l1 = -1L;
            } else
            {
                l1 = source.read(okbuffer, Math.min(bytesRemaining, l));
                if(l1 == -1L)
                {
                    unexpectedEndOfInput();
                    throw new ProtocolException("unexpected end of stream");
                }
                bytesRemaining = bytesRemaining - l1;
                cacheWrite(okbuffer, l1);
                if(bytesRemaining == 0L)
                {
                    endOfInput(true);
                    return l1;
                }
            }
            return l1;
        }

        private long bytesRemaining;
        final HttpConnection this$0;

        public FixedLengthSource(CacheRequest cacherequest, long l)
            throws IOException
        {
            this$0 = HttpConnection.this;
            super(cacherequest);
            bytesRemaining = l;
            if(bytesRemaining == 0L)
                endOfInput(true);
        }
    }

    class UnknownLengthSource extends AbstractSource
        implements Source
    {

        public void close()
            throws IOException
        {
            if(closed)
                return;
            if(!inputExhausted)
                unexpectedEndOfInput();
            closed = true;
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
            if(closed)
                throw new IllegalStateException("closed");
            if(inputExhausted)
                return -1L;
            long l1 = source.read(okbuffer, l);
            if(l1 == -1L)
            {
                inputExhausted = true;
                endOfInput(false);
                return -1L;
            } else
            {
                cacheWrite(okbuffer, l1);
                return l1;
            }
        }

        private boolean inputExhausted;
        final HttpConnection this$0;

        UnknownLengthSource(CacheRequest cacherequest)
            throws IOException
        {
            this$0 = HttpConnection.this;
            super(cacherequest);
        }
    }


    public HttpConnection(ConnectionPool connectionpool, Connection connection1, BufferedSource bufferedsource, BufferedSink bufferedsink)
    {
        state = 0;
        onIdle = 0;
        pool = connectionpool;
        connection = connection1;
        source = bufferedsource;
        sink = bufferedsink;
    }

    public void closeIfOwnedBy(Object obj)
        throws IOException
    {
        connection.closeIfOwnedBy(obj);
    }

    public void closeOnIdle()
        throws IOException
    {
        onIdle = 2;
        if(state == 0)
        {
            state = 6;
            connection.close();
        }
    }

    public boolean discard(Source source1, int i)
    {
        Socket socket = connection.getSocket();
        int j;
        Exception exception;
        boolean flag;
        try
        {
            j = socket.getSoTimeout();
            socket.setSoTimeout(i);
        }
        catch(IOException ioexception)
        {
            return false;
        }
        flag = Util.skipAll(source1, i);
        socket.setSoTimeout(j);
        return flag;
        exception;
        socket.setSoTimeout(j);
        throw exception;
    }

    public void emptyResponseBody()
        throws IOException
    {
        newFixedLengthSource(null, 0L);
    }

    public void flush()
        throws IOException
    {
        sink.flush();
    }

    public boolean isClosed()
    {
        return state == 6;
    }

    public Sink newChunkedSink()
    {
        if(state != 1)
        {
            throw new IllegalStateException((new StringBuilder()).append("state: ").append(state).toString());
        } else
        {
            state = 2;
            return new ChunkedSink();
        }
    }

    public Source newChunkedSource(CacheRequest cacherequest, HttpEngine httpengine)
        throws IOException
    {
        if(state != 4)
        {
            throw new IllegalStateException((new StringBuilder()).append("state: ").append(state).toString());
        } else
        {
            state = 5;
            return new ChunkedSource(cacherequest, httpengine);
        }
    }

    public Sink newFixedLengthSink(long l)
    {
        if(state != 1)
        {
            throw new IllegalStateException((new StringBuilder()).append("state: ").append(state).toString());
        } else
        {
            state = 2;
            return new FixedLengthSink(l);
        }
    }

    public Source newFixedLengthSource(CacheRequest cacherequest, long l)
        throws IOException
    {
        if(state != 4)
        {
            throw new IllegalStateException((new StringBuilder()).append("state: ").append(state).toString());
        } else
        {
            state = 5;
            return new FixedLengthSource(cacherequest, l);
        }
    }

    public Source newUnknownLengthSource(CacheRequest cacherequest)
        throws IOException
    {
        if(state != 4)
        {
            throw new IllegalStateException((new StringBuilder()).append("state: ").append(state).toString());
        } else
        {
            state = 5;
            return new UnknownLengthSource(cacherequest);
        }
    }

    public void poolOnIdle()
    {
        onIdle = 1;
        if(state == 0)
        {
            onIdle = 0;
            pool.recycle(connection);
        }
    }

    public void readHeaders(Headers.Builder builder)
        throws IOException
    {
        do
        {
            String s = source.readUtf8Line(true);
            if(s.length() != 0)
                builder.addLine(s);
            else
                return;
        } while(true);
    }

    public Response.Builder readResponse()
        throws IOException
    {
        if(state != 1 && state != 3)
            throw new IllegalStateException((new StringBuilder()).append("state: ").append(state).toString());
        StatusLine statusline;
        Response.Builder builder;
        do
        {
            statusline = new StatusLine(source.readUtf8Line(true));
            builder = (new Response.Builder()).statusLine(statusline).header(OkHeaders.SELECTED_PROTOCOL, Protocol.HTTP_11.name.utf8());
            Headers.Builder builder1 = new Headers.Builder();
            readHeaders(builder1);
            builder.headers(builder1.build());
        } while(statusline.code() == 100);
        state = 4;
        return builder;
    }

    public void writeRequest(Headers headers, String s)
        throws IOException
    {
        if(state != 0)
            throw new IllegalStateException((new StringBuilder()).append("state: ").append(state).toString());
        sink.writeUtf8(s).writeUtf8("\r\n");
        for(int i = 0; i < headers.size(); i++)
            sink.writeUtf8(headers.name(i)).writeUtf8(": ").writeUtf8(headers.value(i)).writeUtf8("\r\n");

        sink.writeUtf8("\r\n");
        state = 1;
    }

    public void writeRequestBody(RetryableSink retryablesink)
        throws IOException
    {
        if(state != 1)
        {
            throw new IllegalStateException((new StringBuilder()).append("state: ").append(state).toString());
        } else
        {
            state = 3;
            retryablesink.writeToSocket(sink);
            return;
        }
    }

    private static final String CRLF = "\r\n";
    private static final byte FINAL_CHUNK[] = {
        48, 13, 10, 13, 10
    };
    private static final byte HEX_DIGITS[] = {
        48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 
        97, 98, 99, 100, 101, 102
    };
    private static final int ON_IDLE_CLOSE = 2;
    private static final int ON_IDLE_HOLD = 0;
    private static final int ON_IDLE_POOL = 1;
    private static final int STATE_CLOSED = 6;
    private static final int STATE_IDLE = 0;
    private static final int STATE_OPEN_REQUEST_BODY = 1;
    private static final int STATE_OPEN_RESPONSE_BODY = 4;
    private static final int STATE_READING_RESPONSE_BODY = 5;
    private static final int STATE_READ_RESPONSE_HEADERS = 3;
    private static final int STATE_WRITING_REQUEST_BODY = 2;
    private final Connection connection;
    private int onIdle;
    private final ConnectionPool pool;
    private final BufferedSink sink;
    private final BufferedSource source;
    private int state;





/*
    static int access$302(HttpConnection httpconnection, int i)
    {
        httpconnection.state = i;
        return i;
    }

*/





/*
    static int access$602(HttpConnection httpconnection, int i)
    {
        httpconnection.onIdle = i;
        return i;
    }

*/



}
