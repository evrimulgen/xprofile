// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.squareup.okhttp.internal.http;

import java.util.*;

public final class HttpMethod
{

    private HttpMethod()
    {
    }

    public static boolean hasRequestBody(String s)
    {
        return s.equals("POST") || s.equals("PUT") || s.equals("PATCH") || s.equals("DELETE");
    }

    public static boolean invalidatesCache(String s)
    {
        return s.equals("POST") || s.equals("PATCH") || s.equals("PUT") || s.equals("DELETE");
    }

    public static final Set METHODS = new LinkedHashSet(Arrays.asList(new String[] {
        "OPTIONS", "GET", "HEAD", "POST", "PUT", "DELETE", "TRACE", "PATCH"
    }));

}
