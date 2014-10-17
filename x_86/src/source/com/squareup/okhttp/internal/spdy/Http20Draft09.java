// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.squareup.okhttp.internal.spdy;

import com.squareup.okhttp.Protocol;
import com.squareup.okhttp.internal.okio.*;
import java.io.IOException;
import java.util.List;

// Referenced classes of package com.squareup.okhttp.internal.spdy:
//            Variant, FrameReader, FrameWriter, ErrorCode, 
//            HeadersMode, Settings

public final class Http20Draft09
    implements Variant
{
    static final class ContinuationSource
        implements Source
    {

        private void readContinuationHeader()
            throws IOException
        {
            int i = streamId;
            int j = source.readInt();
            int k = source.readInt();
            int l = (short)((0x3fff0000 & j) >> 16);
            left = l;
            length = l;
            byte byte0 = (byte)((0xff00 & j) >> 8);
            flags = (byte)(j & 0xff);
            streamId = 0x7fffffff & k;
            if(byte0 != 10)
            {
                Object aobj[] = new Object[1];
                aobj[0] = Byte.valueOf(byte0);
                throw Http20Draft09.ioException("%s != TYPE_CONTINUATION", aobj);
            }
            if(streamId != i)
                throw Http20Draft09.ioException("TYPE_CONTINUATION streamId changed", new Object[0]);
            else
                return;
        }

        public void close()
            throws IOException
        {
        }

        public Source deadline(Deadline deadline1)
        {
            source.deadline(deadline1);
            return this;
        }

        public long read(OkBuffer okbuffer, long l)
            throws IOException
        {
            while(left == 0) 
            {
                if((4 & flags) != 0)
                    return -1L;
                readContinuationHeader();
            }
            long l1 = source.read(okbuffer, Math.min(l, left));
            if(l1 == -1L)
            {
                return -1L;
            } else
            {
                left = (int)((long)left - l1);
                return l1;
            }
        }

        byte flags;
        int left;
        int length;
        private final BufferedSource source;
        int streamId;

        public ContinuationSource(BufferedSource bufferedsource)
        {
            source = bufferedsource;
        }
    }

    static final class Reader
        implements FrameReader
    {

        private void readData(FrameReader.Handler handler, short word0, byte byte0, int i)
            throws IOException
        {
            boolean flag;
            if((byte0 & 1) != 0)
                flag = true;
            else
                flag = false;
            handler.data(flag, i, source, word0);
        }

        private void readGoAway(FrameReader.Handler handler, short word0, byte byte0, int i)
            throws IOException
        {
            if(word0 < 8)
            {
                Object aobj1[] = new Object[1];
                aobj1[0] = Short.valueOf(word0);
                throw Http20Draft09.ioException("TYPE_GOAWAY length < 8: %s", aobj1);
            }
            if(i != 0)
                throw Http20Draft09.ioException("TYPE_GOAWAY streamId != 0", new Object[0]);
            int j = source.readInt();
            int k = source.readInt();
            int l = word0 - 8;
            ErrorCode errorcode = ErrorCode.fromHttp2(k);
            if(errorcode == null)
            {
                Object aobj[] = new Object[1];
                aobj[0] = Integer.valueOf(k);
                throw Http20Draft09.ioException("TYPE_GOAWAY unexpected error code: %d", aobj);
            }
            ByteString bytestring = ByteString.EMPTY;
            if(l > 0)
                bytestring = source.readByteString(l);
            handler.goAway(j, errorcode, bytestring);
        }

        private List readHeaderBlock(short word0, byte byte0, int i)
            throws IOException
        {
            ContinuationSource continuationsource = continuation;
            continuation.left = word0;
            continuationsource.length = word0;
            continuation.flags = byte0;
            continuation.streamId = i;
            hpackReader.readHeaders();
            hpackReader.emitReferenceSet();
            return hpackReader.getAndReset();
        }

        private void readHeaders(FrameReader.Handler handler, short word0, byte byte0, int i)
            throws IOException
        {
            if(i == 0)
                throw Http20Draft09.ioException("PROTOCOL_ERROR: TYPE_HEADERS streamId == 0", new Object[0]);
            boolean flag;
            int j;
            if((byte0 & 1) != 0)
                flag = true;
            else
                flag = false;
            j = -1;
            if((byte0 & 8) != 0)
            {
                j = 0x7fffffff & source.readInt();
                word0 -= 4;
            }
            handler.headers(false, flag, i, -1, j, readHeaderBlock(word0, byte0, i), HeadersMode.HTTP_20_HEADERS);
        }

        private void readPing(FrameReader.Handler handler, short word0, byte byte0, int i)
            throws IOException
        {
            boolean flag = true;
            if(word0 != 8)
            {
                Object aobj[] = new Object[flag];
                aobj[0] = Short.valueOf(word0);
                throw Http20Draft09.ioException("TYPE_PING length != 8: %s", aobj);
            }
            if(i != 0)
                throw Http20Draft09.ioException("TYPE_PING streamId != 0", new Object[0]);
            int j = source.readInt();
            int k = source.readInt();
            if((byte0 & 1) == 0)
                flag = false;
            handler.ping(flag, j, k);
        }

        private void readPriority(FrameReader.Handler handler, short word0, byte byte0, int i)
            throws IOException
        {
            if(word0 != 4)
            {
                Object aobj[] = new Object[1];
                aobj[0] = Short.valueOf(word0);
                throw Http20Draft09.ioException("TYPE_PRIORITY length: %d != 4", aobj);
            }
            if(i == 0)
            {
                throw Http20Draft09.ioException("TYPE_PRIORITY streamId == 0", new Object[0]);
            } else
            {
                handler.priority(i, 0x7fffffff & source.readInt());
                return;
            }
        }

        private void readPushPromise(FrameReader.Handler handler, short word0, byte byte0, int i)
            throws IOException
        {
            if(i == 0)
            {
                throw Http20Draft09.ioException("PROTOCOL_ERROR: TYPE_PUSH_PROMISE streamId == 0", new Object[0]);
            } else
            {
                handler.pushPromise(i, 0x7fffffff & source.readInt(), readHeaderBlock((short)(word0 - 4), byte0, i));
                return;
            }
        }

        private void readRstStream(FrameReader.Handler handler, short word0, byte byte0, int i)
            throws IOException
        {
            if(word0 != 4)
            {
                Object aobj1[] = new Object[1];
                aobj1[0] = Short.valueOf(word0);
                throw Http20Draft09.ioException("TYPE_RST_STREAM length: %d != 4", aobj1);
            }
            if(i == 0)
                throw Http20Draft09.ioException("TYPE_RST_STREAM streamId == 0", new Object[0]);
            int j = source.readInt();
            ErrorCode errorcode = ErrorCode.fromHttp2(j);
            if(errorcode == null)
            {
                Object aobj[] = new Object[1];
                aobj[0] = Integer.valueOf(j);
                throw Http20Draft09.ioException("TYPE_RST_STREAM unexpected error code: %d", aobj);
            } else
            {
                handler.rstStream(i, errorcode);
                return;
            }
        }

        private void readSettings(FrameReader.Handler handler, short word0, byte byte0, int i)
            throws IOException
        {
            if(i != 0)
                throw Http20Draft09.ioException("TYPE_SETTINGS streamId != 0", new Object[0]);
            if((byte0 & 1) != 0)
            {
                if(word0 != 0)
                    throw Http20Draft09.ioException("FRAME_SIZE_ERROR ack frame should be empty!", new Object[0]);
                handler.ackSettings();
            } else
            {
                if(word0 % 8 != 0)
                {
                    Object aobj[] = new Object[1];
                    aobj[0] = Short.valueOf(word0);
                    throw Http20Draft09.ioException("TYPE_SETTINGS length %% 8 != 0: %s", aobj);
                }
                Settings settings = new Settings();
                for(int j = 0; j < word0; j += 8)
                {
                    int k = source.readInt();
                    int l = source.readInt();
                    settings.set(k & 0xffffff, 0, l);
                }

                handler.settings(false, settings);
                if(settings.getHeaderTableSize() >= 0)
                {
                    hpackReader.maxHeaderTableByteCount(settings.getHeaderTableSize());
                    return;
                }
            }
        }

        private void readWindowUpdate(FrameReader.Handler handler, short word0, byte byte0, int i)
            throws IOException
        {
            if(word0 != 4)
            {
                Object aobj1[] = new Object[1];
                aobj1[0] = Short.valueOf(word0);
                throw Http20Draft09.ioException("TYPE_WINDOW_UPDATE length !=4: %s", aobj1);
            }
            long l = 0x7fffffff & source.readInt();
            if(l == 0L)
            {
                Object aobj[] = new Object[1];
                aobj[0] = Long.valueOf(l);
                throw Http20Draft09.ioException("windowSizeIncrement was 0", aobj);
            } else
            {
                handler.windowUpdate(i, l);
                return;
            }
        }

        public void close()
            throws IOException
        {
            source.close();
        }

        public boolean nextFrame(FrameReader.Handler handler)
            throws IOException
        {
            int k;
            byte byte1;
            int l;
            int i;
            int j;
            byte byte0;
            try
            {
                i = source.readInt();
                j = source.readInt();
            }
            catch(IOException ioexception)
            {
                return false;
            }
            k = (short)((0x3fff0000 & i) >> 16);
            byte0 = (byte)((0xff00 & i) >> 8);
            byte1 = (byte)(i & 0xff);
            l = j & 0x7fffffff;
            byte0;
            JVM INSTR tableswitch 0 9: default 112
        //                       0 129
        //                       1 143
        //                       2 157
        //                       3 171
        //                       4 185
        //                       5 199
        //                       6 213
        //                       7 227
        //                       8 112
        //                       9 241;
               goto _L1 _L2 _L3 _L4 _L5 _L6 _L7 _L8 _L9 _L1 _L10
_L1:
            source.skip(k);
_L12:
            return true;
_L2:
            readData(handler, k, byte1, l);
            continue; /* Loop/switch isn't completed */
_L3:
            readHeaders(handler, k, byte1, l);
            continue; /* Loop/switch isn't completed */
_L4:
            readPriority(handler, k, byte1, l);
            continue; /* Loop/switch isn't completed */
_L5:
            readRstStream(handler, k, byte1, l);
            continue; /* Loop/switch isn't completed */
_L6:
            readSettings(handler, k, byte1, l);
            continue; /* Loop/switch isn't completed */
_L7:
            readPushPromise(handler, k, byte1, l);
            continue; /* Loop/switch isn't completed */
_L8:
            readPing(handler, k, byte1, l);
            continue; /* Loop/switch isn't completed */
_L9:
            readGoAway(handler, k, byte1, l);
            continue; /* Loop/switch isn't completed */
_L10:
            readWindowUpdate(handler, k, byte1, l);
            if(true) goto _L12; else goto _L11
_L11:
        }

        public void readConnectionHeader()
            throws IOException
        {
            ByteString bytestring;
            if(!client)
                if(!Http20Draft09.CONNECTION_HEADER.equals(bytestring = source.readByteString(Http20Draft09.CONNECTION_HEADER.size())))
                {
                    Object aobj[] = new Object[1];
                    aobj[0] = bytestring.utf8();
                    throw Http20Draft09.ioException("Expected a connection header but was %s", aobj);
                }
        }

        private final boolean client;
        private final ContinuationSource continuation;
        final HpackDraft05.Reader hpackReader;
        private final BufferedSource source;

        Reader(BufferedSource bufferedsource, int i, boolean flag)
        {
            source = bufferedsource;
            client = flag;
            continuation = new ContinuationSource(source);
            hpackReader = new HpackDraft05.Reader(flag, i, continuation);
        }
    }

    static final class Writer
        implements FrameWriter
    {

        private void headers(boolean flag, int i, int j, List list)
            throws IOException
        {
            if(closed)
                throw new IOException("closed");
            if(hpackBuffer.size() != 0L)
                throw new IllegalStateException();
            hpackWriter.writeHeaders(list);
            int k = (int)hpackBuffer.size();
            byte byte0 = 4;
            if(flag)
                byte0 = 5;
            if(j != -1)
                byte0 |= 8;
            if(j != -1)
                k += 4;
            frameHeader(k, (byte)1, byte0, i);
            if(j != -1)
                sink.writeInt(0x7fffffff & j);
            sink.write(hpackBuffer, hpackBuffer.size());
        }

        public void ackSettings()
            throws IOException
        {
            this;
            JVM INSTR monitorenter ;
            if(closed)
                throw new IOException("closed");
            break MISSING_BLOCK_LABEL_24;
            Exception exception;
            exception;
            this;
            JVM INSTR monitorexit ;
            throw exception;
            frameHeader(0, (byte)4, (byte)1, 0);
            sink.flush();
            this;
            JVM INSTR monitorexit ;
        }

        public void close()
            throws IOException
        {
            this;
            JVM INSTR monitorenter ;
            closed = true;
            sink.close();
            this;
            JVM INSTR monitorexit ;
            return;
            Exception exception;
            exception;
            throw exception;
        }

        public void connectionHeader()
            throws IOException
        {
            this;
            JVM INSTR monitorenter ;
            if(closed)
                throw new IOException("closed");
            break MISSING_BLOCK_LABEL_24;
            Exception exception;
            exception;
            this;
            JVM INSTR monitorexit ;
            throw exception;
            boolean flag = client;
            if(flag)
                break MISSING_BLOCK_LABEL_36;
_L1:
            this;
            JVM INSTR monitorexit ;
            return;
            sink.write(Http20Draft09.CONNECTION_HEADER.toByteArray());
            sink.flush();
              goto _L1
        }

        public void data(boolean flag, int i, OkBuffer okbuffer)
            throws IOException
        {
            this;
            JVM INSTR monitorenter ;
            data(flag, i, okbuffer, (int)okbuffer.size());
            this;
            JVM INSTR monitorexit ;
            return;
            Exception exception;
            exception;
            throw exception;
        }

        public void data(boolean flag, int i, OkBuffer okbuffer, int j)
            throws IOException
        {
            this;
            JVM INSTR monitorenter ;
            if(closed)
                throw new IOException("closed");
            break MISSING_BLOCK_LABEL_26;
            Exception exception;
            exception;
            this;
            JVM INSTR monitorexit ;
            throw exception;
            byte byte0;
            byte0 = 0;
            if(flag)
                byte0 = (byte)1;
            dataFrame(i, byte0, okbuffer, j);
            this;
            JVM INSTR monitorexit ;
        }

        void dataFrame(int i, byte byte0, OkBuffer okbuffer, int j)
            throws IOException
        {
            frameHeader(j, (byte)0, byte0, i);
            if(j > 0)
                sink.write(okbuffer, j);
        }

        public void flush()
            throws IOException
        {
            this;
            JVM INSTR monitorenter ;
            if(closed)
                throw new IOException("closed");
            break MISSING_BLOCK_LABEL_24;
            Exception exception;
            exception;
            this;
            JVM INSTR monitorexit ;
            throw exception;
            sink.flush();
            this;
            JVM INSTR monitorexit ;
        }

        void frameHeader(int i, byte byte0, byte byte1, int j)
            throws IOException
        {
            if(i > 16383)
            {
                Object aobj1[] = new Object[1];
                aobj1[0] = Integer.valueOf(i);
                throw Http20Draft09.illegalArgument("FRAME_SIZE_ERROR length > 16383: %s", aobj1);
            }
            if((0x80000000 & j) != 0)
            {
                Object aobj[] = new Object[1];
                aobj[0] = Integer.valueOf(j);
                throw Http20Draft09.illegalArgument("reserved bit set: %s", aobj);
            } else
            {
                sink.writeInt((i & 0x3fff) << 16 | (byte0 & 0xff) << 8 | byte1 & 0xff);
                sink.writeInt(0x7fffffff & j);
                return;
            }
        }

        public void goAway(int i, ErrorCode errorcode, byte abyte0[])
            throws IOException
        {
            this;
            JVM INSTR monitorenter ;
            if(closed)
                throw new IOException("closed");
            break MISSING_BLOCK_LABEL_26;
            Exception exception;
            exception;
            this;
            JVM INSTR monitorexit ;
            throw exception;
            if(errorcode.httpCode == -1)
                throw Http20Draft09.illegalArgument("errorCode.httpCode == -1", new Object[0]);
            frameHeader(8 + abyte0.length, (byte)7, (byte)0, 0);
            sink.writeInt(i);
            sink.writeInt(errorcode.httpCode);
            if(abyte0.length > 0)
                sink.write(abyte0);
            sink.flush();
            this;
            JVM INSTR monitorexit ;
        }

        public void headers(int i, List list)
            throws IOException
        {
            this;
            JVM INSTR monitorenter ;
            if(closed)
                throw new IOException("closed");
            break MISSING_BLOCK_LABEL_24;
            Exception exception;
            exception;
            this;
            JVM INSTR monitorexit ;
            throw exception;
            headers(false, i, -1, list);
            this;
            JVM INSTR monitorexit ;
        }

        public void ping(boolean flag, int i, int j)
            throws IOException
        {
            this;
            JVM INSTR monitorenter ;
            if(closed)
                throw new IOException("closed");
            break MISSING_BLOCK_LABEL_26;
            Exception exception;
            exception;
            this;
            JVM INSTR monitorexit ;
            throw exception;
            byte byte0;
            if(flag)
                byte0 = 1;
            else
                byte0 = 0;
            frameHeader(8, (byte)6, byte0, 0);
            sink.writeInt(i);
            sink.writeInt(j);
            sink.flush();
            this;
            JVM INSTR monitorexit ;
        }

        public void pushPromise(int i, int j, List list)
            throws IOException
        {
            this;
            JVM INSTR monitorenter ;
            if(closed)
                throw new IOException("closed");
            break MISSING_BLOCK_LABEL_26;
            Exception exception;
            exception;
            this;
            JVM INSTR monitorexit ;
            throw exception;
            if(hpackBuffer.size() != 0L)
                throw new IllegalStateException();
            hpackWriter.writeHeaders(list);
            frameHeader((int)(4L + hpackBuffer.size()), (byte)5, (byte)4, i);
            sink.writeInt(0x7fffffff & j);
            sink.write(hpackBuffer, hpackBuffer.size());
            this;
            JVM INSTR monitorexit ;
        }

        public void rstStream(int i, ErrorCode errorcode)
            throws IOException
        {
            this;
            JVM INSTR monitorenter ;
            if(closed)
                throw new IOException("closed");
            break MISSING_BLOCK_LABEL_24;
            Exception exception;
            exception;
            this;
            JVM INSTR monitorexit ;
            throw exception;
            if(errorcode.spdyRstCode == -1)
                throw new IllegalArgumentException();
            frameHeader(4, (byte)3, (byte)0, i);
            sink.writeInt(errorcode.httpCode);
            sink.flush();
            this;
            JVM INSTR monitorexit ;
        }

        public void settings(Settings settings1)
            throws IOException
        {
            this;
            JVM INSTR monitorenter ;
            if(closed)
                throw new IOException("closed");
            break MISSING_BLOCK_LABEL_24;
            Exception exception;
            exception;
            this;
            JVM INSTR monitorexit ;
            throw exception;
            frameHeader(8 * settings1.size(), (byte)4, (byte)0, 0);
            int i = 0;
_L2:
            if(i >= 10)
                break MISSING_BLOCK_LABEL_89;
            if(!settings1.isSet(i))
                break MISSING_BLOCK_LABEL_101;
            sink.writeInt(0xffffff & i);
            sink.writeInt(settings1.get(i));
            break MISSING_BLOCK_LABEL_101;
            sink.flush();
            this;
            JVM INSTR monitorexit ;
            return;
            i++;
            if(true) goto _L2; else goto _L1
_L1:
        }

        public void synReply(boolean flag, int i, List list)
            throws IOException
        {
            this;
            JVM INSTR monitorenter ;
            if(closed)
                throw new IOException("closed");
            break MISSING_BLOCK_LABEL_26;
            Exception exception;
            exception;
            this;
            JVM INSTR monitorexit ;
            throw exception;
            headers(flag, i, -1, list);
            this;
            JVM INSTR monitorexit ;
        }

        public void synStream(boolean flag, boolean flag1, int i, int j, int k, int l, List list)
            throws IOException
        {
            this;
            JVM INSTR monitorenter ;
            if(!flag1)
                break MISSING_BLOCK_LABEL_21;
            throw new UnsupportedOperationException();
            Exception exception;
            exception;
            this;
            JVM INSTR monitorexit ;
            throw exception;
            if(closed)
                throw new IOException("closed");
            headers(flag, i, k, list);
            this;
            JVM INSTR monitorexit ;
        }

        public void windowUpdate(int i, long l)
            throws IOException
        {
            this;
            JVM INSTR monitorenter ;
            if(closed)
                throw new IOException("closed");
            break MISSING_BLOCK_LABEL_26;
            Exception exception;
            exception;
            this;
            JVM INSTR monitorexit ;
            throw exception;
            if(l != 0L && l <= 0x7fffffffL)
                break MISSING_BLOCK_LABEL_62;
            Object aobj[] = new Object[1];
            aobj[0] = Long.valueOf(l);
            throw Http20Draft09.illegalArgument("windowSizeIncrement == 0 || windowSizeIncrement > 0x7fffffffL: %s", aobj);
            frameHeader(4, (byte)9, (byte)0, i);
            sink.writeInt((int)l);
            sink.flush();
            this;
            JVM INSTR monitorexit ;
        }

        private final boolean client;
        private boolean closed;
        private final OkBuffer hpackBuffer = new OkBuffer();
        private final HpackDraft05.Writer hpackWriter;
        private final BufferedSink sink;

        Writer(BufferedSink bufferedsink, boolean flag)
        {
            sink = bufferedsink;
            client = flag;
            hpackWriter = new HpackDraft05.Writer(hpackBuffer);
        }
    }


    public Http20Draft09()
    {
    }

    private static transient IllegalArgumentException illegalArgument(String s, Object aobj[])
    {
        throw new IllegalArgumentException(String.format(s, aobj));
    }

    private static transient IOException ioException(String s, Object aobj[])
        throws IOException
    {
        throw new IOException(String.format(s, aobj));
    }

    public Protocol getProtocol()
    {
        return Protocol.HTTP_2;
    }

    public int maxFrameSize()
    {
        return 16383;
    }

    public FrameReader newReader(BufferedSource bufferedsource, boolean flag)
    {
        return new Reader(bufferedsource, 4096, flag);
    }

    public FrameWriter newWriter(BufferedSink bufferedsink, boolean flag)
    {
        return new Writer(bufferedsink, flag);
    }

    private static final ByteString CONNECTION_HEADER = ByteString.encodeUtf8("PRI * HTTP/2.0\r\n\r\nSM\r\n\r\n");
    static final byte FLAG_ACK = 1;
    static final byte FLAG_END_HEADERS = 4;
    static final byte FLAG_END_PUSH_PROMISE = 4;
    static final byte FLAG_END_STREAM = 1;
    static final byte FLAG_NONE = 0;
    static final byte FLAG_PRIORITY = 8;
    static final byte TYPE_CONTINUATION = 10;
    static final byte TYPE_DATA = 0;
    static final byte TYPE_GOAWAY = 7;
    static final byte TYPE_HEADERS = 1;
    static final byte TYPE_PING = 6;
    static final byte TYPE_PRIORITY = 2;
    static final byte TYPE_PUSH_PROMISE = 5;
    static final byte TYPE_RST_STREAM = 3;
    static final byte TYPE_SETTINGS = 4;
    static final byte TYPE_WINDOW_UPDATE = 9;




}
