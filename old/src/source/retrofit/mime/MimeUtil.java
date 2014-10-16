// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package retrofit.mime;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class MimeUtil
{

    private MimeUtil()
    {
    }

    public static String parseCharset(String s)
    {
        Matcher matcher = CHARSET.matcher(s);
        if(matcher.find())
            return matcher.group(1).replaceAll("[\"\\\\]", "");
        else
            return "UTF-8";
    }

    private static final Pattern CHARSET = Pattern.compile("\\Wcharset=([^\\s;]+)", 2);

}
