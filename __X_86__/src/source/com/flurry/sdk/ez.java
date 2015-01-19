// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.flurry.sdk;

import android.text.TextUtils;

// Referenced classes of package com.flurry.sdk:
//            fb, fa, fc

public final class ez extends fb
{

    public ez(String s, int i)
    {
        super(a(s, i));
        a = i;
    }

    private static fc a(String s, int i)
    {
        boolean flag = b(s, i);
        fc fc = null;
        if(flag)
            fc = fa.a(s);
        return fc;
    }

    private static boolean b(String s, int i)
    {
        return !TextUtils.isEmpty(s) && android.os.Build.VERSION.SDK_INT >= i;
    }

    private final int a;
}
