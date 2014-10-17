// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.fasterxml.jackson.databind.util;

import java.util.*;

public class ISO8601Utils
{

    public ISO8601Utils()
    {
    }

    private static void checkOffset(String s, int i, char c)
        throws IndexOutOfBoundsException
    {
        char c1 = s.charAt(i);
        if(c1 != c)
            throw new IndexOutOfBoundsException((new StringBuilder()).append("Expected '").append(c).append("' character but found '").append(c1).append("'").toString());
        else
            return;
    }

    public static String format(Date date)
    {
        return format(date, false, TIMEZONE_GMT);
    }

    public static String format(Date date, boolean flag)
    {
        return format(date, flag, TIMEZONE_GMT);
    }

    public static String format(Date date, boolean flag, TimeZone timezone)
    {
        GregorianCalendar gregoriancalendar = new GregorianCalendar(timezone, Locale.US);
        gregoriancalendar.setTime(date);
        int i = "yyyy-MM-ddThh:mm:ss".length();
        int j;
        int k;
        int l;
        StringBuilder stringbuilder;
        int i1;
        if(flag)
            j = ".sss".length();
        else
            j = 0;
        k = i + j;
        if(timezone.getRawOffset() == 0)
            l = "Z".length();
        else
            l = "+hh:mm".length();
        stringbuilder = new StringBuilder(l + k);
        padInt(stringbuilder, gregoriancalendar.get(1), "yyyy".length());
        stringbuilder.append('-');
        padInt(stringbuilder, 1 + gregoriancalendar.get(2), "MM".length());
        stringbuilder.append('-');
        padInt(stringbuilder, gregoriancalendar.get(5), "dd".length());
        stringbuilder.append('T');
        padInt(stringbuilder, gregoriancalendar.get(11), "hh".length());
        stringbuilder.append(':');
        padInt(stringbuilder, gregoriancalendar.get(12), "mm".length());
        stringbuilder.append(':');
        padInt(stringbuilder, gregoriancalendar.get(13), "ss".length());
        if(flag)
        {
            stringbuilder.append('.');
            padInt(stringbuilder, gregoriancalendar.get(14), "sss".length());
        }
        i1 = timezone.getOffset(gregoriancalendar.getTimeInMillis());
        if(i1 != 0)
        {
            int j1 = Math.abs(i1 / 60000 / 60);
            int k1 = Math.abs((i1 / 60000) % 60);
            char c;
            if(i1 < 0)
                c = '-';
            else
                c = '+';
            stringbuilder.append(c);
            padInt(stringbuilder, j1, "hh".length());
            stringbuilder.append(':');
            padInt(stringbuilder, k1, "mm".length());
        } else
        {
            stringbuilder.append('Z');
        }
        return stringbuilder.toString();
    }

    private static void padInt(StringBuilder stringbuilder, int i, int j)
    {
        String s = Integer.toString(i);
        for(int k = j - s.length(); k > 0; k--)
            stringbuilder.append('0');

        stringbuilder.append(s);
    }

    public static Date parse(String s)
    {
        int i;
        int j;
        int k;
        int l;
        int i1;
        int j1;
        i = parseInt(s, 0, 4);
        checkOffset(s, 4, '-');
        j = parseInt(s, 5, 7);
        checkOffset(s, 7, '-');
        k = parseInt(s, 8, 10);
        checkOffset(s, 10, 'T');
        l = parseInt(s, 11, 13);
        checkOffset(s, 13, ':');
        i1 = parseInt(s, 14, 16);
        checkOffset(s, 16, ':');
        j1 = parseInt(s, 17, 19);
        if(s.charAt(19) != '.') goto _L2; else goto _L1
_L1:
        int l1;
        checkOffset(s, 19, '.');
        l1 = parseInt(s, 20, 23);
        byte byte0;
        int k1;
        byte0 = 23;
        k1 = l1;
_L8:
        char c = s.charAt(byte0);
        if(c != '+' && c != '-') goto _L4; else goto _L3
_L3:
        String s2 = (new StringBuilder()).append("GMT").append(s.substring(byte0)).toString();
_L6:
        TimeZone timezone;
        timezone = TimeZone.getTimeZone(s2);
        if(!timezone.getID().equals(s2))
            throw new IndexOutOfBoundsException();
          goto _L5
        IndexOutOfBoundsException indexoutofboundsexception;
        indexoutofboundsexception;
        Object obj = indexoutofboundsexception;
_L7:
        IllegalArgumentException illegalargumentexception;
        String s1;
        GregorianCalendar gregoriancalendar;
        Date date;
        if(s == null)
            s1 = null;
        else
            s1 = (new StringBuilder()).append('"').append(s).append("'").toString();
        throw new IllegalArgumentException((new StringBuilder()).append("Failed to parse date [").append(s1).append("]: ").append(((Exception) (obj)).getMessage()).toString(), ((Throwable) (obj)));
_L4:
label0:
        {
            if(c != 'Z')
                break label0;
            s2 = "GMT";
        }
          goto _L6
        throw new IndexOutOfBoundsException((new StringBuilder()).append("Invalid time zone indicator ").append(c).toString());
_L5:
        gregoriancalendar = new GregorianCalendar(timezone);
        gregoriancalendar.setLenient(false);
        gregoriancalendar.set(1, i);
        gregoriancalendar.set(2, j - 1);
        gregoriancalendar.set(5, k);
        gregoriancalendar.set(11, l);
        gregoriancalendar.set(12, i1);
        gregoriancalendar.set(13, j1);
        gregoriancalendar.set(14, k1);
        date = gregoriancalendar.getTime();
        return date;
        illegalargumentexception;
        obj = illegalargumentexception;
          goto _L7
_L2:
        byte0 = 19;
        k1 = 0;
          goto _L8
        NumberFormatException numberformatexception;
        numberformatexception;
        obj = numberformatexception;
          goto _L7
    }

    private static int parseInt(String s, int i, int j)
        throws NumberFormatException
    {
        if(i < 0 || j > s.length() || i > j)
            throw new NumberFormatException(s);
        int k = 0;
        if(i < j)
        {
            int j1 = i + 1;
            int k1 = Character.digit(s.charAt(i), 10);
            if(k1 < 0)
                throw new NumberFormatException((new StringBuilder()).append("Invalid number: ").append(s).toString());
            k = -k1;
            i = j1;
        }
        int l;
        for(; i < j; i = l)
        {
            l = i + 1;
            int i1 = Character.digit(s.charAt(i), 10);
            if(i1 < 0)
                throw new NumberFormatException((new StringBuilder()).append("Invalid number: ").append(s).toString());
            k = k * 10 - i1;
        }

        return -k;
    }

    public static TimeZone timeZoneGMT()
    {
        return TIMEZONE_GMT;
    }

    private static final String GMT_ID = "GMT";
    private static final TimeZone TIMEZONE_GMT = TimeZone.getTimeZone("GMT");

}
