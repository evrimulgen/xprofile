// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.roadtrippers.util;

import android.content.SharedPreferences;
import android.os.Looper;
import android.text.TextUtils;
import com.fasterxml.jackson.core.type.TypeReference;
import com.roadtrippers.api.models.*;
import dagger.Lazy;
import rx.*;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.subscriptions.Subscriptions;

// Referenced classes of package com.roadtrippers.util:
//            Serializer, Log

public class Persistence
{

    public Persistence(Lazy lazy, Lazy lazy1)
    {
        prefsLazy = lazy;
        jackson = lazy1;
    }

    private Observable get(String s, TypeReference typereference)
    {
        return get(s, null, typereference);
    }

    private String getString(String s, String s1)
    {
        return ((SharedPreferences)prefsLazy.get()).getString(s, s1);
    }

    public static boolean isHealthy(String s)
    {
        return !"null".equals(s) && !TextUtils.isEmpty(s);
    }

    public Observable clear()
    {
        return Observable.from(prefsLazy).map(new Func1() {

            public Boolean call(Lazy lazy)
            {
                return Boolean.valueOf(((SharedPreferences)lazy.get()).edit().clear().commit());
            }

            public volatile Object call(Object obj)
            {
                return call((Lazy)obj);
            }

            final Persistence this$0;

            
            {
                this$0 = Persistence.this;
                super();
            }
        }
);
    }

    public Observable get(String s, Class class1)
    {
        return get(s, class1, null);
    }

    Observable get(final String key, final Class clazz, final TypeReference type)
    {
        return Observable.create(new rx.Observable.OnSubscribeFunc() {

            public Subscription onSubscribe(Observer observer)
            {
                String s = ((SharedPreferences)prefsLazy.get()).getString(key, null);
                if(Persistence.isHealthy(s)) goto _L2; else goto _L1
_L1:
                observer.onError(new Exception((new StringBuilder()).append("Missing ").append(key).append(" object").toString()));
_L5:
                return Subscriptions.empty();
_L2:
                if(type == null) goto _L4; else goto _L3
_L3:
                Object obj1 = ((Serializer)jackson.get()).deserialize(s, type);
_L6:
                observer.onNext(obj1);
                Log.d((new StringBuilder()).append("got ").append(key).append(" from persistence").toString());
                observer.onCompleted();
                  goto _L5
                Throwable throwable;
                throwable;
                observer.onError(throwable);
                  goto _L5
_L4:
                Object obj = ((Serializer)jackson.get()).deserialize(s, clazz);
                obj1 = obj;
                  goto _L6
            }

            final Persistence this$0;
            final Class val$clazz;
            final String val$key;
            final TypeReference val$type;

            
            {
                this$0 = Persistence.this;
                key = s;
                type = typereference;
                clazz = class1;
                super();
            }
        }
);
    }

    public String getAuthCookie()
    {
        return (new StringBuilder()).append("auth_token=").append(getAuthToken()).toString();
    }

    public String getAuthToken()
    {
        return getString("auth_token", null);
    }

    public Observable getBuckets()
    {
        return get("bucketsV2", [Lcom/roadtrippers/api/models/Bucket;);
    }

    public Observable getGroups()
    {
        return get("categories", [Lcom/roadtrippers/api/models/Group;);
    }

    public Trip getLastTrip()
    {
        String s = ((SharedPreferences)prefsLazy.get()).getString("last_trip", null);
        boolean flag = isHealthy(s);
        Trip trip = null;
        if(flag)
            trip = (Trip)((Serializer)jackson.get()).deserialize(s, com/roadtrippers/api/models/Trip);
        return trip;
    }

    public SharedPreferences getSharedPreferences()
    {
        return (SharedPreferences)prefsLazy.get();
    }

    public Observable getTrips()
    {
        return get("trips", [Lcom/roadtrippers/api/models/Trip;);
    }

    public Observable getUser()
    {
        return get("user", com/roadtrippers/api/models/User);
    }

    public int getUserId()
    {
        return ((SharedPreferences)prefsLazy.get()).getInt("user_id", 0);
    }

    public String getUsername()
    {
        return getString("username", null);
    }

    public String getVersion()
    {
        return getString("version", null);
    }

    public void save(Object obj, String s)
    {
        if(Looper.myLooper() == Looper.getMainLooper())
        {
            throw new RuntimeException("wrong thread, buddy, do it in background");
        } else
        {
            String s1 = ((Serializer)jackson.get()).serialize(obj);
            ((SharedPreferences)prefsLazy.get()).edit().putString(s, s1).commit();
            return;
        }
    }

    public Action1 saveBuckets()
    {
        return new Action1() {

            public volatile void call(Object obj)
            {
                call((Notification)obj);
            }

            public void call(Notification notification)
            {
                saveBuckets((Bucket[])(Bucket[])notification.getValue());
            }

            final Persistence this$0;

            
            {
                this$0 = Persistence.this;
                super();
            }
        }
;
    }

    public void saveBuckets(Bucket abucket[])
    {
        save(abucket, "bucketsV2");
    }

    public Action1 saveGroups()
    {
        return new Action1() {

            public volatile void call(Object obj)
            {
                call((Notification)obj);
            }

            public void call(Notification notification)
            {
                saveGroups((Group[])(Group[])notification.getValue());
            }

            final Persistence this$0;

            
            {
                this$0 = Persistence.this;
                super();
            }
        }
;
    }

    public void saveGroups(Group agroup[])
    {
        save(agroup, "categories");
    }

    public Trip saveLastTrip(Trip trip)
    {
        save(trip, "last_trip");
        return trip;
    }

    public Trip[] saveTrips(Trip atrip[])
    {
        save(atrip, "trips");
        return atrip;
    }

    public Observable saveUser(UserResponse userresponse)
    {
        return Observable.from(userresponse).map(new Func1() {

            public Boolean call(UserResponse userresponse1)
            {
                return Boolean.valueOf(((SharedPreferences)prefsLazy.get()).edit().putInt("user_id", userresponse1.user_id).putString("username", userresponse1.username).putString("email", userresponse1.email).putString("auth_token", userresponse1.auth_token).commit());
            }

            public volatile Object call(Object obj)
            {
                return call((UserResponse)obj);
            }

            final Persistence this$0;

            
            {
                this$0 = Persistence.this;
                super();
            }
        }
);
    }

    public void saveUser(User user)
    {
        save(user, "user");
    }

    public Observable setIntroWasShown(boolean flag)
    {
        return Observable.from(Boolean.valueOf(flag)).map(new Func1() {

            public Boolean call(Boolean boolean1)
            {
                return Boolean.valueOf(((SharedPreferences)prefsLazy.get()).edit().putBoolean("intro", boolean1.booleanValue()).commit());
            }

            public volatile Object call(Object obj)
            {
                return call((Boolean)obj);
            }

            final Persistence this$0;

            
            {
                this$0 = Persistence.this;
                super();
            }
        }
);
    }

    public boolean wasIntroShown()
    {
        return ((SharedPreferences)prefsLazy.get()).getBoolean("intro", false);
    }

    static final String AUTH_TOKEN = "auth_token";
    static final String BUCKETS = "bucketsV2";
    static final String EMAIL = "email";
    static final String GROUPS = "categories";
    static final String INTRO_WAS_SHOWN = "intro";
    static final String LAST_TRIP = "last_trip";
    static final String TRIPS = "trips";
    static final String USER = "user";
    static final String USERNAME = "username";
    static final String USER_ID = "user_id";
    static final String VERSION = "version";
    Lazy jackson;
    Lazy prefsLazy;
    private String thisVersion;
}
