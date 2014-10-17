// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.newrelic.agent.android.instrumentation.io;

import java.util.*;

// Referenced classes of package com.newrelic.agent.android.instrumentation.io:
//            StreamCompleteListener, StreamCompleteEvent

class StreamCompleteListenerManager
{

    StreamCompleteListenerManager()
    {
        streamComplete = false;
        streamCompleteListeners = new ArrayList();
    }

    private boolean checkComplete()
    {
        this;
        JVM INSTR monitorenter ;
        boolean flag = isComplete();
        if(flag)
            break MISSING_BLOCK_LABEL_16;
        streamComplete = true;
        this;
        JVM INSTR monitorexit ;
        return flag;
        Exception exception;
        exception;
        this;
        JVM INSTR monitorexit ;
        throw exception;
    }

    private List getStreamCompleteListeners()
    {
        ArrayList arraylist1;
        synchronized(streamCompleteListeners)
        {
            arraylist1 = new ArrayList(streamCompleteListeners);
            streamCompleteListeners.clear();
        }
        return arraylist1;
        exception;
        arraylist;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public void addStreamCompleteListener(StreamCompleteListener streamcompletelistener)
    {
        synchronized(streamCompleteListeners)
        {
            streamCompleteListeners.add(streamcompletelistener);
        }
        return;
        exception;
        arraylist;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public boolean isComplete()
    {
        this;
        JVM INSTR monitorenter ;
        boolean flag = streamComplete;
        this;
        JVM INSTR monitorexit ;
        return flag;
        Exception exception;
        exception;
        this;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public void notifyStreamComplete(StreamCompleteEvent streamcompleteevent)
    {
        if(!checkComplete())
        {
            for(Iterator iterator = getStreamCompleteListeners().iterator(); iterator.hasNext(); ((StreamCompleteListener)iterator.next()).streamComplete(streamcompleteevent));
        }
    }

    public void notifyStreamError(StreamCompleteEvent streamcompleteevent)
    {
        if(!checkComplete())
        {
            for(Iterator iterator = getStreamCompleteListeners().iterator(); iterator.hasNext(); ((StreamCompleteListener)iterator.next()).streamError(streamcompleteevent));
        }
    }

    public void removeStreamCompleteListener(StreamCompleteListener streamcompletelistener)
    {
        synchronized(streamCompleteListeners)
        {
            streamCompleteListeners.remove(streamcompletelistener);
        }
        return;
        exception;
        arraylist;
        JVM INSTR monitorexit ;
        throw exception;
    }

    private boolean streamComplete;
    private ArrayList streamCompleteListeners;
}
