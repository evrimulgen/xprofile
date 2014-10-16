// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.roadtrippers.events;


public final class CreateAccountButtonClicked extends Enum
{

    private CreateAccountButtonClicked(String s, int i)
    {
        super(s, i);
    }

    public static CreateAccountButtonClicked valueOf(String s)
    {
        return (CreateAccountButtonClicked)Enum.valueOf(com/roadtrippers/events/CreateAccountButtonClicked, s);
    }

    public static CreateAccountButtonClicked[] values()
    {
        return (CreateAccountButtonClicked[])$VALUES.clone();
    }

    private static final CreateAccountButtonClicked $VALUES[];
    public static final CreateAccountButtonClicked INSTANCE;

    static 
    {
        INSTANCE = new CreateAccountButtonClicked("INSTANCE", 0);
        CreateAccountButtonClicked acreateaccountbuttonclicked[] = new CreateAccountButtonClicked[1];
        acreateaccountbuttonclicked[0] = INSTANCE;
        $VALUES = acreateaccountbuttonclicked;
    }
}
