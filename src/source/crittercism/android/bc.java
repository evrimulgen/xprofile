// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package crittercism.android;

import com.newrelic.agent.android.instrumentation.JSONObjectInstrumentation;
import java.io.*;
import java.util.*;
import org.json.JSONArray;
import org.json.JSONObject;

// Referenced classes of package crittercism.android:
//            bk, bl, be, bj, 
//            dp

public final class bc
    implements bk
{
    public static final class a extends Enum
    {

        public static a valueOf(String s)
        {
            return (a)Enum.valueOf(crittercism/android/bc$a, s);
        }

        public static a[] values()
        {
            return (a[])f.clone();
        }

        public final String a()
        {
            return e;
        }

        public final String b()
        {
            return c;
        }

        public final String c()
        {
            return d;
        }

        public static final a a;
        public static final a b;
        private static final a f[];
        private String c;
        private String d;
        private String e;

        static 
        {
            a = new a("SDK", 0, "uhe", "crashed_session", "previous_session");
            b = new a("EXC", 1, "he", "current_session", "previous_session");
            a aa[] = new a[2];
            aa[0] = a;
            aa[1] = b;
            f = aa;
        }

        private a(String s, int l, String s1, String s2, String s3)
        {
            super(s, l);
            e = s1;
            c = s2;
            d = s3;
        }
    }


    public bc(Throwable throwable, long l, a a1, bj bj1, bj bj2, bj bj3)
    {
        k = a1;
        a = (new bl()).a(new bq.a()).a(new bq.c()).a(new bq.b()).a(new bq.d()).a(new bq.e()).a(new bq.f()).a(new bq.h()).a(new bq.j()).a(new bq.k()).a(new bq.i()).a(new bq.y()).a(new bq.z()).a(new bq.l()).a(new bq.m()).a(new bq.n()).a(new bq.o()).a(new bq.p()).a(new bq.q()).a(new bq.r()).a(new bq.s()).a(new bq.t()).a(new bq.u()).a(new bq.v()).a(new bq.w()).a(new bq.x()).a();
        HashMap hashmap = new HashMap();
        hashmap.put(k.b(), (new be(bj1)).a);
        if(k == a.a && bj2.c() != 0)
            hashmap.put(k.c(), (new be(bj2)).a);
        b = new JSONObject(hashmap);
        c = l;
        JSONArray jsonarray;
        String s;
        String as[];
        int i1;
        if(a1 == a.a)
            jsonarray = (new be(bj3)).a;
        else
            jsonarray = null;
        d = jsonarray;
        e = a(throwable);
        if(throwable.getMessage() == null)
            s = new String();
        else
            s = throwable.getMessage();
        f = s;
        g = "android";
        i = a();
        j = dp.a.a();
        h = new JSONArray();
        as = b(throwable);
        i1 = as.length;
        for(int j1 = 0; j1 < i1; j1++)
        {
            String s1 = as[j1];
            h.put(s1);
        }

    }

    private static String a(Throwable throwable)
    {
        do
        {
            String s = throwable.getClass().getName();
            Throwable throwable1 = throwable.getCause();
            if(throwable1 == null || throwable1 == throwable)
                return s;
            throwable = throwable1;
        } while(true);
    }

    private JSONArray a()
    {
        JSONArray jsonarray = new JSONArray();
        Iterator iterator = Thread.getAllStackTraces().entrySet().iterator();
        do
        {
            if(!iterator.hasNext())
                break;
            java.util.Map.Entry entry = (java.util.Map.Entry)iterator.next();
            HashMap hashmap = new HashMap();
            Thread thread = (Thread)entry.getKey();
            if(thread.getId() != c)
            {
                hashmap.put("name", thread.getName());
                hashmap.put("id", Long.valueOf(thread.getId()));
                hashmap.put("state", thread.getState().name());
                hashmap.put("stacktrace", new JSONArray(Arrays.asList((Object[])entry.getValue())));
                jsonarray.put(new JSONObject(hashmap));
            }
        } while(true);
        return jsonarray;
    }

    private static String[] b(Throwable throwable)
    {
        StringWriter stringwriter = new StringWriter();
        PrintWriter printwriter = new PrintWriter(stringwriter);
        do
        {
            throwable.printStackTrace(printwriter);
            Throwable throwable1 = throwable.getCause();
            if(throwable1 == null || throwable1 == throwable)
                return stringwriter.toString().split("\n");
            throwable = throwable1;
        } while(true);
    }

    public final void a(OutputStream outputstream)
    {
        HashMap hashmap = new HashMap();
        hashmap.put("app_state", a);
        hashmap.put("breadcrumbs", b);
        hashmap.put("current_thread_id", Long.valueOf(c));
        if(d != null)
            hashmap.put("endpoints", d);
        hashmap.put("exception_name", e);
        hashmap.put("exception_reason", f);
        hashmap.put("platform", g);
        hashmap.put("threads", i);
        hashmap.put("ts", j);
        String s;
        OutputStreamWriter outputstreamwriter;
        JSONObject jsonobject;
        String s1;
        if(c == 1L)
            s = k.a();
        else
            s = (new StringBuilder()).append(k.a()).append("-bg").toString();
        hashmap.put("type", s);
        hashmap.put("unsymbolized_stacktrace", h);
        outputstreamwriter = new OutputStreamWriter(outputstream);
        jsonobject = new JSONObject(hashmap);
        if(!(jsonobject instanceof JSONObject))
            s1 = jsonobject.toString();
        else
            s1 = JSONObjectInstrumentation.toString((JSONObject)jsonobject);
        outputstreamwriter.write(s1);
        outputstreamwriter.close();
    }

    private JSONObject a;
    private JSONObject b;
    private long c;
    private JSONArray d;
    private String e;
    private String f;
    private String g;
    private JSONArray h;
    private JSONArray i;
    private String j;
    private a k;
}
