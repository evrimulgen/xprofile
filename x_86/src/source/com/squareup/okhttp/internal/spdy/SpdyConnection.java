// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.squareup.okhttp.internal.spdy;

import com.squareup.okhttp.Protocol;
import com.squareup.okhttp.internal.NamedRunnable;
import com.squareup.okhttp.internal.Util;
import com.squareup.okhttp.internal.okio.*;
import java.io.*;
import java.net.Socket;
import java.util.*;
import java.util.concurrent.*;

// Referenced classes of package com.squareup.okhttp.internal.spdy:
//            Settings, Http20Draft09, Variant, Spdy3, 
//            SpdyStream, Ping, FrameReader, FrameWriter, 
//            ErrorCode, IncomingStreamHandler, PushObserver, HeadersMode

public final class SpdyConnection
    implements Closeable
{
    public static class Builder
    {

        public SpdyConnection build()
        {
            return new SpdyConnection(this);
        }

        public Builder handler(IncomingStreamHandler incomingstreamhandler)
        {
            handler = incomingstreamhandler;
            return this;
        }

        public Builder protocol(Protocol protocol1)
        {
            protocol = protocol1;
            return this;
        }

        public Builder pushObserver(PushObserver pushobserver)
        {
            pushObserver = pushobserver;
            return this;
        }

        private boolean client;
        private IncomingStreamHandler handler;
        private String hostName;
        private Protocol protocol;
        private PushObserver pushObserver;
        private BufferedSink sink;
        private BufferedSource source;








        public Builder(String s, boolean flag, BufferedSource bufferedsource, BufferedSink bufferedsink)
        {
            handler = IncomingStreamHandler.REFUSE_INCOMING_STREAMS;
            protocol = Protocol.SPDY_3;
            pushObserver = PushObserver.CANCEL;
            hostName = s;
            client = flag;
            source = bufferedsource;
            sink = bufferedsink;
        }

        public Builder(boolean flag, Socket socket)
            throws IOException
        {
            this("", flag, Okio.buffer(Okio.source(socket.getInputStream())), Okio.buffer(Okio.sink(socket.getOutputStream())));
        }
    }

    class Reader extends NamedRunnable
        implements FrameReader.Handler
    {

        private void ackSettingsLater()
        {
            ExecutorService executorservice = SpdyConnection.executor;
            Object aobj[] = new Object[1];
            aobj[0] = hostName;
            executorservice.submit(new NamedRunnable("OkHttp %s ACK Settings", aobj) {

                public void execute()
                {
                    try
                    {
                        frameWriter.ackSettings();
                        return;
                    }
                    catch(IOException ioexception)
                    {
                        return;
                    }
                }

                final Reader this$1;

            transient 
            {
                this$1 = Reader.this;
                super(s, aobj);
            }
            }
);
        }

        public void ackSettings()
        {
        }

        public void data(boolean flag, int i, BufferedSource bufferedsource, int j)
            throws IOException
        {
            if(pushedStream(i))
            {
                pushDataLater(i, bufferedsource, j, flag);
            } else
            {
                SpdyStream spdystream = getStream(i);
                if(spdystream == null)
                {
                    writeSynResetLater(i, ErrorCode.INVALID_STREAM);
                    bufferedsource.skip(j);
                    return;
                }
                spdystream.receiveData(bufferedsource, j);
                if(flag)
                {
                    spdystream.receiveFin();
                    return;
                }
            }
        }

        protected void execute()
        {
            ErrorCode errorcode;
            ErrorCode errorcode1;
            errorcode = ErrorCode.INTERNAL_ERROR;
            errorcode1 = ErrorCode.INTERNAL_ERROR;
            ErrorCode errorcode3;
            if(!client)
                frameReader.readConnectionHeader();
            while(frameReader.nextFrame(this)) ;
            errorcode = ErrorCode.NO_ERROR;
            errorcode3 = ErrorCode.CANCEL;
            Exception exception;
            IOException ioexception1;
            ErrorCode errorcode2;
            try
            {
                close(errorcode, errorcode3);
                return;
            }
            catch(IOException ioexception3)
            {
                return;
            }
            ioexception1;
            errorcode = ErrorCode.PROTOCOL_ERROR;
            errorcode2 = ErrorCode.PROTOCOL_ERROR;
            try
            {
                close(errorcode, errorcode2);
                return;
            }
            catch(IOException ioexception2)
            {
                return;
            }
            exception;
            try
            {
                close(errorcode, errorcode1);
            }
            catch(IOException ioexception) { }
            throw exception;
        }

        public void goAway(int i, ErrorCode errorcode, ByteString bytestring)
        {
            if(bytestring.size() <= 0);
            SpdyConnection spdyconnection = SpdyConnection.this;
            spdyconnection;
            JVM INSTR monitorenter ;
            shutdown = true;
            Iterator iterator = streams.entrySet().iterator();
            do
            {
                if(!iterator.hasNext())
                    break;
                java.util.Map.Entry entry = (java.util.Map.Entry)iterator.next();
                if(((Integer)entry.getKey()).intValue() > i && ((SpdyStream)entry.getValue()).isLocallyInitiated())
                {
                    ((SpdyStream)entry.getValue()).receiveRstStream(ErrorCode.REFUSED_STREAM);
                    iterator.remove();
                }
            } while(true);
            break MISSING_BLOCK_LABEL_133;
            Exception exception;
            exception;
            spdyconnection;
            JVM INSTR monitorexit ;
            throw exception;
            spdyconnection;
            JVM INSTR monitorexit ;
        }

        public void headers(boolean flag, boolean flag1, int i, int j, int k, List list, HeadersMode headersmode)
        {
            if(!pushedStream(i)) goto _L2; else goto _L1
_L1:
            pushHeadersLater(i, list, flag1);
_L4:
            return;
_L2:
            synchronized(SpdyConnection.this)
            {
                if(!shutdown)
                    break MISSING_BLOCK_LABEL_54;
            }
            return;
            exception;
            spdyconnection;
            JVM INSTR monitorexit ;
            throw exception;
            SpdyStream spdystream = getStream(i);
            if(spdystream != null)
                break MISSING_BLOCK_LABEL_230;
            if(!headersmode.failIfStreamAbsent())
                break MISSING_BLOCK_LABEL_92;
            writeSynResetLater(i, ErrorCode.INVALID_STREAM);
            spdyconnection;
            JVM INSTR monitorexit ;
            return;
            if(i > lastGoodStreamId)
                break MISSING_BLOCK_LABEL_107;
            spdyconnection;
            JVM INSTR monitorexit ;
            return;
            if(i % 2 != nextStreamId % 2)
                break MISSING_BLOCK_LABEL_126;
            spdyconnection;
            JVM INSTR monitorexit ;
            return;
            SpdyStream spdystream1 = new SpdyStream(i, SpdyConnection.this, flag, flag1, k, list);
            lastGoodStreamId = i;
            streams.put(Integer.valueOf(i), spdystream1);
            ExecutorService executorservice = SpdyConnection.executor;
            Object aobj[] = new Object[2];
            aobj[0] = hostName;
            aobj[1] = Integer.valueOf(i);
            executorservice.submit("OkHttp %s stream %d". new NamedRunnable(aobj, spdystream1) {

                public void execute()
                {
                    try
                    {
                        handler.receive(newStream);
                        return;
                    }
                    catch(IOException ioexception)
                    {
                        throw new RuntimeException(ioexception);
                    }
                }

                final Reader this$1;
                final SpdyStream val$newStream;

            transient 
            {
                this$1 = final_reader;
                newStream = spdystream;
                super(String.this, aobj);
            }
            }
);
            spdyconnection;
            JVM INSTR monitorexit ;
            return;
            spdyconnection;
            JVM INSTR monitorexit ;
            if(headersmode.failIfStreamPresent())
            {
                spdystream.closeLater(ErrorCode.PROTOCOL_ERROR);
                removeStream(i);
                return;
            }
            spdystream.receiveHeaders(list, headersmode);
            if(flag1)
            {
                spdystream.receiveFin();
                return;
            }
            if(true) goto _L4; else goto _L3
_L3:
        }

        public void ping(boolean flag, int i, int j)
        {
            if(flag)
            {
                Ping ping1 = removePing(i);
                if(ping1 != null)
                    ping1.receive();
                return;
            } else
            {
                writePingLater(true, i, j, null);
                return;
            }
        }

        public void priority(int i, int j)
        {
        }

        public void pushPromise(int i, int j, List list)
        {
            pushRequestLater(j, list);
        }

        public void rstStream(int i, ErrorCode errorcode)
        {
            if(pushedStream(i))
            {
                pushResetLater(i, errorcode);
            } else
            {
                SpdyStream spdystream = removeStream(i);
                if(spdystream != null)
                {
                    spdystream.receiveRstStream(errorcode);
                    return;
                }
            }
        }

        public void settings(boolean flag, Settings settings1)
        {
            long l = 0L;
            SpdyConnection spdyconnection = SpdyConnection.this;
            spdyconnection;
            JVM INSTR monitorenter ;
            int i = peerSettings.getInitialWindowSize(0x10000);
            if(!flag)
                break MISSING_BLOCK_LABEL_40;
            peerSettings.clear();
            int j;
            peerSettings.merge(settings1);
            if(getProtocol() == Protocol.HTTP_2)
                ackSettingsLater();
            j = peerSettings.getInitialWindowSize(0x10000);
            SpdyStream aspdystream[];
            aspdystream = null;
            if(j == -1)
                break MISSING_BLOCK_LABEL_195;
            aspdystream = null;
            if(j == i)
                break MISSING_BLOCK_LABEL_195;
            l = j - i;
            boolean flag1;
            if(!receivedInitialPeerSettings)
            {
                addBytesToWriteWindow(l);
                receivedInitialPeerSettings = true;
            }
            flag1 = streams.isEmpty();
            aspdystream = null;
            if(flag1)
                break MISSING_BLOCK_LABEL_195;
            aspdystream = (SpdyStream[])streams.values().toArray(new SpdyStream[streams.size()]);
            spdyconnection;
            JVM INSTR monitorexit ;
            if(aspdystream != null && l != 0L)
            {
                for(Iterator iterator = streams.values().iterator(); iterator.hasNext();)
                    synchronized((SpdyStream)iterator.next())
                    {
                        spdystream.addBytesToWriteWindow(l);
                    }

            }
            break MISSING_BLOCK_LABEL_281;
            exception1;
            spdystream;
            JVM INSTR monitorexit ;
            throw exception1;
            Exception exception;
            exception;
            spdyconnection;
            JVM INSTR monitorexit ;
            throw exception;
        }

        public void windowUpdate(int i, long l)
        {
            if(i == 0)
            {
                synchronized(SpdyConnection.this)
                {
                    SpdyConnection spdyconnection1 = SpdyConnection.this;
                    spdyconnection1.bytesLeftInWriteWindow = l + spdyconnection1.bytesLeftInWriteWindow;
                    notifyAll();
                }
                return;
            }
            break MISSING_BLOCK_LABEL_50;
            exception1;
            spdyconnection;
            JVM INSTR monitorexit ;
            throw exception1;
            SpdyStream spdystream;
            spdystream = getStream(i);
            if(spdystream == null)
                break MISSING_BLOCK_LABEL_86;
            spdystream;
            JVM INSTR monitorenter ;
            spdystream.addBytesToWriteWindow(l);
            spdystream;
            JVM INSTR monitorexit ;
            return;
            Exception exception;
            exception;
            spdystream;
            JVM INSTR monitorexit ;
            throw exception;
        }

        final SpdyConnection this$0;

        private Reader()
        {
            this$0 = SpdyConnection.this;
            Object aobj[] = new Object[1];
            aobj[0] = hostName;
            super("OkHttp %s", aobj);
        }

    }


    private SpdyConnection(Builder builder)
    {
        int i = 1;
        super();
        streams = new HashMap();
        idleStartTimeNs = System.nanoTime();
        unacknowledgedBytesRead = 0L;
        okHttpSettings = new Settings();
        peerSettings = new Settings();
        receivedInitialPeerSettings = false;
        currentPushRequests = new LinkedHashSet();
        protocol = builder.protocol;
        pushObserver = builder.pushObserver;
        client = builder.client;
        handler = builder.handler;
        int j;
        Object obj;
        if(builder.client)
            j = i;
        else
            j = 2;
        nextStreamId = j;
        if(!builder.client)
            i = 2;
        nextPingId = i;
        if(builder.client)
            okHttpSettings.set(7, 0, 0x1000000);
        hostName = builder.hostName;
        if(protocol == Protocol.HTTP_2)
            obj = new Http20Draft09();
        else
        if(protocol == Protocol.SPDY_3)
            obj = new Spdy3();
        else
            throw new AssertionError(protocol);
        bytesLeftInWriteWindow = peerSettings.getInitialWindowSize(0x10000);
        frameReader = ((Variant) (obj)).newReader(builder.source, client);
        frameWriter = ((Variant) (obj)).newWriter(builder.sink, client);
        maxFrameSize = ((Variant) (obj)).maxFrameSize();
        readerRunnable = new Reader();
        (new Thread(readerRunnable)).start();
    }


    private void close(ErrorCode errorcode, ErrorCode errorcode1)
        throws IOException
    {
        IOException ioexception;
        Ping aping[];
        if(!$assertionsDisabled && Thread.holdsLock(this))
            throw new AssertionError();
        ioexception = null;
        boolean flag;
        SpdyStream aspdystream[];
        Map map;
        SpdyStream aspdystream1[];
        int k;
        int l;
        SpdyStream spdystream;
        try
        {
            shutdown(errorcode);
        }
        catch(IOException ioexception1)
        {
            ioexception = ioexception1;
        }
        this;
        JVM INSTR monitorenter ;
        flag = streams.isEmpty();
        aspdystream = null;
        if(flag)
            break MISSING_BLOCK_LABEL_94;
        aspdystream = (SpdyStream[])streams.values().toArray(new SpdyStream[streams.size()]);
        streams.clear();
        setIdle(false);
        map = pings;
        aping = null;
        if(map == null)
            break MISSING_BLOCK_LABEL_144;
        aping = (Ping[])pings.values().toArray(new Ping[pings.size()]);
        pings = null;
        this;
        JVM INSTR monitorexit ;
        if(aspdystream != null)
        {
            aspdystream1 = aspdystream;
            k = aspdystream1.length;
            l = 0;
            do
            {
                if(l >= k)
                    break;
                spdystream = aspdystream1[l];
                Exception exception;
                try
                {
                    spdystream.close(errorcode1);
                }
                catch(IOException ioexception4)
                {
                    if(ioexception != null)
                        ioexception = ioexception4;
                }
                l++;
            } while(true);
        }
        break MISSING_BLOCK_LABEL_216;
        exception;
        this;
        JVM INSTR monitorexit ;
        throw exception;
        if(aping != null)
        {
            Ping aping1[] = aping;
            int i = aping1.length;
            for(int j = 0; j < i; j++)
                aping1[j].cancel();

        }
        try
        {
            frameReader.close();
        }
        catch(IOException ioexception2)
        {
            ioexception = ioexception2;
        }
        try
        {
            frameWriter.close();
        }
        catch(IOException ioexception3)
        {
            if(ioexception == null)
                ioexception = ioexception3;
        }
        if(ioexception != null)
            throw ioexception;
        else
            return;
    }

    private SpdyStream newStream(int i, List list, boolean flag, boolean flag1)
        throws IOException
    {
        boolean flag2;
        boolean flag3;
        FrameWriter framewriter;
        Exception exception;
        Exception exception1;
        if(!flag)
            flag2 = true;
        else
            flag2 = false;
        if(!flag1)
            flag3 = true;
        else
            flag3 = false;
        framewriter = frameWriter;
        framewriter;
        JVM INSTR monitorenter ;
        this;
        JVM INSTR monitorenter ;
        if(shutdown)
            throw new IOException("shutdown");
        break MISSING_BLOCK_LABEL_71;
        exception1;
        this;
        JVM INSTR monitorexit ;
        throw exception1;
        exception;
        framewriter;
        JVM INSTR monitorexit ;
        throw exception;
        int j;
        SpdyStream spdystream;
        j = nextStreamId;
        nextStreamId = 2 + nextStreamId;
        spdystream = new SpdyStream(j, this, flag2, flag3, -1, list);
        if(spdystream.isOpen())
        {
            streams.put(Integer.valueOf(j), spdystream);
            setIdle(false);
        }
        this;
        JVM INSTR monitorexit ;
        if(i != 0)
            break MISSING_BLOCK_LABEL_179;
        frameWriter.synStream(flag2, flag3, j, i, -1, 0, list);
_L1:
        framewriter;
        JVM INSTR monitorexit ;
        if(!flag)
            frameWriter.flush();
        return spdystream;
        if(client)
            throw new IllegalArgumentException("client streams shouldn't have associated stream IDs");
        frameWriter.pushPromise(i, j, list);
          goto _L1
    }

    private void pushDataLater(int i, BufferedSource bufferedsource, int j, boolean flag)
        throws IOException
    {
        OkBuffer okbuffer = new OkBuffer();
        bufferedsource.require(j);
        bufferedsource.read(okbuffer, j);
        if(okbuffer.size() != (long)j)
        {
            throw new IOException((new StringBuilder()).append(okbuffer.size()).append(" != ").append(j).toString());
        } else
        {
            ExecutorService executorservice = executor;
            Object aobj[] = new Object[2];
            aobj[0] = hostName;
            aobj[1] = Integer.valueOf(i);
            executorservice.submit(new NamedRunnable(j, flag) {

                public void execute()
                {
                    boolean flag1 = pushObserver.onData(streamId, buffer, byteCount, inFinished);
                    if(!flag1)
                        break MISSING_BLOCK_LABEL_52;
                    frameWriter.rstStream(streamId, ErrorCode.CANCEL);
                    if(flag1)
                        break MISSING_BLOCK_LABEL_63;
                    if(!inFinished)
                        break MISSING_BLOCK_LABEL_101;
                    synchronized(SpdyConnection.this)
                    {
                        currentPushRequests.remove(Integer.valueOf(streamId));
                    }
                    return;
                    exception;
                    spdyconnection;
                    JVM INSTR monitorexit ;
                    try
                    {
                        throw exception;
                    }
                    catch(IOException ioexception) { }
                }

                final SpdyConnection this$0;
                final OkBuffer val$buffer;
                final int val$byteCount;
                final boolean val$inFinished;
                final int val$streamId;

            transient 
            {
                this$0 = SpdyConnection.this;
                streamId = i;
                buffer = okbuffer;
                byteCount = j;
                inFinished = flag;
                super(final_s, final_aobj);
            }
            }
);
            return;
        }
    }

    private void pushHeadersLater(int i, List list, boolean flag)
    {
        ExecutorService executorservice = executor;
        Object aobj[] = new Object[2];
        aobj[0] = hostName;
        aobj[1] = Integer.valueOf(i);
        executorservice.submit(new NamedRunnable(list, flag) {

            public void execute()
            {
                boolean flag1;
                flag1 = pushObserver.onHeaders(streamId, requestHeaders, inFinished);
                if(!flag1)
                    break MISSING_BLOCK_LABEL_48;
                frameWriter.rstStream(streamId, ErrorCode.CANCEL);
                if(flag1)
                    break MISSING_BLOCK_LABEL_59;
                if(!inFinished)
                    break MISSING_BLOCK_LABEL_97;
                synchronized(SpdyConnection.this)
                {
                    currentPushRequests.remove(Integer.valueOf(streamId));
                }
                return;
                exception;
                spdyconnection;
                JVM INSTR monitorexit ;
                try
                {
                    throw exception;
                }
                catch(IOException ioexception) { }
            }

            final SpdyConnection this$0;
            final boolean val$inFinished;
            final List val$requestHeaders;
            final int val$streamId;

            transient 
            {
                this$0 = SpdyConnection.this;
                streamId = i;
                requestHeaders = list;
                inFinished = flag;
                super(final_s, final_aobj);
            }
        }
);
    }

    private void pushRequestLater(int i, List list)
    {
        this;
        JVM INSTR monitorenter ;
        if(!currentPushRequests.contains(Integer.valueOf(i)))
            break MISSING_BLOCK_LABEL_29;
        writeSynResetLater(i, ErrorCode.PROTOCOL_ERROR);
        this;
        JVM INSTR monitorexit ;
        return;
        currentPushRequests.add(Integer.valueOf(i));
        this;
        JVM INSTR monitorexit ;
        ExecutorService executorservice = executor;
        Object aobj[] = new Object[2];
        aobj[0] = hostName;
        aobj[1] = Integer.valueOf(i);
        executorservice.submit(new NamedRunnable(i, list) {

            public void execute()
            {
                if(!pushObserver.onRequest(streamId, requestHeaders))
                    break MISSING_BLOCK_LABEL_78;
                frameWriter.rstStream(streamId, ErrorCode.CANCEL);
                synchronized(SpdyConnection.this)
                {
                    currentPushRequests.remove(Integer.valueOf(streamId));
                }
                return;
                exception1;
                spdyconnection;
                JVM INSTR monitorexit ;
                try
                {
                    throw exception1;
                }
                catch(IOException ioexception) { }
            }

            final SpdyConnection this$0;
            final List val$requestHeaders;
            final int val$streamId;

            transient 
            {
                this$0 = SpdyConnection.this;
                streamId = i;
                requestHeaders = list;
                super(final_s, final_aobj);
            }
        }
);
        return;
        Exception exception;
        exception;
        this;
        JVM INSTR monitorexit ;
        throw exception;
    }

    private void pushResetLater(int i, ErrorCode errorcode)
    {
        ExecutorService executorservice = executor;
        Object aobj[] = new Object[2];
        aobj[0] = hostName;
        aobj[1] = Integer.valueOf(i);
        executorservice.submit(new NamedRunnable(i, errorcode) {

            public void execute()
            {
                pushObserver.onReset(streamId, errorCode);
                synchronized(SpdyConnection.this)
                {
                    currentPushRequests.remove(Integer.valueOf(streamId));
                }
                return;
                exception;
                spdyconnection;
                JVM INSTR monitorexit ;
                throw exception;
            }

            final SpdyConnection this$0;
            final ErrorCode val$errorCode;
            final int val$streamId;

            transient 
            {
                this$0 = SpdyConnection.this;
                streamId = i;
                errorCode = errorcode;
                super(final_s, final_aobj);
            }
        }
);
    }

    private boolean pushedStream(int i)
    {
        return protocol == Protocol.HTTP_2 && i != 0 && (i & 1) == 0;
    }

    private Ping removePing(int i)
    {
        this;
        JVM INSTR monitorenter ;
        Ping ping1;
        if(pings == null)
            break MISSING_BLOCK_LABEL_30;
        ping1 = (Ping)pings.remove(Integer.valueOf(i));
_L1:
        this;
        JVM INSTR monitorexit ;
        return ping1;
        ping1 = null;
          goto _L1
        Exception exception;
        exception;
        throw exception;
    }

    private void setIdle(boolean flag)
    {
        this;
        JVM INSTR monitorenter ;
        if(!flag)
            break MISSING_BLOCK_LABEL_18;
        long l = System.nanoTime();
_L1:
        idleStartTimeNs = l;
        this;
        JVM INSTR monitorexit ;
        return;
        l = 0x7fffffffffffffffL;
          goto _L1
        Exception exception;
        exception;
        throw exception;
    }

    private void writePing(boolean flag, int i, int j, Ping ping1)
        throws IOException
    {
        FrameWriter framewriter = frameWriter;
        framewriter;
        JVM INSTR monitorenter ;
        if(ping1 == null)
            break MISSING_BLOCK_LABEL_19;
        ping1.send();
        frameWriter.ping(flag, i, j);
        framewriter;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        framewriter;
        JVM INSTR monitorexit ;
        throw exception;
    }

    private void writePingLater(boolean flag, int i, int j, Ping ping1)
    {
        ExecutorService executorservice = executor;
        Object aobj[] = new Object[3];
        aobj[0] = hostName;
        aobj[1] = Integer.valueOf(i);
        aobj[2] = Integer.valueOf(j);
        executorservice.submit(new NamedRunnable(j, ping1) {

            public void execute()
            {
                try
                {
                    writePing(reply, payload1, payload2, ping);
                    return;
                }
                catch(IOException ioexception)
                {
                    return;
                }
            }

            final SpdyConnection this$0;
            final int val$payload1;
            final int val$payload2;
            final Ping val$ping;
            final boolean val$reply;

            transient 
            {
                this$0 = SpdyConnection.this;
                reply = flag;
                payload1 = i;
                payload2 = j;
                ping = ping1;
                super(final_s, final_aobj);
            }
        }
);
    }

    void addBytesToWriteWindow(long l)
    {
        bytesLeftInWriteWindow = l + bytesLeftInWriteWindow;
        if(l > 0L)
            notifyAll();
    }

    public void close()
        throws IOException
    {
        close(ErrorCode.NO_ERROR, ErrorCode.CANCEL);
    }

    public void flush()
        throws IOException
    {
        frameWriter.flush();
    }

    public long getIdleStartTimeNs()
    {
        this;
        JVM INSTR monitorenter ;
        long l = idleStartTimeNs;
        this;
        JVM INSTR monitorexit ;
        return l;
        Exception exception;
        exception;
        throw exception;
    }

    public Protocol getProtocol()
    {
        return protocol;
    }

    SpdyStream getStream(int i)
    {
        this;
        JVM INSTR monitorenter ;
        SpdyStream spdystream = (SpdyStream)streams.get(Integer.valueOf(i));
        this;
        JVM INSTR monitorexit ;
        return spdystream;
        Exception exception;
        exception;
        throw exception;
    }

    public boolean isIdle()
    {
        this;
        JVM INSTR monitorenter ;
        long l = idleStartTimeNs;
        boolean flag;
        if(l != 0x7fffffffffffffffL)
            flag = true;
        else
            flag = false;
        this;
        JVM INSTR monitorexit ;
        return flag;
        Exception exception;
        exception;
        throw exception;
    }

    public SpdyStream newStream(List list, boolean flag, boolean flag1)
        throws IOException
    {
        return newStream(0, list, flag, flag1);
    }

    public int openStreamCount()
    {
        this;
        JVM INSTR monitorenter ;
        int i = streams.size();
        this;
        JVM INSTR monitorexit ;
        return i;
        Exception exception;
        exception;
        throw exception;
    }

    public Ping ping()
        throws IOException
    {
        Ping ping1 = new Ping();
        this;
        JVM INSTR monitorenter ;
        if(shutdown)
            throw new IOException("shutdown");
        break MISSING_BLOCK_LABEL_33;
        Exception exception;
        exception;
        this;
        JVM INSTR monitorexit ;
        throw exception;
        int i;
        i = nextPingId;
        nextPingId = 2 + nextPingId;
        if(pings == null)
            pings = new HashMap();
        pings.put(Integer.valueOf(i), ping1);
        this;
        JVM INSTR monitorexit ;
        writePing(false, i, 0x4f4b6f6b, ping1);
        return ping1;
    }

    public SpdyStream pushStream(int i, List list, boolean flag)
        throws IOException
    {
        if(client)
            throw new IllegalStateException("Client cannot push requests.");
        if(protocol != Protocol.HTTP_2)
            throw new IllegalStateException("protocol != HTTP_2");
        else
            return newStream(i, list, flag, false);
    }

    SpdyStream removeStream(int i)
    {
        this;
        JVM INSTR monitorenter ;
        SpdyStream spdystream = (SpdyStream)streams.remove(Integer.valueOf(i));
        if(spdystream == null)
            break MISSING_BLOCK_LABEL_40;
        if(streams.isEmpty())
            setIdle(true);
        this;
        JVM INSTR monitorexit ;
        return spdystream;
        Exception exception;
        exception;
        throw exception;
    }

    public void sendConnectionHeader()
        throws IOException
    {
        frameWriter.connectionHeader();
        frameWriter.settings(okHttpSettings);
    }

    public void shutdown(ErrorCode errorcode)
        throws IOException
    {
        FrameWriter framewriter = frameWriter;
        framewriter;
        JVM INSTR monitorenter ;
        this;
        JVM INSTR monitorenter ;
        if(!shutdown)
            break MISSING_BLOCK_LABEL_21;
        this;
        JVM INSTR monitorexit ;
        framewriter;
        JVM INSTR monitorexit ;
        return;
        int i;
        shutdown = true;
        i = lastGoodStreamId;
        this;
        JVM INSTR monitorexit ;
        frameWriter.goAway(i, errorcode, Util.EMPTY_BYTE_ARRAY);
        framewriter;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        framewriter;
        JVM INSTR monitorexit ;
        throw exception;
        Exception exception1;
        exception1;
        this;
        JVM INSTR monitorexit ;
        throw exception1;
    }

    public void writeData(int i, boolean flag, OkBuffer okbuffer, long l)
        throws IOException
    {
        if(l != 0L) goto _L2; else goto _L1
_L1:
        frameWriter.data(flag, i, okbuffer, 0);
_L4:
        return;
_L5:
        int j;
        j = (int)Math.min(Math.min(l, bytesLeftInWriteWindow), maxFrameSize);
        bytesLeftInWriteWindow = bytesLeftInWriteWindow - (long)j;
        this;
        JVM INSTR monitorexit ;
        l -= j;
        FrameWriter framewriter = frameWriter;
        Exception exception;
        InterruptedException interruptedexception;
        boolean flag1;
        if(flag && l == 0L)
            flag1 = true;
        else
            flag1 = false;
        framewriter.data(flag1, i, okbuffer, j);
_L2:
        if(l <= 0L) goto _L4; else goto _L3
_L3:
        this;
        JVM INSTR monitorenter ;
        while(bytesLeftInWriteWindow <= 0L) 
            wait();
          goto _L5
        interruptedexception;
        throw new InterruptedIOException();
        exception;
        this;
        JVM INSTR monitorexit ;
        throw exception;
    }

    void writeSynReply(int i, boolean flag, List list)
        throws IOException
    {
        frameWriter.synReply(flag, i, list);
    }

    void writeSynReset(int i, ErrorCode errorcode)
        throws IOException
    {
        frameWriter.rstStream(i, errorcode);
    }

    void writeSynResetLater(int i, ErrorCode errorcode)
    {
        ExecutorService executorservice = executor;
        Object aobj[] = new Object[2];
        aobj[0] = hostName;
        aobj[1] = Integer.valueOf(i);
        executorservice.submit(new NamedRunnable(i, errorcode) {

            public void execute()
            {
                try
                {
                    writeSynReset(streamId, errorCode);
                    return;
                }
                catch(IOException ioexception)
                {
                    return;
                }
            }

            final SpdyConnection this$0;
            final ErrorCode val$errorCode;
            final int val$streamId;

            transient 
            {
                this$0 = SpdyConnection.this;
                streamId = i;
                errorCode = errorcode;
                super(final_s, final_aobj);
            }
        }
);
    }

    void writeWindowUpdateLater(int i, long l)
    {
        ExecutorService executorservice = executor;
        Object aobj[] = new Object[2];
        aobj[0] = hostName;
        aobj[1] = Integer.valueOf(i);
        executorservice.submit(new NamedRunnable(i, l) {

            public void execute()
            {
                try
                {
                    frameWriter.windowUpdate(streamId, unacknowledgedBytesRead);
                    return;
                }
                catch(IOException ioexception)
                {
                    return;
                }
            }

            final SpdyConnection this$0;
            final int val$streamId;
            final long val$unacknowledgedBytesRead;

            transient 
            {
                this$0 = SpdyConnection.this;
                streamId = i;
                unacknowledgedBytesRead = l;
                super(final_s, final_aobj);
            }
        }
);
    }

    static final boolean $assertionsDisabled;
    private static final ExecutorService executor;
    long bytesLeftInWriteWindow;
    final boolean client;
    private final Set currentPushRequests;
    final FrameReader frameReader;
    final FrameWriter frameWriter;
    private final IncomingStreamHandler handler;
    private final String hostName;
    private long idleStartTimeNs;
    private int lastGoodStreamId;
    final long maxFrameSize;
    private int nextPingId;
    private int nextStreamId;
    final Settings okHttpSettings;
    final Settings peerSettings;
    private Map pings;
    final Protocol protocol;
    private final PushObserver pushObserver;
    final Reader readerRunnable;
    private boolean receivedInitialPeerSettings;
    private boolean shutdown;
    private final Map streams;
    long unacknowledgedBytesRead;

    static 
    {
        boolean flag;
        if(!com/squareup/okhttp/internal/spdy/SpdyConnection.desiredAssertionStatus())
            flag = true;
        else
            flag = false;
        $assertionsDisabled = flag;
        executor = new ThreadPoolExecutor(0, 0x7fffffff, 60L, TimeUnit.SECONDS, new SynchronousQueue(), Util.threadFactory("OkHttp SpdyConnection", true));
    }








/*
    static boolean access$1502(SpdyConnection spdyconnection, boolean flag)
    {
        spdyconnection.shutdown = flag;
        return flag;
    }

*/



/*
    static int access$1602(SpdyConnection spdyconnection, int i)
    {
        spdyconnection.lastGoodStreamId = i;
        return i;
    }

*/








/*
    static boolean access$2202(SpdyConnection spdyconnection, boolean flag)
    {
        spdyconnection.receivedInitialPeerSettings = flag;
        return flag;
    }

*/






}
