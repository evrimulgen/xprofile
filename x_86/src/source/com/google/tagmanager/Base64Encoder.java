// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.tagmanager;

import android.util.Base64;

// Referenced classes of package com.google.tagmanager:
//            Base64

class Base64Encoder
{

    Base64Encoder()
    {
    }

    public static byte[] decode(String s, int i)
    {
        boolean flag = true;
        if(getSdkVersion() >= 8)
        {
            int j = 2;
            if((i & 1) != 0)
                j |= 1;
            if((i & 2) != 0)
                j |= 8;
            return Base64.decode(s, j);
        }
        if((i & 1) == 0)
        {
            boolean _tmp = flag;
        }
        if((i & 2) == 0)
            flag = false;
        if(flag)
            return Base64.decodeWebSafe(s);
        else
            return Base64.decode(s);
    }

    public static String encodeToString(byte abyte0[], int i)
    {
        boolean flag = true;
        if(getSdkVersion() >= 8)
        {
            int j = 2;
            if((i & 1) != 0)
                j |= 1;
            if((i & 2) != 0)
                j |= 8;
            return Base64.encodeToString(abyte0, j);
        }
        boolean flag1;
        if((i & 1) == 0)
            flag1 = flag;
        else
            flag1 = false;
        if((i & 2) == 0)
            flag = false;
        if(flag)
            return Base64.encodeWebSafe(abyte0, flag1);
        else
            return Base64.encode(abyte0, flag1);
    }

    static int getSdkVersion()
    {
        return android.os.Build.VERSION.SDK_INT;
    }

    public static final int DEFAULT = 0;
    public static final int NO_PADDING = 1;
    public static final int URL_SAFE = 2;
}
