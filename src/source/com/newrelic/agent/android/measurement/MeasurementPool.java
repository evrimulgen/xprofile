// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.newrelic.agent.android.measurement;

import com.newrelic.agent.android.logging.AgentLog;
import com.newrelic.agent.android.logging.AgentLogManager;
import com.newrelic.agent.android.measurement.consumer.MeasurementConsumer;
import com.newrelic.agent.android.measurement.producer.BaseMeasurementProducer;
import com.newrelic.agent.android.measurement.producer.MeasurementProducer;
import java.util.*;

// Referenced classes of package com.newrelic.agent.android.measurement:
//            MeasurementType, Measurement

public class MeasurementPool extends BaseMeasurementProducer
    implements MeasurementConsumer
{

    public MeasurementPool()
    {
        super(MeasurementType.Any);
        addMeasurementProducer(this);
    }

    public void addMeasurementConsumer(MeasurementConsumer measurementconsumer)
    {
        if(consumers.contains(measurementconsumer))
        {
            log.debug((new StringBuilder()).append("Attempted to add the same MeasurementConsumer ").append(measurementconsumer).append(" multiple times.").toString());
            return;
        } else
        {
            consumers.add(measurementconsumer);
            return;
        }
    }

    public void addMeasurementProducer(MeasurementProducer measurementproducer)
    {
        if(producers.contains(measurementproducer))
        {
            log.debug((new StringBuilder()).append("Attempted to add the same MeasurementProducer ").append(measurementproducer).append("  multiple times.").toString());
            return;
        } else
        {
            producers.add(measurementproducer);
            return;
        }
    }

    public void broadcastMeasurements()
    {
        ArrayList arraylist;
        arraylist = new ArrayList();
        Iterator iterator = producers.iterator();
        do
        {
            if(!iterator.hasNext())
                break;
            Collection collection1 = ((MeasurementProducer)iterator.next()).drainMeasurements();
            if(collection1.size() > 0)
                arraylist.addAll(collection1);
        } while(true);
        Collection collection = consumers;
        collection;
        JVM INSTR monitorenter ;
        for(Iterator iterator1 = consumers.iterator(); iterator1.hasNext();)
        {
            MeasurementConsumer measurementconsumer = (MeasurementConsumer)iterator1.next();
            Iterator iterator2 = (new ArrayList(arraylist)).iterator();
            while(iterator2.hasNext()) 
            {
                Measurement measurement = (Measurement)iterator2.next();
                if(measurementconsumer.getMeasurementType() == measurement.getType() || measurementconsumer.getMeasurementType() == MeasurementType.Any)
                    measurementconsumer.consumeMeasurement(measurement);
            }
        }

        break MISSING_BLOCK_LABEL_191;
        Exception exception;
        exception;
        collection;
        JVM INSTR monitorexit ;
        throw exception;
        collection;
        JVM INSTR monitorexit ;
    }

    public void consumeMeasurement(Measurement measurement)
    {
        produceMeasurement(measurement);
    }

    public void consumeMeasurements(Collection collection)
    {
        produceMeasurements(collection);
    }

    public Collection getMeasurementConsumers()
    {
        return consumers;
    }

    public Collection getMeasurementProducers()
    {
        return producers;
    }

    public MeasurementType getMeasurementType()
    {
        return MeasurementType.Any;
    }

    public void removeMeasurementConsumer(MeasurementConsumer measurementconsumer)
    {
        if(!consumers.contains(measurementconsumer))
        {
            log.debug((new StringBuilder()).append("Attempted to remove MeasurementConsumer ").append(measurementconsumer).append(" which is not registered.").toString());
            return;
        } else
        {
            consumers.remove(measurementconsumer);
            return;
        }
    }

    public void removeMeasurementProducer(MeasurementProducer measurementproducer)
    {
        if(!producers.contains(measurementproducer))
        {
            log.debug((new StringBuilder()).append("Attempted to remove MeasurementProducer ").append(measurementproducer).append(" which is not registered.").toString());
            return;
        } else
        {
            producers.remove(measurementproducer);
            return;
        }
    }

    private static final AgentLog log = AgentLogManager.getAgentLog();
    private final Collection consumers = new ArrayList();
    private final Collection producers = new ArrayList();

}
