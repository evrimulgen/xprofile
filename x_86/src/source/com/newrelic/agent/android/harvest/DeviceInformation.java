// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.newrelic.agent.android.harvest;

import com.newrelic.agent.android.harvest.type.HarvestableArray;
import com.newrelic.com.google.gson.*;
import java.util.*;

public class DeviceInformation extends HarvestableArray
{

    public DeviceInformation()
    {
        misc = new HashMap();
    }

    public void addMisc(String s, String s1)
    {
        misc.put(s, s1);
    }

    public JsonArray asJsonArray()
    {
        JsonArray jsonarray = new JsonArray();
        notEmpty(osName);
        jsonarray.add(new JsonPrimitive(osName));
        notEmpty(osVersion);
        jsonarray.add(new JsonPrimitive(osVersion));
        notEmpty(manufacturer);
        notEmpty(model);
        jsonarray.add(new JsonPrimitive((new StringBuilder()).append(manufacturer).append(" ").append(model).toString()));
        notEmpty(agentName);
        jsonarray.add(new JsonPrimitive(agentName));
        notEmpty(agentVersion);
        jsonarray.add(new JsonPrimitive(agentVersion));
        notEmpty(deviceId);
        jsonarray.add(new JsonPrimitive(deviceId));
        jsonarray.add(new JsonPrimitive(optional(countryCode)));
        jsonarray.add(new JsonPrimitive(optional(regionCode)));
        jsonarray.add(new JsonPrimitive(manufacturer));
        if(misc == null || misc.isEmpty())
            misc = Collections.emptyMap();
        jsonarray.add((new Gson()).toJsonTree(misc, GSON_STRING_MAP_TYPE));
        return jsonarray;
    }

    public String getAgentName()
    {
        return agentName;
    }

    public String getAgentVersion()
    {
        return agentVersion;
    }

    public String getCountryCode()
    {
        return countryCode;
    }

    public String getDeviceId()
    {
        return deviceId;
    }

    public String getManufacturer()
    {
        return manufacturer;
    }

    public String getModel()
    {
        return model;
    }

    public String getOsName()
    {
        return osName;
    }

    public String getOsVersion()
    {
        return osVersion;
    }

    public String getRegionCode()
    {
        return regionCode;
    }

    public void setAgentName(String s)
    {
        agentName = s;
    }

    public void setAgentVersion(String s)
    {
        agentVersion = s;
    }

    public void setCountryCode(String s)
    {
        countryCode = s;
    }

    public void setDeviceId(String s)
    {
        deviceId = s;
    }

    public void setManufacturer(String s)
    {
        manufacturer = s;
    }

    public void setMisc(Map map)
    {
        misc = new HashMap(map);
    }

    public void setModel(String s)
    {
        model = s;
    }

    public void setOsName(String s)
    {
        osName = s;
    }

    public void setOsVersion(String s)
    {
        osVersion = s;
    }

    public void setRegionCode(String s)
    {
        regionCode = s;
    }

    public String toJsonString()
    {
        return (new StringBuilder()).append("DeviceInformation{manufacturer='").append(manufacturer).append('\'').append(", osName='").append(osName).append('\'').append(", osVersion='").append(osVersion).append('\'').append(", model='").append(model).append('\'').append(", agentName='").append(agentName).append('\'').append(", agentVersion='").append(agentVersion).append('\'').append(", deviceId='").append(deviceId).append('\'').append(", countryCode='").append(countryCode).append('\'').append(", regionCode='").append(regionCode).append('\'').append('}').toString();
    }

    private String agentName;
    private String agentVersion;
    private String countryCode;
    private String deviceId;
    private String manufacturer;
    private Map misc;
    private String model;
    private String osName;
    private String osVersion;
    private String regionCode;
}
