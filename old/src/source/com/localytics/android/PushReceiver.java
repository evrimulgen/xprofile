// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.localytics.android;

import android.app.*;
import android.content.*;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import com.newrelic.agent.android.instrumentation.JSONObjectInstrumentation;
import com.newrelic.agent.android.instrumentation.SQLiteInstrumentation;
import org.json.JSONException;
import org.json.JSONObject;

// Referenced classes of package com.localytics.android:
//            DatapointHelper, LocalyticsProvider

public class PushReceiver extends BroadcastReceiver
{

    public PushReceiver()
    {
    }

    private void handleNotificationReceived(Context context, Intent intent)
    {
        String s;
        Object obj;
        int i;
        s = intent.getExtras().getString("message");
        obj = "";
        i = 0x1080093;
        Intent intent2;
        ApplicationInfo applicationinfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), 0);
        i = applicationinfo.icon;
        obj = context.getPackageManager().getApplicationLabel(applicationinfo);
        intent2 = context.getPackageManager().getLaunchIntentForPackage(context.getPackageName());
        Intent intent1 = intent2;
_L4:
        Notification notification;
        String s1;
        int j;
        notification = new Notification(i, s, System.currentTimeMillis());
        if(intent1 != null)
        {
            intent1.putExtras(intent);
            notification.setLatestEventInfo(context, ((CharSequence) (obj)), s, PendingIntent.getActivity(context, 0, intent1, 0x8000000));
        }
        notification.flags = 0x10 | notification.flags;
        s1 = intent.getExtras().getString("ll");
        j = 0;
        if(s1 == null)
            break MISSING_BLOCK_LABEL_164;
        int k;
        JVM INSTR new #94  <Class JSONObject>;
        k = JSONObjectInstrumentation.init(s1).getInt("ca");
        j = k;
_L2:
        ((NotificationManager)context.getSystemService("notification")).notify(j, notification);
        return;
        JSONException jsonexception;
        jsonexception;
        j = 0;
        if(true) goto _L2; else goto _L1
_L1:
        android.content.pm.PackageManager.NameNotFoundException namenotfoundexception;
        namenotfoundexception;
        intent1 = null;
        if(true) goto _L4; else goto _L3
_L3:
    }

    private void handleRegistration(Context context, Intent intent)
    {
        String s = intent.getStringExtra("registration_id");
        if(intent.getStringExtra("error") == null)
        {
            if(intent.getStringExtra("unregistered") != null)
            {
                setRegistrationId(context, null);
                return;
            }
            if(s != null)
            {
                setRegistrationId(context, s);
                return;
            }
        }
    }

    private void setRegistrationId(final Context context, final String registrationId)
    {
        String s = DatapointHelper.getLocalyticsAppKeyOrNull(context);
        if(s == null || s.length() == 0)
        {
            return;
        } else
        {
            final LocalyticsProvider provider = LocalyticsProvider.getInstance(context, s);
            provider.runBatchTransaction(new Runnable() {

                public void run()
                {
                    ContentValues contentvalues = new ContentValues();
                    String s1;
                    LocalyticsProvider localyticsprovider;
                    if(registrationId == null)
                        s1 = "";
                    else
                        s1 = registrationId;
                    contentvalues.put("registration_id", s1);
                    contentvalues.put("registration_version", DatapointHelper.getAppVersion(context));
                    localyticsprovider = provider;
                    if(!(localyticsprovider instanceof SQLiteDatabase))
                    {
                        localyticsprovider.update("info", contentvalues, null, null);
                        return;
                    } else
                    {
                        SQLiteInstrumentation.update((SQLiteDatabase)localyticsprovider, "info", contentvalues, null, null);
                        return;
                    }
                }

                final PushReceiver this$0;
                private final Context val$context;
                private final LocalyticsProvider val$provider;
                private final String val$registrationId;

            
            {
                this$0 = PushReceiver.this;
                registrationId = s;
                context = context1;
                provider = localyticsprovider;
                super();
            }
            }
);
            return;
        }
    }

    public void onReceive(Context context, Intent intent)
    {
        if(intent.getAction().equals("com.google.android.c2dm.intent.REGISTRATION"))
            handleRegistration(context, intent);
        else
        if(intent.getAction().equals("com.google.android.c2dm.intent.RECEIVE"))
        {
            handleNotificationReceived(context, intent);
            return;
        }
    }
}
