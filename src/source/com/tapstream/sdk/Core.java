// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.tapstream.sdk;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.*;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// Referenced classes of package com.tapstream.sdk:
//            Platform, CoreListener, Config, Logging, 
//            Event, Delegate, Hit, Response, 
//            ConversionListener

class Core
{

    Core(Delegate delegate1, Platform platform1, CoreListener corelistener, String s, String s1, Config config1)
    {
        postData = null;
        firingEvents = new HashSet(16);
        firedEvents = new HashSet(16);
        failingEventId = null;
        delay = 0;
        _flddelegate = delegate1;
        platform = platform1;
        listener = corelistener;
        config = config1;
        accountName = clean(s);
        secret = s1;
        makePostArgs();
        firedEvents = platform1.loadFiredEvents();
        executor = new ScheduledThreadPoolExecutor(1, platform1.makeWorkerThreadFactory());
        executor.prestartAllCoreThreads();
    }

    private void appendPostPair(String s, String s1)
    {
        if(s1 == null)
            return;
        String s2;
        String s3;
        try
        {
            s2 = URLEncoder.encode(s, "UTF-8").replace("+", "%20");
        }
        catch(UnsupportedEncodingException unsupportedencodingexception)
        {
            unsupportedencodingexception.printStackTrace();
            return;
        }
        try
        {
            s3 = URLEncoder.encode(s1, "UTF-8").replace("+", "%20");
        }
        catch(UnsupportedEncodingException unsupportedencodingexception1)
        {
            unsupportedencodingexception1.printStackTrace();
            return;
        }
        if(postData == null)
            postData = new StringBuilder();
        else
            postData.append("&");
        postData.append(s2);
        postData.append("=");
        postData.append(s3);
    }

    private String clean(String s)
    {
        String s1;
        try
        {
            s1 = URLEncoder.encode(s.toLowerCase().trim(), "UTF-8").replace("+", "%20");
        }
        catch(UnsupportedEncodingException unsupportedencodingexception)
        {
            unsupportedencodingexception.printStackTrace();
            return "";
        }
        return s1;
    }

    private void increaseDelay()
    {
        int i = 60;
        if(delay == 0)
        {
            delay = 2;
        } else
        {
            int j = (int)Math.pow(2D, 1L + Math.round(Math.log(delay) / Math.log(2D)));
            if(j <= i)
                i = j;
            delay = i;
        }
        listener.reportOperation("increased-delay");
    }

    private void makePostArgs()
    {
        appendPostPair("secret", secret);
        appendPostPair("sdkversion", "2.4");
        String s = config.getHardware();
        String s1;
        String s2;
        if(s != null)
            if(s.length() > 255)
                Logging.log(5, "Tapstream Warning: Hardware argument exceeds 255 characters, it will not be included with fired events", new Object[0]);
            else
                appendPostPair("hardware", s);
        s1 = config.getOdin1();
        if(s1 != null)
            if(s1.length() > 255)
                Logging.log(5, "Tapstream Warning: ODIN-1 argument exceeds 255 characters, it will not be included with fired events", new Object[0]);
            else
                appendPostPair("hardware-odin1", s1);
        s2 = config.getOpenUdid();
        if(s2 != null)
            if(s2.length() > 255)
                Logging.log(5, "Tapstream Warning: OpenUDID argument exceeds 255 characters, it will not be included with fired events", new Object[0]);
            else
                appendPostPair("hardware-open-udid", s2);
        if(config.getCollectWifiMac())
            appendPostPair("hardware-wifi-mac", platform.getWifiMac());
        if(config.getCollectDeviceId())
            appendPostPair("hardware-android-device-id", platform.getDeviceId());
        if(config.getCollectAndroidId())
            appendPostPair("hardware-android-android-id", platform.getAndroidId());
        appendPostPair("uuid", platform.loadUuid());
        appendPostPair("platform", "Android");
        appendPostPair("vendor", platform.getManufacturer());
        appendPostPair("model", platform.getModel());
        appendPostPair("os", platform.getOs());
        appendPostPair("resolution", platform.getResolution());
        appendPostPair("locale", platform.getLocale());
        appendPostPair("app-name", platform.getAppName());
        appendPostPair("package-name", platform.getPackageName());
        appendPostPair("gmtoffset", Integer.toString(TimeZone.getDefault().getOffset((new Date()).getTime()) / 1000));
    }

    public void fireEvent(Event event)
    {
        this;
        JVM INSTR monitorenter ;
        event.firing();
        if(!event.isOneTimeOnly())
            break MISSING_BLOCK_LABEL_180;
        if(!firedEvents.contains(event.getName())) goto _L2; else goto _L1
_L1:
        Object aobj2[] = new Object[1];
        aobj2[0] = event.getName();
        Logging.log(4, "Tapstream ignoring event named \"%s\" because it is a one-time-only event that has already been fired", aobj2);
        listener.reportOperation("event-ignored-already-fired", event.getName());
        listener.reportOperation("job-ended", event.getName());
_L3:
        this;
        JVM INSTR monitorexit ;
        return;
_L2:
        if(!firingEvents.contains(event.getName()))
            break MISSING_BLOCK_LABEL_166;
        Object aobj1[] = new Object[1];
        aobj1[0] = event.getName();
        Logging.log(4, "Tapstream ignoring event named \"%s\" because it is a one-time-only event that is already in progress", aobj1);
        listener.reportOperation("event-ignored-already-in-progress", event.getName());
        listener.reportOperation("job-ended", event.getName());
          goto _L3
        Exception exception;
        exception;
        throw exception;
        firingEvents.add(event.getName());
        Locale locale = Locale.US;
        Object aobj[] = new Object[2];
        aobj[0] = accountName;
        aobj[1] = event.getEncodedName();
        Runnable runnable = new Runnable() {

            public void innerRun()
            {
                Response response;
                boolean flag1;
                Core core;
                response = platform.request(url, data, "POST");
                boolean flag;
                Object aobj7[];
                if(response.status < 200 || response.status >= 300)
                    flag = true;
                else
                    flag = false;
                if(response.status < 0 || response.status >= 500 && response.status < 600)
                    flag1 = true;
                else
                    flag1 = false;
                core = self;
                core;
                JVM INSTR monitorenter ;
                if(e.isOneTimeOnly())
                    self.firingEvents.remove(e.getName());
                if(!flag) goto _L2; else goto _L1
_L1:
                if(!flag1) goto _L4; else goto _L3
_L3:
                if(self.delay != 0) goto _L6; else goto _L5
_L5:
                self.failingEventId = e.getUid();
                self.increaseDelay();
_L4:
                core;
                JVM INSTR monitorexit ;
                if(flag)
                {
                    Exception exception1;
                    if(response.status < 0)
                    {
                        aobj7 = new Object[1];
                        aobj7[0] = response.message;
                        Logging.log(6, "Tapstream Error: Failed to fire event, error=%s", aobj7);
                    } else
                    if(response.status == 404)
                    {
                        Object aobj6[] = new Object[1];
                        aobj6[0] = Integer.valueOf(response.status);
                        Logging.log(6, "Tapstream Error: Failed to fire event, http code %d\nDoes your event name contain characters that are not url safe? This event will not be retried.", aobj6);
                    } else
                    if(response.status == 403)
                    {
                        Object aobj5[] = new Object[1];
                        aobj5[0] = Integer.valueOf(response.status);
                        Logging.log(6, "Tapstream Error: Failed to fire event, http code %d\nAre your account name and application secret correct?  This event will not be retried.", aobj5);
                    } else
                    {
                        String s = "";
                        if(!flag1)
                            s = "  This event will not be retried.";
                        Object aobj4[] = new Object[2];
                        aobj4[0] = Integer.valueOf(response.status);
                        aobj4[1] = s;
                        Logging.log(6, "Tapstream Error: Failed to fire event, http code %d.%s", aobj4);
                    }
                    self.listener.reportOperation("event-failed", e.getName());
                    if(flag1)
                    {
                        self.listener.reportOperation("retry", e.getName());
                        self.listener.reportOperation("job-ended", e.getName());
                        if(self._flddelegate.isRetryAllowed())
                            self.fireEvent(e);
                        return;
                    }
                } else
                {
                    Object aobj3[] = new Object[1];
                    aobj3[0] = e.getName();
                    Logging.log(4, "Tapstream fired event named \"%s\"", aobj3);
                    self.listener.reportOperation("event-succeeded", e.getName());
                }
                break MISSING_BLOCK_LABEL_582;
_L6:
                if(self.failingEventId != e.getUid()) goto _L4; else goto _L7
_L7:
                self.increaseDelay();
                  goto _L4
                exception1;
                core;
                JVM INSTR monitorexit ;
                throw exception1;
_L2:
                if(e.isOneTimeOnly())
                {
                    self.firedEvents.add(e.getName());
                    self.platform.saveFiredEvents(self.firedEvents);
                    self.listener.reportOperation("fired-list-saved", e.getName());
                }
                self.delay = 0;
                  goto _L4
                self.listener.reportOperation("job-ended", e.getName());
                return;
                  goto _L4
            }

            public void run()
            {
                try
                {
                    innerRun();
                    return;
                }
                catch(Exception exception1)
                {
                    exception1.printStackTrace();
                }
            }

            final Core this$0;
            final String val$data;
            final Event val$e;
            final Core val$self;
            final String val$url;

            
            {
                this$0 = Core.this;
                url = s;
                data = s1;
                self = core1;
                e = event;
                super();
            }
        }
;
        int i = _flddelegate.getDelay();
        executor.schedule(runnable, i, TimeUnit.SECONDS);
          goto _L3
    }

    public void fireHit(Hit hit, Hit.CompletionHandler completionhandler)
    {
        Locale locale = Locale.US;
        Object aobj[] = new Object[2];
        aobj[0] = accountName;
        aobj[1] = hit.getEncodedTrackerName();
        Runnable runnable = new Runnable() {

            public void run()
            {
                Response response = platform.request(url, data, "POST");
                if(response.status < 200 || response.status >= 300)
                {
                    Object aobj1[] = new Object[1];
                    aobj1[0] = Integer.valueOf(response.status);
                    Logging.log(6, "Tapstream Error: Failed to fire hit, http code: %d", aobj1);
                    listener.reportOperation("hit-failed");
                } else
                {
                    Object aobj2[] = new Object[1];
                    aobj2[0] = h.getTrackerName();
                    Logging.log(4, "Tapstream fired hit to tracker: %s", aobj2);
                    listener.reportOperation("hit-succeeded");
                }
                if(completion != null)
                    completion.complete(response);
            }

            final Core this$0;
            final Hit.CompletionHandler val$completion;
            final String val$data;
            final Hit val$h;
            final String val$url;

            
            {
                this$0 = Core.this;
                url = s;
                data = s1;
                h = hit;
                completion = completionhandler;
                super();
            }
        }
;
        executor.schedule(runnable, 0L, TimeUnit.SECONDS);
    }

    public int getDelay()
    {
        return delay;
    }

    public String getPostData()
    {
        return postData.toString();
    }

    public void start()
    {
        String s = platform.getAppName();
        if(s == null)
            s = "";
        if(config.getFireAutomaticInstallEvent())
        {
            String s2 = config.getInstallEventName();
            Locale locale;
            Object aobj[];
            Runnable runnable;
            String s1;
            if(s2 != null)
                fireEvent(new Event(s2, true));
            else
                fireEvent(new Event(String.format(Locale.US, "android-%s-install", new Object[] {
                    s
                }), true));
        }
        if(config.getFireAutomaticOpenEvent())
        {
            s1 = config.getOpenEventName();
            if(s1 != null)
                fireEvent(new Event(s1, false));
            else
                fireEvent(new Event(String.format(Locale.US, "android-%s-open", new Object[] {
                    s
                }), false));
        }
        if(config.getConversionListener() != null)
        {
            locale = Locale.US;
            aobj = new Object[2];
            aobj[0] = secret;
            aobj[1] = platform.loadUuid();
            runnable = new Runnable() {

                public void run()
                {
                    tries = 1 + tries;
                    boolean flag = true;
                    Response response = platform.request(url, null, "GET");
                    if(response.status >= 200 && response.status < 300 && !Pattern.compile("^\\s*\\[\\s*\\]\\s*$").matcher(response.data).matches())
                    {
                        flag = false;
                        config.getConversionListener().conversionInfo(response.data);
                    }
                    if(flag && tries <= 10)
                        executor.schedule(this, 1L, TimeUnit.SECONDS);
                }

                final Core this$0;
                private int tries;
                final String val$url;

            
            {
                this$0 = Core.this;
                url = s;
                super();
                tries = 0;
            }
            }
;
            executor.schedule(runnable, 1L, TimeUnit.SECONDS);
        }
    }

    private static final int CONVERSION_POLL_COUNT = 10;
    private static final int CONVERSION_POLL_INTERVAL = 1;
    private static final String CONVERSION_URL_TEMPLATE = "https://reporting.tapstream.com/v1/timelines/lookup?secret=%s&event_session=%s";
    private static final String EVENT_URL_TEMPLATE = "https://api.tapstream.com/%s/event/%s/";
    private static final String HIT_URL_TEMPLATE = "http://api.tapstream.com/%s/hit/%s.gif";
    private static final int MAX_THREADS = 1;
    public static final String VERSION = "2.4";
    private String accountName;
    private Config config;
    private int delay;
    private Delegate _flddelegate;
    private ScheduledThreadPoolExecutor executor;
    private String failingEventId;
    private Set firedEvents;
    private Set firingEvents;
    private CoreListener listener;
    private Platform platform;
    private StringBuilder postData;
    private String secret;







/*
    static int access$402(Core core, int i)
    {
        core.delay = i;
        return i;
    }

*/



/*
    static String access$502(Core core, String s)
    {
        core.failingEventId = s;
        return s;
    }

*/




}
