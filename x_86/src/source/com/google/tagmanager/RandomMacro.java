// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.tagmanager;

import com.google.analytics.containertag.common.FunctionType;
import java.util.Map;

// Referenced classes of package com.google.tagmanager:
//            FunctionCallImplementation, Types

class RandomMacro extends FunctionCallImplementation
{

    public RandomMacro()
    {
        super(ID, new String[0]);
    }

    public static String getFunctionId()
    {
        return ID;
    }

    public com.google.analytics.midtier.proto.containertag.TypeSystem.Value evaluate(Map map)
    {
        return Types.objectToValue(Long.valueOf(Math.round(2147483647D * Math.random())));
    }

    public boolean isCacheable()
    {
        return false;
    }

    private static final String ID;

    static 
    {
        ID = FunctionType.RANDOM.toString();
    }
}
