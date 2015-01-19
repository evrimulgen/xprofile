// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.roadtrippers.fragments;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.roadtrippers.adapters.TripsAdapter;
import com.roadtrippers.api.Roadtrippers;
import com.roadtrippers.api.models.Trip;
import com.roadtrippers.db.DatabaseHelper;
import com.roadtrippers.events.*;
import com.roadtrippers.fragments.base.RetainedProgressFragment;
import com.roadtrippers.util.*;
import com.squareup.otto.Bus;
import dagger.Lazy;
import de.keyboardsurfer.android.widget.crouton.Crouton;
import de.keyboardsurfer.android.widget.crouton.Style;
import retrofit.client.Response;
import rx.*;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

// Referenced classes of package com.roadtrippers.fragments:
//            RTMapFragment

public class TripsListFragment extends RetainedProgressFragment
{

    public TripsListFragment()
    {
    }

    private void abortSubscription()
    {
        if(subscription != null)
        {
            subscription.unsubscribe();
            subscription = null;
        }
    }

    private Observable getTripsFromServer()
    {
        return ((Roadtrippers)roadtrippersLazy.get()).getTrips().map(new Func1() {

            public volatile Object call(Object obj)
            {
                return call((Trip[])obj);
            }

            public Trip[] call(Trip atrip[])
            {
                return ((Persistence)persistenceLazy.get()).saveTrips(atrip);
            }

            final TripsListFragment this$0;

            
            {
                this$0 = TripsListFragment.this;
                super();
            }
        }
);
    }

    private Observer tripsObserver()
    {
        return new Observer() {

            public void onCompleted()
            {
            }

            public void onError(Throwable throwable)
            {
label0:
                {
                    Log.e(throwable);
                    if(getView() != null)
                    {
                        if(getListAdapter() != null)
                            break label0;
                        if(trips == null)
                        {
                            setContentEmpty(true);
                            setContentShown(true);
                        }
                    }
                    return;
                }
                Crouton.cancelAllCroutons();
                Crouton.showText(getActivity(), 0x7f0c00b4, Style.ALERT, (ViewGroup)getView());
                tripsListView.onRefreshComplete();
            }

            public volatile void onNext(Object obj)
            {
                onNext((Trip[])obj);
            }

            public void onNext(Trip atrip[])
            {
                trips = atrip;
                setListAdapter();
                ((DatabaseHelper)database.get()).saveTrips(atrip);
                int i = atrip.length;
                for(int j = 0; j < i; j++)
                {
                    Trip trip = atrip[j];
                    loadPolylineSub = ((Roadtrippers)roadtrippersLazy.get()).getTrip(trip.id).flatMap(RTMapFragment.loadPolyline(okHttpClientLazy, objectMapperLazy)).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber() {

                        public void onCompleted()
                        {
                        }

                        public void onError(Throwable throwable)
                        {
                            throwable.printStackTrace();
                        }

                        public void onNext(Trip trip)
                        {
                        }

                        public volatile void onNext(Object obj)
                        {
                            onNext((Trip)obj);
                        }

                        final _cls4 this$1;

            
            {
                this$1 = _cls4.this;
                super();
            }
                    }
);
                }

                tripsListView.onRefreshComplete();
                setContentEmpty(false);
                setContentShown(true);
            }

            final TripsListFragment this$0;

            
            {
                this$0 = TripsListFragment.this;
                super();
            }
        }
;
    }

    void downloadTrips(boolean flag)
    {
        Observable observable;
        if(flag)
            observable = getTripsFromServer();
        else
            observable = ((Persistence)persistenceLazy.get()).getTrips().onErrorResumeNext(getTripsFromServer());
        subscription = observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(tripsObserver());
    }

    TripsAdapter getListAdapter()
    {
        if(tripsListView == null || tripsListView.getRefreshableView() == null)
            return null;
        HeaderViewListAdapter headerviewlistadapter = (HeaderViewListAdapter)((ListView)tripsListView.getRefreshableView()).getAdapter();
        if(headerviewlistadapter != null)
            return (TripsAdapter)headerviewlistadapter.getWrappedAdapter();
        else
            return null;
    }

    void newTrip()
    {
        ((Bus)busLazy.get()).post(CreateNewTrip.EVENT);
    }

    public void notifyDataSetChanged()
    {
        if(getListAdapter() != null)
            getListAdapter().notifyDataSetChanged();
    }

    protected void onContentCreated(Bundle bundle)
    {
        ((ListView)tripsListView.getRefreshableView()).setHeaderDividersEnabled(false);
        if(bundle != null && bundle.containsKey("trips"))
            trips = (Trip[])((Serializer)jackson.get()).deserialize(bundle.getString("trips"), [Lcom/roadtrippers/api/models/Trip;);
        if(bundle != null && bundle.containsKey("currentTrip"))
        {
            current = (Trip)((Serializer)jackson.get()).deserialize(bundle.getString("currentTrip"), com/roadtrippers/api/models/Trip);
            Object aobj[] = new Object[1];
            aobj[0] = current.name;
            Log.d("Current trip loaded %s", aobj);
            ((Bus)busLazy.get()).post(new TripClick(current));
        }
        if(trips == null) goto _L2; else goto _L1
_L1:
        setListAdapter();
        setContentShownNoAnimation(true);
_L4:
        tripsListView.setOnItemClickListener(new android.widget.AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView adapterview, View view, int i, long l)
            {
                Trip trip = getListAdapter().getItem(i - 1);
                ((Bus)busLazy.get()).post(new TripClick(trip));
            }

            final TripsListFragment this$0;

            
            {
                this$0 = TripsListFragment.this;
                super();
            }
        }
);
        tripsListView.setOnRefreshListener(new com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener() {

            public void onRefresh(PullToRefreshBase pulltorefreshbase)
            {
                downloadTrips(true);
            }

            final TripsListFragment this$0;

            
            {
                this$0 = TripsListFragment.this;
                super();
            }
        }
);
        ((ListView)tripsListView.getRefreshableView()).setOnTouchListener(new SwipeTouchListener((ListView)tripsListView.getRefreshableView(), 0x7f09013c, 0x7f09013b, new com.roadtrippers.util.SwipeTouchListener.OnSwipeListener() {

            public void onSwipe(ListView listview, int i)
            {
                (new android.app.AlertDialog.Builder(getActivity())).setTitle("Delete Trip?").setMessage("Are you sure?\nYour trip will be gone forever").setPositiveButton("Yes", i. new android.content.DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialoginterface, int i)
                    {
                        Trip trip = getListAdapter().getItem(-1 + position);
                        if(trip != null)
                        {
                            RTAnalytics.logEvent(getActivity(), 0x7f0c0071, 0x7f0c0045);
                            if(trip.equals(((TripManager)tripManagerLazy.get()).getCurrentTrip()))
                                ((Bus)busLazy.get()).post(DeleteCurrentTrip.EVENT);
                            ((DatabaseHelper)database.get()).deleteTrip(trip.id);
                            ((Roadtrippers)roadtrippersLazy.get()).deleteTrip(trip.id).flatMap(new Func1() {

                                public volatile Object call(Object obj)
                                {
                                    return call((Response)obj);
                                }

                                public Observable call(Response response)
                                {
                                    return ((Roadtrippers)roadtrippersLazy.get()).getTrips();
                                }

                                final _cls1 this$2;

            
            {
                this$2 = _cls1.this;
                super();
            }
                            }
).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(tripsObserver());
                        }
                    }

                    final _cls3 this$1;
                    final int val$position;

            
            {
                this$1 = final__pcls3;
                position = I.this;
                super();
            }
                }
).setNegativeButton("No", null).show();
            }

            final TripsListFragment this$0;

            
            {
                this$0 = TripsListFragment.this;
                super();
            }
        }
));
        setColorFilter(newTrip, 0xffcccccc);
        return;
_L2:
        if(subscription == null)
        {
            setContentShown(false);
            downloadTrips(false);
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    protected void onCreateContent(Bundle bundle)
    {
        setContentView(0x7f030063);
    }

    public void onDestroy()
    {
        abortSubscription();
        super.onDestroy();
    }

    public void onEvent(TripUpdatedEvent tripupdatedevent)
    {
        if(tripupdatedevent.trip != null)
            tripsListView.setRefreshing();
    }

    protected void onRetryButtonClick()
    {
        setContentShown(false);
        downloadTrips(false);
    }

    public void onSaveInstanceState(Bundle bundle)
    {
        super.onSaveInstanceState(bundle);
        bundle.putString("trips", ((Serializer)jackson.get()).serialize(trips));
        if(((TripManager)tripManagerLazy.get()).getCurrentTrip() != null)
            bundle.putString("currentTrip", ((Serializer)jackson.get()).serialize(((TripManager)tripManagerLazy.get()).getCurrentTrip()));
    }

    public void onTripLoaded(TripLoadedEvent triploadedevent)
    {
        getListAdapter().notifyDataSetChanged();
    }

    public TripClick selectedTrip()
    {
        if(current == null)
            return null;
        else
            return new TripClick(current);
    }

    void setListAdapter()
    {
        if(getListAdapter() == null)
        {
            tripsListView.setAdapter(new TripsAdapter(getActivity(), trips));
            return;
        } else
        {
            getListAdapter().setTrips(trips);
            return;
        }
    }

    public static final String CURRENT_TRIP = "currentTrip";
    static final String SELECTED = "selected";
    private static final int TRACKING_CATEGORY = 0x7f0c0071;
    static final String TRIPS = "trips";
    Lazy busLazy;
    private Trip current;
    Lazy database;
    Lazy jackson;
    private Subscription loadPolylineSub;
    ImageView newTrip;
    Lazy objectMapperLazy;
    Lazy okHttpClientLazy;
    Lazy persistenceLazy;
    Lazy roadtrippersLazy;
    Subscription subscription;
    Lazy tripManagerLazy;
    Trip trips[];
    PullToRefreshListView tripsListView;



/*
    static Subscription access$102(TripsListFragment tripslistfragment, Subscription subscription1)
    {
        tripslistfragment.loadPolylineSub = subscription1;
        return subscription1;
    }

*/
}
