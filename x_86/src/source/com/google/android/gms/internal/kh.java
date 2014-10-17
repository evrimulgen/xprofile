// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.android.gms.internal;

import java.io.IOException;

// Referenced classes of package com.google.android.gms.internal:
//            jy

public final class kh
{

    public static boolean b(jy jy1, int j)
        throws IOException
    {
        return jy1.cv(j);
    }

    public static final int c(jy jy1, int j)
        throws IOException
    {
        int k = 1;
        int l = jy1.getPosition();
        jy1.cv(j);
        do
        {
            if(jy1.kJ() <= 0 || jy1.ky() != j)
            {
                jy1.cy(l);
                return k;
            }
            jy1.cv(j);
            k++;
        } while(true);
    }

    static int cJ(int j)
    {
        return j & 7;
    }

    public static int cK(int j)
    {
        return j >>> 3;
    }

    static int i(int j, int k)
    {
        return k | j << 3;
    }

    public static final int aaj[] = new int[0];
    public static final long aak[] = new long[0];
    public static final float aal[] = new float[0];
    public static final double aam[] = new double[0];
    public static final boolean aan[] = new boolean[0];
    public static final String aao[] = new String[0];
    public static final byte aap[][] = new byte[0][];
    public static final byte aaq[] = new byte[0];

}
