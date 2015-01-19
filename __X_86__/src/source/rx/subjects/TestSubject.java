// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package rx.subjects;

import java.util.Collection;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import rx.Notification;
import rx.Observer;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.schedulers.TestScheduler;

// Referenced classes of package rx.subjects:
//            Subject, SubjectSubscriptionManager

public final class TestSubject extends Subject
{

    protected TestSubject(rx.Observable.OnSubscribe onsubscribe, SubjectSubscriptionManager subjectsubscriptionmanager, AtomicReference atomicreference, TestScheduler testscheduler)
    {
        Subject(onsubscribe);
        subscriptionManager = subjectsubscriptionmanager;
        lastNotification = atomicreference;
        innerScheduler = testscheduler.createInnerScheduler();
    }

    private void _onCompleted()
    {
        Collection collection = subscriptionManager.terminate(new Action0() {

            public void call()
            {
                lastNotification.set(Notification.createOnCompleted());
            }

            final TestSubject this$0;

            
            {
                this$0 = TestSubject.this;
                Object();
            }
        }
);
        if(collection != null)
        {
            for(Iterator iterator = collection.iterator(); iterator.hasNext(); ((Observer)iterator.next()).onCompleted());
        }
    }

    private void _onError(final Throwable e)
    {
        Collection collection = subscriptionManager.terminate(new Action0() {

            public void call()
            {
                lastNotification.set(Notification.createOnError(e));
            }

            final TestSubject this$0;
            final Throwable val$e;

            
            {
                this$0 = TestSubject.this;
                e = throwable;
                Object();
            }
        }
);
        if(collection != null)
        {
            for(Iterator iterator = collection.iterator(); iterator.hasNext(); ((Observer)iterator.next()).onError(e));
        }
    }

    private void _onNext(Object obj)
    {
        SubjectSubscriptionManager.SubjectObserver asubjectobserver[] = subscriptionManager.rawSnapshot();
        int i = asubjectobserver.length;
        for(int j = 0; j < i; j++)
            asubjectobserver[j].onNext(obj);

    }

    public static TestSubject create(TestScheduler testscheduler)
    {
        SubjectSubscriptionManager subjectsubscriptionmanager = new SubjectSubscriptionManager();
        AtomicReference atomicreference = new AtomicReference();
        return new TestSubject(subjectsubscriptionmanager.getOnSubscribeFunc(new Action1() {

            public volatile void call(Object obj)
            {
                call((SubjectSubscriptionManager.SubjectObserver)obj);
            }

            public void call(SubjectSubscriptionManager.SubjectObserver subjectobserver)
            {
            }

        }
, new Action1(atomicreference) {

            public volatile void call(Object obj)
            {
                call((SubjectSubscriptionManager.SubjectObserver)obj);
            }

            public void call(SubjectSubscriptionManager.SubjectObserver subjectobserver)
            {
                ((Notification)lastNotification.get()).accept(subjectobserver);
            }

            final AtomicReference val$lastNotification;

            
            {
                lastNotification = atomicreference;
                Object();
            }
        }
, null), subjectsubscriptionmanager, atomicreference, testscheduler);
    }

    public void onCompleted()
    {
        onCompleted(innerScheduler.now());
    }

    public void onCompleted(long l)
    {
        innerScheduler.schedule(new Action1() {

            public volatile void call(Object obj)
            {
                call((rx.Scheduler.Inner)obj);
            }

            public void call(rx.Scheduler.Inner inner)
            {
                _onCompleted();
            }

            final TestSubject this$0;

            
            {
                this$0 = TestSubject.this;
                Object();
            }
        }
, l, TimeUnit.MILLISECONDS);
    }

    public void onError(Throwable throwable)
    {
        onError(throwable, innerScheduler.now());
    }

    public void onError(final Throwable e, long l)
    {
        innerScheduler.schedule(new Action1() {

            public volatile void call(Object obj)
            {
                call((rx.Scheduler.Inner)obj);
            }

            public void call(rx.Scheduler.Inner inner)
            {
                _onError(e);
            }

            final TestSubject this$0;
            final Throwable val$e;

            
            {
                this$0 = TestSubject.this;
                e = throwable;
                Object();
            }
        }
, l, TimeUnit.MILLISECONDS);
    }

    public void onNext(Object obj)
    {
        onNext(obj, innerScheduler.now());
    }

    public void onNext(final Object v, long l)
    {
        innerScheduler.schedule(new Action1() {

            public volatile void call(Object obj)
            {
                call((rx.Scheduler.Inner)obj);
            }

            public void call(rx.Scheduler.Inner inner)
            {
                _onNext(v);
            }

            final TestSubject this$0;
            final Object val$v;

            
            {
                this$0 = TestSubject.this;
                v = obj;
                Object();
            }
        }
, l, TimeUnit.MILLISECONDS);
    }

    private final rx.Scheduler.Inner innerScheduler;
    private final AtomicReference lastNotification;
    private final SubjectSubscriptionManager subscriptionManager;




}
