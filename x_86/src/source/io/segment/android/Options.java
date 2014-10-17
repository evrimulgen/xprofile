// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package io.segment.android;

import android.text.TextUtils;

// Referenced classes of package io.segment.android:
//            Defaults

public class Options
{

    public Options()
    {
        this(false, "https://api.segment.io", 20, Defaults.FLUSH_AFTER, 10000, 0x36ee80, true);
    }

    Options(boolean flag, String s, int i, int j, int k, int l, boolean flag1)
    {
        setDebug(flag);
        setHost(s);
        setFlushAt(i);
        setFlushAfter(j);
        setMaxQueueSize(k);
        setSettingsCacheExpiry(l);
        setSendLocation(flag1);
    }

    public int getFlushAfter()
    {
        return flushAfter;
    }

    public int getFlushAt()
    {
        return flushAt;
    }

    public String getHost()
    {
        return host;
    }

    public int getMaxQueueSize()
    {
        return maxQueueSize;
    }

    public int getSettingsCacheExpiry()
    {
        return settingsCacheExpiry;
    }

    public boolean isDebug()
    {
        return debug;
    }

    public Options setDebug(boolean flag)
    {
        debug = flag;
        return this;
    }

    public Options setFlushAfter(int i)
    {
        if(i <= 50)
        {
            throw new IllegalArgumentException("Analytics Options #flushAfter must be greater than 50.");
        } else
        {
            flushAfter = i;
            return this;
        }
    }

    public Options setFlushAt(int i)
    {
        if(i <= 0)
        {
            throw new IllegalArgumentException("Analytics Options #flushAt must be greater than 0.");
        } else
        {
            flushAt = i;
            return this;
        }
    }

    public Options setHost(String s)
    {
        if(TextUtils.isEmpty(s))
        {
            throw new IllegalArgumentException("Analytics Options #host must be non-null or empty.");
        } else
        {
            host = s;
            return this;
        }
    }

    public Options setMaxQueueSize(int i)
    {
        if(flushAfter <= 0)
        {
            throw new IllegalArgumentException("Analytics Options #flushAfter must be greater than 0.");
        } else
        {
            maxQueueSize = i;
            return this;
        }
    }

    public Options setSendLocation(boolean flag)
    {
        sendLocation = flag;
        return this;
    }

    public Options setSettingsCacheExpiry(int i)
    {
        if(i < 1000 || i > 0x3b9ac9ff)
        {
            throw new IllegalArgumentException("Analytics Options #settingsCacheExpiry must be between 1000 and 999999999.");
        } else
        {
            settingsCacheExpiry = i;
            return this;
        }
    }

    public boolean shouldSendLocation()
    {
        return sendLocation;
    }

    private boolean debug;
    private int flushAfter;
    private int flushAt;
    private String host;
    private int maxQueueSize;
    private boolean sendLocation;
    private int settingsCacheExpiry;
}
