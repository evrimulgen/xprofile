// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.newrelic.agent.android.harvest;

import com.newrelic.agent.android.metric.Metric;
import com.newrelic.agent.android.metric.MetricStore;
import com.newrelic.agent.android.tracing.ActivityTrace;
import com.newrelic.agent.android.tracing.Trace;
import java.util.*;

// Referenced classes of package com.newrelic.agent.android.harvest:
//            HarvestAdapter, Harvest, HarvestData, ActivityTraces, 
//            MachineMeasurements

public class HarvestDataValidator extends HarvestAdapter
{

    public HarvestDataValidator()
    {
    }

    public void ensureActivityNameMetricsExist()
    {
        HarvestData harvestdata = Harvest.getInstance().getHarvestData();
        ActivityTraces activitytraces = harvestdata.getActivityTraces();
        MachineMeasurements machinemeasurements;
        if(activitytraces != null && activitytraces.count() != 0)
            if((machinemeasurements = harvestdata.getMetrics()) != null && !machinemeasurements.isEmpty())
            {
                Iterator iterator = activitytraces.getActivityTraces().iterator();
                while(iterator.hasNext()) 
                {
                    String s1;
                    boolean flag;
label0:
                    {
                        String s = ((ActivityTrace)iterator.next()).rootTrace.displayName;
                        int i = s.indexOf("#");
                        if(i > 0)
                            s = s.substring(0, i);
                        s1 = (new StringBuilder()).append("Mobile/Activity/Name/").append(s).toString();
                        List list = machinemeasurements.getMetrics().getAllUnscoped();
                        flag = false;
                        if(list == null)
                            break label0;
                        int j = list.size();
                        flag = false;
                        if(j <= 0)
                            break label0;
                        Iterator iterator1 = list.iterator();
                        do
                        {
                            boolean flag1 = iterator1.hasNext();
                            flag = false;
                            if(!flag1)
                                break label0;
                        } while(!((Metric)iterator1.next()).getName().startsWith(s1));
                        flag = true;
                    }
                    if(!flag)
                        machinemeasurements.addMetric(new Metric(s1));
                }
            }
_L2:
        return;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public void onHarvestFinalize()
    {
        if(!Harvest.isInitialized())
        {
            return;
        } else
        {
            ensureActivityNameMetricsExist();
            return;
        }
    }
}
