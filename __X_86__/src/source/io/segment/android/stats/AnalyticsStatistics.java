// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package io.segment.android.stats;


// Referenced classes of package io.segment.android.stats:
//            Statistics, Statistic

public class AnalyticsStatistics extends Statistics
{

    public AnalyticsStatistics()
    {
    }

    public Statistic getAlias()
    {
        return ensure(ALiAS_KEY);
    }

    public Statistic getFailed()
    {
        return ensure(FAILED_KEY);
    }

    public Statistic getFlushAttempts()
    {
        return ensure(FLUSHED_ATTEMPTS_KEY);
    }

    public Statistic getFlushTime()
    {
        return ensure(FLUSH_TIME_KEY);
    }

    public Statistic getGroups()
    {
        return ensure(GROUP_KEY);
    }

    public Statistic getIdentifies()
    {
        return ensure(IDENTIFY_KEY);
    }

    public Statistic getInsertAttempts()
    {
        return ensure(INSERT_ATTEMPTS_KEY);
    }

    public Statistic getInsertTime()
    {
        return ensure(INSERT_TIME_KEY);
    }

    public Statistic getRequestTime()
    {
        return ensure(REQUEST_TIME_KEY);
    }

    public Statistic getScreens()
    {
        return ensure(SCREEN_KEY);
    }

    public Statistic getSuccessful()
    {
        return ensure(SUCCESSFUL_KEY);
    }

    public Statistic getTracks()
    {
        return ensure(TRACK_KEY);
    }

    public void updateAlias(double d)
    {
        update(ALiAS_KEY, d);
    }

    public void updateFailed(double d)
    {
        update(FAILED_KEY, d);
    }

    public void updateFlushAttempts(double d)
    {
        update(FLUSHED_ATTEMPTS_KEY, d);
    }

    public void updateFlushTime(double d)
    {
        update(FLUSH_TIME_KEY, d);
    }

    public void updateGroups(double d)
    {
        update(GROUP_KEY, d);
    }

    public void updateIdentifies(double d)
    {
        update(IDENTIFY_KEY, d);
    }

    public void updateInsertAttempts(double d)
    {
        update(INSERT_ATTEMPTS_KEY, d);
    }

    public void updateInsertTime(double d)
    {
        update(INSERT_TIME_KEY, d);
    }

    public void updateRequestTime(double d)
    {
        update(REQUEST_TIME_KEY, d);
    }

    public void updateScreens(double d)
    {
        update(SCREEN_KEY, d);
    }

    public void updateSuccessful(double d)
    {
        update(SUCCESSFUL_KEY, d);
    }

    public void updateTracks(double d)
    {
        update(TRACK_KEY, d);
    }

    private static String ALiAS_KEY = "Alias";
    private static String FAILED_KEY = "Failed";
    private static String FLUSHED_ATTEMPTS_KEY = "Flushed Attempts";
    private static String FLUSH_TIME_KEY = "Flush Time";
    private static String GROUP_KEY = "Group";
    private static String IDENTIFY_KEY = "Identify";
    private static String INSERT_ATTEMPTS_KEY = "Insert Attempts";
    private static String INSERT_TIME_KEY = "Insert Time";
    private static String REQUEST_TIME_KEY = "Request Time";
    private static String SCREEN_KEY = "Screen";
    private static String SUCCESSFUL_KEY = "Successful";
    private static String TRACK_KEY = "Track";
    private static final long serialVersionUID = 0x4be6e9ab2ff8206bL;

}
