// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package io.segment.android.utils;

import android.os.Handler;

// Referenced classes of package io.segment.android.utils:
//            LooperThreadWithHandler

public class HandlerTimer extends LooperThreadWithHandler
{

    public HandlerTimer(int i, Runnable runnable)
    {
        tick = new Runnable() {

            public void run()
            {
                if(active)
                {
                    clock.run();
                    scheduleTick();
                }
            }

            final HandlerTimer this$0;

            
            {
                this$0 = HandlerTimer.this;
                super();
            }
        }
;
        frequencyMs = i;
        clock = runnable;
    }

    private void scheduleTick()
    {
        scheduleTick(frequencyMs);
    }

    private void scheduleTick(int i)
    {
        if(!active)
        {
            return;
        } else
        {
            handler().postDelayed(tick, i);
            return;
        }
    }

    public void quit()
    {
        active = false;
        super.quit();
    }

    public void scheduleNow()
    {
        scheduleTick(0);
    }

    public void start()
    {
        this;
        JVM INSTR monitorenter ;
        super.start();
        active = true;
        scheduleTick();
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    private boolean active;
    private Runnable clock;
    private int frequencyMs;
    private Runnable tick;



}
