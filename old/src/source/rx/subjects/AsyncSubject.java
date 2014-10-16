// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package rx.subjects;

import java.util.Collection;
import java.util.Iterator;
import java.util.concurrent.atomic.AtomicReference;
import rx.Notification;
import rx.Observer;
import rx.functions.Action0;
import rx.functions.Action1;

// Referenced classes of package rx.subjects:
//            Subject, SubjectSubscriptionManager

public final class AsyncSubject extends Subject
{

    protected AsyncSubject(rx.Observable.OnSubscribe onsubscribe, SubjectSubscriptionManager subjectsubscriptionmanager, AtomicReference atomicreference)
    {
        super(onsubscribe);
        subscriptionManager = subjectsubscriptionmanager;
        lastNotification = atomicreference;
    }

    public static AsyncSubject create()
    {
        SubjectSubscriptionManager subjectsubscriptionmanager = new SubjectSubscriptionManager();
        AtomicReference atomicreference = new AtomicReference(new Notification());
        return new AsyncSubject(subjectsubscriptionmanager.getOnSubscribeFunc(new Action1() {

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
                AsyncSubject.emitValueToObserver((Notification)lastNotification.get(), subjectobserver);
            }

            final AtomicReference val$lastNotification;

            
            {
                lastNotification = atomicreference;
                super();
            }
        }
, null), subjectsubscriptionmanager, atomicreference);
    }

    protected static void emitValueToObserver(Notification notification, Observer observer)
    {
        notification.accept(observer);
        if(notification.isOnNext())
            observer.onCompleted();
    }

    public void onCompleted()
    {
        Collection collection = subscriptionManager.terminate(new Action0() {

            public void call()
            {
            }

            final AsyncSubject this$0;

            
            {
                this$0 = AsyncSubject.this;
                super();
            }
        }
);
        if(collection != null)
        {
            Observer observer;
            for(Iterator iterator = collection.iterator(); iterator.hasNext(); emitValueToObserver((Notification)lastNotification.get(), observer))
                observer = (Observer)iterator.next();

        }
    }

    public void onError(final Throwable e)
    {
        Collection collection = subscriptionManager.terminate(new Action0() {

            public void call()
            {
                lastNotification.set(Notification.createOnError(e));
            }

            final AsyncSubject this$0;
            final Throwable val$e;

            
            {
                this$0 = AsyncSubject.this;
                e = throwable;
                super();
            }
        }
);
        if(collection != null)
        {
            Observer observer;
            for(Iterator iterator = collection.iterator(); iterator.hasNext(); emitValueToObserver((Notification)lastNotification.get(), observer))
                observer = (Observer)iterator.next();

        }
    }

    public void onNext(Object obj)
    {
        lastNotification.set(Notification.createOnNext(obj));
    }

    final AtomicReference lastNotification;
    private final SubjectSubscriptionManager subscriptionManager;
}
