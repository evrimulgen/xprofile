// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.tagmanager;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import com.newrelic.agent.android.api.v2.TraceFieldInterface;
import com.newrelic.agent.android.background.ApplicationStateMonitor;
import com.newrelic.agent.android.tracing.TraceMachine;

// Referenced classes of package com.google.tagmanager:
//            Log, TagManager

public class PreviewActivity extends Activity
    implements TraceFieldInterface
{

    public PreviewActivity()
    {
    }

    public void onCreate(Bundle bundle)
    {
        TraceMachine.startTracing("PreviewActivity");
        TraceMachine.enterMethod(_nr_trace, "PreviewActivity#onCreate", null);
_L1:
        super.onCreate(bundle);
        Log.i("Preview activity");
        android.net.Uri uri = getIntent().getData();
        if(TagManager.getInstance(this).setPreviewData(uri))
            break MISSING_BLOCK_LABEL_84;
        Log.w((new StringBuilder()).append("Cannot preview the app with the uri: ").append(uri).toString());
        TraceMachine.exitMethod();
        return;
        NoSuchFieldError nosuchfielderror;
        nosuchfielderror;
        TraceMachine.enterMethod(null, "PreviewActivity#onCreate", null);
          goto _L1
        Intent intent = getPackageManager().getLaunchIntentForPackage(getPackageName());
        if(intent == null)
            break MISSING_BLOCK_LABEL_137;
        try
        {
            Log.i((new StringBuilder()).append("Invoke the launch activity for package name: ").append(getPackageName()).toString());
            startActivity(intent);
        }
        catch(Exception exception)
        {
            Log.e((new StringBuilder()).append("Calling preview threw an exception: ").append(exception.getMessage()).toString());
        }
        TraceMachine.exitMethod();
        return;
        Log.i((new StringBuilder()).append("No launch activity found for package name: ").append(getPackageName()).toString());
        break MISSING_BLOCK_LABEL_133;
    }

    protected void onStart()
    {
        super.onStart();
        ApplicationStateMonitor.getInstance().activityStarted();
    }

    protected void onStop()
    {
        super.onStop();
        ApplicationStateMonitor.getInstance().activityStopped();
    }
}
