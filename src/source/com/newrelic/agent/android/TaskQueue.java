// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.newrelic.agent.android;

import com.newrelic.agent.android.harvest.AgentHealth;
import com.newrelic.agent.android.harvest.AgentHealthException;
import com.newrelic.agent.android.harvest.Harvest;
import com.newrelic.agent.android.harvest.HarvestAdapter;
import com.newrelic.agent.android.measurement.http.HttpTransactionMeasurement;
import com.newrelic.agent.android.metric.Metric;
import com.newrelic.agent.android.tracing.ActivityTrace;
import com.newrelic.agent.android.tracing.Trace;
import java.util.concurrent.*;

// Referenced classes of package com.newrelic.agent.android:
//            Measurements

public class TaskQueue extends HarvestAdapter
{

    public TaskQueue()
    {
    }

    public static void backgroundDequeue()
    {
        queueExecutor.execute(dequeueTask);
    }

    public static void clear()
    {
        queue.clear();
    }

    private static void dequeue()
    {
        if(queue.size() == 0)
            return;
        Measurements.setBroadcastNewMeasurements(false);
_L2:
        if(queue.isEmpty())
            break; /* Loop/switch isn't completed */
        Exception exception;
        Object obj;
        obj = queue.remove();
        if(obj instanceof ActivityTrace)
        {
            Harvest.addActivityTrace((ActivityTrace)obj);
            continue; /* Loop/switch isn't completed */
        }
        if(obj instanceof Metric)
        {
            Harvest.addMetric((Metric)obj);
            continue; /* Loop/switch isn't completed */
        }
        if(obj instanceof AgentHealthException)
        {
            Harvest.addAgentHealthException((AgentHealthException)obj);
            continue; /* Loop/switch isn't completed */
        }
        if(obj instanceof Trace)
        {
            Measurements.addTracedMethod((Trace)obj);
            continue; /* Loop/switch isn't completed */
        }
        try
        {
            if(obj instanceof HttpTransactionMeasurement)
                Measurements.addHttpTransaction((HttpTransactionMeasurement)obj);
        }
        // Misplaced declaration of an exception variable
        catch(Exception exception)
        {
            exception.printStackTrace();
            AgentHealth.noticeException(exception);
        }
        if(true) goto _L2; else goto _L1
_L1:
        Measurements.broadcast();
        Measurements.setBroadcastNewMeasurements(true);
        return;
    }

    public static void queue(Object obj)
    {
        queue.add(obj);
    }

    public static int size()
    {
        return queue.size();
    }

    public static void start()
    {
        if(dequeueFuture != null)
        {
            return;
        } else
        {
            dequeueFuture = queueExecutor.scheduleAtFixedRate(dequeueTask, 0L, 1000L, TimeUnit.MILLISECONDS);
            return;
        }
    }

    public static void stop()
    {
        if(dequeueFuture == null)
        {
            return;
        } else
        {
            dequeueFuture.cancel(true);
            dequeueFuture = null;
            return;
        }
    }

    public static void synchronousDequeue()
    {
        Future future = queueExecutor.submit(dequeueTask);
        try
        {
            future.get();
            return;
        }
        catch(InterruptedException interruptedexception)
        {
            interruptedexception.printStackTrace();
            return;
        }
        catch(ExecutionException executionexception)
        {
            executionexception.printStackTrace();
        }
    }

    private static final long DEQUEUE_PERIOD_MS = 1000L;
    private static Future dequeueFuture;
    private static final Runnable dequeueTask = new Runnable() {

        public void run()
        {
            TaskQueue.dequeue();
        }

    }
;
    private static final ConcurrentLinkedQueue queue = new ConcurrentLinkedQueue();
    private static final ScheduledExecutorService queueExecutor = Executors.newSingleThreadScheduledExecutor();


}
