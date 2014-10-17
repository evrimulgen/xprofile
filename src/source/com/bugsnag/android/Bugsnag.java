// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.bugsnag.android;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import com.bugsnag.MetaData;

// Referenced classes of package com.bugsnag.android:
//            ActivityStack, Client

public class Bugsnag
{

    public Bugsnag()
    {
    }

    public static void addToTab(String s, String s1, Object obj)
    {
        runOnClient(new Runnable(s, s1, obj) {

            public final void run()
            {
                Bugsnag.client.addToTab(tab, key, value);
            }

            private String val$key;
            private String val$tab;
            private Object val$value;

            
            {
                tab = s;
                key = s1;
                value = obj;
                super();
            }
        }
);
    }

    public static void notify(Throwable throwable)
    {
        notify(throwable, null, null);
    }

    public static void notify(Throwable throwable, MetaData metadata)
    {
        notify(throwable, null, metadata);
    }

    public static void notify(Throwable throwable, String s)
    {
        notify(throwable, s, null);
    }

    public static void notify(Throwable throwable, String s, MetaData metadata)
    {
        runOnClient(new Runnable(throwable, s, metadata) {

            public final void run()
            {
                Bugsnag.client.notify(e, severity, overrides);
            }

            private Throwable val$e;
            private MetaData val$overrides;
            private String val$severity;

            
            {
                e = throwable;
                severity = s;
                overrides = metadata;
                super();
            }
        }
);
    }

    public static void onActivityCreate(Activity activity)
    {
        ActivityStack.add(activity);
    }

    public static void onActivityDestroy(Activity activity)
    {
        ActivityStack.remove(activity);
    }

    public static void onActivityPause(Activity activity)
    {
        ActivityStack.clearTopActivity();
    }

    public static void onActivityResume(Activity activity)
    {
        ActivityStack.setTopActivity(activity);
    }

    public static void register(Context context, String s)
    {
        register(context, s, true);
    }

    public static void register(Context context, String s, boolean flag)
    {
        try
        {
            client = new Client(context, s, flag);
            return;
        }
        catch(Exception exception)
        {
            Log.e("Bugsnag", "Unable to register with bugsnag. ", exception);
        }
    }

    private static void runOnClient(Runnable runnable)
    {
        if(client != null)
        {
            try
            {
                runnable.run();
                return;
            }
            catch(Exception exception)
            {
                Log.e("Bugsnag", "Error in bugsnag.", exception);
            }
            return;
        } else
        {
            Log.e("Bugsnag", "You must call Bugsnag.register before any other Bugsnag methods.");
            return;
        }
    }

    public static void setAutoNotify(boolean flag)
    {
        runOnClient(new Runnable(flag) {

            public final void run()
            {
                Bugsnag.client.setAutoNotify(autoNotify);
            }

            private boolean val$autoNotify;

            
            {
                autoNotify = flag;
                super();
            }
        }
);
    }

    public static void setContext(Activity activity)
    {
        runOnClient(new Runnable(activity) {

            public final void run()
            {
                Bugsnag.client.setContext(context);
            }

            private Activity val$context;

            
            {
                context = activity;
                super();
            }
        }
);
    }

    public static void setContext(String s)
    {
        runOnClient(new Runnable(s) {

            public final void run()
            {
                Bugsnag.client.setContext(context);
            }

            private String val$context;

            
            {
                context = s;
                super();
            }
        }
);
    }

    public static void setEndpoint(String s)
    {
        runOnClient(new Runnable(s) {

            public final void run()
            {
                Bugsnag.client.setEndpoint(endpoint);
            }

            private String val$endpoint;

            
            {
                endpoint = s;
                super();
            }
        }
);
    }

    public static transient void setFilters(String as[])
    {
        runOnClient(new Runnable(as) {

            public final void run()
            {
                Bugsnag.client.setFilters(filters);
            }

            private String val$filters[];

            
            {
                filters = as;
                super();
            }
        }
);
    }

    public static transient void setIgnoreClasses(String as[])
    {
        runOnClient(new Runnable(as) {

            public final void run()
            {
                Bugsnag.client.setIgnoreClasses(ignoreClasses);
            }

            private String val$ignoreClasses[];

            
            {
                ignoreClasses = as;
                super();
            }
        }
);
    }

    public static transient void setNotifyReleaseStages(String as[])
    {
        runOnClient(new Runnable(as) {

            public final void run()
            {
                Bugsnag.client.setNotifyReleaseStages(notifyReleaseStages);
            }

            private String val$notifyReleaseStages[];

            
            {
                notifyReleaseStages = as;
                super();
            }
        }
);
    }

    public static transient void setProjectPackages(String as[])
    {
        runOnClient(new Runnable(as) {

            public final void run()
            {
                Bugsnag.client.setProjectPackages(projectPackages);
            }

            private String val$projectPackages[];

            
            {
                projectPackages = as;
                super();
            }
        }
);
    }

    public static void setReleaseStage(String s)
    {
        runOnClient(new Runnable(s) {

            public final void run()
            {
                Bugsnag.client.setReleaseStage(releaseStage);
            }

            private String val$releaseStage;

            
            {
                releaseStage = s;
                super();
            }
        }
);
    }

    public static void setUseSSL(boolean flag)
    {
        runOnClient(new Runnable(flag) {

            public final void run()
            {
                Bugsnag.client.setUseSSL(useSSL);
            }

            private boolean val$useSSL;

            
            {
                useSSL = flag;
                super();
            }
        }
);
    }

    public static void setUser(String s, String s1, String s2)
    {
        runOnClient(new Runnable(s, s1, s2) {

            public final void run()
            {
                Bugsnag.client.setUser(id, email, name);
            }

            private String val$email;
            private String val$id;
            private String val$name;

            
            {
                id = s;
                email = s1;
                name = s2;
                super();
            }
        }
);
    }

    public static void setUserId(String s)
    {
        runOnClient(new Runnable(s) {

            public final void run()
            {
                Bugsnag.client.setUserId(userId);
            }

            private String val$userId;

            
            {
                userId = s;
                super();
            }
        }
);
    }

    private static Client client;

}
