// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.newrelic.agent.android.instrumentation;

import com.newrelic.agent.android.tracing.TraceMachine;
import java.util.ArrayList;
import java.util.Arrays;
import org.json.JSONException;
import org.json.JSONObject;

// Referenced classes of package com.newrelic.agent.android.instrumentation:
//            MetricCategory

public class JSONObjectInstrumentation
{

    JSONObjectInstrumentation()
    {
    }

    public static JSONObject init(String s)
        throws JSONException
    {
        JSONObject jsonobject;
        try
        {
            TraceMachine.enterMethod(null, "JSONObject#<init>", categoryParams);
            jsonobject = new JSONObject(s);
            TraceMachine.exitMethod();
        }
        catch(JSONException jsonexception)
        {
            TraceMachine.exitMethod();
            throw jsonexception;
        }
        return jsonobject;
    }

    public static String toString(JSONObject jsonobject)
    {
        TraceMachine.enterMethod(null, "JSONObject#toString", categoryParams);
        String s = jsonobject.toString();
        TraceMachine.exitMethod();
        return s;
    }

    public static String toString(JSONObject jsonobject, int i)
        throws JSONException
    {
        TraceMachine.enterMethod(null, "JSONObject#toString", categoryParams);
        String s;
        try
        {
            s = jsonobject.toString(i);
            TraceMachine.exitMethod();
        }
        catch(JSONException jsonexception)
        {
            TraceMachine.exitMethod();
            throw jsonexception;
        }
        return s;
    }

    private static final ArrayList categoryParams;

    static 
    {
        String as[] = new String[3];
        as[0] = "category";
        as[1] = com/newrelic/agent/android/instrumentation/MetricCategory.getName();
        as[2] = "JSON";
        categoryParams = new ArrayList(Arrays.asList(as));
    }
}
