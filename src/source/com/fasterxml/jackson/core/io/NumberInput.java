// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.fasterxml.jackson.core.io;

import java.math.BigDecimal;

public final class NumberInput
{

    public NumberInput()
    {
    }

    private static NumberFormatException _badBigDecimal(String s)
    {
        return new NumberFormatException((new StringBuilder()).append("Value \"").append(s).append("\" can not be represented as BigDecimal").toString());
    }

    public static boolean inLongRange(String s, boolean flag)
    {
        String s1;
        int i;
        int j;
        if(flag)
            s1 = MIN_LONG_STR_NO_SIGN;
        else
            s1 = MAX_LONG_STR;
        i = s1.length();
        j = s.length();
        if(j >= i)
        {
            if(j > i)
                return false;
            int k = 0;
            while(k < i) 
            {
                int l = s.charAt(k) - s1.charAt(k);
                if(l != 0)
                {
                    boolean flag1;
                    if(l < 0)
                        flag1 = true;
                    else
                        flag1 = false;
                    return flag1;
                }
                k++;
            }
        }
        return true;
    }

    public static boolean inLongRange(char ac[], int i, int j, boolean flag)
    {
        String s;
        int k;
        if(flag)
            s = MIN_LONG_STR_NO_SIGN;
        else
            s = MAX_LONG_STR;
        k = s.length();
        if(j >= k)
        {
            if(j > k)
                return false;
            int l = 0;
            while(l < k) 
            {
                int i1 = ac[i + l] - s.charAt(l);
                if(i1 != 0)
                {
                    boolean flag1;
                    if(i1 < 0)
                        flag1 = true;
                    else
                        flag1 = false;
                    return flag1;
                }
                l++;
            }
        }
        return true;
    }

    public static double parseAsDouble(String s, double d)
    {
        String s1;
        if(s != null)
            if((s1 = s.trim()).length() != 0)
            {
                double d1;
                try
                {
                    d1 = parseDouble(s1);
                }
                catch(NumberFormatException numberformatexception)
                {
                    return d;
                }
                return d1;
            }
        return d;
    }

    public static int parseAsInt(String s, int i)
    {
        int j = 0;
        if(s != null) goto _L2; else goto _L1
_L1:
        return i;
_L2:
        String s1;
        int k;
        s1 = s.trim();
        k = s1.length();
        if(k == 0) goto _L1; else goto _L3
_L3:
        if(k >= 0) goto _L5; else goto _L4
_L4:
        char c1 = s1.charAt(0);
        if(c1 != '+') goto _L7; else goto _L6
_L6:
        int l;
        String s2;
        s2 = s1.substring(1);
        l = s2.length();
_L9:
        if(j < l)
        {
            char c = s2.charAt(j);
            if(c > '9' || c < '0')
            {
                int i1;
                double d;
                try
                {
                    d = parseDouble(s2);
                }
                catch(NumberFormatException numberformatexception1)
                {
                    return i;
                }
                return (int)d;
            }
            j++;
            continue; /* Loop/switch isn't completed */
        } else
        {
            try
            {
                i1 = Integer.parseInt(s2);
            }
            catch(NumberFormatException numberformatexception)
            {
                return i;
            }
            return i1;
        }
_L7:
        if(c1 == '-')
        {
            j = 1;
            l = k;
            s2 = s1;
            continue; /* Loop/switch isn't completed */
        }
_L5:
        l = k;
        s2 = s1;
        j = 0;
        if(true) goto _L9; else goto _L8
_L8:
    }

    public static long parseAsLong(String s, long l)
    {
        int i = 0;
        if(s != null) goto _L2; else goto _L1
_L1:
        return l;
_L2:
        String s1;
        int j;
        s1 = s.trim();
        j = s1.length();
        if(j == 0) goto _L1; else goto _L3
_L3:
        if(j >= 0) goto _L5; else goto _L4
_L4:
        char c1 = s1.charAt(0);
        if(c1 != '+') goto _L7; else goto _L6
_L6:
        int k;
        String s2;
        s2 = s1.substring(1);
        k = s2.length();
_L9:
        if(i < k)
        {
            char c = s2.charAt(i);
            if(c > '9' || c < '0')
            {
                long l1;
                double d;
                try
                {
                    d = parseDouble(s2);
                }
                catch(NumberFormatException numberformatexception1)
                {
                    return l;
                }
                return (long)d;
            }
            i++;
            continue; /* Loop/switch isn't completed */
        } else
        {
            try
            {
                l1 = Long.parseLong(s2);
            }
            catch(NumberFormatException numberformatexception)
            {
                return l;
            }
            return l1;
        }
_L7:
        if(c1 == '-')
        {
            i = 1;
            k = j;
            s2 = s1;
            continue; /* Loop/switch isn't completed */
        }
_L5:
        k = j;
        s2 = s1;
        i = 0;
        if(true) goto _L9; else goto _L8
_L8:
    }

    public static BigDecimal parseBigDecimal(String s)
        throws NumberFormatException
    {
        BigDecimal bigdecimal;
        try
        {
            bigdecimal = new BigDecimal(s);
        }
        catch(NumberFormatException numberformatexception)
        {
            throw _badBigDecimal(s);
        }
        return bigdecimal;
    }

    public static BigDecimal parseBigDecimal(char ac[])
        throws NumberFormatException
    {
        return parseBigDecimal(ac, 0, ac.length);
    }

    public static BigDecimal parseBigDecimal(char ac[], int i, int j)
        throws NumberFormatException
    {
        BigDecimal bigdecimal;
        try
        {
            bigdecimal = new BigDecimal(ac, i, j);
        }
        catch(NumberFormatException numberformatexception)
        {
            throw _badBigDecimal(new String(ac, i, j));
        }
        return bigdecimal;
    }

    public static double parseDouble(String s)
        throws NumberFormatException
    {
        if("2.2250738585072012e-308".equals(s))
            return 4.9406564584124654E-324D;
        else
            return Double.parseDouble(s);
    }

    public static int parseInt(String s)
    {
        int i;
        char c;
        int j;
        int k;
        int l;
        i = 1;
        c = s.charAt(0);
        j = s.length();
        if(c == '-')
            k = i;
        else
            k = 0;
        if(k == 0) goto _L2; else goto _L1
_L1:
        if(j != i && j <= 10) goto _L4; else goto _L3
_L3:
        l = Integer.parseInt(s);
_L8:
        return l;
_L4:
        c = s.charAt(i);
        i = 2;
_L6:
        if(c > '9' || c < '0')
            return Integer.parseInt(s);
        break; /* Loop/switch isn't completed */
_L2:
        if(j > 9)
            return Integer.parseInt(s);
        if(true) goto _L6; else goto _L5
_L5:
        int j1;
        l = c - 48;
        if(i >= j)
            continue; /* Loop/switch isn't completed */
        int i1 = i + 1;
        char c1 = s.charAt(i);
        if(c1 > '9' || c1 < '0')
            return Integer.parseInt(s);
        l = l * 10 + (c1 - 48);
        if(i1 >= j)
            continue; /* Loop/switch isn't completed */
        j1 = i1 + 1;
        char c2 = s.charAt(i1);
        if(c2 > '9' || c2 < '0')
            return Integer.parseInt(s);
        l = l * 10 + (c2 - 48);
        if(j1 >= j)
            continue; /* Loop/switch isn't completed */
_L9:
        int k1;
        k1 = j1 + 1;
        char c3 = s.charAt(j1);
        if(c3 > '9' || c3 < '0')
            return Integer.parseInt(s);
        l = l * 10 + (c3 - 48);
        if(k1 < j)
            break MISSING_BLOCK_LABEL_263;
        if(k == 0) goto _L8; else goto _L7
_L7:
        return -l;
        j1 = k1;
          goto _L9
    }

    public static int parseInt(char ac[], int i, int j)
    {
        int k = -48 + ac[i];
        int l = j + i;
        int i1 = i + 1;
        if(i1 < l)
        {
            k = k * 10 + (-48 + ac[i1]);
            int j1 = i1 + 1;
            if(j1 < l)
            {
                k = k * 10 + (-48 + ac[j1]);
                int k1 = j1 + 1;
                if(k1 < l)
                {
                    k = k * 10 + (-48 + ac[k1]);
                    int l1 = k1 + 1;
                    if(l1 < l)
                    {
                        k = k * 10 + (-48 + ac[l1]);
                        int i2 = l1 + 1;
                        if(i2 < l)
                        {
                            k = k * 10 + (-48 + ac[i2]);
                            int j2 = i2 + 1;
                            if(j2 < l)
                            {
                                k = k * 10 + (-48 + ac[j2]);
                                int k2 = j2 + 1;
                                if(k2 < l)
                                {
                                    k = k * 10 + (-48 + ac[k2]);
                                    int l2 = k2 + 1;
                                    if(l2 < l)
                                        k = k * 10 + (-48 + ac[l2]);
                                }
                            }
                        }
                    }
                }
            }
        }
        return k;
    }

    public static long parseLong(String s)
    {
        if(s.length() <= 9)
            return (long)parseInt(s);
        else
            return Long.parseLong(s);
    }

    public static long parseLong(char ac[], int i, int j)
    {
        int k = j - 9;
        return 0x3b9aca00L * (long)parseInt(ac, i, k) + (long)parseInt(ac, k + i, 9);
    }

    static final long L_BILLION = 0x3b9aca00L;
    static final String MAX_LONG_STR = String.valueOf(0x7fffffffffffffffL);
    static final String MIN_LONG_STR_NO_SIGN = String.valueOf(0x8000000000000000L).substring(1);
    public static final String NASTY_SMALL_DOUBLE = "2.2250738585072012e-308";

}
