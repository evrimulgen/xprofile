// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.squareup.otto;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

class EventProducer
{

    EventProducer(Object obj, Method method1)
    {
        valid = true;
        if(obj == null)
            throw new NullPointerException("EventProducer target cannot be null.");
        if(method1 == null)
        {
            throw new NullPointerException("EventProducer method cannot be null.");
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
            EventProducer eventproducer = (EventProducer)obj;
            if(!method.equals(eventproducer.method) || target != eventproducer.target)
                return false;
        }
        return true;
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

    public Object produceEvent()
        throws InvocationTargetException
    {
        if(!valid)
            throw new IllegalStateException((new StringBuilder()).append(toString()).append(" has been invalidated and can no longer produce events.").toString());
        Object obj;
        try
        {
            obj = method.invoke(target, new Object[0]);
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
        return obj;
    }

    public String toString()
    {
        return (new StringBuilder()).append("[EventProducer ").append(method).append("]").toString();
    }

    private final int hashCode;
    private final Method method;
    final Object target;
    private boolean valid;
}
