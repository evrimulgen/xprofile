// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.tagmanager;

import com.google.analytics.containertag.common.FunctionType;
import com.google.analytics.containertag.common.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

// Referenced classes of package com.google.tagmanager:
//            FunctionCallImplementation, Types, Base16, Log

class HashMacro extends FunctionCallImplementation
{

    public HashMacro()
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

    private byte[] hash(String s, byte abyte0[])
        throws NoSuchAlgorithmException
    {
        MessageDigest messagedigest = MessageDigest.getInstance(s);
        messagedigest.update(abyte0);
        return messagedigest.digest();
    }

    public com.google.analytics.midtier.proto.containertag.TypeSystem.Value evaluate(Map map)
    {
        com.google.analytics.midtier.proto.containertag.TypeSystem.Value value = (com.google.analytics.midtier.proto.containertag.TypeSystem.Value)map.get(ARG0);
        if(value == null || value == Types.getDefaultValue())
            return Types.getDefaultValue();
        String s = Types.valueToString(value);
        com.google.analytics.midtier.proto.containertag.TypeSystem.Value value1 = (com.google.analytics.midtier.proto.containertag.TypeSystem.Value)map.get(ALGORITHM);
        String s1;
        com.google.analytics.midtier.proto.containertag.TypeSystem.Value value2;
        String s2;
        byte abyte0[];
        com.google.analytics.midtier.proto.containertag.TypeSystem.Value value3;
        if(value1 == null)
            s1 = "MD5";
        else
            s1 = Types.valueToString(value1);
        value2 = (com.google.analytics.midtier.proto.containertag.TypeSystem.Value)map.get(INPUT_FORMAT);
        if(value2 == null)
            s2 = "text";
        else
            s2 = Types.valueToString(value2);
        if("text".equals(s2))
            abyte0 = s.getBytes();
        else
        if("base16".equals(s2))
        {
            abyte0 = Base16.decode(s);
        } else
        {
            Log.e((new StringBuilder()).append("Hash: unknown input format: ").append(s2).toString());
            return Types.getDefaultValue();
        }
        try
        {
            value3 = Types.objectToValue(Base16.encode(hash(s1, abyte0)));
        }
        catch(NoSuchAlgorithmException nosuchalgorithmexception)
        {
            Log.e((new StringBuilder()).append("Hash: unknown algorithm: ").append(s1).toString());
            return Types.getDefaultValue();
        }
        return value3;
    }

    public boolean isCacheable()
    {
        return true;
    }

    private static final String ALGORITHM;
    private static final String ARG0;
    private static final String DEFAULT_ALGORITHM = "MD5";
    private static final String DEFAULT_INPUT_FORMAT = "text";
    private static final String ID;
    private static final String INPUT_FORMAT;

    static 
    {
        ID = FunctionType.HASH.toString();
        ARG0 = Key.ARG0.toString();
        ALGORITHM = Key.ALGORITHM.toString();
        INPUT_FORMAT = Key.INPUT_FORMAT.toString();
    }
}
