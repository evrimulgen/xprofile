// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package rx.joins;

import rx.Observable;
import rx.functions.Func2;

// Referenced classes of package rx.joins:
//            Pattern, Pattern3, Plan2, Plan0

public class Pattern2
    implements Pattern
{

    public Pattern2(Observable observable, Observable observable1)
    {
        first = observable;
        second = observable1;
    }

    public Pattern3 and(Observable observable)
    {
        if(observable == null)
            throw new NullPointerException();
        else
            return new Pattern3(first, second, observable);
    }

    public Observable first()
    {
        return first;
    }

    public Observable second()
    {
        return second;
    }

    public Plan0 then(Func2 func2)
    {
        if(func2 == null)
            throw new NullPointerException();
        else
            return new Plan2(this, func2);
    }

    private final Observable first;
    private final Observable second;
}
