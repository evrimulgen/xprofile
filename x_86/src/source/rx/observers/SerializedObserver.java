// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package rx.observers;

import rx.Observer;

public class SerializedObserver
    implements Observer
{
    private static final class ErrorSentinel
    {

        final Throwable e;

        ErrorSentinel(Throwable throwable)
        {
            e = throwable;
        }
    }

    static final class FastList
    {

        public void add(Object obj)
        {
            int i;
            Object aobj[];
            i = size;
            aobj = array;
            if(aobj != null) goto _L2; else goto _L1
_L1:
            aobj = new Object[16];
            array = aobj;
_L4:
            aobj[i] = obj;
            size = i + 1;
            return;
_L2:
            if(i == aobj.length)
            {
                Object aobj1[] = new Object[i + (i >> 2)];
                System.arraycopy(((Object) (aobj)), 0, ((Object) (aobj1)), 0, i);
                aobj = aobj1;
                array = aobj;
            }
            if(true) goto _L4; else goto _L3
_L3:
        }

        Object array[];
        int size;

        FastList()
        {
        }
    }


    public SerializedObserver(Observer observer)
    {
        emitting = false;
        terminated = false;
        actual = observer;
    }

    void drainQueue(FastList fastlist)
    {
        if(fastlist != null && fastlist.size != 0) goto _L2; else goto _L1
_L1:
        return;
_L2:
        Object aobj[];
        int i;
        int j;
        aobj = fastlist.array;
        i = aobj.length;
        j = 0;
_L5:
        if(j >= i) goto _L1; else goto _L3
_L3:
        Object obj = aobj[j];
        if(obj == null) goto _L1; else goto _L4
_L4:
        if(obj == NULL_SENTINEL)
            actual.onNext(null);
        else
        if(obj == COMPLETE_SENTINEL)
            actual.onCompleted();
        else
        if(obj.getClass() == rx/observers/SerializedObserver$ErrorSentinel)
            actual.onError(((ErrorSentinel)obj).e);
        else
            actual.onNext(obj);
        j++;
          goto _L5
    }

    public void onCompleted()
    {
        this;
        JVM INSTR monitorenter ;
        if(!terminated)
            break MISSING_BLOCK_LABEL_12;
        this;
        JVM INSTR monitorexit ;
        return;
        terminated = true;
        if(!emitting)
            break MISSING_BLOCK_LABEL_60;
        if(queue == null)
            queue = new FastList();
        queue.add(COMPLETE_SENTINEL);
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        this;
        JVM INSTR monitorexit ;
        throw exception;
        FastList fastlist;
        emitting = true;
        fastlist = queue;
        queue = null;
        this;
        JVM INSTR monitorexit ;
        drainQueue(fastlist);
        actual.onCompleted();
        return;
    }

    public void onError(Throwable throwable)
    {
        this;
        JVM INSTR monitorenter ;
        if(!terminated)
            break MISSING_BLOCK_LABEL_12;
        this;
        JVM INSTR monitorexit ;
        return;
        terminated = true;
        if(!emitting)
            break MISSING_BLOCK_LABEL_65;
        if(queue == null)
            queue = new FastList();
        queue.add(new ErrorSentinel(throwable));
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        this;
        JVM INSTR monitorexit ;
        throw exception;
        FastList fastlist;
        emitting = true;
        fastlist = queue;
        queue = null;
        this;
        JVM INSTR monitorexit ;
        drainQueue(fastlist);
        actual.onError(throwable);
        return;
    }

    public void onNext(Object obj)
    {
        this;
        JVM INSTR monitorenter ;
        if(!terminated)
            break MISSING_BLOCK_LABEL_12;
        this;
        JVM INSTR monitorexit ;
        return;
        FastList fastlist1;
        if(!emitting)
            break MISSING_BLOCK_LABEL_68;
        if(queue == null)
            queue = new FastList();
        fastlist1 = queue;
        if(obj == null)
            break MISSING_BLOCK_LABEL_61;
_L1:
        fastlist1.add(obj);
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        this;
        JVM INSTR monitorexit ;
        throw exception;
        obj = NULL_SENTINEL;
          goto _L1
        FastList fastlist;
        emitting = true;
        fastlist = queue;
        queue = null;
        this;
        JVM INSTR monitorexit ;
        boolean flag;
        int i;
        flag = false;
        i = 0x7fffffff;
_L8:
        drainQueue(fastlist);
        flag = false;
        if(i != 0x7fffffff)
            break MISSING_BLOCK_LABEL_117;
        actual.onNext(obj);
        i--;
        flag = false;
        if(i <= 0)
            continue; /* Loop/switch isn't completed */
        this;
        JVM INSTR monitorenter ;
        fastlist = queue;
        queue = null;
        if(fastlist != null) goto _L3; else goto _L2
_L2:
        emitting = false;
        flag = true;
        this;
        JVM INSTR monitorexit ;
        if(flag) goto _L5; else goto _L4
_L4:
        this;
        JVM INSTR monitorenter ;
        if(!terminated)
            break MISSING_BLOCK_LABEL_188;
        queue;
        queue = null;
_L6:
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception4;
        exception4;
        this;
        JVM INSTR monitorexit ;
        throw exception4;
        emitting = false;
          goto _L6
_L3:
        this;
        JVM INSTR monitorexit ;
        if(i > 0) goto _L8; else goto _L7
_L7:
        if(false) goto _L10; else goto _L9
_L9:
        this;
        JVM INSTR monitorenter ;
        if(!terminated) goto _L12; else goto _L11
_L11:
        fastlist = queue;
        queue = null;
_L19:
        this;
        JVM INSTR monitorexit ;
_L10:
        drainQueue(fastlist);
        return;
        Exception exception3;
        exception3;
        this;
        JVM INSTR monitorexit ;
        throw exception3;
        Exception exception1;
        exception1;
        if(flag) goto _L14; else goto _L13
_L13:
        this;
        JVM INSTR monitorenter ;
        if(!terminated) goto _L16; else goto _L15
_L15:
        queue;
        queue = null;
_L17:
        this;
        JVM INSTR monitorexit ;
_L14:
        throw exception1;
_L12:
        emitting = false;
        fastlist = null;
        continue; /* Loop/switch isn't completed */
        Exception exception5;
        exception5;
        this;
        JVM INSTR monitorexit ;
        throw exception5;
_L16:
        emitting = false;
          goto _L17
        Exception exception2;
        exception2;
        this;
        JVM INSTR monitorexit ;
        throw exception2;
_L5:
        return;
        if(true) goto _L19; else goto _L18
_L18:
    }

    private static final Object COMPLETE_SENTINEL = new Object();
    private static final int MAX_DRAIN_ITERATION = 0x7fffffff;
    private static final Object NULL_SENTINEL = new Object();
    private final Observer actual;
    private boolean emitting;
    private FastList queue;
    private boolean terminated;

}
