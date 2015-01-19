// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.tagmanager;

import com.google.analytics.containertag.common.FunctionType;
import com.google.analytics.containertag.common.Key;
import java.util.Map;

// Referenced classes of package com.google.tagmanager:
//            FunctionCallImplementation, Types, Base16, Base64Encoder, 
//            Log

class EncodeMacro extends FunctionCallImplementation
{

    public EncodeMacro()
    {
        String s = ID;
        String as[] = new String[1];
        as[0] = ARG0;
        super(s, as);
    }

    public static String getFunctionId()
    {
        return ID;
    }

    public com.google.analytics.midtier.proto.containertag.TypeSystem.Value evaluate(Map map)
    {
        String s;
        String s1;
        String s2;
        int i;
        byte abyte0[];
        String s3;
        com.google.analytics.midtier.proto.containertag.TypeSystem.Value value = (com.google.analytics.midtier.proto.containertag.TypeSystem.Value)map.get(ARG0);
        if(value == null || value == Types.getDefaultValue())
            return Types.getDefaultValue();
        s = Types.valueToString(value);
        com.google.analytics.midtier.proto.containertag.TypeSystem.Value value1 = (com.google.analytics.midtier.proto.containertag.TypeSystem.Value)map.get(INPUT_FORMAT);
        com.google.analytics.midtier.proto.containertag.TypeSystem.Value value2;
        com.google.analytics.midtier.proto.containertag.TypeSystem.Value value3;
        byte abyte1[];
        if(value1 == null)
            s1 = "text";
        else
            s1 = Types.valueToString(value1);
        value2 = (com.google.analytics.midtier.proto.containertag.TypeSystem.Value)map.get(OUTPUT_FORMAT);
        if(value2 == null)
            s2 = "base16";
        else
            s2 = Types.valueToString(value2);
        (com.google.analytics.midtier.proto.containertag.TypeSystem.Value)map.get(INPUT_FORMAT);
        value3 = (com.google.analytics.midtier.proto.containertag.TypeSystem.Value)map.get(NO_PADDING);
        i = 0;
        if(value3 != null)
        {
            boolean flag = Types.valueToBoolean(value3).booleanValue();
            i = 0;
            if(flag)
                i = false | true;
        }
        if(!"text".equals(s1)) goto _L2; else goto _L1
_L1:
        abyte1 = s.getBytes();
        abyte0 = abyte1;
_L8:
        if(!"base16".equals(s2)) goto _L4; else goto _L3
_L3:
        s3 = Base16.encode(abyte0);
_L6:
        return Types.objectToValue(s3);
_L2:
        if("base16".equals(s1))
        {
            abyte0 = Base16.decode(s);
            continue; /* Loop/switch isn't completed */
        }
        if("base64".equals(s1))
        {
            abyte0 = Base64Encoder.decode(s, i);
            continue; /* Loop/switch isn't completed */
        }
        if("base64url".equals(s1))
        {
            abyte0 = Base64Encoder.decode(s, i | 2);
            continue; /* Loop/switch isn't completed */
        } else
        {
            com.google.analytics.midtier.proto.containertag.TypeSystem.Value value4;
            try
            {
                Log.e((new StringBuilder()).append("Encode: unknown input format: ").append(s1).toString());
                value4 = Types.getDefaultValue();
            }
            catch(IllegalArgumentException illegalargumentexception)
            {
                Log.e("Encode: invalid input:");
                return Types.getDefaultValue();
            }
            return value4;
        }
_L4:
        if("base64".equals(s2))
        {
            s3 = Base64Encoder.encodeToString(abyte0, i);
            continue; /* Loop/switch isn't completed */
        }
        if(!"base64url".equals(s2))
            break; /* Loop/switch isn't completed */
        s3 = Base64Encoder.encodeToString(abyte0, i | 2);
        if(true) goto _L6; else goto _L5
_L5:
        Log.e((new StringBuilder()).append("Encode: unknown output format: ").append(s2).toString());
        return Types.getDefaultValue();
        if(true) goto _L8; else goto _L7
_L7:
    }

    public boolean isCacheable()
    {
        return true;
    }

    private static final String ARG0;
    private static final String DEFAULT_INPUT_FORMAT = "text";
    private static final String DEFAULT_OUTPUT_FORMAT = "base16";
    private static final String ID;
    private static final String INPUT_FORMAT;
    private static final String NO_PADDING;
    private static final String OUTPUT_FORMAT;

    static 
    {
        ID = FunctionType.ENCODE.toString();
        ARG0 = Key.ARG0.toString();
        NO_PADDING = Key.NO_PADDING.toString();
        INPUT_FORMAT = Key.INPUT_FORMAT.toString();
        OUTPUT_FORMAT = Key.OUTPUT_FORMAT.toString();
    }
}
