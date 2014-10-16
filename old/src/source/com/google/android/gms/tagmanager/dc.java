// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.a;
import java.util.Map;

// Referenced classes of package com.google.android.gms.tagmanager:
//            dd

class dc extends dd
{

    public dc()
    {
        super(ID);
    }

    protected boolean a(String s, String s1, Map map)
    {
        return s.startsWith(s1);
    }

    private static final String ID;

    static 
    {
        ID = a.as.toString();
    }
}
