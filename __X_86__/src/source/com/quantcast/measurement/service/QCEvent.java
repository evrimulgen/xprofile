// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.quantcast.measurement.service;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Point;
import android.os.Build;
import android.telephony.TelephonyManager;
import android.view.Display;
import android.view.WindowManager;
import java.io.File;
import java.lang.reflect.Field;
import java.util.*;

// Referenced classes of package com.quantcast.measurement.service:
//            QCReachability, QCReferrerReceiver, QCUtility, QCLog, 
//            QCPolicy

class QCEvent
{

    QCEvent(Long long1)
    {
        m_parameters = new HashMap();
        m_eventId = Long.toString(long1.longValue());
        m_forceUpload = false;
    }

    QCEvent(String s)
    {
        m_parameters = new HashMap();
        addParameter("et", Long.toString(System.currentTimeMillis() / 1000L));
        addParameter("sid", s);
        m_eventId = null;
    }

    static QCEvent beginSessionEvent(Context context, String s, String s1, String s2, String s3, String s4, String s5, String as[], 
            String as1[])
    {
        QCEvent qcevent;
        String s6;
        PackageManager packagemanager;
        qcevent = new QCEvent(s2);
        qcevent.addParameter("event", "load");
        qcevent.addParameter("nsr", s1);
        qcevent.addParameter("apikey", s3);
        qcevent.addParameter("media", "app");
        qcevent.addParameter("ct", QCReachability.networkType(context));
        qcevent.addParameter("pcode", s4);
        qcevent.addParameter("r", QCReferrerReceiver.referrer);
        qcevent.addParameter("did", s5);
        qcevent.addParameter("aid", QCUtility.getAppInstallId(context));
        qcevent.addParameter("aname", QCUtility.getAppName(context));
        qcevent.addParameter("uh", s);
        s6 = context.getPackageName();
        qcevent.addParameter("pkid", s6);
        packagemanager = context.getPackageManager();
        if(packagemanager == null)
            break MISSING_BLOCK_LABEL_217;
        PackageInfo packageinfo = packagemanager.getPackageInfo(s6, 0);
        if(packageinfo == null)
            break MISSING_BLOCK_LABEL_217;
        int j;
        qcevent.addParameter("aver", packageinfo.versionName);
        qcevent.addParameter("iver", Integer.toString(packageinfo.versionCode));
        j = android.os.Build.VERSION.SDK_INT;
        if(j < 9)
            break MISSING_BLOCK_LABEL_720;
        qcevent.addParameter("inst", String.valueOf(android/content/pm/PackageInfo.getField("firstInstallTime").getLong(packageinfo)));
_L1:
        WindowManager windowmanager = (WindowManager)context.getSystemService("window");
        boolean flag;
        String s7;
        if(windowmanager != null)
        {
            Display display = windowmanager.getDefaultDisplay();
            TimeZone timezone;
            Date date;
            TelephonyManager telephonymanager;
            int i;
            Locale locale;
            String s9;
            String s10;
            String s11;
            android.content.pm.PackageManager.NameNotFoundException namenotfoundexception;
            Exception exception;
            if(android.os.Build.VERSION.SDK_INT >= 13)
            {
                Point point = new Point();
                display.getSize(point);
                Object aobj[] = new Object[2];
                aobj[0] = Integer.valueOf(point.x);
                aobj[1] = Integer.valueOf(point.y);
                s11 = String.format("%dx%dx32", aobj);
            } else
            {
                Object aobj1[] = new Object[2];
                aobj1[0] = Integer.valueOf(display.getWidth());
                aobj1[1] = Integer.valueOf(display.getHeight());
                s11 = String.format("%dx%dx32", aobj1);
            }
            qcevent.addParameter("sr", s11);
        }
        timezone = TimeZone.getDefault();
        date = new Date();
        qcevent.addParameter("dst", Boolean.toString(timezone.inDaylightTime(date)));
        qcevent.addParameter("tz", Long.toString(timezone.getOffset(date.getTime()) / 1000 / 60));
        telephonymanager = (TelephonyManager)context.getSystemService("phone");
        if(telephonymanager != null)
        {
            String s8 = telephonymanager.getNetworkOperator();
            if(s8 == null || s8.length() <= 0)
                s8 = telephonymanager.getSimOperator();
            if(s8 != null && s8.length() > 0)
                if(s8.length() <= 3)
                {
                    qcevent.addParameter("mcc", s8);
                } else
                {
                    qcevent.addParameter("mcc", s8.substring(0, 3));
                    qcevent.addParameter("mnc", s8.substring(3));
                }
            s9 = telephonymanager.getNetworkCountryIso();
            if(s9 == null || s9.length() == 0)
                s9 = telephonymanager.getSimCountryIso();
            if(s9 != null && s9.length() > 0)
                qcevent.addParameter("icc", s9);
            s10 = telephonymanager.getNetworkOperatorName();
            if(s10 == null || s10.length() == 0)
                s10 = telephonymanager.getSimOperatorName();
            if(s10 != null && s10.length() > 0)
                qcevent.addParameter("mnn", s10);
        }
        i = 0xf & context.getResources().getConfiguration().screenLayout;
        if(i == 4 || i == 3)
            flag = true;
        else
            flag = false;
        if(flag)
            s7 = "Tablet";
        else
            s7 = "Handset";
        qcevent.addParameter("dtype", s7);
        qcevent.addParameter("dos", "android");
        qcevent.addParameter("dmod", Build.MODEL);
        qcevent.addParameter("dosv", android.os.Build.VERSION.RELEASE);
        qcevent.addParameter("dm", Build.MANUFACTURER);
        locale = Locale.getDefault();
        qcevent.addParameter("lc", locale.getISO3Country());
        qcevent.addParameter("ll", locale.getISO3Language());
        qcevent.addLabels(as);
        qcevent.addNetworkLabels(as1);
        return qcevent;
        exception;
        try
        {
            qcevent.addParameter("inst", String.valueOf(context.getFilesDir().lastModified()));
        }
        // Misplaced declaration of an exception variable
        catch(android.content.pm.PackageManager.NameNotFoundException namenotfoundexception)
        {
            QCLog.e(TAG, "Unable to get application info for this app.", namenotfoundexception);
        }
          goto _L1
        qcevent.addParameter("inst", String.valueOf(context.getFilesDir().lastModified()));
          goto _L1
    }

    static QCEvent closeSessionEvent(Context context, String s, String as[], String as1[])
    {
        QCEvent qcevent = new QCEvent(s);
        qcevent.setForceUpload(true);
        qcevent.addParameter("event", "finished");
        String s1 = QCUtility.getAppInstallId(context);
        if(s1 != null)
            qcevent.addParameter("aid", s1);
        qcevent.addLabels(as);
        qcevent.addNetworkLabels(as1);
        return qcevent;
    }

    static QCEvent dataBaseEventWithPolicyCheck(Long long1, Map map, QCPolicy qcpolicy)
    {
        QCEvent qcevent;
        if(qcpolicy == null || !qcpolicy.policyIsLoaded() || qcpolicy.isBlackedOut() || qcpolicy.isBlacklisted("event"))
        {
            qcevent = null;
        } else
        {
            qcevent = new QCEvent(long1);
            if(qcpolicy.getSalt() != null)
            {
                if(map.containsKey("did"))
                    map.put("did", QCUtility.applyHash((new StringBuilder()).append((String)map.get("did")).append(qcpolicy.getSalt()).toString()));
                if(map.containsKey("aid"))
                    map.put("aid", QCUtility.applyHash((new StringBuilder()).append((String)map.get("aid")).append(qcpolicy.getSalt()).toString()));
            }
            Iterator iterator = map.keySet().iterator();
            while(iterator.hasNext()) 
            {
                String s = (String)iterator.next();
                if(!qcpolicy.isBlacklisted(s))
                    qcevent.addParameter(s, (String)map.get(s));
            }
        }
        return qcevent;
    }

    static QCEvent logEvent(Context context, String s, String s1, String as[], String as1[])
    {
        QCEvent qcevent = new QCEvent(s);
        qcevent.addParameter("event", "appevent");
        qcevent.addParameter("appevent", s1);
        String s2 = QCUtility.getAppInstallId(context);
        if(s2 != null)
            qcevent.addParameter("aid", s2);
        qcevent.addLabels(as);
        qcevent.addNetworkLabels(as1);
        return qcevent;
    }

    static QCEvent logLatency(Context context, String s, String s1, String s2)
    {
        QCEvent qcevent = new QCEvent(s);
        qcevent.addParameter("event", "latency");
        String s3 = QCUtility.getAppInstallId(context);
        if(s3 != null)
            qcevent.addParameter("aid", s3);
        qcevent.addParameter("uplid", s1);
        qcevent.addParameter("latency-value", s2);
        return qcevent;
    }

    static QCEvent logOptionalEvent(Context context, String s, Map map, String as[], String as1[])
    {
        QCEvent qcevent = new QCEvent(s);
        qcevent.addParameters(map);
        String s1 = QCUtility.getAppInstallId(context);
        if(s1 != null)
            qcevent.addParameter("aid", s1);
        qcevent.addLabels(as);
        qcevent.addNetworkLabels(as1);
        return qcevent;
    }

    static QCEvent logSDKError(String s, String s1, String s2, String s3)
    {
        QCEvent qcevent = new QCEvent(s);
        qcevent.setForceUpload(true);
        qcevent.addParameter("event", "sdkerror");
        qcevent.addParameter("error-type", s1);
        if(s2 != null)
            qcevent.addParameter("error-desc", s2);
        if(s3 != null)
            qcevent.addParameter("error-param", s3);
        return qcevent;
    }

    static QCEvent pauseSession(Context context, String s, String as[], String as1[])
    {
        QCEvent qcevent = new QCEvent(s);
        qcevent.setForceUpload(true);
        qcevent.addParameter("event", "pause");
        String s1 = QCUtility.getAppInstallId(context);
        if(s1 != null)
            qcevent.addParameter("aid", s1);
        qcevent.addLabels(as);
        qcevent.addNetworkLabels(as1);
        return qcevent;
    }

    static QCEvent resumeSession(Context context, String s, String as[], String as1[])
    {
        QCEvent qcevent = new QCEvent(s);
        qcevent.addParameter("event", "resume");
        String s1 = QCUtility.getAppInstallId(context);
        if(s1 != null)
            qcevent.addParameter("aid", s1);
        qcevent.addLabels(as);
        qcevent.addNetworkLabels(as1);
        return qcevent;
    }

    void addLabels(String as[])
    {
        addParameter("labels", QCUtility.encodeStringArray(as));
    }

    void addNetworkLabels(String as[])
    {
        addParameter("netlabels", QCUtility.encodeStringArray(as));
    }

    void addParameter(String s, String s1)
    {
        if(s1 != null)
            m_parameters.put(s, s1);
    }

    void addParameters(Map map)
    {
        if(map != null && map.size() > 0)
            m_parameters.putAll(map);
    }

    String getEventId()
    {
        return m_eventId;
    }

    Map getParameters()
    {
        return m_parameters;
    }

    void setForceUpload(boolean flag)
    {
        m_forceUpload = flag;
    }

    boolean shouldForceUpload()
    {
        return m_forceUpload;
    }

    static final String QC_APIKEY_KEY = "apikey";
    static final String QC_APPEVENT_KEY = "appevent";
    static final String QC_APPID_KEY = "aid";
    static final String QC_APPNAME_KEY = "aname";
    protected static final String QC_BEGIN_LAUNCH_REASON = "launch";
    protected static final String QC_BEGIN_RESUME_REASON = "resume";
    protected static final String QC_BEGIN_USERHASH_REASON = "userhash";
    static final String QC_BUILDNUM_KEY = "iver";
    static final String QC_CARRIERNAME_KEY = "mnn";
    static final String QC_CONNECTION_KEY = "ct";
    static final String QC_COUNTRYCODE_KEY = "icc";
    static final String QC_DEVICEID_KEY = "did";
    static final String QC_DEVICEMODEL_KEY = "dmod";
    static final String QC_DEVICEOS_KEY = "dos";
    static final String QC_DEVICEOS_VALUE = "android";
    static final String QC_DEVICETYPE_KEY = "dtype";
    static final String QC_DST_KEY = "dst";
    static final String QC_ERRORDESC_KEY = "error-desc";
    static final String QC_ERRORPARAM_KEY = "error-param";
    static final String QC_ERRORTYPE_KEY = "error-type";
    static final String QC_EVENT_APPEVENT = "appevent";
    static final String QC_EVENT_FINISHED = "finished";
    static final String QC_EVENT_KEY = "event";
    static final String QC_EVENT_LATENCY = "latency";
    static final String QC_EVENT_LOAD = "load";
    static final String QC_EVENT_PAUSE = "pause";
    static final String QC_EVENT_RESUME = "resume";
    static final String QC_EVENT_SDKERROR = "sdkerror";
    static final String QC_INSTALLDATE_KEY = "inst";
    static final String QC_LATENCYID_KEY = "uplid";
    static final String QC_LATENCYVALUE_KEY = "latency-value";
    static final String QC_LOCALECOUNTRY_KEY = "lc";
    static final String QC_LOCALELANG_KEY = "ll";
    static final String QC_MANUFACTURER_KEY = "dm";
    static final String QC_MCC_KEY = "mcc";
    static final String QC_MEDIA_KEY = "media";
    static final String QC_MEDIA_VALUE = "app";
    static final String QC_MNC_KEY = "mnc";
    static final String QC_NETWORKCODE_KEY = "pcode";
    static final String QC_OSVERSION_KEY = "dosv";
    static final String QC_PACKAGEID_KEY = "pkid";
    static final String QC_PARAMETER_APP_LABEL = "labels";
    static final String QC_PARAMETER_NETWORK_LABEL = "netlabels";
    static final String QC_REASON_KEY = "nsr";
    static final String QC_REFERRER_KEY = "r";
    static final String QC_SCREENRES_KEY = "sr";
    static final String QC_SESSIONID_KEY = "sid";
    static final String QC_TIMESTAMP_KEY = "et";
    static final String QC_TIMEZONE_KEY = "tz";
    static final String QC_USERHASH_KEY = "uh";
    static final String QC_VERSION_KEY = "aver";
    private static final QCLog.Tag TAG = new QCLog.Tag(com/quantcast/measurement/service/QCEvent);
    private final String m_eventId;
    private boolean m_forceUpload;
    private final Map m_parameters;

}
