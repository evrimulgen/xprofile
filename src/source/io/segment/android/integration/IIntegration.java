// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package io.segment.android.integration;

import android.app.Activity;
import android.content.Context;
import io.segment.android.models.*;

public interface IIntegration
{

    public abstract void alias(Alias alias1);

    public abstract void flush();

    public abstract void group(Group group1);

    public abstract void identify(Identify identify1);

    public abstract void onActivityPause(Activity activity);

    public abstract void onActivityResume(Activity activity);

    public abstract void onActivityStart(Activity activity);

    public abstract void onActivityStop(Activity activity);

    public abstract void onCreate(Context context);

    public abstract void reset();

    public abstract void screen(Screen screen1);

    public abstract void toggleOptOut(boolean flag);

    public abstract void track(Track track1);
}
