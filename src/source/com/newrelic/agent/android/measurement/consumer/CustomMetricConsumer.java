// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.newrelic.agent.android.measurement.consumer;

import com.newrelic.agent.android.measurement.*;
import com.newrelic.agent.android.metric.Metric;

// Referenced classes of package com.newrelic.agent.android.measurement.consumer:
//            MetricMeasurementConsumer

public class CustomMetricConsumer extends MetricMeasurementConsumer
{

    public CustomMetricConsumer()
    {
        super(MeasurementType.Custom);
    }

    public void consumeMeasurement(Measurement measurement)
    {
        Metric metric = ((CustomMetricMeasurement)measurement).getCustomMetric();
        metric.setName(formatMetricName(metric.getName()));
        addMetric(metric);
    }

    protected String formatMetricName(String s)
    {
        return (new StringBuilder()).append("Custom/").append(s).toString();
    }

    private static final String METRIC_PREFIX = "Custom/";
}
