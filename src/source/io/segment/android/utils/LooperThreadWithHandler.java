// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package io.segment.android.utils;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import io.segment.android.Logger;

// Referenced classes of package io.segment.android.utils:
//            IThreadedLayer

public class LooperThreadWithHandler extends Thread
    implements IThreadedLayer
{

    public LooperThreadWithHandler()
    {
    }

    private void waitForReady()
    {
_L1:
        if(handler != null)
            return;
        this;
        JVM INSTR monitorenter ;
        wait();
_L2:
        this;
        JVM INSTR monitorexit ;
          goto _L1
        Exception exception;
        exception;
        this;
        JVM INSTR monitorexit ;
        throw exception;
        InterruptedException interruptedexception;
        interruptedexception;
        Logger.e((new StringBuilder("Failed while waiting for singleton thread ready. ")).append(Log.getStackTraceString(interruptedexception)).toString());
          goto _L2
    }

    public Handler handler()
    {
        waitForReady();
        return handler;
    }

    public void quit()
    {
        Looper.myLooper().quit();
    }

    public void run()
    {
        Looper.prepare();
        this;
        JVM INSTR monitorenter ;
        handler = new Handler();
        notifyAll();
        this;
        JVM INSTR monitorexit ;
        Looper.loop();
        return;
        Exception exception;
        exception;
        this;
        JVM INSTR monitorexit ;
        throw exception;
    }

    private Handler handler;
}
