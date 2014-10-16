// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.newrelic.agent.android.measurement.producer;

import com.newrelic.agent.android.measurement.CustomMetricMeasurement;
import com.newrelic.agent.android.measurement.MeasurementType;
import com.newrelic.agent.android.metric.MetricUnit;

// Referenced classes of package com.newrelic.agent.android.measurement.producer:
//            BaseMeasurementProducer

public class CustomMetricProducer extends BaseMeasurementProducer
{

    public CustomMetricProducer()
    {
        super(MeasurementType.Custom);
    }

    private String createMetricName(String s, String s1, MetricUnit metricunit, MetricUnit metricunit1)
    {
        StringBuffer stringbuffer = new StringBuffer();
        stringbuffer.append(s1.replaceAll("[/\\[\\]|*]", ""));
        stringbuffer.append("/");
        stringbuffer.append(s.replaceAll("[/\\[\\]|*]", ""));
        if(metricunit != null || metricunit1 != null)
        {
            stringbuffer.append("[");
            if(metricunit1 != null)
                stringbuffer.append(metricunit1.getLabel());
            if(metricunit != null)
            {
                stringbuffer.append("|");
                stringbuffer.append(metricunit.getLabel());
            }
            stringbuffer.append("]");
        }
        return stringbuffer.toString();
    }

    public void produceMeasurement(String s, String s1, int i, double d, double d1)
    {
        produceMeasurement(s1, s, i, d, d1, null, null);
    }

    public void produceMeasurement(String s, String s1, int i, double d, double d1, 
            MetricUnit metricunit, MetricUnit metricunit1)
    {
        produceMeasurement(((com.newrelic.agent.android.measurement.Measurement) (new CustomMetricMeasurement(createMetricName(s, s1, metricunit, metricunit1), i, d, d1))));
    }

    private static final String FILTER_REGEX = "[/\\[\\]|*]";
}
