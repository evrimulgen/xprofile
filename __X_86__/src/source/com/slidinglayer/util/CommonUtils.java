// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.slidinglayer.util;

import java.util.Random;

public class CommonUtils
{

    public CommonUtils()
    {
    }

    public static boolean getNextRandomBoolean()
    {
        if(mRandom == null)
            mRandom = new Random();
        return mRandom.nextBoolean();
    }

    private static Random mRandom;
}
