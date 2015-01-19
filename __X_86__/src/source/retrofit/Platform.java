// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package retrofit;

import android.os.Process;
import com.google.gson.Gson;
import java.io.PrintStream;
import java.util.concurrent.*;
import retrofit.android.AndroidApacheClient;
import retrofit.android.AndroidLog;
import retrofit.android.MainThreadExecutor;
import retrofit.appengine.UrlFetchClient;
import retrofit.client.Client;
import retrofit.client.OkClient;
import retrofit.client.UrlConnectionClient;
import retrofit.converter.Converter;
import retrofit.converter.GsonConverter;

abstract class Platform
{
    private static class Android extends Platform
    {

        Executor defaultCallbackExecutor()
        {
            return new MainThreadExecutor();
        }

        retrofit.client.Client.Provider defaultClient()
        {
            Object obj;
            if(Platform.hasOkHttpOnClasspath())
                obj = OkClientInstantiator.instantiate();
            else
            if(android.os.Build.VERSION.SDK_INT < 9)
                obj = new AndroidApacheClient();
            else
                obj = new UrlConnectionClient();
            return ((_cls1) (obj)). new retrofit.client.Client.Provider() {

                public Client get()
                {
                    return client;
                }

                final Android this$0;
                final Client val$client;

            
            {
                this$0 = final_android;
                client = Client.this;
                super();
            }
            }
;
        }

        Converter defaultConverter()
        {
            return new GsonConverter(new Gson());
        }

        Executor defaultHttpExecutor()
        {
            return Executors.newCachedThreadPool(new ThreadFactory() {

                public Thread newThread(Runnable runnable)
                {
                    return new Thread(runnable. new Runnable() {

                        public void run()
                        {
                            Process.setThreadPriority(10);
                            r.run();
                        }

                        final Android._cls2 this$1;
                        final Runnable val$r;

            
            {
                this$1 = final__pcls2;
                r = Runnable.this;
                super();
            }
                    }
, "Retrofit-Idle");
                }

                final Android this$0;

            
            {
                this$0 = Android.this;
                super();
            }
            }
);
        }

        RestAdapter.Log defaultLog()
        {
            return new AndroidLog("Retrofit");
        }

        private Android()
        {
        }

    }

    private static class AppEngine extends Base
    {

        retrofit.client.Client.Provider defaultClient()
        {
            return (new UrlFetchClient()). new retrofit.client.Client.Provider() {

                public Client get()
                {
                    return client;
                }

                final AppEngine this$0;
                final UrlFetchClient val$client;

            
            {
                this$0 = final_appengine;
                client = UrlFetchClient.this;
                super();
            }
            }
;
        }

        private AppEngine()
        {
        }

    }

    private static class Base extends Platform
    {

        Executor defaultCallbackExecutor()
        {
            return new Utils.SynchronousExecutor();
        }

        retrofit.client.Client.Provider defaultClient()
        {
            Object obj;
            if(Platform.hasOkHttpOnClasspath())
                obj = OkClientInstantiator.instantiate();
            else
                obj = new UrlConnectionClient();
            return ((_cls1) (obj)). new retrofit.client.Client.Provider() {

                public Client get()
                {
                    return client;
                }

                final Base this$0;
                final Client val$client;

            
            {
                this$0 = final_base;
                client = Client.this;
                super();
            }
            }
;
        }

        Converter defaultConverter()
        {
            return new GsonConverter(new Gson());
        }

        Executor defaultHttpExecutor()
        {
            return Executors.newCachedThreadPool(new ThreadFactory() {

                public Thread newThread(Runnable runnable)
                {
                    return new Thread(runnable. new Runnable() {

                        public void run()
                        {
                            Thread.currentThread().setPriority(1);
                            r.run();
                        }

                        final Base._cls2 this$1;
                        final Runnable val$r;

            
            {
                this$1 = final__pcls2;
                r = Runnable.this;
                super();
            }
                    }
, "Retrofit-Idle");
                }

                final Base this$0;

            
            {
                this$0 = Base.this;
                super();
            }
            }
);
        }

        RestAdapter.Log defaultLog()
        {
            return new RestAdapter.Log() {

                public void log(String s)
                {
                    System.out.println(s);
                }

                final Base this$0;

            
            {
                this$0 = Base.this;
                super();
            }
            }
;
        }

        private Base()
        {
        }

    }

    private static class OkClientInstantiator
    {

        static Client instantiate()
        {
            return new OkClient();
        }

        private OkClientInstantiator()
        {
        }
    }


    Platform()
    {
    }

    private static Platform findPlatform()
    {
        Android android;
        Class.forName("android.os.Build");
        if(android.os.Build.VERSION.SDK_INT == 0)
            break MISSING_BLOCK_LABEL_24;
        android = new Android();
        return android;
        ClassNotFoundException classnotfoundexception;
        classnotfoundexception;
        if(System.getProperty("com.google.appengine.runtime.version") != null)
            return new AppEngine();
        else
            return new Base();
    }

    static Platform get()
    {
        return PLATFORM;
    }

    private static boolean hasOkHttpOnClasspath()
    {
        try
        {
            Class.forName("com.squareup.okhttp.OkHttpClient");
        }
        catch(ClassNotFoundException classnotfoundexception)
        {
            return false;
        }
        return true;
    }

    private static boolean hasRxJavaOnClasspath()
    {
        try
        {
            Class.forName("rx.Observable");
        }
        catch(ClassNotFoundException classnotfoundexception)
        {
            return false;
        }
        return true;
    }

    abstract Executor defaultCallbackExecutor();

    abstract retrofit.client.Client.Provider defaultClient();

    abstract Converter defaultConverter();

    abstract Executor defaultHttpExecutor();

    abstract RestAdapter.Log defaultLog();

    static final boolean HAS_RX_JAVA = hasRxJavaOnClasspath();
    private static final Platform PLATFORM = findPlatform();


}
