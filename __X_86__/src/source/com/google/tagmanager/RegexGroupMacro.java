// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.tagmanager;

import com.google.analytics.containertag.common.FunctionType;
import com.google.analytics.containertag.common.Key;
import java.util.Map;
import java.util.regex.*;

// Referenced classes of package com.google.tagmanager:
//            FunctionCallImplementation, Types

class RegexGroupMacro extends FunctionCallImplementation
{

    public RegexGroupMacro()
    {
        String s = ID;
        String as[] = new String[2];
        as[0] = TO_MATCH;
        as[1] = REGEX;
        super(s, as);
    }

    public static String getFunctionId()
    {
        return ID;
    }

    public com.google.analytics.midtier.proto.containertag.TypeSystem.Value evaluate(Map map)
    {
        com.google.analytics.midtier.proto.containertag.TypeSystem.Value value = (com.google.analytics.midtier.proto.containertag.TypeSystem.Value)map.get(TO_MATCH);
        com.google.analytics.midtier.proto.containertag.TypeSystem.Value value1 = (com.google.analytics.midtier.proto.containertag.TypeSystem.Value)map.get(REGEX);
        if(value == null || value == Types.getDefaultValue() || value1 == null || value1 == Types.getDefaultValue())
            return Types.getDefaultValue();
        int i = 64;
        if(Types.valueToBoolean((com.google.analytics.midtier.proto.containertag.TypeSystem.Value)map.get(IGNORE_CASE)).booleanValue())
            i |= 2;
        int j = 1;
        com.google.analytics.midtier.proto.containertag.TypeSystem.Value value2 = (com.google.analytics.midtier.proto.containertag.TypeSystem.Value)map.get(GROUP);
        if(value2 != null)
        {
            Long long1 = Types.valueToInt64(value2);
            if(long1 == Types.getDefaultInt64())
                return Types.getDefaultValue();
            j = long1.intValue();
            if(j < 0)
                return Types.getDefaultValue();
        }
        Matcher matcher;
        boolean flag;
        String s1;
        int k;
        com.google.analytics.midtier.proto.containertag.TypeSystem.Value value3;
        try
        {
            String s = Types.valueToString(value);
            matcher = Pattern.compile(Types.valueToString(value1), i).matcher(s);
            flag = matcher.find();
        }
        catch(PatternSyntaxException patternsyntaxexception)
        {
            return Types.getDefaultValue();
        }
        s1 = null;
        if(!flag)
            break MISSING_BLOCK_LABEL_203;
        k = matcher.groupCount();
        s1 = null;
        if(k < j)
            break MISSING_BLOCK_LABEL_203;
        s1 = matcher.group(j);
        if(s1 != null)
            break MISSING_BLOCK_LABEL_212;
        return Types.getDefaultValue();
        value3 = Types.objectToValue(s1);
        return value3;
    }

    public boolean isCacheable()
    {
        return true;
    }

    private static final String GROUP;
    private static final String ID;
    private static final String IGNORE_CASE;
    private static final String REGEX;
    private static final String TO_MATCH;

    static 
    {
        ID = FunctionType.REGEX_GROUP.toString();
        TO_MATCH = Key.ARG0.toString();
        REGEX = Key.ARG1.toString();
        IGNORE_CASE = Key.IGNORE_CASE.toString();
        GROUP = Key.GROUP.toString();
    }
}
