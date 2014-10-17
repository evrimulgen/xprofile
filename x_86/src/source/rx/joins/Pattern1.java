// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package rx.joins;

import rx.Observable;
import rx.functions.Func1;

// Referenced classes of package rx.joins:
//            Pattern, Plan1, Plan0

public class Pattern1
    implements Pattern
{

    public Pattern1(Observable observable)
    {
        first = observable;
    }

    public Observable first()
    {
        return first;
    }

    public Plan0 then(Func1 func1)
    {
        if(func1 == null)
            throw new NullPointerException();
        else
            return new Plan1(this, func1);
    }

    private final Observable first;
}
