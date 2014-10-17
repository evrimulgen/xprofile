// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.newrelic.agent.android;

import com.newrelic.agent.android.activity.MeasuredActivity;
import com.newrelic.agent.android.api.common.TransactionData;
import com.newrelic.agent.android.harvest.Harvest;
import com.newrelic.agent.android.logging.AgentLog;
import com.newrelic.agent.android.logging.AgentLogManager;
import com.newrelic.agent.android.measurement.ThreadInfo;
import com.newrelic.agent.android.measurement.consumer.ActivityMeasurementConsumer;
import com.newrelic.agent.android.measurement.consumer.CustomMetricConsumer;
import com.newrelic.agent.android.measurement.consumer.HttpErrorHarvestingConsumer;
import com.newrelic.agent.android.measurement.consumer.HttpTransactionHarvestingConsumer;
import com.newrelic.agent.android.measurement.consumer.MeasurementConsumer;
import com.newrelic.agent.android.measurement.consumer.MethodMeasurementConsumer;
import com.newrelic.agent.android.measurement.consumer.SummaryMetricMeasurementConsumer;
import com.newrelic.agent.android.measurement.http.HttpTransactionMeasurement;
import com.newrelic.agent.android.measurement.producer.ActivityMeasurementProducer;
import com.newrelic.agent.android.measurement.producer.CustomMetricProducer;
import com.newrelic.agent.android.measurement.producer.HttpErrorMeasurementProducer;
import com.newrelic.agent.android.measurement.producer.MeasurementProducer;
import com.newrelic.agent.android.measurement.producer.MethodMeasurementProducer;
import com.newrelic.agent.android.measurement.producer.NetworkMeasurementProducer;
import com.newrelic.agent.android.metric.MetricUnit;
import com.newrelic.agent.android.tracing.Trace;
import java.util.Map;

// Referenced classes of package com.newrelic.agent.android:
//            MeasurementEngine, TaskQueue

public class Measurements
{

    public Measurements()
    {
    }

    public static void addCustomMetric(String s, String s1, int i, double d, double d1)
    {
        if(Harvest.isDisabled())
        {
            return;
        } else
        {
            customMetricProducer.produceMeasurement(s, s1, i, d, d1);
            newMeasurementBroadcast();
            return;
        }
    }

    public static void addCustomMetric(String s, String s1, int i, double d, double d1, MetricUnit metricunit, 
            MetricUnit metricunit1)
    {
        if(Harvest.isDisabled())
        {
            return;
        } else
        {
            customMetricProducer.produceMeasurement(s, s1, i, d, d1, metricunit, metricunit1);
            newMeasurementBroadcast();
            return;
        }
    }

    public static void addHttpError(TransactionData transactiondata, String s, Map map)
    {
        addHttpError(transactiondata.getUrl(), transactiondata.getStatusCode(), s, map);
    }

    public static void addHttpError(String s, int i)
    {
        if(Harvest.isDisabled())
        {
            return;
        } else
        {
            httpErrorMeasurementProducer.produceMeasurement(s, i);
            newMeasurementBroadcast();
            return;
        }
    }

    public static void addHttpError(String s, int i, String s1)
    {
        if(Harvest.isDisabled())
        {
            return;
        } else
        {
            httpErrorMeasurementProducer.produceMeasurement(s, i, s1);
            newMeasurementBroadcast();
            return;
        }
    }

    public static void addHttpError(String s, int i, String s1, Map map)
    {
        if(Harvest.isDisabled())
        {
            return;
        } else
        {
            httpErrorMeasurementProducer.produceMeasurement(s, i, s1, map);
            newMeasurementBroadcast();
            return;
        }
    }

    public static void addHttpError(String s, int i, String s1, Map map, ThreadInfo threadinfo)
    {
        if(Harvest.isDisabled())
        {
            return;
        } else
        {
            httpErrorMeasurementProducer.produceMeasurement(s, i, s1, map, threadinfo);
            newMeasurementBroadcast();
            return;
        }
    }

    public static void addHttpTransaction(HttpTransactionMeasurement httptransactionmeasurement)
    {
        if(Harvest.isDisabled())
        {
            return;
        } else
        {
            networkMeasurementProducer.produceMeasurement(httptransactionmeasurement);
            newMeasurementBroadcast();
            return;
        }
    }

    public static void addMeasurementConsumer(MeasurementConsumer measurementconsumer)
    {
        measurementEngine.addMeasurementConsumer(measurementconsumer);
    }

    public static void addMeasurementProducer(MeasurementProducer measurementproducer)
    {
        measurementEngine.addMeasurementProducer(measurementproducer);
    }

    public static void addTracedMethod(Trace trace)
    {
        if(Harvest.isDisabled())
        {
            return;
        } else
        {
            methodMeasurementProducer.produceMeasurement(trace);
            newMeasurementBroadcast();
            return;
        }
    }

    public static void broadcast()
    {
        measurementEngine.broadcastMeasurements();
    }

    public static void endActivity(MeasuredActivity measuredactivity)
    {
        if(Harvest.isDisabled())
        {
            return;
        } else
        {
            measurementEngine.endActivity(measuredactivity);
            activityMeasurementProducer.produceMeasurement(measuredactivity);
            newMeasurementBroadcast();
            return;
        }
    }

    public static void endActivity(String s)
    {
        if(Harvest.isDisabled())
        {
            return;
        } else
        {
            MeasuredActivity measuredactivity = measurementEngine.endActivity(s);
            activityMeasurementProducer.produceMeasurement(measuredactivity);
            newMeasurementBroadcast();
            return;
        }
    }

    public static void endActivityWithoutMeasurement(MeasuredActivity measuredactivity)
    {
        if(Harvest.isDisabled())
        {
            return;
        } else
        {
            measurementEngine.endActivity(measuredactivity);
            return;
        }
    }

    public static void initialize()
    {
        log.info("Measurement Engine initialized.");
        TaskQueue.start();
        addMeasurementProducer(httpErrorMeasurementProducer);
        addMeasurementProducer(networkMeasurementProducer);
        addMeasurementProducer(activityMeasurementProducer);
        addMeasurementProducer(methodMeasurementProducer);
        addMeasurementProducer(customMetricProducer);
        addMeasurementConsumer(httpErrorHarvester);
        addMeasurementConsumer(httpTransactionHarvester);
        addMeasurementConsumer(activityConsumer);
        addMeasurementConsumer(methodMeasurementConsumer);
        addMeasurementConsumer(summaryMetricMeasurementConsumer);
        addMeasurementConsumer(customMetricConsumer);
    }

    private static void newMeasurementBroadcast()
    {
        if(broadcastNewMeasurements)
            broadcast();
    }

    public static void process()
    {
        measurementEngine.broadcastMeasurements();
    }

    public static void removeMeasurementConsumer(MeasurementConsumer measurementconsumer)
    {
        measurementEngine.removeMeasurementConsumer(measurementconsumer);
    }

    public static void removeMeasurementProducer(MeasurementProducer measurementproducer)
    {
        measurementEngine.removeMeasurementProducer(measurementproducer);
    }

    public static void renameActivity(String s, String s1)
    {
        measurementEngine.renameActivity(s, s1);
    }

    public static void setBroadcastNewMeasurements(boolean flag)
    {
        broadcastNewMeasurements = flag;
    }

    public static void shutdown()
    {
        TaskQueue.stop();
        measurementEngine.clear();
        log.info("Measurement Engine shutting down.");
        removeMeasurementProducer(httpErrorMeasurementProducer);
        removeMeasurementProducer(networkMeasurementProducer);
        removeMeasurementProducer(activityMeasurementProducer);
        removeMeasurementProducer(methodMeasurementProducer);
        removeMeasurementProducer(customMetricProducer);
        removeMeasurementConsumer(httpErrorHarvester);
        removeMeasurementConsumer(httpTransactionHarvester);
        removeMeasurementConsumer(activityConsumer);
        removeMeasurementConsumer(methodMeasurementConsumer);
        removeMeasurementConsumer(summaryMetricMeasurementConsumer);
        removeMeasurementConsumer(customMetricConsumer);
    }

    public static MeasuredActivity startActivity(String s)
    {
        if(Harvest.isDisabled())
            return null;
        else
            return measurementEngine.startActivity(s);
    }

    private static final ActivityMeasurementConsumer activityConsumer = new ActivityMeasurementConsumer();
    private static final ActivityMeasurementProducer activityMeasurementProducer = new ActivityMeasurementProducer();
    private static boolean broadcastNewMeasurements = true;
    private static final CustomMetricConsumer customMetricConsumer = new CustomMetricConsumer();
    private static final CustomMetricProducer customMetricProducer = new CustomMetricProducer();
    private static final HttpErrorHarvestingConsumer httpErrorHarvester = new HttpErrorHarvestingConsumer();
    private static final HttpErrorMeasurementProducer httpErrorMeasurementProducer = new HttpErrorMeasurementProducer();
    private static final HttpTransactionHarvestingConsumer httpTransactionHarvester = new HttpTransactionHarvestingConsumer();
    private static final AgentLog log = AgentLogManager.getAgentLog();
    private static final MeasurementEngine measurementEngine = new MeasurementEngine();
    private static final MethodMeasurementConsumer methodMeasurementConsumer = new MethodMeasurementConsumer();
    private static final MethodMeasurementProducer methodMeasurementProducer = new MethodMeasurementProducer();
    private static final NetworkMeasurementProducer networkMeasurementProducer = new NetworkMeasurementProducer();
    private static final SummaryMetricMeasurementConsumer summaryMetricMeasurementConsumer = new SummaryMetricMeasurementConsumer();

}
