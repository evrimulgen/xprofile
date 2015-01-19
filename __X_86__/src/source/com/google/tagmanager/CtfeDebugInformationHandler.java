// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.tagmanager;

import java.io.IOException;

// Referenced classes of package com.google.tagmanager:
//            DebugInformationHandler, NetworkClientFactory, CtfeHost, NetworkClient, 
//            Log

class CtfeDebugInformationHandler
    implements DebugInformationHandler
{

    public CtfeDebugInformationHandler(CtfeHost ctfehost)
    {
        this((new NetworkClientFactory()).createNetworkClient(), ctfehost);
    }

    CtfeDebugInformationHandler(NetworkClient networkclient, CtfeHost ctfehost)
    {
        mCtfeHost = ctfehost;
        mClient = networkclient;
        mDebugEvents = com.google.analytics.containertag.proto.MutableDebug.DebugEvents.newMessage();
    }

    private byte[] getDebugEventsAsBytes()
    {
        return mDebugEvents.toByteArray();
    }

    private boolean sendDebugInformationtoCtfe()
    {
        try
        {
            NetworkClient networkclient = mClient;
            CtfeHost ctfehost = mCtfeHost;
            int i = currentDebugEventNumber;
            currentDebugEventNumber = i + 1;
            networkclient.sendPostRequest(ctfehost.constructCtfeDebugUrl(i), getDebugEventsAsBytes());
        }
        catch(IOException ioexception)
        {
            Log.e((new StringBuilder()).append("CtfeDebugInformationHandler: Error sending information to server that handles debug information: ").append(ioexception.getMessage()).toString());
            return false;
        }
        return true;
    }

    public void receiveEventInfo(com.google.analytics.containertag.proto.MutableDebug.EventInfo eventinfo)
    {
        this;
        JVM INSTR monitorenter ;
        mDebugEvents.addEvent(eventinfo);
        if(mDebugEvents.getEventCount() >= 1 && sendDebugInformationtoCtfe())
            mDebugEvents = mDebugEvents.clear();
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    static final String CTFE_URL_PATH_PREFIX = "/d?";
    static final int NUM_EVENTS_PER_SEND = 1;
    private int currentDebugEventNumber;
    private NetworkClient mClient;
    private CtfeHost mCtfeHost;
    private com.google.analytics.containertag.proto.MutableDebug.DebugEvents mDebugEvents;
}
