// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.tagmanager;

import com.google.analytics.containertag.common.FunctionType;
import java.util.Map;

// Referenced classes of package com.google.tagmanager:
//            StringPredicate

class StartsWithPredicate extends StringPredicate
{

    public StartsWithPredicate()
    {
        super(ID);
    }

    public static String getFunctionId()
    {
        return ID;
    }

    protected boolean evaluateString(String s, String s1, Map map)
    {
        return s.startsWith(s1);
    }

    private static final String ID;

    static 
    {
        ID = FunctionType.STARTS_WITH.toString();
    }
}
