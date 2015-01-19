// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.a;
import com.google.android.gms.internal.b;
import java.util.Map;

// Referenced classes of package com.google.android.gms.tagmanager:
//            aj, di, DataLayer

class u extends aj
{

    public u(DataLayer datalayer)
    {
        String s = ID;
        String as[] = new String[1];
        as[0] = NAME;
        super(s, as);
        TN = datalayer;
    }

    public boolean iy()
    {
        return false;
    }

    public com.google.android.gms.internal.d.a u(Map map)
    {
        Object obj = TN.get(di.j((com.google.android.gms.internal.d.a)map.get(NAME)));
        if(obj == null)
        {
            com.google.android.gms.internal.d.a a1 = (com.google.android.gms.internal.d.a)map.get(UC);
            if(a1 != null)
                return a1;
            else
                return di.ku();
        } else
        {
            return di.r(obj);
        }
    }

    private static final String ID;
    private static final String NAME;
    private static final String UC;
    private final DataLayer TN;

    static 
    {
        ID = a.N.toString();
        NAME = b.dn.toString();
        UC = b.cm.toString();
    }
}
