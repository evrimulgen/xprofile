// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.crittercism.app;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;
import java.io.*;

public class CrittercismNDK
{

    public CrittercismNDK()
    {
    }

    public static native boolean checkLibraryVersion(int i);

    public static boolean copyLibFromAssets(Context context, File file)
    {
        if(file.exists())
            return true;
        FileOutputStream fileoutputstream;
        InputStream inputstream;
        byte abyte0[];
        file.getParentFile().mkdirs();
        fileoutputstream = new FileOutputStream(file);
        inputstream = getJarredLibFileStream(context);
        abyte0 = new byte[8192];
_L1:
        int i = inputstream.read(abyte0);
label0:
        {
            if(i < 0)
                break label0;
            try
            {
                fileoutputstream.write(abyte0, 0, i);
            }
            catch(Exception exception)
            {
                Log.e("Crittercism", (new StringBuilder("Could not install breakpad library: ")).append(exception.toString()).toString());
                return false;
            }
        }
          goto _L1
        inputstream.close();
        fileoutputstream.close();
        return true;
    }

    public static boolean doNdkSharedLibrariesExist(Context context)
    {
        try
        {
            getJarredLibFileStream(context);
        }
        catch(IOException ioexception)
        {
            return false;
        }
        return true;
    }

    public static File getInstalledLibraryFile(Context context)
    {
        String s = (new StringBuilder()).append(context.getFilesDir().getAbsolutePath()).append("/com.crittercism/lib/").toString();
        return new File((new StringBuilder()).append(s).append("libcrittercism-ndk.so").toString());
    }

    public static InputStream getJarredLibFileStream(Context context)
    {
        String s = "armeabi";
        if(System.getProperty("os.arch").contains("v7"))
            s = (new StringBuilder()).append(s).append("-v7a").toString();
        return context.getAssets().open((new StringBuilder()).append(s).append("/libcrittercism-ndk.so").toString());
    }

    public static native boolean installNdk(String s);

    public static void installNdkLib(Context context, String s)
    {
        String s1;
        boolean flag = true;
        s1 = (new StringBuilder()).append(context.getFilesDir().getAbsolutePath()).append("/").append(s).toString();
        if(doNdkSharedLibrariesExist(context))
            flag = loadLibraryFromAssets(context);
        else
            try
            {
                System.loadLibrary("crittercism-ndk");
            }
            catch(Throwable throwable)
            {
                flag = false;
            }
        if(flag) goto _L2; else goto _L1
_L1:
        Log.e("Crittercism", "Could not load native crash library");
_L4:
        return;
_L2:
        if(!installNdk(s1)) goto _L4; else goto _L3
_L3:
        File file = new File(s1);
        file.getAbsolutePath();
        file.mkdirs();
        isNdkInstalled = true;
        return;
        Throwable throwable1;
        throwable1;
    }

    public static boolean isNdkCrashReportingInstalled()
    {
        return isNdkInstalled;
    }

    public static boolean loadLibraryFromAssets(Context context)
    {
        File file = getInstalledLibraryFile(context);
        if(copyLibFromAssets(context, file)) goto _L2; else goto _L1
_L1:
        return false;
_L2:
        boolean flag1;
        System.load(file.getAbsolutePath());
        flag1 = checkLibraryVersion(2);
        boolean flag = flag1;
_L4:
        if(flag)
            break MISSING_BLOCK_LABEL_50;
        if(!copyLibFromAssets(context, file)) goto _L1; else goto _L3
_L3:
        Throwable throwable;
        try
        {
            System.load(file.getAbsolutePath());
        }
        catch(Throwable throwable1)
        {
            Log.e("Crittercism", (new StringBuilder("Unable to load breakpad")).append(throwable1.toString()).toString());
            return false;
        }
        return true;
        throwable;
        flag = false;
          goto _L4
    }

    private static final String LIBNAME = "libcrittercism-ndk.so";
    private static final int LIBRARY_VERSION = 2;
    private static boolean isNdkInstalled = false;

}
