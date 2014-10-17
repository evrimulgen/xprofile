// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package io.segment.android.integrations;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import com.bugsnag.android.Bugsnag;
import io.segment.android.errors.InvalidSettingsException;
import io.segment.android.integration.SimpleIntegration;
import io.segment.android.models.*;
import java.util.Iterator;

public class BugsnagIntegration extends SimpleIntegration
{
    private static class SettingKey
    {

        private static final String API_KEY = "apiKey";
        private static final String USE_SSL = "useSSL";

        private SettingKey()
        {
        }
    }


    public BugsnagIntegration()
    {
    }

    public String getKey()
    {
        return "Bugsnag";
    }

    public void identify(Identify identify1)
    {
        Traits traits;
        String s = identify1.getUserId();
        traits = identify1.getTraits();
        Bugsnag.setUser(s, traits.getString("email", "user@gmail.com"), traits.getString("name", "User Name"));
        if(traits == null) goto _L2; else goto _L1
_L1:
        Iterator iterator = traits.keys();
_L5:
        if(iterator.hasNext()) goto _L3; else goto _L2
_L2:
        return;
_L3:
        String s1 = (String)iterator.next();
        Bugsnag.addToTab("User", s1, traits.get(s1));
        if(true) goto _L5; else goto _L4
_L4:
    }

    public void onActivityStart(Activity activity)
    {
        Bugsnag.setContext(activity.getLocalClassName());
    }

    public void onCreate(Context context)
    {
        EasyJSONObject easyjsonobject = getSettings();
        String s = easyjsonobject.getString("apiKey");
        Bugsnag.setUseSSL(easyjsonobject.getBoolean("useSSL", Boolean.valueOf(false)).booleanValue());
        Bugsnag.register(context, s);
        ready();
    }

    public void validate(EasyJSONObject easyjsonobject)
        throws InvalidSettingsException
    {
        if(TextUtils.isEmpty(easyjsonobject.getString("apiKey")))
            throw new InvalidSettingsException("apiKey", "Bugsnag requires the setting apiKey.");
        else
            return;
    }
}
