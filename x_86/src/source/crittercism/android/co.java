// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package crittercism.android;

import java.net.MalformedURLException;
import java.net.URL;

public final class co
{

    public co(String s, String s1)
    {
        s.endsWith("/");
        s1.startsWith("/");
        a = s;
        b = s1;
    }

    public final URL a()
    {
        URL url;
        try
        {
            url = new URL((new StringBuilder()).append(a).append(b).toString());
        }
        catch(MalformedURLException malformedurlexception)
        {
            (new StringBuilder("Invalid url: ")).append(a).append(b);
            return null;
        }
        return url;
    }

    private String a;
    private String b;
}
