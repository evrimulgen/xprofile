// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.pinterest.pinit.exceptions;


public class ImageException extends RuntimeException
{

    public ImageException()
    {
        super("imageUrl and/or imageUri cannot be null! Did you call setImageUrl(String) or setImageUri(Uri)?");
    }

    public static final String MESSAGE = "imageUrl and/or imageUri cannot be null! Did you call setImageUrl(String) or setImageUri(Uri)?";
}
