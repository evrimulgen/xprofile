// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.roadtrippers.api.models;


public class UserResponse
{

    public UserResponse()
    {
    }

    public void setId(int i)
    {
        user_id = i;
    }

    public String auth_token;
    public String email;
    public int user_id;
    public String username;
}
