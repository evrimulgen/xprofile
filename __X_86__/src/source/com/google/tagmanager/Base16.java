// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.tagmanager;


class Base16
{

    Base16()
    {
    }

    public static byte[] decode(String s)
    {
        int i = s.length();
        if(i % 2 != 0)
            throw new IllegalArgumentException("purported base16 string has odd number of characters");
        byte abyte0[] = new byte[i / 2];
        for(int j = 0; j < i; j += 2)
        {
            int k = Character.digit(s.charAt(j), 16);
            int l = Character.digit(s.charAt(j + 1), 16);
            if(k == -1 || l == -1)
                throw new IllegalArgumentException("purported base16 string has illegal char");
            abyte0[j / 2] = (byte)(l + (k << 4));
        }

        return abyte0;
    }

    public static String encode(byte abyte0[])
    {
        StringBuilder stringbuilder = new StringBuilder();
        int i = abyte0.length;
        for(int j = 0; j < i; j++)
        {
            byte byte0 = abyte0[j];
            if((byte0 & 0xf0) == 0)
                stringbuilder.append("0");
            stringbuilder.append(Integer.toHexString(byte0 & 0xff));
        }

        return stringbuilder.toString().toUpperCase();
    }
}
