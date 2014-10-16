// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.tagmanager;

import android.os.Build;
import com.google.analytics.containertag.common.FunctionType;
import java.util.Map;

// Referenced classes of package com.google.tagmanager:
//            FunctionCallImplementation, Types

class DeviceNameMacro extends FunctionCallImplementation
{

    public DeviceNameMacro()
    {
        super(ID, new String[0]);
    }

    public static String getFunctionId()
    {
        return ID;
    }

    public com.google.analytics.midtier.proto.containertag.TypeSystem.Value evaluate(Map map)
    {
        String s = Build.MANUFACTURER;
        String s1 = Build.MODEL;
        if(!s1.startsWith(s) && !s.equals("unknown"))
            s1 = (new StringBuilder()).append(s).append(" ").append(s1).toString();
        return Types.objectToValue(s1);
    }

    public boolean isCacheable()
    {
        return true;
    }

    private static final String ID;

    static 
    {
        ID = FunctionType.DEVICE_NAME.toString();
    }
}
