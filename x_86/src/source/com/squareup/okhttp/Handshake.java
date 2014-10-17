// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.squareup.okhttp;

import com.squareup.okhttp.internal.Util;
import java.security.Principal;
import java.security.cert.X509Certificate;
import java.util.Collections;
import java.util.List;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLSession;

public final class Handshake
{

    private Handshake(String s, List list, List list1)
    {
        cipherSuite = s;
        peerCertificates = list;
        localCertificates = list1;
    }

    public static Handshake get(String s, List list, List list1)
    {
        if(s == null)
            throw new IllegalArgumentException("cipherSuite == null");
        else
            return new Handshake(s, Util.immutableList(list), Util.immutableList(list1));
    }

    public static Handshake get(SSLSession sslsession)
    {
        String s;
        s = sslsession.getCipherSuite();
        if(s == null)
            throw new IllegalStateException("cipherSuite == null");
        java.security.cert.Certificate acertificate2[] = sslsession.getPeerCertificates();
        java.security.cert.Certificate acertificate[] = acertificate2;
_L1:
        SSLPeerUnverifiedException sslpeerunverifiedexception;
        List list;
        java.security.cert.Certificate acertificate1[];
        List list1;
        if(acertificate != null)
            list = Util.immutableList(acertificate);
        else
            list = Collections.emptyList();
        acertificate1 = sslsession.getLocalCertificates();
        if(acertificate1 != null)
            list1 = Util.immutableList(acertificate1);
        else
            list1 = Collections.emptyList();
        return new Handshake(s, list, list1);
        sslpeerunverifiedexception;
        acertificate = null;
          goto _L1
    }

    public String cipherSuite()
    {
        return cipherSuite;
    }

    public boolean equals(Object obj)
    {
        Handshake handshake;
        if(obj instanceof Handshake)
            if(cipherSuite.equals((handshake = (Handshake)obj).cipherSuite) && peerCertificates.equals(handshake.peerCertificates) && localCertificates.equals(handshake.localCertificates))
                return true;
        return false;
    }

    public int hashCode()
    {
        return 31 * (31 * (527 + cipherSuite.hashCode()) + peerCertificates.hashCode()) + localCertificates.hashCode();
    }

    public List localCertificates()
    {
        return localCertificates;
    }

    public Principal localPrincipal()
    {
        if(!localCertificates.isEmpty())
            return ((X509Certificate)localCertificates.get(0)).getSubjectX500Principal();
        else
            return null;
    }

    public List peerCertificates()
    {
        return peerCertificates;
    }

    public Principal peerPrincipal()
    {
        if(!peerCertificates.isEmpty())
            return ((X509Certificate)peerCertificates.get(0)).getSubjectX500Principal();
        else
            return null;
    }

    private final String cipherSuite;
    private final List localCertificates;
    private final List peerCertificates;
}
