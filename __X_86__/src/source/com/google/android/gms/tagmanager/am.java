// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.a;
import java.util.Map;

// Referenced classes of package com.google.android.gms.tagmanager:
//            bx, dh

class am extends bx
{

    public am()
    {
        super(ID);
    }

    protected boolean a(dh dh1, dh dh2, Map map)
    {
        return dh1.a(dh2) > 0;
    }

    private static final String ID;

    static 
    {
        ID = a.ay.toString();
    }
}
