// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.newrelic.agent.android.instrumentation;

import com.newrelic.agent.android.tracing.TraceMachine;
import java.util.ArrayList;
import java.util.Arrays;
import org.json.JSONArray;
import org.json.JSONException;

// Referenced classes of package com.newrelic.agent.android.instrumentation:
//            MetricCategory

public class JSONArrayInstrumentation
{

    JSONArrayInstrumentation()
    {
    }

    public static JSONArray init(String s)
        throws JSONException
    {
        JSONArray jsonarray;
        try
        {
            TraceMachine.enterMethod("JSONArray#<init>", categoryParams);
            jsonarray = new JSONArray(s);
            TraceMachine.exitMethod();
        }
        catch(JSONException jsonexception)
        {
            TraceMachine.exitMethod();
            throw jsonexception;
        }
        return jsonarray;
    }

    public static String toString(JSONArray jsonarray)
    {
        TraceMachine.enterMethod("JSONArray#toString", categoryParams);
        String s = jsonarray.toString();
        TraceMachine.exitMethod();
        return s;
    }

    public static String toString(JSONArray jsonarray, int i)
        throws JSONException
    {
        String s;
        try
        {
            TraceMachine.enterMethod("JSONArray#toString", categoryParams);
            s = jsonarray.toString(i);
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
