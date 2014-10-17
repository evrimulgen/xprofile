// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package dagger.internal;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.locks.*;

abstract class Memoizer
{

    public Memoizer()
    {
        ReentrantReadWriteLock reentrantreadwritelock = new ReentrantReadWriteLock();
        readLock = reentrantreadwritelock.readLock();
        writeLock = reentrantreadwritelock.writeLock();
    }

    protected abstract Object create(Object obj);

    public final Object get(Object obj)
    {
        if(obj == null)
            throw new NullPointerException("key == null");
        readLock.lock();
        Object obj1 = map.get(obj);
        Object obj2;
        if(obj1 != null)
        {
            readLock.unlock();
            return obj1;
        }
        readLock.unlock();
        obj2 = create(obj);
        if(obj2 == null)
            throw new NullPointerException("create returned null");
        break MISSING_BLOCK_LABEL_92;
        Exception exception;
        exception;
        readLock.unlock();
        throw exception;
        writeLock.lock();
        map.put(obj, obj2);
        writeLock.unlock();
        return obj2;
        Exception exception1;
        exception1;
        writeLock.unlock();
        throw exception1;
    }

    public final String toString()
    {
        readLock.lock();
        String s = map.toString();
        readLock.unlock();
        return s;
        Exception exception;
        exception;
        readLock.unlock();
        throw exception;
    }

    private final Map map = new LinkedHashMap();
    private final Lock readLock;
    private final Lock writeLock;
}
