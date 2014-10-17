// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package rx.operators;

import java.util.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import rx.*;
import rx.functions.*;
import rx.observers.Subscribers;
import rx.subscriptions.CompositeSubscription;

// Referenced classes of package rx.operators:
//            SafeObservableSubscription

public class OperationCombineLatest
{
    static final class CombineLatest
        implements rx.Observable.OnSubscribeFunc
    {

        public Subscription onSubscribe(Observer observer)
        {
            CompositeSubscription compositesubscription = new CompositeSubscription();
            Collector collector = new Collector(observer, compositesubscription, sources.size());
            int i = 0;
            ArrayList arraylist = new ArrayList(1 + sources.size());
            for(Iterator iterator = sources.iterator(); iterator.hasNext();)
            {
                Observable observable = (Observable)iterator.next();
                SafeObservableSubscription safeobservablesubscription = new SafeObservableSubscription();
                compositesubscription.add(safeobservablesubscription);
                arraylist.add(new SourceObserver(collector, safeobservablesubscription, i, observable));
                i++;
            }

            Iterator iterator1 = arraylist.iterator();
            do
            {
                if(!iterator1.hasNext())
                    break;
                SourceObserver sourceobserver = (SourceObserver)iterator1.next();
                if(!compositesubscription.isUnsubscribed())
                    sourceobserver.connect();
            } while(true);
            return compositesubscription;
        }

        final FuncN combiner;
        final List sources = new ArrayList();

        public CombineLatest(Iterable iterable, FuncN funcn)
        {
            combiner = funcn;
            Observable observable;
            for(Iterator iterator = iterable.iterator(); iterator.hasNext(); sources.add(observable))
                observable = (Observable)iterator.next();

        }
    }

    final class CombineLatest.Collector
    {

        public void completed(int i)
        {
            lock.lock();
            int j;
            int k;
            if(!completed.get(i))
            {
                completed.set(i);
                completedCount = 1 + completedCount;
            }
            if(!hasValue.get(i))
                break MISSING_BLOCK_LABEL_72;
            j = completedCount;
            k = values.length;
            boolean flag1;
            flag1 = false;
            if(j != k)
                break MISSING_BLOCK_LABEL_91;
            boolean flag = isTerminated();
            flag1 = false;
            if(flag)
                break MISSING_BLOCK_LABEL_91;
            terminate();
            flag1 = true;
            lock.unlock();
            if(flag1)
            {
                observer.onCompleted();
                cancel.unsubscribe();
            }
            return;
            Exception exception;
            exception;
            lock.unlock();
            throw exception;
        }

        public void error(int i, Throwable throwable)
        {
            lock.lock();
            boolean flag = isTerminated();
            boolean flag1;
            flag1 = false;
            if(flag)
                break MISSING_BLOCK_LABEL_30;
            terminate();
            flag1 = true;
            lock.unlock();
            if(flag1)
            {
                observer.onError(throwable);
                cancel.unsubscribe();
            }
            return;
            Exception exception;
            exception;
            lock.unlock();
            throw exception;
        }

        boolean isTerminated()
        {
            return completedCount == 1 + values.length;
        }

        public void next(int i, Object obj)
        {
            lock.lock();
            boolean flag = isTerminated();
            Throwable throwable;
            throwable = null;
            if(flag)
                break MISSING_BLOCK_LABEL_113;
            int j;
            int k;
            values[i] = obj;
            if(!hasValue.get(i))
            {
                hasValue.set(i);
                hasCount = 1 + hasCount;
            }
            j = hasCount;
            k = values.length;
            throwable = null;
            if(j != k)
                break MISSING_BLOCK_LABEL_113;
            observer.onNext(combiner.call((Object[])((Object []) (values)).clone()));
_L2:
            lock.unlock();
            if(throwable != null)
            {
                observer.onError(throwable);
                cancel.unsubscribe();
            }
            return;
            Throwable throwable1;
            throwable1;
            terminate();
            throwable = throwable1;
            if(true) goto _L2; else goto _L1
_L1:
            Exception exception;
            exception;
            lock.unlock();
            throw exception;
        }

        void terminate()
        {
            completedCount = 1 + values.length;
            Arrays.fill(values, null);
        }

        final Subscription cancel;
        final BitSet completed;
        int completedCount;
        int hasCount;
        final BitSet hasValue;
        final Lock lock = new ReentrantLock();
        final Observer observer;
        final CombineLatest this$0;
        final Object values[];

        public CombineLatest.Collector(Observer observer1, Subscription subscription, int i)
        {
            this$0 = CombineLatest.this;
            super();
            observer = observer1;
            cancel = subscription;
            values = new Object[i];
            hasValue = new BitSet(i);
            completed = new BitSet(i);
        }
    }

    final class CombineLatest.SourceObserver extends Subscriber
    {

        void connect()
        {
            self.wrap(source.unsafeSubscribe(Subscribers.from(this)));
            source = null;
        }

        public void onCompleted()
        {
            collector.completed(index);
            self.unsubscribe();
        }

        public void onError(Throwable throwable)
        {
            collector.error(index, throwable);
        }

        public void onNext(Object obj)
        {
            collector.next(index, obj);
        }

        final CombineLatest.Collector collector;
        final int index;
        final SafeObservableSubscription self;
        Observable source;
        final CombineLatest this$0;

        public CombineLatest.SourceObserver(CombineLatest.Collector collector1, SafeObservableSubscription safeobservablesubscription, int i, Observable observable)
        {
            this$0 = CombineLatest.this;
            super();
            self = safeobservablesubscription;
            collector = collector1;
            index = i;
            source = observable;
        }
    }


    public OperationCombineLatest()
    {
    }

    public static rx.Observable.OnSubscribeFunc combineLatest(Observable observable, Observable observable1, Observable observable2, Observable observable3, Observable observable4, Observable observable5, Observable observable6, Observable observable7, 
            Observable observable8, Func9 func9)
    {
        return new CombineLatest(Arrays.asList(new Observable[] {
            observable, observable1, observable2, observable3, observable4, observable5, observable6, observable7, observable8
        }), Functions.fromFunc(func9));
    }

    public static rx.Observable.OnSubscribeFunc combineLatest(Observable observable, Observable observable1, Observable observable2, Observable observable3, Observable observable4, Observable observable5, Observable observable6, Observable observable7, 
            Func8 func8)
    {
        return new CombineLatest(Arrays.asList(new Observable[] {
            observable, observable1, observable2, observable3, observable4, observable5, observable6, observable7
        }), Functions.fromFunc(func8));
    }

    public static rx.Observable.OnSubscribeFunc combineLatest(Observable observable, Observable observable1, Observable observable2, Observable observable3, Observable observable4, Observable observable5, Observable observable6, Func7 func7)
    {
        return new CombineLatest(Arrays.asList(new Observable[] {
            observable, observable1, observable2, observable3, observable4, observable5, observable6
        }), Functions.fromFunc(func7));
    }

    public static rx.Observable.OnSubscribeFunc combineLatest(Observable observable, Observable observable1, Observable observable2, Observable observable3, Observable observable4, Observable observable5, Func6 func6)
    {
        return new CombineLatest(Arrays.asList(new Observable[] {
            observable, observable1, observable2, observable3, observable4, observable5
        }), Functions.fromFunc(func6));
    }

    public static rx.Observable.OnSubscribeFunc combineLatest(Observable observable, Observable observable1, Observable observable2, Observable observable3, Observable observable4, Func5 func5)
    {
        return new CombineLatest(Arrays.asList(new Observable[] {
            observable, observable1, observable2, observable3, observable4
        }), Functions.fromFunc(func5));
    }

    public static rx.Observable.OnSubscribeFunc combineLatest(Observable observable, Observable observable1, Observable observable2, Observable observable3, Func4 func4)
    {
        return new CombineLatest(Arrays.asList(new Observable[] {
            observable, observable1, observable2, observable3
        }), Functions.fromFunc(func4));
    }

    public static rx.Observable.OnSubscribeFunc combineLatest(Observable observable, Observable observable1, Observable observable2, Func3 func3)
    {
        return new CombineLatest(Arrays.asList(new Observable[] {
            observable, observable1, observable2
        }), Functions.fromFunc(func3));
    }

    public static rx.Observable.OnSubscribeFunc combineLatest(Observable observable, Observable observable1, Func2 func2)
    {
        return new CombineLatest(Arrays.asList(new Observable[] {
            observable, observable1
        }), Functions.fromFunc(func2));
    }
}
