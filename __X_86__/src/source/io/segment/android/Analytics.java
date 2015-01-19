// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package io.segment.android;

import android.app.Activity;
import android.text.TextUtils;
import io.segment.android.cache.ISettingsLayer;
import io.segment.android.cache.SessionIdCache;
import io.segment.android.cache.SettingsCache;
import io.segment.android.cache.SettingsThread;
import io.segment.android.cache.SimpleStringCache;
import io.segment.android.db.IPayloadDatabaseLayer;
import io.segment.android.db.PayloadDatabase;
import io.segment.android.db.PayloadDatabaseThread;
import io.segment.android.flush.FlushThread;
import io.segment.android.flush.IFlushLayer;
import io.segment.android.info.InfoManager;
import io.segment.android.info.SessionId;
import io.segment.android.integration.Integration;
import io.segment.android.integration.IntegrationManager;
import io.segment.android.models.Alias;
import io.segment.android.models.BasePayload;
import io.segment.android.models.Batch;
import io.segment.android.models.Context;
import io.segment.android.models.EasyJSONObject;
import io.segment.android.models.Group;
import io.segment.android.models.Identify;
import io.segment.android.models.Props;
import io.segment.android.models.Screen;
import io.segment.android.models.Track;
import io.segment.android.models.Traits;
import io.segment.android.request.BasicRequester;
import io.segment.android.stats.AnalyticsStatistics;
import io.segment.android.utils.HandlerTimer;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CountDownLatch;

// Referenced classes of package io.segment.android:
//            Logger, Configuration, Options

public class Analytics
{

    public Analytics()
    {
    }

    public static void activityPause(Activity activity)
    {
        initialize(activity);
        if(optedOut)
        {
            return;
        } else
        {
            integrationManager.onActivityPause(activity);
            return;
        }
    }

    public static void activityResume(Activity activity)
    {
        initialize(activity);
        if(optedOut)
        {
            return;
        } else
        {
            integrationManager.onActivityResume(activity);
            return;
        }
    }

    public static void activityStart(Activity activity)
    {
        initialize(activity);
        integrationManager.onActivityStart(activity);
    }

    public static void activityStart(Activity activity, String s)
    {
        initialize(activity, s);
        integrationManager.onActivityStart(activity);
    }

    public static void activityStart(Activity activity, String s, Options options1)
    {
        initialize(activity, s, options1);
        if(optedOut)
        {
            return;
        } else
        {
            integrationManager.onActivityStart(activity);
            return;
        }
    }

    public static void activityStop(Activity activity)
    {
        initialize(activity);
        if(optedOut)
        {
            return;
        } else
        {
            integrationManager.onActivityStop(activity);
            return;
        }
    }

    public static void alias(String s, String s1)
    {
        alias(s, s1, null, null);
    }

    public static void alias(String s, String s1, Context context)
    {
        alias(s, s1, null, context);
    }

    public static void alias(String s, String s1, Calendar calendar)
    {
        alias(s, s1, calendar, null);
    }

    public static void alias(String s, String s1, Calendar calendar, Context context)
    {
        checkInitialized();
        if(optedOut)
            return;
        if(s == null || s.length() == 0)
            throw new IllegalArgumentException("analytics-android #alias must be initialized with a valid from id.");
        if(s1 == null || s1.length() == 0)
            throw new IllegalArgumentException("analytics-android #alias must be initialized with a valid to id.");
        if(context == null)
            context = new Context();
        Alias alias1 = new Alias(s, s1, calendar, context);
        enqueue(alias1);
        integrationManager.alias(alias1);
        statistics.updateAlias(1.0D);
    }

    private static void checkInitialized()
    {
        if(!initialized)
            throw new IllegalStateException("Please call Analytics.initialize before using the library.");
        else
            return;
    }

    public static void close()
    {
        checkInitialized();
        flushTimer.quit();
        refreshSettingsTimer.quit();
        flushLayer.quit();
        databaseLayer.quit();
        settingsLayer.quit();
        database.close();
        options = null;
        writeKey = null;
        initialized = false;
    }

    public static void enqueue(final BasePayload payload)
    {
        statistics.updateInsertAttempts(1.0D);
        final long start = System.currentTimeMillis();
        databaseLayer.enqueue(payload, new io.segment.android.db.IPayloadDatabaseLayer.EnqueueCallback() {

            public void onEnqueue(boolean flag, long l)
            {
                long l1 = System.currentTimeMillis() - start;
                Analytics.statistics.updateInsertTime(l1);
                if(flag)
                    Logger.i((new StringBuilder("Item ")).append(payload.toDescription()).append(" successfully enqueued.").toString());
                else
                    Logger.w((new StringBuilder("Item ")).append(payload.toDescription()).append(" failed to be enqueued.").toString());
                if(l >= (long)Analytics.options.getFlushAt())
                    Analytics.flush(true);
            }

            private final BasePayload val$payload;
            private final long val$start;

            
            {
                start = l;
                payload = basepayload;
                super();
            }
        }
);
    }

    public static void flush(boolean flag)
    {
        final CountDownLatch latch;
        checkInitialized();
        statistics.updateFlushAttempts(1.0D);
        final long start = System.currentTimeMillis();
        latch = new CountDownLatch(1);
        flushLayer.flush(new io.segment.android.flush.IFlushLayer.FlushCallback() {

            public void onFlushCompleted(boolean flag1)
            {
                latch.countDown();
                if(flag1)
                {
                    long l = System.currentTimeMillis() - start;
                    Analytics.statistics.updateFlushTime(l);
                }
            }

            private final CountDownLatch val$latch;
            private final long val$start;

            
            {
                latch = countdownlatch;
                start = l;
                super();
            }
        }
);
        integrationManager.flush();
        if(flag)
            break MISSING_BLOCK_LABEL_54;
        latch.await();
        return;
        InterruptedException interruptedexception;
        interruptedexception;
        Logger.e("Interrupted while waiting for a blocking flush.");
        return;
    }

    public static Options getOptions()
    {
        if(options == null)
            checkInitialized();
        return options;
    }

    private static String getOrSetGroupId(String s)
    {
        if(TextUtils.isEmpty(s))
        {
            return groupIdCache.get();
        } else
        {
            groupIdCache.set(s);
            return s;
        }
    }

    private static String getOrSetUserId(String s)
    {
        if(TextUtils.isEmpty(s))
        {
            String s1 = userIdCache.get();
            if(TextUtils.isEmpty(s1))
                s1 = sessionIdCache.get();
            return s1;
        } else
        {
            userIdCache.set(s);
            return s;
        }
    }

    public static IntegrationManager getProviderManager()
    {
        return integrationManager;
    }

    public static String getSessionId()
    {
        checkInitialized();
        return sessionIdCache.get();
    }

    public static AnalyticsStatistics getStatistics()
    {
        if(statistics == null)
            checkInitialized();
        return statistics;
    }

    public static String getUserId()
    {
        checkInitialized();
        return userIdCache.get();
    }

    public static String getWriteKey()
    {
        if(writeKey == null)
            checkInitialized();
        return writeKey;
    }

    public static void group(Traits traits)
    {
        group(null, traits, null, null);
    }

    public static void group(Traits traits, Context context)
    {
        group(null, traits, null, context);
    }

    public static void group(Traits traits, Calendar calendar)
    {
        group(null, traits, calendar, null);
    }

    public static void group(Traits traits, Calendar calendar, Context context)
    {
        group(null, traits, calendar, context);
    }

    public static void group(String s)
    {
        group(s, null, null, null);
    }

    public static void group(String s, Traits traits)
    {
        group(s, traits, null, null);
    }

    public static void group(String s, Traits traits, Context context)
    {
        group(s, traits, null, context);
    }

    public static void group(String s, Traits traits, Calendar calendar)
    {
        group(s, traits, calendar, null);
    }

    public static void group(String s, Traits traits, Calendar calendar, Context context)
    {
        checkInitialized();
        if(optedOut)
            return;
        String s1 = getSessionId();
        String s2 = getUserId();
        String s3 = getOrSetGroupId(s);
        if(s3 == null || s3.length() == 0)
            throw new IllegalArgumentException("analytics-android #group must be called with a valid group id.");
        if(context == null)
            context = new Context();
        if(traits == null)
            traits = new Traits();
        Group group1 = new Group(s1, s2, s3, traits, calendar, context);
        enqueue(group1);
        integrationManager.group(group1);
        statistics.updateGroups(1.0D);
    }

    public static void identify(Traits traits)
    {
        identify(null, traits, null, null);
    }

    public static void identify(Traits traits, Context context)
    {
        identify(null, traits, null, context);
    }

    public static void identify(Traits traits, Calendar calendar)
    {
        identify(null, traits, calendar, null);
    }

    public static void identify(Traits traits, Calendar calendar, Context context)
    {
        identify(null, traits, calendar, context);
    }

    public static void identify(String s)
    {
        identify(s, null, null, null);
    }

    public static void identify(String s, Traits traits)
    {
        identify(s, traits, null, null);
    }

    public static void identify(String s, Traits traits, Context context)
    {
        identify(s, traits, null, context);
    }

    public static void identify(String s, Traits traits, Calendar calendar)
    {
        identify(s, traits, calendar, null);
    }

    public static void identify(String s, Traits traits, Calendar calendar, Context context)
    {
        checkInitialized();
        if(optedOut)
            return;
        String s1 = getSessionId();
        String s2 = getOrSetUserId(s);
        if(s2 == null || s2.length() == 0)
            throw new IllegalArgumentException("analytics-android #identify must be initialized with a valid user id.");
        if(context == null)
            context = new Context();
        if(traits == null)
            traits = new Traits();
        Identify identify1 = new Identify(s1, s2, traits, calendar, context);
        enqueue(identify1);
        integrationManager.identify(identify1);
        statistics.updateIdentifies(1.0D);
    }

    public static void initialize(android.content.Context context)
    {
        if(initialized)
        {
            return;
        } else
        {
            initialize(context, Configuration.getWriteKey(context), Configuration.getOptions(context));
            return;
        }
    }

    public static void initialize(android.content.Context context, String s)
    {
        if(initialized)
        {
            return;
        } else
        {
            initialize(context, s, Configuration.getOptions(context));
            return;
        }
    }

    public static void initialize(android.content.Context context, String s, Options options1)
    {
        if(context == null)
            throw new IllegalArgumentException((new StringBuilder(String.valueOf("analytics-android client must be initialized with a valid "))).append("android context.").toString());
        if(s == null || s.length() == 0)
            throw new IllegalArgumentException((new StringBuilder(String.valueOf("analytics-android client must be initialized with a valid "))).append("writeKey.").toString());
        if(options1 == null)
            throw new IllegalArgumentException((new StringBuilder(String.valueOf("analytics-android client must be initialized with a valid "))).append("options.").toString());
        if(initialized)
            return;
        statistics = new AnalyticsStatistics();
        writeKey = s;
        options = options1;
        Logger.setLog(options1.isDebug());
        database = PayloadDatabase.getInstance(context);
        infoManager = new InfoManager(options1);
        sessionIdCache = new SessionIdCache(context);
        groupIdCache = new SimpleStringCache(context, "group.id");
        userIdCache = new SimpleStringCache(context, "user.id");
        sessionIdCache.set((new SessionId()).get(context));
        globalContext = new Context(infoManager.build(context));
        BasicRequester basicrequester = new BasicRequester();
        databaseLayer = new PayloadDatabaseThread(database);
        databaseLayer.start();
        flushLayer = new FlushThread(basicrequester, batchFactory, databaseLayer);
        flushTimer = new HandlerTimer(options1.getFlushAfter(), flushClock);
        refreshSettingsTimer = new HandlerTimer(1000 + options1.getSettingsCacheExpiry(), refreshSettingsClock);
        settingsLayer = new SettingsThread(basicrequester);
        settingsCache = new SettingsCache(context, settingsLayer, options1.getSettingsCacheExpiry());
        integrationManager = new IntegrationManager(settingsCache);
        EasyJSONObject easyjsonobject = new EasyJSONObject();
        Iterator iterator = integrationManager.getProviders().iterator();
        do
        {
            if(!iterator.hasNext())
            {
                globalContext.put("providers", easyjsonobject);
                initialized = true;
                flushTimer.start();
                refreshSettingsTimer.start();
                flushLayer.start();
                settingsLayer.start();
                refreshSettingsTimer.scheduleNow();
                return;
            }
            easyjsonobject.put(((Integration)iterator.next()).getKey(), false);
        } while(true);
    }

    public static boolean isInitialized()
    {
        return initialized;
    }

    public static void onCreate(android.content.Context context)
    {
        initialize(context);
        integrationManager.onCreate(context);
    }

    public static void onCreate(android.content.Context context, String s)
    {
        initialize(context, s);
        integrationManager.onCreate(context);
    }

    public static void onCreate(android.content.Context context, String s, Options options1)
    {
        initialize(context, s, options1);
        integrationManager.onCreate(context);
    }

    public static void optOut()
    {
        optOut(true);
    }

    public static void optOut(boolean flag)
    {
        boolean flag1 = flag ^ optedOut;
        optedOut = flag;
        if(flag1)
            integrationManager.toggleOptOut(flag);
    }

    public static void refreshSettings()
    {
        if(initialized)
            integrationManager.refresh();
    }

    public static void reset()
    {
        if(initialized)
        {
            userIdCache.reset();
            groupIdCache.reset();
            integrationManager.reset();
        }
    }

    public static void screen(String s)
    {
        screen(s, null, null, null);
    }

    public static void screen(String s, Props props)
    {
        screen(s, props, null, null);
    }

    public static void screen(String s, Props props, Calendar calendar)
    {
        screen(s, props, calendar, null);
    }

    public static void screen(String s, Props props, Calendar calendar, Context context)
    {
        checkInitialized();
        if(optedOut)
            return;
        String s1 = getSessionId();
        String s2 = getOrSetUserId(null);
        if(s2 == null || s2.length() == 0)
            throw new IllegalArgumentException("analytics-android #track must be initialized with a valid user id.");
        if(s == null || s.length() == 0)
            throw new IllegalArgumentException("analytics-android #screen must be initialized with a valid screen name.");
        if(context == null)
            context = new Context();
        if(props == null)
            props = new Props();
        Screen screen1 = new Screen(s1, s2, s, props, calendar, context);
        enqueue(screen1);
        integrationManager.screen(screen1);
        statistics.updateScreens(1.0D);
    }

    public static void setSessionId(String s)
    {
        checkInitialized();
        sessionIdCache.set(s);
    }

    public static void setWriteKey(String s)
    {
        writeKey = s;
    }

    public static void track(String s)
    {
        track(s, null, null, null);
    }

    public static void track(String s, Props props)
    {
        track(s, props, null, null);
    }

    public static void track(String s, Props props, Context context)
    {
        track(s, props, null, context);
    }

    public static void track(String s, Props props, Calendar calendar)
    {
        track(s, props, calendar, null);
    }

    public static void track(String s, Props props, Calendar calendar, Context context)
    {
        checkInitialized();
        if(optedOut)
            return;
        String s1 = getSessionId();
        String s2 = getOrSetUserId(null);
        if(s2 == null || s2.length() == 0)
            throw new IllegalArgumentException("analytics-android #track must be initialized with a valid user id.");
        if(s == null || s.length() == 0)
            throw new IllegalArgumentException("analytics-android #track must be initialized with a valid event name.");
        if(context == null)
            context = new Context();
        if(props == null)
            props = new Props();
        Track track1 = new Track(s1, s2, s, props, calendar, context);
        enqueue(track1);
        integrationManager.track(track1);
        statistics.updateTracks(1.0D);
    }

    public static final String VERSION = "0.6.2";
    private static io.segment.android.flush.FlushThread.BatchFactory batchFactory = new io.segment.android.flush.FlushThread.BatchFactory() {

        public Batch create(List list)
        {
            Batch batch = new Batch(Analytics.writeKey, list);
            batch.setContext(Analytics.globalContext);
            return batch;
        }

    }
;
    private static PayloadDatabase database;
    private static IPayloadDatabaseLayer databaseLayer;
    private static Runnable flushClock = new Runnable() {

        public void run()
        {
            Analytics.flush(true);
        }

    }
;
    private static IFlushLayer flushLayer;
    private static HandlerTimer flushTimer;
    private static Context globalContext;
    private static SimpleStringCache groupIdCache;
    private static InfoManager infoManager;
    private static volatile boolean initialized;
    private static IntegrationManager integrationManager;
    private static volatile boolean optedOut;
    private static Options options;
    private static Runnable refreshSettingsClock = new Runnable() {

        public void run()
        {
            Analytics.settingsCache.load(new io.segment.android.cache.ISettingsLayer.SettingsCallback() {

                public void onSettingsLoaded(boolean flag, EasyJSONObject easyjsonobject)
                {
                    Analytics.integrationManager.refresh();
                }

                final _cls3 this$1;

            
            {
                this$1 = _cls3.this;
                super();
            }
            }
);
        }

    }
;
    private static HandlerTimer refreshSettingsTimer;
    private static SimpleStringCache sessionIdCache;
    private static SettingsCache settingsCache;
    private static ISettingsLayer settingsLayer;
    private static AnalyticsStatistics statistics;
    private static SimpleStringCache userIdCache;
    private static String writeKey;







}
