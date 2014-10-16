// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.tapstream.sdk;


// Referenced classes of package com.tapstream.sdk:
//            ConversionListener

public class Config
{

    public Config()
    {
        hardware = null;
        odin1 = null;
        openUdid = null;
        collectWifiMac = true;
        collectDeviceId = true;
        collectAndroidId = true;
        installEventName = null;
        openEventName = null;
        fireAutomaticInstallEvent = true;
        fireAutomaticOpenEvent = true;
        conversionListener = null;
    }

    public boolean getCollectAndroidId()
    {
        return collectAndroidId;
    }

    public boolean getCollectDeviceId()
    {
        return collectDeviceId;
    }

    public boolean getCollectWifiMac()
    {
        return collectWifiMac;
    }

    public ConversionListener getConversionListener()
    {
        return conversionListener;
    }

    public boolean getFireAutomaticInstallEvent()
    {
        return fireAutomaticInstallEvent;
    }

    public boolean getFireAutomaticOpenEvent()
    {
        return fireAutomaticOpenEvent;
    }

    public String getHardware()
    {
        return hardware;
    }

    public String getInstallEventName()
    {
        return installEventName;
    }

    public String getOdin1()
    {
        return odin1;
    }

    public String getOpenEventName()
    {
        return openEventName;
    }

    public String getOpenUdid()
    {
        return openUdid;
    }

    public void setCollectAndroidId(boolean flag)
    {
        collectAndroidId = flag;
    }

    public void setCollectDeviceId(boolean flag)
    {
        collectDeviceId = flag;
    }

    public void setCollectWifiMac(boolean flag)
    {
        collectWifiMac = flag;
    }

    public void setConversionListener(ConversionListener conversionlistener)
    {
        conversionListener = conversionlistener;
    }

    public void setFireAutomaticInstallEvent(boolean flag)
    {
        fireAutomaticInstallEvent = flag;
    }

    public void setFireAutomaticOpenEvent(boolean flag)
    {
        fireAutomaticOpenEvent = flag;
    }

    public void setHardware(String s)
    {
        hardware = s;
    }

    public void setInstallEventName(String s)
    {
        installEventName = s;
    }

    public void setOdin1(String s)
    {
        odin1 = s;
    }

    public void setOpenEventName(String s)
    {
        openEventName = s;
    }

    public void setOpenUdid(String s)
    {
        openUdid = s;
    }

    private boolean collectAndroidId;
    private boolean collectDeviceId;
    private boolean collectWifiMac;
    private ConversionListener conversionListener;
    private boolean fireAutomaticInstallEvent;
    private boolean fireAutomaticOpenEvent;
    private String hardware;
    private String installEventName;
    private String odin1;
    private String openEventName;
    private String openUdid;
}
