// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.roadtrippers.fragments;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.*;
import android.text.*;
import android.text.method.LinkMovementMethod;
import android.view.*;
import android.view.inputmethod.InputMethodManager;
import android.widget.*;
import com.dmitriy.tarasov.android.intents.IntentUtils;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.location.*;
import com.google.android.gms.maps.GoogleMapOptions;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.nineoldandroids.animation.*;
import com.nineoldandroids.view.ViewHelper;
import com.roadtrippers.adapters.base.InflaterAdapter;
import com.roadtrippers.anim.HeightEvaluator;
import com.roadtrippers.anim.LayoutHeightAnimation;
import com.roadtrippers.api.Roadtrippers;
import com.roadtrippers.api.models.*;
import com.roadtrippers.events.*;
import com.roadtrippers.fragments.base.RetainedProgressFragment;
import com.roadtrippers.util.*;
import com.roadtrippers.widget.NotifyingScrollView;
import com.squareup.otto.Bus;
import com.squareup.picasso.*;
import dagger.Lazy;
import de.keyboardsurfer.android.widget.crouton.Crouton;
import de.keyboardsurfer.android.widget.crouton.Style;
import io.segment.android.models.EasyJSONObject;
import java.io.*;
import java.net.*;
import java.util.List;
import java.util.concurrent.Callable;
import rx.*;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

// Referenced classes of package com.roadtrippers.fragments:
//            RateAndReviewFragment, GalleryFragment, DetailMapFragment, AddImageFragment

public class DetailFragment extends RetainedProgressFragment
    implements com.google.android.gms.common.GooglePlayServicesClient.ConnectionCallbacks, com.google.android.gms.common.GooglePlayServicesClient.OnConnectionFailedListener, LocationListener
{
    static class HtmlPlace
    {

        HtmlPlace computeHtml()
        {
            if(poi.description != null)
                html = Html.fromHtml(poi.description);
            return this;
        }

        Spanned html;
        Poi poi;

        HtmlPlace(Poi poi1)
        {
            poi = poi1;
        }
    }


    public DetailFragment()
    {
        lastKnownLocation = null;
    }

    private void calculateInitialTranslate(int i, int j)
    {
        initialBottomTranslate = (int)(0.5F * (float)(mainChild.getHeight() - i - addressShadow.getHeight() - j));
    }

    public static Intent createDirectionsIntent(Location location, Location location1)
    {
        Object aobj[] = new Object[4];
        aobj[0] = Double.valueOf(location.getLatitude());
        aobj[1] = Double.valueOf(location.getLongitude());
        aobj[2] = Double.valueOf(location1.getLatitude());
        aobj[3] = Double.valueOf(location1.getLongitude());
        return IntentUtils.openLink(String.format("http://maps.google.com/maps?saddr=%f,%f&daddr=%f,%f", aobj));
    }

    private void drawComment(Poi poi)
    {
        Comment comment = poi.getLastComment(getActivity());
        commentatorName.setText(comment.author_name);
        commentTime.setText(comment.created_at);
        commentText.setText(Html.fromHtml(comment.text));
        ((Picasso)picassoLazy.get()).load(comment.author_avatar).placeholder(0x7f0200e1).error(0x7f0200e1).into(commentatorPic);
        if(poi.hasMoreComments())
            readMoreComments.setVisibility(0);
    }

    private void getGallery(final Poi poi)
    {
        ((Roadtrippers)roadtrippers.get()).getGallery(poi.id).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).doOnError(new Action1() {

            public volatile void call(Object obj)
            {
                call((Throwable)obj);
            }

            public void call(Throwable throwable)
            {
                throwable.printStackTrace();
            }

            final DetailFragment this$0;

            
            {
                this$0 = DetailFragment.this;
                super();
            }
        }
).subscribe(new Subscriber() {

            public void onCompleted()
            {
            }

            public void onError(Throwable throwable)
            {
                throwable.printStackTrace();
            }

            public volatile void onNext(Object obj)
            {
                onNext((GalleryItem[])obj);
            }

            public void onNext(GalleryItem agalleryitem[])
            {
                Log.d("Gallery items loaded");
                GalleryItem galleryitem = new GalleryItem(poi.image.iphone_detail.replaceAll("h_[0-9]+", "h_2048").replaceAll("w_[0-9]+", "w_2048").replaceAll("c_fill", "c_fit"));
                items = new GalleryItem[1 + agalleryitem.length];
                items[0] = galleryitem;
                for(int i = 0; i < agalleryitem.length; i++)
                    items[i + 1] = agalleryitem[i];

                if(photoClicked)
                {
                    photoClicked = false;
                    displayGallery();
                }
            }

            final DetailFragment this$0;
            final Poi val$poi;

            
            {
                this$0 = DetailFragment.this;
                poi = poi1;
                super();
            }
        }
);
    }

    private Observable getHtmlPlaceObservable(final Poi poi)
    {
        return Observable.create(new rx.Observable.OnSubscribe() {

            public volatile void call(Object obj)
            {
                call((Subscriber)obj);
            }

            public void call(Subscriber subscriber)
            {
                subscriber.onNext((new HtmlPlace(poi)).computeHtml());
            }

            final DetailFragment this$0;
            final Poi val$poi;

            
            {
                this$0 = DetailFragment.this;
                poi = poi1;
                super();
            }
        }
);
    }

    private String getLocationUrlParameter(Poi poi)
    {
        if(poi.address1 == null)
            break MISSING_BLOCK_LABEL_90;
        String s = URLEncoder.encode((new StringBuilder()).append(poi.address1).append(" ").append(poi.city).append(", ").append(poi.state).append(" ").append(poi.zip_code).append(" ").append(poi.country).toString(), "UTF-8");
        return s;
        UnsupportedEncodingException unsupportedencodingexception;
        unsupportedencodingexception;
        unsupportedencodingexception.printStackTrace();
        return "";
    }

    static int measureHeight(TextView textview)
    {
        textview.measure(android.view.View.MeasureSpec.makeMeasureSpec(textview.getWidth(), 0x40000000), android.view.View.MeasureSpec.makeMeasureSpec(0, 0));
        return textview.getMeasuredHeight();
    }

    public static DetailFragment newInstance(String s)
    {
        DetailFragment detailfragment = new DetailFragment();
        Bundle bundle = new Bundle();
        bundle.putString("place", s);
        detailfragment.setArguments(bundle);
        return detailfragment;
    }

    public static void runAfterMeasure(View view, Runnable runnable, Callable callable)
    {
        view.getViewTreeObserver().addOnGlobalLayoutListener(new android.view.ViewTreeObserver.OnGlobalLayoutListener(callable, view, runnable) {

            public void onGlobalLayout()
            {
                try
                {
                    if(((Boolean)when.call()).booleanValue())
                    {
                        view.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                        what.run();
                    }
                    return;
                }
                catch(Exception exception)
                {
                    view.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                }
            }

            final View val$view;
            final Runnable val$what;
            final Callable val$when;

            
            {
                when = callable;
                view = view1;
                what = runnable;
                super();
            }
        }
);
    }

    static void setHeight(View view, int i)
    {
        android.view.ViewGroup.LayoutParams layoutparams = view.getLayoutParams();
        layoutparams.height = i;
        view.setLayoutParams(layoutparams);
    }

    private void updateParallax(int i)
    {
        float f = 0.5F * (float)(-i);
        ViewHelper.setTranslationY(detail, f);
        ViewHelper.setTranslationY(bottomFrame, f + (float)initialBottomTranslate);
    }

    public void addPhoto()
    {
        Log.d("Add Photo clicked");
        choosePhotoClick();
    }

    void addToTrip()
    {
        final Poi poi = getPoi();
        if(poi == null || poi.longitude == 0.0D && poi.latitude == 0.0D)
        {
            Crouton.cancelAllCroutons();
            Crouton.showText(getActivity(), "Place is not yet loaded", Style.ALERT, (ViewGroup)getContentView().getParent().getParent());
            return;
        }
        if(((TripManager)tripManagerLazy.get()).getCurrentTrip() != null && ((TripManager)tripManagerLazy.get()).getWaypoints().length < 40)
        {
            RTAnalytics.logEvent(getActivity(), 0x7f0c006d, 0x7f0c0026, "existing trip");
            ((TripManager)tripManagerLazy.get()).add(poi);
            getActivity().setResult(-1);
            getActivity().finish();
            return;
        }
        if(((TripManager)tripManagerLazy.get()).getCurrentTrip() != null && ((TripManager)tripManagerLazy.get()).getWaypoints().length >= 40)
        {
            (new android.app.AlertDialog.Builder(getActivity())).setTitle("Maximum Waypoints Reached").setMessage(0x7f0c00fa).setPositiveButton("Okay", null).show();
            return;
        } else
        {
            (new android.app.AlertDialog.Builder(getActivity())).setTitle("No trip selected!").setMessage("You don't have a trip selected. Would you like to start a new trip with this as the starting location?").setPositiveButton("Yes", new android.content.DialogInterface.OnClickListener() {

                public void onClick(DialogInterface dialoginterface, int i)
                {
                    RTAnalytics.logEvent(getActivity(), 0x7f0c006d, 0x7f0c0026, "new trip");
                    RTAnalytics.logEvent(getActivity(), 0x7f0c0071, 0x7f0c0049);
                    ((TripManager)tripManagerLazy.get()).createNewTripWith(Waypoint.from(poi));
                    getActivity().setResult(-1);
                    getActivity().finish();
                }

                final DetailFragment this$0;
                final Poi val$poi;

            
            {
                this$0 = DetailFragment.this;
                poi = poi1;
                super();
            }
            }
).setNegativeButton("Cancel", null).show();
            return;
        }
    }

    void back()
    {
        if(!popRateAndReview())
            getActivity().finish();
    }

    void chooseFromCamera()
    {
        startActivityForResult(IntentUtils.photoCapture(getPhotoFile().getAbsolutePath()), 6846);
    }

    void chooseFromGallery()
    {
        startActivityForResult(IntentUtils.pickImage(), 7897);
    }

    void choosePhotoClick()
    {
        if(IntentUtils.isIntentAvailable(getActivity(), new Intent("android.media.action.IMAGE_CAPTURE")))
        {
            (new android.app.AlertDialog.Builder(getActivity())).setTitle("Add Photo").setItems(getResources().getStringArray(0x7f060002), new android.content.DialogInterface.OnClickListener() {

                public void onClick(DialogInterface dialoginterface, int i)
                {
                    switch(i)
                    {
                    default:
                        return;

                    case 0: // '\0'
                        chooseFromCamera();
                        return;

                    case 1: // '\001'
                        chooseFromGallery();
                        break;
                    }
                }

                final DetailFragment this$0;

            
            {
                this$0 = DetailFragment.this;
                super();
            }
            }
).show();
            return;
        } else
        {
            chooseFromGallery();
            return;
        }
    }

    void comments()
    {
        RTAnalytics.logEvent(getActivity(), 0x7f0c006d, 0x7f0c002e);
        String s = ((Serializer)jackson.get()).serialize(getPoi());
        getFragmentManager().beginTransaction().addToBackStack(null).setCustomAnimations(0x7f04000c, 0, 0, 0x7f040011).add(0x7f0900da, RateAndReviewFragment.newInstance(s)).commitAllowingStateLoss();
    }

    public void displayGallery()
    {
        if(items != null && items.length > 0)
        {
            if(getActivity() != null)
            {
                Log.d("Gallery Should display");
                RTAnalytics.logEvent(getActivity(), 0x7f0c006d, 0x7f0c002f);
                getChildFragmentManager().beginTransaction().addToBackStack(null).setCustomAnimations(0x7f040006, 0, 0, 0x7f040007).add(0x7f0900da, GalleryFragment.newInstance(items), "gallery").commitAllowingStateLoss();
            }
            return;
        } else
        {
            photoClicked = true;
            Log.d("Gallery items null or empty");
            return;
        }
    }

    void draw(HtmlPlace htmlplace)
    {
        Poi poi = htmlplace.poi;
        drawTitle(poi);
        drawComment(poi);
        if(poi.image != null)
            ((Picasso)picassoLazy.get()).load(poi.image.iphone_detail).placeholder(0x7f0201ab).error(0x7f0201ab).into(detail);
        subTitle.setText(poi.subtitle);
        if(htmlplace.html != null)
        {
            description.setText(htmlplace.html);
            description.setMovementMethod(LinkMovementMethod.getInstance());
        }
        runAfterMeasure(description, new Runnable() {

            public void run()
            {
                int i = getResources().getDimensionPixelSize(0x7f0a002e);
                if(description.getHeight() > i)
                {
                    android.widget.FrameLayout.LayoutParams layoutparams = (android.widget.FrameLayout.LayoutParams)description.getLayoutParams();
                    layoutparams.height = i;
                    layoutparams.bottomMargin = getResources().getDimensionPixelSize(0x7f0a002d);
                    description.setLayoutParams(layoutparams);
                    InflaterAdapter.setVisibility(expandCollapse, 0);
                    InflaterAdapter.setVisibility(gradient, 0);
                }
            }

            final DetailFragment this$0;

            
            {
                this$0 = DetailFragment.this;
                super();
            }
        }
, new Callable() {

            public Boolean call()
                throws Exception
            {
                boolean flag;
                if(description.getHeight() > 0)
                    flag = true;
                else
                    flag = false;
                return Boolean.valueOf(flag);
            }

            public volatile Object call()
                throws Exception
            {
                return call();
            }

            final DetailFragment this$0;

            
            {
                this$0 = DetailFragment.this;
                super();
            }
        }
);
        TextView textview = website;
        String s;
        TextView textview1;
        String s1;
        String s2;
        if(poi.website != null)
            s = "visit website";
        else
            s = "not available";
        textview.setText(s);
        textview1 = call;
        if(Persistence.isHealthy(poi.phone))
        {
            Object aobj[] = new Object[1];
            aobj[0] = poi.phone;
            s1 = String.format("call %s", aobj);
        } else
        {
            s1 = "not available";
        }
        textview1.setText(s1);
        address1.setText(poi.address1);
        address2.setText(poi.getSecondLine());
        s2 = (new StringBuilder()).append("rad_o_meter_").append((int)Math.floor(8F * ((float)poi.display_rating / 100F))).toString();
        radometer.setImageResource(getResources().getIdentifier(s2, "drawable", getActivity().getPackageName()));
        if(getChildFragmentManager().findFragmentById(0x7f0900be) == null)
        {
            GoogleMapOptions googlemapoptions = (new GoogleMapOptions()).camera(CameraPosition.fromLatLngZoom(poi.getLatLng(), 16F));
            getChildFragmentManager().beginTransaction().add(0x7f0900be, DetailMapFragment.newInstance(((Serializer)jackson.get()).serialize(poi), googlemapoptions)).commit();
        }
        getActivity().getWindow().setBackgroundDrawable(null);
    }

    void drawTitle(Poi poi)
    {
        if(poi.name != null)
        {
            title.setText(poi.name.toUpperCase());
            title.setSelected(true);
        }
    }

    void expandCollapse()
    {
        RTAnalytics.logEvent(getActivity(), 0x7f0c006d, 0x7f0c0035);
        int i = description.getHeight();
        int j = getResources().getDimensionPixelSize(0x7f0a002e);
        int k;
        AnimatorSet animatorset;
        HeightEvaluator heightevaluator;
        Object aobj[];
        ValueAnimator valueanimator;
        Animator aanimator[];
        View view;
        float af[];
        float f;
        TextView textview;
        String s;
        if(i > j)
            k = j;
        else
            k = measureHeight(description);
        animatorset = new AnimatorSet();
        heightevaluator = new HeightEvaluator(description);
        aobj = new Object[2];
        aobj[0] = Integer.valueOf(i);
        aobj[1] = Integer.valueOf(k);
        valueanimator = ObjectAnimator.ofObject(heightevaluator, aobj);
        valueanimator.addUpdateListener(new com.nineoldandroids.animation.ValueAnimator.AnimatorUpdateListener() {

            public void onAnimationUpdate(ValueAnimator valueanimator1)
            {
                calculateInitialTranslate(addressPanel.getHeight(), detail.getHeight());
            }

            final DetailFragment this$0;

            
            {
                this$0 = DetailFragment.this;
                super();
            }
        }
);
        aanimator = new Animator[2];
        aanimator[0] = valueanimator;
        view = gradient;
        af = new float[1];
        if(i > j)
            f = 1.0F;
        else
            f = 0.0F;
        af[0] = f;
        aanimator[1] = ObjectAnimator.ofFloat(view, "alpha", af);
        animatorset.playTogether(aanimator);
        animatorset.start();
        textview = expandCollapse;
        if(i > j)
            s = "Read more";
        else
            s = "Read less";
        textview.setText(s);
    }

    boolean galleryUp()
    {
        return getChildFragmentManager().findFragmentByTag("gallery") != null;
    }

    File getPhotoFile()
    {
        return new File(DiskUtils.getFilesDir(getActivity()), "photo");
    }

    Poi getPoi()
    {
        return (Poi)((Serializer)jackson.get()).deserialize(getArguments().getString("place"), com/roadtrippers/api/models/Poi);
    }

    protected File getTempFile()
    {
        return new File(DiskUtils.getFilesDir(getActivity()), "temp");
    }

    void makeRequest(Poi poi)
    {
        Log.d("Make Request");
        if(poi != null)
        {
            setContentShown(false);
            ((Roadtrippers)roadtrippers.get()).getPoi(poi.id).map(new Func1() {

                public Poi call(Poi poi1)
                {
                    Object aobj[] = new Object[1];
                    aobj[0] = String.valueOf(poi1.getStatus());
                    Log.d("Status: %s", aobj);
                    setPoi(poi1);
                    return poi1;
                }

                public volatile Object call(Object obj)
                {
                    return call((Poi)obj);
                }

                final DetailFragment this$0;

            
            {
                this$0 = DetailFragment.this;
                super();
            }
            }
).flatMap(new Func1() {

                public volatile Object call(Object obj)
                {
                    return call((Poi)obj);
                }

                public Observable call(Poi poi1)
                {
                    return getHtmlPlaceObservable(poi1);
                }

                final DetailFragment this$0;

            
            {
                this$0 = DetailFragment.this;
                super();
            }
            }
).doOnError(new Action1() {

                public volatile void call(Object obj)
                {
                    call((Throwable)obj);
                }

                public void call(Throwable throwable)
                {
                    throwable.printStackTrace();
                    Crouton.cancelAllCroutons();
                    Croutons.showNetworkUnavailableError((ViewGroup)scrollView.getParent(), getActivity());
                }

                final DetailFragment this$0;

            
            {
                this$0 = DetailFragment.this;
                super();
            }
            }
).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(htmlPlaceObserver);
        }
    }

    public void onActivityResult(int i, int j, Intent intent)
    {
        super.onActivityResult(i, j, intent);
        if(j == -1)
        {
            Uri uri;
            switch(i)
            {
            default:
                return;

            case 6846: 
                submitPhoto(Uri.fromFile(getPhotoFile()));
                return;

            case 7897: 
                uri = intent.getData();
                break;
            }
            if(uri != null)
            {
                submitPhoto(uri);
                return;
            }
        }
    }

    public boolean onBackPressed()
    {
        if(bottomMarginView.getHeight() > detail.getHeight())
        {
            onBottomMarginClick();
            return true;
        } else
        {
            return popRateAndReview();
        }
    }

    void onBottomMarginClick()
    {
        RTAnalytics.logEvent(getActivity(), 0x7f0c006d, 0x7f0c0036);
        int i = getContentView().getHeight() - addressPanel.getHeight() - addressShadow.getHeight();
        if(i > bottomMarginView.getHeight())
        {
            setHeight(bottomMarginView, i);
            detail.offsetTopAndBottom((int)(0.5F * (float)(-(i - bottomMarginView.getHeight()))));
            scrollView.setOnTouchInterceptor(new com.roadtrippers.widget.NotifyingScrollView.OnTouchInterceptor() {

                public void onInterceptTouchEvent(MotionEvent motionevent)
                {
                    if(bottomFrame != null)
                        bottomFrame.dispatchTouchEvent(motionevent);
                }

                final DetailFragment this$0;

            
            {
                this$0 = DetailFragment.this;
                super();
            }
            }
);
            scrollView.post(new Runnable() {

                public void run()
                {
                    scrollView.fullScroll(130);
                }

                final DetailFragment this$0;

            
            {
                this$0 = DetailFragment.this;
                super();
            }
            }
);
            bottomMarginView.setOnClickListener(null);
            addressPanel.setOnClickListener(onMapClickListener);
            return;
        } else
        {
            bottomMarginView.startAnimation(new LayoutHeightAnimation(bottomMarginView, detail.getHeight()));
            scrollView.setOnTouchInterceptor(null);
            addressPanel.setOnClickListener(null);
            bottomMarginView.setOnClickListener(onMapClickListener);
            return;
        }
    }

    public void onBucketsChanged(BucketsChangedEvent bucketschangedevent)
    {
        Observable.from(bucketschangedevent.buckets).toList().map(new Func1() {

            public volatile Object call(Object obj)
            {
                return call((List)obj);
            }

            public Category[] call(List list)
            {
                return (Category[])list.toArray(new Category[list.size()]);
            }

            final DetailFragment this$0;

            
            {
                this$0 = DetailFragment.this;
                super();
            }
        }
).map(bucketsContainPlace).subscribeOn(Schedulers.computation()).observeOn(AndroidSchedulers.mainThread()).subscribe(bucketsContainPlaceObserver);
    }

    void onCallClick()
    {
        RTAnalytics.logEvent(getActivity(), 0x7f0c006d, 0x7f0c002b);
        try
        {
            Poi poi = getPoi();
            if(Persistence.isHealthy(poi.phone))
                startActivity(IntentUtils.dialPhone(poi.phone));
            return;
        }
        catch(Throwable throwable)
        {
            return;
        }
    }

    public void onConnected(Bundle bundle)
    {
        locationClient.requestLocationUpdates(request, this);
    }

    public void onConnectionFailed(ConnectionResult connectionresult)
    {
    }

    protected void onContentCreated(Bundle bundle)
    {
        Poi poi = getPoi();
        Object aobj[] = new Object[1];
        aobj[0] = poi.id;
        Log.d("DETAIL: id: %s", aobj);
        if(poi != null)
            if(poi.address1 != null)
            {
                Object aobj1[] = new Object[1];
                aobj1[0] = String.valueOf(poi.getStatus());
                Log.d("Status: %s", aobj1);
                getHtmlPlaceObservable(poi).subscribeOn(Schedulers.computation()).observeOn(AndroidSchedulers.mainThread()).subscribe(htmlPlaceObserver);
            } else
            {
                drawTitle(poi);
                makeRequest(poi);
            }
        runAfterMeasure(getView(), new Runnable() {

            public void run()
            {
                double d = (double)getContentView().getWidth() / 2.5600000000000001D;
                double d1 = (double)getContentView().getHeight() / 2D;
                int i;
                final int addressPanelHeight;
                if(d > d1)
                    i = (int)d1;
                else
                    i = (int)d;
                DetailFragment.setHeight(detail, i);
                DetailFragment.setHeight(topMarginView, i);
                DetailFragment.setHeight(bottomMarginView, i);
                addressPanelHeight = addressPanel.getHeight();
                DetailFragment.setHeight(bottomFrame, getContentView().getHeight() - addressPanelHeight);
                DetailFragment.runAfterMeasure(mainChild, i. new Runnable() {

                    public void run()
                    {
                        calculateInitialTranslate(addressPanelHeight, imageHeight);
                        ViewHelper.setTranslationY(bottomFrame, initialBottomTranslate);
                    }

                    final _cls2 this$1;
                    final int val$addressPanelHeight;
                    final int val$imageHeight;

            
            {
                this$1 = final__pcls2;
                addressPanelHeight = i;
                imageHeight = I.this;
                super();
            }
                }
, new Callable() {

                    public Boolean call()
                        throws Exception
                    {
                        boolean flag;
                        if(bottomFrame.getHeight() > 0 && mainChild.getHeight() > 0 && addressShadow.getHeight() > 0)
                            flag = true;
                        else
                            flag = false;
                        return Boolean.valueOf(flag);
                    }

                    public volatile Object call()
                        throws Exception
                    {
                        return call();
                    }

                    final _cls2 this$1;

            
            {
                this$1 = _cls2.this;
                super();
            }
                }
);
            }

            final DetailFragment this$0;

            
            {
                this$0 = DetailFragment.this;
                super();
            }
        }
, new Callable() {

            public Boolean call()
                throws Exception
            {
                boolean flag;
                if(getContentView().getHeight() > 0 && addressPanel.getHeight() > 0)
                    flag = true;
                else
                    flag = false;
                return Boolean.valueOf(flag);
            }

            public volatile Object call()
                throws Exception
            {
                return call();
            }

            final DetailFragment this$0;

            
            {
                this$0 = DetailFragment.this;
                super();
            }
        }
);
        scrollView.setOnScrollChangedListener(new com.roadtrippers.widget.NotifyingScrollView.OnScrollChangedListener() {

            public void onScrollChanged(ScrollView scrollview, int i, int j, int k, int l)
            {
                updateParallax(j);
            }

            final DetailFragment this$0;

            
            {
                this$0 = DetailFragment.this;
                super();
            }
        }
);
        scrollView.setOnTouchListener(new android.view.View.OnTouchListener() {

            public boolean onTouch(View view, MotionEvent motionevent)
            {
                if(motionevent.getY() < (float)(topMarginView.getHeight() - scrollView.getScrollY()) && motionevent.getAction() == 1)
                {
                    displayGallery();
                    return true;
                } else
                {
                    return false;
                }
            }

            final DetailFragment this$0;

            
            {
                this$0 = DetailFragment.this;
                super();
            }
        }
);
        setColorFilter(backButton, 0xffcccccc);
        setColorFilter(shareButton, 0xffcccccc);
        setColorFilter(leaveComment, 0xffcccccc);
        bottomMarginView.setOnClickListener(onMapClickListener);
        getGallery(poi);
    }

    public void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        locationClient = new LocationClient(getActivity(), this, this);
        request = LocationRequest.create();
        request.setFastestInterval(1000L);
        request.setPriority(100);
        request.setInterval(2000L);
    }

    protected void onCreateContent(Bundle bundle)
    {
        setContentView(0x7f030037);
    }

    public View onCreateView(LayoutInflater layoutinflater, ViewGroup viewgroup, Bundle bundle)
    {
        RTAnalytics.logScreenView(getActivity(), "Place Page");
        return layoutinflater.inflate(0x7f03003a, viewgroup, false);
    }

    public void onDisconnected()
    {
    }

    public void onEvent(CommentAdded commentadded)
    {
        RTAnalytics.logEvent(getActivity(), 0x7f0c006d, 0x7f0c002d);
        Poi poi = getPoi();
        poi.comments = Comment.add(poi.comments, commentadded.comment);
        drawComment(poi);
        setPoi(poi);
    }

    void onGetDirectionsClick()
    {
label0:
        {
label1:
            {
                SupportMapFragment supportmapfragment = (SupportMapFragment)getChildFragmentManager().findFragmentById(0x7f0900be);
                if(supportmapfragment != null && supportmapfragment.getMap() != null)
                {
                    Location location = locationClient.getLastLocation();
                    Object aobj[] = new Object[2];
                    aobj[0] = String.valueOf(location);
                    aobj[1] = String.valueOf(lastKnownLocation);
                    Log.d("Directions: Location: %s  LastKnownLocation: %s", aobj);
                    if(location == null && lastKnownLocation == null)
                        break label0;
                    Poi poi = getPoi();
                    if(poi.getLocation() == null)
                        break label1;
                    EasyJSONObject easyjsonobject = new EasyJSONObject();
                    easyjsonobject.put("place_type", "poi");
                    easyjsonobject.put("launched_from", "place_details");
                    RTAnalytics.logEvent(getActivity(), 0x7f0c006c, 0x7f0c0023, easyjsonobject.toString());
                    if(location == null)
                        location = lastKnownLocation;
                    startActivity(createDirectionsIntent(location, poi.getLocation()));
                }
                return;
            }
            Crouton.cancelAllCroutons();
            Crouton.showText(getActivity(), "Place information unavailable", Style.ALERT, (ViewGroup)getContentView().getParent().getParent());
            return;
        }
        Crouton.cancelAllCroutons();
        Croutons.showLocationUnavailableError((ViewGroup)scrollView.getParent(), getActivity());
    }

    public void onLocationChanged(Location location)
    {
        lastKnownLocation = location;
    }

    public void onLocationChanged(LocationChangedEvent locationchangedevent)
    {
        Object aobj[] = new Object[1];
        aobj[0] = String.valueOf(locationchangedevent.location);
        Log.d("Details Location changed to %s", aobj);
        lastKnownLocation = locationchangedevent.location;
    }

    public void onPhotoAdded(PhotoAddedEvent photoaddedevent)
    {
        getGallery(getPoi());
        RTAnalytics.logEvent(getActivity(), 0x7f0c006d, 0x7f0c0030);
        getActivity().runOnUiThread(new Runnable() {

            public void run()
            {
                Crouton.cancelAllCroutons();
                Crouton crouton = Crouton.makeText(getActivity(), "Image added successfully!", Style.CONFIRM, (ViewGroup)frameRateReview);
                crouton.setConfiguration((new de.keyboardsurfer.android.widget.crouton.Configuration.Builder()).setDuration(5000).build());
                crouton.show();
            }

            final DetailFragment this$0;

            
            {
                this$0 = DetailFragment.this;
                super();
            }
        }
);
    }

    protected void onRetryButtonClick()
    {
        makeRequest(getPoi());
    }

    void onSavePlaceClick()
    {
        RTAnalytics.logEvent(getActivity(), 0x7f0c006d, 0x7f0c002a);
        ((Bus)bus.get()).post(new SavePlaceEvent(getPoi()));
    }

    void onShareClick()
    {
        if(!galleryUp())
        {
            (new android.app.AlertDialog.Builder(getActivity())).setItems(new String[] {
                "Open in browser", "Share"
            }, new android.content.DialogInterface.OnClickListener() {

                public void onClick(DialogInterface dialoginterface, int i)
                {
                    Poi poi1 = getPoi();
                    Intent intent;
                    switch(i)
                    {
                    default:
                        return;

                    case 0: // '\0'
                        RTAnalytics.logEvent(getActivity(), 0x7f0c006d, 0x7f0c0033);
                        startActivity(IntentUtils.openLink(poi1.link));
                        return;

                    case 1: // '\001'
                        intent = IntentUtils.shareText(poi1.name, poi1.link);
                        break;
                    }
                    RTAnalytics.logEvent(getActivity(), 0x7f0c006d, 0x7f0c0034);
                    startActivity(Intent.createChooser(intent, getString(0x7f0c00e6)));
                }

                final DetailFragment this$0;

            
            {
                this$0 = DetailFragment.this;
                super();
            }
            }
).show();
            return;
        } else
        {
            final Poi poi = getPoi();
            shareImageObservable = new rx.Observable.OnSubscribe() {

                public volatile void call(Object obj)
                {
                    call((Subscriber)obj);
                }

                public void call(Subscriber subscriber)
                {
                    subscriber.onNext(image.getContentUrl());
                    subscriber.onCompleted();
                }

                final DetailFragment this$0;
                final com.roadtrippers.api.models.GalleryItem.GalleryImage val$image;

            
            {
                this$0 = DetailFragment.this;
                image = galleryimage;
                super();
            }
            }
;
            shareImageSubscriber = new Subscriber() {

                public void onCompleted()
                {
                    Intent intent = new Intent();
                    intent.setAction("android.intent.action.SEND");
                    intent.setType("image/jpeg");
                    intent.putExtra("android.intent.extra.STREAM", Uri.fromFile(new File(sharePath)));
                    intent.putExtra("android.intent.extra.SUBJECT", poi.name);
                    RTAnalytics.logEvent(getActivity(), 0x7f0c006d, 0x7f0c0034);
                    startActivity(Intent.createChooser(intent, getString(0x7f0c00e6)));
                }

                public void onError(Throwable throwable)
                {
                }

                public volatile void onNext(Object obj)
                {
                    onNext((String)obj);
                }

                public void onNext(String s)
                {
                    Log.d("Sharing image %s", new Object[] {
                        s
                    });
                    URL url = new URL(s);
                    InputStream inputstream = url.openStream();
                    FileOutputStream fileoutputstream;
                    File file = Environment.getExternalStorageDirectory();
                    sharePath = (new StringBuilder()).append(file).append("/sharedimage.png").toString();
                    Object aobj[] = new Object[1];
                    aobj[0] = sharePath;
                    Log.d("SharePath %s", aobj);
                    fileoutputstream = new FileOutputStream(sharePath);
                    byte abyte0[] = new byte[2048];
_L3:
                    int i = inputstream.read(abyte0, 0, abyte0.length);
                    if(i < 0) goto _L2; else goto _L1
_L1:
                    fileoutputstream.write(abyte0, 0, i);
                      goto _L3
                    Exception exception4;
                    exception4;
                    exception4.printStackTrace();
                    fileoutputstream.close();
_L14:
                    inputstream.close();
_L4:
                    return;
_L2:
                    fileoutputstream.close();
                    continue; /* Loop/switch isn't completed */
                    Exception exception2;
                    exception2;
                    exception2.printStackTrace();
                    inputstream.close();
                      goto _L4
                    MalformedURLException malformedurlexception;
                    malformedurlexception;
_L12:
                    malformedurlexception.printStackTrace();
                    return;
                    Exception exception3;
                    exception3;
                    fileoutputstream.close();
                    throw exception3;
                    Exception exception1;
                    exception1;
                    inputstream.close();
                    throw exception1;
                    FileNotFoundException filenotfoundexception;
                    filenotfoundexception;
_L10:
                    filenotfoundexception.printStackTrace();
                    return;
                    IOException ioexception;
                    ioexception;
_L8:
                    ioexception.printStackTrace();
                    return;
                    Exception exception;
                    exception;
_L6:
                    exception.printStackTrace();
                    return;
                    exception;
                    if(true) goto _L6; else goto _L5
_L5:
                    ioexception;
                    if(true) goto _L8; else goto _L7
_L7:
                    filenotfoundexception;
                    if(true) goto _L10; else goto _L9
_L9:
                    malformedurlexception;
                    if(true) goto _L12; else goto _L11
_L11:
                    if(true) goto _L14; else goto _L13
_L13:
                }

                final DetailFragment this$0;
                final Poi val$poi;

            
            {
                this$0 = DetailFragment.this;
                poi = poi1;
                super();
            }
            }
;
            Observable.create(shareImageObservable).subscribeOn(Schedulers.io()).subscribe(shareImageSubscriber);
            return;
        }
    }

    public void onStart()
    {
        super.onStart();
        locationClient.connect();
    }

    public void onStop()
    {
        if(locationClient.isConnected())
            locationClient.removeLocationUpdates(this);
        locationClient.disconnect();
        super.onStop();
    }

    void onWebsiteClick()
    {
        RTAnalytics.logEvent(getActivity(), 0x7f0c006d, 0x7f0c0037);
        if(getPoi() == null || TextUtils.isEmpty(getPoi().website))
            break MISSING_BLOCK_LABEL_47;
        startActivity(IntentUtils.openLink(getPoi().website));
        return;
        Throwable throwable;
        throwable;
    }

    boolean popRateAndReview()
    {
        if(getActivity().getCurrentFocus() != null)
            ((InputMethodManager)getActivity().getSystemService("input_method")).hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), 0);
        if(getFragmentManager().getBackStackEntryCount() > 0)
        {
            getFragmentManager().popBackStack();
            return true;
        }
        if(getChildFragmentManager().getBackStackEntryCount() > 0)
        {
            getChildFragmentManager().popBackStack();
            return true;
        } else
        {
            return false;
        }
    }

    Poi setPoi(Poi poi)
    {
        getArguments().putString("place", ((Serializer)jackson.get()).serialize(poi));
        return poi;
    }

    void submitPhoto(Uri uri)
    {
        String s = ((Serializer)jackson.get()).serialize(getPoi());
        getFragmentManager().beginTransaction().addToBackStack(null).setCustomAnimations(0x7f04000c, 0, 0, 0x7f040011).add(0x7f0900da, AddImageFragment.newInstance(uri.toString(), s)).commitAllowingStateLoss();
    }

    static final int CAMERA_REQUEST = 6846;
    static final int GALLERY_REQUEST = 7897;
    static final float PARALLAX_DAMPING = 0.5F;
    private static final String PHOTO_JPG = "photo";
    TextView address1;
    TextView address2;
    LinearLayout addressPanel;
    View addressShadow;
    ImageView backButton;
    FrameLayout bottomFrame;
    View bottomMarginView;
    final Func1 bucketsContainPlace = new Func1() {

        public Boolean call(Category acategory[])
        {
            Poi poi = getPoi();
            int i = acategory.length;
            for(int j = 0; j < i; j++)
            {
                Poi apoi[] = acategory[j].pois;
                int k = apoi.length;
                for(int l = 0; l < k; l++)
                    if(poi.equals(apoi[l]))
                        return Boolean.valueOf(true);

            }

            return Boolean.valueOf(false);
        }

        public volatile Object call(Object obj)
        {
            return call((Category[])obj);
        }

        final DetailFragment this$0;

            
            {
                this$0 = DetailFragment.this;
                super();
            }
    }
;
    final Observer bucketsContainPlaceObserver = new Observer() {

        public void onCompleted()
        {
        }

        public void onError(Throwable throwable)
        {
            if(getView() != null)
                savePlace.setImageResource(0x7f020176);
        }

        public void onNext(Boolean boolean1)
        {
            if(boolean1.booleanValue())
            {
                savePlace.setImageResource(0x7f020175);
                return;
            } else
            {
                savePlace.setImageResource(0x7f020176);
                return;
            }
        }

        public volatile void onNext(Object obj)
        {
            onNext((Boolean)obj);
        }

        final DetailFragment this$0;

            
            {
                this$0 = DetailFragment.this;
                super();
            }
    }
;
    Lazy bus;
    TextView call;
    TextView commentText;
    TextView commentTime;
    TextView commentatorName;
    ImageView commentatorPic;
    TextView description;
    ImageView detail;
    TextView expandCollapse;
    View frameRateReview;
    View gradient;
    final Observer htmlPlaceObserver = new Observer() {

        public void onCompleted()
        {
        }

        public void onError(Throwable throwable)
        {
            Log.e(throwable);
            if(getView() != null)
            {
                setContentEmpty(true);
                setContentShown(true);
            }
        }

        public void onNext(HtmlPlace htmlplace)
        {
            draw(htmlplace);
            setContentEmpty(false);
            setContentShown(true);
        }

        public volatile void onNext(Object obj)
        {
            onNext((HtmlPlace)obj);
        }

        final DetailFragment this$0;

            
            {
                this$0 = DetailFragment.this;
                super();
            }
    }
;
    byte image[];
    String imagePath;
    int initialBottomTranslate;
    private GalleryItem items[];
    Lazy jackson;
    Location lastKnownLocation;
    ImageView leaveComment;
    LocationClient locationClient;
    RelativeLayout mainChild;
    final android.view.View.OnClickListener onMapClickListener = new android.view.View.OnClickListener() {

        public void onClick(View view)
        {
            onBottomMarginClick();
        }

        final DetailFragment this$0;

            
            {
                this$0 = DetailFragment.this;
                super();
            }
    }
;
    Lazy persistence;
    private boolean photoClicked;
    Lazy picassoLazy;
    ImageView radometer;
    TextView readMoreComments;
    private LocationRequest request;
    Lazy roadtrippers;
    ImageView savePlace;
    NotifyingScrollView scrollView;
    ImageView shareButton;
    private rx.Observable.OnSubscribe shareImageObservable;
    private Subscriber shareImageSubscriber;
    private String sharePath;
    TextView subTitle;
    private Target target;
    TextView title;
    View topMarginView;
    Lazy tripManagerLazy;
    TextView website;






/*
    static GalleryItem[] access$302(DetailFragment detailfragment, GalleryItem agalleryitem[])
    {
        detailfragment.items = agalleryitem;
        return agalleryitem;
    }

*/



/*
    static boolean access$402(DetailFragment detailfragment, boolean flag)
    {
        detailfragment.photoClicked = flag;
        return flag;
    }

*/



/*
    static String access$502(DetailFragment detailfragment, String s)
    {
        detailfragment.sharePath = s;
        return s;
    }

*/
}
