// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.tagmanager;

import com.google.analytics.containertag.common.FunctionType;
import java.util.Map;

// Referenced classes of package com.google.tagmanager:
//            FunctionCallImplementation, Runtime, Types

class EventMacro extends FunctionCallImplementation
{

    public EventMacro(Runtime runtime)
    {
        super(ID, new String[0]);
        mRuntime = runtime;
    }

    public static String getFunctionId()
    {
        return ID;
    }

    public com.google.analytics.midtier.proto.containertag.TypeSystem.Value evaluate(Map map)
    {
        String s = mRuntime.getCurrentEventName();
        if(s == null)
            return Types.getDefaultValue();
        else
            return Types.objectToValue(s);
    }

    public boolean isCacheable()
    {
        return false;
    }

    private static final String ID;
    private final Runtime mRuntime;

    static 
    {
        ID = FunctionType.EVENT.toString();
    }
}
