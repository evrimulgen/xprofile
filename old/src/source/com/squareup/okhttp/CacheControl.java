// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.squareup.okhttp;

import com.squareup.okhttp.internal.http.HeaderParser;
import com.squareup.okhttp.internal.http.Headers;

public final class CacheControl
{

    private CacheControl(boolean flag, boolean flag1, int i, int j, boolean flag2, boolean flag3, int k, 
            int l, boolean flag4)
    {
        noCache = flag;
        noStore = flag1;
        maxAgeSeconds = i;
        sMaxAgeSeconds = j;
        isPublic = flag2;
        mustRevalidate = flag3;
        maxStaleSeconds = k;
        minFreshSeconds = l;
        onlyIfCached = flag4;
    }

    public static CacheControl parse(Headers headers)
    {
        boolean flag;
        boolean flag1;
        int i;
        int j;
        boolean flag2;
        boolean flag3;
        int k;
        int l;
        boolean flag4;
        int i1;
        flag = false;
        flag1 = false;
        i = -1;
        j = -1;
        flag2 = false;
        flag3 = false;
        k = -1;
        l = -1;
        flag4 = false;
        i1 = 0;
_L2:
        if(i1 >= headers.size())
            break MISSING_BLOCK_LABEL_422;
        if(headers.name(i1).equalsIgnoreCase("Cache-Control") || headers.name(i1).equalsIgnoreCase("Pragma"))
            break; /* Loop/switch isn't completed */
_L4:
        i1++;
        if(true) goto _L2; else goto _L1
_L1:
        String s;
        int j1;
        s = headers.value(i1);
        j1 = 0;
_L5:
        int k1 = s.length();
        if(j1 >= k1) goto _L4; else goto _L3
_L3:
        int l1 = j1;
        int i2 = HeaderParser.skipUntil(s, j1, "=,;");
        String s1 = s.substring(l1, i2).trim();
        String s2;
        if(i2 == s.length() || s.charAt(i2) == ',' || s.charAt(i2) == ';')
        {
            j1 = i2 + 1;
            s2 = null;
        } else
        {
            int j2 = HeaderParser.skipWhitespace(s, i2 + 1);
            if(j2 < s.length() && s.charAt(j2) == '"')
            {
                int k2 = j2 + 1;
                int l2 = HeaderParser.skipUntil(s, k2, "\"");
                s2 = s.substring(k2, l2);
                j1 = l2 + 1;
            } else
            {
                j1 = HeaderParser.skipUntil(s, j2, ",;");
                s2 = s.substring(j2, j1).trim();
            }
        }
        if("no-cache".equalsIgnoreCase(s1))
            flag = true;
        else
        if("no-store".equalsIgnoreCase(s1))
            flag1 = true;
        else
        if("max-age".equalsIgnoreCase(s1))
            i = HeaderParser.parseSeconds(s2);
        else
        if("s-maxage".equalsIgnoreCase(s1))
            j = HeaderParser.parseSeconds(s2);
        else
        if("public".equalsIgnoreCase(s1))
            flag2 = true;
        else
        if("must-revalidate".equalsIgnoreCase(s1))
            flag3 = true;
        else
        if("max-stale".equalsIgnoreCase(s1))
            k = HeaderParser.parseSeconds(s2);
        else
        if("min-fresh".equalsIgnoreCase(s1))
            l = HeaderParser.parseSeconds(s2);
        else
        if("only-if-cached".equalsIgnoreCase(s1))
            flag4 = true;
          goto _L5
          goto _L4
        return new CacheControl(flag, flag1, i, j, flag2, flag3, k, l, flag4);
    }

    public boolean isPublic()
    {
        return isPublic;
    }

    public int maxAgeSeconds()
    {
        return maxAgeSeconds;
    }

    public int maxStaleSeconds()
    {
        return maxStaleSeconds;
    }

    public int minFreshSeconds()
    {
        return minFreshSeconds;
    }

    public boolean mustRevalidate()
    {
        return mustRevalidate;
    }

    public boolean noCache()
    {
        return noCache;
    }

    public boolean noStore()
    {
        return noStore;
    }

    public boolean onlyIfCached()
    {
        return onlyIfCached;
    }

    public int sMaxAgeSeconds()
    {
        return sMaxAgeSeconds;
    }

    private final boolean isPublic;
    private final int maxAgeSeconds;
    private final int maxStaleSeconds;
    private final int minFreshSeconds;
    private final boolean mustRevalidate;
    private final boolean noCache;
    private final boolean noStore;
    private final boolean onlyIfCached;
    private final int sMaxAgeSeconds;
}
