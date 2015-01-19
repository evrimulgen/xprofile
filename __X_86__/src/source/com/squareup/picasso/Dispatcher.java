// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.squareup.picasso;

import android.content.*;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.*;
import java.util.*;
import java.util.concurrent.ExecutorService;

// Referenced classes of package com.squareup.picasso:
//            Utils, BitmapHunter, Action, Cache, 
//            PicassoExecutorService, Downloader, Stats, Picasso

class Dispatcher
{
    private static class DispatcherHandler extends Handler
    {

        public void handleMessage(Message message)
        {
            boolean flag = true;
            Dispatcher dispatcher1;
            switch(message.what)
            {
            case 3: // '\003'
            case 8: // '\b'
            default:
                Picasso.HANDLER.post(message. new Runnable() {

                    public void run()
                    {
                        throw new AssertionError((new StringBuilder()).append("Unknown handler message received: ").append(msg.what).toString());
                    }

                    final DispatcherHandler this$0;
                    final Message val$msg;

            
            {
                this$0 = final_dispatcherhandler;
                msg = Message.this;
                super();
            }
                }
);
                return;

            case 1: // '\001'
                Action action1 = (Action)message.obj;
                dispatcher.performSubmit(action1);
                return;

            case 2: // '\002'
                Action action = (Action)message.obj;
                dispatcher.performCancel(action);
                return;

            case 4: // '\004'
                BitmapHunter bitmaphunter2 = (BitmapHunter)message.obj;
                dispatcher.performComplete(bitmaphunter2);
                return;

            case 5: // '\005'
                BitmapHunter bitmaphunter1 = (BitmapHunter)message.obj;
                dispatcher.performRetry(bitmaphunter1);
                return;

            case 6: // '\006'
                BitmapHunter bitmaphunter = (BitmapHunter)message.obj;
                dispatcher.performError(bitmaphunter);
                return;

            case 7: // '\007'
                dispatcher.performBatchComplete();
                return;

            case 9: // '\t'
                NetworkInfo networkinfo = (NetworkInfo)message.obj;
                dispatcher.performNetworkStateChange(networkinfo);
                return;

            case 10: // '\n'
                dispatcher1 = dispatcher;
                break;
            }
            if(message.arg1 != flag)
                flag = false;
            dispatcher1.performAirplaneModeChange(flag);
        }

        private final Dispatcher dispatcher;

        public DispatcherHandler(Looper looper, Dispatcher dispatcher1)
        {
            super(looper);
            dispatcher = dispatcher1;
        }
    }

    static class DispatcherThread extends HandlerThread
    {

        DispatcherThread()
        {
            super("Picasso-Dispatcher", 10);
        }
    }

    private class NetworkBroadcastReceiver extends BroadcastReceiver
    {

        public void onReceive(Context context1, Intent intent)
        {
            if(intent != null)
            {
                String s = intent.getAction();
                Bundle bundle = intent.getExtras();
                if("android.intent.action.AIRPLANE_MODE".equals(s))
                {
                    dispatchAirplaneModeChange(bundle.getBoolean("state", false));
                    return;
                }
                if("android.net.conn.CONNECTIVITY_CHANGE".equals(s))
                {
                    dispatchNetworkStateChange(connectivityManager.getActiveNetworkInfo());
                    return;
                }
            }
        }

        void register()
        {
            boolean flag;
            IntentFilter intentfilter;
            if((service instanceof PicassoExecutorService) && Utils.hasPermission(context, "android.permission.ACCESS_NETWORK_STATE"))
                flag = true;
            else
                flag = false;
            intentfilter = new IntentFilter();
            intentfilter.addAction("android.intent.action.AIRPLANE_MODE");
            if(flag)
                intentfilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
            context.registerReceiver(this, intentfilter);
        }

        void unregister()
        {
            context.unregisterReceiver(this);
        }

        private static final String EXTRA_AIRPLANE_STATE = "state";
        private final ConnectivityManager connectivityManager;
        final Dispatcher this$0;

        NetworkBroadcastReceiver(Context context1)
        {
            this$0 = Dispatcher.this;
            super();
            connectivityManager = (ConnectivityManager)context1.getSystemService("connectivity");
        }
    }


    Dispatcher(Context context1, ExecutorService executorservice, Handler handler1, Downloader downloader1, Cache cache1, Stats stats1)
    {
        dispatcherThread.start();
        context = context1;
        service = executorservice;
        handler = new DispatcherHandler(dispatcherThread.getLooper(), this);
        downloader = downloader1;
        mainThreadHandler = handler1;
        cache = cache1;
        stats = stats1;
        airplaneMode = Utils.isAirplaneModeOn(context);
        receiver = new NetworkBroadcastReceiver(context);
        receiver.register();
    }

    private void batch(BitmapHunter bitmaphunter)
    {
        if(!bitmaphunter.isCancelled())
        {
            batch.add(bitmaphunter);
            if(!handler.hasMessages(7))
            {
                handler.sendEmptyMessageDelayed(7, 200L);
                return;
            }
        }
    }

    void dispatchAirplaneModeChange(boolean flag)
    {
        Handler handler1 = handler;
        Handler handler2 = handler;
        int i;
        if(flag)
            i = 1;
        else
            i = 0;
        handler1.sendMessage(handler2.obtainMessage(10, i, 0));
    }

    void dispatchCancel(Action action)
    {
        handler.sendMessage(handler.obtainMessage(2, action));
    }

    void dispatchComplete(BitmapHunter bitmaphunter)
    {
        handler.sendMessage(handler.obtainMessage(4, bitmaphunter));
    }

    void dispatchFailed(BitmapHunter bitmaphunter)
    {
        handler.sendMessage(handler.obtainMessage(6, bitmaphunter));
    }

    void dispatchNetworkStateChange(NetworkInfo networkinfo)
    {
        handler.sendMessage(handler.obtainMessage(9, networkinfo));
    }

    void dispatchRetry(BitmapHunter bitmaphunter)
    {
        handler.sendMessageDelayed(handler.obtainMessage(5, bitmaphunter), 500L);
    }

    void dispatchSubmit(Action action)
    {
        handler.sendMessage(handler.obtainMessage(1, action));
    }

    void performAirplaneModeChange(boolean flag)
    {
        airplaneMode = flag;
    }

    void performBatchComplete()
    {
        ArrayList arraylist = new ArrayList(batch);
        batch.clear();
        mainThreadHandler.sendMessage(mainThreadHandler.obtainMessage(8, arraylist));
    }

    void performCancel(Action action)
    {
        String s = action.getKey();
        BitmapHunter bitmaphunter = (BitmapHunter)hunterMap.get(s);
        if(bitmaphunter != null)
        {
            bitmaphunter.detach(action);
            if(bitmaphunter.cancel())
                hunterMap.remove(s);
        }
    }

    void performComplete(BitmapHunter bitmaphunter)
    {
        if(!bitmaphunter.shouldSkipMemoryCache())
            cache.set(bitmaphunter.getKey(), bitmaphunter.getResult());
        hunterMap.remove(bitmaphunter.getKey());
        batch(bitmaphunter);
    }

    void performError(BitmapHunter bitmaphunter)
    {
        hunterMap.remove(bitmaphunter.getKey());
        batch(bitmaphunter);
    }

    void performNetworkStateChange(NetworkInfo networkinfo)
    {
        networkInfo = networkinfo;
        if(service instanceof PicassoExecutorService)
            ((PicassoExecutorService)service).adjustThreadCount(networkinfo);
    }

    void performRetry(BitmapHunter bitmaphunter)
    {
        if(bitmaphunter.isCancelled())
            return;
        if(service.isShutdown())
        {
            performError(bitmaphunter);
            return;
        }
        if(bitmaphunter.shouldRetry(airplaneMode, networkInfo))
        {
            bitmaphunter.future = service.submit(bitmaphunter);
            return;
        } else
        {
            performError(bitmaphunter);
            return;
        }
    }

    void performSubmit(Action action)
    {
        BitmapHunter bitmaphunter = (BitmapHunter)hunterMap.get(action.getKey());
        if(bitmaphunter != null)
            bitmaphunter.attach(action);
        else
        if(!service.isShutdown())
        {
            BitmapHunter bitmaphunter1 = BitmapHunter.forRequest(context, action.getPicasso(), this, cache, stats, action, downloader);
            bitmaphunter1.future = service.submit(bitmaphunter1);
            hunterMap.put(action.getKey(), bitmaphunter1);
            return;
        }
    }

    void shutdown()
    {
        service.shutdown();
        dispatcherThread.quit();
        receiver.unregister();
    }

    static final int AIRPLANE_MODE_CHANGE = 10;
    private static final int AIRPLANE_MODE_OFF = 0;
    private static final int AIRPLANE_MODE_ON = 1;
    private static final int BATCH_DELAY = 200;
    private static final String DISPATCHER_THREAD_NAME = "Dispatcher";
    static final int HUNTER_BATCH_COMPLETE = 8;
    static final int HUNTER_COMPLETE = 4;
    static final int HUNTER_DECODE_FAILED = 6;
    static final int HUNTER_DELAY_NEXT_BATCH = 7;
    static final int HUNTER_RETRY = 5;
    static final int NETWORK_STATE_CHANGE = 9;
    static final int REQUEST_CANCEL = 2;
    static final int REQUEST_GCED = 3;
    static final int REQUEST_SUBMIT = 1;
    private static final int RETRY_DELAY = 500;
    boolean airplaneMode;
    final List batch = new ArrayList(4);
    final Cache cache;
    final Context context;
    final DispatcherThread dispatcherThread = new DispatcherThread();
    final Downloader downloader;
    final Handler handler;
    final Map hunterMap = new LinkedHashMap();
    final Handler mainThreadHandler;
    NetworkInfo networkInfo;
    final NetworkBroadcastReceiver receiver;
    final ExecutorService service;
    final Stats stats;
}
