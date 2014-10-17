// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.roadtrippers.activities;

import android.app.Application;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.*;
import android.view.*;
import android.view.inputmethod.InputMethodManager;
import android.widget.*;
import com.google.android.gms.maps.GoogleMapOptions;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.nineoldandroids.animation.Animator;
import com.nineoldandroids.animation.AnimatorListenerAdapter;
import com.nineoldandroids.view.ViewPropertyAnimator;
import com.roadtrippers.RoadTrippersApp;
import com.roadtrippers.activities.base.BaseActivity;
import com.roadtrippers.adapters.base.InflaterAdapter;
import com.roadtrippers.api.Roadtrippers;
import com.roadtrippers.api.models.*;
import com.roadtrippers.controllers.TripsMenuController;
import com.roadtrippers.events.*;
import com.roadtrippers.fragments.*;
import com.roadtrippers.util.*;
import com.slidinglayer.SlidingLayer;
import com.squareup.otto.Bus;
import com.squareup.picasso.*;
import dagger.Lazy;
import de.keyboardsurfer.android.widget.crouton.Crouton;
import de.keyboardsurfer.android.widget.crouton.Style;
import io.segment.android.Analytics;
import io.segment.android.models.Props;
import io.segment.android.models.Traits;
import java.util.*;
import rx.*;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

// Referenced classes of package com.roadtrippers.activities:
//            LoginActivity

public class MainActivity extends BaseActivity
{

    public MainActivity()
    {
        identified = false;
        fragmentsForController = new ArrayList();
    }

    private boolean hasPlayStoreInstalled()
    {
        Iterator iterator = getApplication().getPackageManager().getInstalledPackages(0).iterator();
        boolean flag1;
        do
        {
            boolean flag = iterator.hasNext();
            flag1 = false;
            if(!flag)
                break;
            PackageInfo packageinfo = (PackageInfo)iterator.next();
            if(!packageinfo.packageName.equals("com.google.market") && !packageinfo.packageName.equals("com.android.vending"))
                continue;
            Object aobj[] = new Object[1];
            aobj[0] = packageinfo.packageName;
            Log.d("Package %s is installed.", aobj);
            flag1 = true;
            break;
        } while(true);
        return flag1;
    }

    private void identifyToAnalytics(User user)
    {
        if(user == null || user.username == null || user.email == null)
            return;
        String s = "";
        Traits traits;
        try
        {
            s = getPackageManager().getPackageInfo(getPackageName(), 0).versionName;
        }
        catch(android.content.pm.PackageManager.NameNotFoundException namenotfoundexception)
        {
            namenotfoundexception.printStackTrace();
        }
        traits = new Traits();
        traits.put("name", user.username);
        traits.put("email", user.email);
        traits.put("appversion", s);
        if(Analytics.getUserId() == null || !Analytics.getUserId().equals(String.valueOf(user.id)))
            Analytics.identify(String.valueOf(user.id), traits);
        identified = true;
    }

    private void logEvent(int i, int j)
    {
        RTAnalytics.logEvent(this, i, j);
    }

    public static void removeUserPanel(FragmentActivity fragmentactivity, Fragment fragment)
    {
        if(fragment.getView() != null)
            ViewPropertyAnimator.animate(fragment.getView()).translationY(-fragment.getView().getHeight()).setListener(new AnimatorListenerAdapter(fragmentactivity, fragment) {

                public void onAnimationEnd(Animator animator)
                {
                    fragmentActivity.getSupportFragmentManager().beginTransaction().remove(userPanel).commitAllowingStateLoss();
                }

                final FragmentActivity val$fragmentActivity;
                final Fragment val$userPanel;

            
            {
                fragmentActivity = fragmentactivity;
                userPanel = fragment;
                super();
            }
            }
);
    }

    FragmentTransaction beginFastFragmentTransaction()
    {
        return getSupportFragmentManager().beginTransaction().setCustomAnimations(0x7f040006, 0x7f040007, 0x7f040006, 0x7f040007);
    }

    public void dismissKeyboard()
    {
        if(getCurrentFocus() != null)
            ((InputMethodManager)getSystemService("input_method")).hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
    }

    void dismissSearch()
    {
        if(searchFragment != null && searchFragment.isVisible())
        {
            getSupportFragmentManager().popBackStack();
            dismissKeyboard();
            RTAnalytics.logEvent(this, 0x7f0c006e, 0x7f0c003a);
        }
    }

    float getSwitchTranslation()
    {
        int i = mapListSwitch.getWidth();
        float f = getResources().getDimension(0x7f0a0053);
        return (float)i - 2.0F * f - (float)selectedImage.getWidth();
    }

    Observable getUserFromServer()
    {
        return ((Roadtrippers)roadtrippers.get()).currentUser().map(new Func1() {

            public User call(User user)
            {
                ((LruCache)lruCacheLazy.get()).clear();
                ((Persistence)persistence.get()).saveUser(user);
                return user;
            }

            public volatile Object call(Object obj)
            {
                return call((User)obj);
            }

            final MainActivity this$0;

            
            {
                this$0 = MainActivity.this;
                super();
            }
        }
);
    }

    boolean hideUserPanel()
    {
        Fragment fragment = getSupportFragmentManager().findFragmentById(0x7f090103);
        if(fragment != null)
        {
            removeUserPanel(this, fragment);
            return true;
        } else
        {
            return false;
        }
    }

    void moveSwitch(boolean flag, int i)
    {
        if(selectedImage != null)
        {
            ViewPropertyAnimator viewpropertyanimator = ViewPropertyAnimator.animate(selectedImage);
            float f;
            if(flag)
                f = getSwitchTranslation();
            else
                f = 0.0F;
            viewpropertyanimator.translationX(f).setDuration(i).start();
        }
    }

    protected void onActivityResult(int i, int j, Intent intent)
    {
        super.onActivityResult(i, j, intent);
        if(j == -1) goto _L2; else goto _L1
_L1:
        return;
_L2:
        switch(i)
        {
        default:
            return;

        case 1006: 
            tripsMenuController.setTrip();
            if(((TripManager)tripManagerLazy.get()).getWaypoints().length == 1)
                tripsMenu.post(new Runnable() {

                    public void run()
                    {
                        tripsMenu.openLayer(true);
                    }

                    final MainActivity this$0;

            
            {
                this$0 = MainActivity.this;
                super();
            }
                }
);
            if(tripsMenuController.currentTripFragment != null)
                tripsMenuController.currentTripFragment.onPoiAdded();
            if(mapFragment != null)
            {
                mapFragment.beforeTripLoading();
                return;
            }
            break;

        case 1007: 
            leftLayer.closeLayer(true);
            (new android.app.AlertDialog.Builder(this)).setTitle("Thanks!").setMessage("Successfully added to our database!").setPositiveButton(0x7f0c00c8, null).show();
            continue; /* Loop/switch isn't completed */
        }
        if(true) goto _L1; else goto _L3
_L3:
        if(intent == null || !intent.hasExtra("CREATED_POI")) goto _L1; else goto _L4
_L4:
        zoomAndSelectPoi((Poi)((Serializer)jacksonSerializerLazy.get()).deserialize(intent.getStringExtra("CREATED_POI"), com/roadtrippers/api/models/Poi));
        return;
    }

    public void onAttachFragment(Fragment fragment)
    {
        super.onAttachFragment(fragment);
        if(fragment instanceof RTMapFragment)
        {
            mapFragment = (RTMapFragment)fragment;
            return;
        }
        if(fragment instanceof CategoriesFragment)
        {
            placesFragment = (CategoriesFragment)fragment;
            return;
        }
        if(fragment instanceof TripsPlacesListFragment)
        {
            listFragment = (TripsPlacesListFragment)fragment;
            return;
        }
        if(tripsMenuController != null)
        {
            tripsMenuController.attachFragment(fragment);
            return;
        } else
        {
            fragmentsForController.add(fragment);
            return;
        }
    }

    public void onBackPressed()
    {
        if(leftLayer.isOpened())
            leftLayer.closeLayer(true);
        else
        if(!tripsMenuController.onBackPressed())
        {
            if(tripsMenu.isOpened())
            {
                tripsMenu.closeLayer(true);
                return;
            }
            if(!hideUserPanel())
            {
                if(searchFragment != null && searchFragment.isVisible())
                {
                    dismissSearch();
                    return;
                }
                if(isTaskRoot())
                {
                    startActivity((new Intent("android.intent.action.MAIN")).addCategory("android.intent.category.HOME"));
                    return;
                } else
                {
                    super.onBackPressed();
                    return;
                }
            }
        }
    }

    public void onBucketsChange(BucketsChangedEvent bucketschangedevent)
    {
        final ArrayList savedPois = new ArrayList();
        ArrayList arraylist = new ArrayList();
        mapFragment.showProgress();
        Category acategory[] = bucketschangedevent.buckets;
        int i = acategory.length;
        for(int j = 0; j < i; j++)
        {
            Category category = acategory[j];
            if(category.checked)
                arraylist.add(((Roadtrippers)roadtrippers.get()).getBucketDetails(((Persistence)persistence.get()).getUserId(), Integer.parseInt(category.value)));
        }

        savedPlaceObservable = Observable.merge(arraylist).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer() {

            public void onCompleted()
            {
                ArrayList arraylist1 = new ArrayList();
                for(int k = 0; k < savedPois.size(); k++)
                    arraylist1.add(((Roadtrippers)roadtrippers.get()).getPoi(((Poi)savedPois.get(k)).id));

                savedPois.clear();
                Observable.merge(arraylist1).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer() {

                    public void onCompleted()
                    {
                        Poi apoi[] = (Poi[])savedPois.toArray(new Poi[savedPois.size()]);
                        listFragment.setSavedPois(apoi);
                        mapFragment.setSavedPlaces(apoi);
                    }

                    public void onError(Throwable throwable)
                    {
                        mapFragment.hideProgress();
                    }

                    public void onNext(Poi poi)
                    {
                        savedPois.add(poi);
                    }

                    public volatile void onNext(Object obj)
                    {
                        onNext((Poi)obj);
                    }

                    final _cls8 this$1;

            
            {
                this$1 = _cls8.this;
                super();
            }
                }
);
            }

            public void onError(Throwable throwable)
            {
                mapFragment.hideProgress();
                Croutons.showNetworkUnavailableError((ViewGroup)mapFragment.getView(), MainActivity.this);
            }

            public void onNext(Bucket bucket)
            {
                savedPois.addAll(bucket.getEntriesAsPois());
            }

            public volatile void onNext(Object obj)
            {
                onNext((Bucket)obj);
            }

            final MainActivity this$0;
            final List val$savedPois;

            
            {
                this$0 = MainActivity.this;
                savedPois = list;
                super();
            }
        }
);
    }

    public void onCameraChange(CameraPosition cameraposition)
    {
        if(placesFragment.getListAdapter() == null)
        {
            return;
        } else
        {
            search(placesFragment.getGroups());
            return;
        }
    }

    public void onClearPlacesClick(ClearPlacesClick clearplacesclick)
    {
        placesFragment.clear();
        listFragment.clearPlaces();
    }

    protected void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        setContentView(0x7f03004e);
        if(RoadTrippersApp.from(this).isStage())
            findViewById(0x7f090104).setVisibility(0);
        if(((Persistence)persistence.get()).getAuthToken() == null)
        {
            startActivity(new Intent(this, com/roadtrippers/activities/LoginActivity));
            finish();
            return;
        }
        tripsMenuController = new TripsMenuController(this);
        Fragment fragment;
        for(Iterator iterator = fragmentsForController.iterator(); iterator.hasNext(); tripsMenuController.attachFragment(fragment))
            fragment = (Fragment)iterator.next();

        fragmentsForController.clear();
        if(bundle == null)
        {
            RTAnalytics.logScreenView(this, "Map");
            TripsPlacesListFragment tripsplaceslistfragment = new TripsPlacesListFragment();
            getSupportFragmentManager().beginTransaction().add(0x7f090106, new CategoriesFragment()).add(0x7f090102, tripsplaceslistfragment).add(0x7f090102, RTMapFragment.newInstance((new GoogleMapOptions()).camera((new com.google.android.gms.maps.model.CameraPosition.Builder()).target(new LatLng(42.949629792469707D, -98.784259669482708D)).build()))).hide(tripsplaceslistfragment).commit();
        }
        if(!((Persistence)persistence.get()).wasIntroShown() && !introshown && getSupportFragmentManager().findFragmentByTag("intro") == null)
        {
            getSupportFragmentManager().beginTransaction().add(0x1020002, new IntroFragment(), "intro").commit();
            ((Persistence)persistence.get()).setIntroWasShown(true);
            introshown = true;
        }
        dismissKeyboard();
    }

    protected void onDestroy()
    {
        if(savedPlaceObservable != null)
            savedPlaceObservable.unsubscribe();
        super.onDestroy();
    }

    public void onEvent(LocationUnavailableEvent locationunavailableevent)
    {
        Crouton.cancelAllCroutons();
        Croutons.showLocationUnavailableError(mainWindow, this);
    }

    public void onEvent(NewTripCreated newtripcreated)
    {
        logEvent(0x7f0c0071, 0x7f0c0044);
        tripsMenuController.setTrip();
    }

    public void onEvent(TripUpdatedEvent tripupdatedevent)
    {
        if(tripupdatedevent.throwable != null)
            tripsMenu.openLayer(true);
    }

    public void onEvent(UpdatingTrip updatingtrip)
    {
        tripsMenu.closeLayer(true);
    }

    public void onGroupsChange(GroupCheck groupcheck)
    {
        search(groupcheck.getGroups());
    }

    public void onIntroComplete(IntroComplete introcomplete)
    {
        introshown = true;
        getSupportFragmentManager().beginTransaction().remove(getSupportFragmentManager().findFragmentByTag("intro")).commit();
    }

    public void onPause()
    {
        super.onPause();
        tripsMenuController.onPause();
        dismissKeyboard();
    }

    public void onPlacesClick()
    {
        hideUserPanel();
        dismissSearch();
        leftLayer.openLayer(true);
    }

    public void onPlacesHighlighted(PlacesHighlighted placeshighlighted)
    {
        if(placeshighlighted == PlacesHighlighted.ENABLED)
        {
            placesButton.setBackgroundResource(0x7f020162);
            return;
        } else
        {
            placesButton.setBackgroundResource(0x7f020160);
            return;
        }
    }

    protected void onRestoreInstanceState(Bundle bundle)
    {
        super.onRestoreInstanceState(bundle);
        if(bundle != null)
            restoreState(bundle);
    }

    public void onResume()
    {
        reloadUserInfo();
        tripsMenuController.onResume();
        if(mapFragment != null && mapFragment.getMap() != null)
        {
            getWindow().setBackgroundDrawable(null);
            Log.d("setting bg to null since we have a map");
        }
        super.onResume();
    }

    protected void onSaveInstanceState(Bundle bundle)
    {
        super.onSaveInstanceState(bundle);
        bundle.putBoolean("left menu", leftLayer.isOpened());
        bundle.putBoolean("right menu", tripsMenu.isOpened());
        bundle.putBoolean("LIST", mapFragment.isHidden());
    }

    void onSearchClick()
    {
        hideUserPanel();
        searchFragment = new SearchFragment();
        beginFastFragmentTransaction().replace(0x7f09010a, searchFragment).addToBackStack(null).commitAllowingStateLoss();
        RTAnalytics.logEvent(this, 0x7f0c006e, 0x7f0c003d);
    }

    public void onSwitchClick()
    {
        if(!hideUserPanel())
        {
            FragmentTransaction fragmenttransaction = beginFastFragmentTransaction();
            boolean flag;
            if(!mapFragment.isHidden())
            {
                RTAnalytics.logScreenView(this, "List View");
                RTAnalytics.logEvent(this, 0x7f0c0068, 0x7f0c001a);
                fragmenttransaction.hide(mapFragment).show(listFragment);
            } else
            {
                RTAnalytics.logScreenView(this, "Map");
                RTAnalytics.logEvent(this, 0x7f0c0068, 0x7f0c0018);
                RTAnalytics.logEvent(this, 0x7f0c006a, 0x7f0c0020);
                fragmenttransaction.hide(listFragment).show(mapFragment);
            }
            if(!mapFragment.isHidden())
                flag = true;
            else
                flag = false;
            moveSwitch(flag, 200);
            fragmenttransaction.commitAllowingStateLoss();
        }
    }

    public void onTicketSubmitted(TicketSubmittedEvent ticketsubmittedevent)
    {
        Crouton.cancelAllCroutons();
        if(ticketsubmittedevent.isOk())
        {
            Crouton.showText(this, 0x7f0c00ef, Style.CONFIRM, mainWindow);
            return;
        } else
        {
            Crouton.showText(this, 0x7f0c00ee, Style.ALERT, mainWindow);
            return;
        }
    }

    public void onTripClick(TripClick tripclick)
    {
        if(tripclick != null)
        {
            Props props = new Props();
            props.put("label", tripclick.getTrip().id);
            RTAnalytics.logEvent(this, 0x7f0c0071, 0x7f0c0048, props);
            tripsMenu.closeLayer(true);
            mapFragment.onTripClick(tripclick.getTrip());
            listFragment.setContentShown(false);
        }
    }

    public void onTripLoaded(TripLoadedEvent triploadedevent)
    {
        tripLoadedEvent = triploadedevent;
        if(triploadedevent.trip != null)
        {
            InflaterAdapter.setVisibility(logo, 4);
            tripName.setText(triploadedevent.trip.display_name.toUpperCase());
            tripName.setSelected(true);
            InflaterAdapter.setVisibility(tripName, 0);
        } else
        {
            InflaterAdapter.setVisibility(logo, 0);
            InflaterAdapter.setVisibility(tripName, 4);
        }
        tripsMenuController.setTrip();
    }

    public void onTripLoadedFailed(TripLoadFail triploadfail)
    {
        if(NetworkState.isOffline(this))
        {
            Crouton.cancelAllCroutons();
            Croutons.showNetworkUnavailableError(mainWindow, this);
        }
    }

    public void onTripsClick()
    {
        hideUserPanel();
        dismissSearch();
        tripsMenu.openLayer(true);
        logEvent(0x7f0c0071, 0x7f0c004c);
    }

    public void onTripsHighLighted(TripsHighlighted tripshighlighted)
    {
        if(tripshighlighted == TripsHighlighted.ENABLED)
        {
            tripsButton.setBackgroundResource(0x7f0201bc);
            return;
        } else
        {
            tripsButton.setBackgroundResource(0x7f0201b9);
            return;
        }
    }

    void onUserImageClick()
    {
        if(!hideUserPanel())
        {
            RTAnalytics.logEvent(this, 0x7f0c006b, 0x7f0c0022);
            getSupportFragmentManager().beginTransaction().setCustomAnimations(0x7f04000e, 0x7f040013).add(0x7f090103, new UserPanelFragment()).commitAllowingStateLoss();
            dismissSearch();
        }
    }

    public void onUserLoaded(User user)
    {
        if(!identified)
            identifyToAnalytics(user);
        ((Picasso)picasso.get()).load(user.image_iphone_url).placeholder(0x7f020166).error(0x7f0200e2).into(userImage);
    }

    public void onUserUpdated(UserUpdatedEvent userupdatedevent)
    {
        Crouton.cancelAllCroutons();
        identified = false;
        identifyToAnalytics(userupdatedevent.user);
        if(userupdatedevent.isOk())
        {
            Crouton.showText(this, 0x7f0c00de, Style.CONFIRM, mainWindow);
            reloadUserInfo();
            return;
        } else
        {
            Crouton.showText(this, 0x7f0c00dd, Style.ALERT, mainWindow);
            return;
        }
    }

    void reloadUserInfo()
    {
        getUserFromServer().onErrorResumeNext(((Persistence)persistence.get()).getUser()).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer() {

            public void onCompleted()
            {
            }

            public void onError(Throwable throwable)
            {
                Log.e(throwable);
            }

            public void onNext(User user)
            {
                if(user != null)
                    ((Bus)busLazy.get()).post(user);
            }

            public volatile void onNext(Object obj)
            {
                onNext((User)obj);
            }

            final MainActivity this$0;

            
            {
                this$0 = MainActivity.this;
                super();
            }
        }
);
    }

    void restoreState(Bundle bundle)
    {
        if(bundle.getBoolean("LIST", false))
            selectedImage.post(new Runnable() {

                public void run()
                {
                    beginFastFragmentTransaction().show(listFragment).hide(mapFragment).commitAllowingStateLoss();
                    moveSwitch(true, 0);
                }

                final MainActivity this$0;

            
            {
                this$0 = MainActivity.this;
                super();
            }
            }
);
        if(bundle.getBoolean("left menu", false))
            leftLayer.post(new Runnable() {

                public void run()
                {
                    if(leftLayer != null)
                        leftLayer.openLayer(false);
                }

                final MainActivity this$0;

            
            {
                this$0 = MainActivity.this;
                super();
            }
            }
);
        if(bundle.getBoolean("right menu", false))
            tripsMenu.post(new Runnable() {

                public void run()
                {
                    if(tripsMenu != null)
                        tripsMenu.openLayer(false);
                }

                final MainActivity this$0;

            
            {
                this$0 = MainActivity.this;
                super();
            }
            }
);
    }

    void search(Group agroup[])
    {
        mapFragment.search(agroup);
        listFragment.setContentShown(false);
    }

    public void startActivityForResult(Intent intent, int i)
    {
        if(intent == null)
            break MISSING_BLOCK_LABEL_10;
        super.startActivityForResult(intent, i);
        return;
        Exception exception;
        exception;
        exception.printStackTrace();
        finish();
        return;
    }

    void zoomAndSelectPoi(Poi poi)
    {
        mapFragment.zoomAndSelectPoi(poi);
        listFragment.addPoi(poi);
    }

    public static final int ADD_NEW_POI = 1007;
    public static final int ADD_POI_TO_TRIP = 1006;
    private static final String GooglePlayStorePackageNameNew = "com.android.vending";
    private static final String GooglePlayStorePackageNameOld = "com.google.market";
    public static final String LAT = "LAT";
    static final String LEFT_LAYER = "left menu";
    public static final String LIST = "LIST";
    public static final String LON = "LON";
    static final String RIGHT_LAYER = "right menu";
    static final String TRIP_LOADED = "trip loaded";
    Lazy busLazy;
    List fragmentsForController;
    boolean identified;
    boolean introshown;
    Lazy jacksonSerializerLazy;
    SlidingLayer leftLayer;
    TripsPlacesListFragment listFragment;
    ImageView logo;
    Lazy lruCacheLazy;
    FrameLayout mainWindow;
    RTMapFragment mapFragment;
    View mapListSwitch;
    Lazy persistence;
    Lazy picasso;
    ImageView placesButton;
    CategoriesFragment placesFragment;
    Lazy roadtrippers;
    private Subscription savedPlaceObservable;
    private SearchFragment searchFragment;
    ImageView selectedImage;
    TextView stageIndicator;
    TripLoadedEvent tripLoadedEvent;
    Lazy tripManagerLazy;
    TextView tripName;
    ImageView tripsButton;
    SlidingLayer tripsMenu;
    TripsMenuController tripsMenuController;
    ImageView userImage;
}
