// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.maps.android.clustering.algo;

import android.support.v4.util.LruCache;
import com.google.maps.android.clustering.ClusterItem;
import java.util.Collection;
import java.util.Set;
import java.util.concurrent.locks.*;

// Referenced classes of package com.google.maps.android.clustering.algo:
//            Algorithm

public class PreCachingAlgorithmDecorator
    implements Algorithm
{
    private class PrecacheRunnable
        implements Runnable
    {

        public void run()
        {
            try
            {
                Thread.sleep((long)(500D + 500D * Math.random()));
            }
            catch(InterruptedException interruptedexception) { }
            getClustersInternal(mZoom);
        }

        private final int mZoom;
        final PreCachingAlgorithmDecorator this$0;

        public PrecacheRunnable(int i)
        {
            this$0 = PreCachingAlgorithmDecorator.this;
            super();
            mZoom = i;
        }
    }


    public PreCachingAlgorithmDecorator(Algorithm algorithm)
    {
        mAlgorithm = algorithm;
    }

    private void clearCache()
    {
        mCache.evictAll();
    }

    private Set getClustersInternal(int i)
    {
        mCacheLock.readLock().lock();
        Set set = (Set)mCache.get(Integer.valueOf(i));
        mCacheLock.readLock().unlock();
        if(set == null)
        {
            mCacheLock.writeLock().lock();
            set = (Set)mCache.get(Integer.valueOf(i));
            if(set == null)
            {
                set = mAlgorithm.getClusters(i);
                mCache.put(Integer.valueOf(i), set);
            }
            mCacheLock.writeLock().unlock();
        }
        return set;
    }

    public void addItem(ClusterItem clusteritem)
    {
        mAlgorithm.addItem(clusteritem);
        clearCache();
    }

    public void addItems(Collection collection)
    {
        mAlgorithm.addItems(collection);
        clearCache();
    }

    public void clearItems()
    {
        mAlgorithm.clearItems();
        clearCache();
    }

    public Set getClusters(double d)
    {
        int i = (int)d;
        Set set = getClustersInternal(i);
        if(mCache.get(Integer.valueOf(i + 1)) == null)
            (new Thread(new PrecacheRunnable(i + 1))).start();
        if(mCache.get(Integer.valueOf(i - 1)) == null)
            (new Thread(new PrecacheRunnable(i - 1))).start();
        return set;
    }

    public Collection getItems()
    {
        return mAlgorithm.getItems();
    }

    public void removeItem(ClusterItem clusteritem)
    {
        mAlgorithm.removeItem(clusteritem);
        clearCache();
    }

    private final Algorithm mAlgorithm;
    private final LruCache mCache = new LruCache(5);
    private final ReadWriteLock mCacheLock = new ReentrantReadWriteLock();

}
