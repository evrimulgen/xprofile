// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.analytics.tracking.android;

import android.content.Context;
import android.content.Intent;
import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.LinkedBlockingQueue;

// Referenced classes of package com.google.analytics.tracking.android:
//            ServiceProxy, GoogleAnalytics, Log, AnalyticsClient, 
//            AnalyticsStore, AnalyticsThread, Clock, GAServiceManager, 
//            AnalyticsGmsCoreClient

class GAServiceProxy
    implements ServiceProxy, AnalyticsGmsCoreClient.OnConnectedListener, AnalyticsGmsCoreClient.OnConnectionFailedListener
{
    private static final class ConnectState extends Enum
    {

        public static ConnectState valueOf(String s)
        {
            return (ConnectState)Enum.valueOf(com/google/analytics/tracking/android/GAServiceProxy$ConnectState, s);
        }

        public static ConnectState[] values()
        {
            return (ConnectState[])$VALUES.clone();
        }

        private static final ConnectState $VALUES[];
        public static final ConnectState BLOCKED;
        public static final ConnectState CONNECTED_LOCAL;
        public static final ConnectState CONNECTED_SERVICE;
        public static final ConnectState CONNECTING;
        public static final ConnectState DISCONNECTED;
        public static final ConnectState PENDING_CONNECTION;
        public static final ConnectState PENDING_DISCONNECT;

        static 
        {
            CONNECTING = new ConnectState("CONNECTING", 0);
            CONNECTED_SERVICE = new ConnectState("CONNECTED_SERVICE", 1);
            CONNECTED_LOCAL = new ConnectState("CONNECTED_LOCAL", 2);
            BLOCKED = new ConnectState("BLOCKED", 3);
            PENDING_CONNECTION = new ConnectState("PENDING_CONNECTION", 4);
            PENDING_DISCONNECT = new ConnectState("PENDING_DISCONNECT", 5);
            DISCONNECTED = new ConnectState("DISCONNECTED", 6);
            ConnectState aconnectstate[] = new ConnectState[7];
            aconnectstate[0] = CONNECTING;
            aconnectstate[1] = CONNECTED_SERVICE;
            aconnectstate[2] = CONNECTED_LOCAL;
            aconnectstate[3] = BLOCKED;
            aconnectstate[4] = PENDING_CONNECTION;
            aconnectstate[5] = PENDING_DISCONNECT;
            aconnectstate[6] = DISCONNECTED;
            $VALUES = aconnectstate;
        }

        private ConnectState(String s, int i)
        {
            super(s, i);
        }
    }

    private class DisconnectCheckTask extends TimerTask
    {

        public void run()
        {
            if(state == ConnectState.CONNECTED_SERVICE && queue.isEmpty() && lastRequestTime + idleTimeout < clock.currentTimeMillis())
            {
                Log.v("Disconnecting due to inactivity");
                disconnectFromService();
                return;
            } else
            {
                disconnectCheckTimer.schedule(new DisconnectCheckTask(), idleTimeout);
                return;
            }
        }

        final GAServiceProxy this$0;

        private DisconnectCheckTask()
        {
            this$0 = GAServiceProxy.this;
            super();
        }

    }

    private class FailedConnectTask extends TimerTask
    {

        public void run()
        {
            if(state == ConnectState.CONNECTING)
                useStore();
        }

        final GAServiceProxy this$0;

        private FailedConnectTask()
        {
            this$0 = GAServiceProxy.this;
            super();
        }

    }

    private static class HitParams
    {

        public List getCommands()
        {
            return commands;
        }

        public long getHitTimeInMilliseconds()
        {
            return hitTimeInMilliseconds;
        }

        public String getPath()
        {
            return path;
        }

        public Map getWireFormatParams()
        {
            return wireFormatParams;
        }

        public String toString()
        {
            StringBuilder stringbuilder = new StringBuilder();
            stringbuilder.append("PATH: ");
            stringbuilder.append(path);
            if(wireFormatParams != null)
            {
                stringbuilder.append("  PARAMS: ");
                for(Iterator iterator = wireFormatParams.entrySet().iterator(); iterator.hasNext(); stringbuilder.append(",  "))
                {
                    java.util.Map.Entry entry = (java.util.Map.Entry)iterator.next();
                    stringbuilder.append((String)entry.getKey());
                    stringbuilder.append("=");
                    stringbuilder.append((String)entry.getValue());
                }

            }
            return stringbuilder.toString();
        }

        private final List commands;
        private final long hitTimeInMilliseconds;
        private final String path;
        private final Map wireFormatParams;

        public HitParams(Map map, long l, String s, List list)
        {
            wireFormatParams = map;
            hitTimeInMilliseconds = l;
            path = s;
            commands = list;
        }
    }

    private class ReconnectTask extends TimerTask
    {

        public void run()
        {
            connectToService();
        }

        final GAServiceProxy this$0;

        private ReconnectTask()
        {
            this$0 = GAServiceProxy.this;
            super();
        }

    }


    GAServiceProxy(Context context, AnalyticsThread analyticsthread)
    {
        this(context, analyticsthread, null, GoogleAnalytics.getInstance(context));
    }

    GAServiceProxy(Context context, AnalyticsThread analyticsthread, AnalyticsStore analyticsstore, GoogleAnalytics googleanalytics)
    {
        queue = new ConcurrentLinkedQueue();
        idleTimeout = 0x493e0L;
        testStore = analyticsstore;
        ctx = context;
        thread = analyticsthread;
        gaInstance = googleanalytics;
        connectTries = 0;
        state = ConnectState.DISCONNECTED;
    }

    private Timer cancelTimer(Timer timer)
    {
        if(timer != null)
            timer.cancel();
        return null;
    }

    private void clearAllTimers()
    {
        reConnectTimer = cancelTimer(reConnectTimer);
        failedConnectTimer = cancelTimer(failedConnectTimer);
        disconnectCheckTimer = cancelTimer(disconnectCheckTimer);
    }

    private void connectToService()
    {
        this;
        JVM INSTR monitorenter ;
        ConnectState connectstate;
        ConnectState connectstate1;
        if(forceLocalDispatch || client == null)
            break MISSING_BLOCK_LABEL_124;
        connectstate = state;
        connectstate1 = ConnectState.CONNECTED_LOCAL;
        if(connectstate == connectstate1)
            break MISSING_BLOCK_LABEL_124;
        connectTries = 1 + connectTries;
        cancelTimer(failedConnectTimer);
        state = ConnectState.CONNECTING;
        failedConnectTimer = new Timer("Failed Connect");
        failedConnectTimer.schedule(new FailedConnectTask(), 3000L);
        Log.v("connecting to Analytics service");
        client.connect();
_L1:
        this;
        JVM INSTR monitorexit ;
        return;
        SecurityException securityexception;
        securityexception;
        Log.w("security exception on connectToService");
        useStore();
          goto _L1
        Exception exception;
        exception;
        throw exception;
        Log.w("client not initialized.");
        useStore();
          goto _L1
    }

    private void disconnectFromService()
    {
        this;
        JVM INSTR monitorenter ;
        if(client != null && state == ConnectState.CONNECTED_SERVICE)
        {
            state = ConnectState.PENDING_DISCONNECT;
            client.disconnect();
        }
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    private void dispatchToStore()
    {
        store.dispatch();
        pendingDispatch = false;
    }

    private void fireReconnectAttempt()
    {
        reConnectTimer = cancelTimer(reConnectTimer);
        reConnectTimer = new Timer("Service Reconnect");
        reConnectTimer.schedule(new ReconnectTask(), 5000L);
    }

    private void sendQueue()
    {
        this;
        JVM INSTR monitorenter ;
        if(Thread.currentThread().equals(thread.getThread())) goto _L2; else goto _L1
_L1:
        thread.getQueue().add(new Runnable() {

            public void run()
            {
                sendQueue();
            }

            final GAServiceProxy this$0;

            
            {
                this$0 = GAServiceProxy.this;
                super();
            }
        }
);
_L3:
        this;
        JVM INSTR monitorexit ;
        return;
_L2:
        if(pendingClearHits)
            clearHits();
        static class _cls3
        {

            static final int $SwitchMap$com$google$analytics$tracking$android$GAServiceProxy$ConnectState[];

            static 
            {
                $SwitchMap$com$google$analytics$tracking$android$GAServiceProxy$ConnectState = new int[ConnectState.values().length];
                try
                {
                    $SwitchMap$com$google$analytics$tracking$android$GAServiceProxy$ConnectState[ConnectState.CONNECTED_LOCAL.ordinal()] = 1;
                }
                catch(NoSuchFieldError nosuchfielderror) { }
                try
                {
                    $SwitchMap$com$google$analytics$tracking$android$GAServiceProxy$ConnectState[ConnectState.CONNECTED_SERVICE.ordinal()] = 2;
                }
                catch(NoSuchFieldError nosuchfielderror1) { }
                try
                {
                    $SwitchMap$com$google$analytics$tracking$android$GAServiceProxy$ConnectState[ConnectState.CONNECTING.ordinal()] = 3;
                }
                catch(NoSuchFieldError nosuchfielderror2) { }
                try
                {
                    $SwitchMap$com$google$analytics$tracking$android$GAServiceProxy$ConnectState[ConnectState.PENDING_CONNECTION.ordinal()] = 4;
                }
                catch(NoSuchFieldError nosuchfielderror3) { }
                try
                {
                    $SwitchMap$com$google$analytics$tracking$android$GAServiceProxy$ConnectState[ConnectState.PENDING_DISCONNECT.ordinal()] = 5;
                }
                catch(NoSuchFieldError nosuchfielderror4) { }
                try
                {
                    $SwitchMap$com$google$analytics$tracking$android$GAServiceProxy$ConnectState[ConnectState.DISCONNECTED.ordinal()] = 6;
                }
                catch(NoSuchFieldError nosuchfielderror5)
                {
                    return;
                }
            }
        }

        _cls3..SwitchMap.com.google.analytics.tracking.android.GAServiceProxy.ConnectState[state.ordinal()];
        JVM INSTR tableswitch 1 6: default 351
    //                   1 104
    //                   2 205
    //                   3 351
    //                   4 351
    //                   5 351
    //                   6 326;
           goto _L3 _L4 _L5 _L3 _L3 _L3 _L6
_L4:
        HitParams hitparams1;
        for(; !queue.isEmpty(); store.putHit(hitparams1.getWireFormatParams(), hitparams1.getHitTimeInMilliseconds(), hitparams1.getPath(), hitparams1.getCommands()))
        {
            hitparams1 = (HitParams)queue.poll();
            Log.v((new StringBuilder()).append("Sending hit to store  ").append(hitparams1).toString());
        }

          goto _L7
        Exception exception;
        exception;
        throw exception;
_L7:
        if(!pendingDispatch) goto _L3; else goto _L8
_L8:
        dispatchToStore();
          goto _L3
_L5:
        if(queue.isEmpty()) goto _L10; else goto _L9
_L9:
        HitParams hitparams;
        hitparams = (HitParams)queue.peek();
        Log.v((new StringBuilder()).append("Sending hit to service   ").append(hitparams).toString());
        if(gaInstance.isDryRunEnabled()) goto _L12; else goto _L11
_L11:
        client.sendHit(hitparams.getWireFormatParams(), hitparams.getHitTimeInMilliseconds(), hitparams.getPath(), hitparams.getCommands());
_L13:
        queue.poll();
          goto _L5
_L12:
        Log.v("Dry run enabled. Hit not actually sent to service.");
          goto _L13
_L10:
        lastRequestTime = clock.currentTimeMillis();
          goto _L3
_L6:
        Log.v("Need to reconnect");
        if(!queue.isEmpty())
            connectToService();
          goto _L3
    }

    private void useStore()
    {
        this;
        JVM INSTR monitorenter ;
        ConnectState connectstate;
        ConnectState connectstate1;
        connectstate = state;
        connectstate1 = ConnectState.CONNECTED_LOCAL;
        if(connectstate != connectstate1) goto _L2; else goto _L1
_L1:
        this;
        JVM INSTR monitorexit ;
        return;
_L2:
        clearAllTimers();
        Log.v("falling back to local store");
        if(testStore == null)
            break; /* Loop/switch isn't completed */
        store = testStore;
_L4:
        state = ConnectState.CONNECTED_LOCAL;
        sendQueue();
        if(true) goto _L1; else goto _L3
        Exception exception;
        exception;
        throw exception;
_L3:
        GAServiceManager gaservicemanager = GAServiceManager.getInstance();
        gaservicemanager.initialize(ctx, thread);
        store = gaservicemanager.getStore();
          goto _L4
    }

    public void clearHits()
    {
        Log.v("clearHits called");
        queue.clear();
        switch(_cls3..SwitchMap.com.google.analytics.tracking.android.GAServiceProxy.ConnectState[state.ordinal()])
        {
        default:
            pendingClearHits = true;
            return;

        case 1: // '\001'
            store.clearHits(0L);
            pendingClearHits = false;
            return;

        case 2: // '\002'
            client.clearHits();
            break;
        }
        pendingClearHits = false;
    }

    public void createService()
    {
        if(client != null)
        {
            return;
        } else
        {
            client = new AnalyticsGmsCoreClient(ctx, this, this);
            connectToService();
            return;
        }
    }

    void createService(AnalyticsClient analyticsclient)
    {
        if(client != null)
        {
            return;
        } else
        {
            client = analyticsclient;
            connectToService();
            return;
        }
    }

    public void dispatch()
    {
        switch(_cls3..SwitchMap.com.google.analytics.tracking.android.GAServiceProxy.ConnectState[state.ordinal()])
        {
        default:
            pendingDispatch = true;
            // fall through

        case 2: // '\002'
            return;

        case 1: // '\001'
            dispatchToStore();
            break;
        }
    }

    public void onConnected()
    {
        this;
        JVM INSTR monitorenter ;
        failedConnectTimer = cancelTimer(failedConnectTimer);
        connectTries = 0;
        Log.v("Connected to service");
        state = ConnectState.CONNECTED_SERVICE;
        if(!pendingServiceDisconnect)
            break MISSING_BLOCK_LABEL_51;
        disconnectFromService();
        pendingServiceDisconnect = false;
_L1:
        this;
        JVM INSTR monitorexit ;
        return;
        sendQueue();
        disconnectCheckTimer = cancelTimer(disconnectCheckTimer);
        disconnectCheckTimer = new Timer("disconnect check");
        disconnectCheckTimer.schedule(new DisconnectCheckTask(), idleTimeout);
          goto _L1
        Exception exception;
        exception;
        throw exception;
    }

    public void onConnectionFailed(int i, Intent intent)
    {
        this;
        JVM INSTR monitorenter ;
        state = ConnectState.PENDING_CONNECTION;
        if(connectTries >= 2)
            break MISSING_BLOCK_LABEL_53;
        Log.w((new StringBuilder()).append("Service unavailable (code=").append(i).append("), will retry.").toString());
        fireReconnectAttempt();
_L1:
        this;
        JVM INSTR monitorexit ;
        return;
        Log.w((new StringBuilder()).append("Service unavailable (code=").append(i).append("), using local store.").toString());
        useStore();
          goto _L1
        Exception exception;
        exception;
        throw exception;
    }

    public void onDisconnected()
    {
        this;
        JVM INSTR monitorenter ;
        if(state != ConnectState.PENDING_DISCONNECT) goto _L2; else goto _L1
_L1:
        Log.v("Disconnected from service");
        clearAllTimers();
        state = ConnectState.DISCONNECTED;
_L3:
        this;
        JVM INSTR monitorexit ;
        return;
_L2:
        Log.v("Unexpected disconnect.");
        state = ConnectState.PENDING_CONNECTION;
        if(connectTries >= 2)
            break MISSING_BLOCK_LABEL_65;
        fireReconnectAttempt();
          goto _L3
        Exception exception;
        exception;
        throw exception;
        useStore();
          goto _L3
    }

    public void putHit(Map map, long l, String s, List list)
    {
        Log.v("putHit called");
        queue.add(new HitParams(map, l, s, list));
        sendQueue();
    }

    void setClock(Clock clock1)
    {
        clock = clock1;
    }

    public void setForceLocalDispatch()
    {
        this;
        JVM INSTR monitorenter ;
        boolean flag = forceLocalDispatch;
        if(!flag) goto _L2; else goto _L1
_L1:
        this;
        JVM INSTR monitorexit ;
        return;
_L2:
        Log.v("setForceLocalDispatch called.");
        forceLocalDispatch = true;
        _cls3..SwitchMap.com.google.analytics.tracking.android.GAServiceProxy.ConnectState[state.ordinal()];
        JVM INSTR tableswitch 1 6: default 96
    //                   1 11
    //                   2 76
    //                   3 88
    //                   4 11
    //                   5 11
    //                   6 11;
           goto _L3 _L3 _L4 _L5 _L3 _L3 _L3
_L3:
        if(true) goto _L1; else goto _L6
_L6:
_L4:
        disconnectFromService();
          goto _L1
        Exception exception;
        exception;
        throw exception;
_L5:
        pendingServiceDisconnect = true;
          goto _L1
    }

    public void setIdleTimeout(long l)
    {
        idleTimeout = l;
    }

    private static final long FAILED_CONNECT_WAIT_TIME = 3000L;
    private static final int MAX_TRIES = 2;
    private static final long RECONNECT_WAIT_TIME = 5000L;
    private static final long SERVICE_CONNECTION_TIMEOUT = 0x493e0L;
    private volatile AnalyticsClient client;
    private Clock clock = new Clock() {

        public long currentTimeMillis()
        {
            return System.currentTimeMillis();
        }

        final GAServiceProxy this$0;

            
            {
                this$0 = GAServiceProxy.this;
                super();
            }
    }
;
    private volatile int connectTries;
    private final Context ctx;
    private volatile Timer disconnectCheckTimer;
    private volatile Timer failedConnectTimer;
    private boolean forceLocalDispatch;
    private final GoogleAnalytics gaInstance;
    private long idleTimeout;
    private volatile long lastRequestTime;
    private boolean pendingClearHits;
    private boolean pendingDispatch;
    private boolean pendingServiceDisconnect;
    private final Queue queue;
    private volatile Timer reConnectTimer;
    private volatile ConnectState state;
    private AnalyticsStore store;
    private AnalyticsStore testStore;
    private final AnalyticsThread thread;










}
