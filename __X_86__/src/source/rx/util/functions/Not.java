// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package rx.util.functions;


// Referenced classes of package rx.util.functions:
//            Func1

public class Not
    implements Func1
{

    public Not(Func1 func1)
    {
        predicate = func1;
    }

    public Boolean call(Object obj)
    {
        boolean flag;
        if(!((Boolean)predicate.call(obj)).booleanValue())
            flag = true;
        else
            flag = false;
        return Boolean.valueOf(flag);
    }

    public volatile Object call(Object obj)
    {
        return call(obj);
    }

    private final Func1 predicate;
}
