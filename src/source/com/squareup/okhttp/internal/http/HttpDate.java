// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.squareup.okhttp.internal.http;

import java.text.*;
import java.util.*;

public final class HttpDate
{

    private HttpDate()
    {
    }

    public static String format(Date date)
    {
        return ((DateFormat)STANDARD_DATE_FORMAT.get()).format(date);
    }

    public static Date parse(String s)
    {
        Date date1 = ((DateFormat)STANDARD_DATE_FORMAT.get()).parse(s);
        return date1;
        ParseException parseexception;
        parseexception;
        String as[] = BROWSER_COMPATIBLE_DATE_FORMAT_STRINGS;
        as;
        JVM INSTR monitorenter ;
        int i = 0;
        int j = BROWSER_COMPATIBLE_DATE_FORMAT_STRINGS.length;
_L2:
        if(i >= j)
            break; /* Loop/switch isn't completed */
        Object obj = BROWSER_COMPATIBLE_DATE_FORMATS[i];
        if(obj != null)
            break MISSING_BLOCK_LABEL_75;
        obj = new SimpleDateFormat(BROWSER_COMPATIBLE_DATE_FORMAT_STRINGS[i], Locale.US);
        BROWSER_COMPATIBLE_DATE_FORMATS[i] = ((DateFormat) (obj));
        Date date = ((DateFormat) (obj)).parse(s);
        as;
        JVM INSTR monitorexit ;
        return date;
        Exception exception;
        exception;
        as;
        JVM INSTR monitorexit ;
        throw exception;
        ParseException parseexception1;
        parseexception1;
        i++;
        if(true) goto _L2; else goto _L1
_L1:
        as;
        JVM INSTR monitorexit ;
        return null;
    }

    private static final DateFormat BROWSER_COMPATIBLE_DATE_FORMATS[] = new DateFormat[BROWSER_COMPATIBLE_DATE_FORMAT_STRINGS.length];
    private static final String BROWSER_COMPATIBLE_DATE_FORMAT_STRINGS[] = {
        "EEEE, dd-MMM-yy HH:mm:ss zzz", "EEE MMM d HH:mm:ss yyyy", "EEE, dd-MMM-yyyy HH:mm:ss z", "EEE, dd-MMM-yyyy HH-mm-ss z", "EEE, dd MMM yy HH:mm:ss z", "EEE dd-MMM-yyyy HH:mm:ss z", "EEE dd MMM yyyy HH:mm:ss z", "EEE dd-MMM-yyyy HH-mm-ss z", "EEE dd-MMM-yy HH:mm:ss z", "EEE dd MMM yy HH:mm:ss z", 
        "EEE,dd-MMM-yy HH:mm:ss z", "EEE,dd-MMM-yyyy HH:mm:ss z", "EEE, dd-MM-yyyy HH:mm:ss z", "EEE MMM d yyyy HH:mm:ss z"
    };
    private static final ThreadLocal STANDARD_DATE_FORMAT = new ThreadLocal() {

        protected volatile Object initialValue()
        {
            return initialValue();
        }

        protected DateFormat initialValue()
        {
            SimpleDateFormat simpledateformat = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss zzz", Locale.US);
            simpledateformat.setTimeZone(TimeZone.getTimeZone("GMT"));
            return simpledateformat;
        }

    }
;

}
