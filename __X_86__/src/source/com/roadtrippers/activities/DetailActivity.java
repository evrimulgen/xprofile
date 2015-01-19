// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.roadtrippers.activities;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import com.roadtrippers.activities.base.BaseActivity;
import com.roadtrippers.api.models.Poi;
import com.roadtrippers.events.SavePlaceEvent;
import com.roadtrippers.fragments.BucketListFragment;
import com.roadtrippers.fragments.DetailFragment;
import com.roadtrippers.util.Serializer;
import dagger.Lazy;

public class DetailActivity extends BaseActivity
{

    public DetailActivity()
    {
    }

    public static Intent newInstance(Context context, String s)
    {
        return (new Intent(context, com/roadtrippers/activities/DetailActivity)).putExtra("place", s);
    }

    boolean dismissBuckets()
    {
        if(bucketListFragment != null)
        {
            getSupportFragmentManager().popBackStack();
            bucketListFragment = null;
            return true;
        } else
        {
            return false;
        }
    }

    protected void onActivityResult(int i, int j, Intent intent)
    {
        super.onActivityResult(i, j, intent);
    }

    public void onBackPressed()
    {
        fragment = (DetailFragment)getSupportFragmentManager().findFragmentById(0x7f0900dc);
        if(!fragment.onBackPressed())
            super.onBackPressed();
    }

    protected void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        setContentView(0x7f03003b);
        if(bundle == null)
        {
            Uri uri = getIntent().getData();
            FragmentTransaction fragmenttransaction = getSupportFragmentManager().beginTransaction();
            String s;
            if(uri == null)
                s = getIntent().getStringExtra("place");
            else
                s = ((Serializer)jackson.get()).serialize(new Poi(uri.getLastPathSegment()));
            fragmenttransaction.add(0x7f0900dc, DetailFragment.newInstance(s)).commitAllowingStateLoss();
        }
    }

    public void onSavePlace(SavePlaceEvent saveplaceevent)
    {
        bucketListFragment = BucketListFragment.newInstance(((Serializer)jackson.get()).serialize(saveplaceevent.getPoi()));
        getSupportFragmentManager().beginTransaction().addToBackStack(null).setCustomAnimations(0x7f04000a, 0x7f04000f, 0x7f04000a, 0x7f04000f).add(0x7f0900de, bucketListFragment).commitAllowingStateLoss();
    }

    static final String PLACE = "place";
    private BucketListFragment bucketListFragment;
    private DetailFragment fragment;
    Lazy jackson;
}
