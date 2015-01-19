// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.roadtrippers;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.os.Handler;
import android.os.Looper;
import android.preference.PreferenceManager;
import android.view.inputmethod.InputMethodManager;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.roadtrippers.api.GooglePlaces;
import com.roadtrippers.api.Roadtrippers;
import com.roadtrippers.util.DiskUtils;
import com.roadtrippers.util.Log;
import com.roadtrippers.util.Persistence;
import com.squareup.okhttp.*;
import com.squareup.otto.Bus;
import com.squareup.picasso.*;
import dagger.Lazy;
import java.io.IOException;
import java.net.Proxy;
import java.net.URL;
import java.security.*;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.List;
import javax.net.ssl.*;
import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.client.Client;
import retrofit.client.OkClient;
import retrofit.converter.Converter;
import retrofit.converter.JacksonConverter;

public class DependenciesModule
{

    DependenciesModule(Context context1)
    {
        context = context1;
    }

    DependenciesModule(Context context1, boolean flag)
    {
        context = context1;
        stage = flag;
    }

    Bus provideBus()
    {
        return new Bus();
    }

    Client provideClient(OkHttpClient okhttpclient)
    {
        return new OkClient(okhttpclient);
    }

    Context provideContext()
    {
        return context;
    }

    GooglePlaces provideGooglePlaces(Client client, Converter converter, retrofit.RestAdapter.Log log)
    {
        return (GooglePlaces)(new retrofit.RestAdapter.Builder()).setEndpoint("https://maps.googleapis.com").setClient(client).setConverter(converter).setLog(log).setLogLevel(retrofit.RestAdapter.LogLevel.BASIC).build().create(com/roadtrippers/api/GooglePlaces);
    }

    Handler provideHandler()
    {
        return new Handler(Looper.getMainLooper());
    }

    OkHttpClient provideHttpClient()
    {
        OkHttpClient okhttpclient = new OkHttpClient();
        try
        {
            okhttpclient.setHostnameVerifier(new HostnameVerifier() {

                public boolean verify(String s, SSLSession sslsession)
                {
                    return true;
                }

                final DependenciesModule this$0;

            
            {
                this$0 = DependenciesModule.this;
                super();
            }
            }
);
            SSLContext sslcontext = SSLContext.getInstance("TLS");
            X509TrustManager ax509trustmanager[] = new X509TrustManager[1];
            ax509trustmanager[0] = new X509TrustManager() {

                public void checkClientTrusted(X509Certificate ax509certificate[], String s)
                    throws CertificateException
                {
                }

                public void checkServerTrusted(X509Certificate ax509certificate[], String s)
                    throws CertificateException
                {
                }

                public X509Certificate[] getAcceptedIssuers()
                {
                    return null;
                }

                final DependenciesModule this$0;

            
            {
                this$0 = DependenciesModule.this;
                super();
            }
            }
;
            sslcontext.init(null, ax509trustmanager, new SecureRandom());
            okhttpclient.setSslSocketFactory(sslcontext.getSocketFactory());
            if(stage)
                okhttpclient.setAuthenticator(new OkAuthenticator() {

                    public com.squareup.okhttp.OkAuthenticator.Credential authenticate(Proxy proxy, URL url, List list)
                        throws IOException
                    {
                        return com.squareup.okhttp.OkAuthenticator.Credential.basic("roadtripper", "laundrysock");
                    }

                    public com.squareup.okhttp.OkAuthenticator.Credential authenticateProxy(Proxy proxy, URL url, List list)
                        throws IOException
                    {
                        return com.squareup.okhttp.OkAuthenticator.Credential.basic("roadtripper", "laundrysock");
                    }

                    final DependenciesModule this$0;

            
            {
                this$0 = DependenciesModule.this;
                super();
            }
                }
);
            okhttpclient.setResponseCache(new HttpResponseCache(DiskUtils.cacheDirNamed(context, "http"), 0x1400000));
        }
        catch(Object obj)
        {
            Log.e(((Throwable) (obj)));
            return okhttpclient;
        }
        return okhttpclient;
    }

    InputMethodManager provideInputMethodManager()
    {
        return (InputMethodManager)context.getSystemService("input_method");
    }

    Converter provideJacksonConverter(ObjectMapper objectmapper)
    {
        return new JacksonConverter(objectmapper);
    }

    LocationManager provideLocationManager()
    {
        return (LocationManager)context.getSystemService("location");
    }

    LruCache provideLruCache()
    {
        return new LruCache(context);
    }

    ObjectMapper provideObjectMapper()
    {
        return new ObjectMapper();
    }

    Picasso providePicasso(OkHttpClient okhttpclient, LruCache lrucache)
    {
        return (new com.squareup.picasso.Picasso.Builder(context)).downloader(new OkHttpDownloader(okhttpclient)).memoryCache(lrucache).build();
    }

    retrofit.RestAdapter.Log provideRetrofitLog()
    {
        return new retrofit.RestAdapter.Log() {

            public void log(String s)
            {
                Log.d(s);
            }

            final DependenciesModule this$0;

            
            {
                this$0 = DependenciesModule.this;
                super();
            }
        }
;
    }

    Roadtrippers provideRoadtrippers(Client client, Converter converter, retrofit.RestAdapter.Log log, final Lazy persistenceLazy)
    {
        retrofit.RestAdapter.Builder builder = new retrofit.RestAdapter.Builder();
        if(stage)
            builder.setEndpoint("https://stage.roadtrippers.com");
        else
            builder.setEndpoint("https://roadtrippers.com");
        builder.setClient(client).setConverter(converter).setRequestInterceptor(new RequestInterceptor() {

            public void intercept(retrofit.RequestInterceptor.RequestFacade requestfacade)
            {
                String s;
                if(stage)
                    requestfacade.addHeader("Authorization", com.squareup.okhttp.OkAuthenticator.Credential.basic("roadtripper", "laundrysock").getHeaderValue());
                requestfacade.addHeader("Content-Type", "application/json");
                requestfacade.addHeader("Accept", "application/json");
                s = "Roadtrippers Android";
                String s2;
                String s1 = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
                s2 = (new StringBuilder()).append(s).append(" ").append(s1).toString();
                s = s2;
_L2:
                requestfacade.addHeader("User-Agent", s);
                requestfacade.addHeader("Cookie", ((Persistence)persistenceLazy.get()).getAuthCookie());
                return;
                android.content.pm.PackageManager.NameNotFoundException namenotfoundexception;
                namenotfoundexception;
                namenotfoundexception.printStackTrace();
                if(true) goto _L2; else goto _L1
_L1:
            }

            final DependenciesModule this$0;
            final Lazy val$persistenceLazy;

            
            {
                this$0 = DependenciesModule.this;
                persistenceLazy = lazy;
                super();
            }
        }
).setLog(log).setLogLevel(retrofit.RestAdapter.LogLevel.BASIC);
        return (Roadtrippers)builder.build().create(com/roadtrippers/api/Roadtrippers);
    }

    SharedPreferences provideSharedPreferences()
    {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    public static final String STAGE_PASS = "laundrysock";
    public static final String STAGE_USERNAME = "roadtripper";
    Context context;
    boolean stage;
}
