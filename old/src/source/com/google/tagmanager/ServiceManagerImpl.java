// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.tagmanager;

import android.content.Context;
import android.os.Handler;
import android.os.Message;

// Referenced classes of package com.google.tagmanager:
//            ServiceManager, NetworkReceiver, Log, HitSendingThread, 
//            PersistentHitStore, HitStoreStateListener, HitStore

class ServiceManagerImpl extends ServiceManager
{

    private ServiceManagerImpl()
    {
        dispatchPeriodInSeconds = 1800;
        pendingDispatch = true;
        readyToDispatch = false;
        connected = true;
        listenForNetwork = true;
        storeIsEmpty = false;
    }

    ServiceManagerImpl(Context context, HitSendingThread hitsendingthread, HitStore hitstore, boolean flag)
    {
        dispatchPeriodInSeconds = 1800;
        pendingDispatch = true;
        readyToDispatch = false;
        connected = true;
        listenForNetwork = true;
        storeIsEmpty = false;
        store = hitstore;
        thread = hitsendingthread;
        listenForNetwork = flag;
        initialize(context, hitsendingthread);
    }

    static void clearInstance()
    {
        instance = null;
    }

    public static ServiceManagerImpl getInstance()
    {
        if(instance == null)
            instance = new ServiceManagerImpl();
        return instance;
    }

    private void initializeHandler()
    {
        handler = new Handler(ctx.getMainLooper(), new android.os.Handler.Callback() {

            public boolean handleMessage(Message message)
            {
                if(1 == message.what && ServiceManagerImpl.MSG_OBJECT.equals(message.obj))
                {
                    dispatch();
                    if(dispatchPeriodInSeconds > 0 && !storeIsEmpty)
                        handler.sendMessageDelayed(handler.obtainMessage(1, ServiceManagerImpl.MSG_OBJECT), 1000 * dispatchPeriodInSeconds);
                }
                return true;
            }

            final ServiceManagerImpl this$0;

            
            {
                this$0 = ServiceManagerImpl.this;
                super();
            }
        }
);
        if(dispatchPeriodInSeconds > 0)
            handler.sendMessageDelayed(handler.obtainMessage(1, MSG_OBJECT), 1000 * dispatchPeriodInSeconds);
    }

    private void initializeNetworkReceiver()
    {
        networkReceiver = new NetworkReceiver(this);
        networkReceiver.register(ctx);
    }

    public void dispatch()
    {
        this;
        JVM INSTR monitorenter ;
        if(readyToDispatch)
            break MISSING_BLOCK_LABEL_22;
        Log.v("Dispatch call queued. Dispatch will run once initialization is complete.");
        pendingDispatch = true;
_L1:
        this;
        JVM INSTR monitorexit ;
        return;
        thread.queueToThread(new Runnable() {

            public void run()
            {
                store.dispatch();
            }

            final ServiceManagerImpl this$0;

            
            {
                this$0 = ServiceManagerImpl.this;
                super();
            }
        }
);
          goto _L1
        Exception exception;
        exception;
        throw exception;
    }

    HitStoreStateListener getListener()
    {
        return listener;
    }

    HitStore getStore()
    {
        this;
        JVM INSTR monitorenter ;
        if(store != null)
            break MISSING_BLOCK_LABEL_50;
        if(ctx == null)
            throw new IllegalStateException("Cant get a store unless we have a context");
        break MISSING_BLOCK_LABEL_31;
        Exception exception;
        exception;
        this;
        JVM INSTR monitorexit ;
        throw exception;
        store = new PersistentHitStore(listener, ctx);
        HitStore hitstore;
        if(handler == null)
            initializeHandler();
        readyToDispatch = true;
        if(pendingDispatch)
        {
            dispatch();
            pendingDispatch = false;
        }
        if(networkReceiver == null && listenForNetwork)
            initializeNetworkReceiver();
        hitstore = store;
        this;
        JVM INSTR monitorexit ;
        return hitstore;
    }

    void initialize(Context context, HitSendingThread hitsendingthread)
    {
        this;
        JVM INSTR monitorenter ;
        Context context1 = ctx;
        if(context1 == null) goto _L2; else goto _L1
_L1:
        this;
        JVM INSTR monitorexit ;
        return;
_L2:
        ctx = context.getApplicationContext();
        if(thread == null)
            thread = hitsendingthread;
        if(true) goto _L1; else goto _L3
_L3:
        Exception exception;
        exception;
        throw exception;
    }

    void onRadioPowered()
    {
        this;
        JVM INSTR monitorenter ;
        if(!storeIsEmpty && connected && dispatchPeriodInSeconds > 0)
        {
            handler.removeMessages(1, MSG_OBJECT);
            handler.sendMessage(handler.obtainMessage(1, MSG_OBJECT));
        }
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public void setDispatchPeriod(int i)
    {
        this;
        JVM INSTR monitorenter ;
        if(handler != null) goto _L2; else goto _L1
_L1:
        Log.v("Dispatch period set with null handler. Dispatch will run once initialization is complete.");
        dispatchPeriodInSeconds = i;
_L4:
        this;
        JVM INSTR monitorexit ;
        return;
_L2:
        if(!storeIsEmpty && connected && dispatchPeriodInSeconds > 0)
            handler.removeMessages(1, MSG_OBJECT);
        dispatchPeriodInSeconds = i;
        if(i <= 0) goto _L4; else goto _L3
_L3:
        if(storeIsEmpty || !connected) goto _L4; else goto _L5
_L5:
        handler.sendMessageDelayed(handler.obtainMessage(1, MSG_OBJECT), i * 1000);
          goto _L4
        Exception exception;
        exception;
        throw exception;
    }

    void updateConnectivityStatus(boolean flag)
    {
        this;
        JVM INSTR monitorenter ;
        updatePowerSaveMode(storeIsEmpty, flag);
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    void updatePowerSaveMode(boolean flag, boolean flag1)
    {
        this;
        JVM INSTR monitorenter ;
        if(storeIsEmpty != flag) goto _L2; else goto _L1
_L1:
        boolean flag2 = connected;
        if(flag2 != flag1) goto _L2; else goto _L3
_L3:
        this;
        JVM INSTR monitorexit ;
        return;
_L2:
        if(!flag && flag1)
            break MISSING_BLOCK_LABEL_51;
        if(dispatchPeriodInSeconds > 0)
            handler.removeMessages(1, MSG_OBJECT);
        if(flag || !flag1)
            break MISSING_BLOCK_LABEL_94;
        if(dispatchPeriodInSeconds > 0)
            handler.sendMessageDelayed(handler.obtainMessage(1, MSG_OBJECT), 1000 * dispatchPeriodInSeconds);
        StringBuilder stringbuilder = (new StringBuilder()).append("PowerSaveMode ");
        Exception exception;
        String s;
        if(!flag && flag1)
            s = "terminated.";
        else
            s = "initiated.";
        Log.v(stringbuilder.append(s).toString());
        storeIsEmpty = flag;
        connected = flag1;
        if(true) goto _L3; else goto _L4
_L4:
        exception;
        throw exception;
    }

    private static final int MSG_KEY = 1;
    private static final Object MSG_OBJECT = new Object();
    private static ServiceManagerImpl instance;
    private boolean connected;
    private Context ctx;
    private int dispatchPeriodInSeconds;
    private Handler handler;
    private boolean listenForNetwork;
    private HitStoreStateListener listener = new HitStoreStateListener() {

        public void reportStoreIsEmpty(boolean flag)
        {
            updatePowerSaveMode(flag, connected);
        }

        final ServiceManagerImpl this$0;

            
            {
                this$0 = ServiceManagerImpl.this;
                super();
            }
    }
;
    private NetworkReceiver networkReceiver;
    private boolean pendingDispatch;
    private boolean readyToDispatch;
    private HitStore store;
    private boolean storeIsEmpty;
    private volatile HitSendingThread thread;







}
