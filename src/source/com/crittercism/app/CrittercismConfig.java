// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.crittercism.app;

import android.util.Log;
import java.util.LinkedList;
import java.util.List;
import org.json.JSONObject;

public class CrittercismConfig
{

    public CrittercismConfig()
    {
        a = null;
        b = false;
        c = false;
        d = false;
        e = true;
        f = a();
        g = "com.crittercism/dumps";
        h = "Developer Reply";
        i = null;
        j = new LinkedList();
        k = new LinkedList();
    }

    public CrittercismConfig(CrittercismConfig crittercismconfig)
    {
        a = null;
        b = false;
        c = false;
        d = false;
        e = true;
        f = a();
        g = "com.crittercism/dumps";
        h = "Developer Reply";
        i = null;
        j = new LinkedList();
        k = new LinkedList();
        a = crittercismconfig.a;
        b = crittercismconfig.b;
        c = crittercismconfig.c;
        d = crittercismconfig.d;
        e = crittercismconfig.e;
        f = crittercismconfig.f;
        g = crittercismconfig.g;
        h = crittercismconfig.h;
        setURLBlacklistPatterns(crittercismconfig.j);
        setPreserveQueryStringPatterns(crittercismconfig.k);
        i = crittercismconfig.i;
    }

    public CrittercismConfig(JSONObject jsonobject)
    {
        a = null;
        b = false;
        c = false;
        d = false;
        e = true;
        f = a();
        g = "com.crittercism/dumps";
        h = "Developer Reply";
        i = null;
        j = new LinkedList();
        k = new LinkedList();
        a = a(jsonobject, "customVersionName", a);
        c = a(jsonobject, "includeVersionCode", c);
        d = a(jsonobject, "installNdk", d);
        b = a(jsonobject, "delaySendingAppLoad", b);
        e = a(jsonobject, "shouldCollectLogcat", e);
        g = a(jsonobject, "nativeDumpPath", g);
        h = a(jsonobject, "notificationTitle", h);
        f = a(jsonobject, "installApm", f);
    }

    private static int a(String s)
    {
        int l = 0;
        if(s != null)
            l = s.hashCode();
        return l;
    }

    private static String a(JSONObject jsonobject, String s, String s1)
    {
        if(jsonobject.has(s))
        {
            String s2;
            try
            {
                s2 = jsonobject.getString(s);
            }
            catch(Exception exception)
            {
                return s1;
            }
            s1 = s2;
        }
        return s1;
    }

    private static final boolean a()
    {
        return android.os.Build.VERSION.SDK_INT >= 10 && android.os.Build.VERSION.SDK_INT <= 18;
    }

    protected static boolean a(String s, String s1)
    {
        if(s == null)
            return s1 == null;
        else
            return s.equals(s1);
    }

    private static boolean a(JSONObject jsonobject, String s, boolean flag)
    {
        if(jsonobject.has(s))
        {
            boolean flag1;
            try
            {
                flag1 = jsonobject.getBoolean(s);
            }
            catch(Exception exception)
            {
                return flag;
            }
            flag = flag1;
        }
        return flag;
    }

    public final boolean delaySendingAppLoad()
    {
        return b;
    }

    public boolean equals(Object obj)
    {
        CrittercismConfig crittercismconfig;
        if(obj instanceof CrittercismConfig)
            if(b == (crittercismconfig = (CrittercismConfig)obj).b && e == crittercismconfig.e && isNdkCrashReportingEnabled() == crittercismconfig.isNdkCrashReportingEnabled() && isOptmzEnabled() == crittercismconfig.isOptmzEnabled() && isVersionCodeToBeIncludedInVersionString() == crittercismconfig.isVersionCodeToBeIncludedInVersionString() && a(a, crittercismconfig.a) && a(h, crittercismconfig.h) && a(g, crittercismconfig.g) && j.equals(crittercismconfig.j) && k.equals(crittercismconfig.k) && a(i, crittercismconfig.i))
                return true;
        return false;
    }

    public final String getCustomVersionName()
    {
        return a;
    }

    public final String getNativeDumpPath()
    {
        return g;
    }

    public final String getNotificationTitle()
    {
        return h;
    }

    public List getOptmzBlackListURLPatterns()
    {
        return getURLBlacklistPatterns();
    }

    public List getPreserveQueryStringPatterns()
    {
        return new LinkedList(k);
    }

    public final String getRateMyAppTestTarget()
    {
        return i;
    }

    public List getURLBlacklistPatterns()
    {
        return new LinkedList(j);
    }

    public int hashCode()
    {
        int l = 1;
        int i1 = 31 * (31 * (31 * (31 * (31 * (0 + a(a)) + a(h)) + a(g)) + a(i)) + j.hashCode()) + k.hashCode();
        int j1;
        int k1;
        int l1;
        int i2;
        int j2;
        int k2;
        int l2;
        int i3;
        int j3;
        if(b)
            j1 = l;
        else
            j1 = 0;
        k1 = j1 + 0 << 1;
        if(e)
            l1 = l;
        else
            l1 = 0;
        i2 = l1 + k1 << 1;
        if(isNdkCrashReportingEnabled())
            j2 = l;
        else
            j2 = 0;
        k2 = j2 + i2 << 1;
        if(isOptmzEnabled())
            l2 = l;
        else
            l2 = 0;
        i3 = l2 + k2 << 1;
        if(!isVersionCodeToBeIncludedInVersionString())
            l = 0;
        j3 = i3 + l;
        return i1 * 31 + Integer.valueOf(j3).hashCode();
    }

    public final boolean isLogcatReportingEnabled()
    {
        return e;
    }

    public final boolean isNdkCrashReportingEnabled()
    {
        return d;
    }

    public final boolean isOptmzEnabled()
    {
        return f;
    }

    public final boolean isVersionCodeToBeIncludedInVersionString()
    {
        return c;
    }

    public final void setCustomVersionName(String s)
    {
        a = s;
    }

    public final void setDelaySendingAppLoad(boolean flag)
    {
        b = flag;
    }

    public final void setLogcatReportingEnabled(boolean flag)
    {
        e = flag;
    }

    public final void setNativeDumpPath(String s)
    {
        g = s;
    }

    public final void setNdkCrashReportingEnabled(boolean flag)
    {
        d = flag;
    }

    public final void setNotificationTitle(String s)
    {
        h = s;
    }

    public void setOptmzBlackListURLPatterns(List list)
    {
        setURLBlacklistPatterns(list);
    }

    public final void setOptmzEnabled(boolean flag)
    {
        if(!a() && flag)
        {
            Log.i("Crittercism", "OPTMZ is currently only allowed for api levels 10 to 18.  APM will not be installed");
            return;
        } else
        {
            f = flag;
            return;
        }
    }

    public void setPreserveQueryStringPatterns(List list)
    {
        k.clear();
        if(list != null)
            k.addAll(list);
    }

    public final void setRateMyAppTestTarget(String s)
    {
        i = s;
    }

    public void setURLBlacklistPatterns(List list)
    {
        j.clear();
        if(list != null)
            j.addAll(list);
    }

    public final void setVersionCodeToBeIncludedInVersionString(boolean flag)
    {
        c = flag;
    }

    public static final String API_VERSION = "4.3.0";
    private String a;
    private boolean b;
    private boolean c;
    private boolean d;
    private boolean e;
    private boolean f;
    private String g;
    private String h;
    private String i;
    private List j;
    private List k;
}
