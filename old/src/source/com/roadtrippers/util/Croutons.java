// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.roadtrippers.util;

import android.app.Activity;
import android.content.Intent;
import android.view.*;
import android.widget.TextView;
import com.dmitriy.tarasov.android.intents.IntentUtils;
import de.keyboardsurfer.android.widget.crouton.Crouton;

public class Croutons
{

    public Croutons()
    {
    }

    public static void showLocationUnavailableError(ViewGroup viewgroup, Activity activity)
    {
        showNotification(viewgroup, activity, activity.getString(0x7f0c00b9), new Runnable(activity) {

            public void run()
            {
                activity.startActivity(IntentUtils.showLocationServices());
            }

            final Activity val$activity;

            
            {
                activity = activity1;
                super();
            }
        }
);
    }

    public static void showNetworkUnavailableError(ViewGroup viewgroup, Activity activity)
    {
        showNotification(viewgroup, activity, activity.getString(0x7f0c00c2), new Runnable(activity) {

            public void run()
            {
                activity.startActivity(new Intent("android.settings.SETTINGS"));
            }

            final Activity val$activity;

            
            {
                activity = activity1;
                super();
            }
        }
);
    }

    private static void showNotification(ViewGroup viewgroup, Activity activity, String s, Runnable runnable)
    {
        View view = LayoutInflater.from(activity).inflate(0x7f030067, viewgroup, false);
        Crouton crouton = Crouton.make(activity, view, viewgroup);
        crouton.setConfiguration((new de.keyboardsurfer.android.widget.crouton.Configuration.Builder()).setDuration(5000).build());
        ((TextView)view.findViewById(0x7f090147)).setText(s);
        view.findViewById(0x7f0900f5).setOnClickListener(new android.view.View.OnClickListener(crouton, runnable) {

            public void onClick(View view1)
            {
                crouton.cancel();
                action.run();
            }

            final Runnable val$action;
            final Crouton val$crouton;

            
            {
                crouton = crouton1;
                action = runnable;
                super();
            }
        }
);
        crouton.show();
    }
}
