// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.roadtrippers.events;

import com.roadtrippers.api.models.User;

public class UserUpdatedEvent
{

    public UserUpdatedEvent(User user1)
    {
        user = user1;
    }

    public UserUpdatedEvent(Throwable throwable1)
    {
        throwable = throwable1;
    }

    public boolean isOk()
    {
        boolean flag = true;
        boolean flag1;
        if(user != null)
            flag1 = flag;
        else
            flag1 = false;
        if(throwable != null)
            flag = false;
        return flag1 & flag;
    }

    public Throwable throwable;
    public User user;
}
