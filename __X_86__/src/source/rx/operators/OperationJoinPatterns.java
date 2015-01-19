// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package rx.operators;

import java.util.*;
import rx.*;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.joins.*;
import rx.observers.Subscribers;
import rx.subscriptions.CompositeSubscription;

public class OperationJoinPatterns
{

    public OperationJoinPatterns()
    {
    }

    public static Pattern2 and(Observable observable, Observable observable1)
    {
        if(observable == null)
            throw new NullPointerException("left");
        if(observable1 == null)
            throw new NullPointerException("right");
        else
            return new Pattern2(observable, observable1);
    }

    public static Plan0 then(Observable observable, Func1 func1)
    {
        if(observable == null)
            throw new NullPointerException("source");
        if(func1 == null)
            throw new NullPointerException("selector");
        else
            return (new Pattern1(observable)).then(func1);
    }

    public static rx.Observable.OnSubscribeFunc when(Iterable iterable)
    {
        if(iterable == null)
            throw new NullPointerException("plans");
        else
            return new rx.Observable.OnSubscribeFunc(iterable) {

                public Subscription onSubscribe(final Observer t1)
                {
                    HashMap hashmap;
                    Object obj;
                    final ArrayList activePlans;
                    Observer observer;
                    hashmap = new HashMap();
                    obj = new Object();
                    activePlans = new ArrayList();
                    observer = hashmap. new Observer() {

                        public void onCompleted()
                        {
                            t1.onCompleted();
                        }

                        public void onError(Throwable throwable)
                        {
                            for(Iterator iterator = externalSubscriptions.values().iterator(); iterator.hasNext(); ((JoinObserver)iterator.next()).unsubscribe());
                            t1.onError(throwable);
                        }

                        public void onNext(Object obj)
                        {
                            t1.onNext(obj);
                        }

                        final _cls1 this$0;
                        final Map val$externalSubscriptions;
                        final Observer val$t1;

            
            {
                this$0 = final__pcls1;
                t1 = observer;
                externalSubscriptions = Map.this;
                super();
            }
                    }
;
                    for(Iterator iterator = plans.iterator(); iterator.hasNext(); activePlans.add(((Plan0)iterator.next()).activate(hashmap, observer, observer. new Action1() {

                public volatile void call(Object obj)
                {
                    call((ActivePlan0)obj);
                }

                public void call(ActivePlan0 activeplan0)
                {
                    activePlans.remove(activeplan0);
                    if(activePlans.isEmpty())
                        out.onCompleted();
                }

                final _cls1 this$0;
                final List val$activePlans;
                final Observer val$out;

            
            {
                this$0 = final__pcls1;
                activePlans = list;
                out = Observer.this;
                super();
            }
            }
)));
                      goto _L1
                    Throwable throwable;
                    throwable;
                    Object obj1 = Observable.error(throwable).unsafeSubscribe(Subscribers.from(t1));
_L3:
                    return ((Subscription) (obj1));
_L1:
                    obj1 = new CompositeSubscription();
                    Iterator iterator1 = hashmap.values().iterator();
                    while(iterator1.hasNext()) 
                    {
                        JoinObserver joinobserver = (JoinObserver)iterator1.next();
                        joinobserver.subscribe(obj);
                        ((CompositeSubscription) (obj1)).add(joinobserver);
                    }
                    if(true) goto _L3; else goto _L2
_L2:
                }

                final Iterable val$plans;

            
            {
                plans = iterable;
                super();
            }
            }
;
    }

    public static transient rx.Observable.OnSubscribeFunc when(Plan0 aplan0[])
    {
        if(aplan0 == null)
            throw new NullPointerException("plans");
        else
            return when(((Iterable) (Arrays.asList(aplan0))));
    }
}
