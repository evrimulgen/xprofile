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

class AdwordsClickReferrerMacro extends FunctionCallImplementation
{

    public AdwordsClickReferrerMacro(Context context1)
    {
        String s = ID;
        String as[] = new String[1];
        as[0] = CONVERSION_ID;
        super(s, as);
        context = context1;
    }

    public static String getFunctionId()
    {
        return ID;
    }

    public com.google.analytics.midtier.proto.containertag.TypeSystem.Value evaluate(Map map)
    {
        com.google.analytics.midtier.proto.containertag.TypeSystem.Value value = (com.google.analytics.midtier.proto.containertag.TypeSystem.Value)map.get(CONVERSION_ID);
        if(value == null)
            return Types.getDefaultValue();
        String s = Types.valueToString(value);
        com.google.analytics.midtier.proto.containertag.TypeSystem.Value value1 = (com.google.analytics.midtier.proto.containertag.TypeSystem.Value)map.get(COMPONENT);
        String s1;
        String s2;
        if(value1 != null)
            s1 = Types.valueToString(value1);
        else
            s1 = null;
        s2 = InstallReferrerUtil.getClickReferrer(context, s, s1);
        if(s2 != null)
            return Types.objectToValue(s2);
        else
            return Types.getDefaultValue();
    }

    public boolean isCacheable()
    {
        return true;
    }

    private static final String COMPONENT;
    private static final String CONVERSION_ID;
    private static final String ID;
    private final Context context;

    static 
    {
        ID = FunctionType.ADWORDS_CLICK_REFERRER.toString();
        COMPONENT = Key.COMPONENT.toString();
        CONVERSION_ID = Key.CONVERSION_ID.toString();
    }
}
