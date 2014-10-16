// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package crittercism.android;

import java.io.File;

// Referenced classes of package crittercism.android:
//            bo, bj

public final class bg extends bo
{

    public bg(boolean flag)
    {
        a = flag;
    }

    public final File a(bj bj1)
    {
        File afile[] = bj1.b();
        File file;
        if(!a)
        {
            int j = afile.length;
            file = null;
            if(j > 0)
                file = afile[0];
        } else
        {
            int i = afile.length;
            file = null;
            if(i >= 2)
                return afile[1];
        }
        return file;
    }

    private boolean a;
}
