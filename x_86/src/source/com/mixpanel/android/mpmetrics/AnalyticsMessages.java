// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.mixpanel.android.mpmetrics;

import android.content.Context;
import android.os.*;
import android.util.Log;
import com.newrelic.agent.android.instrumentation.JSONObjectInstrumentation;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import org.json.JSONObject;

// Referenced classes of package com.mixpanel.android.mpmetrics:
//            HttpPoster, MPDbAdapter

class AnalyticsMessages
{
    private class Worker
    {

        private Handler restartWorkerThread()
        {
            SynchronousQueue synchronousqueue = new SynchronousQueue();
            Thread thread = synchronousqueue. new Thread() {

                public void run()
                {
                    Looper.prepare();
                    try
                    {
                        handlerQueue.put(new Worker.AnalyticsMessageHandler());
                    }
                    catch(InterruptedException interruptedexception)
                    {
                        throw new RuntimeException("Couldn't build worker thread for Analytics Messages", interruptedexception);
                    }
                    try
                    {
                        Looper.loop();
                        return;
                    }
                    catch(RuntimeException runtimeexception)
                    {
                        Log.e("MixpanelAPI", "Mixpanel Thread dying from RuntimeException", runtimeexception);
                    }
                }

                final Worker this$1;
                final SynchronousQueue val$handlerQueue;

            
            {
                this$1 = final_worker;
                handlerQueue = SynchronousQueue.this;
                super();
            }
            }
;
            thread.setPriority(1);
            thread.start();
            Handler handler;
            try
            {
                handler = (Handler)synchronousqueue.take();
            }
            catch(InterruptedException interruptedexception)
            {
                throw new RuntimeException("Couldn't retrieve handler from worker thread");
            }
            return handler;
        }

        private void updateFlushFrequency()
        {
            long l = System.currentTimeMillis();
            long l1 = 1L + mFlushCount;
            if(mLastFlushTime > 0L)
            {
                mAveFlushFrequency = ((l - mLastFlushTime) + mAveFlushFrequency * mFlushCount) / l1;
                long l2 = mAveFlushFrequency / 1000L;
                logAboutMessageToMixpanel((new StringBuilder()).append("Average send frequency approximately ").append(l2).append(" seconds.").toString());
            }
            mLastFlushTime = l;
            mFlushCount = l1;
        }

        public boolean isDead()
        {
            Object obj = mHandlerLock;
            obj;
            JVM INSTR monitorenter ;
            Exception exception;
            boolean flag;
            if(mHandler == null)
                flag = true;
            else
                flag = false;
            obj;
            JVM INSTR monitorexit ;
            return flag;
            exception;
            obj;
            JVM INSTR monitorexit ;
            throw exception;
        }

        public void runMessage(Message message)
        {
            if(isDead())
            {
                logAboutMessageToMixpanel((new StringBuilder()).append("Dead mixpanel worker dropping a message: ").append(message).toString());
                return;
            }
            synchronized(mHandlerLock)
            {
                if(mHandler != null)
                    mHandler.sendMessage(message);
            }
            return;
            exception;
            obj;
            JVM INSTR monitorexit ;
            throw exception;
        }

        private long mAveFlushFrequency;
        private long mFlushCount;
        private long mFlushInterval;
        private Handler mHandler;
        private final Object mHandlerLock = new Object();
        private long mLastFlushTime;
        final AnalyticsMessages this$0;



/*
        static Handler access$1202(Worker worker, Handler handler)
        {
            worker.mHandler = handler;
            return handler;
        }

*/



/*
        static long access$302(Worker worker, long l)
        {
            worker.mFlushInterval = l;
            return l;
        }

*/


        public Worker()
        {
            this$0 = AnalyticsMessages.this;
            super();
            mFlushInterval = 60000L;
            mFlushCount = 0L;
            mAveFlushFrequency = 0L;
            mLastFlushTime = -1L;
            mHandler = restartWorkerThread();
        }
    }

    private class Worker.AnalyticsMessageHandler extends Handler
    {

        private void sendAllData()
        {
            logAboutMessageToMixpanel("Sending records to Mixpanel");
            sendData(MPDbAdapter.Table.EVENTS, "/track?ip=1");
            sendData(MPDbAdapter.Table.PEOPLE, "/engage");
        }

        private void sendData(MPDbAdapter.Table table, String s)
        {
            String as[] = mDbAdapter.generateDataString(table);
            if(as != null)
            {
                String s1 = as[0];
                String s2 = as[1];
                HttpPoster.PostResult postresult = getPoster(mEndpointHost, mFallbackHost).postData(s2, s);
                if(postresult == HttpPoster.PostResult.SUCCEEDED)
                {
                    logAboutMessageToMixpanel((new StringBuilder()).append("Posted to ").append(s).toString());
                    logAboutMessageToMixpanel((new StringBuilder()).append("Sent Message\n").append(s2).toString());
                    mDbAdapter.cleanupEvents(s1, table);
                } else
                if(postresult == HttpPoster.PostResult.FAILED_RECOVERABLE)
                {
                    if(!hasMessages(AnalyticsMessages.FLUSH_QUEUE))
                    {
                        sendEmptyMessageDelayed(AnalyticsMessages.FLUSH_QUEUE, mFlushInterval);
                        return;
                    }
                } else
                {
                    mDbAdapter.cleanupEvents(s1, table);
                    return;
                }
            }
        }

        public void handleMessage(Message message)
        {
            int i = -1;
            if(message.what != AnalyticsMessages.SET_FLUSH_INTERVAL) goto _L2; else goto _L1
_L1:
            Long long1 = (Long)message.obj;
            logAboutMessageToMixpanel((new StringBuilder()).append("Changing flush interval to ").append(long1).toString());
            mFlushInterval = long1.longValue();
            removeMessages(AnalyticsMessages.FLUSH_QUEUE);
_L18:
            if(i < 40) goto _L4; else goto _L3
_L3:
            logAboutMessageToMixpanel("Flushing queue due to bulk upload limit");
            updateFlushFrequency();
            sendAllData();
            return;
_L2:
            if(message.what != AnalyticsMessages.SET_ENDPOINT_HOST) goto _L6; else goto _L5
_L5:
            Object obj3;
            logAboutMessageToMixpanel((new StringBuilder()).append("Setting endpoint API host to ").append(mEndpointHost).toString());
            obj3 = message.obj;
            String s3 = null;
            if(obj3 != null) goto _L8; else goto _L7
_L7:
            mEndpointHost = s3;
            continue; /* Loop/switch isn't completed */
            RuntimeException runtimeexception;
            runtimeexception;
            Log.e("MixpanelAPI", "Worker threw an unhandled exception- will not send any more mixpanel messages", runtimeexception);
            Object obj = mHandlerLock;
            obj;
            JVM INSTR monitorenter ;
            mHandler = null;
            Looper.myLooper().quit();
_L16:
            throw runtimeexception;
_L8:
            s3 = message.obj.toString();
              goto _L7
_L6:
            Object obj2;
            if(message.what != AnalyticsMessages.SET_FALLBACK_HOST)
                break MISSING_BLOCK_LABEL_297;
            logAboutMessageToMixpanel((new StringBuilder()).append("Setting fallback API host to ").append(mFallbackHost).toString());
            obj2 = message.obj;
            String s2;
            s2 = null;
            if(obj2 != null)
                break MISSING_BLOCK_LABEL_285;
_L9:
            mFallbackHost = s2;
            continue; /* Loop/switch isn't completed */
            s2 = message.obj.toString();
              goto _L9
            JSONObject jsonobject1;
            AnalyticsMessages analyticsmessages1;
            StringBuilder stringbuilder1;
            String s1;
            if(message.what != AnalyticsMessages.ENQUEUE_PEOPLE)
                break MISSING_BLOCK_LABEL_410;
            jsonobject1 = (JSONObject)message.obj;
            logAboutMessageToMixpanel("Queuing people record for sending later");
            analyticsmessages1 = _fld0;
            stringbuilder1 = (new StringBuilder()).append("    ");
            if(jsonobject1 instanceof JSONObject)
                break MISSING_BLOCK_LABEL_397;
            s1 = jsonobject1.toString();
_L10:
            analyticsmessages1.logAboutMessageToMixpanel(stringbuilder1.append(s1).toString());
            i = mDbAdapter.addJSON(jsonobject1, MPDbAdapter.Table.PEOPLE);
            continue; /* Loop/switch isn't completed */
            s1 = JSONObjectInstrumentation.toString((JSONObject)jsonobject1);
              goto _L10
            if(message.what != AnalyticsMessages.ENQUEUE_EVENTS) goto _L12; else goto _L11
_L11:
            JSONObject jsonobject;
            AnalyticsMessages analyticsmessages;
            StringBuilder stringbuilder;
            jsonobject = (JSONObject)message.obj;
            logAboutMessageToMixpanel("Queuing event for sending later");
            analyticsmessages = _fld0;
            stringbuilder = (new StringBuilder()).append("    ");
            if(jsonobject instanceof JSONObject) goto _L14; else goto _L13
_L13:
            String s = jsonobject.toString();
_L15:
            analyticsmessages.logAboutMessageToMixpanel(stringbuilder.append(s).toString());
            i = mDbAdapter.addJSON(jsonobject, MPDbAdapter.Table.EVENTS);
            continue; /* Loop/switch isn't completed */
_L14:
            s = JSONObjectInstrumentation.toString((JSONObject)jsonobject);
            if(true) goto _L15; else goto _L12
_L12:
            if(message.what == AnalyticsMessages.FLUSH_QUEUE)
            {
                logAboutMessageToMixpanel("Flushing queue due to scheduled or forced flush");
                updateFlushFrequency();
                sendAllData();
                continue; /* Loop/switch isn't completed */
            }
            if(message.what != AnalyticsMessages.KILL_WORKER)
                break MISSING_BLOCK_LABEL_647;
            Log.w("MixpanelAPI", (new StringBuilder()).append("Worker recieved a hard kill. Dumping all events and force-killing. Thread id ").append(Thread.currentThread().getId()).toString());
            synchronized(mHandlerLock)
            {
                mDbAdapter.deleteDB();
                mHandler = null;
                Looper.myLooper().quit();
            }
            continue; /* Loop/switch isn't completed */
            exception2;
            obj1;
            JVM INSTR monitorexit ;
            throw exception2;
            Log.e("MixpanelAPI", (new StringBuilder()).append("Unexpected message recieved by Mixpanel worker: ").append(message).toString());
            continue; /* Loop/switch isn't completed */
_L4:
            if(i <= 0)
                break MISSING_BLOCK_LABEL_776;
            if(!hasMessages(AnalyticsMessages.FLUSH_QUEUE))
            {
                logAboutMessageToMixpanel((new StringBuilder()).append("Queue depth ").append(i).append(" - Adding flush in ").append(mFlushInterval).toString());
                sendEmptyMessageDelayed(AnalyticsMessages.FLUSH_QUEUE, mFlushInterval);
                return;
            }
            break MISSING_BLOCK_LABEL_776;
            Exception exception1;
            exception1;
            Log.e("MixpanelAPI", "Could not halt looper", exception1);
              goto _L16
            Exception exception;
            exception;
            obj;
            JVM INSTR monitorexit ;
            throw exception;
            return;
            if(true) goto _L18; else goto _L17
_L17:
        }

        private final MPDbAdapter mDbAdapter;
        private String mEndpointHost;
        private String mFallbackHost;
        final Worker this$1;

        public Worker.AnalyticsMessageHandler()
        {
            this$1 = Worker.this;
            super();
            mEndpointHost = "https://api.mixpanel.com";
            mFallbackHost = "http://api.mixpanel.com";
            mDbAdapter = makeDbAdapter(mContext);
            mDbAdapter.cleanupEvents(System.currentTimeMillis() - 0xa4cb800L, MPDbAdapter.Table.EVENTS);
            mDbAdapter.cleanupEvents(System.currentTimeMillis() - 0xa4cb800L, MPDbAdapter.Table.PEOPLE);
        }
    }


    AnalyticsMessages(Context context)
    {
        mContext = context;
    }

    public static AnalyticsMessages getInstance(Context context)
    {
        Map map = sInstances;
        map;
        JVM INSTR monitorenter ;
        Context context1;
        AnalyticsMessages analyticsmessages;
        context1 = context.getApplicationContext();
        if(sInstances.containsKey(context1))
            break MISSING_BLOCK_LABEL_50;
        analyticsmessages = new AnalyticsMessages(context1);
        sInstances.put(context1, analyticsmessages);
_L2:
        return analyticsmessages;
        analyticsmessages = (AnalyticsMessages)sInstances.get(context1);
        if(true) goto _L2; else goto _L1
_L1:
        Exception exception;
        exception;
        map;
        JVM INSTR monitorexit ;
        throw exception;
    }

    private void logAboutMessageToMixpanel(String s)
    {
        if(!mLogMixpanelMessages.get())
        {
            return;
        } else
        {
            Log.i("MixpanelAPI", (new StringBuilder()).append(s).append(" (Thread ").append(Thread.currentThread().getId()).append(")").toString());
            return;
        }
    }

    public void eventsMessage(JSONObject jsonobject)
    {
        Message message = Message.obtain();
        message.what = ENQUEUE_EVENTS;
        message.obj = jsonobject;
        mWorker.runMessage(message);
    }

    protected HttpPoster getPoster(String s, String s1)
    {
        return new HttpPoster(s, s1);
    }

    public void hardKill()
    {
        Message message = Message.obtain();
        message.what = KILL_WORKER;
        mWorker.runMessage(message);
    }

    boolean isDead()
    {
        return mWorker.isDead();
    }

    public void logPosts()
    {
        mLogMixpanelMessages.set(true);
    }

    protected MPDbAdapter makeDbAdapter(Context context)
    {
        return new MPDbAdapter(context);
    }

    public void peopleMessage(JSONObject jsonobject)
    {
        Message message = Message.obtain();
        message.what = ENQUEUE_PEOPLE;
        message.obj = jsonobject;
        mWorker.runMessage(message);
    }

    public void postToServer()
    {
        Message message = Message.obtain();
        message.what = FLUSH_QUEUE;
        mWorker.runMessage(message);
    }

    public void setEndpointHost(String s)
    {
        Message message = Message.obtain();
        message.what = SET_ENDPOINT_HOST;
        message.obj = s;
        mWorker.runMessage(message);
    }

    public void setFallbackHost(String s)
    {
        Message message = Message.obtain();
        message.what = SET_FALLBACK_HOST;
        message.obj = s;
        mWorker.runMessage(message);
    }

    public void setFlushInterval(long l)
    {
        Message message = Message.obtain();
        message.what = SET_FLUSH_INTERVAL;
        message.obj = new Long(l);
        mWorker.runMessage(message);
    }

    private static int ENQUEUE_EVENTS = 0;
    private static int ENQUEUE_PEOPLE = 0;
    private static int FLUSH_QUEUE = 0;
    private static int KILL_WORKER = 0;
    private static final String LOGTAG = "MixpanelAPI";
    private static int SET_ENDPOINT_HOST = 6;
    private static int SET_FALLBACK_HOST = 7;
    private static int SET_FLUSH_INTERVAL = 4;
    private static final Map sInstances = new HashMap();
    private final Context mContext;
    private final AtomicBoolean mLogMixpanelMessages = new AtomicBoolean(false);
    private final Worker mWorker = new Worker();

    static 
    {
        ENQUEUE_PEOPLE = 0;
        ENQUEUE_EVENTS = 1;
        FLUSH_QUEUE = 2;
        KILL_WORKER = 5;
    }









}
