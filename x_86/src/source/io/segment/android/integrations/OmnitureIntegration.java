// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package io.segment.android.integrations;

import android.app.Activity;
import android.text.TextUtils;
import com.adobe.adms.measurement.ADMS_Measurement;
import io.segment.android.Logger;
import io.segment.android.errors.InvalidSettingsException;
import io.segment.android.integration.SimpleIntegration;
import io.segment.android.models.Context;
import io.segment.android.models.EasyJSONObject;
import io.segment.android.models.Screen;
import io.segment.android.models.Track;
import java.util.Hashtable;
import java.util.Iterator;

public class OmnitureIntegration extends SimpleIntegration
{
    private static class SettingKey
    {

        private static final String REPORT_SUITE_ID = "reportSuiteId";
        private static final String TRACKING_SERVER = "trackingServerUrl";

        private SettingKey()
        {
        }
    }


    public OmnitureIntegration()
    {
    }

    private void initialize(android.content.Context context)
    {
        EasyJSONObject easyjsonobject = getSettings();
        String s = easyjsonobject.getString("reportSuiteId");
        String s1 = easyjsonobject.getString("trackingServerUrl");
        ADMS_Measurement.sharedInstance(context).configureMeasurement(s, s1);
        ready();
    }

    public String getKey()
    {
        return "Omniture";
    }

    public void onActivityStart(Activity activity)
    {
        ADMS_Measurement.sharedInstance(activity).startActivity(activity);
    }

    public void onActivityStop(Activity activity)
    {
        ADMS_Measurement.sharedInstance().stopActivity();
    }

    public void onCreate(android.content.Context context)
    {
        initialize(context);
    }

    public void screen(Screen screen1)
    {
        track(screen1);
    }

    public Hashtable toObjectHashtable(EasyJSONObject easyjsonobject)
    {
        Hashtable hashtable = new Hashtable();
        Iterator iterator = easyjsonobject.keys();
        do
        {
            if(!iterator.hasNext())
                return hashtable;
            String s = (String)iterator.next();
            hashtable.put(s, easyjsonobject.get(s));
        } while(true);
    }

    public void track(Track track1)
    {
        if(!track1.getContext().isProviderStrictlyEnabled(getKey()))
        {
            Logger.d("Omniture track not triggered because context.providers.Omniture not set to true.");
            return;
        } else
        {
            String s = track1.getEvent();
            io.segment.android.models.Props props = track1.getProperties();
            ADMS_Measurement.sharedInstance().trackEvents(s, toObjectHashtable(props));
            return;
        }
    }

    public void validate(EasyJSONObject easyjsonobject)
        throws InvalidSettingsException
    {
        if(TextUtils.isEmpty(easyjsonobject.getString("reportSuiteId")))
            throw new InvalidSettingsException("reportSuiteId", "Omniture requires the reportSuiteId setting.");
        if(TextUtils.isEmpty(easyjsonobject.getString("trackingServerUrl")))
            throw new InvalidSettingsException("trackingServerUrl", "Omniture requires the trackingServer setting.");
        else
            return;
    }
}
