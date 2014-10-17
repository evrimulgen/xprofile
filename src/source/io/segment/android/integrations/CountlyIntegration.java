// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package io.segment.android.integrations;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import io.segment.android.errors.InvalidSettingsException;
import io.segment.android.integration.SimpleIntegration;
import io.segment.android.models.*;
import java.util.HashMap;
import ly.count.android.api.Countly;

public class CountlyIntegration extends SimpleIntegration
{
    private static class SettingKey
    {

        private static final String APP_KEY = "appKey";
        private static final String SERVER_URL = "serverUrl";

        private SettingKey()
        {
        }
    }


    public CountlyIntegration()
    {
    }

    public String getKey()
    {
        return "Countly";
    }

    public void onActivityStart(Activity activity)
    {
        Countly.sharedInstance().onStart();
    }

    public void onActivityStop(Activity activity)
    {
        Countly.sharedInstance().onStop();
    }

    public void onCreate(Context context)
    {
        EasyJSONObject easyjsonobject = getSettings();
        String s = easyjsonobject.getString("serverUrl");
        String s1 = easyjsonobject.getString("appKey");
        Countly.sharedInstance().init(context, s, s1);
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
        Object obj = new HashMap();
        int i = 0;
        if(props != null)
        {
            obj = props.toStringMap();
            boolean flag = props.has("sum");
            i = 0;
            if(flag)
                i = props.getInt("sum", Integer.valueOf(0)).intValue();
        }
        Countly.sharedInstance().recordEvent(s, ((java.util.Map) (obj)), i);
    }

    public void validate(EasyJSONObject easyjsonobject)
        throws InvalidSettingsException
    {
        if(TextUtils.isEmpty(easyjsonobject.getString("serverUrl")))
            throw new InvalidSettingsException("serverUrl", "Countly requires the serverUrl setting.");
        if(TextUtils.isEmpty(easyjsonobject.getString("appKey")))
            throw new InvalidSettingsException("appKey", "Amplitude requires the appKey setting.");
        else
            return;
    }
}
