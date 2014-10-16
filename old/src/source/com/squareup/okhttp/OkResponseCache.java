// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.squareup.okhttp;

import com.squareup.okhttp.internal.http.Request;
import com.squareup.okhttp.internal.http.Response;
import java.io.IOException;
import java.net.CacheRequest;

// Referenced classes of package com.squareup.okhttp:
//            ResponseSource

public interface OkResponseCache
{

    public abstract Response get(Request request)
        throws IOException;

    public abstract boolean maybeRemove(Request request)
        throws IOException;

    public abstract CacheRequest put(Response response)
        throws IOException;

    public abstract void trackConditionalCacheHit();

    public abstract void trackResponse(ResponseSource responsesource);

    public abstract void update(Response response, Response response1)
        throws IOException;
}
