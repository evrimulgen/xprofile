// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.newrelic.agent.android.harvest;

import com.newrelic.agent.android.Agent;
import com.newrelic.agent.android.harvest.type.HarvestableArray;
import com.newrelic.agent.android.stats.StatsEngine;
import com.newrelic.com.google.gson.*;

// Referenced classes of package com.newrelic.agent.android.harvest:
//            DataToken, HttpTransactions, HttpErrors, ActivityTraces, 
//            MachineMeasurements, AgentHealth, DeviceInformation, Harvest, 
//            HarvestConfiguration

public class HarvestData extends HarvestableArray
{

    public HarvestData()
    {
        dataToken = new DataToken();
        httpTransactions = new HttpTransactions();
        httpErrors = new HttpErrors();
        activityTraces = new ActivityTraces();
        machineMeasurements = new MachineMeasurements();
        deviceInformation = Agent.getDeviceInformation();
        agentHealth = new AgentHealth();
    }

    public JsonArray asJsonArray()
    {
        JsonArray jsonarray = new JsonArray();
        jsonarray.add(dataToken.asJson());
        jsonarray.add(deviceInformation.asJson());
        jsonarray.add(new JsonPrimitive(Double.valueOf(harvestTimeDelta)));
        jsonarray.add(httpTransactions.asJson());
        jsonarray.add(machineMeasurements.asJson());
        jsonarray.add(httpErrors.asJson());
        JsonElement jsonelement = activityTraces.asJson();
        String s = jsonelement.toString();
        if(s.length() < Harvest.getHarvestConfiguration().getActivity_trace_max_size())
            jsonarray.add(jsonelement);
        else
            StatsEngine.get().sample("Supportability/AgentHealth/BigActivityTracesDropped", s.length());
        jsonarray.add(agentHealth.asJson());
        return jsonarray;
    }

    public ActivityTraces getActivityTraces()
    {
        return activityTraces;
    }

    public AgentHealth getAgentHealth()
    {
        return agentHealth;
    }

    public DataToken getDataToken()
    {
        return dataToken;
    }

    public DeviceInformation getDeviceInformation()
    {
        return deviceInformation;
    }

    public HttpErrors getHttpErrors()
    {
        return httpErrors;
    }

    public HttpTransactions getHttpTransactions()
    {
        return httpTransactions;
    }

    public MachineMeasurements getMetrics()
    {
        return machineMeasurements;
    }

    public void reset()
    {
        httpErrors.clear();
        httpTransactions.clear();
        activityTraces.clear();
        machineMeasurements.clear();
        agentHealth.clear();
    }

    public void setActivityTraces(ActivityTraces activitytraces)
    {
        activityTraces = activitytraces;
    }

    public void setDataToken(DataToken datatoken)
    {
        if(datatoken == null)
        {
            return;
        } else
        {
            dataToken = datatoken;
            return;
        }
    }

    public void setDeviceInformation(DeviceInformation deviceinformation)
    {
        deviceInformation = deviceinformation;
    }

    public void setHarvestTimeDelta(double d)
    {
        harvestTimeDelta = d;
    }

    public void setHttpErrors(HttpErrors httperrors)
    {
        httpErrors = httperrors;
    }

    public void setHttpTransactions(HttpTransactions httptransactions)
    {
        httpTransactions = httptransactions;
    }

    public void setMachineMeasurements(MachineMeasurements machinemeasurements)
    {
        machineMeasurements = machinemeasurements;
    }

    public String toString()
    {
        return (new StringBuilder()).append("HarvestData{dataToken=").append(dataToken).append(", deviceInformation=").append(deviceInformation).append(", harvestTimeDelta=").append(harvestTimeDelta).append(", httpTransactions=").append(httpTransactions).append(", machineMeasurements=").append(machineMeasurements).append(", httpErrors=").append(httpErrors).append(", activityTraces=").append(activityTraces).append('}').toString();
    }

    private ActivityTraces activityTraces;
    private AgentHealth agentHealth;
    private DataToken dataToken;
    private DeviceInformation deviceInformation;
    private double harvestTimeDelta;
    private HttpErrors httpErrors;
    private HttpTransactions httpTransactions;
    private MachineMeasurements machineMeasurements;
}
