// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.newrelic.agent.android.sample;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Process;
import com.newrelic.agent.android.harvest.AgentHealth;
import com.newrelic.agent.android.logging.AgentLog;
import com.newrelic.agent.android.logging.AgentLogManager;
import com.newrelic.agent.android.tracing.*;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

public class Sampler
    implements TraceLifecycleAware, Runnable
{

    private Sampler(Context context)
    {
        activityManager = (ActivityManager)context.getSystemService("activity");
        samples.put(com.newrelic.agent.android.tracing.Sample.SampleType.MEMORY, new ArrayList());
        samples.put(com.newrelic.agent.android.tracing.Sample.SampleType.CPU, new ArrayList());
    }

    private void clear()
    {
        for(Iterator iterator = samples.values().iterator(); iterator.hasNext(); ((Collection)iterator.next()).clear());
    }

    public static Map copySamples()
    {
        EnumMap enummap = new EnumMap(sampler.samples);
        com.newrelic.agent.android.tracing.Sample.SampleType sampletype;
        for(Iterator iterator = sampler.samples.keySet().iterator(); iterator.hasNext(); enummap.put(sampletype, new ArrayList((Collection)sampler.samples.get(sampletype))))
            sampletype = (com.newrelic.agent.android.tracing.Sample.SampleType)iterator.next();

        return Collections.unmodifiableMap(enummap);
    }

    private Collection getSampleCollection(com.newrelic.agent.android.tracing.Sample.SampleType sampletype)
    {
        return (Collection)samples.get(sampletype);
    }

    public static void init(Context context)
    {
        samplerLock.lock();
        sampler = new Sampler(context);
        samplerLock.unlock();
        TraceMachine.addTraceListener(sampler);
    }

    public static boolean isRunning()
    {
        while(sampler == null || sampler.sampleFuture.isDone()) 
            return false;
        return true;
    }

    private void resetCpuSampler()
    {
        lastCpuTime = null;
        lastAppCpuTime = null;
        if(appStatFile == null || procStatFile == null)
            break MISSING_BLOCK_LABEL_48;
        appStatFile.close();
        procStatFile.close();
        appStatFile = null;
        procStatFile = null;
        return;
        IOException ioexception;
        ioexception;
        log.debug((new StringBuilder()).append("Exception hit while resetting CPU sampler: ").append(ioexception.getMessage()).toString());
        AgentHealth.noticeException(ioexception);
        return;
    }

    private void sample()
    {
        Sample sample1 = sampleMemory();
        samplerLock.lock();
        if(sample1 != null)
            getSampleCollection(com.newrelic.agent.android.tracing.Sample.SampleType.MEMORY).add(sample1);
        Sample sample2 = sampleCpu();
        if(sample2 != null)
            getSampleCollection(com.newrelic.agent.android.tracing.Sample.SampleType.CPU).add(sample2);
        samplerLock.unlock();
    }

    public static Sample sampleMemory()
    {
        int i;
        if(sampler != null)
            if((i = sampler.activityManager.getProcessMemoryInfo(PID)[0].getTotalPss()) >= 0)
            {
                Sample sample1 = new Sample(com.newrelic.agent.android.tracing.Sample.SampleType.MEMORY);
                sample1.setSampleValue((double)i / 1024D);
                return sample1;
            }
        return null;
    }

    private void schedule()
    {
        if(isRunning.get())
        {
            return;
        } else
        {
            clear();
            isRunning.set(true);
            sampleFuture = scheduler.scheduleAtFixedRate(this, 0L, 100L, TimeUnit.MILLISECONDS);
            return;
        }
    }

    public static void start()
    {
        samplerLock.lock();
        if(sampler == null)
        {
            return;
        } else
        {
            sampler.schedule();
            samplerLock.unlock();
            log.debug("Sampler started");
            return;
        }
    }

    public static void stop()
    {
        if(sampler == null)
        {
            return;
        } else
        {
            sampler.stop(false);
            return;
        }
    }

    private void stop(boolean flag)
    {
        if(!isRunning.get())
        {
            return;
        } else
        {
            samplerLock.lock();
            isRunning.set(false);
            sampleFuture.cancel(flag);
            resetCpuSampler();
            samplerLock.unlock();
            log.debug("Sampler stopped");
            return;
        }
    }

    public static void stopNow()
    {
        if(sampler == null)
        {
            return;
        } else
        {
            sampler.stop(true);
            return;
        }
    }

    public void onEnterMethod()
    {
        if(isRunning.get())
        {
            return;
        } else
        {
            start();
            return;
        }
    }

    public void onExitMethod()
    {
    }

    public void onTraceComplete(ActivityTrace activitytrace)
    {
        stop();
        activitytrace.setVitals(copySamples());
        clear();
    }

    public void onTraceStart(ActivityTrace activitytrace)
    {
        start();
    }

    public void run()
    {
        try
        {
            if(isRunning.get())
                sample();
            return;
        }
        catch(Exception exception)
        {
            log.error("Caught exception while running the sampler", exception);
            AgentHealth.noticeException(exception);
            return;
        }
    }

    public Sample sampleCpu()
    {
        if(cpuSamplingDisabled)
            return null;
        if(procStatFile != null && appStatFile != null)
            break MISSING_BLOCK_LABEL_230;
        procStatFile = new RandomAccessFile("/proc/stat", "r");
        appStatFile = new RandomAccessFile((new StringBuilder()).append("/proc/").append(PID[0]).append("/stat").toString(), "r");
_L1:
        long l;
        long l1;
        String s = procStatFile.readLine();
        String s1 = appStatFile.readLine();
        String as[] = s.split(" ");
        String as1[] = s1.split(" ");
        l = Long.parseLong(as[2]) + Long.parseLong(as[3]) + Long.parseLong(as[4]) + Long.parseLong(as[5]) + Long.parseLong(as[6]) + Long.parseLong(as[7]) + Long.parseLong(as[8]);
        l1 = Long.parseLong(as1[13]) + Long.parseLong(as1[14]);
        if(lastCpuTime != null || lastAppCpuTime != null)
            break MISSING_BLOCK_LABEL_291;
        lastCpuTime = Long.valueOf(l);
        lastAppCpuTime = Long.valueOf(l1);
        return null;
        try
        {
            procStatFile.seek(0L);
            appStatFile.seek(0L);
        }
        catch(Exception exception)
        {
            cpuSamplingDisabled = true;
            log.debug((new StringBuilder()).append("Exception hit while CPU sampling: ").append(exception.getMessage()).toString());
            AgentHealth.noticeException(exception);
            return null;
        }
          goto _L1
        Sample sample1;
        sample1 = new Sample(com.newrelic.agent.android.tracing.Sample.SampleType.CPU);
        sample1.setSampleValue(100D * ((double)(l1 - lastAppCpuTime.longValue()) / (double)(l - lastCpuTime.longValue())));
        lastCpuTime = Long.valueOf(l);
        lastAppCpuTime = Long.valueOf(l1);
        return sample1;
    }

    private static final int KB_IN_MB = 1024;
    private static final int PID[];
    private static final long SAMPLE_FREQ_MS = 100L;
    private static boolean cpuSamplingDisabled = false;
    private static final AgentLog log = AgentLogManager.getAgentLog();
    private static Sampler sampler;
    private static final ReentrantLock samplerLock = new ReentrantLock();
    private final ActivityManager activityManager;
    private RandomAccessFile appStatFile;
    private final AtomicBoolean isRunning = new AtomicBoolean(false);
    private Long lastAppCpuTime;
    private Long lastCpuTime;
    private RandomAccessFile procStatFile;
    private ScheduledFuture sampleFuture;
    private final EnumMap samples = new EnumMap(com/newrelic/agent/android/tracing/Sample$SampleType);
    private final ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();

    static 
    {
        int ai[] = new int[1];
        ai[0] = Process.myPid();
        PID = ai;
    }
}
