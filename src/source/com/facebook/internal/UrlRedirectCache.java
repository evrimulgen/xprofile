// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.facebook.internal;

import android.content.Context;
import com.facebook.LoggingBehavior;
import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;

// Referenced classes of package com.facebook.internal:
//            FileLruCache, Utility, Logger

class UrlRedirectCache
{

    UrlRedirectCache()
    {
    }

    static void cacheUriRedirect(Context context, URI uri, URI uri1)
    {
        OutputStream outputstream;
        if(uri == null || uri1 == null)
            return;
        outputstream = null;
        try
        {
            outputstream = getCache(context).openPutStream(uri.toString(), REDIRECT_CONTENT_TAG);
            outputstream.write(uri1.toString().getBytes());
        }
        catch(IOException ioexception)
        {
            Utility.closeQuietly(outputstream);
            return;
        }
        Utility.closeQuietly(outputstream);
        return;
        Exception exception;
        exception;
        Utility.closeQuietly(outputstream);
        throw exception;
    }

    static void clearCache(Context context)
    {
        try
        {
            getCache(context).clearCache();
            return;
        }
        catch(IOException ioexception)
        {
            Logger.log(LoggingBehavior.CACHE, 5, TAG, (new StringBuilder()).append("clearCache failed ").append(ioexception.getMessage()).toString());
        }
    }

    static FileLruCache getCache(Context context)
        throws IOException
    {
        com/facebook/internal/UrlRedirectCache;
        JVM INSTR monitorenter ;
        FileLruCache filelrucache;
        if(urlRedirectCache == null)
            urlRedirectCache = new FileLruCache(context.getApplicationContext(), TAG, new FileLruCache.Limits());
        filelrucache = urlRedirectCache;
        com/facebook/internal/UrlRedirectCache;
        JVM INSTR monitorexit ;
        return filelrucache;
        Exception exception;
        exception;
        throw exception;
    }

    static URI getRedirectedUri(Context context, URI uri)
    {
        String s;
        InputStreamReader inputstreamreader;
        if(uri == null)
            return null;
        s = uri.toString();
        inputstreamreader = null;
        FileLruCache filelrucache = getCache(context);
        boolean flag;
        InputStreamReader inputstreamreader1;
        flag = false;
        inputstreamreader1 = null;
_L6:
        java.io.InputStream inputstream = filelrucache.get(s, REDIRECT_CONTENT_TAG);
        if(inputstream == null) goto _L2; else goto _L1
_L1:
        flag = true;
        inputstreamreader = new InputStreamReader(inputstream);
        char ac[];
        StringBuilder stringbuilder;
        ac = new char[128];
        stringbuilder = new StringBuilder();
_L5:
        int i = inputstreamreader.read(ac, 0, ac.length);
        if(i <= 0) goto _L4; else goto _L3
_L3:
        stringbuilder.append(ac, 0, i);
          goto _L5
        URISyntaxException urisyntaxexception;
        urisyntaxexception;
_L11:
        Utility.closeQuietly(inputstreamreader);
        return null;
_L4:
        String s1;
        Utility.closeQuietly(inputstreamreader);
        s1 = stringbuilder.toString();
        s = s1;
        inputstreamreader1 = inputstreamreader;
          goto _L6
_L2:
        if(!flag)
            break MISSING_BLOCK_LABEL_152;
        URI uri1 = new URI(s);
        Utility.closeQuietly(inputstreamreader1);
        return uri1;
        Utility.closeQuietly(inputstreamreader1);
        inputstreamreader1;
        return null;
        IOException ioexception;
        ioexception;
_L10:
        Utility.closeQuietly(inputstreamreader);
        return null;
        Exception exception;
        exception;
_L8:
        Utility.closeQuietly(inputstreamreader);
        throw exception;
        exception;
        inputstreamreader = inputstreamreader1;
        if(true) goto _L8; else goto _L7
_L7:
        IOException ioexception1;
        ioexception1;
        inputstreamreader = inputstreamreader1;
        if(true) goto _L10; else goto _L9
_L9:
        URISyntaxException urisyntaxexception1;
        urisyntaxexception1;
        inputstreamreader = inputstreamreader1;
          goto _L11
    }

    private static final String REDIRECT_CONTENT_TAG = (new StringBuilder()).append(TAG).append("_Redirect").toString();
    static final String TAG = com/facebook/internal/UrlRedirectCache.getSimpleName();
    private static volatile FileLruCache urlRedirectCache;

}
