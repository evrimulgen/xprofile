// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package rx.operators;

import java.io.Serializable;
import rx.Observer;

public final class NotificationLite
{
    private static class OnErrorSentinel
        implements Serializable
    {

        private static final long serialVersionUID = 3L;
        private final Throwable e;


        public OnErrorSentinel(Throwable throwable)
        {
            e = throwable;
        }
    }


    private NotificationLite()
    {
    }

    public static NotificationLite instance()
    {
        return INSTANCE;
    }

    public void accept(Observer observer, Object obj)
    {
        static class _cls3
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
                catch(NoSuchFieldError nosuchfielderror1) { }
                try
                {
                    $SwitchMap$rx$Notification$Kind[rx.Notification.Kind.OnError.ordinal()] = 3;
                }
                catch(NoSuchFieldError nosuchfielderror2)
                {
                    return;
                }
            }
        }

        switch(_cls3..SwitchMap.rx.Notification.Kind[kind(obj).ordinal()])
        {
        default:
            return;

        case 1: // '\001'
            observer.onNext(getValue(obj));
            return;

        case 2: // '\002'
            observer.onCompleted();
            return;

        case 3: // '\003'
            observer.onError(getError(obj));
            break;
        }
    }

    public Object completed()
    {
        return ON_COMPLETED_SENTINEL;
    }

    public Object error(Throwable throwable)
    {
        return new OnErrorSentinel(throwable);
    }

    public Throwable getError(Object obj)
    {
        return ((OnErrorSentinel)obj).e;
    }

    public Object getValue(Object obj)
    {
        if(obj == ON_NEXT_NULL_SENTINEL)
            obj = null;
        return obj;
    }

    public boolean isCompleted(Object obj)
    {
        return obj == ON_COMPLETED_SENTINEL;
    }

    public boolean isError(Object obj)
    {
        return obj instanceof OnErrorSentinel;
    }

    public rx.Notification.Kind kind(Object obj)
    {
        if(obj == null)
            throw new IllegalArgumentException("The lite notification can not be null");
        if(obj == ON_COMPLETED_SENTINEL)
            return rx.Notification.Kind.OnCompleted;
        if(obj instanceof OnErrorSentinel)
            return rx.Notification.Kind.OnError;
        else
            return rx.Notification.Kind.OnNext;
    }

    public Object next(Object obj)
    {
        if(obj == null)
            obj = ON_NEXT_NULL_SENTINEL;
        return obj;
    }

    private static final NotificationLite INSTANCE = new NotificationLite();
    private static final Object ON_COMPLETED_SENTINEL = new Serializable() {

        private static final long serialVersionUID = 1L;

    }
;
    private static final Object ON_NEXT_NULL_SENTINEL = new Serializable() {

        private static final long serialVersionUID = 2L;

    }
;

}
