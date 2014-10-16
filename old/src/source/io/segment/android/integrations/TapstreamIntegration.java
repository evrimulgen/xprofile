// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package io.segment.android.integrations;

import android.content.Context;
import android.text.TextUtils;
import com.tapstream.sdk.*;
import io.segment.android.errors.InvalidSettingsException;
import io.segment.android.integration.SimpleIntegration;
import io.segment.android.models.*;
import java.util.Iterator;
import org.OpenUDID.OpenUDID_manager;

public class TapstreamIntegration extends SimpleIntegration
{
    private static class SettingKey
    {

        private static final String ACCOUNT_NAME = "accountName";
        private static final String SDK_SECRET = "sdkSecret";

        private SettingKey()
        {
        }
    }


    public TapstreamIntegration()
    {
    }

    private void initialize(Context context)
    {
        EasyJSONObject easyjsonobject = getSettings();
        String s = easyjsonobject.getString("accountName");
        String s1 = easyjsonobject.getString("sdkSecret");
        Config config = new Config();
        config.setOpenUdid(OpenUDID_manager.getOpenUDID());
        Tapstream.create(context, s, s1, config);
        ready();
    }

    private Event makeEvent(String s, Props props)
    {
        Event event = new Event(s, false);
        if(props == null) goto _L2; else goto _L1
_L1:
        Iterator iterator = props.keys();
_L5:
        if(iterator.hasNext()) goto _L3; else goto _L2
_L2:
        return event;
_L3:
        String s1 = (String)iterator.next();
        event.addPair(s1, props.get(s1));
        if(true) goto _L5; else goto _L4
_L4:
    }

    public String getKey()
    {
        return "Tapstream";
    }

    public void onCreate(Context context)
    {
        initialize(context);
    }

    public void screen(Screen screen1)
    {
        String s = screen1.getName();
        Tapstream.getInstance().fireEvent(makeEvent((new StringBuilder("screen-")).append(s).toString(), screen1.getProperties()));
    }

    public void track(Track track1)
    {
        String s = track1.getEvent();
        Tapstream.getInstance().fireEvent(makeEvent(s, track1.getProperties()));
    }

    public void validate(EasyJSONObject easyjsonobject)
        throws InvalidSettingsException
    {
        if(TextUtils.isEmpty(easyjsonobject.getString("accountName")))
            throw new InvalidSettingsException("accountName", "accountName required.");
        if(TextUtils.isEmpty(easyjsonobject.getString("sdkSecret")))
            throw new InvalidSettingsException("sdkSecret", "sdkSecret required.");
        else
            return;
    }
}
