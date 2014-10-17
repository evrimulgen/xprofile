// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package eu.janmuller.android.simplecropimage;

import android.graphics.Bitmap;
import android.util.Log;
import com.newrelic.agent.android.instrumentation.BitmapFactoryInstrumentation;
import java.io.FileDescriptor;
import java.util.*;

public class BitmapManager
{
    private static final class State extends Enum
    {

        public static State valueOf(String s)
        {
            return (State)Enum.valueOf(eu/janmuller/android/simplecropimage/BitmapManager$State, s);
        }

        public static State[] values()
        {
            return (State[])$VALUES.clone();
        }

        private static final State $VALUES[];
        public static final State ALLOW;
        public static final State CANCEL;

        static 
        {
            CANCEL = new State("CANCEL", 0);
            ALLOW = new State("ALLOW", 1);
            State astate[] = new State[2];
            astate[0] = CANCEL;
            astate[1] = ALLOW;
            $VALUES = astate;
        }

        private State(String s, int i)
        {
            super(s, i);
        }
    }

    public static class ThreadSet
        implements Iterable
    {

        public void add(Thread thread)
        {
            mWeakCollection.put(thread, null);
        }

        public Iterator iterator()
        {
            return mWeakCollection.keySet().iterator();
        }

        public void remove(Thread thread)
        {
            mWeakCollection.remove(thread);
        }

        private final WeakHashMap mWeakCollection = new WeakHashMap();

        public ThreadSet()
        {
        }
    }

    private static class ThreadStatus
    {

        public String toString()
        {
            String s;
            if(mState == State.CANCEL)
                s = "Cancel";
            else
            if(mState == State.ALLOW)
                s = "Allow";
            else
                s = "?";
            return (new StringBuilder()).append("thread state = ").append(s).append(", options = ").append(mOptions).toString();
        }

        public android.graphics.BitmapFactory.Options mOptions;
        public State mState;

        private ThreadStatus()
        {
            mState = State.ALLOW;
        }

    }


    private BitmapManager()
    {
    }

    private ThreadStatus getOrCreateThreadStatus(Thread thread)
    {
        this;
        JVM INSTR monitorenter ;
        ThreadStatus threadstatus = (ThreadStatus)mThreadStatus.get(thread);
        if(threadstatus != null)
            break MISSING_BLOCK_LABEL_37;
        threadstatus = new ThreadStatus();
        mThreadStatus.put(thread, threadstatus);
        this;
        JVM INSTR monitorexit ;
        return threadstatus;
        Exception exception;
        exception;
        throw exception;
    }

    public static BitmapManager instance()
    {
        eu/janmuller/android/simplecropimage/BitmapManager;
        JVM INSTR monitorenter ;
        BitmapManager bitmapmanager;
        if(sManager == null)
            sManager = new BitmapManager();
        bitmapmanager = sManager;
        eu/janmuller/android/simplecropimage/BitmapManager;
        JVM INSTR monitorexit ;
        return bitmapmanager;
        Exception exception;
        exception;
        throw exception;
    }

    private void setDecodingOptions(Thread thread, android.graphics.BitmapFactory.Options options)
    {
        this;
        JVM INSTR monitorenter ;
        getOrCreateThreadStatus(thread).mOptions = options;
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public void allowThreadDecoding(ThreadSet threadset)
    {
        this;
        JVM INSTR monitorenter ;
        for(Iterator iterator = threadset.iterator(); iterator.hasNext(); allowThreadDecoding((Thread)iterator.next()));
        break MISSING_BLOCK_LABEL_37;
        Exception exception;
        exception;
        throw exception;
        this;
        JVM INSTR monitorexit ;
    }

    public void allowThreadDecoding(Thread thread)
    {
        this;
        JVM INSTR monitorenter ;
        getOrCreateThreadStatus(thread).mState = State.ALLOW;
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public boolean canThreadDecoding(Thread thread)
    {
        boolean flag = true;
        this;
        JVM INSTR monitorenter ;
        ThreadStatus threadstatus = (ThreadStatus)mThreadStatus.get(thread);
        if(threadstatus != null) goto _L2; else goto _L1
_L1:
        this;
        JVM INSTR monitorexit ;
        return flag;
_L2:
        State state;
        State state1;
        state = threadstatus.mState;
        state1 = State.CANCEL;
        if(state == state1)
            flag = false;
        if(true) goto _L1; else goto _L3
_L3:
        Exception exception;
        exception;
        throw exception;
    }

    public void cancelThreadDecoding(ThreadSet threadset)
    {
        this;
        JVM INSTR monitorenter ;
        for(Iterator iterator = threadset.iterator(); iterator.hasNext(); cancelThreadDecoding((Thread)iterator.next()));
        break MISSING_BLOCK_LABEL_37;
        Exception exception;
        exception;
        throw exception;
        this;
        JVM INSTR monitorexit ;
    }

    public void cancelThreadDecoding(Thread thread)
    {
        this;
        JVM INSTR monitorenter ;
        ThreadStatus threadstatus = getOrCreateThreadStatus(thread);
        threadstatus.mState = State.CANCEL;
        if(threadstatus.mOptions != null)
            threadstatus.mOptions.requestCancelDecode();
        notifyAll();
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public Bitmap decodeFileDescriptor(FileDescriptor filedescriptor, android.graphics.BitmapFactory.Options options)
    {
        Thread thread;
        if(!options.mCancel)
            if(canThreadDecoding(thread = Thread.currentThread()))
            {
                setDecodingOptions(thread, options);
                Bitmap bitmap = BitmapFactoryInstrumentation.decodeFileDescriptor(filedescriptor, null, options);
                removeDecodingOptions(thread);
                return bitmap;
            }
        return null;
    }

    public void dump()
    {
        this;
        JVM INSTR monitorenter ;
        java.util.Map.Entry entry;
        for(Iterator iterator = mThreadStatus.entrySet().iterator(); iterator.hasNext(); Log.v("BitmapManager", (new StringBuilder()).append("[Dump] Thread ").append(entry.getKey()).append(" (").append(((Thread)entry.getKey()).getId()).append(")'s status is ").append(entry.getValue()).toString()))
            entry = (java.util.Map.Entry)iterator.next();

        break MISSING_BLOCK_LABEL_106;
        Exception exception;
        exception;
        throw exception;
        this;
        JVM INSTR monitorexit ;
    }

    android.graphics.BitmapFactory.Options getDecodingOptions(Thread thread)
    {
        this;
        JVM INSTR monitorenter ;
        ThreadStatus threadstatus = (ThreadStatus)mThreadStatus.get(thread);
        if(threadstatus == null)
            break MISSING_BLOCK_LABEL_29;
        android.graphics.BitmapFactory.Options options = threadstatus.mOptions;
_L1:
        this;
        JVM INSTR monitorexit ;
        return options;
        options = null;
          goto _L1
        Exception exception;
        exception;
        throw exception;
    }

    void removeDecodingOptions(Thread thread)
    {
        this;
        JVM INSTR monitorenter ;
        ((ThreadStatus)mThreadStatus.get(thread)).mOptions = null;
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    private static final String TAG = "BitmapManager";
    private static BitmapManager sManager = null;
    private final WeakHashMap mThreadStatus = new WeakHashMap();

}
