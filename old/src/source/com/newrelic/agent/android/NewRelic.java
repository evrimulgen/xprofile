// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.newrelic.agent.android;

import android.content.Context;
import com.newrelic.agent.android.logging.AgentLog;
import com.newrelic.agent.android.logging.AgentLogManager;
import com.newrelic.agent.android.logging.AndroidAgentLog;
import com.newrelic.agent.android.logging.NullAgentLog;
import com.newrelic.agent.android.measurement.http.HttpTransactionMeasurement;
import com.newrelic.agent.android.metric.MetricUnit;
import com.newrelic.agent.android.tracing.TraceMachine;
import com.newrelic.agent.android.util.NetworkFailure;
import java.net.URLConnection;
import java.util.Map;
import org.apache.http.Header;
import org.apache.http.HttpResponse;

// Referenced classes of package com.newrelic.agent.android:
//            TaskQueue, Measurements, Agent, AgentImpl, 
//            NullAgentImpl, AndroidAgentImpl

public class NewRelic
{

    private NewRelic(String s)
    {
        collectorAddress = "mobile-collector.newrelic.com";
        ssl = true;
        loggingEnabled = true;
        locationServicesEnabled = false;
        logLevel = 3;
        token = s;
    }

    private static void _noticeHttpTransaction(String s, int i, long l, long l1, long l2, 
            long l3, String s1, Map map, String s2)
    {
        double d;
        if(!isEmpty(s, "noticeHttpTransaction: url must not be empty."))
            if(!isNegative((int)(d = l1 - l), "noticeHttpTransaction: the startTime is later than the endTime, resulting in a negative total time."))
            {
                TaskQueue.queue(new HttpTransactionMeasurement(s, i, 0, l, d / 1000D, l2, l3, s2));
                if((long)i >= 400L)
                {
                    Measurements.addHttpError(s, i, s1, map);
                    return;
                }
            }
    }

    private static boolean isEmpty(String s, String s1)
    {
        if(isNull(s, s1))
            return true;
        if(s.length() == 0)
        {
            log.error(s1);
            return true;
        } else
        {
            return false;
        }
    }

    private boolean isInstrumented()
    {
        return true;
    }

    private static boolean isNegative(int i, String s)
    {
        if(i < 0)
        {
            log.error(s);
            return true;
        } else
        {
            return false;
        }
    }

    private static boolean isNull(Object obj, String s)
    {
        if(obj == null)
        {
            log.error(s);
            return true;
        } else
        {
            return false;
        }
    }

    public static boolean isStarted()
    {
        return started;
    }

    public static void noticeHttpTransaction(String s, int i, long l, long l1, long l2, 
            long l3)
    {
        _noticeHttpTransaction(s, i, l, l1, l2, l3, null, null, null);
    }

    public static void noticeHttpTransaction(String s, int i, long l, long l1, long l2, 
            long l3, String s1)
    {
        _noticeHttpTransaction(s, i, l, l1, l2, l3, s1, null, null);
    }

    public static void noticeHttpTransaction(String s, int i, long l, long l1, long l2, 
            long l3, String s1, Map map)
    {
        _noticeHttpTransaction(s, i, l, l1, l2, l3, s1, map, null);
    }

    public static void noticeHttpTransaction(String s, int i, long l, long l1, long l2, 
            long l3, String s1, Map map, String s2)
    {
        _noticeHttpTransaction(s, i, l, l1, l2, l3, s1, map, s2);
    }

    public static void noticeHttpTransaction(String s, int i, long l, long l1, long l2, 
            long l3, String s1, Map map, URLConnection urlconnection)
    {
        if(urlconnection != null)
        {
            String s2 = urlconnection.getHeaderField("X-NewRelic-ID");
            if(s2 != null && s2.length() > 0)
            {
                _noticeHttpTransaction(s, i, l, l1, l2, l3, s1, map, s2);
                return;
            }
        }
        _noticeHttpTransaction(s, i, l, l1, l2, l3, s1, map, null);
    }

    public static void noticeHttpTransaction(String s, int i, long l, long l1, long l2, 
            long l3, String s1, Map map, HttpResponse httpresponse)
    {
        if(httpresponse != null)
        {
            Header header = httpresponse.getFirstHeader("X-NewRelic-ID");
            if(header != null && header.getValue() != null && header.getValue().length() > 0)
            {
                _noticeHttpTransaction(s, i, l, l1, l2, l3, s1, map, header.getValue());
                return;
            }
        }
        _noticeHttpTransaction(s, i, l, l1, l2, l3, s1, map, null);
    }

    public static void noticeNetworkFailure(String s, long l, long l1, NetworkFailure networkfailure)
    {
        TaskQueue.queue(new HttpTransactionMeasurement(s, 0, networkfailure.getErrorCode(), l, l1, 0L, 0L, null));
    }

    public static void noticeNetworkFailure(String s, long l, long l1, Exception exception)
    {
        if(isEmpty(s, "noticeHttpException: url must not be empty."))
        {
            return;
        } else
        {
            noticeNetworkFailure(s, l, l1, NetworkFailure.exceptionToNetworkFailure(exception));
            return;
        }
    }

    public static void recordMetric(String s, String s1, double d)
    {
        recordMetric(s, s1, 1, d, d, null, null);
    }

    public static void recordMetric(String s, String s1, int i, double d, double d1)
    {
        recordMetric(s, s1, i, d, d1, null, null);
    }

    public static void recordMetric(String s, String s1, int i, double d, double d1, MetricUnit metricunit, 
            MetricUnit metricunit1)
    {
        while(isNull(s1, "recordMetric: category must not be null. If no MetricCategory is applicable, use MetricCategory.NONE.") || isEmpty(s, "recordMetric: name must not be empty.") || isNegative(i, "recordMetric: count must not be negative.")) 
            return;
        Measurements.addCustomMetric(s, s1, i, d, d1, metricunit, metricunit1);
    }

    public static void setInteractionName(String s)
    {
        TraceMachine.setRootDisplayName(s);
    }

    public static void shutdown()
    {
        if(!started)
            break MISSING_BLOCK_LABEL_24;
        Agent.getImpl().stop();
        Agent.setImpl(NullAgentImpl.instance);
        started = false;
        return;
        Exception exception;
        exception;
        Agent.setImpl(NullAgentImpl.instance);
        started = false;
        throw exception;
    }

    public static void startInteraction(Context context, String s)
    {
        while(isNull(context, "startInteraction: context must be an Activity instance.") || isNull(s, "startInteraction: actionName must be an action/method name.")) 
            return;
        TraceMachine.startTracing((new StringBuilder()).append(context.getClass().getSimpleName()).append("#").append(s.replace("/", ".")).toString());
    }

    public static void startInteraction(Context context, String s, boolean flag)
    {
        if(TraceMachine.isTracingActive() && !flag)
        {
            log.warning("startInteraction: An interaction is already being traced, and invalidateActiveTrace is false. This interaction will not be traced.");
            return;
        } else
        {
            startInteraction(context, s);
            return;
        }
    }

    public static NewRelic withApplicationToken(String s)
    {
        return new NewRelic(s);
    }

    public void start(Context context)
    {
        if(started)
        {
            log.debug("NewRelic is already running.");
            return;
        }
        Object obj;
        if(!loggingEnabled)
            break MISSING_BLOCK_LABEL_98;
        obj = new AndroidAgentLog();
_L1:
        Throwable throwable;
        AgentLogManager.setAgentLog(((AgentLog) (obj)));
        log.setLevel(logLevel);
        if(isInstrumented())
        {
            AndroidAgentImpl.init(context, token, collectorAddress, ssl, locationServicesEnabled, appName);
            started = true;
            return;
        }
        break MISSING_BLOCK_LABEL_109;
        try
        {
            obj = new NullAgentLog();
        }
        // Misplaced declaration of an exception variable
        catch(Throwable throwable)
        {
            log.error("Error occurred while starting the New Relic agent!", throwable);
            return;
        }
          goto _L1
        log.error("Failed to detect New Relic instrumentation.  Something likely went wrong during your build process and you should contact support@newrelic.com.");
        return;
    }

    public NewRelic usingCollectorAddress(String s)
    {
        collectorAddress = s;
        return this;
    }

    public NewRelic usingSsl(boolean flag)
    {
        ssl = flag;
        return this;
    }

    public NewRelic withLocationServiceEnabled(boolean flag)
    {
        locationServicesEnabled = flag;
        return this;
    }

    public NewRelic withLogLevel(int i)
    {
        logLevel = i;
        return this;
    }

    public NewRelic withLoggingEnabled(boolean flag)
    {
        loggingEnabled = flag;
        return this;
    }

    private static final String DEFAULT_COLLECTOR_ADDR = "mobile-collector.newrelic.com";
    private static final AgentLog log = AgentLogManager.getAgentLog();
    private static boolean started = false;
    private String appName;
    private String collectorAddress;
    private boolean locationServicesEnabled;
    private int logLevel;
    private boolean loggingEnabled;
    private boolean ssl;
    private final String token;

}
