// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.tagmanager;

import android.content.Context;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.concurrent.LinkedBlockingQueue;

// Referenced classes of package com.google.tagmanager:
//            HitSendingThread, Log, HitStore, ServiceManagerImpl

class HitSendingThreadImpl extends Thread
    implements HitSendingThread
{

    private HitSendingThreadImpl(Context context)
    {
        super("GAThread");
        queue = new LinkedBlockingQueue();
        mDisabled = false;
        mClosed = false;
        if(context != null)
            mContext = context.getApplicationContext();
        else
            mContext = context;
        start();
    }

    HitSendingThreadImpl(Context context, HitStore hitstore)
    {
        super("GAThread");
        queue = new LinkedBlockingQueue();
        mDisabled = false;
        mClosed = false;
        if(context != null)
            mContext = context.getApplicationContext();
        else
            mContext = context;
        mUrlStore = hitstore;
        start();
    }

    static HitSendingThreadImpl getInstance(Context context)
    {
        if(sInstance == null)
            sInstance = new HitSendingThreadImpl(context);
        return sInstance;
    }

    private String printStackTrace(Throwable throwable)
    {
        ByteArrayOutputStream bytearrayoutputstream = new ByteArrayOutputStream();
        PrintStream printstream = new PrintStream(bytearrayoutputstream);
        throwable.printStackTrace(printstream);
        printstream.flush();
        return new String(bytearrayoutputstream.toByteArray());
    }

    void close()
    {
        mClosed = true;
        interrupt();
    }

    int getQueueSize()
    {
        return queue.size();
    }

    HitStore getStore()
    {
        return mUrlStore;
    }

    boolean isDisabled()
    {
        return mDisabled;
    }

    public void queueToThread(Runnable runnable)
    {
        queue.add(runnable);
    }

    public void run()
    {
_L2:
        if(mClosed)
            break; /* Loop/switch isn't completed */
        Runnable runnable = (Runnable)queue.take();
        if(!mDisabled)
            runnable.run();
        continue; /* Loop/switch isn't completed */
        InterruptedException interruptedexception;
        interruptedexception;
        try
        {
            Log.i(interruptedexception.toString());
        }
        catch(Throwable throwable)
        {
            Log.e((new StringBuilder()).append("Error on GAThread: ").append(printStackTrace(throwable)).toString());
            Log.e("Google Analytics is shutting down.");
            mDisabled = true;
        }
        if(true) goto _L2; else goto _L1
_L1:
    }

    public void sendHit(String s)
    {
        sendHit(s, System.currentTimeMillis());
    }

    void sendHit(final String url, final long hitTime)
    {
        queueToThread(new Runnable() {

            public void run()
            {
                if(mUrlStore == null)
                {
                    ServiceManagerImpl servicemanagerimpl = ServiceManagerImpl.getInstance();
                    servicemanagerimpl.initialize(mContext, thread);
                    mUrlStore = servicemanagerimpl.getStore();
                }
                mUrlStore.putHit(hitTime, url);
            }

            final HitSendingThreadImpl this$0;
            final long val$hitTime;
            final HitSendingThread val$thread;
            final String val$url;

            
            {
                this$0 = HitSendingThreadImpl.this;
                thread = hitsendingthread;
                hitTime = l;
                url = s;
                super();
            }
        }
);
    }

    private static HitSendingThreadImpl sInstance;
    private volatile boolean mClosed;
    private final Context mContext;
    private volatile boolean mDisabled;
    private volatile HitStore mUrlStore;
    private final LinkedBlockingQueue queue;



/*
    static HitStore access$002(HitSendingThreadImpl hitsendingthreadimpl, HitStore hitstore)
    {
        hitsendingthreadimpl.mUrlStore = hitstore;
        return hitstore;
    }

*/

}
