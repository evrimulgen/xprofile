// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.squareup.okhttp;

import java.nio.charset.Charset;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class MediaType
{

    private MediaType(String s, String s1, String s2, String s3)
    {
        mediaType = s;
        type = s1;
        subtype = s2;
        charset = s3;
    }

    public static MediaType parse(String s)
    {
        Matcher matcher = TYPE_SUBTYPE.matcher(s);
        if(matcher.lookingAt()) goto _L2; else goto _L1
_L1:
        return null;
_L2:
        String s1;
        String s2;
        String s3;
        s1 = matcher.group(1).toLowerCase(Locale.US);
        s2 = matcher.group(2).toLowerCase(Locale.US);
        s3 = null;
        Matcher matcher1 = PARAMETER.matcher(s);
        int i = matcher.end();
label0:
        do
        {
label1:
            {
                if(i >= s.length())
                    break label1;
                matcher1.region(i, s.length());
                if(!matcher1.lookingAt())
                    break label0;
                String s4 = matcher1.group(1);
                if(s4 != null && s4.equalsIgnoreCase("charset"))
                {
                    if(s3 != null)
                        throw new IllegalArgumentException((new StringBuilder()).append("Multiple charsets: ").append(s).toString());
                    if(matcher1.group(2) != null)
                        s3 = matcher1.group(2);
                    else
                        s3 = matcher1.group(3);
                }
                i = matcher1.end();
            }
        } while(true);
        if(true) goto _L1; else goto _L3
_L3:
        return new MediaType(s, s1, s2, s3);
    }

    public Charset charset()
    {
        if(charset != null)
            return Charset.forName(charset);
        else
            return null;
    }

    public Charset charset(Charset charset1)
    {
        if(charset != null)
            charset1 = Charset.forName(charset);
        return charset1;
    }

    public boolean equals(Object obj)
    {
        return (obj instanceof MediaType) && ((MediaType)obj).mediaType.equals(mediaType);
    }

    public int hashCode()
    {
        return mediaType.hashCode();
    }

    public String subtype()
    {
        return subtype;
    }

    public String toString()
    {
        return mediaType;
    }

    public String type()
    {
        return type;
    }

    private static final Pattern PARAMETER = Pattern.compile(";\\s*([a-zA-Z0-9-!#$%&'*+.^_`{|}~]+)=(?:([a-zA-Z0-9-!#$%&'*+.^_`{|}~]+)|\"([^\"]*)\")");
    private static final String QUOTED = "\"([^\"]*)\"";
    private static final String TOKEN = "([a-zA-Z0-9-!#$%&'*+.^_`{|}~]+)";
    private static final Pattern TYPE_SUBTYPE = Pattern.compile("([a-zA-Z0-9-!#$%&'*+.^_`{|}~]+)/([a-zA-Z0-9-!#$%&'*+.^_`{|}~]+)");
    private final String charset;
    private final String mediaType;
    private final String subtype;
    private final String type;

}
