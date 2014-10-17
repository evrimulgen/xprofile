// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.amplitude.api;

import android.os.Handler;
import android.os.Looper;

class HTTPThread extends Thread
{

    private HTTPThread()
    {
    }

    static void post(Runnable runnable)
    {
        waitForHandlerInitialization();
        instance.handler.post(runnable);
    }

    static void postDelayed(Runnable runnable, long l)
    {
        waitForHandlerInitialization();
        instance.handler.postDelayed(runnable, l);
    }

    static void removeCallbacks(Runnable runnable)
    {
        waitForHandlerInitialization();
        instance.handler.removeCallbacks(runnable);
    }

    private static void waitForHandlerInitialization()
    {
        exception;
        httpthread;
        JVM INSTR monitorexit ;
        throw exception;
        InterruptedException interruptedexception;
        do
        {
            if(instance.handler != null)
                break;
            synchronized(instance)
            {
                try
                {
                    instance.wait();
                }
                // Misplaced declaration of an exception variable
                catch(InterruptedException interruptedexception) { }
            }
        } while(true);
        return;
    }

    public void run()
    {
        Looper.prepare();
        synchronized(instance)
        {
            handler = new Handler();
            instance.notifyAll();
        }
        Looper.loop();
        return;
        exception;
        httpthread;
        JVM INSTR monitorexit ;
        throw exception;
    }

    static HTTPThread instance;
    private Handler handler;

    static 
    {
        instance = new HTTPThread();
        instance.start();
    }
}
