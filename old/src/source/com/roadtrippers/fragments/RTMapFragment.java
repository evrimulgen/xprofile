// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.roadtrippers.fragments;

import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.*;
import android.graphics.drawable.AnimationDrawable;
import android.hardware.*;
import android.location.Location;
import android.os.*;
import android.support.v4.app.*;
import android.util.Base64;
import android.view.*;
import android.view.animation.*;
import android.widget.*;
import com.google.android.gms.maps.*;
import com.google.android.gms.maps.model.*;
import com.google.gson.JsonObject;
import com.newrelic.agent.android.instrumentation.BitmapFactoryInstrumentation;
import com.nineoldandroids.animation.Animator;
import com.nineoldandroids.animation.AnimatorListenerAdapter;
import com.nineoldandroids.view.ViewPropertyAnimator;
import com.roadtrippers.adapters.CurrentTripAdapter;
import com.roadtrippers.adapters.base.InflaterAdapter;
import com.roadtrippers.api.GooglePlaces;
import com.roadtrippers.api.Roadtrippers;
import com.roadtrippers.api.models.*;
import com.roadtrippers.api.requests.SearchRequest;
import com.roadtrippers.db.DatabaseHelper;
import com.roadtrippers.events.*;
import com.roadtrippers.fragments.base.RetainedMapFragment;
import com.roadtrippers.util.*;
import com.squareup.otto.Bus;
import dagger.Lazy;
import de.keyboardsurfer.android.widget.crouton.Crouton;
import de.keyboardsurfer.android.widget.crouton.Style;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.*;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import retrofit.RetrofitError;
import rx.*;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
import rx.subscriptions.Subscriptions;

// Referenced classes of package com.roadtrippers.fragments:
//            SelectedPlaceFragment

public class RTMapFragment extends RetainedMapFragment
{
    static final class CompassStatus extends Enum
    {

        public static CompassStatus valueOf(String s)
        {
            return (CompassStatus)Enum.valueOf(com/roadtrippers/fragments/RTMapFragment$CompassStatus, s);
        }

        public static CompassStatus[] values()
        {
            return (CompassStatus[])$VALUES.clone();
        }

        private static final CompassStatus $VALUES[];
        public static final CompassStatus ACTIVE;
        public static final CompassStatus ACTIVE2;
        public static final CompassStatus INACTIVE;

        static 
        {
            INACTIVE = new CompassStatus("INACTIVE", 0);
            ACTIVE = new CompassStatus("ACTIVE", 1);
            ACTIVE2 = new CompassStatus("ACTIVE2", 2);
            CompassStatus acompassstatus[] = new CompassStatus[3];
            acompassstatus[0] = INACTIVE;
            acompassstatus[1] = ACTIVE;
            acompassstatus[2] = ACTIVE2;
            $VALUES = acompassstatus;
        }

        private CompassStatus(String s, int i)
        {
            super(s, i);
        }
    }

    class TouchableWrapper extends FrameLayout
    {

        public boolean onInterceptTouchEvent(MotionEvent motionevent)
        {
            gestureDetector.onTouchEvent(motionevent);
            return super.onInterceptTouchEvent(motionevent);
        }

        GestureDetector gestureDetector;
        final RTMapFragment this$0;

        public TouchableWrapper(Context context)
        {
            this$0 = RTMapFragment.this;
            super(context);
            setBackgroundColor(-1);
            gestureDetector = new GestureDetector(context, new _cls1());
        }
    }


    public RTMapFragment()
    {
        compassStatus = CompassStatus.INACTIVE;
        placeMarkers = new HashMap();
        savedPlaceMarkers = new HashMap();
        searchPlaceMarkers = new HashMap();
        addedPlaceMarkers = new HashMap();
        tripMarkers = new HashMap();
        Map amap[] = new Map[5];
        amap[0] = placeMarkers;
        amap[1] = savedPlaceMarkers;
        amap[2] = searchPlaceMarkers;
        amap[3] = tripMarkers;
        amap[4] = addedPlaceMarkers;
        markerHashes = amap;
    }

    private void actuallyClearTrips(boolean flag)
    {
        Crouton.cancelAllCroutons();
        clearTrip();
        onMapClick(null);
        hideClearTrip();
        if(flag)
            ((TripManager)tripManagerLazy.get()).setCurrentTrip(null);
        ((Bus)bus.get()).post(new TripLoadedEvent(null));
        clearTripImage.setImageResource(0x7f02009e);
        clearPlaceIsHighlighted = false;
    }

    private float distance(LatLng latlng, LatLng latlng1)
    {
        return (float)Math.sqrt(Math.pow(latlng1.latitude - latlng.latitude, 2D) + Math.pow(latlng1.longitude - latlng.longitude, 2D));
    }

    private static Observable getGoogleLineObservable(Trip trip)
    {
        return Observable.create(new rx.Observable.OnSubscribe(trip) {

            public volatile void call(Object obj)
            {
                call((Subscriber)obj);
            }

            public void call(Subscriber subscriber)
            {
                ArrayList arraylist;
                Waypoint awaypoint[];
                int i;
                arraylist = new ArrayList();
                awaypoint = trip.waypoints;
                if(awaypoint.length == 1 || awaypoint.length == 2 && (awaypoint[1] == null || awaypoint[1].name == null))
                {
                    Waypoint waypoint = awaypoint[0];
                    awaypoint = new Waypoint[2];
                    awaypoint[0] = waypoint;
                    awaypoint[1] = waypoint;
                }
                i = 0;
_L5:
                String s;
                if(i * 20 >= awaypoint.length)
                    break; /* Loop/switch isn't completed */
                Log.d("GetPolyline");
                Log.d("WWaypoints --------");
                Waypoint awaypoint1[] = awaypoint;
                int j = awaypoint1.length;
                for(int k = 0; k < j; k++)
                {
                    Waypoint waypoint1 = awaypoint1[k];
                    Object aobj3[] = new Object[1];
                    aobj3[0] = waypoint1.name;
                    Log.d("WWaypoints %s", aobj3);
                }

                Object aobj[] = new Object[4];
                aobj[0] = "/maps/api/directions/json?sensor=true&client=gme-roadtrippersllc";
                aobj[1] = awaypoint[i * 20].getLocationString();
                aobj[2] = awaypoint[Math.min(20 + i * 20, -1 + awaypoint.length)].getLocationString();
                int l = i * 20;
                int i1 = Math.min(20 + i * 20, -1 + awaypoint.length);
                aobj[3] = RTMapFragment.waypointsFrom(awaypoint, l, i1);
                s = String.format("%s&origin=%s&destination=%s&waypoints=%s", aobj);
                GooglePlaces googleplaces;
                String s1;
                String s2;
                googleplaces = (GooglePlaces)RTMapFragment.googlePlacesLazy.get();
                s1 = awaypoint[i * 20].getLocationString();
                s2 = awaypoint[Math.min(20 + i * 20, -1 + awaypoint.length)].getLocationString();
                int i2 = i * 20;
                com.roadtrippers.api.models.Route.Response response1;
                int j2 = Math.min(20 + i * 20, -1 + awaypoint.length);
                response1 = googleplaces.getDirections(s1, s2, RTMapFragment.waypointsFrom(awaypoint, i2, j2), RTMapFragment.hmacSha1(s, "hWSLa7CP4NEjmxJ3l-N8lYCODl4="));
                com.roadtrippers.api.models.Route.Response response = response1;
_L1:
                int j1;
                if(response == null || response.routes == null || response.routes.length == 0)
                    subscriber.onError(new Throwable());
                j1 = 0;
_L3:
                int k1;
                if(j1 >= response.routes.length)
                    break MISSING_BLOCK_LABEL_577;
                k1 = 0;
_L2:
                if(k1 >= response.routes[j1].legs.length)
                    break MISSING_BLOCK_LABEL_571;
                for(int l1 = 0; l1 < response.routes[j1].legs[k1].steps.length; l1++)
                    arraylist.add(response.routes[j1].legs[k1].steps[l1].polyline.points);

                break MISSING_BLOCK_LABEL_565;
                RetrofitError retrofiterror;
                retrofiterror;
                Object aobj1[] = new Object[1];
                aobj1[0] = Integer.valueOf(i);
                Log.d("Error q %d", aobj1);
                Object aobj2[] = new Object[1];
                aobj2[0] = retrofiterror.getUrl();
                Log.d("Error url %s", aobj2);
                Log.d("Error path %s", new Object[] {
                    s
                });
                retrofiterror.printStackTrace();
                subscriber.onError(retrofiterror);
                response = null;
                  goto _L1
                Exception exception;
                exception;
                exception.printStackTrace();
                subscriber.onError(exception);
                response = null;
                  goto _L1
                k1++;
                  goto _L2
                j1++;
                  goto _L3
                i++;
                if(true) goto _L5; else goto _L4
_L4:
                String as[] = new String[arraylist.size()];
                arraylist.toArray(as);
                subscriber.onNext(as);
                return;
            }

            final Trip val$trip;

            
            {
                trip = trip1;
                super();
            }
        }
);
    }

    private Object getObject(Marker marker)
    {
        Object obj = null;
        Map amap[] = markerHashes;
        int i = amap.length;
        int j = 0;
        do
        {
label0:
            {
                if(j < i)
                {
                    obj = amap[j].get(marker);
                    if(obj == null)
                        break label0;
                }
                return obj;
            }
            j++;
        } while(true);
    }

    private static Observable getPolyline(Trip trip)
    {
        return getGoogleLineObservable(trip).onErrorResumeNext(getRTLineObservable(trip)).onErrorResumeNext(Observable.from(new String[0][]));
    }

    private static Observable getRTLineObservable(Trip trip)
    {
        return ((Roadtrippers)roadtrippers.get()).getTripFullDetails(trip.id).onErrorResumeNext(Observable.from(trip)).flatMap(new Func1() {

            public volatile Object call(Object obj)
            {
                return call((Trip)obj);
            }

            public Observable call(Trip trip1)
            {
                if(trip1.legs == null || trip1.legs.length == 0 || trip1.legs[0] == null)
                    return Observable.error(new Throwable((new StringBuilder()).append("Can't load polyline for trip ").append(trip1.name).toString()));
                ArrayList arraylist = new ArrayList();
                for(int i = 0; i < trip1.legs.length; i++)
                {
                    String s = trip1.legs[i].encoded_polyline;
                    if(s != null && s.length() > 0)
                        arraylist.add(s);
                }

                String as[] = new String[arraylist.size()];
                arraylist.toArray(as);
                return Observable.from(new String[][] {
                    as
                });
            }

        }
);
    }

    private void hideClearPlacesButton()
    {
        if(clearPlaceImage.getVisibility() == 0)
            btnsAnimation(clearPlaceImage, 0, -200);
        showClearPlace = false;
        ((Bus)bus.get()).post(PlacesHighlighted.DISABLED);
    }

    private void hideClearTrip()
    {
        btnsAnimation(clearTripImage, 0, 200);
        showClearTrip = false;
        ((Bus)bus.get()).post(TripsHighlighted.DISABLED);
    }

    private static String hmacSha1(String s, String s1)
        throws Exception
    {
        Mac mac = Mac.getInstance("HmacSHA1");
        mac.init(new SecretKeySpec(Base64.decode(s1, 8), mac.getAlgorithm()));
        return Base64.encodeToString(mac.doFinal(s.getBytes()), 8);
    }

    public static Func1 loadPolyline(Lazy lazy, Lazy lazy1)
    {
        return new Func1() {

            public volatile Object call(Object obj)
            {
                return call((Trip)obj);
            }

            public Observable call(Trip trip)
            {
                Waypoint awaypoint[] = trip.waypoints;
                for(int i = 0; i < awaypoint.length; i++)
                    if(awaypoint[i].location == null)
                    {
                        int j = i - 1;
                        awaypoint = CurrentTripAdapter.remove(awaypoint, i);
                        i = j;
                    }

                if(trip.polylines == null)
                    return RTMapFragment.getPolyline(trip).map(trip. new Func1() {

                        public Trip call(String as[])
                        {
                            trip.polylines = as;
                            ((DatabaseHelper)RTMapFragment.database.get()).saveTrip(trip);
                            return trip;
                        }

                        public volatile Object call(Object obj)
                        {
                            return call((String[])obj);
                        }

                        final _cls17 this$0;
                        final Trip val$trip;

            
            {
                this$0 = final__pcls17;
                trip = Trip.this;
                super();
            }
                    }
);
                else
                    return Observable.from(trip);
            }

        }
;
    }

    private Bitmap micropinBitmap(Poi poi)
    {
        int i = getResources().getDimensionPixelSize(0x7f0a0049);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStyle(android.graphics.Paint.Style.FILL);
        paint.setColor(Group.forName(poi.group).getColor(getActivity()));
        Bitmap bitmap = Bitmap.createBitmap(i, i, android.graphics.Bitmap.Config.ARGB_8888);
        (new Canvas(bitmap)).drawCircle(i / 2, i / 2, i / 2, paint);
        return bitmap;
    }

    public static RTMapFragment newInstance(GoogleMapOptions googlemapoptions)
    {
        RTMapFragment rtmapfragment = new RTMapFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable("MapOptions", googlemapoptions);
        rtmapfragment.setArguments(bundle);
        return rtmapfragment;
    }

    static float normalizeDegree(float f)
    {
        return (720F + f) % 360F;
    }

    private void showNoResultsCrouton()
    {
        FragmentActivity fragmentactivity = getActivity();
        View view = LayoutInflater.from(fragmentactivity).inflate(0x7f030068, (ViewGroup)getView(), false);
        final Crouton crouton = Crouton.make(fragmentactivity, view, (ViewGroup)getView());
        crouton.setConfiguration((new de.keyboardsurfer.android.widget.crouton.Configuration.Builder()).setDuration(5000).build());
        ((TextView)view.findViewById(0x7f090147)).setText("No results found");
        view.setOnClickListener(new android.view.View.OnClickListener() {

            public void onClick(View view1)
            {
                crouton.cancel();
            }

            final RTMapFragment this$0;
            final Crouton val$crouton;

            
            {
                this$0 = RTMapFragment.this;
                crouton = crouton1;
                super();
            }
        }
);
        crouton.show();
    }

    static String waypointsFrom(Waypoint awaypoint[], int i, int j)
    {
        StringBuilder stringbuilder = new StringBuilder();
        for(int k = i; k <= j; k++)
            stringbuilder.append(awaypoint[k].getLocationString()).append("|");

        if(stringbuilder.length() > 0)
            stringbuilder.setLength(-1 + stringbuilder.length());
        String s;
        try
        {
            s = URLEncoder.encode(stringbuilder.toString(), "UTF-8");
        }
        catch(UnsupportedEncodingException unsupportedencodingexception)
        {
            unsupportedencodingexception.printStackTrace();
            return "";
        }
        return s;
    }

    void abortPlacesRequestSubscription()
    {
        if(placesRequestSubscription != null)
        {
            Log.d("unsubscribing");
            placesRequestSubscription.unsubscribe();
            placesRequestSubscription = null;
        }
    }

    void abortTripClickRequest()
    {
        if(tripClickSubscription != null)
        {
            tripClickSubscription.unsubscribe();
            tripClickSubscription = null;
        }
    }

    void actuallyClearPlaces()
    {
        abortPlacesRequestSubscription();
        hideProgress();
        removeMarkers(placeMarkers);
        removeMarkers(savedPlaceMarkers);
        removeMarkers(searchPlaceMarkers);
        removeMarkers(addedPlaceMarkers);
        hideClearPlacesButton();
        lastClickedMarker = null;
        onMapClick(null);
        ((Bus)bus.get()).post(ClearPlacesClick.INSTANCE);
        clearPlaceImage.setImageResource(0x7f02009c);
    }

    public void beforeTripLoading()
    {
        abortTripClickRequest();
        showProgress();
    }

    FragmentTransaction beginSlideTransaction()
    {
        return getChildFragmentManager().beginTransaction().setCustomAnimations(0x7f040008, 0x7f040009);
    }

    void btnsAnimation(View view, int i, int j)
    {
        TranslateAnimation translateanimation = new TranslateAnimation(i, j, 0.0F, 0.0F);
        translateanimation.setDuration(getResources().getInteger(0x7f0b0001));
        view.startAnimation(translateanimation);
        byte byte0;
        if(view.getVisibility() == 0)
            byte0 = 4;
        else
            byte0 = 0;
        view.setVisibility(byte0);
    }

    public void clearPlaces()
    {
        ((Handler)handlerLazy.get()).removeCallbacks(showClearPlacesCrouton);
        if(!clearPlaceIsHighlighted)
        {
            ((Handler)handlerLazy.get()).postDelayed(showClearPlacesCrouton, 250L);
            clearPlaceImage.setImageResource(0x7f02009d);
            clearPlaceIsHighlighted = true;
            return;
        } else
        {
            Crouton.cancelAllCroutons();
            actuallyClearPlaces();
            clearPlaceIsHighlighted = false;
            return;
        }
    }

    void clearTrip()
    {
        Iterator iterator = tripMarkers.keySet().iterator();
        do
        {
            if(!iterator.hasNext())
                break;
            Marker marker = (Marker)iterator.next();
            marker.remove();
            if(marker == lastClickedMarker)
                lastClickedMarker = null;
        } while(true);
        tripMarkers.clear();
        if(tripPolyline != null)
            tripPolyline.remove();
    }

    public void clearTrips()
    {
        ((Handler)handlerLazy.get()).removeCallbacks(showClearTripsCrouton);
        if(!clearTripIsHighlighted)
        {
            ((Handler)handlerLazy.get()).postDelayed(showClearTripsCrouton, 250L);
            clearTripImage.setImageResource(0x7f02009f);
            clearTripIsHighlighted = true;
            return;
        } else
        {
            actuallyClearTrips(true);
            return;
        }
    }

    void compassChangeStatus()
    {
        RTAnalytics.logEvent(getActivity(), 0x7f0c006a, 0x7f0c0016);
        CompassStatus compassstatus;
        if(compassStatus == CompassStatus.ACTIVE)
            compassstatus = CompassStatus.ACTIVE2;
        else
            compassstatus = CompassStatus.ACTIVE;
        compassStatus = compassstatus;
        updateCompass();
        restoreClearMarkersState();
    }

    void drawTrip(Trip trip)
    {
        clearTrip();
        GoogleMap googlemap = getMap();
        Waypoint awaypoint[] = trip.waypoints;
        com.google.android.gms.maps.model.LatLngBounds.Builder builder = new com.google.android.gms.maps.model.LatLngBounds.Builder();
        int i = 0;
        int j = 0;
        do
        {
            while(j < awaypoint.length) 
            {
                Waypoint waypoint = awaypoint[j];
                Poi poi = waypoint.poi;
                LatLng latlng;
                if(poi != null)
                    latlng = poi.getLatLng();
                else
                    latlng = waypoint.getLatLng();
                if(latlng != null)
                {
                    MarkerOptions markeroptions = (new MarkerOptions()).position(latlng);
                    Marker marker;
                    if(j == 0)
                    {
                        markeroptions.icon(BitmapDescriptorFactory.fromResource(0x7f020154));
                        markeroptions.snippet("start");
                    } else
                    if(j == -1 + awaypoint.length)
                    {
                        markeroptions.icon(BitmapDescriptorFactory.fromResource(0x7f02013d));
                        markeroptions.snippet("end");
                    } else
                    if(waypoint.type != com.roadtrippers.api.requests.WaypointRequest.Type.via)
                    {
                        markeroptions.icon(generateNumberingBitmapFactory(i, false));
                        markeroptions.snippet(String.valueOf(i));
                    } else
                    {
                        markeroptions.icon(BitmapDescriptorFactory.fromResource(0x7f020156));
                        markeroptions.snippet("via");
                    }
                    builder.include(latlng);
                    marker = googlemap.addMarker(markeroptions);
                    tripMarkers.put(marker, waypoint);
                    i++;
                } else
                {
                    int j2 = j - 1;
                    awaypoint = CurrentTripAdapter.remove(awaypoint, j);
                    trip.waypoints = awaypoint;
                    j = j2;
                }
                j++;
            }
            ArrayList arraylist = new ArrayList();
            String as[] = trip.polylines;
            int k = as.length;
            int l = 0;
            while(l < k) 
            {
                List list1 = decodePoints(as[l]);
                if(list1 != null && list1.size() != 0)
                {
                    arraylist.add(list1.get(0));
                    for(int i2 = 1; i2 < -1 + list1.size(); i2++)
                        arraylist.add(list1.get(i2));

                    arraylist.add(list1.get(-1 + list1.size()));
                }
                l++;
            }
            Object aobj[] = new Object[1];
            aobj[0] = Integer.valueOf(arraylist.size());
            Log.d("Points %d", aobj);
            int i1 = (int)(Math.log10(arraylist.size()) / Math.log10(8D));
            if(i1 < 1)
                i1 = 1;
            Object aobj1[] = new Object[1];
            aobj1[0] = Integer.valueOf(i1);
            Log.d("Points sample rate %d", aobj1);
            arraylist.clear();
            int j1 = as.length;
            int k1 = 0;
            while(k1 < j1) 
            {
                List list = decodePoints(as[k1]);
                if(list != null && list.size() != 0)
                {
                    arraylist.add(list.get(0));
                    for(int l1 = 1; l1 < -1 + list.size(); l1 += i1)
                        arraylist.add(list.get(l1));

                    arraylist.add(list.get(-1 + list.size()));
                }
                k1++;
            }
            Object aobj2[] = new Object[1];
            aobj2[0] = Integer.valueOf(arraylist.size());
            Log.d("Points after sample %d", aobj2);
            tripPolyline = googlemap.addPolyline((new PolylineOptions()).add((LatLng[])arraylist.toArray(new LatLng[arraylist.size()])).width(10F).color(getResources().getColor(0x7f080042)));
            for(Iterator iterator = arraylist.iterator(); iterator.hasNext(); builder.include((LatLng)iterator.next()));
            getMap().animateCamera(CameraUpdateFactory.newLatLngBounds(builder.build(), getResources().getDimensionPixelSize(0x7f0a0054)));
            ((TripManager)tripManagerLazy.get()).setCurrentTrip(trip);
            Bus bus1 = (Bus)bus.get();
            TripLoadedEvent triploadedevent = new TripLoadedEvent(trip);
            bus1.post(triploadedevent);
            showClearTripButton();
            hideProgress();
            mapIsScrolled();
            return;
        } while(true);
    }

    BitmapDescriptor generateNumberingBitmapFactory(int i, boolean flag)
    {
        Resources resources = getResources();
        int j;
        Bitmap bitmap;
        Canvas canvas;
        Paint paint;
        int k;
        int l;
        int j1;
        if(flag)
            j = 0x7f020134;
        else
            j = 0x7f020133;
        bitmap = BitmapFactoryInstrumentation.decodeResource(resources, j).copy(android.graphics.Bitmap.Config.ARGB_8888, true);
        canvas = new Canvas(bitmap);
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(-1);
        paint.setFilterBitmap(true);
        k = (int)((double)bitmap.getWidth() / 1.9299999999999999D);
        l = (int)((double)bitmap.getHeight() / 5.8333000000000004D);
        if(i < 10)
        {
            Resources resources3 = getResources();
            Rect rect;
            String s;
            int k1;
            int l1;
            int i2;
            int j2;
            BitmapDescriptor bitmapdescriptor;
            int l2;
            if(flag)
                l2 = 0x7f0a0043;
            else
                l2 = 0x7f0a0042;
            j1 = resources3.getDimensionPixelSize(l2);
        } else
        if(i < 100)
        {
            Resources resources2 = getResources();
            int k2;
            if(flag)
                k2 = 0x7f0a0045;
            else
                k2 = 0x7f0a0044;
            j1 = resources2.getDimensionPixelSize(k2);
        } else
        {
            Resources resources1 = getResources();
            int i1;
            if(flag)
                i1 = 0x7f0a0047;
            else
                i1 = 0x7f0a0046;
            j1 = resources1.getDimensionPixelSize(i1);
        }
        paint.setTypeface(lobster);
        paint.setTextSize(j1);
        rect = new Rect();
        s = String.valueOf(i);
        paint.getTextBounds(s, 0, s.length(), rect);
        k1 = rect.width();
        l1 = rect.height();
        i2 = (bitmap.getWidth() - k1) / 2;
        j2 = l1 + (l + (k - l1) / 2);
        canvas.drawText(String.valueOf(i), i2, j2, paint);
        bitmapdescriptor = BitmapDescriptorFactory.fromBitmap(bitmap);
        bitmap.recycle();
        return bitmapdescriptor;
    }

    public void hideProgress()
    {
        ((Handler)handlerLazy.get()).removeCallbacks(showProgressRunnable);
        if(mapProgress != null)
        {
            mapProgress.stop();
            InflaterAdapter.setVisibility(progressImage, 4);
            showProgress = false;
        }
    }

    void mapIsScrolled()
    {
        if(compassStatus != CompassStatus.INACTIVE)
        {
            compassStatus = CompassStatus.INACTIVE;
            updateCompass();
        }
    }

    public void onAutocompleteSelected(RTAutocompleteSuggestion rtautocompletesuggestion)
    {
        abortPlacesRequestSubscription();
        showProgress();
        ((Roadtrippers)roadtrippers.get()).getPoi(String.valueOf(rtautocompletesuggestion.getId())).map(new Func1() {

            public Places call(Poi poi)
            {
                return new Places(poi);
            }

            public volatile Object call(Object obj)
            {
                return call((Poi)obj);
            }

            final RTMapFragment this$0;

            
            {
                this$0 = RTMapFragment.this;
                super();
            }
        }
).onErrorResumeNext(searchWithText(rtautocompletesuggestion.getName())).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(searchObserver);
    }

    public void onCameraChange(CameraPosition cameraposition)
    {
        lastCameraPosition = cameraposition;
        if(compassStatus != CompassStatus.ACTIVE2)
        {
            ((Bus)bus.get()).post(cameraposition);
            restoreClearMarkersState();
        }
    }

    public void onCreate(Bundle bundle)
    {
        Object aobj[] = new Object[1];
        String s;
        if(bundle == null)
            s = "null";
        else
            s = bundle.toString();
        aobj[0] = s;
        Log.d("RTMapFragment onCreate bundle: %s", aobj);
        super.onCreate(bundle);
        mWakeLock = ((PowerManager)getActivity().getSystemService("power")).newWakeLock(6, "rotation");
        sensorManager = (SensorManager)getActivity().getSystemService("sensor");
        if(lobster == null)
            Observable.from(getResources().getAssets()).subscribeOn(Schedulers.io()).subscribe(new Action1() {

                public void call(AssetManager assetmanager)
                {
                    lobster = Typeface.createFromAsset(assetmanager, "fonts/Lobster.ttf");
                }

                public volatile void call(Object obj)
                {
                    call((AssetManager)obj);
                }

                final RTMapFragment this$0;

            
            {
                this$0 = RTMapFragment.this;
                super();
            }
            }
);
        if(bundle != null && bundle.containsKey("LAT") && bundle.containsKey("LON"))
        {
            initialLocation = true;
            savedLocation = true;
            savedLat = bundle.getDouble("LAT");
            savedLon = bundle.getDouble("LON");
            savedZoom = bundle.getFloat("ZOOM");
        }
        clearPlaceIsHighlighted = false;
        clearTripIsHighlighted = false;
        ((TripManager)tripManagerLazy.get()).acquire();
    }

    public View onCreateView(LayoutInflater layoutinflater, ViewGroup viewgroup, Bundle bundle)
    {
        TouchableWrapper touchablewrapper = new TouchableWrapper(getActivity());
        touchablewrapper.addView(super.onCreateView(layoutinflater, viewgroup, bundle));
        touchablewrapper.invalidate();
        LayoutInflater.from(getActivity()).inflate(0x7f030051, touchablewrapper, true);
        return touchablewrapper;
    }

    public void onDestroy()
    {
        abortPlacesRequestSubscription();
        abortTripClickRequest();
        ((TripManager)tripManagerLazy.get()).release();
        super.onDestroy();
    }

    public void onDestroyView()
    {
        boolean flag = true;
        boolean flag1;
        if(clearPlaceImage.getVisibility() == 0)
            flag1 = flag;
        else
            flag1 = false;
        showClearPlace = flag1;
        if(clearTripImage.getVisibility() != 0)
            flag = false;
        showClearTrip = flag;
        mapProgress = null;
        super.onDestroyView();
    }

    public void onEvent(DeleteCurrentTrip deletecurrenttrip)
    {
        actuallyClearTrips(true);
    }

    public void onEvent(NewTripCreated newtripcreated)
    {
        actuallyClearTrips(false);
    }

    public void onGooglePlaceSelected(final GooglePlace place)
    {
        abortPlacesRequestSubscription();
        showProgress();
        placesRequestSubscription = ((GooglePlaces)googlePlacesLazy.get()).details(place.reference, "AIzaSyBm8Rs4h4xvSJ1AD5O8Aej-h3KUkL2DV60").map(new Func1() {

            public GooglePlaceDetail call(com.roadtrippers.api.models.GooglePlaceDetail.Response response)
            {
                return response.result;
            }

            public volatile Object call(Object obj)
            {
                return call((com.roadtrippers.api.models.GooglePlaceDetail.Response)obj);
            }

            final RTMapFragment this$0;

            
            {
                this$0 = RTMapFragment.this;
                super();
            }
        }
).flatMap(new Func1() {

            public volatile Object call(Object obj)
            {
                return call((GooglePlaceDetail)obj);
            }

            public Observable call(GooglePlaceDetail googleplacedetail)
            {
                return ((Roadtrippers)RTMapFragment.roadtrippers.get()).resolveGoogle(googleplacedetail.geometry.location.lat, googleplacedetail.geometry.location.lng, googleplacedetail.id, googleplacedetail.formatted_address, googleplacedetail.name).map(new Func1() {

                    public Places call(Poi poi)
                    {
                        return new Places(poi);
                    }

                    public volatile Object call(Object obj)
                    {
                        return call((Poi)obj);
                    }

                    final _cls8 this$1;

            
            {
                this$1 = _cls8.this;
                super();
            }
                }
).onErrorResumeNext(searchWithText(place.split()[0]));
            }

            final RTMapFragment this$0;
            final GooglePlace val$place;

            
            {
                this$0 = RTMapFragment.this;
                place = googleplace;
                super();
            }
        }
).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(searchObserver);
    }

    public void onMapClick(LatLng latlng)
    {
        if(lastClickedMarker != null)
        {
            if(!tripMarkers.containsKey(lastClickedMarker) && (getObject(lastClickedMarker) instanceof Poi) && ((Poi)getObject(lastClickedMarker)).isSearchResult)
                setSearchIcon(lastClickedMarker, false);
            else
                setMarkerIcon(lastClickedMarker, false);
            lastClickedMarker = null;
        }
        removeSelectedPlaceFragment();
        restoreClearMarkersState();
    }

    public boolean onMarkerClick(Marker marker)
    {
        Object obj = getObject(lastClickedMarker);
        Object obj1 = getObject(marker);
        if(obj1 != null && obj1 != obj)
        {
            JsonObject jsonobject;
            String s;
            if(lastClickedMarker != null)
                if(!tripMarkers.containsKey(lastClickedMarker) && (obj instanceof Poi) && ((Poi)obj).isSearchResult)
                    setSearchIcon(lastClickedMarker, false);
                else
                    setMarkerIcon(lastClickedMarker, false);
            if(!tripMarkers.containsKey(marker) && (obj1 instanceof Poi) && !((Poi)obj1).isSearchResult)
                setMarkerIcon(marker, true);
            else
            if((obj1 instanceof Poi) && ((Poi)obj1).isSearchResult)
                setSearchIcon(marker, true);
            else
                setMarkerIcon(marker, true);
            jsonobject = new JsonObject();
            if(obj1 instanceof Poi)
                s = "poi";
            else
                s = "waypoint";
            jsonobject.addProperty("pin_type", s);
            if(obj1 instanceof Poi)
                jsonobject.addProperty("poi_id", ((Poi)obj1).id);
            RTAnalytics.logEvent(getActivity(), 0x7f0c006a, 0x7f0c0024, jsonobject.toString());
            beginSlideTransaction().replace(0x7f090113, SelectedPlaceFragment.newInstance(((Serializer)jackson.get()).serialize(obj1), obj1 instanceof Waypoint)).commitAllowingStateLoss();
        }
        lastClickedMarker = marker;
        restoreClearMarkersState();
        return true;
    }

    public void onPause()
    {
        super.onPause();
        if(getMap() != null)
            getMap().setOnMyLocationChangeListener(null);
        if(compassStatus == CompassStatus.ACTIVE2)
            unregisterSensors();
    }

    void onPlacesLoaded(Poi apoi[], Map map, boolean flag)
    {
        GoogleMap googlemap = getMap();
        com.google.android.gms.maps.model.LatLngBounds.Builder builder = new com.google.android.gms.maps.model.LatLngBounds.Builder();
        int i = 0;
        if(apoi != null)
        {
            int j = apoi.length;
            int k = 0;
            while(k < j) 
            {
                Poi poi = apoi[k];
                if(poi.getStatus() == null || !poi.getStatus().equals("inactive"))
                {
                    LatLng latlng = new LatLng(poi.latitude, poi.longitude);
                    MarkerOptions markeroptions = (new MarkerOptions()).title(poi.group).position(latlng);
                    if(map == placeMarkers)
                    {
                        if(i < 25)
                        {
                            int i1 = Group.forName(poi.group).getSmallMarkerRes(getActivity());
                            if(i1 > 0)
                                markeroptions.icon(BitmapDescriptorFactory.fromResource(i1));
                        } else
                        {
                            Bitmap bitmap = micropinBitmap(poi);
                            markeroptions.icon(BitmapDescriptorFactory.fromBitmap(bitmap));
                            markeroptions.snippet("micro");
                            bitmap.recycle();
                        }
                    } else
                    if(map == searchPlaceMarkers)
                    {
                        poi.isSearchResult = true;
                        if(0x7f02014c > 0)
                            markeroptions.icon(BitmapDescriptorFactory.fromResource(0x7f02014c));
                    } else
                    {
                        int l = Group.forName(poi.group).getSmallMarkerRes(getActivity());
                        if(l > 0)
                            markeroptions.icon(BitmapDescriptorFactory.fromResource(l));
                    }
                    map.put(googlemap.addMarker(markeroptions), poi);
                    builder.include(latlng);
                    i++;
                }
                k++;
            }
        }
        if(map == searchPlaceMarkers)
        {
            LatLngBounds latlngbounds = builder.build();
            ArrayList arraylist;
            if(distance(latlngbounds.northeast, latlngbounds.southwest) <= 1E-005F)
                getMap().animateCamera(CameraUpdateFactory.newLatLngZoom(latlngbounds.northeast, 17F));
            else
                getMap().animateCamera(CameraUpdateFactory.newLatLngBounds(latlngbounds, getResources().getDimensionPixelSize(0x7f0a0054)));
            mapIsScrolled();
        }
        if(map == placeMarkers || map == searchPlaceMarkers)
        {
            arraylist = new ArrayList(placeMarkers.size() + searchPlaceMarkers.size());
            arraylist.addAll(placeMarkers.values());
            arraylist.addAll(searchPlaceMarkers.values());
            ((Bus)bus.get()).post(((Object) (arraylist.toArray(new Poi[arraylist.size()]))));
            hideProgress();
        }
        if(placeMarkers.size() > 0 || addedPlaceMarkers.size() > 0 || savedPlaceMarkers.size() > 0 || searchPlaceMarkers.size() > 0 || flag)
        {
            showClearPlacesButton();
            return;
        } else
        {
            hideClearPlacesButton();
            return;
        }
    }

    public void onResume()
    {
        super.onResume();
        if(getMap() != null)
            getMap().setOnMyLocationChangeListener(freshLocationProvider);
        if(compassStatus == CompassStatus.ACTIVE2)
            registerSensors();
    }

    public void onSaveInstanceState(Bundle bundle)
    {
        bundle.putBoolean("showClearPlace", showClearPlace);
        bundle.putBoolean("showClearTrip", showClearTrip);
        if(lastCameraPosition != null)
        {
            bundle.putDouble("LAT", lastCameraPosition.target.latitude);
            bundle.putDouble("LON", lastCameraPosition.target.longitude);
            bundle.putFloat("ZOOM", lastCameraPosition.zoom);
        }
        super.onSaveInstanceState(bundle);
    }

    public void onSearchText(String s)
    {
        abortPlacesRequestSubscription();
        showProgress();
        placesRequestSubscription = searchWithText(s).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(searchObserver);
    }

    public void onStop()
    {
        super.onStop();
    }

    public void onTripClick(final Trip trip)
    {
        Object aobj[] = new Object[1];
        aobj[0] = trip.name;
        Log.d("TripClick: %s ", aobj);
        initialLocation = true;
        beforeTripLoading();
        tripClickSubscription = ((Roadtrippers)roadtrippers.get()).getTrip(trip.id).flatMap(loadPolyline(okHttpClientLazy, objectMapperLazy)).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer() {

            public void onCompleted()
            {
            }

            public void onError(Throwable throwable)
            {
                Trip trip1 = ((DatabaseHelper)RTMapFragment.database.get()).getTrip(trip.id);
                throwable.printStackTrace();
                if(trip1 != null && trip1.polylines != null && trip1.polylines.length > 0 && trip1.polylines[0] != null)
                {
                    Object aobj1[] = new Object[1];
                    aobj1[0] = trip1.name;
                    Log.d("Trip from database %s", aobj1);
                    drawTrip(trip1);
                    ((Bus)bus.get()).post(TripPolylineEvent.EVENT);
                    return;
                } else
                {
                    ((Bus)bus.get()).post(TripLoadFail.EVENT);
                    tripLoadingError(throwable);
                    return;
                }
            }

            public void onNext(Trip trip1)
            {
                drawTrip(trip1);
                ((Bus)bus.get()).post(TripPolylineEvent.EVENT);
            }

            public volatile void onNext(Object obj)
            {
                onNext((Trip)obj);
            }

            final RTMapFragment this$0;
            final Trip val$trip;

            
            {
                this$0 = RTMapFragment.this;
                trip = trip1;
                super();
            }
        }
);
    }

    public void onTripUpdatedEvent(TripUpdatedEvent tripupdatedevent)
    {
        if(tripupdatedevent.trip != null)
        {
            drawTrip(tripupdatedevent.trip);
            return;
        } else
        {
            tripLoadingError(tripupdatedevent.throwable);
            return;
        }
    }

    public void onUpdatingTripEvent(UpdatingTrip updatingtrip)
    {
        beforeTripLoading();
    }

    public void onViewCreated(View view, Bundle bundle)
    {
        super.onViewCreated(view, bundle);
        mapProgress = (AnimationDrawable)progressImage.getDrawable();
        if(bundle != null)
        {
            showClearPlace = bundle.getBoolean("showClearPlace", false);
            showClearTrip = bundle.getBoolean("showClearTrip", false);
        }
        if(showClearPlace)
            InflaterAdapter.setVisibility(clearPlaceImage, 0);
        if(showClearTrip)
            InflaterAdapter.setVisibility(clearTripImage, 0);
        if(showProgress)
            showProgress();
    }

    public LocationChangedEvent produceLocation()
    {
        Location location;
        if(getMap() != null)
            location = getMap().getMyLocation();
        else
            location = null;
        return new LocationChangedEvent(location);
    }

    void registerSensors()
    {
        Sensor sensor = sensorManager.getDefaultSensor(3);
        sensorManager.registerListener(rotationListener, sensor, 3);
        ((Handler)handlerLazy.get()).postDelayed(mCompassViewUpdater, 20L);
        mWakeLock.acquire();
    }

    void removeMarkers(Map map)
    {
        Iterator iterator = map.keySet().iterator();
        do
        {
            if(!iterator.hasNext())
                break;
            Marker marker = (Marker)iterator.next();
            marker.remove();
            if(marker == lastClickedMarker)
                lastClickedMarker = null;
        } while(true);
        map.clear();
    }

    void removeSelectedPlaceFragment()
    {
        final Fragment placeFragment = getChildFragmentManager().findFragmentById(0x7f090113);
        if(placeFragment != null)
            ViewPropertyAnimator.animate(placeFragment.getView()).translationX(-placeFragment.getView().getWidth()).setListener(new AnimatorListenerAdapter() {

                public void onAnimationEnd(Animator animator)
                {
                    getChildFragmentManager().beginTransaction().remove(placeFragment).commitAllowingStateLoss();
                }

                final RTMapFragment this$0;
                final Fragment val$placeFragment;

            
            {
                this$0 = RTMapFragment.this;
                placeFragment = fragment;
                super();
            }
            }
).start();
    }

    void restoreClearMarkersState()
    {
        if(clearPlaceIsHighlighted)
        {
            Crouton.cancelAllCroutons();
            ((Handler)handlerLazy.get()).removeCallbacks(showClearPlacesCrouton);
            clearPlaceImage.setImageResource(0x7f02009c);
            clearPlaceIsHighlighted = false;
        }
        if(clearTripIsHighlighted)
        {
            Crouton.cancelAllCroutons();
            ((Handler)handlerLazy.get()).removeCallbacks(showClearTripsCrouton);
            clearTripImage.setImageResource(0x7f02009e);
            clearTripIsHighlighted = false;
        }
    }

    public void search(final Group groups[])
    {
        abortPlacesRequestSubscription();
        if(getMap() == null)
            return;
        showProgress();
        final LatLngBounds latLngBounds = getMap().getProjection().getVisibleRegion().latLngBounds;
        if(placesRequestSubscription != null)
            placesRequestSubscription.unsubscribe();
        placesRequestSubscription = Observable.create(new rx.Observable.OnSubscribeFunc() {

            public Subscription onSubscribe(Observer observer)
            {
                StringBuilder stringbuilder = new StringBuilder();
                if(groups != null)
                {
                    boolean flag = true;
                    if(groups[0].name != null)
                        flag = false;
                    for(int i = ((flag) ? 1 : 0); i < groups.length; i++)
                    {
                        Group group = groups[i];
                        if(!group.checked)
                            continue;
                        Category acategory[] = group.categories;
                        int j = acategory.length;
                        for(int k = 0; k < j; k++)
                        {
                            Category category = acategory[k];
                            if(category == null)
                                Log.d("Category is null???");
                            if(category != null && category.checked)
                                stringbuilder.append(category.value).append(',');
                        }

                    }

                }
                if(stringbuilder.length() > 0)
                {
                    stringbuilder.setLength(-1 + stringbuilder.length());
                    double ad[] = new double[4];
                    ad[0] = latLngBounds.southwest.latitude;
                    ad[1] = latLngBounds.southwest.longitude;
                    ad[2] = latLngBounds.northeast.latitude;
                    ad[3] = latLngBounds.northeast.longitude;
                    SearchRequest searchrequest = new SearchRequest(stringbuilder.toString(), ad, "list", 50);
                    Object aobj[] = new Object[1];
                    aobj[0] = ((Serializer)RTMapFragment.jackson.get()).serialize(searchrequest);
                    Log.d("SearchRequest %s", aobj);
                    try
                    {
                        observer.onNext(((Roadtrippers)RTMapFragment.roadtrippers.get()).search(searchrequest).response.results);
                    }
                    catch(Throwable throwable)
                    {
                        observer.onError(throwable);
                    }
                } else
                {
                    observer.onNext(null);
                }
                return Subscriptions.empty();
            }

            final RTMapFragment this$0;
            final Group val$groups[];
            final LatLngBounds val$latLngBounds;

            
            {
                this$0 = RTMapFragment.this;
                groups = agroup;
                latLngBounds = latlngbounds;
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
                ((Bus)bus.get()).post(TripLoadFail.EVENT);
                if(getView() != null)
                {
                    if(placeMarkers.isEmpty())
                        ((Bus)bus.get()).post(new Poi[0]);
                    hideProgress();
                }
            }

            public volatile void onNext(Object obj)
            {
                onNext((Poi[])obj);
            }

            public void onNext(Poi apoi[])
            {
                if(apoi == null || apoi.length == 0)
                {
                    Crouton.cancelAllCroutons();
                    Crouton.makeText(getActivity(), "No results found.", Style.ALERT);
                }
                removeMarkers(placeMarkers);
                RTMapFragment rtmapfragment = RTMapFragment.this;
                Map map = placeMarkers;
                boolean flag;
                if(apoi != null)
                    flag = true;
                else
                    flag = false;
                rtmapfragment.onPlacesLoaded(apoi, map, flag);
            }

            final RTMapFragment this$0;

            
            {
                this$0 = RTMapFragment.this;
                super();
            }
        }
);
    }

    Observable searchWithText(final String searchText)
    {
        return Observable.create(new rx.Observable.OnSubscribeFunc() {

            public Subscription onSubscribe(Observer observer)
            {
                try
                {
                    Places places = ((Roadtrippers)RTMapFragment.roadtrippers.get()).search(searchText).response;
                    observer.onNext(places);
                    if(places.results.length == 0)
                    {
                        Crouton.cancelAllCroutons();
                        showNoResultsCrouton();
                    }
                }
                catch(Exception exception)
                {
                    observer.onError(exception);
                }
                return Subscriptions.empty();
            }

            final RTMapFragment this$0;
            final String val$searchText;

            
            {
                this$0 = RTMapFragment.this;
                searchText = s;
                super();
            }
        }
);
    }

    void setMarkerIcon(Marker marker, boolean flag)
    {
        if(marker.getSnippet() == null)
        {
            setMarkerIcon(((Context) (getActivity())), marker, flag);
            return;
        } else
        {
            setTripMarkerIcon(marker, flag);
            return;
        }
    }

    public void setSavedPlaces(Poi apoi[])
    {
        removeMarkers(savedPlaceMarkers);
        onPlacesLoaded(apoi, savedPlaceMarkers, false);
        hideProgress();
        com.google.android.gms.maps.model.LatLngBounds.Builder builder = new com.google.android.gms.maps.model.LatLngBounds.Builder();
        int i = apoi.length;
        for(int j = 0; j < i; j++)
        {
            Poi poi = apoi[j];
            builder.include(new LatLng(poi.latitude, poi.longitude));
        }

        LatLngBounds latlngbounds = builder.build();
        if(distance(latlngbounds.northeast, latlngbounds.southwest) <= 1E-005F)
            getMap().animateCamera(CameraUpdateFactory.newLatLngZoom(latlngbounds.northeast, 17F));
        else
            getMap().animateCamera(CameraUpdateFactory.newLatLngBounds(latlngbounds, getResources().getDimensionPixelSize(0x7f0a0054)));
        mapIsScrolled();
    }

    void setSearchIcon(Marker marker, boolean flag)
    {
        int i;
        if(flag)
            i = 0x7f02014d;
        else
            i = 0x7f02014c;
        marker.setIcon(BitmapDescriptorFactory.fromResource(i));
    }

    void setTripMarkerIcon(Marker marker, boolean flag)
    {
        String s = marker.getSnippet();
        int j;
        int k;
        if("start".equals(s))
        {
            int i;
            Bitmap bitmap;
            if(flag)
                i = 0x7f020155;
            else
                i = 0x7f020154;
            try
            {
                marker.setIcon(BitmapDescriptorFactory.fromResource(i));
                return;
            }
            catch(Exception exception)
            {
                return;
            }
        }
        if(!"end".equals(s))
            break MISSING_BLOCK_LABEL_66;
        if(flag)
            j = 0x7f02013e;
        else
            j = 0x7f02013d;
        marker.setIcon(BitmapDescriptorFactory.fromResource(j));
        return;
        if(!"via".equals(s))
            break MISSING_BLOCK_LABEL_96;
        if(flag)
            k = 0x7f020157;
        else
            k = 0x7f020156;
        marker.setIcon(BitmapDescriptorFactory.fromResource(k));
        return;
        if("micro".equals(s))
        {
            bitmap = micropinBitmap((Poi)getObject(marker));
            marker.setIcon(BitmapDescriptorFactory.fromBitmap(bitmap));
            bitmap.recycle();
            return;
        }
        marker.setIcon(generateNumberingBitmapFactory(Integer.valueOf(s).intValue(), flag));
        return;
    }

    void showClearPlacesButton()
    {
        if(clearPlaceImage.getVisibility() != 0)
            btnsAnimation(clearPlaceImage, -200, 0);
        ((Bus)bus.get()).post(PlacesHighlighted.ENABLED);
        showClearPlace = true;
    }

    void showClearTripButton()
    {
        if(clearTripImage.getVisibility() != 0)
            btnsAnimation(clearTripImage, 200, 0);
        ((Bus)bus.get()).post(TripsHighlighted.ENABLED);
        showClearTrip = true;
    }

    void showImageCrouton(int i)
    {
        if(getView() != null)
        {
            View view = LayoutInflater.from(getActivity()).inflate(0x7f03003e, (ViewGroup)getView(), false);
            Crouton.cancelAllCroutons();
            getView().setId(0xbae7f);
            Crouton.make(getActivity(), view, 0xbae7f, (new de.keyboardsurfer.android.widget.crouton.Configuration.Builder()).setDuration(-1).build()).show();
        }
    }

    public void showProgress()
    {
        ((Handler)handlerLazy.get()).removeCallbacks(showProgressRunnable);
        ((Handler)handlerLazy.get()).postDelayed(showProgressRunnable, 50L);
    }

    void tripLoadingError(Throwable throwable)
    {
        Log.e(throwable);
        if(getView() != null)
            hideProgress();
    }

    void unregisterSensors()
    {
        ((Handler)handlerLazy.get()).removeCallbacks(mCompassViewUpdater);
        sensorManager.unregisterListener(rotationListener);
        if(mWakeLock.isHeld())
            mWakeLock.release();
    }

    void updateCompass()
    {
        if(getMap() == null)
            return;
        Location location = getMap().getMyLocation();
        if(location != null)
        {
            static class _cls21
            {

                static final int $SwitchMap$com$roadtrippers$fragments$RTMapFragment$CompassStatus[];

                static 
                {
                    $SwitchMap$com$roadtrippers$fragments$RTMapFragment$CompassStatus = new int[CompassStatus.values().length];
                    try
                    {
                        $SwitchMap$com$roadtrippers$fragments$RTMapFragment$CompassStatus[CompassStatus.INACTIVE.ordinal()] = 1;
                    }
                    catch(NoSuchFieldError nosuchfielderror) { }
                    try
                    {
                        $SwitchMap$com$roadtrippers$fragments$RTMapFragment$CompassStatus[CompassStatus.ACTIVE.ordinal()] = 2;
                    }
                    catch(NoSuchFieldError nosuchfielderror1) { }
                    try
                    {
                        $SwitchMap$com$roadtrippers$fragments$RTMapFragment$CompassStatus[CompassStatus.ACTIVE2.ordinal()] = 3;
                    }
                    catch(NoSuchFieldError nosuchfielderror2)
                    {
                        return;
                    }
                }
            }

            switch(_cls21..SwitchMap.com.roadtrippers.fragments.RTMapFragment.CompassStatus[compassStatus.ordinal()])
            {
            default:
                return;

            case 1: // '\001'
                unregisterSensors();
                compassImage.setImageResource(0x7f0200db);
                return;

            case 2: // '\002'
                unregisterSensors();
                getMap().animateCamera(CameraUpdateFactory.newCameraPosition(CameraPosition.builder(getMap().getCameraPosition()).target(new LatLng(location.getLatitude(), location.getLongitude())).zoom(17F).bearing(0.0F).tilt(0.0F).build()));
                compassImage.setImageResource(0x7f0200d9);
                return;

            case 3: // '\003'
                registerSensors();
                break;
            }
            compassImage.setImageResource(0x7f0200da);
            return;
        } else
        {
            compassStatus = CompassStatus.INACTIVE;
            unregisterSensors();
            compassImage.setImageResource(0x7f0200db);
            Crouton.cancelAllCroutons();
            Croutons.showLocationUnavailableError((ViewGroup)getView(), getActivity());
            return;
        }
    }

    public void zoomAndSelectPoi(Poi poi)
    {
        showClearPlacesButton();
        removeMarkers(addedPlaceMarkers);
        onPlacesLoaded(new Poi[] {
            poi
        }, addedPlaceMarkers, false);
        getMap().animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(poi.latitude, poi.longitude), 17F));
        Iterator iterator = addedPlaceMarkers.keySet().iterator();
        if(iterator.hasNext())
            onMarkerClick((Marker)iterator.next());
        mapIsScrolled();
    }

    public static final int CROUTON_ID = 0xbae7f;
    public static final String LAT = "LAT";
    public static final String LON = "LON";
    static final float MAX_ROTATE_DEGREE = 1F;
    public static final String ZOOM = "ZOOM";
    static Lazy database;
    static Lazy googlePlacesLazy;
    static Lazy jackson;
    static Lazy roadtrippers;
    Map addedPlaceMarkers;
    Lazy bus;
    ImageView clearPlaceImage;
    boolean clearPlaceIsHighlighted;
    ImageView clearTripImage;
    boolean clearTripIsHighlighted;
    ImageView compassImage;
    CompassStatus compassStatus;
    final com.google.android.gms.maps.GoogleMap.OnMyLocationChangeListener freshLocationProvider = new com.google.android.gms.maps.GoogleMap.OnMyLocationChangeListener() {

        public void onMyLocationChange(Location location)
        {
            if(compassStatus != CompassStatus.ACTIVE2) goto _L2; else goto _L1
_L1:
            getMap().animateCamera(CameraUpdateFactory.newCameraPosition(CameraPosition.builder(getMap().getCameraPosition()).target(new LatLng(location.getLatitude(), location.getLongitude())).zoom(17F).bearing(0.0F).tilt(0.0F).build()));
_L4:
            ((Bus)bus.get()).post(new LocationChangedEvent(location));
            return;
_L2:
            if(!initialLocation)
            {
                initialLocation = true;
                getMap().moveCamera(CameraUpdateFactory.newCameraPosition(CameraPosition.builder(getMap().getCameraPosition()).target(new LatLng(location.getLatitude(), location.getLongitude())).zoom(17F).bearing(0.0F).tilt(0.0F).build()));
            } else
            if(savedLocation)
            {
                savedLocation = false;
                getMap().moveCamera(CameraUpdateFactory.newCameraPosition(CameraPosition.builder(getMap().getCameraPosition()).target(new LatLng(savedLat, savedLon)).zoom(savedZoom).bearing(0.0F).tilt(0.0F).build()));
            }
            if(true) goto _L4; else goto _L3
_L3:
        }

        final RTMapFragment this$0;

            
            {
                this$0 = RTMapFragment.this;
                super();
            }
    }
;
    Lazy handlerLazy;
    boolean initialLocation;
    final AccelerateInterpolator interpolator = new AccelerateInterpolator();
    private CameraPosition lastCameraPosition;
    Marker lastClickedMarker;
    Typeface lobster;
    final Runnable mCompassViewUpdater = new Runnable() {

        public void run()
        {
            if(getMap() == null)
                return;
            float f = getMap().getCameraPosition().bearing;
            if(f != targetDirection)
            {
                float f1 = targetDirection;
                float f2;
                float f3;
                AccelerateInterpolator accelerateinterpolator;
                float f4;
                float f5;
                Location location;
                if(f1 - f > 180F)
                    f1 -= 360F;
                else
                if(f1 - f < -180F)
                    f1 += 360F;
                f2 = f1 - f;
                if(Math.abs(f2) > 1.0F)
                    if(f2 > 0.0F)
                        f2 = 1.0F;
                    else
                        f2 = -1F;
                f3 = f1 - f;
                accelerateinterpolator = interpolator;
                if(Math.abs(f2) > 1.0F)
                    f4 = 0.4F;
                else
                    f4 = 0.3F;
                f5 = RTMapFragment.normalizeDegree(f + f3 * accelerateinterpolator.getInterpolation(f4));
                location = getMap().getMyLocation();
                getMap().moveCamera(CameraUpdateFactory.newCameraPosition(CameraPosition.builder().target(new LatLng(location.getLatitude(), location.getLongitude())).zoom(17F).bearing(f5).tilt(0.0F).build()));
            }
            ((Handler)handlerLazy.get()).postDelayed(mCompassViewUpdater, 20L);
        }

        final RTMapFragment this$0;

            
            {
                this$0 = RTMapFragment.this;
                super();
            }
    }
;
    android.os.PowerManager.WakeLock mWakeLock;
    AnimationDrawable mapProgress;
    Map markerHashes[];
    Lazy objectMapperLazy;
    Lazy okHttpClientLazy;
    ViewGroup overlayRoot;
    Lazy persistenceLazy;
    Map placeMarkers;
    Subscription placesRequestSubscription;
    ImageView progressImage;
    final SensorEventListener rotationListener = new SensorEventListener() {

        public void onAccuracyChanged(Sensor sensor, int i)
        {
        }

        public void onSensorChanged(SensorEvent sensorevent)
        {
            float f = sensorevent.values[0];
            targetDirection = RTMapFragment.normalizeDegree(f);
        }

        final RTMapFragment this$0;

            
            {
                this$0 = RTMapFragment.this;
                super();
            }
    }
;
    double savedLat;
    boolean savedLocation;
    double savedLon;
    Map savedPlaceMarkers;
    float savedZoom;
    final Observer searchObserver = new Observer() {

        public void onCompleted()
        {
        }

        public void onError(Throwable throwable)
        {
            if(getView() != null)
                hideProgress();
        }

        public void onNext(Places places)
        {
            removeMarkers(searchPlaceMarkers);
            onPlacesLoaded(places.results, searchPlaceMarkers, false);
            if(places != null && places.results != null && places.results.length == 1)
            {
                Iterator iterator = searchPlaceMarkers.keySet().iterator();
                if(iterator.hasNext())
                    onMarkerClick((Marker)iterator.next());
            }
        }

        public volatile void onNext(Object obj)
        {
            onNext((Places)obj);
        }

        final RTMapFragment this$0;

            
            {
                this$0 = RTMapFragment.this;
                super();
            }
    }
;
    Map searchPlaceMarkers;
    SensorManager sensorManager;
    boolean showClearPlace;
    final Runnable showClearPlacesCrouton = new Runnable() {

        public void run()
        {
            showImageCrouton(0x7f02015f);
        }

        final RTMapFragment this$0;

            
            {
                this$0 = RTMapFragment.this;
                super();
            }
    }
;
    boolean showClearTrip;
    final Runnable showClearTripsCrouton = new Runnable() {

        public void run()
        {
            showImageCrouton(0x7f0201b6);
        }

        final RTMapFragment this$0;

            
            {
                this$0 = RTMapFragment.this;
                super();
            }
    }
;
    boolean showProgress;
    final Runnable showProgressRunnable = new Runnable() {

        public void run()
        {
            if(mapProgress != null)
            {
                if(!mapProgress.isRunning())
                    mapProgress.start();
                InflaterAdapter.setVisibility(progressImage, 0);
                showProgress = true;
            }
        }

        final RTMapFragment this$0;

            
            {
                this$0 = RTMapFragment.this;
                super();
            }
    }
;
    float targetDirection;
    Subscription tripClickSubscription;
    Lazy tripManagerLazy;
    Map tripMarkers;
    Polyline tripPolyline;




    // Unreferenced inner class com/roadtrippers/fragments/RTMapFragment$TouchableWrapper$1

/* anonymous class */
    class TouchableWrapper._cls1 extends android.view.GestureDetector.SimpleOnGestureListener
    {

        public boolean onScroll(MotionEvent motionevent, MotionEvent motionevent1, float f, float f1)
        {
            if(f > 0.0F || f1 > 0.0F)
                mapIsScrolled();
            return super.onScroll(motionevent, motionevent1, f, f1);
        }

        final TouchableWrapper this$1;
        final RTMapFragment val$this$0;

            
            {
                this$1 = final_touchablewrapper;
                this$0 = RTMapFragment.this;
                super();
            }
    }

}
