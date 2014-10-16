// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package io.segment.android.integrations;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import com.amplitude.api.Amplitude;
import io.segment.android.errors.InvalidSettingsException;
import io.segment.android.integration.SimpleIntegration;
import io.segment.android.models.*;

public class AmplitudeIntegration extends SimpleIntegration
{
    private static class SettingKey
    {

        private static final String API_KEY = "apiKey";

        private SettingKey()
        {
        }
    }


    public AmplitudeIntegration()
    {
    }

    public void flush()
    {
        Amplitude.uploadEvents();
    }

    public String getKey()
    {
        return "Amplitude";
    }

    public void identify(Identify identify1)
    {
        String s = identify1.getUserId();
        io.segment.android.models.Traits traits = identify1.getTraits();
        Amplitude.setUserId(s);
        Amplitude.setGlobalUserProperties(traits);
    }

    public void onActivityStart(Activity activity)
    {
        Amplitude.startSession();
    }

    public void onActivityStop(Activity activity)
    {
        Amplitude.endSession();
    }

    public void onCreate(Context context)
    {
        Amplitude.initialize(context, getSettings().getString("apiKey"));
        ready();
    }

    public void screen(Screen screen1)
    {
        track(screen1);
    }

    public void track(Track track1)
    {
        String s = track1.getEvent();
        Props props = track1.getProperties();
        if(props != null && props.has("revenue"))
            Amplitude.logRevenue(props.getDouble("revenue", Double.valueOf(0.0D)).doubleValue());
        Amplitude.logEvent(s, props);
    }

    public void validate(EasyJSONObject easyjsonobject)
        throws InvalidSettingsException
    {
        if(TextUtils.isEmpty(easyjsonobject.getString("apiKey")))
            throw new InvalidSettingsException("apiKey", "Amplitude requires the apiKey setting.");
        else
            return;
    }
}
