// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.pinterest.pinit.exceptions;


public class SourceUrlException extends RuntimeException
{

    public SourceUrlException()
    {
        super("url cannot be null! Did you call setUrl?");
    }

    public static final String MESSAGE = "url cannot be null! Did you call setUrl?";
}
