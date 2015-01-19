// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.tagmanager;

import android.content.Context;
import java.util.concurrent.*;

// Referenced classes of package com.google.tagmanager:
//            ResourceLoader, Log, LoadCallback, CtfeHost

class ResourceLoaderSchedulerImpl
    implements Container.ResourceLoaderScheduler
{
    static interface ResourceLoaderFactory
    {

        public abstract ResourceLoader createResourceLoader(CtfeHost ctfehost);
    }

    static interface ScheduledExecutorServiceFactory
    {

        public abstract ScheduledExecutorService createExecutorService();
    }


    public ResourceLoaderSchedulerImpl(Context context, String s, CtfeHost ctfehost)
    {
        this(context, s, ctfehost, null, null);
    }

    ResourceLoaderSchedulerImpl(Context context, String s, CtfeHost ctfehost, ScheduledExecutorServiceFactory scheduledexecutorservicefactory, ResourceLoaderFactory resourceloaderfactory)
    {
        mCtfeHost = ctfehost;
        mContext = context;
        mContainerId = s;
        if(scheduledexecutorservicefactory == null)
            scheduledexecutorservicefactory = new ScheduledExecutorServiceFactory() {

                public ScheduledExecutorService createExecutorService()
                {
                    return Executors.newSingleThreadScheduledExecutor();
                }

                final ResourceLoaderSchedulerImpl this$0;

            
            {
                this$0 = ResourceLoaderSchedulerImpl.this;
                super();
            }
            }
;
        mExecutor = scheduledexecutorservicefactory.createExecutorService();
        if(resourceloaderfactory == null)
        {
            mResourceLoaderFactory = new ResourceLoaderFactory() {

                public ResourceLoader createResourceLoader(CtfeHost ctfehost1)
                {
                    return new ResourceLoader(mContext, mContainerId, ctfehost1);
                }

                final ResourceLoaderSchedulerImpl this$0;

            
            {
                this$0 = ResourceLoaderSchedulerImpl.this;
                super();
            }
            }
;
            return;
        } else
        {
            mResourceLoaderFactory = resourceloaderfactory;
            return;
        }
    }

    private ResourceLoader createResourceLoader(String s)
    {
        ResourceLoader resourceloader = mResourceLoaderFactory.createResourceLoader(mCtfeHost);
        resourceloader.setLoadCallback(mCallback);
        resourceloader.setCtfeURLPathAndQuery(mCtfeUrlPathAndQuery);
        resourceloader.setPreviousVersion(s);
        return resourceloader;
    }

    private void ensureNotClosed()
    {
        this;
        JVM INSTR monitorenter ;
        if(mClosed)
            throw new IllegalStateException("called method after closed");
        break MISSING_BLOCK_LABEL_24;
        Exception exception;
        exception;
        this;
        JVM INSTR monitorexit ;
        throw exception;
        this;
        JVM INSTR monitorexit ;
    }

    public void close()
    {
        this;
        JVM INSTR monitorenter ;
        ensureNotClosed();
        if(mFuture != null)
            mFuture.cancel(false);
        mExecutor.shutdown();
        mClosed = true;
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public void loadAfterDelay(long l, String s)
    {
        this;
        JVM INSTR monitorenter ;
        Log.v((new StringBuilder()).append("loadAfterDelay: containerId=").append(mContainerId).append(" delay=").append(l).toString());
        ensureNotClosed();
        if(mCallback == null)
            throw new IllegalStateException("callback must be set before loadAfterDelay() is called.");
        break MISSING_BLOCK_LABEL_64;
        Exception exception;
        exception;
        this;
        JVM INSTR monitorexit ;
        throw exception;
        if(mFuture != null)
            mFuture.cancel(true);
        mFuture = mExecutor.schedule(createResourceLoader(s), l, TimeUnit.MILLISECONDS);
        this;
        JVM INSTR monitorexit ;
    }

    public void setCtfeURLPathAndQuery(String s)
    {
        this;
        JVM INSTR monitorenter ;
        ensureNotClosed();
        mCtfeUrlPathAndQuery = s;
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public void setLoadCallback(LoadCallback loadcallback)
    {
        this;
        JVM INSTR monitorenter ;
        ensureNotClosed();
        mCallback = loadcallback;
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    private static final boolean MAY_INTERRUPT_IF_RUNNING = true;
    private LoadCallback mCallback;
    private boolean mClosed;
    private final String mContainerId;
    private final Context mContext;
    private CtfeHost mCtfeHost;
    private String mCtfeUrlPathAndQuery;
    private final ScheduledExecutorService mExecutor;
    private ScheduledFuture mFuture;
    private final ResourceLoaderFactory mResourceLoaderFactory;


}
