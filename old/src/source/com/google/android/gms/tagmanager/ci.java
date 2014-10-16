// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.a;
import com.google.android.gms.internal.b;
import java.util.Map;
import java.util.regex.*;

// Referenced classes of package com.google.android.gms.tagmanager:
//            dd, di

class ci extends dd
{

    public ci()
    {
        super(ID);
    }

    protected boolean a(String s, String s1, Map map)
    {
        byte byte0;
        boolean flag;
        if(di.n((com.google.android.gms.internal.d.a)map.get(We)).booleanValue())
            byte0 = 66;
        else
            byte0 = 64;
        try
        {
            flag = Pattern.compile(s1, byte0).matcher(s).find();
        }
        catch(PatternSyntaxException patternsyntaxexception)
        {
            return false;
        }
        return flag;
    }

    private static final String ID;
    private static final String We;

    static 
    {
        ID = a.ar.toString();
        We = b.cQ.toString();
    }
}
