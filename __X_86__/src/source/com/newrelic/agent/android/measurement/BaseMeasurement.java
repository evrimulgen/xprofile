// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.newrelic.agent.android.measurement;

import com.newrelic.agent.android.logging.AgentLog;
import com.newrelic.agent.android.logging.AgentLogManager;

// Referenced classes of package com.newrelic.agent.android.measurement:
//            Measurement, MeasurementException, ThreadInfo, MeasurementType

public class BaseMeasurement
    implements Measurement
{

    public BaseMeasurement(Measurement measurement)
    {
        setType(measurement.getType());
        setName(measurement.getName());
        setScope(measurement.getScope());
        setStartTime(measurement.getStartTime());
        setEndTime(measurement.getEndTime());
        setExclusiveTime(measurement.getExclusiveTime());
        setThreadInfo(measurement.getThreadInfo());
        finished = measurement.isFinished();
    }

    public BaseMeasurement(MeasurementType measurementtype)
    {
        setType(measurementtype);
    }

    private void throwIfFinished()
    {
        if(finished)
            throw new MeasurementException("Attempted to modify finished Measurement");
        else
            return;
    }

    public double asDouble()
    {
        throw new UnsupportedOperationException();
    }

    public void finish()
    {
        if(finished)
        {
            throw new MeasurementException("Finish called on already finished Measurement");
        } else
        {
            finished = true;
            return;
        }
    }

    public long getEndTime()
    {
        return endTime;
    }

    public double getEndTimeInSeconds()
    {
        return (double)endTime / 1000D;
    }

    public long getExclusiveTime()
    {
        return exclusiveTime;
    }

    public double getExclusiveTimeInSeconds()
    {
        return (double)exclusiveTime / 1000D;
    }

    public String getName()
    {
        return name;
    }

    public String getScope()
    {
        return scope;
    }

    public long getStartTime()
    {
        return startTime;
    }

    public double getStartTimeInSeconds()
    {
        return (double)startTime / 1000D;
    }

    public ThreadInfo getThreadInfo()
    {
        return threadInfo;
    }

    public MeasurementType getType()
    {
        return type;
    }

    public boolean isFinished()
    {
        return finished;
    }

    public boolean isInstantaneous()
    {
        return endTime == 0L;
    }

    public void setEndTime(long l)
    {
        throwIfFinished();
        if(l < startTime)
        {
            log.error((new StringBuilder()).append("Measurement end time must not precede start time - startTime: ").append(startTime).append(" endTime: ").append(l).toString());
            return;
        } else
        {
            endTime = l;
            return;
        }
    }

    public void setExclusiveTime(long l)
    {
        throwIfFinished();
        exclusiveTime = l;
    }

    public void setName(String s)
    {
        throwIfFinished();
        name = s;
    }

    public void setScope(String s)
    {
        throwIfFinished();
        scope = s;
    }

    public void setStartTime(long l)
    {
        throwIfFinished();
        startTime = l;
    }

    public void setThreadInfo(ThreadInfo threadinfo)
    {
        threadInfo = threadinfo;
    }

    void setType(MeasurementType measurementtype)
    {
        throwIfFinished();
        type = measurementtype;
    }

    public String toString()
    {
        return (new StringBuilder()).append("BaseMeasurement{type=").append(type).append(", name='").append(name).append('\'').append(", scope='").append(scope).append('\'').append(", startTime=").append(startTime).append(", endTime=").append(endTime).append(", exclusiveTime=").append(exclusiveTime).append(", threadInfo=").append(threadInfo).append(", finished=").append(finished).append('}').toString();
    }

    private static final AgentLog log = AgentLogManager.getAgentLog();
    private long endTime;
    private long exclusiveTime;
    private boolean finished;
    private String name;
    private String scope;
    private long startTime;
    private ThreadInfo threadInfo;
    private MeasurementType type;

}
