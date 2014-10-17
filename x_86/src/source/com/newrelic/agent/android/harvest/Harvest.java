// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.newrelic.agent.android.harvest;

import com.newrelic.agent.android.AgentConfiguration;
import com.newrelic.agent.android.activity.config.ActivityTraceConfiguration;
import com.newrelic.agent.android.harvest.type.Harvestable;
import com.newrelic.agent.android.logging.AgentLog;
import com.newrelic.agent.android.logging.AgentLogManager;
import com.newrelic.agent.android.metric.Metric;
import com.newrelic.agent.android.stats.StatsEngine;
import com.newrelic.agent.android.tracing.ActivityTrace;
import com.newrelic.agent.android.tracing.Trace;
import java.util.*;
import java.util.concurrent.TimeUnit;

// Referenced classes of package com.newrelic.agent.android.harvest:
//            HarvestableCache, HarvestConfiguration, HarvestData, Harvester, 
//            ActivityTraces, AgentHealth, HttpErrors, HttpTransactions, 
//            MachineMeasurements, HarvestTimer, HarvestLifecycleAware, HarvestConnection, 
//            HarvestDataValidator, AgentHealthException, HttpError, HttpTransaction

public class Harvest
{

    public Harvest()
    {
        configuration = HarvestConfiguration.getDefaultHarvestConfiguration();
    }

    public static void addActivityTrace(ActivityTrace activitytrace)
    {
        if(isDisabled())
            return;
        if(!isInitialized())
        {
            activityTraceCache.add(activitytrace);
            return;
        }
        if(activitytrace.rootTrace == null)
        {
            log.error("Activity trace is lacking a root trace!");
            return;
        }
        if(activitytrace.rootTrace.childExclusiveTime == 0L)
        {
            log.error((new StringBuilder()).append("Total trace exclusive time is zero. Ignoring trace ").append(activitytrace.rootTrace.displayName).toString());
            return;
        }
        double d = (double)activitytrace.rootTrace.childExclusiveTime / 1000D;
        if(d < instance.getConfiguration().getActivity_trace_min_utilization())
        {
            StatsEngine.get().inc("Supportability/AgentHealth/IgnoredTraces");
            log.debug((new StringBuilder()).append("Total trace exclusive time is too low (").append(d).append("). Ignoring trace ").append(activitytrace.rootTrace.displayName).toString());
            return;
        }
        ActivityTraces activitytraces = instance.getHarvestData().getActivityTraces();
        ActivityTraceConfiguration activitytraceconfiguration = instance.getActivityTraceConfiguration();
        instance.getHarvester().expireActivityTraces();
        if(activitytraces.count() >= activitytraceconfiguration.getMaxTotalTraceCount())
        {
            log.debug((new StringBuilder()).append("Activity trace limit of ").append(activitytraceconfiguration.getMaxTotalTraceCount()).append(" exceeded. Ignoring trace: ").append(activitytrace.toJsonString()).toString());
            return;
        } else
        {
            log.debug((new StringBuilder()).append("Adding activity trace: ").append(activitytrace.toJsonString()).toString());
            activitytraces.add(activitytrace);
            return;
        }
    }

    public static void addAgentHealthException(AgentHealthException agenthealthexception)
    {
        if(isDisabled() || !isInitialized())
        {
            return;
        } else
        {
            instance.getHarvestData().getAgentHealth().addException(agenthealthexception);
            return;
        }
    }

    public static void addHarvestListener(HarvestLifecycleAware harvestlifecycleaware)
    {
        if(harvestlifecycleaware == null)
            log.error("Harvest: Argument to addHarvestListener cannot be null.");
        else
        if(!isInitialized())
        {
            if(!isUnregisteredListener(harvestlifecycleaware))
            {
                addUnregisteredListener(harvestlifecycleaware);
                return;
            }
        } else
        {
            instance.getHarvester().addHarvestListener(harvestlifecycleaware);
            return;
        }
    }

    public static void addHttpError(HttpError httperror)
    {
        if(!instance.shouldCollectNetworkErrors() || isDisabled())
            return;
        HttpErrors httperrors = instance.getHarvestData().getHttpErrors();
        instance.getHarvester().expireHttpErrors();
        int i = instance.getConfiguration().getError_limit();
        if(httperrors.count() >= i)
        {
            StatsEngine.get().inc("Supportability/AgentHealth/ErrorsDropped");
            log.debug((new StringBuilder()).append("Maximum number of HTTP errors (").append(i).append(") reached. HTTP Error dropped.").toString());
            return;
        } else
        {
            httperrors.addHttpError(httperror);
            return;
        }
    }

    public static void addHttpTransaction(HttpTransaction httptransaction)
    {
        if(isDisabled())
            return;
        HttpTransactions httptransactions = instance.getHarvestData().getHttpTransactions();
        instance.getHarvester().expireHttpTransactions();
        int i = instance.getConfiguration().getReport_max_transaction_count();
        if(httptransactions.count() >= i)
        {
            StatsEngine.get().inc("Supportability/AgentHealth/TransactionsDropped");
            log.debug((new StringBuilder()).append("Maximum number of transactions (").append(i).append(") reached. HTTP Transaction dropped.").toString());
            return;
        } else
        {
            httptransactions.add(httptransaction);
            return;
        }
    }

    public static void addMetric(Metric metric)
    {
        if(isDisabled() || !isInitialized())
        {
            return;
        } else
        {
            instance.getHarvestData().getMetrics().addMetric(metric);
            return;
        }
    }

    private static void addUnregisteredListener(HarvestLifecycleAware harvestlifecycleaware)
    {
        if(harvestlifecycleaware == null)
            return;
        synchronized(unregisteredLifecycleListeners)
        {
            unregisteredLifecycleListeners.add(harvestlifecycleaware);
        }
        return;
        exception;
        collection;
        JVM INSTR monitorexit ;
        throw exception;
    }

    private void flushActivityTraceCache()
    {
        for(Iterator iterator = activityTraceCache.flush().iterator(); iterator.hasNext(); addActivityTrace((ActivityTrace)(Harvestable)iterator.next()));
    }

    private void flushHarvestableCaches()
    {
        try
        {
            flushActivityTraceCache();
            return;
        }
        catch(Exception exception)
        {
            exception.printStackTrace();
        }
    }

    public static int getActivityTraceCacheSize()
    {
        return activityTraceCache.getSize();
    }

    public static HarvestConfiguration getHarvestConfiguration()
    {
        if(!isInitialized())
            return HarvestConfiguration.getDefaultHarvestConfiguration();
        else
            return instance.getConfiguration();
    }

    private HarvestTimer getHarvestTimer()
    {
        return harvestTimer;
    }

    public static Harvest getInstance()
    {
        return instance;
    }

    public static void harvestNow()
    {
        StatsEngine.get().sampleTimeMs("Session/Duration", instance.getHarvestTimer().timeSinceStart());
        instance.getHarvestTimer().tickNow();
    }

    public static void initialize(AgentConfiguration agentconfiguration)
    {
        instance.initializeHarvester(agentconfiguration);
        registerUnregisteredListeners();
        addHarvestListener(StatsEngine.get());
    }

    public static boolean isDisabled()
    {
        if(!isInitialized())
            return false;
        else
            return instance.getHarvester().isDisabled();
    }

    public static boolean isInitialized()
    {
        return instance.getHarvester() != null;
    }

    private static boolean isUnregisteredListener(HarvestLifecycleAware harvestlifecycleaware)
    {
        if(harvestlifecycleaware == null)
            return false;
        else
            return unregisteredLifecycleListeners.contains(harvestlifecycleaware);
    }

    private static void registerUnregisteredListeners()
    {
        for(Iterator iterator = unregisteredLifecycleListeners.iterator(); iterator.hasNext(); addHarvestListener((HarvestLifecycleAware)iterator.next()));
        unregisteredLifecycleListeners.clear();
    }

    public static void removeHarvestListener(HarvestLifecycleAware harvestlifecycleaware)
    {
        if(harvestlifecycleaware == null)
            log.error("Harvest: Argument to removeHarvestListener cannot be null.");
        else
        if(!isInitialized())
        {
            if(isUnregisteredListener(harvestlifecycleaware))
            {
                removeUnregisteredListener(harvestlifecycleaware);
                return;
            }
        } else
        {
            instance.getHarvester().removeHarvestListener(harvestlifecycleaware);
            return;
        }
    }

    private static void removeUnregisteredListener(HarvestLifecycleAware harvestlifecycleaware)
    {
        if(harvestlifecycleaware == null)
            return;
        synchronized(unregisteredLifecycleListeners)
        {
            unregisteredLifecycleListeners.remove(harvestlifecycleaware);
        }
        return;
        exception;
        collection;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public static void setHarvestConfiguration(HarvestConfiguration harvestconfiguration)
    {
        if(!isInitialized())
        {
            log.error("Cannot configure Harvester before initialization.");
            (new Exception()).printStackTrace();
            return;
        } else
        {
            log.debug((new StringBuilder()).append("Harvest Configuration: ").append(harvestconfiguration).toString());
            instance.setConfiguration(harvestconfiguration);
            return;
        }
    }

    public static void setInstance(Harvest harvest)
    {
        instance = harvest;
    }

    public static void setPeriod(long l)
    {
        instance.getHarvestTimer().setPeriod(l);
    }

    public static boolean shouldCollectActivityTraces()
    {
        boolean flag = true;
        if(isDisabled())
            flag = false;
        else
        if(isInitialized())
        {
            ActivityTraceConfiguration activitytraceconfiguration = instance.getActivityTraceConfiguration();
            if(activitytraceconfiguration != null && activitytraceconfiguration.getMaxTotalTraceCount() <= 0)
                return false;
        }
        return flag;
    }

    public static void shutdown()
    {
        if(!isInitialized())
        {
            return;
        } else
        {
            stop();
            instance.shutdownHarvester();
            return;
        }
    }

    public static void start()
    {
        instance.getHarvestTimer().start();
    }

    public static void stop()
    {
        instance.getHarvestTimer().stop();
    }

    public void createHarvester()
    {
        harvestConnection = new HarvestConnection();
        harvestData = new HarvestData();
        harvester = new Harvester();
        harvester.setHarvestConnection(harvestConnection);
        harvester.setHarvestData(harvestData);
        harvestTimer = new HarvestTimer(harvester);
        harvestDataValidator = new HarvestDataValidator();
        addHarvestListener(harvestDataValidator);
    }

    protected ActivityTraceConfiguration getActivityTraceConfiguration()
    {
        return configuration.getAt_capture();
    }

    public HarvestConfiguration getConfiguration()
    {
        return configuration;
    }

    public HarvestConnection getHarvestConnection()
    {
        return harvestConnection;
    }

    public HarvestData getHarvestData()
    {
        return harvestData;
    }

    protected Harvester getHarvester()
    {
        return harvester;
    }

    public void initializeHarvester(AgentConfiguration agentconfiguration)
    {
        createHarvester();
        harvester.setAgentConfiguration(agentconfiguration);
        harvester.setConfiguration(instance.getConfiguration());
        flushHarvestableCaches();
    }

    public void setConfiguration(HarvestConfiguration harvestconfiguration)
    {
        configuration.reconfigure(harvestconfiguration);
        harvestTimer.setPeriod(TimeUnit.MILLISECONDS.convert(configuration.getData_report_period(), TimeUnit.SECONDS));
        harvestConnection.setServerTimestamp(configuration.getServer_timestamp());
        harvestData.setDataToken(configuration.getDataToken());
        harvester.setConfiguration(configuration);
    }

    public void setHarvestConnection(HarvestConnection harvestconnection)
    {
        harvestConnection = harvestconnection;
    }

    public boolean shouldCollectNetworkErrors()
    {
        return configuration.isCollect_network_errors();
    }

    public void shutdownHarvester()
    {
        harvestTimer = null;
        harvester = null;
        harvestConnection = null;
        harvestData = null;
    }

    private static final boolean DISABLE_ACTIVITY_TRACE_LIMITS_FOR_DEBUGGING;
    private static final HarvestableCache activityTraceCache = new HarvestableCache();
    protected static Harvest instance = new Harvest();
    private static final AgentLog log = AgentLogManager.getAgentLog();
    private static final Collection unregisteredLifecycleListeners = new ArrayList();
    private HarvestConfiguration configuration;
    private HarvestConnection harvestConnection;
    protected HarvestData harvestData;
    private HarvestDataValidator harvestDataValidator;
    private HarvestTimer harvestTimer;
    private Harvester harvester;

}
