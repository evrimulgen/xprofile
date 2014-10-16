// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.roadtrippers.api.requests;


public class LoginRequest
{

    public LoginRequest(String s, String s1)
    {
        username_or_email = s.trim();
        password = s1.trim();
    }

    public String password;
    public String username_or_email;
}
