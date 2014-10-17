// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.a;
import java.util.Map;

// Referenced classes of package com.google.android.gms.tagmanager:
//            aj, di

class bz extends aj
{

    public bz()
    {
        super(ID, new String[0]);
    }

    public boolean iy()
    {
        return true;
    }

    public com.google.android.gms.internal.d.a u(Map map)
    {
        return di.r(android.os.Build.VERSION.RELEASE);
    }

    private static final String ID;

    static 
    {
        ID = a.X.toString();
    }
}
