// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.flurry.android;

import android.content.Context;
import android.location.Criteria;
import com.flurry.sdk.*;
import java.util.Date;
import java.util.Map;

public final class FlurryAgent
{

    private FlurryAgent()
    {
    }

    public static void endTimedEvent(String s)
    {
        if(s == null)
        {
            ex.b(a, "String eventId passed to endTimedEvent was null.");
            return;
        }
        try
        {
            bx.a().b(s);
            return;
        }
        catch(Throwable throwable)
        {
            ex.b(a, (new StringBuilder()).append("Failed to signify the end of event: ").append(s).toString(), throwable);
        }
    }

    public static void endTimedEvent(String s, Map map)
    {
        if(s == null)
        {
            ex.b(a, "String eventId passed to endTimedEvent was null.");
            return;
        }
        if(map == null)
        {
            ex.b(a, "String eventId passed to endTimedEvent was null.");
            return;
        }
        try
        {
            bx.a().b(s, map);
            return;
        }
        catch(Throwable throwable)
        {
            ex.b(a, (new StringBuilder()).append("Failed to signify the end of event: ").append(s).toString(), throwable);
        }
    }

    public static int getAgentVersion()
    {
        return bx.a().b();
    }

    public static String getReleaseVersion()
    {
        return bx.a().g();
    }

    public static boolean getUseHttps()
    {
        return ((Boolean)eh.a().a("UseHttps")).booleanValue();
    }

    public static void logEvent(String s)
    {
        if(s == null)
        {
            ex.b(a, "String eventId passed to logEvent was null.");
            return;
        }
        try
        {
            bx.a().a(s);
            return;
        }
        catch(Throwable throwable)
        {
            ex.b(a, (new StringBuilder()).append("Failed to log event: ").append(s).toString(), throwable);
        }
    }

    public static void logEvent(String s, Map map)
    {
        if(s == null)
        {
            ex.b(a, "String eventId passed to logEvent was null.");
            return;
        }
        if(map == null)
        {
            ex.b(a, "String parameters passed to logEvent was null.");
            return;
        }
        try
        {
            bx.a().a(s, map);
            return;
        }
        catch(Throwable throwable)
        {
            ex.b(a, (new StringBuilder()).append("Failed to log event: ").append(s).toString(), throwable);
        }
    }

    public static void logEvent(String s, Map map, boolean flag)
    {
        if(s == null)
        {
            ex.b(a, "String eventId passed to logEvent was null.");
            return;
        }
        if(map == null)
        {
            ex.b(a, "String parameters passed to logEvent was null.");
            return;
        }
        try
        {
            bx.a().a(s, map, flag);
            return;
        }
        catch(Throwable throwable)
        {
            ex.b(a, (new StringBuilder()).append("Failed to log event: ").append(s).toString(), throwable);
        }
    }

    public static void logEvent(String s, boolean flag)
    {
        if(s == null)
        {
            ex.b(a, "String eventId passed to logEvent was null.");
            return;
        }
        try
        {
            bx.a().a(s, flag);
            return;
        }
        catch(Throwable throwable)
        {
            ex.b(a, (new StringBuilder()).append("Failed to log event: ").append(s).toString(), throwable);
        }
    }

    public static void onEndSession(Context context)
    {
        if(context == null)
            throw new NullPointerException("Null context");
        ey.a().g(context);
        try
        {
            bx.a().a(context);
            return;
        }
        catch(Throwable throwable)
        {
            ex.b(a, "", throwable);
        }
    }

    public static void onError(String s, String s1, String s2)
    {
        if(s == null)
        {
            ex.b(a, "String errorId passed to onError was null.");
            return;
        }
        if(s1 == null)
        {
            ex.b(a, "String message passed to onError was null.");
            return;
        }
        if(s2 == null)
        {
            ex.b(a, "String errorClass passed to onError was null.");
            return;
        }
        try
        {
            bx.a().a(s, s1, s2);
            return;
        }
        catch(Throwable throwable)
        {
            ex.b(a, "", throwable);
        }
    }

    public static void onError(String s, String s1, Throwable throwable)
    {
        if(s == null)
        {
            ex.b(a, "String errorId passed to onError was null.");
            return;
        }
        if(s1 == null)
        {
            ex.b(a, "String message passed to onError was null.");
            return;
        }
        if(throwable == null)
        {
            ex.b(a, "Throwable passed to onError was null.");
            return;
        }
        try
        {
            bx.a().a(s, s1, throwable);
            return;
        }
        catch(Throwable throwable1)
        {
            ex.b(a, "", throwable1);
        }
    }

    public static void onEvent(String s)
    {
        if(s == null)
        {
            ex.b(a, "String eventId passed to onEvent was null.");
            return;
        }
        try
        {
            bx.a().c(s);
            return;
        }
        catch(Throwable throwable)
        {
            ex.b(a, "", throwable);
        }
    }

    public static void onEvent(String s, Map map)
    {
        if(s == null)
        {
            ex.b(a, "String eventId passed to onEvent was null.");
            return;
        }
        if(map == null)
        {
            ex.b(a, "Parameters Map passed to onEvent was null.");
            return;
        }
        try
        {
            bx.a().c(s, map);
            return;
        }
        catch(Throwable throwable)
        {
            ex.b(a, "", throwable);
        }
    }

    public static void onPageView()
    {
        try
        {
            bx.a().i();
            return;
        }
        catch(Throwable throwable)
        {
            ex.b(a, "", throwable);
        }
    }

    public static void onStartSession(Context context, String s)
    {
        if(context == null)
            throw new NullPointerException("Null context");
        if(s == null || s.length() == 0)
            throw new IllegalArgumentException("Api key not specified");
        eg.a(context);
        try
        {
            bx.a().a(context, s);
        }
        catch(Throwable throwable)
        {
            ex.b(a, "", throwable);
        }
        ey.a().f(context);
    }

    public static void setAge(int i)
    {
        if(i > 0 && i < 110)
        {
            long l = (new Date((new Date(System.currentTimeMillis() - 0x7528ad000L * (long)i)).getYear(), 1, 1)).getTime();
            eh.a().a("Age", Long.valueOf(l));
        }
    }

    public static void setCaptureUncaughtExceptions(boolean flag)
    {
        eh.a().a("CaptureUncaughtExceptions", Boolean.valueOf(flag));
    }

    public static void setContinueSessionMillis(long l)
    {
        if(l < 5000L)
        {
            ex.b(a, (new StringBuilder()).append("Invalid time set for session resumption: ").append(l).toString());
            return;
        } else
        {
            eh.a().a("ContinueSessionMillis", Long.valueOf(l));
            return;
        }
    }

    public static void setGender(byte byte0)
    {
        switch(byte0)
        {
        default:
            eh.a().a("Gender", Byte.valueOf((byte)-1));
            return;

        case 0: // '\0'
        case 1: // '\001'
            eh.a().a("Gender", Byte.valueOf(byte0));
            break;
        }
    }

    static void setInternalLoggingEnabled(boolean flag)
    {
        bx.a().a(flag);
    }

    public static void setLocationCriteria(Criteria criteria)
    {
        eh.a().a("LocationCriteria", criteria);
    }

    public static void setLogEnabled(boolean flag)
    {
        if(flag)
        {
            ex.b();
            return;
        } else
        {
            ex.a();
            return;
        }
    }

    public static void setLogEvents(boolean flag)
    {
        eh.a().a("LogEvents", Boolean.valueOf(flag));
    }

    public static void setLogLevel(int i)
    {
        ex.a(i);
    }

    public static void setReportLocation(boolean flag)
    {
        eh.a().a("ReportLocation", Boolean.valueOf(flag));
    }

    public static void setReportUrl(String s)
    {
        eh.a().a("ReportUrl", s);
    }

    public static void setUseHttps(boolean flag)
    {
        eh.a().a("UseHttps", Boolean.valueOf(flag));
    }

    public static void setUserId(String s)
    {
        if(s == null)
        {
            ex.b(a, "String userId passed to setUserId was null.");
            return;
        } else
        {
            eh.a().a("UserId", fh.a(s));
            return;
        }
    }

    public static void setVersionName(String s)
    {
        if(s == null)
        {
            ex.b(a, "String versionName passed to setVersionName was null.");
            return;
        } else
        {
            eh.a().a("VesionName", s);
            return;
        }
    }

    private static final String a = com/flurry/android/FlurryAgent.getSimpleName();

}
