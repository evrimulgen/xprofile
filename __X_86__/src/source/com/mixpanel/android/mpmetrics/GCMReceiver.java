// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.mixpanel.android.mpmetrics;

import android.app.*;
import android.content.*;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;

// Referenced classes of package com.mixpanel.android.mpmetrics:
//            MixpanelAPI

public class GCMReceiver extends BroadcastReceiver
{

    public GCMReceiver()
    {
        LOGTAG = "MPGCMReceiver";
    }

    private void handleNotificationIntent(Context context, Intent intent)
    {
        String s = intent.getExtras().getString("mp_message");
        if(s == null)
            return;
        PackageManager packagemanager = context.getPackageManager();
        Intent intent1 = packagemanager.getLaunchIntentForPackage(context.getPackageName());
        Object obj = "";
        int i = 0x1080093;
        PendingIntent pendingintent;
        NotificationManager notificationmanager;
        Notification notification;
        try
        {
            ApplicationInfo applicationinfo = packagemanager.getApplicationInfo(context.getPackageName(), 0);
            obj = packagemanager.getApplicationLabel(applicationinfo);
            i = applicationinfo.icon;
        }
        catch(android.content.pm.PackageManager.NameNotFoundException namenotfoundexception) { }
        pendingintent = PendingIntent.getActivity(context.getApplicationContext(), 0, intent1, 0x8000000);
        notificationmanager = (NotificationManager)context.getSystemService("notification");
        notification = new Notification(i, s, System.currentTimeMillis());
        notification.flags = 0x10 | notification.flags;
        notification.setLatestEventInfo(context, ((CharSequence) (obj)), s, pendingintent);
        notificationmanager.notify(0, notification);
    }

    private void handleRegistrationIntent(Intent intent)
    {
        final String registration = intent.getStringExtra("registration_id");
        if(intent.getStringExtra("error") != null)
        {
            Log.e(LOGTAG, (new StringBuilder()).append("Error when registering for GCM: ").append(intent.getStringExtra("error")).toString());
        } else
        {
            if(registration != null)
            {
                MixpanelAPI.allInstances(new MixpanelAPI.InstanceProcessor() {

                    public void process(MixpanelAPI mixpanelapi)
                    {
                        mixpanelapi.getPeople().setPushRegistrationId(registration);
                    }

                    final GCMReceiver this$0;
                    final String val$registration;

            
            {
                this$0 = GCMReceiver.this;
                registration = s;
                super();
            }
                }
);
                return;
            }
            if(intent.getStringExtra("unregistered") != null)
            {
                MixpanelAPI.allInstances(new MixpanelAPI.InstanceProcessor() {

                    public void process(MixpanelAPI mixpanelapi)
                    {
                        mixpanelapi.getPeople().clearPushRegistrationId();
                    }

                    final GCMReceiver this$0;

            
            {
                this$0 = GCMReceiver.this;
                super();
            }
                }
);
                return;
            }
        }
    }

    public void onReceive(Context context, Intent intent)
    {
        String s = intent.getAction();
        if("com.google.android.c2dm.intent.REGISTRATION".equals(s))
            handleRegistrationIntent(intent);
        else
        if("com.google.android.c2dm.intent.RECEIVE".equals(s))
        {
            handleNotificationIntent(context, intent);
            return;
        }
    }

    String LOGTAG;
}
