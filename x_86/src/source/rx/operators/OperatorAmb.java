// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package rx.operators;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import rx.Observable;
import rx.Subscriber;

public final class OperatorAmb
    implements rx.Observable.OnSubscribe
{
    private static final class AmbSubscriber extends Subscriber
    {

        private boolean isSelected()
        {
            int i = choice.get();
            if(i == -1)
                return choice.compareAndSet(-1, index);
            return i == index;
        }

        public void onCompleted()
        {
            if(!isSelected())
            {
                unsubscribe();
                return;
            } else
            {
                subscriber.onCompleted();
                return;
            }
        }

        public void onError(Throwable throwable)
        {
            if(!isSelected())
            {
                unsubscribe();
                return;
            } else
            {
                subscriber.onError(throwable);
                return;
            }
        }

        public void onNext(Object obj)
        {
            if(!isSelected())
            {
                unsubscribe();
                return;
            } else
            {
                subscriber.onNext(obj);
                return;
            }
        }

        private static final int NONE = -1;
        private final AtomicInteger choice;
        private final int index;
        private final Subscriber subscriber;

        private AmbSubscriber(Subscriber subscriber1, int i, AtomicInteger atomicinteger)
        {
            subscriber = subscriber1;
            choice = atomicinteger;
            index = i;
        }

    }


    private OperatorAmb(Iterable iterable)
    {
        sources = iterable;
    }

    public static rx.Observable.OnSubscribe amb(Iterable iterable)
    {
        return new OperatorAmb(iterable);
    }

    public static rx.Observable.OnSubscribe amb(Observable observable, Observable observable1)
    {
        ArrayList arraylist = new ArrayList();
        arraylist.add(observable);
        arraylist.add(observable1);
        return amb(((Iterable) (arraylist)));
    }

    public static rx.Observable.OnSubscribe amb(Observable observable, Observable observable1, Observable observable2)
    {
        ArrayList arraylist = new ArrayList();
        arraylist.add(observable);
        arraylist.add(observable1);
        arraylist.add(observable2);
        return amb(((Iterable) (arraylist)));
    }

    public static rx.Observable.OnSubscribe amb(Observable observable, Observable observable1, Observable observable2, Observable observable3)
    {
        ArrayList arraylist = new ArrayList();
        arraylist.add(observable);
        arraylist.add(observable1);
        arraylist.add(observable2);
        arraylist.add(observable3);
        return amb(((Iterable) (arraylist)));
    }

    public static rx.Observable.OnSubscribe amb(Observable observable, Observable observable1, Observable observable2, Observable observable3, Observable observable4)
    {
        ArrayList arraylist = new ArrayList();
        arraylist.add(observable);
        arraylist.add(observable1);
        arraylist.add(observable2);
        arraylist.add(observable3);
        arraylist.add(observable4);
        return amb(((Iterable) (arraylist)));
    }

    public static rx.Observable.OnSubscribe amb(Observable observable, Observable observable1, Observable observable2, Observable observable3, Observable observable4, Observable observable5)
    {
        ArrayList arraylist = new ArrayList();
        arraylist.add(observable);
        arraylist.add(observable1);
        arraylist.add(observable2);
        arraylist.add(observable3);
        arraylist.add(observable4);
        arraylist.add(observable5);
        return amb(((Iterable) (arraylist)));
    }

    public static rx.Observable.OnSubscribe amb(Observable observable, Observable observable1, Observable observable2, Observable observable3, Observable observable4, Observable observable5, Observable observable6)
    {
        ArrayList arraylist = new ArrayList();
        arraylist.add(observable);
        arraylist.add(observable1);
        arraylist.add(observable2);
        arraylist.add(observable3);
        arraylist.add(observable4);
        arraylist.add(observable5);
        arraylist.add(observable6);
        return amb(((Iterable) (arraylist)));
    }

    public static rx.Observable.OnSubscribe amb(Observable observable, Observable observable1, Observable observable2, Observable observable3, Observable observable4, Observable observable5, Observable observable6, Observable observable7)
    {
        ArrayList arraylist = new ArrayList();
        arraylist.add(observable);
        arraylist.add(observable1);
        arraylist.add(observable2);
        arraylist.add(observable3);
        arraylist.add(observable4);
        arraylist.add(observable5);
        arraylist.add(observable6);
        arraylist.add(observable7);
        return amb(((Iterable) (arraylist)));
    }

    public static rx.Observable.OnSubscribe amb(Observable observable, Observable observable1, Observable observable2, Observable observable3, Observable observable4, Observable observable5, Observable observable6, Observable observable7, 
            Observable observable8)
    {
        ArrayList arraylist = new ArrayList();
        arraylist.add(observable);
        arraylist.add(observable1);
        arraylist.add(observable2);
        arraylist.add(observable3);
        arraylist.add(observable4);
        arraylist.add(observable5);
        arraylist.add(observable6);
        arraylist.add(observable7);
        arraylist.add(observable8);
        return amb(((Iterable) (arraylist)));
    }

    public volatile void call(Object obj)
    {
        call((Subscriber)obj);
    }

    public void call(Subscriber subscriber)
    {
        AtomicInteger atomicinteger = new AtomicInteger(-1);
        int i = 0;
        Iterator iterator = sources.iterator();
        do
        {
            Observable observable;
            if(iterator.hasNext())
            {
                observable = (Observable)iterator.next();
                break MISSING_BLOCK_LABEL_44;
            }
            do
                return;
            while(subscriber.isUnsubscribed() || atomicinteger.get() != -1);
            AmbSubscriber ambsubscriber = new AmbSubscriber(subscriber, i, atomicinteger);
            subscriber.add(ambsubscriber);
            observable.unsafeSubscribe(ambsubscriber);
            i++;
        } while(true);
    }

    private final Iterable sources;
}
