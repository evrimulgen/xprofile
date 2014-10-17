// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.tagmanager;

import com.google.analytics.containertag.common.FunctionType;
import java.util.Map;

// Referenced classes of package com.google.tagmanager:
//            NumberPredicate, TypedNumber

class GreaterThanPredicate extends NumberPredicate
{

    public GreaterThanPredicate()
    {
        super(ID);
    }

    public static String getFunctionId()
    {
        return ID;
    }

    protected boolean evaluateNumber(TypedNumber typednumber, TypedNumber typednumber1, Map map)
    {
        return typednumber.compareTo(typednumber1) > 0;
    }

    private static final String ID;

    static 
    {
        ID = FunctionType.GREATER_THAN.toString();
    }
}
