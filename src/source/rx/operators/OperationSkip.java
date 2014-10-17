// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package rx.operators;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import rx.*;
import rx.functions.Action1;
import rx.subscriptions.CompositeSubscription;

// Referenced classes of package rx.operators:
//            SafeObservableSubscription

public final class OperationSkip
{
    public static final class SkipTimed
        implements rx.Observable.OnSubscribeFunc
    {

        public Subscription onSubscribe(Observer observer)
        {
            SafeObservableSubscription safeobservablesubscription = new SafeObservableSubscription();
            SafeObservableSubscription safeobservablesubscription1 = new SafeObservableSubscription();
            CompositeSubscription compositesubscription = new CompositeSubscription(new Subscription[] {
                safeobservablesubscription, safeobservablesubscription1
            });
            SourceObserver sourceobserver = new SourceObserver(observer, compositesubscription);
            safeobservablesubscription1.wrap(source.unsafeSubscribe(sourceobserver));
            if(!safeobservablesubscription1.isUnsubscribed())
                safeobservablesubscription.wrap(scheduler.schedule(sourceobserver, time, unit));
            return compositesubscription;
        }

        final Scheduler scheduler;
        final Observable source;
        final long time;
        final TimeUnit unit;

        public SkipTimed(Observable observable, long l, TimeUnit timeunit, Scheduler scheduler1)
        {
            source = observable;
            time = l;
            unit = timeunit;
            scheduler = scheduler1;
        }
    }

    private static final class SkipTimed.SourceObserver extends Subscriber
        implements Action1
    {

        public volatile void call(Object obj)
        {
            call((rx.Scheduler.Inner)obj);
        }

        public void call(rx.Scheduler.Inner inner)
        {
            gate.set(true);
        }

        public void onCompleted()
        {
            observer.onCompleted();
            cancel.unsubscribe();
            return;
            Exception exception;
            exception;
            cancel.unsubscribe();
            throw exception;
        }

        public void onError(Throwable throwable)
        {
            observer.onError(throwable);
            cancel.unsubscribe();
            return;
            Exception exception;
            exception;
            cancel.unsubscribe();
            throw exception;
        }

        public void onNext(Object obj)
        {
            if(gate.get())
                observer.onNext(obj);
        }

        final Subscription cancel;
        final AtomicBoolean gate = new AtomicBoolean();
        final Observer observer;

        public SkipTimed.SourceObserver(Observer observer1, Subscription subscription)
        {
            observer = observer1;
            cancel = subscription;
        }
    }


    public OperationSkip()
    {
    }
}
