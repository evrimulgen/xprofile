// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package io.segment.android.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public final class ISO8601
{

    public ISO8601()
    {
    }

    public static String fromCalendar(Calendar calendar)
    {
        java.util.Date date = calendar.getTime();
        String s = (new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")).format(date);
        return (new StringBuilder(String.valueOf(s.substring(0, 22)))).append(":").append(s.substring(22)).toString();
    }

    public static String now()
    {
        return fromCalendar(GregorianCalendar.getInstance());
    }

    public static Calendar toCalendar(String s)
        throws ParseException
    {
        Calendar calendar = GregorianCalendar.getInstance();
        String s1 = s.replace("Z", "+00:00");
        String s2;
        try
        {
            s2 = (new StringBuilder(String.valueOf(s1.substring(0, 22)))).append(s1.substring(23)).toString();
        }
        catch(IndexOutOfBoundsException indexoutofboundsexception)
        {
            throw new ParseException("Invalid length", 0);
        }
        calendar.setTime((new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")).parse(s2));
        return calendar;
    }
}
