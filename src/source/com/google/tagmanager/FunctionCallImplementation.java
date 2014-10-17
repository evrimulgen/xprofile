// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.tagmanager;

import java.util.*;

abstract class FunctionCallImplementation
{

    public transient FunctionCallImplementation(String s, String as[])
    {
        mFunctionId = s;
        mRequiredKeys = new HashSet(as.length);
        int i = as.length;
        for(int j = 0; j < i; j++)
        {
            String s1 = as[j];
            mRequiredKeys.add(s1);
        }

    }

    public static String getFunctionKey()
    {
        return "function";
    }

    public abstract com.google.analytics.midtier.proto.containertag.TypeSystem.Value evaluate(Map map);

    public String getInstanceFunctionId()
    {
        return mFunctionId;
    }

    public Set getRequiredKeys()
    {
        return mRequiredKeys;
    }

    boolean hasRequiredKeys(Set set)
    {
        return set.containsAll(mRequiredKeys);
    }

    public abstract boolean isCacheable();

    private static final String FUNCTION_KEY = "function";
    private final String mFunctionId;
    private final Set mRequiredKeys;
}
