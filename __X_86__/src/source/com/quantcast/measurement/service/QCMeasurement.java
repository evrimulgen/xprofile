// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.quantcast.measurement.service;

import android.content.Context;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import java.text.SimpleDateFormat;
import java.util.*;

// Referenced classes of package com.quantcast.measurement.service:
//            QCNotificationListener, QCNotificationCenter, QCUtility, QCLog, 
//            QCEvent, QCDataManager, QCPolicy, QCOptOutUtility

final class QCMeasurement extends Enum
    implements QCNotificationListener
{

    private QCMeasurement(String s, int i)
    {
        super(s, i);
        m_usesSecureConnection = false;
        QCNotificationCenter.INSTANCE.addListener("QC_PU", this);
        QCNotificationCenter.INSTANCE.addListener("QC_OUC", this);
        m_numActiveContext = 0;
        m_optedOut = true;
    }

    private void setUserIdentifier(String s)
    {
        if(s == null)
        {
            m_userId = null;
            return;
        } else
        {
            m_userId = QCUtility.applyHash(s);
            return;
        }
    }

    public static QCMeasurement valueOf(String s)
    {
        return (QCMeasurement)Enum.valueOf(com/quantcast/measurement/service/QCMeasurement, s);
    }

    public static QCMeasurement[] values()
    {
        return (QCMeasurement[])$VALUES.clone();
    }

    final void clearSession()
    {
        if(m_context != null)
            m_context.deleteDatabase("Quantcast.db");
        m_numActiveContext = 0;
        m_sessionId = null;
        m_manager = null;
        m_policy = null;
        m_context = null;
    }

    final void end(String as[], String as1[])
    {
        if(m_optedOut)
            return;
        if(isMeasurementActive())
        {
            QCLog.i(TAG, "Calling end.");
            m_manager.postEvent(QCEvent.closeSessionEvent(m_context, m_sessionId, as, as1), m_policy);
            m_sessionId = null;
            m_numActiveContext = 0;
            return;
        } else
        {
            QCLog.e(TAG, "End event called without first calling startActivity");
            return;
        }
    }

    final String getAndroidId(Context context)
    {
        String s = android.provider.Settings.Secure.getString(context.getContentResolver(), "android_id");
        if(s == null || s.equals("9774d56d682e549c"))
            s = null;
        return s;
    }

    public String getApiKey()
    {
        return m_apiKey;
    }

    final Context getAppContext()
    {
        return m_context;
    }

    final QCDataManager getManager()
    {
        return m_manager;
    }

    final long getSessionTimeoutInMs()
    {
        long l = 0x1b7740L;
        if(m_policy.policyIsLoaded() && m_policy.hasSessionTimeout())
            l = m_policy.getSessionTimeout().longValue();
        return l;
    }

    final boolean hasNetworkCode()
    {
        return m_networkCode != null;
    }

    public final boolean isMeasurementActive()
    {
        return m_sessionId != null;
    }

    final void logBeginSessionEvent(String s, String as[], String as1[])
    {
        if(m_optedOut)
        {
            return;
        } else
        {
            m_sessionId = QCUtility.generateUniqueId();
            m_manager.postEvent(QCEvent.beginSessionEvent(m_context, m_userId, s, m_sessionId, m_apiKey, m_networkCode, m_deviceId, as, as1), m_policy);
            return;
        }
    }

    final void logEvent(String s, String as[])
    {
        logEvent(s, as, null);
    }

    final void logEvent(String s, String as[], String as1[])
    {
        if(m_optedOut)
            return;
        if(isMeasurementActive())
        {
            m_manager.postEvent(QCEvent.logEvent(m_context, m_sessionId, s, as, as1), m_policy);
            return;
        } else
        {
            QCLog.e(TAG, "Log event called without first calling startActivity");
            return;
        }
    }

    final void logLatency(String s, long l)
    {
        if(m_optedOut)
        {
            return;
        } else
        {
            m_manager.postEvent(QCEvent.logLatency(m_context, m_sessionId, s, Long.toString(l)), m_policy);
            return;
        }
    }

    final void logOptionalEvent(Map map, String as[], String as1[])
    {
        if(m_optedOut)
            return;
        if(isMeasurementActive())
        {
            m_manager.postEvent(QCEvent.logOptionalEvent(m_context, m_sessionId, map, as, as1), m_policy);
            return;
        } else
        {
            QCLog.e(TAG, "Log event called without first calling startActivity");
            return;
        }
    }

    final void logSDKError(String s, String s1, String s2)
    {
        if(m_optedOut || m_manager == null)
        {
            return;
        } else
        {
            m_manager.postEvent(QCEvent.logSDKError(m_sessionId, s, s1, s2), m_policy);
            return;
        }
    }

    public void notificationCallback(String s, Object obj)
    {
        if(s.equals("QC_OUC"))
        {
            m_optedOut = ((Boolean)obj).booleanValue();
            if(!m_optedOut)
            {
                m_policy.updatePolicy(m_context);
                logBeginSessionEvent("launch", new String[] {
                    "_OPT-IN"
                }, null);
            } else
            {
                QCUtility.dumpAppInstallID(m_context);
                m_context.deleteDatabase("Quantcast.db");
            }
            setOptOutCookie(m_optedOut);
        }
    }

    final String recordUserIdentifier(String s, String as[])
    {
        return recordUserIdentifier(s, as, null);
    }

    final String recordUserIdentifier(String s, String as[], String as1[])
    {
        if(m_optedOut)
            return null;
        if(isMeasurementActive()) goto _L2; else goto _L1
_L1:
        setUserIdentifier(s);
_L4:
        return m_userId;
_L2:
        String s1 = m_userId;
        setUserIdentifier(s);
        if(m_userId == null && s1 != null || m_userId != null && !m_userId.equals(s1))
            logBeginSessionEvent("userhash", as, as1);
        if(true) goto _L4; else goto _L3
_L3:
    }

    void setOptOutCookie(boolean flag)
    {
        CookieSyncManager.createInstance(m_context);
        CookieManager cookiemanager = CookieManager.getInstance();
        Calendar calendar = GregorianCalendar.getInstance();
        SimpleDateFormat simpledateformat = new SimpleDateFormat("EEE, dd-MMM-yyyy H:m:s z");
        simpledateformat.setTimeZone(TimeZone.getTimeZone("GMT"));
        CookieSyncManager cookiesyncmanager;
        if(flag)
            calendar.add(1, 10);
        else
            calendar.add(13, 1);
        cookiemanager.setCookie("quantserve.com", (new StringBuilder()).append("qoo=OPT_OUT;domain=.quantserve.com;path=/;expires=").append(simpledateformat.format(calendar.getTime())).toString());
        cookiesyncmanager = CookieSyncManager.getInstance();
        if(cookiesyncmanager != null)
            cookiesyncmanager.sync();
    }

    public final void setUploadEventCount(int i)
    {
        if(isMeasurementActive())
            m_manager.setUploadCount(i);
    }

    final void setUsesSecureConnection(boolean flag)
    {
        m_usesSecureConnection = flag;
    }

    final String startUp(Context context, String s, String s1, String s2, String as[], String as1[], boolean flag)
    {
        if(m_numActiveContext > 0) goto _L2; else goto _L1
_L1:
        if(m_context != null || context != null) goto _L4; else goto _L3
_L3:
        QCLog.e(TAG, "Context passed to Quantcast API cannot be null.");
_L8:
        return null;
_L4:
        if(context != null)
            if(context.getApplicationContext() != null)
                m_context = context.getApplicationContext();
            else
                m_context = context;
        m_optedOut = QCOptOutUtility.isOptedOut(m_context);
        if(m_optedOut)
            setOptOutCookie(true);
        if(isMeasurementActive()) goto _L6; else goto _L5
_L5:
        QCLog.i(TAG, "First start of Quantcast");
        if(s == null)
            s = QCUtility.getAPIKey(m_context);
        if(!validateApiKeyAndNetworkCode(s, s1)) goto _L8; else goto _L7
_L7:
        m_apiKey = s;
        m_networkCode = s1;
        m_deviceId = getAndroidId(m_context);
        if(s2 != null)
            setUserIdentifier(s2);
        m_policy = QCPolicy.getQuantcastPolicy(m_context, m_apiKey, m_networkCode, m_context.getPackageName(), flag);
        m_manager = new QCDataManager(m_context);
        logBeginSessionEvent("launch", as, as1);
        setUploadEventCount(100);
        QCNotificationCenter.INSTANCE.postNotification("QC_START", m_context);
_L2:
        m_numActiveContext = 1 + m_numActiveContext;
        return m_userId;
_L6:
        QCLog.i(TAG, "Resuming Quantcast");
        m_policy.updatePolicy(m_context);
        m_manager.postEvent(QCEvent.resumeSession(m_context, m_sessionId, as, as1), m_policy);
        if(m_lastPause + getSessionTimeoutInMs() < System.currentTimeMillis())
        {
            QCLog.i(TAG, "Past session timeout.  Starting new session.");
            logBeginSessionEvent("resume", as, as1);
        }
        if(true) goto _L2; else goto _L9
_L9:
    }

    final String startUp(Context context, String s, String s1, String as[])
    {
        return startUp(context, s, null, s1, as, null, false);
    }

    final void stop(String as[])
    {
        stop(as, null);
    }

    final void stop(String as[], String as1[])
    {
        if(!m_optedOut)
            if(isMeasurementActive())
            {
                m_numActiveContext = Math.max(0, -1 + m_numActiveContext);
                if(m_numActiveContext == 0)
                {
                    QCLog.i(TAG, "Last Activity stopped, pausing");
                    m_lastPause = System.currentTimeMillis();
                    m_manager.postEvent(QCEvent.pauseSession(m_context, m_sessionId, as, as1), m_policy);
                    QCNotificationCenter.INSTANCE.postNotification("QC_STOP", m_context);
                    return;
                }
            } else
            {
                QCLog.e(TAG, "Pause event called without first calling startActivity");
                return;
            }
    }

    final boolean usesSecureConnection()
    {
        return m_usesSecureConnection;
    }

    final boolean validateApiKeyAndNetworkCode(String s, String s1)
    {
        boolean flag = true;
        if(s == null && s1 == null)
        {
            QCLog.e(TAG, "No Quantcast API Key was passed to the SDK. Please use the API Key provided to you by Quantcast.");
            flag = false;
        }
        if(s != null && !s.matches("[a-zA-Z0-9]{16}-[a-zA-Z0-9]{16}"))
        {
            QCLog.e(TAG, "The Quantcast API Key passed to the SDK is malformed. Please use the API Key provided to you by Quantcast.");
            flag = false;
        }
        if(s1 != null && !s1.matches("p-[-_a-zA-Z0-9]{13}"))
        {
            QCLog.e(TAG, "The Quantcast network p-code passed to the SDK is malformed. Please use the network p-code found on Quantcast.com.");
            flag = false;
        }
        return flag;
    }

    private static final QCMeasurement $VALUES[];
    static final long DEFAULT_SESSION_TIMEOUT = 0x1b7740L;
    static final int DEFAULT_UPLOAD_EVENT_COUNT = 100;
    public static final QCMeasurement INSTANCE;
    public static final String QC_NOTIF_APP_START = "QC_START";
    public static final String QC_NOTIF_APP_STOP = "QC_STOP";
    private static final QCLog.Tag TAG = new QCLog.Tag(com/quantcast/measurement/service/QCMeasurement);
    private String m_apiKey;
    private Context m_context;
    private String m_deviceId;
    private long m_lastPause;
    private QCDataManager m_manager;
    private String m_networkCode;
    private int m_numActiveContext;
    private boolean m_optedOut;
    private QCPolicy m_policy;
    private String m_sessionId;
    private String m_userId;
    private boolean m_usesSecureConnection;

    static 
    {
        INSTANCE = new QCMeasurement("INSTANCE", 0);
        QCMeasurement aqcmeasurement[] = new QCMeasurement[1];
        aqcmeasurement[0] = INSTANCE;
        $VALUES = aqcmeasurement;
    }
}
