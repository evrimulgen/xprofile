// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package rx.operators;

import java.util.Iterator;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import rx.Observable;
import rx.Subscriber;
import rx.exceptions.Exceptions;

public final class BlockingOperatorMostRecent
{
    private static class MostRecentIterator
        implements Iterator
    {

        public boolean hasNext()
        {
            return !observer.isCompleted();
        }

        public Object next()
        {
            if(observer.getThrowable() != null)
                throw Exceptions.propagate(observer.getThrowable());
            else
                return observer.getRecentValue();
        }

        public void remove()
        {
            throw new UnsupportedOperationException("Read only iterator");
        }

        private final MostRecentObserver observer;

        private MostRecentIterator(MostRecentObserver mostrecentobserver)
        {
            observer = mostrecentobserver;
        }

    }

    private static class MostRecentObserver extends Subscriber
    {

        private Object getRecentValue()
        {
            return value.get();
        }

        private Throwable getThrowable()
        {
            return (Throwable)exception.get();
        }

        private boolean isCompleted()
        {
            return completed.get();
        }

        public void onCompleted()
        {
            completed.set(true);
        }

        public void onError(Throwable throwable)
        {
            exception.set(throwable);
        }

        public void onNext(Object obj)
        {
            value.set(obj);
        }

        private final AtomicBoolean completed;
        private final AtomicReference exception;
        private final AtomicReference value;




        private MostRecentObserver(Object obj)
        {
            completed = new AtomicBoolean(false);
            exception = new AtomicReference();
            value = new AtomicReference(obj);
        }

    }


    public BlockingOperatorMostRecent()
    {
    }

    public static Iterable mostRecent(Observable observable, Object obj)
    {
        return new Iterable(obj, observable) {

            public Iterator iterator()
            {
                MostRecentObserver mostrecentobserver = new MostRecentObserver(initialValue);
                MostRecentIterator mostrecentiterator = new MostRecentIterator(mostrecentobserver);
                source.subscribe(mostrecentobserver);
                return mostrecentiterator;
            }

            final Object val$initialValue;
            final Observable val$source;

            
            {
                initialValue = obj;
                source = observable;
                super();
            }
        }
;
    }
}
