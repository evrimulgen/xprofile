// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.tapstream.sdk;

import android.content.Context;

// Referenced classes of package com.tapstream.sdk:
//            Api, PlatformImpl, CoreListenerImpl, Core, 
//            Logging, Delegate, CoreListener, Platform, 
//            Config, Event, Hit

public class Tapstream
    implements Api
{
    private class DelegateImpl
        implements Delegate
    {

        public int getDelay()
        {
            return core.getDelay();
        }

        public boolean isRetryAllowed()
        {
            return true;
        }

        public void setDelay(int i)
        {
        }

        final Tapstream this$0;

        private DelegateImpl()
        {
            this$0 = Tapstream.this;
            super();
        }

    }


    private Tapstream(Context context, String s, String s1, Config config)
    {
        _flddelegate = new DelegateImpl();
        platform = new PlatformImpl(context);
        listener = new CoreListenerImpl();
        core = new Core(_flddelegate, platform, listener, s, s1, config);
        core.start();
    }

    public static void create(Context context, String s, String s1, Config config)
    {
        com/tapstream/sdk/Tapstream;
        JVM INSTR monitorenter ;
        if(instance != null)
            break MISSING_BLOCK_LABEL_27;
        instance = new Tapstream(context, s, s1, config);
_L2:
        com/tapstream/sdk/Tapstream;
        JVM INSTR monitorexit ;
        return;
        Logging.log(5, "Tapstream Warning: Tapstream already instantiated, it cannot be re-created.", new Object[0]);
        if(true) goto _L2; else goto _L1
_L1:
        Exception exception;
        exception;
        com/tapstream/sdk/Tapstream;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public static Tapstream getInstance()
    {
        com/tapstream/sdk/Tapstream;
        JVM INSTR monitorenter ;
        if(instance == null)
            throw new RuntimeException("You must first call Tapstream.create");
        break MISSING_BLOCK_LABEL_25;
        Exception exception;
        exception;
        com/tapstream/sdk/Tapstream;
        JVM INSTR monitorexit ;
        throw exception;
        Tapstream tapstream = instance;
        com/tapstream/sdk/Tapstream;
        JVM INSTR monitorexit ;
        return tapstream;
    }

    public void fireEvent(Event event)
    {
        core.fireEvent(event);
    }

    public void fireHit(Hit hit, Hit.CompletionHandler completionhandler)
    {
        core.fireHit(hit, completionhandler);
    }

    private static Tapstream instance;
    private Core core;
    private Delegate _flddelegate;
    private CoreListener listener;
    private Platform platform;

}
