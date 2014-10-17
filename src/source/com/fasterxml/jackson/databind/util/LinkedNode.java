// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.fasterxml.jackson.databind.util;


public final class LinkedNode
{

    public LinkedNode(Object obj, LinkedNode linkednode)
    {
        _value = obj;
        _next = linkednode;
    }

    public static boolean contains(LinkedNode linkednode, Object obj)
    {
        for(; linkednode != null; linkednode = linkednode.next())
            if(linkednode.value() == obj)
                return true;

        return false;
    }

    public LinkedNode next()
    {
        return _next;
    }

    public Object value()
    {
        return _value;
    }

    final LinkedNode _next;
    final Object _value;
}
