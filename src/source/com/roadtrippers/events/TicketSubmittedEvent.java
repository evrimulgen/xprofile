// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.roadtrippers.events;

import org.json.JSONObject;

public class TicketSubmittedEvent
{

    public TicketSubmittedEvent(Throwable throwable1)
    {
        throwable = throwable1;
    }

    public TicketSubmittedEvent(JSONObject jsonobject)
    {
    }

    public boolean isOk()
    {
        return throwable == null;
    }

    Throwable throwable;
}
