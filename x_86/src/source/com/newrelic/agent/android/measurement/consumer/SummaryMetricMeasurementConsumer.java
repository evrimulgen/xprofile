// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.newrelic.agent.android.measurement.consumer;

import com.newrelic.agent.android.harvest.Harvest;
import com.newrelic.agent.android.instrumentation.MetricCategory;
import com.newrelic.agent.android.logging.AgentLog;
import com.newrelic.agent.android.logging.AgentLogManager;
import com.newrelic.agent.android.measurement.*;
import com.newrelic.agent.android.measurement.http.HttpTransactionMeasurement;
import com.newrelic.agent.android.metric.Metric;
import com.newrelic.agent.android.metric.MetricStore;
import com.newrelic.agent.android.tracing.*;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

// Referenced classes of package com.newrelic.agent.android.measurement.consumer:
//            MetricMeasurementConsumer

public class SummaryMetricMeasurementConsumer extends MetricMeasurementConsumer
    implements TraceLifecycleAware
{

    public SummaryMetricMeasurementConsumer()
    {
        super(MeasurementType.Any);
        recordUnscopedMetrics = false;
        TraceMachine.addTraceListener(this);
    }

    private void consumeCustomMeasurement(CustomMetricMeasurement custommetricmeasurement)
    {
        if(custommetricmeasurement.getCategory() == null || custommetricmeasurement.getCategory() == MetricCategory.NONE)
        {
            return;
        } else
        {
            BaseMeasurement basemeasurement = new BaseMeasurement(custommetricmeasurement);
            basemeasurement.setName(custommetricmeasurement.getCategory().getCategoryName());
            super.consumeMeasurement(basemeasurement);
            return;
        }
    }

    private void consumeMethodMeasurement(MethodMeasurement methodmeasurement)
    {
        if(methodmeasurement.getCategory() == null || methodmeasurement.getCategory() == MetricCategory.NONE)
        {
            methodmeasurement.setCategory(MetricCategory.categoryForMethod(methodmeasurement.getName()));
            if(methodmeasurement.getCategory() == MetricCategory.NONE)
                return;
        }
        BaseMeasurement basemeasurement = new BaseMeasurement(methodmeasurement);
        basemeasurement.setName(methodmeasurement.getCategory().getCategoryName());
        super.consumeMeasurement(basemeasurement);
    }

    private void consumeNetworkMeasurement(HttpTransactionMeasurement httptransactionmeasurement)
    {
        BaseMeasurement basemeasurement = new BaseMeasurement(httptransactionmeasurement);
        basemeasurement.setName(MetricCategory.NETWORK.getCategoryName());
        super.consumeMeasurement(basemeasurement);
    }

    private void summarizeActivityMetrics(ActivityTrace activitytrace)
    {
        Trace trace = activitytrace.rootTrace;
        List list = metrics.removeAllWithScope(trace.metricName);
        List list1 = metrics.removeAllWithScope(trace.metricBackgroundName);
        HashMap hashmap = new HashMap();
        Metric metric3;
        for(Iterator iterator = list.iterator(); iterator.hasNext(); hashmap.put(metric3.getName(), metric3))
            metric3 = (Metric)iterator.next();

        for(Iterator iterator1 = list1.iterator(); iterator1.hasNext();)
        {
            Metric metric2 = (Metric)iterator1.next();
            if(hashmap.containsKey(metric2.getName()))
                ((Metric)hashmap.get(metric2.getName())).aggregate(metric2);
            else
                hashmap.put(metric2.getName(), metric2);
        }

        double d = 0.0D;
        for(Iterator iterator2 = hashmap.values().iterator(); iterator2.hasNext();)
            d += ((Metric)iterator2.next()).getExclusive();

        double d1 = (double)(trace.exitTimestamp - trace.entryTimestamp) / 1000D;
        Metric metric1;
        for(Iterator iterator3 = hashmap.values().iterator(); iterator3.hasNext(); Harvest.addMetric(metric1))
        {
            Metric metric = (Metric)iterator3.next();
            double d2 = 0.0D;
            if(metric.getExclusive() != 0.0D && d != 0.0D)
                d2 = metric.getExclusive() / d;
            double d3 = d2 * d1;
            metric.setTotal(Double.valueOf(d2));
            metric.setExclusive(Double.valueOf(d2));
            metric.setMinFieldValue(Double.valueOf(0.0D));
            metric.setMaxFieldValue(Double.valueOf(0.0D));
            metric.setSumOfSquares(Double.valueOf(0.0D));
            metric.setScope((new StringBuilder()).append("Mobile/Activity/Summary/Name/").append(trace.displayName).toString());
            metric.setTotal(Double.valueOf(d3));
            metric.setExclusive(Double.valueOf(d3));
            Harvest.addMetric(metric);
            metric1 = new Metric(metric);
            metric1.setScope(null);
        }

    }

    public void consumeMeasurement(Measurement measurement)
    {
        if(measurement == null)
            return;
        static class _cls1
        {

            static final int $SwitchMap$com$newrelic$agent$android$measurement$MeasurementType[];

            static 
            {
                $SwitchMap$com$newrelic$agent$android$measurement$MeasurementType = new int[MeasurementType.values().length];
                try
                {
                    $SwitchMap$com$newrelic$agent$android$measurement$MeasurementType[MeasurementType.Method.ordinal()] = 1;
                }
                catch(NoSuchFieldError nosuchfielderror) { }
                try
                {
                    $SwitchMap$com$newrelic$agent$android$measurement$MeasurementType[MeasurementType.Network.ordinal()] = 2;
                }
                catch(NoSuchFieldError nosuchfielderror1) { }
                try
                {
                    $SwitchMap$com$newrelic$agent$android$measurement$MeasurementType[MeasurementType.Custom.ordinal()] = 3;
                }
                catch(NoSuchFieldError nosuchfielderror2)
                {
                    return;
                }
            }
        }

        switch(_cls1..SwitchMap.com.newrelic.agent.android.measurement.MeasurementType[measurement.getType().ordinal()])
        {
        default:
            return;

        case 1: // '\001'
            consumeMethodMeasurement((MethodMeasurement)measurement);
            return;

        case 2: // '\002'
            consumeNetworkMeasurement((HttpTransactionMeasurement)measurement);
            return;

        case 3: // '\003'
            consumeCustomMeasurement((CustomMetricMeasurement)measurement);
            break;
        }
    }

    protected String formatMetricName(String s)
    {
        return (new StringBuilder()).append("Mobile/Summary/").append(s.replace("#", "/")).toString();
    }

    public void onEnterMethod()
    {
    }

    public void onExitMethod()
    {
    }

    public void onHarvest()
    {
        while(metrics.getAll().size() == 0 || completedTraces.size() == 0) 
            return;
        for(Iterator iterator = completedTraces.iterator(); iterator.hasNext(); summarizeActivityMetrics((ActivityTrace)iterator.next()));
        if(metrics.getAll().size() != 0)
            log.debug("Not all metrics were summarized!");
        completedTraces.clear();
    }

    public void onHarvestComplete()
    {
    }

    public void onHarvestError()
    {
    }

    public void onTraceComplete(ActivityTrace activitytrace)
    {
        if(!completedTraces.contains(activitytrace))
            completedTraces.add(activitytrace);
    }

    public void onTraceStart(ActivityTrace activitytrace)
    {
    }

    private static final String ACTIVITY_METRIC_PREFIX = "Mobile/Activity/Summary/Name/";
    private static final String METRIC_PREFIX = "Mobile/Summary/";
    private static final AgentLog log = AgentLogManager.getAgentLog();
    private final List completedTraces = new CopyOnWriteArrayList();

}
