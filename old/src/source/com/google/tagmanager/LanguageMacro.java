// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.tagmanager;

import com.google.analytics.containertag.common.FunctionType;
import java.util.Locale;
import java.util.Map;

// Referenced classes of package com.google.tagmanager:
//            FunctionCallImplementation, Types

class LanguageMacro extends FunctionCallImplementation
{

    public LanguageMacro()
    {
        super(ID, new String[0]);
    }

    public static String getFunctionId()
    {
        return ID;
    }

    public com.google.analytics.midtier.proto.containertag.TypeSystem.Value evaluate(Map map)
    {
        Locale locale = Locale.getDefault();
        if(locale == null)
            return Types.getDefaultValue();
        String s = locale.getLanguage();
        if(s == null)
            return Types.getDefaultValue();
        else
            return Types.objectToValue(s.toLowerCase());
    }

    public boolean isCacheable()
    {
        return false;
    }

    private static final String ID;

    static 
    {
        ID = FunctionType.LANGUAGE.toString();
    }
}
