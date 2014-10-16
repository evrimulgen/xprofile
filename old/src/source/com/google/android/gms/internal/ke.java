// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.android.gms.internal;

import java.io.IOException;

// Referenced classes of package com.google.android.gms.internal:
//            kd, jz, jy, kf

public abstract class ke
{

    public ke()
    {
        DY = -1;
    }

    public static final ke a(ke ke1, byte abyte0[])
        throws kd
    {
        return b(ke1, abyte0, 0, abyte0.length);
    }

    public static final void a(ke ke1, byte abyte0[], int i, int j)
    {
        try
        {
            jz jz1 = jz.b(abyte0, i, j);
            ke1.a(jz1);
            jz1.kN();
            return;
        }
        catch(IOException ioexception)
        {
            throw new RuntimeException("Serializing to a byte array threw an IOException (should never happen).", ioexception);
        }
    }

    public static final ke b(ke ke1, byte abyte0[], int i, int j)
        throws kd
    {
        try
        {
            jy jy1 = jy.a(abyte0, i, j);
            ke1.b(jy1);
            jy1.cu(0);
        }
        catch(kd kd1)
        {
            throw kd1;
        }
        catch(IOException ioexception)
        {
            throw new RuntimeException("Reading from a byte array threw an IOException (should never happen).");
        }
        return ke1;
    }

    public static final byte[] d(ke ke1)
    {
        byte abyte0[] = new byte[ke1.c()];
        a(ke1, abyte0, 0, abyte0.length);
        return abyte0;
    }

    public void a(jz jz1)
        throws IOException
    {
    }

    public abstract ke b(jy jy1)
        throws IOException;

    public int c()
    {
        DY = 0;
        return 0;
    }

    public int eW()
    {
        if(DY < 0)
            c();
        return DY;
    }

    public String toString()
    {
        return kf.e(this);
    }

    protected int DY;
}
