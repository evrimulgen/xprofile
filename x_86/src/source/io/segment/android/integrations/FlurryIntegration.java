// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package io.segment.android.integrations;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import com.flurry.android.FlurryAgent;
import io.segment.android.Logger;
import io.segment.android.errors.InvalidSettingsException;
import io.segment.android.integration.SimpleIntegration;
import io.segment.android.models.*;
import java.util.*;

public class FlurryIntegration extends SimpleIntegration
{
    private static class SettingKey
    {

        private static final String API_KEY = "apiKey";
        private static final String CAPTURE_UNCAUGHT_EXCEPTIONS = "captureUncaughtExceptions";
        private static final String SESSION_LENGTH = "sessionLength";
        private static final String USE_HTTPS = "useHttps";

        private SettingKey()
        {
        }
    }


    public FlurryIntegration()
    {
    }

    private void initialize()
    {
        EasyJSONObject easyjsonobject = getSettings();
        int i = easyjsonobject.getInt("sessionLength", Integer.valueOf(10000)).intValue();
        boolean flag = easyjsonobject.getBoolean("captureUncaughtExceptions", Boolean.valueOf(false)).booleanValue();
        boolean flag1 = easyjsonobject.getBoolean("useHttps", Boolean.valueOf(false)).booleanValue();
        FlurryAgent.setContinueSessionMillis(i);
        FlurryAgent.setCaptureUncaughtExceptions(flag);
        FlurryAgent.setUseHttps(flag1);
    }

    private Map toMap(Props props)
    {
        HashMap hashmap = new HashMap();
        if(props == null) goto _L2; else goto _L1
_L1:
        Iterator iterator = props.keys();
_L5:
        if(iterator.hasNext()) goto _L3; else goto _L2
_L2:
        return hashmap;
_L3:
        String s = (String)iterator.next();
        String s1 = (new StringBuilder()).append(props.get(s)).toString();
        if(s1.length() > 255)
            s1 = s1.substring(0, 255);
        hashmap.put(s, s1);
        if(true) goto _L5; else goto _L4
_L4:
    }

    public String getKey()
    {
        return "Flurry";
    }

    public void identify(Identify identify1)
    {
        Traits traits;
        String s;
        traits = identify1.getTraits();
        s = traits.getString("gender");
        if(TextUtils.isEmpty(s)) goto _L2; else goto _L1
_L1:
        if(!s.equalsIgnoreCase("male")) goto _L4; else goto _L3
_L3:
        FlurryAgent.setGender((byte)1);
_L2:
        Integer integer = traits.getInt("age", null);
        if(integer != null && integer.intValue() > 0)
            FlurryAgent.setAge(integer.intValue());
        FlurryAgent.setUserId(identify1.getUserId());
        return;
_L4:
        if(s.equalsIgnoreCase("female"))
            FlurryAgent.setGender((byte)0);
        if(true) goto _L2; else goto _L5
_L5:
    }

    public void onActivityStart(Activity activity)
    {
        initialize();
        FlurryAgent.onStartSession(activity, getSettings().getString("apiKey"));
        ready();
    }

    public void onActivityStop(Activity activity)
    {
        try
        {
            FlurryAgent.onEndSession(activity);
            return;
        }
        catch(NullPointerException nullpointerexception)
        {
            Logger.w("Flurry Agent's #onEndSession threw a NullPointerException.", nullpointerexception);
        }
    }

    public void onCreate(Context context)
    {
    }

    public void screen(Screen screen1)
    {
        FlurryAgent.onPageView();
        track(screen1);
    }

    public void track(Track track1)
    {
        FlurryAgent.logEvent(track1.getEvent(), toMap(track1.getProperties()));
    }

    public void validate(EasyJSONObject easyjsonobject)
        throws InvalidSettingsException
    {
        if(TextUtils.isEmpty(easyjsonobject.getString("apiKey")))
            throw new InvalidSettingsException("apiKey", "API Key (apiKey) required.");
        else
            return;
    }
}
