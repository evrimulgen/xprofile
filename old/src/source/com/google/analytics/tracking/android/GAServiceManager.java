// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.analytics.tracking.android;

import android.content.Context;
import android.os.Handler;
import android.os.Message;

// Referenced classes of package com.google.analytics.tracking.android:
//            ServiceManager, GANetworkReceiver, Log, GAUsage, 
//            AnalyticsThread, PersistentAnalyticsStore, AnalyticsStore, Dispatcher, 
//            AnalyticsStoreStateListener

public class GAServiceManager extends ServiceManager
{

    private GAServiceManager()
    {
        dispatchPeriodInSeconds = 1800;
        pendingDispatch = true;
        connected = true;
        listenForNetwork = true;
        storeIsEmpty = false;
    }

    GAServiceManager(Context context, AnalyticsThread analyticsthread, AnalyticsStore analyticsstore, boolean flag)
    {
        dispatchPeriodInSeconds = 1800;
        pendingDispatch = true;
        connected = true;
        listenForNetwork = true;
        storeIsEmpty = false;
        store = analyticsstore;
        thread = analyticsthread;
        listenForNetwork = flag;
        initialize(context, analyticsthread);
    }

    static void clearInstance()
    {
        instance = null;
    }

    public static GAServiceManager getInstance()
    {
        if(instance == null)
            instance = new GAServiceManager();
        return instance;
    }

    private void initializeHandler()
    {
        handler = new Handler(ctx.getMainLooper(), new android.os.Handler.Callback() {

            public boolean handleMessage(Message message)
            {
                if(1 == message.what && GAServiceManager.MSG_OBJECT.equals(message.obj))
                {
                    GAUsage.getInstance().setDisableUsage(true);
                    dispatchLocalHits();
                    GAUsage.getInstance().setDisableUsage(false);
                    if(dispatchPeriodInSeconds > 0 && !storeIsEmpty)
                        handler.sendMessageDelayed(handler.obtainMessage(1, GAServiceManager.MSG_OBJECT), 1000 * dispatchPeriodInSeconds);
                }
                return true;
            }

            final GAServiceManager this$0;

            
            {
                this$0 = GAServiceManager.this;
                super();
            }
        }
);
        if(dispatchPeriodInSeconds > 0)
            handler.sendMessageDelayed(handler.obtainMessage(1, MSG_OBJECT), 1000 * dispatchPeriodInSeconds);
    }

    private void initializeNetworkReceiver()
    {
        networkReceiver = new GANetworkReceiver(this);
        networkReceiver.register(ctx);
    }

    public void dispatchLocalHits()
    {
        this;
        JVM INSTR monitorenter ;
        if(thread != null)
            break MISSING_BLOCK_LABEL_22;
        Log.v("Dispatch call queued. Dispatch will run once initialization is complete.");
        pendingDispatch = true;
_L1:
        this;
        JVM INSTR monitorexit ;
        return;
        GAUsage.getInstance().setUsage(GAUsage.Field.DISPATCH);
        thread.dispatch();
          goto _L1
        Exception exception;
        exception;
        throw exception;
    }

    AnalyticsStoreStateListener getListener()
    {
        return listener;
    }

    AnalyticsStore getStore()
    {
        this;
        JVM INSTR monitorenter ;
        if(store != null)
            break MISSING_BLOCK_LABEL_80;
        if(ctx == null)
            throw new IllegalStateException("Cant get a store unless we have a context");
        break MISSING_BLOCK_LABEL_31;
        Exception exception;
        exception;
        this;
        JVM INSTR monitorexit ;
        throw exception;
        store = new PersistentAnalyticsStore(listener, ctx);
        if(pendingHostOverride != null)
        {
            store.getDispatcher().overrideHostUrl(pendingHostOverride);
            pendingHostOverride = null;
        }
        AnalyticsStore analyticsstore;
        if(handler == null)
            initializeHandler();
        if(networkReceiver == null && listenForNetwork)
            initializeNetworkReceiver();
        analyticsstore = store;
        this;
        JVM INSTR monitorexit ;
        return analyticsstore;
    }

    void initialize(Context context, AnalyticsThread analyticsthread)
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
        {
            thread = analyticsthread;
            if(pendingDispatch)
            {
                dispatchLocalHits();
                pendingDispatch = false;
            }
            if(pendingForceLocalDispatch)
            {
                setForceLocalDispatch();
                pendingForceLocalDispatch = false;
            }
        }
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

    void overrideHostUrl(String s)
    {
        this;
        JVM INSTR monitorenter ;
        if(store != null)
            break MISSING_BLOCK_LABEL_17;
        pendingHostOverride = s;
_L1:
        this;
        JVM INSTR monitorexit ;
        return;
        store.getDispatcher().overrideHostUrl(s);
          goto _L1
        Exception exception;
        exception;
        throw exception;
    }

    public void setForceLocalDispatch()
    {
        if(thread == null)
        {
            Log.v("setForceLocalDispatch() queued. It will be called once initialization is complete.");
            pendingForceLocalDispatch = true;
            return;
        } else
        {
            GAUsage.getInstance().setUsage(GAUsage.Field.SET_FORCE_LOCAL_DISPATCH);
            thread.setForceLocalDispatch();
            return;
        }
    }

    public void setLocalDispatchPeriod(int i)
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
        GAUsage.getInstance().setUsage(GAUsage.Field.SET_DISPATCH_PERIOD);
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
    private static GAServiceManager instance;
    private boolean connected;
    private Context ctx;
    private int dispatchPeriodInSeconds;
    private Handler handler;
    private boolean listenForNetwork;
    private AnalyticsStoreStateListener listener = new AnalyticsStoreStateListener() {

        public void reportStoreIsEmpty(boolean flag)
        {
            updatePowerSaveMode(flag, connected);
        }

        final GAServiceManager this$0;

            
            {
                this$0 = GAServiceManager.this;
                super();
            }
    }
;
    private GANetworkReceiver networkReceiver;
    private boolean pendingDispatch;
    private boolean pendingForceLocalDispatch;
    private String pendingHostOverride;
    private AnalyticsStore store;
    private boolean storeIsEmpty;
    private volatile AnalyticsThread thread;






}
