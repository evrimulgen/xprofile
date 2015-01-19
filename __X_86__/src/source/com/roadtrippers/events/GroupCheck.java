// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.roadtrippers.events;

import com.roadtrippers.api.models.Group;

public class GroupCheck
{

    public GroupCheck(Group agroup[])
    {
        groups = agroup;
    }

    public Group[] getGroups()
    {
        return groups;
    }

    private final Group groups[];
}
