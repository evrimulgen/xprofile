// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.newrelic.agent.android.api.v1;


public final class DeviceForm extends Enum
{

    private DeviceForm(String s, int i)
    {
        super(s, i);
    }

    public static DeviceForm valueOf(String s)
    {
        return (DeviceForm)Enum.valueOf(com/newrelic/agent/android/api/v1/DeviceForm, s);
    }

    public static DeviceForm[] values()
    {
        return (DeviceForm[])$VALUES.clone();
    }

    private static final DeviceForm $VALUES[];
    public static final DeviceForm LARGE;
    public static final DeviceForm NORMAL;
    public static final DeviceForm SMALL;
    public static final DeviceForm UNKNOWN;
    public static final DeviceForm XLARGE;

    static 
    {
        UNKNOWN = new DeviceForm("UNKNOWN", 0);
        SMALL = new DeviceForm("SMALL", 1);
        NORMAL = new DeviceForm("NORMAL", 2);
        LARGE = new DeviceForm("LARGE", 3);
        XLARGE = new DeviceForm("XLARGE", 4);
        DeviceForm adeviceform[] = new DeviceForm[5];
        adeviceform[0] = UNKNOWN;
        adeviceform[1] = SMALL;
        adeviceform[2] = NORMAL;
        adeviceform[3] = LARGE;
        adeviceform[4] = XLARGE;
        $VALUES = adeviceform;
    }
}
