// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.squareup.okhttp;

import com.squareup.okhttp.internal.DiskLruCache;
import com.squareup.okhttp.internal.Util;
import com.squareup.okhttp.internal.http.Headers;
import com.squareup.okhttp.internal.http.HttpMethod;
import com.squareup.okhttp.internal.http.Request;
import com.squareup.okhttp.internal.http.Response;
import com.squareup.okhttp.internal.okio.BufferedSource;
import com.squareup.okhttp.internal.okio.ByteString;
import com.squareup.okhttp.internal.okio.Okio;
import java.io.*;
import java.net.*;
import java.security.cert.*;
import java.util.*;

// Referenced classes of package com.squareup.okhttp:
//            OkResponseCache, ResponseSource, MediaType, Handshake

public final class HttpResponseCache extends ResponseCache
    implements OkResponseCache
{
    private final class CacheRequestImpl extends CacheRequest
    {

        public void abort()
        {
label0:
            {
                synchronized(HttpResponseCache.this)
                {
                    if(!done)
                        break label0;
                }
                return;
            }
            done = true;
            int i = ((access._cls308) (this)).access$308;
            httpresponsecache;
            JVM INSTR monitorexit ;
            Util.closeQuietly(cacheOut);
            try
            {
                editor.abort();
                return;
            }
            catch(IOException ioexception)
            {
                return;
            }
            exception;
            httpresponsecache;
            JVM INSTR monitorexit ;
            throw exception;
        }

        public OutputStream getBody()
            throws IOException
        {
            return body;
        }

        private OutputStream body;
        private OutputStream cacheOut;
        private boolean done;
        private final com.squareup.okhttp.internal.DiskLruCache.Editor editor;
        final HttpResponseCache this$0;



/*
        static boolean access$102(CacheRequestImpl cacherequestimpl, boolean flag)
        {
            cacherequestimpl.done = flag;
            return flag;
        }

*/

        public CacheRequestImpl(com.squareup.okhttp.internal.DiskLruCache.Editor editor1)
            throws IOException
        {
            this$0 = HttpResponseCache.this;
            super();
            editor = editor1;
            cacheOut = editor1.newOutputStream(1);
            body = new _cls1(editor1);
        }
    }

    private static class CacheResponseBody extends com.squareup.okhttp.internal.http.Response.Body
    {

        public InputStream byteStream()
        {
            return bodyIn;
        }

        public long contentLength()
        {
label0:
            {
                long l = -1L;
                long l1;
                try
                {
                    if(contentLength == null)
                        break label0;
                    l1 = Long.parseLong(contentLength);
                }
                catch(NumberFormatException numberformatexception)
                {
                    return l;
                }
                l = l1;
            }
            return l;
        }

        public MediaType contentType()
        {
            if(contentType != null)
                return MediaType.parse(contentType);
            else
                return null;
        }

        public boolean ready()
            throws IOException
        {
            return true;
        }

        private final InputStream bodyIn;
        private final String contentLength;
        private final String contentType;
        private final com.squareup.okhttp.internal.DiskLruCache.Snapshot snapshot;


        public CacheResponseBody(com.squareup.okhttp.internal.DiskLruCache.Snapshot snapshot1, String s, String s1)
        {
            snapshot = snapshot1;
            contentType = s;
            contentLength = s1;
            bodyIn = snapshot1.getInputStream(1). new _cls1(snapshot1);
        }
    }

    private static final class Entry
    {

        private boolean isHttps()
        {
            return url.startsWith("https://");
        }

        private List readCertificateList(BufferedSource bufferedsource)
            throws IOException
        {
            int i = HttpResponseCache.readInt(bufferedsource);
            if(i != -1) goto _L2; else goto _L1
_L1:
            Object obj = Collections.emptyList();
_L6:
            return ((List) (obj));
_L2:
            CertificateFactory certificatefactory;
            int j;
            try
            {
                certificatefactory = CertificateFactory.getInstance("X.509");
                obj = new ArrayList(i);
            }
            catch(CertificateException certificateexception)
            {
                throw new IOException(certificateexception.getMessage());
            }
            j = 0;
            if(j >= i)
                continue; /* Loop/switch isn't completed */
            ((List) (obj)).add(certificatefactory.generateCertificate(new ByteArrayInputStream(ByteString.decodeBase64(bufferedsource.readUtf8Line(true)).toByteArray())));
            j++;
            if(true) goto _L4; else goto _L3
_L3:
            break MISSING_BLOCK_LABEL_79;
_L4:
            break MISSING_BLOCK_LABEL_38;
            if(true) goto _L6; else goto _L5
_L5:
        }

        private void writeCertArray(Writer writer, List list)
            throws IOException
        {
            int i;
            int j;
            String s;
            try
            {
                writer.write((new StringBuilder()).append(Integer.toString(list.size())).append('\n').toString());
            }
            catch(CertificateEncodingException certificateencodingexception)
            {
                throw new IOException(certificateencodingexception.getMessage());
            }
            i = 0;
            j = list.size();
_L2:
            if(i >= j)
                break; /* Loop/switch isn't completed */
            s = ByteString.of(((Certificate)list.get(i)).getEncoded()).base64();
            writer.write((new StringBuilder()).append(s).append('\n').toString());
            i++;
            if(true) goto _L2; else goto _L1
_L1:
        }

        public boolean matches(Request request, Response response1)
        {
            return url.equals(request.urlString()) && requestMethod.equals(request.method()) && response1.varyMatches(varyHeaders, request);
        }

        public Response response(Request request, com.squareup.okhttp.internal.DiskLruCache.Snapshot snapshot)
        {
            String s = responseHeaders.get("Content-Type");
            String s1 = responseHeaders.get("Content-Length");
            return (new com.squareup.okhttp.internal.http.Response.Builder()).request(request).statusLine(statusLine).headers(responseHeaders).body(new CacheResponseBody(snapshot, s, s1)).handshake(handshake).build();
        }

        public void writeTo(com.squareup.okhttp.internal.DiskLruCache.Editor editor)
            throws IOException
        {
            BufferedWriter bufferedwriter = new BufferedWriter(new OutputStreamWriter(editor.newOutputStream(0), Util.UTF_8));
            bufferedwriter.write((new StringBuilder()).append(url).append('\n').toString());
            bufferedwriter.write((new StringBuilder()).append(requestMethod).append('\n').toString());
            bufferedwriter.write((new StringBuilder()).append(Integer.toString(varyHeaders.size())).append('\n').toString());
            for(int i = 0; i < varyHeaders.size(); i++)
                bufferedwriter.write((new StringBuilder()).append(varyHeaders.name(i)).append(": ").append(varyHeaders.value(i)).append('\n').toString());

            bufferedwriter.write((new StringBuilder()).append(statusLine).append('\n').toString());
            bufferedwriter.write((new StringBuilder()).append(Integer.toString(responseHeaders.size())).append('\n').toString());
            for(int j = 0; j < responseHeaders.size(); j++)
                bufferedwriter.write((new StringBuilder()).append(responseHeaders.name(j)).append(": ").append(responseHeaders.value(j)).append('\n').toString());

            if(isHttps())
            {
                bufferedwriter.write(10);
                bufferedwriter.write((new StringBuilder()).append(handshake.cipherSuite()).append('\n').toString());
                writeCertArray(bufferedwriter, handshake.peerCertificates());
                writeCertArray(bufferedwriter, handshake.localCertificates());
            }
            bufferedwriter.close();
        }

        private final Handshake handshake;
        private final String requestMethod;
        private final Headers responseHeaders;
        private final String statusLine;
        private final String url;
        private final Headers varyHeaders;

        public Entry(Response response1)
        {
            url = response1.request().urlString();
            varyHeaders = response1.request().headers().getAll(response1.getVaryFields());
            requestMethod = response1.request().method();
            statusLine = response1.statusLine();
            responseHeaders = response1.headers();
            handshake = response1.handshake();
        }

        public Entry(InputStream inputstream)
            throws IOException
        {
            BufferedSource bufferedsource;
            com.squareup.okhttp.internal.http.Headers.Builder builder;
            int i;
            bufferedsource = Okio.buffer(Okio.source(inputstream));
            url = bufferedsource.readUtf8Line(true);
            requestMethod = bufferedsource.readUtf8Line(true);
            builder = new com.squareup.okhttp.internal.http.Headers.Builder();
            i = HttpResponseCache.readInt(bufferedsource);
            int j = 0;
_L2:
            if(j >= i)
                break; /* Loop/switch isn't completed */
            builder.addLine(bufferedsource.readUtf8Line(true));
            j++;
            if(true) goto _L2; else goto _L1
_L1:
            com.squareup.okhttp.internal.http.Headers.Builder builder1;
            int k;
            varyHeaders = builder.build();
            statusLine = bufferedsource.readUtf8Line(true);
            builder1 = new com.squareup.okhttp.internal.http.Headers.Builder();
            k = HttpResponseCache.readInt(bufferedsource);
            int l = 0;
_L4:
            if(l >= k)
                break; /* Loop/switch isn't completed */
            builder1.addLine(bufferedsource.readUtf8Line(true));
            l++;
            if(true) goto _L4; else goto _L3
_L3:
            responseHeaders = builder1.build();
            if(!isHttps())
                break MISSING_BLOCK_LABEL_244;
            String s = bufferedsource.readUtf8Line(true);
            if(s.length() > 0)
                throw new IOException((new StringBuilder()).append("expected \"\" but was \"").append(s).append("\"").toString());
            break MISSING_BLOCK_LABEL_215;
            Exception exception;
            exception;
            inputstream.close();
            throw exception;
            handshake = Handshake.get(bufferedsource.readUtf8Line(true), readCertificateList(bufferedsource), readCertificateList(bufferedsource));
_L5:
            inputstream.close();
            return;
            handshake = null;
              goto _L5
        }
    }


    public HttpResponseCache(File file, long l)
        throws IOException
    {
        cache = DiskLruCache.open(file, 0x31191, 2, l);
    }

    private void abortQuietly(com.squareup.okhttp.internal.DiskLruCache.Editor editor)
    {
        if(editor == null)
            break MISSING_BLOCK_LABEL_8;
        editor.abort();
        return;
        IOException ioexception;
        ioexception;
    }

    private static int readInt(BufferedSource bufferedsource)
        throws IOException
    {
        String s = bufferedsource.readUtf8Line(true);
        int i;
        try
        {
            i = Integer.parseInt(s);
        }
        catch(NumberFormatException numberformatexception)
        {
            throw new IOException((new StringBuilder()).append("Expected an integer but was \"").append(s).append("\"").toString());
        }
        return i;
    }

    private static String urlToKey(Request request)
    {
        return Util.hash(request.urlString());
    }

    public void close()
        throws IOException
    {
        cache.close();
    }

    public void delete()
        throws IOException
    {
        cache.delete();
    }

    public void flush()
        throws IOException
    {
        cache.flush();
    }

    public Response get(Request request)
    {
        String s = urlToKey(request);
        com.squareup.okhttp.internal.DiskLruCache.Snapshot snapshot = cache.get(s);
        if(snapshot == null)
            return null;
        Entry entry = new Entry(snapshot.getInputStream(0));
        Response response;
        response = entry.response(request, snapshot);
        if(!entry.matches(request, response))
        {
            Util.closeQuietly(response.body());
            return null;
        }
        break MISSING_BLOCK_LABEL_72;
        IOException ioexception;
        ioexception;
        response = null;
        return response;
    }

    public CacheResponse get(URI uri, String s, Map map)
        throws IOException
    {
        throw new UnsupportedOperationException("This is not a general purpose response cache.");
    }

    public File getDirectory()
    {
        return cache.getDirectory();
    }

    public int getHitCount()
    {
        this;
        JVM INSTR monitorenter ;
        int i = hitCount;
        this;
        JVM INSTR monitorexit ;
        return i;
        Exception exception;
        exception;
        throw exception;
    }

    public long getMaxSize()
    {
        return cache.getMaxSize();
    }

    public int getNetworkCount()
    {
        this;
        JVM INSTR monitorenter ;
        int i = networkCount;
        this;
        JVM INSTR monitorexit ;
        return i;
        Exception exception;
        exception;
        throw exception;
    }

    public int getRequestCount()
    {
        this;
        JVM INSTR monitorenter ;
        int i = requestCount;
        this;
        JVM INSTR monitorexit ;
        return i;
        Exception exception;
        exception;
        throw exception;
    }

    public long getSize()
    {
        return cache.size();
    }

    public int getWriteAbortCount()
    {
        this;
        JVM INSTR monitorenter ;
        int i = writeAbortCount;
        this;
        JVM INSTR monitorexit ;
        return i;
        Exception exception;
        exception;
        throw exception;
    }

    public int getWriteSuccessCount()
    {
        this;
        JVM INSTR monitorenter ;
        int i = writeSuccessCount;
        this;
        JVM INSTR monitorexit ;
        return i;
        Exception exception;
        exception;
        throw exception;
    }

    public boolean isClosed()
    {
        return cache.isClosed();
    }

    public boolean maybeRemove(Request request)
    {
        if(HttpMethod.invalidatesCache(request.method()))
        {
            try
            {
                cache.remove(urlToKey(request));
            }
            catch(IOException ioexception) { }
            return true;
        } else
        {
            return false;
        }
    }

    public CacheRequest put(Response response)
        throws IOException
    {
        String s;
        s = response.request().method();
        break MISSING_BLOCK_LABEL_8;
        while(editor == null) 
        {
            do
                return null;
            while(maybeRemove(response.request()) || !s.equals("GET") || response.hasVaryAll());
            Entry entry = new Entry(response);
            com.squareup.okhttp.internal.DiskLruCache.Editor editor = null;
            CacheRequestImpl cacherequestimpl;
            try
            {
                editor = cache.edit(urlToKey(response.request()));
            }
            catch(IOException ioexception)
            {
                abortQuietly(editor);
                return null;
            }
        }
        entry.writeTo(editor);
        cacherequestimpl = new CacheRequestImpl(editor);
        return cacherequestimpl;
    }

    public CacheRequest put(URI uri, URLConnection urlconnection)
        throws IOException
    {
        throw new UnsupportedOperationException("This is not a general purpose response cache.");
    }

    public void trackConditionalCacheHit()
    {
        this;
        JVM INSTR monitorenter ;
        hitCount = 1 + hitCount;
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public void trackResponse(ResponseSource responsesource)
    {
        this;
        JVM INSTR monitorenter ;
        int i;
        requestCount = 1 + requestCount;
        static class _cls1
        {

            static final int $SwitchMap$com$squareup$okhttp$ResponseSource[];

            static 
            {
                $SwitchMap$com$squareup$okhttp$ResponseSource = new int[ResponseSource.values().length];
                try
                {
                    $SwitchMap$com$squareup$okhttp$ResponseSource[ResponseSource.CACHE.ordinal()] = 1;
                }
                catch(NoSuchFieldError nosuchfielderror) { }
                try
                {
                    $SwitchMap$com$squareup$okhttp$ResponseSource[ResponseSource.CONDITIONAL_CACHE.ordinal()] = 2;
                }
                catch(NoSuchFieldError nosuchfielderror1) { }
                try
                {
                    $SwitchMap$com$squareup$okhttp$ResponseSource[ResponseSource.NETWORK.ordinal()] = 3;
                }
                catch(NoSuchFieldError nosuchfielderror2)
                {
                    return;
                }
            }
        }

        i = _cls1..SwitchMap.com.squareup.okhttp.ResponseSource[responsesource.ordinal()];
        i;
        JVM INSTR tableswitch 1 3: default 48
    //                   1 51
    //                   2 69
    //                   3 69;
           goto _L1 _L2 _L3 _L3
_L1:
        this;
        JVM INSTR monitorexit ;
        return;
_L2:
        hitCount = 1 + hitCount;
          goto _L1
        Exception exception;
        exception;
        throw exception;
_L3:
        networkCount = 1 + networkCount;
          goto _L1
    }

    public void update(Response response, Response response1)
    {
        Entry entry = new Entry(response1);
        com.squareup.okhttp.internal.DiskLruCache.Snapshot snapshot = ((CacheResponseBody)response.body()).snapshot;
        com.squareup.okhttp.internal.DiskLruCache.Editor editor = null;
        try
        {
            editor = snapshot.edit();
        }
        catch(IOException ioexception)
        {
            abortQuietly(editor);
            return;
        }
        if(editor == null)
            break MISSING_BLOCK_LABEL_47;
        entry.writeTo(editor);
        editor.commit();
    }

    private static final int ENTRY_BODY = 1;
    private static final int ENTRY_COUNT = 2;
    private static final int ENTRY_METADATA = 0;
    private static final int VERSION = 0x31191;
    private final DiskLruCache cache;
    private int hitCount;
    private int networkCount;
    private int requestCount;
    private int writeAbortCount;
    private int writeSuccessCount;


/*
    static int access$208(HttpResponseCache httpresponsecache)
    {
        int i = httpresponsecache.writeSuccessCount;
        httpresponsecache.writeSuccessCount = i + 1;
        return i;
    }

*/


/*
    static int access$308(HttpResponseCache httpresponsecache)
    {
        int i = httpresponsecache.writeAbortCount;
        httpresponsecache.writeAbortCount = i + 1;
        return i;
    }

*/


    // Unreferenced inner class com/squareup/okhttp/HttpResponseCache$CacheRequestImpl$1

/* anonymous class */
    class CacheRequestImpl._cls1 extends FilterOutputStream
    {

        public void close()
            throws IOException
        {
label0:
            {
                synchronized(_fld0)
                {
                    if(!done)
                        break label0;
                }
                return;
            }
            done = true;
            int i = ((FilterOutputStream) (this)).close;
            httpresponsecache;
            JVM INSTR monitorexit ;
            super.close();
            editor.commit();
            return;
            exception;
            httpresponsecache;
            JVM INSTR monitorexit ;
            throw exception;
        }

        public void write(byte abyte0[], int i, int j)
            throws IOException
        {
            out.write(abyte0, i, j);
        }

        final CacheRequestImpl this$1;
        final com.squareup.okhttp.internal.DiskLruCache.Editor val$editor;
        final HttpResponseCache val$this$0;

            
            {
                this$1 = final_cacherequestimpl;
                this$0 = HttpResponseCache.this;
                editor = editor1;
                super(final_outputstream);
            }
    }


    // Unreferenced inner class com/squareup/okhttp/HttpResponseCache$CacheResponseBody$1

/* anonymous class */
    class CacheResponseBody._cls1 extends FilterInputStream
    {

        public void close()
            throws IOException
        {
            snapshot.close();
            super.close();
        }

        final CacheResponseBody this$0;
        final com.squareup.okhttp.internal.DiskLruCache.Snapshot val$snapshot;

            
            {
                this$0 = final_cacheresponsebody;
                snapshot = snapshot1;
                super(InputStream.this);
            }
    }

}
