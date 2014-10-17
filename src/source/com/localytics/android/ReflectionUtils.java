// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.localytics.android;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public final class ReflectionUtils
{

    private ReflectionUtils()
    {
        throw new UnsupportedOperationException("This class is non-instantiable");
    }

    private static Object helper(Object obj, Class class1, String s, String s1, Class aclass[], Object aobj[])
    {
        if(class1 == null) goto _L2; else goto _L1
_L1:
        Class class2 = class1;
_L3:
        Class class3;
        try
        {
            return class2.getMethod(s1, aclass).invoke(obj, aobj);
        }
        catch(NoSuchMethodException nosuchmethodexception)
        {
            throw new RuntimeException(nosuchmethodexception);
        }
        catch(IllegalAccessException illegalaccessexception)
        {
            throw new RuntimeException(illegalaccessexception);
        }
        catch(InvocationTargetException invocationtargetexception)
        {
            throw new RuntimeException(invocationtargetexception);
        }
        catch(ClassNotFoundException classnotfoundexception)
        {
            throw new RuntimeException(classnotfoundexception);
        }
_L2:
        if(obj == null)
            break MISSING_BLOCK_LABEL_35;
        class2 = obj.getClass();
          goto _L3
        class3 = Class.forName(s);
        class2 = class3;
          goto _L3
    }

    public static Object tryInvokeInstance(Object obj, String s, Class aclass[], Object aobj[])
    {
        return helper(obj, null, null, s, aclass, aobj);
    }

    public static Object tryInvokeStatic(Class class1, String s, Class aclass[], Object aobj[])
    {
        return helper(null, class1, null, s, aclass, aobj);
    }

    public static Object tryInvokeStatic(String s, String s1, Class aclass[], Object aobj[])
    {
        return helper(s, null, null, s1, aclass, aobj);
    }
}
