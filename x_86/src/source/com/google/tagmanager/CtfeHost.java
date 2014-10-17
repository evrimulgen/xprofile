// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.tagmanager;


// Referenced classes of package com.google.tagmanager:
//            PreviewManager, Log

class CtfeHost
{

    public CtfeHost()
    {
        mCtfeServerAddress = "https://www.googletagmanager.com";
    }

    String constructCtfeDebugUrl(int i)
    {
        return (new StringBuilder()).append(mCtfeServerAddress).append("/d?").append(PreviewManager.getInstance().getCTFEUrlDebugQuery()).append("&event_number=").append(i).toString();
    }

    public String getCtfeServerAddress()
    {
        return mCtfeServerAddress;
    }

    public void setCtfeServerAddress(String s)
    {
        mCtfeServerAddress = s;
        Log.i((new StringBuilder()).append("The Ctfe server endpoint was changed to: ").append(s).toString());
    }

    private static final String CTFE_SERVER_ADDRESS = "https://www.googletagmanager.com";
    static final String CTFE_URL_PATH_PREFIX = "/d?";
    static final String DEBUG_EVENT_NUMBER_QUERY = "&event_number=";
    private String mCtfeServerAddress;
}
