// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.squareup.otto;


public class DeadEvent
{

    public DeadEvent(Object obj, Object obj1)
    {
        source = obj;
        event = obj1;
    }

    public final Object event;
    public final Object source;
}
