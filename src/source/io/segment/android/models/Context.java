// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package io.segment.android.models;

import org.json.JSONObject;

// Referenced classes of package io.segment.android.models:
//            EasyJSONObject, Providers

public class Context extends EasyJSONObject
{

    public Context()
    {
        addLibraryContext();
    }

    public Context(JSONObject jsonobject)
    {
        super(jsonobject);
        addLibraryContext();
    }

    public transient Context(Object aobj[])
    {
        super(aobj);
        addLibraryContext();
    }

    private void addLibraryContext()
    {
        put("library", "analytics-android");
        put("libraryVersion", "0.6.2");
    }

    public String getIp()
    {
        return (String)get("ip");
    }

    public boolean isProviderStrictlyEnabled(String s)
    {
        boolean flag = has("providers");
        boolean flag1 = false;
        if(flag)
        {
            EasyJSONObject easyjsonobject = new EasyJSONObject(getObject("providers"));
            boolean flag2 = easyjsonobject.has(s);
            flag1 = false;
            if(flag2)
                flag1 = easyjsonobject.getBoolean(s, Boolean.valueOf(false)).booleanValue();
        }
        return flag1;
    }

    public Context put(String s, Object obj)
    {
        super.putObject(s, obj);
        return this;
    }

    public volatile JSONObject put(String s, Object obj)
    {
        return put(s, obj);
    }

    public Context setIp(String s)
    {
        put("ip", s);
        return this;
    }

    public Context setProviders(Providers providers)
    {
        put("providers", providers);
        return this;
    }

    private static final String IP_KEY = "ip";
    private static final String LIBRARY_KEY = "library";
    private static final String LIBRARY_VERSION_KEY = "libraryVersion";
    private static final String PROVIDERS_KEY = "providers";
}
