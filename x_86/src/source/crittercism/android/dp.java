// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package crittercism.android;

import java.text.SimpleDateFormat;
import java.util.*;

// Referenced classes of package crittercism.android:
//            dq

public final class dp
{
    final class a
        implements dq
    {

        public final Date a()
        {
            return new Date();
        }

        final dp a;

        private a()
        {
            a = dp.this;
            super();
        }

        a(byte byte0)
        {
            this();
        }
    }


    private dp()
    {
        b = new a((byte)0);
        c = null;
        try
        {
            c = b();
            return;
        }
        catch(Exception exception)
        {
            return;
        }
    }

    private static SimpleDateFormat b()
    {
        SimpleDateFormat simpledateformat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ", Locale.US);
        simpledateformat.setTimeZone(TimeZone.getTimeZone("GMT"));
        return simpledateformat;
    }

    public final String a()
    {
        return a(b.a());
    }

    public final String a(Date date)
    {
        if(c != null)
        {
            return c.format(date);
        } else
        {
            b();
            return "";
        }
    }

    public static final dp a = new dp();
    private dq b;
    private SimpleDateFormat c;

}
