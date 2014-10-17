// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package io.segment.android.cache;

import android.content.Context;
import android.content.SharedPreferences;
import io.segment.android.Constants;

public class SimpleStringCache
{

    public SimpleStringCache(Context context, String s)
    {
        preferences = context.getSharedPreferences((new StringBuilder(String.valueOf(Constants.PACKAGE_NAME))).append(".").append(context.getPackageName()).toString(), 0);
        cacheKey = s;
    }

    public String get()
    {
        String s = preferences.getString(cacheKey, null);
        if(s == null)
        {
            s = load();
            if(s != null)
                set(s);
        }
        return s;
    }

    public String load()
    {
        return null;
    }

    public void reset()
    {
        preferences.edit().remove(cacheKey).commit();
    }

    public void set(String s)
    {
        preferences.edit().putString(cacheKey, s).commit();
    }

    private String cacheKey;
    private SharedPreferences preferences;
}
