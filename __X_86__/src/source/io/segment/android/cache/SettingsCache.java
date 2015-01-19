// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package io.segment.android.cache;

import android.content.Context;
import com.newrelic.agent.android.instrumentation.JSONObjectInstrumentation;
import io.segment.android.Logger;
import io.segment.android.models.EasyJSONObject;
import java.util.Calendar;
import org.json.JSONException;
import org.json.JSONObject;

// Referenced classes of package io.segment.android.cache:
//            SimpleStringCache, ISettingsLayer

public class SettingsCache extends SimpleStringCache
{

    public SettingsCache(Context context, ISettingsLayer isettingslayer, int i)
    {
        super(context, "settings");
        layer = isettingslayer;
        cacheForMs = i;
    }

    private EasyJSONObject parseContainer(String s)
    {
        if(s == null)
            break MISSING_BLOCK_LABEL_30;
        EasyJSONObject easyjsonobject;
        JVM INSTR new #38  <Class JSONObject>;
        easyjsonobject = new EasyJSONObject(JSONObjectInstrumentation.init(s));
        return easyjsonobject;
        JSONException jsonexception;
        jsonexception;
        Logger.w("Failed to parse json object representing cached settings.");
        return null;
    }

    public String get()
    {
        EasyJSONObject easyjsonobject = parseContainer(super.get());
        if(easyjsonobject != null)
        {
            Calendar calendar = Calendar.getInstance();
            Calendar calendar1 = easyjsonobject.getCalendar("lastUpdated");
            if(calendar1 != null)
            {
                if(calendar.getTimeInMillis() - calendar1.getTimeInMillis() > (long)cacheForMs)
                {
                    Logger.d("Segment.io settings cache expired, loading new settings ...");
                    load();
                }
                return easyjsonobject.toString();
            }
            Logger.w((new StringBuilder("Container exists, but without last updated key. JSON: ")).append(easyjsonobject.toString()).toString());
        }
        return null;
    }

    public int getReloads()
    {
        return reloads;
    }

    public EasyJSONObject getSettings()
    {
        EasyJSONObject easyjsonobject = parseContainer(get());
        if(easyjsonobject != null)
        {
            JSONObject jsonobject = easyjsonobject.getObject("settings");
            if(jsonobject != null)
                return new EasyJSONObject(jsonobject);
        }
        return null;
    }

    public String load()
    {
        return load(null);
    }

    public String load(final ISettingsLayer.SettingsCallback callback)
    {
        Logger.d("Requesting Segment.io settings ..");
        layer.fetch(new ISettingsLayer.SettingsCallback() {

            public void onSettingsLoaded(boolean flag, EasyJSONObject easyjsonobject)
            {
                if(easyjsonobject == null)
                {
                    Logger.w("Failed to fetch new Segment.io settings.");
                } else
                {
                    EasyJSONObject easyjsonobject1 = new EasyJSONObject();
                    easyjsonobject1.put("lastUpdated", Calendar.getInstance());
                    easyjsonobject1.put("settings", easyjsonobject);
                    SettingsCache settingscache = SettingsCache.this;
                    settingscache.reloads = 1 + settingscache.reloads;
                    Logger.d("Successfully fetched new Segment.io settings.");
                    set(easyjsonobject1.toString());
                }
                if(callback != null)
                    callback.onSettingsLoaded(flag, easyjsonobject);
            }

            final SettingsCache this$0;
            private final ISettingsLayer.SettingsCallback val$callback;

            
            {
                this$0 = SettingsCache.this;
                callback = settingscallback;
                super();
            }
        }
);
        return null;
    }

    private static final String CACHE_KEY = "settings";
    private static final String LAST_UPDATED_KEY = "lastUpdated";
    private static final String SETTINGS_KEY = "settings";
    private int cacheForMs;
    private ISettingsLayer layer;
    private int reloads;


}
