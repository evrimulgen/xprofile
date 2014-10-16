// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.flurry.sdk;

import android.widget.Toast;
import java.util.concurrent.ExecutorService;

// Referenced classes of package com.flurry.sdk:
//            cf, eh, ei, ex, 
//            ce, ch, eg, bx, 
//            cl

public class cd extends cf
    implements ei.a
{

    public cd()
    {
        cd(null);
    }

    cd(cf.a a1)
    {
        cf("Analytics", com/flurry/sdk/cd.getSimpleName());
        i = "AnalyticsData_";
        b();
        a(a1);
    }

    private void b()
    {
        ei ei1 = eh.a();
        j = ((Boolean)ei1.a("UseHttps")).booleanValue();
        ei1.a("UseHttps", this);
        ex.a(4, d, (new StringBuilder()).append("initSettings, UseHttps = ").append(j).toString());
        String s = (String)ei1.a("ReportUrl");
        ei1.a("ReportUrl", this);
        b(s);
        ex.a(4, d, (new StringBuilder()).append("initSettings, ReportUrl = ").append(s).toString());
    }

    private void b(String s)
    {
        if(s != null && !s.endsWith(".do"))
            ex.a(5, d, "overriding analytics agent report URL without an endpoint, are you sure?");
        a = s;
    }

    String a()
    {
        if(a != null)
            return a;
        if(j)
            return c;
        else
            return b;
    }

    public void a(String s, Object obj)
    {
        if(s.equals("UseHttps"))
        {
            j = ((Boolean)obj).booleanValue();
            ex.a(4, d, (new StringBuilder()).append("onSettingUpdate, UseHttps = ").append(j).toString());
            return;
        }
        if(s.equals("ReportUrl"))
        {
            String s1 = (String)obj;
            b(s1);
            ex.a(4, d, (new StringBuilder()).append("onSettingUpdate, ReportUrl = ").append(s1).toString());
            return;
        } else
        {
            ex.a(6, d, "onSettingUpdate internal error!");
            return;
        }
    }

    protected void a(String s, String s1, int i)
    {
        f.submit(new Runnable(i) {

            public void run()
            {
                if(a == 200)
                {
                    cl cl1 = bx.a().h();
                    if(cl1 != null)
                        cl1.c();
                }
            }

            final int a;
            final cd b;

            
            {
                b = cd.this;
                a = i;
                Object();
            }
        }
);
        a(s, s1, i);
    }

    protected void a(byte abyte0[], String s, String s1)
    {
        String s2 = a();
        ex.a(4, d, (new StringBuilder()).append("FlurryDataSender: start upload data ").append(abyte0).append(" with id = ").append(s).append(" to ").append(s2).toString());
        ch ch = new ch() {

            public void a(int i, String s3, String s4, String s5)
            {
                ex.e(cf.d, (new StringBuilder()).append("FlurryDataSender: report ").append(s4).append(" sent. HTTP response: ").append(i).append(" : ").append(s3).toString());
                if(ex.c() <= 3 && ex.d())
                    eg.a().a(new Runnable(this, i) {

                        public void run()
                        {
                            Toast.makeText(eg.a().b(), (new StringBuilder()).append("SD HTTP Response Code: ").append(a).toString(), 0).show();
                        }

                        final int a;
                        final _cls1 b;

            
            {
                b = _pcls1;
                a = i;
                Object();
            }
                    }
);
                a.a(s4, s5, i);
                a.d();
            }

            public void a(String s3, String s4)
            {
                ex.e(cf.d, (new StringBuilder()).append("FlurryDataSender: could not send report ").append(s3).toString());
                a.b(s3, s4);
            }

            final cd a;

            
            {
                a = cd.this;
                Object();
            }
        }
;
        g.submit(new ce(s2, s, s1, abyte0, ch));
    }

    static String a;
    static String b = "http://data.flurry.com/aap.do";
    static String c = "https://data.flurry.com/aap.do";
    private boolean j;

}
