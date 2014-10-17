// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.newrelic.agent.android.measurement.http;

import com.newrelic.agent.android.measurement.BaseMeasurement;
import com.newrelic.agent.android.measurement.MeasurementType;
import com.newrelic.agent.android.tracing.TraceMachine;
import com.newrelic.agent.android.util.Util;

public class HttpTransactionMeasurement extends BaseMeasurement
{

    public HttpTransactionMeasurement(String s, int i, int j, long l, double d, 
            long l1, long l2, String s1)
    {
        this(s, i, l, d, l1, l2, s1);
        errorCode = j;
    }

    public HttpTransactionMeasurement(String s, int i, long l, double d, long l1, long l2, String s1)
    {
        super(MeasurementType.Network);
        String s2 = Util.sanitizeUrl(s);
        setName(s2);
        setScope(TraceMachine.getCurrentScope());
        setStartTime(l);
        setEndTime(l + (long)(int)d);
        statusCode = i;
        url = s2;
        bytesSent = l1;
        bytesReceived = l2;
        totalTime = d;
        appData = s1;
    }

    public double asDouble()
    {
        return totalTime;
    }

    public String getAppData()
    {
        return appData;
    }

    public long getBytesReceived()
    {
        return bytesReceived;
    }

    public long getBytesSent()
    {
        return bytesSent;
    }

    public int getErrorCode()
    {
        return errorCode;
    }

    public int getStatusCode()
    {
        return statusCode;
    }

    public double getTotalTime()
    {
        return totalTime;
    }

    public String getUrl()
    {
        return url;
    }

    public void setUrl(String s)
    {
        url = s;
    }

    public String toString()
    {
        return (new StringBuilder()).append("HttpTransactionMeasurement{url='").append(url).append('\'').append(", totalTime=").append(totalTime).append(", statusCode=").append(statusCode).append(", errorCode=").append(errorCode).append(", bytesSent=").append(bytesSent).append(", bytesReceived=").append(bytesReceived).append(", appData='").append(appData).append('\'').append('}').toString();
    }

    private String appData;
    private long bytesReceived;
    private long bytesSent;
    private int errorCode;
    private int statusCode;
    private double totalTime;
    private String url;
}
