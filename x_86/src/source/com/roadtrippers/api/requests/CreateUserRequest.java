// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.roadtrippers.api.requests;


// Referenced classes of package com.roadtrippers.api.requests:
//            UserRequest

public class CreateUserRequest extends UserRequest
{

    public CreateUserRequest(String s, String s1, String s2, String s3, int i)
    {
        super(s, s1, s2, s3);
        terms_of_service = i;
    }

    public int terms_of_service;
}
