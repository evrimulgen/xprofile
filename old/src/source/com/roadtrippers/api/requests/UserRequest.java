// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.roadtrippers.api.requests;


public class UserRequest
{

    public UserRequest(String s, String s1, String s2, String s3)
    {
        username = s;
        email = s1;
        password = s2;
        password_confirmation = s3;
    }

    public String email;
    public String password;
    public String password_confirmation;
    public String username;
}
