// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.android.gms.ads;

import android.app.Activity;
import android.os.Bundle;
import android.os.RemoteException;
import com.google.android.gms.internal.*;
import com.newrelic.agent.android.api.v2.TraceFieldInterface;
import com.newrelic.agent.android.background.ApplicationStateMonitor;
import com.newrelic.agent.android.tracing.TraceMachine;

public final class AdActivity extends Activity
    implements TraceFieldInterface
{

    public AdActivity()
    {
    }

    protected void onCreate(Bundle bundle)
    {
        TraceMachine.startTracing("AdActivity");
        TraceMachine.enterMethod(_nr_trace, "AdActivity#onCreate", null);
_L2:
        super.onCreate(bundle);
        kz = bv.a(this);
        if(kz == null)
        {
            da.w("Could not create ad overlay.");
            finish();
            TraceMachine.exitMethod();
            return;
        }
        break; /* Loop/switch isn't completed */
        NoSuchFieldError nosuchfielderror;
        nosuchfielderror;
        TraceMachine.enterMethod(null, "AdActivity#onCreate", null);
        if(true) goto _L2; else goto _L1
_L1:
        try
        {
            kz.onCreate(bundle);
        }
        catch(RemoteException remoteexception)
        {
            da.b("Could not forward onCreate to ad overlay:", remoteexception);
            finish();
        }
        TraceMachine.exitMethod();
        return;
    }

    protected void onDestroy()
    {
        try
        {
            if(kz != null)
                kz.onDestroy();
        }
        catch(RemoteException remoteexception)
        {
            da.b("Could not forward onDestroy to ad overlay:", remoteexception);
        }
        super.onDestroy();
    }

    protected void onPause()
    {
        try
        {
            if(kz != null)
                kz.onPause();
        }
        catch(RemoteException remoteexception)
        {
            da.b("Could not forward onPause to ad overlay:", remoteexception);
            finish();
        }
        super.onPause();
    }

    protected void onRestart()
    {
        super.onRestart();
        try
        {
            if(kz != null)
                kz.onRestart();
            return;
        }
        catch(RemoteException remoteexception)
        {
            da.b("Could not forward onRestart to ad overlay:", remoteexception);
        }
        finish();
    }

    protected void onResume()
    {
        super.onResume();
        try
        {
            if(kz != null)
                kz.onResume();
            return;
        }
        catch(RemoteException remoteexception)
        {
            da.b("Could not forward onResume to ad overlay:", remoteexception);
        }
        finish();
    }

    protected void onSaveInstanceState(Bundle bundle)
    {
        try
        {
            if(kz != null)
                kz.onSaveInstanceState(bundle);
        }
        catch(RemoteException remoteexception)
        {
            da.b("Could not forward onSaveInstanceState to ad overlay:", remoteexception);
            finish();
        }
        super.onSaveInstanceState(bundle);
    }

    protected void onStart()
    {
        ApplicationStateMonitor.getInstance().activityStarted();
        super.onStart();
        try
        {
            if(kz != null)
                kz.onStart();
            return;
        }
        catch(RemoteException remoteexception)
        {
            da.b("Could not forward onStart to ad overlay:", remoteexception);
        }
        finish();
    }

    protected void onStop()
    {
        ApplicationStateMonitor.getInstance().activityStopped();
        try
        {
            if(kz != null)
                kz.onStop();
        }
        catch(RemoteException remoteexception)
        {
            da.b("Could not forward onStop to ad overlay:", remoteexception);
            finish();
        }
        super.onStop();
    }

    public static final String CLASS_NAME = "com.google.android.gms.ads.AdActivity";
    public static final String SIMPLE_CLASS_NAME = "AdActivity";
    private bw kz;
}
