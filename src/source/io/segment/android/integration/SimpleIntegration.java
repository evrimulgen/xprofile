// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package io.segment.android.integration;

import android.app.Activity;
import io.segment.android.models.*;

// Referenced classes of package io.segment.android.integration:
//            Integration

public abstract class SimpleIntegration extends Integration
{

    public SimpleIntegration()
    {
    }

    public void alias(Alias alias1)
    {
    }

    public void flush()
    {
    }

    public String[] getRequiredPermissions()
    {
        return new String[0];
    }

    public void group(Group group1)
    {
    }

    public void identify(Identify identify1)
    {
    }

    public void onActivityPause(Activity activity)
    {
    }

    public void onActivityResume(Activity activity)
    {
    }

    public void onActivityStart(Activity activity)
    {
    }

    public void onActivityStop(Activity activity)
    {
    }

    public void reset()
    {
    }

    public void screen(Screen screen1)
    {
    }

    public void toggleOptOut(boolean flag)
    {
    }

    public void track(Track track1)
    {
    }
}
