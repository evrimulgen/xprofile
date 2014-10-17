// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.roadtrippers.util;

import android.content.Context;
import android.os.Environment;
import java.io.File;

public class DiskUtils
{

    public DiskUtils()
    {
    }

    public static File cacheDirNamed(Context context, String s)
    {
        File file = new File(getCacheDir(context), s);
        if(!file.exists())
            file.mkdirs();
        return file;
    }

    public static File getCacheDir(Context context)
    {
        if("mounted".equals(Environment.getExternalStorageState()))
            return context.getExternalCacheDir();
        else
            return context.getCacheDir();
    }

    public static File getFilesDir(Context context)
    {
        if("mounted".equals(Environment.getExternalStorageState()))
            return context.getExternalFilesDir(null);
        else
            return context.getFilesDir();
    }
}
