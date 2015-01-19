// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.newrelic.agent.android.measurement.producer;

import com.newrelic.agent.android.Agent;
import com.newrelic.agent.android.measurement.MeasurementType;
import com.newrelic.agent.android.measurement.ThreadInfo;
import com.newrelic.agent.android.measurement.http.HttpErrorMeasurement;
import com.newrelic.agent.android.util.Util;
import java.util.Map;

// Referenced classes of package com.newrelic.agent.android.measurement.producer:
//            BaseMeasurementProducer

public class HttpErrorMeasurementProducer extends BaseMeasurementProducer
{

    public HttpErrorMeasurementProducer()
    {
        super(MeasurementType.HttpError);
    }

    private String getSanitizedStackTrace()
    {
        StringBuilder stringbuilder = new StringBuilder();
        StackTraceElement astacktraceelement[] = Thread.currentThread().getStackTrace();
        int i = 0;
        int j = 0;
        do
        {
label0:
            {
                if(j < astacktraceelement.length)
                {
                    StackTraceElement stacktraceelement = astacktraceelement[j];
                    if(shouldFilterStackTraceElement(stacktraceelement))
                        break label0;
                    stringbuilder.append(stacktraceelement.toString());
                    if(j <= -1 + astacktraceelement.length)
                        stringbuilder.append("\n");
                    if(++i < Agent.getStackTraceLimit())
                        break label0;
                }
                return stringbuilder.toString();
            }
            j++;
        } while(true);
    }

    private boolean shouldFilterStackTraceElement(StackTraceElement stacktraceelement)
    {
        String s = stacktraceelement.getClassName();
        for(String s1 = stacktraceelement.getMethodName(); s.startsWith("com.newrelic") || s.startsWith("dalvik.system.VMStack") && s1.startsWith("getThreadStackTrace") || s.startsWith("java.lang.Thread") && s1.startsWith("getStackTrace");)
            return true;

        return false;
    }

    public void produceMeasurement(String s, int i)
    {
        produceMeasurement(s, i, "");
    }

    public void produceMeasurement(String s, int i, String s1)
    {
        produceMeasurement(s, i, s1, null);
    }

    public void produceMeasurement(String s, int i, String s1, Map map)
    {
        produceMeasurement(s, i, s1, map, new ThreadInfo());
    }

    public void produceMeasurement(String s, int i, String s1, Map map, ThreadInfo threadinfo)
    {
        String s2 = Util.sanitizeUrl(s);
        if(s2 == null)
        {
            return;
        } else
        {
            HttpErrorMeasurement httperrormeasurement = new HttpErrorMeasurement(s2, i);
            httperrormeasurement.setThreadInfo(threadinfo);
            httperrormeasurement.setStackTrace(getSanitizedStackTrace());
            httperrormeasurement.setResponseBody(s1);
            httperrormeasurement.setParams(map);
            produceMeasurement(((com.newrelic.agent.android.measurement.Measurement) (httperrormeasurement)));
            return;
        }
    }
}
