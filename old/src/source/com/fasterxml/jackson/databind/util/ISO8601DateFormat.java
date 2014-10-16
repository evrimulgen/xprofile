// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.fasterxml.jackson.databind.util;

import java.text.*;
import java.util.*;

// Referenced classes of package com.fasterxml.jackson.databind.util:
//            ISO8601Utils

public class ISO8601DateFormat extends DateFormat
{

    public ISO8601DateFormat()
    {
        numberFormat = NUMBER_FORMAT;
        calendar = CALENDAR;
    }

    public Object clone()
    {
        return this;
    }

    public StringBuffer format(Date date, StringBuffer stringbuffer, FieldPosition fieldposition)
    {
        stringbuffer.append(ISO8601Utils.format(date));
        return stringbuffer;
    }

    public Date parse(String s, ParsePosition parseposition)
    {
        parseposition.setIndex(s.length());
        return ISO8601Utils.parse(s);
    }

    public String toString()
    {
        return getClass().getName();
    }

    private static Calendar CALENDAR = new GregorianCalendar();
    private static NumberFormat NUMBER_FORMAT = new DecimalFormat();
    private static final long serialVersionUID = 1L;

}
