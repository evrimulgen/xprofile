// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.tagmanager;

import com.google.analytics.containertag.common.FunctionType;
import com.google.analytics.containertag.common.Key;
import java.util.Map;

// Referenced classes of package com.google.tagmanager:
//            FunctionCallImplementation, Types, DataLayer

class DataLayerMacro extends FunctionCallImplementation
{

    public DataLayerMacro(DataLayer datalayer)
    {
        String s = ID;
        String as[] = new String[1];
        as[0] = NAME;
        super(s, as);
        mDataLayer = datalayer;
    }

    public static String getDefaultValueKey()
    {
        return DEFAULT_VALUE;
    }

    public static String getFunctionId()
    {
        return ID;
    }

    public static String getNameKey()
    {
        return NAME;
    }

    public com.google.analytics.midtier.proto.containertag.TypeSystem.Value evaluate(Map map)
    {
        Object obj = mDataLayer.get(Types.valueToString((com.google.analytics.midtier.proto.containertag.TypeSystem.Value)map.get(NAME)));
        if(obj == null)
        {
            com.google.analytics.midtier.proto.containertag.TypeSystem.Value value = (com.google.analytics.midtier.proto.containertag.TypeSystem.Value)map.get(DEFAULT_VALUE);
            if(value != null)
                return value;
            else
                return Types.getDefaultValue();
        } else
        {
            return Types.objectToValue(obj);
        }
    }

    public boolean isCacheable()
    {
        return false;
    }

    private static final String DEFAULT_VALUE;
    private static final String ID;
    private static final String NAME;
    private final DataLayer mDataLayer;

    static 
    {
        ID = FunctionType.CUSTOM_VAR.toString();
        NAME = Key.NAME.toString();
        DEFAULT_VALUE = Key.DEFAULT_VALUE.toString();
    }
}
