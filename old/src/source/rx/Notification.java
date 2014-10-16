// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package rx;


// Referenced classes of package rx:
//            Observer

public class Notification
{
    public static final class Kind extends Enum
    {

        public static Kind valueOf(String s)
        {
            return (Kind)Enum.valueOf(rx/Notification$Kind, s);
        }

        public static Kind[] values()
        {
            return (Kind[])$VALUES.clone();
        }

        private static final Kind $VALUES[];
        public static final Kind OnCompleted;
        public static final Kind OnError;
        public static final Kind OnNext;

        static 
        {
            OnNext = new Kind("OnNext", 0);
            OnError = new Kind("OnError", 1);
            OnCompleted = new Kind("OnCompleted", 2);
            Kind akind[] = new Kind[3];
            akind[0] = OnNext;
            akind[1] = OnError;
            akind[2] = OnCompleted;
            $VALUES = akind;
        }

        private Kind(String s, int i)
        {
            super(s, i);
        }
    }


    public Notification()
    {
        throwable = null;
        value = null;
        kind = Kind.OnCompleted;
    }

    public Notification(Object obj)
    {
        value = obj;
        throwable = null;
        kind = Kind.OnNext;
    }

    public Notification(Throwable throwable1)
    {
        throwable = throwable1;
        value = null;
        kind = Kind.OnError;
    }

    private Notification(Kind kind1, Object obj, Throwable throwable1)
    {
        value = obj;
        throwable = throwable1;
        kind = kind1;
    }

    public static Notification createOnCompleted()
    {
        return ON_COMPLETED;
    }

    public static Notification createOnCompleted(Class class1)
    {
        return ON_COMPLETED;
    }

    public static Notification createOnError(Throwable throwable1)
    {
        return new Notification(Kind.OnError, null, throwable1);
    }

    public static Notification createOnNext(Object obj)
    {
        return new Notification(Kind.OnNext, obj, null);
    }

    public void accept(Observer observer)
    {
        if(isOnNext())
        {
            observer.onNext(getValue());
        } else
        {
            if(isOnCompleted())
            {
                observer.onCompleted();
                return;
            }
            if(isOnError())
            {
                observer.onError(getThrowable());
                return;
            }
        }
    }

    public boolean equals(Object obj)
    {
        if(obj != null)
        {
            if(this == obj)
                return true;
            if(obj.getClass() == getClass())
            {
                Notification notification = (Notification)obj;
                if(notification.getKind() == getKind() && (!hasValue() || getValue().equals(notification.getValue())) && (!hasThrowable() || getThrowable().equals(notification.getThrowable())))
                    return true;
            }
        }
        return false;
    }

    public Kind getKind()
    {
        return kind;
    }

    public Throwable getThrowable()
    {
        return throwable;
    }

    public Object getValue()
    {
        return value;
    }

    public boolean hasThrowable()
    {
        return isOnError() && throwable != null;
    }

    public boolean hasValue()
    {
        return isOnNext() && value != null;
    }

    public int hashCode()
    {
        int i = getKind().hashCode();
        if(hasValue())
            i = i * 31 + getValue().hashCode();
        if(hasThrowable())
            i = i * 31 + getThrowable().hashCode();
        return i;
    }

    public boolean isOnCompleted()
    {
        return getKind() == Kind.OnCompleted;
    }

    public boolean isOnError()
    {
        return getKind() == Kind.OnError;
    }

    public boolean isOnNext()
    {
        return getKind() == Kind.OnNext;
    }

    public String toString()
    {
        StringBuilder stringbuilder = (new StringBuilder("[")).append(super.toString()).append(" ").append(getKind());
        if(hasValue())
            stringbuilder.append(" ").append(getValue());
        if(hasThrowable())
            stringbuilder.append(" ").append(getThrowable().getMessage());
        stringbuilder.append("]");
        return stringbuilder.toString();
    }

    private static final Notification ON_COMPLETED;
    private final Kind kind;
    private final Throwable throwable;
    private final Object value;

    static 
    {
        ON_COMPLETED = new Notification(Kind.OnCompleted, null, null);
    }
}
