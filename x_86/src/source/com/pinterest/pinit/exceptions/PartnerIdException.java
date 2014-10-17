// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.pinterest.pinit.exceptions;


public class PartnerIdException extends RuntimeException
{

    public PartnerIdException()
    {
        super("partnerId cannot be null! Did you call setPartnerId?");
    }

    public static final String MESSAGE = "partnerId cannot be null! Did you call setPartnerId?";
}
