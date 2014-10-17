// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.newrelic.agent.android.instrumentation;

import android.content.Context;
import android.content.res.AssetManager;
import com.newrelic.agent.android.AndroidAgentImpl;
import com.newrelic.agent.android.logging.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Bootstrap
{

    public Bootstrap()
    {
    }

    public static void go(Context context)
    {
        InputStream inputstream = context.getAssets().open("newrelic.properties");
        Properties properties;
        properties = new Properties();
        properties.load(inputstream);
        if(truthy(properties.getProperty("new_relic_enable_logging", "true"))) goto _L2; else goto _L1
_L1:
        AgentLogManager.setAgentLog(new NullAgentLog());
_L3:
        String s;
        String s1;
        log.debug("Trying to bootstrap New Relic");
        s = properties.getProperty("new_relic_collector", "mobile-collector.newrelic.com");
        s1 = properties.getProperty("new_relic_application_token", null);
        if(s1 == null)
            break MISSING_BLOCK_LABEL_296;
        boolean flag;
        boolean flag1;
        String s2;
        log.info("License key found! Starting the New Relic agent.");
        log.debug((new StringBuilder()).append("Collector host is ").append(s).toString());
        flag = truthy(properties.getProperty("new_relic_enable_ssl", "true"));
        flag1 = truthy(properties.getProperty("new_relic_enable_location", "false"));
        s2 = properties.getProperty("new_relic_application_name", null);
        if(!flag)
            break MISSING_BLOCK_LABEL_177;
        log.debug("SSL enabled");
        if(s2 == null)
            break MISSING_BLOCK_LABEL_210;
        log.debug((new StringBuilder()).append("App name override: ").append(s2).toString());
        AndroidAgentImpl.init(context, s1, s, flag, flag1, s2);
_L4:
        IOException ioexception;
        try
        {
            inputstream.close();
            return;
        }
        catch(Throwable throwable)
        {
            log.error("Error occurred while starting the New Relic agent!", throwable);
        }
        break MISSING_BLOCK_LABEL_273;
        ioexception;
        AgentLogManager.setAgentLog(new AndroidAgentLog());
        log.error("Could not open assets/newrelic.properties! New Relic will not start.");
        AgentLogManager.setAgentLog(new NullAgentLog());
        return;
        return;
_L2:
        AgentLogManager.setAgentLog(new AndroidAgentLog());
          goto _L3
        Exception exception;
        exception;
        inputstream.close();
        throw exception;
        log.warning("No licenase key found: New Relic will not start");
          goto _L4
    }

    private static boolean truthy(String s)
    {
        return "on".equals(s) || "1".equals(s) || "true".equals(s) || "yes".equals(s);
    }

    private static final String CONF_NEW_RELIC_APPLICATION_NAME = "new_relic_application_name";
    public static final String CONF_NEW_RELIC_APPLICATION_TOKEN = "new_relic_application_token";
    private static final String CONF_NEW_RELIC_COLLECTOR = "new_relic_collector";
    private static final String CONF_NEW_RELIC_ENABLE_LOCATION = "new_relic_enable_location";
    private static final String CONF_NEW_RELIC_ENABLE_LOGGING = "new_relic_enable_logging";
    private static final String CONF_NEW_RELIC_ENABLE_SSL = "new_relic_enable_ssl";
    private static final String DEFAULT_COLLECTOR_ADDR = "mobile-collector.newrelic.com";
    private static final AgentLog log = AgentLogManager.getAgentLog();

}
