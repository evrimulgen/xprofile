// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.newrelic.agent.android.measurement.producer;

import com.newrelic.agent.android.measurement.Measurement;
import com.newrelic.agent.android.measurement.MeasurementType;
import java.util.Collection;

public interface MeasurementProducer
{

    public abstract Collection drainMeasurements();

    public abstract MeasurementType getMeasurementType();

    public abstract void produceMeasurement(Measurement measurement);

    public abstract void produceMeasurements(Collection collection);
}
