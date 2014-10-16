// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.newrelic.agent.android.harvest;

import com.newrelic.agent.android.Agent;
import com.newrelic.agent.android.harvest.type.HarvestableArray;
import com.newrelic.com.google.gson.JsonArray;

// Referenced classes of package com.newrelic.agent.android.harvest:
//            ApplicationInformation, DeviceInformation

public class ConnectInformation extends HarvestableArray
{

    public ConnectInformation()
    {
        setApplicationInformation(Agent.getApplicationInformation());
        setDeviceInformation(Agent.getDeviceInformation());
    }

    public JsonArray asJsonArray()
    {
        JsonArray jsonarray = new JsonArray();
        notNull(applicationInformation);
        jsonarray.add(applicationInformation.asJsonArray());
        notNull(deviceInformation);
        jsonarray.add(deviceInformation.asJsonArray());
        return jsonarray;
    }

    public void setApplicationInformation(ApplicationInformation applicationinformation)
    {
        applicationInformation = applicationinformation;
    }

    public void setDeviceInformation(DeviceInformation deviceinformation)
    {
        deviceInformation = deviceinformation;
    }

    private ApplicationInformation applicationInformation;
    private DeviceInformation deviceInformation;
}
