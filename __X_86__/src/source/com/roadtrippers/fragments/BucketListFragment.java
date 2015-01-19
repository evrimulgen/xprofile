// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.roadtrippers.fragments;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.view.*;
import android.widget.*;
import com.roadtrippers.adapters.BucketListV2Adapter;
import com.roadtrippers.adapters.base.InflaterAdapter;
import com.roadtrippers.api.Roadtrippers;
import com.roadtrippers.api.models.*;
import com.roadtrippers.fragments.base.BaseProgressFragment;
import com.roadtrippers.fragments.base.RetainedProgressFragment;
import com.roadtrippers.util.*;
import dagger.Lazy;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import rx.*;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.*;
import rx.schedulers.Schedulers;
import rx.subscriptions.Subscriptions;

public class BucketListFragment extends RetainedProgressFragment
{

    public BucketListFragment()
    {
    }

    private Observable addPlaceObservable(final Poi poi, final Bucket bucket)
    {
        return Observable.create(new rx.Observable.OnSubscribe() {

            public volatile void call(Object obj)
            {
                call((Subscriber)obj);
            }

            public void call(Subscriber subscriber)
            {
                retrofit.client.Response response = null;
                boolean flag;
                Object aobj1[];
                try
                {
                    Log.d("About to make call to add place");
                    flag = bucket.contains(poi);
                }
                catch(Throwable throwable)
                {
                    if(response != null)
                    {
                        Object aobj[] = new Object[1];
                        aobj[0] = BaseProgressFragment.getBodyString(response);
                        Log.d("Response %s", aobj);
                    }
                    throwable.printStackTrace();
                    subscriber.onError(throwable);
                    return;
                }
                response = null;
                if(flag)
                    break MISSING_BLOCK_LABEL_38;
                bucket.add(poi);
                response = ((Roadtrippers)roadtrippers.get()).addPlaceToBucketList(((Persistence)persistence.get()).getUserId(), bucket.getId(), BucketListEntry.fromPoi(poi).asAddRequest());
                aobj1 = new Object[1];
                aobj1[0] = BaseProgressFragment.getBodyString(response);
                Log.d("Response %s", aobj1);
                Log.d("Made call to add place");
                subscriber.onNext(bucket);
                return;
            }

            final BucketListFragment this$0;
            final Bucket val$bucket;
            final Poi val$poi;

            
            {
                this$0 = BucketListFragment.this;
                bucket = bucket1;
                poi = poi1;
                super();
            }
        }
);
    }

    public static BucketListFragment newInstance(String s)
    {
        BucketListFragment bucketlistfragment = new BucketListFragment();
        Bundle bundle = new Bundle();
        bundle.putString("place", s);
        bucketlistfragment.setArguments(bundle);
        return bucketlistfragment;
    }

    Observer createBucketObserver(final Bucket bucket)
    {
        if(activeRequests.incrementAndGet() > 0)
            showProgress();
        return new Observer() {

            public void onCompleted()
            {
            }

            public void onError(Throwable throwable)
            {
                Bucket bucket1 = bucket;
                boolean flag;
                if(!bucket.checked)
                    flag = true;
                else
                    flag = false;
                bucket1.checked = flag;
                if(getView() != null)
                    getListAdapter().notifyDataSetChanged();
                onNext(((Bucket) (null)));
            }

            public void onNext(Bucket bucket1)
            {
                subscriptions.remove(bucket);
                if(decrementRequestsAndGet() <= 0 && getView() != null)
                    hideProgress();
            }

            public volatile void onNext(Object obj)
            {
                onNext((Bucket)obj);
            }

            final BucketListFragment this$0;
            final Bucket val$bucket;

            
            {
                this$0 = BucketListFragment.this;
                bucket = bucket1;
                super();
            }
        }
;
    }

    Subscription createBucketSubscription()
    {
        return Subscriptions.create(new Action0() {

            public void call()
            {
                decrementRequestsAndGet();
            }

            final BucketListFragment this$0;

            
            {
                this$0 = BucketListFragment.this;
                super();
            }
        }
);
    }

    int decrementRequestsAndGet()
    {
        int i = activeRequests.decrementAndGet();
        if(i < 0)
        {
            activeRequests.set(0);
            i = 0;
        }
        return i;
    }

    void done()
    {
        RTAnalytics.logEvent(getActivity(), 0x7f0c006d, 0x7f0c0028);
        getFragmentManager().popBackStack();
    }

    BucketListV2Adapter getListAdapter()
    {
        return (BucketListV2Adapter)listView.getAdapter();
    }

    Poi getPlace()
    {
        return (Poi)((Serializer)jackson.get()).deserialize(getArguments().getString("place"), com/roadtrippers/api/models/Poi);
    }

    AnimationDrawable getProgressDrawable()
    {
        return (AnimationDrawable)bucketProgress.getDrawable();
    }

    void hideProgress()
    {
        InflaterAdapter.setVisibility(bucketProgress, 8);
        getProgressDrawable().stop();
    }

    void makeRequest()
    {
        setContentShown(false);
        subscription = getBucketsFromServer().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer() {

            public void onCompleted()
            {
            }

            public void onError(Throwable throwable)
            {
            }

            public volatile void onNext(Object obj)
            {
                onNext((Bucket[])obj);
            }

            public void onNext(Bucket abucket[])
            {
                buckets = abucket;
                Observable aobservable[] = new Observable[abucket.length];
                for(int i = 0; i < abucket.length; i++)
                    aobservable[i] = ((Roadtrippers)roadtrippers.get()).getBucketDetails(((Persistence)persistence.get()).getUserId(), abucket[i].getId());

                Observable.merge(aobservable).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer() {

                    public void onCompleted()
                    {
                        setListAdapter();
                        setContentShown(true);
                        setContentEmpty(false);
                    }

                    public void onError(Throwable throwable)
                    {
                        if(getView() != null)
                        {
                            setContentShown(true);
                            setContentEmpty(false);
                        }
                    }

                    public void onNext(Bucket bucket)
                    {
                        if(bucket.contains(getPlace()))
                            bucket.checked = true;
                        for(int i = 0; i < buckets.length; i++)
                            if(bucket.getId() == buckets[i].getId())
                                buckets[i] = bucket;

                    }

                    public volatile void onNext(Object obj)
                    {
                        onNext((Bucket)obj);
                    }

                    final _cls5 this$1;

            
            {
                this$1 = _cls5.this;
                super();
            }
                }
);
            }

            final BucketListFragment this$0;

            
            {
                this$0 = BucketListFragment.this;
                super();
            }
        }
);
    }

    protected void onContentCreated(Bundle bundle)
    {
        if(buckets == null && subscription == null)
        {
            makeRequest();
            return;
        } else
        {
            setListAdapter();
            setContentShownNoAnimation(true);
            return;
        }
    }

    protected void onCreateContent(Bundle bundle)
    {
        listView = new ListView(getActivity());
        listView.setOnItemClickListener(new android.widget.AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView adapterview, View view, int i, long l)
            {
                Poi poi = getPlace();
                final Bucket bucket = getListAdapter().getItem(i);
                Subscription subscription1;
                Subscription subscription2;
                boolean flag;
                if(bucket.checked)
                {
                    subscription1 = Observable.create(poi. new rx.Observable.OnSubscribeFunc() {

                        public Subscription onSubscribe(Observer observer)
                        {
                            try
                            {
                                bucket.remove(poi);
                                RTAnalytics.logEvent(getActivity(), 0x7f0c006d, 0x7f0c0029);
                                ((Roadtrippers)roadtrippers.get()).deletePlaceFromBucket(((Persistence)persistence.get()).getUserId(), bucket.getId(), Integer.parseInt(poi.id));
                                observer.onNext(bucket);
                            }
                            catch(Throwable throwable)
                            {
                                observer.onError(throwable);
                            }
                            return createBucketSubscription();
                        }

                        final _cls1 this$1;
                        final Bucket val$bucket;
                        final Poi val$poi;

            
            {
                this$1 = final__pcls1;
                bucket = bucket1;
                poi = Poi.this;
                super();
            }
                    }
).onErrorResumeNext(addPlaceObservable(poi, bucket)).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(createBucketObserver(bucket));
                } else
                {
                    Log.d("Add place");
                    RTAnalytics.logEvent(getActivity(), 0x7f0c006d, 0x7f0c0027);
                    subscription1 = addPlaceObservable(poi, bucket).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(createBucketObserver(bucket));
                }
                subscription2 = (Subscription)subscriptions.put(bucket, subscription1);
                if(subscription2 != null)
                    subscription2.unsubscribe();
                if(!bucket.checked)
                    flag = true;
                else
                    flag = false;
                bucket.checked = flag;
                getListAdapter().notifyDataSetChanged();
            }

            final BucketListFragment this$0;

            
            {
                this$0 = BucketListFragment.this;
                super();
            }
        }
);
        setContentView(listView);
    }

    public View onCreateView(LayoutInflater layoutinflater, ViewGroup viewgroup, Bundle bundle)
    {
        return layoutinflater.inflate(0x7f030020, viewgroup, false);
    }

    public void onDestroy()
    {
        unsubscribe();
        if(buckets != null && buckets.length > 0)
            Observable.from(buckets).toList().map(new Func1() {

                public volatile Object call(Object obj)
                {
                    return call((List)obj);
                }

                public Bucket[] call(List list)
                {
                    if(list != null)
                        return (Bucket[])list.toArray(new Bucket[list.size()]);
                    else
                        return new Bucket[0];
                }

                final BucketListFragment this$0;

            
            {
                this$0 = BucketListFragment.this;
                super();
            }
            }
).observeOn(Schedulers.io()).subscribe(new Action1() {

                public volatile void call(Object obj)
                {
                    call((Bucket[])obj);
                }

                public void call(Bucket abucket[])
                {
                }

                final BucketListFragment this$0;

            
            {
                this$0 = BucketListFragment.this;
                super();
            }
            }
);
        super.onDestroy();
    }

    protected void onRetryButtonClick()
    {
        makeRequest();
    }

    void setListAdapter()
    {
        listView.setAdapter(new BucketListV2Adapter(getActivity(), buckets));
    }

    void showProgress()
    {
        if(bucketProgress != null)
            InflaterAdapter.setVisibility(bucketProgress, 0);
        AnimationDrawable animationdrawable = getProgressDrawable();
        if(!animationdrawable.isRunning())
            animationdrawable.start();
    }

    void unsubscribe()
    {
        if(subscription != null)
        {
            subscription.unsubscribe();
            subscription = null;
        }
        subscriptions.clear();
    }

    static final String PLACE = "place";
    final AtomicInteger activeRequests = new AtomicInteger(0);
    ImageView bucketProgress;
    Bucket buckets[];
    Lazy bus;
    Lazy jackson;
    ListView listView;
    Lazy persistence;
    Lazy roadtrippers;
    Subscription subscription;
    final Map subscriptions = new HashMap();

}
