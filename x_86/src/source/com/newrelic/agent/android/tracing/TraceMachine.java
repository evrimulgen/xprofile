// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.newrelic.agent.android.tracing;

import com.newrelic.agent.android.Measurements;
import com.newrelic.agent.android.TaskQueue;
import com.newrelic.agent.android.api.v2.TraceFieldInterface;
import com.newrelic.agent.android.api.v2.TraceMachineInterface;
import com.newrelic.agent.android.harvest.*;
import com.newrelic.agent.android.logging.AgentLog;
import com.newrelic.agent.android.logging.AgentLogManager;
import com.newrelic.agent.android.stats.StatsEngine;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

// Referenced classes of package com.newrelic.agent.android.tracing:
//            ActivityTrace, TraceLifecycleAware, TracingInactiveException, Trace, 
//            TraceType

public class TraceMachine extends HarvestAdapter
{
    private static class TraceStack extends Stack
    {

        private TraceStack()
        {
        }

    }


    protected TraceMachine(Trace trace)
    {
        activityTrace = new ActivityTrace(trace);
        Harvest.addHarvestListener(this);
    }

    public static void addTraceListener(TraceLifecycleAware tracelifecycleaware)
    {
        traceListeners.add(tracelifecycleaware);
    }

    private void completeActivityTrace()
    {
        TraceMachine tracemachine = traceMachine;
        traceMachine = null;
        for(Iterator iterator = traceListeners.iterator(); iterator.hasNext(); ((TraceLifecycleAware)iterator.next()).onTraceComplete(tracemachine.activityTrace));
        tracemachine.activityTrace.complete();
        Harvest.removeHarvestListener(tracemachine);
    }

    public static void enterMethod(Trace trace, String s, ArrayList arraylist)
    {
        long l;
        long l1;
        long l2;
        Trace trace1;
        try
        {
            if(isTracingInactive())
                return;
        }
        catch(TracingInactiveException tracinginactiveexception)
        {
            return;
        }
        catch(Exception exception)
        {
            log.error("Caught error while calling enterMethod()", exception);
            AgentHealth.noticeException(exception);
            return;
        }
        l = System.currentTimeMillis();
        l1 = traceMachine.activityTrace.lastUpdatedAt;
        l2 = traceMachine.activityTrace.startedAt;
        if(500L + l1 >= l)
            break MISSING_BLOCK_LABEL_75;
        if(!traceMachine.activityTrace.hasMissingChildren())
        {
            log.debug("Completing activity trace after hitting healthy timeout (500ms)");
            traceMachine.completeActivityTrace();
            return;
        }
        if(60000L + l2 >= l)
            break MISSING_BLOCK_LABEL_121;
        log.debug("Completing activity trace after hitting unhealthy timeout (60000ms)");
        traceMachine.completeActivityTrace();
        return;
        loadTraceContext(trace);
        trace1 = registerNewTrace(s);
        pushTraceContext(trace1);
        trace1.scope = getCurrentScope();
        trace1.setAnnotationParams(arraylist);
        for(Iterator iterator = traceListeners.iterator(); iterator.hasNext(); ((TraceLifecycleAware)iterator.next()).onEnterMethod());
        trace1.entryTimestamp = System.currentTimeMillis();
        return;
    }

    public static void enterMethod(String s)
    {
        enterMethod(null, s, null);
    }

    public static void enterMethod(String s, ArrayList arraylist)
    {
        enterMethod(null, s, arraylist);
    }

    public static void enterNetworkSegment(String s)
    {
        try
        {
            if(isTracingInactive())
                return;
        }
        catch(TracingInactiveException tracinginactiveexception)
        {
            return;
        }
        catch(Exception exception)
        {
            log.error("Caught error while calling enterNetworkSegment()", exception);
            AgentHealth.noticeException(exception);
            return;
        }
        if(getCurrentTrace().getType() == TraceType.NETWORK)
            exitMethod();
        enterMethod(null, s, null);
        getCurrentTrace().setType(TraceType.NETWORK);
        return;
    }

    public static void exitMethod()
    {
        Trace trace;
        try
        {
            if(isTracingInactive())
                return;
        }
        catch(Exception exception)
        {
            log.error("Caught error while calling exitMethod()", exception);
            AgentHealth.noticeException(exception);
            return;
        }
        trace = (Trace)threadLocalTrace.get();
        if(trace != null)
            break MISSING_BLOCK_LABEL_49;
        log.debug("threadLocalTrace is null");
        return;
        trace.exitTimestamp = System.currentTimeMillis();
        if(trace.threadId == 0L && traceMachineInterface != null)
        {
            trace.threadId = traceMachineInterface.getCurrentThreadId();
            trace.threadName = traceMachineInterface.getCurrentThreadName();
        }
        for(Iterator iterator = traceListeners.iterator(); iterator.hasNext(); ((TraceLifecycleAware)iterator.next()).onExitMethod());
        trace.complete();
        ((TraceStack)threadLocalTraceStack.get()).pop();
        if(!((TraceStack)threadLocalTraceStack.get()).empty())
            break MISSING_BLOCK_LABEL_212;
        threadLocalTrace.set(null);
_L1:
        if(trace.getType() == TraceType.TRACE)
        {
            TaskQueue.queue(trace);
            return;
        }
        break MISSING_BLOCK_LABEL_255;
        TracingInactiveException tracinginactiveexception;
        tracinginactiveexception;
        threadLocalTrace.remove();
        threadLocalTraceStack.remove();
        if(trace.getType() == TraceType.TRACE)
        {
            TaskQueue.queue(trace);
            return;
        }
        break MISSING_BLOCK_LABEL_255;
        Trace trace1 = (Trace)((TraceStack)threadLocalTraceStack.get()).peek();
        threadLocalTrace.set(trace1);
        trace1.childExclusiveTime = trace1.childExclusiveTime + trace.getDuration();
          goto _L1
    }

    public static String formatActivityBackgroundMetricName(String s)
    {
        return (new StringBuilder()).append("Mobile/Activity/Background/Name/").append(s).toString();
    }

    public static String formatActivityMetricName(String s)
    {
        return (new StringBuilder()).append("Mobile/Activity/Name/").append(s).toString();
    }

    public static ActivityTrace getActivityTrace()
        throws TracingInactiveException
    {
        ActivityTrace activitytrace;
        try
        {
            activitytrace = traceMachine.activityTrace;
        }
        catch(NullPointerException nullpointerexception)
        {
            throw new TracingInactiveException();
        }
        return activitytrace;
    }

    public static String getCurrentScope()
    {
        if(isTracingInactive())
            return null;
        String s;
        try
        {
            if(traceMachineInterface == null || traceMachineInterface.isUIThread())
                return traceMachine.activityTrace.rootTrace.metricName;
            s = traceMachine.activityTrace.rootTrace.metricBackgroundName;
        }
        catch(Exception exception)
        {
            log.error("Caught error while calling getCurrentScope()", exception);
            AgentHealth.noticeException(exception);
            return null;
        }
        return s;
    }

    public static Trace getCurrentTrace()
        throws TracingInactiveException
    {
        if(isTracingInactive())
            throw new TracingInactiveException();
        Trace trace = (Trace)threadLocalTrace.get();
        if(trace != null)
            return trace;
        else
            return getRootTrace();
    }

    public static Map getCurrentTraceParams()
        throws TracingInactiveException
    {
        return getCurrentTrace().getParams();
    }

    public static Trace getRootTrace()
        throws TracingInactiveException
    {
        Trace trace;
        try
        {
            trace = traceMachine.activityTrace.rootTrace;
        }
        catch(NullPointerException nullpointerexception)
        {
            throw new TracingInactiveException();
        }
        return trace;
    }

    public static TraceMachine getTraceMachine()
    {
        return traceMachine;
    }

    public static void haltTracing()
    {
        if(isTracingInactive())
        {
            return;
        } else
        {
            traceMachine.completeActivityTrace();
            threadLocalTrace.remove();
            threadLocalTraceStack.remove();
            return;
        }
    }

    public static boolean isTracingActive()
    {
        return traceMachine != null;
    }

    public static boolean isTracingInactive()
    {
        return traceMachine == null;
    }

    private static void loadTraceContext(Trace trace)
    {
        if(!isTracingInactive()) goto _L2; else goto _L1
_L1:
        return;
_L2:
        if(threadLocalTrace.get() != null)
            break; /* Loop/switch isn't completed */
        threadLocalTrace.set(trace);
        threadLocalTraceStack.set(new TraceStack());
        if(trace == null)
            continue; /* Loop/switch isn't completed */
        ((TraceStack)threadLocalTraceStack.get()).push(trace);
_L4:
        if(log.getLevel() == 5)
        {
            log.debug((new StringBuilder()).append("Trace ").append(trace.myUUID.toString()).append(" is now active").toString());
            return;
        }
        if(true) goto _L1; else goto _L3
_L3:
        if(trace == null)
        {
            if(((TraceStack)threadLocalTraceStack.get()).isEmpty())
            {
                if(log.getLevel() == 5)
                    log.debug("No context to load!");
                threadLocalTrace.set(null);
                return;
            }
            trace = (Trace)((TraceStack)threadLocalTraceStack.get()).peek();
            threadLocalTrace.set(trace);
        }
          goto _L4
        if(true) goto _L1; else goto _L5
_L5:
    }

    private static void pushTraceContext(Trace trace)
    {
        TraceStack tracestack;
        if(isTracingInactive() || trace == null)
            return;
        tracestack = (TraceStack)threadLocalTraceStack.get();
        if(!tracestack.empty()) goto _L2; else goto _L1
_L1:
        tracestack.push(trace);
_L4:
        threadLocalTrace.set(trace);
        return;
_L2:
        if(tracestack.peek() != trace)
            tracestack.push(trace);
        if(true) goto _L4; else goto _L3
_L3:
    }

    private static Trace registerNewTrace(String s)
        throws TracingInactiveException
    {
        if(isTracingInactive())
        {
            log.debug("Tried to register a new trace but tracing is inactive!");
            throw new TracingInactiveException();
        }
        Trace trace = getCurrentTrace();
        Trace trace1 = new Trace(s, trace.myUUID, traceMachine);
        try
        {
            traceMachine.activityTrace.addTrace(trace1);
        }
        catch(Exception exception)
        {
            throw new TracingInactiveException();
        }
        if(log.getLevel() == 5)
            log.debug((new StringBuilder()).append("Registering trace of ").append(s).append(" with parent ").append(trace.displayName).toString());
        trace.addChild(trace1);
        return trace1;
    }

    public static void removeTraceListener(TraceLifecycleAware tracelifecycleaware)
    {
        traceListeners.remove(tracelifecycleaware);
    }

    public static void setCurrentDisplayName(String s)
    {
        if(isTracingInactive())
            return;
        try
        {
            getCurrentTrace().displayName = s;
            return;
        }
        catch(TracingInactiveException tracinginactiveexception)
        {
            return;
        }
    }

    public static void setCurrentTraceParam(String s, Object obj)
    {
        if(isTracingInactive())
            return;
        try
        {
            getCurrentTrace().getParams().put(s, obj);
            return;
        }
        catch(TracingInactiveException tracinginactiveexception)
        {
            return;
        }
    }

    public static void setRootDisplayName(String s)
    {
        if(isTracingInactive())
            return;
        try
        {
            Trace trace = getRootTrace();
            Measurements.renameActivity(trace.displayName, s);
            trace.metricName = formatActivityMetricName(s);
            trace.metricBackgroundName = formatActivityBackgroundMetricName(s);
            trace.displayName = s;
            getCurrentTrace().scope = getCurrentScope();
            return;
        }
        catch(TracingInactiveException tracinginactiveexception)
        {
            return;
        }
    }

    public static void setTraceMachineInterface(TraceMachineInterface tracemachineinterface)
    {
        traceMachineInterface = tracemachineinterface;
    }

    public static void startTracing(String s)
    {
        if(!Harvest.shouldCollectActivityTraces())
            return;
        try
        {
            Trace trace = new Trace();
            trace.metricName = formatActivityMetricName(s);
            trace.metricBackgroundName = formatActivityBackgroundMetricName(s);
            trace.displayName = s;
            trace.entryTimestamp = System.currentTimeMillis();
            if(log.getLevel() == 5)
                log.debug((new StringBuilder()).append("Started trace of ").append(s).append(":").append(trace.myUUID.toString()).toString());
            if(isTracingActive())
                traceMachine.completeActivityTrace();
            threadLocalTrace.remove();
            threadLocalTraceStack.set(new TraceStack());
            traceMachine = new TraceMachine(trace);
            trace.traceMachine = traceMachine;
            pushTraceContext(trace);
            for(Iterator iterator = traceListeners.iterator(); iterator.hasNext(); ((TraceLifecycleAware)iterator.next()).onTraceStart(traceMachine.activityTrace));
        }
        catch(Exception exception)
        {
            log.error("Caught error while initializing TraceMachine, shutting it down", exception);
            AgentHealth.noticeException(exception);
            traceMachine = null;
            threadLocalTrace.remove();
            threadLocalTraceStack.remove();
        }
        return;
    }

    public static void unloadTraceContext(Object obj)
    {
        if(isTracingInactive())
            return;
        try
        {
            if(traceMachineInterface == null || !traceMachineInterface.isUIThread())
            {
                if(threadLocalTrace.get() != null && log.getLevel() == 5)
                    log.debug((new StringBuilder()).append("Trace ").append(((Trace)threadLocalTrace.get()).myUUID.toString()).append(" is now inactive").toString());
                threadLocalTrace.remove();
                threadLocalTraceStack.remove();
                ((TraceFieldInterface)obj)._nr_setTrace(null);
                return;
            }
        }
        catch(Exception exception)
        {
            log.error("Caught error while calling unloadTraceContext()", exception);
            AgentHealth.noticeException(exception);
        }
        return;
    }

    public void onHarvestBefore()
    {
        long l;
        long l1;
        long l2;
        if(!isTracingActive())
            break MISSING_BLOCK_LABEL_112;
        l = System.currentTimeMillis();
        l1 = traceMachine.activityTrace.lastUpdatedAt;
        l2 = traceMachine.activityTrace.startedAt;
        if(500L + l1 >= l || traceMachine.activityTrace.hasMissingChildren()) goto _L2; else goto _L1
_L1:
        log.debug("Completing activity trace after hitting healthy timeout (500ms)");
        completeActivityTrace();
        StatsEngine.get().inc("Supportability/AgentHealth/HealthyActivityTraces");
_L4:
        return;
_L2:
        if(60000L + l2 >= l) goto _L4; else goto _L3
_L3:
        log.debug("Completing activity trace after hitting unhealthy timeout (60000ms)");
        completeActivityTrace();
        StatsEngine.get().inc("Supportability/AgentHealth/UnhealthyActivityTraces");
        return;
        log.debug("TraceMachine is inactive");
        return;
    }

    public void onHarvestSendFailed()
    {
        traceMachine.activityTrace.incrementReportAttemptCount();
    }

    public void storeCompletedTrace(Trace trace)
    {
        try
        {
            if(isTracingInactive())
            {
                log.debug("Attempted to store a completed trace with no trace machine!");
                return;
            }
        }
        catch(Exception exception)
        {
            log.error("Caught error while calling storeCompletedTrace()", exception);
            AgentHealth.noticeException(exception);
            return;
        }
        activityTrace.addCompletedTrace(trace);
        return;
    }

    public static final String ACTIVITY_BACKGROUND_METRIC_PREFIX = "Mobile/Activity/Background/Name/";
    public static final String ACTIVITY_METRIC_PREFIX = "Mobile/Activity/Name/";
    public static final int HEALTHY_TRACE_TIMEOUT = 500;
    public static final String NR_TRACE_FIELD = "_nr_trace";
    public static final String NR_TRACE_TYPE = "Lcom/newrelic/agent/android/tracing/Trace;";
    public static final int UNHEALTHY_TRACE_TIMEOUT = 60000;
    private static final AgentLog log = AgentLogManager.getAgentLog();
    private static ThreadLocal threadLocalTrace = new ThreadLocal();
    private static ThreadLocal threadLocalTraceStack = new ThreadLocal();
    private static final Collection traceListeners = new CopyOnWriteArrayList();
    private static TraceMachine traceMachine = null;
    private static TraceMachineInterface traceMachineInterface;
    private ActivityTrace activityTrace;

}
