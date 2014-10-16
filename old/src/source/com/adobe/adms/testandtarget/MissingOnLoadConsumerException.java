// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.adobe.adms.testandtarget;


public final class MissingOnLoadConsumerException extends Exception
{

    public MissingOnLoadConsumerException()
    {
        super("Mbox.addOnloadConsumer() must be called before loading Mbox.");
    }

    private static final String defaultMessage = "Mbox.addOnloadConsumer() must be called before loading Mbox.";
    private static final long serialVersionUID = 0x2fe0061e145ff9a5L;
}
