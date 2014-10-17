// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.roadtrippers.controllers;

import android.content.res.Resources;
import android.support.v4.app.*;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.ButterKnife;
import com.nineoldandroids.view.ViewHelper;
import com.roadtrippers.RoadTrippersApp;
import com.roadtrippers.events.CancelCurrentTrip;
import com.roadtrippers.fragments.*;
import com.roadtrippers.util.TripManager;
import com.squareup.otto.Bus;
import dagger.Lazy;
import java.util.concurrent.Callable;

public class TripsMenuController
{

    public TripsMenuController(FragmentActivity fragmentactivity)
    {
        fragmentActivity = fragmentactivity;
        RoadTrippersApp.from(fragmentactivity).inject(this);
        ButterKnife.inject(this, fragmentactivity);
        if(fragmentactivity.getSupportFragmentManager().findFragmentById(0x7f09012a) == null)
        {
            currentTripFragment = new CurrentTripFragment();
            tripsListFragment = new TripsListFragment();
            fragmentactivity.getSupportFragmentManager().beginTransaction().add(0x7f09012a, tripsListFragment).add(0x7f09012a, currentTripFragment).commit();
        }
        setTrip();
    }

    private void setCurrentTripButtonVisible(boolean flag)
    {
        if(flag)
        {
            currentTrip.setVisibility(0);
            return;
        } else
        {
            currentTrip.setVisibility(4);
            return;
        }
    }

    private void translateTriangle(float f)
    {
        int i = ((View)triangle.getParent()).getWidth();
        ViewHelper.setTranslationX(triangle, ((float)(i / 4) + f * (float)(i / 2)) - (float)(triangle.getWidth() / 2));
    }

    public void attachCurrentTripFragment(CurrentTripFragment currenttripfragment)
    {
        currentTripFragment = currenttripfragment;
    }

    public void attachFragment(Fragment fragment)
    {
        if(fragment instanceof CurrentTripFragment)
            attachCurrentTripFragment((CurrentTripFragment)fragment);
        else
        if(fragment instanceof TripsListFragment)
        {
            attachTripsListFragment((TripsListFragment)fragment);
            return;
        }
    }

    public void attachTripsListFragment(TripsListFragment tripslistfragment)
    {
        tripsListFragment = tripslistfragment;
    }

    public boolean onBackPressed()
    {
        return currentTripFragment.onBackPressed();
    }

    void onCurrentTripClick()
    {
        if(((TripManager)tripManagerLazy.get()).getCurrentTrip() != null)
            selectFragment(0);
    }

    public void onEvent(CancelCurrentTrip cancelcurrenttrip)
    {
        ((TripManager)tripManagerLazy.get()).setCurrentTrip(null);
        setTrip();
        selectFragment(1);
    }

    void onMyTripsClick()
    {
        selectFragment(1);
    }

    public void onPause()
    {
        ((Bus)busLazy.get()).unregister(this);
    }

    public void onResume()
    {
        ((Bus)busLazy.get()).register(this);
        setTrip();
    }

    void selectFragment(final int i)
    {
        int j = 0x7f080043;
        int k;
        if(tripsListFragment != null && currentTripFragment != null)
        {
            FragmentTransaction fragmenttransaction = fragmentActivity.getSupportFragmentManager().beginTransaction();
            Resources resources;
            TextView textview;
            TextView textview1;
            Object obj;
            FragmentTransaction fragmenttransaction1;
            Object obj1;
            if(i == 0)
                obj = tripsListFragment;
            else
                obj = currentTripFragment;
            fragmenttransaction1 = fragmenttransaction.hide(((Fragment) (obj)));
            if(i == 0)
                obj1 = currentTripFragment;
            else
                obj1 = tripsListFragment;
            fragmenttransaction1.show(((Fragment) (obj1))).commitAllowingStateLoss();
        }
        resources = fragmentActivity.getResources();
        textview = currentTrip;
        if(i == 0)
            k = j;
        else
            k = 0x7f080034;
        textview.setTextColor(resources.getColor(k));
        textview1 = myTrips;
        if(i != 1)
            j = 0x7f080034;
        textview1.setTextColor(resources.getColor(j));
        DetailFragment.runAfterMeasure(triangle, new Runnable() {

            public void run()
            {
                translateTriangle(i);
            }

            final TripsMenuController this$0;
            final int val$i;

            
            {
                this$0 = TripsMenuController.this;
                i = j;
                super();
            }
        }
, new Callable() {

            public Boolean call()
                throws Exception
            {
                boolean flag;
                if(((View)triangle.getParent()).getWidth() > 0 && triangle.getWidth() > 0)
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

            final TripsMenuController this$0;

            
            {
                this$0 = TripsMenuController.this;
                super();
            }
        }
);
    }

    public void setTrip()
    {
        boolean flag;
        int i;
        if(((TripManager)tripManagerLazy.get()).getCurrentTrip() != null)
            flag = true;
        else
            flag = false;
        if(flag)
            i = 0;
        else
            i = 1;
        selectFragment(i);
        setCurrentTripButtonVisible(flag);
    }

    Lazy busLazy;
    TextView currentTrip;
    public CurrentTripFragment currentTripFragment;
    FragmentActivity fragmentActivity;
    TextView myTrips;
    ImageView triangle;
    Lazy tripManagerLazy;
    TripsListFragment tripsListFragment;

}
