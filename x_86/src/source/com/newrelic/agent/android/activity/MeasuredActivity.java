// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.newrelic.agent.android.activity;

import com.newrelic.agent.android.measurement.*;

public interface MeasuredActivity
{

    public abstract void finish();

    public abstract String getBackgroundMetricName();

    public abstract long getEndTime();

    public abstract Measurement getEndingMeasurement();

    public abstract ThreadInfo getEndingThread();

    public abstract MeasurementPool getMeasurementPool();

    public abstract String getMetricName();

    public abstract String getName();

    public abstract long getStartTime();

    public abstract Measurement getStartingMeasurement();

    public abstract ThreadInfo getStartingThread();

    public abstract boolean isAutoInstrumented();

    public abstract boolean isFinished();

    public abstract void setName(String s);
}
