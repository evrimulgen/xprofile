// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.tagmanager;

import java.io.File;

// Referenced classes of package com.google.tagmanager:
//            Log

class FutureApis
{

    private FutureApis()
    {
    }

    static boolean setOwnerOnlyReadWrite(String s)
    {
        if(version() < 9)
        {
            return false;
        } else
        {
            File file = new File(s);
            file.setReadable(false, false);
            file.setWritable(false, false);
            file.setReadable(true, true);
            file.setWritable(true, true);
            return true;
        }
    }

    public static int version()
    {
        int i;
        try
        {
            i = Integer.parseInt(android.os.Build.VERSION.SDK);
        }
        catch(NumberFormatException numberformatexception)
        {
            Log.e((new StringBuilder()).append("Invalid version number: ").append(android.os.Build.VERSION.SDK).toString());
            return 0;
        }
        return i;
    }
}
