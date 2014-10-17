// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package io.segment.android.integrations;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import com.quantcast.measurement.service.QuantcastClient;
import io.segment.android.Logger;
import io.segment.android.errors.InvalidSettingsException;
import io.segment.android.integration.SimpleIntegration;
import io.segment.android.models.*;

public class QuantcastIntegration extends SimpleIntegration
{
    private static class SettingKey
    {

        private static final String API_KEY = "apiKey";

        private SettingKey()
        {
        }
    }


    public QuantcastIntegration()
    {
    }

    public String getKey()
    {
        return "Quantcast";
    }

    public String[] getRequiredPermissions()
    {
        return (new String[] {
            "android.permission.ACCESS_NETWORK_STATE"
        });
    }

    public void identify(Identify identify1)
    {
        if(!hasPermission)
        {
            return;
        } else
        {
            QuantcastClient.recordUserIdentifier(identify1.getUserId());
            return;
        }
    }

    public void onActivityStart(Activity activity)
    {
        if(!checkPermission(activity))
        {
            return;
        } else
        {
            QuantcastClient.activityStart(activity, apiKey, null, null);
            return;
        }
    }

    public void onActivityStop(Activity activity)
    {
        if(!checkPermission(activity))
        {
            return;
        } else
        {
            QuantcastClient.activityStop();
            return;
        }
    }

    public void onCreate(Context context)
    {
        checkPermission(context);
        apiKey = getSettings().getString("apiKey");
        QuantcastClient.enableLogging(Logger.isLogging());
        ready();
    }

    public void screen(Screen screen1)
    {
        if(!hasPermission)
        {
            return;
        } else
        {
            track(screen1);
            return;
        }
    }

    public void track(Track track1)
    {
        if(!hasPermission)
        {
            return;
        } else
        {
            QuantcastClient.logEvent(track1.getEvent());
            return;
        }
    }

    public void validate(EasyJSONObject easyjsonobject)
        throws InvalidSettingsException
    {
        if(TextUtils.isEmpty(easyjsonobject.getString("apiKey")))
            throw new InvalidSettingsException("apiKey", "Quantcast requires the apiKey setting.");
        else
            return;
    }

    private String apiKey;
}
