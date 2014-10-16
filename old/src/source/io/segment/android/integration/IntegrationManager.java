// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package io.segment.android.integration;

import android.app.Activity;
import android.text.TextUtils;
import android.util.Log;
import io.segment.android.cache.SettingsCache;
import io.segment.android.errors.InvalidSettingsException;
import io.segment.android.integrations.AmplitudeIntegration;
import io.segment.android.integrations.BugsnagIntegration;
import io.segment.android.integrations.CountlyIntegration;
import io.segment.android.integrations.CrittercismIntegration;
import io.segment.android.integrations.FlurryIntegration;
import io.segment.android.integrations.GoogleAnalyticsIntegration;
import io.segment.android.integrations.LocalyticsIntegration;
import io.segment.android.integrations.MixpanelIntegration;
import io.segment.android.integrations.OmnitureIntegration;
import io.segment.android.integrations.QuantcastIntegration;
import io.segment.android.integrations.TapstreamIntegration;
import io.segment.android.models.Alias;
import io.segment.android.models.BasePayload;
import io.segment.android.models.Context;
import io.segment.android.models.EasyJSONObject;
import io.segment.android.models.Group;
import io.segment.android.models.Identify;
import io.segment.android.models.Screen;
import io.segment.android.models.Track;
import io.segment.android.stats.Stopwatch;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

// Referenced classes of package io.segment.android.integration:
//            IIntegration, Integration, IntegrationState

public class IntegrationManager
    implements IIntegration
{
    private static interface ProviderOperation
    {

        public abstract void run(Integration integration);
    }


    public IntegrationManager(SettingsCache settingscache)
    {
        settingsCache = settingscache;
        integrations = new LinkedList();
        addIntegration(new AmplitudeIntegration());
        addIntegration(new BugsnagIntegration());
        addIntegration(new CountlyIntegration());
        addIntegration(new CrittercismIntegration());
        addIntegration(new FlurryIntegration());
        addIntegration(new GoogleAnalyticsIntegration());
        addIntegration(new LocalyticsIntegration());
        addIntegration(new MixpanelIntegration());
        addIntegration(new OmnitureIntegration());
        addIntegration(new QuantcastIntegration());
        addIntegration(new TapstreamIntegration());
    }

    private boolean ensureInitialized()
    {
        if(!initialized)
            refresh();
        if(!initialized)
            Log.i("analytics", "Provider manager waiting to be initialized ..");
        return initialized;
    }

    private boolean isProviderEnabled(Integration integration, BasePayload basepayload)
    {
        boolean flag = true;
        Context context = basepayload.getContext();
        if(context != null && context.has("providers"))
        {
            EasyJSONObject easyjsonobject = new EasyJSONObject(context.getObject("providers"));
            String s = integration.getKey();
            if(easyjsonobject.has("all"))
                flag = easyjsonobject.getBoolean("all", Boolean.valueOf(true)).booleanValue();
            if(easyjsonobject.has("All"))
                flag = easyjsonobject.getBoolean("All", Boolean.valueOf(true)).booleanValue();
            if(easyjsonobject.has(s))
                flag = easyjsonobject.getBoolean(s, Boolean.valueOf(true)).booleanValue();
        }
        return flag;
    }

    private void runOperation(String s, IntegrationState integrationstate, ProviderOperation provideroperation)
    {
        Stopwatch stopwatch = new Stopwatch((new StringBuilder("[All Providers] ")).append(s).toString());
        if(!ensureInitialized()) goto _L2; else goto _L1
_L1:
        Iterator iterator = integrations.iterator();
_L5:
        if(iterator.hasNext()) goto _L3; else goto _L2
_L2:
        stopwatch.end();
        return;
_L3:
        Integration integration = (Integration)iterator.next();
        if(integration.getState().ge(integrationstate))
        {
            Stopwatch stopwatch1 = new Stopwatch((new StringBuilder("[")).append(integration.getKey()).append("] ").append(s).toString());
            provideroperation.run(integration);
            stopwatch1.end();
        }
        if(true) goto _L5; else goto _L4
_L4:
    }

    public void addIntegration(Integration integration)
    {
        if(TextUtils.isEmpty(integration.getKey()))
        {
            throw new IllegalArgumentException("Provider #getKey() must return a non-null non-empty provider key.");
        } else
        {
            integrations.add(integration);
            return;
        }
    }

    public void alias(final Alias alias)
    {
        runOperation("Alias", IntegrationState.READY, new ProviderOperation() {

            public void run(Integration integration)
            {
                if(isProviderEnabled(integration, alias))
                    integration.alias(alias);
            }

            final IntegrationManager this$0;
            private final Alias val$alias;

            
            {
                this$0 = IntegrationManager.this;
                alias = alias1;
                super();
            }
        }
);
    }

    public void checkPermissions(android.content.Context context)
    {
        Iterator iterator = integrations.iterator();
        do
        {
            if(!iterator.hasNext())
                return;
            ((Integration)iterator.next()).checkPermission(context);
        } while(true);
    }

    public void flush()
    {
        runOperation("Flush", IntegrationState.READY, new ProviderOperation() {

            public void run(Integration integration)
            {
                integration.flush();
            }

            final IntegrationManager this$0;

            
            {
                this$0 = IntegrationManager.this;
                super();
            }
        }
);
    }

    public List getProviders()
    {
        return integrations;
    }

    public SettingsCache getSettingsCache()
    {
        return settingsCache;
    }

    public void group(final Group group)
    {
        runOperation("Group", IntegrationState.READY, new ProviderOperation() {

            public void run(Integration integration)
            {
                if(isProviderEnabled(integration, group))
                    integration.group(group);
            }

            final IntegrationManager this$0;
            private final Group val$group;

            
            {
                this$0 = IntegrationManager.this;
                group = group1;
                super();
            }
        }
);
    }

    public void identify(final Identify identify)
    {
        runOperation("Identify", IntegrationState.READY, new ProviderOperation() {

            public void run(Integration integration)
            {
                if(isProviderEnabled(integration, identify))
                    integration.identify(identify);
            }

            final IntegrationManager this$0;
            private final Identify val$identify;

            
            {
                this$0 = IntegrationManager.this;
                identify = identify1;
                super();
            }
        }
);
    }

    public boolean isInitialized()
    {
        return initialized;
    }

    public void onActivityPause(final Activity activity)
    {
        runOperation("onActivityPause", IntegrationState.INITIALIZED, new ProviderOperation() {

            public void run(Integration integration)
            {
                integration.onActivityPause(activity);
            }

            final IntegrationManager this$0;
            private final Activity val$activity;

            
            {
                this$0 = IntegrationManager.this;
                activity = activity1;
                super();
            }
        }
);
    }

    public void onActivityResume(final Activity activity)
    {
        runOperation("onActivityResume", IntegrationState.INITIALIZED, new ProviderOperation() {

            public void run(Integration integration)
            {
                integration.onActivityResume(activity);
            }

            final IntegrationManager this$0;
            private final Activity val$activity;

            
            {
                this$0 = IntegrationManager.this;
                activity = activity1;
                super();
            }
        }
);
    }

    public void onActivityStart(final Activity activity)
    {
        runOperation("onActivityStart", IntegrationState.INITIALIZED, new ProviderOperation() {

            public void run(Integration integration)
            {
                integration.onActivityStart(activity);
            }

            final IntegrationManager this$0;
            private final Activity val$activity;

            
            {
                this$0 = IntegrationManager.this;
                activity = activity1;
                super();
            }
        }
);
    }

    public void onActivityStop(final Activity activity)
    {
        runOperation("onActivityStop", IntegrationState.INITIALIZED, new ProviderOperation() {

            public void run(Integration integration)
            {
                integration.onActivityStop(activity);
            }

            final IntegrationManager this$0;
            private final Activity val$activity;

            
            {
                this$0 = IntegrationManager.this;
                activity = activity1;
                super();
            }
        }
);
    }

    public void onCreate(final android.content.Context context)
    {
        checkPermissions(context);
        runOperation("onCreate", IntegrationState.INITIALIZED, new ProviderOperation() {

            public void run(Integration integration)
            {
                integration.onCreate(context);
            }

            final IntegrationManager this$0;
            private final android.content.Context val$context;

            
            {
                this$0 = IntegrationManager.this;
                context = context1;
                super();
            }
        }
);
    }

    public void refresh()
    {
        Log.d("analytics", "Refreshing provider manager ...");
        EasyJSONObject easyjsonobject = settingsCache.getSettings();
        if(easyjsonobject != null)
        {
            Iterator iterator = integrations.iterator();
            do
            {
                if(!iterator.hasNext())
                {
                    initialized = true;
                    Log.i("analytics", "Initialized the Segment.io provider manager.");
                    return;
                }
                Integration integration = (Integration)iterator.next();
                String s = integration.getKey();
                if(easyjsonobject.has(s))
                {
                    EasyJSONObject easyjsonobject1 = new EasyJSONObject(easyjsonobject.getObject(s));
                    try
                    {
                        integration.initialize(easyjsonobject1);
                        integration.enable();
                    }
                    catch(InvalidSettingsException invalidsettingsexception)
                    {
                        Object aobj[] = new Object[2];
                        aobj[0] = integration.getKey();
                        aobj[1] = invalidsettingsexception.getMessage();
                        Log.w("analytics", String.format("Provider %s couldn't be initialized: %s", aobj));
                    }
                } else
                if(integration.getState().ge(IntegrationState.ENABLED))
                    integration.disable();
            } while(true);
        } else
        {
            Log.i("analytics", "Async settings aren't fetched yet, waiting ..");
            return;
        }
    }

    public void reset()
    {
        runOperation("Reset", IntegrationState.READY, new ProviderOperation() {

            public void run(Integration integration)
            {
                integration.reset();
            }

            final IntegrationManager this$0;

            
            {
                this$0 = IntegrationManager.this;
                super();
            }
        }
);
    }

    public void screen(final Screen screen)
    {
        runOperation("Screen", IntegrationState.READY, new ProviderOperation() {

            public void run(Integration integration)
            {
                if(isProviderEnabled(integration, screen))
                    integration.screen(screen);
            }

            final IntegrationManager this$0;
            private final Screen val$screen;

            
            {
                this$0 = IntegrationManager.this;
                screen = screen1;
                super();
            }
        }
);
    }

    public void setSettingsCache(SettingsCache settingscache)
    {
        settingsCache = settingscache;
    }

    public void toggleOptOut(final boolean optedOut)
    {
        runOperation("optOut", IntegrationState.INITIALIZED, new ProviderOperation() {

            public void run(Integration integration)
            {
                integration.toggleOptOut(optedOut);
            }

            final IntegrationManager this$0;
            private final boolean val$optedOut;

            
            {
                this$0 = IntegrationManager.this;
                optedOut = flag;
                super();
            }
        }
);
    }

    public void track(final Track track)
    {
        runOperation("Track", IntegrationState.READY, new ProviderOperation() {

            public void run(Integration integration)
            {
                if(isProviderEnabled(integration, track))
                    integration.track(track);
            }

            final IntegrationManager this$0;
            private final Track val$track;

            
            {
                this$0 = IntegrationManager.this;
                track = track1;
                super();
            }
        }
);
    }

    private static final String TAG = "analytics";
    private boolean initialized;
    private List integrations;
    private SettingsCache settingsCache;

}
