// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.squareup.okhttp.internal.okio;

import java.io.UnsupportedEncodingException;

final class Base64
{

    private Base64()
    {
    }

    public static byte[] decode(String s)
    {
        int i = s.length();
_L6:
        if(i <= 0) goto _L2; else goto _L1
_L1:
        char c1 = s.charAt(i - 1);
        if(c1 == '=' || c1 == '\n' || c1 == '\r' || c1 == ' ' || c1 == '\t') goto _L3; else goto _L2
_L2:
        byte abyte0[];
        int j;
        int k;
        int l;
        int i1;
        abyte0 = new byte[(int)((6L * (long)i) / 8L)];
        j = 0;
        k = 0;
        l = 0;
        i1 = 0;
_L5:
        char c;
        int k2;
        if(l >= i)
            break; /* Loop/switch isn't completed */
        c = s.charAt(l);
        int l2;
        int i3;
        int j3;
        if(c >= 'A' && c <= 'Z')
            l2 = c - 65;
        else
        if(c >= 'a' && c <= 'z')
            l2 = c - 71;
        else
        if(c >= '0' && c <= '9')
            l2 = c + 4;
        else
        if(c == '+')
        {
            l2 = 62;
        } else
        {
label0:
            {
                if(c != '/')
                    break label0;
                l2 = 63;
            }
        }
        k = k << 6 | (byte)l2;
        if(++j % 4 != 0)
            break MISSING_BLOCK_LABEL_434;
        i3 = i1 + 1;
        abyte0[i1] = (byte)(k >> 16);
        j3 = i3 + 1;
        abyte0[i3] = (byte)(k >> 8);
        k2 = j3 + 1;
        abyte0[j3] = (byte)k;
_L7:
        l++;
        i1 = k2;
        if(true) goto _L5; else goto _L4
_L3:
        i--;
          goto _L6
label1:
        {
            if(c == '\n' || c == '\r' || c == ' ')
                break MISSING_BLOCK_LABEL_434;
            if(c != '\t')
                break label1;
            k2 = i1;
        }
          goto _L7
        abyte0 = null;
        i1;
_L9:
        return abyte0;
_L4:
        int j1 = j % 4;
        if(j1 == 1)
        {
            i1;
            return null;
        }
        int k1;
        byte abyte1[];
        if(j1 == 2)
        {
            int j2 = k << 12;
            k1 = i1 + 1;
            abyte0[i1] = (byte)(j2 >> 16);
        } else
        {
            if(j1 == 3)
            {
                int l1 = k << 6;
                int i2 = i1 + 1;
                abyte0[i1] = (byte)(l1 >> 16);
                i1 = i2 + 1;
                abyte0[i2] = (byte)(l1 >> 8);
            }
            k1 = i1;
        }
        if(k1 == abyte0.length) goto _L9; else goto _L8
_L8:
        abyte1 = new byte[k1];
        System.arraycopy(abyte0, 0, abyte1, 0, k1);
        return abyte1;
        k2 = i1;
          goto _L7
    }

    public static String encode(byte abyte0[])
    {
        byte abyte1[];
        int i;
        int k;
        abyte1 = new byte[(4 * (2 + abyte0.length)) / 3];
        i = abyte0.length - abyte0.length % 3;
        int j = 0;
        k = 0;
        for(; j < i; j += 3)
        {
            int l2 = k + 1;
            abyte1[k] = MAP[(0xff & abyte0[j]) >> 2];
            int i3 = l2 + 1;
            abyte1[l2] = MAP[(3 & abyte0[j]) << 4 | (0xff & abyte0[j + 1]) >> 4];
            int j3 = i3 + 1;
            abyte1[i3] = MAP[(0xf & abyte0[j + 1]) << 2 | (0xff & abyte0[j + 2]) >> 6];
            k = j3 + 1;
            abyte1[j3] = MAP[0x3f & abyte0[j + 2]];
        }

        abyte0.length % 3;
        JVM INSTR tableswitch 1 2: default 176
    //                   1 198
    //                   2 273;
           goto _L1 _L2 _L3
_L1:
        int k1 = k;
_L4:
        int l;
        int i1;
        int j1;
        String s;
        int l1;
        int i2;
        int j2;
        int k2;
        try
        {
            s = new String(abyte1, 0, k1, "US-ASCII");
        }
        catch(UnsupportedEncodingException unsupportedencodingexception)
        {
            throw new AssertionError(unsupportedencodingexception);
        }
        return s;
_L2:
        l1 = k + 1;
        abyte1[k] = MAP[(0xff & abyte0[i]) >> 2];
        i2 = l1 + 1;
        abyte1[l1] = MAP[(3 & abyte0[i]) << 4];
        j2 = i2 + 1;
        abyte1[i2] = 61;
        k2 = j2 + 1;
        abyte1[j2] = 61;
        k1 = k2;
          goto _L4
_L3:
        l = k + 1;
        abyte1[k] = MAP[(0xff & abyte0[i]) >> 2];
        i1 = l + 1;
        abyte1[l] = MAP[(3 & abyte0[i]) << 4 | (0xff & abyte0[i + 1]) >> 4];
        j1 = i1 + 1;
        abyte1[i1] = MAP[(0xf & abyte0[i + 1]) << 2];
        k = j1 + 1;
        abyte1[j1] = 61;
        if(true) goto _L1; else goto _L5
_L5:
    }

    private static final byte MAP[] = {
        65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 
        75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 
        85, 86, 87, 88, 89, 90, 97, 98, 99, 100, 
        101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 
        111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 
        121, 122, 48, 49, 50, 51, 52, 53, 54, 55, 
        56, 57, 43, 47
    };

}
