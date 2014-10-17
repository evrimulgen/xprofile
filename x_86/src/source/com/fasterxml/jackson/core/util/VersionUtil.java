// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.fasterxml.jackson.core.util;

import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.core.Versioned;
import java.io.*;
import java.util.Properties;
import java.util.regex.Pattern;

public class VersionUtil
{

    protected VersionUtil()
    {
        Version version2 = versionFor(getClass());
        Version version1 = version2;
_L2:
        if(version1 == null)
            version1 = Version.unknownVersion();
        _version = version1;
        return;
        Exception exception;
        exception;
        System.err.println((new StringBuilder()).append("ERROR: Failed to load Version information from ").append(getClass()).toString());
        version1 = null;
        if(true) goto _L2; else goto _L1
_L1:
    }

    private static final void _close(Closeable closeable)
    {
        try
        {
            closeable.close();
            return;
        }
        catch(IOException ioexception)
        {
            return;
        }
    }

    private static Version doReadVersion(Reader reader)
    {
        BufferedReader bufferedreader = new BufferedReader(reader);
        String s3 = bufferedreader.readLine();
        String s1 = s3;
        if(s1 == null) goto _L2; else goto _L1
_L1:
        String s4 = bufferedreader.readLine();
        String s;
        String s2;
        s = s4;
        s2 = null;
        if(s == null)
            break MISSING_BLOCK_LABEL_52;
        String s5 = bufferedreader.readLine();
        s2 = s5;
_L7:
        _close(bufferedreader);
_L4:
        if(s != null)
            s = s.trim();
        if(s2 != null)
            s2 = s2.trim();
        return parseVersion(s1, s, s2);
        IOException ioexception;
        ioexception;
        s = null;
        s1 = null;
_L5:
        _close(bufferedreader);
        s2 = null;
        if(true) goto _L4; else goto _L3
_L3:
        Exception exception;
        exception;
        _close(bufferedreader);
        throw exception;
        IOException ioexception1;
        ioexception1;
        s = null;
          goto _L5
        IOException ioexception2;
        ioexception2;
          goto _L5
_L2:
        s2 = null;
        s = null;
        if(true) goto _L7; else goto _L6
_L6:
    }

    public static Version mavenVersionFor(ClassLoader classloader, String s, String s1)
    {
        java.io.InputStream inputstream;
        inputstream = classloader.getResourceAsStream((new StringBuilder()).append("META-INF/maven/").append(s.replaceAll("\\.", "/")).append("/").append(s1).append("/pom.properties").toString());
        if(inputstream == null)
            break MISSING_BLOCK_LABEL_111;
        Version version1;
        Properties properties = new Properties();
        properties.load(inputstream);
        String s2 = properties.getProperty("version");
        String s3 = properties.getProperty("artifactId");
        version1 = parseVersion(s2, properties.getProperty("groupId"), s3);
        _close(inputstream);
        return version1;
        IOException ioexception;
        ioexception;
        _close(inputstream);
        return Version.unknownVersion();
        Exception exception;
        exception;
        _close(inputstream);
        throw exception;
    }

    public static Version packageVersionFor(Class class1)
    {
        Class class2;
        Exception exception1;
        Version version1;
        try
        {
            class2 = Class.forName((new StringBuilder()).append(class1.getPackage().getName()).append(".PackageVersion").toString(), true, class1.getClassLoader());
        }
        catch(Exception exception)
        {
            return null;
        }
        version1 = ((Versioned)class2.newInstance()).version();
        return version1;
        exception1;
        throw new IllegalArgumentException((new StringBuilder()).append("Failed to get Versioned out of ").append(class2).toString());
    }

    public static Version parseVersion(String s, String s1, String s2)
    {
        if(s != null)
        {
            String s3 = s.trim();
            if(s3.length() > 0)
            {
                String as[] = VERSION_SEPARATOR.split(s3);
                int i = parseVersionPart(as[0]);
                int j;
                int k;
                int l;
                int i1;
                String s4;
                if(as.length > 1)
                    j = parseVersionPart(as[1]);
                else
                    j = 0;
                k = as.length;
                l = 0;
                if(k > 2)
                    l = parseVersionPart(as[2]);
                i1 = as.length;
                s4 = null;
                if(i1 > 3)
                    s4 = as[3];
                return new Version(i, j, l, s4, s1, s2);
            }
        }
        return null;
    }

    protected static int parseVersionPart(String s)
    {
        int i = 0;
        int j = s.length();
        int k = 0;
        do
        {
            char c;
label0:
            {
                if(i < j)
                {
                    c = s.charAt(i);
                    if(c <= '9' && c >= '0')
                        break label0;
                }
                return k;
            }
            k = k * 10 + (c - 48);
            i++;
        } while(true);
    }

    public static final void throwInternal()
    {
        throw new RuntimeException("Internal error: this code path should never get executed");
    }

    public static Version versionFor(Class class1)
    {
        java.io.InputStream inputstream;
        Version version1 = packageVersionFor(class1);
        if(version1 != null)
            return version1;
        inputstream = class1.getResourceAsStream("VERSION.txt");
        if(inputstream == null)
            return Version.unknownVersion();
        Version version3 = doReadVersion(new InputStreamReader(inputstream, "UTF-8"));
        _close(inputstream);
        return version3;
        UnsupportedEncodingException unsupportedencodingexception;
        unsupportedencodingexception;
        Version version2 = Version.unknownVersion();
        _close(inputstream);
        return version2;
        Exception exception;
        exception;
        _close(inputstream);
        throw exception;
    }

    public Version version()
    {
        return _version;
    }

    private static final Pattern VERSION_SEPARATOR = Pattern.compile("[-_./;:]");
    private final Version _version;

}
