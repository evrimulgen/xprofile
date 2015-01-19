// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.squareup.okhttp;

import com.squareup.okhttp.internal.okio.ByteString;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.Proxy;
import java.net.URL;
import java.util.List;

public interface OkAuthenticator
{
    public static final class Challenge
    {

        public boolean equals(Object obj)
        {
            return (obj instanceof Challenge) && ((Challenge)obj).scheme.equals(scheme) && ((Challenge)obj).realm.equals(realm);
        }

        public String getRealm()
        {
            return realm;
        }

        public String getScheme()
        {
            return scheme;
        }

        public int hashCode()
        {
            return scheme.hashCode() + 31 * realm.hashCode();
        }

        public String toString()
        {
            return (new StringBuilder()).append(scheme).append(" realm=\"").append(realm).append("\"").toString();
        }

        private final String realm;
        private final String scheme;

        public Challenge(String s, String s1)
        {
            scheme = s;
            realm = s1;
        }
    }

    public static final class Credential
    {

        public static Credential basic(String s, String s1)
        {
            Credential credential;
            try
            {
                String s2 = ByteString.of((new StringBuilder()).append(s).append(":").append(s1).toString().getBytes("ISO-8859-1")).base64();
                credential = new Credential((new StringBuilder()).append("Basic ").append(s2).toString());
            }
            catch(UnsupportedEncodingException unsupportedencodingexception)
            {
                throw new AssertionError();
            }
            return credential;
        }

        public boolean equals(Object obj)
        {
            return (obj instanceof Credential) && ((Credential)obj).headerValue.equals(headerValue);
        }

        public String getHeaderValue()
        {
            return headerValue;
        }

        public int hashCode()
        {
            return headerValue.hashCode();
        }

        public String toString()
        {
            return headerValue;
        }

        private final String headerValue;

        private Credential(String s)
        {
            headerValue = s;
        }
    }


    public abstract Credential authenticate(Proxy proxy, URL url, List list)
        throws IOException;

    public abstract Credential authenticateProxy(Proxy proxy, URL url, List list)
        throws IOException;
}
