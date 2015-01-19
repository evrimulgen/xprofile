// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.squareup.otto;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

class EventHandler
{

    EventHandler(Object obj, Method method1)
    {
        valid = true;
        if(obj == null)
            throw new NullPointerException("EventHandler target cannot be null.");
        if(method1 == null)
        {
            throw new NullPointerException("EventHandler method cannot be null.");
        } else
        {
            target = obj;
            method = method1;
            method1.setAccessible(true);
            hashCode = 31 * (31 + method1.hashCode()) + obj.hashCode();
            return;
        }
    }

    public boolean equals(Object obj)
    {
        if(this != obj)
        {
            if(obj == null)
                return false;
            if(getClass() != obj.getClass())
                return false;
            EventHandler eventhandler = (EventHandler)obj;
            if(!method.equals(eventhandler.method) || target != eventhandler.target)
                return false;
        }
        return true;
    }

    public void handleEvent(Object obj)
        throws InvocationTargetException
    {
        if(!valid)
            throw new IllegalStateException((new StringBuilder()).append(toString()).append(" has been invalidated and can no longer handle events.").toString());
        try
        {
            method.invoke(target, new Object[] {
                obj
            });
            return;
        }
        catch(IllegalAccessException illegalaccessexception)
        {
            throw new AssertionError(illegalaccessexception);
        }
        catch(InvocationTargetException invocationtargetexception)
        {
            if(invocationtargetexception.getCause() instanceof Error)
                throw (Error)invocationtargetexception.getCause();
            else
                throw invocationtargetexception;
        }
    }

    public int hashCode()
    {
        return hashCode;
    }

    public void invalidate()
    {
        valid = false;
    }

    public boolean isValid()
    {
        return valid;
    }

    public String toString()
    {
        return (new StringBuilder()).append("[EventHandler ").append(method).append("]").toString();
    }

    private final int hashCode;
    private final Method method;
    private final Object target;
    private boolean valid;
}
