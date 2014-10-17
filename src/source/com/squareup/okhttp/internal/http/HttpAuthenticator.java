// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.squareup.okhttp.internal.http;

import com.squareup.okhttp.OkAuthenticator;
import java.io.IOException;
import java.net.*;
import java.util.ArrayList;
import java.util.List;

// Referenced classes of package com.squareup.okhttp.internal.http:
//            Headers, HeaderParser, Response, Request

public final class HttpAuthenticator
{

    private HttpAuthenticator()
    {
    }

    private static List parseChallenges(Headers headers, String s)
    {
        ArrayList arraylist;
        int i;
        arraylist = new ArrayList();
        i = 0;
_L2:
        if(i >= headers.size())
            break MISSING_BLOCK_LABEL_186;
        if(s.equalsIgnoreCase(headers.name(i)))
            break; /* Loop/switch isn't completed */
_L4:
        i++;
        if(true) goto _L2; else goto _L1
_L1:
        String s1;
        int j;
        s1 = headers.value(i);
        j = 0;
_L6:
        if(j >= s1.length()) goto _L4; else goto _L3
_L3:
        String s2;
        int i1;
        int k = j;
        int l = HeaderParser.skipUntil(s1, j, " ");
        s2 = s1.substring(k, l).trim();
        i1 = HeaderParser.skipWhitespace(s1, l);
        if(!s1.regionMatches(true, i1, "realm=\"", 0, "realm=\"".length())) goto _L4; else goto _L5
_L5:
        int j1 = i1 + "realm=\"".length();
        int k1 = HeaderParser.skipUntil(s1, j1, "\"");
        String s3 = s1.substring(j1, k1);
        j = HeaderParser.skipWhitespace(s1, 1 + HeaderParser.skipUntil(s1, k1 + 1, ","));
        arraylist.add(new com.squareup.okhttp.OkAuthenticator.Challenge(s2, s3));
          goto _L6
        return arraylist;
    }

    public static Request processAuthHeader(OkAuthenticator okauthenticator, Response response, Proxy proxy)
        throws IOException
    {
        String s;
        String s1;
        List list;
        if(response.code() == 401)
        {
            s = "WWW-Authenticate";
            s1 = "Authorization";
        } else
        if(response.code() == 407)
        {
            s = "Proxy-Authenticate";
            s1 = "Proxy-Authorization";
        } else
        {
            throw new IllegalArgumentException();
        }
        list = parseChallenges(response.headers(), s);
        if(!list.isEmpty())
        {
            Request request = response.request();
            com.squareup.okhttp.OkAuthenticator.Credential credential;
            if(response.code() == 407)
                credential = okauthenticator.authenticateProxy(proxy, request.url(), list);
            else
                credential = okauthenticator.authenticate(proxy, request.url(), list);
            if(credential != null)
                return request.newBuilder().header(s1, credential.getHeaderValue()).build();
        }
        return null;
    }

    public static final OkAuthenticator SYSTEM_DEFAULT = new OkAuthenticator() {

        private InetAddress getConnectToInetAddress(Proxy proxy, URL url)
            throws IOException
        {
            if(proxy != null && proxy.type() != java.net.Proxy.Type.DIRECT)
                return ((InetSocketAddress)proxy.address()).getAddress();
            else
                return InetAddress.getByName(url.getHost());
        }

        public com.squareup.okhttp.OkAuthenticator.Credential authenticate(Proxy proxy, URL url, List list)
            throws IOException
        {
            int i;
            int j;
            i = 0;
            j = list.size();
_L2:
            com.squareup.okhttp.OkAuthenticator.Challenge challenge;
            if(i >= j)
                break MISSING_BLOCK_LABEL_113;
            challenge = (com.squareup.okhttp.OkAuthenticator.Challenge)list.get(i);
            if("Basic".equalsIgnoreCase(challenge.getScheme()))
                break; /* Loop/switch isn't completed */
_L4:
            i++;
            PasswordAuthentication passwordauthentication;
            if(true) goto _L2; else goto _L1
_L1:
            if((passwordauthentication = Authenticator.requestPasswordAuthentication(url.getHost(), getConnectToInetAddress(proxy, url), url.getPort(), url.getProtocol(), challenge.getRealm(), challenge.getScheme(), url, java.net.Authenticator.RequestorType.SERVER)) == null) goto _L4; else goto _L3
_L3:
            return com.squareup.okhttp.OkAuthenticator.Credential.basic(passwordauthentication.getUserName(), new String(passwordauthentication.getPassword()));
            return null;
        }

        public com.squareup.okhttp.OkAuthenticator.Credential authenticateProxy(Proxy proxy, URL url, List list)
            throws IOException
        {
            int i;
            int j;
            i = 0;
            j = list.size();
_L2:
            com.squareup.okhttp.OkAuthenticator.Challenge challenge;
            if(i >= j)
                break MISSING_BLOCK_LABEL_124;
            challenge = (com.squareup.okhttp.OkAuthenticator.Challenge)list.get(i);
            if("Basic".equalsIgnoreCase(challenge.getScheme()))
                break; /* Loop/switch isn't completed */
_L4:
            i++;
            if(true) goto _L2; else goto _L1
_L1:
            PasswordAuthentication passwordauthentication;
            InetSocketAddress inetsocketaddress = (InetSocketAddress)proxy.address();
            passwordauthentication = Authenticator.requestPasswordAuthentication(inetsocketaddress.getHostName(), getConnectToInetAddress(proxy, url), inetsocketaddress.getPort(), url.getProtocol(), challenge.getRealm(), challenge.getScheme(), url, java.net.Authenticator.RequestorType.PROXY);
            if(passwordauthentication == null) goto _L4; else goto _L3
_L3:
            return com.squareup.okhttp.OkAuthenticator.Credential.basic(passwordauthentication.getUserName(), new String(passwordauthentication.getPassword()));
            return null;
        }

    }
;

}
