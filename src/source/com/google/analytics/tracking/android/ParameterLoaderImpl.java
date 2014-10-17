// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.analytics.tracking.android;

import android.content.Context;
import android.content.res.Resources;
import android.text.TextUtils;

// Referenced classes of package com.google.analytics.tracking.android:
//            ParameterLoader, Log

class ParameterLoaderImpl
    implements ParameterLoader
{

    public ParameterLoaderImpl(Context context)
    {
        if(context == null)
        {
            throw new NullPointerException("Context cannot be null");
        } else
        {
            mContext = context.getApplicationContext();
            return;
        }
    }

    private int getResourceIdForType(String s, String s1)
    {
        if(mContext == null)
            return 0;
        String s2;
        if(mOverrideResourcePackageName != null)
            s2 = mOverrideResourcePackageName;
        else
            s2 = mContext.getPackageName();
        return mContext.getResources().getIdentifier(s, s1, s2);
    }

    public boolean getBoolean(String s)
    {
        int i = getResourceIdForType(s, "bool");
        if(i == 0)
            return false;
        else
            return "true".equalsIgnoreCase(mContext.getString(i));
    }

    public Double getDoubleFromString(String s)
    {
        String s1 = getString(s);
        if(TextUtils.isEmpty(s1))
            return null;
        Double double1;
        try
        {
            double1 = Double.valueOf(Double.parseDouble(s1));
        }
        catch(NumberFormatException numberformatexception)
        {
            Log.w((new StringBuilder()).append("NumberFormatException parsing ").append(s1).toString());
            return null;
        }
        return double1;
    }

    public int getInt(String s, int i)
    {
        int j = getResourceIdForType(s, "integer");
        if(j == 0)
            return i;
        int k;
        try
        {
            k = Integer.parseInt(mContext.getString(j));
        }
        catch(NumberFormatException numberformatexception)
        {
            Log.w((new StringBuilder()).append("NumberFormatException parsing ").append(mContext.getString(j)).toString());
            return i;
        }
        return k;
    }

    public String getString(String s)
    {
        int i = getResourceIdForType(s, "string");
        if(i == 0)
            return null;
        else
            return mContext.getString(i);
    }

    public boolean isBooleanKeyPresent(String s)
    {
        return getResourceIdForType(s, "bool") != 0;
    }

    public void setResourcePackageName(String s)
    {
        mOverrideResourcePackageName = s;
    }

    private final Context mContext;
    private String mOverrideResourcePackageName;
}
