// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.newrelic.agent.android.instrumentation;

import com.google.gson.*;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import com.newrelic.agent.android.tracing.TraceMachine;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;

// Referenced classes of package com.newrelic.agent.android.instrumentation:
//            MetricCategory

public class GsonInstrumentation
{

    public GsonInstrumentation()
    {
    }

    public static Object fromJson(Gson gson, JsonElement jsonelement, Class class1)
        throws JsonSyntaxException
    {
        TraceMachine.enterMethod("Gson#fromJson", categoryParams);
        Object obj = gson.fromJson(jsonelement, class1);
        TraceMachine.exitMethod();
        return obj;
    }

    public static Object fromJson(Gson gson, JsonElement jsonelement, Type type)
        throws JsonSyntaxException
    {
        TraceMachine.enterMethod("Gson#fromJson", categoryParams);
        Object obj = gson.fromJson(jsonelement, type);
        TraceMachine.exitMethod();
        return obj;
    }

    public static Object fromJson(Gson gson, JsonReader jsonreader, Type type)
        throws JsonIOException, JsonSyntaxException
    {
        TraceMachine.enterMethod("Gson#fromJson", categoryParams);
        Object obj = gson.fromJson(jsonreader, type);
        TraceMachine.exitMethod();
        return obj;
    }

    public static Object fromJson(Gson gson, Reader reader, Class class1)
        throws JsonSyntaxException, JsonIOException
    {
        TraceMachine.enterMethod("Gson#fromJson", categoryParams);
        Object obj = gson.fromJson(reader, class1);
        TraceMachine.exitMethod();
        return obj;
    }

    public static Object fromJson(Gson gson, Reader reader, Type type)
        throws JsonIOException, JsonSyntaxException
    {
        TraceMachine.enterMethod("Gson#fromJson", categoryParams);
        Object obj = gson.fromJson(reader, type);
        TraceMachine.exitMethod();
        return obj;
    }

    public static Object fromJson(Gson gson, String s, Class class1)
        throws JsonSyntaxException
    {
        TraceMachine.enterMethod("Gson#fromJson", categoryParams);
        Object obj = gson.fromJson(s, class1);
        TraceMachine.exitMethod();
        return obj;
    }

    public static Object fromJson(Gson gson, String s, Type type)
        throws JsonSyntaxException
    {
        TraceMachine.enterMethod("Gson#fromJson", categoryParams);
        Object obj = gson.fromJson(s, type);
        TraceMachine.exitMethod();
        return obj;
    }

    public static String toJson(Gson gson, JsonElement jsonelement)
    {
        TraceMachine.enterMethod("Gson#toJson", categoryParams);
        String s = gson.toJson(jsonelement);
        TraceMachine.exitMethod();
        return s;
    }

    public static String toJson(Gson gson, Object obj)
    {
        TraceMachine.enterMethod("Gson#toJson", categoryParams);
        String s = gson.toJson(obj);
        TraceMachine.exitMethod();
        return s;
    }

    public static String toJson(Gson gson, Object obj, Type type)
    {
        TraceMachine.enterMethod("Gson#toJson", categoryParams);
        String s = gson.toJson(obj, type);
        TraceMachine.exitMethod();
        return s;
    }

    public static void toJson(Gson gson, JsonElement jsonelement, JsonWriter jsonwriter)
        throws JsonIOException
    {
        TraceMachine.enterMethod("Gson#toJson", categoryParams);
        gson.toJson(jsonelement, jsonwriter);
        TraceMachine.exitMethod();
    }

    public static void toJson(Gson gson, JsonElement jsonelement, Appendable appendable)
        throws JsonIOException
    {
        TraceMachine.enterMethod("Gson#toJson", categoryParams);
        gson.toJson(jsonelement, appendable);
        TraceMachine.exitMethod();
    }

    public static void toJson(Gson gson, Object obj, Appendable appendable)
        throws JsonIOException
    {
        TraceMachine.enterMethod("Gson#toJson", categoryParams);
        gson.toJson(obj, appendable);
        TraceMachine.exitMethod();
    }

    public static void toJson(Gson gson, Object obj, Type type, JsonWriter jsonwriter)
        throws JsonIOException
    {
        TraceMachine.enterMethod("Gson#toJson", categoryParams);
        gson.toJson(obj, type, jsonwriter);
        TraceMachine.exitMethod();
    }

    public static void toJson(Gson gson, Object obj, Type type, Appendable appendable)
        throws JsonIOException
    {
        TraceMachine.enterMethod("Gson#toJson", categoryParams);
        gson.toJson(obj, type, appendable);
        TraceMachine.exitMethod();
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
