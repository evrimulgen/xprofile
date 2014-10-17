// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.tagmanager;

import com.google.analytics.containertag.common.FunctionType;
import java.util.Map;

// Referenced classes of package com.google.tagmanager:
//            FunctionCallImplementation, Types

class RuntimeVersionMacro extends FunctionCallImplementation
{

    public RuntimeVersionMacro()
    {
        super(ID, new String[0]);
    }

    public static String getFunctionId()
    {
        return ID;
    }

    public com.google.analytics.midtier.proto.containertag.TypeSystem.Value evaluate(Map map)
    {
        return Types.objectToValue(Long.valueOf(0x306f73aL));
    }

    public boolean isCacheable()
    {
        return true;
    }

    private static final String ID;
    public static final long VERSION_NUMBER = 0x306f73aL;

    static 
    {
        ID = FunctionType.RUNTIME_VERSION.toString();
    }
}
