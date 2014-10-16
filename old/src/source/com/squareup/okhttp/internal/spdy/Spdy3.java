// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.squareup.okhttp.internal.spdy;

import com.squareup.okhttp.Protocol;
import com.squareup.okhttp.internal.Util;
import com.squareup.okhttp.internal.okio.*;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.ProtocolException;
import java.nio.charset.Charset;
import java.util.List;
import java.util.zip.Deflater;

// Referenced classes of package com.squareup.okhttp.internal.spdy:
//            Variant, FrameReader, FrameWriter, NameValueBlockReader, 
//            ErrorCode, HeadersMode, Settings, Header

final class Spdy3
    implements Variant
{
    static final class Reader
        implements FrameReader
    {

        private static transient IOException ioException(String s, Object aobj[])
            throws IOException
        {
            throw new IOException(String.format(s, aobj));
        }

        private void readGoAway(FrameReader.Handler handler, int i, int j)
            throws IOException
        {
            if(j != 8)
            {
                Object aobj1[] = new Object[1];
                aobj1[0] = Integer.valueOf(j);
                throw ioException("TYPE_GOAWAY length: %d != 8", aobj1);
            }
            int k = 0x7fffffff & source.readInt();
            int l = source.readInt();
            ErrorCode errorcode = ErrorCode.fromSpdyGoAway(l);
            if(errorcode == null)
            {
                Object aobj[] = new Object[1];
                aobj[0] = Integer.valueOf(l);
                throw ioException("TYPE_GOAWAY unexpected error code: %d", aobj);
            } else
            {
                handler.goAway(k, errorcode, ByteString.EMPTY);
                return;
            }
        }

        private void readHeaders(FrameReader.Handler handler, int i, int j)
            throws IOException
        {
            handler.headers(false, false, 0x7fffffff & source.readInt(), -1, -1, headerBlockReader.readNameValueBlock(j - 4), HeadersMode.SPDY_HEADERS);
        }

        private void readPing(FrameReader.Handler handler, int i, int j)
            throws IOException
        {
            boolean flag = true;
            if(j != 4)
            {
                Object aobj[] = new Object[flag];
                aobj[0] = Integer.valueOf(j);
                throw ioException("TYPE_PING length: %d != 4", aobj);
            }
            int k = source.readInt();
            int l = client;
            int i1;
            if((k & 1) == flag)
                i1 = ((flag) ? 1 : 0);
            else
                i1 = 0;
            if(l != i1)
                flag = false;
            handler.ping(flag, k, 0);
        }

        private void readRstStream(FrameReader.Handler handler, int i, int j)
            throws IOException
        {
            if(j != 8)
            {
                Object aobj1[] = new Object[1];
                aobj1[0] = Integer.valueOf(j);
                throw ioException("TYPE_RST_STREAM length: %d != 8", aobj1);
            }
            int k = 0x7fffffff & source.readInt();
            int l = source.readInt();
            ErrorCode errorcode = ErrorCode.fromSpdy3Rst(l);
            if(errorcode == null)
            {
                Object aobj[] = new Object[1];
                aobj[0] = Integer.valueOf(l);
                throw ioException("TYPE_RST_STREAM unexpected error code: %d", aobj);
            } else
            {
                handler.rstStream(k, errorcode);
                return;
            }
        }

        private void readSettings(FrameReader.Handler handler, int i, int j)
            throws IOException
        {
            boolean flag = true;
            int k = source.readInt();
            if(j != 4 + k * 8)
            {
                Object aobj[] = new Object[2];
                aobj[0] = Integer.valueOf(j);
                aobj[flag] = Integer.valueOf(k);
                throw ioException("TYPE_SETTINGS length: %d != 4 + 8 * %d", aobj);
            }
            Settings settings = new Settings();
            for(int l = 0; l < k; l++)
            {
                int i1 = source.readInt();
                int j1 = source.readInt();
                int k1 = (0xff000000 & i1) >>> 24;
                settings.set(i1 & 0xffffff, k1, j1);
            }

            if((i & 1) == 0)
                flag = false;
            handler.settings(flag, settings);
        }

        private void readSynReply(FrameReader.Handler handler, int i, int j)
            throws IOException
        {
            int k = 0x7fffffff & source.readInt();
            List list = headerBlockReader.readNameValueBlock(j - 4);
            boolean flag;
            if((i & 1) != 0)
                flag = true;
            else
                flag = false;
            handler.headers(false, flag, k, -1, -1, list, HeadersMode.SPDY_REPLY);
        }

        private void readSynStream(FrameReader.Handler handler, int i, int j)
            throws IOException
        {
            boolean flag = true;
            int k = source.readInt();
            int l = source.readInt();
            short word0 = source.readShort();
            int i1 = k & 0x7fffffff;
            int j1 = l & 0x7fffffff;
            int k1 = (0xe000 & word0) >>> 13;
            List list = headerBlockReader.readNameValueBlock(j - 10);
            boolean flag1;
            if((i & 1) != 0)
                flag1 = flag;
            else
                flag1 = false;
            if((i & 2) == 0)
                flag = false;
            handler.headers(flag, flag1, i1, j1, k1, list, HeadersMode.SPDY_SYN_STREAM);
        }

        private void readWindowUpdate(FrameReader.Handler handler, int i, int j)
            throws IOException
        {
            if(j != 8)
            {
                Object aobj1[] = new Object[1];
                aobj1[0] = Integer.valueOf(j);
                throw ioException("TYPE_WINDOW_UPDATE length: %d != 8", aobj1);
            }
            int k = source.readInt();
            int l = source.readInt();
            int i1 = k & 0x7fffffff;
            long l1 = l & 0x7fffffff;
            if(l1 == 0L)
            {
                Object aobj[] = new Object[1];
                aobj[0] = Long.valueOf(l1);
                throw ioException("windowSizeIncrement was 0", aobj);
            } else
            {
                handler.windowUpdate(i1, l1);
                return;
            }
        }

        public void close()
            throws IOException
        {
            headerBlockReader.close();
        }

        public boolean nextFrame(FrameReader.Handler handler)
            throws IOException
        {
            int i;
            int j;
            boolean flag;
            int k;
            int l;
            try
            {
                i = source.readInt();
                j = source.readInt();
            }
            catch(IOException ioexception)
            {
                return false;
            }
            if((0x80000000 & i) != 0)
                flag = true;
            else
                flag = false;
            k = (0xff000000 & j) >>> 24;
            l = j & 0xffffff;
            if(flag)
            {
                int k1 = (0x7fff0000 & i) >>> 16;
                int l1 = i & 0xffff;
                if(k1 != 3)
                    throw new ProtocolException((new StringBuilder()).append("version != 3: ").append(k1).toString());
                switch(l1)
                {
                case 5: // '\005'
                default:
                    source.skip(l);
                    return true;

                case 1: // '\001'
                    readSynStream(handler, k, l);
                    return true;

                case 2: // '\002'
                    readSynReply(handler, k, l);
                    return true;

                case 3: // '\003'
                    readRstStream(handler, k, l);
                    return true;

                case 4: // '\004'
                    readSettings(handler, k, l);
                    return true;

                case 6: // '\006'
                    readPing(handler, k, l);
                    return true;

                case 7: // '\007'
                    readGoAway(handler, k, l);
                    return true;

                case 8: // '\b'
                    readHeaders(handler, k, l);
                    return true;

                case 9: // '\t'
                    readWindowUpdate(handler, k, l);
                    break;
                }
                return true;
            }
            int i1 = i & 0x7fffffff;
            int j1 = k & 1;
            boolean flag1 = false;
            if(j1 != 0)
                flag1 = true;
            handler.data(flag1, i1, source, l);
            return true;
        }

        public void readConnectionHeader()
        {
        }

        private final boolean client;
        private final NameValueBlockReader headerBlockReader;
        private final BufferedSource source;

        Reader(BufferedSource bufferedsource, boolean flag)
        {
            source = bufferedsource;
            headerBlockReader = new NameValueBlockReader(source);
            client = flag;
        }
    }

    static final class Writer
        implements FrameWriter
    {

        private void writeNameValueBlockToBuffer(List list)
            throws IOException
        {
            if(headerBlockBuffer.size() != 0L)
                throw new IllegalStateException();
            headerBlockOut.writeInt(list.size());
            int i = 0;
            for(int j = list.size(); i < j; i++)
            {
                ByteString bytestring = ((Header)list.get(i)).name;
                headerBlockOut.writeInt(bytestring.size());
                headerBlockOut.write(bytestring);
                ByteString bytestring1 = ((Header)list.get(i)).value;
                headerBlockOut.writeInt(bytestring1.size());
                headerBlockOut.write(bytestring1);
            }

            headerBlockOut.flush();
        }

        public void ackSettings()
        {
        }

        public void close()
            throws IOException
        {
            this;
            JVM INSTR monitorenter ;
            closed = true;
            Util.closeAll(sink, headerBlockOut);
            this;
            JVM INSTR monitorexit ;
            return;
            Exception exception;
            exception;
            throw exception;
        }

        public void connectionHeader()
        {
            this;
            JVM INSTR monitorenter ;
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
            int k;
            if(flag)
                k = 1;
            else
                k = 0;
            sendDataFrame(i, k, okbuffer, j);
            this;
            JVM INSTR monitorexit ;
            return;
            Exception exception;
            exception;
            throw exception;
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
            if(errorcode.spdyGoAwayCode == -1)
                throw new IllegalArgumentException("errorCode.spdyGoAwayCode == -1");
            sink.writeInt(0x80030007);
            sink.writeInt(8);
            sink.writeInt(i);
            sink.writeInt(errorcode.spdyGoAwayCode);
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
            writeNameValueBlockToBuffer(list);
            int j = (int)(4L + headerBlockBuffer.size());
            sink.writeInt(0x80030008);
            sink.writeInt(0 | 0xffffff & j);
            sink.writeInt(0x7fffffff & i);
            sink.write(headerBlockBuffer, headerBlockBuffer.size());
            this;
            JVM INSTR monitorexit ;
        }

        public void ping(boolean flag, int i, int j)
            throws IOException
        {
            int k = 1;
            this;
            JVM INSTR monitorenter ;
            if(closed)
                throw new IOException("closed");
            break MISSING_BLOCK_LABEL_29;
            Exception exception;
            exception;
            this;
            JVM INSTR monitorexit ;
            throw exception;
            int l = client;
            int i1;
            if((i & 1) == k)
                i1 = k;
            else
                i1 = 0;
            if(l == i1)
                k = 0;
              goto _L1
_L3:
            if(flag == k)
                break MISSING_BLOCK_LABEL_66;
            throw new IllegalArgumentException("payload != reply");
            sink.writeInt(0x80030006);
            sink.writeInt(4);
            sink.writeInt(i);
            sink.flush();
            this;
            JVM INSTR monitorexit ;
            return;
_L1:
            if(true) goto _L3; else goto _L2
_L2:
        }

        public void pushPromise(int i, int j, List list)
            throws IOException
        {
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
            sink.writeInt(0x80030003);
            sink.writeInt(8);
            sink.writeInt(0x7fffffff & i);
            sink.writeInt(errorcode.spdyRstCode);
            sink.flush();
            this;
            JVM INSTR monitorexit ;
        }

        void sendDataFrame(int i, int j, OkBuffer okbuffer, int k)
            throws IOException
        {
            if(closed)
                throw new IOException("closed");
            if((long)k > 0xffffffL)
                throw new IllegalArgumentException((new StringBuilder()).append("FRAME_TOO_LARGE max size is 16Mib: ").append(k).toString());
            sink.writeInt(0x7fffffff & i);
            sink.writeInt((j & 0xff) << 24 | 0xffffff & k);
            if(k > 0)
                sink.write(okbuffer, k);
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
            int i = settings1.size();
            int j = 4 + i * 8;
            sink.writeInt(0x80030004);
            sink.writeInt(0 | j & 0xffffff);
            sink.writeInt(i);
            int k = 0;
_L2:
            if(k > 10)
                break MISSING_BLOCK_LABEL_151;
            if(!settings1.isSet(k))
                break MISSING_BLOCK_LABEL_163;
            int l = settings1.flags(k);
            sink.writeInt((l & 0xff) << 24 | k & 0xffffff);
            sink.writeInt(settings1.get(k));
            break MISSING_BLOCK_LABEL_163;
            sink.flush();
            this;
            JVM INSTR monitorexit ;
            return;
            k++;
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
            writeNameValueBlockToBuffer(list);
            int j;
            int k;
            if(flag)
                j = 1;
            else
                j = 0;
            k = (int)(4L + headerBlockBuffer.size());
            sink.writeInt(0x80030002);
            sink.writeInt((j & 0xff) << 24 | 0xffffff & k);
            sink.writeInt(0x7fffffff & i);
            sink.write(headerBlockBuffer, headerBlockBuffer.size());
            sink.flush();
            this;
            JVM INSTR monitorexit ;
        }

        public void synStream(boolean flag, boolean flag1, int i, int j, int k, int l, List list)
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
            int i1;
            writeNameValueBlockToBuffer(list);
            i1 = (int)(10L + headerBlockBuffer.size());
            break MISSING_BLOCK_LABEL_46;
_L2:
            j1 = flag2 | byte0;
            sink.writeInt(0x80030001);
            sink.writeInt((j1 & 0xff) << 24 | 0xffffff & i1);
            sink.writeInt(0x7fffffff & i);
            sink.writeInt(0x7fffffff & j);
            sink.writeShort(0 | (k & 7) << 13 | l & 0xff);
            sink.write(headerBlockBuffer, headerBlockBuffer.size());
            sink.flush();
            this;
            JVM INSTR monitorexit ;
            return;
            boolean flag2;
            byte byte0;
            int j1;
            if(flag)
                flag2 = true;
            else
                flag2 = false;
            while(!flag1) 
            {
                byte0 = 0;
                continue; /* Loop/switch isn't completed */
            }
            byte0 = 2;
            if(true) goto _L2; else goto _L1
_L1:
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
                break MISSING_BLOCK_LABEL_67;
            throw new IllegalArgumentException((new StringBuilder()).append("windowSizeIncrement must be between 1 and 0x7fffffff: ").append(l).toString());
            sink.writeInt(0x80030009);
            sink.writeInt(8);
            sink.writeInt(i);
            sink.writeInt((int)l);
            sink.flush();
            this;
            JVM INSTR monitorexit ;
        }

        private final boolean client;
        private boolean closed;
        private final OkBuffer headerBlockBuffer = new OkBuffer();
        private final BufferedSink headerBlockOut;
        private final BufferedSink sink;

        Writer(BufferedSink bufferedsink, boolean flag)
        {
            sink = bufferedsink;
            client = flag;
            Deflater deflater = new Deflater();
            deflater.setDictionary(Spdy3.DICTIONARY);
            headerBlockOut = Okio.buffer(new DeflaterSink(headerBlockBuffer, deflater));
        }
    }


    Spdy3()
    {
    }

    public Protocol getProtocol()
    {
        return Protocol.SPDY_3;
    }

    public int maxFrameSize()
    {
        return 16383;
    }

    public FrameReader newReader(BufferedSource bufferedsource, boolean flag)
    {
        return new Reader(bufferedsource, flag);
    }

    public FrameWriter newWriter(BufferedSink bufferedsink, boolean flag)
    {
        return new Writer(bufferedsink, flag);
    }

    static final byte DICTIONARY[];
    static final int FLAG_FIN = 1;
    static final int FLAG_UNIDIRECTIONAL = 2;
    static final int TYPE_DATA = 0;
    static final int TYPE_GOAWAY = 7;
    static final int TYPE_HEADERS = 8;
    static final int TYPE_PING = 6;
    static final int TYPE_RST_STREAM = 3;
    static final int TYPE_SETTINGS = 4;
    static final int TYPE_SYN_REPLY = 2;
    static final int TYPE_SYN_STREAM = 1;
    static final int TYPE_WINDOW_UPDATE = 9;
    static final int VERSION = 3;

    static 
    {
        try
        {
            DICTIONARY = "\000\000\000\007options\000\000\000\004head\000\000\000\004post\000\000\000\003put\000\000\000\006delete\000\000\000\005trace\000\000\000\006accept\000\000\000\016accept-charset\000\000\000\017accept-encoding\000\000\000\017accept-language\000\000\000\raccept-ranges\000\000\000\003age\000\000\000\005allow\000\000\000\rauthorization\000\000\000\rcache-control\000\000\000\nconnection\000\000\000\fcontent-base\000\000\000\020content-encoding\000\000\000\020content-language\000\000\000\016content-length\000\000\000\020content-location\000\000\000\013content-md5\000\000\000\rcontent-range\000\000\000\fcontent-type\000\000\000\004date\000\000\000\004etag\000\000\000\006expect\000\000\000\007expires\000\000\000\004from\000\000\000\004host\000\000\000\bif-match\000\000\000\021if-modified-since\000\000\000\rif-none-match\000\000\000\bif-range\000\000\000\023if-unmodified-since\000\000\000\rlast-modified\000\000\000\blocation\000\000\000\fmax-forwards\000\000\000\006pragma\000\000\000\022proxy-authenticate\000\000\000\023proxy-authorization\000\000\000\005range\000\000\000\007referer\000\000\000\013retry-after\000\000\000\006server\000\000\000\002te\000\000\000\007trailer\000\000\000\021transfer-encoding\000\000\000\007upgrade\000\000\000\nuser-agent\000\000\000\004vary\000\000\000\003via\000\000\000\007warning\000\000\000\020www-authenticate\000\000\000\006method\000\000\000\003get\000\000\000\006status\000\000\000\006200 OK\000\000\000\007version\000\000\000\bHTTP/1.1\000\000\000\003url\000\000\000\006public\000\000\000\nset-cookie\000\000\000\nkeep-alive\000\000\000\006origin100101201202205206300302303304305306307402405406407408409410411412413414415416417502504505203 Non-Authoritative Information204 No Content301 Moved Permanently400 Bad Request401 Unauthorized403 Forbidden404 Not Found500 Internal Server Error501 Not Implemented503 Service UnavailableJan Feb Mar Apr May Jun Jul Aug Sept Oct Nov Dec 00:00:00 Mon, Tue, Wed, Thu, Fri, Sat, Sun, GMTchunked,text/html,image/png,image/jpg,image/gif,application/xml,application/xhtml+xml,text/plain,text/javascript,publicprivatemax-age=gzip,deflate,sdchcharset=utf-8charset=iso-8859-1,utf-,*,enq=0.".getBytes(Util.UTF_8.name());
        }
        catch(UnsupportedEncodingException unsupportedencodingexception)
        {
            throw new AssertionError();
        }
    }
}
