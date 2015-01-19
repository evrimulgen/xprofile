// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.android.gms.internal;


public class jx
{

    public jx(byte abyte0[])
    {
        for(int i = 0; i < 256; i++)
            ZR[i] = (byte)i;

        int j = 0;
        for(int k = 0; k < 256; k++)
        {
            j = 0xff & j + ZR[k] + abyte0[k % abyte0.length];
            byte byte0 = ZR[k];
            ZR[k] = ZR[j];
            ZR[j] = byte0;
        }

        ZS = 0;
        ZT = 0;
    }

    public void m(byte abyte0[])
    {
        int i = ZS;
        int j = ZT;
        for(int k = 0; k < abyte0.length; k++)
        {
            i = 0xff & i + 1;
            j = 0xff & j + ZR[i];
            byte byte0 = ZR[i];
            ZR[i] = ZR[j];
            ZR[j] = byte0;
            abyte0[k] = (byte)(abyte0[k] ^ ZR[0xff & ZR[i] + ZR[j]]);
        }

        ZS = i;
        ZT = j;
    }

    private final byte ZR[] = new byte[256];
    private int ZS;
    private int ZT;
}
