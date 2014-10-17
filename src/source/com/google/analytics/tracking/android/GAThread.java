// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.analytics.tracking.android;

import android.content.Context;
import android.text.TextUtils;
import com.google.android.gms.analytics.internal.Command;
import java.io.*;
import java.util.*;
import java.util.concurrent.LinkedBlockingQueue;

// Referenced classes of package com.google.analytics.tracking.android:
//            AnalyticsThread, AppFieldsDefaultProvider, DefaultProvider, Utils, 
//            Log, ServiceProxy, GAServiceProxy, ClientIdDefaultProvider, 
//            GoogleAnalytics, GAUsage, MapBuilder, HitBuilder

class GAThread extends Thread
    implements AnalyticsThread
{

    private GAThread(Context context)
    {
        super("GAThread");
        queue = new LinkedBlockingQueue();
        mDisabled = false;
        mClosed = false;
        if(context != null)
            mContext = context.getApplicationContext();
        else
            mContext = context;
        start();
    }

    GAThread(Context context, ServiceProxy serviceproxy)
    {
        super("GAThread");
        queue = new LinkedBlockingQueue();
        mDisabled = false;
        mClosed = false;
        if(context != null)
            mContext = context.getApplicationContext();
        else
            mContext = context;
        mServiceProxy = serviceproxy;
        start();
    }

    private void fillAppParameters(Map map)
    {
        AppFieldsDefaultProvider appfieldsdefaultprovider = AppFieldsDefaultProvider.getProvider();
        Utils.putIfAbsent(map, "&an", appfieldsdefaultprovider.getValue("&an"));
        Utils.putIfAbsent(map, "&av", appfieldsdefaultprovider.getValue("&av"));
        Utils.putIfAbsent(map, "&aid", appfieldsdefaultprovider.getValue("&aid"));
        Utils.putIfAbsent(map, "&aiid", appfieldsdefaultprovider.getValue("&aiid"));
        map.put("&v", "1");
    }

    static String getAndClearCampaign(Context context)
    {
        FileInputStream fileinputstream;
        byte abyte0[];
        int i;
        fileinputstream = context.openFileInput("gaInstallData");
        abyte0 = new byte[8192];
        i = fileinputstream.read(abyte0, 0, 8192);
        if(fileinputstream.available() <= 0)
            break MISSING_BLOCK_LABEL_55;
        Log.e("Too much campaign data, ignoring it.");
        fileinputstream.close();
        context.deleteFile("gaInstallData");
        return null;
        String s;
        try
        {
            fileinputstream.close();
            context.deleteFile("gaInstallData");
        }
        catch(FileNotFoundException filenotfoundexception)
        {
            Log.i("No campaign data found.");
            return null;
        }
        catch(IOException ioexception)
        {
            Log.e("Error reading campaign data.");
            context.deleteFile("gaInstallData");
            return null;
        }
        if(i > 0)
            break MISSING_BLOCK_LABEL_79;
        Log.w("Campaign file is empty.");
        return null;
        s = new String(abyte0, 0, i);
        Log.i((new StringBuilder()).append("Campaign found: ").append(s).toString());
        return s;
    }

    static GAThread getInstance(Context context)
    {
        if(sInstance == null)
            sInstance = new GAThread(context);
        return sInstance;
    }

    private String getUrlScheme(Map map)
    {
        if(map.containsKey("useSecure"))
        {
            if(Utils.safeParseBoolean((String)map.get("useSecure"), true))
                return "https:";
            else
                return "http:";
        } else
        {
            return "https:";
        }
    }

    static int hashClientIdForSampling(String s)
    {
        int i = 1;
        if(!TextUtils.isEmpty(s))
        {
            i = 0;
            for(int j = -1 + s.length(); j >= 0; j--)
            {
                char c = s.charAt(j);
                i = c + (0xfffffff & i << 6) + (c << 14);
                int k = i & 0xfe00000;
                if(k != 0)
                    i ^= k >> 21;
            }

        }
        return i;
    }

    private boolean isSampledOut(Map map)
    {
        if(map.get("&sf") == null)
            return false;
        double d = Utils.safeParseDouble((String)map.get("&sf"), 100D);
        if(d >= 100D)
            return false;
        if((double)(hashClientIdForSampling((String)map.get("&cid")) % 10000) >= 100D * d)
        {
            String s;
            if(map.get("&t") == null)
                s = "unknown";
            else
                s = (String)map.get("&t");
            Log.v(String.format("%s hit sampled out", new Object[] {
                s
            }));
            return true;
        } else
        {
            return false;
        }
    }

    private String printStackTrace(Throwable throwable)
    {
        ByteArrayOutputStream bytearrayoutputstream = new ByteArrayOutputStream();
        PrintStream printstream = new PrintStream(bytearrayoutputstream);
        throwable.printStackTrace(printstream);
        printstream.flush();
        return new String(bytearrayoutputstream.toByteArray());
    }

    public void clearHits()
    {
        queueToThread(new Runnable() {

            public void run()
            {
                mServiceProxy.clearHits();
            }

            final GAThread this$0;

            
            {
                this$0 = GAThread.this;
                super();
            }
        }
);
    }

    void close()
    {
        mClosed = true;
        interrupt();
    }

    public void dispatch()
    {
        queueToThread(new Runnable() {

            public void run()
            {
                mServiceProxy.dispatch();
            }

            final GAThread this$0;

            
            {
                this$0 = GAThread.this;
                super();
            }
        }
);
    }

    public LinkedBlockingQueue getQueue()
    {
        return queue;
    }

    public Thread getThread()
    {
        return this;
    }

    protected void init()
    {
        mServiceProxy.createService();
        mCommands = new ArrayList();
        mCommands.add(new Command("appendVersion", "&_v".substring(1), "ma3.0.1"));
        mCommands.add(new Command("appendQueueTime", "&qt".substring(1), null));
        mCommands.add(new Command("appendCacheBuster", "&z".substring(1), null));
    }

    boolean isDisabled()
    {
        return mDisabled;
    }

    void queueToThread(Runnable runnable)
    {
        queue.add(runnable);
    }

    public void run()
    {
        Throwable throwable1;
        InterruptedException interruptedexception1;
        Runnable runnable;
        try
        {
            Thread.sleep(5000L);
        }
        catch(InterruptedException interruptedexception)
        {
            Log.w("sleep interrupted in GAThread initialize");
        }
        try
        {
            if(mServiceProxy == null)
                mServiceProxy = new GAServiceProxy(mContext, this);
            init();
            mClientId = ClientIdDefaultProvider.getProvider().getValue("&cid");
            mInstallCampaign = getAndClearCampaign(mContext);
        }
        catch(Throwable throwable)
        {
            Log.e((new StringBuilder()).append("Error initializing the GAThread: ").append(printStackTrace(throwable)).toString());
            Log.e("Google Analytics will not start up.");
            mDisabled = true;
        }
        if(mClosed)
            break; /* Loop/switch isn't completed */
        runnable = (Runnable)queue.take();
        if(!mDisabled)
            runnable.run();
        continue; /* Loop/switch isn't completed */
        interruptedexception1;
        try
        {
            Log.i(interruptedexception1.toString());
        }
        // Misplaced declaration of an exception variable
        catch(Throwable throwable1)
        {
            Log.e((new StringBuilder()).append("Error on GAThread: ").append(printStackTrace(throwable1)).toString());
            Log.e("Google Analytics is shutting down.");
            mDisabled = true;
        }
        if(true) goto _L2; else goto _L1
_L2:
        break MISSING_BLOCK_LABEL_56;
_L1:
    }

    public void sendHit(Map map)
    {
        final HashMap hitCopy = new HashMap(map);
        String s = (String)map.get("&ht");
        if(s != null)
            try
            {
                Long.valueOf(s).longValue();
            }
            catch(NumberFormatException numberformatexception)
            {
                s = null;
            }
        if(s == null)
            hitCopy.put("&ht", Long.toString(System.currentTimeMillis()));
        queueToThread(new Runnable() {

            public void run()
            {
                if(TextUtils.isEmpty((CharSequence)hitCopy.get("&cid")))
                    hitCopy.put("&cid", mClientId);
                if(GoogleAnalytics.getInstance(mContext).getAppOptOut() || isSampledOut(hitCopy))
                    return;
                if(!TextUtils.isEmpty(mInstallCampaign))
                {
                    GAUsage.getInstance().setDisableUsage(true);
                    hitCopy.putAll((new MapBuilder()).setCampaignParamsFromUrl(mInstallCampaign).build());
                    GAUsage.getInstance().setDisableUsage(false);
                    mInstallCampaign = null;
                }
                fillAppParameters(hitCopy);
                Map map1 = HitBuilder.generateHitParams(hitCopy);
                mServiceProxy.putHit(map1, Long.valueOf((String)hitCopy.get("&ht")).longValue(), getUrlScheme(hitCopy), mCommands);
            }

            final GAThread this$0;
            final Map val$hitCopy;

            
            {
                this$0 = GAThread.this;
                hitCopy = map;
                super();
            }
        }
);
    }

    public void setForceLocalDispatch()
    {
        queueToThread(new Runnable() {

            public void run()
            {
                mServiceProxy.setForceLocalDispatch();
            }

            final GAThread this$0;

            
            {
                this$0 = GAThread.this;
                super();
            }
        }
);
    }

    static final String API_VERSION = "1";
    private static final String CLIENT_VERSION = "ma3.0.1";
    private static final int MAX_SAMPLE_RATE = 100;
    private static final int SAMPLE_RATE_MODULO = 10000;
    private static final int SAMPLE_RATE_MULTIPLIER = 100;
    private static GAThread sInstance;
    private volatile String mClientId;
    private volatile boolean mClosed;
    private volatile List mCommands;
    private final Context mContext;
    private volatile boolean mDisabled;
    private volatile String mInstallCampaign;
    private volatile ServiceProxy mServiceProxy;
    private final LinkedBlockingQueue queue;






/*
    static String access$302(GAThread gathread, String s)
    {
        gathread.mInstallCampaign = s;
        return s;
    }

*/




}
