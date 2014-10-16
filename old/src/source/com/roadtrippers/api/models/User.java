// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.roadtrippers.api.models;


// Referenced classes of package com.roadtrippers.api.models:
//            Errors

public class User
{

    public User()
    {
    }

    public User merge(User user)
    {
        username = user.username;
        email = user.email;
        return this;
    }

    public String email;
    public Errors errors;
    public int id;
    public String image_iphone_url;
    public String username;
}
