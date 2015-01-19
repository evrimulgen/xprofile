// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.pinterest.pinit.exceptions;


public class NotInstalledException extends RuntimeException
{

    public NotInstalledException()
    {
        super("Pinterest for Android is not installed!");
    }

    public static final String MESSAGE = "Pinterest for Android is not installed!";
}
