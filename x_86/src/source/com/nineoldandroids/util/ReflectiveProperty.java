// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.nineoldandroids.util;

import java.lang.reflect.*;

// Referenced classes of package com.nineoldandroids.util:
//            Property, NoSuchPropertyException

class ReflectiveProperty extends Property
{

    public ReflectiveProperty(Class class1, Class class2, String s)
    {
        Class class4;
        String s5;
        super(class2, s);
        char c = Character.toUpperCase(s.charAt(0));
        String s1 = s.substring(1);
        String s2 = (new StringBuilder()).append(c).append(s1).toString();
        String s3 = (new StringBuilder()).append("get").append(s2).toString();
        try
        {
            mGetter = class1.getMethod(s3, (Class[])null);
        }
        catch(NoSuchMethodException nosuchmethodexception)
        {
            try
            {
                mGetter = class1.getDeclaredMethod(s3, (Class[])null);
                mGetter.setAccessible(true);
            }
            catch(NoSuchMethodException nosuchmethodexception1)
            {
                String s4 = (new StringBuilder()).append("is").append(s2).toString();
                try
                {
                    mGetter = class1.getMethod(s4, (Class[])null);
                }
                catch(NoSuchMethodException nosuchmethodexception2)
                {
                    try
                    {
                        mGetter = class1.getDeclaredMethod(s4, (Class[])null);
                        mGetter.setAccessible(true);
                    }
                    catch(NoSuchMethodException nosuchmethodexception3)
                    {
                        try
                        {
                            mField = class1.getField(s);
                            Class class3 = mField.getType();
                            if(!typesMatch(class2, class3))
                                throw new NoSuchPropertyException((new StringBuilder()).append("Underlying type (").append(class3).append(") ").append("does not match Property type (").append(class2).append(")").toString());
                        }
                        catch(NoSuchFieldException nosuchfieldexception)
                        {
                            throw new NoSuchPropertyException((new StringBuilder()).append("No accessor method or field found for property with name ").append(s).toString());
                        }
                        break MISSING_BLOCK_LABEL_397;
                    }
                }
            }
        }
        class4 = mGetter.getReturnType();
        if(!typesMatch(class2, class4))
            throw new NoSuchPropertyException((new StringBuilder()).append("Underlying type (").append(class4).append(") ").append("does not match Property type (").append(class2).append(")").toString());
        s5 = (new StringBuilder()).append("set").append(s2).toString();
        mSetter = class1.getDeclaredMethod(s5, new Class[] {
            class4
        });
        mSetter.setAccessible(true);
        return;
        NoSuchMethodException nosuchmethodexception4;
        nosuchmethodexception4;
    }

    private boolean typesMatch(Class class1, Class class2)
    {
label0:
        {
            boolean flag1;
label1:
            {
                if(class2 == class1)
                    break label0;
                boolean flag = class2.isPrimitive();
                flag1 = false;
                if(!flag)
                    break label1;
                if((class2 != Float.TYPE || class1 != java/lang/Float) && (class2 != Integer.TYPE || class1 != java/lang/Integer) && (class2 != Boolean.TYPE || class1 != java/lang/Boolean) && (class2 != Long.TYPE || class1 != java/lang/Long) && (class2 != Double.TYPE || class1 != java/lang/Double) && (class2 != Short.TYPE || class1 != java/lang/Short) && (class2 != Byte.TYPE || class1 != java/lang/Byte))
                {
                    Class class3 = Character.TYPE;
                    flag1 = false;
                    if(class2 != class3)
                        break label1;
                    flag1 = false;
                    if(class1 != java/lang/Character)
                        break label1;
                }
                flag1 = true;
            }
            return flag1;
        }
        return true;
    }

    public Object get(Object obj)
    {
        if(mGetter != null)
        {
            Object obj2;
            try
            {
                obj2 = mGetter.invoke(obj, (Object[])null);
            }
            catch(IllegalAccessException illegalaccessexception1)
            {
                throw new AssertionError();
            }
            catch(InvocationTargetException invocationtargetexception)
            {
                throw new RuntimeException(invocationtargetexception.getCause());
            }
            return obj2;
        }
        if(mField != null)
        {
            Object obj1;
            try
            {
                obj1 = mField.get(obj);
            }
            catch(IllegalAccessException illegalaccessexception)
            {
                throw new AssertionError();
            }
            return obj1;
        } else
        {
            throw new AssertionError();
        }
    }

    public boolean isReadOnly()
    {
        return mSetter == null && mField == null;
    }

    public void set(Object obj, Object obj1)
    {
        if(mSetter != null)
            try
            {
                mSetter.invoke(obj, new Object[] {
                    obj1
                });
                return;
            }
            catch(IllegalAccessException illegalaccessexception1)
            {
                throw new AssertionError();
            }
            catch(InvocationTargetException invocationtargetexception)
            {
                throw new RuntimeException(invocationtargetexception.getCause());
            }
        if(mField != null)
            try
            {
                mField.set(obj, obj1);
                return;
            }
            catch(IllegalAccessException illegalaccessexception)
            {
                throw new AssertionError();
            }
        else
            throw new UnsupportedOperationException((new StringBuilder()).append("Property ").append(getName()).append(" is read-only").toString());
    }

    private static final String PREFIX_GET = "get";
    private static final String PREFIX_IS = "is";
    private static final String PREFIX_SET = "set";
    private Field mField;
    private Method mGetter;
    private Method mSetter;
}
