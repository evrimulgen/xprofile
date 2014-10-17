// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.roadtrippers.events;


public final class LoginButtonClicked extends Enum
{

    private LoginButtonClicked(String s, int i)
    {
        super(s, i);
    }

    public static LoginButtonClicked valueOf(String s)
    {
        return (LoginButtonClicked)Enum.valueOf(com/roadtrippers/events/LoginButtonClicked, s);
    }

    public static LoginButtonClicked[] values()
    {
        return (LoginButtonClicked[])$VALUES.clone();
    }

    private static final LoginButtonClicked $VALUES[];
    public static final LoginButtonClicked INSTANCE;

    static 
    {
        INSTANCE = new LoginButtonClicked("INSTANCE", 0);
        LoginButtonClicked aloginbuttonclicked[] = new LoginButtonClicked[1];
        aloginbuttonclicked[0] = INSTANCE;
        $VALUES = aloginbuttonclicked;
    }
}
