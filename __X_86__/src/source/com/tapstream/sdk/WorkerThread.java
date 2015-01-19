// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.tapstream.sdk;

import java.util.concurrent.ThreadFactory;
import org.apache.http.impl.client.DefaultHttpClient;

public class WorkerThread extends Thread
{
    public static class Factory
        implements ThreadFactory
    {

        public Thread newThread(Runnable runnable)
        {
            return new WorkerThread(runnable);
        }

        public Factory()
        {
        }
    }


    public WorkerThread(Runnable runnable)
    {
        super(runnable);
        client = new DefaultHttpClient();
    }

    public DefaultHttpClient client;
}
