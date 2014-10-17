// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.newrelic.agent.android;

import android.content.Context;
import android.content.SharedPreferences;
import com.newrelic.agent.android.harvest.DataToken;
import com.newrelic.agent.android.harvest.DeviceInformation;
import com.newrelic.agent.android.harvest.Harvest;
import com.newrelic.agent.android.harvest.HarvestAdapter;
import com.newrelic.agent.android.harvest.HarvestConfiguration;
import com.newrelic.agent.android.logging.AgentLog;
import com.newrelic.agent.android.logging.AgentLogManager;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import org.json.*;

// Referenced classes of package com.newrelic.agent.android:
//            Agent

public class SavedState extends HarvestAdapter
{

    public SavedState(Context context)
    {
        prefs = context.getSharedPreferences(getPreferenceFileName(context.getPackageName()), 0);
        editor = prefs.edit();
        loadHarvestConfiguration();
    }

    private String getPreferenceFileName(String s)
    {
        return (new StringBuilder()).append("com.newrelic.android.agent.v1_").append(s).toString();
    }

    private boolean has(String s)
    {
        return prefs.contains(s);
    }

    public void clear()
    {
        lock.lock();
        editor.clear();
        editor.commit();
        configuration.setDefaultValues();
        lock.unlock();
        return;
        Exception exception;
        exception;
        lock.unlock();
        throw exception;
    }

    public float getActivityTraceMinUtilization()
    {
        if(activityTraceMinUtilization == null)
            activityTraceMinUtilization = getFloat("activityTraceMinUtilization");
        return activityTraceMinUtilization.floatValue();
    }

    public String getAgentVersion()
    {
        return getString("agentVersion");
    }

    public String getAndroidIdBugWorkAround()
    {
        return getString("androidIdBugWorkAround");
    }

    public String getAppToken()
    {
        return getString("appToken");
    }

    public boolean getBoolean(String s)
    {
        return prefs.getBoolean(s, false);
    }

    public String getCrossProcessId()
    {
        return getString("crossProcessId");
    }

    public int[] getDataToken()
    {
        int ai[] = new int[2];
        String s = getString("dataToken");
        if(s == null)
            return null;
        JSONTokener jsontokener;
        JSONArray jsonarray;
        try
        {
            jsontokener = new JSONTokener(s);
        }
        catch(JSONException jsonexception)
        {
            jsonexception.printStackTrace();
            return ai;
        }
        if(jsontokener == null)
            return null;
        jsonarray = (JSONArray)jsontokener.nextValue();
        if(jsonarray == null)
            return null;
        ai[0] = jsonarray.getInt(0);
        ai[1] = jsonarray.getInt(1);
        return ai;
    }

    public String getDisabledVersion()
    {
        return getString("NewRelicAgentDisabledVersion");
    }

    public int getErrorLimit()
    {
        return getInt("errorLimit");
    }

    public Float getFloat(String s)
    {
        if(!prefs.contains(s))
            return null;
        else
            return Float.valueOf((float)(int)(100F * prefs.getFloat(s, 0.0F)) / 100F);
    }

    public HarvestConfiguration getHarvestConfiguration()
    {
        return configuration;
    }

    public long getHarvestInterval()
    {
        return getLong("harvestIntervalInSeconds");
    }

    public long getHarvestIntervalInSeconds()
    {
        return getHarvestInterval();
    }

    public int getInt(String s)
    {
        return prefs.getInt(s, 0);
    }

    public long getLong(String s)
    {
        return prefs.getLong(s, 0L);
    }

    public long getMaxTransactionAge()
    {
        return getLong("maxTransactionAgeInSeconds");
    }

    public long getMaxTransactionAgeInSeconds()
    {
        return getMaxTransactionAge();
    }

    public long getMaxTransactionCount()
    {
        return getLong("maxTransactionCount");
    }

    public int getResponseBodyLimit()
    {
        return getInt("responseBodyLimit");
    }

    public long getServerTimestamp()
    {
        return getLong("serverTimestamp");
    }

    public int getStackTraceLimit()
    {
        return getInt("stackTraceLimit");
    }

    public String getString(String s)
    {
        if(!prefs.contains(s))
            return null;
        else
            return prefs.getString(s, null);
    }

    public boolean isCollectingNetworkErrors()
    {
        return getBoolean("collectNetworkErrors");
    }

    public void loadHarvestConfiguration()
    {
        if(has("dataToken"))
            configuration.setData_token(getDataToken());
        if(has("crossProcessId"))
            configuration.setCross_process_id(getCrossProcessId());
        if(has("serverTimestamp"))
            configuration.setServer_timestamp(getServerTimestamp());
        if(has("harvestIntervalInSeconds"))
            configuration.setData_report_period((int)getHarvestIntervalInSeconds());
        if(has("maxTransactionAgeInSeconds"))
            configuration.setReport_max_transaction_age((int)getMaxTransactionAgeInSeconds());
        if(has("maxTransactionCount"))
            configuration.setReport_max_transaction_count((int)getMaxTransactionCount());
        if(has("stackTraceLimit"))
            configuration.setStack_trace_limit(getStackTraceLimit());
        if(has("responseBodyLimit"))
            configuration.setResponse_body_limit(getResponseBodyLimit());
        if(has("collectNetworkErrors"))
            configuration.setCollect_network_errors(isCollectingNetworkErrors());
        if(has("errorLimit"))
            configuration.setError_limit(getErrorLimit());
        if(has("activityTraceMinUtilization"))
            configuration.setActivity_trace_min_utilization(getActivityTraceMinUtilization());
        log.info((new StringBuilder()).append("Loaded configuration: ").append(configuration).toString());
    }

    public void onHarvestComplete()
    {
        saveHarvestConfiguration(Harvest.getHarvestConfiguration());
    }

    public void onHarvestConnected()
    {
        saveHarvestConfiguration(Harvest.getHarvestConfiguration());
    }

    public void onHarvestDisabled()
    {
        String s = Agent.getDeviceInformation().getAgentVersion();
        log.info((new StringBuilder()).append("Disabling agent version ").append(s).toString());
        saveDisabledVersion(s);
    }

    public void onHarvestDisconnected()
    {
        log.info("Clearing harvest configuration.");
        clear();
    }

    public void save(String s, float f)
    {
        lock.lock();
        editor.putFloat(s, f);
        editor.commit();
        lock.unlock();
        return;
        Exception exception;
        exception;
        lock.unlock();
        throw exception;
    }

    public void save(String s, int i)
    {
        lock.lock();
        editor.putInt(s, i);
        editor.commit();
        lock.unlock();
        return;
        Exception exception;
        exception;
        lock.unlock();
        throw exception;
    }

    public void save(String s, long l)
    {
        lock.lock();
        editor.putLong(s, l);
        editor.commit();
        lock.unlock();
        return;
        Exception exception;
        exception;
        lock.unlock();
        throw exception;
    }

    public void save(String s, String s1)
    {
        lock.lock();
        editor.putString(s, s1);
        editor.commit();
        lock.unlock();
        return;
        Exception exception;
        exception;
        lock.unlock();
        throw exception;
    }

    public void save(String s, boolean flag)
    {
        lock.lock();
        editor.putBoolean(s, flag);
        editor.commit();
        lock.unlock();
        return;
        Exception exception;
        exception;
        lock.unlock();
        throw exception;
    }

    public void saveActivityTraceMinUtilization(float f)
    {
        activityTraceMinUtilization = Float.valueOf(f);
        save("activityTraceMinUtilization", f);
    }

    public void saveAgentVersion(String s)
    {
        save("agentVersion", s);
    }

    public void saveAndroidIdBugWorkAround(String s)
    {
        save("androidIdBugWorkAround", s);
    }

    public void saveAppToken(String s)
    {
        save("appToken", s);
    }

    public void saveCollectNetworkErrors(boolean flag)
    {
        save("collectNetworkErrors", flag);
    }

    public void saveCrossProcessId(String s)
    {
        save("crossProcessId", s);
    }

    public void saveDataToken(String s)
    {
        log.debug((new StringBuilder()).append("!! saving data token: ").append(s).toString());
        save("dataToken", s);
    }

    public void saveDisabledVersion(String s)
    {
        save("NewRelicAgentDisabledVersion", s);
    }

    public void saveErrorLimit(int i)
    {
        save("errorLimit", i);
    }

    public void saveHarvestConfiguration(HarvestConfiguration harvestconfiguration)
    {
        if(configuration.equals(harvestconfiguration))
            return;
        if(!harvestconfiguration.getDataToken().isValid())
            harvestconfiguration.setData_token(configuration.getData_token());
        log.info((new StringBuilder()).append("Saving configuration: ").append(harvestconfiguration).toString());
        saveDataToken(harvestconfiguration.getDataToken().toJsonString());
        saveCrossProcessId(harvestconfiguration.getCross_process_id());
        saveServerTimestamp(harvestconfiguration.getServer_timestamp());
        saveHarvestInterval(harvestconfiguration.getData_report_period());
        saveMaxTransactionAge(harvestconfiguration.getReport_max_transaction_age());
        saveMaxTransactionCount(harvestconfiguration.getReport_max_transaction_count());
        saveStackTraceLimit(harvestconfiguration.getStack_trace_limit());
        saveResponseBodyLimit(harvestconfiguration.getResponse_body_limit());
        saveCollectNetworkErrors(harvestconfiguration.isCollect_network_errors());
        saveErrorLimit(harvestconfiguration.getError_limit());
        saveActivityTraceMinUtilization((float)harvestconfiguration.getActivity_trace_min_utilization());
        loadHarvestConfiguration();
    }

    public void saveHarvestInterval(long l)
    {
        save("harvestIntervalInSeconds", l);
    }

    public void saveMaxTransactionAge(long l)
    {
        save("maxTransactionAgeInSeconds", l);
    }

    public void saveMaxTransactionCount(long l)
    {
        save("maxTransactionCount", l);
    }

    public void saveResponseBodyLimit(int i)
    {
        save("responseBodyLimit", i);
    }

    public void saveServerTimestamp(long l)
    {
        save("serverTimestamp", l);
    }

    public void saveStackTraceLimit(int i)
    {
        save("stackTraceLimit", i);
    }

    private static final AgentLog log = AgentLogManager.getAgentLog();
    private final String NEW_RELIC_AGENT_DISABLED_VERSION_KEY = "NewRelicAgentDisabledVersion";
    private final String PREFERENCE_FILE_PREFIX = "com.newrelic.android.agent.v1_";
    private final String PREF_ACTIVITY_TRACE_MIN_UTILIZATION = "activityTraceMinUtilization";
    private final String PREF_AGENT_VERSION = "agentVersion";
    private final String PREF_ANDROID_ID_BUG_WORK_AROUND = "androidIdBugWorkAround";
    private final String PREF_APP_TOKEN = "appToken";
    private final String PREF_COLLECT_NETWORK_ERRORS = "collectNetworkErrors";
    private final String PREF_CROSS_PROCESS_ID = "crossProcessId";
    private final String PREF_DATA_TOKEN = "dataToken";
    private final String PREF_ERROR_LIMIT = "errorLimit";
    private final String PREF_HARVEST_INTERVAL = "harvestIntervalInSeconds";
    private final String PREF_MAX_TRANSACTION_AGE = "maxTransactionAgeInSeconds";
    private final String PREF_MAX_TRANSACTION_COUNT = "maxTransactionCount";
    private final String PREF_RESPONSE_BODY_LIMIT = "responseBodyLimit";
    private final String PREF_SERVER_TIMESTAMP = "serverTimestamp";
    private final String PREF_STACK_TRACE_LIMIT = "stackTraceLimit";
    private Float activityTraceMinUtilization;
    private final HarvestConfiguration configuration = new HarvestConfiguration();
    private final android.content.SharedPreferences.Editor editor;
    private final Lock lock = new ReentrantLock();
    private final SharedPreferences prefs;

}
