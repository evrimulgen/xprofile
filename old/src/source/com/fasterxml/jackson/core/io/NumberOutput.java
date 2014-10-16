// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.fasterxml.jackson.core.io;


public final class NumberOutput
{

    public NumberOutput()
    {
    }

    private static int calcLongStrLength(long l)
    {
        int i = 10;
        long l1 = TEN_BILLION_L;
        do
        {
            if(l < l1 || i == 19)
                return i;
            i++;
            l1 = (l1 << 3) + (l1 << 1);
        } while(true);
    }

    private static int outputFullTriplet(int i, byte abyte0[], int j)
    {
        int k = i << 2;
        int l = j + 1;
        byte abyte1[] = FULL_TRIPLETS_B;
        int i1 = k + 1;
        abyte0[j] = abyte1[k];
        int j1 = l + 1;
        byte abyte2[] = FULL_TRIPLETS_B;
        int k1 = i1 + 1;
        abyte0[l] = abyte2[i1];
        int l1 = j1 + 1;
        abyte0[j1] = FULL_TRIPLETS_B[k1];
        return l1;
    }

    private static int outputFullTriplet(int i, char ac[], int j)
    {
        int k = i << 2;
        int l = j + 1;
        char ac1[] = FULL_TRIPLETS;
        int i1 = k + 1;
        ac[j] = ac1[k];
        int j1 = l + 1;
        char ac2[] = FULL_TRIPLETS;
        int k1 = i1 + 1;
        ac[l] = ac2[i1];
        int l1 = j1 + 1;
        ac[j1] = FULL_TRIPLETS[k1];
        return l1;
    }

    public static int outputInt(int i, byte abyte0[], int j)
    {
        if(i < 0)
        {
            if(i == 0x80000000)
                return outputLong(i, abyte0, j);
            int l2 = j + 1;
            abyte0[j] = 45;
            i = -i;
            j = l2;
        }
        if(i < MILLION)
            if(i < 1000)
            {
                if(i < 10)
                {
                    int k2 = j + 1;
                    abyte0[j] = (byte)(i + 48);
                    return k2;
                } else
                {
                    return outputLeadingTriplet(i, abyte0, j);
                }
            } else
            {
                int j2 = i / 1000;
                return outputFullTriplet(i - j2 * 1000, abyte0, outputLeadingTriplet(j2, abyte0, j));
            }
        boolean flag;
        int i1;
        int k1;
        if(i >= BILLION)
            flag = true;
        else
            flag = false;
        if(flag)
        {
            i -= BILLION;
            int k;
            int l;
            int j1;
            if(i >= BILLION)
            {
                i -= BILLION;
                int i2 = j + 1;
                abyte0[j] = 50;
                j = i2;
            } else
            {
                int l1 = j + 1;
                abyte0[j] = 49;
                j = l1;
            }
        }
        k = i / 1000;
        l = i - k * 1000;
        i1 = k / 1000;
        j1 = k - i1 * 1000;
        if(flag)
            k1 = outputFullTriplet(i1, abyte0, j);
        else
            k1 = outputLeadingTriplet(i1, abyte0, j);
        return outputFullTriplet(l, abyte0, outputFullTriplet(j1, abyte0, k1));
    }

    public static int outputInt(int i, char ac[], int j)
    {
        if(i < 0)
        {
            if(i == 0x80000000)
                return outputLong(i, ac, j);
            int l2 = j + 1;
            ac[j] = '-';
            i = -i;
            j = l2;
        }
        if(i < MILLION)
            if(i < 1000)
            {
                if(i < 10)
                {
                    int k2 = j + 1;
                    ac[j] = (char)(i + 48);
                    return k2;
                } else
                {
                    return outputLeadingTriplet(i, ac, j);
                }
            } else
            {
                int j2 = i / 1000;
                return outputFullTriplet(i - j2 * 1000, ac, outputLeadingTriplet(j2, ac, j));
            }
        boolean flag;
        int i1;
        int k1;
        if(i >= BILLION)
            flag = true;
        else
            flag = false;
        if(flag)
        {
            i -= BILLION;
            int k;
            int l;
            int j1;
            if(i >= BILLION)
            {
                i -= BILLION;
                int i2 = j + 1;
                ac[j] = '2';
                j = i2;
            } else
            {
                int l1 = j + 1;
                ac[j] = '1';
                j = l1;
            }
        }
        k = i / 1000;
        l = i - k * 1000;
        i1 = k / 1000;
        j1 = k - i1 * 1000;
        if(flag)
            k1 = outputFullTriplet(i1, ac, j);
        else
            k1 = outputLeadingTriplet(i1, ac, j);
        return outputFullTriplet(l, ac, outputFullTriplet(j1, ac, k1));
    }

    private static int outputLeadingTriplet(int i, byte abyte0[], int j)
    {
        int k = i << 2;
        char ac[] = LEADING_TRIPLETS;
        int l = k + 1;
        char c = ac[k];
        if(c != 0)
        {
            int l1 = j + 1;
            abyte0[j] = (byte)c;
            j = l1;
        }
        char ac1[] = LEADING_TRIPLETS;
        int i1 = l + 1;
        char c1 = ac1[l];
        if(c1 != 0)
        {
            int k1 = j + 1;
            abyte0[j] = (byte)c1;
            j = k1;
        }
        int j1 = j + 1;
        abyte0[j] = (byte)LEADING_TRIPLETS[i1];
        return j1;
    }

    private static int outputLeadingTriplet(int i, char ac[], int j)
    {
        int k = i << 2;
        char ac1[] = LEADING_TRIPLETS;
        int l = k + 1;
        char c = ac1[k];
        if(c != 0)
        {
            int l1 = j + 1;
            ac[j] = c;
            j = l1;
        }
        char ac2[] = LEADING_TRIPLETS;
        int i1 = l + 1;
        char c1 = ac2[l];
        if(c1 != 0)
        {
            int k1 = j + 1;
            ac[j] = c1;
            j = k1;
        }
        int j1 = j + 1;
        ac[j] = LEADING_TRIPLETS[i1];
        return j1;
    }

    public static int outputLong(long l, byte abyte0[], int i)
    {
        if(l >= 0L) goto _L2; else goto _L1
_L1:
        if(l <= MIN_INT_AS_LONG) goto _L4; else goto _L3
_L3:
        int j3 = outputInt((int)l, abyte0, i);
_L6:
        return j3;
_L4:
        if(l != 0x8000000000000000L)
            break; /* Loop/switch isn't completed */
        int k2 = SMALLEST_LONG.length();
        int i3 = 0;
        j3 = i;
        while(i3 < k2) 
        {
            int k3 = j3 + 1;
            abyte0[j3] = (byte)SMALLEST_LONG.charAt(i3);
            i3++;
            j3 = k3;
        }
        if(true) goto _L6; else goto _L5
_L5:
        int j2 = i + 1;
        abyte0[i] = 45;
        l = -l;
        i = j2;
_L8:
        int j;
        int k;
        j = i + calcLongStrLength(l);
        k = j;
        long l2;
        for(; l > MAX_INT_AS_LONG; l = l2)
        {
            k -= 3;
            l2 = l / THOUSAND_L;
            outputFullTriplet((int)(l - l2 * THOUSAND_L), abyte0, k);
        }

        break; /* Loop/switch isn't completed */
_L2:
        if(l <= MAX_INT_AS_LONG)
            return outputInt((int)l, abyte0, i);
        if(true) goto _L8; else goto _L7
_L7:
        int i1 = (int)l;
        int j1 = k;
        int k1;
        for(k1 = i1; k1 >= 1000;)
        {
            int l1 = j1 - 3;
            int i2 = k1 / 1000;
            outputFullTriplet(k1 - i2 * 1000, abyte0, l1);
            k1 = i2;
            j1 = l1;
        }

        outputLeadingTriplet(k1, abyte0, i);
        return j;
    }

    public static int outputLong(long l, char ac[], int i)
    {
        int j;
        int k;
        if(l < 0L)
        {
            if(l > MIN_INT_AS_LONG)
                return outputInt((int)l, ac, i);
            if(l == 0x8000000000000000L)
            {
                int k2 = SMALLEST_LONG.length();
                SMALLEST_LONG.getChars(0, k2, ac, i);
                return i + k2;
            }
            int j2 = i + 1;
            ac[i] = '-';
            l = -l;
            i = j2;
        } else
        if(l <= MAX_INT_AS_LONG)
            return outputInt((int)l, ac, i);
        j = i + calcLongStrLength(l);
        k = j;
        long l2;
        for(; l > MAX_INT_AS_LONG; l = l2)
        {
            k -= 3;
            l2 = l / THOUSAND_L;
            outputFullTriplet((int)(l - l2 * THOUSAND_L), ac, k);
        }

        int i1 = (int)l;
        int j1 = k;
        int k1;
        for(k1 = i1; k1 >= 1000;)
        {
            int l1 = j1 - 3;
            int i2 = k1 / 1000;
            outputFullTriplet(k1 - i2 * 1000, ac, l1);
            k1 = i2;
            j1 = l1;
        }

        outputLeadingTriplet(k1, ac, i);
        return j;
    }

    public static String toString(double d)
    {
        return Double.toString(d);
    }

    public static String toString(int i)
    {
        if(i < sSmallIntStrs.length)
        {
            if(i >= 0)
                return sSmallIntStrs[i];
            int j = -1 + -i;
            if(j < sSmallIntStrs2.length)
                return sSmallIntStrs2[j];
        }
        return Integer.toString(i);
    }

    public static String toString(long l)
    {
        if(l <= 0x7fffffffL && l >= 0xffffffff80000000L)
            return toString((int)l);
        else
            return Long.toString(l);
    }

    private static int BILLION = 0x3b9aca00;
    static final char FULL_TRIPLETS[];
    static final byte FULL_TRIPLETS_B[];
    static final char LEADING_TRIPLETS[];
    private static long MAX_INT_AS_LONG = 0x7fffffffL;
    private static int MILLION = 0xf4240;
    private static long MIN_INT_AS_LONG = 0xffffffff80000000L;
    private static final char NULL_CHAR;
    static final String SMALLEST_LONG = String.valueOf(0x8000000000000000L);
    private static long TEN_BILLION_L = 0x2540be400L;
    private static long THOUSAND_L = 1000L;
    static final String sSmallIntStrs[] = {
        "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", 
        "10"
    };
    static final String sSmallIntStrs2[] = {
        "-1", "-2", "-3", "-4", "-5", "-6", "-7", "-8", "-9", "-10"
    };

    static 
    {
        LEADING_TRIPLETS = new char[4000];
        FULL_TRIPLETS = new char[4000];
        int i = 0;
        int j = 0;
        for(; i < 10; i++)
        {
            char c = (char)(i + 48);
            char c1;
            int l;
            if(i == 0)
                c1 = '\0';
            else
                c1 = c;
            for(l = 0; l < 10;)
            {
                char c2 = (char)(l + 48);
                char c3;
                int i1;
                if(i == 0 && l == 0)
                    c3 = '\0';
                else
                    c3 = c2;
                i1 = j;
                for(int j1 = 0; j1 < 10; j1++)
                {
                    char c4 = (char)(j1 + 48);
                    LEADING_TRIPLETS[i1] = c1;
                    LEADING_TRIPLETS[i1 + 1] = c3;
                    LEADING_TRIPLETS[i1 + 2] = c4;
                    FULL_TRIPLETS[i1] = c;
                    FULL_TRIPLETS[i1 + 1] = c2;
                    FULL_TRIPLETS[i1 + 2] = c4;
                    i1 += 4;
                }

                l++;
                j = i1;
            }

        }

        FULL_TRIPLETS_B = new byte[4000];
        for(int k = 0; k < 4000; k++)
            FULL_TRIPLETS_B[k] = (byte)FULL_TRIPLETS[k];

    }
}
