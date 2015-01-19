// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.android.gms.internal;

import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.lang.reflect.Field;

public abstract class eg
    implements SafeParcelable
{

    public eg()
    {
        Bu = false;
    }

    private static boolean a(Class class1)
    {
        boolean flag;
        try
        {
            flag = "SAFE_PARCELABLE_NULL_STRING".equals(class1.getField("NULL").get(null));
        }
        catch(NoSuchFieldException nosuchfieldexception)
        {
            return false;
        }
        catch(IllegalAccessException illegalaccessexception)
        {
            return false;
        }
        return flag;
    }

    protected static boolean ae(String s)
    {
        ClassLoader classloader = dX();
        if(classloader == null)
            return true;
        boolean flag;
        try
        {
            flag = a(classloader.loadClass(s));
        }
        catch(Exception exception)
        {
            return false;
        }
        return flag;
    }

    protected static ClassLoader dX()
    {
        ClassLoader classloader;
        synchronized(Br)
        {
            classloader = Bs;
        }
        return classloader;
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
    }

    protected static Integer dY()
    {
        Integer integer;
        synchronized(Br)
        {
            integer = Bt;
        }
        return integer;
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
    }

    protected boolean dZ()
    {
        return Bu;
    }

    private static final Object Br = new Object();
    private static ClassLoader Bs = null;
    private static Integer Bt = null;
    private boolean Bu;

}
