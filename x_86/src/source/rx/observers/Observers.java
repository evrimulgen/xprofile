// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package rx.observers;

import rx.Observer;
import rx.exceptions.OnErrorNotImplementedException;
import rx.functions.Action0;
import rx.functions.Action1;

public class Observers
{

    public Observers()
    {
    }

    public static final Observer create(Action1 action1)
    {
        if(action1 == null)
            throw new IllegalArgumentException("onNext can not be null");
        else
            return new Observer(action1) {

                public final void onCompleted()
                {
                }

                public final void onError(Throwable throwable)
                {
                    throw new OnErrorNotImplementedException(throwable);
                }

                public final void onNext(Object obj)
                {
                    onNext.call(obj);
                }

                final Action1 val$onNext;

            
            {
                onNext = action1;
                super();
            }
            }
;
    }

    public static final Observer create(Action1 action1, Action1 action1_1)
    {
        if(action1 == null)
            throw new IllegalArgumentException("onNext can not be null");
        if(action1_1 == null)
            throw new IllegalArgumentException("onError can not be null");
        else
            return new Observer(action1_1, action1) {

                public final void onCompleted()
                {
                }

                public final void onError(Throwable throwable)
                {
                    onError.call(throwable);
                }

                public final void onNext(Object obj)
                {
                    onNext.call(obj);
                }

                final Action1 val$onError;
                final Action1 val$onNext;

            
            {
                onError = action1;
                onNext = action1_1;
                super();
            }
            }
;
    }

    public static final Observer create(Action1 action1, Action1 action1_1, Action0 action0)
    {
        if(action1 == null)
            throw new IllegalArgumentException("onNext can not be null");
        if(action1_1 == null)
            throw new IllegalArgumentException("onError can not be null");
        if(action0 == null)
            throw new IllegalArgumentException("onComplete can not be null");
        else
            return new Observer(action0, action1_1, action1) {

                public final void onCompleted()
                {
                    onComplete.call();
                }

                public final void onError(Throwable throwable)
                {
                    onError.call(throwable);
                }

                public final void onNext(Object obj)
                {
                    onNext.call(obj);
                }

                final Action0 val$onComplete;
                final Action1 val$onError;
                final Action1 val$onNext;

            
            {
                onComplete = action0;
                onError = action1;
                onNext = action1_1;
                super();
            }
            }
;
    }

    public static Observer empty()
    {
        return EMPTY;
    }

    private static final Observer EMPTY = new Observer() {

        public final void onCompleted()
        {
        }

        public final void onError(Throwable throwable)
        {
            throw new OnErrorNotImplementedException(throwable);
        }

        public final void onNext(Object obj)
        {
        }

    }
;

}
