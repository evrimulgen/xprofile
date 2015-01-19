// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package rx.operators;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicLong;
import rx.*;
import rx.exceptions.OnErrorThrowable;
import rx.functions.*;
import rx.subscriptions.CompositeSubscription;

// Referenced classes of package rx.operators:
//            NotificationLite

public final class OperatorZip
    implements rx.Observable.Operator
{
    private static class Zip
    {

        void tick()
        {
            if(counter.getAndIncrement() != 0L) goto _L2; else goto _L1
_L1:
            Object aobj[];
            boolean flag;
            int i;
            aobj = new Object[observers.length];
            flag = true;
            i = 0;
_L7:
            if(i >= observers.length) goto _L4; else goto _L3
_L3:
            Object obj = ((InnerObserver)observers[i]).items.peek();
            if(obj != null) goto _L6; else goto _L5
_L5:
            flag = false;
_L8:
            i++;
              goto _L7
_L6:
            static class _cls2
            {

                static final int $SwitchMap$rx$Notification$Kind[];

                static 
                {
                    $SwitchMap$rx$Notification$Kind = new int[rx.Notification.Kind.values().length];
                    try
                    {
                        $SwitchMap$rx$Notification$Kind[rx.Notification.Kind.OnNext.ordinal()] = 1;
                    }
                    catch(NoSuchFieldError nosuchfielderror) { }
                    try
                    {
                        $SwitchMap$rx$Notification$Kind[rx.Notification.Kind.OnCompleted.ordinal()] = 2;
                    }
                    catch(NoSuchFieldError nosuchfielderror1)
                    {
                        return;
                    }
                }
            }

            _cls2..SwitchMap.rx.Notification.Kind[OperatorZip.on.kind(obj).ordinal()];
            JVM INSTR tableswitch 1 2: default 100
        //                       1 103
        //                       2 117;
               goto _L8 _L9 _L10
_L9:
            aobj[i] = OperatorZip.on.getValue(obj);
              goto _L8
_L10:
            observer.onCompleted();
            childSubscription.unsubscribe();
_L2:
            return;
_L4:
            if(flag)
            {
                int j;
                try
                {
                    observer.onNext(zipFunction.call(aobj));
                }
                catch(Throwable throwable)
                {
                    observer.onError(OnErrorThrowable.addValueAsLastCause(throwable, ((Object) (aobj))));
                    return;
                }
                for(j = 0; j < observers.length; j++)
                {
                    ((InnerObserver)observers[j]).items.poll();
                    if(OperatorZip.on.isCompleted(((InnerObserver)observers[j]).items.peek()))
                    {
                        observer.onCompleted();
                        childSubscription.unsubscribe();
                        return;
                    }
                }

            }
            if(counter.decrementAndGet() <= 0L)
                return;
            if(true) goto _L1; else goto _L11
_L11:
        }

        public void zip()
        {
            for(int i = 0; i < os.length; i++)
                os[i].unsafeSubscribe((InnerObserver)observers[i]);

        }

        final CompositeSubscription childSubscription = new CompositeSubscription();
        final AtomicLong counter = new AtomicLong(0L);
        final Observer observer;
        final Object observers[];
        final Observable os[];
        final FuncN zipFunction;

        public Zip(Observable aobservable[], Subscriber subscriber, FuncN funcn)
        {
            os = aobservable;
            observer = subscriber;
            zipFunction = funcn;
            observers = new Object[aobservable.length];
            for(int i = 0; i < aobservable.length; i++)
            {
                InnerObserver innerobserver = new InnerObserver();
                observers[i] = innerobserver;
                childSubscription.add(innerobserver);
            }

            subscriber.add(childSubscription);
        }
    }

    final class Zip.InnerObserver extends Subscriber
    {

        public void onCompleted()
        {
            items.add(OperatorZip.on.completed());
            tick();
        }

        public void onError(Throwable throwable)
        {
            observer.onError(throwable);
        }

        public void onNext(Object obj)
        {
            items.add(OperatorZip.on.next(obj));
            tick();
        }

        final ConcurrentLinkedQueue items = new ConcurrentLinkedQueue();
        final Zip this$0;

        Zip.InnerObserver()
        {
            this$0 = Zip.this;
            super();
        }
    }


    public OperatorZip(Func2 func2)
    {
        zipFunction = Functions.fromFunc(func2);
    }

    public OperatorZip(Func3 func3)
    {
        zipFunction = Functions.fromFunc(func3);
    }

    public OperatorZip(Func4 func4)
    {
        zipFunction = Functions.fromFunc(func4);
    }

    public OperatorZip(Func5 func5)
    {
        zipFunction = Functions.fromFunc(func5);
    }

    public OperatorZip(Func6 func6)
    {
        zipFunction = Functions.fromFunc(func6);
    }

    public OperatorZip(Func7 func7)
    {
        zipFunction = Functions.fromFunc(func7);
    }

    public OperatorZip(Func8 func8)
    {
        zipFunction = Functions.fromFunc(func8);
    }

    public OperatorZip(Func9 func9)
    {
        zipFunction = Functions.fromFunc(func9);
    }

    public OperatorZip(FuncN funcn)
    {
        zipFunction = funcn;
    }

    public volatile Object call(Object obj)
    {
        return call((Subscriber)obj);
    }

    public Subscriber call(final Subscriber final_subscriber)
    {
        return new Subscriber(final_subscriber) {

            public void onCompleted()
            {
                if(!started)
                    observer.onCompleted();
            }

            public void onError(Throwable throwable)
            {
                observer.onError(throwable);
            }

            public volatile void onNext(Object obj)
            {
                onNext((Observable[])obj);
            }

            public void onNext(Observable aobservable[])
            {
                if(aobservable == null || aobservable.length == 0)
                {
                    observer.onCompleted();
                    return;
                } else
                {
                    started = true;
                    (new Zip(aobservable, observer, zipFunction)).zip();
                    return;
                }
            }

            boolean started;
            final OperatorZip this$0;
            final Subscriber val$observer;

            
            {
                this$0 = OperatorZip.this;
                observer = subscriber1;
                super(final_subscriber);
                started = false;
            }
        }
;
    }

    static final NotificationLite on = NotificationLite.instance();
    final FuncN zipFunction;

}
