// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package io.segment.android.integrations;

import android.content.Context;
import android.text.TextUtils;
import com.mixpanel.android.mpmetrics.MixpanelAPI;
import io.segment.android.errors.InvalidSettingsException;
import io.segment.android.integration.SimpleIntegration;
import io.segment.android.models.*;
import io.segment.android.utils.Parameters;
import java.util.*;

public class MixpanelIntegration extends SimpleIntegration
{
    private static class SettingKey
    {

        private static final String PEOPLE = "people";
        private static final String TOKEN = "token";

        private SettingKey()
        {
        }
    }


    public MixpanelIntegration()
    {
    }

    private boolean isMixpanelPeopleEnabled()
    {
        return getSettings().getBoolean("people", Boolean.valueOf(false)).booleanValue();
    }

    public void alias(Alias alias1)
    {
        String s = alias1.getTo();
        mixpanel.identify(s);
    }

    public void flush()
    {
        if(mixpanel != null)
            mixpanel.flush();
    }

    public String getKey()
    {
        return "Mixpanel";
    }

    public void identify(Identify identify1)
    {
        String s;
        EasyJSONObject easyjsonobject;
        s = identify1.getUserId();
        io.segment.android.models.Traits traits = identify1.getTraits();
        easyjsonobject = Parameters.move(traits, traitsMapping);
        mixpanel.identify(s);
        if(traits != null)
            mixpanel.registerSuperProperties(easyjsonobject);
        if(!isMixpanelPeopleEnabled()) goto _L2; else goto _L1
_L1:
        com.mixpanel.android.mpmetrics.MixpanelAPI.People people;
        Iterator iterator;
        people = mixpanel.getPeople();
        people.identify(s);
        iterator = easyjsonobject.keys();
_L5:
        if(iterator.hasNext()) goto _L3; else goto _L2
_L2:
        return;
_L3:
        String s1 = (String)iterator.next();
        people.set(s1, easyjsonobject.get(s1));
        if(true) goto _L5; else goto _L4
_L4:
    }

    public void onCreate(Context context)
    {
        mixpanel = MixpanelAPI.getInstance(context, getSettings().getString("token"));
        ready();
    }

    public void reset()
    {
        if(mixpanel != null)
            mixpanel.clearSuperProperties();
    }

    public void screen(Screen screen1)
    {
        track(screen1);
    }

    public void track(Track track1)
    {
        String s = track1.getEvent();
        Props props = track1.getProperties();
        mixpanel.track(s, props);
        if(isMixpanelPeopleEnabled() && props != null && props.has("revenue"))
            mixpanel.getPeople().trackCharge(props.getDouble("revenue", Double.valueOf(0.0D)).doubleValue(), props);
    }

    public void validate(EasyJSONObject easyjsonobject)
        throws InvalidSettingsException
    {
        if(TextUtils.isEmpty(easyjsonobject.getString("token")))
            throw new InvalidSettingsException("token", "Mixpanel requires the token setting.");
        else
            return;
    }

    private static final Map traitsMapping = new HashMap() {

            
            {
                put("created", "$created");
                put("email", "$email");
                put("firstName", "$first_name");
                put("lastName", "$last_name");
                put("lastSeen", "$last_seen");
                put("name", "$name");
                put("username", "$username");
            }
    }
;
    private MixpanelAPI mixpanel;

}
