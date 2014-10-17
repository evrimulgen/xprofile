// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.squareup.okhttp.internal;

import java.net.InetAddress;
import java.net.UnknownHostException;

public interface Dns
{

    public abstract InetAddress[] getAllByName(String s)
        throws UnknownHostException;

    public static final Dns DEFAULT = new Dns() {

        public InetAddress[] getAllByName(String s)
            throws UnknownHostException
        {
            if(s == null)
                throw new UnknownHostException("host == null");
            else
                return InetAddress.getAllByName(s);
        }

    }
;

}
