// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.adobe.adms.measurement;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

// Referenced classes of package com.adobe.adms.measurement:
//            ADMS_MeasurementBase

abstract class ADMS_ChurnBase
{

    protected ADMS_ChurnBase(ADMS_MeasurementBase adms_measurementbase)
    {
        appInstallEvent = "a.InstallEvent";
        appUpgradeEvent = "a.UpgradeEvent";
        dailyEngagedUserEvent = "a.DailyEngUserEvent";
        monthlyEngagedUserEvent = "a.MonthlyEngUserEvent";
        appLaunchEvent = "a.LaunchEvent";
        appCrashEvent = "a.CrashEvent";
        appInstallDateEvar = "a.InstallDate";
        appIdEvar = "a.AppID";
        engagedDaysLifetimeEvar = "a.EngDaysLifetime";
        daysSinceFirstUseEvar = "a.DaysSinceFirstUse";
        daysSinceLastUseEvar = "a.DaysSinceLastUse";
        appLaunchNumberEvar = "a.Launches";
        hourOfDayEvar = "a.HourOfDay";
        dayOfWeekEvar = "a.DayOfWeek";
        appEnvironmentEvar = "a.OSEnvironment";
        daysSinceLastUpgradeEvar = "a.DaysSinceLastUpgrade";
        appLaunchNumberSinceLastUpgradeEvar = "a.LaunchesSinceUpgrade";
        lifecycleSessionTimeout = 300;
        contextData = new Hashtable();
        eventList = new ArrayList();
        variables = new Hashtable();
        currentAppVersion = null;
        appId = null;
        currentDate = null;
        measurementBase = null;
        dayMonthYearFormat = new SimpleDateFormat("M/d/yyyy");
        hourOfDayDateFormat = new SimpleDateFormat("H");
        monthYearDateFormat = new SimpleDateFormat("M/yyyy");
        measurementBase = adms_measurementbase;
    }

    private void cleanInstanceVariables()
    {
        variables = new Hashtable();
        contextData = new Hashtable();
        eventList = new ArrayList();
        currentAppVersion = getApplicationVersion();
        appId = (new StringBuilder()).append(getApplicationName()).append("(").append(currentAppVersion).append(")").toString();
        currentDate = new Date();
    }

    private String dayMonthYearFromDate(Date date)
    {
        return dayMonthYearFormat.format(date);
    }

    private String dayOfWeekFromDate(Date date)
    {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return Integer.toString(calendar.get(7));
    }

    private int daysBetween(Date date, Date date1)
    {
        return (int)((date1.getTime() - date.getTime()) / 0x5265c00L);
    }

    private Date getDateFromPrefs(String s)
    {
        return new Date(prefsGetLong(s, 0L));
    }

    private void handleEvents()
    {
        String s;
        for(Iterator iterator = eventList.iterator(); iterator.hasNext(); setContextDataValue(s.replace("a.", ""), s))
            s = (String)iterator.next();

    }

    private void handleSessionLength()
    {
        if(prefsContains("ADMS_PauseDate"))
        {
            Date date = getDateFromPrefs("ADMS_PauseDate");
            if(date != null)
            {
                Date date1 = getDateFromPrefs("ADMS_SessionStart");
                if(secondsBetween(date, new Date()) > lifecycleSessionTimeout)
                {
                    int i = secondsBetween(date1, date);
                    if(i > 0)
                        setContextDataValue((new StringBuilder()).append("").append(i).toString(), "a.PrevSessionLength");
                    removeObjectFromPrefsWithKey("ADMS_SessionStart");
                }
            }
        }
    }

    private void handleUpgradeDateForConfigType(int i)
    {
        if(prefsContains("ADMS_UpgradeDate"))
        {
            int j = 1 + prefsGetInt("ADMS_LaunchesAfterUpgrade", 0);
            setContextDataValue((new StringBuilder()).append("").append(j).toString(), appLaunchNumberSinceLastUpgradeEvar);
            if(i == 2)
            {
                int k = daysBetween(getDateFromPrefs("ADMS_UpgradeDate"), currentDate);
                setContextDataValue((new StringBuilder()).append("").append(k).toString(), daysSinceLastUpgradeEvar);
            }
            prefsPutInt("ADMS_LaunchesAfterUpgrade", j);
        }
    }

    private String hourFromDate(Date date)
    {
        return hourOfDayDateFormat.format(date);
    }

    private String monthYearFromDate(Date date)
    {
        return monthYearDateFormat.format(date);
    }

    private void putDateIntoPrefs(Date date, String s)
    {
        prefsPutLong(s, date.getTime());
    }

    private int secondsBetween(Date date, Date date1)
    {
        return (int)((date1.getTime() - date.getTime()) / 1000L);
    }

    private boolean sessionPauseDetected()
    {
        boolean flag = prefsContains("ADMS_PauseDate");
        boolean flag1 = false;
        if(flag)
        {
            Date date = getDateFromPrefs("ADMS_PauseDate");
            flag1 = false;
            if(date != null)
            {
                int i = secondsBetween(date, new Date());
                Date date1 = getDateFromPrefs("ADMS_SessionStart");
                int j = lifecycleSessionTimeout;
                flag1 = false;
                if(i < j)
                {
                    flag1 = false;
                    if(date1 != null)
                    {
                        Calendar calendar = Calendar.getInstance();
                        calendar.setTime(date1);
                        calendar.add(13, i);
                        putDateIntoPrefs(calendar.getTime(), "ADMS_SessionStart");
                        removeObjectFromPrefsWithKey("ADMS_PauseDate");
                        prefsPutBool("ADMS_SuccessfulClose", false);
                        flag1 = true;
                    }
                }
            }
        }
        return flag1;
    }

    private void setEvent(String s)
    {
        if(measurementBase.isSet(s))
            eventList.add(s);
    }

    private void setInstallVariables()
    {
        setVariable((new StringBuilder()).append(appId).append(" Install").toString(), "pageName");
        setContextDataValue(dayMonthYearFromDate(currentDate), appInstallDateEvar);
        setContextDataValue("+1", engagedDaysLifetimeEvar);
        handleReferrers();
        setEvent(appInstallEvent);
        setEvent(dailyEngagedUserEvent);
        setEvent(monthlyEngagedUserEvent);
        putDateIntoPrefs(currentDate, "ADMS_InstallDate");
    }

    private void setLaunchTypeVariables()
    {
        if(!prefsContains("ADMS_InstallDate"))
        {
            setInstallVariables();
            handleUpgradeDateForConfigType(0);
        } else
        if(prefsContains("ADMS_LastVersion"))
            if(prefsGetString("ADMS_LastVersion", null).equalsIgnoreCase(currentAppVersion))
            {
                setLaunchVariables();
                setNotInstallVariables();
                handleUpgradeDateForConfigType(2);
                return;
            } else
            {
                setUpgradeVariables();
                setNotInstallVariables();
                handleUpgradeDateForConfigType(1);
                return;
            }
    }

    private void setLaunchVariables()
    {
        setVariable((new StringBuilder()).append(appId).append(" Launch").toString(), "pageName");
    }

    private void setNotInstallVariables()
    {
        Date date = getDateFromPrefs("ADMS_LastDateUsed");
        if(!dayMonthYearFromDate(date).equalsIgnoreCase(dayMonthYearFromDate(currentDate)))
        {
            setContextDataValue("+1", engagedDaysLifetimeEvar);
            setEvent(dailyEngagedUserEvent);
        }
        if(!monthYearFromDate(date).equals(monthYearFromDate(currentDate)))
            setEvent(monthlyEngagedUserEvent);
        Date date1 = getDateFromPrefs("ADMS_InstallDate");
        setContextDataValue((new StringBuilder()).append("").append(daysBetween(date1, currentDate)).toString(), daysSinceFirstUseEvar);
        setContextDataValue((new StringBuilder()).append("").append(daysBetween(date, currentDate)).toString(), daysSinceLastUseEvar);
        if(!prefsGetBool("ADMS_SuccessfulClose", false))
        {
            setEvent(appCrashEvent);
            removeObjectFromPrefsWithKey("ADMS_PauseDate");
            removeObjectFromPrefsWithKey("ADMS_SessionStart");
        }
    }

    private void setUpgradeVariables()
    {
        setVariable((new StringBuilder()).append(appId).append(" Upgrade").toString(), "pageName");
        setEvent(appUpgradeEvent);
        prefsPutInt("ADMS_LaunchesAfterUpgrade", 0);
        putDateIntoPrefs(currentDate, "ADMS_UpgradeDate");
    }

    private void setVariable(String s, String s1)
    {
        if(measurementBase.isSet(s) && measurementBase.isSet(s1))
            variables.put(s1, s);
    }

    protected abstract String getApplicationName();

    protected abstract String getApplicationVersion();

    protected abstract void handleReferrers();

    protected abstract boolean prefsContains(String s);

    protected abstract boolean prefsGetBool(String s, boolean flag);

    protected abstract int prefsGetInt(String s, int i);

    protected abstract long prefsGetLong(String s, long l);

    protected abstract String prefsGetString(String s, String s1);

    protected abstract void prefsPutBool(String s, boolean flag);

    protected abstract void prefsPutInt(String s, int i);

    protected abstract void prefsPutLong(String s, long l);

    protected abstract void prefsPutString(String s, String s1);

    protected abstract void removeObjectFromPrefsWithKey(String s);

    protected void setContextDataValue(String s, String s1)
    {
        if(measurementBase.isSet(s) && measurementBase.isSet(s1))
            contextData.put(s1, s);
    }

    protected void setGenericVariables()
    {
        setEvent(appLaunchEvent);
        int i = 1 + prefsGetInt("ADMS_Launches", 0);
        setContextDataValue((new StringBuilder()).append("").append(i).toString(), appLaunchNumberEvar);
        setContextDataValue(appId, appIdEvar);
        setContextDataValue(hourFromDate(currentDate), hourOfDayEvar);
        setContextDataValue(dayOfWeekFromDate(currentDate), dayOfWeekEvar);
        putDateIntoPrefs(currentDate, "ADMS_LastDateUsed");
        prefsPutInt("ADMS_Launches", i);
    }

    protected final void startSession()
    {
        if(sessionPauseDetected())
            return;
        cleanInstanceVariables();
        setLaunchTypeVariables();
        setGenericVariables();
        handleEvents();
        handleSessionLength();
        measurementBase.trackLink(null, "o", "ADMS BP Event", contextData, variables);
        if(!prefsContains("ADMS_SessionStart"))
            putDateIntoPrefs(currentDate, "ADMS_SessionStart");
        prefsPutString("ADMS_LastVersion", currentAppVersion);
        prefsPutBool("ADMS_SuccessfulClose", false);
        removeObjectFromPrefsWithKey("ADMS_PauseDate");
    }

    protected final void stopSession()
    {
        putDateIntoPrefs(new Date(), "ADMS_PauseDate");
        prefsPutBool("ADMS_SuccessfulClose", true);
    }

    private static final int kADMS_ConfigTypeInstall = 0;
    private static final int kADMS_ConfigTypeLaunch = 2;
    private static final int kADMS_ConfigTypeUpgrade = 1;
    protected static final String kADMS_InstallDate = "ADMS_InstallDate";
    protected static final String kADMS_LastDateUsed = "ADMS_LastDateUsed";
    protected static final String kADMS_LastVersion = "ADMS_LastVersion";
    protected static final String kADMS_Launches = "ADMS_Launches";
    protected static final String kADMS_LaunchesAfterUpgrade = "ADMS_LaunchesAfterUpgrade";
    protected static final String kADMS_PauseDate = "ADMS_PauseDate";
    protected static final String kADMS_PrevSessionLength = "a.PrevSessionLength";
    protected static final String kADMS_SessionStart = "ADMS_SessionStart";
    protected static final String kADMS_SuccessfulClose = "ADMS_SuccessfulClose";
    protected static final String kADMS_UpgradeDate = "ADMS_UpgradeDate";
    private String appCrashEvent;
    protected String appEnvironmentEvar;
    private String appId;
    private String appIdEvar;
    private String appInstallDateEvar;
    private String appInstallEvent;
    private String appLaunchEvent;
    private String appLaunchNumberEvar;
    private String appLaunchNumberSinceLastUpgradeEvar;
    private String appUpgradeEvent;
    private Hashtable contextData;
    private String currentAppVersion;
    private Date currentDate;
    private String dailyEngagedUserEvent;
    private DateFormat dayMonthYearFormat;
    private String dayOfWeekEvar;
    private String daysSinceFirstUseEvar;
    private String daysSinceLastUpgradeEvar;
    private String daysSinceLastUseEvar;
    private String engagedDaysLifetimeEvar;
    private ArrayList eventList;
    private DateFormat hourOfDayDateFormat;
    private String hourOfDayEvar;
    protected int lifecycleSessionTimeout;
    private ADMS_MeasurementBase measurementBase;
    private DateFormat monthYearDateFormat;
    private String monthlyEngagedUserEvent;
    private Hashtable variables;
}
