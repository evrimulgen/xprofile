// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.newrelic.agent.android.harvest;

import com.newrelic.agent.android.AgentConfiguration;
import com.newrelic.agent.android.TaskQueue;
import com.newrelic.agent.android.activity.config.ActivityTraceConfiguration;
import com.newrelic.agent.android.activity.config.ActivityTraceConfigurationDeserializer;
import com.newrelic.agent.android.logging.AgentLog;
import com.newrelic.agent.android.logging.AgentLogManager;
import com.newrelic.agent.android.stats.StatsEngine;
import com.newrelic.agent.android.tracing.ActivityTrace;
import com.newrelic.com.google.gson.*;
import java.util.*;

// Referenced classes of package com.newrelic.agent.android.harvest:
//            HarvestConfiguration, HarvestData, Harvest, HarvestLifecycleAware, 
//            AgentHealth, HarvestResponse, HttpTransactions, HttpErrors, 
//            ActivityTraces, HarvestConnection, DataToken, HttpError, 
//            HttpTransaction, ConnectInformation

public class Harvester
{
    protected static final class State extends Enum
    {

        public static State valueOf(String s)
        {
            return (State)Enum.valueOf(com/newrelic/agent/android/harvest/Harvester$State, s);
        }

        public static State[] values()
        {
            return (State[])$VALUES.clone();
        }

        private static final State $VALUES[];
        public static final State CONNECTED;
        public static final State DISABLED;
        public static final State DISCONNECTED;
        public static final State UNINITIALIZED;

        static 
        {
            UNINITIALIZED = new State("UNINITIALIZED", 0);
            DISCONNECTED = new State("DISCONNECTED", 1);
            CONNECTED = new State("CONNECTED", 2);
            DISABLED = new State("DISABLED", 3);
            State astate[] = new State[4];
            astate[0] = UNINITIALIZED;
            astate[1] = DISCONNECTED;
            astate[2] = CONNECTED;
            astate[3] = DISABLED;
            $VALUES = astate;
        }

        private State(String s, int i)
        {
            super(s, i);
        }
    }


    public Harvester()
    {
        state = State.UNINITIALIZED;
        configuration = HarvestConfiguration.getDefaultHarvestConfiguration();
    }

    private void changeState(State state1)
    {
        log.debug((new StringBuilder()).append("Harvester changing state: ").append(state).append(" -> ").append(state1).toString());
        if(state != State.CONNECTED) goto _L2; else goto _L1
_L1:
        if(state1 != State.DISCONNECTED) goto _L4; else goto _L3
_L3:
        fireOnHarvestDisconnected();
_L2:
        state = state1;
        stateChanged = true;
        return;
_L4:
        if(state1 == State.DISABLED)
            fireOnHarvestDisabled();
        if(true) goto _L2; else goto _L5
_L5:
    }

    private void configureHarvester(HarvestConfiguration harvestconfiguration)
    {
        configuration.reconfigure(harvestconfiguration);
        harvestData.setDataToken(configuration.getDataToken());
        Harvest.setHarvestConfiguration(configuration);
    }

    private void fireOnHarvest()
    {
        try
        {
            for(Iterator iterator = getHarvestListeners().iterator(); iterator.hasNext(); ((HarvestLifecycleAware)iterator.next()).onHarvest());
        }
        catch(Exception exception)
        {
            log.error("Error in fireOnHarvest", exception);
            AgentHealth.noticeException(exception);
        }
    }

    private void fireOnHarvestBefore()
    {
        try
        {
            for(Iterator iterator = getHarvestListeners().iterator(); iterator.hasNext(); ((HarvestLifecycleAware)iterator.next()).onHarvestBefore());
        }
        catch(Exception exception)
        {
            log.error("Error in fireOnHarvestBefore", exception);
            AgentHealth.noticeException(exception);
        }
    }

    private void fireOnHarvestComplete()
    {
        try
        {
            for(Iterator iterator = getHarvestListeners().iterator(); iterator.hasNext(); ((HarvestLifecycleAware)iterator.next()).onHarvestComplete());
        }
        catch(Exception exception)
        {
            log.error("Error in fireOnHarvestComplete", exception);
            AgentHealth.noticeException(exception);
        }
    }

    private void fireOnHarvestConnected()
    {
        try
        {
            for(Iterator iterator = getHarvestListeners().iterator(); iterator.hasNext(); ((HarvestLifecycleAware)iterator.next()).onHarvestConnected());
        }
        catch(Exception exception)
        {
            log.error("Error in fireOnHarvestConnected", exception);
            AgentHealth.noticeException(exception);
        }
    }

    private void fireOnHarvestDisabled()
    {
        try
        {
            for(Iterator iterator = getHarvestListeners().iterator(); iterator.hasNext(); ((HarvestLifecycleAware)iterator.next()).onHarvestDisabled());
        }
        catch(Exception exception)
        {
            log.error("Error in fireOnHarvestDisabled", exception);
            AgentHealth.noticeException(exception);
        }
    }

    private void fireOnHarvestDisconnected()
    {
        try
        {
            for(Iterator iterator = getHarvestListeners().iterator(); iterator.hasNext(); ((HarvestLifecycleAware)iterator.next()).onHarvestDisconnected());
        }
        catch(Exception exception)
        {
            log.error("Error in fireOnHarvestDisconnected", exception);
            AgentHealth.noticeException(exception);
        }
    }

    private void fireOnHarvestError()
    {
        try
        {
            for(Iterator iterator = getHarvestListeners().iterator(); iterator.hasNext(); ((HarvestLifecycleAware)iterator.next()).onHarvestError());
        }
        catch(Exception exception)
        {
            log.error("Error in fireOnHarvestError", exception);
            AgentHealth.noticeException(exception);
        }
    }

    private void fireOnHarvestFinalize()
    {
        try
        {
            for(Iterator iterator = getHarvestListeners().iterator(); iterator.hasNext(); ((HarvestLifecycleAware)iterator.next()).onHarvestFinalize());
        }
        catch(Exception exception)
        {
            log.error("Error in fireOnHarvestFinalize", exception);
            AgentHealth.noticeException(exception);
        }
    }

    private void fireOnHarvestSendFailed()
    {
        try
        {
            for(Iterator iterator = getHarvestListeners().iterator(); iterator.hasNext(); ((HarvestLifecycleAware)iterator.next()).onHarvestSendFailed());
        }
        catch(Exception exception)
        {
            log.error("Error in fireOnHarvestSendFailed", exception);
            AgentHealth.noticeException(exception);
        }
    }

    private void fireOnHarvestStart()
    {
        try
        {
            for(Iterator iterator = getHarvestListeners().iterator(); iterator.hasNext(); ((HarvestLifecycleAware)iterator.next()).onHarvestStart());
        }
        catch(Exception exception)
        {
            log.error("Error in fireOnHarvestStart", exception);
            AgentHealth.noticeException(exception);
        }
    }

    private void fireOnHarvestStop()
    {
        try
        {
            for(Iterator iterator = getHarvestListeners().iterator(); iterator.hasNext(); ((HarvestLifecycleAware)iterator.next()).onHarvestStop());
        }
        catch(Exception exception)
        {
            log.error("Error in fireOnHarvestStop", exception);
            AgentHealth.noticeException(exception);
        }
    }

    private Collection getHarvestListeners()
    {
        return new ArrayList(harvestListeners);
    }

    private HarvestConfiguration parseHarvesterConfiguration(HarvestResponse harvestresponse)
    {
        GsonBuilder gsonbuilder = new GsonBuilder();
        gsonbuilder.registerTypeAdapter(com/newrelic/agent/android/activity/config/ActivityTraceConfiguration, new ActivityTraceConfigurationDeserializer());
        Gson gson = gsonbuilder.create();
        HarvestConfiguration harvestconfiguration;
        try
        {
            harvestconfiguration = (HarvestConfiguration)gson.fromJson(harvestresponse.getResponseBody(), com/newrelic/agent/android/harvest/HarvestConfiguration);
        }
        catch(JsonSyntaxException jsonsyntaxexception)
        {
            log.error((new StringBuilder()).append("Unable to parse collector configuration: ").append(jsonsyntaxexception.getMessage()).toString());
            AgentHealth.noticeException(jsonsyntaxexception);
            return null;
        }
        return harvestconfiguration;
    }

    private transient boolean stateIn(State state1, State astate[])
    {
        int i = astate.length;
        for(int j = 0; j < i; j++)
            if(state1 == astate[j])
                return true;

        return false;
    }

    public void addHarvestListener(HarvestLifecycleAware harvestlifecycleaware)
    {
        if(harvestlifecycleaware == null)
        {
            log.error("Can't add null harvest listener");
            (new Exception()).printStackTrace();
            return;
        }
        synchronized(harvestListeners)
        {
            if(!harvestListeners.contains(harvestlifecycleaware))
                break MISSING_BLOCK_LABEL_55;
        }
        return;
        exception;
        collection;
        JVM INSTR monitorexit ;
        throw exception;
        harvestListeners.add(harvestlifecycleaware);
        collection;
        JVM INSTR monitorexit ;
    }

    protected void connected()
    {
        log.info("Harvester: connected");
        log.info((new StringBuilder()).append("Harvester: Sending ").append(harvestData.getHttpTransactions().count()).append(" HTTP transactions.").toString());
        log.info((new StringBuilder()).append("Harvester: Sending ").append(harvestData.getHttpErrors().count()).append(" HTTP errors.").toString());
        log.info((new StringBuilder()).append("Harvester: Sending ").append(harvestData.getActivityTraces().count()).append(" activity traces.").toString());
        HarvestResponse harvestresponse = harvestConnection.sendData(harvestData);
        if(harvestresponse == null || harvestresponse.isUnknown())
        {
            fireOnHarvestSendFailed();
            return;
        }
        harvestData.reset();
        StatsEngine.get().sampleTimeMs("Supportability/AgentHealth/Collector/Harvest", harvestresponse.getResponseTime());
        log.debug((new StringBuilder()).append("Harvest data response: ").append(harvestresponse.getResponseCode()).toString());
        log.debug((new StringBuilder()).append("Harvest data response status code: ").append(harvestresponse.getStatusCode()).toString());
        log.debug((new StringBuilder()).append("Harvest data response body: ").append(harvestresponse.getResponseBody()).toString());
        if(harvestresponse.isError())
        {
            fireOnHarvestError();
            static class _cls1
            {

                static final int $SwitchMap$com$newrelic$agent$android$harvest$HarvestResponse$Code[];
                static final int $SwitchMap$com$newrelic$agent$android$harvest$Harvester$State[];

                static 
                {
                    $SwitchMap$com$newrelic$agent$android$harvest$Harvester$State = new int[State.values().length];
                    try
                    {
                        $SwitchMap$com$newrelic$agent$android$harvest$Harvester$State[State.UNINITIALIZED.ordinal()] = 1;
                    }
                    catch(NoSuchFieldError nosuchfielderror) { }
                    try
                    {
                        $SwitchMap$com$newrelic$agent$android$harvest$Harvester$State[State.DISCONNECTED.ordinal()] = 2;
                    }
                    catch(NoSuchFieldError nosuchfielderror1) { }
                    try
                    {
                        $SwitchMap$com$newrelic$agent$android$harvest$Harvester$State[State.CONNECTED.ordinal()] = 3;
                    }
                    catch(NoSuchFieldError nosuchfielderror2) { }
                    try
                    {
                        $SwitchMap$com$newrelic$agent$android$harvest$Harvester$State[State.DISABLED.ordinal()] = 4;
                    }
                    catch(NoSuchFieldError nosuchfielderror3) { }
                    $SwitchMap$com$newrelic$agent$android$harvest$HarvestResponse$Code = new int[HarvestResponse.Code.values().length];
                    try
                    {
                        $SwitchMap$com$newrelic$agent$android$harvest$HarvestResponse$Code[HarvestResponse.Code.UNAUTHORIZED.ordinal()] = 1;
                    }
                    catch(NoSuchFieldError nosuchfielderror4) { }
                    try
                    {
                        $SwitchMap$com$newrelic$agent$android$harvest$HarvestResponse$Code[HarvestResponse.Code.INVALID_AGENT_ID.ordinal()] = 2;
                    }
                    catch(NoSuchFieldError nosuchfielderror5) { }
                    try
                    {
                        $SwitchMap$com$newrelic$agent$android$harvest$HarvestResponse$Code[HarvestResponse.Code.FORBIDDEN.ordinal()] = 3;
                    }
                    catch(NoSuchFieldError nosuchfielderror6) { }
                    try
                    {
                        $SwitchMap$com$newrelic$agent$android$harvest$HarvestResponse$Code[HarvestResponse.Code.UNSUPPORTED_MEDIA_TYPE.ordinal()] = 4;
                    }
                    catch(NoSuchFieldError nosuchfielderror7) { }
                    try
                    {
                        $SwitchMap$com$newrelic$agent$android$harvest$HarvestResponse$Code[HarvestResponse.Code.ENTITY_TOO_LARGE.ordinal()] = 5;
                    }
                    catch(NoSuchFieldError nosuchfielderror8)
                    {
                        return;
                    }
                }
            }

            switch(_cls1..SwitchMap.com.newrelic.agent.android.harvest.HarvestResponse.Code[harvestresponse.getResponseCode().ordinal()])
            {
            default:
                log.error("An unknown error occurred when connecting to the Collector.");
                return;

            case 1: // '\001'
            case 2: // '\002'
                harvestData.getDataToken().clear();
                transition(State.DISCONNECTED);
                return;

            case 3: // '\003'
                if(harvestresponse.isDisableCommand())
                {
                    log.error("Collector has commanded Agent to disable.");
                    transition(State.DISABLED);
                    return;
                } else
                {
                    log.error("Unexpected Collector response: FORBIDDEN");
                    transition(State.DISCONNECTED);
                    return;
                }

            case 4: // '\004'
            case 5: // '\005'
                log.error("Invalid ConnectionInformation was sent to the Collector.");
                return;
            }
        }
        HarvestConfiguration harvestconfiguration = parseHarvesterConfiguration(harvestresponse);
        if(harvestconfiguration == null)
        {
            log.error("Unable to configure Harvester using Collector configuration.");
            return;
        } else
        {
            configureHarvester(harvestconfiguration);
            fireOnHarvestComplete();
            return;
        }
    }

    protected void disabled()
    {
        Harvest.stop();
        fireOnHarvestDisabled();
    }

    protected void disconnected()
    {
        HarvestResponse harvestresponse;
        if(harvestData.getDataToken().isValid())
        {
            log.verbose((new StringBuilder()).append("Skipping connect call, saved state is available: ").append(harvestData.getDataToken()).toString());
            fireOnHarvestConnected();
            transition(State.CONNECTED);
            return;
        }
        log.info((new StringBuilder()).append("Connecting, saved state is not available: ").append(harvestData.getDataToken()).toString());
        harvestresponse = harvestConnection.sendConnect();
        if(harvestresponse == null)
        {
            log.error("Unable to connect to the Collector.");
            return;
        }
        if(harvestresponse.isOK())
        {
            HarvestConfiguration harvestconfiguration = parseHarvesterConfiguration(harvestresponse);
            if(harvestconfiguration == null)
            {
                log.error("Unable to configure Harvester using Collector configuration.");
                return;
            } else
            {
                configureHarvester(harvestconfiguration);
                StatsEngine.get().sampleTimeMs("Supportability/AgentHealth/Collector/Harvest", harvestresponse.getResponseTime());
                fireOnHarvestConnected();
                transition(State.CONNECTED);
                execute();
                return;
            }
        }
        log.debug((new StringBuilder()).append("Harvest connect response: ").append(harvestresponse.getResponseCode()).toString());
        _cls1..SwitchMap.com.newrelic.agent.android.harvest.HarvestResponse.Code[harvestresponse.getResponseCode().ordinal()];
        JVM INSTR tableswitch 1 5: default 260
    //                   1 277
    //                   2 277
    //                   3 292
    //                   4 338
    //                   5 338;
           goto _L1 _L2 _L2 _L3 _L4 _L4
_L1:
        log.error("An unknown error occurred when connecting to the Collector.");
_L6:
        fireOnHarvestError();
        return;
_L2:
        harvestData.getDataToken().clear();
        fireOnHarvestDisconnected();
        return;
_L3:
        if(harvestresponse.isDisableCommand())
        {
            log.error("Collector has commanded Agent to disable.");
            fireOnHarvestDisabled();
            transition(State.DISABLED);
            return;
        }
        log.error("Unexpected Collector response: FORBIDDEN");
        continue; /* Loop/switch isn't completed */
_L4:
        log.error("Invalid ConnectionInformation was sent to the Collector.");
        if(true) goto _L6; else goto _L5
_L5:
    }

    protected void execute()
    {
        log.debug((new StringBuilder()).append("Harvester state: ").append(state).toString());
        stateChanged = false;
        expireHarvestData();
        _cls1..SwitchMap.com.newrelic.agent.android.harvest.Harvester.State[state.ordinal()];
        JVM INSTR tableswitch 1 4: default 84
    //                   1 111
    //                   2 116
    //                   3 125
    //                   4 145;
           goto _L1 _L2 _L3 _L4 _L5
_L1:
        Exception exception;
        throw new IllegalStateException();
_L2:
        try
        {
            uninitialized();
            return;
        }
        // Misplaced declaration of an exception variable
        catch(Exception exception)
        {
            log.error("Exception encountered while attempting to harvest", exception);
            AgentHealth.noticeException(exception);
            return;
        }
_L3:
        fireOnHarvestBefore();
        disconnected();
        return;
_L4:
        fireOnHarvestBefore();
        fireOnHarvest();
        fireOnHarvestFinalize();
        TaskQueue.synchronousDequeue();
        connected();
        return;
_L5:
        disabled();
        return;
    }

    public void expireActivityTraces()
    {
        ActivityTraces activitytraces = harvestData.getActivityTraces();
        activitytraces;
        JVM INSTR monitorenter ;
        ArrayList arraylist;
        arraylist = new ArrayList();
        long l = configuration.getActivity_trace_max_report_attempts();
        Iterator iterator = activitytraces.getActivityTraces().iterator();
        do
        {
            if(!iterator.hasNext())
                break;
            ActivityTrace activitytrace = (ActivityTrace)iterator.next();
            if(activitytrace.getReportAttemptCount() >= l)
            {
                log.debug((new StringBuilder()).append("ActivityTrace has had ").append(activitytrace.getReportAttemptCount()).append(" report attempts, purging: ").append(activitytrace).toString());
                arraylist.add(activitytrace);
            }
        } while(true);
        break MISSING_BLOCK_LABEL_133;
        Exception exception;
        exception;
        activitytraces;
        JVM INSTR monitorexit ;
        throw exception;
        for(Iterator iterator1 = arraylist.iterator(); iterator1.hasNext(); activitytraces.remove((ActivityTrace)iterator1.next()));
        activitytraces;
        JVM INSTR monitorexit ;
    }

    public void expireHarvestData()
    {
        expireHttpErrors();
        expireHttpTransactions();
        expireActivityTraces();
    }

    public void expireHttpErrors()
    {
        HttpErrors httperrors = harvestData.getHttpErrors();
        httperrors;
        JVM INSTR monitorenter ;
        ArrayList arraylist;
        arraylist = new ArrayList();
        long l = System.currentTimeMillis();
        long l1 = configuration.getReportMaxTransactionAgeMilliseconds();
        Iterator iterator = httperrors.getHttpErrors().iterator();
        do
        {
            if(!iterator.hasNext())
                break;
            HttpError httperror = (HttpError)iterator.next();
            if(httperror.getTimestamp().longValue() < l - l1)
            {
                log.debug((new StringBuilder()).append("HttpError too old, purging: ").append(httperror).toString());
                arraylist.add(httperror);
            }
        } while(true);
        break MISSING_BLOCK_LABEL_129;
        Exception exception;
        exception;
        httperrors;
        JVM INSTR monitorexit ;
        throw exception;
        for(Iterator iterator1 = arraylist.iterator(); iterator1.hasNext(); httperrors.removeHttpError((HttpError)iterator1.next()));
        httperrors;
        JVM INSTR monitorexit ;
    }

    public void expireHttpTransactions()
    {
        HttpTransactions httptransactions = harvestData.getHttpTransactions();
        httptransactions;
        JVM INSTR monitorenter ;
        ArrayList arraylist;
        arraylist = new ArrayList();
        long l = System.currentTimeMillis();
        long l1 = configuration.getReportMaxTransactionAgeMilliseconds();
        Iterator iterator = httptransactions.getHttpTransactions().iterator();
        do
        {
            if(!iterator.hasNext())
                break;
            HttpTransaction httptransaction = (HttpTransaction)iterator.next();
            if(httptransaction.getTimestamp().longValue() < l - l1)
            {
                log.debug((new StringBuilder()).append("HttpTransaction too old, purging: ").append(httptransaction).toString());
                arraylist.add(httptransaction);
            }
        } while(true);
        break MISSING_BLOCK_LABEL_129;
        Exception exception;
        exception;
        httptransactions;
        JVM INSTR monitorexit ;
        throw exception;
        for(Iterator iterator1 = arraylist.iterator(); iterator1.hasNext(); httptransactions.remove((HttpTransaction)iterator1.next()));
        httptransactions;
        JVM INSTR monitorexit ;
    }

    public State getCurrentState()
    {
        return state;
    }

    public HarvestConnection getHarvestConnection()
    {
        return harvestConnection;
    }

    public HarvestData getHarvestData()
    {
        return harvestData;
    }

    public boolean isDisabled()
    {
        return State.DISABLED == state;
    }

    public void removeHarvestListener(HarvestLifecycleAware harvestlifecycleaware)
    {
label0:
        {
            synchronized(harvestListeners)
            {
                if(harvestListeners.contains(harvestlifecycleaware))
                    break label0;
            }
            return;
        }
        harvestListeners.remove(harvestlifecycleaware);
        collection;
        JVM INSTR monitorexit ;
        return;
        exception;
        collection;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public void setAgentConfiguration(AgentConfiguration agentconfiguration)
    {
        agentConfiguration = agentconfiguration;
    }

    public void setConfiguration(HarvestConfiguration harvestconfiguration)
    {
        configuration = harvestconfiguration;
    }

    public void setHarvestConnection(HarvestConnection harvestconnection)
    {
        harvestConnection = harvestconnection;
    }

    public void setHarvestData(HarvestData harvestdata)
    {
        harvestData = harvestdata;
    }

    public void start()
    {
        fireOnHarvestStart();
    }

    public void stop()
    {
        fireOnHarvestStop();
    }

    protected void transition(State state1)
    {
        if(!stateChanged) goto _L2; else goto _L1
_L1:
        log.debug((new StringBuilder()).append("Ignoring multiple transition: ").append(state1).toString());
_L4:
        return;
_L2:
        if(state == state1) goto _L4; else goto _L3
_L3:
        _cls1..SwitchMap.com.newrelic.agent.android.harvest.Harvester.State[state.ordinal()];
        JVM INSTR tableswitch 1 3: default 84
    //                   1 92
    //                   2 148
    //                   3 188;
           goto _L5 _L6 _L7 _L8
_L5:
        throw new IllegalStateException();
_L6:
        State astate2[];
        astate2 = new State[4];
        astate2[0] = State.DISCONNECTED;
        astate2[1] = state1;
        astate2[2] = State.CONNECTED;
        astate2[3] = State.DISABLED;
        if(!stateIn(state1, astate2)) goto _L10; else goto _L9
_L9:
        changeState(state1);
        return;
_L10:
        throw new IllegalStateException();
_L7:
        State astate1[] = new State[3];
        astate1[0] = State.UNINITIALIZED;
        astate1[1] = State.CONNECTED;
        astate1[2] = State.DISABLED;
        if(!stateIn(state1, astate1))
            throw new IllegalStateException();
        continue; /* Loop/switch isn't completed */
_L8:
        State astate[] = new State[2];
        astate[0] = State.DISCONNECTED;
        astate[1] = State.DISABLED;
        if(!stateIn(state1, astate))
            throw new IllegalStateException();
        if(true) goto _L9; else goto _L11
_L11:
    }

    protected void uninitialized()
    {
        if(agentConfiguration == null)
        {
            log.error("Agent configuration unavailable.");
            return;
        } else
        {
            harvestConnection.setConnectInformation(new ConnectInformation());
            harvestConnection.setApplicationToken(agentConfiguration.getApplicationToken());
            harvestConnection.setCollectorHost(agentConfiguration.getCollectorHost());
            harvestConnection.useSsl(agentConfiguration.useSsl());
            transition(State.DISCONNECTED);
            execute();
            return;
        }
    }

    private AgentConfiguration agentConfiguration;
    private HarvestConfiguration configuration;
    private HarvestConnection harvestConnection;
    private HarvestData harvestData;
    private final Collection harvestListeners = new ArrayList();
    private final AgentLog log = AgentLogManager.getAgentLog();
    private State state;
    protected boolean stateChanged;
}
