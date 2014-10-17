// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.newrelic.agent.android.harvest;

import com.newrelic.agent.android.harvest.type.HarvestableArray;
import com.newrelic.com.google.gson.JsonArray;
import com.newrelic.com.google.gson.JsonPrimitive;

public class ApplicationInformation extends HarvestableArray
{

    public ApplicationInformation()
    {
    }

    public ApplicationInformation(String s, String s1, String s2)
    {
        this();
        appName = s;
        appVersion = s1;
        packageId = s2;
    }

    public JsonArray asJsonArray()
    {
        JsonArray jsonarray = new JsonArray();
        notEmpty(appName);
        jsonarray.add(new JsonPrimitive(appName));
        notEmpty(appVersion);
        jsonarray.add(new JsonPrimitive(appVersion));
        notEmpty(packageId);
        jsonarray.add(new JsonPrimitive(packageId));
        return jsonarray;
    }

    public String getPackageId()
    {
        return packageId;
    }

    public void setAppName(String s)
    {
        appName = s;
    }

    public void setAppVersion(String s)
    {
        appVersion = s;
    }

    public void setPackageId(String s)
    {
        packageId = s;
    }

    private String appName;
    private String appVersion;
    private String packageId;
}
