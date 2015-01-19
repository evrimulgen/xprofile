// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package io.segment.android.utils;

import android.content.Context;

public class AndroidUtils
{

    public AndroidUtils()
    {
    }

    public static boolean permissionGranted(Context context, String s)
    {
        return context.checkCallingOrSelfPermission(s) == 0;
    }

    public static boolean permissionGranted(Context context, String as[])
    {
        int i;
        int j;
        i = as.length;
        j = 0;
_L6:
        if(j < i) goto _L2; else goto _L1
_L1:
        boolean flag1 = true;
_L4:
        return flag1;
_L2:
        boolean flag;
        flag = permissionGranted(context, as[j]);
        flag1 = false;
        if(!flag) goto _L4; else goto _L3
_L3:
        j++;
        if(true) goto _L6; else goto _L5
_L5:
    }
}
