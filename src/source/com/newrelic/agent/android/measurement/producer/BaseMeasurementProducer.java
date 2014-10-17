// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.newrelic.agent.android.measurement.producer;

import com.newrelic.agent.android.measurement.Measurement;
import com.newrelic.agent.android.measurement.MeasurementType;
import java.util.*;

// Referenced classes of package com.newrelic.agent.android.measurement.producer:
//            MeasurementProducer

public class BaseMeasurementProducer
    implements MeasurementProducer
{

    public BaseMeasurementProducer(MeasurementType measurementtype)
    {
        producedMeasurementType = measurementtype;
    }

    public Collection drainMeasurements()
    {
        if(producedMeasurements.size() == 0)
            return Collections.emptyList();
        this;
        JVM INSTR monitorenter ;
        ArrayList arraylist;
        arraylist = new ArrayList(producedMeasurements);
        producedMeasurements.clear();
        this;
        JVM INSTR monitorexit ;
        return arraylist;
        Exception exception;
        exception;
        this;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public MeasurementType getMeasurementType()
    {
        return producedMeasurementType;
    }

    public void produceMeasurement(Measurement measurement)
    {
        synchronized(producedMeasurements)
        {
            producedMeasurements.add(measurement);
        }
        return;
        exception;
        arraylist;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public void produceMeasurements(Collection collection)
    {
        synchronized(producedMeasurements)
        {
            producedMeasurements.addAll(collection);
        }
        return;
        exception;
        arraylist;
        JVM INSTR monitorexit ;
        throw exception;
    }

    private final MeasurementType producedMeasurementType;
    private final ArrayList producedMeasurements = new ArrayList();
}
