// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.tagmanager;

import com.google.analytics.containertag.common.FunctionType;
import java.util.Map;

// Referenced classes of package com.google.tagmanager:
//            StringPredicate

class EqualsPredicate extends StringPredicate
{

    public EqualsPredicate()
    {
        super(ID);
    }

    public static String getFunctionId()
    {
        return ID;
    }

    protected boolean evaluateString(String s, String s1, Map map)
    {
        return s.equals(s1);
    }

    private static final String ID;

    static 
    {
        ID = FunctionType.EQUALS.toString();
    }
}
