// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.roadtrippers.fragments;

import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.*;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import com.mobeta.android.dslv.DragSortListView;
import com.nineoldandroids.animation.*;
import com.roadtrippers.adapters.CurrentTripAdapter;
import com.roadtrippers.adapters.DragSortController;
import com.roadtrippers.adapters.base.InflaterAdapter;
import com.roadtrippers.api.GooglePlaces;
import com.roadtrippers.api.Roadtrippers;
import com.roadtrippers.api.models.*;
import com.roadtrippers.api.requests.TripRequest;
import com.roadtrippers.events.*;
import com.roadtrippers.fragments.base.RetainedProgressFragment;
import com.roadtrippers.util.*;
import com.squareup.otto.Bus;
import dagger.Lazy;
import de.keyboardsurfer.android.widget.crouton.Crouton;
import de.keyboardsurfer.android.widget.crouton.Style;
import java.util.*;
import rx.*;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

// Referenced classes of package com.roadtrippers.fragments:
//            SettingsFragment, EditDescriptionFragment, RTMapFragment

public class CurrentTripFragment extends RetainedProgressFragment
{

    public CurrentTripFragment()
    {
    }

    private void createNewTrip()
    {
        RTAnalytics.logEvent(getActivity(), 0x7f0c0071, 0x7f0c0044);
        ((Bus)busLazy.get()).post(NewTripCreated.EVENT);
        ((TripManager)tripManagerLazy.get()).createNewEmptyTrip();
        drawTrip();
    }

    void cancel()
    {
        RTAnalytics.logEvent(getActivity(), 0x7f0c0071, 0x7f0c0043);
        ((Bus)busLazy.get()).post(CancelCurrentTrip.EVENT);
    }

    void closeDescription()
    {
        final Fragment fragmentById = getChildFragmentManager().findFragmentById(0x7f0900b0);
        if(fragmentById != null)
        {
            View view = fragmentById.getView();
            AnimatorSet animatorset = new AnimatorSet();
            Animator aanimator[] = new Animator[2];
            float af[] = new float[2];
            af[0] = 0.0F;
            af[1] = -view.getHeight();
            aanimator[0] = ObjectAnimator.ofFloat(view, "translationY", af);
            aanimator[1] = ObjectAnimator.ofFloat(darkAlpha, "alpha", new float[] {
                0.5F, 0.0F
            });
            animatorset.playTogether(aanimator);
            animatorset.addListener(new AnimatorListenerAdapter() {

                public void onAnimationEnd(Animator animator)
                {
                    InflaterAdapter.setVisibility(darkAlpha, 8);
                    getChildFragmentManager().beginTransaction().remove(fragmentById).commitAllowingStateLoss();
                }

                final CurrentTripFragment this$0;
                final Fragment val$fragmentById;

            
            {
                this$0 = CurrentTripFragment.this;
                fragmentById = fragment;
                super();
            }
            }
);
            animatorset.start();
        }
    }

    void drawTrip()
    {
        Trip trip = ((TripManager)tripManagerLazy.get()).getCurrentTrip();
        if(trip != null)
        {
            tripName.setText(trip.name);
            if(getListAdapter() == null)
            {
                adapter = new CurrentTripAdapter(getActivity());
                listView.setAdapter(adapter);
            } else
            {
                getListAdapter().notifyDataSetChanged();
            }
            setContentEmpty(false);
            setContentShown(true);
        }
    }

    public CurrentTripAdapter getListAdapter()
    {
        return (CurrentTripAdapter)listView.getInputAdapter();
    }

    public void go()
    {
        final TripRequest tripForRequest = ((TripManager)tripManagerLazy.get()).createTripRequest();
        if(tripForRequest.waypoints == null || tripForRequest.waypoints.size() == 0)
        {
            (new android.app.AlertDialog.Builder(getActivity())).setTitle(0x7f0c00aa).setMessage(0x7f0c00bf).setPositiveButton(0x7f0c00c8, null).show();
            return;
        }
        tripForRequest.setName(SettingsFragment.trim(tripName));
        ((TripManager)tripManagerLazy.get()).fill(tripForRequest);
        setContentShown(false);
        ((Bus)busLazy.get()).post(UpdatingTrip.EVENT);
        final String currentTripId = ((TripManager)tripManagerLazy.get()).getCurrentTrip().id;
        Observable observable;
        if(currentTripId != null)
            observable = ((Roadtrippers)roadtrippersLazy.get()).updateTrip(currentTripId, tripForRequest);
        else
            observable = ((Roadtrippers)roadtrippersLazy.get()).createNewTrip(tripForRequest);
        observable.flatMap(new Func1() {

            public volatile Object call(Object obj)
            {
                return call((Trip)obj);
            }

            public Observable call(Trip trip)
            {
                return ((Roadtrippers)roadtrippersLazy.get()).getTrip(trip.id).map(trip. new Func1() {

                    public Trip call(Trip trip)
                    {
                        if(getString(0x7f0c00e4).equals(trip.message))
                            trip = createdTrip;
                        return trip;
                    }

                    public volatile Object call(Object obj)
                    {
                        return call((Trip)obj);
                    }

                    final _cls9 this$1;
                    final Trip val$createdTrip;

            
            {
                this$1 = final__pcls9;
                createdTrip = Trip.this;
                super();
            }
                }
).flatMap(RTMapFragment.loadPolyline(okHttpClientLazy, objectMapperLazy));
            }

            final CurrentTripFragment this$0;

            
            {
                this$0 = CurrentTripFragment.this;
                super();
            }
        }
).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer() {

            public void onCompleted()
            {
            }

            public void onError(Throwable throwable)
            {
                Log.e(throwable);
                if(getView() != null)
                {
                    setContentShown(true);
                    Crouton.cancelAllCroutons();
                    CurrentTripFragment currenttripfragment = CurrentTripFragment.this;
                    Object aobj[] = new Object[1];
                    CurrentTripFragment currenttripfragment1 = CurrentTripFragment.this;
                    int i;
                    String s;
                    if(currentTripId == null)
                        i = 0x7f0c00a4;
                    else
                        i = 0x7f0c00f5;
                    aobj[0] = currenttripfragment1.getString(i);
                    s = currenttripfragment.getString(0x7f0c00e8, aobj);
                    Crouton.showText(getActivity(), s, Style.ALERT, (ViewGroup)getView());
                }
                ((Bus)busLazy.get()).post(new TripUpdatedEvent(throwable));
            }

            public void onNext(Trip trip)
            {
                ((Bus)busLazy.get()).post(new TripUpdatedEvent(trip));
                RTAnalytics.logEvent(getActivity(), 0x7f0c0071, 0x7f0c0044, tripForRequest.getAnalyticsProps());
            }

            public volatile void onNext(Object obj)
            {
                onNext((Trip)obj);
            }

            final CurrentTripFragment this$0;
            final String val$currentTripId;
            final TripRequest val$tripForRequest;

            
            {
                this$0 = CurrentTripFragment.this;
                currentTripId = s;
                tripForRequest = triprequest;
                super();
            }
        }
);
    }

    void newTrip()
    {
        (new android.app.AlertDialog.Builder(getActivity())).setTitle(0x7f0c00e9).setMessage(0x7f0c00f2).setPositiveButton(0x7f0c0064, new android.content.DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialoginterface, int i)
            {
                createNewTrip();
            }

            final CurrentTripFragment this$0;

            
            {
                this$0 = CurrentTripFragment.this;
                super();
            }
        }
).setNegativeButton(0x7f0c0063, null).show();
    }

    public boolean onBackPressed()
    {
        if(getChildFragmentManager().findFragmentById(0x7f0900b0) != null)
        {
            closeDescription();
            return true;
        } else
        {
            return false;
        }
    }

    protected void onContentCreated(Bundle bundle)
    {
        listView.setOnScrollListener(new android.widget.AbsListView.OnScrollListener() {

            public void onScroll(AbsListView abslistview, int i, int j, int k)
            {
            }

            public void onScrollStateChanged(AbsListView abslistview, int i)
            {
                if(i != 0)
                    getListAdapter().onScroll();
            }

            final CurrentTripFragment this$0;

            
            {
                this$0 = CurrentTripFragment.this;
                super();
            }
        }
);
        DragSortController dragsortcontroller = new DragSortController(listView, 0x7f0900b7, 0, 1) {

            public View onCreateFloatView(int i)
            {
                View view = listView.getChildAt((i + listView.getHeaderViewsCount()) - listView.getFirstVisiblePosition());
                if(view == null)
                    return null;
                view.findViewById(0x7f0900ba).setVisibility(8);
                view.setPressed(false);
                view.setDrawingCacheEnabled(true);
                floatBitmap = Bitmap.createBitmap(view.getDrawingCache());
                view.setDrawingCacheEnabled(false);
                view.findViewById(0x7f0900ba).setVisibility(0);
                if(floatImageView == null)
                    floatImageView = new ImageView(listView.getContext());
                floatImageView.setBackgroundColor(0);
                floatImageView.setPadding(0, 0, 0, 0);
                floatImageView.setImageBitmap(floatBitmap);
                floatImageView.setLayoutParams(new android.view.ViewGroup.LayoutParams(view.getWidth(), view.getHeight()));
                return floatImageView;
            }

            public void onDestroyFloatView(View view)
            {
                floatImageView.setImageDrawable(null);
                if(floatBitmap != null)
                {
                    floatBitmap.recycle();
                    floatBitmap = null;
                }
            }

            Bitmap floatBitmap;
            ImageView floatImageView;
            final CurrentTripFragment this$0;

            
            {
                this$0 = CurrentTripFragment.this;
                super(dragsortlistview, i, j, k);
            }
        }
;
        dragsortcontroller.setRemoveEnabled(false);
        dragsortcontroller.setBackgroundColor(0);
        dragsortcontroller.setSortEnabled(true);
        listView.setFloatViewManager(dragsortcontroller);
        listView.setOnTouchListener(dragsortcontroller);
        listView.setDropListener(new com.mobeta.android.dslv.DragSortListView.DropListener() {

            public void drop(int i, int j)
            {
                ((TripManager)tripManagerLazy.get()).swap(i, j);
                getListAdapter().notifyDataSetChanged();
            }

            final CurrentTripFragment this$0;

            
            {
                this$0 = CurrentTripFragment.this;
                super();
            }
        }
);
        tripName.setOnFocusChangeListener(new android.view.View.OnFocusChangeListener() {

            public void onFocusChange(View view, boolean flag)
            {
                if(flag)
                    showDescription();
            }

            final CurrentTripFragment this$0;

            
            {
                this$0 = CurrentTripFragment.this;
                super();
            }
        }
);
        drawTrip();
    }

    protected void onCreateContent(Bundle bundle)
    {
        setContentView(0x7f030034);
    }

    public void onEvent(CloseEditDescription closeeditdescription)
    {
        closeDescription();
    }

    public void onEvent(CreateNewTrip createnewtrip)
    {
        createNewTrip();
    }

    public void onEvent(CurrentTripFull currenttripfull)
    {
        Crouton.cancelAllCroutons();
        Crouton.showText(getActivity(), 0x7f0c00fa, Style.ALERT, (ViewGroup)getView());
    }

    public void onEvent(final GooglePlaceSelected event)
    {
        Subscription subscription = ((GooglePlaces)placesLazy.get()).details(event.googlePlace.reference, "AIzaSyBm8Rs4h4xvSJ1AD5O8Aej-h3KUkL2DV60").map(new Func1() {

            public Waypoint call(com.roadtrippers.api.models.GooglePlaceDetail.Response response)
            {
                GooglePlaceDetail googleplacedetail = response.result;
                com.roadtrippers.api.models.Geometry.Location location = googleplacedetail.geometry.location;
                Waypoint waypoint = new Waypoint();
                waypoint.type = com.roadtrippers.api.requests.WaypointRequest.Type.geo;
                waypoint.name = googleplacedetail.formatted_address;
                double ad[] = new double[2];
                ad[0] = location.lng;
                ad[1] = location.lat;
                waypoint.location = ad;
                return waypoint;
            }

            public volatile Object call(Object obj)
            {
                return call((com.roadtrippers.api.models.GooglePlaceDetail.Response)obj);
            }

            final CurrentTripFragment this$0;

            
            {
                this$0 = CurrentTripFragment.this;
                super();
            }
        }
).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer() {

            public void onCompleted()
            {
            }

            public void onError(Throwable throwable)
            {
                throwable.printStackTrace();
            }

            public void onNext(Waypoint waypoint)
            {
                ((TripManager)tripManagerLazy.get()).replace(event.waypoint, waypoint);
                getListAdapter().notifyDataSetChanged();
            }

            public volatile void onNext(Object obj)
            {
                onNext((Waypoint)obj);
            }

            final CurrentTripFragment this$0;
            final GooglePlaceSelected val$event;

            
            {
                this$0 = CurrentTripFragment.this;
                event = googleplaceselected;
                super();
            }
        }
);
        subscriptions.put(event.waypoint, subscription);
    }

    public void onEvent(PoiAddedToTrip poiaddedtotrip)
    {
        drawTrip();
    }

    public void onEvent(TripLoadedEvent triploadedevent)
    {
        if(triploadedevent.trip != null)
        {
            ((TripManager)tripManagerLazy.get()).setCurrentTrip(triploadedevent.trip.clone());
            drawTrip();
        }
    }

    public void onEvent(WaypointDeleted waypointdeleted)
    {
        RTAnalytics.logEvent(getActivity(), 0x7f0c0071, 0x7f0c004b);
        Subscription subscription = (Subscription)subscriptions.remove(waypointdeleted.waypoint);
        if(subscription != null)
            subscription.unsubscribe();
    }

    public void onPoiAdded()
    {
        if(getListAdapter() != null)
            getListAdapter().notifyDataSetChanged();
        go();
    }

    protected void onRetryButtonClick()
    {
        go();
    }

    void showDescription()
    {
        if(getChildFragmentManager().findFragmentById(0x7f0900b0) == null)
        {
            RTAnalytics.logEvent(getActivity(), 0x7f0c0071, 0x7f0c0046);
            getChildFragmentManager().beginTransaction().setCustomAnimations(0x7f04000d, 0x7f040012).add(0x7f0900b0, new EditDescriptionFragment()).commitAllowingStateLoss();
            InflaterAdapter.setVisibility(darkAlpha, 0);
            ObjectAnimator.ofFloat(darkAlpha, "alpha", new float[] {
                0.0F, 0.5F
            }).start();
        }
    }

    private CurrentTripAdapter adapter;
    Lazy busLazy;
    View darkAlpha;
    DragSortListView listView;
    Lazy objectMapperLazy;
    Lazy okHttpClientLazy;
    Lazy placesLazy;
    Lazy roadtrippersLazy;
    Lazy serializerLazy;
    final Map subscriptions = new HashMap();
    Lazy tripManagerLazy;
    EditText tripName;

}
