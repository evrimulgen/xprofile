// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package rx.operators;

import rx.Scheduler;
import rx.Subscriber;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.subscriptions.*;

public class OperatorUnsubscribeOn
    implements rx.Observable.Operator
{

    public OperatorUnsubscribeOn(Scheduler scheduler1)
    {
        scheduler = scheduler1;
    }

    public volatile Object call(Object obj)
    {
        return call((Subscriber)obj);
    }

    public Subscriber call(Subscriber subscriber)
    {
        final CompositeSubscription parentSubscription = new CompositeSubscription();
        subscriber.add(Subscriptions.create(new Action0() {

            public void call()
            {
                MultipleAssignmentSubscription multipleassignmentsubscription = new MultipleAssignmentSubscription();
                multipleassignmentsubscription.set(scheduler.schedule(multipleassignmentsubscription. new Action1() {

                    public volatile void call(Object obj)
                    {
                        call((rx.Scheduler.Inner)obj);
                    }

                    public void call(rx.Scheduler.Inner inner)
                    {
                        parentSubscription.unsubscribe();
                        mas.unsubscribe();
                    }

                    final _cls1 this$1;
                    final MultipleAssignmentSubscription val$mas;

            
            {
                this$1 = final__pcls1;
                mas = MultipleAssignmentSubscription.this;
                super();
            }
                }
));
            }

            final OperatorUnsubscribeOn this$0;
            final CompositeSubscription val$parentSubscription;

            
            {
                this$0 = OperatorUnsubscribeOn.this;
                parentSubscription = compositesubscription;
                super();
            }
        }
));
        return new Subscriber(subscriber) {

            public void onCompleted()
            {
                subscriber.onCompleted();
            }

            public void onError(Throwable throwable)
            {
                subscriber.onError(throwable);
            }

            public void onNext(Object obj)
            {
                subscriber.onNext(obj);
            }

            final OperatorUnsubscribeOn this$0;
            final Subscriber val$subscriber;

            
            {
                this$0 = OperatorUnsubscribeOn.this;
                subscriber = subscriber1;
                super(final_compositesubscription);
            }
        }
;
    }

    private final Scheduler scheduler;

}
