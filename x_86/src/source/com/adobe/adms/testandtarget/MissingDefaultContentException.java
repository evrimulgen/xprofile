// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.adobe.adms.testandtarget;


public final class MissingDefaultContentException extends Exception
{

    public MissingDefaultContentException()
    {
        super("Mbox.setDefaultContent() must be called before loading Mbox.");
    }

    private static final String defaultMessage = "Mbox.setDefaultContent() must be called before loading Mbox.";
    private static final long serialVersionUID = 0xf20d97a1a33fae50L;
}
