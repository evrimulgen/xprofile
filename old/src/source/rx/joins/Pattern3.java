// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package rx.joins;

import rx.Observable;
import rx.functions.Func3;

// Referenced classes of package rx.joins:
//            Pattern, Plan3, Plan0

public class Pattern3
    implements Pattern
{

    public Pattern3(Observable observable, Observable observable1, Observable observable2)
    {
        first = observable;
        second = observable1;
        third = observable2;
    }

    public Observable first()
    {
        return first;
    }

    public Observable second()
    {
        return second;
    }

    public Plan0 then(Func3 func3)
    {
        if(func3 == null)
            throw new NullPointerException();
        else
            return new Plan3(this, func3);
    }

    public Observable third()
    {
        return third;
    }

    private final Observable first;
    private final Observable second;
    private final Observable third;
}
