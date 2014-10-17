// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package io.segment.android.integrations;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import com.localytics.android.LocalyticsSession;
import io.segment.android.errors.InvalidSettingsException;
import io.segment.android.integration.SimpleIntegration;
import io.segment.android.models.*;
import java.util.HashMap;
import java.util.Iterator;

public class LocalyticsIntegration extends SimpleIntegration
{
    private static class SettingKey
    {

        private static final String APP_KEY = "appKey";

        private SettingKey()
        {
        }
    }


    public LocalyticsIntegration()
    {
    }

    public void flush()
    {
        if(localyticsSession != null)
            localyticsSession.upload();
    }

    public String getKey()
    {
        return "Localytics";
    }

    public void identify(Identify identify1)
    {
        Traits traits;
        String s = identify1.getUserId();
        traits = identify1.getTraits();
        localyticsSession.setCustomerId(s);
        if(traits == null) goto _L2; else goto _L1
_L1:
        Iterator iterator;
        if(traits.has("email"))
            localyticsSession.setCustomerEmail(traits.getString("email"));
        if(traits.has("name"))
            localyticsSession.setCustomerEmail(traits.getString("name"));
        iterator = traits.keys();
_L5:
        if(iterator.hasNext()) goto _L3; else goto _L2
_L2:
        return;
_L3:
        String s1 = (String)iterator.next();
        String s2 = (new StringBuilder()).append(traits.get(s1)).toString();
        localyticsSession.setCustomerData(s1, s2);
        if(true) goto _L5; else goto _L4
_L4:
    }

    public void onActivityPause(Activity activity)
    {
        if(localyticsSession != null)
        {
            localyticsSession.close();
            localyticsSession.upload();
        }
    }

    public void onActivityResume(Activity activity)
    {
        if(localyticsSession != null)
            localyticsSession.open();
    }

    public void onCreate(Context context)
    {
        localyticsSession = new LocalyticsSession(context, getSettings().getString("appKey"));
        localyticsSession.open();
        localyticsSession.upload();
        ready();
    }

    public void screen(Screen screen1)
    {
        String s = screen1.getName();
        localyticsSession.tagScreen(s);
    }

    public void track(Track track1)
    {
        String s = track1.getEvent();
        Props props = track1.getProperties();
        Object obj = new HashMap();
        if(props != null)
            obj = props.toStringMap();
        localyticsSession.tagEvent(s, ((java.util.Map) (obj)));
    }

    public void validate(EasyJSONObject easyjsonobject)
        throws InvalidSettingsException
    {
        if(TextUtils.isEmpty(easyjsonobject.getString("appKey")))
            throw new InvalidSettingsException("appKey", "Localytics requires the appKey setting.");
        else
            return;
    }

    private LocalyticsSession localyticsSession;
}
