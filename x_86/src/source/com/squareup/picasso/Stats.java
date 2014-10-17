// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.squareup.picasso;

import android.graphics.Bitmap;
import android.os.*;

// Referenced classes of package com.squareup.picasso:
//            Utils, StatsSnapshot, Cache, Picasso

class Stats
{
    private static class StatsHandler extends Handler
    {

        public void handleMessage(Message message)
        {
            switch(message.what)
            {
            default:
                Picasso.HANDLER.post(message. new Runnable() {

                    public void run()
                    {
                        throw new AssertionError((new StringBuilder()).append("Unhandled stats message.").append(msg.what).toString());
                    }

                    final StatsHandler this$0;
                    final Message val$msg;

            
            {
                this$0 = final_statshandler;
                msg = Message.this;
                super();
            }
                }
);
                return;

            case 0: // '\0'
                stats.performCacheHit();
                return;

            case 1: // '\001'
                stats.performCacheMiss();
                return;

            case 2: // '\002'
                stats.performBitmapDecoded(message.arg1);
                return;

            case 3: // '\003'
                stats.performBitmapTransformed(message.arg1);
                break;
            }
        }

        private final Stats stats;

        public StatsHandler(Looper looper, Stats stats1)
        {
            super(looper);
            stats = stats1;
        }
    }


    Stats(Cache cache1)
    {
        cache = cache1;
        statsThread.start();
        handler = new StatsHandler(statsThread.getLooper(), this);
    }

    private static long getAverage(int i, long l)
    {
        return l / (long)i;
    }

    private void processBitmap(Bitmap bitmap, int i)
    {
        int j = Utils.getBitmapBytes(bitmap);
        handler.sendMessage(handler.obtainMessage(i, j, 0));
    }

    StatsSnapshot createSnapshot()
    {
        this;
        JVM INSTR monitorenter ;
        StatsSnapshot statssnapshot = new StatsSnapshot(cache.maxSize(), cache.size(), cacheHits, cacheMisses, totalOriginalBitmapSize, totalTransformedBitmapSize, averageOriginalBitmapSize, averageTransformedBitmapSize, originalBitmapCount, transformedBitmapCount, System.currentTimeMillis());
        this;
        JVM INSTR monitorexit ;
        return statssnapshot;
        Exception exception;
        exception;
        throw exception;
    }

    void dispatchBitmapDecoded(Bitmap bitmap)
    {
        processBitmap(bitmap, 2);
    }

    void dispatchBitmapTransformed(Bitmap bitmap)
    {
        processBitmap(bitmap, 3);
    }

    void dispatchCacheHit()
    {
        handler.sendEmptyMessage(0);
    }

    void dispatchCacheMiss()
    {
        handler.sendEmptyMessage(1);
    }

    void performBitmapDecoded(long l)
    {
        originalBitmapCount = 1 + originalBitmapCount;
        totalOriginalBitmapSize = l + totalOriginalBitmapSize;
        averageOriginalBitmapSize = getAverage(originalBitmapCount, totalOriginalBitmapSize);
    }

    void performBitmapTransformed(long l)
    {
        transformedBitmapCount = 1 + transformedBitmapCount;
        totalTransformedBitmapSize = l + totalTransformedBitmapSize;
        averageTransformedBitmapSize = getAverage(originalBitmapCount, totalTransformedBitmapSize);
    }

    void performCacheHit()
    {
        cacheHits = 1L + cacheHits;
    }

    void performCacheMiss()
    {
        cacheMisses = 1L + cacheMisses;
    }

    void shutdown()
    {
        statsThread.quit();
    }

    private static final int BITMAP_DECODE_FINISHED = 2;
    private static final int BITMAP_TRANSFORMED_FINISHED = 3;
    private static final int CACHE_HIT = 0;
    private static final int CACHE_MISS = 1;
    private static final String STATS_THREAD_NAME = "Picasso-Stats";
    long averageOriginalBitmapSize;
    long averageTransformedBitmapSize;
    final Cache cache;
    long cacheHits;
    long cacheMisses;
    final Handler handler;
    int originalBitmapCount;
    final HandlerThread statsThread = new HandlerThread("Picasso-Stats", 10);
    long totalOriginalBitmapSize;
    long totalTransformedBitmapSize;
    int transformedBitmapCount;
}
