// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.tagmanager;

import com.google.analytics.containertag.common.FunctionType;
import com.google.analytics.containertag.common.Key;
import java.util.Map;

// Referenced classes of package com.google.tagmanager:
//            FunctionCallImplementation

class ConstantMacro extends FunctionCallImplementation
{

    public ConstantMacro()
    {
        String s = ID;
        String as[] = new String[1];
        as[0] = VALUE;
        super(s, as);
    }

    public static String getFunctionId()
    {
        return ID;
    }

    public static String getValueKey()
    {
        return VALUE;
    }

    public com.google.analytics.midtier.proto.containertag.TypeSystem.Value evaluate(Map map)
    {
        return (com.google.analytics.midtier.proto.containertag.TypeSystem.Value)map.get(VALUE);
    }

    public boolean isCacheable()
    {
        return true;
    }

    private static final String ID;
    private static final String VALUE;

    static 
    {
        ID = FunctionType.CONSTANT.toString();
        VALUE = Key.VALUE.toString();
    }
}
