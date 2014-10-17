// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package rx.operators;

import rx.Notification;
import rx.Observable;
import rx.functions.*;

public class OperationSequenceEqual
{

    public OperationSequenceEqual()
    {
    }

    public static Observable sequenceEqual(Observable observable, Observable observable1, Func2 func2)
    {
        return Observable.zip(Observable.concat(observable.map(new Func1() {

            public volatile Object call(Object obj)
            {
                return call(obj);
            }

            public Notification call(Object obj)
            {
                return new Notification(obj);
            }

        }
), Observable.from(new Notification())), Observable.concat(observable1.map(new Func1() {

            public volatile Object call(Object obj)
            {
                return call(obj);
            }

            public Notification call(Object obj)
            {
                return new Notification(obj);
            }

        }
), Observable.from(new Notification())), new Func2(func2) {

            public Boolean call(Notification notification, Notification notification1)
            {
                if(notification.isOnCompleted() && notification1.isOnCompleted())
                    return Boolean.valueOf(true);
                if(notification.isOnCompleted() || notification1.isOnCompleted())
                    return Boolean.valueOf(false);
                else
                    return (Boolean)equality.call(notification.getValue(), notification1.getValue());
            }

            public volatile Object call(Object obj, Object obj1)
            {
                return call((Notification)obj, (Notification)obj1);
            }

            final Func2 val$equality;

            
            {
                equality = func2;
                Object();
            }
        }
).all(Functions.identity());
    }
}
