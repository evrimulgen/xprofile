// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.tagmanager;

import android.content.Context;
import com.google.analytics.containertag.common.FunctionType;
import com.google.analytics.containertag.common.Key;
import java.util.Map;

// Referenced classes of package com.google.tagmanager:
//            FunctionCallImplementation, Types, InstallReferrerUtil

class InstallReferrerMacro extends FunctionCallImplementation
{

    public InstallReferrerMacro(Context context1)
    {
        super(ID, new String[0]);
        context = context1;
    }

    public static String getFunctionId()
    {
        return ID;
    }

    public com.google.analytics.midtier.proto.containertag.TypeSystem.Value evaluate(Map map)
    {
        String s;
        String s1;
        if((com.google.analytics.midtier.proto.containertag.TypeSystem.Value)map.get(COMPONENT) != null)
            s = Types.valueToString((com.google.analytics.midtier.proto.containertag.TypeSystem.Value)map.get(COMPONENT));
        else
            s = null;
        s1 = InstallReferrerUtil.getInstallReferrer(context, s);
        if(s1 != null)
            return Types.objectToValue(s1);
        else
            return Types.getDefaultValue();
    }

    public boolean isCacheable()
    {
        return true;
    }

    private static final String COMPONENT;
    private static final String ID;
    private final Context context;

    static 
    {
        ID = FunctionType.INSTALL_REFERRER.toString();
        COMPONENT = Key.COMPONENT.toString();
    }
}
