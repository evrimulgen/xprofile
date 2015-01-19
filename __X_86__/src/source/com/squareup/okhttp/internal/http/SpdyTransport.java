// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.squareup.okhttp.internal.http;

import com.squareup.okhttp.*;
import com.squareup.okhttp.internal.Util;
import com.squareup.okhttp.internal.okio.*;
import com.squareup.okhttp.internal.spdy.*;
import java.io.IOException;
import java.io.OutputStream;
import java.net.*;
import java.util.*;

// Referenced classes of package com.squareup.okhttp.internal.http:
//            Transport, OkHeaders, StatusLine, Request, 
//            Headers, RequestLine, HttpEngine, RetryableSink

public final class SpdyTransport
    implements Transport
{
    private static class SpdySource
        implements Source
    {

        private boolean discardStream()
        {
            long l;
            Exception exception;
            try
            {
                l = stream.getReadTimeoutMillis();
                stream.setReadTimeout(l);
                stream.setReadTimeout(100L);
            }
            catch(IOException ioexception)
            {
                return false;
            }
            Util.skipAll(this, 100);
            stream.setReadTimeout(l);
            return true;
            exception;
            stream.setReadTimeout(l);
            throw exception;
        }

        public void close()
            throws IOException
        {
            if(!closed)
            {
                if(!inputExhausted && cacheBody != null)
                    discardStream();
                closed = true;
                if(!inputExhausted)
                {
                    stream.closeLater(ErrorCode.CANCEL);
                    if(cacheRequest != null)
                    {
                        cacheRequest.abort();
                        return;
                    }
                }
            }
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
            if(inputExhausted)
            {
                l1 = -1L;
            } else
            {
                l1 = source.read(okbuffer, l);
                if(l1 == -1L)
                {
                    inputExhausted = true;
                    if(cacheRequest != null)
                        cacheBody.close();
                    return -1L;
                }
                if(cacheBody != null)
                {
                    Okio.copy(okbuffer, okbuffer.size() - l1, l1, cacheBody);
                    return l1;
                }
            }
            return l1;
        }

        private final OutputStream cacheBody;
        private final CacheRequest cacheRequest;
        private boolean closed;
        private boolean inputExhausted;
        private final Source source;
        private final SpdyStream stream;

        SpdySource(SpdyStream spdystream, CacheRequest cacherequest)
            throws IOException
        {
            stream = spdystream;
            source = spdystream.getSource();
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


    public SpdyTransport(HttpEngine httpengine, SpdyConnection spdyconnection)
    {
        httpEngine = httpengine;
        spdyConnection = spdyconnection;
    }

    private static boolean isProhibitedHeader(Protocol protocol, ByteString bytestring)
    {
        if(protocol != Protocol.SPDY_3) goto _L2; else goto _L1
_L1:
        boolean flag1;
label0:
        {
            if(!bytestring.equalsAscii("connection") && !bytestring.equalsAscii("host") && !bytestring.equalsAscii("keep-alive") && !bytestring.equalsAscii("proxy-connection"))
            {
                boolean flag2 = bytestring.equalsAscii("transfer-encoding");
                flag1 = false;
                if(!flag2)
                    break label0;
            }
            flag1 = true;
        }
_L4:
        return flag1;
_L2:
        boolean flag;
        if(protocol != Protocol.HTTP_2)
            break MISSING_BLOCK_LABEL_147;
        if(bytestring.equalsAscii("connection") || bytestring.equalsAscii("host") || bytestring.equalsAscii("keep-alive") || bytestring.equalsAscii("proxy-connection") || bytestring.equalsAscii("te") || bytestring.equalsAscii("transfer-encoding") || bytestring.equalsAscii("encoding"))
            break; /* Loop/switch isn't completed */
        flag = bytestring.equalsAscii("upgrade");
        flag1 = false;
        if(!flag) goto _L4; else goto _L3
_L3:
        return true;
        throw new AssertionError(protocol);
    }

    private static String joinOnNull(String s, String s1)
    {
        return (new StringBuilder(s)).append('\0').append(s1).toString();
    }

    public static Response.Builder readNameValueBlock(List list, Protocol protocol)
        throws IOException
    {
        String s = null;
        String s1 = "HTTP/1.1";
        Headers.Builder builder = new Headers.Builder();
        builder.set(OkHeaders.SELECTED_PROTOCOL, protocol.name.utf8());
        int i = 0;
        do
        {
            if(i >= list.size())
                break;
            ByteString bytestring = ((Header)list.get(i)).name;
            String s2 = ((Header)list.get(i)).value.utf8();
            int j = 0;
            while(j < s2.length()) 
            {
                int k = s2.indexOf('\0', j);
                if(k == -1)
                    k = s2.length();
                String s3 = s2.substring(j, k);
                if(bytestring.equals(Header.RESPONSE_STATUS))
                    s = s3;
                else
                if(bytestring.equals(Header.VERSION))
                    s1 = s3;
                else
                if(!isProhibitedHeader(protocol, bytestring))
                    builder.add(bytestring.utf8(), s3);
                j = k + 1;
            }
            i++;
        } while(true);
        if(s == null)
            throw new ProtocolException("Expected ':status' header not present");
        if(s1 == null)
            throw new ProtocolException("Expected ':version' header not present");
        else
            return (new Response.Builder()).statusLine(new StatusLine((new StringBuilder()).append(s1).append(" ").append(s).toString())).headers(builder.build());
    }

    public static List writeNameValueBlock(Request request, Protocol protocol, String s)
    {
        ArrayList arraylist;
        LinkedHashSet linkedhashset;
        ByteString bytestring;
        String s2;
        Headers headers = request.headers();
        arraylist = new ArrayList(10 + headers.size());
        arraylist.add(new Header(Header.TARGET_METHOD, request.method()));
        arraylist.add(new Header(Header.TARGET_PATH, RequestLine.requestPath(request.url())));
        String s1 = HttpEngine.hostHeader(request.url());
        int i;
        if(Protocol.SPDY_3 == protocol)
        {
            arraylist.add(new Header(Header.VERSION, s));
            arraylist.add(new Header(Header.TARGET_HOST, s1));
        } else
        if(Protocol.HTTP_2 == protocol)
            arraylist.add(new Header(Header.TARGET_AUTHORITY, s1));
        else
            throw new AssertionError();
        arraylist.add(new Header(Header.TARGET_SCHEME, request.url().getProtocol()));
        linkedhashset = new LinkedHashSet();
        i = 0;
        break MISSING_BLOCK_LABEL_160;
_L5:
        i++;
        if(true) goto _L2; else goto _L1
_L2:
        break MISSING_BLOCK_LABEL_160;
        if(i >= headers.size())
            break; /* Loop/switch isn't completed */
        bytestring = ByteString.encodeUtf8(headers.name(i).toLowerCase(Locale.US));
        s2 = headers.value(i);
        if(!isProhibitedHeader(protocol, bytestring) && !bytestring.equals(Header.TARGET_METHOD) && !bytestring.equals(Header.TARGET_PATH) && !bytestring.equals(Header.TARGET_SCHEME) && !bytestring.equals(Header.TARGET_AUTHORITY) && !bytestring.equals(Header.TARGET_HOST) && !bytestring.equals(Header.VERSION))
        {
label0:
            {
                if(!linkedhashset.add(bytestring))
                    break label0;
                arraylist.add(new Header(bytestring, s2));
            }
        }
        break MISSING_BLOCK_LABEL_203;
        int j = 0;
_L3:
        if(j < arraylist.size())
        {
label1:
            {
                if(!((Header)arraylist.get(j)).name.equals(bytestring))
                    break label1;
                arraylist.set(j, new Header(bytestring, joinOnNull(((Header)arraylist.get(j)).value.utf8(), s2)));
            }
        }
        continue; /* Loop/switch isn't completed */
        j++;
        if(true) goto _L3; else goto _L1
_L1:
        return arraylist;
        if(true) goto _L5; else goto _L4
_L4:
    }

    public boolean canReuseConnection()
    {
        return true;
    }

    public Sink createRequestBody(Request request)
        throws IOException
    {
        writeRequestHeaders(request);
        return stream.getSink();
    }

    public void disconnect(HttpEngine httpengine)
        throws IOException
    {
        stream.close(ErrorCode.CANCEL);
    }

    public void emptyTransferStream()
    {
    }

    public void flushRequest()
        throws IOException
    {
        stream.getSink().close();
    }

    public Source getTransferStream(CacheRequest cacherequest)
        throws IOException
    {
        return new SpdySource(stream, cacherequest);
    }

    public Response.Builder readResponseHeaders()
        throws IOException
    {
        return readNameValueBlock(stream.getResponseHeaders(), spdyConnection.getProtocol());
    }

    public void releaseConnectionOnIdle()
    {
    }

    public void writeRequestBody(RetryableSink retryablesink)
        throws IOException
    {
        throw new UnsupportedOperationException();
    }

    public void writeRequestHeaders(Request request)
        throws IOException
    {
        if(stream != null)
        {
            return;
        } else
        {
            httpEngine.writingRequestHeaders();
            boolean flag = httpEngine.hasRequestBody();
            String s = RequestLine.version(httpEngine.getConnection().getHttpMinorVersion());
            stream = spdyConnection.newStream(writeNameValueBlock(request, spdyConnection.getProtocol(), s), flag, true);
            stream.setReadTimeout(httpEngine.client.getReadTimeout());
            return;
        }
    }

    private final HttpEngine httpEngine;
    private final SpdyConnection spdyConnection;
    private SpdyStream stream;
}
