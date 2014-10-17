// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package crittercism.android;

import com.newrelic.agent.android.instrumentation.JSONObjectInstrumentation;
import org.json.JSONException;
import org.json.JSONObject;

// Referenced classes of package crittercism.android:
//            bm, aw

public final class di
    implements bm
{
    public static final class a
    {

        public static di a(aw aw1, String s, String s1)
        {
            String s2;
            JSONObject jsonobject;
            s2 = aw1.a(s, s1);
            jsonobject = null;
            if(s2 == null)
                break MISSING_BLOCK_LABEL_30;
            JSONObject jsonobject2;
            JVM INSTR new #15  <Class JSONObject>;
            jsonobject2 = JSONObjectInstrumentation.init(s2);
            jsonobject = jsonobject2;
            JSONObject jsonobject1 = jsonobject;
_L1:
            JSONException jsonexception;
            if(jsonobject1 != null)
                return new di(jsonobject1);
            else
                return new di();
            jsonexception;
            jsonobject1 = null;
              goto _L1
        }
    }


    public di()
    {
        a = false;
        b = false;
        c = 0;
        d = 5;
        e = 5;
        f = "Would you mind taking a second to rate my app?  I would really appreciate it!";
        g = "Rate My App";
    }

    public di(di di1)
    {
        a = di1.a;
        b = di1.b;
        c = di1.c;
        d = di1.d;
        e = di1.e;
        f = di1.f;
        g = di1.g;
    }

    public di(JSONObject jsonobject)
    {
        a = jsonobject.optBoolean("rateMyAppEnabled", false);
        b = jsonobject.optBoolean("hasRatedApp", false);
        c = jsonobject.optInt("numAppLoads", 0);
        d = jsonobject.optInt("rateAfterNumLoads", 5);
        e = jsonobject.optInt("remindAfterNumLoads", 5);
        f = jsonobject.optString("rateAppMessage", "Would you mind taking a second to rate my app?  I would really appreciate it!");
        g = jsonobject.optString("rateAppTitle", "Rate My App");
    }

    private JSONObject d()
    {
        JSONObject jsonobject = new JSONObject();
        try
        {
            jsonobject.put("rateAfterNumLoads", d).put("remindAfterNumLoads", e).put("rateAppMessage", f).put("rateAppTitle", g).put("hasRatedApp", b).put("numAppLoads", c).put("rateMyAppEnabled", a);
        }
        catch(JSONException jsonexception)
        {
            return jsonobject;
        }
        return jsonobject;
    }

    public final void a()
    {
        this;
        JVM INSTR monitorenter ;
        a = true;
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public final void a(aw aw1, String s, String s1)
    {
        this;
        JVM INSTR monitorenter ;
        JSONObject jsonobject = d();
        if(jsonobject instanceof JSONObject) goto _L2; else goto _L1
_L1:
        String s3 = jsonobject.toString();
_L3:
        aw1.a(s, s1, s3);
        this;
        JVM INSTR monitorexit ;
        return;
_L2:
        String s2 = JSONObjectInstrumentation.toString((JSONObject)jsonobject);
        s3 = s2;
          goto _L3
        Exception exception;
        exception;
        throw exception;
    }

    public final Object b()
    {
        return d();
    }

    public final void c()
    {
        this;
        JVM INSTR monitorenter ;
        a = false;
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public boolean a;
    public boolean b;
    public int c;
    public int d;
    public int e;
    public String f;
    public String g;
}
