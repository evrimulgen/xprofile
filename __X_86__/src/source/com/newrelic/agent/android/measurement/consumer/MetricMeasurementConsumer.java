// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.newrelic.agent.android.measurement.consumer;

import com.newrelic.agent.android.harvest.Harvest;
import com.newrelic.agent.android.harvest.HarvestLifecycleAware;
import com.newrelic.agent.android.measurement.Measurement;
import com.newrelic.agent.android.measurement.MeasurementType;
import com.newrelic.agent.android.metric.Metric;
import com.newrelic.agent.android.metric.MetricStore;
import java.util.Iterator;
import java.util.List;

// Referenced classes of package com.newrelic.agent.android.measurement.consumer:
//            BaseMeasurementConsumer

public abstract class MetricMeasurementConsumer extends BaseMeasurementConsumer
    implements HarvestLifecycleAware
{

    public MetricMeasurementConsumer(MeasurementType measurementtype)
    {
        super(measurementtype);
        recordUnscopedMetrics = true;
        metrics = new MetricStore();
        Harvest.addHarvestListener(this);
    }

    protected void addMetric(Metric metric)
    {
        Metric metric1;
        if(metric.getScope() != null)
            metric1 = metrics.get(metric.getName(), metric.getScope());
        else
            metric1 = metrics.get(metric.getName());
        if(metric1 != null)
        {
            metric1.aggregate(metric);
            return;
        } else
        {
            metrics.add(metric);
            return;
        }
    }

    public void consumeMeasurement(Measurement measurement)
    {
        String s = formatMetricName(measurement.getName());
        String s1 = measurement.getScope();
        double d = measurement.getEndTimeInSeconds() - measurement.getStartTimeInSeconds();
        if(s1 != null)
        {
            Metric metric1 = metrics.get(s, s1);
            if(metric1 == null)
            {
                metric1 = new Metric(s, s1);
                metrics.add(metric1);
            }
            metric1.sample(d);
            metric1.addExclusive(measurement.getExclusiveTimeInSeconds());
        }
        if(!recordUnscopedMetrics)
            return;
        Metric metric = metrics.get(s);
        if(metric == null)
        {
            metric = new Metric(s);
            metrics.add(metric);
        }
        metric.sample(d);
        metric.addExclusive(measurement.getExclusiveTimeInSeconds());
    }

    protected abstract String formatMetricName(String s);

    public void onHarvest()
    {
        for(Iterator iterator = metrics.getAll().iterator(); iterator.hasNext(); Harvest.addMetric((Metric)iterator.next()));
    }

    public void onHarvestComplete()
    {
        metrics.clear();
    }

    public void onHarvestError()
    {
        metrics.clear();
    }

    public void onHarvestSendFailed()
    {
        metrics.clear();
    }

    protected MetricStore metrics;
    protected boolean recordUnscopedMetrics;
}
