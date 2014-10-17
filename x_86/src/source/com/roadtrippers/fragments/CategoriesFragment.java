// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.roadtrippers.fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.HeaderViewListAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import butterknife.ButterKnife;
import com.roadtrippers.activities.AddPlaceActivity;
import com.roadtrippers.adapters.CategoriesAdapter;
import com.roadtrippers.api.Roadtrippers;
import com.roadtrippers.api.models.Bucket;
import com.roadtrippers.api.models.Category;
import com.roadtrippers.api.models.Group;
import com.roadtrippers.events.GroupCheck;
import com.roadtrippers.fragments.base.RetainedProgressFragment;
import com.roadtrippers.util.Log;
import com.roadtrippers.util.Persistence;
import com.roadtrippers.util.Serializer;
import com.squareup.otto.Bus;
import com.tjerkw.slideexpandable.library.SlideExpandableListAdapter;
import dagger.Lazy;
import rx.Notification;
import rx.Observable;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
import rx.subscriptions.Subscriptions;

public class CategoriesFragment extends RetainedProgressFragment
{

    public CategoriesFragment()
    {
        sameVersion = false;
    }

    public static int indexOf(Object obj, Object aobj[])
    {
        for(int i = 0; i < aobj.length; i++)
            if(aobj[i].equals(obj))
                return i;

        return -1;
    }

    private static void logGroupStatus(Group agroup[])
    {
        if(agroup != null)
        {
            int i = agroup.length;
            for(int j = 0; j < i; j++)
            {
                Group group = agroup[j];
                Object aobj[] = new Object[3];
                aobj[0] = group.name;
                aobj[1] = String.valueOf(group.checked);
                aobj[2] = Integer.valueOf(group.categories.length);
                Log.d("GroupFromSavestate: %s, Checked? %s, With %d categories", aobj);
                Category acategory[] = group.categories;
                int k = acategory.length;
                for(int l = 0; l < k; l++)
                {
                    Category category = acategory[l];
                    Object aobj1[] = new Object[2];
                    aobj1[0] = category.name;
                    aobj1[1] = String.valueOf(category.checked);
                    Log.d("\t\t\tCategory: %s, Checked? %s", aobj1);
                }

            }

        } else
        {
            Log.d("GroupFromSavestate: Groups are null");
        }
    }

    void abortSubscription()
    {
        if(subscription != null)
        {
            subscription.unsubscribe();
            subscription = null;
        }
    }

    public void clear()
    {
        if(groups != null)
        {
            Group agroup[] = groups;
            int i = agroup.length;
            for(int j = 0; j < i; j++)
            {
                Group group = agroup[j];
                group.checked = false;
                Category acategory[] = group.categories;
                int k = acategory.length;
                for(int l = 0; l < k; l++)
                    acategory[l].checked = false;

            }

            getListAdapter().notifyDataSetChanged();
        }
    }

    View createAddPlaceFooter()
    {
        View view = LayoutInflater.from(getActivity()).inflate(0x7f030044, places, false);
        if(view != null)
        {
            ButterKnife.findById(view, 0x7f09007a).setVisibility(8);
            ButterKnife.findById(view, 0x7f09007c).setVisibility(8);
            ((TextView)ButterKnife.findById(view, 0x7f09007b)).setText(0x7f0c004e);
            ((ImageView)ButterKnife.findById(view, 0x7f09007e)).setImageResource(0x7f0200f9);
            insertPlus((FrameLayout)ButterKnife.findById(view, 0x7f090078));
            view.setOnClickListener(new android.view.View.OnClickListener() {

                public void onClick(View view1)
                {
                    getActivity().startActivityForResult(new Intent(getActivity(), com/roadtrippers/activities/AddPlaceActivity), 1007);
                }

                final CategoriesFragment this$0;

            
            {
                this$0 = CategoriesFragment.this;
                Object();
            }
            }
);
        }
        return view;
    }

    Observable getBuckets()
    {
        return getBucketsFromServer().onErrorReturn(new Func1() {

            public volatile Object call(Object obj)
            {
                return call((Throwable)obj);
            }

            public Bucket[] call(Throwable throwable)
            {
                return new Bucket[0];
            }

            final CategoriesFragment this$0;

            
            {
                this$0 = CategoriesFragment.this;
                Object();
            }
        }
);
    }

    Observable getCategories()
    {
        return getCategoriesServerOnly();
    }

    Observable getCategoriesPersistence()
    {
        return Observable.mergeDelayError(getGroupsFromServer(), ((Persistence)persistence.get()).getGroups()).map(Group.removeTownsAndServices()).onErrorReturn(new Func1() {

            public volatile Object call(Object obj)
            {
                return call((Throwable)obj);
            }

            public Group[] call(Throwable throwable)
            {
                return new Group[0];
            }

            final CategoriesFragment this$0;

            
            {
                this$0 = CategoriesFragment.this;
                Object();
            }
        }
);
    }

    Observable getCategoriesServerOnly()
    {
        return getGroupsFromServer().map(Group.removeTownsAndServices()).onErrorReturn(new Func1() {

            public volatile Object call(Object obj)
            {
                return call((Throwable)obj);
            }

            public Group[] call(Throwable throwable)
            {
                return new Group[0];
            }

            final CategoriesFragment this$0;

            
            {
                this$0 = CategoriesFragment.this;
                Object();
            }
        }
);
    }

    public Group[] getGroups()
    {
        return groups;
    }

    Observable getGroupsFromServer()
    {
        return ((Roadtrippers)roadtrippers.get()).getCategories().map(Group.categoriesToGroups()).doOnEach(((Persistence)persistence.get()).saveGroups());
    }

    public CategoriesAdapter getListAdapter()
    {
        if(places.getAdapter() != null)
            return (CategoriesAdapter)((SlideExpandableListAdapter)((HeaderViewListAdapter)places.getAdapter()).getWrappedAdapter()).getWrappedAdapter();
        else
            return null;
    }

    public GroupCheck groupsChecked()
    {
        return new GroupCheck(groups);
    }

    void insertPlus(FrameLayout framelayout)
    {
        framelayout.removeAllViews();
        int i = getResources().getDimensionPixelSize(0x7f0a0033);
        int j = getResources().getDimensionPixelSize(0x7f0a0034);
        View view = new View(getActivity());
        view.setBackgroundResource(0x7f08002c);
        framelayout.addView(view, new LayoutParams(i, j, 17));
        View view1 = new View(getActivity());
        view1.setBackgroundResource(0x7f08002c);
        framelayout.addView(view1, new LayoutParams(j, i, 17));
    }

    void makeRequest()
    {
        setContentShown(false);
        subscription = Observable.zip(getCategories(), getBuckets(), Group.addBucketsToCategories()).doOnEach(mergeFirstGroup()).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer() {

            public void onCompleted()
            {
            }

            public void onError(Throwable throwable)
            {
                android.util.Log.e("onError categoryFragment", "");
                if(groups == null && getView() != null)
                {
                    setContentEmpty(true);
                    setContentShown(true);
                }
            }

            public volatile void onNext(Object obj)
            {
                onNext((Group[])obj);
            }

            public void onNext(Group agroup[])
            {
                if(agroup.length > 0)
                {
                    if(groups == null || groups.length <= 2)
                    {
                        groups = agroup;
                        setListAdapter();
                        setContentEmpty(false);
                        setContentShown(true);
                        return;
                    } else
                    {
                        groups[0] = agroup[0];
                        getListAdapter().notifyDataSetChanged();
                        return;
                    }
                } else
                {
                    onError(null);
                    return;
                }
            }

            final CategoriesFragment this$0;

            
            {
                this$0 = CategoriesFragment.this;
                Object();
            }
        }
);
    }

    void merge(Category acategory[], Category acategory1[])
    {
        int i = acategory1.length;
        for(int j = 0; j < i; j++)
        {
            Category category = acategory1[j];
            int k = indexOf(category, acategory);
            if(k != -1)
                category.checked = acategory[k].checked;
        }

    }

    Action1 mergeFirstGroup()
    {
        return new Action1() {

            public volatile void call(Object obj)
            {
                call((Notification)obj);
            }

            public void call(Notification notification)
            {
                if(!notification.isOnError() && notification.getValue() != null)
                {
                    Group agroup[] = (Group[])(Group[])notification.getValue();
                    if(groups != null && groups.length > 0 && agroup.length > 0)
                    {
                        agroup[0].checked = groups[0].checked;
                        agroup[0].expanded = groups[0].expanded;
                        merge(groups[0].categories, agroup[0].categories);
                    }
                }
            }

            final CategoriesFragment this$0;

            
            {
                this$0 = CategoriesFragment.this;
                Object();
            }
        }
;
    }

    protected void onContentCreated(Bundle bundle)
    {
        int i = -1;
        boolean flag;
        try
        {
            i = getActivity().getApplicationContext().getPackageManager().getPackageInfo(getActivity().getApplicationContext().getPackageName(), 0).versionCode;
        }
        catch(android.content.pm.PackageManager.NameNotFoundException namenotfoundexception)
        {
            namenotfoundexception.printStackTrace();
        }
        if(i == ((Persistence)persistence.get()).getSharedPreferences().getInt("version", -2))
            flag = true;
        else
            flag = false;
        sameVersion = flag;
        ((Persistence)persistence.get()).getSharedPreferences().edit().putInt("version", i).commit();
        if(bundle != null)
        {
            Group agroup[] = (Group[])((Serializer)jackson.get()).deserialize(bundle.getString("groups"), [Lcom/roadtrippers/api/models/Group;);
            logGroupStatus(agroup);
            groups = agroup;
        }
        if(groups != null && groups.length > 2)
        {
            android.util.Log.d("Groups", (new StringBuilder()).append("Not null, length = ").append(groups.length).toString());
            ((Bus)bus.get()).post(new GroupCheck(groups));
            setListAdapter();
            setContentShownNoAnimation(true);
            return;
        } else
        {
            android.util.Log.d("Groups", "No groups");
            makeRequest();
            return;
        }
    }

    protected void onCreateContent(Bundle bundle)
    {
        setContentView(0x7f030053);
    }

    public View onCreateView(LayoutInflater layoutinflater, ViewGroup viewgroup, Bundle bundle)
    {
        return layoutinflater.inflate(0x7f030054, viewgroup, false);
    }

    public void onDestroy()
    {
        abortSubscription();
        onDestroy();
    }

    public void onDestroyView()
    {
        if(getListAdapter() != null)
            getListAdapter().release();
        onDestroyView();
    }

    public void onResume()
    {
        onResume();
        ((Persistence)persistence.get()).getBuckets().flatMap(new Func1() {

            public volatile Object call(Object obj)
            {
                return call((Bucket[])obj);
            }

            public Observable call(Bucket abucket[])
            {
                return Observable.create(abucket. new rx.Observable.OnSubscribeFunc() {

                    public Subscription onSubscribe(Observer observer)
                    {
                        if(groups != null)
                        {
                            Group group = new Group(newBuckets);
                            merge(groups[0].categories, group.categories);
                            group.checked = groups[0].checked;
                            group.expanded = groups[0].expanded;
                            observer.onNext(group);
                        } else
                        {
                            observer.onError(null);
                        }
                        return Subscriptions.empty();
                    }

                    final _cls8 this$1;
                    final Bucket val$newBuckets[];

            
            {
                this$1 = final__pcls8;
                newBuckets = _5B_Lcom.roadtrippers.api.models.Bucket_3B_.this;
                Object();
            }
                }
);
            }

            final CategoriesFragment this$0;

            
            {
                this$0 = CategoriesFragment.this;
                Object();
            }
        }
).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer() {

            public void onCompleted()
            {
            }

            public void onError(Throwable throwable)
            {
            }

            public void onNext(Group group)
            {
                if(groups != null)
                {
                    groups[0] = group;
                    getListAdapter().notifyDataSetChanged();
                }
            }

            public volatile void onNext(Object obj)
            {
                onNext((Group)obj);
            }

            final CategoriesFragment this$0;

            
            {
                this$0 = CategoriesFragment.this;
                Object();
            }
        }
);
    }

    protected void onRetryButtonClick()
    {
        makeRequest();
    }

    public void onSaveInstanceState(Bundle bundle)
    {
        onSaveInstanceState(bundle);
        String s = ((Serializer)jackson.get()).serialize(groups);
        bundle.putString("groups", s);
        try
        {
            bundle.putInt("version", getActivity().getApplicationContext().getPackageManager().getPackageInfo(getActivity().getApplicationContext().getPackageName(), 0).versionCode);
        }
        catch(android.content.pm.PackageManager.NameNotFoundException namenotfoundexception)
        {
            namenotfoundexception.printStackTrace();
        }
        Log.d("Categories serialized: %s", new Object[] {
            s
        });
    }

    void setListAdapter()
    {
        if(places.getFooterViewsCount() == 0)
        {
            View view = createAddPlaceFooter();
            if(view != null)
                places.addFooterView(view);
        }
        places.setAdapter(new SlideExpandableListAdapter(getActivity(), new CategoriesAdapter(getActivity(), groups), 0x7f090077, 0x7f09007f));
    }

    Lazy bus;
    Group groups[];
    Lazy jackson;
    Lazy persistence;
    ListView places;
    Lazy roadtrippers;
    boolean sameVersion;
    Subscription subscription;
}
