// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package crittercism.android;

import android.content.Context;
import android.content.SharedPreferences;
import com.newrelic.agent.android.instrumentation.JSONObjectInstrumentation;
import org.json.JSONException;
import org.json.JSONObject;

// Referenced classes of package crittercism.android:
//            cv, cf, aw, h

public final class cy extends cv
{

    public cy(Context context, aw aw1, boolean flag)
    {
        a = aw1;
        b = flag;
        c = context;
    }

    public final void a()
    {
        (new StringBuilder("Setting opt out status to ")).append(b).append(".  This will take effect in the next user session.");
        boolean flag = b;
        aw aw1 = a;
        String s = cf.j.a();
        String s1 = cf.j.b();
        JSONObject jsonobject = new JSONObject();
        String s2;
        try
        {
            jsonobject.put("optOutStatus", flag).put("optOutStatusSet", true);
        }
        catch(JSONException jsonexception) { }
        if(!(jsonobject instanceof JSONObject))
            s2 = jsonobject.toString();
        else
            s2 = JSONObjectInstrumentation.toString((JSONObject)jsonobject);
        aw1.a(s, s1, s2);
        if(b)
        {
            android.content.SharedPreferences.Editor editor = c.getSharedPreferences("com.crittercism.optmz.config", 0).edit();
            editor.clear();
            editor.commit();
            h.b(c);
        }
    }

    private aw a;
    private final boolean b;
    private Context c;
}
