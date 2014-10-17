// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.mixpanel.android.util;

import java.io.*;

public class StringUtils
{

    public StringUtils()
    {
    }

    public static String inputStreamToString(InputStream inputstream)
        throws IOException
    {
        BufferedReader bufferedreader = new BufferedReader(new InputStreamReader(inputstream));
        StringBuilder stringbuilder = new StringBuilder();
        do
        {
            String s = bufferedreader.readLine();
            if(s != null)
            {
                stringbuilder.append((new StringBuilder()).append(s).append("\n").toString());
            } else
            {
                bufferedreader.close();
                return stringbuilder.toString();
            }
        } while(true);
    }
}
