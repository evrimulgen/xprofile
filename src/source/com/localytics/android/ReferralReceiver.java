// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.localytics.android;

import android.content.*;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import com.newrelic.agent.android.instrumentation.SQLiteInstrumentation;

// Referenced classes of package com.localytics.android:
//            DatapointHelper, LocalyticsProvider

public class ReferralReceiver extends BroadcastReceiver
{

    public ReferralReceiver()
    {
        appKey = null;
    }

    public void onReceive(Context context, Intent intent)
    {
        Bundle bundle;
        try
        {
            bundle = intent.getExtras();
        }
        catch(Exception exception)
        {
            return;
        }
        if(bundle == null)
            break MISSING_BLOCK_LABEL_18;
        bundle.containsKey(null);
        if(intent.getAction().equals("com.android.vending.INSTALL_REFERRER"))
        {
            if(appKey == null || appKey.length() == 0)
                appKey = DatapointHelper.getLocalyticsAppKeyOrNull(context);
            if(appKey != null && appKey.length() != 0)
            {
                final String referrer = intent.getStringExtra("referrer");
                if(referrer != null && referrer.length() != 0)
                {
                    final LocalyticsProvider provider = LocalyticsProvider.getInstance(context, appKey);
                    provider.runBatchTransaction(new Runnable() {

                        public void run()
                        {
                            ContentValues contentvalues = new ContentValues();
                            contentvalues.put("play_attribution", referrer);
                            LocalyticsProvider localyticsprovider = provider;
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

                        final ReferralReceiver this$0;
                        private final LocalyticsProvider val$provider;
                        private final String val$referrer;

            
            {
                this$0 = ReferralReceiver.this;
                referrer = s;
                provider = localyticsprovider;
                super();
            }
                    }
);
                    return;
                }
            }
        }
        return;
    }

    protected String appKey;
}
