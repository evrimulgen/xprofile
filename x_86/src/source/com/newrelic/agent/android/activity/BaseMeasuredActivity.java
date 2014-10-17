// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.newrelic.agent.android.activity;

import com.newrelic.agent.android.measurement.*;
import com.newrelic.agent.android.tracing.TraceMachine;

// Referenced classes of package com.newrelic.agent.android.activity:
//            MeasuredActivity

public class BaseMeasuredActivity
    implements MeasuredActivity
{

    public BaseMeasuredActivity()
    {
    }

    private void throwIfFinished()
    {
        if(finished)
            throw new MeasurementException("Cannot modify finished Activity");
        else
            return;
    }

    public void finish()
    {
        finished = true;
    }

    public String getBackgroundMetricName()
    {
        return TraceMachine.formatActivityBackgroundMetricName(name);
    }

    public long getEndTime()
    {
        return endTime;
    }

    public Measurement getEndingMeasurement()
    {
        return endingMeasurement;
    }

    public ThreadInfo getEndingThread()
    {
        return endingThread;
    }

    public MeasurementPool getMeasurementPool()
    {
        return measurementPool;
    }

    public String getMetricName()
    {
        return TraceMachine.formatActivityMetricName(name);
    }

    public String getName()
    {
        return name;
    }

    public long getStartTime()
    {
        return startTime;
    }

    public Measurement getStartingMeasurement()
    {
        return startingMeasurement;
    }

    public ThreadInfo getStartingThread()
    {
        return startingThread;
    }

    public boolean isAutoInstrumented()
    {
        return autoInstrumented;
    }

    public boolean isFinished()
    {
        return finished;
    }

    public void setAutoInstrumented(boolean flag)
    {
        throwIfFinished();
        autoInstrumented = flag;
    }

    public void setEndTime(long l)
    {
        throwIfFinished();
        endTime = l;
    }

    public void setEndingMeasurement(Measurement measurement)
    {
        throwIfFinished();
        endingMeasurement = measurement;
    }

    public void setEndingThread(ThreadInfo threadinfo)
    {
        throwIfFinished();
        endingThread = threadinfo;
    }

    public void setMeasurementPool(MeasurementPool measurementpool)
    {
        throwIfFinished();
        measurementPool = measurementpool;
    }

    public void setName(String s)
    {
        throwIfFinished();
        name = s;
    }

    public void setStartTime(long l)
    {
        throwIfFinished();
        startTime = l;
    }

    public void setStartingMeasurement(Measurement measurement)
    {
        throwIfFinished();
        startingMeasurement = measurement;
    }

    public void setStartingThread(ThreadInfo threadinfo)
    {
        throwIfFinished();
        startingThread = threadinfo;
    }

    private boolean autoInstrumented;
    private long endTime;
    private Measurement endingMeasurement;
    private ThreadInfo endingThread;
    private boolean finished;
    private MeasurementPool measurementPool;
    private String name;
    private long startTime;
    private Measurement startingMeasurement;
    private ThreadInfo startingThread;
}
