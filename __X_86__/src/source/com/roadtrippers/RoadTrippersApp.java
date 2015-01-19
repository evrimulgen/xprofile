// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.roadtrippers;

import android.app.Application;
import android.content.Context;
import com.crashlytics.android.Crashlytics;
import com.google.android.gms.analytics.GoogleAnalytics;
import com.newrelic.agent.android.NewRelic;
import com.squareup.picasso.LruCache;
import dagger.ObjectGraph;
import java.util.ArrayList;
import java.util.List;
import rx.Observable;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

// Referenced classes of package com.roadtrippers:
//            DependenciesModule

public class RoadTrippersApp extends Application
{

    public RoadTrippersApp()
    {
        stage = false;
    }

    public static RoadTrippersApp from(Context context)
    {
        return (RoadTrippersApp)context.getApplicationContext();
    }

    protected List getModules()
    {
        ArrayList arraylist = new ArrayList();
        arraylist.add(new DependenciesModule(this));
        return arraylist;
    }

    public Object inject(Object obj)
    {
        return objectGraph.inject(obj);
    }

    public boolean isStage()
    {
        return stage;
    }

    public void onCreate()
    {
        super.onCreate();
        GoogleAnalytics.getInstance(this).getLogger();
        Object aobj[] = new Object[1];
        aobj[0] = new DependenciesModule(this, stage);
        objectGraph = ObjectGraph.create(aobj);
        objectGraph.injectStatics();
        Observable.from(this).subscribeOn(Schedulers.io()).subscribe(new Action1() {

            public void call(RoadTrippersApp roadtrippersapp)
            {
                Crashlytics.start(roadtrippersapp);
                NewRelic.withApplicationToken("AA30985a8cbf0e51cbc24ba3d30e2041f3276f9e35").withLoggingEnabled(false).start(roadtrippersapp);
            }

            public volatile void call(Object obj)
            {
                call((RoadTrippersApp)obj);
            }

            final RoadTrippersApp this$0;

            
            {
                this$0 = RoadTrippersApp.this;
                super();
            }
        }
);
    }

    public void onLowMemory()
    {
        super.onLowMemory();
        ((LruCache)objectGraph.get(com/squareup/picasso/LruCache)).clear();
    }

    public void setStage(boolean flag)
    {
        Object aobj[] = new Object[1];
        aobj[0] = new DependenciesModule(this, flag);
        objectGraph = ObjectGraph.create(aobj);
        stage = flag;
    }

    ObjectGraph objectGraph;
    boolean stage;
}
