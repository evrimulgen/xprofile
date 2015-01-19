// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.newrelic.agent.android.measurement.consumer;

import com.newrelic.agent.android.measurement.MeasurementType;

// Referenced classes of package com.newrelic.agent.android.measurement.consumer:
//            MetricMeasurementConsumer

public class MethodMeasurementConsumer extends MetricMeasurementConsumer
{

    public MethodMeasurementConsumer()
    {
        super(MeasurementType.Method);
    }

    protected String formatMetricName(String s)
    {
        return (new StringBuilder()).append("Method/").append(s.replace("#", "/")).toString();
    }

    private static final String METRIC_PREFIX = "Method/";
}
