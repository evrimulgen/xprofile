// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.squareup.picasso;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.*;
import android.widget.ImageView;
import java.io.File;
import java.lang.ref.ReferenceQueue;
import java.util.*;
import java.util.concurrent.ExecutorService;

// Referenced classes of package com.squareup.picasso:
//            Action, Dispatcher, DeferredRequestCreator, BitmapHunter, 
//            Request, Stats, RequestCreator, Cache, 
//            Target, StatsSnapshot, Utils, LruCache, 
//            PicassoExecutorService, Downloader

public class Picasso
{
    public static class Builder
    {

        public Picasso build()
        {
            Context context1 = context;
            if(downloader == null)
                downloader = Utils.createDefaultDownloader(context1);
            if(cache == null)
                cache = new LruCache(context1);
            if(service == null)
                service = new PicassoExecutorService();
            if(transformer == null)
                transformer = RequestTransformer.IDENTITY;
            Stats stats1 = new Stats(cache);
            return new Picasso(context1, new Dispatcher(context1, service, Picasso.HANDLER, downloader, cache, stats1), cache, listener, transformer, stats1, debugging);
        }

        public Builder debugging(boolean flag)
        {
            debugging = flag;
            return this;
        }

        public Builder downloader(Downloader downloader1)
        {
            if(downloader1 == null)
                throw new IllegalArgumentException("Downloader must not be null.");
            if(downloader != null)
            {
                throw new IllegalStateException("Downloader already set.");
            } else
            {
                downloader = downloader1;
                return this;
            }
        }

        public Builder executor(ExecutorService executorservice)
        {
            if(executorservice == null)
                throw new IllegalArgumentException("Executor service must not be null.");
            if(service != null)
            {
                throw new IllegalStateException("Executor service already set.");
            } else
            {
                service = executorservice;
                return this;
            }
        }

        public Builder listener(Listener listener1)
        {
            if(listener1 == null)
                throw new IllegalArgumentException("Listener must not be null.");
            if(listener != null)
            {
                throw new IllegalStateException("Listener already set.");
            } else
            {
                listener = listener1;
                return this;
            }
        }

        public Builder memoryCache(Cache cache1)
        {
            if(cache1 == null)
                throw new IllegalArgumentException("Memory cache must not be null.");
            if(cache != null)
            {
                throw new IllegalStateException("Memory cache already set.");
            } else
            {
                cache = cache1;
                return this;
            }
        }

        public Builder requestTransformer(RequestTransformer requesttransformer)
        {
            if(requesttransformer == null)
                throw new IllegalArgumentException("Transformer must not be null.");
            if(transformer != null)
            {
                throw new IllegalStateException("Transformer already set.");
            } else
            {
                transformer = requesttransformer;
                return this;
            }
        }

        private Cache cache;
        private final Context context;
        private boolean debugging;
        private Downloader downloader;
        private Listener listener;
        private ExecutorService service;
        private RequestTransformer transformer;

        public Builder(Context context1)
        {
            if(context1 == null)
            {
                throw new IllegalArgumentException("Context must not be null.");
            } else
            {
                context = context1.getApplicationContext();
                return;
            }
        }
    }

    private static class CleanupThread extends Thread
    {

        public void run()
        {
            Process.setThreadPriority(10);
            try
            {
                do
                {
                    Action.RequestWeakReference requestweakreference = (Action.RequestWeakReference)referenceQueue.remove();
                    handler.sendMessage(handler.obtainMessage(3, requestweakreference.action));
                } while(true);
            }
            catch(InterruptedException interruptedexception)
            {
                return;
            }
            catch(Exception exception)
            {
                handler.post(exception. new Runnable() {

                    public void run()
                    {
                        throw new RuntimeException(e);
                    }

                    final CleanupThread this$0;
                    final Exception val$e;

            
            {
                this$0 = final_cleanupthread;
                e = Exception.this;
                super();
            }
                }
);
            }
        }

        void shutdown()
        {
            interrupt();
        }

        private final Handler handler;
        private final ReferenceQueue referenceQueue;

        CleanupThread(ReferenceQueue referencequeue, Handler handler1)
        {
            referenceQueue = referencequeue;
            handler = handler1;
            setDaemon(true);
            setName("Picasso-refQueue");
        }
    }

    public static interface Listener
    {

        public abstract void onImageLoadFailed(Picasso picasso, Uri uri, Exception exception);
    }

    public static final class LoadedFrom extends Enum
    {

        public static LoadedFrom valueOf(String s)
        {
            return (LoadedFrom)Enum.valueOf(com/squareup/picasso/Picasso$LoadedFrom, s);
        }

        public static LoadedFrom[] values()
        {
            return (LoadedFrom[])$VALUES.clone();
        }

        private static final LoadedFrom $VALUES[];
        public static final LoadedFrom DISK;
        public static final LoadedFrom MEMORY;
        public static final LoadedFrom NETWORK;
        final int debugColor;

        static 
        {
            MEMORY = new LoadedFrom("MEMORY", 0, 0xff00ff00);
            DISK = new LoadedFrom("DISK", 1, -256);
            NETWORK = new LoadedFrom("NETWORK", 2, 0xffff0000);
            LoadedFrom aloadedfrom[] = new LoadedFrom[3];
            aloadedfrom[0] = MEMORY;
            aloadedfrom[1] = DISK;
            aloadedfrom[2] = NETWORK;
            $VALUES = aloadedfrom;
        }

        private LoadedFrom(String s, int i, int j)
        {
            super(s, i);
            debugColor = j;
        }
    }

    public static interface RequestTransformer
    {

        public abstract Request transformRequest(Request request);

        public static final RequestTransformer IDENTITY = new RequestTransformer() {

            public Request transformRequest(Request request)
            {
                return request;
            }

        }
;

    }


    Picasso(Context context1, Dispatcher dispatcher1, Cache cache1, Listener listener1, RequestTransformer requesttransformer, Stats stats1, boolean flag)
    {
        context = context1;
        dispatcher = dispatcher1;
        cache = cache1;
        listener = listener1;
        requestTransformer = requesttransformer;
        stats = stats1;
        debugging = flag;
        cleanupThread = new CleanupThread(referenceQueue, HANDLER);
        cleanupThread.start();
    }

    private void cancelExistingRequest(Object obj)
    {
        Action action = (Action)targetToAction.remove(obj);
        if(action != null)
        {
            action.cancel();
            dispatcher.dispatchCancel(action);
        }
        if(obj instanceof ImageView)
        {
            ImageView imageview = (ImageView)obj;
            DeferredRequestCreator deferredrequestcreator = (DeferredRequestCreator)targetToDeferredRequestCreator.remove(imageview);
            if(deferredrequestcreator != null)
                deferredrequestcreator.cancel();
        }
    }

    public static Picasso with(Context context1)
    {
        if(singleton == null)
            singleton = (new Builder(context1)).build();
        return singleton;
    }

    public void cancelRequest(ImageView imageview)
    {
        cancelExistingRequest(imageview);
    }

    public void cancelRequest(Target target)
    {
        cancelExistingRequest(target);
    }

    void complete(BitmapHunter bitmaphunter)
    {
        List list = bitmaphunter.getActions();
        if(!list.isEmpty())
        {
            Uri uri = bitmaphunter.getData().uri;
            Exception exception = bitmaphunter.getException();
            Bitmap bitmap = bitmaphunter.getResult();
            LoadedFrom loadedfrom = bitmaphunter.getLoadedFrom();
            Iterator iterator = list.iterator();
            do
            {
                if(!iterator.hasNext())
                    break;
                Action action = (Action)iterator.next();
                if(!action.isCancelled())
                {
                    targetToAction.remove(action.getTarget());
                    if(bitmap != null)
                    {
                        if(loadedfrom == null)
                            throw new AssertionError("LoadedFrom cannot be null.");
                        action.complete(bitmap, loadedfrom);
                    } else
                    {
                        action.error();
                    }
                }
            } while(true);
            if(listener != null && exception != null)
            {
                listener.onImageLoadFailed(this, uri, exception);
                return;
            }
        }
    }

    void defer(ImageView imageview, DeferredRequestCreator deferredrequestcreator)
    {
        targetToDeferredRequestCreator.put(imageview, deferredrequestcreator);
    }

    void enqueueAndSubmit(Action action)
    {
        Object obj = action.getTarget();
        if(obj != null)
        {
            cancelExistingRequest(obj);
            targetToAction.put(obj, action);
        }
        submit(action);
    }

    public StatsSnapshot getSnapshot()
    {
        return stats.createSnapshot();
    }

    public boolean isDebugging()
    {
        return debugging;
    }

    public RequestCreator load(int i)
    {
        if(i == 0)
            throw new IllegalArgumentException("Resource ID must not be zero.");
        else
            return new RequestCreator(this, null, i);
    }

    public RequestCreator load(Uri uri)
    {
        return new RequestCreator(this, uri, 0);
    }

    public RequestCreator load(File file)
    {
        if(file == null)
            return new RequestCreator(this, null, 0);
        else
            return load(Uri.fromFile(file));
    }

    public RequestCreator load(String s)
    {
        if(s == null)
            return new RequestCreator(this, null, 0);
        if(s.trim().length() == 0)
            throw new IllegalArgumentException("Path must not be empty.");
        else
            return load(Uri.parse(s));
    }

    Bitmap quickMemoryCacheCheck(String s)
    {
        Bitmap bitmap = cache.get(s);
        if(bitmap != null)
        {
            stats.dispatchCacheHit();
            return bitmap;
        } else
        {
            stats.dispatchCacheMiss();
            return bitmap;
        }
    }

    public void setDebugging(boolean flag)
    {
        debugging = flag;
    }

    public void shutdown()
    {
        if(this == singleton)
            throw new UnsupportedOperationException("Default singleton instance cannot be shutdown.");
        if(shutdown)
            return;
        cache.clear();
        cleanupThread.shutdown();
        stats.shutdown();
        dispatcher.shutdown();
        for(Iterator iterator = targetToDeferredRequestCreator.values().iterator(); iterator.hasNext(); ((DeferredRequestCreator)iterator.next()).cancel());
        targetToDeferredRequestCreator.clear();
        shutdown = true;
    }

    void submit(Action action)
    {
        dispatcher.dispatchSubmit(action);
    }

    Request transformRequest(Request request)
    {
        Request request1 = requestTransformer.transformRequest(request);
        if(request1 == null)
            throw new IllegalStateException((new StringBuilder()).append("Request transformer ").append(requestTransformer.getClass().getCanonicalName()).append(" returned null for ").append(request).toString());
        else
            return request1;
    }

    static final Handler HANDLER = new Handler(Looper.getMainLooper()) {

        public void handleMessage(Message message)
        {
            switch(message.what)
            {
            default:
                throw new AssertionError((new StringBuilder()).append("Unknown handler message received: ").append(message.what).toString());

            case 8: // '\b'
                BitmapHunter bitmaphunter;
                for(Iterator iterator = ((List)message.obj).iterator(); iterator.hasNext(); bitmaphunter.picasso.complete(bitmaphunter))
                    bitmaphunter = (BitmapHunter)iterator.next();

                break;

            case 3: // '\003'
                Action action = (Action)message.obj;
                action.picasso.cancelExistingRequest(action.getTarget());
                break;
            }
        }

    }
;
    static Picasso singleton = null;
    final Cache cache;
    private final CleanupThread cleanupThread;
    final Context context;
    boolean debugging;
    final Dispatcher dispatcher;
    private final Listener listener;
    final ReferenceQueue referenceQueue = new ReferenceQueue();
    private final RequestTransformer requestTransformer;
    boolean shutdown;
    final Stats stats;
    final Map targetToAction = new WeakHashMap();
    final Map targetToDeferredRequestCreator = new WeakHashMap();


}
