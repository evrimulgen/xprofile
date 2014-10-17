// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.squareup.okhttp.internal.http;

import com.squareup.okhttp.*;
import com.squareup.okhttp.internal.Util;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Date;
import java.util.concurrent.TimeUnit;

// Referenced classes of package com.squareup.okhttp.internal.http:
//            StatusLine, Response, Request, Headers, 
//            HttpDate, HeaderParser, OkHeaders

public final class CacheStrategy
{
    public static class Factory
    {

        private long cacheResponseAge()
        {
            long l = 0L;
            if(servedDate != null)
                l = Math.max(l, receivedResponseMillis - servedDate.getTime());
            long l1;
            long l2;
            if(ageSeconds != -1)
                l1 = Math.max(l, TimeUnit.SECONDS.toMillis(ageSeconds));
            else
                l1 = l;
            l2 = receivedResponseMillis - sentRequestMillis;
            return (nowMillis - receivedResponseMillis) + (l1 + l2);
        }

        private long computeFreshnessLifetime()
        {
            long l = 0L;
            CacheControl cachecontrol = cacheResponse.cacheControl();
            if(cachecontrol.maxAgeSeconds() != -1)
            {
                l = TimeUnit.SECONDS.toMillis(cachecontrol.maxAgeSeconds());
            } else
            {
                if(expires != null)
                {
                    long l3;
                    long l4;
                    if(servedDate != null)
                        l3 = servedDate.getTime();
                    else
                        l3 = receivedResponseMillis;
                    l4 = expires.getTime() - l3;
                    if(l4 <= l)
                        l4 = l;
                    return l4;
                }
                if(lastModified != null && cacheResponse.request().url().getQuery() == null)
                {
                    long l1;
                    long l2;
                    if(servedDate != null)
                        l1 = servedDate.getTime();
                    else
                        l1 = sentRequestMillis;
                    l2 = l1 - lastModified.getTime();
                    if(l2 > l)
                        return l2 / 10L;
                }
            }
            return l;
        }

        private CacheStrategy getCandidate()
        {
            if(cacheResponse == null)
                return new CacheStrategy(request, cacheResponse, ResponseSource.NETWORK);
            if(request.isHttps() && cacheResponse.handshake() == null)
                return new CacheStrategy(request, cacheResponse, ResponseSource.NETWORK);
            if(!CacheStrategy.isCacheable(cacheResponse, request))
                return new CacheStrategy(request, cacheResponse, ResponseSource.NETWORK);
            CacheControl cachecontrol = request.cacheControl();
            if(cachecontrol.noCache() || hasConditions(request))
                return new CacheStrategy(request, cacheResponse, ResponseSource.NETWORK);
            long l = cacheResponseAge();
            long l1 = computeFreshnessLifetime();
            if(cachecontrol.maxAgeSeconds() != -1)
                l1 = Math.min(l1, TimeUnit.SECONDS.toMillis(cachecontrol.maxAgeSeconds()));
            long l2 = 0L;
            if(cachecontrol.minFreshSeconds() != -1)
                l2 = TimeUnit.SECONDS.toMillis(cachecontrol.minFreshSeconds());
            long l3 = 0L;
            CacheControl cachecontrol1 = cacheResponse.cacheControl();
            if(!cachecontrol1.mustRevalidate() && cachecontrol.maxStaleSeconds() != -1)
                l3 = TimeUnit.SECONDS.toMillis(cachecontrol.maxStaleSeconds());
            if(!cachecontrol1.noCache() && l + l2 < l1 + l3)
            {
                Response.Builder builder1 = cacheResponse.newBuilder().setResponseSource(ResponseSource.CACHE);
                if(l + l2 >= l1)
                    builder1.addHeader("Warning", "110 HttpURLConnection \"Response is stale\"");
                if(l > 0x5265c00L && isFreshnessLifetimeHeuristic())
                    builder1.addHeader("Warning", "113 HttpURLConnection \"Heuristic expiration\"");
                return new CacheStrategy(request, builder1.build(), ResponseSource.CACHE);
            }
            Request.Builder builder = request.newBuilder();
            Request request1;
            ResponseSource responsesource;
            CacheStrategy cachestrategy;
            if(lastModified != null)
                builder.header("If-Modified-Since", lastModifiedString);
            else
            if(servedDate != null)
                builder.header("If-Modified-Since", servedDateString);
            if(etag != null)
                builder.header("If-None-Match", etag);
            request1 = builder.build();
            if(hasConditions(request1))
                responsesource = ResponseSource.CONDITIONAL_CACHE;
            else
                responsesource = ResponseSource.NETWORK;
            cachestrategy = new CacheStrategy(request1, cacheResponse, responsesource);
            return cachestrategy;
        }

        private static boolean hasConditions(Request request1)
        {
            return request1.header("If-Modified-Since") != null || request1.header("If-None-Match") != null;
        }

        private boolean isFreshnessLifetimeHeuristic()
        {
            return cacheResponse.cacheControl().maxAgeSeconds() == -1 && expires == null;
        }

        public CacheStrategy get()
        {
            CacheStrategy cachestrategy = getCandidate();
            if(cachestrategy.source != ResponseSource.CACHE && request.cacheControl().onlyIfCached())
            {
                Response response1 = (new Response.Builder()).request(cachestrategy.request).statusLine(CacheStrategy.GATEWAY_TIMEOUT_STATUS_LINE).setResponseSource(ResponseSource.NONE).body(CacheStrategy.EMPTY_BODY).build();
                cachestrategy = new CacheStrategy(cachestrategy.request, response1, ResponseSource.NONE);
            }
            return cachestrategy;
        }

        private int ageSeconds;
        final Response cacheResponse;
        private String etag;
        private Date expires;
        private Date lastModified;
        private String lastModifiedString;
        final long nowMillis;
        private long receivedResponseMillis;
        final Request request;
        private long sentRequestMillis;
        private Date servedDate;
        private String servedDateString;

        public Factory(long l, Request request1, Response response1)
        {
            ageSeconds = -1;
            nowMillis = l;
            request = request1;
            cacheResponse = response1;
            if(response1 != null)
            {
                int i = 0;
                while(i < response1.headers().size()) 
                {
                    String s = response1.headers().name(i);
                    String s1 = response1.headers().value(i);
                    if("Date".equalsIgnoreCase(s))
                    {
                        servedDate = HttpDate.parse(s1);
                        servedDateString = s1;
                    } else
                    if("Expires".equalsIgnoreCase(s))
                        expires = HttpDate.parse(s1);
                    else
                    if("Last-Modified".equalsIgnoreCase(s))
                    {
                        lastModified = HttpDate.parse(s1);
                        lastModifiedString = s1;
                    } else
                    if("ETag".equalsIgnoreCase(s))
                        etag = s1;
                    else
                    if("Age".equalsIgnoreCase(s))
                        ageSeconds = HeaderParser.parseSeconds(s1);
                    else
                    if(OkHeaders.SENT_MILLIS.equalsIgnoreCase(s))
                        sentRequestMillis = Long.parseLong(s1);
                    else
                    if(OkHeaders.RECEIVED_MILLIS.equalsIgnoreCase(s))
                        receivedResponseMillis = Long.parseLong(s1);
                    i++;
                }
            }
        }
    }


    private CacheStrategy(Request request1, Response response1, ResponseSource responsesource)
    {
        request = request1;
        response = response1;
        source = responsesource;
    }


    public static boolean isCacheable(Response response1, Request request1)
    {
        int i = response1.code();
        if(i == 200 || i == 203 || i == 300 || i == 301 || i == 410)
        {
            CacheControl cachecontrol = response1.cacheControl();
            if((request1.header("Authorization") == null || cachecontrol.isPublic() || cachecontrol.mustRevalidate() || cachecontrol.sMaxAgeSeconds() != -1) && !cachecontrol.noStore())
                return true;
        }
        return false;
    }

    private static final Response.Body EMPTY_BODY = new Response.Body() {

        public InputStream byteStream()
        {
            return Util.EMPTY_INPUT_STREAM;
        }

        public long contentLength()
        {
            return 0L;
        }

        public MediaType contentType()
        {
            return null;
        }

        public boolean ready()
            throws IOException
        {
            return true;
        }

    }
;
    private static final StatusLine GATEWAY_TIMEOUT_STATUS_LINE;
    public final Request request;
    public final Response response;
    public final ResponseSource source;

    static 
    {
        try
        {
            GATEWAY_TIMEOUT_STATUS_LINE = new StatusLine("HTTP/1.1 504 Gateway Timeout");
        }
        catch(IOException ioexception)
        {
            throw new AssertionError();
        }
    }


}
