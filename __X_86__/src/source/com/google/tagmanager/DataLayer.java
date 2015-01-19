// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.tagmanager;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;

public class DataLayer
{
    static interface Listener
    {

        public abstract void changed(Map map);
    }


    DataLayer()
    {
    }

    public static transient List listOf(Object aobj[])
    {
        ArrayList arraylist = new ArrayList();
        for(int i = 0; i < aobj.length; i++)
            arraylist.add(aobj[i]);

        return arraylist;
    }

    public static transient Map mapOf(Object aobj[])
    {
        if(aobj.length % 2 != 0)
            throw new IllegalArgumentException("expected even number of key-value pairs");
        HashMap hashmap = new HashMap();
        for(int i = 0; i < aobj.length; i += 2)
            hashmap.put(aobj[i], aobj[i + 1]);

        return hashmap;
    }

    private void notifyListeners(Map map)
    {
        for(Iterator iterator = mListeners.keySet().iterator(); iterator.hasNext(); ((Listener)iterator.next()).changed(map));
    }

    private void processQueuedUpdates()
    {
        int i = 0;
        do
        {
            Map map = (Map)mUpdateQueue.poll();
            if(map != null)
            {
                processUpdate(map);
                if(++i > 500)
                {
                    mUpdateQueue.clear();
                    throw new RuntimeException("Seems like an infinite loop of pushing to the data layer");
                }
            } else
            {
                return;
            }
        } while(true);
    }

    private void processUpdate(Map map)
    {
        Map map1 = mModel;
        map1;
        JVM INSTR monitorenter ;
        Object obj;
        for(Iterator iterator = map.keySet().iterator(); iterator.hasNext(); mergeMap(expandKeyValue(obj, map.get(obj)), mModel))
            obj = iterator.next();

        break MISSING_BLOCK_LABEL_69;
        Exception exception;
        exception;
        map1;
        JVM INSTR monitorexit ;
        throw exception;
        map1;
        JVM INSTR monitorexit ;
        notifyListeners(map);
        return;
    }

    Map expandKeyValue(Object obj, Object obj1)
    {
        HashMap hashmap = new HashMap();
        HashMap hashmap1 = hashmap;
        String as[] = obj.toString().split("\\.");
        for(int i = 0; i < -1 + as.length; i++)
        {
            HashMap hashmap2 = new HashMap();
            hashmap1.put(as[i], hashmap2);
            hashmap1 = hashmap2;
        }

        hashmap1.put(as[-1 + as.length], obj1);
        return hashmap;
    }

    public Object get(String s)
    {
        Map map = mModel;
        map;
        JVM INSTR monitorenter ;
        Object obj;
        String as[];
        int i;
        obj = mModel;
        as = s.split("\\.");
        i = as.length;
        int j = 0;
_L2:
        if(j >= i)
            break MISSING_BLOCK_LABEL_78;
        String s1;
        s1 = as[j];
        if(obj instanceof Map)
            break MISSING_BLOCK_LABEL_55;
        map;
        JVM INSTR monitorexit ;
        return null;
        Object obj1 = ((Map)obj).get(s1);
        if(obj1 != null)
            break MISSING_BLOCK_LABEL_88;
        map;
        JVM INSTR monitorexit ;
        return null;
        map;
        JVM INSTR monitorexit ;
        return obj;
        Exception exception;
        exception;
        map;
        JVM INSTR monitorexit ;
        throw exception;
        obj = obj1;
        j++;
        if(true) goto _L2; else goto _L1
_L1:
    }

    void mergeList(List list, List list1)
    {
        for(; list1.size() < list.size(); list1.add(null));
        int i = 0;
        while(i < list.size()) 
        {
            Object obj = list.get(i);
            if(obj instanceof List)
            {
                if(!(list1.get(i) instanceof List))
                    list1.set(i, new ArrayList());
                mergeList((List)obj, (List)list1.get(i));
            } else
            if(obj instanceof Map)
            {
                if(!(list1.get(i) instanceof Map))
                    list1.set(i, new HashMap());
                mergeMap((Map)obj, (Map)list1.get(i));
            } else
            if(obj != OBJECT_NOT_PRESENT)
                list1.set(i, obj);
            i++;
        }
    }

    void mergeMap(Map map, Map map1)
    {
        for(Iterator iterator = map.keySet().iterator(); iterator.hasNext();)
        {
            Object obj = iterator.next();
            Object obj1 = map.get(obj);
            if(obj1 instanceof List)
            {
                if(!(map1.get(obj) instanceof List))
                    map1.put(obj, new ArrayList());
                mergeList((List)obj1, (List)map1.get(obj));
            } else
            if(obj1 instanceof Map)
            {
                if(!(map1.get(obj) instanceof Map))
                    map1.put(obj, new HashMap());
                mergeMap((Map)obj1, (Map)map1.get(obj));
            } else
            {
                map1.put(obj, obj1);
            }
        }

    }

    public void push(Object obj, Object obj1)
    {
        push(expandKeyValue(obj, obj1));
    }

    public void push(Map map)
    {
        mPushLock.lock();
        mUpdateQueue.offer(map);
        if(mPushLock.getHoldCount() == 1)
            processQueuedUpdates();
        mPushLock.unlock();
        return;
        Exception exception;
        exception;
        mPushLock.unlock();
        throw exception;
    }

    void registerListener(Listener listener)
    {
        mListeners.put(listener, Integer.valueOf(0));
    }

    void unregisterListener(Listener listener)
    {
        mListeners.remove(listener);
    }

    static final int MAX_QUEUE_DEPTH = 500;
    public static final Object OBJECT_NOT_PRESENT = new Object();
    private final ConcurrentHashMap mListeners = new ConcurrentHashMap();
    private final Map mModel = new HashMap();
    private final ReentrantLock mPushLock = new ReentrantLock();
    private final LinkedList mUpdateQueue = new LinkedList();

}
