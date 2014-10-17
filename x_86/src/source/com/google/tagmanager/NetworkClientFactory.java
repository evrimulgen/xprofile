// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.tagmanager;


// Referenced classes of package com.google.tagmanager:
//            HttpNetworkClient, HttpUrlConnectionNetworkClient, NetworkClient

class NetworkClientFactory
{

    NetworkClientFactory()
    {
    }

    public NetworkClient createNetworkClient()
    {
        if(getSdkVersion() < 8)
            return new HttpNetworkClient();
        else
            return new HttpUrlConnectionNetworkClient();
    }

    int getSdkVersion()
    {
        return android.os.Build.VERSION.SDK_INT;
    }
}
