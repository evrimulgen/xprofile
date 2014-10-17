// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package io.segment.android.integrations;

import android.content.Context;
import android.text.TextUtils;
import com.crittercism.app.Crittercism;
import com.crittercism.app.CrittercismConfig;
import io.segment.android.errors.InvalidSettingsException;
import io.segment.android.integration.SimpleIntegration;
import io.segment.android.models.*;

public class CrittercismIntegration extends SimpleIntegration
{
    private static class SettingKey
    {

        private static final String APP_ID = "appId";
        private static final String DELAY_SENDING_APP_LOAD = "delaySendingAppLoad";
        private static final String INCLUDE_VERSION_CODE = "includeVersionCode";
        private static final String SHOULD_INCLUDE_LOGCAT = "shouldCollectLogcat";

        private SettingKey()
        {
        }
    }


    public CrittercismIntegration()
    {
    }

    public void flush()
    {
        Crittercism.sendAppLoadData();
    }

    public String getKey()
    {
        return "Crittercism";
    }

    public void identify(Identify identify1)
    {
        String s = identify1.getUserId();
        Traits traits = identify1.getTraits();
        Crittercism.setUsername(s);
        if(traits != null)
        {
            if(traits.has("name"))
                Crittercism.setUsername(traits.getString("name"));
            Crittercism.setMetadata(traits);
        }
    }

    public void onCreate(Context context)
    {
        EasyJSONObject easyjsonobject = getSettings();
        String s = easyjsonobject.getString("appId");
        CrittercismConfig crittercismconfig = new CrittercismConfig();
        crittercismconfig.setDelaySendingAppLoad(easyjsonobject.getBoolean("delaySendingAppLoad", Boolean.valueOf(false)).booleanValue());
        crittercismconfig.setLogcatReportingEnabled(easyjsonobject.getBoolean("shouldCollectLogcat", Boolean.valueOf(false)).booleanValue());
        crittercismconfig.setVersionCodeToBeIncludedInVersionString(easyjsonobject.getBoolean("includeVersionCode", Boolean.valueOf(false)).booleanValue());
        Crittercism.initialize(context, s, crittercismconfig);
        ready();
    }

    public void screen(Screen screen1)
    {
        track(screen1);
    }

    public void track(Track track1)
    {
        Crittercism.leaveBreadcrumb(track1.getEvent());
    }

    public void validate(EasyJSONObject easyjsonobject)
        throws InvalidSettingsException
    {
        if(TextUtils.isEmpty(easyjsonobject.getString("appId")))
            throw new InvalidSettingsException("appId", "Crittercism requires the appId setting.");
        else
            return;
    }
}
