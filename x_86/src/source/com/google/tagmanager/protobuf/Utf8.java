// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.tagmanager.protobuf;


final class Utf8
{

    private Utf8()
    {
    }

    private static int incompleteStateFor(int i)
    {
        if(i > -12)
            i = -1;
        return i;
    }

    private static int incompleteStateFor(int i, int j)
    {
        if(i > -12 || j > -65)
            return -1;
        else
            return i ^ j << 8;
    }

    private static int incompleteStateFor(int i, int j, int k)
    {
        if(i > -12 || j > -65 || k > -65)
            return -1;
        else
            return i ^ j << 8 ^ k << 16;
    }

    private static int incompleteStateFor(byte abyte0[], int i, int j)
    {
        byte byte0 = abyte0[i - 1];
        switch(j - i)
        {
        default:
            throw new AssertionError();

        case 0: // '\0'
            return incompleteStateFor(byte0);

        case 1: // '\001'
            return incompleteStateFor(byte0, abyte0[i]);

        case 2: // '\002'
            return incompleteStateFor(byte0, abyte0[i], abyte0[i + 1]);
        }
    }

    public static boolean isValidUtf8(byte abyte0[])
    {
        return isValidUtf8(abyte0, 0, abyte0.length);
    }

    public static boolean isValidUtf8(byte abyte0[], int i, int j)
    {
        return partialIsValidUtf8(abyte0, i, j) == 0;
    }

    public static int partialIsValidUtf8(int i, byte abyte0[], int j, int k)
    {
        if(i == 0) goto _L2; else goto _L1
_L1:
        byte byte0;
        if(j >= k)
            return i;
        byte0 = (byte)i;
        if(byte0 >= -32) goto _L4; else goto _L3
_L3:
        if(byte0 < -62) goto _L6; else goto _L5
_L5:
        int i2 = j + 1;
        if(abyte0[j] <= -65) goto _L7; else goto _L6
_L6:
        return -1;
_L4:
        if(byte0 >= -16) goto _L9; else goto _L8
_L8:
        byte byte3;
        int k1;
        byte3 = (byte)(-1 ^ i >> 8);
        if(byte3 == 0)
        {
            k1 = j + 1;
            byte3 = abyte0[j];
            if(k1 >= k)
            {
                int l1 = incompleteStateFor(byte0, byte3);
                k1;
                return l1;
            }
        } else
        {
            k1 = j;
        }
        if(byte3 > -65 || byte0 == -32 && byte3 < -96 || byte0 == -19 && byte3 >= -96) goto _L11; else goto _L10
_L10:
        j = k1 + 1;
        if(abyte0[k1] <= -65) goto _L2; else goto _L12
_L12:
        return -1;
_L9:
        byte byte1;
        byte byte2;
        int l;
        byte1 = (byte)(-1 ^ i >> 8);
        if(byte1 == 0)
        {
            l = j + 1;
            byte1 = abyte0[j];
            byte2 = 0;
            if(l >= k)
            {
                int j1 = incompleteStateFor(byte0, byte1);
                l;
                return j1;
            }
        } else
        {
            byte2 = (byte)(i >> 16);
            l = j;
        }
        if(byte2 == 0)
        {
            int i1 = l + 1;
            byte2 = abyte0[l];
            if(i1 >= k)
                return incompleteStateFor(byte0, byte1, byte2);
            l = i1;
        }
        if(byte1 > -65 || (byte0 << 28) + (byte1 + 112) >> 30 != 0 || byte2 > -65) goto _L14; else goto _L13
_L13:
        j = l + 1;
        if(abyte0[l] <= -65) goto _L2; else goto _L15
_L15:
        return -1;
_L7:
        j = i2;
_L2:
        return partialIsValidUtf8(abyte0, j, k);
_L14:
        l;
        if(true) goto _L15; else goto _L11
_L11:
        k1;
        if(true) goto _L12; else goto _L16
_L16:
    }

    public static int partialIsValidUtf8(byte abyte0[], int i, int j)
    {
        for(; i < j && abyte0[i] >= 0; i++);
        if(i >= j)
            return 0;
        else
            return partialIsValidUtf8NonAscii(abyte0, i, j);
    }

    private static int partialIsValidUtf8NonAscii(byte abyte0[], int i, int j)
    {
        int k = i;
_L6:
        if(k < j) goto _L2; else goto _L1
_L1:
        int i1;
        i1 = 0;
        k;
_L4:
        return i1;
_L2:
        int l;
        int j1;
        l = k + 1;
        i1 = abyte0[k];
        if(i1 >= 0)
            break MISSING_BLOCK_LABEL_257;
        if(i1 >= -32)
            break; /* Loop/switch isn't completed */
        if(l < j)
        {
            if(i1 >= -62)
            {
                j1 = l + 1;
                if(abyte0[l] <= -65)
                    break MISSING_BLOCK_LABEL_244;
                j1;
            }
            return -1;
        }
        if(true) goto _L4; else goto _L3
_L3:
        int l1;
label0:
        {
            if(i1 < -16)
            {
                if(l >= j - 1)
                    return incompleteStateFor(abyte0, l, j);
                int i2 = l + 1;
                byte byte1 = abyte0[l];
                if(byte1 <= -65 && (i1 != -32 || byte1 >= -96) && (i1 != -19 || byte1 < -96))
                {
                    l1 = i2 + 1;
                    if(abyte0[i2] <= -65)
                        break MISSING_BLOCK_LABEL_248;
                }
                return -1;
            }
            if(l >= j - 2)
                return incompleteStateFor(abyte0, l, j);
            j1 = l + 1;
            byte byte0 = abyte0[l];
            if(byte0 <= -65 && (i1 << 28) + (byte0 + 112) >> 30 == 0)
            {
                int k1 = j1 + 1;
                if(abyte0[j1] > -65)
                    break label0;
                j1 = k1 + 1;
                if(abyte0[k1] <= -65)
                    break MISSING_BLOCK_LABEL_244;
            }
            j1;
        }
        return -1;
        l1 = j1;
        k = l1;
        continue; /* Loop/switch isn't completed */
        k = l;
        if(true) goto _L6; else goto _L5
_L5:
    }

    public static final int COMPLETE = 0;
    public static final int MALFORMED = -1;
}
