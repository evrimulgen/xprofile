// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.newrelic.agent.android.background;

import com.newrelic.agent.android.logging.AgentLog;
import com.newrelic.agent.android.logging.AgentLogManager;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.*;

// Referenced classes of package com.newrelic.agent.android.background:
//            ApplicationStateEvent, ApplicationStateListener

public class ApplicationStateMonitor
    implements Runnable
{

    private ApplicationStateMonitor()
    {
        this(5, 5, TimeUnit.SECONDS, 5000);
    }

    ApplicationStateMonitor(int i, int j, TimeUnit timeunit, int k)
    {
        count = 0L;
        snoozeStartTime = 0L;
        snoozeLock = new Object();
        applicationStateListeners = new ArrayList();
        foregrounded = true;
        foregroundLock = new Object();
        ScheduledThreadPoolExecutor scheduledthreadpoolexecutor = new ScheduledThreadPoolExecutor(1, new ThreadFactory() {

            public Thread newThread(Runnable runnable)
            {
                return new Thread(runnable, "[New Relic] App State Monitor");
            }

            final ApplicationStateMonitor this$0;

            
            {
                this$0 = ApplicationStateMonitor.this;
                super();
            }
        }
);
        activitySnoozeTimeInMilliseconds = k;
        scheduledthreadpoolexecutor.scheduleAtFixedRate(this, i, j, timeunit);
        log.info("Application state monitor has started");
    }

    public static ApplicationStateMonitor getInstance()
    {
        if(instance == null)
            instance = new ApplicationStateMonitor();
        return instance;
    }

    private long getSnoozeTime()
    {
        Object obj = foregroundLock;
        obj;
        JVM INSTR monitorenter ;
        synchronized(snoozeLock)
        {
            if(snoozeStartTime != 0L)
                break MISSING_BLOCK_LABEL_29;
        }
        obj;
        JVM INSTR monitorexit ;
        return 0L;
        long l = System.currentTimeMillis() - snoozeStartTime;
        obj1;
        JVM INSTR monitorexit ;
        obj;
        JVM INSTR monitorexit ;
        return l;
        Exception exception;
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
        exception1;
        obj1;
        JVM INSTR monitorexit ;
        throw exception1;
    }

    private void notifyApplicationInBackground()
    {
        ArrayList arraylist1;
        synchronized(applicationStateListeners)
        {
            arraylist1 = new ArrayList(applicationStateListeners);
        }
        ApplicationStateEvent applicationstateevent = new ApplicationStateEvent(this);
        for(Iterator iterator = arraylist1.iterator(); iterator.hasNext(); ((ApplicationStateListener)iterator.next()).applicationBackgrounded(applicationstateevent));
        break MISSING_BLOCK_LABEL_72;
        exception;
        arraylist;
        JVM INSTR monitorexit ;
        throw exception;
    }

    private void notifyApplicationInForeground()
    {
        ArrayList arraylist1;
        synchronized(applicationStateListeners)
        {
            arraylist1 = new ArrayList(applicationStateListeners);
        }
        ApplicationStateEvent applicationstateevent = new ApplicationStateEvent(this);
        for(Iterator iterator = arraylist1.iterator(); iterator.hasNext(); ((ApplicationStateListener)iterator.next()).applicationForegrounded(applicationstateevent));
        break MISSING_BLOCK_LABEL_72;
        exception;
        arraylist;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public void activityStarted()
    {
        Object obj = foregroundLock;
        obj;
        JVM INSTR monitorenter ;
        synchronized(snoozeLock)
        {
            count = 1L + count;
            if(count == 1L)
                snoozeStartTime = 0L;
        }
        if(!foregrounded)
        {
            log.verbose("Application appears to be in the foreground");
            notifyApplicationInForeground();
            foregrounded = true;
        }
        obj;
        JVM INSTR monitorexit ;
        return;
        exception1;
        obj1;
        JVM INSTR monitorexit ;
        throw exception1;
        Exception exception;
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public void activityStopped()
    {
        Object obj = foregroundLock;
        obj;
        JVM INSTR monitorenter ;
        synchronized(snoozeLock)
        {
            count = count - 1L;
            if(count == 0L)
                snoozeStartTime = System.currentTimeMillis();
        }
        obj;
        JVM INSTR monitorexit ;
        return;
        exception1;
        obj1;
        JVM INSTR monitorexit ;
        throw exception1;
        Exception exception;
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public void addApplicationStateListener(ApplicationStateListener applicationstatelistener)
    {
        synchronized(applicationStateListeners)
        {
            applicationStateListeners.add(applicationstatelistener);
        }
        return;
        exception;
        arraylist;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public void removeApplicationStateListener(ApplicationStateListener applicationstatelistener)
    {
        synchronized(applicationStateListeners)
        {
            applicationStateListeners.remove(applicationstatelistener);
        }
        return;
        exception;
        arraylist;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public void run()
    {
        synchronized(foregroundLock)
        {
            if(getSnoozeTime() >= (long)activitySnoozeTimeInMilliseconds && foregrounded)
            {
                log.verbose("Application appears to have gone to the background");
                notifyApplicationInBackground();
                foregrounded = false;
            }
        }
        return;
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
    }

    private static ApplicationStateMonitor instance;
    private static final AgentLog log = AgentLogManager.getAgentLog();
    private final int activitySnoozeTimeInMilliseconds;
    private final ArrayList applicationStateListeners;
    private long count;
    private final Object foregroundLock;
    private boolean foregrounded;
    private final Object snoozeLock;
    private long snoozeStartTime;

}
