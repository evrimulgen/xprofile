// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.bugsnag;

import com.bugsnag.utils.JSONUtils;
import com.newrelic.agent.android.instrumentation.JSONObjectInstrumentation;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

// Referenced classes of package com.bugsnag:
//            MetaData, Configuration, Diagnostics, Logger

public class Error
{

    public Error(Throwable throwable, String s, MetaData metadata, Configuration configuration, Diagnostics diagnostics1)
    {
        exception = throwable;
        config = configuration;
        metaData = metadata;
        diagnostics = diagnostics1;
        if(s == null || !ALLOWED_SEVERITIES.contains(s))
            severity = "error";
        else
            severity = s;
        if(metaData == null)
            metaData = new MetaData();
    }

    public void addToTab(String s, String s1, Object obj)
    {
        metaData.addToTab(s, s1, obj);
    }

    public boolean shouldIgnore()
    {
        return config.shouldIgnore(exception.getClass().getName());
    }

    public JSONObject toJSON()
    {
        JSONObject jsonobject;
        JSONArray jsonarray;
        Throwable throwable;
        jsonobject = new JSONObject();
        JSONUtils.safePut(jsonobject, "user", diagnostics.getUser());
        JSONUtils.safePutOpt(jsonobject, "app", diagnostics.getAppData());
        JSONUtils.safePutOpt(jsonobject, "appState", diagnostics.getAppState());
        JSONUtils.safePutOpt(jsonobject, "device", diagnostics.getDeviceData());
        JSONUtils.safePutOpt(jsonobject, "deviceState", diagnostics.getDeviceState());
        JSONUtils.safePut(jsonobject, "context", diagnostics.getContext());
        JSONUtils.safePut(jsonobject, "severity", severity);
        jsonarray = new JSONArray();
        throwable = exception;
_L9:
        if(throwable == null)
            break; /* Loop/switch isn't completed */
        JSONObject jsonobject1 = new JSONObject();
        JSONUtils.safePut(jsonobject1, "errorClass", throwable.getClass().getName());
        JSONUtils.safePut(jsonobject1, "message", throwable.getLocalizedMessage());
        JSONArray jsonarray1 = new JSONArray();
        StackTraceElement astacktraceelement[] = throwable.getStackTrace();
        int i = astacktraceelement.length;
        Throwable throwable1;
        StackTraceElement stacktraceelement;
        JSONObject jsonobject2;
        Exception exception1;
        String s;
        String as[];
        int k;
        int l;
        String s1;
        String s2;
        for(int j = 0; j >= i; j++)
            break MISSING_BLOCK_LABEL_379;

        stacktraceelement = astacktraceelement[j];
        jsonobject2 = new JSONObject();
        JSONUtils.safePut(jsonobject2, "method", (new StringBuilder()).append(stacktraceelement.getClassName()).append(".").append(stacktraceelement.getMethodName()).toString());
        if(stacktraceelement.getFileName() != null) goto _L2; else goto _L1
_L1:
        s = "Unknown";
_L6:
        JSONUtils.safePut(jsonobject2, "file", s);
        JSONUtils.safePut(jsonobject2, "lineNumber", Integer.valueOf(stacktraceelement.getLineNumber()));
        if(config.projectPackages == null) goto _L4; else goto _L3
_L3:
        as = config.projectPackages;
        k = as.length;
        l = 0;
_L7:
        if(l >= k) goto _L4; else goto _L5
_L5:
        try
        {
            s1 = as[l];
        }
        // Misplaced declaration of an exception variable
        catch(Exception exception1)
        {
            exception1.printStackTrace(System.err);
            break MISSING_BLOCK_LABEL_446;
        }
        if(s1 == null)
            break MISSING_BLOCK_LABEL_360;
        if(!stacktraceelement.getClassName().startsWith(s1))
            break MISSING_BLOCK_LABEL_360;
        jsonobject2.put("inProject", true);
_L4:
        jsonarray1.put(jsonobject2);
        break MISSING_BLOCK_LABEL_446;
_L2:
        s2 = stacktraceelement.getFileName();
        s = s2;
          goto _L6
        l++;
          goto _L7
        JSONUtils.safePut(jsonobject1, "stacktrace", jsonarray1);
        throwable1 = throwable.getCause();
        jsonarray.put(jsonobject1);
        throwable = throwable1;
        if(true) goto _L9; else goto _L8
_L8:
        JSONUtils.safePut(jsonobject, "exceptions", jsonarray);
        JSONUtils.safePut(jsonobject, "metaData", config.getMetaData().merge(metaData).filter(config.filters));
        return jsonobject;
    }

    public String toString()
    {
        JSONObject jsonobject = toJSON();
        if(!(jsonobject instanceof JSONObject))
            return jsonobject.toString();
        else
            return JSONObjectInstrumentation.toString((JSONObject)jsonobject);
    }

    public void writeToFile(String s)
        throws IOException
    {
        String s1;
        s1 = toString();
        if(s1.length() <= 0)
            break MISSING_BLOCK_LABEL_58;
        FileWriter filewriter = new FileWriter(s);
        filewriter.write(s1);
        filewriter.flush();
        config.logger.debug(String.format("Saved unsent error to disk (%s) ", new Object[] {
            s
        }));
        filewriter.close();
        return;
        Exception exception1;
        exception1;
        filewriter = null;
_L2:
        if(filewriter != null)
            filewriter.close();
        throw exception1;
        exception1;
        if(true) goto _L2; else goto _L1
_L1:
    }

    private static final List ALLOWED_SEVERITIES = Arrays.asList(new String[] {
        "fatal", "error", "warning", "info"
    });
    private Configuration config;
    private Diagnostics diagnostics;
    private Throwable exception;
    private MetaData metaData;
    private String severity;

}
