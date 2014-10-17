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

public final class BehaviorSubject extends Subject
{

    protected BehaviorSubject(rx.Observable.OnSubscribe onsubscribe, SubjectSubscriptionManager subjectsubscriptionmanager, AtomicReference atomicreference)
    {
        super(onsubscribe);
        subscriptionManager = subjectsubscriptionmanager;
        lastNotification = atomicreference;
    }

    public static BehaviorSubject create(Object obj)
    {
        SubjectSubscriptionManager subjectsubscriptionmanager = new SubjectSubscriptionManager();
        AtomicReference atomicreference = new AtomicReference(new Notification(obj));
        return new BehaviorSubject(subjectsubscriptionmanager.getOnSubscribeFunc(new Action1(atomicreference) {

            public volatile void call(Object obj1)
            {
                call((SubjectSubscriptionManager.SubjectObserver)obj1);
            }

            public void call(SubjectSubscriptionManager.SubjectObserver subjectobserver)
            {
                Notification notification = (Notification)lastNotification.get();
                if(notification.isOnNext())
                    notification.accept(subjectobserver);
            }

            final AtomicReference val$lastNotification;

            
            {
                lastNotification = atomicreference;
                super();
            }
        }
, new Action1(atomicreference) {

            public volatile void call(Object obj1)
            {
                call((SubjectSubscriptionManager.SubjectObserver)obj1);
            }

            public void call(SubjectSubscriptionManager.SubjectObserver subjectobserver)
            {
                ((Notification)lastNotification.get()).accept(subjectobserver);
            }

            final AtomicReference val$lastNotification;

            
            {
                lastNotification = atomicreference;
                super();
            }
        }
, null), subjectsubscriptionmanager, atomicreference);
    }

    public static BehaviorSubject createWithDefaultValue(Object obj)
    {
        return create(obj);
    }

    public void onCompleted()
    {
        Collection collection = subscriptionManager.terminate(new Action0() {

            public void call()
            {
                lastNotification.set(Notification.createOnCompleted());
            }

            final BehaviorSubject this$0;

            
            {
                this$0 = BehaviorSubject.this;
                super();
            }
        }
);
        if(collection != null)
        {
            for(Iterator iterator = collection.iterator(); iterator.hasNext(); ((Observer)iterator.next()).onCompleted());
        }
    }

    public void onError(final Throwable e)
    {
        Collection collection = subscriptionManager.terminate(new Action0() {

            public void call()
            {
                lastNotification.set(Notification.createOnError(e));
            }

            final BehaviorSubject this$0;
            final Throwable val$e;

            
            {
                this$0 = BehaviorSubject.this;
                e = throwable;
                super();
            }
        }
);
        if(collection != null)
        {
            for(Iterator iterator = collection.iterator(); iterator.hasNext(); ((Observer)iterator.next()).onError(e));
        }
    }

    public void onNext(Object obj)
    {
        if(((Notification)lastNotification.get()).isOnNext())
        {
            lastNotification.set(Notification.createOnNext(obj));
            SubjectSubscriptionManager.SubjectObserver asubjectobserver[] = subscriptionManager.rawSnapshot();
            int i = asubjectobserver.length;
            for(int j = 0; j < i; j++)
                asubjectobserver[j].onNext(obj);

        }
    }

    final AtomicReference lastNotification;
    private final SubjectSubscriptionManager subscriptionManager;
}
