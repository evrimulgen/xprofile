// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.roadtrippers.api.requests;


// Referenced classes of package com.roadtrippers.api.requests:
//            UserRequest

public class UpdateUserRequest extends UserRequest
{

    public UpdateUserRequest(String s, String s1, String s2, String s3, String s4)
    {
        super(s, s1, s2, s3);
        image_data = s4;
    }

    public String image_data;
}
