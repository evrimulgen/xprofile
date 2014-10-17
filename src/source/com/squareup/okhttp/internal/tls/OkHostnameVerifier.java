// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.squareup.okhttp.internal.tls;

import java.security.cert.CertificateParsingException;
import java.security.cert.X509Certificate;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.net.ssl.*;

// Referenced classes of package com.squareup.okhttp.internal.tls:
//            DistinguishedNameParser

public final class OkHostnameVerifier
    implements HostnameVerifier
{

    private OkHostnameVerifier()
    {
    }

    private List getSubjectAltNames(X509Certificate x509certificate, int i)
    {
        Object obj = new ArrayList();
        Collection collection = x509certificate.getSubjectAlternativeNames();
        if(collection != null)
            break MISSING_BLOCK_LABEL_23;
        return Collections.emptyList();
        Iterator iterator = collection.iterator();
_L2:
        List list;
        do
        {
            if(!iterator.hasNext())
                break MISSING_BLOCK_LABEL_133;
            list = (List)iterator.next();
        } while(list == null);
        if(list.size() < 2) goto _L2; else goto _L1
_L1:
        Integer integer = (Integer)list.get(0);
        if(integer == null) goto _L2; else goto _L3
_L3:
        if(integer.intValue() != i) goto _L2; else goto _L4
_L4:
        String s = (String)list.get(1);
        if(s == null) goto _L2; else goto _L5
_L5:
        ((List) (obj)).add(s);
          goto _L2
        CertificateParsingException certificateparsingexception;
        certificateparsingexception;
        obj = Collections.emptyList();
        return ((List) (obj));
    }

    static boolean verifyAsIpAddress(String s)
    {
        return VERIFY_AS_IP_ADDRESS.matcher(s).matches();
    }

    private boolean verifyHostName(String s, X509Certificate x509certificate)
    {
        String s1 = s.toLowerCase(Locale.US);
        boolean flag = false;
        for(Iterator iterator = getSubjectAltNames(x509certificate, 2).iterator(); iterator.hasNext();)
        {
            String s3 = (String)iterator.next();
            flag = true;
            if(verifyHostName(s1, s3))
                return true;
        }

        if(!flag)
        {
            String s2 = (new DistinguishedNameParser(x509certificate.getSubjectX500Principal())).findMostSpecific("cn");
            if(s2 != null)
                return verifyHostName(s1, s2);
        }
        return false;
    }

    private boolean verifyIpAddress(String s, X509Certificate x509certificate)
    {
        for(Iterator iterator = getSubjectAltNames(x509certificate, 7).iterator(); iterator.hasNext();)
            if(s.equalsIgnoreCase((String)iterator.next()))
                return true;

        return false;
    }

    public boolean verify(String s, X509Certificate x509certificate)
    {
        if(verifyAsIpAddress(s))
            return verifyIpAddress(s, x509certificate);
        else
            return verifyHostName(s, x509certificate);
    }

    public boolean verify(String s, SSLSession sslsession)
    {
        boolean flag;
        try
        {
            flag = verify(s, (X509Certificate)sslsession.getPeerCertificates()[0]);
        }
        catch(SSLException sslexception)
        {
            return false;
        }
        return flag;
    }

    public boolean verifyHostName(String s, String s1)
    {
        boolean flag = true;
        if(s == null || s.length() == 0 || s1 == null || s1.length() == 0)
        {
            flag = false;
        } else
        {
            String s2 = s1.toLowerCase(Locale.US);
            if(!s2.contains("*"))
                return s.equals(s2);
            if(!s2.startsWith("*.") || !s.regionMatches(0, s2, 2, -2 + s2.length()))
            {
                int i = s2.indexOf('*');
                if(i > s2.indexOf('.'))
                    return false;
                if(!s.regionMatches(0, s2, 0, i))
                    return false;
                int j = s2.length() - (i + 1);
                int k = s.length() - j;
                if(s.indexOf('.', i) < k && !s.endsWith(".clients.google.com"))
                    return false;
                if(!s.regionMatches(k, s2, i + 1, j))
                    return false;
            }
        }
        return flag;
    }

    private static final int ALT_DNS_NAME = 2;
    private static final int ALT_IPA_NAME = 7;
    public static final OkHostnameVerifier INSTANCE = new OkHostnameVerifier();
    private static final Pattern VERIFY_AS_IP_ADDRESS = Pattern.compile("([0-9a-fA-F]*:[0-9a-fA-F:.]*)|([\\d.]+)");

}
