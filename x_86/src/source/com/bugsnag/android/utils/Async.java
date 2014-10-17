// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.bugsnag.android.utils;

import android.os.AsyncTask;
import com.bugsnag.android.Logger;
import com.newrelic.agent.android.api.v2.TraceFieldInterface;
import com.newrelic.agent.android.instrumentation.AsyncTaskInstrumentation;
import com.newrelic.agent.android.tracing.Trace;
import com.newrelic.agent.android.tracing.TraceMachine;

public class Async
{

    public Async()
    {
    }

    public static void safeAsync(Runnable runnable)
    {
        TraceFieldInterface tracefieldinterface = new TraceFieldInterface(runnable) {

            private transient Void doInBackground$10299ca()
            {
                delegate.run();
_L2:
                return null;
                Exception exception;
                exception;
                if(Async.logger != null)
                    Async.logger.warn("Error in bugsnag", exception);
                if(true) goto _L2; else goto _L1
_L1:
            }

            public void _nr_setTrace(Trace trace)
            {
                try
                {
                    _nr_trace = trace;
                    return;
                }
                catch(Exception exception)
                {
                    return;
                }
            }

            protected final volatile Object doInBackground(Object aobj[])
            {
                TraceMachine.enterMethod(_nr_trace, "Async$1#doInBackground", null);
_L1:
                Void void1 = doInBackground$10299ca();
                TraceMachine.exitMethod();
                TraceMachine.unloadTraceContext(this);
                return void1;
                NoSuchFieldError nosuchfielderror;
                nosuchfielderror;
                TraceMachine.enterMethod(null, "Async$1#doInBackground", null);
                  goto _L1
            }

            public Trace _nr_trace;
            private Runnable val$delegate;

            
            {
                delegate = runnable;
                super();
            }
        }
;
        Void avoid[] = new Void[0];
        if(!(tracefieldinterface instanceof AsyncTask))
        {
            tracefieldinterface.execute(avoid);
            return;
        } else
        {
            AsyncTaskInstrumentation.execute((AsyncTask)tracefieldinterface, avoid);
            return;
        }
    }

    public static Logger logger = null;

}
