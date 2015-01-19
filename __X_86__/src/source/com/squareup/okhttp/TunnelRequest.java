// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.squareup.okhttp;

import com.squareup.okhttp.internal.Util;
import com.squareup.okhttp.internal.http.Request;
import java.io.IOException;
import java.net.URL;

public final class TunnelRequest
{

    public TunnelRequest(String s, int i, String s1, String s2)
    {
        if(s == null)
            throw new NullPointerException("host == null");
        if(s1 == null)
        {
            throw new NullPointerException("userAgent == null");
        } else
        {
            host = s;
            port = i;
            userAgent = s1;
            proxyAuthorization = s2;
            return;
        }
    }

    Request getRequest()
        throws IOException
    {
        com.squareup.okhttp.internal.http.Request.Builder builder = (new com.squareup.okhttp.internal.http.Request.Builder()).url(new URL("https", host, port, "/"));
        String s;
        if(port == Util.getDefaultPort("https"))
            s = host;
        else
            s = (new StringBuilder()).append(host).append(":").append(port).toString();
        builder.header("Host", s);
        builder.header("User-Agent", userAgent);
        if(proxyAuthorization != null)
            builder.header("Proxy-Authorization", proxyAuthorization);
        builder.header("Proxy-Connection", "Keep-Alive");
        return builder.build();
    }

    String requestLine()
    {
        return (new StringBuilder()).append("CONNECT ").append(host).append(":").append(port).append(" HTTP/1.1").toString();
    }

    final String host;
    final int port;
    final String proxyAuthorization;
    final String userAgent;
}
