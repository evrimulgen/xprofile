// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.squareup.okhttp.internal.http;

import java.net.URL;

// Referenced classes of package com.squareup.okhttp.internal.http:
//            Request

public final class RequestLine
{

    private RequestLine()
    {
    }

    static String get(Request request, java.net.Proxy.Type type, int i)
    {
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append(request.method());
        stringbuilder.append(" ");
        if(includeAuthorityInRequestLine(request, type))
            stringbuilder.append(request.url());
        else
            stringbuilder.append(requestPath(request.url()));
        stringbuilder.append(" ");
        stringbuilder.append(version(i));
        return stringbuilder.toString();
    }

    private static boolean includeAuthorityInRequestLine(Request request, java.net.Proxy.Type type)
    {
        return !request.isHttps() && type == java.net.Proxy.Type.HTTP;
    }

    public static String requestPath(URL url)
    {
        String s = url.getFile();
        if(s == null)
            s = "/";
        else
        if(!s.startsWith("/"))
            return (new StringBuilder()).append("/").append(s).toString();
        return s;
    }

    public static String version(int i)
    {
        if(i == 1)
            return "HTTP/1.1";
        else
            return "HTTP/1.0";
    }
}
